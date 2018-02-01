<!-- 修改考试页面 -->
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
<title>修改考试</title>
<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	/* 一个是记录项目名字  一个是考试ID 一个是记录试卷ID 一个是考试类型 */
	var examPaperId = "${response.examBaseInfo.paperid}";
	var examId = "${response.examBaseInfo.examid}";
	var examType = "${response.examBaseInfo.examtype}";
	var contextPath = "${pageContext.request.contextPath}";
</script>
<%@ include file="/public/cssJs.jsp"%>

<!--复选框 树-->
<%-- <script src="<%=path%>/js/public/treeC.js"></script> --%>

<%-- <script src="<%=path%>/js/examParper/exam/addExam.js"></script> --%>
<script src="<%=path%>/js/examParper/exam/updateExam.js"></script>
<link rel="stylesheet" href="<%=path%>/css/examParper/modifyExam.css">
<!-- 日期格式转换 -->
<script
	src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>
<style type="text/css">
#el_chooseUnit li {
	list-style-type: none;
	float: left;
}

#el_chooseDepart1 li {
	list-style-type: none;
	float: left;
}
/*validate中不成功显示的样式设置*/
label.error {
	background: url(${baseurl }/controls/validate/unchecked.gif) no-repeat
		10px 3px;
	padding-left: 30px;
	font-family: georgia;
	font-size: 15px;
	font-style: normal;
	color: red;
}

label.success {
	background: url(${baseurl }/controls/validate/checked.gif) no-repeat
		10px 3px;
	padding-left: 30px;
}
</style>
<script src="${baseurl }/controls/validate/jquery.validate.js"
	type="text/javascript" charset="utf-8"></script>
<script src="${baseurl }/controls/validate/messages_zh.js"
	type="text/javascript" charset="utf-8"></script>
<script>
	$(function() {
		$("#inpstart0").jeDate({
			isinitVal : false,
			minDate : '2000-06-16',
			maxDate : '2225-06-16',
			format : 'YYYY-MM-DD hh:mm:ss',
			zIndex : 3000
		})

		$("#inpend0").jeDate({
			isinitVal : false,
			minDate : '2000-06-16',
			maxDate : '2225-06-16',
			format : 'YYYY-MM-DD hh:mm:ss',
			zIndex : 3000
		})

	})
