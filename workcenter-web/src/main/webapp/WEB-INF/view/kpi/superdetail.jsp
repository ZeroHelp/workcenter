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
				<a class="navbar-brand" href="<%=basePath%>/${sid }/workcenter/home">workcenter系统</a><a class="navbar-brand" href="#">></a><a class="navbar-brand" href="<%=basePath%>/${sid }/kpi/superhome">KPI超级用户查看</a>
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
				<h1>欢迎使用,KPI系统</h1>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">${waitAssessmentPerson } 的KPI->&nbsp;
				<c:choose>
					<c:when test="${selfDirection_access == 3}">
						<!-- 起草 -->
						起草任务
					</c:when>

					<c:when test="${selfEvaluate_access == 3}">
						<!-- 任务自评 -->
						任务自评
					</c:when>

					<c:when test="${leaderEvaluation_access == 3}">
						<!-- 领导审评 -->
						领导审评
					</c:when>

					<c:when test="${method == 'view'}">
						<!-- 查看 -->
						${method}查看详情
					</c:when>
					<c:otherwise>
						<!-- 领导审批 -->
						领导审批
					</c:otherwise>
				</c:choose>
			</div>
			<!--<div class="panel-body">
			<p>&nbsp;</p>
		</div>-->

		</div>

		<style>
