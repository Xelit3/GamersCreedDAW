package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.project.gamerscreed.model.dao.UserDAO;
import com.project.gamerscreed.model.dao.implentation.UserDAOLayer;
import com.project.gamerscreed.model.dto.User;
import com.project.gamerscreed.service.SessionBean;


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
        // TODO Auto-generated constructor stub
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
				
				default:
					out.println("Action number wrong");
					break;
			}
		}
		catch(IOException ioe){
			out.println("Error in the server - "+ioe);
			ioe.printStackTrace();			
		}
		catch(Exception e){
			out.println("Error in the server - "+e);
			e.printStackTrace();
		}
	}

	private void getAllUsers(HttpServletResponse response) throws IOException {
		UserDAO tmpUserLayer = new UserDAOLayer();
		List<User> tmpListaUsuarios = (List<User>) tmpUserLayer.getAll();
		User[] tmpUsers =  new User[tmpListaUsuarios.size()];
		for(int i = 0; i < tmpListaUsuarios.size(); i++){
			tmpUsers[i] = new User();
			tmpUsers[i].setName(tmpListaUsuarios.get(i).getName());
			tmpUsers[i].setUsername(tmpListaUsuarios.get(i).getUsername());			
		}
		
		Gson gson = new Gson();
//      Type tmpType = new TypeToken<List<User>>(){}.getType();
//      String json = new Gson().toJson(tmpUsers, tmpType);

		String json = new Gson().toJson(tmpUsers);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		ArrayList array=new ArrayList();

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
		UserDAO userDAO =new UserDAOLayer();	
		boolean val=userDAO.create(user);
		out.println(val);
		
	}
	
	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User tmpUser = new Gson().fromJson(request.getParameter("JSONUserData"), User.class);
		
		//User user=UserDAO.validateUser(tmpUser.getUsername(),tmpUser.getPassword());//TODO class DAOUsers
		
		UserDAO tmpUserLayer = new UserDAOLayer();
		User user = tmpUserLayer.login(tmpUser.getUsername(), tmpUser.getPassword());
		ArrayList array=new ArrayList();
				
		//BEGIN TEST -front end roles-
			/*User user=tmpUser;
			Role testRole=new Role();
			if(user.getUsername().equals("admin")){
				testRole.setId(2);
			}
			else {
				testRole.setId(1);
			}
			user.setRole(testRole);*/
		//END TEST
			
        if(user==null || user.getUsername() == null || user.getUsername().equals("")){
            array.add(false);
        }
        else{//TODO banned users response
        	startSession(request, tmpUser);//start server session
         	array.add(true);
         	array.add(user);

        }
		String json = new Gson().toJson(array);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}
	
	private void searchPost(HttpServletRequest request, HttpServletResponse response){
		
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
        HttpSession session = request.getSession();
        synchronized(session) {
           SessionBean sessionBean = (SessionBean)session.getAttribute("userSession");
    	   ArrayList array=new ArrayList();
           if (sessionBean != null) {
        	   User user=sessionBean.getUser();
        	   array.add(true);
        	   array.add(user);
              
           } else{
               array.add(false);
           }
           String json = new Gson().toJson(array);
	   		response.setContentType("application/json");
	   		response.setCharacterEncoding("UTF-8");
	   		response.getWriter().write(json);
        }
    }

}