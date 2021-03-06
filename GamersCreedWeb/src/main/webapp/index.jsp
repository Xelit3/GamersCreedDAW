<!DOCTYPE html>
<html ng-app="gamersCreedApp">

<head>
	<title>Gamer's creed</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" HREF="img/icon.jpg"/>	
	<link href="css/style.css" rel="stylesheet" type="text/css"/>
	
	<!-- Angular-->
	<script src="js/frameWorks/angular/angular.min.js" type="text/javascript" xml:space="preserve"></script>
	<script src="js/frameWorks/angular/i18n/angular-locale_es-es.js" type="text/javascript" xml:space="preserve"></script>
	<script src="js/frameWorks/angular/ng-currency.js" type="text/javascript" xml:space="preserve"></script>
	<script src="js/frameWorks/angular/angular-file-upload.js" type="text/javascript" xml:space="preserve"></script>
	
	<!-- jQuery-->
	<script src="js/frameWorks/jQuery/jQuery.js" type="text/javascript" xml:space="preserve"></script>

	<!-- Own JS scripts -->			
	<script type="text/javascript" src="js/model/post.js"></script>
	<script type="text/javascript" src="js/model/user.js"></script>
	<script type="text/javascript" src="js/model/brand.js"></script>
	<script type="text/javascript" src="js/model/city.js"></script>
	<script type="text/javascript" src="js/model/country.js"></script>
	<script type="text/javascript" src="js/model/videogame.js"></script>
	<script type="text/javascript" src="js/model/operation.js"></script>
	<script type="text/javascript" src="js/control/index.js"></script>
</head>

<body ng-controller="gamersCreedController as gCreedCtrl" ng-init="gCreedCtrl.sessionCtrl()">
	<header>
		<h1>Gamer's creed</h1>

		<ul>
			<li><a href="" ng-click="appAction=0">Main</a></li>			
			<li ng-show="userType>-1"><a href="" ng-click="appAction=2">Profile</a></li>
			<li ng-show="userType==0"><a href="" ng-click="appAction=6">Manage users</a></li>			
			<li ng-show="userType==0"><a href="" ng-click="appAction=7">Manage videogames list</a></li>
			<li ng-show="userType>0"><a href="" ng-click="appAction=8">Suggest videogame</a></li>
			<li><a href="" ng-click="appAction=3">Forum</a></li>
			<li ng-show="userType==-1"><a href="" id="loginButton" ng-click="appAction=1">Login</a></li>
			<li ng-show="userType!=-1"><a href="" id="loginButton" ng-click="gCreedCtrl.logout()">Logout</a></li>
		</ul>
		
	</header>

	<div class="mainDiv contentSections" ng-init="gCreedCtrl.loadUsers()">
		<wall-view ng-show="appAction==0" ng-init="gCreedCtrl.searchPosts()"></wall-view>
		<login-form ng-show="appAction==1"></login-form>
		<user-modify-form ng-show="appAction==2" ng-init="gCreedCtrl.getUserListData()"></user-modify-form>
		<forum-view ng-if="appAction==3" ng-init="gCreedCtrl.getForumSections()"></forum-view>
		<user-entry-form ng-show="appAction==4"></user-entry-form>
		<operation-view ng-show="appAction==5"></operation-view>
		<manage-users-view ng-show="appAction==6" ng-init="gCreedCtrl.loadAllPlaces()"></manage-users-view>
		<manage-videogames-view ng-if="appAction==7" ng-init="gCreedCtrl.loadAllVideogames(); gCreedCtrl.loadAllBrands()"></manage-videogames-view>
		<videogame-suggestion-form ng-if="appAction==8" ng-init="gCreedCtrl.loadAllBrands()"></videogame-suggestion-form>
		<user-search-form ng-show="appAction==10"></user-search-form>
		<videogame-search-form ng-show="appAction==11"></videogame-search-form>
		<unconfirmed-user-operation-view ng-if="appAction==12" ng-init="gCreedCtrl.loadUnconfirmedUserOperations()"></unconfirmed-user-operation-view>
	</div>	
	
	<aside class="advDiv contentSections">
		<img src="img/googleAdsense.jpg"/>
	</aside>
	
	<footer>
		<p>Gamer's Creed</p>
		<p>DAW Final Porject</p>
		<p>Adria Nieto & Xavi Rueda</p>
	</footer>

</body>

</html>