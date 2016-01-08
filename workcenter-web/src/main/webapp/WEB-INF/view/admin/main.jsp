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
<!-- Bootstrap core CSS -->
<link href="<%=basePath%>/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>/css/dashboard.css" rel="stylesheet">
<link rel="stylesheet" href="<%=basePath%>/css/ztree/zTreeStyle.css" type="text/css">

<script src="<%=basePath%>/js/jquery.min.js"></script>
<script src="<%=basePath%>/dist/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/js/jquery.form.js"></script>
<script src="<%=basePath%>/js/jquery.table.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.ztree.all-3.5.min.js"></script>

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=basePath%>/${sid }/workcenter/home">workcenter系统</a><a class="navbar-brand" href="#">></a><a class="navbar-brand" href="<%=basePath%>/${sid }/admin/home">admin后台系统</a>
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
		</div>
	</nav>

	<div class="container-fluid">
	
		<div class="row">
			<!-- 左侧列表  -->
			<jsp:include page="menu.jsp"></jsp:include>
			
			<!-- 右侧列表  -->
			
			<jsp:include page="${viewPage }"></jsp:include>
			
		</div>
	</div>
	<script>
		$(document).ready(function() {
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
	</script>
</body>
</html>
