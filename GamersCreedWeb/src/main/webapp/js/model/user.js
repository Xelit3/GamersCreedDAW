/* @name: userObj
 * @author: Adria Nieto
 * @date: 05/07/15
 * @description: the user obj
*/
function userObj ()
{
	//Attributes declaration
	this.id;
	this.name;
	this.username;
	this.password;
	this.mail;
	this.address;

	this.construct = function (id, name, username, password, mail, address){
		this.id=id;
		this.name=name;
		this.username=username;
		this.password=password;
		this.mail=mail;
		this.address=address;


	}

	this.setId = function (id){this.id=id;}
	this.setName = function (name){this.name=name;}
	this.setUsername = function (username){this.username=username;}
	this.setPassword = function (password){this.password=password;}
	this.setMail = function (mail){this.mail=mail;}
	this.setAddress = function (address){this.address=address;}


	this.getId = function () {return this.id;}
	this.getName = function () {return this.name;}
	this.getUsername = function () {return this.username;}
	this.getPassword = function () {return this.password;}
	this.getMail = function () {return this.mail;}
	this.getAddress = function () {return this.address;}	

	this.toString = function(){
		return "ID: " + this.id;
	};
}