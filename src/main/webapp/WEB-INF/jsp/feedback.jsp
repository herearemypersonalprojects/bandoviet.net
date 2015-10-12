<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <!-- Trigger the modal with a button
  <button style="display:none" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myLocationAlert">Open Modal</button>
	
  <!-- Modal -->
  <div class="modal fade" id="form-feedback" role="dialog">
      <div class="modal-dialog">
      	<div class="modal-content">
		    <div class="modal-header">
       		   <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4>Viết cho chúng tôi/Feedback</h4>
		    </div>
		    <div class="modal-body">
		        <form class="contact" name="contact" method="POST" action="/feedback" class="form-horizontal">
			       <div class="control-group">
			            <label class="control-label" for="name">Tên của bạn/Your Name</label>
			            <div class="controls">
			                <input type="text" name="name" id="name" placeholder="Your name">
			            </div>
			        </div>
			        <div class="control-group">
			            <label class="control-label" for="email">Địa chỉ thư điện tử/Your Email Address</label>
			            <div class="controls">
			                <input type="text" name="email" id="email" placeholder="Your email address">
			            </div>
			        </div>
			        <div class="control-group">
			            <label class="control-label" for="subject">Chủ đề/Subject</label>
			            <div class="controls">
			                <select id="subject" name="subject">
			                    <option value="na" selected="">Chọn chủ đề/Choose One:</option>
			                    <option value="service">Thông báo lỗi/Feedback</option>
			                    <option value="suggestions">Gợi ý/Suggestion</option>
			                    <option value="support">Đặt câu hỏi/Question</option>
			                    <option value="other">Thông tin khác/Other</option>
			                </select>
			            </div>
			        </div>
			        <div class="control-group">
			            <label class="control-label" for="message">Nội dung/Message</label>
			            <div class="controls">
			                <textarea name="message" id="message" rows="8" class="span5 form-control" placeholder="The message you want to send to us."></textarea>
			            </div>
			        </div>
			        <div class="form-actions">
			            <input type="hidden" name="save" value="contact">
			            <button type="submit" class="btn btn-success">Gửi đi/Submit Message</button>
			            <button type="reset" class="btn">Viết lại/Reset</button> <%--data-dismiss="modal" --%>
			        </div>
			    </form>
		        <div id="thanks"></div>
		    </div>
		 </div>
	 </div>
	 
</div>
