package com.project.gamerscreed.model.dao;

import com.project.gamerscreed.model.dto.Operation;


public interface OperationDAO extends GenericDAOInterface<Operation> {
	
	public boolean acceptOperation(int anId);
	public boolean rejectOperation(int anId);
	
}
