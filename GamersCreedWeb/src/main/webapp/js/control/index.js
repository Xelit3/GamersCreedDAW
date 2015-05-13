(function(){

	var gamersCreedApp = angular.module("gamersCreedApp", []);
	
	gamersCreedApp.controller("gamersCreedController", function($scope){
		console.log("Controller intitialized");

		//SIMULATION OF POST
		this.postArray=new Array();
		for(var i=0;i<10;i++){
			var post=new postObj();
			post.construct(i, "UserId"+i, "Content "+i, new Date());
			this.postArray.push(post);

		}
		
		this.user=new userObj();

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
			/*if(response[0]){
				//open session local
				//redirect to $scope.appAction=1;
			}
			else{
				//show fail to user
				
			}*/
			alert(response[0]+"//"+response[1].username);
			
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