package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOInterface;
import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dto.ForumThread;

public class ForumThreadDAOLayer extends GenericDAOLayer implements GenericDAOInterface{

	@Override
	public boolean create(Object anObject) {
		try{
			ForumThread tmpForumThread = (ForumThread) anObject;
			this.beginTransaction();
			this.entityManager.persist(tmpForumThread);
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
			ForumThread tmpForumThread = (ForumThread) anObject;
			this.beginTransaction();
			this.entityManager.merge(tmpForumThread);
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
			ForumThread tmpForumThread = (ForumThread) anObject;
			
			this.beginTransaction();
			this.entityManager.remove(tmpForumThread);
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
	public List<ForumThread> getAll() {
		TypedQuery<ForumThread> query = this.entityManager.createNamedQuery("ForumThread.findAll", ForumThread.class);
		List<ForumThread> result = query.getResultList();
		
		return result;
	}

}
