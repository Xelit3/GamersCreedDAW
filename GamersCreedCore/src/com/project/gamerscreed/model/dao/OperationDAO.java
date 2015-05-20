package com.project.gamerscreed.model.dao;

public interface OperationDAO extends GenericDAOInterface {
	
	public boolean acceptOperation(int anId);
	public boolean rejectOperation(int anId);
	
}
