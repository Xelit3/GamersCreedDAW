package com.project.gamerscreed.model.dao;

import java.util.Map;

import com.project.gamerscreed.model.dto.User;

public interface UserDAO extends GenericDAOInterface {
	
	public User login(String aUsername, String aPassword);
	public Map<String, String> getAllUsernames();
}
