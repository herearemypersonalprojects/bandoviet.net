<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title><spring:message code="application.title" /></title>
<meta name="generator" content="Bootply" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
<link href="css/styles.css" rel="stylesheet">
<link rel="icon" type="image/png" href="img/map.png">
<!-- http://www.favicon.cc -->
</head>
<body>
	<!-- begin template -->
	<div class="navbar navbar-custom navbar-fixed-top">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">
				<img  height="42" width="42" alt="Brand" src="img/map.png">
			</a> 
			<a class="navbar-toggle"
				data-toggle="collapse" data-target=".navbar-collapse"> <span
				class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
			</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#"><spring:message code="home.navbar.event" /></a></li>
				<li><a href="#"><spring:message code="home.navbar.news" /></a></li>
				<li>&nbsp;</li>
			</ul>
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group" style="display: inline;">
					<div class="input-group">
						<div class="input-group-btn">
							<button type="button" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown">
								<span class="glyphicon glyphicon-chevron-down"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="#"><spring:message code="home.navbar.restaurant" /> </a></li>
								<li><a href="#"><spring:message code="home.navbar.administration" /></a></li>
								<li><a href="#"><spring:message code="home.navbar.company" /></a></li>
								<li><a href="#"><spring:message code="home.navbar.association" /></a></li>
								<li><a href="#"><spring:message code="home.navbar.tourism" /></a></li>
								<li><a href="#"><spring:message code="home.navbar.sport" /></a></li>
								<li><a href="#"><spring:message code="home.navbar.market" /></a></li>
								<li><a href="#"><spring:message code="home.navbar.service" /></a></li>
								<li><a href="#"><spring:message code="home.navbar.individual" /></a></li>
							</ul>
						</div>
						<input type="text" class="form-control"
							placeholder=<spring:message code="home.navbar.search.placeholder" />> 
							<span class="input-group-addon">
							<span class="glyphicon glyphicon-search"></span> 
							</span>
					</div>
				</div>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/?lang=vn">Ti&#7871;ng Vi&#7879;t</a></li>
				<li><a href="/?lang=en">English</a></li>
				<li><a href="/?lang=fr">Fran&#231;ais</a></li>
				<li><a href="#"><img  height="20" width="20" alt="Brand" src="img/user.png"></a></li>
				<li><a href="#"></a></li>
			</ul>
		</div>
	</div>

	<div id="map-canvas"></div>
	
	<div class="addbutton">+</div>
	
	
	<div class="container-fluid" id="main">
		<div class="row">
			<div class="col-xs-6" id="left">

				<h2>Bootstrap Google Maps Demo</h2>

				<!-- item list -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="">Item heading</a>
					</div>
				</div>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis
					pharetra varius quam sit amet vulputate. Quisque mauris augue,
					molestie tincidunt condimentum vitae, gravida a libero. Aenean sit
					amet felis dolor, in sagittis nisi. Sed ac orci quis tortor
					imperdiet venenatis. Duis elementum auctor accumsan. Aliquam in
					felis sit amet augue.</p>

				<hr>

				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="">Item heading</a>
					</div>
				</div>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis
					pharetra varius quam sit amet vulputate. Quisque mauris augue,
					molestie tincidunt condimentum vitae, gravida a libero. Aenean sit
					amet felis dolor, in sagittis nisi. Sed ac orci quis tortor
					imperdiet venenatis. Duis elementum auctor accumsan. Aliquam in
					felis sit amet augue.</p>

				<hr>

				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="">Item heading</a>
					</div>
				</div>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis
					pharetra varius quam sit amet vulputate. Quisque mauris augue,
					molestie tincidunt condimentum vitae, gravida a libero. Aenean sit
					amet felis dolor, in sagittis nisi. Sed ac orci quis tortor
					imperdiet venenatis. Duis elementum auctor accumsan. Aliquam in
					felis sit amet augue.</p>

				<hr>

				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="">Item heading</a>
					</div>
				</div>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis
					pharetra varius quam sit amet vulputate. Quisque mauris augue,
					molestie tincidunt condimentum vitae, gravida a libero. Aenean sit
					amet felis dolor, in sagittis nisi. Sed ac orci quis tortor
					imperdiet venenatis. Duis elementum auctor accumsan. Aliquam in
					felis sit amet augue.</p>

				<hr>
				<!-- /item list -->

				<p>
					<a href="http://www.bootply.com/render/129229">Demo</a> | <a
						href="http://bootply.com/129229">Source Code</a>
				</p>

				<hr>

				<p>
					<a href="http://bootply.com" target="_ext"
						class="center-block btn btn-primary">More Bootstrap Snippets
						on Bootply</a>
				</p>

				<hr>

			</div>
			<div class="col-xs-4">
				<!--map-canvas will be postioned here-->
			</div>

		</div>
	</div>
	<!-- end template -->

	<!-- script references -->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script
		src="http://maps.googleapis.com/maps/api/js?sensor=false&extension=.js&output=embed"></script>
	<script src="js/scripts.js"></script>
</body>
</html>