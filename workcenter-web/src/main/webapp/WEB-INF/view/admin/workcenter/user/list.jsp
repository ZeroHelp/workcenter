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
		<button id="edit_role_btn" type="button" class="btn btn-primary" >编辑角色</button>
		<button id="edit_group_btn" type="button" class="btn btn-primary" >编辑用户组</button>
		
		<div class="col-sm-3">
			<form id="queryForm">
				<div class="input-group">
					<div class="input-group-btn">
						<button id="queryLabel_btn" type="button" class="btn btn-default">${queryLabel_ch }</button>
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
						</button>
						<ul class="dropdown-menu">
							<li><a class="query_a" href="javascript:void(0);" value1="userName">用户名</a></li>
							<li><a class="query_a" href="javascript:void(0);" value1="realName">真实姓名</a></li>
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
	
	<div class="row">
		<br>
	</div> <!-- 空行 -->

	<div class="table-responsive">
		<table id="listTable" class="table table-striped table-bordered"  >
			<thead>
				<tr>
					<th>#</th>
					<th>id</th>
					<th>用户名</th>
					<th>真实姓名</th>
					<th>最后登录时间</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users }" var="user">
					<tr>
						<td><input type="checkbox" name="userId" value1="${user.id}" value2="${user.realName}" ></td>
						<td>${user.id }</td>
						<td>${user.userName }</td>
						<td>${user.realName }</td>
						<td>${user.lastLogin }</td>
						<c:if test="${user.status == '1' }">
							<td>可用</td>
						</c:if>
						<c:if test="${user.status == '0' }">
							<td>禁用</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>

<div class="modal fade bs-example-modal-lg" id="userRoleModal" tabindex="-1" role="dialog" aria-labelledby="userRoleModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="back_btn close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleRoleModalLabel">更新用户角色</h4>
				<h4 class="modal-title" id="exampleRoleModalLabel1">username</h4>
			</div>
			<div class="modal-body">
			
				<!--<div class="row">
					<br>
				</div>  空行 -->
				
				<form id="userRoleForm">
					<input id="updateRole_userid_input" type="hidden" name="userId" />
					<table id="modelRoleListTable" class="table table-striped table-bordered" >
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
							<tr style="display: none;">
								<td><input type="checkbox" name="roleId"></td>
								<td title="id"></td>
								<td title="roleName"></td>
								<td title="roleChName"></td>
								<td title="createTime"></td>
								<td title="status"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="back_btn btn btn-default" data-dismiss="modal">关闭</button>
				<button id="update_role_btn" type="button" class="btn btn-primary">更新</button>
			</div>
		</div>
	</div>
</div>

