package com.project.gamerscreed.model.dao;

import java.util.List;

import com.project.gamerscreed.model.dto.Post;
import com.project.gamerscreed.model.dto.User;

public interface PostDAO extends GenericDAOInterface<Post> {
	
	public List<Post> getPostById(User anUser);
	
}