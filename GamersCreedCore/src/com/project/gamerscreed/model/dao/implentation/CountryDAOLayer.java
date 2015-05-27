package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOInterface;
import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dto.Brand;
import com.project.gamerscreed.model.dto.Country;
import com.project.gamerscreed.model.dto.User;

/**
 * The Class CountryDAOLayer.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class CountryDAOLayer extends GenericDAOLayer implements GenericDAOInterface<Country>{

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#create(java.lang.Object)
	 */
	@Override
	public boolean create(Country anObject) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#modify(java.lang.Object)
	 */
	@Override
	public boolean modify(Country anObject) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Country anObject) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#getAll()
	 */
	@Override
	public List<Country> getAll() {
		TypedQuery<Country> query = this.entityManager.createNamedQuery("Country.findAll", Country.class);
		List<Country> result = query.getResultList();
		
		return result;
	}

}
