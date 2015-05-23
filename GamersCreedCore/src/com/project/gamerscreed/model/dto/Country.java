package com.project.gamerscreed.model.dto;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name="countries")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	//bi-directional many-to-one association to Brand
	@OneToMany(mappedBy="country", cascade=CascadeType.MERGE)
	private List<Brand> brands;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="country", cascade=CascadeType.MERGE)
	private List<City> cities;

	public Country() {
	}

	public Country(String aName) {
		this.name = aName;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Brand> getBrands() {
		return this.brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public Brand addBrand(Brand brand) {
		getBrands().add(brand);
		brand.setCountry(this);

		return brand;
	}

	public Brand removeBrand(Brand brand) {
		getBrands().remove(brand);
		brand.setCountry(null);

		return brand;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setCountry(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setCountry(null);

		return city;
	}

}