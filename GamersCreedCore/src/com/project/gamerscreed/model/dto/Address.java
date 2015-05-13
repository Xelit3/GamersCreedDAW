package com.project.gamerscreed.model.dto;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the addresses database table.
 * 
 */
@Entity
@Table(name="addresses")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int cp;

	private String street;

	//bi-directional many-to-one association to City
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_city")
	private City city;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="address")
	private List<User> users;

	public Address() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCp() {
		return this.cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setAddress(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setAddress(null);

		return user;
	}

	@Override
	public String toString() {
		return "Address [cp=" + cp + ", street=" + street + ", city=" + city + "]";
	}

}