<script>

	$(document).ready(function() {
		
		$("#update_role_btn").on("click", function() {
			var formParam = $("#userRoleForm").formSerialize();
			$.ajax({
				type: "post",
				url: "<%=basePath%>/${sid}/admin/user/updateRole",
				dataType: "json",
				data: formParam,
				success: function(data) {
					if (data.returncode == "200") {
						$("#userRoleModal").modal('hide');
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
		
		$("#edit_role_btn").on("click", function() {
			
			$("#userRoleModalLabel").text("编辑用户角色");
			var checkedNum = $("#listTable").find("input:checkbox[name='userId']:checked").length;
			if(checkedNum > 1||checkedNum<=0) {
				$("#exampleModal").modal('hide');
				
				$("#model_title").text("警告");
				$("#model_content").text("只能选择一个要编辑的项");
				$('#myModal').modal();
				return ;
			}
			
			var editUserId = $("#listTable").find("input:checkbox[name='userId']:checked").attr("value1");
			var editUserName = $("#listTable").find("input:checkbox[name='userId']:checked").attr("value2");
			
			$.ajax({
				type: "get",
				url: '<%=basePath%>/${sid}/admin/user/rolelist',
				dataType: "json",
				data: {
					userId: editUserId,
				},
				success : function(data) {
					$("#updateRole_userid_input").val(editUserId);
					$("#exampleRoleModalLabel1").text(editUserName);
					$('#modelRoleListTable').tableData(data); 
					$("#userRoleModal").modal('show');
				}
			});
		});
		
		
	})
</script>


<div class="modal fade bs-example-modal-lg" id="userGroupModal" tabindex="-1" role="dialog" aria-labelledby="userGroupModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="back_btn close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleGroupModalLabel">更新用户用户组</h4>
				<h4 class="modal-title" id="exampleGroupModalLabel1">username</h4>
			</div>
			<div class="modal-body">
			
				<!--<div class="row">
					<br>
				</div>  空行 -->
				
				<form id="userGroupForm">
					<input id="updateGroup_userid_input" type="hidden" name="userId" />
					<table id="modelGroupListTable" class="table table-striped table-bordered" >
						<thead>
							<tr>
								<th>#</th>
								<th>id</th>
								<th>用户组名</th>
								<th>用户组中文名</th>
								<th>创建时间</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody>
							<tr id="test_tr" style="display: none;">
								<td><input type="radio" name="groupId"></td>
								<td title="id"></td>
								<td title="groupName"></td>
								<td title="groupChName"></td>
								<td title="createTime"></td>
								<td title="status"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="back_btn btn btn-default" data-dismiss="modal">关闭</button>
				<button id="update_group_btn" type="button" class="btn btn-primary">更新</button>
			</div>
		</div>
	</div>
</div>

<script>

	$(document).ready(function() {
		
		$("#update_group_btn").on("click", function() {
			var formParam = $("#userGroupForm").formSerialize();
			$.ajax({
				type: "post",
				url: "<%=basePath%>/${sid}/admin/user/updateGroup",
				dataType: "json",
				data: formParam,
				success: function(data) {
					if (data.returncode == "200") {
						$("#userGroupModal").modal('hide');
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
		
		$("#edit_group_btn").on("click", function() {
			
			$("#userGroupModalLabel").text("编辑用户用户组");
			var checkedNum = $("#listTable").find("input:checkbox[name='userId']:checked").length;
			if(checkedNum > 1||checkedNum<=0) {
				$("#exampleModal").modal('hide');
				
				$("#model_title").text("警告");
				$("#model_content").text("只能选择一个要编辑的项");
				$('#myModal').modal();
				return ;
			}
			
			var editUserId = $("#listTable").find("input:checkbox[name='userId']:checked").attr("value1");
			var editUserName = $("#listTable").find("input:checkbox[name='userId']:checked").attr("value2");
			
			$.ajax({
				type: "get",
				url: '<%=basePath%>/${sid}/admin/user/grouplist',
				dataType: "json",
				data: {
					userId: editUserId,
				},
				success : function(data) {
					$("#updateGroup_userid_input").val(editUserId);
					$("#exampleGroupModalLabel1").text(editUserName);
					$('#modelGroupListTable').tableData(data); 
					$("#userGroupModal").modal('show');
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
				<form id="userForm">
					<input id="id" name="id" type="hidden" >
					<div class="form-group">
						<label for="recipient-name" class="control-label">用户名:</label> 
						<input id="userName" name="userName" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">真实姓名:</label> 
						<input id="realName" name="realName" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">状态:</label> 
						<select id="status" name="status" class="form-control">
						  <option value="1">可用</option>
						  <option value="0">禁用</option>
						</select>
					</div>
					
					<div id="checkbox_div">
					
					<c:forEach items="${roles}" var="role">
						<div class="checkbox">
						  <label>
						    <input type="checkbox" checked name="roleId" value="${role.id}">
						    ${role.roleChName}
						  </label>
						</div>
					</c:forEach>
					
					<div class="col-sm-12">
						<br>
					</div> <!-- 空行 -->
					
					<c:forEach items="${groups}" var="group">
						<c:if test="${group.id == defaultGroupId}">
							<div class="radio">
							  <label>
							    <input type="radio" checked name="groupId" value="${group.id}" >
							    ${group.groupName}-${group.groupChName}
							  </label>
							</div>
						</c:if>
						<c:if test="${group.id != defaultGroupId}">
							<div class="radio">
							  <label>
							    <input type="radio" name="groupId" value="${group.id}" >
							    ${group.groupName}-${group.groupChName}
							  </label>
							</div>
						</c:if>
					</c:forEach>
					
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
			var url = '<%=basePath%>/${sid}/admin/user/list?'+queryKey + '=' + queryValue;
			var encodeUrl = encodeURI(url);
			window.location.href = encodeUrl;
		});
		
		$(".back_btn").on("click", function() {
			window.location.href = '<%=basePath%>/${sid}/admin/user/list';
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
				url = "<%=basePath%>/${sid}/admin/user/add";
			} else if(operator =='edit') {
				url = "<%=basePath%>/${sid}/admin/user/edit";
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
			
			var checkedNum = $("#listTable").find("input:checkbox[name='userId']:checked").length;
			
			if(checkedNum > 1||checkedNum<=0) {
				$("#exampleModal").modal('hide');
				
				$("#model_title").text("警告");
				$("#model_content").text("只能选择一个要编辑的项");
				$('#myModal').modal();
				return ;
			}
			
			var editUserId = $("#listTable").find("input:checkbox[name='userId']:checked").attr("value1");
			
			$.ajax({
				type: "get",
				url: "<%=basePath%>/${sid}/admin/user/get",
				dataType: "json",
				data: {
					userid: editUserId,
				},
				success: function(data) {
					if (data.returncode == "200") {
						var user = data.data;
						$("#exampleModalLabel").text("编辑用户");
						$("#id").val(user.id);
						$("#userName").val(user.userName);
						$("#realName").val(user.realName);
						$("#status").val(user.status);
						$("#checkbox_div").hide();
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
				url = "<%=basePath%>/${sid}/admin/user/forbidden";
			} else if(operator == 'delete') {
				url = "<%=basePath%>/${sid}/admin/user/delete";
			}
			var deleteUserIds = '';
			
			$("#listTable").find("input:checkbox[name='userId']:checked").each(function(){
				var userid = $(this).attr("value1");
				deleteUserIds = deleteUserIds + "," +userid;
			})
			
			deleteUserIds = deleteUserIds.substring(1);
			
			if(deleteUserIds == '') {
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
					userids: deleteUserIds,
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



