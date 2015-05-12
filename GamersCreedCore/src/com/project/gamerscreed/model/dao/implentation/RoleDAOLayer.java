package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import com.project.gamerscreed.model.dao.GenericDAOInterface;
import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dto.Role;

public class RoleDAOLayer extends GenericDAOLayer implements GenericDAOInterface {

	public RoleDAOLayer() {
		super();
	}

	@Override
	public boolean create(Object anObject) {		
		try{
			Role tmpRole = (Role) anObject;
			
			this.beginTransaction();
			this.entityManager.persist(tmpRole);
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

}
