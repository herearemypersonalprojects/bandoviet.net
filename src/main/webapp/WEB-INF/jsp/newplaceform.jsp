<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="container-fluid" id="main">
	<div class="row">
		<div id="results" class="col-xs-5" id="left">
			<br>

			<form:form action="/save" modelAttribute="item" class="form-horizontal" id="newplaceform" method="POST" enctype="multipart/form-data">
				<div class="form-group">
					<form:label path="title" class="col-sm-3 control-label">
						<spring:message	code="home.new.name" />
					</form:label>
					<div class="col-sm-9">
						<spring:message code="home.new.name.suggest" var="suggest"/>
						<form:input class="form-control" id="title" name="title" 
						placeholder="${suggest}" path="title" title="${suggest}"/>						
					</div>
				</div>
				<div class="form-group">
					<form:label path="address" class="col-sm-3 control-label">
						<spring:message	code="home.new.address" />
					</form:label>
					<div class="col-sm-9">
						<spring:message code="home.new.address.suggest" var="suggest"/>
						<form:input class="form-control" id="address" name="address"	
						placeholder="${suggest}" path="address" title="${suggest}"/>	
					</div>
					<form:input path="streetNumber" type="hidden" id="street_number" name="streetNumber" /> 
					<form:input path="route" type="hidden" id="route" name="route" /> 
					<form:input path="postalCode" type="hidden" id="postal_code" name="postalCode"/> 
					<form:input path="latitude"	type="hidden" id="latitude" name="latitude"/> 
					<form:input path="longitude" type="hidden" id="longitude" name="longitude"/>
				</div> 
				
				<div class="form-group hide">
					<form:input path="city" id="locality" name="city" /> 
					<form:input path="country" id="country"	name="country"/>
				</div>
 
				<div class="form-group">
					<label for="image" class="col-sm-3 control-label">
						<spring:message	code="home.new.photo" /> 
					</label>
					<div class="col-sm-9">
						<input name="image" type="file" id="file" class="form-control">						
					</div>
					<c:if test="${ not empty imagePath }">	<img class="photo_item" src="${imagePath}" alt="Photo"> </c:if>
					<!-- <p class="help-block">Các thông tin có dấu (*) là bắt buộc phải điền.</p> -->
				</div>

				<div class="form-group">
					<label for="title" class="col-sm-3 control-label"><spring:message
							code="home.new.type" /></label>
					<div class="col-sm-9">
                		<form:select path="placeType" class="form-control" id="placeType" name="placeType">
							<c:forEach items="${typeList}" var="option">
									<form:option value="${option}" ><spring:message code="${option.code }"/></form:option>
							</c:forEach>
						</form:select>
						<%--
						<form:select onChange="changeType(this.value);" name="placeType"
							class="form-control" id="placeType">
							<option value="" selected><spring:message
									code="home.new.type.suggest" /></option>
							<option value="friendsmap"><spring:message
									code="home.navbar.friendsmap" /></option>
							<option value="event"><spring:message
									code="home.navbar.event" /></option>
							<option value="news"><spring:message
									code="home.navbar.news" /></option>
							<option value="annoucement"><spring:message
									code="home.navbar.annoucement" /></option>
							<option value="restaurant"><spring:message
									code="home.navbar.restaurant" /></option>
							<option value="administration"><spring:message
									code="home.navbar.administration" /></option>
							<option value="company"><spring:message
									code="home.navbar.company" /></option>
							<option value="association"><spring:message
									code="home.navbar.association" /></option>
							<option value="tourism"><spring:message
									code="home.navbar.tourism" /></option>
							<option value="sport"><spring:message
									code="home.navbar.sport" /></option>
							<option value="market"><spring:message
									code="home.navbar.market" /></option>
							<option value="service"><spring:message
									code="home.navbar.service" /></option>
							<option value="individual"><spring:message
									code="home.navbar.individual" /></option>
							<option value="countries"><spring:message
									code="home.navbar.countries" /></option>
							<option value="usefulinfo"><spring:message
									code="home.navbar.usefulinfo" /></option>
						</select>
						 --%>
					</div>
				</div>

				<div id="eventTimeGroup" class="form-group hide">
					<label for="startDate" class="col-sm-3 control-label"><spring:message
							code="home.new.time" /></label>

					<div class="span9 col-md-9" id="sandbox-container">
						<div class="input-daterange input-group">
							<input type="text" class="form-control eventTime startTime"
								placeholder="<spring:message code="home.new.from.suggest"/>">
							<input id="startTime" type="hidden"> <span
								class="input-group-addon"><spring:message
									code="home.new.to" /></span> <input type="text"
								class="form-control eventTime endTime"
								placeholder="<spring:message code="home.new.to.suggest"/>">
							<input id="endTime" type="hidden">
						</div>
					</div>
				</div>

				<div id="contentGroup" class="form-group hide">
					<label for="information" class="col-sm-3 control-label"><spring:message
							code="home.new.content" /></label>
					<div class="col-sm-9">
						<textarea rows="3" name="information" class="form-control"
							id="information"
							placeholder="<spring:message code="home.new.content.suggest"/>"></textarea>
					</div>
				</div>

				<div id="sizeGroup" class="form-group hide">
					<label for="size" class="col-sm-3 control-label"><spring:message
							code="home.new.size" /></label>
					<div class="col-sm-9">
						<input type="text" name="size" class="form-control" id="size"
							placeholder="<spring:message code="home.new.size.suggest"/>" />
					</div>
				</div>

				<div class="form-group" style="display: none">
					<label for="title">Thuộc cộng đồng</label> <input
						class="form-control" id="communityCode" name="communityCode"
						id="vn"></input>
				</div>

				<div id="openTimeInput" class="form-group hide">
					<label for="openTime" class="col-sm-3 control-label"><spring:message
							code="home.new.time" /></label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="openTime"
							name="openTime"
							placeholder="<spring:message code="home.new.time.suggest"/>">
					</div>
				</div>

				<div id="telephoneGroup" class="form-group hide">
					<label for="telephone" class="col-sm-3 control-label"><spring:message
							code="home.new.telephone" /></label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="telephone"
							name="telephone"
							placeholder="<spring:message code="home.new.telephone.suggest"/>">
					</div>
				</div>

				<div id="emailGroup" class="form-group hide">
					<label for="email" class="col-sm-3 control-label"><spring:message
							code="home.new.email" /></label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="email" name="email"
							placeholder="<spring:message code="home.new.email.suggest"/>">
					</div>
				</div>

				<div id="referenceUrlGroup" class="form-group hide">
					<label for="title" class="col-sm-3 control-label"><spring:message
							code="home.new.homepage" /></label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="referenceUrl"
							name="referenceUrl"
							placeholder="<spring:message code="home.new.homepage.suggest"/>">
					</div>
				</div>

				<!-- 
  <div class="checkbox">
    <label>
      <input type="checkbox"> Chỉ hiển thị cho tôi
    </label>
  </div>
    -->
				<!-- User's information -->
				<input type="hidden" id="userId" name="userId" value="34"> <input
					type="hidden" id="id" name="id">

				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<button id="newPlaceFormSubmit" type="submit"
							class="btn btn-default col-sm-9">
							<spring:message code="home.new.send" />
						</button>
					</div>
				</div>

				<hr>
			</form:form>
		</div>
	</div>
</div>