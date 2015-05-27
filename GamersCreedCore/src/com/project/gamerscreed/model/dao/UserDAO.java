package com.project.gamerscreed.model.dao;

import java.util.List;
import java.util.Map;

import com.project.gamerscreed.model.dto.User;
import com.project.gamerscreed.model.dto.Videogame;

/**
 * The Interface UserDAO.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public interface UserDAO extends GenericDAOInterface<User> {
	
	/**
	 * Login.
	 *
	 * @param aUsername the a username
	 * @param aPassword the a password
	 * @return the user
	 */
	public User login(String aUsername, String aPassword);
	
	/**
	 * Gets the user by id.
	 *
	 * @param anId the an id
	 * @return the user by id
	 */
	public User getUserById(int anId);
	
	/**
	 * Gets the all usernames.
	 *
	 * @return the all usernames
	 */
	public Map<String, String> getAllUsernames();
	
	/**
	 * Adds the videogame to user.
	 *
	 * @param anUser the an user
	 * @param aVideogame the a videogame
	 * @return true, if successful
	 */
	public boolean addVideogameToUser(User anUser, Videogame aVideogame);
	
	/**
	 * Removes the videogame from user.
	 *
	 * @param anUser the an user
	 * @param aVideogame the a videogame
	 * @return true, if successful
	 */
	public boolean removeVideogameFromUser(User anUser, Videogame aVideogame);
	
	/**
	 * Adds the follower.
	 *
	 * @param anUser the an user
	 * @param aFollower the a follower
	 * @return true, if successful
	 */
	public boolean addFollower(User anUser, User aFollower);
	
	/**
	 * Removes the follower.
	 *
	 * @param anUser the an user
	 * @param aFollower the a follower
	 * @return true, if successful
	 */
	public boolean removeFollower(User anUser, User aFollower);
	
	/**
	 * Adds the following.
	 *
	 * @param anUser the an user
	 * @param aFollowing the a following
	 * @return true, if successful
	 */
	public boolean addFollowing(User anUser, User aFollowing);
	
	/**
	 * Removes the following.
	 *
	 * @param anUser the an user
	 * @param aFollowing the a following
	 * @return true, if successful
	 */
	public boolean removeFollowing(User anUser, User aFollowing);	
	
	/**
	 * Gets the other users.
	 *
	 * @param anId the an id
	 * @return the other users
	 */
	public List<User> getOtherUsers(int anId);
}