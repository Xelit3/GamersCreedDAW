package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object anObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}