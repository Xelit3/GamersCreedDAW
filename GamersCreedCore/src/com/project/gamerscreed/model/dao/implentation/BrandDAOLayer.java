package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOInterface;
import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dto.Brand;

/**
 * The Class BrandDAOLayer.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class BrandDAOLayer extends GenericDAOLayer implements GenericDAOInterface<Brand> {

	/**
	 * Instantiates a new brand dao layer.
	 */
	public BrandDAOLayer() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#create(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#modify(java.lang.Object)
	 */
	@Override
	public boolean modify(Brand anObject) {
		//Will not be implemented. NOT NEEDED
		return false;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#remove(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#getAll()
	 */
	@Override
	public List<Brand> getAll() {
		TypedQuery<Brand> query = this.entityManager.createNamedQuery("Brand.findAll", Brand.class);
		List<Brand> result = query.getResultList();
		
		return result;
	}

}
