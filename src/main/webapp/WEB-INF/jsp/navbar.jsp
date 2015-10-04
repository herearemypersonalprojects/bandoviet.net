<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<div class="navbar navbar-custom navbar-fixed-top">
	<div class="navbar-header">
		<a class="navbar-brand" href="/"><spring:message code="home.navbar.title"/></a> <a class="navbar-toggle"
			data-toggle="collapse" data-target=".navbar-collapse"> <span
			class="icon-bar"></span> <span class="icon-bar"></span> <span
			class="icon-bar"></span>
		</a>
	</div>
	<div class="navbar-collapse collapse">
		<ul class="nav navbar-nav">
			<li class="language" lang="en"><a href="#">English</a></li>
			<li class="language" lang="fr"><a href="#">Français</a></li>
			<li class="language" lang="vn"><a href="#">Tiếng Việt</a></li>
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
							<li id="eventCategory" class="active"><a href="#"><spring:message
										code="home.navbar.event" /></a></li>
							<li><a href="#"><spring:message code="home.navbar.news" /></a></li>
							<li onClick="searchByType(this.value);" id="searchByfriendsmap"><a
								href="#"><spring:message code="home.navbar.friendsmap" /> </a></li>
							<li><a href="#type=annoucement"><spring:message
										code="home.navbar.annoucement" /> </a></li>
							<li><a href="#"><spring:message
										code="home.navbar.restaurant" /> </a></li>
							<li><a href="#"><spring:message
										code="home.navbar.administration" /></a></li>
							<li><a href="#"><spring:message
										code="home.navbar.company" /></a></li>
							<li><a href="#"><spring:message
										code="home.navbar.association" /></a></li>
							<li><a href="#"><spring:message
										code="home.navbar.tourism" /></a></li>
							<li><a href="#"><spring:message code="home.navbar.sport" /></a></li>
							<li><a href="#"><spring:message
										code="home.navbar.market" /></a></li>
							<li><a href="#"><spring:message
										code="home.navbar.service" /></a></li>
							<li><a href="#"><spring:message
										code="home.navbar.individual" /></a></li>
							<li><a href="#"><spring:message
										code="home.navbar.usefulinfo" /></a></li>
							<li><a href="#"><spring:message
										code="home.navbar.countries" /></a></li>
						</ul>
					</div>
					<input id="keywords" type="text" data-provide="typeahead" class="form-control"
						placeholder="<spring:message code="home.navbar.search.placeholder" />"> 
						<span class="input-group-addon"><span
						class="glyphicon glyphicon-search"></span> </span>
				</div>
			</div>
		</form>
	</div>
</div>