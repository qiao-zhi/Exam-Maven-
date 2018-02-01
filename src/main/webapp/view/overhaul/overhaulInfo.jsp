<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 获取页面传来的大修ID进行初始化 -->
<%
	String haulId = request.getParameter("haulId");
	if (haulId != null) {
		request.setAttribute("haulId", haulId);
	}
	//标记是否已经结束
	String isf = request.getParameter("isf");
	if (isf != null) {
		request.setAttribute("isf", isf);
	}
%>

<%@ include file="/public/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>检修详情</title>

<%@ include file="/public/cssJs.jsp"%>
<!-- 格式化日期函数 -->
<script src="<%=path%>/js/public/dateformat.js"></script>
<script type="text/javascript">
	/* 初始化的时候设置一个项目名字全局变量与大修ID变量 */
	var contextPath = "${baseurl}";
	var haulId = "${haulId}";
	var hasOutunitOperating = false;//记录是否有删除外来单位权限
</script>
<!-- 有修改删除外来单位的权限就修改全局变量的值 -->
<shiro:hasPermission name="outunit:operating">
	<script>
		hasOutunitOperating = true;
	</script>
</shiro:hasPermission>

<!--验证-->
<script src="<%=path%>/controls/validate/jquery.validate.js"></script>
<script src="<%=path%>/controls/validate/messages_zh.js"></script>
<style type="text/css">
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

<script src="<%=path%>/js/overhaul/overhaulInfo.js"></script>

<link rel="stylesheet" href="<%=path%>/css/overhaul/overhaulInfo.css">
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
						<span>短委员工管理</span><span>><a
							href="<%=path%>/view/overhaul/overhaulManage.jsp">检修项目管理</a></span><span>>检修详情</span>
					</div>

					<!--添加考试基本内容-->
					<div class="row el_addContent">

						<h2 class="el_mainHead">检修项目详细信息</h2>

						<!-- 隐藏检修ID -->
						<input type="hidden" id="hauid" value="${haulId}" />
						<!-- 大修详细信息 -->
						<div id="overhaulInfo">
							<span class="sdf"><b>检修基本信息</b></span>
							<table>
