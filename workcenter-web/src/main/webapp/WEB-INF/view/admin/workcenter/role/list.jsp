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
		<button value1="forbidden" type="button" class="delete_btn btn btn-primary" >禁用</button>
		<button value1="delete" type="button" class="delete_btn btn btn-primary" >删除</button>
		<button id="edit_resource_btn" type="button" class="btn btn-primary" >编辑用户组</button>
		
		<div class="col-sm-3">
			<form id="queryForm">
				<div class="input-group">
					<div class="input-group-btn">
						<button id="queryLabel_btn" type="button" class="btn btn-default">${queryLabel_ch }</button>
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
						</button>
						<ul class="dropdown-menu">
							<li><a class="query_a" href="javascript:void(0);" value1="roleName">角色名</a></li>
							<li><a class="query_a" href="javascript:void(0);" value1="roleChName">角色中文名</a></li>
							<li><a class="query_a" href="javascript:void(0);" value1="status">状态</a></li>
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
		<table id="listTable" class="table table-striped" >
			<thead>
				<tr>
					<th>#</th>
					<th>id</th>
					<th>角色名</th>
					<th>角色中文名</th>
					<th>创建时间</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${roles }" var="role">
					<tr>
						<td><input type="checkbox" name="roleId" value1="${role.id}" value2="${role.roleName}" value3="${role.roleChName}"></td>
						<td>${role.id }</td>
						<td>${role.roleName }</td>
						<td>${role.roleChName }</td>
						<td>${role.createTime }</td>
						<td>
						<c:if test="${role.status == '1' }">
							可用
						</c:if>
						<c:if test="${role.status == '0' }">
							禁用
						</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<div class="modal fade bs-example-modal-lg" id="roleResourceModal" tabindex="-1" role="dialog" aria-labelledby="roleResourceModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="back_btn close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleResourceModalLabel">更新用户用户组</h4>
				<h4 class="modal-title" id="exampleResourceModalLabel1">rolename</h4>
			</div>
			<div class="modal-body">
			
				<!--<div class="row">
					<br>
				</div>  空行 -->
				
				<form id="roleResourceForm">
					<input id="updateResource_roleid_input" type="hidden" name="roleId" />
					<table id="modelResourceListTable" class="table table-striped table-bordered" >
						<thead>
							<tr>
								<th>#</th>
								<th>id</th>
								<th>资源名</th>
								<th>资源链接地址</th>
								<th>创建时间</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody>
							<tr id="test_tr" style="display: none;">
								<td><input type="checkbox" name="resourceId"></td>
								<td title="id"></td>
								<td title="resourceName"></td>
								<td title="resourceUrl"></td>
								<td title="createTime"></td>
								<td title="status"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="back_btn btn btn-default" data-dismiss="modal">关闭</button>
				<button id="update_resource_btn" type="button" class="btn btn-primary">更新</button>
			</div>
		</div>
	</div>
</div>

<script>

	$(document).ready(function() {
		
		$("#update_resource_btn").on("click", function() {
			var formParam = $("#roleResourceForm").formSerialize();
			$.ajax({
				type: "post",
				url: "<%=basePath%>/${sid}/admin/role/updateResource",
				dataType: "json",
				data: formParam,
				success: function(data) {
					if (data.returncode == "200") {
						$("#roleResourceModal").modal('hide');
						$("#model_title").text(data.returnmsg);
						$("#model_content").text(data.returnmemo);
						$('#myModal').modal();
					} else {
						$("#model_title").text(data.returnmsg);
						$("#model_content").text(data.returnmemo);
						$('#myModal').modal();
					}
				}
			})
		});
		
		$("#edit_resource_btn").on("click", function() {
			
			$("#roleResourceModalLabel").text("编辑用户用户组");
			var checkedNum = $("#listTable").find("input:checkbox[name='roleId']:checked").length;
			if(checkedNum > 1||checkedNum<=0) {
				$("#exampleModal").modal('hide');
				
				$("#model_title").text("警告");
				$("#model_content").text("只能选择一个要编辑的项");
				$('#myModal').modal();
				return ;
			}
			
			var editRoleId = $("#listTable").find("input:checkbox[name='roleId']:checked").attr("value1");
			var editRoleName = $("#listTable").find("input:checkbox[name='roleId']:checked").attr("value2");
			var editRoleChName = $("#listTable").find("input:checkbox[name='roleId']:checked").attr("value2");
			
			$.ajax({
				type: "get",
				url: '<%=basePath%>/${sid}/admin/role/resourcelist',
				dataType: "json",
				data: {
					roleId: editRoleId,
				},
				success : function(data) {
					$("#updateResource_roleid_input").val(editRoleId);
					$("#exampleResourceModalLabel1").text(editRoleName+"-"+editRoleChName);
					$('#modelResourceListTable').tableData(data); 
					$("#roleResourceModal").modal('show');
				}
			});
		});
		
		
	})
