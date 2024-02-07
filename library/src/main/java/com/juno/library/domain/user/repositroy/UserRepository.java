package com.juno.library.domain.user.repositroy;


import com.juno.library.controller.userdto.UpdateUserDto;
import com.juno.library.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserEntity save(UserEntity entity){
        jdbcTemplate.update("INSERT INTO USER (NAME, ADDRESS, PHONE) VALUES (?, ?, ?)",
                entity.getName(), entity.getAddress(), entity.getPhone());

        return entity;
    }

    public List<UserEntity> findAll(){
        return jdbcTemplate.query("SELECT * FROM USER", userEntityRowMapper());
    }

    public UserEntity findById(Long id){
        return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE ID = ?", new Object[]{id}, userEntityRowMapper());
    }

    public List<UserEntity> findByName(String name){
        return jdbcTemplate.query("SELECT * FROM USER WHERE NAME = ?", new Object[]{name} ,userEntityRowMapper());
    }

    public void deleteUser(Long id){
        jdbcTemplate.update("DELETE FROM USER WHERE ID = ?", id);
    }

    public UserEntity updateUser(Long id, UpdateUserDto dto){
        jdbcTemplate.update("UPDATE USER SET NAME = ?, ADDRESS = ?, PHONE = ? WHERE ID = ?",
                dto.getName(), dto.getAddress(), dto.getPhone(), id);

        return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE ID = ?", new Object[]{id}, userEntityRowMapper());
    }

    public List<UserEntity> paging(int pageSize, int pageNumber){
        int offset = (pageNumber - 1) * pageSize;

        return jdbcTemplate.query("SELECT * FROM USER LIMIT ? OFFSET ?", new Object[]{pageSize, offset}, userEntityRowMapper());
    }

    public int getTotalPage(int pageSize){
        int totalRows = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER", Integer.class);

        return (int) Math.ceil((double) totalRows / pageSize);
    }

    RowMapper<UserEntity> userEntityRowMapper(){
        return (rs, rowNum) -> new UserEntity(rs.getLong("ID"), rs.getString("NAME"),
                rs.getString("ADDRESS"), rs.getString("PHONE"));
    }
}
