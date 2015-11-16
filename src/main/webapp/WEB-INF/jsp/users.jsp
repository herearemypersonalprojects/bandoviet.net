<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Danh sach nguoi dung</title>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
}
</style>
</head>
<body>

	<table style="width: 100%">
		<tr>
			<th>Stt</th>
			<th>Ho ten</th>
			<th>Email</th>
			<th>Do tin tuong</th>
			<th>Enabled</th>
			<th>Dia chi</th>
		</tr>
		<c:forEach items="${users}" var="user" varStatus="recipeCounter">
			<tr>
				<td>${recipeCounter.count}</td>
				<td>${user.fullname}</td>
				<td>${user.email}</td>
				<td>${user.confidentLevel}</td>
				<td><c:if test="${user.enabled}">Da kich hoat</c:if></td>
				<td>${user.address}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
