<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 右侧列表  -->
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<!-- <h1 class="page-header">Dashboard</h1>

	<h2 class="sub-header">Section title</h2> -->

	<!-- <button id="kpi" type="button" class="btn btn-primary ">创建绩效</button>
	 -->
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">创建新季度绩效</button>

	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Header</th>
					<th>Header</th>
					<th>Header</th>
					<th>Header</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1,001</td>
					<td>Lorem</td>
					<td>ipsum</td>
					<td>dolor</td>
					<td>sit</td>
				</tr>
			</tbody>
		</table>
	</div>

</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">新季度绩效</h4>
			</div>
			<div class="modal-body">
				<form id="startKpiForm">
					<div class="form-group">
						<label for="recipient-name" class="control-label">年度:</label> 
						<input name="year" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="recipient-name" class="control-label">备注:</label> 
						<input name="remark" type="text" class="form-control">
					</div>
					<!-- <div class="form-group">
						<label for="message-text" class="control-label">Message:</label>
						<textarea class="form-control" id="message-text"></textarea>
					</div> -->
					
					<div class="col-sm-12">
						<br>
					</div> <!-- 空行 -->
					
					<c:forEach items="${groups}" var="group">
						<div class="radio">
						  <label>
						    <input type="radio" name="groupId" value="${group.id}" >
						    ${group.groupName}-${group.groupChName}
						  </label>
						</div>
					</c:forEach>
					
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="startNewKpi" type="button" class="btn btn-primary">发起</button>
			</div>
		</div>
	</div>
</div>

<!-- dialog -->
<div class="modal fade" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="model_title">model_title</h4>
			</div>
			<div class="modal-body">
				<p id="model_content">model_content</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
		
		$('#exampleModal').on('show.bs.modal', function(event) {
			//var button = $(event.relatedTarget);// Button that triggered the modal
			//var recipient = button.data('whatever'); // Extract info from data-* attributes
			// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
			// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
			//var modal = $(this);
			//modal.find('.modal-title').text('New message to ' + recipient);
			//modal.find('.modal-body input').val(recipient);
		});
		
		$("#startNewKpi").on("click", function(){
			var formParam = $("#startKpiForm").formSerialize();
			
			$.ajax({
				type: "post",
				url: "<%=basePath%>/${sid}/admin/kpi/init",
				dataType : "json",
				data : formParam,
				success : function(data) {
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
			})
		});

	});
</script>



