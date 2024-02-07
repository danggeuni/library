package com.juno.library.domain.category.repository;

import com.juno.library.domain.category.CategoryEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public CategoryEntity save(CategoryEntity entity){
        jdbcTemplate.update("INSERT INTO BOOK_CATEGORY (CODE, NAME) VALUES (?, ?)", entity.getCode(), entity.getName());

        return entity;
    }

    public List<CategoryEntity> findALl(){
        return jdbcTemplate.query("SELECT * FROM BOOK_CATEGORY", categoryEntityRowMapper());
    }

    public CategoryEntity findByCode(Long id){
        return jdbcTemplate.queryForObject("SELECT * FROM BOOK_CATEGORY WHERE CODE = ?", new Object[]{id}, categoryEntityRowMapper());
    }

    public void deleteByCode(Long code){
        jdbcTemplate.update("DELETE FROM BOOK_CATEGORY WHERE CODE = ?", code);
    }

    public RowMapper<CategoryEntity> categoryEntityRowMapper(){
        return (rs, rowNum) -> new CategoryEntity(rs.getLong("CODE"), rs.getString("NAME"));
    }
}
