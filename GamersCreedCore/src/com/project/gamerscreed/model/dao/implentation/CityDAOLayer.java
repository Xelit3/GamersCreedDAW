package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOInterface;
import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dto.City;

/**
 * The Class CityDAOLayer.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class CityDAOLayer extends GenericDAOLayer implements GenericDAOInterface<City> {

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#create(java.lang.Object)
	 */
	@Override
	public boolean create(City anObject) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#modify(java.lang.Object)
	 */
	@Override
	public boolean modify(City anObject) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(City anObject) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#getAll()
	 */
	@Override
	public List<City> getAll() {
		TypedQuery<City> query = this.entityManager.createNamedQuery("City.findAll", City.class);
		List<City> result = query.getResultList();
		
		return result;
	}

}
