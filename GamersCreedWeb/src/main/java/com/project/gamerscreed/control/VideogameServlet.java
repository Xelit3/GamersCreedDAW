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
import com.project.gamerscreed.model.dao.UserDAO;
import com.project.gamerscreed.model.dao.VideogameDAO;
import com.project.gamerscreed.model.dao.implentation.BrandDAOLayer;
import com.project.gamerscreed.model.dao.implentation.CityDAOLayer;
import com.project.gamerscreed.model.dao.implentation.CountryDAOLayer;
import com.project.gamerscreed.model.dao.implentation.UserDAOLayer;
import com.project.gamerscreed.model.dao.implentation.VideogameDAOLayer;
import com.project.gamerscreed.model.dto.Brand;
import com.project.gamerscreed.model.dto.City;
import com.project.gamerscreed.model.dto.Country;
import com.project.gamerscreed.model.dto.User;
import com.project.gamerscreed.model.dto.Videogame;
import com.project.gamerscreed.view.BrandBasicData;
import com.project.gamerscreed.view.CityBasicData;
import com.project.gamerscreed.view.CountryBasicData;
import com.project.gamerscreed.view.VideogameBasicData;

/**
 * Servlet implementation class VideogameServlet.
 * @author: Xavi Rueda
 * @version: 1.0, 5-27-15
 */
public class VideogameServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new videogame servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public VideogameServlet() {
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
			
				case 30: //get all videogames
					getAllVideogames(request, response);
					break;
					
				case 31:
					getVideogamesByUser(request, response);
					break;
					
				case 32: //get all brands
					getAllBrands(request, response);
					break;
					
				case 33: //get all places
					getAllPlaces(request, response);
					break;
					
				case 34: //make a videogame suggestion
					makeVideogameSuggestion(request, response);
					break;
					
				case 35: //search videogames
					searchVideogame(request, response);
					break;
					
				case 36:
					changeVideogameConfirmation(request, response);
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

	private void changeVideogameConfirmation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int tmpVideogameId = Integer.parseInt(request.getParameter("videogameId"));
		boolean tmpFlag = false;
		VideogameDAO tmpLayer = new VideogameDAOLayer();
		tmpFlag = tmpLayer.changeConfirmation(tmpVideogameId);
		if(tmpFlag)
			response.getWriter().println("Confirmation/uncorfimation done");
		else
			response.getWriter().println("Confirmation/uncorfimation fail");
	}

	private void searchVideogame(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String tmpVideogameName = request.getParameter("name");
		VideogameDAO tmpVideogameLayer = new VideogameDAOLayer();
		List<Object> tmpResponseData = new ArrayList<Object>();
		
		List<Videogame> tmpVideogames = tmpVideogameLayer.searchVideogame(tmpVideogameName);
		List<VideogameBasicData> tmpVideogamesResponse = new ArrayList<VideogameBasicData>();
		
		if(tmpVideogames.size() > 0){
			tmpResponseData.add(true);
			for(Videogame v : tmpVideogames){
				tmpVideogamesResponse.add(new VideogameBasicData(v));				
			}
			tmpResponseData.add(tmpVideogamesResponse);
		}
		else{
			tmpResponseData.add(false);			
		}
		
		String json = new Gson().toJson(tmpResponseData);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);		
	}

	private void makeVideogameSuggestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();		
		
		VideogameBasicData tmpVideogameReceived = new Gson().fromJson(request.getParameter("JSONVideogameData"), VideogameBasicData.class);
		Videogame tmpVideogame = new Videogame(tmpVideogameReceived);
		tmpVideogame.setConfirmed(false);
		VideogameDAO tmpDAO =new VideogameDAOLayer();
		
		boolean val = tmpDAO.create(tmpVideogame);		
		out.print("User creation is: " + val);				
	}

	/**
	 * Gets the videogames by user.
	 *
	 * @param aRequest the a request
	 * @param aResponse the a response
	 * @return the videogames by user
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void getVideogamesByUser(HttpServletRequest aRequest, HttpServletResponse aResponse) throws IOException {
		List<Object> tmpResponseArray = new ArrayList<Object>();
		
		int userId = Integer.parseInt(aRequest.getParameter("userId"));
		
		UserDAO tmpLayer = new UserDAOLayer();
		User tmpUser = tmpLayer.getUserById(userId);
		if(tmpUser.getVideogames().size() > 0){
			tmpResponseArray.add(true);
			// Videogames array creation
			List<VideogameBasicData> tmpVideogames = new ArrayList<VideogameBasicData>();
			for (Videogame v : tmpUser.getVideogames()) {
				tmpVideogames.add(new VideogameBasicData(v));
			}
			tmpResponseArray.add(tmpVideogames);		
		}		
		
		String json = new Gson().toJson(tmpResponseArray);
		aResponse.setContentType("application/json");
		aResponse.setCharacterEncoding("UTF-8");
		aResponse.getWriter().write(json);	
	}

	/**
	 * Gets the all videogames.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the all videogames
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void getAllVideogames(HttpServletRequest request, HttpServletResponse response) throws IOException {
		VideogameDAO tmpVideogameLayer = new VideogameDAOLayer();
		List<Object> tmpResponseData = new ArrayList<Object>();
		List<Videogame> tmpVideogames = tmpVideogameLayer.getAll();
		List<VideogameBasicData> tmpVideogamesResponse = new ArrayList<VideogameBasicData>();
		
		if(tmpVideogames.size() > 0){
			tmpResponseData.add(true);
			for(Videogame v : tmpVideogames){
				tmpVideogamesResponse.add(new VideogameBasicData(v));				
			}
			tmpResponseData.add(tmpVideogamesResponse);
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
	 * Gets the all brands.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the all brands
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void getAllBrands(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		BrandDAOLayer brandLayer = new BrandDAOLayer();
		List<Object> tmpResponseData = new ArrayList<Object>();
		List<Brand> brandList = brandLayer.getAll();
		List<BrandBasicData> tmpBrandResponse = new ArrayList<BrandBasicData>();
		
		if(brandList.size() > 0){
			tmpResponseData.add(true);
			for(Brand b : brandList){
				tmpBrandResponse.add(new BrandBasicData(b));				
			}
			tmpResponseData.add(tmpBrandResponse);
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
	 * Gets the all places.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the all places
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void getAllPlaces(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Object> tmpResponseData = new ArrayList<Object>();
		
		CountryDAOLayer countryLayer = new CountryDAOLayer();
		List<Country> countryList = countryLayer.getAll();
		List<CountryBasicData> tmpCountryResponse = new ArrayList<CountryBasicData>();
		if(countryList.size() > 0){
			tmpResponseData.add(true);
			for(Country coun : countryList){
				tmpCountryResponse.add(new CountryBasicData(coun));				
			}
			tmpResponseData.add(tmpCountryResponse);
		}
		else{
			tmpResponseData.add(false);			
		}
		
		CityDAOLayer cityLayer = new CityDAOLayer();
		List<City> cityList = cityLayer.getAll();
		List<CityBasicData> tmpCityResponse = new ArrayList<CityBasicData>();
		if(countryList.size() > 0){
			tmpResponseData.add(true);
			for(City city : cityList){
				tmpCityResponse.add(new CityBasicData(city));				
			}
			tmpResponseData.add(tmpCityResponse);
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
