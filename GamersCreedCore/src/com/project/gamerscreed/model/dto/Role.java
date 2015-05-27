package com.project.gamerscreed.model.dto;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the roles database table.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The Enum RoleType.
	 */
	public enum RoleType{
		
		/** The admin. */
		ADMIN, 
 /** The mod. */
 MOD, 
 /** The basic. */
 BASIC;		
	}

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	/** The role name. */
	@Column(name="role_name")
	private String roleName;

	//bi-directional many-to-one association to User
	/** The users. */
	@OneToMany(mappedBy="role", cascade=CascadeType.MERGE)
	private List<User> users;

	/**
	 * Instantiates a new role.
	 */
	public Role() {
	}
	
	/**
	 * Instantiates a new role.
	 *
	 * @param aType the a type
	 */
	public Role(RoleType aType){
		this.roleName = aType.toString();
		this.id = aType.ordinal();
	}

	/**
	 * Instantiates a new role.
	 *
	 * @param aName the a name
	 */
	public Role(String aName) {
		this.roleName = aName;
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
	 * Gets the role name.
	 *
	 * @return the role name
	 */
	public String getRoleName() {
		return this.roleName;
	}

	/**
	 * Sets the role name.
	 *
	 * @param roleName the new role name
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
		if(this.getUsers() == null)
			this.users = new ArrayList<User>();
		
		this.getUsers().add(user);
		
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
		user.setRole(null);

		return user;
	}

}