</script>

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
				<form id="roleForm">
					<input id="id" name="id" type="hidden" >
					<div class="form-group">
						<label for="recipient-name" class="control-label">角色名:</label> 
						<input id="roleName" name="roleName" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">角色中文名:</label> 
						<input id="roleChName" name="roleChName" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">状态:</label> 
						<select id="status" name="status" class="form-control">
						  <option value="1">可用</option>
						  <option value="0">禁用</option>
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
			var url = '<%=basePath%>/${sid}/admin/role/list?'+queryKey + '=' + queryValue;
			var encodeUrl = encodeURI(url);
			window.location.href = encodeUrl;
		});
		
		$(".back_btn").on("click", function() {
			window.location.href = '<%=basePath%>/${sid}/admin/role/list';
		});
		
		$('#exampleModal').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget);
			var operator = button.data('whatever'); 
			if(operator == 'add') {
				$("#exampleModalLabel").text("添加角色");
			}
		});
		
		$("#model_submit_btn").on("click", function() {
			var formParam = $("#roleForm").formSerialize();
			var operator = $(this).attr("value1");
			var url = '';
			if(operator=='add') {
				url = "<%=basePath%>/${sid}/admin/role/add";
			} else if(operator =='edit') {
				url = "<%=basePath%>/${sid}/admin/role/edit";
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
			
			$("#exampleModalLabel").text("编辑角色");
			
			var checkedNum = $("#listTable").find("input:checkbox[name='roleId']:checked").length;
			
			if(checkedNum > 1||checkedNum<=0) {
				$("#exampleModal").modal('hide');
				
				$("#model_title").text("警告");
				$("#model_content").text("只能选择一个要编辑的项");
				$('#myModal').modal();
				return ;
			}
			
			var editRoleId = $("#listTable").find("input:checkbox[name='roleId']:checked").attr("value1");
			
			$.ajax({
				type: "get",
				url: "<%=basePath%>/${sid}/admin/role/get",
				dataType: "json",
				data: {
					roleid: editRoleId,
				},
				success: function(data) {
					if (data.returncode == "200") {
						var role = data.data;
						$("#exampleModalLabel").text("编辑角色");
						$("#id").val(role.id);
						$("#roleName").val(role.roleName);
						$("#roleChName").val(role.roleChName);
						$("#status").val(role.status);
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
			if(operator=='forbidden') {
				url = "<%=basePath%>/${sid}/admin/role/forbidden";
			} else if(operator == 'delete') {
				url = "<%=basePath%>/${sid}/admin/role/delete";
			}
			var deleteRoleIds = '';
			
			$("#listTable").find("input:checkbox[name='roleId']:checked").each(function(){
				var roleid = $(this).attr("value1");
				deleteRoleIds = deleteRoleIds + "," +roleid;
			})
			
			deleteRoleIds = deleteRoleIds.substring(1);
			
			if(deleteRoleIds == '') {
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
					roleids: deleteRoleIds,
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



