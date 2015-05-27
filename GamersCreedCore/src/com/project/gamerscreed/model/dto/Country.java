package com.project.gamerscreed.model.dto;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the countries database table.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
@Entity
@Table(name="countries")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/** The name. */
	private String name;

	//bi-directional many-to-one association to Brand
	/** The brands. */
	@OneToMany(mappedBy="country", cascade=CascadeType.MERGE)
	private List<Brand> brands;

	//bi-directional many-to-one association to City
	/** The cities. */
	@OneToMany(mappedBy="country", cascade=CascadeType.MERGE)
	private List<City> cities;

	/**
	 * Instantiates a new country.
	 */
	public Country() {
	}

	/**
	 * Instantiates a new country.
	 *
	 * @param aName the a name
	 */
	public Country(String aName) {
		this.name = aName;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
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
		return this.name;
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
	 * Gets the brands.
	 *
	 * @return the brands
	 */
	public List<Brand> getBrands() {
		return this.brands;
	}

	/**
	 * Sets the brands.
	 *
	 * @param brands the new brands
	 */
	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	/**
	 * Adds the brand.
	 *
	 * @param brand the brand
	 * @return the brand
	 */
	public Brand addBrand(Brand brand) {
		getBrands().add(brand);
		brand.setCountry(this);

		return brand;
	}

	/**
	 * Removes the brand.
	 *
	 * @param brand the brand
	 * @return the brand
	 */
	public Brand removeBrand(Brand brand) {
		getBrands().remove(brand);
		brand.setCountry(null);

		return brand;
	}

	/**
	 * Gets the cities.
	 *
	 * @return the cities
	 */
	public List<City> getCities() {
		return this.cities;
	}

	/**
	 * Sets the cities.
	 *
	 * @param cities the new cities
	 */
	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	/**
	 * Adds the city.
	 *
	 * @param city the city
	 * @return the city
	 */
	public City addCity(City city) {
		getCities().add(city);
		city.setCountry(this);

		return city;
	}

	/**
	 * Removes the city.
	 *
	 * @param city the city
	 * @return the city
	 */
	public City removeCity(City city) {
		getCities().remove(city);
		city.setCountry(null);

		return city;
	}

}