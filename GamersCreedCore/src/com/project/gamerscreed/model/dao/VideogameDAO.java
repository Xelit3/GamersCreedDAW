package com.project.gamerscreed.model.dao;

import java.util.List;

import com.project.gamerscreed.model.dto.Videogame;

public interface VideogameDAO extends GenericDAOInterface{
	public List<Videogame> getAllVideogamesByUser();
}
