package com.project.gamerscreed.view;

import com.project.gamerscreed.model.dto.Brand;

/**
 * The Class BrandBasicData.
 * @author: Adrià Nieto
 * @version: 1.0, 5-27-15
 */
public class BrandBasicData {

	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The country. */
	private String country;
	
	/**
	 * Instantiates a new brand basic data.
	 */
	public BrandBasicData(){
		
	}
	
	/**
	 * Instantiates a new brand basic data.
	 *
	 * @param brand the brand
	 */
	public BrandBasicData(Brand brand) {
		super();
		this.id = brand.getId();
		this.name = brand.getName();
		this.country = brand.getCountry().getName();
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
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
