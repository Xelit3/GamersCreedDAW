package com.project.gamerscreed.model.dao.implentation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import com.project.gamerscreed.model.dao.GenericDAOLayer;
import com.project.gamerscreed.model.dao.UserDAO;
import com.project.gamerscreed.model.dto.Operation;
import com.project.gamerscreed.model.dto.Role;
import com.project.gamerscreed.model.dto.User;
import com.project.gamerscreed.model.dto.Videogame;

public class UserDAOLayer extends GenericDAOLayer implements UserDAO{
	
	public UserDAOLayer() {
		super();
	}

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

	@Override
	public boolean modify(User anObject) {
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

	@Override
	public List<User> getAll() {
		TypedQuery<User> query = this.entityManager.createNamedQuery("User.findAll", User.class);
		List<User> result = query.getResultList();
		
		return result;
	}

	@Override
	public User login(String aUsername, String aPassword) {
		TypedQuery<User> query = this.entityManager.createNamedQuery("User.loginUser", User.class);
		query.setParameter("username", aUsername);
		query.setParameter("password", aPassword);
		
		User tmpUser = query.getSingleResult();
			
		return tmpUser;
	}

	@Override
	public Map<String, String> getAllUsernames() {
		Map<String, String> tmpUsernames = new HashMap<String, String>();
		
		TypedQuery<User> query = this.entityManager.createNamedQuery("User.findAll", User.class);
		
		for(User u : query.getResultList()){
			tmpUsernames.put(u.getUsername(), "");
		}
		
		return tmpUsernames;
	}

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

	@Override
	public User getAllReferences(User anUser) {
		this.beginTransaction();
		for(int i = 0; i < anUser.getFollowers().size(); i++){
			anUser.getFollowers().set(i, this.entityManager.getReference(User.class, anUser.getFollowers().get(i).getId()));
		}
		for(int i = 0; i < anUser.getFollowings().size(); i++){
			anUser.getFollowings().set(i, this.entityManager.getReference(User.class, anUser.getFollowings().get(i).getId()));
		}
		for(int i = 0; i < anUser.getOperationsSended().size(); i++){
			anUser.getOperationsSended().set(i, this.entityManager.getReference(Operation.class, anUser.getOperationsSended().get(i).getId()));
		}	
		for(int i = 0; i < anUser.getOperationsReceived().size(); i++){
			anUser.getOperationsReceived().set(i, this.entityManager.getReference(Operation.class, anUser.getOperationsReceived().get(i).getId()));
		}
		for(int i = 0; i < anUser.getVideogames().size(); i++){
			anUser.getVideogames().set(i, this.entityManager.getReference(Videogame.class, anUser.getVideogames().get(i).getId()));
		}
		this.commitTransaction();
		this.closeTransaction();
		
		return anUser;
	}

}