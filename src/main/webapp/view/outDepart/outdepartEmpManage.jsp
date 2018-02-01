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

<title>短委员工管理</title>


<%@ include file="/public/cssJs.jsp"%>

<link rel="stylesheet" href="<%=path%>/css/outDepart/outdepartTree.css">

<script
	src="<%=path%>/js/outDepart/outdepartEmpAndBreakRulesInfoManager.js"></script>
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

<!--验证-->
<script
	src="${pageContext.request.contextPath }/controls/validate/jquery.validate.js"></script>
<script
	src="${pageContext.request.contextPath }/controls/validate/messages_zh.js"></script>

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

<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>

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
<style>
#el_breakType {
	font-size: 13px;
}

.el_Mainmain {
	padding-bottom: 0px;
	top: 1px;
}

.el_mainHead {
	border-bottom: 0px solid #ddd;
}

.el_threeScoreList {
	width: 500px;
	margin: -10px auto;
	margin-bottom: 20px;
	height: 160px;
	margin-top: 10px;
	margin-left: 70px;
	margin-bottom: 0px;
}

.el_threeScoreList table {
	width: 100%;
	font-size: 13px;
}

.el_threeScoreList table caption {
	font-size: 16px;
	margin: 0;
}

.el_threeScoreList table tr {
	border: 1px solid #ccc;
	width: 100%;
	height: 30px;
	line-height: 30px;
}

.el_threeScoreList table tr td {
	padding: 5px;
}

.el_threeScoreList table tr td:nth-child(odd) {
	background-color: #eee;
	width: 20%;
	text-align: center;
	border-left: 1px solid #ccc;
}
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


