package com.project.gamerscreed.model.dao.implentation;

import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dao.OperationDAO;
import com.project.gamerscreed.model.dto.Operation;

public class OperationDAOLayer extends GenericDAOLayer implements OperationDAO {

	@Override
	public boolean create(Object anObject) {
		try{
			Operation tmpOperation = (Operation) anObject;
			this.beginTransaction();
			this.entityManager.persist(tmpOperation);
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
			Operation tmpOperation = (Operation) anObject;
			this.beginTransaction();
			this.entityManager.merge(tmpOperation);
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
			Operation tmpOperation = (Operation) anObject;
			
			this.beginTransaction();
			this.entityManager.remove(tmpOperation);
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
	public List<Operation> getAll() {
		TypedQuery<Operation> query = this.entityManager.createNamedQuery("Operation.findAll", Operation.class);
		List<Operation> result = query.getResultList();
		
		return result;
	}

	@Override
	public boolean acceptOperation(int anId) {
		//TODO Accept operation
		return false;
	}

	@Override
	public boolean rejectOperation(int anId) {
		try{
			TypedQuery<Operation> query = this.entityManager.createNamedQuery("Operation.rejectOperation", Operation.class);
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
