package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import com.project.gamerscreed.model.dao.GenericDAOInterface;
import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dto.Post;

public class PostDAOLayer  extends GenericDAOLayer implements GenericDAOInterface{

	@Override
	public boolean create(Object anObject) {
		try{
			Post tmpPost = (Post) anObject;
			this.beginTransaction();
			this.entityManager.persist(tmpPost);
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
	public List<?> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
