package com.boraji.tutorial.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
//import org.springframework.jdbc.core.RowMapper;

public class UserExtractor implements ResultSetExtractor<UserInfo> {

	/*public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserInfo u = new UserInfo(rs.getInt("id"),
				rs.getString("username"));
		return u;
	}*/

	@Override
	public UserInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		if(!rs.next()) return null;
		
		UserInfo u = new UserInfo(rs.getInt("id"),
				rs.getString("username"));
		return u;
	}
}