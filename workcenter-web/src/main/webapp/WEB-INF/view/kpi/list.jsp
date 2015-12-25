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
				<a class="navbar-brand" href="#">kpi系统</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">

				<ul class="nav navbar-nav navbar-right">

					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">王林 <span class="caret"></span></a>
						<ul class="dropdown-menu">

							<!-- <li><a href="#">修改密码</a></li> -->
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
				<h1>欢迎使用,kpi系统</h1>
			</div>
		</div>




		<div class="panel panel-default">
			<div class="panel-heading">&nbsp;考核列表</div>
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>年度</th>
						<th>备注</th>
						<th>待考核人</th>
						<th>审核人</th>
						<th>等级</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${kpiList}" var="kpi">
						
						
					</c:forEach>
					<tr>
						<th scope="row">1</th>
						<td>2015</td>
						<td>年末考评</td>
						<td>王林</td>
						<td>刘宝剑</td>
						<td>未评出</td>
						<td>起草</td>
						<td><button id="write" type="button" class="btn btn-sm btn-default">起草</button></td>
					</tr>
					<tr>
						<th scope="row">2</th>
						<td>2015</td>
						<td>年中考评</td>
						<td>王林</td>
						<td>刘宝剑</td>
						<td>A</td>
						<td>完成</td>
						<td><button id="view" type="button" class="btn btn-sm btn-default">查看</button></td>
					</tr>
				</tbody>
			</table>
		</div>

	</div>
	<!-- /container -->

	<script src="<%=basePath%>/js/jquery.min.js"></script>
	<script src="<%=basePath%>/dist/js/bootstrap.min.js"></script>

	<script>
		$(document).ready(function() {
			$("#write").on("click", function() {
				window.location.href = './4write_task.html';
			});
			$("#view").on("click", function() {
				window.location.href = './9viewkpi.html';
			});
		})
	</script>
</body>
</html>
