<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form class="form-horizontal" id = "newplaceform" style="display:block" method="POST" enctype="multipart/form-data">
  <div class="form-group">
    <label for="title" class="col-sm-3 control-label">Tên (*)</label>
     <div class="col-sm-9">
  	   <input type="text" class="form-control" id="title" name="title" placeholder="Xin vui lòng nhập tên thông tin">
 	 </div>
  </div>
  <div class="form-group">
    <label for="address" class="col-sm-3 control-label" >Địa chỉ (*)</label>
    <div class="col-sm-9">
    	<input type="text" class="form-control" id="address" name="address" placeholder="Number, route, city, country">
    </div>              
    <input type="hidden" id="street_number" name="streetNumber"/>
    <input type="hidden" id="route" name="route">

    <input type="hidden" id="postal_code" name="postalCode">

    <input type="hidden" id="latitude" name="latitude" value="30">
    <input type="hidden" id="longitude" name="longitude" value="34">
  </div>
  
  <div class="form-group" style="display:none">
  	  <input type="text" id="locality" name="city" size="10" readonly style="color: darkgray;">
      <input type="text" id="country" name="country" size="14" readonly style="color: darkgray;">
  </div>
 
  <div class="form-group">
  	<label for="title" class="col-sm-3 control-label">Phân loại</label>
  	<div class="col-sm-9">
  		<select name="placeType" class="form-control" id="placeType"></select>
  	</div>	  	
  </div>
   
  <div class="form-group">
  	<label for="information" class="col-sm-3 control-label">Thông tin chung</label>
  	<div class="col-sm-9">
  		<textarea rows="3" name="information" class="form-control" id="information" placeholder="Nhập thông tin chi tiết"></textarea>
  	</div>	                                
  </div>
 	
  <div class="form-group" style="display:none">
  	<label for="title">Thuộc cộng đồng</label>
  	<input class="form-control" id="communityCode" name="communityCode" id="vn"></input>  	
  </div>
  
  <div class="form-group">
    <label for="telephone" class="col-sm-3 control-label">Điện thoại</label>
    <div class="col-sm-9">
    	<input type="text" class="form-control" id="telephone" name="telephone" placeholder="Số điện thoại">
    </div>
  </div>
  
    <div class="form-group">
    <label for="email" class="col-sm-3 control-label">Địa chỉ email</label>
    <div class="col-sm-9">
    	<input type="text" class="form-control" id="email" name="email" placeholder="Địa chỉ email">
    </div>	
  </div>
  
    <div class="form-group">
    <label for="title" class="col-sm-3 control-label">Thời gian </label>
    <div class="col-sm-9">
    	<input type="text" class="form-control" id="openTime" name="openTime" placeholder="Từ thời gian nào đến thời gian nào?">
    </div>	
  </div>
    
  <div class="form-group">
    <label for="title" class="col-sm-3 control-label">Trang chủ</label>
    <div class="col-sm-9">
    	<input type="text" class="form-control" id="referenceUrl" name="referenceUrl" placeholder="Đường dẫn đến trang web">
    </div>	
  </div>
  
  <div class="form-group">
    <label for="file" class="col-sm-3 control-label">Hình ảnh</label>
    <div class="col-sm-9">
    	<input type="file" id="file">
    </div>	
    <!-- <p class="help-block">Các thông tin có dấu (*) là bắt buộc phải điền.</p> -->
  </div>
  <!-- 
  <div class="checkbox">
    <label>
      <input type="checkbox"> Chỉ hiển thị cho tôi
    </label>
  </div>
    -->
  <!-- User's information -->
  <input type="hidden" id="userId" name="userId" value="34">
  <input type="hidden" id="id" name="id">

  <div class="form-group">
	  <div class="col-sm-offset-3 col-sm-9">
	  	<button id="newPlaceFormSubmit" type="submit" class="btn btn-default col-sm-9">Gửi đi</button>
	  </div>
  </div>	
  
  <hr>
</form>