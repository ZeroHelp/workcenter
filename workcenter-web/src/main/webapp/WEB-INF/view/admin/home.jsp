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
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">admin后台</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">

					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">王林 <span class="caret"></span></a>
						<ul class="dropdown-menu">

							<li><a href="#">修改密码</a></li>
							<li role="separator" class="divider"></li>
						</ul></li>
					<li><a href="1login.html">退出</a></li>
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

	<script src="<%=basePath%>/js/jquery.min.js"></script>
	<script src="<%=basePath%>/dist/js/bootstrap.min.js"></script>
</body>
</html>
