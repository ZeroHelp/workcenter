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

		<div class="col-sm-3">
			<form id="queryForm">
				<div class="input-group">
					<div class="input-group-btn">
						<button id="queryLabel_btn" type="button" class="btn btn-default">${queryLabel_ch }</button>
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
						</button>
						<ul class="dropdown-menu">
							<li><a class="query_a" href="javascript:void(0);" value1="groupName">用户组名</a></li>
							<li><a class="query_a" href="javascript:void(0);" value1="groupChName">用户组中文名</a></li>
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
					<th>用户组名</th>
					<th>用户组中文名</th>
					<th>创建时间</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${groups }" var="group">
					<tr>
						<td><input type="checkbox" name="groupId" value1="${group.id}" ></td>
						<td>${group.id }</td>
						<td>${group.groupName }</td>
						<td>${group.groupChName }</td>
						<td>${group.createTime }</td>
						<td>
						<c:if test="${group.status == '1' }">
							可用
						</c:if>
						<c:if test="${group.status == '0' }">
							禁用
						</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>

<div class="modal fade" id="exampleModal" tabindex="-1" group="dialog" aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" group="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="back_btn close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">exampleModal_title</h4>
			</div>
			<div class="modal-body">
				<form id="groupForm">
					<input id="id" name="id" type="hidden" >
					<div class="form-group">
						<label for="recipient-name" class="control-label">用户组名:</label> 
						<input id="groupName" name="groupName" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">用户组中文名:</label> 
						<input id="groupChName" name="groupChName" type="text" class="form-control">
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
			var url = '<%=basePath%>/${sid}/admin/group/list?'+queryKey + '=' + queryValue;
			var encodeUrl = encodeURI(url);
			window.location.href = encodeUrl;
		});
		
		$(".back_btn").on("click", function() {
			window.location.href = '<%=basePath%>/${sid}/admin/group/list';
		});
		
		$('#exampleModal').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget);
			var operator = button.data('whatever'); 
			if(operator == 'add') {
				$("#exampleModalLabel").text("添加角色");
			}
		});
		
		$("#model_submit_btn").on("click", function() {
			var formParam = $("#groupForm").formSerialize();
			var operator = $(this).attr("value1");
			var url = '';
			if(operator=='add') {
				url = "<%=basePath%>/${sid}/admin/group/add";
			} else if(operator =='edit') {
				url = "<%=basePath%>/${sid}/admin/group/edit";
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
			
			var checkedNum = $("#listTable").find("input:checkbox[name='groupId']:checked").length;
			
			if(checkedNum > 1||checkedNum<=0) {
				$("#exampleModal").modal('hide');
				
				$("#model_title").text("警告");
				$("#model_content").text("只能选择一个要编辑的项");
				$('#myModal').modal();
				return ;
			}
			
			var editGroupId = $("#listTable").find("input:checkbox[name='groupId']:checked").attr("value1");
			
			$.ajax({
				type: "get",
				url: "<%=basePath%>/${sid}/admin/group/get",
				dataType: "json",
				data: {
					groupid: editGroupId,
				},
				success: function(data) {
					if (data.returncode == "200") {
						var group = data.data;
						$("#exampleModalLabel").text("编辑角色");
						$("#id").val(group.id);
						$("#groupName").val(group.groupName);
						$("#groupChName").val(group.groupChName);
						$("#status").val(group.status);
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
				url = "<%=basePath%>/${sid}/admin/group/forbidden";
			} else if(operator == 'delete') {
				url = "<%=basePath%>/${sid}/admin/group/delete";
			}
			var deleteGroupIds = '';
			
			$("#listTable").find("input:checkbox[name='groupId']:checked").each(function(){
				var groupid = $(this).attr("value1");
				deleteGroupIds = deleteGroupIds + "," +groupid;
			})
			
			deleteGroupIds = deleteGroupIds.substring(1);
			
			if(deleteGroupIds == '') {
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
					groupids: deleteGroupIds,
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



