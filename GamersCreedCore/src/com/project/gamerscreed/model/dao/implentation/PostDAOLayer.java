package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import com.project.gamerscreed.model.dao.GenericDAOInterface;
import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dto.Post;
import com.project.gamerscreed.model.dto.User;

public class PostDAOLayer  extends GenericDAOLayer implements GenericDAOInterface<Post>{

	@Override
	public boolean create(Post aPost) {
		try{
			this.beginTransaction();
			aPost.setUser(this.entityManager.getReference(User.class, aPost.getUser().getId()));
			this.entityManager.persist(aPost);
			aPost.getUser().addPost(aPost);
			this.entityManager.merge(aPost.getUser());
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
	public boolean modify(Post aPost) {
		//Will not be implemented. NOT NEEDED
		return false;
	}

	@Override
	public boolean remove(Post aPost) {
		//Will not be implemented. NOT NEEDED
		return false;
	}

	@Override
	public List<Post> getAll() {
		//Will not be implemented. NOT NEEDED
		return null;
	}

}
