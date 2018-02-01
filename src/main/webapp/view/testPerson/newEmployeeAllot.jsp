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
<title>内部员工培训</title>

<%@ include file="/public/cssJs.jsp"%>
<link rel="stylesheet" href="<%=path%>/css/outDepart/outdepartTree.css">

<script src="<%=path%>/js/newPeopleTrain/newEmployeeAllot.js"></script>
<link rel="stylesheet"
	href="<%=path%>/css/outDepart/outEmployeeAllot.css">
<!-- 日期格式转换 -->
<script
	src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>

<script>
	//定义一个全局变量
	var baseurl = "${pageContext.request.contextPath}";
</script>
<%-- <shiro:hasPermission name="empout:operating">
<script>
hasOperatingEmpout = true;
</script>
</shiro:hasPermission> --%>
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
						<span>培训管理</span><span>>员工培训</span><span>>内部员工培训</span>
					</div>
					<div class="el_main">

						<!--树-->
						<div class="el_leftTree">
							<!--标题类，添加了一个颜色-->
							<span class="el_treeTitle">部门</span>
							<ul id="departmentAndOverHaulTree" class="ztree"></ul>
						</div>

						<!--内容-->
						<div class="el_qlmContent">

							<!--索引-->
							<div class="row el_queryBox">
								<form id="queryDistributeInfoForm">
									<!-- 隐藏一些查询条件，隐患ID，部门ID -->
									<input type="hidden" name="distributeStatus" />
									<!-- 增加标记外来单位员工管理 -->
									<input type="hidden" name="markTrainType" value="0" />

									<div class="row el_queryBoxrow">
										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
												<input type="text" class="form-control clearInput"
													name="employeeOutName" />
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">性&nbsp;&nbsp;&nbsp;别：</span> <label
													class="el_radioBox"><input type="radio"
													name="employeeOutSex" value="1"> 男</label> <label
													class="el_radioBox clearInput"><input type="radio"
													class="clearInput" name="employeeOutSex" value="2">
													女</label>
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">身&nbsp;份&nbsp;&nbsp;证：</span> <input
													type="text" class="form-control clearInput"
													name="employeeOutIdCard" />
											</div>
										</div>
									</div>


									<!-- 隐藏部门ID和大修ID -->
									<input type="hidden" name="unitId" id="query_unitId"
										class="clearInput" /> <input type="hidden" name="bigId"
										id="query_bigId" class="clearInput" />
									<!-- 隐藏当前页和显示条数 -->
									<input type="hidden" name="currentPage" id="currentPage" /> <input
										type="hidden" name="currentCount" id="currentCount" />
									<!--提交查询按钮-->
									<button type="button"
										class="btn btn-primary el_queryButton btn-sm"
										onclick="clearPageQuery()">查询</button>
									<button type="reset"
										class="btn btn-default el_queryButton0 btn-sm"
										onclick="clearQueryInfo()">清空</button>
								</form>

							</div>
							<!--结束 查询表单提交-->
							<!-- 部门基本信息 -->
							<div id="unitInfoDiv" style="display: none;">
								<h3 class="el_mainHead">部门基本信息</h3>
								<div class="panel panel-default el_Mainmain">
									<div class="panel-body">
										<table class="table table-hover table-bordered">
											<thead>
												<tr>
													<th>单位名称</th>
													<th>项目经理</th>
													<th>经理电话</th>
													<th>安全员</th>
													<th>安全员电话</th>
													<th>参与项目</th>
													<th>单位人数</th>
													<th>违章积分</th>
												</tr>
											</thead>
											<tbody id="unitTbody">
											</tbody>
										</table>
										<!-- 部门基本信息结束 -->
									</div>
								</div>
							</div>

							<!--显示内容-->
							<h3 class="el_mainHead">分配员工</h3>
							<!--按钮区-->
							<div class="panel panel-default">
								<div class="panel-body el_MainxiaoMain">

									<div class="el_topButton">

										<select class="btn btn-primary" id="el_showManager"
											title="请选择" onchange="selectFenpeiInfo()" name="examLevel">
											<option value="">查看所有</option>
											<option value="0">未参加培训</option>
											<option value="1">待分配</option>
											<option value="2">正在培训</option>
											<option value="3">已合格</option>
											<option value="4">已入职</option>
										</select>
										<button class="btn btn-primary" id="lookTrainInfo"
											onclick="lookTrainInfo()">查看培训档案员工</button>
										<button class="btn btn-primary" id="el_lookTrainDocument"
											style="display: none" onclick="el_empTrainDoc()">分配员工</button>
										<shiro:hasPermission name="grademanager:printcard">
											<button style="display: none" id="generateWork"
												class="btn btn-primary" onclick="el_empCardModel()">
												新员工入职</button>
										</shiro:hasPermission>
										<shiro:hasPermission name="grademanager:redisdepart">
											<button style="display: none" id="reDistributeDepart"
												class="btn btn-primary" onclick="reDstributeDepart()">
												二次分配</button>
										</shiro:hasPermission>
									</div>
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th><input type="checkbox" id="empCheckAll"></th>
												<th>序号</th>
												<th>姓名</th>
												<th>性别</th>
												<th>身份证</th>
												<th>培训类别</th>
												<th>所属单位</th>
												<th>工种</th>
												<th>违章积分</th>
												<th>分配部门</th>
												<th>分配班组</th>
												<th class="operate">操作</th>
											</tr>
										</thead>
										<tbody id="employeeOutBaseInfoList">
										</tbody>
									</table>

									<!--分页-->
									<div id="paginationIDU" class="paginationID"></div>
								</div>
							</div>
							<br /> <br /> <br /> <br /> <br /> <br />
							<div class="modal fade" id="el_empTrainDoc" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								data-backdrop="static" data-keyboard="false" aria-hidden="true">
								<div class="modal-dialog" style="width: 50%;">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel234">分配员工</h4>
										</div>
										<form>
											<!--树-->
											<div class="el_leftTree">
												<!--标题类，添加了一个颜色-->
												<span class="el_treeTitle">部门</span>
											</div>

											<ul id="treeDemo_permission" class="ztree"
												style="width: auto !important; height: auto !important; border: none !important;"></ul>

											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary"
													onclick="saveFenpei()">保存</button>
											</div>
										</form>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>



							<!-- 隱藏提交的 部门ID-->
							<input type="hidden" id="selectDepartmentId" />
							<!--隐藏提交的表单元素  -->
							<form id="submitFenpeiForm"></form>

							<!-- 模态框 修改分配员工的树-->
							<div class="modal fade" id="el_changeAllot" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								data-backdrop="static" data-keyboard="false" aria-hidden="true">
								<div class="modal-dialog" style="width: 50%;">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel234">分配员工</h4>
										</div>
										<table class="table table-hover table-bordered">
											<thead>
												<tr>
													<th>姓名</th>
													<th>性别</th>
													<th>身份证</th>
													<th>培训类别</th>
													<th>所属单位</th>
												</tr>
											</thead>
											<tbody id="employeeOutUpdateInfo">
											</tbody>
										</table>
										<form id="updateFenpeiForm"></form>
										<form>
											<!--  -->

											<!--树-->
											<div class="el_leftTree">
												<!--标题类，添加了一个颜色-->
												<span class="el_treeTitle">部门</span>
											</div>

											<ul id="treeDemo_permission1" class="ztree"
												style="width: auto !important; height: auto !important; border: none !important;"></ul>

											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary"
													onclick="updateFenpeiInfo()">保存</button>
											</div>
										</form>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>


							<!-- 模态框 生成工作证-->
							<div class="modal fade" id="el_empCardModel" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								data-backdrop="static" data-keyboard="false" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel23">新员工入职</h4>
										</div>
										<form>
											<div class="modal-body">
												<span>符合入职的员工</span>

												<div class="el_threeScoreList">
													<table class="table table-bordered"
														style="font-size: 13px;">
														<thead>
															<tr>
																<th>姓名</th>
																<th>性别</th>
																<th>身份证号</th>
																<th>部门</th>
															</tr>
														</thead>
														<tbody id="empInfoListForCertificate">
														</tbody>
													</table>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary"
													onclick="exportEmployeeOutInfo()">确认入职</button>
											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>




							<!-- 模態框二次分配 -->
							<div class="modal fade" id="reDistributeDepartModal"
								tabindex="-1" role="dialog" aria-labelledby="myModalLabel23"
								data-backdrop="static" data-keyboard="false" aria-hidden="true">
								<div class="modal-dialog" style="width: 50%;">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel234">二次分配员工</h4>
										</div>
										<!--隱藏用于二次分配的表单  -->
										<form id="reDistributeForm">
											<!-- 隐藏再次分配的部门ID -->
											<!-- <input type="hidden" id="reDistributeDepartmentId">	 -->
										</form>

										<form>
											<!--树-->
											<div class="el_leftTree">
												<!--标题类，添加了一个颜色-->
												<span class="el_treeTitle">部门</span>
											</div>

											<ul id="reDistribueTree" class="ztree"
												style="width: auto !important; height: auto !important; border: none !important;"></ul>

											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary"
													onclick="saveReDis()">保存</button>
											</div>
										</form>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>


						<!-- 模态框 查看培训档案-->
							<div class="modal fade" id="el_empTrainDoc_1" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								data-backdrop="static" data-keyboard="false" aria-hidden="true">
								<div class="modal-dialog" style="width: 80%;">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel234">员工培训档案</h4>
										</div>
										<form>
											<div class="modal-body" style="padding: 10px 30px 0 30px;">
												<div>
													<table
														class="table table-bordered table-hover el_threeScoreListTable">
														<thead>
															<tr>
																<th>序号</th>
																<th>检修名称</th>
																<th>工种</th>
																<th>考试名称</th>
																<th>考试级别</th>
																<th>考试时间</th>
																<th>试卷总分</th>
																<th>获得成绩</th>
																<th>是否通过</th>
																<th>培训学时</th>
																<th>培训内容</th>
															</tr>
														</thead>
														<tbody id="employeeOutExamInfos">
														</tbody>
													</table>
													<!--分页-->
													<div id="paginationID2" class="paginationID"></div>
												</div>
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