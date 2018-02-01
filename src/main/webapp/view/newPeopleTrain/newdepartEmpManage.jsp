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
<title>新员工员工管理</title>

<%@ include file="/public/cssJs.jsp"%>

<link rel="stylesheet" href="<%=path%>/css/outDepart/outdepartTree.css">

<script src="<%=path%>/js/newPeopleTrain/newdepartEmpManage.js"></script>
<link rel="stylesheet"
	href="<%=path%>/css/outDepart/outdepartEmpManage.css">
<!-- 日期格式转换 -->
<script
	src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>
<!-- 身份证阅读机的js -->
<script src="<%=path%>/js/outDepart/baseISSObject.js"></script>
<script src="<%=path%>/js/outDepart/baseISSOnline.js"></script>
<script src="<%=path%>/js/outDepart/common.js"></script>
<script src="<%=path%>/js/outDepart/jquery.jBox-2.3.min.js"></script>
<script>
	//定义一个全局变量
	var basePathUrl = "${pageContext.request.contextPath}";
	var hasOperatingEmpout = false;
</script>
<shiro:hasPermission name="empout:operating">
<script>
hasOperatingEmpout = true;
</script>
</shiro:hasPermission>
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
						<span>新员工入厂培训</span><span>>新员工员工管理</span>
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
								<form id="form_findEmployeeOutBaseInfo">

									<div class="row el_queryBoxrow">
										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
												<input type="text" class="form-control"
													name="employeeOutName" />
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">性&nbsp;&nbsp;&nbsp;别：</span> <label
													class="el_radioBox"><input type="radio"
													name="employeeOutSex" value="1"> 男</label> <label
													class="el_radioBox"><input type="radio"
													name="employeeOutSex" value="2"> 女</label>
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">身&nbsp;份&nbsp;&nbsp;证：</span> <input
													type="text" class="form-control" name="employeeOutIdCard" />
											</div>
										</div>
									</div>

<!-- 									<div class="row el_queryBoxrow">
										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">违章积分：</span> <select
													class="selectpicker form-control" title="请选择"
													name="minusNum">
													<option value="">--请选择--</option>
													<option value="0,3">3分以下</option>
													<option value="4,6">4-6分</option>
													<option value="7,9">7-9分</option>
													<option value="9,11">9-11分</option>
													<option value="12">12分及以上</option>
												</select>
											</div>
										</div> -->

		<!-- 								<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">黑名单：</span> <label
													class="el_radioBox"><input type="radio"
													name="blackListInfo" value="是"> 是</label> <label
													class="el_radioBox"><input type="radio"
													name="blackListInfo" value="否"> 否</label>
											</div>
										</div> -->

										<!-- <div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">考试情况：</span> <select
													class="selectpicker form-control" title="请选择"
													name="trainStatus">
													<option value="">--请选择--</option>
													<option value="1">通过一级考试</option>
													<option value="2">通过二级考试</option>
													<option value="3">通过三级考试</option>
													<option value="0">未参加培训</option>
												</select>
											</div>
										</div> -->
<!-- 										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;种：</span>
												<select class="selectpicker form-control" title="请选择"
													name="employeeType" id="query_empType">

												</select>
											</div>
										</div> -->
									</div>
									<!-- <div class="row el_queryBoxrow">
										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;种：</span>
												<select class="selectpicker form-control" title="请选择"
													name="employeeType" id="query_empType">

												</select>
											</div>
										</div>
									</div> -->
									<!-- 隐藏部门ID和大修ID -->
									<input type="hidden" name="unitId" id="query_unitId" /> <input
										type="hidden" name="bigId" id="query_bigId" />
									<!-- 隐藏当前页和显示条数 -->
									<input type="hidden" name="currentPage" id="currentPage" /> <input
										type="hidden" name="currentCount" id="currentCount" />
									<!-- 增加标记外来单位员工管理 -->
									<input type="hidden" name="markTrainType" value="0"/>
									<!--提交查询按钮-->
									<button type="button"
										class="btn btn-primary el_queryButton btn-sm"
										onclick="queryEmployeeOutBaseInfo()">查询</button>
									<button type="reset"
										class="btn btn-default el_queryButton0 btn-sm"
										onclick="clearQueryInfo()">清空</button>
								</form>

							</div>
							<!--结束 查询表单提交-->

							<!--显示内容-->
							<h3 class="el_mainHead">新员工信息</h3>
							<div class="panel panel-default el_Mainmain">

								<!--按钮面板-->
								<div class="panel-body">

									<!--按钮区-->
									<div class="panel panel-default">
										<div class="panel-body el_MainxiaoMain">

											<div class="el_topButton">
												<shiro:hasPermission name="empout:add">
													<button class="btn btn-primary" onclick="el_addEmp()">
														添加员工</button>
												</shiro:hasPermission>
												<button class="btn btn-primary" id="el_lookTrainDocument"
													onclick="el_empTrainDoc()">查看员工培训档案</button>
