package com.project.gamerscreed.view;

import com.project.gamerscreed.model.dto.Operation;

/**
 * The Class OperationBasicData.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class OperationBasicData {
	
	/** The user sender id. */
	private int id;
	
	/** The user sender id. */
	private int userSenderId;
	
	/** The user receiver id. */
	private int userReceiverId;
	
	/** The videogame receiver id. */
	private String userSenderName;
	
	/** The videogame sender id. */
	private int videogameSenderId;
	
	/** The videogame receiver id. */
	private int videogameReceiverId;
	
	/** The price. */
	private String videogameSenderName;
	
	/** The price. */
	private String videogameReceiverName;
	
	/** The date sended. */
	private String dateSended;
	
	/** The date accepted. */
	private String dateAccepted;
	
	/** The price. */
	private float price;
	
	/** The rejected. */
	private boolean rejected;
	
	/**
	 * Instantiates a new operation basic data.
	 */
	public OperationBasicData(){}
	
	/**
	 * Instantiates a new operation basic data from an Operation class
	 */
	public OperationBasicData(Operation anOperation){
		this.id = anOperation.getId();
		this.userSenderId = anOperation.getUserSender().getId();
		this.userReceiverId = anOperation.getUserReceived().getId();
		this.userSenderName = anOperation.getUserSender().getUsername();
		this.videogameSenderId = anOperation.getVideogameSended().getId();
		this.videogameSenderName = anOperation.getVideogameSended().getName();
		this.videogameReceiverId = anOperation.getVideogameReceived().getId();
		this.videogameReceiverName = anOperation.getVideogameReceived().getName();
		this.dateSended = anOperation.getDateSended().toString();
		this.price = anOperation.getPrice();
		this.rejected = anOperation.getRejected();
	}
	
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

	/**
	 * @return the userSenderName
	 */
	public String getUserSenderName() {
		return userSenderName;
	}

	/**
	 * @param userSenderName the userSenderName to set
	 */
	public void setUserSenderName(String userSenderName) {
		this.userSenderName = userSenderName;
	}

	/**
	 * @return the videogameSenderName
	 */
	public String getVideogameSenderName() {
		return videogameSenderName;
	}

	/**
	 * @param videogameSenderName the videogameSenderName to set
	 */
	public void setVideogameSenderName(String videogameSenderName) {
		this.videogameSenderName = videogameSenderName;
	}

	/**
	 * @return the videogameReceiverName
	 */
	public String getVideogameReceiverName() {
		return videogameReceiverName;
	}

	/**
	 * @param videogameReceiverName the videogameReceiverName to set
	 */
	public void setVideogameReceiverName(String videogameReceiverName) {
		this.videogameReceiverName = videogameReceiverName;
	}

	/**
	 * @return the rejected
	 */
	public boolean isRejected() {
		return rejected;
	}

	/**
	 * @param rejected the rejected to set
	 */
	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}
