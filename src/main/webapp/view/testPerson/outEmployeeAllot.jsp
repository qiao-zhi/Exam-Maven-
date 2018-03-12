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
<title>分配员工</title>

<%@ include file="/public/cssJs.jsp"%>
<link rel="stylesheet" href="<%=path%>/css/outDepart/outdepartTree.css">

<script src="<%=path%>/js/outDepart/outEmployeeAllot.js"></script>
<link rel="stylesheet"
	href="<%=path%>/css/outDepart/outEmployeeAllot.css">
<!-- 日期格式转换 -->
<script
	src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js"></script>

<script>
	//定义一个全局变量
	var baseurl = "${pageContext.request.contextPath}";
</script>
<style>
.mark-type{
	 font-size: 10px;
	 padding: 2px 2px;
}
.el_treeTitle{
	display:inline;	
    padding-right: 9px;	
    padding-left: 9px;
    
}
</style>
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
						<span>培训管理</span><span>>员工培训</span><span>>短委员工培训</span>
					</div>
					<div class="el_main">

						<!--树-->
						<div class="el_leftTree">
							<!--标题类，添加了一个颜色-->
							<span class="el_treeTitle">检修单位</span>
							<select class="btn btn-default mark-type" id="el_bigStatusMark"	title="请选择" onchange="historyBigInfoFind()">
									<option value="0">当前检修</option>
									<option value="1">历史检修</option>
							</select>
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
									<input type="hidden" name="markTrainType" value="1" />

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
													class="el_radioBox"><input type="radio"
													 name="employeeOutSex" value="2">
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
				
									<!-- 隐藏大修状态标记，默认查询当前未结束的-->
									<input id="bigStatus_Mark" type="hidden" value="0"
										name="bigStatusMark">
		
									<!-- 隐藏部门ID和大修ID -->
									<input type="hidden" name="unitId" id="query_unitId"
										class="clearInput" /> <input type="hidden" name="bigId"
										id="query_bigId" class="clearInput" />
									<!-- 隐藏当前页和显示条数 -->
									<input type="hidden" name="currentPage" id="currentPage" /> <input
										type="hidden" name="currentCount" id="currentCount" value="8"/>
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
											<option value="4">已生成工作证</option>
											<option value="5">已回收工作证</option>
										</select>
										<button class="btn btn-primary" id="lookTrainInfo"
											 onclick="lookTrainInfo()">查看培训档案员工</button>
										<button class="btn btn-primary" id="el_lookTrainDocument"
											style="display: none" onclick="el_empTrainDoc()">分配员工</button>
										<shiro:hasPermission name="grademanager:printcard">
											<button style="display: none" id="generateWork"
												class="btn btn-primary" onclick="el_empCardModel()">
												生成工作证</button>
										</shiro:hasPermission>
										<!--S 免培训  -->
										<shiro:hasPermission name="trainmanager:notrain">
											<button style="display: none" id="notrainbtn"
												class="btn btn-primary" onclick="notrain()">
												技术服务</button>
										</shiro:hasPermission>
										<!--S 免培训  -->
										<shiro:hasPermission name="grademanager:revokecard">
											<button style="display: none" id="revokeWork"
												class="btn btn-primary" onclick="revokeWork()">
												回收工作证</button>
										</shiro:hasPermission>
										<shiro:hasPermission name="grademanager:revokegene">
											<button style="display: none" id="reGeberateWord"
												class="btn btn-primary" onclick="el_empCardModel()">
												重新发放工作证</button>
										</shiro:hasPermission>
										<shiro:hasPermission name="grademanager:redisdepart">
										<button style="display: none" id="reDistributeDepart"
											class="btn btn-primary" onclick="reDstributeDepart()">
											二次分配</button>
										</shiro:hasPermission>
										<shiro:hasPermission name="grademanager:redistribute">
										<button style="display: none" id="reDistributeUnit"
											class="btn btn-primary" onclick="reDistributeUnit()">
											重新分配单位</button>
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
												<th>所属检修</th>
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

							<!-- 模态框分配员工 -->
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



							<!-- 模態框重新分配单位 -->
							<div class="modal fade" id="reDistributeUnitModal" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								data-backdrop="static" data-keyboard="false" aria-hidden="true">
								<div class="modal-dialog" style="width: 50%;">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel234">重新分配单位</h4>
										</div>
										<!--隱藏用于二次分配的表单  -->
											<!-- 隐藏大修ID和 大修单位ID-->
											<input type="hidden" id="reDisUnitBigId" /> <input
												type="hidden" id="reDisUnitUnitId" />
										<form id="reDistributeUnitForm">
										</form>

										<form>
											<!--树-->
											<div class="el_leftTree">
												<!--标题类，添加了一个颜色-->
												<span class="el_treeTitle">检修单位</span>
											</div>

											<ul id="departmentAndOverHaulTree_modal" class="ztree"
												style="width: auto !important; height: auto !important; border: none !important;"></ul>


											<div style="margin-left: 100px;margin-bottom:15px;">
												<label>请选择要分配的单位：</label><input
													placeholder="请点击单位树进行选择!" readonly id="input_reDisUnit" />
											</div>

											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary"
													onclick="saveReDisUnit()">保存</button>
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
													<th>所属检修</th>
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
											<h4 class="modal-title" id="myModalLabel23">生成工作证</h4>
										</div>
										<form>
											<div class="modal-body">
												<span>符合生成工作证的员工</span>

												<div class="el_threeScoreList">
													<table class="table table-bordered"
														style="font-size: 13px;">
														<thead>
															<tr>
																<th>姓名</th>
																<th>性别</th>
																<th>身份证号</th>
																<th>工种</th>
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
													onclick="exportEmployeeOutInfo()">生成工作证</button>
											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>





							<!-- 模态框 回收工作证-->
							<div class="modal fade" id="el_empCardModel1" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								data-backdrop="static" data-keyboard="false" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel23">回收工作证</h4>
										</div>
										<form>
											<div class="modal-body">
												<span>符合回收工作证的员工</span>

												<div class="el_threeScoreList">
													<table class="table table-bordered"
														style="font-size: 13px;">
														<thead>
															<tr>
																<th>姓名</th>
																<th>性别</th>
																<th>身份证号</th>
																<th>工种</th>
																<th>部门</th>
															</tr>
														</thead>
														<tbody id="empInfoListForRevoke">
														</tbody>
													</table>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary"
													onclick="revokeEmployeeOutInfo()">回收工作证</button>
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