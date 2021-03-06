package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dao.VideogameDAO;
import com.project.gamerscreed.model.dto.Brand;
import com.project.gamerscreed.model.dto.Videogame;

/**
 * The Class VideogameDAOLayer.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class VideogameDAOLayer extends GenericDAOLayer implements VideogameDAO{
	
	/**
	 * Instantiates a new videogame dao layer.
	 */
	public VideogameDAOLayer() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#create(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#modify(java.lang.Object)
	 */
	@Override
	public boolean modify(Videogame aVideogame) {
		try{			
			this.beginTransaction();
			Videogame tmpVideogame = this.entityManager.getReference(Videogame.class, aVideogame.getId());
			if(!tmpVideogame.getName().equals(aVideogame.getName()))
				tmpVideogame.setName(aVideogame.getName());
			tmpVideogame.setPublisher(this.entityManager.getReference(Brand.class, aVideogame.getPublisher().getId()));
			tmpVideogame.setDeveloper(this.entityManager.getReference(Brand.class, aVideogame.getDeveloper().getId()));
			tmpVideogame.setYear(aVideogame.getYear());
			this.entityManager.merge(tmpVideogame);
			this.commitTransaction();
			
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

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#getAll()
	 */
	@Override
	public List<Videogame> getAll() {
		TypedQuery<Videogame> query = this.entityManager.createNamedQuery("Videogame.findAll", Videogame.class);
		List<Videogame> result = query.getResultList();
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.VideogameDAO#confirmVideogame(int)
	 */
	@Override
	public boolean changeConfirmation(int anId) {
		try{			
			Videogame tmpVideogame = new Videogame();
			tmpVideogame.setId(anId);
			
			this.beginTransaction();
			tmpVideogame = this.entityManager.getReference(Videogame.class, tmpVideogame.getId());
			tmpVideogame.setConfirmed(!tmpVideogame.getConfirmed());
			
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

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.VideogameDAO#getAllUnconfirmedGames()
	 */
	@Override
	public List<Videogame> getAllByConfirmation(boolean aConf) {
		TypedQuery<Videogame> query = this.entityManager.createNamedQuery("Videogame.findAllUnconfirmed", Videogame.class);
		query.setParameter("confirmed", aConf);
		List<Videogame> result = query.getResultList();
		
		return result;
	}

	@Override
	public List<Videogame> searchVideogame(String aName) {
		TypedQuery<Videogame> query = this.entityManager.createNamedQuery("Videogame.searchVideogame", Videogame.class);
//		query.setParameter("name", aName);
		query.setParameter("name", "%" + aName + "%");
		List<Videogame> result = query.getResultList();
		
		return result;
	}
	
	public void closeTransactionConnection(){
		this.closeTransaction();
	}

}