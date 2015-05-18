package com.project.gamerscreed;

import com.project.gamerscreed.control.utilities.Encrypter;
import com.project.gamerscreed.model.dao.UserDAO;
import com.project.gamerscreed.model.dao.implentation.UserDAOLayer;
import com.project.gamerscreed.model.dto.User;


public class MainCoreTest {

	public static void main(String[] args) {
//		Role role = new Role("ADMIN");
//		
//		User user = new User();
//		user.setName("Adrià Nieto");
//		user.setRole(role);
//		user.setUsername("nieto");
//		user.setPassword(Encrypter.getHash("1234"));
//		Address tmpAddress = new Address();
//		tmpAddress.setCp(800);
//		tmpAddress.setStreet("Street blabla");
//		tmpAddress.setCity(new City("Barcelona", new Country("Spain")));
//		user.setAddress(tmpAddress);
//		
//		Videogame videogame = new Videogame();
//		videogame.setYear(2004);
//		videogame.setName("Call of Duty 4");
//		videogame.setDeveloper(new Brand("Infinity Ward", new Country("USA")));
//		videogame.setPublisher(new Brand("Activision", new Country("USA")));
//		videogame.setConfirmed(true);
//		
//		UserDAO dao = new UserDAOLayer();
//		dao.create(user);
		
//		UserDAO dao = new UserDAOLayer();
//		
//		List<User> users = (List<User>) dao.getAll();
//		
//		for(User u : users){
//			System.out.println(u.toString());
//		}
		
//		String username = "nieto";
//		String pwd = Encrypter.getHash("1234");
//		
//		User obtUser = new UserDAOLayer().login(username, pwd);
//		System.out.println(obtUser.toString());
		
		UserDAO dao = new UserDAOLayer();
		dao.getAllUsernames();
				
		System.exit(0);
		
	}

}
