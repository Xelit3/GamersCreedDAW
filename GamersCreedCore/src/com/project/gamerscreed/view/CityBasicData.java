package com.project.gamerscreed.view;

import com.project.gamerscreed.model.dto.City;

/**
 * The Class CityBasicData.
 * @author: Adrià Nieto
 * @version: 1.0, 5-27-15
 */
public class CityBasicData {

	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new city basic data.
	 */
	public CityBasicData(){
		
	}
	
	/**
	 * Instantiates a new city basic data.
	 *
	 * @param city the city
	 */
	public CityBasicData(City city){
		this.id=city.getId();
		this.name=city.getName();
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
	
}
