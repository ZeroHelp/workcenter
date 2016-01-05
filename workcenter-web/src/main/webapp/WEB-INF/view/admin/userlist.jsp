<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<div class="panel-heading">&nbsp;用户管理列表</div>
	<div>
		<!-- Button trigger modal -->
		<button onclick="adddata()" type="button"
			class="btn btn-primary btn-lg" data-toggle="modal"
			data-target="#myModal">新增</button>
		<button id="updatebutton" onclick="updatedata()" type="button"
			data-toggle="modal" class="btn btn-primary btn-lg">修改</button>
		<button id="deletebutton" onclick="deletedata()" type="button"
			data-toggle="modal" class="btn btn-primary btn-lg">删除</button>
	</div>
	<table class="table table-hover" id="table-transform" data-height="246"
		data-pagination="true"
		data-url="<%=basePath%>/${sid}/admin/user/loadallList"
		data-toggle="table">
		<thead>
			<tr>
				<th data-field="state" data-checkbox="true"></th>
				<th data-field="id" data-align="center" data-sortable="true">id</th>
				<th data-field="userName" data-align="center" data-sortable="true">用户名</th>
				<th data-field="realName" data-align="center" data-sortable="true">姓名</th>
				<!-- <th data-field="password" data-align="center" data-sortable="true">密码</th> -->
				<th data-field="lastLogin" data-align="center" data-sortable="true">最后登录时间</th>
				<th data-field="status" data-align="center" data-sortable="true">状态</th>
			</tr>
		</thead>
	</table>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
				</div>
				<div class="modal-body">

					<form id="testform">
						<div class="form-group">
							<label class="control-label">id:</label> <input type="text"
								class="form-control" name="id">
						</div>
						<div class="form-group">
							<label class="control-label">用户名:</label> <input type="text"
								class="form-control" name="userName">
						</div>
						<div class="form-group">
							<label class="control-label">姓名:</label> <input type="text"
								class="form-control" name="realName">
						</div>
						<div class="form-group">
							<label class="control-label">密码:</label> <input type="text"
								class="form-control" name="password">
						</div>
						<div class="form-group">
							<label class="control-label">最后登录时间:</label> <input type="text"
								class="form-control" name="lastLogin">
						</div>
						<div class="form-group">
							<label class="control-label">状态:</label> <input type="text"
								class="form-control" name="status">
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="submit" type="button" class="btn btn-primary"
						onclick="">提交</button>
				</div>
			</div>
		</div>

	</div>

	<!-- /container -->

	<script>
 		var $table  = $('#table-transform');
 		var	$buttond = $('#deletebutton');
    	var	$button = $('#updatebutton');
      $(function () {
        $button.click(function () {   
        	var ss=$table.bootstrapTable('getSelections');
        	  $("#testform input[type='text']").val("");
        	 $.ajax({
          	   type: "POST",
          	   url: "<%=basePath%>/${sid}/admin/user/loadone",
          	   dataType:"json",
          	   data:{id:ss[0].id},
          	   async: false,
          	   success: function(data){
        			var value=data;
          			$("#testform input[name='id']").val(value.id);
             		$("#testform input[name='userName']").val(value.userName);
             		$("#testform input[name='realName']").val(value.realName);
             		$("#testform input[name='password']").val(value.password);
             		$("#testform input[name='lastLogin']").val(value.lastLogin);
             		$("#testform input[name='status']").val(value.status);   
          	   }
          	});
     	}); 
        $buttond.click(function () {   
          var ss=$table.bootstrapTable('getSelections');
          alert(ss.length);
          var con= confirm("确认删除这条数据")
          if(con==true){
        	   for(i=0;i<ss.length;i++){

             	  $.ajax({
                	   type: "POST",
                	   url: "<%=basePath%>/${sid}/admin/user/deleteUser",
                	   dataType:"json",
                	   data:{id:ss[i].id},
                	   async: false,
                	   success: function(data){
                		   
                	   }
                	});     	  
               }  
			   window.location.href = '<%=basePath%>/${sid}/admin/user/list';
          }  	 
   		});          
 	});

function adddata(){
		$("#myModalLabel").text("新增用户信息");
		$('#submit').attr('onclick','adddatas()');

  	    $("#testform input[type='text']").val("");

}
function adddatas(){
	$("#testform input[name='password']").val("96e79218965eb72c92a549dd5a330112");
	var data= $("#testform").serialize();
	    $.ajax({
	    	   type: "POST",
	    	   url: "<%=basePath%>/${sid}/admin/user/insertUser",
	    	   dataType:"json",
	    	   data:$("#testform").serialize(),
	    	   async: false,
	    	   error: function(msg) {
	    		   alert(msg);
               },
	    	   success: function(msg){
			   window.location.href = '<%=basePath%>/${sid}/admin/user/list';
	    	   }
	    });
}
function updatedata(){

	var flag=false;
	var index=0;
   $("#table-transform input[type='checkbox']").each(function() {
   	if($(this).prop("checked")){	
   		index++;
   		flag=true;
    }
	});  
if(flag&&index==1){
			$("#myModalLabel").text("修改用户信息");
	  		$('#myModal').modal('show');
	  		$("#testform input[name='lastLogin']").attr('style','display:none;');
	  		$('#submit').attr('onclick','update()');
	}else{
		 alert("请选择一行");
    	$('#myModal').modal('hide');  
    }
} 
function update(){
	var password=$("#testform input[name='password']").val();
	password = hex_md5(password);
	$("#testform input[name='password']").val(password);

	var data= $("#testform").serialize();
	alert(data);
	    $.ajax({
	    	   type: "POST",
	    	   url: "<%=basePath%>/${sid}/admin/user/updateUser",
	    	   dataType:"json",
	    	   data:$("#testform").serialize(),
	    	   async: false,
	    	   error: function(msg) {
	    		   alert(msg)
                   alert("修改失败");
               },
	    	   success: function(msg){
	    		   alert(msg)
	    	     alert("修改成功");
				 window.location.href = '<%=basePath%>/${sid}/admin/user/list';
	    	   }
	    });

}


</script>

</div>
