/**
 * 
 */
function videogameObj ()
{
	//Attributes
	this.id;
	this.confirmed;
	this.name;
	this.year;		
	this.developer;
	this.publisher;
	this.developerId;
	this.publisherId;
	
	this.construct = function(id, name, developer, publisher, year){
		this.id = id;
		this.name = name;
		this.developer = developer;
		this.publisher = publisher;
		this.year = year;
	};
	
	//Getters and setters
	this.setId = function(id){this.id = id;}
	this.setConfirmed = function(confirmed){this.confirmed = confirmed;}
	this.setName = function (name){this.name = name;}
	this.setYear = function (year){this.year = year;}
	this.setDeveloper = function(developer){
		this.developer = developer.getName();
		this.developerId = developer.getId();
	}
	this.setPublisher = function(publisher){
		this.publisher = publisher.getName();
		this.publisherId = publisher.getId();
	}
	
	this.getId = function(){return this.id;}
	this.getConfirmed = function(){return this.confirmed;}
	this.getName = function (){return this.name;}
	this.getYear = function (){return this.year;}
	this.getDeveloper = function(){return this.developer;}
	this.getPublisher = function(){return this.publisher;}	
	
	this.format = function(){
		this.setPublisher(this.publisher);
		this.setDeveloper(this.developer);
	}
	
	this.toString = function(){
		return "ID:" + this.id + " Name: " + this.name + " Developer: " + this.developer +  " Publisher: " + this.publisher;
	}

}