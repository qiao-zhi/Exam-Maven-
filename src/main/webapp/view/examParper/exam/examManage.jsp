<!-- 考试管理 -->
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
<title>考试管理</title>
<!-- 设置一全局变量记录工程名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<%@ include file="/public/cssJs.jsp"%>

<!--下拉树-->
<!--索引中选择部门-->
<script type="text/javascript" src="<%=path%>/js/public/tree.js"></script>
<!-- QLQ引入的转换日期的JS -->
<script type="text/javascript" src="<%=path%>/js/public/dateformat.js"></script>
<link rel="stylesheet" href="<%=path%>/css/public/tree.css">

<script src="<%=path%>/js/examParper/exam/examManage.js"></script>
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
						<span class="el_stairTitle">培训管理</span><span>>考试管理</span>
					</div>
					<div class="panel-body el_main">

						<!--内容-->
						<div class=" col-md-12">

							<!--索引-->
							<div class="row el_queryBox">
								<form action="" id="queryExam">
									<!--隐藏当前页与页大小  -->
									<input type="hidden" name="currentPage" id="currentPage">
									<input type="hidden" name="currentCount" id="currentCount">
									<!--隐藏考试ID  -->
									<input type="hidden" name="examId" id="examId">

									<div class="row el_queryBoxrow">

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">考试名称：</span> <input type="text"
													class="form-control" name="examName" />
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">考试级别：</span> <select
													class="selectpicker form-control" name="level" title="请选择">
													<option value="">--请选择--</option>
													<option value="1">一级(厂级)</option>
													<option value="2">二级(部门级)</option>
													<option value="3">三级(班组级)</option>
												</select>
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">考试状态：</span> <select
													class="selectpicker form-control" name="status" title="请选择">
													<option value="">--请选择--</option>
													<option value="未开始">未开始</option>
													<option value="正在答题">正在答题</option>
													<option value="已结束">已结束</option>
												</select>
											</div>
										</div>

									</div>

									<div class="row el_queryBoxrow">

										<!-- <div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans el_chooseSpan">所属部门：</span>
												<ul id="log" class="el_modelinput el_chooseInput log"></ul>
												<img src="../../controls/selectDropTree/smallTriangle.png"
													class="el_smallTriangle" width="7" />
												<ul id="treeDemo2" class="ztree"></ul>
											</div>
										</div> -->

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">考试时间：</span> <input type="text"
													id="test" class="wicon el_noVlaue form-control"
													name="month" readonly />
											</div>
										</div>

										<!-- 检修名称 -->
										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" id="bigNameDiv">
												<span class="el_spans">检修名称：</span><select
													class="selectpicker form-control" title="请选择" name="bigId"
													id="bigName">
													<option value="" selected>---请选择---</option>
												</select>
											</div>
										</div>

									</div>
									<!--清空按钮-->
									<button type="reset"
										class="btn btn-default el_queryButton0 btn-sm">清空</button>
									<!--提交查询按钮-->
									<button type="button"
										class="btn btn-primary el_queryButton btn-sm"
										id="queryExamBtn">查询</button>
								</form>

							</div>
							<!--结束 查询表单提交-->

							<!--显示内容-->
							<h3 class="el_mainHead">考试信息</h3>
							<div class="panel panel-default el_Mainmain">

								<!--按钮面板-->
								<div class="panel-body">

									<div class="panel panel-default">
										<div class="panel-body el_MainxiaoMain">

											<div class="el_topButton">
												<!-- 按钮触发模态框1-->
												<div class="col-md-2">
													<a href="<%=path%>/view/examParper/exam/addExam.jsp">
														<button class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>创建考试</button>
													</a>
												</div>
											</div>

										</div>
									</div>

									<!--表格
                                内容都提取到json里边
                            -->
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>序号</th>
												<th>名称</th>
												<th>所属检修</th>
												<th>级别</th>
												<th>考试时长</th>
												<th>类型</th>
												<th>参考人数</th>
												<!-- <th>考试地点</th> -->
												<th>考试时间</th>
												<th>状态</th>
												<th width="160">操作</th>
											</tr>
										</thead>
										<tbody id="examTableBody">
											<%-- <tr>
												<td>内啊手动阀</td>
												<td>1级</td>
												<td>20分钟</td>
												<td><a class="el_delButton" onClick="examPersons2()">20</a>
												</td>
												<td>工厂阶教</td>
												<td>2017-12-20 10:00AM -- 12:00AM</td>
												<td class="danger">未考</td>
												<td><a href="<%=path%>/view/examParper/modifyExam.jsp">修改</a>
													<a class="el_delButton" onClick="delcfm()">删除</a> <a
													href="<%=path%>/view/examParper/parperModel.jsp">预览试卷</a></td>
											</tr> --%>
										</tbody>
									</table>

									<!-- 模态框 查看已考考试的参考员工信息-->
									<div class="modal fade" id="examPersons" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" style="width:1000px">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<!--关闭符号-->
													<!--标题-->
													<h4 class="modal-title" id="myModalLabel1">参考人员信息</h4>
												</div>
												<form>
													<div class="modal-body">
														<table class="table table-bordered">
															<thead>
																<tr>
																	<th>序号</th>
																	<th>姓名</th>
																	<th>身份证号</th>
																	<th>性别</th>
																	<th>所属部门</th>
																	<th>员工类型</th>
																	<th>考试方式</th>
																</tr>
															</thead>
															<tbody id="examEmployeesTable">
															</tbody>
														</table>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">关闭</button>

														<button type="button" id="outPutBtn"
															class="btn btn-primary">导出参考人员信息</button>
													</div>
												</form>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal -->
									</div>

									<!-- 模态框 查看未考考试考试的参考员工信息-->
									<div class="modal fade" id="examPersons2" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<!--关闭符号-->
													<!--标题-->
													<h4 class="modal-title" id="myModalLabel">参考人员信息</h4>
												</div>
												<form>
													<div class="modal-body">
														<table class="table table-bordered">
															<thead>
																<tr>
																	<th>姓名</th>
																	<th>性别</th>
																	<th>身份证</th>
																	<th>所属部门</th>
																	<th>考试情况</th>
																	<th>成绩</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>王菲</td>
																	<td>nan</td>
																	<td>1838235486</td>
																	<td>234234234</td>
																	<td>通过一级考试</td>
																	<td>23</td>
																</tr>
															</tbody>
														</table>
													</div>
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

									<!--分页-->
									<div id="paginationID" class="paginationID"></div>
								</div>
							</div>

							<!-- 模态框   信息删除确认 -->
							<div class="modal fade" id="delcfmModel">
								<div class="modal-dialog">
									<div class="modal-content message_align">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<h4 class="modal-title">提示</h4>
										</div>
										<div class="modal-body">
											<p>您确认要删除该试卷吗？</p>
										</div>
										<div class="modal-footer">
											<input type="hidden" id="url" />
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<a onclick="urlSubmit()" href="javascript:void(0)"
												class="btn btn-success" data-dismiss="modal">确定</a>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
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