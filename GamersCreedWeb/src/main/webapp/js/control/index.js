(function(){

	var gamersCreedApp = angular.module("gamersCreedApp", []);
	
	gamersCreedApp.controller("gamersCreedController", function($scope){
		console.log("Controller intitialized");
		
		this.user = new userObj();
		this.usersArray = new Array();
		this.followersArray = new Array();
		this.followingsArray = new Array();
		this.videogamesArray = new Array();
		this.operationsArray = new Array();
		this.postArray = new Array();
		this.brandArray = new Array();
		this.countryArray = new Array();
		this.citieArray = new Array();
		this.forumSectionsArray = new Array();
		this.forumThreadsArray = new Array();
		//Videogames variables
		this.videogame = new videogameObj();
		//Variables for operations		
		this.videogamesRecieverArray = new Array();
		this.videogamesSenderArray = new Array();
		this.operation = new operationObj();		
		this.tempUserReceiver;
		this.tmpVideogameSender;
		this.tmpVideogameReceiver;
		//Variables for searching
		this.textToFind = "";
		this.foundedArray = new Array();
		
		
		$scope.appAction=0;
		//if user is not registered, basic or admin
		$scope.userType=-1;
				
		this.sessionCtrl=function(){
			
			$.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 14
				},
				async: false,
				success : function(responseText) {
					response = responseText;				
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    });
			//alert(response[0]);
			if(response[0]){
				this.user = new userObj();
				this.user.construct(response[1].id, response[1].roleId, response[1].name, response[1].username, response[1].mail);
				var tmpCountry = new countryObj();		
				tmpCountry.construct(response[1].addressCountryId, response[1].addressCountry);
				this.user.setAddress(response[1].addressStreet, response[1].addressCp, response[1].addressCity, tmpCountry);
				$scope.appAction=0;
				$scope.userType=response[1].roleId;
			}
		};
		
		this.login = function(){
			this.user.setRoleId(null);
			this.user=angular.copy(this.user);
			
			$.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 12,
					JSONUserData :JSON.stringify(this.user)
				},
				async: false,
				success : function(responseText) {
					response = responseText;				
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    });
			if(response[0]){

				location.reload();
			}
			else{
				//TODO how wrong validation to the user
				//alert("The user or password is incorrect");
				$scope.noLoginValidate=true;
				
			}
		};
		
		this.modifyUsersArray= function (){
			for(var i=0;i<this.usersArray;i++){				
				this.usersArray[i].format();
			}
			
			this.usersArray=angular.copy(this.usersArray);
			
			$.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 23,
					JSONUserListData : JSON.stringify(this.usersArray)
				},
				async: false,
				success : function(responseText) {
					response = responseText;										
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })			
		};
		
		this.modifyVideogamesArray = function(){
			var videogamesArray=angular.copy(this.videogamesArray);
			for(var i=0;i<this.videogamesArray.length;i++){
				this.videogamesArray[i].format();
			}
			
			$.ajax({
				url : 'VideogameServlet',
				type : "POST",
				data : {
					action : 37,
					JSONVideogameListData : JSON.stringify(this.videogamesArray)
				},
				async: false,
				success : function(responseText) {
					response = responseText;										
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })
	
		    this.videogamesArray=videogamesArray;
		};
				
		this.userCreate=function(){
			this.user.setRoleId(null);
			this.user=angular.copy(this.user);
				$.ajax({
					url : 'UserServlet',
					type : "POST",
					data : {
						action : 11,
						JSONUserData :JSON.stringify(this.user)
					},
					async: false,
					success : function(responseText) {
						response = responseText;
						
					},
					error: function (xhr, ajaxOptions, thrownError) {
						alert("There has been an error while connecting to the server, try later");
						console.log(xhr.status+"\n"+thrownError);
					}
			    })
			    if(response){
			    	$scope.appAction=1;
			    }
			    else{
			    	alert("Error in register");
			    }
		};
		
		this.modifyUser=function(){			
			this.user.format();
			this.user=angular.copy(this.user);
			$.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 22,
					JSONUserData :JSON.stringify(this.user)					
				},
				async: false,
				success : function(responseText) {
					response = responseText;
					
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })
		    if(!response){
		    	alert("Error in register");
		    }
		};
		
		this.checkAvail = function ()
		{
			if(this.user.getUsername()==""||this.user.getUsername()==null){
				return;
			}
			this.user=angular.copy(this.user);
			
			$.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 10,
					username : this.user.getUsername()
				},
				async: false,
				success : function(responseText) {
					response = responseText;				
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    });
			if(response[0])
			{
				$("#user").removeClass("ng-invalid");
				$("#user").addClass("ng-valid");
				$scope.userValid=true;

			}
			else
			{
				$("#user").removeClass("ng-valid");
				$("#user").addClass("ng-invalid");
				$scope.userValid=false;
			}
		};
		
		/**
		 *This function check the password
		 *NOTE: This function is used only by angular by using the actionHouseApp actionHouseCtrl controller		 
		 *@since: 1.1
		 *@param: none
		 *@return: none
		*/
		this.checkPassword = function ()
		{
			//if( $("#password").val()!= $scope.passControl)
			if( this.user.getPassword()!= $scope.passControl)
			{
				$("#password2").removeClass("ng-valid");
				$("#password2").addClass("ng-invalid");
				$scope.passwordValid = false;//show password do not match missage to user
			}
			else
			{

				$("#password2").removeClass("ng-invalid");
				$("#password2").addClass("ng-valid");
				$scope.passwordValid = true;
			}
		};
		
		this.logout = function ()
		{
			$.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 13
				},
				async: false,
				success : function(responseText) {
					response = responseText;				
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    });
			this.user = new userObj();
			location.reload();
		};
		
		this.loadUsers= function (){
			$.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 15					
				},
				async: false,
				success : function(responseText) {
					response = responseText;										
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })
		    if(response[0]){
		    	for(var i=0; i<response[1].length; i++){
		    		var user = new userObj();
		    		user.construct(response[1][i].id, response[1][i].roleId, response[1][i].name, response[1][i].username, response[1][i].mail);
					user.setAddress(response[1][i].addressStreet, response[1][i].addressCp, response[1][i].addressCity, response[1][i].addressCountry);
		    		this.usersArray.push(user);
		    	}
		    }
		};
		
		this.getUserReceiverData = function (){
			$.ajax({
				url : 'VideogameServlet',
				type : "POST",
				data : {
					action : 31,
					userId: this.tempUserReceiver.getId()
				},
				async: false,
				success : function(responseText) {
					response = responseText;										
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })
		    if(response[0]){
		    	this.videogamesRecieverArray = new Array();
		    	for(var i=0; i<response[1].length; i++){
		    		var videogame = new videogameObj();
		    		videogame.construct(response[1][i].id, response[1][i].name, response[1][i].developer, response[1][i].publisher, response[1][i].year);
		    		videogame.setConfirmed(response[1][i].confirmed);
		    		this.videogamesRecieverArray.push(videogame);
		    	}
		    }
			this.getSessionUserVideogames();			
		};
		
		this.getSessionUserVideogames = function(){			
			$.ajax({
				url : 'VideogameServlet',
				type : "POST",
				data : {
					action : 31,
					userId: this.user.getId()
				},
				async: false,
				success : function(responseText) {
					response = responseText;										
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })
		    if(response[0]){
		    	this.videogamesSenderArray = new Array();
		    	for(var i=0; i<response[1].length; i++){
		    		var videogame = new videogameObj();
		    		videogame.construct(response[1][i].id, response[1][i].name, response[1][i].developer, response[1][i].publisher, response[1][i].year);
		    		videogame.setConfirmed(response[1][i].confirmed);
		    		this.videogamesSenderArray.push(videogame);
		    	}
		    }
		};		
		
		this.videogameSuggest = function(){
			this.videogame.format();
			$.ajax({
				url : 'VideogameServlet',
				type : "POST",
				data : {
					action : 34,
					JSONVideogameData :JSON.stringify(this.videogame)
				},
				async: false,
				success : function(responseText) {
					response = responseText;										
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })
		};
		
		this.makeOperation = function(){			
			this.operation.formatOperation(this.user, this.tempUserReceiver, this.tmpVideogameSender, this.tmpVideogameReceiver);
			$.ajax({
				url : 'OperationsServlet',
				type : "POST",
				data : {
					action : 40,
					JSONOperationData : JSON.stringify(this.operation)
				},
				async: true,
				success : function(responseText) {
					alert(responseText);					
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })		    
		};
		
		this.loadAllVideogames = function (){
			$.ajax({
				url : 'VideogameServlet',
				type : "POST",
				data : {
					action : 30					
				},
				async: false,
				success : function(responseText) {
					response = responseText;										
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })
		    if(response[0]){
		    	this.videogamesArray = new Array();
		    	for(var i=0; i<response[1].length; i++){
		    		var tmpDeveloper = new brandObj();		
		    		tmpDeveloper.construct(response[1][i].developerId, response[1][i].developer);
					var tmpPublisher = new brandObj();		
					tmpPublisher.construct(response[1][i].publisherId, response[1][i].publisher);
		    		var videogame = new videogameObj();
		    		videogame.construct(response[1][i].id, response[1][i].name, tmpDeveloper, tmpPublisher, response[1][i].year);
		    		videogame.setConfirmed(response[1][i].confirmed);
		    		this.videogamesArray.push(videogame);
		    	}
		    }
		};
		
		this.loadAllBrands = function (){
			$.ajax({
				url : 'VideogameServlet',
				type : "POST",
				data : {
					action : 32					
				},
				async: false,
				success : function(responseText) {
					response = responseText;										
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })
		    if(response[0]){
		    	this.brandArray = new Array();
		    	for(var i=0; i<response[1].length; i++){
		    		var brand = new brandObj();
		    		brand.construct(response[1][i].id, response[1][i].name);
		    		this.brandArray.push(brand);
		    	}
		    }
		};
		
		this.loadAllPlaces = function (){
			$.ajax({
				url : 'VideogameServlet',
				type : "POST",
				data : {
					action : 33					
				},
				async: false,
				success : function(responseText) {
					response = responseText;										
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })
		    if(response[0]){
		    	this.countryArray = new Array();
		    	for(var i=0; i<response[1].length; i++){
		    		var country = new countryObj();
		    		country.construct(response[1][i].id, response[1][i].name);
		    		this.countryArray.push(country);
		    	}
		    }
			if(response[2]){
		    	this.cityArray = new Array();
		    	for(var i=0; i<response[3].length; i++){
		    		var city = new cityObj();
		    		city.construct(response[3][i].id, response[3][i].name);
		    		this.cityArray.push(city);
		    	}
		    }
		};
		
		this.getForumSections = function(){
			$.ajax({
				url : 'ForumServlet',
				type : "POST",
				data : {
					action : 20					
				},
				async: false,
				success : function(responseText) {
					response = responseText;										
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })
		    if(response[0]){
		    	for(var i=0; i<response[1].length; i++){
		    		this.forumSectionsArray.push(response[1][i].name);
		    	}		    	
		    }
		};
		
		this.getUserListData = function (){			
			$.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 18					
				},
				async: false,
				success : function(responseText) {
					response = responseText;
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })
		    if(response[0]){
				for(var i=0; i<response[1].length; i++){
					var user = new userObj();
					user.construct(response[1][i].id, response[1][i].roleId, response[1][i].name, response[1][i].username, response[1][i].mail);
					user.setAddress(response[1][i].addressStreet, response[1][i].addressCp, response[1][i].addressCity, response[1][i].addressCountry);
					this.followersArray.push(user);
				}
				for(var i=0; i<response[2].length; i++){
					var user = new userObj();
					user.construct(response[2][i].id, response[2][i].roleId, response[2][i].name, response[2][i].username, response[2][i].mail);
					user.setAddress(response[2][i].addressStreet, response[2][i].addressCp, response[2][i].addressCity, response[2][i].addressCountry);
					this.followingsArray.push(user);
				}
				for(var i=0; i<response[3].length; i++){
					var videogame = new videogameObj();
					videogame.construct(response[3][i].id, response[3][i].name, response[3][i].developer, response[3][i].publisher, response[3][i].year);
					this.videogamesArray.push(videogame);
				}
				for(var i=0; i<response[4].length; i++){
					this.operationsArray.push(response[4][i]);
				}
			}
		};
		
		this.searchPosts=function(){			
			 $.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 16
				},
				async: false,
				success : function(responseText) {
					response = responseText;				
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				} 	
			 });
			 if (response) {
				 for (var i = 0; i<response.length; i++) {
					 var post = new postObj();
					 post.construct(response[i].id, response[i].username, response[i].content, response[i].postDate);
					 this.postArray.push(post);
				 }
			}
		};
		
		this.submitPost=function(){
			var post = new postObj();
			post.construct(null, this.user.getUsername(), $("#contentPostBox").val());
			this.postArray.push(post);
			 
			$.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 17,
					JSONPostData : JSON.stringify(post)
				},
				async : true,
				success : function(responseText) {
					response = responseText;
					$("#contentPostBox").val("");
					//location.reload();
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status + "\n" + thrownError);
				} 	
			 });
		};
		
		this.searchUser = function(){
			this.foundedArray = new Array();
			
			$.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 19,
					username: this.textToFind
				},
				async: false,
				success : function(responseText) {
					response = responseText;
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				} 	
			});
			if (response[0]) {
				for (var i = 0; i<response[1].length; i++) {
					var user = new userObj();
					user.construct(response[1][i].id, response[1][i].roleId, response[1][i].name, response[1][i].username, response[1][i].mail);
					user.setAddress(response[1][i].addressStreet, response[1][i].addressCp, response[1][i].addressCity, response[1][i].addressCountry);
					this.foundedArray.push(user);
				}
			}
			else
				alert("No user found");
		};
		
		this.searchVideogame = function(){
			this.foundedArray = new Array();
			
			$.ajax({
				url : 'VideogameServlet',
				type : "POST",
				data : {
					action : 35,
					name: this.textToFind
				},
				async: false,
				success : function(responseText) {
					response = responseText;				
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				} 	
			});
			if (response[0]) {
				for (var i = 0; i<response[1].length; i++) {
					var videogame = new videogameObj();
		    		videogame.construct(response[1][i].id, response[1][i].name, response[1][i].developer, response[1][i].publisher, response[1][i].year);		    		
		    		this.foundedArray.push(videogame);
				}
			}
			else
				alert("No videogame found");
		};
		
		this.followUser = function(anId, anIndex){
			$.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 20,
					userLocalId: this.user.getId(),
					userFollowId: anId
				},
				async: true,
				success : function(responseText) {
					alert(responseText);				
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				} 	
			});
			
			this.followingsArray.push(this.foundedArray[anIndex]);
			this.textToFind = "";
			this.foundedArray = new Array();
		};
		
		this.addVideogameToUser = function(anId, anIndex){
			$.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 21,
					userId: this.user.getId(),
					videogameId: anId
				},
				async: true,
				success : function(responseText) {
					alert(responseText);
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				} 	
			});
			
			this.videogamesArray.push(this.foundedArray[anIndex]);
			this.textToFind = "";
			this.foundedArray = new Array();
		};
		
		this.confirmVideogame = function(anIndex){
			$.ajax({
				url : 'VideogameServlet',
				type : "POST",
				data : {
					action : 36,
					videogameId: this.videogamesArray[anIndex].getId()
				},
				async: true,
				success : function(responseText) {
					alert(responseText);
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				} 	
			});
		};
		
		this.loadUnconfirmedUserOperations = function(){
			$.ajax({
				url : 'OperationsServlet',
				type : "POST",
				data : {
					action : 41,
					userId: this.user.getId(),
				},
				async: false,
				success : function(responseText) {
					response = responseText;										
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })
		    if(response[0]){
		    	this.foundedArray = new Array();
		    	for(var i=0; i<response[1].length; i++){
		    		var operation = new operationObj();
		    		operation.construct(response[1][i].id, response[1][i].userSenderId, response[1][i].userReceiverId, response[1][i].userSenderName, response[1][i].dateSended, response[1][i].price, response[1][i].videogameSenderId, response[1][i].videogameReceiverId, response[1][i].videogameSenderName, response[1][i].videogameReceiverName);
		    		this.foundedArray.push(operation);
		    	}
		    }
		};
		
		this.acceptOperation = function(aConfirmation, anIndex){
			var operation = this.foundedArray[anIndex];
			operation.setRejected(!aConfirmation);
			$.ajax({
				url : 'OperationsServlet',
				type : "POST",
				data : {
					action : 42,
					JSONOperationData: JSON.stringify(operation)					
				},
				async: true,
				success : function(responseText) {
					alert(responseText);
					this.foundedArray = new Array();
					$scope.appAction=2;
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    });
			this.foundedArray = new Array();
		};
		
	});

	gamersCreedApp.directive("loginForm", function (){
		return {
		  restrict: 'E',
		  templateUrl:"templates/login-form.html",
		  controller:function(){
			
		  },
		  controllerAs: 'loginForm'
		};
	});

	gamersCreedApp.directive("userEntryForm", function (){
		return {
		  restrict: 'E',
		  templateUrl:"templates/user-entry-form.html",
		  controller:function(){
			  
		  },
		  controllerAs: 'userEntryForm'
		};
	});
	gamersCreedApp.directive("userModifyForm", function (){
		return {
		  restrict: 'E',
		  templateUrl:"templates/user-modify-form.html",
		  controller:function(){
			
		  },
		  controllerAs: 'userModifyForm'
		};
	});

	gamersCreedApp.directive("forumView", function (){
		return {
		  restrict: 'E',
		  templateUrl:"templates/forum-view.html",
		  controller:function(){
			
		  },
		  controllerAs: 'forumView'
		};
	});
	gamersCreedApp.directive("operationView", function (){
		return {
		  restrict: 'E',
		  templateUrl:"templates/operation-view.html",
		  controller:function(){
			
		  },
		  controllerAs: 'operationView'
		};
	});
	gamersCreedApp.directive("manageUsersView", function (){
		return {
		  restrict: 'E',
		  templateUrl:"templates/manage-users-view.html",
		  controller:function(){
			
		  },
		  controllerAs: 'manageUsersView'
		};
	});
	gamersCreedApp.directive("manageVideogamesView", function (){
		return {
		  restrict: 'E',
		  templateUrl:"templates/manage-videogames-view.html",
		  controller:function(){
			
		  },
		  controllerAs: 'manageVideogamesView'
		};
	});
	gamersCreedApp.directive("wallView", function (){
		return {
		  restrict: 'E',
		  templateUrl:"templates/wall-view.html",
		  controller:function(){
			
		  },
		  controllerAs: 'wallView'
		};
	});
	gamersCreedApp.directive("videogameSuggestionForm", function (){
		return {
		  restrict: 'E',
		  templateUrl:"templates/videogame-suggestion-form.html",
		  controller:function(){
			
		  },
		  controllerAs: 'videogameSuggestionForm'
		};
	});
	gamersCreedApp.directive("userSearchForm", function (){
		return {
		  restrict: 'E',
		  templateUrl:"templates/user-search-form.html",
		  controller:function(){
			
		  },
		  controllerAs: 'userSearchForm'
		};
	});
	gamersCreedApp.directive("videogameSearchForm", function (){
		return {
		  restrict: 'E',
		  templateUrl:"templates/videogame-search-form.html",
		  controller:function(){
			
		  },
		  controllerAs: 'videogameSearchForm'
		};
	});
	gamersCreedApp.directive("unconfirmedUserOperationView", function (){
		return {
		  restrict: 'E',
		  templateUrl:"templates/unconfirmed-user-operation-view.html",
		  controller:function(){
			
		  },
		  controllerAs: 'unconfirmedUserOperationView'
		};
	});	

})();