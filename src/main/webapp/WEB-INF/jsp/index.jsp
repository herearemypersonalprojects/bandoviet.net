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
<!--[if lt IE 9]>	<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>	<![endif]-->
<link href="css/styles.css" rel="stylesheet">
<link rel="icon" type="image/png" href="img/map.png"> <!-- http://www.favicon.cc -->
</head>
<body>
	<!-- begin view -->
	<jsp:include page="navbar.jsp" />

	<div id="map-canvas"></div>
	
	<div class="addbutton">+</div>
	
	<jsp:include page="results.jsp" />

	<!-- end view -->

	<!-- script references -->
	<script	src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script	src="http://maps.googleapis.com/maps/api/js?sensor=false&extension=.js&output=embed"></script>
	<script src="js/scripts.js"></script>
	<script src="js/newplace.js"></script>
</body>
</html>