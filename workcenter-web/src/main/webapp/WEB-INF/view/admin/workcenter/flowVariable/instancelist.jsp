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
							<li><a class="query_a" href="javascript:void(0);" value1="varName">变量名称</a></li>
							<li><a class="query_a" href="javascript:void(0);" value1="varType">变量值</a></li>
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
		<table id="listTable" class="table table-striped"  >
			<thead>
				<tr>
					<th>#</th>
					<th>id</th>
					<th>流程id</th>
					<th>变量类型</th>
					<th>变量名称</th>
					<th>变量值</th>
					<th>变量顺序号</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${flowVariableinstances}" var="flowVariableinstances">
					<tr>
						<td><input type="checkbox" name="flowVariableinstanceId" value1="${flowVariableinstances.id}" ></td>
						<td>${flowVariableinstances.id }</td>
						<td>${flowVariableinstances.processdefinitionId }</td>
						<td>${flowVariableinstances.varType }</td>
						<td>${flowVariableinstances.varName }</td>
						<td>${flowVariableinstances.varValue }</td>
						<td>${flowVariableinstances.indexNum }</td>
						<c:if test="${flowVariableinstances.status == '1' }">
							<td>可用</td>
						</c:if>
						<c:if test="${flowVariableinstances.status == '0' }">
							<td>禁用</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
		<div class="fixed-table-pagination">
			<div class="pull-left pagination">
				<div class="pagination-info">显示 1 至 10 项记录 共 21 条</div>
			</div>
			<div class="pull-right">
			<nav>
				<ul class="pagination">
					<li class="page-first active"><a href="javascript:void(0)">&lt;&lt;首页</a></li>
					<li class="page-pre disabled"><a href="javascript:void(0)">&lt;上一页</a></li>
					<li class="page-number active"><a href="javascript:void(0)">1</a></li>
					<li class="page-number"><a href="javascript:void(0)">2</a></li>
					<li class="page-number"><a href="javascript:void(0)">3</a></li>
					<li class="page-next"><a href="javascript:void(0)">下一页&gt;</a></li>
					<li class="page-last"><a href="javascript:void(0)">尾页&gt;&gt;</a></li>
				</ul>
			</nav>
		</div>
			<div class="pull-right pagination btn-group dropup page-list">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					10 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li><a href="javascript:void(0)" value2="10">10</a></li>
					<li><a href="javascript:void(0)" value2="20">20</a></li>
					<li><a href="javascript:void(0)" value2="30">30</a></li>
					<li><a href="javascript:void(0)" value2="40">40</a></li>
					<li><a href="javascript:void(0)" value2="50">50</a></li>
				</ul>
			</div>
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
						<label for="recipient-name" class="control-label">变量类型:</label>
						<select id="varType" name="varType" class="form-control">
							<option value="S">string</option>
							<option value="L">Long</option>
							<option value="D">Double</option>
							<option value="B">Boolean</option>
							<option value="I">Integer</option>
						</select>

					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">流程Id:</label> 
						<input id="processdefinitionId" name="processdefinitionId" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">变量名称:</label> 
						<input id="varName" name="varName" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">变量值:</label> 
						<input id="varValue" name="varValue" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">变量顺序号:</label> 
						<input id="indexNum" name="indexNum" type="text" class="form-control">
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
			var url = '<%=basePath%>/${sid}/admin/flow/Variable/instance/list?'+queryKey + '=' + queryValue;
			var encodeUrl = encodeURI(url);
			window.location.href = encodeUrl;
			
		});
		
		$(".back_btn").on("click", function() {
			window.location.href = '<%=basePath%>/${sid}/admin/flow/Variable/instance/list';
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
				url = "<%=basePath%>/${sid}/admin/flow/Variable/instance/add";
			} else if(operator =='edit') {
				url = "<%=basePath%>/${sid}/admin/flow/Variable/instance/edit";
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
			
			var checkedNum = $("#listTable").find("input:checkbox[name='flowVariableinstanceId']:checked").length;
			
			if(checkedNum > 1||checkedNum<=0) {
				$("#exampleModal").modal('hide');
				
				$("#model_title").text("警告");
				$("#model_content").text("只能选择一个要编辑的项");
				$('#myModal').modal();
				return ;
			}
			
			var flowVariableinstanceId = $("#listTable").find("input:checkbox[name='flowVariableinstanceId']:checked").attr("value1");
			
			$.ajax({
				type: "get",
				url: "<%=basePath%>/${sid}/admin/flow/Variable/instance/get",
				dataType: "json",
				data: {
					flowVariableinstanceid: flowVariableinstanceId,
				},
				success: function(data) {
					if (data.returncode == "200") {
						var flowVariableinstance = data.data;
						$("#exampleModalLabel").text("编辑用户");
						$("#id").val(flowVariableinstance.id);
						$("#processdefinitionId").val(flowVariableinstance.processdefinitionId);				
						$("#varName").val(flowVariableinstance.varName);
						$("#varType").val(flowVariableinstance.varType);
						$("#status").val(flowVariableinstance.status);
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
			alert(operator=='forbidden');
			if(operator=='forbidden') {
				url = "<%=basePath%>/${sid}/admin/flow/Variable/instance/forbidden";
			} else if(operator == 'delete') {
				url = "<%=basePath%>/${sid}/admin/flow/Variable/instance/delete";
			}
			var deleteflowVariableinstanceIds = '';
			
			$("#listTable").find("input:checkbox[name='flowVariableinstanceId']:checked").each(function(){
				var flowVariableinstanceId = $(this).attr("value1");
				deleteflowVariableinstanceIds = deleteflowVariableinstanceIds + "," +flowVariableinstanceId;
			})
			
			deleteflowVariableinstanceIds = deleteflowVariableinstanceIds.substring(1);
			
			if(deleteflowVariableinstanceIds == '') {
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
					flowVariableinstanceids: deleteflowVariableinstanceIds,
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



