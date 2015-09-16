<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <!-- Trigger the modal with a button
  <button style="display:none" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myLocationAlert">Open Modal</button>
	
  <!-- Modal -->
  <div class="modal fade" id="myLocationAlert" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"><spring:message code="application.alert" /></h4>
        </div>
        <div class="modal-body">
          <p><spring:message code="home.map.mylocationalert" /></p>
          <p id = "myLocationAlertcity"></p>
          <p id = "myLocationAlertregion"></p> 
          <p id = "myLocationAlertcountry"></p> 
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="application.close" /></button>
        </div>
      </div>
      
    </div>
  </div>