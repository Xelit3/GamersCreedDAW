package com.project.gamerscreed.model.dto;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the videogames database table.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
@Entity
@Table(name="videogames")
@NamedQueries({
		@NamedQuery(name="Videogame.findAll", query="SELECT v FROM Videogame v"),
		@NamedQuery(name="Videogame.findAllUnconfirmed", query="SELECT v FROM Videogame v WHERE v.confirmed = false"),
		@NamedQuery(name="Videogame.confirmVideogame", query="UPDATE Videogame v SET v.confirmed = true WHERE v.id = :id")
})
public class Videogame implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/** The confirmed. */
	private boolean confirmed;

	/** The name. */
	private String name;

	/** The year. */
	private int year;

	//bi-directional many-to-one association to Operation
	/** The operations received. */
	@OneToMany(mappedBy="videogameReceived")
	private List<Operation> operationsReceived;

	//bi-directional many-to-one association to Operation
	/** The operations sended. */
	@OneToMany(mappedBy="videogameSended")
	private List<Operation> operationsSended;

	//bi-directional many-to-many association to User
	/** The users. */
	@ManyToMany(mappedBy="videogames")
	private List<User> users;

	//bi-directional many-to-one association to Brand
	/** The developer. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_developer")
	private Brand developer;

	//bi-directional many-to-one association to Brand
	/** The publisher. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_publisher")
	private Brand publisher;

	/**
	 * Instantiates a new videogame.
	 */
	public Videogame() {
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
	 * Gets the confirmed.
	 *
	 * @return the confirmed
	 */
	public boolean getConfirmed() {
		return this.confirmed;
	}

	/**
	 * Sets the confirmed.
	 *
	 * @param confirmed the new confirmed
	 */
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
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
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return this.year;
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
	 * Gets the operations received.
	 *
	 * @return the operations received
	 */
	public List<Operation> getOperationsReceived() {
		return this.operationsReceived;
	}

	/**
	 * Sets the operations received.
	 *
	 * @param operationsReceived the new operations received
	 */
	public void setOperationsReceived(List<Operation> operationsReceived) {
		this.operationsReceived = operationsReceived;
	}

	/**
	 * Adds the operations received.
	 *
	 * @param operationsReceived the operations received
	 * @return the operation
	 */
	public Operation addOperationsReceived(Operation operationsReceived) {
		getOperationsReceived().add(operationsReceived);
		operationsReceived.setVideogameReceived(this);

		return operationsReceived;
	}

	/**
	 * Removes the operations received.
	 *
	 * @param operationsReceived the operations received
	 * @return the operation
	 */
	public Operation removeOperationsReceived(Operation operationsReceived) {
		getOperationsReceived().remove(operationsReceived);
		operationsReceived.setVideogameReceived(null);

		return operationsReceived;
	}

	/**
	 * Gets the operations sended.
	 *
	 * @return the operations sended
	 */
	public List<Operation> getOperationsSended() {
		return this.operationsSended;
	}

	/**
	 * Sets the operations sended.
	 *
	 * @param operationsSended the new operations sended
	 */
	public void setOperationsSended(List<Operation> operationsSended) {
		this.operationsSended = operationsSended;
	}

	/**
	 * Adds the operations sended.
	 *
	 * @param operationsSended the operations sended
	 * @return the operation
	 */
	public Operation addOperationsSended(Operation operationsSended) {
		getOperationsSended().add(operationsSended);
		operationsSended.setVideogameSended(this);

		return operationsSended;
	}

	/**
	 * Removes the operations sended.
	 *
	 * @param operationsSended the operations sended
	 * @return the operation
	 */
	public Operation removeOperationsSended(Operation operationsSended) {
		getOperationsSended().remove(operationsSended);
		operationsSended.setVideogameSended(null);

		return operationsSended;
	}
	
	/**
	 * Adds the user.
	 *
	 * @param anUser the an user
	 */
	public void addUser(User anUser){
		if(this.getUsers() == null)
			this.users = new ArrayList<User>();
		
		this.users.add(anUser);
	}
	
	/**
	 * Removes the user.
	 *
	 * @param anUser the an user
	 */
	public void removeUser(User anUser){
		if(this.getUsers() != null)
			this.users.remove(anUser);
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
	 * Gets the developer.
	 *
	 * @return the developer
	 */
	public Brand getDeveloper() {
		return this.developer;
	}

	/**
	 * Sets the developer.
	 *
	 * @param developer the new developer
	 */
	public void setDeveloper(Brand developer) {
		this.developer = developer;
	}

	/**
	 * Gets the publisher.
	 *
	 * @return the publisher
	 */
	public Brand getPublisher() {
		return this.publisher;
	}

	/**
	 * Sets the publisher.
	 *
	 * @param publisher the new publisher
	 */
	public void setPublisher(Brand publisher) {
		this.publisher = publisher;
	}

}