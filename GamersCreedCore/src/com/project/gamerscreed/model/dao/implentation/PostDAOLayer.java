package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dao.PostDAO;
import com.project.gamerscreed.model.dto.Post;
import com.project.gamerscreed.model.dto.User;

/**
 * The Class PostDAOLayer.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class PostDAOLayer  extends GenericDAOLayer implements PostDAO{

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#create(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#modify(java.lang.Object)
	 */
	@Override
	public boolean modify(Post aPost) {
		//Will not be implemented. NOT NEEDED
		return false;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Post aPost) {
		//Will not be implemented. NOT NEEDED
		return false;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#getAll()
	 */
	@Override
	public List<Post> getAll() {
		//Will not be implemented. NOT NEEDED
		return null;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.PostDAO#getPostById(com.project.gamerscreed.model.dto.User)
	 */
	@Override
	public List<Post> getPostById(User anUser) {
		
		TypedQuery<Post> query = this.entityManager.createNamedQuery("Post.findAllByUser", Post.class);
		query.setParameter("id", anUser.getId());
		List<Post> result = query.getResultList();
		
		return result;
	}

}
