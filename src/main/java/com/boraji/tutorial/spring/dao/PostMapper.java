package com.boraji.tutorial.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PostMapper implements RowMapper<Post> {

	public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
		Post p = new Post();
		p.setId(rs.getInt("id"));
		p.setTitle(rs.getString("title"));
		p.setContent(rs.getString("content"));
		//p.setPhoto(rs.getString("photo"));
		p.setDate(rs.getDate("date"));
		p.setAuthorId(rs.getInt("author_id"));
		p.setAuthorName(rs.getString("username"));
		return p;
	}

}
