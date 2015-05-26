package com.project.gamerscreed.view;

import java.util.Date;

import com.project.gamerscreed.model.dto.Post;

public class PostBasicData {

	private int id;
	private String username;
	private String content;
	private Date postDate;
	
	public PostBasicData(Post anPost){
		this.id = anPost.getId();
		this.username = anPost.getUser().getUsername();
		this.content = anPost.getContent();
		this.postDate = anPost.getPostDate();
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
}
