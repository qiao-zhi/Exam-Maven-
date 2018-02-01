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
<title>晚来员工管理</title>

<%@ include file="/public/cssJs.jsp"%>

<!--分页-->
<script src="<%=path%>/js/public/page.js"></script>
<!--左边的树-->
<script src="<%=path%>/js/public/tree.js"></script>

<link rel="stylesheet" href="<%=path%>/css/outDepart/outdepartTree.css">

<script src="<%=path%>/js/outDepart/outdepartEmpManage.js"></script>
<link rel="stylesheet"
	href="<%=path%>/css/outDepart/outdepartEmpManage.css">
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
						<span>大修管理</span><span>><a href="<%=path%>/view/overhaul/overhaulManage.jsp">大修信息管理</a></span>
						<span>><a href="<%=path%>/view/overhual/overhaulInfo.jsp">大修详情</a></span><span>>大修员工</span>
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
							<div class="row el_queryBox">
								<form action="">

									<div class="row el_queryBoxrow">
										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
												<input type="text" class="form-control" name="" />
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">性&nbsp;&nbsp;&nbsp;别：</span> <label
													class="el_radioBox"><input type="radio"
													name="el_gender"> 男</label> <label class="el_radioBox"><input
													type="radio" name="el_gender"> 女</label>
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">身&nbsp;份&nbsp;&nbsp;证：</span> <input
													type="text" class="form-control" name="" />
											</div>
										</div>
									</div>
									<div class="row el_queryBoxrow">
										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">违章积分：</span> <select
													class="selectpicker form-control" title="请选择">
													<option>--请选择--</option>
													<option>3分以下</option>
													<option>4-6分</option>
													<option>7-9分</option>
													<option>9-11分</option>
													<option>12分及以上</option>
												</select>
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">黑名单：</span> <label
													class="el_radioBox"><input type="radio"
													name="el_blackList"> 是</label> <label class="el_radioBox"><input
													type="radio" name="el_blackList"> 否</label>
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">考试情况：</span> <select
													class="selectpicker form-control" title="请选择">
													<option>--请选择--</option>
													<option>通过一级考试</option>
													<option>通过二级考试</option>
													<option>通过三级考试</option>
													<option>未通过</option>
												</select>
											</div>
										</div>
									</div>
									<!--提交查询按钮-->
									<button type="submit"
										class="btn btn-default el_queryButton btn-sm">查询</button>
								</form>

							</div>
							<!--结束 查询表单提交-->

							<!--显示内容-->
							<h3 class="el_mainHead">外来单位员工信息</h3>
							<div class="panel panel-default el_Mainmain">

								<!--按钮面板-->
								<div class="panel-body">

									<!--表格内容都提取到json里边-->
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>选择</th>
												<th>姓名</th>
												<th>性别</th>
												<th>身份证</th>
												<th>所属单位</th>
												<th>违章积分</th>
												<th>黑名单状态</th>
												<th>考试情况</th>
												<th width="200">操作</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><input type="radio" name="el_chooseEmp"
													class="el_checks" /></td>
												<td>王菲</td>
												<td>nan</td>
												<td>138794687864524</td>
												<td>试卷撒旦解来发</td>
												<td onclick="el_breakRulesCase" class="success">20</td>
												<td onclick="el_breakRulesCase" class="success">是</td>
												<td>通过一级考试</td>
												<td><a href="javascript:void(0)" onclick="allInfo()">详细信息</a>&nbsp;
													<a href="javascript:void(0)" onclick="el_modifyEmp()">修改</a>&nbsp;
													<a class="el_delButton" onClick="delcfm()">删除</a><br /></td>
											</tr>
										</tbody>
									</table>

									<!--分页-->
									<div id="paginationIDU"></div>

								</div>
							</div>

							
							<!-- 模态框  修改员工-->
							<div class="modal fade" id="el_modifyEmp" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel23"
								aria-hidden="true">
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
												<div class="big-photo"></div>

												<div class="input-group el_modellist01" role="toolbar">
													<span class="el_spans0">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;种：</span>
													<select class="selectpicker el_modelinput form-control"
														title="请选择">
														<option>--请选择--</option>
														<option>工种1</option>
														<option>工种1</option>
														<option>工种1</option>
														<option>工种1</option>
													</select>
												</div>

												<div class="input-group el_modellist01" role="toolbar">
													<span class="el_spans0">员工姓名：</span> <input type="text"
														class="form-control el_modelinput" disabled name="" />
												</div>

												<div class="input-group el_modellist01">
													<span class="el_spans0"> 员工性别：</span>
													<div>
														<label><input type="radio" disabled
															name="el_gender" checked value="0"> 男</label> <label><input
															type="radio" disabled name="el_gender" value="1">
															女</label>
													</div>
												</div>

												<div class="input-group el_modellist01" role="toolbar">
													<span class="el_spans0">出生日期：</span> <input type="text"
														class="form-control el_modelinput" disabled name="" />
												</div>

												<div class="input-group el_modellist0" role="toolbar">
													<span class="el_spans0">身&nbsp;&nbsp;份&nbsp;证：</span> <input
														type="text" class="form-control el_modelinput" disabled
														name="" />
												</div>

												<div class="input-group el_modellist0" role="toolbar">
													<span class="el_spans0">选择单位：</span> <input type="text"
														class="form-control el_modelinput" disabled name="" />
												</div>

											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary">保存</button>
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
								aria-hidden="true">
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
												<div class="el_threeScoreList">
													<table>
														<caption>员工信息：</caption>
														<tr>
															<td>名称</td>
															<td>asdf</td>
															<td>性别</td>
															<td>sadf</td>
														</tr>
														<tr>
															<td>联系方式</td>
															<td>asdf</td>
															<td>违章记分</td>
															<td>asdf</td>
														</tr>
														<tr>
															<td>所属单位</td>
															<td>asdf</td>
															<td>黑名单</td>
															<td>否</td>
														</tr>
													</table>
												</div>
												<table class="table table-bordered">
													<thead>
														<th>违章时间</th>
														<th>违章地点</th>
														<th>违章积分</th>
														<th>违章内容</th>
													</thead>
													<tbody>
														<tr>
															<td>2011-12-45</td>
															<td>撒打发士大夫士大</td>
															<td>12</td>
															<td>啊撒旦飞洒地方撒地方沙发上阿飞士大夫撒旦发</td>
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

							<!-- 模态框 员工详细信息-->
							<div class="modal fade" id="allInfo" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
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
													<img src="../../image/a1.jpg" width="90">
												</div>
												<div class="el_parperInfo">
													<table>
														<tr>
															<td>员工姓名</td>
															<td>咪咪</td>
														</tr>
														<tr>
															<td>性别</td>
															<td>男</td>
														</tr>
														<tr>
															<td>出生日期</td>
															<td>2017年 11月 10日</td>
														</tr>
														<tr>
															<td>身份证</td>
															<td>145864786541235445</td>
														</tr>
														<tr>
															<td>黑名单状态</td>
															<td>无</td>
														</tr>
														<tr>
															<td>安全培训情况</td>
															<td>通过三级考试</td>
														</tr>
														<tr>
															<td>所属单位</td>
															<td>****部门</td>
														</tr>
													</table>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary">提交更改</button>
											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
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
