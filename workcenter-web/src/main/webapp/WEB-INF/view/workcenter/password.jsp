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
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="<%=basePath%>/img/logo.ico">
<title>宜信财富在线</title>
<link href="<%=basePath%>/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>/css/navbar-fixed-top.css" rel="stylesheet">
</head>

<body>

	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=basePath%>/${sid }/workcenter/home">workcenter系统</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">

				<ul class="nav navbar-nav navbar-right">

					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${username}<span class="caret"></span></a>
						<ul class="dropdown-menu">

							<li><a id="changepassword" href="javascript:void(0);">修改密码</a></li>
							<!-- <li role="separator" class="divider"></li> -->
						</ul></li>
					<li><a id="logout" href="javascript:void(0);">退出</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container">

		<!-- Main component for a primary marketing message or call to action -->
		<div class="jumbotron">
			<div class="span7 text-center">
				<h1>欢迎使用,workcenter</h1>
			</div>
		</div>
		
		<div class="row">
			<form id="passwordForm">
				<div class="input-group col-sm-6 col-sm-offset-3">
				  <span class="input-group-addon" id="sizing-addon2">旧密码</span>
				  <input id="oldPassword" name="oldPassword" type="text" class="form-control" placeholder="old password..." aria-describedby="sizing-addon2">
				</div>
				
				<div class="col-sm-12">
					<br>
				</div> <!-- 空行 -->
				
				<div class="input-group col-sm-6 col-sm-offset-3">
				  <span class="input-group-addon" id="sizing-addon2">新密码</span>
				  <input id="newPassword" name="newPassword" type="text" class="form-control" placeholder="new password..." aria-describedby="sizing-addon2">
				</div>
				
				<div class="col-sm-12">
					<br>
				</div> <!-- 空行 -->
				
				<div class="input-group col-sm-6 col-sm-offset-3">
				  <span class="input-group-addon" id="sizing-addon2">再次输入新密码</span>
				  <input id="confirmPassword" name="confirmPassword" type="text" class="form-control" placeholder="confirm password..." aria-describedby="sizing-addon2">
				</div>
				
				<div class="col-sm-12">
					<br>
				</div> <!-- 空行 -->
			</form>
			<div class="span7 text-center">
				<button id="forget_btn" type="button" class="btn btn-primary btn-lg">忘记旧密码</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="submit_btn" type="button" class="btn btn-primary btn-lg">确认提交</button>
			</div>
		</div>
		
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
	<script src="<%=basePath%>/js/jquery.form.js"></script>
	<script src="<%=basePath%>/js/md5.js"></script>
	
	<script>
		
		$(document).ready(function() {
			$("#forget_btn").on("click", function() {
				$("#model_title").text("建议");
				$("#model_content").text("请联系管理员重置密码");
				$('#myModal').modal();
			});
			$("#submit_btn").on("click", function() {
				
				var oldPassword = $("#oldPassword").val();
				var newPassword = $("#newPassword").val();
				var confirmPassword = $("#confirmPassword").val();
				
				oldPassword = oldPassword.replace(/(^\s*)|(\s*$)/g, "");
				newPassword = newPassword.replace(/(^\s*)|(\s*$)/g, "");
				confirmPassword = confirmPassword.replace(/(^\s*)|(\s*$)/g, "");
				
				if(isEmpty(oldPassword)) {
					$("#model_title").text("*警告");
					$("#model_content").text("*旧密码为空");
					$('#myModal').modal();
					return ;
				} 
				
				if(isEmpty(newPassword)) {
					$("#model_title").text("*警告");
					$("#model_content").text("*新密码为空");
					$('#myModal').modal();
					return ;
				} 
				
				if(isEmpty(confirmPassword)) {
					$("#model_title").text("*警告");
					$("#model_content").text("*确认新密码为空");
					$('#myModal').modal();
					return ;
				} 
				
				if(oldPassword == newPassword) {
					$("#model_title").text("*警告");
					$("#model_content").text("*新旧密码相同,不需要更改");
					$('#myModal').modal();
					return ;
				}
				
				if(newPassword != confirmPassword) {
					$("#model_title").text("*警告");
					$("#model_content").text("*确认新密码与输入新密码不同");
					$('#myModal').modal();
					return ;
				}
				
				oldPassword = hex_md5(oldPassword);
				newPassword = hex_md5(newPassword);
				confirmPassword = hex_md5(confirmPassword);
				
				var formParam = $("#passwordForm").formSerialize();
				$.ajax({
					type: "post",
					url: "<%=basePath%>/${sid}/workcenter/password",
					dataType: "json",
					data : {
						oldPassword: oldPassword,
						newPassword: newPassword,
						confirmPassword: confirmPassword,
					},
					success: function(data) {
						if(data.returncode=="200") {
							$("#model_title").text(data.returnmsg);
							$("#model_content").text(data.returnmemo);
							$('#myModal').modal();
						} else {
							$("#model_title").text(data.returnmsg);
							$("#model_content").text(data.returnmemo);
							$('#myModal').modal();
						}
					}
				});
				
			});
			$("#changepassword").on("click", function() {
				window.location.href = '<%=basePath%>/${sid}/workcenter/password';
			});
			
			$("#logout").on("click", function() {
				$.ajax({
					type: "post",
					url: '<%=basePath%>/workcenter/logout',
					data: {
						sid : '${sid}',
					},
					dataType: "json",
					success : function(data) {
						if(data.returncode == "200") {
							window.location.href = '<%=basePath%>/workcenter/index';
						} else {
							$("#model_title").text(data.returnmsg);
							$("#model_content").text(data.returnmemo);
							$('#myModal').modal();
						}
					}
				});
			});
		})
			
		function isEmpty(obj) {
			if (obj == null || obj == '') {
				return true;
			}
			return false;
		}
	</script>
</body>
</html>
