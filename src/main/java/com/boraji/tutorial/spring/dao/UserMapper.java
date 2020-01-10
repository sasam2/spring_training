package com.boraji.tutorial.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<UserInfo> {

	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserInfo u = new UserInfo(rs.getInt("id"), rs.getString("username"));
		return u;
	}

}
