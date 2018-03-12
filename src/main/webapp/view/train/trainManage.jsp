<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/public/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>培训资料管理</title>
<%@ include file="/public/cssJs.jsp"%>

<!-- 日期格式转换 -->
<script
	src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js ">
	
</script>

<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
	var hasTrainContentManager = false;
</script>
<!--索引中选择部门-->
<%-- <script type="text/javascript" src="<%=path %>/js/public/tree.js"></script> --%>
<%-- <script src="<%=path %>/js/public/page.js"></script>  --%>

<link rel="stylesheet" href="<%=path%>/css/public/tree.css">
<script src="<%=path%>/js/train/trainManage.js"></script>

</head>
<body>
	<!-- 判断是否有培训内容管理权限 -->
	<shiro:hasPermission name="traincontent:manager">
		<script>
			hasTrainContentManager = true;
		</script>
	</shiro:hasPermission>

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

			<!-- **********页面初始化 初始化 知识点、部门、资料级别下拉框 start ***********-->
			<script type="text/javascript">
				$(function() {
					//queryFy(10,1);

					//初始化知识点
					initKnowledgeType();
					//初始化资料级别
					//initTrainLevel();
					//让页面加载的时候就默认把所有数据查询出来(分页查询) 
					btnFindFy()
					//initJspFindFy();
				});
			</script>
			<!--******* 页面初始化 初始化 知识点、部门、资料级别下拉框 end **********-->

			<!-- 让页面加载的时候就默认查询一些数据出来 (分页查询) start -->
			<script type="text/javascript">
				
			</script>


			<!-- 让页面加载的时候就默认查询一些数据出来(分页查询)   end -->

			<div class="container-fluid">
				<div class="panel panel-default">
					<!--菜单连接标题-->
					<div class="panel-heading">
						<span>培训管理</span><span>>培训内容管理</span>
					</div>
					<div class="panel-body el_main">

						<!--树-->
						<div class="el_leftTree">
							<!--标题类，添加了一个颜色-->
							<span class="el_treeTitle">培训类别</span>
							<ul id="tree" class="ztree"></ul>
						</div>
						<!-- 隐藏一些东西 -->
						<!-- 培训类别编号 -->
						<input type="hidden" id="trainContentTypeId" class="qlqClear">
						<!-- 培训类别编名称-->
						<input type="hidden" id="trainContentTypeName">


						<!--内容-->
						<div class="el_qlmContent">
							<!--内容1-->
							<div class=" col-md-12">

								<!--索引-->
								<div class="row el_queryBox">
									<form id="findForm">

										<div class="row el_queryBoxrow">

											<div class="col-md-3 el_qlmQuery">
												<div class="input-group" role="toolbar">
													<span class="el_spans">资料名称：</span> <input type="text"
														class="form-control qlqClear" name="documentName" />
												</div>
											</div>

											

 											<div class="col-md-3 el_qlmQuery">
												<div class="input-group" role="toolbar">
													<span class="el_spans el_chooseSpan">上传部门：</span>
													<input type="text"
														class="form-control qlqClear" name="departmentName" />
												</div>
											</div>

<!-- 											<div class="col-md-3 el_qlmQuery">
												<div class="input-group" role="toolbar">
													<span class="el_spans">资料级别：</span> <select id="trainLevel"
														class="combox form-control" name="traincontent.level">
														<option value="-请选择-">-请选择-</option>
														<option value="一级">一级</option>
														<option value="二级">二级</option>
														<option value="三级">三级</option>
													</select>
												</div>
											</div> -->
										</div>

								<!-- 		<div class="row el_queryBoxrow">
											<div class="col-md-3 el_qlmQuery">
												<div class="input-group" role="toolbar">
													<span class="el_spans">知&nbsp;识&nbsp;&nbsp;点：</span> <select
														id="trainKnowledge" class="combox form-control"
														name="traincontent.knowledgetype">
														<option value="-请选择-">-请选择-</option>

													</select>
												</div>
											</div>
										</div> -->
										<!-- 隐藏域 隐藏当前页页号 -->
										<input id="yeHao" type="hidden" name="currentPage" value="1" />
										<!-- 隐藏域  隐藏每页显示的记录数 -->
										<input id="jiLuShu" type="hidden" name="currentTotal"
											value="8" />
										<!-- 隐藏培训类别 -->
										<input type="hidden" name="typeId" class="qlqClear"/>	
										<!-- 清空按钮 -->
										<button type="button" onclick="clearBtn()"
											class="btn btn-default el_queryButton0 btn-sm">清空</button>
										<!--提交查询按钮-->
										<button type="button" onclick="btnFindFy()"
											class="btn btn-primary el_queryButton btn-sm">查询</button>
									</form>
								</div>
								<!-- 清空按钮的事件 -->
								<script type="text/javascript">
									
								</script>
								<!-- 查询按钮的事件 -->
								<script type="text/javascript">
									
								</script>
								<!--结束 查询表单提交-->

								<!--显示内容-->
								<h4 class="el_mainHead">培训资料信息</h4>
								<div class="panel panel-default el_Mainmain">

									<!--按钮面板-->
									<div class="panel-body">

										<div class="panel panel-default">
											<div class="panel-body el_MainxiaoMain">
												<div class="el_topButton">
													<!-- 增加培训资料管理权限 -->
													<shiro:hasPermission name="traincontent:manager">
														<a href=javascript:void(0) onclick="goToAdd()">
															<button class="btn btn-primary">
																<span class="glyphicon glyphicon-plus"></span>增加培训资料
															</button>
														</a>

														<button type="button" class="btn btn-primary"
															onclick="piliangdelcfm()">批量删除</button>
													</shiro:hasPermission>
												</div>

											</div>
										</div>

										<!--表格 内容都提取到json里边-->
										<table class="table  table-hover table-bordered">
											<thead>
												<tr>
													<th><input type="checkbox" id="el_checkQuanxuan" /></th>
													<th>资料名称</th>
													<th>所属类别</th>
													<th>资料类型</th>
													<th>文件大小</th>
													<th>浏览量</th>
													<th>上传部门</th>
													<th>上传时间</th>
													<th>上传人</th>
													<th width="170">操作</th>
												</tr>
											</thead>
											<tbody id="tBody">

											</tbody>
										</table>


										<!-- 隐藏域 隐藏dicId -->
										<input id="dicId" type="hidden" value="" />
										<!-- ********* 删除 ********** -->
										<script type="text/javascript">
											//var docId = "";
										</script>
										<!-- 信息 单条删除确认的   确定按钮的事件 -->
										<script type="text/javascript">
											
										</script>

										<!--********分页  start   分页的代码抽取到trainManage.js文件中了**************-->
										<div id="paginationIDU" class="paginationID"></div>
										<!--********分页  end**************-->
									</div>
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
												<p>您确认要删除该培训资料吗？</p>
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

								<!-- 模态框   批量信息删除确认 -->
								<div class="modal fade" id="delcfmModel2" data-backdrop="static"
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
												<p>您确认要删除选中的资料吗？</p>
											</div>
											<div class="modal-footer">
												<input type="hidden" id="url2" />
												<button type="button" class="btn btn-default"
													data-dismiss="modal">取消</button>
												<a onclick="urlSubmit2()" class="btn btn-success"
													data-dismiss="modal">确定</a>
											</div>

											<!-- 隐藏域 隐藏ids -->
											<input id="idsDel" type="hidden" value="" />

											<!-- 确定按钮的点击事件  进行批量删除 -->
											<script type="text/javascript">
												
											</script>
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
	</div>
	<!--放脚-->
	<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>