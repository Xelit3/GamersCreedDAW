package com.project.gamerscreed.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The Class GenericDAOLayer.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public abstract class GenericDAOLayer {
	
	/** The entity manager. */
	protected EntityManager entityManager;
	
	/** The factory name. */
	private final String FACTORY_NAME = "GamersCreedCore";
	
	/**
	 * Instantiates a new generic dao layer.
	 */
	public GenericDAOLayer(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(FACTORY_NAME);
		this.entityManager = emf.createEntityManager();	
	}
		
	/**
	 * Begin transaction.
	 */
	protected void beginTransaction(){
		entityManager.getTransaction().begin();
	}
	
	/**
	 * Commit transaction.
	 */
	protected void commitTransaction(){
		entityManager.getTransaction().commit();
//		entityManager.flush();
	}
	
	/**
	 * Close transaction.
	 */
	protected void closeTransaction(){
		entityManager.close();
	}

}
