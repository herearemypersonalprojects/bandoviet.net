<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="container-fluid" id="main">
		<div class="row">
			<div class="col-xs-6" id="left">
			<br>
				<div id="textbox">
					  <p style="font-size: 20px;color:#007bb3;float: left;"><spring:message code="home.result.title" /></p>
					  <p class="newplace addbutton2" >+</p>
				</div>
				<br>	
				<br>
<form id = "newplaceform" style="display:none">
  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div>
  <div class="form-group">
    <label for="exampleInputFile">File input</label>
    <input type="file" id="exampleInputFile">
    <p class="help-block">Example block-level help text here.</p>
  </div>
  <div class="checkbox">
    <label>
      <input type="checkbox"> Check me out
    </label>
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form>
				<!-- item list -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="">Pho 14</a>
						<div style="float: right;  color:#007bb3;   margin: auto;">
							<img  class="img-circle" height="15" width="15" alt="facebook" src="img/love.jpg">
							<span style="font-size:10px;">Thich (23)</span>
							<img  class="img-circle" height="15" width="15" alt="facebook" src="img/comment.png">
							<span style="font-size:10px;">Binh luan (123)</span>
							<img  class="img-circle" height="15" width="15" alt="facebook" src="img/facebook.jpg">
							<span style="font-size:10px;">Chia se (3)</span>
						</div>
						
					</div>
				</div>
				<p>    
					Thong tin chung: La quan pho duoc nhieu nguoi biet den o Paris. Khong chi khach la nguoi Viet
					ma nguoi Phap cung rat chuong mon Pho dac trung cua Viet Nam.
				    <fieldset>
				        <table>
				            <tr>
				                <td><span style="color: ">Dia chi: </span></td>
				                <td><span>9361 Bolsa Ave. Ste 108 Westminster, CA 92683</span></td>
				            </tr>
				            <tr>
				                <td><span>Dien thoai: </span></td>
				                <td><span>(714) 775-3724</span></td>
				            </tr>	
				            <tr>
				                <td><span>Gio mo cua: </span></td>
				                <td><span>Tu thu hai den thu bay</span></td>
				            </tr>					            			            
				        </table>
            		</fieldset>
				</p>
				<img  class="img-circle" height="15" width="15" alt="facebook" src="img/user.png">
				<span style="font-size:10px;">
					Dang boi 12.183.13.4.	
				</span>
				<img  class="img-circle" height="15" width="15" alt="facebook" src="img/like.jpg">
				<span style="font-size:10px;">Cam on (23)</span>
				<img  class="img-circle" height="15" width="15" alt="facebook" src="img/edit.png">
				<span style="font-size:10px;">
					Sua thong tin
				</span>
				<hr>

				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="">Banh cuon mon an ngon thuye</a>
					
						<div style="float: right;  color:#007bb3;   margin: auto;">
							<img  class="img-circle" height="15" width="15" alt="facebook" src="img/love.jpg">
							<span style="font-size:10px;">Thich (23)</span>
							<img  class="img-circle" height="15" width="15" alt="facebook" src="img/comment.png">
							<span style="font-size:10px;">Binh luan (123)</span>
							<img  class="img-circle" height="15" width="15" alt="facebook" src="img/facebook.jpg">
							<span style="font-size:10px;">Chia se (3)</span>
						</div>		
					</div>			
				</div>
				<p>    
					Thong tin chung: La quan pho duoc nhieu nguoi biet den o Paris. Khong chi khach la nguoi Viet
					ma nguoi Phap cung rat chuong mon Pho dac trung cua Viet Nam.
				    <fieldset>
				        <table>
				            <tr>
				                <td><span style="color: ">Dia chi: </span></td>
				                <td><span>9361 Bolsa Ave. Ste 108 Westminster, CA 92683</span></td>
				            </tr>
				            <tr>
				                <td><span>Dien thoai: </span></td>
				                <td><span>(714) 775-3724</span></td>
				            </tr>	
				            <tr>
				                <td><span>Gio mo cua: </span></td>
				                <td><span>Tu thu hai den thu bay</span></td>
				            </tr>					            			            
				        </table>
            		</fieldset>
				</p>
				<img  class="img-circle" height="15" width="15" alt="facebook" src="img/user.png">
				<span style="font-size:10px;">
					Dang boi 12.183.13.4.	
				</span>
				<img  class="img-circle" height="15" width="15" alt="facebook" src="img/like.jpg">
				<span style="font-size:10px;">Cam on (23)</span>
				<img  class="img-circle" height="15" width="15" alt="facebook" src="img/edit.png">
				<span style="font-size:10px;">
					Sua thong tin
				</span>
				<hr>

				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="">Cha ca La Vong</a>
						<div style="float: right;  color:#007bb3;   margin: auto;">
							<img  class="img-circle" height="15" width="15" alt="facebook" src="img/love.jpg">
							<span style="font-size:10px;">Thich (23)</span>
							<img  class="img-circle" height="15" width="15" alt="facebook" src="img/comment.png">
							<span style="font-size:10px;">Binh luan (123)</span>
							<img  class="img-circle" height="15" width="15" alt="facebook" src="img/facebook.jpg">
							<span style="font-size:10px;">Chia se (3)</span>
						</div>						
					</div>
				</div>
				<p>    
					Thong tin chung: La quan pho duoc nhieu nguoi biet den o Paris. Khong chi khach la nguoi Viet
					ma nguoi Phap cung rat chuong mon Pho dac trung cua Viet Nam.
				    <fieldset>
				        <table>
				            <tr>
				                <td><span style="color: ">Dia chi: </span></td>
				                <td><span>9361 Bolsa Ave. Ste 108 Westminster, CA 92683</span></td>
				            </tr>
				            <tr>
				                <td><span>Dien thoai: </span></td>
				                <td><span>(714) 775-3724</span></td>
				            </tr>	
				            <tr>
				                <td><span>Gio mo cua: </span></td>
				                <td><span>Tu thu hai den thu bay</span></td>
				            </tr>					            			            
				        </table>
            		</fieldset>
				</p>
				<img  class="img-circle" height="15" width="15" alt="facebook" src="img/user.png">
				<span style="font-size:10px;">
					Dang boi 12.183.13.4.	
				</span>
				<img  class="img-circle" height="15" width="15" alt="facebook" src="img/like.jpg">
				<span style="font-size:10px;">Cam on (23)</span>
				<img  class="img-circle" height="15" width="15" alt="facebook" src="img/edit.png">
				<span style="font-size:10px;">
					Sua thong tin
				</span>
				<hr>

				<div class="panel panel-default">
					<div class="panel-heading">
						<a id="123" href="">Item heading</a>
					</div>
				</div>
				<p>    
					Thong tin chung: La quan pho duoc nhieu nguoi biet den o Paris. Khong chi khach la nguoi Viet
					ma nguoi Phap cung rat chuong mon Pho dac trung cua Viet Nam.
				    <fieldset>
				        <table>
				            <tr>
				                <td><span style="color: ">Dia chi: </span></td>
				                <td><span>9361 Bolsa Ave. Ste 108 Westminster, CA 92683</span></td>
				            </tr>
				            <tr>
				                <td><span>Dien thoai: </span></td>
				                <td><span>(714) 775-3724</span></td>
				            </tr>	
				            <tr>
				                <td><span>Gio mo cua: </span></td>
				                <td><span>Tu thu hai den thu bay</span></td>
				            </tr>					            			            
				        </table>
            		</fieldset>
				</p>
				<img  class="img-circle" height="15" width="15" alt="facebook" src="img/user.png">
				<span style="font-size:10px;">
					Dang boi 12.183.13.4.	
				</span>
				<img  class="img-circle" height="15" width="15" alt="facebook" src="img/like.jpg">
				<span style="font-size:10px;">Cam on (23)</span>
				<img  class="img-circle" height="15" width="15" alt="facebook" src="img/edit.png">
				<span style="font-size:10px;">
					Sua thong tin
				</span>
				<hr>
				<!-- /item list -->

				<p>
					<a href="http://bootply.com" target="_ext"
						class="center-block btn btn-primary"><spring:message code="home.result.more" /></a>
				</p>

				<hr>

			</div>
			<div class="col-xs-4">
				<!--map-canvas will be postioned here-->
			</div>

		</div>
	</div>