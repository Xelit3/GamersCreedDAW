package com.project.gamerscreed.model.dao.implentation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dao.UserDAO;
import com.project.gamerscreed.model.dto.Address;
import com.project.gamerscreed.model.dto.City;
import com.project.gamerscreed.model.dto.Country;
import com.project.gamerscreed.model.dto.Role;
import com.project.gamerscreed.model.dto.User;
import com.project.gamerscreed.model.dto.Videogame;

/**
 * The Class UserDAOLayer.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class UserDAOLayer extends GenericDAOLayer implements UserDAO{
	
	/**
	 * Instantiates a new user dao layer.
	 */
	public UserDAOLayer() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#create(java.lang.Object)
	 */
	@Override
	public boolean create(User anObject) {
		try{			
			this.beginTransaction();
			Role tmpRole = anObject.getRole();
			anObject.setRole(this.entityManager.getReference(Role.class, tmpRole.getId()));
			this.entityManager.persist(anObject);
			tmpRole.addUser(anObject);
			this.entityManager.merge(tmpRole);
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
	public boolean modify(User anObject) {
		try{			
			this.beginTransaction();
			User tmpUserReference = this.entityManager.getReference(User.class, anObject.getId());
			if(anObject.getName() != null)
				tmpUserReference.setName(anObject.getName());
			if(anObject.getMail() != null)
				tmpUserReference.setMail(anObject.getMail());
			if(tmpUserReference.getAddress() == null)
				tmpUserReference.setAddress(new Address());
			if(anObject.getAddress().getStreet() != null)
				tmpUserReference.getAddress().setStreet(anObject.getAddress().getStreet());
			if(anObject.getAddress().getCp() > 0)
				tmpUserReference.getAddress().setCp(anObject.getAddress().getCp());
			if(tmpUserReference.getAddress().getCity() == null)
				tmpUserReference.getAddress().setCity(new City());
			if(anObject.getAddress().getCity().getCountry().getId() > 0)
				tmpUserReference.getAddress().getCity().setCountry(this.entityManager.getReference(Country.class, anObject.getAddress().getCity().getCountry().getId()));
			if(anObject.getAddress().getCity().getName() != null)
				tmpUserReference.getAddress().getCity().setName(anObject.getAddress().getCity().getName());
						
			this.entityManager.merge(tmpUserReference);
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
	 * @see com.project.gamerscreed.model.dao.GenericDAOInterface#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(User anObject) {
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
	public List<User> getAll() {
		TypedQuery<User> query = this.entityManager.createNamedQuery("User.findAll", User.class);
		List<User> result = query.getResultList();
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.UserDAO#login(java.lang.String, java.lang.String)
	 */
	@Override
	public User login(String aUsername, String aPassword) {
		TypedQuery<User> query = this.entityManager.createNamedQuery("User.loginUser", User.class);
		query.setParameter("username", aUsername);
		query.setParameter("password", aPassword);
		
		User tmpUser = query.getSingleResult();
			
		return tmpUser;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.UserDAO#getAllUsernames()
	 */
	@Override
	public Map<String, String> getAllUsernames() {
		Map<String, String> tmpUsernames = new HashMap<String, String>();
		
		TypedQuery<User> query = this.entityManager.createNamedQuery("User.findAll", User.class);
		
		for(User u : query.getResultList()){
			tmpUsernames.put(u.getUsername(), "");
		}
		
		return tmpUsernames;
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.UserDAO#addVideogameToUser(com.project.gamerscreed.model.dto.User, com.project.gamerscreed.model.dto.Videogame)
	 */
	@Override
	public boolean addVideogameToUser(User anUser, Videogame aVideogame) {
		try{
			this.beginTransaction();
			
			User tmpUser = this.entityManager.getReference(User.class, anUser.getId());
			Videogame tmpVideogame = this.entityManager.getReference(Videogame.class, aVideogame.getId());
			
			tmpUser.addVideogame(tmpVideogame);
			tmpVideogame.addUser(tmpUser);
			
			this.entityManager.merge(tmpUser);
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
	 * @see com.project.gamerscreed.model.dao.UserDAO#removeVideogameFromUser(com.project.gamerscreed.model.dto.User, com.project.gamerscreed.model.dto.Videogame)
	 */
	@Override
	public boolean removeVideogameFromUser(User anUser, Videogame aVideogame) {
		try{
			this.beginTransaction();
			
			User tmpUser = this.entityManager.getReference(User.class, anUser.getId());
			Videogame tmpVideogame = this.entityManager.getReference(Videogame.class, aVideogame.getId());
			
			tmpUser.removeVideogame(tmpVideogame);
			tmpVideogame.removeUser(tmpUser);
			
			this.entityManager.merge(tmpUser);
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
	 * @see com.project.gamerscreed.model.dao.UserDAO#addFollower(com.project.gamerscreed.model.dto.User, com.project.gamerscreed.model.dto.User)
	 */
	@Override
	public boolean addFollower(User anUser, User aFollower) {
		try{
			this.beginTransaction();
			
			User tmpUser = this.entityManager.getReference(User.class, anUser.getId());
			User tmpFollower = this.entityManager.getReference(User.class, aFollower.getId());
			
			tmpUser.addFollower(tmpFollower);
			tmpFollower.addFollowing(tmpUser);
			
			this.entityManager.merge(tmpUser);
			this.entityManager.merge(tmpFollower);
			
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
	 * @see com.project.gamerscreed.model.dao.UserDAO#removeFollower(com.project.gamerscreed.model.dto.User, com.project.gamerscreed.model.dto.User)
	 */
	@Override
	public boolean removeFollower(User anUser, User aFollower) {
		try{
			this.beginTransaction();
			
			User tmpUser = this.entityManager.getReference(User.class, anUser.getId());
			User tmpFollower = this.entityManager.getReference(User.class, aFollower.getId());
			
			tmpUser.removeFollower(tmpFollower);
			tmpFollower.removeFollowing(tmpUser);
			
			this.entityManager.merge(tmpUser);
			this.entityManager.merge(tmpFollower);
			
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
	 * @see com.project.gamerscreed.model.dao.UserDAO#addFollowing(com.project.gamerscreed.model.dto.User, com.project.gamerscreed.model.dto.User)
	 */
	@Override
	public boolean addFollowing(User anUser, User aFollowing) {
		try{
			this.beginTransaction();
			
			User tmpUser = this.entityManager.getReference(User.class, anUser.getId());
			User tmpFollowing = this.entityManager.getReference(User.class, aFollowing.getId());
			
			tmpUser.addFollowing(aFollowing);
			aFollowing.addFollower(tmpUser);
			
			this.entityManager.merge(tmpUser);
			this.entityManager.merge(tmpFollowing);
			
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
	 * @see com.project.gamerscreed.model.dao.UserDAO#removeFollowing(com.project.gamerscreed.model.dto.User, com.project.gamerscreed.model.dto.User)
	 */
	@Override
	public boolean removeFollowing(User anUser, User aFollowing) {
		try{
			this.beginTransaction();
			
			User tmpUser = this.entityManager.getReference(User.class, anUser.getId());
			User tmpFollowing = this.entityManager.getReference(User.class, aFollowing.getId());
			
			tmpUser.removeFollowing(tmpFollowing);
			tmpFollowing.removeFollower(tmpUser);
			
			this.entityManager.merge(tmpUser);
			this.entityManager.merge(tmpFollowing);
			
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
	 * @see com.project.gamerscreed.model.dao.UserDAO#getUserById(int)
	 */
	@Override
	public User getUserById(int anId) {
		TypedQuery<User> query = this.entityManager.createNamedQuery("User.getById", User.class);
		query.setParameter("id", anId);
				
		return query.getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.UserDAO#getOtherUsers(int)
	 */
	@Override
	public List<User> getOtherUsers(int anId) {
		TypedQuery<User> query = this.entityManager.createNamedQuery("User.findOthers", User.class);
		query.setParameter("id", anId);
				
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.project.gamerscreed.model.dao.UserDAO#searchUser(String)
	 */
	@Override
	public List<User> searchUser(String aUsername) {
		TypedQuery<User> query = this.entityManager.createNamedQuery("User.searchUser", User.class);
		query.setParameter("username", aUsername);
				
		return query.getResultList();
	}

}