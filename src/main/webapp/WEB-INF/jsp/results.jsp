<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<div class="container-fluid" id="main">
		<div class="row">
			<div id="results" class="col-xs-5" id="left">
				<br>
				<jsp:include page="newplaceform.jsp" />
				
				<div id="textbox">
					  <p style="font-size: 20px;color:#007bb3;float: left;">
					  	${fn:length(items)} <spring:message code="home.result.title" /> <c:if test="${ not empty keywords }">	"${keywords}" </c:if>
					  </p>
					  <p id="newplace" class="newplace addbutton2" >+</p>
				</div>
				<br>	
				<br>

				<!-- item list -->
				<c:forEach var="item" items="${ items }" varStatus="status">
				<div id="${status.index}" class="item_content" 
				data-lat="${item.latitude}" 
				data-lng="${item.longitude}"
				data-title="${item.title}"
				data-img="${item.imagePath}">
				
					<div class="panel panel-default">
						<div class="panel-heading">
							<a href="#" target="_blank">${item.title}</a>
							<div class="hide" style="float: right;  margin: auto;">
								<img  class="/img-circle button_item" alt="facebook" src="/img/like.jpg">
								<span class="comment_item"><spring:message code="home.result.item.like"/> (23)</span>
								<img  class="/img-circle button_item"  alt="facebook" src="/img/comment.png">
								<span class="comment_item"><spring:message code="home.result.item.comment"/> (123)</span>
								<img  class="/img-circle button_item" alt="facebook" src="/img/facebook.jpg">
								<span class="comment_item"><spring:message code="home.result.item.share"/> (3)</span>
							</div>
							
						</div>
					</div>	
					<img class="photo_item" src="img/test.jpg" alt="Item test">
					${item.information} ${item.imagePath}

			        <table>
			            <tr>
			                <td><span><spring:message code="home.result.item.address"/>: </span></td>
			                <td><span>${item.address}</span></td>
			            </tr>
			            <c:if test="${ not empty item.openTime }">	
				            <tr>
				                <td><span><spring:message code="home.result.item.time"/>: </span></td>
				                <td><span>${item.openTime}</span></td>
				            </tr>
				        </c:if>
			            <c:if test="${ not empty item.telephone }">	
				            <tr>
				                <td><span><spring:message code="home.result.item.telephone"/>: </span></td>
				                <td><span>${item.telephone}</span></td>
				            </tr>	
				        </c:if>
			            <c:if test="${ not empty item.email }">		
				            <tr>
				                <td><span><spring:message code="home.result.item.email"/>: </span></td>
				                <td><span>${item.email}</span></td>
				            </tr>	
				        </c:if>
			            <c:if test="${ not empty item.referenceUrl }">		
				            <tr>
				                <td><span><spring:message code="home.result.item.homepage"/>: </span></td>
				                <td><span>${item.referenceUrl}</span></td>
				            </tr>	
				        </c:if>			            	            			            
			        </table>
				
					<img  class="/img-circle button_item" alt="facebook" src="/img/user.png">
					<span class="comment_item"><spring:message code="home.result.item.postedby"/> ${item.updatedDate}</span>
					<!-- 
					<img  class="/img-circle button_item" alt="facebook" src="/img/thank.jpg">
					<span class="comment_item"><spring:message code="home.result.item.thank"/> (23)</span>
					 -->
					<img  class="/img-circle button_item" alt="facebook" src="/img/edit.png">
					<span class="comment_item"><spring:message code="home.result.item.edit"/></span>
					<hr>
				</div>				
				</c:forEach>
				<!-- end item list -->

				<p id="scroll">
					<a href="#" target="_ext"
						class="center-block btn btn-primary"><spring:message code="home.result.more" /></a>
				</p>

				<hr>

			</div>
			<div class="col-xs-4">
				<!--map-canvas will be postioned here-->
			</div>

		</div>
	</div>