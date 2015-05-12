/* 
 * @name: postObj
 * @author: Adria Nieto
 * @date: 05/04/15
 * @description: the post obj
*/
function postObj ()
{
	//Attributes declaration
	this.id;
	this.userName;
	this.content;
	this.date;

	this.construct = function (id, userName, content, date){
		this.id=id;
		this.userName=userName;
		this.content=content;
		this.date=date;

	}

	this.setId = function (id){this.id=id;}
	this.setUserName= function (userName){this.userName=userName;}
	this.setContent= function (content){this.content=content;}
	this.setDate= function (date){this.date=date;}

	this.getId = function () {return this.id;}
	this.getUserName = function () {return this.userName;}
	this.getContent = function () {return this.content;}
	this.getDate = function () {return this.date;}


	this.toString = function(){
		return "ID: " + this.id + " - UserName: " + this.userName + " - Content: " + this.content + " - Date: " + this.date;
	};
	
}
