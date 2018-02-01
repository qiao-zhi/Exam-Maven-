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
<title>部门信息统计</title>

<%@ include file="/public/cssJs.jsp"%>

<!--分页-->
<%-- <script src="<%=path %>/js/public/page.js"></script> --%>

<!--左边的树-->
<!-- <script src="../../js/public/tree.js"></script> -->

<script src="../../js/innerDepart/innerDepartCount.js"></script>
<script>
	var basePathUrl = '${pageContext.request.contextPath}'
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
						<span>内部员工管理</span><span>>部门统计</span>						
						<ul class="nav navbar-nav navbar-right">
							<li>
								<!-- <a href="javascript:history.back(-1);"></a> -->
								<button type="button" onclick="javascript:history.back(-1);" class="btn btn-block btn-default btn-sm">返回</button>
							</li>
						</ul>
					</div>
					
					<div class="panel-body el_main">						
						<!--内容-->
						<div class="el_qlmContent" style="width: 100%;">

						

							<!--显示内容-->
							<h3 class="el_mainHead">内部部门信息</h3>
							
							<div class="panel panel-default el_Mainmain" style="padding-bottom: 20px;">

							
								<div class="panel-body">
																		
									&nbsp;&nbsp;&nbsp;<span>部门数：</span><span id="formalDepCountInfo"></span>
									&nbsp;&nbsp;&nbsp;
									<span>员工数：</span><span id="formalEmpCountInfo"></span>
									<!--表格 内容都提取到json里边 -->
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>部门名称</th>												
												<th>负责人</th>
												<th>联系方式</th>
												<th>员工数</th>
												<th>违章积分</th>
												<th>加权积分</th>												
											</tr>
										</thead>
										<tbody id="departmentInFormalInfolist">
										</tbody>
									</table>

									<!--分页-->
									<div id="paginationIDU" class="paginationID"></div>
								</div>
				
							</div>
							
							<!--显示内容-->
							<h3 class="el_mainHead">长委单位信息</h3>
							<div class="panel panel-default el_Mainmain" >

								<div class="panel-body">
									
									&nbsp;&nbsp;&nbsp;<span>单位数：</span><span id="toDoDepCountInfo"></span>
									&nbsp;&nbsp;&nbsp;
									<span>员工数：</span><span id="toDoEmpCountInfo"></span>					
									<!--表格 内容都提取到json里边 -->
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>部门名称</th>												
												<th>负责人</th>
												<th>联系方式</th>
												<th>员工数</th>
												<th>违章积分</th>
												<th>加权积分</th>												
											</tr>
										</thead>
										<tbody id="departmentInToDoInfolist">
										</tbody>
									</table>

									<!--分页-->
									<div id="paginationIDU2" class="paginationID"></div>
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
