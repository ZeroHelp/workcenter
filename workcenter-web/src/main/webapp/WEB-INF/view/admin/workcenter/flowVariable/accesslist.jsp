<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 右侧列表  -->
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<!-- <h1 class="page-header">Dashboard</h1>

	<h2 class="sub-header">Section title</h2> -->

	<div class="row">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="add">添加</button>
		<button id="edit_btn" type="button" class="btn btn-primary" >修改</button>
		<button value1="delete" type="button" class="delete_btn btn btn-primary" >删除</button>

		<div class="col-sm-3">
			<form id="queryForm">
				<div class="input-group">
					<div class="input-group-btn">
						<button id="queryLabel_btn" type="button" class="btn btn-default">${queryLabel_ch }</button>
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
						</button>
						<ul class="dropdown-menu">
							<li><a class="query_a" href="javascript:void(0);" value1="variablename">变量名称</a></li>
							<li><a class="query_a" href="javascript:void(0);" value1="nodeId">节点Id</a></li>
							<li><a class="query_a" href="javascript:void(0);" value1="variableinstanceId">流程Id</a></li>
							<li><a class="query_a" href="javascript:void(0);" value1="access">权限</a></li>											
						</ul>
					</div>
					
					<input id="query_input" type="text" name="${queryLabel_en }" value="${queryValue }" class="form-control" aria-label="Text input with segmented button dropdown">
					<span class="input-group-btn">
					<button id="query_btn" class="btn btn-default" type="button">查询</button>
					</span>
				</div>
				<!-- /.input-group -->
			</form>
		</div>

	</div>
	<!-- /.row -->

	<div class="table-responsive">
		<table id="listTable" class="table table-striped"  >
			<thead>
				<tr>
					<th>#</th>
					<th>id</th>
					<th>流程节点Id</th>
					<th>流程Id</th>
					<th>流程变量名称</th>
					<th>权限</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${flowVariableaccesss }" var="flowVariableaccess">
					<tr>
						<td><input type="checkbox" name="flowVariableaccessId" value1="${flowVariableaccess.id}" ></td>
						<td>${flowVariableaccess.id }</td>
						<td>${flowVariableaccess.nodeId }</td>
						<td>${flowVariableaccess.variableinstanceId }</td>
						<td>${flowVariableaccess.variablename }</td>
						<c:if test="${flowVariableaccess.access == '1' }">
							<td>可读</td>
						</c:if>
						<c:if test="${flowVariableaccess.access == '2' }">
							<td>可写</td>
						</c:if>
						<c:if test="${flowVariableaccess.access == '3' }">
							<td>可读写</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="back_btn close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">exampleModal_title</h4>
			</div>
			<div class="modal-body">
				<form id="userForm">
					<input id="id" name="id" type="hidden" >
					<div class="form-group">
						<label for="recipient-name" class="control-label">流程节点Id:</label> 
						<input id="nodeId" name="nodeId" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">流程Id:</label> 
						<input id="variableinstanceId" name="variableinstanceId" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">流程变量名称:</label> 
						<input id="variablename" name="variablename" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">状态:</label> 
						<select id="access" name="access" class="form-control">
						  <option value="1">可读</option>
						  <option value="2">可写</option>
						  <option value="3">可读写</option>
						</select>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="back_btn btn btn-default" data-dismiss="modal">关闭</button>
				<button id="model_submit_btn" value1="add" type="button" class="btn btn-primary">提交</button>
			</div>
		</div>
	</div>
</div>

