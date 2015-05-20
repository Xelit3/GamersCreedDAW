package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dao.VideogameDAO;
import com.project.gamerscreed.model.dto.Videogame;

public class VideogameDAOLayer extends GenericDAOLayer implements VideogameDAO{
	
	public VideogameDAOLayer() {
		super();
	}

	@Override
	public boolean create(Object anObject) {
		try{
			Videogame tmpVideogame = (Videogame) anObject;
			this.beginTransaction();
			this.entityManager.persist(tmpVideogame);
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
			Videogame tmpVideogame = (Videogame) anObject;
			this.beginTransaction();
			this.entityManager.merge(tmpVideogame);
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
			Videogame tmpVideogame = (Videogame) anObject;
			
			this.beginTransaction();
			this.entityManager.remove(tmpVideogame);
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
	public List<Videogame> getAll() {
		TypedQuery<Videogame> query = this.entityManager.createNamedQuery("Videogame.findAll", Videogame.class);
		List<Videogame> result = query.getResultList();
		
		return result;
	}

	@Override
	public boolean confirmVideogame(int anId) {
		try{			
			TypedQuery<Videogame> query = this.entityManager.createNamedQuery("Videogame.confirmVideogame", Videogame.class);
			query.setParameter("id", anId);
			int tmpResult = query.executeUpdate();
			
			if(tmpResult > 0)
				return true;
			else
				return false;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}	
		
	}

}