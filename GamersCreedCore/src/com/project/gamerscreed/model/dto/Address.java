package com.project.gamerscreed.model.dto;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the addresses database table.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
@Entity
@Table(name="addresses")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/** The cp. */
	private int cp;

	/** The street. */
	private String street;

	//bi-directional many-to-one association to City
	/** The city. */
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_city")
	private City city;

	//bi-directional many-to-one association to User
	/** The users. */
	@OneToMany(mappedBy="address")
	private List<User> users;

	/**
	 * Instantiates a new address.
	 */
	public Address() {
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
	 * Gets the cp.
	 *
	 * @return the cp
	 */
	public int getCp() {
		return this.cp;
	}

	/**
	 * Sets the cp.
	 *
	 * @param cp the new cp
	 */
	public void setCp(int cp) {
		this.cp = cp;
	}

	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public City getCity() {
		return this.city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<User> getUsers() {
		return this.users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User addUser(User user) {
		getUsers().add(user);
		user.setAddress(this);

		return user;
	}

	/**
	 * Removes the user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User removeUser(User user) {
		getUsers().remove(user);
		user.setAddress(null);

		return user;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Address [cp=" + cp + ", street=" + street + ", city=" + city + "]";
	}

}