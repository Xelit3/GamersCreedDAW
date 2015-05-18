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
	this.operationsSended;
	this.operationsReceived;
	this.users;
	this.developer;
	this.publisher;
	
	//Getters and setters
	this.setId = function(id){this.id = id;}
	this.setConfirmed = function(confirmed){this.confirmed = confirmed;}
	this.setName = function (name){this.name = name;}
	this.setYear = function (year){this.year = year;}
	this.setOperationsSended = function(operations){this.operationsSended = operations;}
	this.setOperationsReceived = function(operations){this.operationsReceived = operations;}
	this.setUsers = function(users){this.users = users;}
	this.setDeveloper = function(developer){this.developer = developer;}
	this.setPublisher = function(publisher){this.publisher = publisher;}
	
	this.getId = function(){return this.id;}
	this.getConfirmed = function(){return this.confirmed;}
	this.getName = function (){return this.name;}
	this.getYear = function (){return this.year;}
	this.getOperationsSended = function(){return this.operationsSended;}
	this.getOperationsReceived = function(){return this.operationsReceived ;}
	this.getUsers = function(){return this.users;}
	this.getDeveloper = function(){return this.developer;}
	this.getPublisher = function(){return this.publisher;}	

}