<!-- 							<table class="table table-hover table-bordered"> -->
								<thead>
									<tr>
										<th>检修名称</th>
										<th>时间</th>
										<th>状态</th>
									</tr>
								</thead>
								<tr>
									<td id="haulName"></td>
									<td id="haulTime"></td>
									<td id="haulStatus"></td>
									<td id="haulDesc"></td>
								</tr>
							</table>
						</div>
						<!--分割线 -->
						<hr></hr>
						<span class="sdf" style="margin-left: 40px;"><b>参加单位信息</b></span>
						<!-- 收缩查询的条件 -->
						<a href=javascript:void(0) style="margin-left:70%;color:rgb(221,221,221);" onclick="toggleQueryDiv()"><span class="glyphicon glyphicon-arrow-down" title="点击显示查询条件"></span></a>
						
						
						 <br />
						<br />

						<div id="query_haulunit_div" style="display:none;">
							<form id="queryHaulunitForm">
								<!--隐藏当前页与页大小  -->
								<input type="hidden" name="currentPage" id="currentPage">
								<input type="hidden" name="currentCount" id="currentCount">
								<!-- 隐藏一个大修的ID -->
								<input type="hidden" name="bigId" value="${haulId}">
								<div class="row">
									<div class="col-md-3 el_qlmQuery">
										<div class="input-group" role="toolbar">
											<span class="el_spans">单位名称：</span> <input type="text"
												class="form-control" name="unitName" />
										</div>
									</div>

									<div class="col-md-3 el_qlmQuery">
										<div class="input-group" role="toolbar">
											<span class="el_spans">违章积分：</span>
											<!--<span class="input-group-addon">违章积分范围</span>-->
											<select class="selectpicker form-control" title="请选择"
												name="unitMinus">
												<option value="">--请选择--</option>
												<option value="0-15">15分以下</option>
												<option value="15-30">15-30分</option>
												<option value="30-50">30-50分</option>
												<option value="50-100">50-100分</option>
												<option value="100-1000000">100分以上</option>
											</select>
										</div>
									</div>

									<div class="col-md-3 el_qlmQuery">
										<div class="input-group" role="toolbar">
											<span class="el_spans">项目名称：</span> <input type="text"
												class="form-control" name="projectName" id="projectName" />
										</div>
									</div>


								</div>


								<div class="row">
									<div class="col-md-4 el_qlmQuery" id="el_breakTimeIndex">
										<div class="input-group" id="el_startEndTime" role="toolbar">
											<span class="el_spans">违章时间：</span> <input type="text"
												class="form-control query_dep_starttime" name="fstarttime"
												placeholder="开始时间" id="inpstart2" readonly> <input
												type="text" class=" form-control query_dep_endtime"
												id="inpend2" placeholder="结束时间" name="fendtime" readonly>
										</div>
									</div>
									<div class="el_qlmQuery"
										style="margin-left: 500px; text-align: right;">
										<!--提交查询按钮-->
										<button type="button" id="queryHualunitBtn"
											class="btns btn-primary  btn-sm">查询</button>
										<!--清空按钮-->
										<button type="reset" class="btn btn-default  btn-sm">清空</button>
									</div>
								</div>


							</form>
						</div>

						<!--分割线 -->
						<hr id="query_haulunit_hr" style="display: none"></hr>

						<div class="panel-body">
							<!--内容，选择试卷-->
							<div class=" col-md-12">

								<!--操作按钮-->
								<div class="row el_topButtonP">
									<div class="">
										<!-- 如果大修没有结束的话就可以显示下面的按钮 -->
										<c:if test="${isf != '1' }">
											<%-- <a href="<%=path%>/view/outDepart/projectManage.jsp">
												<button class="btn btn-primary btn-sm">添加工程</button>
											</a>
											<button class="btn btn-primary btn-sm" onclick="el_addEmp()">
												添加员工</button> --%>
											<shiro:hasPermission name="outunit:add">
												<a href=javascript:void(0) onclick="addUnit()">
													<button class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus"></span>添加单位</button>
												</a>
											</shiro:hasPermission>
										</c:if>
									</div>
								</div>
								<!--结束 查询表单提交-->


								<br />
								<!--显示内容-->
								<div class="panel panel-default el_Mainmain">

									<!--按钮面板-->
									<div class="panel-body">
										<!-- 用于分页查询基本信息 -->
										<table class="table table-hover table-bordered">
											<thead>
												<tr>
													<th>序号</th>
													<th>单位名称</th>
													<th>所属检修</th>
													<th>项目经理</th>
													<th>经理电话</th>
													<th>安全员</th>
													<th>安全员电话</th>
													<th>参与项目</th>
													<th>违章积分</th>
													<th>单位人数</th>
													<th>加权积分</th>
													<th width="150">操作</th>
												</tr>
											</thead>
											<tbody id="haunUnitTbody">
											</tbody>
										</table>

										<!--分页-->
										<div id="paginationIDU" class="paginationID"></div>
									</div>
								</div>


								<!-- 模态框 单位修改-->
								<div class="modal fade" id="myModal2" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<!--关闭符号-->
												<!--标题-->
												<h4 class="modal-title" id="myModalLabel24">修改部门信息</h4>
											</div>
											<form id="updateForm">
												<div class="modal-body">
													<!-- 隐藏一个部门ID -->
													<input type="hidden" id="update_unitid" name="unitid">
													<!-- 隐藏一个大修部门ID -->
													<input type="hidden" id="update_haulUnitid"
														name="haulUnit.unitbigid">
													<!-- 隐藏一个检修ID -->
													<input type="hidden" id="update_haulUnitid"
														name="haulUnit.bigid" value="${haulId }">

													<div class="input-group el_modellist" role="toolbar">
														<!--<span class="input-group-addon">单位名称</span>-->
														<span class="el_spans">单位名称：</span> <input type="text"
															class="form-control el_modelinput clearFormInput"
															name="name" id="update_name" /> <span></span>
													</div>



													<div class="input-group el_modellist" role="toolbar">
														<!--<span class="input-group-addon">单位地址</span>-->
														<span class="el_spans">项目经理：</span> <input type="text"
															class="form-control addUnitInput" name="haulUnit.manager"
															id="update_manager" />
													</div>



													<div class="input-group el_modellist" role="toolbar">
														<!--<span class="input-group-addon">联系人</span>-->
														<span class="el_spans">经理电话：</span> <input type="text"
															class="form-control addUnitInput"
															id="update_managerphone" name="haulUnit.managerphone" />
													</div>

													<div class="input-group el_modellist" role="toolbar">
														<!--<span class="input-group-addon">联系方式</span>-->
														<span class="el_spans">安&nbsp;全&nbsp;员&nbsp;：</span> <input
															type="text" class="form-control addUnitInput"
															name="haulUnit.secure" id="update_secure" />
													</div>
													<div class="input-group el_modellist" role="toolbar">
														<!--<span class="input-group-addon">联系方式</span>-->
														<span class="el_spans">安全员电话：</span> <input type="text"
															class="form-control addUnitInput"
															name="haulUnit.securephone" id="update_securephone" />
													</div>

													<div class="input-group el_modellist" role="toolbar" id="updateBiaoduanDiv">
														<!--<span class="input-group-addon">联系方式</span>-->
														<span class="el_spans">参与工程：</span>  <input type="hidden"
															class="form-control addUnitInput"
															name="haulUnit.projectnames" id="update_projectnames" />
															<input type="hidden"
															class="form-control addUnitInput"
															name="projectids" id="update_projectids" />
															
															
															
															
															
															
													</div>

													<div class="input-group el_modellist" role="toolbar">
														<!--<span class="input-group-addon">上级单位</span>-->
														<span class="el_spans">所属检修：</span> <input type="text"
															class="form-control" name="" value="" id="update_big"
															disabled />
													</div>

													<div class="input-group el_modellist" role="toolbar">
														<!--<span class="input-group-addon">上级单位</span>-->
														<span class="el_spans">违章积分：</span> <input type="text"
															class="form-control" name="" id="update_nuitMinus"
															value="" disabled />
													</div>

												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">关闭</button>
													<button type="button" class="btn btn-primary"
														onclick="updateUnit()">保存</button>
												</div>
											</form>

										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal -->
								</div>



								<!-- 模态框  信息删除确认 -->
								<div class="modal fade" id="delcfmModel">
									<div class="modal-dialog">
										<div class="modal-content message_align">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">×</span>
												</button>
												<h4 class="modal-title">提示&nbsp;&nbsp;&nbsp;!!!&nbsp;&nbsp;</h4>
											</div>
											<div class="modal-body">
												<form id="deleteUnitForm">
													<!-- 隐藏需要删除的id -->
													<input type="hidden" id="deleteBigId" class="queryIsFinish"
														name="bigId" /> <input type="hidden" id="deleteUnitId"
														name="unitId" />
												</form>
												<p style="font-size: 25px">您确认要删除该条信息吗?</p>
												<br /> <br />
												<p style="font-size: 18px; color: red">如果删除,该部门下的员工信息以及员工考试与成绩信息也将被删除！</p>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">取消</button>
												<a onclick="deleteSubmit()" class="btn btn-success"
													data-dismiss="modal">确定</a>
											</div>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
								</div>
								<!-- /.modal -->


								<!-- 模态框 点击违章积分，显示违章员工-->
								<div class="modal fade" id="el_bRInfor" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<!--关闭符号-->
												<!--标题-->
												<h4 class="modal-title" id="myModalLabel27">部门员工违章信息</h4>
											</div>
											<form>
												<div class="modal-body">
													<table class="table table-hover table-bordered">
														<thead>
															<tr>
																<th>违章员工</th>
																<th>性别</th>
																<th>身份证</th>
																<th>违章内容</th>
																<th>违章时间</th>
																<th>扣分</th>
															</tr>
														</thead>
														<tbody id="breakrulesTbody">
														</tbody>
													</table>

													<div id="paginationIDU1" class="paginationID"></div>

												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">关闭</button>
												</div>
											</form>
											<!--查询违章信息的条件  -->
											<form id="query_break_form">
												<input type="hidden" id="query_unitid" name="unitId">
												<input type="hidden" id="query_bigid" name="bigId">
												<input type="hidden" id="query_fstarttime" name="fstarttime">
												<input type="hidden" id="query_fendtime" name="fendtime">
												<input type="hidden" id="currentPage1" name="currentPage"
													value="1"> <input type="hidden" id="currentCount1"
													name="currentCount" value="8">
											</form>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal -->
								</div>




								<!-- 检修单位下的员工信息模态框-->
								<div class="modal fade" id="employeeModal" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog" style="width: 700px">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<!--关闭符号-->
												<!--标题-->
												<h4 class="modal-title" id="myModalLabel27">部门员工信息</h4>
											</div>
											<form>
												<div class="modal-body">


													<table class="table table-hover table-bordered">
														<thead>
															<tr>
																<th>员工姓名</th>
																<th>身份证</th>
																<th>性别</th>
																<th>地址</th>
																<th>工种</th>
																<!-- <th>电话</th> -->
																<!-- <th>扣分情况</th> -->
															</tr>
														</thead>
														<tbody id="employeeTbody">

														</tbody>
													</table>
													<!-- 隐藏查询条件 -->
													<input type="hidden" id="currentPage2" name="currentPage"
														value="1"> <input type="hidden" id="currentCount2"
														name="currentCount" value="8"> <input
														type="hidden" id="q_bigId"> <input type="hidden"
														id="q_unitId">
													<div id="paginationIDU2" class="paginationID"></div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">关闭</button>
												</div>
											</form>

										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal -->
								</div>




								<!-- 模态框单位添加-->
								<div class="modal fade" id="myModal" role="dialog"
									aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<!--关闭符号-->
												<!--标题-->
												<h4 class="modal-title" id="myModalLabel2">添加单位信息</h4>
											</div>
											<form id="addUnitForm">
												<div class="modal-body">

													<div class="input-group el_modellist" role="toolbar">
														<!--<span class="input-group-addon">单位名称</span>-->
														<span class="el_spans">单位名称：</span> <input type="text"
															id="addUnitname" class="form-control addUnitInput"
															name="name" onkeyup="findNames(this)" /> <span
															id="validateName" style="color: red"></span>
														<div id="showDiv"
															style="margin: 25px 0px 0px 0px; position: absolute; width: 78%; z-index: 3000; background-color: white; border: 1px solid; display: none;"></div>
													</div>

													<div class="input-group el_modellist" role="toolbar">
														<!--<span class="input-group-addon">单位地址</span>-->
														<span class="el_spans">项目经理：</span> <input type="text"
															class="form-control addUnitInput" name="haulUnit.manager"
															id="manager" />
													</div>

													<div class="input-group el_modellist" role="toolbar">
														<!--<span class="input-group-addon">联系人</span>-->
														<span class="el_spans">经理电话：</span> <input type="text"
															class="form-control addUnitInput" id="managerphone"
															name="haulUnit.managerphone" />
													</div>

													<div class="input-group el_modellist" role="toolbar">
														<!--<span class="input-group-addon">联系方式</span>-->
														<span class="el_spans">安&nbsp;全&nbsp;员&nbsp;：</span> <input
															type="text" class="form-control addUnitInput"
															name="haulUnit.secure" id="secure" />
													</div>
													<div class="input-group el_modellist" role="toolbar">
														<!--<span class="input-group-addon">联系方式</span>-->
														<span class="el_spans">安全员电话：</span> <input type="text"
															class="form-control addUnitInput"
															name="haulUnit.securephone" id="securephone" />
													</div>

													<div class="input-group el_modellist" role="toolbar" id="projectDiv">
														<!--<span class="input-group-addon">联系方式</span>-->
														<span class="el_spans">参与工程：</span>
																
																 <input type="hidden"
															class="form-control addUnitInput"
															name="haulUnit.projectnames" id="projectnames" />
															<!-- 隐藏工程ID -->
																 <input type="hidden"
															class="form-control addUnitInput"
															name="projectids" id="projectids" />
															
															
															
													</div>

													<div class="input-group el_modellist" role="toolbar">
														<!--<span class="input-group-addon">上级单位</span>-->
														<span class="el_spans">所属检修：</span> <input type="text"
															class="form-control el_modelinput el_chooseInput"
															id="bigname" name="bigname" disabled /> <input
															type="hidden"
															class="form-control el_modelinput el_chooseInput"
															id="bigid" name="bigid" value="${haulId}" />
													</div>
													<!--  -->

												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">关闭</button>
													<button type="button" class="btn btn-primary"
														onclick="saveUnit()">保存</button>
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
	</div>


	<!--放脚-->
	<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>
