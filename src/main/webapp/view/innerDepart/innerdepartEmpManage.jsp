<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
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
<title>内部员工管理</title>

<%@ include file="/public/cssJs.jsp"%>
<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>

<script src="<%=path%>/js/innerDepart/emplyInBreakRulesManage.js"></script>
<link rel="stylesheet" href="<%=path%>/css/outDepart/outdepartTree.css">

<!-- 员工的CSS -->
<link rel="stylesheet"
	href="<%=path %>/css/innerDepart/innerdepartEmpManage.css">
<!--验证-->
<script
	src="${pageContext.request.contextPath }/controls/validate/jquery.validate.js"></script>
<script
	src="${pageContext.request.contextPath }/controls/validate/messages_zh.js"></script>
<script>
	$(function() {

		$("#inpstart2").jeDate({
			isinitVal : false,
			minDate : '2000-06-16',
			maxDate : '2225-06-16',
			format : 'YYYY-MM-DD',
			zIndex : 3000
		})

		$("#inpend2").jeDate({
			isinitVal : false,
			minDate : '2000-06-16',
			maxDate : '2225-06-16',
			format : 'YYYY-MM-DD',
			zIndex : 3000
		})

	})
</script>

<!-- ------------员工引入的---------- -->


<script>
    var contextPath="${baseurl}";
    var hasOperatingEmpin=false;
</script>

<shiro:hasPermission name="empin:operating">
	<script>
hasOperatingEmpin = true;
</script>
</shiro:hasPermission>

<!-- 验证 -->
<script src="<%=path %>/controls/validate/jquery.validate.js"></script>
<script src="<%=path %>/controls/validate/messages_zh.js"></script>

<!-- 身份证阅读机的js -->
<script src="<%=path%>/js/outDepart/baseISSObject.js"></script>
<script src="<%=path%>/js/outDepart/baseISSOnline.js"></script>
<script src="<%=path%>/js/outDepart/common.js"></script>
<script src="<%=path%>/js/outDepart/jquery.jBox-2.3.min.js"></script>
<!-- 日期格式转换 -->
<script
	src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>

<script>
    	//定义一个全局变量
    	var basePathUrl = "${pageContext.request.contextPath}";
    </script>


<style>
#el_breakTimeIndex input {
	width: 33% !important;
	margin-right: 10px;
}

#el_breakTimeIndex {
	margin-left: 15px;
}

#el_breakType {
	font-size: 13px;
}

