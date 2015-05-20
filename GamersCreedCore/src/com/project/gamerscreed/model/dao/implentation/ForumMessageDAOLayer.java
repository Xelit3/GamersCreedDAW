package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOInterface;
import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dto.ForumMessage;

public class ForumMessageDAOLayer extends GenericDAOLayer implements GenericDAOInterface {

	@Override
	public boolean create(Object anObject) {
		try{
			ForumMessage tmpForumMessage = (ForumMessage) anObject;
			this.beginTransaction();
			this.entityManager.persist(tmpForumMessage);
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
			ForumMessage tmpForumMessage = (ForumMessage) anObject;
			this.beginTransaction();
			this.entityManager.merge(tmpForumMessage);
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
			ForumMessage tmpForumMessage = (ForumMessage) anObject;
			
			this.beginTransaction();
			this.entityManager.remove(tmpForumMessage);
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
	public List<ForumMessage> getAll() {
		TypedQuery<ForumMessage> query = this.entityManager.createNamedQuery("ForumMessage.findAll", ForumMessage.class);
		List<ForumMessage> result = query.getResultList();
		
		return result;
	}

}
