<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title><spring:message code="application.title"/></title>
<meta name="generator" content="Bootply" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>
<!--[if lt IE 9]>	<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>	<![endif]-->
<link href="css/styles.css" rel="stylesheet">
<link rel="icon" type="image/png" href="img/map.png"> <!-- http://www.favicon.cc -->
</head>
<body>

<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.4&appId=702339429897142";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

	<!-- begin view -->
	<jsp:include page="about.jsp" />
	
	<jsp:include page="selectCountry.jsp" />
	
	<jsp:include page="navbar.jsp" />

	<div id="map-canvas"></div>
	
	<div class="newplace addbutton">+</div>
	
	<div class="my_location"><spring:message code="home.map.mylocation" /></div>
	
	<jsp:include page="mylocationalert.jsp" />
	
	<jsp:include page="results.jsp" />

<form action="/offres/creeralerte" class="alerte-form alerte-form-fixed" data-ajax="true" data-ajax-success="creerAlerte" data-ajaxloademail="true" id="form0" method="post" novalidate="novalidate">        
	<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
	<input id="Requete" name="Requete" type="hidden" value="?f=Informatique_dev&amp;q=Ingenieur_B5&amp;l=Saint-Mand%C3%A9%2094160">    <label><span class="picto picto-job-alert-white"></span> Nhan tin moi quanh ban</label>
	<input data-val="true" data-val-email="Veuillez saisir une adresse email valide." data-val-required="Veuillez saisir une adresse email." id="EmailCreationAlerte" name="EmailCreationAlerte" placeholder="Adresse email" type="email" value="">    
	<input type="submit" value="Gui di">
	
</form>
	<!-- end view -->

	<!-- script references -->
	<script	src="js/jquery.js"></script>
	<script src="js/jquery.datetimepicker.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false&extension=.js&output=embed&amp;language=en&amp;v=3.exp&libraries=places"></script>
  
	<script src="js/scripts.js"></script>
	<script src="js/app.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/search.js"></script>
	<script type="text/javascript" src="js/newplace.js"></script>
</body>
</html>