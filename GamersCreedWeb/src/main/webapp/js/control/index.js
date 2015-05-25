(function(){

	var gamersCreedApp = angular.module("gamersCreedApp", []);
	
	gamersCreedApp.controller("gamersCreedController", function($scope){
		console.log("Controller intitialized");
		
		this.user = new userObj();
		this.postArray = new Array();
		//Variables for operations
		this.usersArray = new Array();
		this.userReceiver = new userObj();
		this.selectedUser = null;
		this.selectedReceiverGame = null;
		this.offeredPrice = 0.0;
		this.selectedSendedGame = null;
		
		$scope.appAction=0;
		//if user is not registered, basic or admin
		$scope.userType=-1;
		$scope.passwordValid=true;
		
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
				//this.user.construct(response[1].id, response[1].role, response[1].name, response[1].username, response[1].password, response[1].mail,  response[1].address)
				$scope.appAction=0;
				$scope.userType=response[1].roleId;
			}
		};
		this.login=function(){
			this.user.setRole(null);
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
		
		this.userCreate=function(){
			this.user.setRole(null);
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
			//$scope.userType=-1;
			//$scope.appAction=0;
			location.reload();
		};
		
		this.loadUsers= function (){
				//TODO Load all users
			/*this.user=angular.copy(this.user);
			$.ajax({
				url : 'UserServlet',
				type : "POST",
				data : {
					action : 15					
				},
				async: true,
				success : function(responseText) {
					response = responseText;
					alert(response);
					$scope.appAction=1;
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
		    })*/
		};
		
		this.getUserReceiverData= function (){
			//TODO Recibir mediante el ID el usuario entero, juegos etc (this.selectedUser)
		}
		
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
					 post.construct(response[i].id, response[i].user.username, response[i].content, response[i].postDate);
					 post.setDate(new Date());//TODO DATE
					 this.postArray.push(post);
				 }
			}
		};
		this.submitPost=function(){
			var post = new postObj();
			 post.construct(101, this.user.getUsername(), $("#contentPostBox").val());
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

})();