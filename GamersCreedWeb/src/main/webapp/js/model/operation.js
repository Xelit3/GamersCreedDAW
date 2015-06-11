/**
 * 
 */
function operationObj ()
{
	//Attributes declaration
	this.id;
	this.userSenderId;
	this.userReceiverId;
	this.userSenderName;
	this.dateSended;
	this.dateAccepted;	
	this.price = 0.0;
	this.videogameSenderId;
	this.videogameReceiverId;
	this.videogameSenderName;
	this.videogameReceiverName;
	this.rejected;
	
	this.construct = function(id, userSenderId, userReceiverId, userSenderName, dateSended, price, videogameSenderId, videogameReceiverId, videogameSenderName, videogameReceiverName){
		this.id = id;
		this.userSenderId = userSenderId;
		this.userReceiverId = userReceiverId;
		this.userSenderName = userSenderName;
		this.dateSended = dateSended;			
		this.price = price;
		this.videogameSenderId = videogameSenderId;
		this.videogameReceiverId = videogameReceiverId;
		this.videogameSenderName = videogameSenderName;
		this.videogameReceiverName = videogameReceiverName;		
	};
	
	//Getters and setters
	this.setId = function(id){this.id = id;};
	this.setUserSenderId = function(userSender){this.userSenderId = userSender;};
	this.setUserReceiverId = function(userReceiver){this.userReceiverId = userReceiver;};
	this.setDateSended = function(dateSended){this.dateSended = dateSended;};
	this.setDateAccepted = function(dateAccepted){this.dateAccepted = dateAccepted;};
	this.setPrice = function(price){this.price = price;};
	this.setVideogameSenderId = function(videogameSended){this.videogameSenderId = videogameSended;};
	this.setVideogameReceiverId = function(videogameReceived){this.videogameReceiverId = videogameReceived;};
	this.setRejected = function(rejected){this.rejected = rejected;};
	
	this.getId = function(){return this.id;};
	this.getUserSender = function(){return this.userSender;};
	this.getUserReceiver = function(){return this.userReceiver;};
	this.getDateSended = function(){return this.dateSended;};
	this.getDateAccepted = function(){return this.dateAccepted;};
	this.getRejected = function(){return this.rejected;};
	this.getPrice = function(){return this.price;};
	this.getVideogameSended = function(){return this.selectedSendedGame;};
	this.getVideogameReceived = function(){return this.selectedReceiverGame;};
	
	this.formatOperation = function(userSender, userReceiver, videogameSender, videogameReceiver){
		if(userSender != null)
			this.userSenderId = userSender.getId();
		if(userReceiver != null)
			this.userReceiverId = userReceiver.getId();
		if(videogameSender != null)
			this.videogameSenderId = videogameSender.getId();
		if(videogameReceiver != null)
			this.videogameReceiverId = videogameReceiver.getId();
	};
	
	this.toString = function(){
		return "ID: " + this.id + " userSender: " + this.userSenderId + "-" + this.userSenderName;
	};
}