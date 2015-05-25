package com.project.gamerscreed.view;

import java.io.Serializable;

import com.project.gamerscreed.model.dto.User;

public class UserBasicInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String name;
	private int roleId;
	private String mail;
	
	public UserBasicInfo(User anUser){
		this.username = anUser.getUsername();
		this.name = anUser.getName();
		this.roleId = anUser.getRole().getId();
		this.mail = anUser.getMail();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}	
}
