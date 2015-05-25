package com.project.gamerscreed.model.dao;

import com.project.gamerscreed.model.dto.ForumMessage;
import com.project.gamerscreed.model.dto.ForumThread;

public interface ForumThreadDAO extends GenericDAOInterface<ForumThread> {

	boolean commentThread(ForumThread aThread, ForumMessage aMessage);

}
