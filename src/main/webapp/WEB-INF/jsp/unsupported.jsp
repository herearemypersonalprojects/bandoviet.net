<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="application.title" /></title>
<link rel="icon" type="image/png" href="/img/map.png"> <!-- http://www.favicon.cc -->

	<style>
		.lang {
			text-decoration: underline;
			cursor: pointer;
		}
	</style>
</head>
<body>


	<spring:message code="application.mobile.unsupported" /> (<a href="/unsupported/?lang=en">English</a>, <a href="/unsupported/?lang=fr">Français</a>, <a href="/unsupported/?lang=vn">Tiếng Việt</a>)
</body>
</html>