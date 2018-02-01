<!-- 创建试卷 -->
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
<title>创建试卷</title>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>

<%@ include file="/public/cssJs.jsp"%>

<!--分页-->
<%-- <script src="<%=path%>/js/public/page.js"></script> --%>
<link rel="stylesheet"
	href="<%=path%>/css/examParper/createExamParper.css">
<script src="<%=path%>/js/examParper/examPaper/createExamParper.js"></script>
<%@ include file="/public/validate.jsp"%>
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
						<span>培训管理</span><span>>创建试卷</span>
					</div>
					<div class="panel-body">
						<br />

						<!--内容-->
						<div class="col-xs-6 col-md-12">

							<!--显示内容-->
							<div class="panel panel-default">
								<h4>创建试卷</h4>

								<!--选择方式-->
								<span class="el_tipInfo">选择创建方式</span>

								<!--标签栏-->
								<div class="el_navButton">
									<a href="#profile" aria-controls="profile" role="tab"
										data-toggle="tab"> <span
										class="btn btn-primary btn-default el_createButton btn-sm">新建试卷</span>
									</a> <a href="#home" aria-controls="home" role="tab"
										data-toggle="tab"> <span
										class="btn btn-default el_createButton btn-sm">根据历史试卷快速生成试卷</span>
									</a>
								</div>

								<!-- Tab panes -->
								<div class="tab-content">

									<!--根据历史试卷生成试卷-->
									<div role=tabpanel " class="tab-pane col-md-12" id="home">

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
															class="selectpicker form-control" title="请选择"
															name="level" id="level">
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
										<h4 class="el_mainHead">历史试卷信息</h4>
										<div class="panel panel-default el_Mainmain">

											<!--按钮面板-->
											<div class="panel-body">

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
													</tbody>
												</table>
												<!--分页插件  -->
												<div id="paginationID"></div>
												<!--隐藏分页用的信息  -->
												<input type="hidden" name="currentPage" id="currentPage">
												<input type="hidden" name="currentCount" id="currentCount">
											</div>
										</div>

									</div>

									<!--新建试卷-->
									<div role="tabpanel" class="tab-pane active" id="profile">

										<form
											action="${baseurl }/createPaper_forward2QuickGeneratePaper.action"
											id="createPaperForm" method="post">


											<div class="input-group el_modellist" role="toolbar">
												<span class="el_spans">试卷标题：</span> <input type="text"
													class="form-control el_modelinput" name="exampaper.title" />
											</div>

											<div class="input-group el_modellist" role="toolbar">
												<span class="el_spans">试卷级别：</span> <select
													class="form-control el_department3" title="请选择"
													name="exampaper.level">
													<option value="">--请选择--</option>
													<option value="1">一级（厂级）</option>
													<option value="2">二级（部门级）</option>
													<option value="3">三级（班级）</option>
												</select>
											</div>

											<div class="input-group el_modellist" role="toolbar">
												<span class="el_spans">试卷分数：</span> <input type="text"
													class="form-control el_modelinput" value="100"
													name="exampaper.paperscore" />
											</div>

											<div class="input-group el_modellist" role="toolbar">
												<span class="el_spans">试卷描述：</span>
												<textarea class="form-control" cols="1"
													name="exampaper.description"></textarea>
											</div>

											<div class="input-group el_modellist" role="toolbar">
												<span class="el_spans">创&nbsp;建&nbsp; 人：</span> <input
													type="text" class="form-control el_modelinput"
													value="${session.userinfo.username }" disabled="disabled" />
												<input type="hidden" class="form-control el_modelinput"
													name="exampaper.employeename"
													value="${session.userinfo.username }" />
											</div>

											<div class="input-group el_modellist" role="toolbar">
												<span class="el_spans">创建时间：</span> <input type="text"
													id="test4" class="wicon form-control" readonly
													name="exampaper.maketime" />
											</div>

											<!-- 用于隐藏点击按钮的时候确定是哪个按钮 -->
											<input type="hidden" id="newPaperMethod"
												name="newPaperMethod" value="" />

											<div class="modal-footer">
												<span class="el_tipInfo">提示：快速生成试卷，或者去题库批量的添加试题，请选择以下操作</span><br />

												<button type="button" class="btn btn-default  btn-sm"
													id="quickGeneBtn" title="随机快速生成试卷，或通过知识点分类随机生成试卷">快速自动生成试卷</button>
												<button type="button" class="btn btn-info btn-sm"
													id="bankGeneBtn" title="从题库中批量添加试题，或者手动添加试题">根据题库添加试题</button>
											</div>

										</form>
									</div>

								</div>

							</div>
						</div>

						<!--莫态框 历史试卷，使用，弹出填写基本信息-->
						<div class="modal fade" id="el_use" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<!--关闭符号-->
										<!--标题-->
										<h4 class="modal-title" id="myModalLabel">填写试卷基本信息</h4>
									</div>
									<form action="examparperManage.html">

										<div class="el_paperInfo">
											<span class="el_spans">试卷名称：</span> <input type="text"
												class="form-control el_modelinput" name="" />
										</div>

										<div class="el_paperInfo">
											<span class="el_spans">试卷级别：</span> <select
												class="form-control el_department3" title="请选择">
												<option>--请选择--</option>
												<option>一级（厂级）</option>
												<option>二级（部门级）</option>
												<option>三级（班级）</option>
											</select>
										</div>

										<div class="el_paperInfo">
											<span class="el_spans">试卷分数：</span> <input type="text"
												class="form-control el_modelinput" name="" />
										</div>

										<div class="el_paperInfo">
											<span class="el_spans">试卷描述：</span>
											<textarea class="form-control" rows="1"></textarea>
										</div>

										<div class="el_paperInfo">
											<span class="el_spans">创&nbsp;&nbsp;建&nbsp;人：</span> <input
												type="text" class="form-control el_modelinput" name="" />
										</div>

										<div class="el_paperInfo">
											<span class="el_spans">创建时间：</span> <input type="text"
												id="test41" class="wicon form-control" readonly />
										</div>

										<div class="row">
											<div class="col-md-12">
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">关闭</button>
													<button type="submit" class="btn btn-primary">保存</button>
												</div>
											</div>
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

			<br /> <br /> <br />
		</div>
	</div>

	<!--放脚-->
	<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>
