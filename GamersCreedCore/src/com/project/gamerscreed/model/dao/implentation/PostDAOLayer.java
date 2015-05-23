package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import com.project.gamerscreed.model.dao.GenericDAOInterface;
import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dto.Post;

public class PostDAOLayer  extends GenericDAOLayer implements GenericDAOInterface<Post>{

	@Override
	public boolean create(Post anObject) {
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
	public boolean modify(Post anObject) {
		//Will not be implemented. NOT NEEDED
		return false;
	}

	@Override
	public boolean remove(Post anObject) {
		//Will not be implemented. NOT NEEDED
		return false;
	}

	@Override
	public List<Post> getAll() {
		//Will not be implemented. NOT NEEDED
		return null;
	}

}
