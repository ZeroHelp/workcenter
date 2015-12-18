<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="<%=basePath%>/img/logo.ico">
<title>宜信财富在线</title>
<link href="<%=basePath%>/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>/css/signin.css" rel="stylesheet">
</head>

<body>

	<div class="container">
		<form class="form-signin" action="<%=basePath%>/workcenter/sid" method="post">
			<div class="span7 text-center">
				<h2 class="form-signin-heading">WorkCenter</h2>
			</div>
			<div class="col-sm-12">
				<br>
			</div>
			<!-- 空行 -->
			<label for="inputEmail" class="sr-only">Email prefix</label> <input type="text" id="username" class="form-control" placeholder="Email prefix" autofocus> <br> <label for="inputPassword" class="sr-only">Password</label> <input type="password" id="password" class="form-control" placeholder="Password">
			<div class="col-sm-12">
				<br>
			</div>
			<!-- 空行 -->
			<!--<div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>-->
			<button id="login" class="btn btn-lg btn-primary btn-block" type="button">登录</button>
		</form>

	</div>
	<!-- /container -->

	<!-- dialog -->
	<div class="modal fade" id="myModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="model_title">model_title</h4>
	      </div>
	      <div class="modal-body">
	        <p id="model_content">model_content</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<script src="<%=basePath%>/js/jquery.min.js"></script>
	<script src="<%=basePath%>/dist/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/js/md5.js"></script>
	<script type="text/javascript">
		//回车按键登录
		//document.getElementById('shadow_box').onkeydown=keyDownSearch;  
		document.onkeydown = keyDownSearch;
		function keyDownSearch(e) {
			// 兼容FF和IE和Opera    
			var theEvent = e || window.event;
			var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
			if (code == 13) {
				//alert('回车');//具体处理函数    
				dologin();
				return false;
			}
			return true;
		}
		$(document).ready(function() {
			$("#login").on("click", function() {
				dologin();
			})
		})
		
		function dologin() {
			var username = $("#username").val();
			var password = $("#password").val();
			
			username = username.replace(/(^\s*)|(\s*$)/g, "");
			password = password.replace(/(^\s*)|(\s*$)/g, "");
			
			if(isEmpty(username)) {
				$("#model_title").text("*警告");
				$("#model_content").text("*用户名为空");
				$('#myModal').modal();
				return ;
			} 
			if(isEmpty(password)) {
				$("#model_title").text("*警告");
				$("#model_content").text("*密码为空");
				$('#myModal').modal();
				return ;
			} 
				
			password = hex_md5(password);
			$.ajax({
				type: "post",
				url: '<%=basePath%>/workcenter/sid',
				data : {
					username : username,
					password : password,
				},
				dataType : "json",
				success : function(data) {
					if (data.returncode == "200") {
						var sid = data.data;
						window.location.href = '<%=basePath%>/' + sid+ '/workcenter/home';
					} else {
						$("#model_title").text(data.returnmsg);
						$("#model_content").text(data.returnmemo);
						$('#myModal').modal();
					}
				}
			});
		}
		function isEmpty(obj) {
			if (obj == null || obj == '') {
				return true;
			}
			return false;
		}
	</script>
</body>
</html>
