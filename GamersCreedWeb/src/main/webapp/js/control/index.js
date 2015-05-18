(function(){

	var gamersCreedApp = angular.module("gamersCreedApp", []);
	
	gamersCreedApp.controller("gamersCreedController", function($scope){
		console.log("Controller intitialized");
		
		this.user=new userObj();
		this.postArray=new Array();
		
		$scope.appAction=0;
		$scope.userType=0;//if user is not registered, basic or admin
		
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
				this.user.construct(response[1].id, response[1].role, response[1].name, response[1].username, response[1].password, response[1].mail,  response[1].address)
				$scope.appAction=0;
				$scope.userType=response[1].role.id;
			}
		}
		this.login=function(){

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
			//alert(response[0]+"//"+response[1].username);
			if(response[0]){
				//open session local?
				/*if(typeof(Storage))
				{
					this.user = new userObj();
					this.user.construct(outPutdata[1].id, outPutdata[1].role, outPutdata[1].name, outPutdata[1].username, outPutdata[1].password, outPutdata[1].mail,  outPutdata[1].address)
					
					sessionStorage.setItem("userConnected",JSON.stringify(this.user));
				}
				else alert("This browser does not support session variables");*/
				
				$scope.appAction=0;
				$scope.userType=response[1].role.id;
			}
			else{
				//TODO how wrong validation to the user
				alert("The user or password is incorrect");
				
			}
			
			
		}
		
		this.userCreate=function(){
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
						alert(response);
						$scope.appAction=1;
					},
					error: function (xhr, ajaxOptions, thrownError) {
						alert("There has been an error while connecting to the server, try later");
						console.log(xhr.status+"\n"+thrownError);
					}
			    })
		}
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
		}
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
		}
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
			$scope.userType=0;
			$scope.appAction=0;
		}
		
		this.searchPosts=function(){
			//SIMULATION OF POST	
			for(var i=0;i<10;i++){
				var post=new postObj();
				post.construct(i, "UserId"+i, "Content "+i, new Date());
				this.postArray.push(post);

			}
			
			/*
			 $.ajax({
				url : 'PostServlet',
				type : "POST",
				data : {
					action : 10
				},
				async: false,
				success : function(responseText) {
					response = responseText;				
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert("There has been an error while connecting to the server, try later");
					console.log(xhr.status+"\n"+thrownError);
				}
			 	if(response){
			 		this.postArray=response;
			 	}
			 */
			
		}
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