package com.project.gamerscreed.view;

import com.project.gamerscreed.model.dto.Country;

/**
 * The Class CountryBasicData.
 * @author: Adrià Nieto
 * @version: 1.0, 5-27-15
 */
public class CountryBasicData {
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new country basic data.
	 */
	public CountryBasicData(){
		
	}
	
	/**
	 * Instantiates a new country basic data.
	 *
	 * @param country the country
	 */
	public CountryBasicData(Country country){
		this.id=country.getId();
		this.name=country.getName();
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
