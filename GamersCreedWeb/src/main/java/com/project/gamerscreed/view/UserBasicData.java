package com.project.gamerscreed.view;

import com.project.gamerscreed.model.dto.User;

public class UserBasicData {
	
	private int id;
	private String username;
	private String name;
	private String mail;
	private int roleId;
	private int addressCp;
	private String addressStreet;
	private String addressCity;
	private String addressCountry;
	
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
		}		
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the addressCp
	 */
	public int getAddressCp() {
		return addressCp;
	}

	/**
	 * @param addressCp the addressCp to set
	 */
	public void setAddressCp(int addressCp) {
		this.addressCp = addressCp;
	}

	/**
	 * @return the addressStreet
	 */
	public String getAddressStreet() {
		return addressStreet;
	}

	/**
	 * @param addressStreet the addressStreet to set
	 */
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	/**
	 * @return the addressCity
	 */
	public String getAddressCity() {
		return addressCity;
	}

	/**
	 * @param addressCity the addressCity to set
	 */
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	/**
	 * @return the addressCountry
	 */
	public String getAddressCountry() {
		return addressCountry;
	}

	/**
	 * @param addressCountry the addressCountry to set
	 */
	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

}
