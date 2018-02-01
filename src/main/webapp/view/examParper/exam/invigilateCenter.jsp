<!-- 监考中心 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/public/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考试监考中心</title>
<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<%@ include file="/public/cssJs.jsp"%>

<!--分页-->
<script src="<%=path%>/js/public/page.js"></script>

<script src="<%=path%>/js/examParper/exam/invigilateCenter.js"></script>
<link rel="stylesheet" href="<%=path%>/css/examParper/examParper.css">
</head>
<body>

	<!--头-->
	<jsp:include page="/view/public/header.jsp"></jsp:include>

	<!--中部-->
	<div class="html_middle">

		<!--放菜单框-->
		<div class="el_left">
			<jsp:include page="/view/public/menu.jsp"></jsp:include>
		</div>

		<!--放主界面内容-->
		<div class="el_right">

			<div class="container-fluid">
				<div class="panel panel-default">
					<!--菜单连接标题-->
					<div class="panel-heading">
						<span>考试及试卷管理</span><span>>监考中心</span>
					</div>

					<div class="panel-body el_main">

						<!--内容-->
						<div class="col-md-12">

							<!--索引-->
							<div class="row el_queryBox">
								<form action="">

									<div class="row el_queryBoxrow">
										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">考试名称：</span> <input type="text"
													class="form-control" name="" />
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">考试级别：</span> <select
													class="selectpicker form-control" title="请选择">
													<option>--请选择--</option>
													<option>一级</option>
													<option>二级</option>
													<option>三级</option>
												</select>
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">考试时间：</span> <input type="text"
													id="test" class="wicon form-control" readonly />
											</div>
										</div>

									</div>

									<div class="row el_queryBoxrow">

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">考试状态：</span> <select
													class="selectpicker form-control" title="请选择">
													<option>--请选择--</option>
													<option>未考</option>
													<option>已考</option>
													<option>考试中</option>
												</select>
											</div>
										</div>
									</div>
									<!--清空按钮-->
									<button type="reset"
										class="btn btn-default el_queryButton0 btn-sm">清空</button>
									<!--提交查询按钮-->
									<button type="submit"
										class="btn btn-default el_queryButton btn-sm">查询</button>
								</form>

							</div>
							<!--结束 查询表单提交-->

							<!--显示内容-->
							<h4 class="el_mainHead">监考中心</h4>
							<div class="panel panel-default el_Mainmain">

								<div class="panel-body">

									<!--按钮面板-->
									<div class="el_topButton">
										<a href="<%=path%>/view/examParper/invigilatePersonCenter.jsp"
											id="el_lookEmp">
											<button class="btn btn-primary">考试详情查看</button>
										</a>
									</div>

									<!--表格
                            内容都提取到json里边
                        -->
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>选择</th>
												<th>考试名称</th>
												<th>考试级别</th>
												<th>考试时长</th>
												<th>考试时间</th>
												<th>考试状态</th>
												<th>参考人数</th>
												<th>线上考试人数</th>
												<th>正考</th>
												<th>考完</th>
												<th>未完</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><input type="radio" name="el_chooseExam"
													class="el_checks" /></td>
												<td>******考试</td>
												<td>一级</td>
												<td>120分钟</td>
												<td>2011-10-12 10:00AM -- 12:00AM</td>
												<td>考试中</td>
												<td><a href="javascript:void(0)"
													onclick="examPersons()"> 20 </a></td>
												<td><a
													href="<%=path%>/view/examParper/invigilatePersonCenter.jsp">
														10 </a></td>
												<td><a
													href="<%=path%>/view/examParper/invigilatePersonCenter.jsp">
														8 </a></td>
												<td><a
													href="<%=path%>/view/examParper/invigilatePersonCenter.jsp">
														1 </a></td>
												<td><a
													href="<%=path%>/view/examParper/invigilatePersonCenter.jsp">
														1 </a></td>
											</tr>
										</tbody>
									</table>

									<div id="paginationID"></div>

								</div>
							</div>

							<!-- 模态框 查询所有考试员工信息-->
							<div class="modal fade" id="examPersons" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel">添加考试员工</h4>
										</div>
										<form>
											<!--索引-->
											<div class="el_queryBox">
												<form action="">

													<div class="row el_queryBoxrow">

														<div class="el_qlmQuery">
															<div class="input-group" role="toolbar">
																<span class="el_spans  el_spans2">姓名：</span> <input
																	type="text" class="form-control" name="" />
															</div>
														</div>

														<div class="el_qlmQuery">
															<div class="input-group" role="toolbar">
																<span class="el_spans el_spans2">考试方式：</span> <select
																	class="selectpicker form-control" title="请选择">
																	<option>--请选择--</option>
																	<option>在线考试</option>
																	<option>线下考试</option>
																</select>
															</div>
														</div>

														<div class="el_qlmQuery">
															<div class="input-group" role="toolbar">
																<span class="el_spans el_spans2">身份证：</span> <input
																	type="text" class="form-control" name="" width="120" />
															</div>
														</div>
													</div>

													<div class="row el_queryBoxrow">

														<div class="el_qlmQuery">
															<div class="input-group" role="toolbar">
																<span class="el_spans el_spans2">性别：</span> <label
																	class="el_radioBox"><input type="radio"
																	name="el_gender"> 男</label> <label class="el_radioBox"><input
																	type="radio" name="el_gender"> 女</label>
															</div>
														</div>
														<!--提交查询按钮-->
													</div>

													<button type="submit"
														class="btn btn-default el_queryButton btn-sm">查询</button>

												</form>
											</div>
											<!--结束 查询表单提交-->

											<!--表格内容-->
											<table
												class="table el_AddPersonTable table-bordered table-hover">
												<thead>
													<tr>
														<th>序号</th>
														<th>姓名</th>
														<th>性别</th>
														<th>身份证</th>
														<th>所属部门</th>
														<th>考试方式</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>1</td>
														<td>王菲</td>
														<td>nan</td>
														<td>1838235486</td>
														<td>234234234</td>
														<td>在线答题</td>
													</tr>
													<tr>
														<td>2</td>
														<td>王菲</td>
														<td>nan</td>
														<td>1838235486</td>
														<td>234234234</td>
														<td>线下考试</td>
													</tr>
												</tbody>
											</table>
											<!--分页-->
											<div id="paginationIDM"></div>

											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>

						</div>

					</div>

				</div>
			</div>


		</div>
	</div>

	<!--放脚-->
	<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>
