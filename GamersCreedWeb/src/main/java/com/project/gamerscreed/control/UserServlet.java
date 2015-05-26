package com.project.gamerscreed.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.project.gamerscreed.control.utilities.Encrypter;
import com.project.gamerscreed.model.dao.UserDAO;
import com.project.gamerscreed.model.dao.implentation.PostDAOLayer;
import com.project.gamerscreed.model.dao.implentation.UserDAOLayer;
import com.project.gamerscreed.model.dto.Post;
import com.project.gamerscreed.model.dto.Role;
import com.project.gamerscreed.model.dto.Role.RoleType;
import com.project.gamerscreed.model.dto.User;
import com.project.gamerscreed.service.SessionBean;
import com.project.gamerscreed.view.UserBasicData;


/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/html; charset=iso-8859-1" );
		PrintWriter out = response.getWriter();

		int action = Integer.parseInt(request.getParameter("action"));
		try{
			switch (action){
				case 10: //check if username already exist in DB
					checkUsername(request, response);
					break;
	
				case 11: //add user into DB
					addUser(request, response);  
					break;
					
				case 12: //login
					loginUser(request, response);
					break;
					
				case 13: //logout
					logoutUser(request);
					break;
				
				case 14: //checkSession
					checkSession(request, response);
					break;
					
				case 15: //Get all users
					getAllUsers(response);
					break;
					
				case 16: //search user Post
					searchPost(request, response);
					break;
					
				case 17: //submit user Post
					submitPost(request, response);
					break;
					
				case 18: //Get all list of data for session user
					getAllUserLists(request, response);
					break;
				
				default:
					out.println("Action number wrong");
					break;
			}
		}
		catch(IOException ioE){
			System.out.println("IO error");
			ioE.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Error in the server - "+e);
			e.printStackTrace();
		}
		
	}

	private void getAllUsers(HttpServletResponse response) throws IOException {
//		UserDAO tmpUserLayer = new UserDAOLayer();
//		List<User> tmpListaUsuarios = (List<User>) tmpUserLayer.getAll();
//		User[] tmpUsers =  new User[tmpListaUsuarios.size()];
//		for(int i = 0; i < tmpListaUsuarios.size(); i++){
//			tmpUsers[i] = new User();
//			tmpUsers[i].setName(tmpListaUsuarios.get(i).getName());
//			tmpUsers[i].setUsername(tmpListaUsuarios.get(i).getUsername());			
//		}
//		
//		Gson gson = new Gson();
////      Type tmpType = new TypeToken<List<User>>(){}.getType();
////      String json = new Gson().toJson(tmpUsers, tmpType);
//
//		String json = new Gson().toJson(tmpUsers);
//		
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(json);
	}

	private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		List<Object> array=new ArrayList<Object>();

		UserDAO tmpUserLayer = new UserDAOLayer();
		Map<String, String> tmpUsernames = tmpUserLayer.getAllUsernames();
		
		if(tmpUsernames.containsKey(username))
			array.add(false);		
		else
			array.add(true);
		
		String json = new Gson().toJson(array);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}
	
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();		
		
		User user = new Gson().fromJson(request.getParameter("JSONUserData"), User.class);
		user.setPassword(Encrypter.getHash(user.getPassword()));
		UserDAO userDAO =new UserDAOLayer();
		user.setRole(new Role(RoleType.BASIC));
		boolean val=userDAO.create(user);//TODO userDAO.crate crashes when creating user with role
		System.out.println("User creation is: "+val);
		out.print(val);		
	}
	
	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User tmpUser = new Gson().fromJson(request.getParameter("JSONUserData"), User.class);
		tmpUser.setPassword(Encrypter.getHash(tmpUser.getPassword()));
		UserDAO tmpUserLayer = new UserDAOLayer();
		User user = tmpUserLayer.login(tmpUser.getUsername(), tmpUser.getPassword());

		List<Object> array=new ArrayList<Object>();
				
        if(user==null || user.getUsername() == null || user.getUsername().equals("")){
            array.add(false);
        }
        else{
        	//TODO banned users response
        	startSession(request, user);//start server session
         	array.add(true);
        }
		String json = new Gson().toJson(array);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);		
	}
	
	private void searchPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		SessionBean sessionBean =sessionIsOpen(request);
		ArrayList array = new ArrayList();
		if (sessionBean != null) {
			User user = sessionBean.getUser();
			//UserDAO tmpUserLayer = new UserDAOLayer();
			//User user = tmpUserLayer.);//TODO POSTDAO del user
			
			//BEGIN TEST -POST USERS-
			for(int i=0;i<10;i++){
				Post post=new Post();
				post.setId(i);
				post.setUser(new User("conectado"+i,"paco2"));
				post.setContent("Content"+i);
				array.add(post);

			}
			//END TEST
		}
		else{
			//BEGIN TEST -POST USERS-
			for(int i=5;i<15;i++){
				Post post=new Post();
				post.setId(i);
				post.setUser(new User("des"+i,"paco"));
				post.setContent("Content"+i);
				array.add(post);

			}
			//END TEST
		}
		String json = new Gson().toJson(array);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
	
	private void submitPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();		
		
		SessionBean sessionBean =sessionIsOpen(request);
		User user=sessionBean.getUser();
		
		//START TESTING users without login
		UserDAO tmpUserLayer = new UserDAOLayer();
		user = tmpUserLayer.login(user.getUsername(), user.getPassword());
		//END TESTING 
		
		Post post = new Gson().fromJson(request.getParameter("JSONPostData"), Post.class);
		post.setUser(user);
		Date date = new Date();
		post.setPostDate(date);
		//TODO Post layer
		PostDAOLayer postLayer = new PostDAOLayer();
		boolean val = postLayer.create(post);
		
		//user.addPost(post);
		//UserDAO userDAO =new UserDAOLayer();
		//boolean val=userDAO.modify(user);
		out.println(val);
		
	}
	
    private void startSession(HttpServletRequest request, User user){
        HttpSession session = request.getSession();
        synchronized(session) {
            SessionBean sessionBean = (SessionBean)session.getAttribute("userSession");
            if (sessionBean != null){
            	logoutUser(request);
            }
            
            sessionBean = new SessionBean(user);
            session.setAttribute("userSession", sessionBean);
            
       }
    }
    
	private void logoutUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        synchronized(session) {
           SessionBean sessionBean = (SessionBean)session.getAttribute("userSession");
           if (sessionBean != null) {
               session.removeAttribute("userSession");
           }
       }
    }
	
	private void checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException{
		SessionBean sessionBean = sessionIsOpen(request);
		List<Object> array = new ArrayList<Object>();
		if (sessionBean != null) {
			User user = sessionBean.getUser();
			array.add(true);
			array.add(new UserBasicData(user));
		} 
		else {
			array.add(false);
		}
		String json = new Gson().toJson(array);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);       
    }
	
	private void getAllUserLists(HttpServletRequest request, HttpServletResponse response) throws IOException{
		SessionBean sessionBean = sessionIsOpen(request);
		List<Object> array = new ArrayList<Object>();
		
		if (sessionBean != null) {
			User user = sessionBean.getUser();
			array.add(true);
			UserDAO tmpLayer = new UserDAOLayer();
			user = tmpLayer.getAllReferences(user);
			System.out.println();
		} 
		else {
			array.add(false);
		}
		String json = new Gson().toJson(array);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
	
	private SessionBean sessionIsOpen(HttpServletRequest request){
		HttpSession session = request.getSession();
        synchronized(session) {
           SessionBean sessionBean = (SessionBean)session.getAttribute("userSession");
           return sessionBean;
        }
	}
}