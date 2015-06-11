package com.project.gamerscreed.model.dao.implentation;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dao.OperationDAO;
import com.project.gamerscreed.model.dto.Operation;
import com.project.gamerscreed.model.dto.User;
import com.project.gamerscreed.model.dto.Videogame;

/**
 * The Class OperationDAOLayer.
 * @author: Xavi Rueda
 * @version: 1.0, 5/27/15
 */
public class OperationDAOLayer extends GenericDAOLayer implements OperationDAO {

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#create(java.lang.Object)
	 */
	@Override
	public boolean create(Operation anOperation) {
		try{
			this.beginTransaction();
			
			anOperation.setUserReceived(this.entityManager.getReference(User.class, anOperation.getUserReceived().getId()));
			anOperation.setUserSender(this.entityManager.getReference(User.class, anOperation.getUserSender().getId()));
			anOperation.setVideogameReceived(this.entityManager.getReference(Videogame.class, anOperation.getVideogameReceived().getId()));
			//Just in case of a trade between two games
			if(anOperation.getVideogameSended().getId() > 0){
				anOperation.setVideogameSended(this.entityManager.getReference(Videogame.class, anOperation.getVideogameSended().getId()));
				anOperation.getVideogameSended().addOperationsSended(anOperation);
				this.entityManager.merge(anOperation.getVideogameSended());
			}
			else{
				anOperation.setVideogameSended(this.entityManager.getReference(Videogame.class, 0));
				anOperation.getVideogameSended().addOperationsSended(anOperation);
				this.entityManager.merge(anOperation.getVideogameSended());
			}	
			
			this.entityManager.persist(anOperation);
			
			anOperation.getUserReceived().addOperationsReceived(anOperation);
			anOperation.getUserSender().addOperationsSended(anOperation);
			
			anOperation.getVideogameReceived().addOperationsReceived(anOperation);
			
			this.entityManager.merge(anOperation.getUserReceived());
			this.entityManager.merge(anOperation.getUserSender());			
			this.entityManager.merge(anOperation.getVideogameReceived());
			
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
	public boolean modify(Operation anObject) {
		//Will not be implemented. NOT NEEDED
		return false;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Operation anObject) {
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

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#getAll()
	 */
	@Override
	public List<Operation> getAll() {
		TypedQuery<Operation> query = this.entityManager.createNamedQuery("Operation.findAll", Operation.class);
		List<Operation> result = query.getResultList();
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.OperationDAO#acceptOperation(int)
	 */
	@Override
	public boolean acceptOperation(int anId) {
		try {
			this.beginTransaction();
			
			Operation tmpOperation = this.entityManager.getReference(Operation.class, anId);
			tmpOperation.setRejected(false);
			tmpOperation.setDateAccepted(new Date());
			
			tmpOperation.setUserSender(this.entityManager.getReference(User.class, tmpOperation.getUserSender().getId()));
			tmpOperation.setUserReceived(this.entityManager.getReference(User.class, tmpOperation.getUserReceived().getId()));
			tmpOperation.setVideogameReceived(this.entityManager.getReference(Videogame.class, tmpOperation.getVideogameReceived().getId()));
			tmpOperation.setVideogameSended(this.entityManager.getReference(Videogame.class, tmpOperation.getVideogameSended().getId()));
			
			tmpOperation.getUserSender().getVideogames().add(tmpOperation.getVideogameReceived());
			tmpOperation.getUserReceived().getVideogames().remove(tmpOperation.getVideogameReceived());
			
			tmpOperation.getVideogameReceived().getUsers().remove(tmpOperation.getUserReceived());
			tmpOperation.getVideogameReceived().getUsers().add(tmpOperation.getUserSender());
					
			if(tmpOperation.getVideogameSended().getId() != 0){
				tmpOperation.getUserReceived().getVideogames().add(tmpOperation.getVideogameSended());
				tmpOperation.getUserSender().getVideogames().remove(tmpOperation.getVideogameSended());
				tmpOperation.getVideogameSended().getUsers().remove(tmpOperation.getUserSender());
				tmpOperation.getVideogameSended().getUsers().add(tmpOperation.getUserReceived());
				
				this.entityManager.merge(tmpOperation.getVideogameSended());
			}
			
			this.entityManager.merge(tmpOperation.getVideogameReceived());
			this.entityManager.merge(tmpOperation.getUserSender());
			this.entityManager.merge(tmpOperation.getUserReceived());
			
			this.entityManager.merge(tmpOperation);
			
			this.commitTransaction();
			this.closeTransaction();
			
			return true;
		} 
		catch (Exception e) {			
			e.printStackTrace();
			
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.OperationDAO#rejectOperation(int)
	 */
	@Override
	public boolean rejectOperation(int anId) {
		try{
			this.beginTransaction();
			
			Operation tmpOperation = this.entityManager.getReference(Operation.class, anId);
			tmpOperation.setRejected(true);
			
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
	public List<Operation> getAllUnconfirmedByUser(int anId) {
		TypedQuery<Operation> query = this.entityManager.createNamedQuery("Operation.findAllByUser", Operation.class);
		query.setParameter("userId", anId);
				
		return query.getResultList();
	}	

}
