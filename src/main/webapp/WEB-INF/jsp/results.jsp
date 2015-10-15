<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="firstUrl" value="${path}1" />
<c:url var="lastUrl" value="${path}${totalPages}" />
<c:url var="prevUrl" value="${path}${currentIndex - 1}" />
<c:url var="nextUrl" value="${path}${currentIndex + 1}" />

	<div class="container-fluid" id="main">
		<div class="row">
			<div id="left" class="col-xs-5">
				<br>
				
					<div class="">
								
				      <ul class="pagination pagination-smaller " style="margin: 0px 0;">
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
				
					  <p id="newplace" class="newplace addbutton2" >+</p>
				</div>

				<!-- item list -->
				<c:forEach var="item" items="${ items }" varStatus="status">
				<div id="item${status.index}" class="item " >
				 	<button type="button" data-id="${item.id}" class="edit" title="<spring:message code="home.result.item.edit"/>">

								<span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 
					</button>
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
						<span style="color: #ddd;">${(currentIndex-1)*20 + status.index + 1}.</span>
						<strong>
						 <a href="/place/${item.titleWithoutAccents}/${item.id}" target="_blank"> ${item.title}</a>
						</strong>
					</div>
					<div>
						<img class="img-rounded photo_item" src="${item.imagePath}" alt="">
					</div>	
					
					<div class="item_content" id="${status.index}" data-lat="${item.latitude}" data-address="${item.address}"  
					data-id="${item.id}" data-type="${item.placeType}" data-icon="${item.iconPath}"
						data-lng="${item.longitude}" data-title="${item.title}"	data-img="${item.imagePath}">
						
						<article class="hide">
							
							${item.information}
						</article>  
	
				        <table>
				            <tr>
				                <td nowrap><span class="glyphicon glyphicon-home"></span></td>
				                <td><span>${item.address}</span>  <%--<img alt="" src="/img/flags/${item.country}.png">--%></td> 
				            </tr>
				            <c:if test="${ not empty item.openTime }">	
					            <tr>
					                <td><span class="glyphicon glyphicon-time"></span></td>
					                <td><span>${item.openTime}</span></td>
					            </tr>
					        </c:if>
				           
					            <tr>
					                <td nowrap><span class="glyphicon glyphicon-phone-alt"> </span></td>
					                <td><span>${item.telephone}</span></td>
					            </tr>	
					      
				            		
					            <tr>
					                <td nowrap><span class="glyphicon glyphicon-envelope"></span></td>
					                <td><span>${item.email}</span></td>
					            </tr>	
					        
				         		
					            <tr class="hide">
					                <td nowrap><span><spring:message code="home.result.item.homepage"/>: </span></td>
					                <td><span><a href="${item.referenceUrl}">${item.referenceUrl}</a></span></td>
					            </tr>	
					     		            	            			            
				        </table>
				        
					</div>
					
					<%--<img  class="img-circle button_item" alt="facebook" src="/img/user.png"> --%>
					<span class="comment_item"><spring:message code="home.result.item.postedby"/> ${item.createdFromIp} - ${item.updatedDate}</span>
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
				 <hr>
				<nav class="text-center">
				      <ul class="pagination pagination-sm" style="margin: 0px 0;">
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
				<hr>

			</div>
			
			<div id="map-canvas" class="col-xs-7">
				<!--map-canvas will be postioned here-->
			</div>
			<%--
				 <div class="col-xs-4 open" id="sidebar"  style="height:400px;border:2px solid blue">
				 	Hey There, I'm a 1/4 width Div
				 </div>
				  --%>
		</div>
	</div>