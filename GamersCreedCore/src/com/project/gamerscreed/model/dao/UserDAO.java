package com.project.gamerscreed.model.dao;

import com.project.gamerscreed.model.dto.User;

public interface UserDAO extends GenericDAOInterface {
	
	public User login(String username, String password);
}
