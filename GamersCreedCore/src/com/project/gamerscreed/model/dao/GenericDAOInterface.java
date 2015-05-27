package com.project.gamerscreed.model.dao;

import java.util.List;

import javax.transaction.Transactional;

/**
 * The Interface GenericDAOInterface.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 * @param <T> the generic type
 */
public interface GenericDAOInterface<T> {

	/**
	 * Creates the.
	 *
	 * @param anObject the an object
	 * @return true, if successful
	 */
	@Transactional
	public boolean create(T anObject);
	
	/**
	 * Modify.
	 *
	 * @param anObject the an object
	 * @return true, if successful
	 */
	public boolean modify(T anObject);
	
	/**
	 * Removes the.
	 *
	 * @param anObject the an object
	 * @return true, if successful
	 */
	public boolean remove(T anObject);	
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<T> getAll();
	
}