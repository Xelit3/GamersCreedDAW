package com.project.gamerscreed.view;

import com.project.gamerscreed.model.dto.ForumThread;

/**
 * The Class ForumThreadBasicData.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class ForumThreadBasicData {

	/** The id. */
	private int id;
	
	/** The title. */
	private String title;
	
	/** The date. */
	private String date;
	
	/** The user. */
	private String user;
	
	/**
	 * Instantiates a new forum thread basic data.
	 *
	 * @param aThread the a thread
	 */
	public ForumThreadBasicData(ForumThread aThread){
		this.id = aThread.getId();
		this.title = aThread.getTitle();
		this.date = aThread.getDate().toString();
		this.user = aThread.getUser().getUsername();
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
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title.
	 *
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * Sets the user.
	 *
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
}
