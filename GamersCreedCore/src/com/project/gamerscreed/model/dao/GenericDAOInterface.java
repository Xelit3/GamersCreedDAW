package com.project.gamerscreed.model.dao;

import java.util.List;

import javax.transaction.Transactional;

public interface GenericDAOInterface<T> {

	@Transactional
	public boolean create(T anObject);
	public boolean modify(T anObject);
	public boolean remove(T anObject);	
	public List<T> getAll();
	
}