.el_topButton {
	margin-top: 10px;
}
</style>
</head>
<body>

	<!-- 用于提交数据的 -->
	<form id="detailInForm"
		action="${pageContext.request.contextPath}/empInbreakrules_detailOp.action"
		method="post">
		<input type="hidden" name="fstarttime" id="q_starttime"> <input
			type="hidden" name="fendtime" id="q_endtime"> <input
			id="detailunitName" name="detailunitName" type="hidden" value="" />
		<input id="detailemployeeId" name="detailemployeeId" type="hidden"
			value="" /> <input id="detail_breakInfoType" name="empBreakInfoType"
			type="hidden" value="0" /> 

		<!-- 隐藏员工的部门类型 -->
		<input type="hidden" name="employeeDepartmentType"
			id="detail_employeeDepartType">
	</form>

	<!-- 隐藏域 start -->


	<!-- 隐藏 操作哪块地标记 -->
	<input id="allMark" type="hidden" value="" />


	<!-- 隐藏域  end -->
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
						<span>内部员工管理</span><span>>员工管理</span>
					</div>
					<div class="el_main">

						<!--树-->
						<div class="el_leftTree">
							<!--标题类，添加了一个颜色-->
							<span class="el_treeTitle">部门</span>
							<ul id="treeDemo" class="ztree"></ul>
						</div>

						<!--内容-->
						<div class="el_qlmContent">

							<!--索引-->
							<div class="row el_queryBox" id="queryDiv">
								<form id="findForm">
									<div class="row el_queryBoxrow">
										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
												<input id="initName" type="text" class="form-control"
													name="fName" value="" />
											</div>
										</div>

										<!-- <div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">身份证：</span> <input id="initIdCard"
													type="text" class="form-control" name="fIdCode" value="" />
													
													
													<input
													type="hidden" name="departmentid" id="queryDepartmentId" />
												
												默认显示管理员
												<input type="hidden" name="isOnlyManager" value="false"
													id="el_showManagerInput" />
											</div>
										</div> -->

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">性别：</span> <label class="el_radioBox"><input
													class="initsex" type="radio" name="fSex" value="1">
													男</label> <label class="el_radioBox"><input class="initsex"
													type="radio" name="fSex" value="2"> 女</label> <input
													type="hidden" name="departmentid" id="queryDepartmentId" />

												<!-- 默认显示管理员 -->
												<input type="hidden" name="isOnlyManager" value="false"
													id="el_showManagerInput" />
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" id="el_blackCheckbox" role="toolbar">
												<span class="el_spans">黑名单：</span> <label
													class="el_radioBox"><input class="initBlack"
													type="radio" name="fIsBreak" value="是"> 是</label> <label
													class="el_radioBox"><input class="initBlack"
													type="radio" name="fIsBreak" value="否"> 否</label>
											</div>
										</div>


									</div>

									<div class="row el_queryBoxrow">

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">违章积分：</span> <select
													class="selectpicker form-control" id="el_breakSelect"
													name="fBreakScore" title="请选择">
													<option value="0,100">--请选择--</option>
													<option value="0,3">3分以下</option>
													<option value="4,6">4-6</option>
													<option value="7,11">7-11</option>
													<option value="12,1000">12分及以上</option>
												</select>
											</div>
										</div>
										<div class="col-md-6" id="el_breakTimeIndex">
											<div class="input-group" id="el_startEndTime" role="toolbar">
												<span class="el_spans">违章时间：</span> <input type="text"
													class=" form-control query_dep_starttime" name="fstarttime"
													placeholder="开始时间" id="inpstart2" readonly> <input
													type="text" class="form-control query_dep_endtime"
													id="inpend2" placeholder="结束时间" name="fendtime" readonly>
											</div>
										</div>



									</div>
									<div class="row el_queryBoxrow">

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">家庭住址：</span> <input
													class="form-control" name="address" type="text">
											</div>
										</div>

										<div class="col-md-6" id="el_breakTimeIndex">
											<div class="input-group" id="el_startEndTime" role="toolbar">

												<span class="el_spans">年&nbsp;龄&nbsp;段：&nbsp;</span> <input
													type="text" class=" form-control query_dep_starttime"
													name="ageLeft"> <input type="text"
													class="form-control query_dep_endtime" name="ageRight">
                      
											</div>
										</div>
									</div>

									<!-- 当前页页号 -->
									<input id="yeHao" type="hidden" name="fcurpage" value="1" />
									<!-- 每页显示的记录数 -->
									<input id="jiLuShu" type="hidden" name="fcurtotal" value="8" />
									<!-- 隐藏一个左侧的树选中的部门的部门id -->
									<input id="departmentidTree" type="hidden" name="fdepartmentid"
										value="" />
									<!-- 隐藏违章信息查询类型，默认查询当前年 -->
									<input id="breakInfo_Type" type="hidden" value="0"
										name="empBreakInfoType">
									<!--提交查询按钮-->
									<button type="button" onclick="clearPagenum()"
										class="btn btn-primary el_queryButton btn-sm">查询</button>
									<button type="reset" onclick="clearBtn()"
										class="btn btn-default el_queryButton btn-sm"
										style="right: 12px">清空</button>
								</form>

							</div>
							<!--结束 查询表单提交-->

							<!-- 清空按钮的点击事件 -->
							<script type="text/javascript">
								
							</script>
							<!-- 解决违章记分和黑名单冲突 -->
							<script>
								$(
										function() {
											$("#el_breakSelect")
													.change(
															function() {
																var breakValue = $(
																		this)
																		.val();

																if (breakValue != "") {
																	$(
																			"#el_blackCheckbox")
																			.find(
																					"input")
																			.attr(
																					"disabled",
																					true);
																	$(
																			"#el_blackCheckbox")
																			.find(
																					"input")
																			.attr(
																					"checked",
																					false);
																} else {
																	$(
																			"#el_blackCheckbox")
																			.find(
																					"input")
																			.attr(
																					"disabled",
																					false);
																}
															})
										})
							</script>





							<!--显示内容-->
							<h3 class="el_mainHead">
								内部员工信息
								<a href=javascript:void(0) onclick="toggleQueryDiv()">
									<span style="margin-right:50px;float:right;color:rgb(221,221,221);" title="点击隐藏查询条件" class="glyphicon glyphicon-chevron-up"></span>
								</a>
							
							</h3>
							<div class="panel panel-default el_Mainmain">

								<!--按钮面板-->
								<div class="panel-body">

									<div class="panel panel-default">
										<div class="panel-body el_MainxiaoMain">

											<div class="el_topButton">
												<div>
													<button class="btn btn-primary" data-toggle="modal"
														onclick="el_empTrainDoc()">查看员工培训档案</button>

													<select class="btn btn-primary" id="el_showManager"
														title="请选择" onchange="isShowOnlyManager()"
														name="examLevel">
														<option value="false">全部员工</option>
														<option value="true">管理人员</option>
													</select>
													<!-- 员工按钮 -->
																										<!-- 违章按钮 -->
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <select
														class="btn btn-primary" id="el_breakType" title="请选择"
														onchange="historyBreakInfoFind()">
														<!-- <select class="btn btn-primary" id="el_breakType"
													title="请选择" name="examLevel"> -->
														<option value="0">当前违章</option>
														<option value="1">历史违章</option>
													</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<!-- 按钮触发模态框1 -->

													<shiro:hasPermission name="breakrules:add">
														<!-- 按钮触发模态框1 -->
														<button id="el_addBreakRules" class="btn btn-primary"
															onclick="el_addBreakInfo()"><span class="glyphicon glyphicon-plus"></span>添加违章信息</button>
													</shiro:hasPermission>
													<!-- 在模态框中添加 -->
													<shiro:hasPermission name="empin:add">
														<a id="el_addUserA" onclick="el_addEmployeeIn()">
															<button class="btn btn-primary">
																<span class="glyphicon glyphicon-plus"></span>添加员工
															</button>
														</a>
													</shiro:hasPermission>

												</div>
											</div>

										</div>
									</div>

									<!--表格    内容都提取到json里边             -->
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>选择</th>
												<th>姓名</th>
												<th>性别</th>
												<th>出生年月</th>
												<th>联系方式</th>
												<th>所属部门</th>
												<th>职务</th>
												<th>违章积分</th>
												<th>黑名单状态</th>
												<th width="190">操作</th>
											</tr>
										</thead>
										<tbody id="tbody">

										</tbody>
									</table>

									<!--分页  用于左侧的树的-->
									<div id="paginationIDU" class="paginationID"></div>

								</div>
							</div>

							<!-- 模态框 违章信息添加-->
							<div class="modal fade" id="el_addBreakInfo" tabindex="-1"
								data-backdrop="static" data-keyboard="false" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabeld2">添加违章信息</h4>
										</div>
										<form id="addForm">

											<div class="modal-body">
												<span>员工信息：</span>
												<div>
													<table class="table table-bordered">
														<thead>
															<tr>
																<th>姓名</th>
																<!--  <td>联系方式</td> -->
																<th>性别</th>
																<th>所属单位</th>
																<th>违章记分</th>
																<th>黑名单</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td id="addName"></td>
																<td id="addSex"></td>
																<!--  <td id="addPhone">asdf</td> -->
																<td id="addunitName"></td>
																<td id="addbreakScore"></td>
																<td id="addIsBreak"></td>
															</tr>
														</tbody>
													</table>
												</div>

												<!-- 隐藏一个员工id -->
												<input id="addEmpID" type="hidden"
													name="emplyinBreakrules.empinemployeeid" value="" />
												<!-- 隐藏一个身份证 -->
												<input id="addIdCard" type="hidden" name="addIdCard"
													value="" />
												<!-- 隐藏一个部门类型-->
												<input id="employeeDepartmentType" type="hidden"
													name="employeeDepartmentType" />

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">违章时间：</span> <input type="text"
														id="test41"
														class="workinput wicon form-control"
														name="emplyinBreakrules.empinbreaktime" readonly />
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">违章积分：</span>
													<!--不得超过12分-->
													<input id="breakScoreID" type="text"
														class="form-control el_modelinput"
														name="emplyinBreakrules.empinminusnum" />
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">违章内容：</span>
													<textarea id="addBreakContent"
														class="form-control el_modelinput" rows="3" value=""
														name="emplyinBreakrules.empinbreakcontent"></textarea>
												</div>

											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" onclick="addSaveBtn()"
													class="btn btn-primary">保存</button>
											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>

							<!-- 模态框，用于提示本次添加的 违章记分>=12分的情况   start-->
							<div class="modal fade" id="addyAlertModel2"
								data-backdrop="static" data-keyboard="false">
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
											<p>您当前添加的违章积分大于等于12分，该员工被永久进入黑名单，是否继续添加？</p>
										</div>
										<div class="modal-footer">
											<input type="hidden" id="url" />
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<a onclick="addAlertModelBtn2()" class="btn btn-success"
												data-dismiss="modal">确定</a>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->
							<!-- 模态框，用于提示本次添加的 违章记分>=12分的情况  end-->





							<!-- zwy start -->


							<!-- 模态框   员工信息删除确认 -->
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
											<p>您确认要删除该条信息吗？</p>
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

							<!-- 模态框 员工详细信息-->
							<div class="modal fade" id="allInfo" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true"
								data-backdrop="static" data-keyboard="false">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel">员工详细信息</h4>
										</div>
										<form>
											<div class="modal-body">

												<div class="input-group el_empPhoto" role="toolbar"
													style="height: 127px; border-left-width: 0px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px;">
													<img id="myimg" width="95" height="121">
												</div>
												<div class="el_parperInfo">
													<table>
														<tr>
															<td>员工姓名：</td>
															<td id="InfoName"></td>
														</tr>
														<tr>
															<td>性别：</td>
															<td id="InfoSex"></td>
														</tr>
														<tr>
															<td>出生日期：</td>
															<td id="InfoBirthday"></td>
														</tr>
														<tr>
															<td>联系方式：</td>
															<td id="InfoPhone"></td>
														</tr>
														<tr>
															<td>身份证：</td>
															<td id="InfoIdcode"></td>
														</tr>
														<!-- <tr>
                                                <td>安全培训情况：</td>
                                                <td id="InfoTrainstatus"></td>
                                            </tr> -->
														<tr>
															<td>所属单位：</td>
															<td id="InfoDepartmentid"></td>
														</tr>
														<tr>
															<td>职务：</td>
															<td id="Infozhiwu"></td>
														</tr>
														<tr>
															<td>家庭住址：</td>
															<td id="InfoAddress"></td>
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


							<!-- 模态框  修改员工-->
							<div class="modal fade" id="el_modifyEmp" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
										<form id="updateEmployeeIn">
											<div class="modal-body" style="position: relative">
												<!--头像一-->
												<div class="big-photo"></div>

												<div class="input-group el_modellist01" role="toolbar">
													<span class="el_spans0">员工姓名：</span> <input type="text"
														class="form-control el_modelinput" disabled
														name="EmployeeIn.name" id="updateEmployeeInName" /> <input
														type="hidden" name="EmployeeIn.employeeid"
														id="updateEmployeeEmployeeid" />
												</div>

												<div class="input-group el_modellist01">
													<span class="el_spans0"> 员工性别：</span>
													<div>
														<label><input type="radio" disabled
															name="EmployeeIn.sex" value="男"> 男</label> <label><input
															type="radio" disabled name="EmployeeIn.sex" value="女">女</label>
													</div>
												</div>

												<div class="input-group el_modellist01" role="toolbar">
													<span class="el_spans0">出生日期：</span> <input type="text"
														class="form-control el_modelinput" disabled
														name="EmployeeIn.birthday" id="updateEmployeeInBirthday" />
												</div>

												<div class="input-group el_modellist01" role="toolbar">
													<span class="el_spans0">身&nbsp;&nbsp;份&nbsp;证：</span> <input
														type="text" class="form-control el_modelinput" disabled
														name="EmployeeIn.idcode" id="updateEmployeeInIdcode" />
												</div>

												<div class="input-group el_modellist01" role="toolbar">
													<!-- <span class="el_spans0">选择单位：</span> <input type="text"
														class="form-control el_modelinput" disabled name="" />
														 -->



													<!-- 部门树 -->
													<span class="el_spans el_chooseSpan">选择部门：</span> <input
														type="hidden" id="updateEmployeeInDepartment"
														name="EmployeeIn.departmentid" />
													<ul id="el_chooseUpdateDepart"
														class="el_modelinput el_chooseInput log"></ul>
													<img src="../../controls/selectDropTree/smallTriangle.png"
														class="el_smallTriangle" width="7" />
													<ul id="treeDemo100" class="ztree" style="display: none"></ul>

												</div>

											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary"
													onclick="saveUpdateEmployeeInButton()">保存</button>
											</div>
										</form>


									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>


							<!-- 模态框 添加员工-->
							<div class="modal fade" id="el_addEmployeeIn" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								data-backdrop="static" data-keyboard="false" aria-hidden="true">
								<div class="modal-dialog"
									style="width: 70%; position: relative; max-height: 550px; overflow-y: auto;">
									<div class="modal-content"
										style="min-height: 420px !important;">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel23">添加员工</h4>
										</div>

										<div class="modal-body" style="position: relative">
											<!--头像一-->
											<div id="localImag" class="big-photo" style="border: none;">
												<img style="width: 120px; height: 140px;"
													src="${pageContext.request.contextPath}/image/userImage.png"
													onerror="this.src='image/userImage.png'"
													style="margin-left:5px" id="id_img_pers">
											</div>
											<input id="idCardImageStr" type="hidden" />

											<div class="input-group el_modellist01" role="toolbar">
												<span class="el_spans0">员工姓名：</span> <input type="text"
													value="" id="personName" disabled
													class="form-control el_modelinput" name="EmployeeIn.name" />
												<span class="el_spans0">员工性别：</span> <input type="text"
													value="" id="gender" disabled
													class="form-control el_modelinput" name="EmployeeIn.sex" />
											</div>
											<div class="input-group el_modellist01" role="toolbar">
												<span class="el_spans0">出生日期：</span> <input type="text"
													value="" id="birthday" disabled
													class="form-control el_modelinput"
													name="EmployeeIn.birthday" /> <span class="el_spans0">身&nbsp;&nbsp;份&nbsp;证：</span>
												<input type="text" value="" id="certNumber" disabled
													class="form-control el_modelinput" name="EmployeeIn.idcode" />
											</div>

											<div class="input-group el_modellist01" role="toolbar">
												<span class="el_spans0">家庭住址：</span> <input type="text"
													value="" id="address" disabled
													class="form-control el_modelinput"
													name="EmployeeIn.address" /> <span class="el_spans0">所选部门：</span>
												<input type="text" class="form-control el_modelinput"
													disabled name="EmployeeIn.departmentid"
													id="add_departmentName" /> <input type="hidden"
													id="yincangzhiwu">
											</div>

											<div class="input-group el_modellist03" role="toolbar">
												<span class="el_spans0">职 &nbsp;&nbsp;&nbsp;&nbsp;务
													&nbsp;&nbsp;：</span> <select class="form-control el_modelinput"
													title="请选择" name="EmployeeIn.duty" id="addEmployeeInDuty"></select>
											</div>
											<div class="input-group el_modellist04" role="toolbar">
												<span class="el_spans0">联系方式：</span> <input type="text"
													class="form-control el_modelinput" name="EmployeeIn.photo"
													id="addEmployeeInPhone" />
											</div>
											<div id="message3"
												style="display: block; margin-left: 99px; color: red;"></div>

											<tr>
												<td id="readIDDiv" colspan="4" style="text-align: center;">
													<button class="btn btn-default" id="button_readID">读取身份证信息</button>
												</td>
											</tr>

											<!-- <button class="btn btn-default" id="button_readID">读取身份证信息</button> -->
											<button class="btn btn-primary el_modellist02"
												onclick="addEmployeeInInfo()">添加</button>

											<table class="table table-hover table-bordered"
												style="margin-bottom: 40px;">
												<thead>
													<tr>
														<th>姓名</th>
														<th>性别</th>
														<th>身份证</th>
														<th>职务</th>
														<th>联系方式</th>
														<th>部门</th>
														<!-- <th>职务</th> -->
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



									</div>

									<form id="form_addEmployeeOutInfo"></form>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>

							<!-- 模态框 查看培训档案-->
							<div class="modal fade" id="el_empTrainDoc" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								aria-hidden="true" data-backdrop="static" data-keyboard="false">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel234">员工培训档案</h4>
										</div>
										<form id="hiddenidcodeForm" method="POST">
											<input type="hidden" id="hiddenidcode" value=""
												name="hiddenidcode" /> <input type="hidden"
												name="currentPage" id="currentPage2" /> <input
												type="hidden" name="currentCount" id="currentCount2" />
										</form>
										<form>
											<div class="modal-body">
												<div class="input-group el_empPhoto" role="toolbar"
													style="height: 127px;">
													<img id="myimg2" width="95" height="121">
												</div>
												<div class="el_threeScoreList">

													<table>
														<caption style="margin-bottom: 20px; margin-top: -10px;">员工信息：</caption>
														<tr>
															<td>姓名</td>
															<td id="TrainName"></td>
															<td>性别</td>
															<td id="TrainSex"></td>
														</tr>
														<tr>
															<td>联系方式</td>
															<td id="TrainPhone"></td>
															<!-- <td>违章记分</td>
                                                <td id="TrainMinusnum"></td> -->
															<td>所属部门</td>
															<td id="TrainUnit"></td>
														</tr>


													</table>
												</div>
												<table class="table table-hover  table-bordered"
													style="width: 95%; margin: 0 auto; font-size: 13px;">
													<thead>
														<tr>
															<th>考试名称</th>
															<th>考试级别</th>
															<th>培训内容</th>
															<th>培训学时</th>
															<th>考试时间</th>
															<th>考试总分数</th>
															<th>获得成绩</th>
															<!-- <th>是否通过</th> -->
														</tr>
													</thead>

													<tbody id="empTrainDoc_body">

													</tbody>


												</table>
												<!--分页-->
												<div id="paginationIDU2" class="paginationID"></div>
											</div>

											<div class="modal-footer">
												<button type="button" class="btn btn-primary"
													onclick="extEmpTrain()">导出培训档案</button>
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
											</div>
										</form>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>





							<!-- zwy end -->

							<!-- 隐藏域，隐藏一个添加前的违章总积分 -->
							<input id="breakScoreSum" type="hidden" value="" />
							<script type="text/javascript">
								
							</script>


						</div>

					</div>

				</div>

			</div>


		</div>
	</div>

	<div id="msg">
		<a>X</a>
		<center>
			<p>请注意:以下内部员工年龄已超过五十五岁</p>
		</center>
		<div id="messageDiv">
			<table class="table table-hover  table-bordered"
				style="width: 95%; margin: 0 auto; font-size: 13px;" id="olderTable">
			</table>
		</div>
	</div>

	<!--放脚-->
	<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>
