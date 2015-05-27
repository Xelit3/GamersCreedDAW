package com.project.gamerscreed.model.dao;

import java.util.List;

import com.project.gamerscreed.model.dto.ForumMessage;
import com.project.gamerscreed.model.dto.ForumSection;
import com.project.gamerscreed.model.dto.ForumThread;

/**
 * The Interface ForumThreadDAO.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public interface ForumThreadDAO extends GenericDAOInterface<ForumThread> {

	/**
	 * Gets the all sections.
	 *
	 * @return the all sections
	 */
	public List<ForumSection> getAllSections();
	
	/**
	 * Comment thread.
	 *
	 * @param aThread the a thread
	 * @param aMessage the a message
	 * @return true, if successful
	 */
	public boolean commentThread(ForumThread aThread, ForumMessage aMessage);

}