<%-- 												<shiro:hasPermission name="grademanager:printcard">
													<button class="btn btn-primary" onclick="el_empCardModel()">
														生成工作证</button>
												</shiro:hasPermission> --%>

											</div>
										</div>
									</div>

									<!--表格
                            	内容都提取到json里边
                        -->
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>选择</th>
												<th>序号</th>
												<th>姓名</th>
												<th>性别</th>
												<th>身份证</th>
												<th>所属单位</th>
												<th>工种</th>
												<th>违章积分</th>
												<th>黑名单状态</th>
												<!-- <th>考试情况</th> -->
												<th width="200">操作</th>
											</tr>
										</thead>
										<tbody id="employeeOutBaseInfoList">

										</tbody>
									</table>

									<!--分页-->
									<div id="paginationIDU" class="paginationID"></div>

								</div>
							</div>


							<!-- 模态框 添加员工-->
							<div class="modal fade" id="el_addEmp" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								data-backdrop="static" data-keyboard="false" aria-hidden="true">
								<div class="modal-dialog"
									style="width: 60%; max-height: 550px; overflow-y: auto;">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel23">添加员工</h4>
										</div>
										<div class="modal-body" style="position: relative">
											<!--头像一-->
											<div id="localImag" class="big-photo"
												style="border-left-width: 0px; border-right-width: 0px; border-top-width: 0px; border-bottom-width: 0px;">
												<img style="width: 120px height:140px"
													src="${pageContext.request.contextPath}/image/userImage.png"
													onerror="this.src='image/userImage.png'"
													style="margin-left:5px" id="id_img_pers">
											</div>
											<input id="idCardImageStr" type="hidden" />

											<div class="input-group el_modellist01" role="toolbar">
												<span class="el_spans0">员工姓名：</span> <input
													readonly="readonly" type="text" value="" id="personName"
													class="form-control el_modelinput" disabled name="" /> <span
													class="el_spans0">员工性别：</span> <input readonly="readonly"
													type="text" value="" id="gender"
													class="form-control el_modelinput" disabled name="" />

											</div>
											<div class="input-group el_modellist01" role="toolbar">
												<span class="el_spans0">出生日期：</span> <input
													readonly="readonly" type="text" value="" id="birthday"
													class="form-control el_modelinput" disabled name="" /> <span
													class="el_spans0">身&nbsp;&nbsp;份&nbsp;证：</span> <input
													readonly="readonly" type="text" value="" id="certNumber"
													class="form-control el_modelinput" disabled name="" />

											</div>
											<div class="input-group el_modellist01" role="toolbar">
												<span class="el_spans0">家庭住址：</span> <input
													readonly="readonly" type="text" value="" id="address"
													class="form-control el_modelinput" disabled name="" /> <span
													class="el_spans0">选择单位：</span> <input type="text"
													class="form-control el_modelinput" disabled name=""
													id="add_departmentName" />
											</div>
											<div class="input-group el_modellist01" role="toolbar">
												<span class="el_spans0">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;种：</span>
												<select class="selectpicker el_modelinput form-control"
													id="add_employeeOutType" title="请选择">
												</select>
											</div>

											<!-- 隐藏部门ID和大修ID -->
											<input id="add_departmentId" type="hidden" /> <input
												id="add_bigId" type="hidden" />
											<tr>
												<td id="readIDDiv" colspan="4" style="text-align: center;">
													<button class="btn btn-default" id="button_readID">读取身份证信息</button>
												</td>
											</tr>

											<!-- <button class="btn btn-default" id="button_readID">读取身份证信息</button> -->
											<button class="btn btn-primary el_modellist02"
												onclick="addEmployeeOutInfo()">添加</button>

											<table class="table table-hover table-bordered">
												<thead>
													<tr>
														<th>工种</th>
														<th>姓名</th>
														<th>性别</th>
														<th>身份证</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody id="addEmployeeOutInfoList">
												</tbody>
											</table>

										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
											<button type="button" class="btn btn-primary"
												onclick="saveEmployeeAndHaulInfo()">保存</button>
										</div>

										<form id="form_addEmployeeOutInfo"></form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>

							<!-- 模态框  修改员工-->
							<div class="modal fade" id="el_modifyEmp" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								data-backdrop="static" data-keyboard="false" aria-hidden="true">
								<div class="modal-dialog"
									style="width: 50%; max-height: 550px; overflow-y: auto;">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel23">修改员工信息</h4>
										</div>
										<form>
											<div class="modal-body" style="position: relative">
												<!--头像一-->
												<div class="big-photo">
													<img width="90" id="update_employeeOutPhoto">
												</div>

												<div class="input-group el_modellist01" role="toolbar">
													<span class="el_spans0">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;种：</span>
													<select class="selectpicker el_modelinput form-control"
														id="update_employeeOutType" title="请选择">

													</select>
												</div>

												<div class="input-group el_modellist01" role="toolbar">
													<span class="el_spans0">员工姓名：</span> <input type="text"
														class="form-control el_modelinput" disabled name=""
														id="update_employeeOutName" />
												</div>

												<div class="input-group el_modellist01">
													<span class="el_spans0"> 员工性别：</span> <input type="text"
														class="form-control el_modelinput" disabled
														id="update_employeeOutSex" />
													<!-- <div>
														<label><input type="radio" disabled id="update_employeeOutSex1"
															name="el_gender"  value="1"> 男</label> <label><input
															type="radio" disabled name="el_gender" value="2" id="update_employeeOutSex2">
															女</label>
													</div> -->
												</div>

												<div class="input-group el_modellist01" role="toolbar">
													<span class="el_spans0">出生日期：</span> <input type="text"
														class="form-control el_modelinput" disabled name=""
														id="update_employeeOutBirthday" />
												</div>

												<div class="input-group el_modellist0" role="toolbar">
													<span class="el_spans0">身&nbsp;&nbsp;份&nbsp;证：</span> <input
														type="text" class="form-control el_modelinput" disabled
														id="update_employeeOutIdCard" name="" />
												</div>

												<div class="input-group el_modellist0" role="toolbar">
													<span class="el_spans0">选择单位：</span> <input type="text"
														class="form-control el_modelinput" disabled name=""
														id="update_departmentName" />
												</div>

											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary"
													onclick="updateEmployeeOutInfo()">保存</button>
											</div>
										</form>
										<form id="form_updateAndDelete">
											<!-- 隐藏大修ID和身份证号，工种信息 -->
											<input type="hidden" name="employeeOutIdCard"
												id="updateAndDelete_employeeOutIdCard" /> <input
												type="hidden" name="bigId" id="updateAndDelete_bigId" /> <input
												type="hidden" name="employeeType"
												id="updateAndDelete_employeeType" />
											<!-- 隐藏大修员工ID -->
											<input type="hidden" name="bigEmployeeOutId" id="updateAndDelete_bigemployeeOutId" />
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
																<th><input type="checkbox" id="el_checkQuanxuan3" /></th>
																<th>部门</th>
																<th>姓名</th>
																<th>性别</th>
																<th>工种</th>
															</tr>
														</thead>
														<tbody id="empInfoListForCertificate">
														</tbody>
													</table>

													<!--分页-->
													<div id="paginationID2_xiugai"></div>

												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary"
													onclick="exportEmployeeOutInfo()">导出工作证信息</button>
											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>

							<!-- 模态框 查看培训档案-->
							<div class="modal fade" id="el_empTrainDoc" tabindex="-1"
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

												<span class="el_spanDoc">员工信息</span>
												<div class="el_threeScoreList el_threeScoreList2">
													<table class="table-bordered table el_threeScoreListTable">
														<thead>
															<tr>
																<th>姓名</th>
																<th>性别</th>
																<th>所属单位</th>
																<th>违章积分</th>
																<th>黑名单</th>
															</tr>
														</thead>
														<tbody id="employeeOutTrainBaseInfo">
														</tbody>
													</table>
												</div>

												<span class="el_spanDoc">培训考试信息</span>
												<div class="el_threeScoreList">
													<table
														class="table table-bordered table-hover el_threeScoreListTable">
														<thead>
															<tr>
																<th>序号</th>
																<th>大修名称</th>
																<th>工种</th>
																<th>考试名称</th>
																<th>考试级别</th>
																<th>考试时间</th>
																<th>试卷总分</th>
																<th>获得成绩</th>
																<th>是否通过</th>
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

							<!-- 模态框 违章情况-->
							<div class="modal fade" id="el_breakRulesCase" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								data-backdrop="static" data-keyboard="false" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabe5l23">违章情况</h4>
										</div>
										<form>
											<div class="modal-body">
												<div class="el_BRCase">
													<span>员工信息</span>
													<table class="table-bordered table">
														<thead>
															<tr>
																<th>姓名</th>
																<th>性别</th>
																<th>所属单位</th>
																<th>违章积分</th>
																<th>黑名单</th>
															</tr>
														</thead>
														<tbody id="employeeOutBreakRulesBaseInfo">
														</tbody>
													</table>
												</div>
												<span>违章信息</span>
												<table class="table table-bordered" style="font-size: 13px;">
													<thead>
														<tr>
															<th>序号</th>
															<th>违章时间</th>
															<th width="80">违章积分</th>
															<th>违章内容</th>
														</tr>
													</thead>
													<tbody id="employeeOutBreakRuleList">
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
							<!-- 模态框 员工详细信息-->
							<div class="modal fade" id="allInfo" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true"
								data-backdrop="static" data-keyboard="false">
								<div class="modal-dialog">
									<div class="modal-content" style="position: relative;">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel">员工详细信息</h4>
										</div>
										<form>
											<div class="modal-body">

												<div class="input-group el_empPhoto" role="toolbar">
													<img width="90" id="details_employeeOutPhoto">
												</div>
												<div class="el_parperInfo">
													<table>
														<tr>
															<td>员工姓名:</td>
															<td id="details_employeeOutName"></td>
														</tr>
														<tr>
															<td>性别:</td>
															<td id="details_employeeOutSex"></td>
														</tr>
														<tr>
															<td>出生日期:</td>
															<td id="details_employeeOutBirthday"></td>
														</tr>
														<tr>
															<td>身份证:</td>
															<td id="details_employeeOutIdCard"></td>
														</tr>
														<tr>
															<td>黑名单状态:</td>
															<td id="details_employeeOutIsBlack"></td>
														</tr>
														<!-- <tr>
															<td>安全培训情况:</td>
															<td id="details_employeeOutTrainStatus"></td>
														</tr> -->
														<tr>
															<td>所属单位:</td>
															<td id="details_departmentName"></td>
														</tr>
														<tr>
															<td>工种:</td>
															<td id="details_employeeType"></td>
														</tr>
													</table>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<!-- <button type="button" class="btn btn-primary">提交更改</button> -->
											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>
							<!-- 模态框   信息删除确认 -->
							<div class="modal fade" id="delcfmModel" data-backdrop="static"
								data-keyboard="false">
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
											<p>您确定要删除该员工吗？</p>
											<p>删除该员工会将该员工在这次大修下的所有考试和违章记录都删除掉！</p>
										</div>
										<div class="modal-footer">
											<input type="hidden" id="url" />
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<a onclick="urlSubmit()" class="btn btn-success"
												data-dismiss="modal">确定</a>
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

	<!--放脚-->
	<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>