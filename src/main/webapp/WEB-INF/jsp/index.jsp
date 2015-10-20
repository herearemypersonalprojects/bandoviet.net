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
<meta name="author" content="Le Quoc Anh - PhD in Computer Science - Telecom ParisTech, France"> 
<meta name="description" content="Ban do nguoi Viet Nam tren the gioi, Viet kieu, the map of Vietnamese people in the world">
        
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/jquery.datetimepicker.css"/>

<!--[if lt IE 9]>	<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>	<![endif]-->

<%--<link href="/css/infobox.css" rel="stylesheet"> --%>
<link href="/css/styles.css" rel="stylesheet">

<link rel="stylesheet" href="/libs/jquery-ui.min.css">

<link rel="icon" type="image/png" href="/img/map.png"> <!-- http://www.favicon.cc -->
</head>
<body>

<div id="fb-root"></div>

	<!-- begin view -->
	<jsp:include page="about.jsp" />
	
	<jsp:include page="feedback.jsp" />
	
	<jsp:include page="selectCountry.jsp" />
	
	<jsp:include page="navbar.jsp" />

	<%--
	<div id="map-canvasd">
	
    </div>
	 --%>
	 <jsp:include page="results.jsp" />
	 
	<div class="newplace addbutton">+</div>
	
	<div class="my_location" title="<spring:message code="home.map.mylocation"/>"></div>
	
	<jsp:include page="mylocationalert.jsp" />
	<%-- 
	      <!--Page Footer-->
        <footer id="page-footer">
            <div class="inner">
                <div class="footer-top">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-4 col-sm-4">
                                <!--New Items-->
                                <section>
                                    <h2>New Items</h2>
                                    <a href="real-estate-item-detail.html" class="item-horizontal small">
                                        <h3>Cash Cow Restaurante</h3>
                                        <figure>63 Birch Street</figure>
                                        <div class="wrapper">
                                            <div class="image"><img src="assets/img/items/1.jpg" alt=""></div>
                                            <div class="info">
                                                <div class="type">
                                                    <i><img src="assets/icons/real-estate/apartment-3.png" alt=""></i>
                                                    <span>Restaurant</span>
                                                </div>
                                                <div class="rating" data-rating="4"></div>
                                            </div>
                                        </div>
                                    </a>
                                    <!--/.item-horizontal small-->
                                    <a href="real-estate-item-detail.html" class="item-horizontal small">
                                        <h3>Blue Chilli</h3>
                                        <figure>2476 Whispering Pines Circle</figure>
                                        <div class="wrapper">
                                            <div class="image"><img src="assets/img/items/2.jpg" alt=""></div>
                                            <div class="info">
                                                <div class="type">
                                                    <i><img src="assets/icons/real-estate/apartment-3.png" alt=""></i>
                                                    <span>Restaurant</span>
                                                </div>
                                                <div class="rating" data-rating="3"></div>
                                            </div>
                                        </div>
                                    </a>
                                    <!--/.item-horizontal small-->
                                </section>
                                <!--end New Items-->
                            </div>
                            <div class="col-md-4 col-sm-4">
                                <!--Recent Reviews-->
                                <section>
                                    <h2>Recent Reviews</h2>
                                    <a href="real-estate-item-detail.html#reviews" class="review small">
                                        <h3>Max Five Lounge</h3>
                                        <figure>4365 Bruce Street</figure>
                                        <div class="info">
                                            <div class="rating" data-rating="4"></div>
                                            <div class="type">
                                                <i><img src="assets/icons/restaurants-bars/restaurants/restaurant.png" alt=""></i>
                                                <span>Restaurant</span>
                                            </div>
                                        </div>
                                        <p>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec non suscipit felis, sed sagittis tellus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras ac placerat mauris.
                                        </p>
                                    </a><!--/.review-->
                                    <a href="real-estate-item-detail.html#reviews" class="review small">
                                        <h3>Saguaro Tavern</h3>
                                        <figure>2476 Whispering Pines Circle</figure>
                                        <div class="info">
                                            <div class="rating" data-rating="5"></div>
                                            <div class="type">
                                                <i><img src="assets/icons/restaurants-bars/restaurants/restaurant.png" alt=""></i>
                                                <span>Restaurant</span>
                                            </div>
                                        </div>
                                        <p>
                                            Pellentesque mauris. Proin sit amet scelerisque risus. Donec semper semper erat ut mollis curabitur
                                        </p>
                                    </a>
                                    <!--/.review-->
                                </section>
                                <!--end Recent Reviews-->
                            </div>
                            <div class="col-md-4 col-sm-4">
                                <section>
                                    <h2>About Us</h2>
                                    <address>
                                        <div>Max Five Lounge</div>
                                        <div>63 Birch Street</div>
                                        <div>Granada Hills, CA 91344</div>
                                        <figure>
                                            <div class="info">
                                                <i class="fa fa-mobile"></i>
                                                <span>818-832-5258</span>
                                            </div>
                                            <div class="info">
                                                <i class="fa fa-phone"></i>
                                                <span>+1 123 456 789</span>
                                            </div>
                                            <div class="info">
                                                <i class="fa fa-globe"></i>
                                                <a href="#">www.maxfivelounge.com</a>
                                            </div>
                                        </figure>
                                    </address>
                                    <div class="social">
                                        <a href="#" class="social-button"><i class="fa fa-twitter"></i></a>
                                        <a href="#" class="social-button"><i class="fa fa-facebook"></i></a>
                                        <a href="#" class="social-button"><i class="fa fa-pinterest"></i></a>
                                    </div>

                                    <a href="contact.html" class="btn framed icon">Contact Us<i class="fa fa-angle-right"></i></a>
                                </section>
                            </div>
                            <!--/.col-md-4-->
                        </div>
                        <!--/.row-->
                    </div>
                    <!--/.container-->
                </div>
                <!--/.footer-top-->
                <div class="footer-bottom">
                    <div class="container">
                        <span class="left">(C) ThemeStarz, All rights reserved</span>
                            <span class="right">
                                <a href="#page-top" class="to-top roll"><i class="fa fa-angle-up"></i></a>
                            </span>
                    </div>
                </div>
                <!--/.footer-bottom-->
            </div>
        </footer>
        <!--end Page Footer-->--%>
	<!-- end view -->

	<!-- script references -->
	<script	src="/js/jquery.js"></script>
	<script	src="/libs/jquery.validate.js"></script>
	<script src="/js/jquery.datetimepicker.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false&extension=.js&output=embed&amp;language=en&amp;v=3.exp&libraries=places"></script>
  
    <script src="/js/cookie.js" type="text/javascript"></script>
    
    <script src="/js/lang.js" type="text/javascript"></script>
    <script src="/js/mapService.js"></script>
	<script src="/js/scripts.js"></script>
	<%--<script	src="/libs/infobox.js"></script> --%>
	
	<script type="text/javascript" src="/js/search.js"></script>
	<%--<script type="text/javascript" src="js/newplace.js"></script> --%>
	<script src="/js/item.js" type="text/javascript"></script>
	<script src="/js/app.js" type="text/javascript"></script>
	
	<script type="text/javascript" src="/libs/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/libs/readmore.min.js"></script>
	<script type="text/javascript" src="/js/avim.js"></script>
</body>
</html>