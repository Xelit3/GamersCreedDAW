package com.project.gamerscreed.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project.gamerscreed.model.dao.OperationDAO;
import com.project.gamerscreed.model.dao.implentation.OperationDAOLayer;
import com.project.gamerscreed.model.dto.Operation;
import com.project.gamerscreed.model.dto.User;
import com.project.gamerscreed.model.dto.Videogame;
import com.project.gamerscreed.view.OperationBasicData;

/**
 * Servlet implementation class OperationsServlet.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class OperationsServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new operations servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public OperationsServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			
				case 40: //get all videogames
					makeOperation(request, response);
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

	/**
	 * Make operation.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void makeOperation(HttpServletRequest request, HttpServletResponse response) throws IOException{
		OperationBasicData tmpOperationReceived = new Gson().fromJson(request.getParameter("JSONOperationData"), OperationBasicData.class);
		Operation tmpOperation = new Operation(tmpOperationReceived);
		System.out.println();
		OperationDAO operationDAO =new OperationDAOLayer();
		
		boolean val = operationDAO.create(tmpOperation);
		
		System.out.println("Operation creation is: " + val);
		
		}

}
