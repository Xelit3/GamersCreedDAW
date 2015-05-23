package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dao.VideogameDAO;
import com.project.gamerscreed.model.dto.Brand;
import com.project.gamerscreed.model.dto.Videogame;

public class VideogameDAOLayer extends GenericDAOLayer implements VideogameDAO{
	
	public VideogameDAOLayer() {
		super();
	}

	@Override
	public boolean create(Videogame aVideogame) {
		try{			
			this.beginTransaction();
			aVideogame.setDeveloper(this.entityManager.getReference(Brand.class, aVideogame.getDeveloper().getId()));
			aVideogame.setPublisher(this.entityManager.getReference(Brand.class, aVideogame.getPublisher().getId()));
			this.entityManager.persist(aVideogame);
			
			aVideogame.getPublisher().addVideogamesPublished(aVideogame);
			aVideogame.getDeveloper().addVideogamesDeveloped(aVideogame);
			
			this.entityManager.merge(aVideogame.getDeveloper());
			this.entityManager.merge(aVideogame.getPublisher());
			
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
	public boolean modify(Videogame anObject) {
		try{			
			this.beginTransaction();
			this.entityManager.merge(anObject);
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
	public boolean remove(Videogame anObject) {
		try{			
			this.beginTransaction();
			this.entityManager.remove(anObject);
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

	@Override
	public List<Videogame> getAllUnconfirmedGames() {
		TypedQuery<Videogame> query = this.entityManager.createNamedQuery("Videogame.findAllUnconfirmed", Videogame.class);
		List<Videogame> result = query.getResultList();
		
		return result;
	}

}