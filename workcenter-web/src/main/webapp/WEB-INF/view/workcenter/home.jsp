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
<link rel="icon" href="./favicon.ico">
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
				<a class="navbar-brand" href="#">Workcenter</a>
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
			<c:forEach var="component" items="${modules}">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<p>&nbsp;</p>
						<img src="<%=basePath%>/img/icons/png/Infinity-Loop.png" alt="Infinity-Loop" class="tile-image big-illustration">
						<div class="caption">
							<div class="span7 text-center">
								<h3>${component.resourceName }</h3>
							</div>
							<div class="span7 text-center">
								<p>
									<a  href="#" class="component btn btn-primary" value1="${component.resourceUrl }" role="button">进入</a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- /container -->

	<script src="<%=basePath%>/js/jquery.min.js"></script>
	<script src="<%=basePath%>/dist/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			$(".component").on("click", function() {
				var url = $(this).attr("value1");
				window.location.href = '<%=basePath%>/'+ url;
			})
		})
	</script>
</body>
</html>
