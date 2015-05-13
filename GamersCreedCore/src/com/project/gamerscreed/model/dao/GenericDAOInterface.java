package com.project.gamerscreed.model.dao;

import java.util.List;

import javax.transaction.Transactional;

public interface GenericDAOInterface {

	@Transactional
	public boolean create(Object anObject);
	public boolean modify(Object anObject);
	public boolean remove(Object anObject);	
	public List<?> getAll();
	
}