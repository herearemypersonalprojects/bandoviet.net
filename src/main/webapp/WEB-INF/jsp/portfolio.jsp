<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->  
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->  
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->  
<head>
    <title>${place.title}</title>
    <link rel="icon" type="image/png" href="/img/map.png"> <!-- http://www.favicon.cc -->
    <!-- Meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="${place.title}, Ban do nguoi Viet Nam tren the gioi, Viet kieu, the map of Vietnamese people in the world">    
    <meta name="author" content="Le Quoc Anh - PhD in Computer Science - Telecom ParisTech, France">    
    <link rel="shortcut icon" href="favicon.ico">  
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,300italic,400italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'> 
    <!-- Global CSS -->
    <link rel="stylesheet" href="/portfolio/plugins/bootstrap/css/bootstrap.min.css">   
    <!-- Plugins CSS -->
    <link rel="stylesheet" href="/portfolio/plugins/font-awesome/css/font-awesome.css">
    <!-- github acitivity css -->
    <link rel="stylesheet" href="/portfolio/plugins/github-activity/dist/github-activity-0.1.1.min.css">
    <link rel="stylesheet" href="/portfolio/plugins/github-activity/dist/octicons/octicons.min.css">
    
    
    <!-- Theme CSS -->  
    <link id="theme-style" rel="stylesheet" href="/portfolio/css/styles.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <style type="text/css">
		    .header .profile-image {
		    height: 168px;
		    margin-right: 30px;
		    width: 168px;
		}
    </style>
    
</head> 

<body>
    <!-- ******HEADER****** --> 
    <header class="header">
        <div class="container">                       
            <img class="img-circle profile-image img-responsive pull-left" src="${place.imagePath}" alt="${place.title}" />
            <div class="profile-content pull-left">
                <h1 class="name">${place.title}</h1>
                <h2 class="desc">${place.title}</h2>   
                <ul class="social list-inline">
                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>                   
                    <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                    <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                    <li><a href="#"><i class="fa fa-github-alt"></i></a></li>                  
                    <li class="last-item"><a href="#"><i class="fa fa-hacker-news"></i></a></li>                 
                </ul> 
            </div><!--//profile-->
            <a class="btn btn-cta-primary pull-right" href="/update?id=${place.id}"><i class="fa fa-paper-plane"></i> <spring:message code="home.result.item.edit"/></a>              
        </div><!--//container-->
    </header><!--//header-->
    
    <div class="container sections-wrapper">
        <div class="row">
            <div class="primary col-md-8 col-sm-12 col-xs-12">
                <section class="about section">
                    <div class="section-inner">
                        <h2 class="heading">Giới thiệu</h2>
                        <div class="content">
                            ${place.information}   
                        </div><!--//content-->
                    </div><!--//section-inner-->                 
                </section><!--//section-->
    

                <section class="experience section">
                    <div class="section-inner">
                        <h2 class="heading">Các dự án liên quan đến Việt Nam</h2>
                        <div class="content">
                            <div class="item">
                                <h3 class="title">Dự án 1 - <span class="place"><a href="#">Hội người Việt</a></span> <span class="year">(2014 - Đến nay)</span></h3>
                                <p>Nội dung.</p>
                            </div><!--//item-->
                            <div class="item">
                                <h3 class="title">Dự án 2 - <span class="place"><a href="#">Quyên góp ủng hộ đồng bào lũ lụt</a></span> <span class="year">(2013 - 2014)</span></h3>
                                <p>Nội dung.</p>
                            </div><!--//item-->
                            
                            <div class="item">
                                <h3 class="title">Dự án 3 - <span class="place"><a href="#">Ngày Việt Nam</a></span> <span class="year">(2012 - 2013)</span></h3>
                                <p>Nội dung.</p>
                            </div><!--//item-->
                            
                        </div><!--//content-->  
                    </div><!--//section-inner-->                 
                </section><!--//section-->
                <section class="github section">
                    <div class="section-inner">
                        <h2 class="heading">Thông tin khác</h2>
                        <p>.... <a href="http://62.210.116.18:8000/" target="_blank">Đang phát triển</a> ...        
                        <!--//Usage: http://caseyscarborough.com/projects/github-activity/                      
                        <div id="ghfeed" class="ghfeed">
                        </div><!--//ghfeed-->
                        
                    </div><!--//secton-inner-->
                </section><!--//section-->
            </div><!--//primary-->
            <div class="secondary col-md-4 col-sm-12 col-xs-12">
                 <aside class="info aside section">
                    <div class="section-inner">
                        <h2 class="heading sr-only">Thông tin cơ bản</h2>
                        <div class="content">
                            <ul class="list-unstyled">
                                <li><i class="fa fa-map-marker"></i><span class="sr-only">Location:</span>${place.address}</li>
                                <li><i class="fa fa-envelope-o"></i><span class="sr-only">Email:</span><a href="#">${place.email}</a></li>
                                <li><i class="fa fa-link"></i><span class="sr-only">Website:</span><a href="${place.referenceUrl}">${place.referenceUrl}</a></li>
                            </ul>
                        </div><!--//content-->  
                    </div><!--//section-inner-->                 
                </aside><!--//aside-->
                
                <aside class="skills aside section">
                    <div class="section-inner">
                        <h2 class="heading">Lĩnh vực</h2>
                        <div class="content">
                            <p class="intro">
                                ${place.placeType}.</p>
                                         
                        </div><!--//content-->  
                    </div><!--//section-inner-->                 
                </aside><!--//section-->
                
                <aside class="testimonials aside section">
                    <div class="section-inner">
                        <h2 class="heading">Câu nói</h2>
                        <div class="content">
                            <div class="item">
                                <blockquote class="quote">                                  
                                    <p><i class="fa fa-quote-left"></i>Không phải ai cũng có khả năng để đạt giải Nobel hay Fields, nhưng ai cũng có thể sống để cuộc sống của mình có ý nghĩa.</p>
                                </blockquote>                
                                <p class="source"><span class="name">Ngô Bảo Châu</span><br /><span class="title">BBC tiếng Việt. Ngày 21 tháng 8 năm 2010.</span></p>                                                             
                            </div><!--//item-->
                            
                            <p><a class="more-link" href="#"><i class="fa fa-external-link"></i> Tham khảo thêm</a></p> 
                            
                        </div><!--//content-->
                    </div><!--//section-inner-->
                </aside><!--//section-->
                
     <!-- Javascript -->          
    <script type="text/javascript" src="/portfolio/plugins/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/portfolio/plugins/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="/portfolio/plugins/bootstrap/js/bootstrap.min.js"></script>    
    <script type="text/javascript" src="/portfolio/plugins/jquery-rss/dist/jquery.rss.min.js"></script> 
    <!-- github activity plugin -->
    <script type="text/javascript" src="/portfolio/plugins/github-activity/dist/mustache/mustache.min.js"></script>
    <script type="text/javascript" src="/portfolio/plugins/github-activity/dist/github-activity-0.1.1.min.js"></script>
    <!-- custom js -->
    <script type="text/javascript" src="/portfolio/js/main.js"></script>            
</body>
</html> 

