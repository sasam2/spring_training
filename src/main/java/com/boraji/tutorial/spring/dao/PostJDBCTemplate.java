package com.boraji.tutorial.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
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
