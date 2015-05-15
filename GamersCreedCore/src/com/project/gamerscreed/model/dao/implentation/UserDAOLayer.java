package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dao.UserDAO;
import com.project.gamerscreed.model.dto.User;

public class UserDAOLayer extends GenericDAOLayer implements UserDAO{
	
	public UserDAOLayer() {
		super();
	}

	@Override
	public boolean create(Object anObject) {
		try{
			User tmpUser = (User) anObject;
			this.beginTransaction();
			this.entityManager.persist(tmpUser);
			this.commitTransaction();
			this.closeTransaction();

			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modify(Object anObject) {
		try{
			User tmpUser = (User) anObject;
			this.beginTransaction();
			this.entityManager.merge(tmpUser);
			this.commitTransaction();
			this.closeTransaction();

			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean remove(Object anObject) {
		try{
			User tmpUser = (User) anObject;
			
			this.beginTransaction();
			this.entityManager.remove(tmpUser);
			this.commitTransaction();
			this.closeTransaction();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public List<User> getAll() {
		TypedQuery<User> query = this.entityManager.createNamedQuery("User.findAll", User.class);
		List<User> result = query.getResultList();
		
		return result;
	}

	@Override
	public User login(String aUsername, String aPassword) {
		TypedQuery<User> query = this.entityManager.createNamedQuery("User.loginUser", User.class);
		System.out.println();
		query.setParameter("username", aUsername);
		query.setParameter("password", aPassword);
		
		User tmpUser = query.getSingleResult();
		
		return tmpUser;
	}

}