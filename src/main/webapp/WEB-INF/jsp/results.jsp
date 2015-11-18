<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url var="firstUrl" value="${path}1" />
<c:url var="lastUrl" value="${path}${totalPages}" />
<c:url var="prevUrl" value="${path}${currentIndex - 1}" />
<c:url var="nextUrl" value="${path}${currentIndex + 1}" />
	<div class="container-fluid" id="main">
		<div class="row">
			<%-- 
			<div style="    float: left;
			    display: inline-block;
			    width: 300px;
			    background-color: #e9e8e2;
			    border-color: white;
			    border-width: 1px;
			    border-style: solid;">
			    <div class="body locations"><div id="a" onclick="ta.plc_search_filters_0_handlers.filterSelected('a', this)" class="filter enabled selected"><span><img class="icon sprite-icon-filter-all" src="https://static.tacdn.com/img2/x.gif"></span><span class="text has_count">Tất cả kết quả </span> <span class="count">(197)</span></div><div class="filter  disabled"><span><img class="icon sprite-icon-filter-location" src="https://static.tacdn.com/img2/x.gif"></span><span class="text ">Địa điểm </span> <span class="count"></span></div><div id="h" onclick="ta.plc_search_filters_0_handlers.filterSelected('h', this)" class="filter enabled "><span><img class="icon sprite-icon-filter-hotels" src="https://static.tacdn.com/img2/x.gif"></span><span class="text has_count">Phòng nghỉ </span> <span class="count">(3)</span></div><div class="filter  disabled"><span><img class="icon sprite-icon-filter-VR" src="https://static.tacdn.com/img2/x.gif"></span><span class="text ">Nhà nghỉ cho thuê </span> <span class="count"></span></div><div id="e" onclick="ta.plc_search_filters_0_handlers.filterSelected('e', this)" class="filter enabled "><span><img class="icon sprite-icon-filter-restaurants" src="https://static.tacdn.com/img2/x.gif"></span><span class="text has_count">Nhà hàng </span> <span class="count">(194)</span></div><div class="filter  disabled"><span><img class="icon sprite-icon-filter-t2d" src="https://static.tacdn.com/img2/x.gif"></span><span class="text ">Điểm du lịch </span> <span class="count"></span></div></div>
			</div>
			--%>
			<div id="left" class="col-xs-6 result">
				<c:if test="${empty items}">
					<div style="color:#6e6e6e;">
						Không tìm thấy kết quả tương ứng nào.
						<a href="/create">Nhấn vào đây để thêm thông tin.</a>
					</div>
				</c:if>
			
			  <c:if test="${totalPages > 1}">
				<div style="color:#ddd;">
					Tìm được khoảng ${ (totalPages-1)*50} kết quả
				</div>
				
				
					<div class="text-center">
								
								
				      <ul class="pagination pagination-smaller ">
				      	<c:choose>
				            <c:when test="${currentIndex == 1}">
				                <li class="disabled"><a href="#">&lt;&lt;</a></li>
				                <li class="disabled"><a href="#">&lt;</a></li>
				            </c:when>
				            <c:otherwise>
				                <li><a href="${firstUrl}">&lt;&lt;</a></li>
				                <li><a href="${prevUrl}">&lt;</a></li>
				            </c:otherwise>
				        </c:choose>
				        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">				        
				            <c:url var="pageUrl" value="${path}${i}" />
				            <c:choose>
				                <c:when test="${i == currentIndex}">
				                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
				                </c:when>
				                <c:otherwise>
				                    <li><a href="${path}${i}"><c:out value="${i}" /></a></li>
				                </c:otherwise>
				            </c:choose>
				        </c:forEach>
				        <c:choose>
				            <c:when test="${currentIndex == totalPages}">
				                <li class="disabled"><a href="#">&gt;</a></li>
				                <li class="disabled"><a href="#">&gt;&gt;</a></li>
				            </c:when>
				            <c:otherwise>
				                <li><a href="${nextUrl}">&gt;</a></li>
				                <li><a href="${lastUrl}">&gt;&gt;</a></li>
				            </c:otherwise>
				        </c:choose>
				     </ul>
					 
					  <p id="newplace" class="newplace addbutton2" >+</p> 
				</div>
				</c:if>
				<!-- item list -->
				<c:forEach var="item" items="${ items }" varStatus="status">
				<div id="item${status.index}" class="item " >
				 	<button type="button" data-id="${item.id}" class="edit" title="<spring:message code="home.result.item.edit"/>">

								<span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 
					</button>
					<sec:authorize access="hasRole('SUPERADMIN')">
						<span style="float: right"><a href="/delete/${item.id}">Xóa</a></span>
					</sec:authorize>
					<%--
					<div class="panel panel-default">
						<div class="panel-heading">
						
							<a href="/place/${item.titleWithoutAccents}/${item.id}" target="_blank">${item.title}</a>
							
							
						
							<div style="float: right;  margin: auto;">
												
							
								<span data-id="${item.id}" class="comment_item glyphicon glyphicon-pencil edit" aria-hidden="true" title="<spring:message code="home.result.item.edit"/>">
									<spring:message code="home.result.item.edit"/>
								</span> 
								
								<!-- 							
								<img  class="/img-circle button_item" alt="facebook" src="/img/like.jpg">
								<span class="comment_item"><spring:message code="home.result.item.like"/> (23)</span>
								<img  class="/img-circle button_item"  alt="facebook" src="/img/comment.png">
								<span class="comment_item"><spring:message code="home.result.item.comment"/> (123)</span>
								<img  class="/img-circle button_item" alt="facebook" src="/img/facebook.jpg">
								<span class="comment_item"><spring:message code="home.result.item.share"/> (3)</span>
								 -->
							</div>
							
						</div>
					</div>	
					 --%>
					<div>
						<span style="color: #428bca;">${(currentIndex-1)*50 + status.index + 1}. </span>
						<strong>
						 <a style="color:#116c9e;" href="/place/${item.titleWithoutAccents}/${item.id}" target="_blank"> ${item.title}</a>						 
						</strong>
						
					</div>
					<div>
						
					</div>	
					
					<div class="item_content" id="${status.index}" data-lat="${item.latitude}" data-address="${item.address}"  
					data-id="${item.id}" data-type="${item.placeType}" data-icon="${item.iconPath}"
						data-lng="${item.longitude}" data-title="${item.title}"	data-img="${item.imagePath}">
						
				        <article>
				       		 <img class="img-rounded photo_item" src="${item.imagePath}" alt="${item.title}">
				       		 <c:if test="${not empty item.address}"> 
				       			 <spring:message code="home.result.item.address"/> ${item.address}<br>
				       		 </c:if>	 
				       		<c:if test="${not empty item.distance}">
				       		 Khoảng cách: 
				       		 <fmt:formatNumber type="number" pattern="######" value="${item.distance}" /> m
				       		 <br>	
				       		 </c:if>
				       		 Chủ đề: <spring:message	code="${item.placeType }" />
				       		 <br>
				       		 <c:if test="${false}">
				       		 	<spring:message code="home.result.item.email"/>: ${item.email} <br>
				       		 </c:if>
				       		 <c:if test="${not empty item.telephone}">
				       	  	 	<spring:message code="home.result.item.telephone"/>: ${item.telephone} <br>
				       		 </c:if>
				       		 <br>${item.information}<br>
				       		 <c:if test="${not empty item.referenceUrl}">
				       		 	<spring:message code="home.result.item.homepage"/>: ${item.referenceUrl}<br>
							 </c:if>
							
						</article>  
					</div>
					
					<%--<img  class="img-circle button_item" alt="facebook" src="/img/user.png"> --%>
					<%--
					<span class="comment_item"><spring:message code="home.result.item.postedby"/> ${item.createdFromIp} - ${item.updatedDate}</span>
					--%>
					<!-- 
					<img  class="/img-circle button_item" alt="facebook" src="/img/thank.jpg">
					<span class="comment_item"><spring:message code="home.result.item.thank"/> (23)</span>
					 
					<img  class="/img-circle button_item" alt="facebook" src="/img/edit.png">
					<span class="comment_item edit"><spring:message code="home.result.item.edit"/></span>
					-->
					
				</div>				
				</c:forEach>
				<!-- end item list -->
				<%--
				<p id="scroll">
					<a href="/search?keywords=Việt Nam" target="_ext"
						class="center-block btn btn-primary"><spring:message code="home.result.more" /></a>
				</p>
				 --%>
				 <c:if test="${totalPages > 1}">
				 
				 
				<nav class="text-center">
				      <ul class="pagination pagination-sm" style="margin: 10px 0;">
				      	<c:choose>
				            <c:when test="${currentIndex == 1}">
				                <li class="disabled"><a href="#">&lt;&lt;</a></li>
				                <li class="disabled"><a href="#">&lt;</a></li>
				            </c:when>
				            <c:otherwise>
				                <li><a href="${firstUrl}">&lt;&lt;</a></li>
				                <li><a href="${prevUrl}">&lt;</a></li>
				            </c:otherwise>
				        </c:choose>
				        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
				            <c:url var="pageUrl" value="${path}${i}" />				            
				            <c:choose>
				                <c:when test="${i == currentIndex}">
				                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
				                </c:when>
				                <c:otherwise>
				                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
				                </c:otherwise>
				            </c:choose>
				        </c:forEach>
				        <c:choose>
				            <c:when test="${currentIndex == totalPages}">
				                <li class="disabled"><a href="#">&gt;</a></li>
				                <li class="disabled"><a href="#">&gt;&gt;</a></li>
				            </c:when>
				            <c:otherwise>
				                <li><a href="${nextUrl}">&gt;</a></li>
				                <li><a href="${lastUrl}">&gt;&gt;</a></li>
				            </c:otherwise>
				        </c:choose>
				     </ul>
				 </nav>
				
				</c:if>
			</div>
			
			<div id="map-canvas" class="col-xs-6" style="width: 50.6%;">
				<!--map-canvas will be postioned here-->
			</div>
			<%--
				 <div class="col-xs-4 open" id="sidebar"  style="height:400px;border:2px solid blue">
				 	Hey There, I'm a 1/4 width Div
				 </div>
				  --%>
		</div>
	</div>
	
	
<%-- 	
<SELECT count(*), ( 3959 * acos( cos( radians(48.8365132 ) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(2.3486268999999993 ) ) + sin( radians(48.8365132 ) ) * sin( radians( latitude ) ) ) ) AS distance FROM place WHERE country like 'FR'  and MATCH(title) AGAINST('viet nam') HAVING distance < 50;
SELECT ( 3959 * acos( cos( radians(48.8365132 ) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(2.3486268999999993 ) ) + sin( radians(48.8365132 ) ) * sin( radians( latitude ) ) ) ) AS distance FROM place WHERE country like 'FR' and MATCH(title) AGAINST('viet nam') HAVING distance < 50;
SELECT ceil(count(*)/50) FROM place WHERE country like 'FR' and MATCH(title) AGAINST('viet nam') and ( 3959 * acos( cos( radians(48.8365132 ) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(2.3486268999999993 ) ) + sin( radians(48.8365132 ) ) * sin( radians( latitude ) ) ) ) < 50;
--%>
