package com.project.gamerscreed.model.dto;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the brands database table.
 * 
 */
@Entity
@Table(name="brands")
@NamedQuery(name="Brand.findAll", query="SELECT b FROM Brand b")
public class Brand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_country")
	private Country country;

	//bi-directional many-to-one association to Videogame
	@OneToMany(mappedBy="developer", cascade=CascadeType.PERSIST)
	private List<Videogame> videogamesDeveloped;

	//bi-directional many-to-one association to Videogame
	@OneToMany(mappedBy="publisher", cascade=CascadeType.PERSIST)
	private List<Videogame> videogamesPublished;

	public Brand() {
	}

	public Brand(String aName, Country aCountry) {
		this.name = aName;
		this.country = aCountry;
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

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Videogame> getVideogamesDeveloped() {
		return this.videogamesDeveloped;
	}

	public void setVideogamesDeveloped(List<Videogame> videogamesDeveloped) {
		this.videogamesDeveloped = videogamesDeveloped;
	}

	public Videogame addVideogamesDeveloped(Videogame videogamesDeveloped) {
		getVideogamesDeveloped().add(videogamesDeveloped);
		videogamesDeveloped.setDeveloper(this);

		return videogamesDeveloped;
	}

	public Videogame removeVideogamesDeveloped(Videogame videogamesDeveloped) {
		getVideogamesDeveloped().remove(videogamesDeveloped);
		videogamesDeveloped.setDeveloper(null);

		return videogamesDeveloped;
	}

	public List<Videogame> getVideogamesPublished() {
		return this.videogamesPublished;
	}

	public void setVideogamesPublished(List<Videogame> videogamesPublished) {
		this.videogamesPublished = videogamesPublished;
	}

	public Videogame addVideogamesPublished(Videogame videogamesPublished) {
		getVideogamesPublished().add(videogamesPublished);
		videogamesPublished.setPublisher(this);

		return videogamesPublished;
	}

	public Videogame removeVideogamesPublished(Videogame videogamesPublished) {
		getVideogamesPublished().remove(videogamesPublished);
		videogamesPublished.setPublisher(null);

		return videogamesPublished;
	}

}