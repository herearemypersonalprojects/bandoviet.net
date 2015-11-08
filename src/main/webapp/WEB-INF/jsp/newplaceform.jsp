<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="container-fluid" id="main">
	<div class="row">
		<div id="left" class="col-xs-8">
			<br>

			<form:form action="/save" modelAttribute="place" class="form-horizontal" id="newplaceform" method="POST" enctype="multipart/form-data">
				<form:errors path="*" cssClass="errorblock" element="div" />
				
				<form:input	path="id" type="hidden" id="id" name="id"/>
				<form:input	path="createdDate" type="hidden" id="createdDate" name="createdDate"/>
				<form:input	path="updatedDate" type="hidden" id="updatedDate" name="updatedDate"/>
				<form:input	path="createdFromIp" type="hidden" id="createdFromIp" name="createdFromIp"/>
				
				<div class="form-group">
					<form:label path="title" class="col-sm-3 control-label">
						<spring:message	code="home.new.name" />
					</form:label>
					<div class="col-sm-9">
						<spring:message code="home.new.name.suggest" var="suggest"/>
						<form:input class="form-control" id="title" name="title" 
						placeholder="${suggest}" path="title" title="${suggest}"/>						
						<form:errors path="title" cssClass="error"/>
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
						<form:errors path="address" />	
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
						<input name="image" type="file" id="image" class="form-control">						
					</div>
					<%--
					<c:if test="${ not empty place.id }">
						<form:input path="imagePath"  type="hidden" name="imagePath" id="imagePath"/>
					</c:if>
					--%>
					<form:input path="iconPath" type="hidden" name="iconPath" id="iconPath"/>
					<!-- <c:if test="${ not empty place.imagePath }">	<img class="photo_item" src="${place.imagePath}" alt="Photo"> </c:if>
					<p class="help-block">Các thông tin có dấu (*) là bắt buộc phải điền.</p> -->
				</div>
				<%--<c:if test="${ empty place.id }"> --%>
				<div class="form-group">
					<label for="image" class="col-sm-3 control-label">
						<spring:message	code="home.new.photo" /> URL
					</label>
					<div class="col-sm-9">
						<form:input path="imagePath" name="imagePath" id="imagePath" class="form-control" placeholder="Nhập đường link url từ Internet"/>
					</div>						
				</div>	
				<%--</c:if> --%>
				<div class="form-group">
					<label for="title" class="col-sm-3 control-label"><spring:message
							code="home.new.type" /></label>
					<div class="col-sm-9">
                		<form:select path="placeType" class="form-control" id="placeType" name="placeType">
                			<form:option value="" ><spring:message code="home.new.type.suggest"/></form:option>
							<c:forEach items="${typeList}" var="option">
									<form:option value="${option}" ><spring:message code="${option.code }"/></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
				
				<div id="eventTimeGroup" class="form-group hide">
					<label for="startDate" class="col-sm-3 control-label"><spring:message
							code="home.new.time" /></label>

					<div class="span9 col-md-9" id="sandbox-container">
						<div class="input-daterange input-group">
							<input type="text" class="form-control eventTime startTime"
								placeholder="<spring:message code="home.new.from.suggest"/>"/>
							<form:input path="startTime" name="startTime" id="startTime" type="hidden"/> 
								<span class="input-group-addon">
									<spring:message	code="home.new.to" />
								</span> 
							<input type="text"
								class="form-control eventTime endTime"
								placeholder="<spring:message code="home.new.to.suggest"/>">
							<form:input path="endTime" name="endTime" id="endTime" type="hidden"/>
						</div>
					</div>
				</div>

				<div id="contentGroup" class="form-group hide">
					<label for="information" class="col-sm-3 control-label"><spring:message
							code="home.new.content" /></label>
					<div class="col-sm-9">
						<spring:message code="home.new.content.suggest" var="suggest"/>
						<form:textarea path="information" name="information" class="form-control"
							id="information"
							placeholder="${suggest }"></form:textarea>
					</div>
				</div>
				<div id="organisedByGroup" class="form-group hide">
					<label for="organisedBy" class="col-sm-3 control-label">
						<spring:message	code="home.new.organisedBy" />
					</label>
					<div class="col-sm-9">
						<spring:message code="home.new.organisedBy.suggest" var="suggest"/>
						<form:input path="organisedBy" name="organisedBy" class="form-control" id="organisedBy"
							placeholder="${suggest }" />
					</div>
				</div>
				<div id="sizeGroup" class="form-group hide">
					<label for="size" class="col-sm-3 control-label"><spring:message
							code="home.new.size" /></label>
					<div class="col-sm-9">
						<spring:message code="home.new.size.suggest" var="suggest"/>
						<form:input path="size" name="size" class="form-control" id="size"
							placeholder="${suggest }" />
					</div>
				</div>
				
				<div class="form-group" style="display: none">
					<label for="title">Thuộc cộng đồng</label> 
					<form:input path="communityCode"	class="form-control" id="communityCode" name="communityCode"/>
				</div>

				<div id="openTimeInput" class="form-group hide">
					<label for="openTime" class="col-sm-3 control-label"><spring:message
							code="home.new.time" /></label>
					<div class="col-sm-9">
						<spring:message code="home.new.time.suggest" var="suggest"/>
						<form:input path="openTime" class="form-control" id="openTime"	name="openTime"
							placeholder="${suggest }"/>
					</div>
				</div>

				<div id="telephoneGroup" class="form-group hide">
					<label for="telephone" class="col-sm-3 control-label"><spring:message
							code="home.new.telephone" /></label>
					<div class="col-sm-9">
						<spring:message code="home.new.telephone.suggest" var="suggest"/>
						<form:input path="telephone" class="form-control" id="telephone" name="telephone"
							placeholder="${suggest }"/>
					</div>
				</div>

				<div id="emailGroup" class="form-group hide">
					<label for="email" class="col-sm-3 control-label"><spring:message
							code="home.new.email" /></label>
					<div class="col-sm-9">
						<spring:message code="home.new.email.suggest" var="suggest"/>
						<form:input path="email" class="form-control" id="email" name="email"
							placeholder="${suggest }"/>
					</div>
				</div>

				<div id="referenceUrlGroup" class="form-group hide">
					<label for="title" class="col-sm-3 control-label">
						<spring:message	code="home.new.homepage" />
					</label>
					<div class="col-sm-9">
						<spring:message code="home.new.homepage.suggest" var="suggest"/>
						<form:input path="referenceUrl" class="form-control" id="referenceUrl"
							name="referenceUrl"	placeholder="${suggest }"/>
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
				<form:input path="createdByUserId" type="hidden" id="createdByUserId" name="createdByUserId" value="34"/> 

				<div class="form-group" >					
					<div class="col-sm-offset-3 col-sm-9 options btn-group">

						<button id="newPlaceFormSubmit" type="submit" class="btn btn-primary col-sm-9">
							<spring:message code="home.new.send" />
						</button>
						
						<button <c:if test="${ not empty place.placeType }">
						onClick="window.location.href='/category?type=${place.placeType}'"</c:if> id="cancel" type="button" class="btn btn-default">
							Hủy bỏ
						</button>
					</div>		    
					  
				</div>

				<hr>
			</form:form>
		</div>
		
			<div id="map-canvas" class="col-xs-4">
				<!--map-canvas will be postioned here-->
			</div>
	</div>
</div>