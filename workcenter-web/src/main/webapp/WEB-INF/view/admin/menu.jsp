<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 左侧列表  -->
<div class="col-sm-3 col-md-2 sidebar">

	<c:forEach items="${menus}" var="catagory">
		<ul class="nav nav-sidebar">
			<c:forEach items="${catagory.subMenus}" var="submenu">
				<li><a href="<%=basePath%>/${sid }/${submenu.url}">${submenu.menuName} </a></li>
			</c:forEach>
			<!-- <li class="active"><a href="#">Overview</a></li> -->
		</ul>
	</c:forEach>
	
</div>

