package com.project.gamerscreed.model.dto;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the brands database table.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
@Entity
@Table(name="brands")
@NamedQuery(name="Brand.findAll", query="SELECT b FROM Brand b")
public class Brand implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/** The name. */
	private String name;

	//bi-directional many-to-one association to Country
	/** The country. */
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="id_country")
	private Country country;

	//bi-directional many-to-one association to Videogame
	/** The videogames developed. */
	@OneToMany(mappedBy="developer")
	private List<Videogame> videogamesDeveloped;

	//bi-directional many-to-one association to Videogame
	/** The videogames published. */
	@OneToMany(mappedBy="publisher")
	private List<Videogame> videogamesPublished;

	/**
	 * Instantiates a new brand.
	 */
	public Brand() {
	}

	/**
	 * Instantiates a new brand.
	 *
	 * @param aName the a name
	 * @param aCountry the a country
	 */
	public Brand(String aName, Country aCountry) {
		this.name = aName;
		this.country = aCountry;
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
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry() {
		return this.country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * Gets the videogames developed.
	 *
	 * @return the videogames developed
	 */
	public List<Videogame> getVideogamesDeveloped() {
		return this.videogamesDeveloped;
	}

	/**
	 * Sets the videogames developed.
	 *
	 * @param videogamesDeveloped the new videogames developed
	 */
	public void setVideogamesDeveloped(List<Videogame> videogamesDeveloped) {
		this.videogamesDeveloped = videogamesDeveloped;
	}

	/**
	 * Adds the videogames developed.
	 *
	 * @param videogamesDeveloped the videogames developed
	 * @return the videogame
	 */
	public Videogame addVideogamesDeveloped(Videogame videogamesDeveloped) {
		getVideogamesDeveloped().add(videogamesDeveloped);
		videogamesDeveloped.setDeveloper(this);

		return videogamesDeveloped;
	}

	/**
	 * Removes the videogames developed.
	 *
	 * @param videogamesDeveloped the videogames developed
	 * @return the videogame
	 */
	public Videogame removeVideogamesDeveloped(Videogame videogamesDeveloped) {
		getVideogamesDeveloped().remove(videogamesDeveloped);
		videogamesDeveloped.setDeveloper(null);

		return videogamesDeveloped;
	}

	/**
	 * Gets the videogames published.
	 *
	 * @return the videogames published
	 */
	public List<Videogame> getVideogamesPublished() {
		return this.videogamesPublished;
	}

	/**
	 * Sets the videogames published.
	 *
	 * @param videogamesPublished the new videogames published
	 */
	public void setVideogamesPublished(List<Videogame> videogamesPublished) {
		this.videogamesPublished = videogamesPublished;
	}

	/**
	 * Adds the videogames published.
	 *
	 * @param videogamesPublished the videogames published
	 * @return the videogame
	 */
	public Videogame addVideogamesPublished(Videogame videogamesPublished) {
		getVideogamesPublished().add(videogamesPublished);
		videogamesPublished.setPublisher(this);

		return videogamesPublished;
	}

	/**
	 * Removes the videogames published.
	 *
	 * @param videogamesPublished the videogames published
	 * @return the videogame
	 */
	public Videogame removeVideogamesPublished(Videogame videogamesPublished) {
		getVideogamesPublished().remove(videogamesPublished);
		videogamesPublished.setPublisher(null);

		return videogamesPublished;
	}

}