package com.project.gamerscreed.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class GenericDAOLayer {
	
	protected EntityManager entityManager;
	private final String FACTORY_NAME = "GamersCreedCore";
	
	public GenericDAOLayer(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(FACTORY_NAME);
		this.entityManager = emf.createEntityManager();	
	}
		
	protected void beginTransaction(){
		entityManager.getTransaction().begin();
	}
	
	protected void commitTransaction(){
		entityManager.getTransaction().commit();
	}
	
	protected void closeTransaction(){
		entityManager.close();
	}

}
