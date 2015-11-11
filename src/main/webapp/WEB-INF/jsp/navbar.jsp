<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<div class="navbar navbar-custom navbar-fixed-top">
	<div class="navbar-header">
		
		<a class="navbar-brand" href="/"><spring:message code="home.navbar.title"/></a> 
		<a class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> 
			<span class="icon-bar"></span> 
			<span class="icon-bar"></span> 
			<span class="icon-bar"></span>
		</a>
	</div>
	<div class="navbar-collapse collapse">
	
		<ul class="nav navbar-nav">		
			<%-- 
			<li class="language" lang="en"> <a href="#">English</a></li>
			<li class="language" lang="fr"><a href="#">Français</a></li>
			<li class="language" lang="vn"><a href="#">Tiếng Việt</a></li>
			 --%>
			 <%--
			 <li class="dropdown">
	          <a href="javascript: onclick();" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><font size="3">Bản đồ nhóm</font> <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="#">Nhóm cô dâu Việt tại Pháp (công khai)</a></li>
	            <li><a href="#">Nhóm Centre Parc (riêng tư)</a></li>
	            <li><a href="#">Nhóm Télécom ParisTech (công khai)</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="#">Separated link</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="#">Nhóm FA tại Paris (riêng tư)</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="#">Tạo nhóm mới...</a></li>
	          </ul>
	        </li>
	         --%>
			 <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Bản đồ của bạn <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	          	<li><a href="#">Đăng nhập</a></li>
	          	<li><a href="#">Đăng ký sử dụng</a></li>
	          	<li role="separator" class="divider"></li>
	            <li onClick="searchByType(this.value);" id="searchByfriendsmap">
								<a href="#"><spring:message code="home.navbar.friendsmap" /> </a>
							</li>
	 
	            <li role="separator" class="divider"></li>
	            <li><a href="#">Gia đình, họ hàng</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="#">Địa chỉ cần nhớ</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="#">Thoát ra</a></li>
	          </ul>
	        </li>	        
			<li>&nbsp;</li>
		</ul>
		<form class="navbar-form">
			<div class="form-group" style="display: inline;">
				<div class="input-group">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown">
							<span class="glyphicon glyphicon-chevron-down"></span>
						</button>
					
						<ul class="dropdown-menu">
							<c:forEach items="<%= net.bandoviet.place.PlaceType.values()%>" var="option">
								<li class="selectedType" id="${option}">
									<a href="#"><spring:message	code="${option.code }" /></a>
								</li>
							</c:forEach>
			
						</ul>
					</div>
					<input style="width: 50%;" id="keywords" type="text" data-provide="typeahead" class="form-control"
						   value="${keywords}"				 placeholder=<spring:message	code="home.navbar.search.placeholder" />> 
					<input style="width: 50%;" id="locationSearch" type="text" data-provide="typeahead" class="form-control"
										 placeholder="Chọn vị trí tìm kiếm" value="${address}"> 		
			   		<input type="hidden" id="countrySearch" name="countrySearch" value="${country}" />
			    	<input type="hidden" id="citySearch" name="citySearch" value="" />
					<input type="hidden" id="cityLat" name="cityLat" value="${lat}" />
					<input type="hidden" id="cityLng" name="cityLng" value="${lng}"/> 			 								 
						<div class="input-group-btn">							
							<button id="searchSubmit" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-search"></span> 
							</button>				
						</div>

					<div class="input-group-btn">
					<div class="dropdown">
  <button id="feedback"  class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" 
  	data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    <span class="glyphicon glyphicon-heart-empty"></span>
    <span class="comment_item"> Góp ý cho chúng tôi</span> 	
    <span class="glyphicon glyphicon-heart-empty"></span>
  </button>
  <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
    <li><a href="#">Giới thiệu</a></li>
    <li><a href="#">Hướng dẫn sử dụng</a></li>

  </ul>
</div>
									
							<!-- <img  class="img-circle" width="25" style="right: 10px;" src="/img/tuan.jpg">  -->
					
					
					</div>				</div>

			</div>
		</form>
	</div>
</div>