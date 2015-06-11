package com.project.gamerscreed.model.dao;

import java.util.List;

import com.project.gamerscreed.model.dto.Operation;

/**
 * The Interface OperationDAO.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public interface OperationDAO extends GenericDAOInterface<Operation> {
	
	/**
	 * Get all Operations by user
	 *
	 * @param anId the user id
	 * @return List<Operation> the operations list
	 */
	public List<Operation> getAllUnconfirmedByUser(int anId);	
	
	/**
	 * Accept operation.
	 *
	 * @param anId the an id
	 * @return true, if successful
	 */
	public boolean acceptOperation(int anId);
	
	/**
	 * Reject operation.
	 *
	 * @param anId the an id
	 * @return true, if successful
	 */
	public boolean rejectOperation(int anId);
	
}