<!-- dialog -->
<div class="modal fade" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="back_btn close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="model_title">model_title</h4>
			</div>
			<div class="modal-body">
				<p id="model_content">model_content</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="back_btn btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="back_btn btn btn-default" data-dismiss="modal">返回列表</button>
				<!-- <button type="button" class="btn btn-primary">Save changes</button> -->
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script>
	$(document).ready(function() {
		
		$(".query_a").on("click", function() {
			var query_a_ch = $(this).text();
			var query_a_en = $(this).attr("value1");
			$("#queryLabel_btn").text(query_a_ch);
			$("#query_input").attr("name", query_a_en);
		});
		
		$("#query_btn").on("click", function() {
			var formParam = $("#queryForm").formSerialize();
			
			var queryKey = $("#query_input").attr("name");
			var queryValue = $("#query_input").val();
			var url = '<%=basePath%>/${sid}/admin/flow/Variableaccess/list?'+queryKey + '=' + queryValue;
			var encodeUrl = encodeURI(url);
			window.location.href = encodeUrl;
		});
		
		$(".back_btn").on("click", function() {
			window.location.href = '<%=basePath%>/${sid}/admin/flow/Variableaccess/list';
		});
		
		$('#exampleModal').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget);
			var operator = button.data('whatever'); 
			if(operator == 'add') {
				$("#exampleModalLabel").text("添加用户");
			}
		});
		
		$("#model_submit_btn").on("click", function() {
			var formParam = $("#userForm").formSerialize();
			var operator = $(this).attr("value1");
			var url = '';
			if(operator=='add') {
				url = "<%=basePath%>/${sid}/admin/flow/Variableaccess/add";
			} else if(operator =='edit') {
				url = "<%=basePath%>/${sid}/admin/flow/Variableaccess/edit";
			}
			$.ajax({
				type: "post",
				url: url,
				dataType: "json",
				data: formParam,
				success : function(data) {
					if (data.returncode == "200") {
						$("#exampleModal").modal('hide');
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
		})
		
		$("#edit_btn").on("click", function() {
			
			$("#exampleModalLabel").text("编辑用户");
			
			var checkedNum = $("#listTable").find("input:checkbox[name='flowVariableaccessId']:checked").length;
			
			if(checkedNum > 1||checkedNum<=0) {
				$("#exampleModal").modal('hide');
				
				$("#model_title").text("警告");
				$("#model_content").text("只能选择一个要编辑的项");
				$('#myModal').modal();
				return ;
			}
			
			var editflowVariableaccessId = $("#listTable").find("input:checkbox[name='flowVariableaccessId']:checked").attr("value1");
			
			$.ajax({
				type: "get",
				url: "<%=basePath%>/${sid}/admin/flow/Variableaccess/get",
				dataType: "json",
				data: {
					flowVariableaccessid: editflowVariableaccessId,
				},
				success: function(data) {
					if (data.returncode == "200") {
						var flowVariableaccess = data.data;
						$("#exampleModalLabel").text("编辑用户");
						$("#id").val(flowVariableaccess.id);
						$("#nodeId").val(flowVariableaccess.nodeId);
						$("#variableinstanceId").val(flowVariableaccess.variableinstanceId);
						$("#variablename").val(flowVariableaccess.variablename);
						$("#access").val(flowVariableaccess.access);
						$("#exampleModal").modal('show');
						$("#model_submit_btn").attr("value1", "edit");
					} else {
						$("#model_title").text(data.returnmsg);
						$("#model_content").text(data.returnmemo);
						$('#myModal').modal();
					}
				}
			});
			
		})
		
		$(".delete_btn").on("click", function() {
			var operator = $(this).attr("value1");
			var url = '';
		    if(operator == 'delete') {
				url = "<%=basePath%>/${sid}/admin/flow/Variableaccess/delete";
			}
			var flowVariableaccessIds = '';
			
			$("#listTable").find("input:checkbox[name='flowVariableaccessId']:checked").each(function(){
				var flowVariableaccessid = $(this).attr("value1");
				flowVariableaccessIds = flowVariableaccessIds + "," +flowVariableaccessid;
			})
			flowVariableaccessIds = flowVariableaccessIds.substring(1);
			if(flowVariableaccessIds == '') {
				$("#model_title").text("警告");
				$("#model_content").text("请先选择要删除的项");
				$('#myModal').modal();
				return;
			}
			
			$.ajax({
				type: "post",
				url: url,
				dataType: "json",
				data: {
					flowVariableaccessServiceids: flowVariableaccessIds,
				},
				success: function(data) {
					if (data.returncode == "200") {
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
	});
</script>



