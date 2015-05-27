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
import com.project.gamerscreed.model.dao.ForumThreadDAO;
import com.project.gamerscreed.model.dao.implentation.ForumThreadDAOLayer;
import com.project.gamerscreed.model.dto.ForumSection;
import com.project.gamerscreed.view.ForumSectionBasicData;

/**
 * Servlet implementation class ForumServlet.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class ForumServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new forum servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public ForumServlet() {
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
							
				case 20:
					getAllForumSections(request, response);
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
	 * Gets the all forum sections.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the all forum sections
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void getAllForumSections(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ForumThreadDAO tmpLayer = new ForumThreadDAOLayer();
		List<Object> tmpResponseData = new ArrayList<Object>();
		List<ForumSection> tmpSections = tmpLayer.getAllSections();
		List<ForumSectionBasicData> tmpSectionsResponse = new ArrayList<ForumSectionBasicData>();
		
		if(!tmpSections.isEmpty()){
			tmpResponseData.add(true);
			
			for(ForumSection s : tmpSections){
				tmpSectionsResponse.add(new ForumSectionBasicData(s));
			}
			tmpResponseData.add(tmpSectionsResponse);
		}
		else{
			tmpResponseData.add(false);
		}
		
		String json = new Gson().toJson(tmpResponseData);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}
