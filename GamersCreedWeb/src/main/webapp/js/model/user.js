/* @name: userObj
 * @author: Adria Nieto
 * @date: 05/07/15
 * @description: the user obj
*/
function userObj ()
{
	//Attributes declaration
	this.id;
	this.role;
	this.name;
	this.username;
	this.password;
	this.mail;
	this.addressCp;
	this.addressStreet;
	this.addressCity;
	this.addressCountry;
	this.addressCountryId;
	this.operationsSended;
	this.operationsReceived;
	this.videogames;
	this.posts;

	this.construct = function (id, role, name, username, mail){
		this.id=id;
		this.role=role;
		this.name=name;
		this.username=username;		
		this.mail=mail;
	}

	this.setId = function (id){this.id=id;}
	this.setRole = function (role){this.role=role;}
	this.setName = function (name){this.name=name;}
	this.setUsername = function (username){this.username=username;}
	this.setPassword = function (password){this.password=password;}
	this.setMail = function (mail){this.mail=mail;}
	this.setOperationsSended = function (operationsSended){this.operationsSended=operationsSended;}
	this.setOperationsReceived = function (operationsReceived){this.operationsReceived=operationsReceived;}
	this.setVideogames = function (videogames){this.videogames=videogames;}
	this.setPosts = function (posts){this.posts=posts;}
	this.setAddress = function(street, cp, city, country){
		this.addressCp = cp;
		this.addressCity = city;
		this.addressCountry = country;
		this.addressStreet = street;
	}
	this.getId = function () {return this.id;}
	this.getRole = function () {return this.role;}
	this.getName = function () {return this.name;}
	this.getUsername = function () {return this.username;}
	this.getPassword = function () {return this.password;}
	this.getMail = function () {return this.mail;}
	
	this.getOperationsSended = function (){return this.operationsSended;}
	this.getOperationsReceived = function (){return this.operationsReceived;}
	this.getVideogames = function (){return this.videogames;}
	this.getPosts = function (){return this.posts;}
	
	this.format = function(){
		this.addressCountryId = this.addressCountry.getId();
		this.addressCountry = this.addressCountry.getName();
	};

	this.toString = function(){
		return "Id: " + this.id + "- Username:" + this.username;
	};
}