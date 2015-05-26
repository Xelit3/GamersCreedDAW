package com.project.gamerscreed.view;

import com.project.gamerscreed.model.dto.Videogame;

public class VideogameBasicData {

	public int id;
	public String name;
	public String developer;
	public String publisher;
	public int year;
	
	public VideogameBasicData(Videogame aVideogame){
		this.id = aVideogame.getId();
		this.name = aVideogame.getName();
		this.developer = aVideogame.getDeveloper().getName();
		this.publisher = aVideogame.getPublisher().getName();
		this.year = aVideogame.getYear();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
}
