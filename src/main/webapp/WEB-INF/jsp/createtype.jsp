<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  <!-- Trigger the modal with a button
  <button style="display:none" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myLocationAlert">Open Modal</button>
	
  <!-- Modal -->
  <div class="modal fade" id="form-createtype" role="dialog">
      <div class="modal-dialog">
      	<div class="modal-content">
		    <div class="modal-header">
       		   <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4>Tạo nhóm bản đồ mới</h4>
		    </div>
		    <div class="modal-body">
		        <form class="type" name="type" method="POST" action="/createtype" class="form-horizontal">
			      
			            <label class="control-label" for="name">Tên của nhóm bản đồ</label>
			            <div class="input-group" style=" width: 100%; ">
			                <input class="form-control" type="text" name="name" id="name" placeholder="đặt tên nhóm" required> 
			            </div>
					<br>
			            <label class="control-label" for="subject">Bảo mật</label>
			            <div class="input-group" style=" width: 100%; ">
			                <select id="securityLevel" name="securityLevel" required>
			                    <option value="1">Chỉ của riêng tôi </option>
			                    <option value="2">Có thể chia sẻ cho bạn bè</option>
			                    <option value="3" selected>Dữ liệu công khai</option>
			                </select>
			            </div>
			        <br>
		       		<div class="form-actions">
			            <button type="submit" class="btn btn-success">Gửi đi</button>
			        </div>
			    </form>
		    </div>
		 </div>
	 </div>
	 
</div>
