<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<li id="eventCategory" class="active"><a href="#"><spring:message code="home.navbar.event" /></a></li>
				<li><a href="#"><spring:message code="home.navbar.news"/></a></li>
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
								<li><a href="#"><spring:message code="home.navbar.usefulinfo" /></a></li>
								
							</ul>
						</div>
						
						<input id = "searchText" style="width:50%;" type="text" class="form-control"	placeholder=<spring:message code="home.navbar.search.placeholder" />> 
								
						<input id = "searchCity" style="width:50%;" type="text" class="form-control" value="Paris, France"	placeholder=<spring:message code="home.navbar.search.placeholder" />> 
						
						<div class="input-group-btn">							
							<button type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-search"></span> 
							</button>							
						</div>
					</div>
				</div>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li class="language vn"><a href="/?lang=vn">Ti&#7871;ng Vi&#7879;t</a></li>
				<li class="language en"><a href="/?lang=en">English</a></li>
				<li class="language fr"><a href="/?lang=fr">Fran&#231;ais</a></li>
				<li><a href="#"><img  height="20" width="20" alt="Brand" src="img/user.png"></a></li>
				<li><a href="#"></a></li>
			</ul>
		</div>
	</div>