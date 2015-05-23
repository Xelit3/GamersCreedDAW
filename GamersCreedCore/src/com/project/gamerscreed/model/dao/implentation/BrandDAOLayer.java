package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOInterface;
import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dto.Brand;

public class BrandDAOLayer extends GenericDAOLayer implements GenericDAOInterface<Brand> {

	public BrandDAOLayer() {
		super();
	}

	@Override
	public boolean create(Brand anObject) {		
		try{
			this.beginTransaction();
			this.entityManager.persist(anObject);
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
	public boolean modify(Brand anObject) {
		//Will not be implemented. NOT NEEDED
		return false;
	}

	@Override
	public boolean remove(Brand anObject) {
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
	public List<Brand> getAll() {
		TypedQuery<Brand> query = this.entityManager.createNamedQuery("Brand.findAll", Brand.class);
		List<Brand> result = query.getResultList();
		
		return result;
	}

}
