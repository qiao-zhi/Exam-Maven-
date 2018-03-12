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
<title>部门管理</title>

<%@ include file="/public/cssJs.jsp"%>

<!--分页-->
<%-- <script src="<%=path %>/js/public/page.js"></script> --%>

<!--左边的树-->
<!-- <script src="../../js/public/tree.js"></script> -->

<script src="../../js/innerDepart/innerDepart.js"></script>
<script>
	var hasOperatingDepart=false;
	$(function() {
		
		$("#inpstart2").jeDate({
		    isinitVal:false,
		    minDate: '2000-06-16',
		    maxDate: '2225-06-16',
		    format : 'YYYY-MM-DD',
		    zIndex:3000
		})
		
		$("#inpend2").jeDate({
		    isinitVal:false,
		    minDate: '2000-06-16',
		    maxDate: '2225-06-16',
		    format : 'YYYY-MM-DD',
		    zIndex:3000
		})
	})
</script>

<link rel="stylesheet" href="<%=path%>/css/outDepart/outdepartTree.css">
<link rel="stylesheet"
	href="<%=path%>/css/innerDepart/innerdepartManage.css">

<!-- 验证 -->
<script src="<%=path%>/controls/validate/jquery.validate.js"></script>
<script src="<%=path%>/controls/validate/messages_zh.js"></script>
<!-- 日期格式转换 -->
<script
	src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>
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
						<span>内部员工管理</span><span>>部门管理</span>
					</div>
					<div class="panel-body el_main">
						<!--树-->
						<div class="el_leftTree">
							<!--标题类，添加了一个颜色-->
							<span class="el_treeTitle">部门</span>
							<ul id="treeDemo" class="ztree"></ul>
						</div>

						<!--内容-->
						<div class="el_qlmContent">

							<!--索引-->
							<div class="row el_queryBox">
								<form id="queryForm" method="POST" onsubmit="return false;">

									<div class="col-md-3 el_qlmQuery">
										<div class="input-group" role="toolbar">
											<span class="el_spans">部门名称：</span> <input type="text"
												class="form-control clearCon" name="departmentname" id="departname" />

											<input type="hidden" name="currentPage" id="currentPage" />
											<input type="hidden" name="currentCount" id="currentCount" />
											<input type="hidden" name="updepartmentid"
												id="queryUpDepartmentId" class="clearCon" />
										</div>
									</div>
									<div class="col-md-6" id="el_breakTimeIndex">
										<div class="input-group" id="el_startEndTime" role="toolbar">
											<span class="el_spans">违章时间：</span> <input type="text"
												class=" form-control query_dep_starttime clearCon" name="fstarttime"
												id="inpstart2" placeholder="开始时间" readonly> <input
												type="text" class=" form-control query_dep_endtime clearCon"
												id="inpend2" name="fendtime" placeholder="结束时间" readonly>
										</div>
									</div>
									<input type="hidden" name="departType"/>

									<!--清空按钮-->

									<button type="button"
										class="btn btn-default el_queryButton0 btn-sm"
										style="padding-bottom: 5px; border-bottom-width: 1px; margin-bottom: 7px;" onclick="clearCondition()">清空</button>



									<!--提交查询按钮-->
									<button type="button" id="button1"
										class="btn btn-default el_queryButton btn-sm btn-primary"
										onclick="clearPageNum()"
										style="padding-bottom: 5px; border-bottom-width: 1px; margin-bottom: 7px;">查询</button>
								</form>

							</div>
							<!--结束 查询表单提交-->

							<!--显示内容-->
							<h3 class="el_mainHead">内部部门信息</h3>
							<div class="panel panel-default el_Mainmain">

								<!--按钮面板-->
								<div class="panel-body">

									<div class="panel panel-default">
										<div class="panel-body el_MainxiaoMain">

											<div class="el_topButton">
												<!-- 按钮触发模态框1 -->			
												
												  
				                                    <button class="btn btn-primary"
															onclick="el_departmentCount()"><span class="glyphicon glyphicon-search"></span>部门统计</button>
															
													<select class="btn btn-primary" id="el_departType" title="请选择" onchange="queryDepartIn()">
				                                        <option value="0">内部部门</option>
				                                        <option value="1">长期外来单位</option>
				                                        <option value="" selected="selected">全部单位</option>
				                                    </select>	
				                                    &nbsp;				
													<shiro:hasPermission name="department:add">
														<button class="btn btn-primary"
															onclick="el_addDepartment()"><span class="glyphicon glyphicon-plus"></span>添加部门</button>
													</shiro:hasPermission>
													<!-- 删除长委单位功能 -->
													<shiro:hasPermission name="cwdepartment:delete">
														<button class="btn btn-primary"
															onclick="delete_cwdw()">删除长委单位</button>
													</shiro:hasPermission>
																									
											</div>
										
										</div>
									</div>

									<!--表格 内容都提取到json里边 -->
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>部门名称</th>
												<th>部门类型</th>
												<th>上级部门</th>
												<th>负责人</th>
												<th>联系方式</th>
												<th>员工数</th>
												<th>违章积分</th>
												<th>加权积分</th>
												<!-- 有权限就讲全局变量有操作部门的权限置为true -->
												<shiro:hasPermission name="department:operating">
												<script>
												hasOperatingDepart = true;
												</script>
												</shiro:hasPermission>
													<th width="120">操作</th>
												
											</tr>
										</thead>
										<tbody id="tbody">
										</tbody>
									</table>

									<!--分页-->
									<div id="paginationIDU" class="paginationID"></div>
								</div>
							</div>

							<!-- 模态框 部门添加-->
							<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true"
								data-backdrop="static" data-keyboard="false">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel32">添加部门信息</h4>
										</div>
										<form id="addDepartmentForm">
											<div class="modal-body">
												<div class="input-group el_modellist" role="toolbar">
													<div class="input-group" role="toolbar" id="first">
														<span class="el_spans">部门类型：</span> <label
															class="el_radioBox" style="margin-left: 0px;"><input
															type="radio" checked name="department.departmenttype"
															value="0"> 内部部门</label> <label class="el_radioBox"
															style="margin-left: 37px;"><input type="radio"
															name="department.departmenttype" value="1">
															长期外来单位</label>

													</div>
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">部门名称：</span> <input type="text"
														class="form-control el_modelinput"
														name="department.departmentname" id="AddDepartmentName" />
													<span></span>
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">负&nbsp;责&nbsp;人&nbsp;：</span> <input
														type="text" class="form-control"
														name="department.employeename" id="AddEmployeename" />
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<!--<span class="input-group-addon">联系方式</span>-->
													<span class="el_spans">联系方式：</span> <input type="text"
														class="form-control" name="department.phone" id="AddPhone" />
												</div>

												<div id="projectnamediv" class="input-group el_modellist"
													role="toolbar" style="display: none">
													<!--<span class="input-group-addon">联系方式</span>-->
													<span class="el_spans">工程名称：</span> <input type="text"
														class="form-control" name="department.departprojectnames"
														id="AddDepartprojectnames" />
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<!--<span class="input-group-addon">上级单位</span>-->
													<span class="el_spans">上级部门：</span>
													<!-- <input type="text"
														class="form-control el_modelinput el_chooseInput"
														name="department.updepartmentid" id="Addupdepartmentid"
														readonly="readonly " /> -->
													<input type="text"
														class="form-control el_modelinput el_chooseInput"
														id="Addupdepartmentid" disabled /> <input type="hidden"
														id="yincangbumenid" name="department.updepartmentid">
												</div>




												<script>
													$(":radio[name='department.departmenttype']")
															.click(
																	function() {
																		var val = $(
																				this)
																				.val();
																		if (val == 1) {
																			$(
																					"#projectnamediv")
																					.show();

																		} else {

																			$(
																					"#projectnamediv")
																					.hide();
																		}
																	});
												</script>
												<!-- id="addDefaultDepart"  -->

											</div>
											<div class="modal-footer">




												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary"
													onclick="saveDepartmentButton()">保存</button>



											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>

							<!-- 模态框 部门修改-->
							<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true"
								data-backdrop="static" data-keyboard="false">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" onclick="closeModal_symbol()"
												class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel2">修改部门信息</h4>
										</div>
										<form id="updateDepartmentForm">

											<!-- QLQ 隐藏上级部门ID -->
											<input type="hidden" name="department.updepartmentid"
												value="" id="update_updepartmentid" /> <input type="hidden"
												name="department.departmentid" value=""
												id="update_departmentid" />
											<!-- QLQ 隐藏上级部门ID -->

											<div class="modal-body">
												<div class="input-group el_modellist" role="toolbar">
													<div class="input-group" role="toolbar" id="two">
														<span class="el_spans">部门类型：</span> <label
															class="el_radioBox" style="margin-left: 0px;"><input
															type="radio" name="department.departmenttype" value="0">
															内部部门</label> <label class="el_radioBox"
															style="margin-left: 37px;"><input type="radio"
															name="department.departmenttype" value="1">
															长期外来单位</label>

													</div>
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">部门名称：</span> <input type="text"
														class="form-control el_modelinput"
														name="department.departmentname" id="updateDepartmentName" />
													<span></span>
												</div>


												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">负&nbsp;责&nbsp;人&nbsp;：</span> <input
														type="text" class="form-control"
														name="department.employeename" id="updateEmployeename" />
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<!--<span class="input-group-addon">联系方式</span>-->
													<span class="el_spans">联系方式：</span> <input type="text"
														class="form-control" name="department.phone"
														id="updatePhone" />
												</div>


												<div id="projectnamediv2" class="input-group el_modellist"
													role="toolbar">

													<span class="el_spans">工程名称：</span> <input type="text"
														class="form-control" name="department.departprojectnames"
														id="UpdateDepartprojectnames" />
												</div>


												<div class="input-group el_modellist01 el_modellist"
													role="toolbar">

													<!-- 部门树 -->
													<span class="el_spans el_chooseSpan">选择部门：</span> <input
														type="hidden"
														class="form-control el_modelinput el_chooseInput"
														id="updateUpdepartmentid" />
													<ul id="el_chooseUpdateDepart" onclick="treeTextClick()"
														class="el_modelinput el_chooseInput log"
														style="height: 24px; width: 311px;">
													</ul>
													<img src="../../controls/selectDropTree/smallTriangle.png"
														class="el_smallTriangle" width="7" />
													<!-- <ul id="treeDemo11" class="ztree" style="display:none"></ul>	
											 -->
												</div>
												<div>

													<ul id="treeDemo11" class="ztree"
														style="display: none; margin-left: 89px; border: none !important; width: 327px !important;"></ul>

												</div>





											</div>
											<script>
												$(
														":radio[name='department.departmenttype']")
														.click(
																function() {
																	var val = $(
																			this)
																			.val();
																	if (val == 1) {
																		$("#UpdateDepartprojectnames").val('');
																		$(
																				"#projectnamediv2")
																				.show();

																	} else {
																		 $("#UpdateDepartprojectnames").val('');
																		$(
																				"#projectnamediv2")
																				.hide();
																	}
																});
											</script>


											<div class="modal-footer">
												<button type="button" onclick="closeModal_symbol()"
													class="btn btn-default" data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary"
													onclick="saveUpdateButton()">保存</button>
											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>

							<!-- 模态框 查看员工-->
							<div class="modal fade" id="el_EmpCase" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								aria-hidden="true" data-backdrop="static" data-keyboard="false">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabe5l23">查看员工</h4>
										</div>
										<form id="EmpCaseForm">
											<div class="modal-body">
												<input type="hidden" id="empbumen" value="" name="empbumen" />
												<input type="hidden" name="currentPage" id="currentPage3" />
												<input type="hidden" name="currentCount" id="currentCount3" />
												<table class="table table-bordered" id="table2">
													<thead>
														<tr>
															<th>姓名</th>
															<th>性别</th>
															<th>职务</th>
															<th>联系方式</th>
														</tr>
													</thead>
													<tbody id="EmpCase_tbody">
													</tbody>
												</table>
												<!--分页-->
												<div id="paginationIDU2" class="paginationID"
													style="margin-top: 0px;"></div>

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

							<!-- 模态框 查看部门的违章信息-->
							<div class="modal fade" id="el_BreakrulesCase" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								aria-hidden="true" data-backdrop="static" data-keyboard="false">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabe5l23">查看违章信息</h4>
										</div>
										<form id="BreakrulesCaseForm">
											<div class="modal-body">
												<input type="hidden" name="fstarttime" id="q_starttime">
												<input type="hidden" name="fendtime" id="q_endtime">
												<input type="hidden" id="breakrulesbumen" value=""
													name="breakrulesbumen" /> <input type="hidden"
													name="currentPage" id="currentPage2" /> <input
													type="hidden" name="currentCount" id="currentCount2" />
												<table class="table table-bordered" id="table2">
													<thead>
														<tr>
															<th>姓名</th>
															<th>违章内容</th>
															<th>违章时间</th>
															<th>扣分</th>
														</tr>
														<!-- <th>考试情况</th> -->
													</thead>
													<tbody id="BreakrulesCase_tbody">
													</tbody>
												</table>
												<!--分页-->
												<div id="paginationIDU3" class="paginationID"
													style="margin-top: 0px;"></div>

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
							
							
							
							
							 <!-- 模态框（Modal） -->
							<div class="modal fade" id="sccw" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							    <div class="modal-dialog">
							        <div class="modal-content">
							            <div class="modal-header">
							                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							                    &times;
							                </button>
							                <h4 class="modal-title" id="myModalLabel">
							                    	删除长委单位
							                </h4>
							            </div>
							                <div class="modal-body" >
							                	<table class="table table-bordered">
							                		<thead>
							                			<tr>
							                				<th>序号</th>
							                				<th>单位名称</th>
							                				<th>操作</th>
							                			</tr>
							                		</thead>
							                		<tbody id="cwdwTbody">
							                		<!-- 数据 -->
							                		</tbody>
							                	</table>
							                	
            	                              <!--分页-->
                                 			   <div id="paginationQ" class="paginationID"></div>
                                 			   <!-- 隐藏分页信息 -->
                                 			   <input type="hidden" id="currentPageQ" value="1"> 
                                 			   <input type="hidden" id="currentCountQ" value="8">
							                </div>
							                <div class="modal-footer">
							                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
							                    </button>
							                    <button type="submit" class="btn btn-primary" onclick="xg()">
							                        确认
							                    </button>
							                </div>
							        </div><!-- /.modal-content -->
							    </div><!-- /.modal -->
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
