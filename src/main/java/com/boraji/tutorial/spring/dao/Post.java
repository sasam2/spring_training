package com.boraji.tutorial.spring.dao;

import java.util.Date;

public class Post {
	
	private int id;
	private String title;
	private String content;
	private String photo;
	private Date date;
	private int author_id;
	private String author_name;
	
	public Post(){}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	public void setContent(String content) {
		this.content=content;
	}
	
	public void setPhoto(String photoUrl) {
		this.photo=photoUrl;
	}
	
	public void setDate(Date date) {
		this.date=date;
	}
	
	public void setAuthorId(int id) {
		this.author_id=id;
	}
	
	public void setAuthorName(String authorName) {
		this.author_name=authorName;
	}
	public String getAuthorName() {
		return this.author_name;
	}
	
	
	public int getAuthorId() {
		return this.author_id;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getPhoto() {
		return this.photo;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getContent() {
		return this.content;
	}

	public int getId() {
		return this.id;
	}
	
}
