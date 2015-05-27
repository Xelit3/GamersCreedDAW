package com.project.gamerscreed.view;

/**
 * The Class OperationBasicData.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class OperationBasicData {
	
	/** The user sender id. */
	private int userSenderId;
	
	/** The user receiver id. */
	private int userReceiverId;
	
	/** The videogame sender id. */
	private int videogameSenderId;
	
	/** The videogame receiver id. */
	private int videogameReceiverId;
	
	/** The date sended. */
	private String dateSended;
	
	/** The date accepted. */
	private String dateAccepted;
	
	/** The price. */
	private float price;
	
	/**
	 * Instantiates a new operation basic data.
	 */
	public OperationBasicData(){}
	
	/**
	 * Gets the user sender id.
	 *
	 * @return the user sender id
	 */
	public int getUserSenderId() {
		return userSenderId;
	}
	
	/**
	 * Sets the user sender id.
	 *
	 * @param userSenderId the new user sender id
	 */
	public void setUserSenderId(int userSenderId) {
		this.userSenderId = userSenderId;
	}
	
	/**
	 * Gets the user receiver id.
	 *
	 * @return the user receiver id
	 */
	public int getUserReceiverId() {
		return userReceiverId;
	}
	
	/**
	 * Sets the user receiver id.
	 *
	 * @param userReceiverId the new user receiver id
	 */
	public void setUserReceiverId(int userReceiverId) {
		this.userReceiverId = userReceiverId;
	}
	
	/**
	 * Gets the videogame sender id.
	 *
	 * @return the videogame sender id
	 */
	public int getVideogameSenderId() {
		return videogameSenderId;
	}
	
	/**
	 * Sets the videogame sender id.
	 *
	 * @param videogameSenderId the new videogame sender id
	 */
	public void setVideogameSenderId(int videogameSenderId) {
		this.videogameSenderId = videogameSenderId;
	}
	
	/**
	 * Gets the videogame receiver id.
	 *
	 * @return the videogame receiver id
	 */
	public int getVideogameReceiverId() {
		return videogameReceiverId;
	}
	
	/**
	 * Sets the videogame receiver id.
	 *
	 * @param videogameReceiverId the new videogame receiver id
	 */
	public void setVideogameReceiverId(int videogameReceiverId) {
		this.videogameReceiverId = videogameReceiverId;
	}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	/**
	 * Gets the date sended.
	 *
	 * @return the date sended
	 */
	public String getDateSended() {
		return dateSended;
	}

	/**
	 * Sets the date sended.
	 *
	 * @param dateSended the new date sended
	 */
	public void setDateSended(String dateSended) {
		this.dateSended = dateSended;
	}
	
	/**
	 * Gets the date accepted.
	 *
	 * @return the date accepted
	 */
	public String getDateAccepted() {
		return dateAccepted;
	}

	/**
	 * Sets the date accepted.
	 *
	 * @param dateAccepted the new date accepted
	 */
	public void setDateAccepted(String dateAccepted) {
		this.dateAccepted = dateAccepted;
	}
}
