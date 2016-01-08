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
				<a class="navbar-brand" href="<%=basePath%>/${sid }/workcenter/home">workcenter系统</a><a class="navbar-brand" href="#">></a><a class="navbar-brand" href="<%=basePath%>/${sid }/kpi/home">KPI系统</a>
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
						<tr>
							<th scope="row">${kpi.index }</th>
							<td>${kpi.year }</td>
							<td>${kpi.remark }</td>
							<td>${kpi.waitAssessmentPerson }</td>
							<td>${kpi.assessmentPerson }</td>
							<td>${kpi.grade }</td>
							<td>${kpi.assessStatusName }</td>
							<td>
								<button type="button" 
									value1= "${kpi.mainId}" value2="${kpi.method}" value3="${kpi.taskinstanceId}" 
									class="get_btn btn btn-sm btn-default">${kpi.operator }</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
	<!-- /container -->

	<script src="<%=basePath%>/js/jquery.min.js"></script>
	<script src="<%=basePath%>/dist/js/bootstrap.min.js"></script>

	<script>
		$(document).ready(function() {
			$(".get_btn").on("click", function() {
				var main_id = $(this).attr("value1");
				var method = $(this).attr("value2");
				var taskinstanceId = $(this).attr("value3");
				window.location.href = '<%=basePath%>/${sid}/kpi/assessment/' + main_id + '?method=' + method;
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
	</script>
</body>
</html>
