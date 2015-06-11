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
import com.google.gson.reflect.TypeToken;
import com.project.gamerscreed.control.utilities.Encrypter;
import com.project.gamerscreed.model.dao.UserDAO;
import com.project.gamerscreed.model.dao.implentation.PostDAOLayer;
import com.project.gamerscreed.model.dao.implentation.UserDAOLayer;
import com.project.gamerscreed.model.dto.Operation;
import com.project.gamerscreed.model.dto.Post;
import com.project.gamerscreed.model.dto.Role;
import com.project.gamerscreed.model.dto.Role.RoleType;
import com.project.gamerscreed.model.dto.User;
import com.project.gamerscreed.model.dto.Videogame;
import com.project.gamerscreed.service.SessionBean;
import com.project.gamerscreed.view.PostBasicData;
import com.project.gamerscreed.view.UserBasicData;
import com.project.gamerscreed.view.VideogameBasicData;

/**
 * Servlet implementation class UserServlet.
 * @author: Adrià Nieto
 * @version: 1.0, 5-27-15
 */
public class UserServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new user servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
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
					getAllUsers(request, response);
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
					
				case 19: //Search users
					searchUsers(request, response);
					break;
					
				case 20: //Follow operation
					followUser(request, response);
					break;
					
				case 21: //Add videogame to user
					addVideogameToUser(request, response);
					
				case 22: //modify user
					modifyUser(request, response);
					break;
					
				case 23: //modify userArray
					modifyUserArray(request, response);
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
	
	private void modifyUserArray(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SessionBean sessionBean =sessionIsOpen(request);
		if(sessionBean!=null){
			PrintWriter out = response.getWriter();
			List<UserBasicData> tmpReceivedUserList = new Gson().fromJson(request.getParameter("JSONUserListData"), new TypeToken<List<UserBasicData>>() {}.getType());
			List<User> tmpUsers = new ArrayList<User>();
			UserDAOLayer userDAO =new UserDAOLayer();
			
			for(UserBasicData u : tmpReceivedUserList){
				tmpUsers.add(new User(u));
			}
			
			for(User u : tmpUsers){
				userDAO.modify(u);
			}
			userDAO.closeTransactionConnection();
		}		
	}
	
	private void modifyUser(HttpServletRequest request,	HttpServletResponse response) throws IOException {
		SessionBean sessionBean =sessionIsOpen(request);
		if(sessionBean!=null){
			PrintWriter out = response.getWriter();
			UserBasicData tmpReceivedUser = new Gson().fromJson(request.getParameter("JSONUserData"), UserBasicData.class);
			User tmpUser = new User(tmpReceivedUser);
		
			UserDAOLayer userDAO =new UserDAOLayer();
			boolean val = userDAO.modify(tmpUser);
			userDAO.closeTransactionConnection();
			out.print("User mod is: " + val);	
		}
	}
	
	private void addVideogameToUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		UserDAO tmpLayer = new UserDAOLayer();
		
		User tmpUser = new User();
		tmpUser.setId(Integer.parseInt(request.getParameter("userId")));
		Videogame tmpVideogame = new Videogame();
		tmpVideogame.setId(Integer.parseInt(request.getParameter("videogameId")));
		
		boolean tmpFlag = tmpLayer.addVideogameToUser(tmpUser, tmpVideogame);
		
		response.getWriter().println("Videogame added: " + tmpFlag);
	}

	private void followUser(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, IOException{
		int tmpUserLocalId = Integer.parseInt(request.getParameter("userLocalId"));
		int tmpUserFollowId = Integer.parseInt(request.getParameter("userFollowId"));
		
		UserDAO tmpUserLayer = new UserDAOLayer();
		
		User tmpUserLocal = tmpUserLayer.getUserById(tmpUserLocalId);
		User tmpUserFollow = tmpUserLayer.getUserById(tmpUserFollowId);
		
		boolean tmpFlag = tmpUserLayer.addFollower(tmpUserFollow, tmpUserLocal);
		
		response.getWriter().println("Following user: " + tmpFlag);
	}

	private void searchUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tmpUsername = request.getParameter("username");
		UserDAO tmpUserLayer = new UserDAOLayer();
		List<Object> tmpResponseData = new ArrayList<Object>();
		
		List<User> tmpUsers = tmpUserLayer.searchUser(tmpUsername);
		List<UserBasicData> tmpUsersResponse = new ArrayList<UserBasicData>();
		
		if(tmpUsers.size() > 0){
			tmpResponseData.add(true);
			for(User u : tmpUsers){
				tmpUsersResponse.add(new UserBasicData(u));				
			}
			tmpResponseData.add(tmpUsersResponse);
		}
		else{
			tmpResponseData.add(false);			
		}
		
		String json = new Gson().toJson(tmpResponseData);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * Gets the all users.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the all users
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SessionBean sessionBean =sessionIsOpen(request);
		UserDAO tmpUserLayer = new UserDAOLayer();
		List<Object> tmpResponseData = new ArrayList<Object>();
		
		List<UserBasicData> tmpUsersResponse = new ArrayList<UserBasicData>();
		if (sessionBean != null) {
			User user = sessionBean.getUser();
			
			List<User> tmpUsers = tmpUserLayer.getOtherUsers(user.getId());
			
			if(tmpUsers.size() > 0){
				tmpResponseData.add(true);
				for(User u : tmpUsers){
					tmpUsersResponse.add(new UserBasicData(u));				
				}
				tmpResponseData.add(tmpUsersResponse);
			}
			else{
				tmpResponseData.add(false);			
			}
			
		}		
				
//      Type tmpType = new TypeToken<List<User>>(){}.getType();
//      String json = new Gson().toJson(tmpUsers, tmpType);

		String json = new Gson().toJson(tmpResponseData);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * Check username.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Adds the user.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();		
		
		User user = new Gson().fromJson(request.getParameter("JSONUserData"), User.class);
		user.setPassword(Encrypter.getHash(user.getPassword()));
		UserDAO userDAO =new UserDAOLayer();
		user.setRole(new Role(RoleType.BASIC));
		boolean val=userDAO.create(user);
		System.out.println("User creation is: "+val);
		out.print(val);		
	}
	
	/**
	 * Login user.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
        	//start server session
        	startSession(request, user);
         	array.add(true);
        }
		String json = new Gson().toJson(array);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);		
	}
	
	/**
	 * Search post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void searchPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		SessionBean sessionBean =sessionIsOpen(request);
		List<Post> postList = new ArrayList<Post>();
		if (sessionBean != null) {
			User user = sessionBean.getUser();
			PostDAOLayer postLayer = new PostDAOLayer();
			postList = postLayer.getPostById(user);
			
		}
		
		List<PostBasicData> postBasicDataList = new ArrayList<PostBasicData>();
		for(int i=0; i<postList.size();i++){
			postBasicDataList.add(new PostBasicData(postList.get(i)));
		}
		String json = new Gson().toJson(postBasicDataList);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	/**
	 * Submit post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void submitPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();		
		
		SessionBean sessionBean =sessionIsOpen(request);
		User user=sessionBean.getUser();
		
		Post post = new Gson().fromJson(request.getParameter("JSONPostData"), Post.class);
		post.setUser(user);
		Date date = new Date();
		post.setPostDate(date);

		PostDAOLayer postLayer = new PostDAOLayer();
		boolean val = postLayer.create(post);
		
		out.println(val);
		
	}
	
	/**
	 * Gets the all user lists.
	 *
	 * @param aRequest the a request
	 * @param aResponse the a response
	 * @return the all user lists
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void getAllUserLists(HttpServletRequest aRequest, HttpServletResponse aResponse) throws IOException{
		SessionBean tmpSessionBean = sessionIsOpen(aRequest);
		List<Object> tmpResponseArray = new ArrayList<Object>();
		
		if (tmpSessionBean != null) {
			User tmpUser = tmpSessionBean.getUser();
			tmpResponseArray.add(true);
			UserDAO tmpLayer = new UserDAOLayer();
			tmpUser = tmpLayer.getUserById(tmpUser.getId());
			//Followers array creation
			List<UserBasicData> tmpFollows = new ArrayList<UserBasicData>();
			for(User u : tmpUser.getFollowers()){
				tmpFollows.add(new UserBasicData(u));
			}
			tmpResponseArray.add(tmpFollows);
			//Followings array creation
			tmpFollows = new ArrayList<UserBasicData>();
			for(User u : tmpUser.getFollowings()){
				tmpFollows.add(new UserBasicData(u));
			}
			tmpResponseArray.add(tmpFollows);
			//Videogames array creation
			List<VideogameBasicData> tmpVideogames = new ArrayList<VideogameBasicData>();
			for(Videogame v : tmpUser.getVideogames()){
				tmpVideogames.add(new VideogameBasicData(v));
			}
			tmpResponseArray.add(tmpVideogames);
			//Operations array creation
			List<String> tmpOperations = new ArrayList<String>();
			for(Operation o : tmpUser.getOperationsReceived()){
				tmpOperations.add(new String("Op. received - " + o.getId() + ", FROM: " + o.getUserSender().getUsername()) + o.getDateSended().toString());
			}
			for(Operation o : tmpUser.getOperationsSended()){
				tmpOperations.add(new String("Op. sended - " + o.getId() + ", TO: " + o.getUserReceived().getUsername()) + o.getDateSended().toString());
			}
			tmpResponseArray.add(tmpOperations);
		} 
		else {
			tmpResponseArray.add(false);
		}
		String json = new Gson().toJson(tmpResponseArray);
		aResponse.setContentType("application/json");
		aResponse.setCharacterEncoding("UTF-8");
		aResponse.getWriter().write(json);
	}
	
    /**
     * Start session.
     *
     * @param request the request
     * @param user the user
     */
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
    
	/**
	 * Logout user.
	 *
	 * @param request the request
	 */
	private void logoutUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        synchronized(session) {
           SessionBean sessionBean = (SessionBean)session.getAttribute("userSession");
           if (sessionBean != null) {
               session.removeAttribute("userSession");
           }
       }
    }
	
	/**
	 * Check session.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Session is open.
	 *
	 * @param request the request
	 * @return the session bean
	 */
	private SessionBean sessionIsOpen(HttpServletRequest request){
		HttpSession session = request.getSession();
        synchronized(session) {
           SessionBean sessionBean = (SessionBean)session.getAttribute("userSession");
           return sessionBean;
        }
	}
}