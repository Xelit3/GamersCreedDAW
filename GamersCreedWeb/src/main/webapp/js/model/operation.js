/**
 * 
 */
function operationObj ()
{
	//Attributes declaration
	this.id;
	this.userSender;
	this.userReceiver;
	this.dateSended;
	this.dateAccepted;
	this.rejected;
	this.price;
	this.videogameSended;
	this.videogameReceived;
	
	//Getters and setters
	this.setId = function(id){this.id = id;}
	this.setUserSender = function(userSender){this.userSender = userSender;}
	this.setUserReceiver = function(userReceiver){this.userReceiver = userReceiver;}
	this.setDateSended = function(dateSended){this.dateSended = dateSended;}
	this.setDateAccepted = function(dateAccepted){this.dateAccepted = dateAccepted;}
	this.setRejected = function(rejected){this.rejected = rejected;}
	this.setPrice = function(price){this.price = price;}
	this.setVideogameSended = function(videogameSended){this.videogameSended = videogameSended;}
	this.setVideogameReceived = function(videogameReceived){this.videogameReceived = videogameReceived;}
	
	this.getId = function(){return this.id;}
	this.getUserSender = function(){return this.userSender;}
	this.getUserReceiver = function(){return this.userReceiver;}
	this.getDateSended = function(){return this.dateSended;}
	this.getDateAccepted = function(){return this.dateAccepted;}
	this.getRejected = function(){return this.rejected;}
	this.getPrice = function(){return this.price;}
	this.getVideogameSended = function(){return this.videogameSended;}
	this.getVideogameReceived = function(){return this.videogameReceived;}
	
}