.border-div {
	border-color: #000;
	border-width: 1px;
}
</style>
		<form id="kpiForm">
		
			<span class="label label-info">个人设定</span>
			<table class="table" id="selfset">
				<tbody>
					<c:forEach items="${selfAttributes}" var="self">
						<tr>
							<td>
								<input type="hidden" name="selfId" value="${self.selfId }" />
 								<!-- selfDirection start --> 
								<c:if test="${selfDirection_access == 1}">

									<div class="row">
										<div class="col-lg-12">
											<div class="input-group">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方向&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <input name="selfDirection" value="${self.selfDirection}" type="text" class="form-control" placeholder="填写方向..."  readonly >
											</div>
										</div>
									</div>
									<!-- /.row -->

								</c:if> 
								<c:if test="${selfDirection_access == 3}">

									<div class="row">
										<div class="col-lg-12">
											<div class="input-group">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方向&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <input name="selfDirection" value="${self.selfDirection}" type="text" class="requireds form-control" placeholder="填写方向..."  > 
												<span class="input-group-btn">
													<button class="plus_btn btn btn-default" type="button">
														<span class="glyphicon glyphicon-plus" />
													</button>
													<!-- <button class="minus_btn btn btn-default" type="button">
														<span class="glyphicon glyphicon-minus" />
													</button> -->
												</span>
											</div>
										</div>
									</div>
									<!-- /.row -->

								</c:if> <!-- selfDirection end -->
								
								<div class="col-sm-12">
									<br>
								</div> <!-- 空行 -->
								
								<!-- selfGoal start -->
								 <c:if test="${selfGoal_access == 1}">
									<div class="row">
										<span class="col-sm-1 control-label text-right label_text">个人设定目标:</span>
										<div class="form-group col-sm-11">
											<textarea name="selfGoal" class="form-control" rows="3" readonly>${self.selfGoal }</textarea>
										</div>
									</div>
								</c:if>
								<c:if test="${selfGoal_access == 3}">
									<div class="row">
										<span class="col-sm-1 control-label text-right label_text">个人设定目标:</span>
										<div class="form-group col-sm-11">
											<textarea name="selfGoal" class="requireds form-control" rows="3" >${self.selfGoal }</textarea>
										</div>
									</div>
								</c:if><!-- selfGoal end -->
								
								<!-- selfWeight start -->
								<c:if test="${selfWeight_access == 1}">
									<div class="row">
										<div class="col-sm-2 col-sm-offset-10">
											<div class="input-group">
												<span class="input-group-addon">权重</span> <input type="text" name="selfWeight" class="form-control text-right" aria-label="Amount (to the nearest dollar)" value="${self.selfWeight }" readonly> <span class="input-group-addon">%</span>
											</div>
										</div>
									</div> <!-- /.row -->
								</c:if>
								<c:if test="${selfWeight_access == 3}">
									<div class="row">
										<div class="col-sm-2 col-sm-offset-10">
											<div class="input-group">
												<span class="input-group-addon">权重</span> <input type="text" name="selfWeight" class="requireds form-control text-right required" aria-label="Amount (to the nearest dollar)" value="${self.selfWeight }"> <span class="input-group-addon">%</span>
											</div>
										</div>
									</div> <!-- /.row -->
								</c:if> <!-- selfWeight end -->
								
								<div class="col-sm-12">
									<br>
								</div> <!-- 空行 -->
								
								<!-- selfEvaluate start -->
								<c:if test="${selfEvaluate_access == 1}">
									<div class="row">
										<span class="col-sm-1 control-label text-right">自我评价:</span>
										<div class="form-group col-sm-11">
											<textarea name="selfEvaluate" class="form-control" rows="3" readonly>${self.selfEvaluate }</textarea>
										</div>
									</div>
								</c:if>
								<c:if test="${selfEvaluate_access == 3}">
									<div class="row">
										<span class="col-sm-1 control-label text-right">自我评价:</span>
										<div class="form-group col-sm-11">
											<textarea name="selfEvaluate" class="requireds form-control" rows="3" >${self.selfEvaluate }</textarea>
										</div>
									</div>
								</c:if> <!-- selfEvaluate end -->


								<!-- selfScore start -->
								<c:if test="${selfScore_access == 1}">
									<div class="row">
										<div class="col-sm-2 col-sm-offset-10">
											<div class="input-group">
												<span class="input-group-addon">自评</span> <input name="selfScore" type="text" class="form-control text-right" aria-label="..." value="${self.selfScore}" readonly> 
												<span class="input-group-addon">分</span>
											</div>
										</div>
									</div> <!-- /.row -->
								</c:if>
								
								<c:if test="${selfScore_access == 3}">
									<div class="row">
										<div class="col-sm-2 col-sm-offset-10">
											<div class="input-group">
												<span class="input-group-addon">自评</span> <input name="selfScore" type="text" class="requireds form-control text-right" aria-label="..." value="${self.selfScore}">
												<div class="input-group-btn">
													<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
														分<span class="caret"></span>
													</button>
													<ul class="dropdown-menu dropdown-menu-right">
														<li><a class="score_a" value1="5" href="javascript:void(0);">5</a></li>
														<li role="separator" class="divider"></li>
														<li><a class="score_a" value1="4" href="javascript:void(0);">4</a></li>
														<li role="separator" class="divider"></li>
														<li><a class="score_a" value1="3.75" href="javascript:void(0);">3.75</a></li>
														<li><a class="score_a" value1="3.5" href="javascript:void(0);">3.5</a></li>
														<li><a class="score_a" value1="3.25" href="javascript:void(0);">3.25</a></li>
														<li><a class="score_a" value1="3" href="javascript:void(0);">3</a></li>
														<li role="separator" class="divider"></li>
														<li><a class="score_a" value1="2" href="javascript:void(0);">2</a></li>
													</ul>
												</div>
											</div>
										</div>
									</div> <!-- /.row -->

								</c:if>
								<!-- selfScore end -->
								
								<div class="col-sm-12">
									<br>
								</div> <!-- 空行 -->
								
								<!-- leaderEvaluation start -->
								<c:if test="${leaderEvaluation_access == 1}">
									<div class="row">
										<span class="col-sm-1 control-label text-right">领导评价:</span>
										<div class="form-group col-sm-11">
											<textarea name="leaderEvaluation" class="form-control" rows="3" value="${self.leaderEvaluation}" readonly></textarea>
										</div>
									</div> <!-- /.row -->
								</c:if>
								<c:if test="${leaderEvaluation_access == 3}">
									<div class="row">
										<span class="col-sm-1 control-label text-right">领导评价:</span>
										<div class="form-group col-sm-11">
											<textarea name="leaderEvaluation" class="requireds form-control" rows="3" value="${self.leaderEvaluation}"></textarea>
										</div>
									</div> <!-- /.row -->
								</c:if>
								<!-- leaderEvaluation end -->
								
								<!-- leaderScore start -->
								<c:if test="${leaderScore_access == 1}">
									<div class="row">
										<div class="col-sm-2 col-sm-offset-10">
											<div class="input-group">
												<span class="input-group-addon">审评</span> <input name="leaderScore" type="text" class="form-control text-right" aria-label="..." value="${self.leaderScore}" readonly> <span class="input-group-addon">分</span>
											</div>
										</div>
									</div> <!-- /.row -->
								</c:if>
								<c:if test="${leaderScore_access == 3}">
									<div class="row">
										<div class="col-sm-2 col-sm-offset-10">
											<div class="input-group">
												<span class="input-group-addon">审评</span> <input name="leaderScore" type="text" class="requireds form-control text-right" aria-label="..." value="${self.leaderScore}">
												<div class="input-group-btn">
													<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
														分<span class="caret"></span>
													</button>
													<ul class="dropdown-menu dropdown-menu-right">
														<li><a class="score_a" value1="5" href="javascript:void(0);">5</a></li>
														<li role="separator" class="divider"></li>
														<li><a class="score_a" value1="4" href="javascript:void(0);">4</a></li>
														<li role="separator" class="divider"></li>
														<li><a class="score_a" value1="3.75" href="javascript:void(0);">3.75</a></li>
														<li><a class="score_a" value1="3.5" href="javascript:void(0);">3.5</a></li>
														<li><a class="score_a" value1="3.25" href="javascript:void(0);">3.25</a></li>
														<li><a class="score_a" value1="3" href="javascript:void(0);">3</a></li>
														<li role="separator" class="divider"></li>
														<li><a class="score_a" value1="2" href="javascript:void(0);">2</a></li>
													</ul>
												</div>
											</div>
										</div>
									</div> <!-- /.row -->
								</c:if>
								<!-- leaderScore end -->
								
							</td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>

			<span class="label label-info">文化设定</span>

			<table class="table">
				<tbody>
					<tr>
						<td>
							<div class="row">
								<div class="col-lg-12">
									<div class="input-group">
										<span class="input-group-addon">企业文化</span> <input type="text" class="form-control" placeholder="填写企业文化..." value="评价标准参见《宜信员工企业文化表现指标库》:诚信、专业、创新、以客户为本、共享价值" readonly>
									</div>
								</div>
							</div> <!-- /.row -->
							
							<div class="col-sm-12">
								<br>
							</div> <!-- 空行 -->
							
							<div class="row">
								<div class="col-sm-2 col-sm-offset-10">
									<div class="input-group">
										<span class="input-group-addon">权重</span> <input type="text" class="form-control text-right" aria-label="Amount (to the nearest dollar)" value="25" readonly> <span class="input-group-addon">%</span>
									</div>
								</div>
							</div> <!-- /.row -->
						</td>
					</tr>
					<tr>
						<td>
							<div class="row">
								<div class="col-lg-12">
									<div class="input-group">
										<span class="input-group-addon">行为规范</span> <input type="text" class="form-control" placeholder="填写行为规范..." value="个人行为规范符合度" readonly>
									</div>
								</div>
							</div> <!-- /.row -->

							<div class="col-sm-12">
								<br>
							</div> <!-- 空行 -->
							
							<div class="row">
								<div class="col-sm-2 col-sm-offset-10">
									<div class="input-group">
										<span class="input-group-addon">权重</span> <input type="text" class="form-control text-right" aria-label="Amount (to the nearest dollar)" value="25" readonly> <span class="input-group-addon">%</span>
									</div>
								</div>
							</div> <!-- /.row -->
						</td>
					</tr>
					
					<c:forEach items="${culturalAttributes}" var="cultural">
						
						<tr>
							<td>
								<input type="hidden" name="culturalId" value="${cultural.cultualId }" />
								<c:if test="${taskContent_access == 1}">
									<div class="row">
										<div class="col-lg-12">
											<div class="input-group">
												<span class="input-group-addon" >能力素质</span> <input name="taskContent" value="${cultural.taskContent}" type="text" class="form-control" placeholder="填写能力素质...(1、评价标准参见《宜信员工能力素质指标库》。2、建议选取3-5个指标。)" readonly>
											</div>
										</div>
									</div> <!-- /.row -->
								</c:if>
								
								<c:if test="${taskContent_access == 3}">
									<div class="row">
										<div class="col-lg-12">
											<div class="input-group">
												<span class="input-group-addon" >能力素质</span> <input name="taskContent" value="${cultural.taskContent}" type="text" class="requireds form-control " placeholder="填写能力素质...(1、评价标准参见《宜信员工能力素质指标库》。2、建议选取3-5个指标。)">
											</div>
										</div>
									</div> <!-- /.row -->
								</c:if>
								
								<div class="col-sm-12">
									<br>
								</div> <!-- 空行 -->
								
								<div class="row">
									<div class="col-sm-2 col-sm-offset-10">
										<div class="input-group">
											<span class="input-group-addon">权重</span> <input type="text" class="form-control text-right" aria-label="Amount (to the nearest dollar)" value="50" readonly> <span class="input-group-addon">%</span>
										</div>
									</div>
								</div> <!-- /.row -->
							</td>
						</tr>
						
					</c:forEach>
					
				</tbody>
			</table>
		</form>

		<span class="label label-info">汇总</span>
		<table class="table">
			<tbody>
				<tr>
					<td>
						<div class="row">
							<div class="col-sm-2 col-sm-offset-8">
								<div class="input-group">
									<span class="input-group-addon">等级</span> <input type="text" value="${main.grade }" class="form-control text-center" aria-label="Amount (to the nearest dollar)" readonly>
								</div>
							</div>

							<div class="col-sm-2 ">
								<div class="input-group">
									<span class="input-group-addon">总分</span> <input type="text" value="${main.totalScore }" class="form-control text-right" aria-label="Amount (to the nearest dollar)" readonly> <span class="input-group-addon">分</span>
								</div>
							</div>
						</div> <!-- /.row -->

					</td>
				</tr>
			</tbody>
		</table>
		
		<div class="col-sm-12">
			<br>
		</div> <!-- 空行 -->
		
		<div class="col-sm-12">
			<br>
		</div> <!-- 空行 -->
	</div>
	<!-- /container -->
	
	<!-- dialog -->
	<div class="modal fade" id="myModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<script src="<%=basePath%>/js/jquery.min.js"></script> 
	<script src="<%=basePath%>/dist/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/js/jquery.form.js"></script>
	<script src="<%=basePath%>/js/jquery.validate.js"></script>
	<script src="<%=basePath%>/js/additional-methods.js"></script>
	<script src="<%=basePath%>/js/messages_zh.js"></script>
	
	<script>
		function checkNum(num) {
			var regex = /^[1-9]*[0-9]*$/;
			if(!regex.test(num)) {
				return false;
			}
			return true;
		}
		function validateWeight() {
			var totalWeight = 0;
			var flag = false;
			var index = 0;
			$("#kpiForm").find("input[name='selfWeight']").each(function(i){
				var weight = $(this).val()==""?0:$(this).val();
				if(!checkNum(weight)){
					flag = true;
					index = i+1;
					return false;
				}
				totalWeight = totalWeight + parseInt(weight);
			})
			if(flag) {
				$("#model_title").text("表单异常");
				$("#model_content").text("第"+index+"个权重非正整数");
				$('#myModal').modal();
				return false;
			}
			if(totalWeight!=100) {
				$("#model_title").text("表单异常");
				$("#model_content").text("权重相加非100%");
				$('#myModal').modal();
				return false;
			}
			return true;
		}
		function alert() {
			$("#model_title").text("表单异常");
			$("#model_content").text("valid");
			$('#myModal').modal();
		}
		
		var button;
		$(document).ready(
				
				function() {
					
					$(".back_btn").on("click", function() {
						window.location.href = '<%=basePath%>/${sid}/kpi/home';
					});
					
					//自评分 选择框  第四个是 input-group div
					$(".score_a").on("click", function() {
						var score = $(this).attr("value1");
						$(this).parent().parent().parent().parent().find("input").val(score);
					});
					
					$("#kpiForm").validate({
						rules: {
							selfDirection: "requireds",
							selfGoal: "requireds",
							selfWeight: "requireds",
							selfEvaluate: "requireds",
							selfScore: "requireds",
							leaderEvaluation: "requireds",
							leaderScore: "requireds",
							
							taskContent: "requireds",
						},
						messages: {
							selfDirection: "存在方向为空",
							selfGoal: "存在个人设定目标为空",
							selfWeight: "存在权重为空",
							selfEvaluate: "存在自我评价为空",
							selfScore: "存在自评为空",
							leaderEvaluation: "存在领导评价为空",
							leaderScore: "存在审评为空",
							
							taskContent: "存在能力素质为空",
						},
						onfocusout: function(element) { 
							return ; 
						},
						onfocuson: function(element) { 
							return ; 
						},
						submitHandler: function(form) {
							
							var formParam = $("#kpiForm").formSerialize();
							
							var method = button.attr("value1");
							if(method=='submit'){
								if(!validateWeight()){
									return false;
								}
							}
							$.ajax({
								type: "post",
								url: "<%= basePath%>/${sid}/kpi/assessment/${main_id}",
								dataType: "json",
								data: formParam + "&method=" + method,
								success: function(data) {
									if(data.returncode=="200") {
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
							return false;
						},
						invalidHandler: function(form, validator) {
							return false;
						}
					});
					
					/* 提交表单 */
					$(".submit_btn").on("click", function() {
						button = $(this);
						
						$("#model_title").text("");
						$("#model_content").text("");
						$('#myModal').modal();
						
						$("#kpiForm").submit();
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
					//减少行 第6个parent 是 tr
					/* $("#selfset").on(
							"click", '.minus_btn',
							function() {
								$(this).parent().parent().parent().parent()
										.parent().parent().remove();
							});*/
					 
					//添加行  第6个parent 是 tr
					$("#selfset").on(
							"click", '.plus_btn',
							function() {
								var tr_html = '<tr><td>' + 
								'<div class="row">' + 
									'<div class="col-lg-12">' + 
										'<div class="input-group">' + 
											'<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方向&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <input name="selfDirection" type="text" class="form-control" placeholder="填写方向..."> <span class="input-group-btn">' + 
												'<button class="plus_btn btn btn-default" type="button">' + 
													'<span class="glyphicon glyphicon-plus" />' + 
												'</button>' + 
												/* '<button class="minus_btn btn btn-default" type="button">' + 
													'<span class="glyphicon glyphicon-minus" />' + 
												'</button>' +  */
											'</span>' + 
										'</div>' + 
									'</div>' + 
								'</div> <!-- /.row -->' + 

								'<div class="col-sm-12">' + 
									'<br>' + 
								'</div> <!-- 空行 -->' + 
							
								'<div class="row">' + 
									'<span class="col-sm-1 control-label text-right label_text">个人设定目标:</span>' + 
									'<div class="form-group col-sm-11">' + 
										'<textarea name="selfGoal" class="requireds form-control" rows="3" placeholder="填写个人设定目标..."></textarea>' + 
									'</div>' + 
								'</div> <!-- /.row -->' + 


								'<div class="row">' + 
									'<div class="col-sm-2 col-sm-offset-10">' + 
										'<div class="input-group">' + 
											'<span class="input-group-addon">权重</span> <input name="selfWeight" type="text" class="form-control text-right" aria-label="Amount (to the nearest dollar)"> <span class="input-group-addon">%</span>' + 
										'</div>' + 
									'</div>' + 
								'</div> <!-- /.row -->' + 

								'<div class="col-sm-12">' + 
									'<br>' + 
								'</div> <!-- 空行 -->' + 
								
								'<div class="row">' + 
									'<span class="col-sm-1 control-label text-right">自我评价:</span>' + 
									'<div class="form-group col-sm-11">' + 
										'<textarea class="requireds form-control" rows="3" readonly></textarea>' + 
									'</div>' + 
								'</div> <!-- /.row -->' + 
								
								'<div class="row">' + 
									'<div class="col-sm-2 col-sm-offset-10">' + 
										'<div class="input-group">' + 
											'<span class="input-group-addon">自评</span> <input type="text" class="form-control text-right" aria-label="..." readonly> <span class="input-group-addon">分</span>' + 
										'</div>' + 
									'</div>' + 
								'</div> <!-- /.row -->' + 
								
								'<div class="col-sm-12">' + 
									'<br>' + 
								'</div> <!-- 空行 -->' + 
								
								'<div class="row">' + 
									'<span class="col-sm-1 control-label text-right">领导评价:</span>' + 
									'<div class="form-group col-sm-11">' + 
										'<textarea class="requireds form-control" rows="3" readonly></textarea>' + 
									'</div>' + 
								'</div> <!-- /.row -->' + 

								'<div class="row">' + 
									'<div class="col-sm-2 col-sm-offset-10">' + 
										'<div class="input-group">' + 
											'<span class="input-group-addon">审评</span> <input type="text" class="form-control text-right" aria-label="..." readonly> <span class="input-group-addon">分</span>' + 
										'</div>' + 
									'</div>' + 
								'</div> <!-- /.row -->' + 

							'</td></tr>' ;
							
								var tbody = $(this).parent().parent().parent()
										.parent().parent().parent().parent();
								tbody.append(tr_html);
							});

				})
	</script>
</body>
</html>
