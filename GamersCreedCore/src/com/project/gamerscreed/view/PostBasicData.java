package com.project.gamerscreed.view;

import java.util.Date;

import com.project.gamerscreed.model.dto.Post;

/**
 * The Class PostBasicData.
 * @author: Adrià Nieto
 * @version: 1.0, 5-27-15
 */
public class PostBasicData {

	/** The id. */
	private int id;
	
	/** The username. */
	private String username;
	
	/** The content. */
	private String content;
	
	/** The post date. */
	private Date postDate;
	
	/**
	 * Instantiates a new post basic data.
	 *
	 * @param anPost the an post
	 */
	public PostBasicData(Post anPost){
		this.id = anPost.getId();
		this.username = anPost.getUser().getUsername();
		this.content = anPost.getContent();
		this.postDate = anPost.getPostDate();
		
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the post date.
	 *
	 * @return the post date
	 */
	public Date getPostDate() {
		return postDate;
	}

	/**
	 * Sets the post date.
	 *
	 * @param postDate the new post date
	 */
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
}
