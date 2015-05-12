package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modeltmp.User;
import Beans.SessionBean;

import com.google.gson.Gson;


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
		/*response.setContentType( "text/html; charset=iso-8859-1" );
		PrintWriter out = response.getWriter();
		out.println("FAIL");*/
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
				case 10: //check if the username is in the DB
						checkUsername(request, response);
					break;
				case 11://add user in the DB
						addUser(request, response);  
					break;
				case 12://login
						loginUser(request, response);
					break;
				case 13://logout
						logoutUser(request);
					break;
				default:
					out.println("Action number wrong");
					break;
			}
		}catch(Exception e){
			out.println("Error in the server - "+e);
		}
	}

	private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		ArrayList array=new ArrayList();

		if(username.equals("Paco")){//TODO class DAOUsers
			array.add(true);
		}
		else{
			array.add(false);
		}
		String json = new Gson().toJson(array);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();		
		
		User user = new Gson().fromJson(request.getParameter("JSONUserData"), User.class);
				
		//DAO user
		
	}
	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User tmpUser = new Gson().fromJson(request.getParameter("JSONUserData"), User.class);
		//User user=UserDAO.validateUser(tmpUser.getUsername(),tmpUser.getPassword());//TODO class DAOUsers
		User user=new User("Paco", "paquete","Eldelbar");
		/*ArrayList array=new ArrayList();
        if(user!=null){
         	startSession(request, user);//start server session
         	array.add(true);
         	array.add(user);
        }
        else{
            array.add(false);
        }
		String json = new Gson().toJson(array);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);*/
		
	}
    private void startSession(HttpServletRequest request, User user){//TODO
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
	private void logoutUser(HttpServletRequest request){//TODO
        HttpSession session = request.getSession();
        synchronized(session) {
           SessionBean sessionBean = (SessionBean)session.getAttribute("userSession");
           if (sessionBean != null) {
               session.removeAttribute("userSession");
           }
        }
    }


}
