package com.project.gamerscreed.model.dao;

import java.util.List;

import com.project.gamerscreed.model.dto.Videogame;


/**
 * The Interface VideogameDAO.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public interface VideogameDAO extends GenericDAOInterface<Videogame> {
	
	/**
	 * Confirm videogame.
	 *
	 * @param anId the an id
	 * @return true, if successful
	 */
	public boolean confirmVideogame(int anId);
	
	/**
	 * Gets the all unconfirmed games.
	 *
	 * @return the all unconfirmed games
	 */
	public List<Videogame> getAllUnconfirmedGames();
}
