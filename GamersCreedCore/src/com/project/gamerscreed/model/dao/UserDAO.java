package com.project.gamerscreed.model.dao;

import java.util.Map;

import com.project.gamerscreed.model.dto.User;
import com.project.gamerscreed.model.dto.Videogame;

public interface UserDAO extends GenericDAOInterface<User> {
	
	public User login(String aUsername, String aPassword);
	public User getUserById(int anId);
	public Map<String, String> getAllUsernames();
	public boolean addVideogameToUser(User anUser, Videogame aVideogame);
	public boolean removeVideogameFromUser(User anUser, Videogame aVideogame);
	public boolean addFollower(User anUser, User aFollower);
	public boolean removeFollower(User anUser, User aFollower);
	public boolean addFollowing(User anUser, User aFollowing);
	public boolean removeFollowing(User anUser, User aFollowing);	
	
}