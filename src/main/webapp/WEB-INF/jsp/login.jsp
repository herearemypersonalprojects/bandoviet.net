<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<title>Bản đồ Việt</title>
<link rel="stylesheet" href="/css/login.css" type="text/css" />

<link rel="icon" type="image/png" href="/img/map.png"> <!-- http://www.favicon.cc -->
</head>

<body class="login">
<!-- header starts here -->
<div id="facebook-Bar">
  <div id="facebook-Frame">
    <div id="logo"> <a href="http://bandoviet.net">Bandoviet.net</a> </div>
    
         
        <div id="header-main-right">
          <div id="header-main-right-nav">
        <form method="post" action="" id="login_form" name="login_form">
          <table border="0" style="border:none">
            <tr>
              <td ><input type="text" tabindex="1"  id="email" placeholder="Email or Phone" name="email" class="inputtext radius1" value=""></td>
              <td ><input type="password" tabindex="2" id="pass" placeholder="Mật khẩu" name="pass" class="inputtext radius1" ></td>
              <td ><input type="submit" class="fbbutton" name="login" value="Đăng nhập" /></td>
              <td> </td>
              <td> </td>
              <td> </td>
               <td ><input type="button" class="facebookbutton"  name="facebooklogin" value="Kết nối qua Facebook" /></td>
                <td ><input type="button" class="linkedinbutton" name="linkedinlogin" value="Kết nối qua Linkedin" /></td>
            </tr>
            <tr>
            <td><label><input id="persist_box" type="checkbox" name="persistent" value="1" checked="1"/><span style="color:#ccc;">Ghi nhớ cho lần sau</span></label>
            </td>
            <td><label><a href="" style="color:#ccc; text-decoration:none">quên mật khẩu?</a></label></td>
            </tr>
          </table>
        </form>
        
        
        
      </div>
          </div>
      </div>
</div>
<!-- header ends here -->

<div class="loginbox radius">
<h2 style="color:#141823; text-align:center;">Gia nhập bản đồ Việt</h2>
	<div class="loginboxinner radius">
    	<div class="loginheader">
    		<h4 class="title">Kết nối với giá trị Việt xung quanh bạn.</h4>
    	</div><!--loginheader-->
        
        <div class="loginform">
                	
        	<form id="login" action="" method="post">
            	<p>
            		<input type="text" id="username" name="username" placeholder="Họ và tên" value="" class="radius" />          
                </p>
            	<p>
            		<input type="text" id="address" name="address" placeholder="Địa chỉ" value="" class="radius" />          
                </p>                
            	<p>
                    <input type="text" id="email" name="email" placeholder="Email" value="" class="radius" />
                </p>
                <p>
                    <input type="text" id="password" name="password" placeholder="Nhắc lại Email" class="radius" />
                </p>
                <p>
                    <input type="password" id="password" name="password" placeholder="Mật khẩu" class="radius" />
                </p>
                <p>
                	<button class="radius title" name="client_login">Đăng ký</button>
                </p>
            </form>
        </div><!--loginform-->
    </div><!--loginboxinner-->
</div><!--loginbox-->

<div id="ad" style="width:100%; margin-top:-12px;">
<center> 
    <%-- 
	<c:forEach var="item" items="${ places }" varStatus="status">
	  <div class="item">
        <img src="${ item.imagePath }" alt="${ item.title }" width="146" height="134">
        <div class="carousel-caption">
          <h3>${ item.title }</h3>
          <p>${ item.address }</p>
        </div>
      </div>
	</c:forEach>
--%>

<div class="container">
  <br>
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>
     -->

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      
      	<c:forEach var="item" items="${ places }" varStatus="status">
		  <div class="item <c:if test="${status.index == 0}">active</c:if>">
	        <img src="${ item.imagePath }"/>
	        <div class="carousel-caption">
	       	 <p>${ item.title }</p>
	          <p>${ item.address }</p>
	        </div>
	      </div>
		</c:forEach>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>
</center>
</div>



<%--
<p style="font-size:12px;">
  <center>
    <a href="http://www.bandoviet.net">Phát triển bởi bandoviet.net</a>
  </center>
</p>
 --%>
 
</body>

</html>
