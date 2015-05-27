package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.ForumThreadDAO;
import com.project.gamerscreed.model.dao.GenericDAOInterface;
import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dto.ForumMessage;
import com.project.gamerscreed.model.dto.ForumSection;
import com.project.gamerscreed.model.dto.ForumThread;
import com.project.gamerscreed.model.dto.User;

/**
 * The Class ForumThreadDAOLayer.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class ForumThreadDAOLayer extends GenericDAOLayer implements ForumThreadDAO{

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#create(java.lang.Object)
	 */
	@Override
	public boolean create(ForumThread aThread) {
		try{
			this.beginTransaction();
			aThread.setUser(this.entityManager.getReference(User.class, aThread.getUser().getId()));
			aThread.setForumSection(this.entityManager.getReference(ForumSection.class, aThread.getForumSection().getId()));
			this.entityManager.persist(aThread);
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
	public boolean modify(ForumThread aThread) {
		try{			
			this.beginTransaction();
			this.entityManager.merge(aThread);
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
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(ForumThread aThread) {
		try{			
			this.beginTransaction();
			this.entityManager.remove(aThread);
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
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#getAll()
	 */
	@Override
	public List<ForumThread> getAll() {
		TypedQuery<ForumThread> query = this.entityManager.createNamedQuery("ForumThread.findAll", ForumThread.class);
		List<ForumThread> result = query.getResultList();
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.ForumThreadDAO#commentThread(com.project.gamerscreed.model.dto.ForumThread, com.project.gamerscreed.model.dto.ForumMessage)
	 */
	@Override
	public boolean commentThread(ForumThread aThread, ForumMessage aMessage) {
		try{
			aThread = this.entityManager.getReference(ForumThread.class, aThread.getId());
			aMessage.setForumThread(aThread);
			aMessage.setUser(this.entityManager.getReference(User.class, aMessage.getUser().getId()));
			aThread.addForumMessage(aMessage);
			
			this.entityManager.persist(aMessage);
			this.entityManager.merge(aThread);
			
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
	 * @see com.project.gamerscreed.model.dao.ForumThreadDAO#getAllSections()
	 */
	@Override
	public List<ForumSection> getAllSections() {
		TypedQuery<ForumSection> query = this.entityManager.createNamedQuery("ForumSection.findAll", ForumSection.class);
		List<ForumSection> result = query.getResultList();
		
		return result;
	}

}
