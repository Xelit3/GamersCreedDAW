package com.project.gamerscreed.view;

import com.project.gamerscreed.model.dto.Videogame;

/**
 * The Class VideogameBasicData.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class VideogameBasicData {

	/** The id. */
	public int id;
	
	/** The name. */
	public String name;
	
	/** The developer. */
	public String developer;
	
	/** The publisher. */
	public String publisher;
	
	/** The year. */
	public int year;
	
	/** The confirmed. */
	public boolean confirmed;
	
	/**
	 * Instantiates a new videogame basic data.
	 *
	 * @param aVideogame the a videogame
	 */
	public VideogameBasicData(Videogame aVideogame){
		this.id = aVideogame.getId();
		this.name = aVideogame.getName();
		this.developer = aVideogame.getDeveloper().getName();
		this.publisher = aVideogame.getPublisher().getName();
		this.year = aVideogame.getYear();
		this.confirmed = aVideogame.getConfirmed();
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the developer.
	 *
	 * @return the developer
	 */
	public String getDeveloper() {
		return developer;
	}
	
	/**
	 * Sets the developer.
	 *
	 * @param developer the new developer
	 */
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
	/**
	 * Gets the publisher.
	 *
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * Sets the publisher.
	 *
	 * @param publisher the new publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Checks if is confirmed.
	 *
	 * @return true, if is confirmed
	 */
	public boolean isConfirmed() {
		return confirmed;
	}

	/**
	 * Sets the confirmed.
	 *
	 * @param confirmed the new confirmed
	 */
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}	
	
}
