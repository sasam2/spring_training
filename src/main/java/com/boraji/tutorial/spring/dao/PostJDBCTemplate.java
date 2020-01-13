package com.boraji.tutorial.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PostJDBCTemplate {

	  
	  private DataSource dataSource;
	  private JdbcTemplate jdbcTemplateObject;
	   
	  
	  public PostJDBCTemplate(DataSource dataSource) {
	     this.dataSource = dataSource;
	     this.jdbcTemplateObject = new JdbcTemplate(this.dataSource);
	  }
	  
	  public List<Post> listPosts() {
	      String SQL = "select p.id, p.title, p.content, p.date, p.author_id, p.photo, u.username "
	    		  + "from display_post p, users u where u.id=p.author_id "
	    		  + "order by p.date desc";
	      List <Post> posts = jdbcTemplateObject.query(SQL, new PostMapper());
	      return posts;
	  }
	  
	  public int getPostLatestId() {
	      String SQL = "select max(id) as max_id "
	    		  + "from display_post ";
	      int postId = jdbcTemplateObject.query(SQL, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(!rs.next()) return null;
				return rs.getInt("max_id");
			}});
	      return postId;
	  }
	  
	  public List<Post> listPostsLessThanId(int id, int nrPosts) {
	      String SQL = "select p.id, p.title, p.content, p.date, p.author_id, p.photo, u.username "
	    		  + "from display_post p, users u where "
	    		  + "u.id=p.author_id and "
	    		  + "p.id <= "+id+" "
	    		  + "order by p.id desc "
	    		  + "limit 0, " + nrPosts;
	      List <Post> posts = jdbcTemplateObject.query(SQL, new PostMapper());
	      return posts;
	  }
	  
	  public List<UserInfo> listUsers() {
	      String SQL = "select * from users";
	      List <UserInfo> users = jdbcTemplateObject.query(SQL, new UserMapper());
	      return users;
	  }
	  
	  public UserInfo getUser(String username) {
	      String SQL = "select * from users where username='"+username+"'" ;
	      UserInfo user = jdbcTemplateObject.query(SQL, new UserExtractor());
	      return user;
	  }
	  
	  
	  public int addPost(Post p, int author_id) {
		  int rAff = jdbcTemplateObject.update(
				    "INSERT INTO display_post (title, content, author_id, date, photo) VALUES (?, ?, ?, SYSDATE(), ?)",
				    p.getTitle(), p.getContent(), author_id, "/"
				);
		  return rAff;
	  }
	  
	  public int addUser(UserInfo u) {
		  int rAff = jdbcTemplateObject.update(
				    "INSERT INTO users (username, password, enabled) VALUES (?, ?, ?)",
				    u.getUsername(), new BCryptPasswordEncoder().encode(u.getPassword()), 1
				);
		  return rAff;
	  }
}
