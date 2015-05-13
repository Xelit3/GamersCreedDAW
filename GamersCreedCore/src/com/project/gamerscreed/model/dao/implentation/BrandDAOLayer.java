package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOInterface;
import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dto.Brand;
import com.project.gamerscreed.model.dto.User;

public class BrandDAOLayer extends GenericDAOLayer implements GenericDAOInterface {

	public BrandDAOLayer() {
		super();
	}

	@Override
	public boolean create(Object anObject) {		
		try{
			Brand tmpRole = (Brand) anObject;
			
			this.beginTransaction();
			this.entityManager.persist(tmpRole);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object anObject) {
		try{
			Brand tmpBrand = (Brand) anObject;
			
			this.beginTransaction();
			this.entityManager.remove(tmpBrand);
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
