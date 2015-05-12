package com.project.gamerscreed.model.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the videogames database table.
 * 
 */
@Entity
@Table(name="videogames")
@NamedQuery(name="Videogame.findAll", query="SELECT v FROM Videogame v")
public class Videogame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private boolean confirmed;

	private String name;

	private int year;

	//bi-directional many-to-one association to Operation
	@OneToMany(mappedBy="videogameReceived")
	private List<Operation> operationsReceived;

	//bi-directional many-to-one association to Operation
	@OneToMany(mappedBy="videogameSended")
	private List<Operation> operationsSended;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="videogames")
	private List<User> users;

	//bi-directional many-to-one association to Brand
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_developer")
	private Brand developer;

	//bi-directional many-to-one association to Brand
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_publisher")
	private Brand publisher;

	public Videogame() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Operation> getOperationsReceived() {
		return this.operationsReceived;
	}

	public void setOperationsReceived(List<Operation> operationsReceived) {
		this.operationsReceived = operationsReceived;
	}

	public Operation addOperationsReceived(Operation operationsReceived) {
		getOperationsReceived().add(operationsReceived);
		operationsReceived.setVideogameReceived(this);

		return operationsReceived;
	}

	public Operation removeOperationsReceived(Operation operationsReceived) {
		getOperationsReceived().remove(operationsReceived);
		operationsReceived.setVideogameReceived(null);

		return operationsReceived;
	}

	public List<Operation> getOperationsSended() {
		return this.operationsSended;
	}

	public void setOperationsSended(List<Operation> operationsSended) {
		this.operationsSended = operationsSended;
	}

	public Operation addOperationsSended(Operation operationsSended) {
		getOperationsSended().add(operationsSended);
		operationsSended.setVideogameSended(this);

		return operationsSended;
	}

	public Operation removeOperationsSended(Operation operationsSended) {
		getOperationsSended().remove(operationsSended);
		operationsSended.setVideogameSended(null);

		return operationsSended;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Brand getDeveloper() {
		return this.developer;
	}

	public void setDeveloper(Brand developer) {
		this.developer = developer;
	}

	public Brand getPublisher() {
		return this.publisher;
	}

	public void setPublisher(Brand publisher) {
		this.publisher = publisher;
	}

}