package com.project.gamerscreed;

import java.util.Date;
import java.util.List;

import com.project.gamerscreed.control.utilities.Encrypter;
import com.project.gamerscreed.model.dao.UserDAO;
import com.project.gamerscreed.model.dao.VideogameDAO;
import com.project.gamerscreed.model.dao.implentation.PostDAOLayer;
import com.project.gamerscreed.model.dao.implentation.UserDAOLayer;
import com.project.gamerscreed.model.dao.implentation.VideogameDAOLayer;
import com.project.gamerscreed.model.dto.Address;
import com.project.gamerscreed.model.dto.Brand;
import com.project.gamerscreed.model.dto.City;
import com.project.gamerscreed.model.dto.Country;
import com.project.gamerscreed.model.dto.Post;
import com.project.gamerscreed.model.dto.Role;
import com.project.gamerscreed.model.dto.Role.RoleType;
import com.project.gamerscreed.model.dto.User;
import com.project.gamerscreed.model.dto.Videogame;



public class MainCoreTest {

	public static void main(String[] args) {
		
//		Country tmpCountry1 = new Country("USA");		
//
//		Brand tmpBrand = new Brand();
//		tmpBrand.setCountry(tmpCountry1);
//		tmpBrand.setName("Electronic Arts");
//		
//		Videogame tmpVideogame = new Videogame();
//		tmpVideogame.setName("Medal of Honor");
//		tmpVideogame.setDeveloper(tmpBrand);
//		tmpVideogame.setPublisher(tmpBrand);
//		tmpVideogame.setYear(2002);
//		
//		Role tmpRole = new Role(RoleType.ADMIN);
//		
//		User tmpUser = new User(tmpRole);
//		
//		Country tmpCountry2 = new Country("SPAIN");
//		City tmpCity = new City("Barcelona", tmpCountry2);
//		
//		Address tmpAddress = new Address();
//		tmpAddress.setCity(tmpCity);
//		tmpAddress.setCp(8024);
//		tmpAddress.setStreet("Direccion Xelit3");
//				
//		tmpUser.setName("Xavi Rueda");
//		tmpUser.setUsername("Xelit3");
//		tmpUser.setPassword(Encrypter.getHash("1234"));
//		tmpUser.setAddress(tmpAddress);
//		tmpUser.setMail("xelit3@mail.com");
//		
//		Address tmpAddress2 = new Address();
//		tmpAddress2.setCity(tmpCity);
//		tmpAddress2.setCp(8024);
//		tmpAddress2.setStreet("Direccion Xelit3");
//		
//		User tmpUser2 = new User(tmpRole);
//		tmpUser2.setName("Adrià Rueda");
//		tmpUser2.setUsername("Adri");
//		tmpUser2.setPassword(Encrypter.getHash("1234"));
//		tmpUser2.setAddress(tmpAddress2);
//		tmpUser2.setMail("xelit3@mail.com");
//		
//		tmpUser2.addVideogame(tmpVideogame);
//		
//		tmpUser.addFollower(tmpUser2);
//		tmpUser.addFollowing(tmpUser2);
//		
//		tmpUser2.addFollower(tmpUser);
//		tmpUser2.addFollowing(tmpUser);
//		
//		UserDAO layer = new UserDAOLayer();
//		layer.create(tmpUser);
//		layer.create(tmpUser2);
		
//		VideogameDAO vGameLayer = new VideogameDAOLayer();
//		
//		vGameLayer.getAll();
		
//		Post post = new Post();
//		post.setContent("Post test");
//		User user = new User();
//		user.setId(2);
//		post.setUser(user);
//		post.setPostDate(new Date());
//		
//		PostDAOLayer layer = new PostDAOLayer();
//		layer.create(post);
		
//		UserDAO layer = new UserDAOLayer();
//		List<User> all = layer.getAll();
//		User yo = all.get(0);
//		yo = layer.getAllReferences(yo);		
//								
//		System.exit(0);
		
	}

}
