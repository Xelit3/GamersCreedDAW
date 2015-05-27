/**
 * 
 */
function operationObj ()
{
	//Attributes declaration
	this.id;
	this.userSenderId;
	this.userReceiverId;
	this.dateSended;
	this.dateAccepted;	
	this.price = 0.0;
	this.videogameSenderId;
	this.videogameReceiverId;
	this.rejected;
	
	//Getters and setters
	this.setId = function(id){this.id = id;}
	this.setUserSenderId = function(userSender){this.userSenderId = userSender;}
	this.setUserReceiverId = function(userReceiver){this.userReceiverId = userReceiver;}
	this.setDateSended = function(dateSended){this.dateSended = dateSended;}
	this.setDateAccepted = function(dateAccepted){this.dateAccepted = dateAccepted;}
	this.setPrice = function(price){this.price = price;}
	this.setVideogameSenderId = function(videogameSended){this.videogameSenderId = videogameSended;}
	this.setVideogameReceiverId = function(videogameReceived){this.videogameReceiverId = videogameReceived;}
	this.setRejected = function(rejected){this.rejected = rejected;}
	
	this.getId = function(){return this.id;}
	this.getUserSender = function(){return this.userSender;}
	this.getUserReceiver = function(){return this.userReceiver;}
	this.getDateSended = function(){return this.dateSended;}
	this.getDateAccepted = function(){return this.dateAccepted;}
	this.getRejected = function(){return this.rejected;}
	this.getPrice = function(){return this.price;}
	this.getVideogameSended = function(){return this.selectedSendedGame;}
	this.getVideogameReceived = function(){return this.selectedReceiverGame;}
	
	this.formatOperation = function(userSender, userReceiver, videogameSender, videogameReceiver){
		this.userSenderId = userSender.getId();
		this.userReceiverId = userReceiver.getId();
		this.videogameSenderId = videogameSender.getId();
		this.videogameReceiverId = videogameReceiver.getId();
	}
}