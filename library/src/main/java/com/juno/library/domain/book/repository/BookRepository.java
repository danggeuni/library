package com.juno.library.domain.book.repository;

import com.juno.library.domain.book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BookEntity save(BookEntity entity) {
        jdbcTemplate.update("INSERT INTO BOOK (NAME, AUTHOR, CATEGORY_CODE, IS_RENTAL) VALUES (?, ?, ?, ?)",
                entity.getName(), entity.getAuthor(), entity.getCategory(), entity.getIsRental());

        return entity;
    }

    public List<BookEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM BOOK", bookEntityRowMapper());
    }

    public BookEntity findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM BOOK WHERE ID = ?", new Object[]{id}, bookEntityRowMapper());
    }

    public List<BookEntity> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM BOOK WHERE NAME = ?", new Object[]{name}, bookEntityRowMapper());
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM BOOK WHERE ID = ?", id);
    }

//    ==================================================================================================================

    @Transactional
    public void rentalBook(Long bookId, Long userId){
        int update = jdbcTemplate.update("UPDATE BOOK SET IS_RENTAL = TRUE WHERE ID = ? AND IS_RENTAL = FALSE", bookId);

        if(update == 0){
            throw new RuntimeException("책 대여에 실패했습니다.");
        }

        jdbcTemplate.update("INSERT INTO BOOK_RENTAL (USER_ID, BOOK_ID, RENTAL_DATE) VALUES (?, ?, ?)", userId, bookId, LocalDateTime.now());
    }

    @Transactional
    public void returnBook(Long bookId){
        int update = jdbcTemplate.update("UPDATE BOOK SET IS_RENTAL = FALSE WHERE ID = ? AND IS_RENTAL = TRUE", bookId);

        if(update == 0){
            throw new RuntimeException("책 반납에 실패했습니다.");
        }

        jdbcTemplate.update("UPDATE BOOK_RENTAL SET RETURN_DATE = ? WHERE BOOK_ID = ? AND RETURN_DATE IS NULL", LocalDateTime.now(), bookId);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<BookEntity> paging(String searchQuery, int pageSize, int pageNumber) {
        int offset = (pageNumber - 1) * pageSize;

        if (searchQuery == null) {
            return jdbcTemplate.query("SELECT * FROM BOOK LIMIT ? OFFSET ?",
                    new Object[]{pageSize, offset}, bookEntityRowMapper());
        } else {
            return jdbcTemplate.query("SELECT * FROM BOOK LIMIT ? OFFSET ? WHERE NAME LIKE ?",
                    new Object[]{pageSize, offset, "%" + searchQuery + "%"}, bookEntityRowMapper());
        }

    }

    public int getTotalPage(String searchQuery, int pageSize) {
        int toTalPage;

        if (searchQuery == null) {
            toTalPage = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BOOK", Integer.class);
        } else {
            toTalPage = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BOOK WHERE NAME LIKE ?", new Object[]{"%" + searchQuery + "%"}, Integer.class);
        }

        return (int) Math.ceil((double) toTalPage / pageSize);
    }

    RowMapper<BookEntity> bookEntityRowMapper() {
        return (rs, rowNum) -> new BookEntity(rs.getLong("ID"), rs.getString("NAME"),
                rs.getString("AUTHOR"), rs.getLong("CATEGORY_CODE"), rs.getBoolean("IS_RENTAL"));
    }
}
