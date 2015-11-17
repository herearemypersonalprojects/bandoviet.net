<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="http://maps.googleapis.com/maps/api/js?extension=.js&output=embed&amp;language=en&amp;v=3.exp&libraries=places"></script>

<script	src="/js/login.js"></script>

<title>Bản đồ Việt</title>
<link rel="stylesheet" href="/css/login.css" type="text/css" />

<link rel="icon" type="image/png" href="/img/map.png">
<!-- http://www.favicon.cc -->
</head>

<body class="login">
	<!-- header starts here -->
	<div id="facebook-Bar">
		<div id="facebook-Frame">
			<div id="logo">
				<a href="http://bandoviet.net">Bandoviet.net</a>
			</div>


			<div id="header-main-right">
				<div id="header-main-right-nav">
					<form role="login_form" method="post" action="/login"
						id="login_form" name="login_form">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<table border="0" style="border: none">
							<tr>
								<c:if
									test="${not empty error and not fn:contains(error, 'empty')}">
									<p style="color:#f28a13;">Email hoặc mật khẩu không đúng hoặc chưa được kích hoạt.</p>
								</c:if>


								<td><input type="email" tabindex="1" id="email"
									placeholder="Email" name="email" class="inputtext radius1"
									value="" required></td>
								<td><input type="password" tabindex="2" id="password"
									placeholder="Mật khẩu" name="password"
									class="inputtext radius1" required></td>
								<td><input type="submit" class="fbbutton" name="login"
									value="Đăng nhập" /></td>
								<%--
              <td> </td>
              <td> </td>
              <td> </td>
               <td ><input type="button" class="facebookbutton"  name="facebooklogin" value="Kết nối qua Facebook" /></td>
                <td ><input type="button" class="linkedinbutton" name="linkedinlogin" value="Kết nối qua Linkedin" /></td>
                 --%>
							</tr>
							<tr>
								<td><label><input id="remember-me" type="checkbox"
										name="remember-me" value="1" checked="1" /><span
										style="color: #ccc;">Ghi nhớ cho lần sau</span></label></td>
								<%--
								<td><label><a href="/lostpassword"
										style="color: #ccc; text-decoration: none">quên mật khẩu?</a></label></td>
								 --%>		
							</tr>
						</table>
					</form>



				</div>
			</div>
		</div>
	</div>
	<!-- header ends here -->

	<div class="loginbox radius">
		<h2 style="color: #141823; text-align: center;">Bản đồ Việt trên thế giới</h2>
		<div class="loginboxinner radius">
			<div class="loginheader">
				<h4 class="title">Khám phá giá trị Việt xung quanh bạn.</h4>
			</div>
			<!--loginheader-->

			<div class="loginform">
				<form:form action="/user/create" modelAttribute="newuser"
					id="newuser" method="POST" enctype="multipart/form-data">
					<%-- <form:errors path="*" cssClass="errorblock" element="div" />--%>
					<p>
						<form:input path="fullname" type="text" id="fullname"
							name="fullname" placeholder="Họ và tên đầy đủ" value="" class="radius" />
						<br>
						<form:errors path="fullname" cssClass="error" />
					</p>
					<p id="addressBox">
						<form:input path="address" id="address" name="address"
						placeholder="Nhập địa chỉ hoặc thành phố của bạn" class="radius" title="Nhập địa chỉ hoặc thành phố của bạn" />
						<br>
						<form:errors path="address" cssClass="error" />
					</p>
					<p id="keywords">
						<form:input path="keywords" id="keywords" name="keywords"
						placeholder="Nghề nghiệp, chuyên môn hoặc lĩnh vực thông thạo" class="radius" title="Nhập nghề nghiệp, chuyên môn hoặc lĩnh vực thông thạo" />
						<br>
						<form:errors path="keywords" cssClass="error" />
					</p>
					<p>
						<form:input path="email" id="email" name="email"
							placeholder="Email" value="" class="radius" />
						<br>
						<form:errors path="email" cssClass="error" />
					</p>
					<p>
						<form:input type="password" path="password" id="password" name="password"
							placeholder="Mật khẩu" class="radius" />
						<br>
						<form:errors path="password" cssClass="error" />
					</p>
					<p>
						<form:input type="password" path="passwordRepeated" id="passwordRepeated"
							name="passwordRepeated" placeholder="Xác nhận Mật khẩu"
							class="radius" />
						<br>
						<form:errors path="passwordRepeated" cssClass="error" />
					</p>
					
					<form:input path="city" type="hidden" id="city" name="city" />
					<form:input path="country" type="hidden" id="country" name="country" />
					<form:input path="latitude" type="hidden" id="latitude"	name="latitude" />
					<form:input path="longitude" type="hidden" id="longitude" name="longitude" />
					<form:input path="confidentLevel" type="hidden" id="confidentLevel" name="confidentLevel"/>
					
					<form:input path="role" type="hidden" id="role" name="role" />
					<p>
						<button type="submit" class="radius title" name="client_login">Đăng
							ký</button>
					</p>
				</form:form>
			</div>
			<!--loginform-->
		</div>
		<!--loginboxinner-->
	</div>
	<!--loginbox-->
	
	<a></a>

	<div id="ad" style="width: 100%; margin-top: -12px;">
		<center>
			<c:if test="${not empty places }">
				<div class="container">
					<br>
					<div id="myCarousel" class="carousel slide" data-ride="carousel">
						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">

							<c:forEach var="item" items="${ places }" varStatus="status">
								<div
									class="item <c:if test="${status.index == 0}">active</c:if>">
									<img src="${ item.imagePath }" />
									<div class="carousel-caption">
										<p>${ item.title }</p>
										<p>${ item.address }</p>
									</div>
								</div>
							</c:forEach>
						</div>

						<!-- Left and right controls -->
						<a class="left carousel-control" href="#myCarousel" role="button"
							data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#myCarousel"
							role="button" data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</c:if>
		</center>
	</div>



	
<p style="font-size:12px; ">
  <center>
    <a style = "visibility:hidden;" href="http://bandoviet.net/public/1">Phát triển bởi bandoviet.net</a>
  </center>
</p>

</body>

</html>
