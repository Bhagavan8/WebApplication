package com.web.ecommerce.databaseutil;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.web.ecommerce.entity.User;

@Component
public class DatabaseUtil {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean validateUser(String username, String password) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, username, password);
        return count > 0;
    }
    
    public List<User> findAllUsers() {
        String sql = "SELECT id, username, email FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }
}

