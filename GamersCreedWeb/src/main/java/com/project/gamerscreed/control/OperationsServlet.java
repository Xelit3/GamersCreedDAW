package com.project.gamerscreed.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project.gamerscreed.model.dao.OperationDAO;
import com.project.gamerscreed.model.dao.implentation.OperationDAOLayer;
import com.project.gamerscreed.model.dto.Operation;
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
			
				case 40: //create operation
					makeOperation(request, response);
					break;
					
				case 41: //get all unconfirmed games
					getAllUnconfirmedOperations(request, response);
					break;
					
				case 42: //accept/rejectOperation
					acceptOperation(request, response);
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

	private void acceptOperation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		OperationBasicData tmpReceivedOperation = new Gson().fromJson(request.getParameter("JSONOperationData"), OperationBasicData.class);
		
		OperationDAO tmpLayer = new OperationDAOLayer();
		boolean tmpFlag = false;
		
		if(!tmpReceivedOperation.isRejected()){
			tmpFlag = tmpLayer.acceptOperation(tmpReceivedOperation.getId());
			if(tmpFlag)
				response.getWriter().println("Operation accepted");
		}
			
		
		else if(tmpReceivedOperation.isRejected()){
			tmpFlag = tmpLayer.rejectOperation(tmpReceivedOperation.getId());
			if(tmpFlag)
				response.getWriter().println("Operation rejected");
		}				
	}

	private void getAllUnconfirmedOperations(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, IOException{
		int tmpUserId = Integer.parseInt(request.getParameter("userId"));
		
		OperationDAO tmpLayer = new OperationDAOLayer();
		List<Operation> tmpOperations = tmpLayer.getAllUnconfirmedByUser(tmpUserId);
		List<OperationBasicData> tmpOperationsFormated = new ArrayList<OperationBasicData>();
		List<Object> tmpResponseData = new ArrayList<Object>();		
		
		if(!tmpOperations.isEmpty()){
			tmpResponseData.add(true);
			for (Operation o : tmpOperations) {
				tmpOperationsFormated.add(new OperationBasicData(o));
			}
			tmpResponseData.add(tmpOperationsFormated);
		}
		else
			tmpResponseData.add(false);
		
		String json = new Gson().toJson(tmpResponseData);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);		
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
		
		boolean tmpFlag = operationDAO.create(tmpOperation);
		
		response.getWriter().println("Operation creation is: " + tmpFlag);
	}

}