</script>
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
				<div class="panel panel-default el_queryBoxMainPage">
					<!--菜单连接标题-->
					<div class="panel-heading">
						<span>培训管理</span><span>>修改试卷</span>
					</div>

					<!--添加考试基本内容-->
					<div class="row el_addContent">
						<h4 class="el_mainHead">修改考试基本信息</h4>

						<form action="" id="updateForm">
							<!--隐藏试卷ID  -->
							<input type="hidden" name="exam.paperid"
								value="${response.examBaseInfo.paperid}">
							<!--隐藏考试ID  -->
							<input type="hidden" name="exam.examid"
								value="${response.examBaseInfo.examid}">
							<!--隐藏检修ID  -->
							<input type="hidden" name="exam.bigid"
								value="${response.examBaseInfo.bigid}">

							<!-- 隐藏内部考试员工ID -->
							<c:if test="${response.examBaseInfo.examtype eq '内部考试' }">
								<div id="innerEmployeeDiv">
									<c:forEach var="employee" items="${response.employees}"
										varStatus="status">
										<input type="hidden" value="${employee.idCode }"
											name="exam.employeeInExams[${status.index }].employeeid">
										<input type="hidden" value="${employee.name }"
											name="exam.employeeInExams[${status.index }].employeename">
										<input type="hidden" value="${employee.unitId }"
											name="exam.employeeInExams[${status.index }].unitid">
									</c:forEach>
								</div>
							</c:if>
							<!-- 隐藏外部考试员工ID -->
							<c:if test="${response.examBaseInfo.examtype eq '外部考试' }">
								<div id="outEmployeeDiv">
									<c:forEach var="employee" items="${response.employees}"
										varStatus="status">
										<input type="hidden" value="${employee.idCode }"
											name="exam.employeeOutExams[${status.index }].employeeid">
										<input type="hidden" value="${employee.name }"
											name="exam.employeeOutExams[${status.index }].employeename">
										<input type="hidden" value="${employee.distributeid }"
											name="exam.employeeOutExams[${status.index }].distributeid">
										<input type="hidden" value="${employee.unitid }"
											name="exam.employeeOutExams[${status.index }].unitid">
										<input type="hidden" value="${employee.bigemployeeoutid }"
											name="exam.employeeOutExams[${status.index }].bigemployeeoutid">
									</c:forEach>

								</div>
							</c:if>

							<div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">考试名称：</span> <input type="text"
									class="form-control el_modelinput" name="exam.examname"
									value="${response.examBaseInfo.examname }" />
							</div>

							<!-- 考试级别 -->
							<div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">考试级别：</span><select
									class="selectpicker form-control" title="请选择"
									name="exam.examlevel" id="exam_level"
									onchange="queryPaper(this)">
									<option value="">--请选择--</option>
									<option value="1"
										<c:if test="${'1' eq response.examBaseInfo.examlevel}">selected</c:if>>一级(厂级)</option>
									<option value="2"
										<c:if test="${'2' eq response.examBaseInfo.examlevel}">selected</c:if>>二级(部门级)</option>
									<option value="3"
										<c:if test="${'3' eq response.examBaseInfo.examlevel}">selected</c:if>>三级(班级)</option>
								</select>
							</div>


							<!-- 培训内容 -->
							<div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">培训内容：</span> <input type="text"
									name="exam.traincontent"
									value="${response.examBaseInfo.traincontent}"
									class="form-control el_modelinput" />
							</div>


							<!-- 培训时长 -->
							<div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">培训时长：</span> <input type="text"
									style="width: 76px;" name="exam.xueshi"
									value="${response.examBaseInfo.xueshi}"
									class="form-control el_modelinput" />学时
							</div>



							<!--根据选择的部门类型，
                动态的加载相应部门类型的部门
                再调用相应部门类型的添加部门一人的模态-->
							<div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">部门类型：</span>
								<c:if test="${response.examBaseInfo.examtype eq '内部考试' }">
									<label class="el_radioBox el_radioBox2"> <input checked
										type="radio" name="el_departType" checked value="0">厂内部门
									</label>
								</c:if>
								<c:if test="${response.examBaseInfo.examtype eq '外部考试'}">
									<label class="el_radioBox el_radioBox2"> <input checked
										type="radio" name="el_departType" value="1">外来单位
									</label>
								</c:if>
							</div>

							<div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">考试方式：</span> 
									<c:if test="${response.examBaseInfo.examtype eq '内部考试' }">
									 	<label
										class="el_radioBox el_radioBox2">
										<input type="radio"
										name="examMethod"
										<c:if test="${'线上' eq response.employees[0].examMethod }">checked</c:if>
										value="线上" id="">线上
										</label>
									</c:if>
								 <label class="el_radioBox el_radioBox2"> <input
									type="radio"
									<c:if test="${'线下' eq response.employees[0].examMethod }">checked</c:if>
									name="examMethod" value="线下" id="">线下
								</label>
							</div>

							<%-- <div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">考试地点：</span> <input type="text"
									class="form-control el_modelinput" name="exam.address"
									value="${response.examBaseInfo.address }" />
							</div> --%>

							<div class="input-prepend input-group el_modellist el_modellist5">
								<span class="add-on el_spans">考试时间：</span><input type="text"
									class="wicon el_noVlaue form-control" id="inpstart0"
									name="exam.starttime"
									value='<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value="${response.examBaseInfo.starttime}" />'
									readonly> <input type="text"
									class="wicon el_noVlaue form-control" id="inpend0"
									name="exam.endtime"
									value='<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value="${response.examBaseInfo.endtime}" />'
									readonly>
							</div>

							<div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">考试时长：</span> <input type="text"
									class="form-control el_modelinput" name="exam.answertime"
									style="width: 76px;"
									value="${response.examBaseInfo.answertime}" />分钟
							</div>

							<div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">创&nbsp;&nbsp;建&nbsp;人：</span> <input
									type="text" class="form-control el_modelinput"
									name="exam.employeename" readonly="readonly"
									value="${response.examBaseInfo.employeename}" />
							</div>

							<div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">创建时间：</span> <input type="text"
									name="exam.createtime" class="wicon el_noVlaue form-control"
									value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value="${response.examBaseInfo.createtime}"/>"
									readonly />
							</div>



							<c:if test="${response.examBaseInfo.examtype eq '内部考试' }">
								<!-- 内部显示的东西 -->
								<div class="input-group el_modellist inShow" role="toolbar">
									<span class="el_spans el_chooseSpan">厂内部门：</span>
									<ul id="el_chooseDepart1" class="form-control"></ul>
									<img
										src="${baseurl }/controls/selectDropTree/smallTriangle.png"
										class="el_smallTriangle" width="7" />
									<ul id="treeDemo10" class="ztree"
										style="width: 300px !important; display: none"></ul>
								</div>
							</c:if>


							<c:if test="${response.examBaseInfo.examtype eq '外部考试' }">
								<!-- 外部显示的东西 -->
								<div class="input-group el_modellist outShow" id="bigNameDiv">
									<span class="el_spans">检修项目：</span><select
										class="selectpicker form-control" title="请选择"
										name="exam.bigid" id="bigName" onchange="queryHaulUnit()"
										disabled="disabled">
										<option value="" selected>---请选择检修后选择部门---</option>
									</select>
								</div>
								<div class="input-group el_modellist outShow" role="toolbar">
									<span class="el_spans el_chooseSpan">外来单位：</span>
									<ul id="el_chooseUnit" class="form-control"
										onclick="openUnitModal()"></ul>
									<img
										src="${baseurl }/controls/selectDropTree/smallTriangle.png"
										class="el_smallTriangle" width="7" />
									<ul id="outUnitModal" class="ztree" style="display: none"></ul>
								</div>
							</c:if>


							<div class="el_modellist el_modellist2">
								<div class="el_examPersonLeft">
									<span class="el_spans">选择员工：</span>
								</div>
							</div>


							<!--S 选择内部员工  -->
							<c:if test="${response.examBaseInfo.examtype eq '内部考试' }">
								<div class="el_examPersons el_examPersons2" id="addInDiv">
									<div class="el_chooseDepartButton">
										<input type="button" class="btn-sm btn btn-default"
											id="selectEmployeeIn" value="修改内部部门人员">
									</div>
									<div id="department_employee_in">
										<!-- <div class="panel panel-default el_departPersons"
											id="部门1">
											<div class="panel-heading">
												<span class="el_addDepart">部门1</span>&nbsp;&nbsp;(人数：<span>1</span>)
											</div>
											<div class="panel-body">
												<span class="el_empList">李明 <img class="el_empDelete"
													src="../../../image/delete.png" /></span>
											</div>
										</div> -->

										<!-- <div class="panel panel-default el_departPersons">
											<div class="panel-heading" id="部门1">
												<span class="el_addDepart">部门1</span>&nbsp;&nbsp;(人数：<span>2</span>)
											</div>
											<div class="panel-body">
												<span class="el_empList">李明1 <img
													class="el_empDelete" src="../../../image/delete.png" />
												</span> <span class="el_empList">李明2 <img
													class="el_empDelete" src="../../../image/delete.png" />
												</span>
											</div>
										</div> -->
									</div>

								</div>
							</c:if>
							<!--E 选择内部员工  -->



							<!--S 选择外部员工  -->
							<c:if test="${response.examBaseInfo.examtype eq '外部考试' }">
								<div id="addOutDiv" class="el_examPersons el_examPersons2">
									<div class="el_chooseDepartButton">
										<input type="button" class="btn-sm btn btn-primary"
											id="selectEmployeeOut" value="修改外来参考人员">
									</div>
									<div id="department_employee_out">
										<!-- <div class="panel panel-default el_departPersons"
											id="部门1">
											<div class="panel-heading">
												<span class="el_addDepart">部门1</span>&nbsp;&nbsp;(人数：<span>1</span>)
											</div>
											<div class="panel-body">
												<span class="el_empList">李明 <img class="el_empDelete"
													src="../../../image/delete.png" /></span>
											</div>
										</div> -->

										<!-- <div class="panel panel-default el_departPersons">
											<div class="panel-heading" id="部门1">
												<span class="el_addDepart">部门1</span>&nbsp;&nbsp;(人数：<span>2</span>)
											</div>
											<div class="panel-body">
												<span class="el_empList">李明1 <img
													class="el_empDelete" src="../../../image/delete.png" />
												</span> <span class="el_empList">李明2 <img
													class="el_empDelete" src="../../../image/delete.png" />
												</span>
											</div>
										</div> -->
									</div>
								</div>
							</c:if>
							<!--E 选择外部员工  -->



						</form>

						<!-- 模态框 查询添加员工，外来单位-->
						<div class="modal fade" id="outEmployeeModal" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog"
								style="height: 500px; overflow-y: auto;">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<!--关闭符号-->
										<!--标题-->
										<h4 class="modal-title" id="myModalLabel">添加外来单位员工</h4>
									</div>
									<div class="modal-body">
										<!--索引-->
										<div class="el_queryBox">
											<form action="" id="queryOutForm">

												<!-- 隐藏部门串 -->
												<input type="hidden"
													name="queryOuterEmployeesCondition.units" />
												<!-- 隐藏一个检修ID -->
												<input type="hidden"
													name="queryOuterEmployeesCondition.bigId" />
												<!-- 隐藏一个培训情况 -->
												<input type="hidden"
													name="queryOuterEmployeesCondition.trainStatus"
													value="${response.examBaseInfo.examlevel }" />
												<div class="row">

													<div class="el_qlmQuery">
														<div class="input-group" role="toolbar">
															<span class="el_spans">姓名：</span> <input type="text"
																class="form-control"
																name="queryOuterEmployeesCondition.name" />
														</div>
													</div>

													<div class="el_qlmQuery">
														<div class="input-group" role="toolbar">
															<span class="el_spans">性别：</span> <label
																class="el_radioBox"><input type="radio"
																name="queryOuterEmployeesCondition.sex" value="1">
																男</label> <label class="el_radioBox"><input type="radio"
																name="queryOuterEmployeesCondition.sex" value="2">
																女</label>
														</div>
													</div>

													<div class="el_qlmQuery">
														<div class="input-group" role="toolbar">
															<span class="el_spans">身份证：</span> <input type="text"
																class="form-control"
																name="queryOuterEmployeesCondition.idCode" width="120" />
														</div>
													</div>

												</div>

												<div class="row">

													<div class="el_qlmQuery">
														<div class="input-group" role="toolbar">
															<span class="el_spans">工种：</span> <select
																name="queryOuterEmployeesCondition.empType"
																class="selectpicker form-control" title="请选择"
																id="employType">
																<option value="">--请选择--</option>
															</select>
														</div>
													</div>
													
													
													<div class="el_qlmQuery">
														<div class="input-group" role="toolbar">
															<span class="el_spans">违章积分：</span> <select
																name="queryOuterEmployeesCondition.minusNum"
																class="selectpicker form-control" title="请选择">
																<option value="">--请选择--</option>
																<option value="0-3">3分以下</option>
																<option value="4-6">4-6分</option>
																<option value="7-9">7-9分</option>
																<option value="9-11">9-11分</option>
																<option value="12-100">12分及以上</option>
															</select>
														</div>
													</div>
													
												</div>


												<!-- 												<div class="el_qlmQuery">
													<div class="input-group" role="toolbar">
														<span class="el_spans">考试情况：</span> <select
															name="defaultTrainStatus" disabled="disabled"
															class="selectpicker form-control" title="请选择">
															隐藏一个查询的value 
															<option value="">--请选择--</option>
															<option value="0">未通过</option>
															<option value="1">通过一级考试</option>
															<option value="2">通过二级考试</option>
															<option value="3">通过三级考试</option>
														</select>
													</div>
												</div> -->
												<!--清空按钮-->
												<button type="reset"
													class="btn btn-default el_queryButton0 btn-sm">清空</button>
												<!--提交查询按钮-->
												<div class="el_addPersonsQuery">
													<button type="button" id="queryOutBtn"
														class="btn btn-primary el_queryButton btn-sm">查询</button>

												</div>

											</form>
										</div>
										<!--结束 查询表单提交-->

										<!--表格内容-->
										<table
											class="table el_AddPersonTable table-hover table-bordered">
											<thead>
												<tr>
													<th><input type="checkbox" class="el_checkQuanxuan" onclick="allSecectAndNotSelect(this)"/></th>
													<th>姓名</th>
													<th>性别</th>
													<th>身份证</th>
													<th>所属部门</th>
													<th>工种</th>
													<th>违章积分</th>
												</tr>
											</thead>
											<tbody id="outEmployeeTable">
											</tbody>
										</table>
									</div>


									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">关闭</button>
										<button type="button" class="btn btn-primary"
											id="okOutEmployee">确定</button>
									</div>


								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal -->
					</div>


					<!-- 模态框   检修部门信息 -->
					<div class="modal fade" id="unitModal">
						<div class="modal-dialog" style="width: 700px;">
							>
							<div class="modal-content message_align">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
									<h4 class="modal-title">请选择参加考试的部门</h4>
								</div>
								<div class="modal-body">
									<div id="haulUnitDiv">
										<!-- <input type="checkbox" value="1" style="margin-left: 20px;" /><span>1</span>
										<input type="checkbox" value="1" style="margin-left: 20px;" /><span>1</span>
										<input type="checkbox" value="1" style="margin-left: 20px;" /><span>1</span>
										<input type="checkbox" value="1" style="margin-left: 20px;" /><span>1</span>
										<input type="checkbox" value="1" style="margin-left: 20px;" /><span>1</span>
										<input type="checkbox" value="1" style="margin-left: 20px;" /><span>1</span> -->
									</div>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary"
										id="selectUnitBtn" onclick="addUnit2Input()">确认</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->







					<!-- 模态框 查询添加员工，厂内部门-->
					<div class="modal fade" id="innerEmployeeModal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog" style="height: 500px; overflow-y: auto;">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<!--关闭符号-->
									<!--标题-->
									<h4 class="modal-title" id="myModalLabel2">修改内部员工</h4>
								</div>
								<!--索引-->
								<div class="el_queryBox">
									<div class="row el_queryBoxrow">
										<form id="queryInnerForm" action="">
											<div class="el_qlmQuery">
												<div class="input-group" role="toolbar">
													<span class="el_spans">姓名：</span> <input type="text"
														class="form-control"
														name="queryInnerEmployeesCondition.name" />
												</div>
											</div>

											<input type="hidden" value=""
												name="queryInnerEmployeesCondition.departments">
											<div class="el_qlmQuery">
												<div class="input-group" role="toolbar">
													<span class="el_spans">性别：</span> <label
														class="el_radioBox"><input type="radio"
														name="queryInnerEmployeesCondition.sex" value="1">
														男</label> <label class="el_radioBox"><input type="radio"
														name="queryInnerEmployeesCondition.sex" value="0">
														女</label>
												</div>
											</div>

											<div class="el_qlmQuery">
												<div class="input-group" role="toolbar">
													<span class="el_spans">身份证：</span> <input type="text"
														class="form-control"
														name="queryInnerEmployeesCondition.idCode" width="120" />
												</div>
											</div>

											<!-- 											<div class="el_qlmQuery">
												<div class="input-group" role="toolbar">
													<span class="el_spans">考试情况：</span> <select
														name="queryInnerEmployeesCondition.trainStatus"
														class="selectpicker form-control" title="请选择">
														<option value="">--请选择--</option>
														<option value="1">通过一级考试</option>
														<option value="2">通过二级考试</option>
														<option value="3">通过三级考试</option>
														<option value="0">未通过</option>
													</select>
												</div>
											</div> -->

											<!--提交查询按钮-->
											<div>
												<!--清空按钮-->
												<button type="button" id="queryInnerBtn"
													class="btn btn-primary el_queryButton btn-sm">查询</button>
												<button type="reset"
													class="btn btn-default el_queryButton0 btn-sm">清空</button>
											</div>
										</form>

									</div>
								</div>
								<!--结束 查询表单提交-->

								<!--表格内容-->
								<table class="table el_AddPersonTable table-hover table-bordered">
									<thead>
										<tr>
											<th><input type="checkbox" class="el_checkQuanxuan" onclick="allSecectAndNotSelect(this)"/></th>
											<th>姓名</th>
											<th>性别</th>
											<th>身份证</th>
											<th>所属部门</th>
										</tr>
									</thead>
									<tbody id="innerEmployeeTable">
									</tbody>
								</table>

								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button type="button" class="btn btn-primary"
										id="okInnerEmployee">确定</button>
								</div>
								</form>

							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal -->
					</div>
				</div>


				<div class="panel-body">
					<!--内容，选择试卷-->
					<div class=" col-md-12">

						<h4 class="el_mainHead">替换试卷</h4>
						<!--索引-->
						<div class="row">
							<form action="">

								<div class="col-md-3">
									<div class="input-group" role="toolbar">
										<span class="el_spans">试卷名称：</span> <input type="text"
											class="form-control" name="title" id="title" />
									</div>
								</div>

								<div class="col-md-3">
									<div class="input-group" role="toolbar">
										<!-- 隐藏一个试卷级别 -->
										<input type="hidden" name="paper_level" id="paper_level" /> <span
											class="el_spans">试卷级别：</span><select
											class="selectpicker form-control" title="请选择" name="level"
											id="level" disabled="disabled">
											<option value="">--请选择--</option>
											<option value="1"
												<c:if test="${'1' eq response.examBaseInfo.examlevel}">selected</c:if>>一级（厂级）</option>
											<option value="2"
												<c:if test="${'2' eq response.examBaseInfo.examlevel}">selected</c:if>>二级（部门级）</option>
											<option value="3"
												<c:if test="${'3' eq response.examBaseInfo.examlevel}">selected</c:if>>三级（班级）</option>
										</select>
									</div>
								</div>

								<!--清空按钮-->
								<button type="reset"
									class="btn btn-default el_queryButton0 btn-sm">清空</button>
								<!--提交查询按钮-->
								<button type="button" id="querBtn"
									class="btn btn-default btn-sm el_queryButton3">查询</button>

							</form>


						</div>
						<!--结束 查询表单提交-->

						<!--显示内容-->
						<br />
						<div class="panel panel-default el_Mainmain">

							<!--按钮面板-->
							<div class="panel-body">

								<!--表格
                            内容都提取到json里边
                        -->
								<table class="table table-hover table-bordered">
									<thead>
										<tr>
											<th>选择</th>
											<th>试卷标题</th>
											<th>试卷级别</th>
											<th>试卷分数</th>
											<th>试卷使用次数</th>
											<th width="30%">试卷描述</th>
											<th>创建人</th>
											<th>出题时间</th>
											<th width="90">操作</th>
										</tr>
									</thead>
									<tbody id="paperTableBody">
										<!-- 动态添加数据 -->
									</tbody>
								</table>

								<!--分页-->
								<div id="paginationID"></div>
								<!--隐藏分页用的信息  -->
								<input type="hidden" name="currentPage" id="currentPage">
								<input type="hidden" name="currentCount" id="currentCount">

								<div id="el_bt_bu">
									<button type="button" class="btn btn-info" id="updateExamBtn">保存</button>
									<input type="button" class="btn btn-danger" name="back"
										value="取消" onclick="javascript:history.back(-1);" />
								</div>
							</div>
						</div>

					</div>

				</div>
			</div>

		</div>

	</div>
	</div>

	</div>
	<br />
	<br />
	<br />



	</div>
	</div>

	<!--放脚-->
	<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>
