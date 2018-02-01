<!-- 试卷管理 -->
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
<title>试卷管理</title>
<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<%@ include file="/public/cssJs.jsp"%>

<!--分页-->
<%-- <script src="<%=path %>/js/public/page.js"></script> --%>
<script src="<%=path%>/js/examParper/examPaper/examparperManage.js"></script>
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
						<span class="el_stairTitle">培训管理</span><span>>试卷管理</span>
					</div>
					<div class="panel-body">

						<!--内容-->
						<div class=" col-md-12">

							<!--索引-->
							<div class="row el_queryBox">
								<form action="">

									<div class="col-md-3 el_qlmQuery">
										<div class="input-group" role="toolbar">
											<span class="el_spans">试卷名称：</span> <input type="text"
												class="form-control" name="title" id="title" />
										</div>
									</div>

									<div class="col-md-3 el_qlmQuery">
										<div class="input-group" role="toolbar">
											<span class="el_spans">试卷级别：</span> <select
												class="selectpicker form-control" title="请选择" name="level"
												id="level">
												<option value="">--请选择--</option>
												<option value="1">一级(厂级)</option>
												<option value="2">二级(部门级)</option>
												<option value="3">三级(班组级)</option>
											</select>
										</div>
									</div>
									
									<div class="col-md-3 el_qlmQuery">
										<div class="input-group" role="toolbar">
											<span class="el_spans">试卷状态：</span> <select
												class="selectpicker form-control" title="请选择" name="paperStatus"
												id="paperStatus">
												<option value="">--请选择--</option>
												<option value="0" selected="selected">未归档</option>
												<option value="1">已归档</option>
											</select>
										</div>
									</div>
									<!--清空按钮-->
									<button type="reset"
										class="btn btn-default el_queryButton0 btn-sm">清空</button>
									<!--提交查询按钮-->
									<button type="button" id="querBtn"
										class="btn btn-primary el_queryButton btn-sm">查询</button>
								</form>

							</div>
							<!--结束 查询表单提交-->

							<!--显示内容-->
							<h4 class="el_mainHead">试卷信息</h4>
							<div class="panel panel-default el_Mainmain">

								<!--按钮面板-->
								<div class="panel-body">

									<div class="panel panel-default">
										<div class="panel-body el_MainxiaoMain">

											<div class="el_topButton">
												<!-- 按钮触发模态框1-->
												<a
													href="<%=path%>/view/examParper/examPaper/createExamParper.jsp">
													<button class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>创建试卷</button>
												</a>
												<%-- <a href="<%=path%>/view/examParper/releaseExam.jsp">
													<button type="button" class="btn btn-primary">发布考试</button>
												</a> --%>
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
												<th>级别</th>
												<th>分数</th>
												<th>使用次数</th>
												<th width="15%">描述</th>
												<th>创建人</th>
												<th>创建时间</th>
												<th width="15%">操作</th>
											</tr>
										</thead>
										<tbody id="paperTableBody">
											<!-- 分页试卷信息 -->

											<%-- 
											<tr>
												<td><input type="radio" name="el_chooseParper"
													class="el_checks" /></td>
												<td>内啊手动阀手动阀容1</td>
												<td>一级</td>
												<td>100</td>
												<td>0</td>
												<td>链接阿斯蒂芬，士大夫就， 士大夫看见恐龙，上岛咖啡，速度飞快，是分开， 斯达克警方为，</td>
												<td>小明</td>
												<td>2017-12-20</td>
												<td><a
													href="<%=path %>/view/examParper/addExamparper.jsp">修改</a>
													<a class="el_delButton" onClick="delcfm()">删除</a> <a
													href="<%=path %>/view/examParper/parperModel.jsp">试卷预览</a>
												</td>
											</tr>
											--%>
										</tbody>
									</table>

									<!--分页-->
									<div id="paginationID" class="paginationID"></div>
									<!--隐藏分页用的信息  -->
									<input type="hidden" name="currentPage" id="currentPage">
									<input type="hidden" name="currentCount" id="currentCount">

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
											<a onclick="urlSubmit()" class="btn btn-success"
												data-dismiss="modal">确定</a>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->

							<!-- 模态框   修改试卷信息 -->
							<div class="modal fade" id="modifyInfo">
								<div class="modal-dialog">
									<div class="modal-content message_align">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<h4 class="modal-title">修改试卷基本信息</h4>
										</div>
										<form action="addExamparper.html">
											<div class="modal-body">

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">试卷标题：</span> <input type="text"
														class="form-control el_modelinput" name="" />
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">试卷级别：</span> <select
														class="form-control el_department3" title="请选择">
														<option>--请选择--</option>
														<option>一级（厂级）</option>
														<option>二级（部门级）</option>
														<option>三级（班级）</option>
													</select>
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">试卷分数：</span> <input type="text"
														class="form-control el_modelinput" name="" />
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">试卷描述：</span>
													<textarea class="form-control" rows="1"></textarea>
												</div>


												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">创 建 人：</span> <input type="text"
														class="form-control el_modelinput" name="" />
												</div>


												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">创建时间：</span> <input type="text"
														id="test4" class="wicon form-control el_modelinput"
														readonly />
												</div>
											</div>

											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">取消</button>
												<button class="btn btn-success" data-dismiss="modal">保存</button>
											</div>

										</form>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->

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
