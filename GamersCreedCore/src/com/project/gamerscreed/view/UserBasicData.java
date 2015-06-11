package com.project.gamerscreed.view;

import com.project.gamerscreed.model.dto.User;

/**
 * The Class UserBasicData.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class UserBasicData {
	
	/** The id. */
	private int id;
	
	/** The username. */
	private String username;
	
	/** The name. */
	private String name;
	
	/** The mail. */
	private String mail;
	
	/** The role id. */
	private int roleId;
	
	/** The address cp. */
	private int addressCp;
	
	/** The address street. */
	private String addressStreet;
	
	/** The address city. */
	private String addressCity;
	
	/** The address country. */
	private String addressCountry;
	
	private int addressCountryId;
	
	/**
	 * Instantiates a new user basic data.
	 *
	 * @param anUser the an user
	 */
	public UserBasicData(User anUser){
		this.id = anUser.getId();
		this.username = anUser.getUsername();
		this.name = anUser.getName();
		this.mail = anUser.getMail();
		this.roleId = anUser.getRole().getId();
		if(anUser.getAddress() != null){
			this.addressCp = anUser.getAddress().getCp();
			this.addressStreet = anUser.getAddress().getStreet();
			this.addressCity = anUser.getAddress().getCity().getName();
			this.addressCountry = anUser.getAddress().getCity().getCountry().getName();
			this.addressCountryId = anUser.getAddress().getCity().getCountry().getId();
		}		
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Gets the role id.
	 *
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * Sets the role id.
	 *
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the address cp.
	 *
	 * @return the addressCp
	 */
	public int getAddressCp() {
		return addressCp;
	}

	/**
	 * Sets the address cp.
	 *
	 * @param addressCp the addressCp to set
	 */
	public void setAddressCp(int addressCp) {
		this.addressCp = addressCp;
	}

	/**
	 * Gets the address street.
	 *
	 * @return the addressStreet
	 */
	public String getAddressStreet() {
		return addressStreet;
	}

	/**
	 * Sets the address street.
	 *
	 * @param addressStreet the addressStreet to set
	 */
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	/**
	 * Gets the address city.
	 *
	 * @return the addressCity
	 */
	public String getAddressCity() {
		return addressCity;
	}

	/**
	 * Sets the address city.
	 *
	 * @param addressCity the addressCity to set
	 */
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	/**
	 * Gets the address country.
	 *
	 * @return the addressCountry
	 */
	public String getAddressCountry() {
		return addressCountry;
	}

	/**
	 * Sets the address country.
	 *
	 * @param addressCountry the addressCountry to set
	 */
	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	/**
	 * @return the addressCountryId
	 */
	public int getAddressCountryId() {
		return addressCountryId;
	}

	/**
	 * @param addressCountryId the addressCountryId to set
	 */
	public void setAddressCountryId(int addressCountryId) {
		this.addressCountryId = addressCountryId;
	}

}
