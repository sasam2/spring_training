package com.boraji.tutorial.spring.dao;

public class UserInfo {

	private String username;
	private int id;
	private String password;
	
	public UserInfo() {
	}
	
	public UserInfo(int id, String username) {
		this.id=id;
		this.username=username;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
