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
	this.address
	this.operationsSended;
	this.operationsReceived;
	this.videogames;
	this.posts;

	this.construct = function (id, role, name, username, password, mail, address){
		this.id=role;
		this.role=id;
		this.name=name;
		this.username=username;
		this.password=password;
		this.mail=mail;
		this.address=address;
	}

	this.setId = function (id){this.id=id;}
	this.setRole = function (role){this.role=role;}
	this.setName = function (name){this.name=name;}
	this.setUsername = function (username){this.username=username;}
	this.setPassword = function (password){this.password=password;}
	this.setMail = function (mail){this.mail=mail;}
	this.setAddress = function (address){this.address=address;}
	this.setOperationsSended = function (operationsSended){this.operationsSended=operationsSended;}
	this.setOperationsReceived = function (operationsReceived){this.operationsReceived=operationsReceived;}
	this.setVideogames = function (videogames){this.videogames=videogames;}
	this.setPosts = function (posts){this.posts=posts;}

	this.getId = function () {return this.id;}
	this.getRole = function () {return this.role;}
	this.getName = function () {return this.name;}
	this.getUsername = function () {return this.username;}
	this.getPassword = function () {return this.password;}
	this.getMail = function () {return this.mail;}
	this.getAddress = function () {return this.address;}
	this.getOperationsSended = function (){return this.operationsSended;}
	this.getOperationsReceived = function (){return this.operationsReceived;}
	this.getVideogames = function (){return this.videogames;}
	this.getPosts = function (){return this.posts;}

	this.toString = function(){
		return "ID: " + this.id + "- Username:" + this.username;
	};
}