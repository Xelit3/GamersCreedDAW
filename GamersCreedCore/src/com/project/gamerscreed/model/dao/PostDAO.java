package com.project.gamerscreed.model.dao;

import java.util.List;

import com.project.gamerscreed.model.dto.Post;
import com.project.gamerscreed.model.dto.User;

/**
 * The Interface PostDAO.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public interface PostDAO extends GenericDAOInterface<Post> {
	
	/**
	 * Gets the post by id.
	 *
	 * @param anUser the an user
	 * @return the post by id
	 */
	public List<Post> getPostById(User anUser);
	
}