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
				<a class="navbar-brand" href="#">KPI系统</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">

				<ul class="nav navbar-nav navbar-right">

					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">王林 <span class="caret"></span></a>
						<ul class="dropdown-menu">

							<!-- <li><a href="#">修改密码</a></li> -->
							<li role="separator" class="divider"></li>
						</ul></li>
					<li><a href="1login.html">退出</a></li>
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
			<div class="panel-heading">KPI->&nbsp;
				<c:choose>
					<c:when test="selfDirection == 3">
						<!-- 起草 -->
						起草任务
					</c:when>

					<c:when test="selfEvaluate == 3">
						<!-- 任务自评 -->
						任务自评
					</c:when>

					<c:when test="leaderEvaluation == 3">
						<!-- 领导审评 -->
						领导审评
					</c:when>

					<c:when test="method == view">
						<!-- 查看 -->
						查看详情
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
			<table class="table">
				<tbody>
					<c:forEach items="${selfAttributes}" var="self">
						<tr>
							<td>
								<!-- selfDirection start --> 
								<c:if test="selfDirection_access == 1">

									<div class="row">
										<div class="col-lg-12">
											<div class="input-group">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方向&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <input name="selfDirection" type="text" class="form-control" placeholder="填写方向..." value="${self.selfDirection}" readonly>
											</div>
										</div>
									</div>
									<!-- /.row -->

								</c:if> 
								<c:if test="selfDirection_access == 3">

									<div class="row">
										<div class="col-lg-12">
											<div class="input-group">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方向&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <input name="selfDirection" type="text" class="form-control" placeholder="填写方向..." value="${self.selfDirection}" > 
												<span class="input-group-btn">
													<button class="plus_btn btn btn-default" type="button">
														<span class="glyphicon glyphicon-plus" />
													</button>
													<button class="minus_btn btn btn-default" type="button">
														<span class="glyphicon glyphicon-minus" />
													</button>
												</span>
											</div>
										</div>
									</div>
									<!-- /.row -->

								</c:if> <!-- selfDirection end -->
								
								<!-- selfGoal start -->
								 <c:if test="selfGoal_access == 1">
									<div class="row">
										<span class="col-sm-1 control-label text-right label_text">个人设定目标:</span>
										<div class="form-group col-sm-11">
											<textarea name="selfGoal" class="form-control" rows="3" readonly>${self.selfGoal }</textarea>
										</div>
									</div>
								</c:if>
								<c:if test="selfGoal_access == 3">
									<div class="row">
										<span class="col-sm-1 control-label text-right label_text">个人设定目标:</span>
										<div class="form-group col-sm-11">
											<textarea name="selfGoal" class="form-control" rows="3" >${self.selfGoal }</textarea>
										</div>
									</div>
								</c:if><!-- selfGoal end -->
								
								<!-- selfWeight start -->
								<c:if test="selfGoal_access == 1">
									<div class="row">
										<div class="col-sm-2 col-sm-offset-10">
											<div class="input-group">
												<span class="input-group-addon">权重</span> <input type="text" name="selfWeight" class="form-control text-right" aria-label="Amount (to the nearest dollar)" value="${self.selfWeight }" readonly> <span class="input-group-addon">%</span>
											</div>
										</div>
									</div> <!-- /.row -->
								</c:if>
								<c:if test="selfGoal_access == 3">
									<div class="row">
										<div class="col-sm-2 col-sm-offset-10">
											<div class="input-group">
												<span class="input-group-addon">权重</span> <input type="text" name="selfWeight" class="form-control text-right" aria-label="Amount (to the nearest dollar)" value="${self.selfWeight }"> <span class="input-group-addon">%</span>
											</div>
										</div>
									</div> <!-- /.row -->
								</c:if> <!-- selfWeight end -->
								
								
								<!-- selfEvaluate start -->
								<c:if test="selfEvaluate_access == 1">
									<div class="row">
										<span class="col-sm-1 control-label text-right">自我评价:</span>
										<div class="form-group col-sm-11">
											<textarea name="selfEvaluate" class="form-control" rows="3" readonly>${self.selfEvaluate }</textarea>
										</div>
									</div>
								</c:if>
								<c:if test="selfEvaluate_access == 3">
									<div class="row">
										<span class="col-sm-1 control-label text-right">自我评价:</span>
										<div class="form-group col-sm-11">
											<textarea name="selfEvaluate" class="form-control" rows="3" >${self.selfEvaluate }</textarea>
										</div>
									</div>
								</c:if> <!-- selfEvaluate end -->


								<!-- selfScore start -->
								<c:if test="selfScore_access == 1">
									<div class="row">
										<div class="col-sm-2 col-sm-offset-10">
											<div class="input-group">
												<span class="input-group-addon">自评</span> <input name="selfScore" type="text" class="form-control text-right" aria-label="..." value="${self.selfScore}" readonly> 
												<span class="input-group-addon">分</span>
											</div>
										</div>
									</div> <!-- /.row -->
								</c:if>
								
								<c:if test="selfScore_access == 3">
									<div class="row">
										<div class="col-sm-2 col-sm-offset-10">
											<div class="input-group">
												<span class="input-group-addon">自评</span> <input name="selfScore" type="text" class="form-control text-right" aria-label="..." value="${self.selfScore}">
												<div class="input-group-btn">
													<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
														分<span class="caret"></span>
													</button>
													<ul class="dropdown-menu dropdown-menu-right">
														<li><a class="selfScore_a" href="#" value1="5">5</a></li>
														<li role="separator" class="divider"></li>
														<li><a class="selfScore_a" href="#" value1="4">4</a></li>
														<li role="separator" class="divider"></li>
														<li><a class="selfScore_a" href="#" value1="3.75">3.75</a></li>
														<li><a class="selfScore_a" href="#" value1="3.5">3.5</a></li>
														<li><a class="selfScore_a" href="#" value1="3.25">3.25</a></li>
														<li><a class="selfScore_a" href="#" value1="3">3</a></li>
														<li role="separator" class="divider"></li>
														<li><a class="selfScore_a" href="#" value1="2">2</a></li>
													</ul>
												</div>
											</div>
										</div>
									</div> <!-- /.row -->

								</c:if>
								<!-- selfScore end -->
								
								<!-- leaderEvaluation start -->
								<c:if test="leaderEvaluation_access == 1">
									<div class="row">
										<span class="col-sm-1 control-label text-right">领导评价:</span>
										<div class="form-group col-sm-11">
											<textarea name="leaderEvaluation" class="form-control" rows="3" value="${self.leaderEvaluation}" readonly></textarea>
										</div>
									</div> <!-- /.row -->
								</c:if>
								<c:if test="leaderEvaluation_access == 3">
									<div class="row">
										<span class="col-sm-1 control-label text-right">领导评价:</span>
										<div class="form-group col-sm-11">
											<textarea name="leaderEvaluation" class="form-control" rows="3" value="${self.leaderEvaluation}"></textarea>
										</div>
									</div> <!-- /.row -->
								</c:if>
								<!-- leaderEvaluation end -->
								
								<!-- leaderScore start -->
								<c:if test="leaderScore_access == 1">
									<div class="row">
										<div class="col-sm-2 col-sm-offset-10">
											<div class="input-group">
												<span class="input-group-addon">审评</span> <input name="leaderScore" type="text" class="form-control text-right" aria-label="..." value="${self.leaderScore}" readonly> <span class="input-group-addon">分</span>
											</div>
										</div>
									</div> <!-- /.row -->
								</c:if>
								<c:if test="leaderScore_access == 3">
									<div class="row">
										<div class="col-sm-2 col-sm-offset-10">
											<div class="input-group">
												<span class="input-group-addon">审评</span> <input name="leaderScore" type="text" class="form-control text-right" aria-label="..." value="${self.leaderScore}">
												<div class="input-group-btn">
													<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
														分<span class="caret"></span>
													</button>
													<ul class="dropdown-menu dropdown-menu-right">
														<li><a href="#">5</a></li>
														<li role="separator" class="divider"></li>
														<li><a href="#">4</a></li>
														<li role="separator" class="divider"></li>
														<li><a href="#">3.75</a></li>
														<li><a href="#">3.5</a></li>
														<li><a href="#">3.25</a></li>
														<li><a href="#">3</a></li>
														<li role="separator" class="divider"></li>
														<li><a href="#">2</a></li>
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
					
					<!-- 如果起草阶段，接一个空的 个人目标  -->
					<c:if test="selfDirection == 3">
						<tr> 
							<td>
								<div class="row">
									<div class="col-lg-12">
										<div class="input-group">
											<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方向&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <input type="text" class="form-control" placeholder="填写方向..."> <span class="input-group-btn">
												<button class="btn btn-default" type="button">
													<span class="glyphicon glyphicon-plus" />
												</button>
												<button class="btn btn-default" type="button">
													<span class="glyphicon glyphicon-minus" />
												</button>
											</span>
										</div>
									</div>
								</div> <!-- /.row -->

								<div class="row">
									<span class="col-sm-1 control-label text-right label_text">个人设定目标:</span>
									<div class="form-group col-sm-11">
										<textarea class="form-control" rows="3" placeholder="填写个人设定目标..."></textarea>
									</div>
								</div> <!-- /.row -->

								<div class="row">
									<div class="col-sm-2 col-sm-offset-10">
										<div class="input-group">
											<span class="input-group-addon">权重</span> <input type="text" class="form-control text-right" aria-label="Amount (to the nearest dollar)"> <span class="input-group-addon">%</span>
										</div>
									</div>
								</div> <!-- /.row -->

								<div class="row">
									<span class="col-sm-1 control-label text-right">自我评价:</span>
									<div class="form-group col-sm-11">
										<textarea class="form-control" rows="3" readonly></textarea>
									</div>
								</div> <!-- /.row -->
								
								<div class="row">
									<div class="col-sm-2 col-sm-offset-10">
										<div class="input-group">
											<span class="input-group-addon">自评</span> <input type="text" class="form-control text-right" aria-label="..." readonly> <span class="input-group-addon">分</span>
										</div>
									</div>
								</div> <!-- /.row -->

								<div class="row">
									<span class="col-sm-1 control-label text-right">领导评价:</span>
									<div class="form-group col-sm-11">
										<textarea class="form-control" rows="3" readonly></textarea>
									</div>
								</div> <!-- /.row -->
								
								<div class="row">

									<div class="col-sm-2 col-sm-offset-10">
										<div class="input-group">
											<span class="input-group-addon">审评</span> <input type="text" class="form-control text-right" aria-label="..." readonly> <span class="input-group-addon">分</span>
										</div>
									</div>
								</div> <!-- /.row -->
							</td>
						</tr>
					</c:if>
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
								<c:if test="taskDirection == 1">
									<div class="row">
										<div class="col-lg-12">
											<div class="input-group">
												<span class="input-group-addon" >能力素质</span> <input name="taskDirection" value="${cultural.taskDirection}" type="text" class="form-control" placeholder="填写能力素质...(1、评价标准参见《宜信员工能力素质指标库》。2、建议选取3-5个指标。)">
											</div>
										</div>
									</div> <!-- /.row -->
								</c:if>
								
								<c:if test="taskDirection == 3">
									<div class="row">
										<div class="col-lg-12">
											<div class="input-group">
												<span class="input-group-addon" >能力素质</span> <input name="taskDirection" value="${cultural.taskDirection}" type="text" class="form-control" placeholder="填写能力素质...(1、评价标准参见《宜信员工能力素质指标库》。2、建议选取3-5个指标。)">
											</div>
										</div>
									</div> <!-- /.row -->
								</c:if>
								
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
		<c:choose>
			<c:when test="selfDirection == 3">
			<!-- 起草 -->
				<div class="span7 text-center">
					<button id="save_btn" type="button" class="btn btn-primary btn-lg">保存草稿</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button id="submit_btn" type="button" class="btn btn-primary btn-lg">提交审批</button>
				</div>
			</c:when>
		
			<c:when test="selfEvaluate == 3">
			<!-- 任务自评 -->
				<div class="span7 text-center">
					<button id="save" type="button" class="btn btn-primary btn-lg">保存自评</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button id="evaluation" type="button" class="btn btn-primary btn-lg">提交审评</button>
				</div>
			</c:when>
			
			<c:when test="leaderEvaluation == 3">
			<!-- 领导审评 -->
				<div class="span7 text-center">
					<button id="self_rating" type="button" class="btn btn-primary btn-lg">驳回</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button id="viewkpi" type="button" class="btn btn-primary btn-lg">审评完成</button>
				</div>
			</c:when>
			
			<c:when test="method == view">
			<!-- 查看 -->
				<div class="span7 text-center">
					<button id="back_btn" type="button" class="btn btn-primary btn-lg">返回</button>
				</div>
			</c:when>
			<c:otherwise>
			<!-- 领导审批 -->
				<div class="span7 text-center">
					<button id="kpi" type="button" class="btn btn-primary btn-lg">不通过审批</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button id="verify" type="button" class="btn btn-primary btn-lg">通过审批</button>
				</div>
			</c:otherwise>
		</c:choose>
		

	</div>
	<!-- /container -->

	<script src="<%=basePath%>/js/jquery.min.js"></script>
	<script src="<%=basePath%>/dist/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/js/jquery.form.js"></script>
	
	<script>
		$(document).ready(
				function() {

					//减少行 第6个parent 是 tr
					$(".plus_btn").on(
							"click",
							function() {
								$(this).parent().parent().parent().parent()
										.parent().parent().remove();
							});

					//自评分 选择框  第四个是 input-group div
					$(".selfScore_a").on("click", function() {
						var score = $(this).attr("value1");
						$(this).parent().parent().parent().parent().find("input").value(score);
					});
					$("#save_btn").on("click", function() {
						window.location.href = './3kpi.html';
					});
					$("#submit_btn").on("click", function() {
						window.location.href = './5verify_task.html';
					});
					$("#back_btn").on("click", function() {
						window.location.href = '<%=basePath%>/${sid}/kpi/home';;
					});
					
					//添加行  第6个parent 是 tr
					$(".plus_btn").on(
							"click",
							function() {
								var tr_html = '<tr><td>' + 
								'<div class="row">' + 
									'<div class="col-lg-12">' + 
										'<div class="input-group">' + 
											'<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方向&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <input name="selfDirection" type="text" class="form-control" placeholder="填写方向..."> <span class="input-group-btn">' + 
												'<button class="btn btn-default" type="button">' + 
													'<span class="glyphicon glyphicon-plus" />' + 
												'</button>' + 
												'<button class="btn btn-default" type="button">' + 
													'<span class="glyphicon glyphicon-minus" />' + 
												'</button>' + 
											'</span>' + 
										'</div>' + 
									'</div>' + 
								'</div> <!-- /.row -->' + 

								'<div class="row">' + 
									'<span class="col-sm-1 control-label text-right label_text">个人设定目标:</span>' + 
									'<div class="form-group col-sm-11">' + 
										'<textarea name="selfGoal" class="form-control" rows="3" placeholder="填写个人设定目标..."></textarea>' + 
									'</div>' + 
								'</div> <!-- /.row -->' + 


								'<div class="row">' + 
									'<div class="col-sm-2 col-sm-offset-10">' + 
										'<div class="input-group">' + 
											'<span class="input-group-addon">权重</span> <input name="selfWeight" type="text" class="form-control text-right" aria-label="Amount (to the nearest dollar)"> <span class="input-group-addon">%</span>' + 
										'</div>' + 
									'</div>' + 
								'</div> <!-- /.row -->' + 

								'<div class="row">' + 
									'<span class="col-sm-1 control-label text-right">自我评价:</span>' + 
									'<div class="form-group col-sm-11">' + 
										'<textarea class="form-control" rows="3" readonly></textarea>' + 
									'</div>' + 
								'</div> <!-- /.row -->' + 
								
								'<div class="row">' + 
									'<div class="col-sm-2 col-sm-offset-10">' + 
										'<div class="input-group">' + 
											'<span class="input-group-addon">自评</span> <input type="text" class="form-control text-right" aria-label="..." readonly> <span class="input-group-addon">分</span>' + 
										'</div>' + 
									'</div>' + 
								'</div> <!-- /.row -->' + 

								'<div class="row">' + 
									'<span class="col-sm-1 control-label text-right">领导评价:</span>' + 
									'<div class="form-group col-sm-11">' + 
										'<textarea class="form-control" rows="3" readonly></textarea>' + 
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
								$(tbody).append(tr_html);
							});

				})
	</script>
</body>
</html>
