/**
 * 
 */
function brandObj() {
	
	this.id;
	this.name;
	this.country;
	
	this.construct = function (id, name){
		this.id=id;
		this.name=name;		
	}
	
	this.setId = function(id) {this.id = id;}
	this.setName = function(name) {this.name = name;}
	this.setCountry = function(country) {this.country = country;}
	
	this.getId = function() {return this.id;}
	this.getName = function() {return this.name;}
	this.getCountry = function() {return this.country;}
	
}