</head>
<body>
	<!-- 用于提交数据的 -->
	<form id="detailForm"
		action="${pageContext.request.contextPath}/breakrules_detailOp.action"
		method="post">
		<!-- 违章的开始时间 -->
		<input type="hidden" name="fstarttime" id="q_starttime">
		<!-- 违章的结束时间 -->
		<input type="hidden" name="fendtime" id="q_endtime">
		<!-- 姓名 -->
		<input id="detailName" name="detailName" type="hidden" value="" />
		<!-- 性别 -->
		<input id="detailSex" name=detailSex type="hidden" value="" />
		<!--身份证  -->
		<input id="detailIdCard" name="detailIdCard" type="hidden" value="" />
		<!-- 单位名称 -->
		<input id="detailUnitName" name="detailUnitName" type="hidden"
			value="" />
		<!-- 职工id -->
		<input id="detailEmployeeId" name="detailEmployeeId" type="hidden"
			value="" />
		<!-- 参加大修的职工id -->
		<input id="detailBigEmployeeoutId" name="detailBigEmployeeoutId"
			type="hidden" value="" />

		<!-- 隐藏违章信息类型 是历史的还是当前的，默认是当前的-->
		<input id="detail_breakInfoType" name="empBreakInfoType" type="hidden"
			value="0" />
	</form>


	<!--头-->
	<jsp:include page="/view/public/header.jsp"></jsp:include>

	<!-- 隐藏域 start-->
	<!-- 隐藏一个添加前的违章总积分 -->
	<input id="beforeBreakScore" type="hidden" value="" />

	<!-- 隐藏域  end -->
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
						<span>短委员工管理</span><span>>员工管理</span>
					</div>
					<div class="el_main">

						<!--树-->
						<div class="el_leftTree">
							<div  style="margin-top:20px;">
							<!--标题类，添加了一个颜色-->
							<span class="el_treeTitle">检修单位</span>
							<select class="btn btn-default mark-type" id="el_bigStatusMark"	title="请选择" onchange="historyBigInfoFind()">
															<option value="0">当前检修</option>
															<option value="1">历史检修</option>
							</select>
							<ul id="departmentAndOverHaulTree" class="ztree"></ul>
							</div>
						</div>

						<!--内容-->
						<div class="el_qlmContent">

							<!--索引-->
							<div class="row el_queryBox" id="queryDiv">
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

									<div class="row el_queryBoxrow">
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
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">黑名单：</span> <label
													class="el_radioBox"><input type="radio"
													name="blackListInfo" value="是"> 是</label> <label
													class="el_radioBox"><input type="radio"
													name="blackListInfo" value="否"> 否</label>
											</div>
										</div>

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
										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;种：</span>
												<select class="selectpicker form-control" title="请选择"
													name="employeeType" id="query_empType">

												</select>
											</div>
										</div>

									</div>
									<!-- 新添加的违章时间 -->
									<div class="row el_queryBoxrow">
										<div class="col-md-6" id="el_breakTimeIndex"
											style="right: -9px;">
											<div class="input-group" id="el_startEndTime" role="toolbar">
												<span class="el_spans">违章时间：</span> <input type="text"
													class=" form-control query_dep_starttime" name="fstarttime"
													id="inpstart2" placeholder="开始时间" value="" readonly>
												<input type="text" class="form-control query_dep_endtime"
													id="inpend2" name="fendtime" placeholder="结束时间" value=""
													readonly>
											</div>
										</div>
										
										<div class="col-md-4 el_qlmQuery" style="right: -39px;">
											<div class="input-group" role="toolbar">
												<span class="el_spans">家庭住址：</span> <input class="form-control" name="address" type="text">
											</div>
										</div>
										
										
									</div>
									<div class="row el_queryBoxrow">
										<div class="col-md-6" id="el_breakTimeIndex" style="right: -9px;">
											<div class="input-group" id="el_startEndTime" role="toolbar">
												<span class="el_spans">年 &nbsp;龄&nbsp; 段：</span> <input type="text"
													class=" form-control query_dep_starttime" name="ageLeft"
													> <input
													type="text" class="form-control query_dep_endtime"
													 name="ageRight" >
											</div>
										</div>
									</div>
									
									<!-- 隐藏部门ID和大修ID -->
									<input type="hidden" name="unitId" id="query_unitId" /> <input
										type="hidden" name="bigId" id="query_bigId" />
									<!-- 隐藏当前页和显示条数 -->
									<input type="hidden" name="currentPage" id="currentPage" /> <input
										type="hidden" name="currentCount" id="currentCount" />
									<!-- 增加标记外来单位员工管理 -->
									<input type="hidden" name="markTrainType" value="1" />

									<!-- 隐藏违章信息查询类型，默认查询当前年 -->
									<input id="breakInfo_Type" type="hidden" value="0"
										name="empBreakInfoType">
										
									<!-- 隐藏大修状态标记，默认查询当前未结束的-->
									<input id="bigStatus_Mark" type="hidden" value="0"
										name="bigStatusMark">
									

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
							<h3 class="el_mainHead">短委员工信息
								<a href=javascript:void(0) onclick="toggleQueryDiv()">
									<span style="margin-right:50px;float:right;color:rgb(221,221,221);" title="点击隐藏查询条件" class="glyphicon glyphicon-chevron-up"></span>
								</a>
							</h3>

							<div class="panel panel-default el_Mainmain">


								<!--按钮面板-->
								<div class="panel-body">

									<!--按钮区-->
									<div class="panel panel-default">
										<div class="panel-body el_MainxiaoMain">

											<div class="el_topButton">
												<!-- 新添加的违章信息、当前违章、历史违章 -->
												<div class="el_topButton">
													<!-- 按钮触发模态框1 -->
													<div class="col-md-8">
														<shiro:hasPermission name="empout:add">
															<button class="btn btn-primary" onclick="el_addEmp()">
																<span class="glyphicon glyphicon-plus"></span>添加员工</button>
														</shiro:hasPermission>

														<button class="btn btn-primary" id="el_lookTrainDocument"
															onclick="el_empTrainDoc()">查看员工培训档案</button>

														<shiro:hasPermission name="outempbreak:add">
															<button id="el_addBreakRules" class="btn btn-primary"
																onclick="el_addBreakInfo()"><span class="glyphicon glyphicon-plus"></span>添加违章信息</button>
														</shiro:hasPermission>


														<select class="btn btn-primary" id="el_breakType"
															title="请选择" onchange="historyBreakInfoFind2()">
															<option value="0">当前违章</option>
															<option value="1">历史违章</option>

														</select>
													</div>
												</div>

											</div>
										</div>
									</div>

									<!-- 添加违章信息的模态框开始 -->

									<!-- 模态框 违章信息添加-->
									<div class="modal fade" id="el_addBreakInfo" tabindex="-1"
										data-backdrop="static" data-keyboard="false" role="dialog"
										aria-labelledby="myModalLabel" aria-hidden="true"
										data-backdrop="static" data-keyboard="false">
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
														<div class="el_threeScoreList888">
															<table class="table table-hover table-bordered">
																<thead>
																	<tr>
																		<th>姓名</th>
																		<th>性别</th>
																		<!--  <td>联系方式</td> -->
																		<th>违章记分</th>
																		<th>所属单位</th>
																		<th>黑名单</th>
																	</tr>
																</thead>
																<tbody>
																	<tr>
																		<td id="addName">asdf</td>
																		<td id="addSex">asdf</td>
																		<!--  <td id="addPhone">asdf</td> -->
																		<td id="addbreakScore">asdf</td>
																		<td id="addunitName">asdf</td>
																		<td id="addIsBreak">asdf</td>
																	</tr>
																</tbody>
															</table>
														</div>

														<!-- 隐藏域 隐藏外来职工的id  隐藏域的值要一起提交到后台-->
														<!-- 隐藏域，隐藏 、职工id、大修外来职工id -->
														<!-- 职工id -->
														<input id="addEmployee" type="hidden" value=""
															name="breakrules.employeeid" />
														<!-- 参加大修外来职工id -->
														<input id="addBigHaulEmployeeId" type="hidden" value=""
															name="breakrules.bigemployeeoutid" />
														<!-- 隐藏一个单位id -->
														<input id="addUnitidM" type="hidden" name="addUnitidM"
															value="" />
														<!-- 隐藏一个大修id  添加违章信息的时候没用到这个-->
														<input id="addBigHaulId" type="hidden" name="addHaulBigId"
															value="" />
														<!-- 隐藏一个职工的违章id 这个在添加违章信息的时候没用到这个-->
														<input id="addEmpBreakId" type="hidden"
															name="addempBreakId" value="" />


														<div class="input-group el_modellist" role="toolbar">
															<span class="el_spans">违章时间：</span> <input type="text"
																id="test4" class="wicon form-control"
																name="breakrules.breaktime" readonly />
														</div>

														<div class="input-group el_modellist" role="toolbar">
															<span class="el_spans">违章积分：</span>
															<!--不得超过12分-->
															<input id="breakScoreID" type="text"
																class="form-control el_modelinput"
																name="breakrules.minusnum" />
														</div>

														<div class="input-group el_modellist" role="toolbar">
															<span class="el_spans">违章内容：</span>
															<textarea id="addBreakContent"
																class="form-control el_modelinput" rows="3" value=""
																name="breakrules.breakcontent"></textarea>
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
									<!-- 模态框  用于当前添加的违章积分 大于12分的情况的提示 start-->
									<div class="modal fade" id="addAlertMsg" data-backdrop="static"
										data-keyboard="false">
										<div class="modal-dialog" data-backdrop="static"
											data-keyboard="false">
											<div class="modal-content message_align">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">×</span>
													</button>
													<h4 class="modal-title">提示</h4>
												</div>
												<div class="modal-body">
													<p>当前添加的违章积分大于等于12，即将永久加入黑名单，是否继续添加？</p>
												</div>
												<div class="modal-footer">
													<input type="hidden" id="url" />
													<button type="button" class="btn btn-default"
														data-dismiss="modal">取消</button>
													<a onclick="addAlertMsgBtn()" class="btn btn-success"
														data-dismiss="modal">确定</a>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- /.modal -->
									<!-- 模态框  用于违当前添加的 章记分>=12分的情况 end-->
									<!-- 添加违章信息模态框结束 -->

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
												<th>出生日期</th>
												<th>所属单位</th>
												<th>工种</th>
												<th>违章积分</th>
												<th>黑名单状态</th>
												<!-- <th>考试情况</th> -->
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="employeeOutBaseInfoList">

										</tbody>
									</table>

									<!--分页-->
									<div id="paginationIDU" class="paginationID"></div>
									<br/><br/><br/>
									<br/><br/><br/>
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
											<button class="btn btn-primary el_modellist02"
												onclick="queryEmployeeOutTrainInfo()">查看历史培训档案</button>

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
											<input type="hidden" name="bigEmployeeOutId"
												id="updateAndDelete_bigemployeeOutId" />
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


												<div  id="train_empPhoto" class="input-group el_empPhoto" role="toolbar"
													style="height: 127px;">
													<img id="myimg2" width="95" height="121">
												</div>
												<div  id="train_empBaseInfo" class="el_threeScoreList">

													<table>
														<caption style="margin-bottom: 20px; margin-top: -10px;">员工信息：</caption>
														<tr>
															<td>姓名</td>
															<td id="trainName"></td>
															<td>性别</td>
															<td id="trainSex"></td>
														</tr>
														<tr>
															<td>黑名单</td>
															<td id="trainBlackInfo"></td>
															<td>所属单位</td>
															<td id="trainUnit"></td>
														</tr>


													</table>
												</div>
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
														<tr>
															<td>家庭住址:</td>
															<td id="details_address"></td>
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
	
	<!-- 超过55岁的年龄超时提醒 -->
	<div id="msg">
		<a>X</a>
		<center>
			<p>请注意:以下短委员工年龄已超过五十五岁</p>
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