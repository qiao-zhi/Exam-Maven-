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
<title>添加培训资料管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<%@ include file="/public/cssJs.jsp"%>
<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<!--验证-->
<script
	src="${pageContext.request.contextPath }/controls/validate/jquery.validate.js"></script>
<script
	src="${pageContext.request.contextPath }/controls/validate/messages_zh.js"></script>

<!--选择树-->
<%-- <script src="<%=path %>/js/public/tree.js"></script> --%>
<link rel="stylesheet" href="<%=path%>/css/public/tree.css">

<!-- 引入文件上传的条条 -->
<link rel="stylesheet" href="<%=path%>/css/train/msgbox.css">


<script src="<%=path%>/js/train/addTrainFile.js"></script>



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

			<!-- 隐藏域  用于隐藏从后台传过来的部门树的信息 -->
			<input id="hidDeptTree" type="hidden" />
			<!-- 初始化页面  start -->
			<script type="text/javascript">
				var baseUrlPath = "${pageContext.request.contextPath}";
				$(function() {
					//初始化资料类型
					//initTrainType();
					//初始化知识点
					initKnowledgeType();

					//初始化部门树
					//initDepartment();
				});
			</script>


			<!-- 初始化页面  end -->
			<div class="container-fluid">
				<div class="panel panel-default">
					<!--菜单连接标题-->
					<div class="panel-heading">
						<span>培训管理</span><span><a
							href="<%=path%>/view/train/trainManage.jsp">>培训内容管理</a></span><span>>添加培训资料</span>
					</div>

					<div class="panel-body">
						<br />
						<h4 class="modal-title" id="myModalLabel">&nbsp;&nbsp;添加培训资料</h4>
						<%--文件上传的三个条件：
    	1、提交方式必须为POST提交
    	2、enctype="multipart/form-data"
    	3、表单有file,也就文件的类型为file
    	 --%>
						<form id="trainForm" class="el_Mainmain"
							action="${pageContext.request.contextPath }/train_addTrainContent.action"
							enctype="multipart/form-data">
							<div class="modal-body">

								<div class="input-group el_modellist" role="toolbar">
									<span class="el_spans">资料名称：</span> <input type="text"
										class="form-control el_modelinput"
										name="traincontent.documentname" />
								</div>

								<div class="input-group el_modellist" role="toolbar">
									<span class="el_spans">所属级别：</span> <label class="el_radioBox"><input
										type="radio" value="一级" name="traincontent.level" checked>
										一级</label> <label class="el_radioBox"><input type="radio"
										value="二级" name="traincontent.level"> 二级</label> <label
										class="el_radioBox"><input type="radio" value="三级"
										name="traincontent.level"> 三级</label>
								</div>

								<div class="input-group el_modellist" role="toolbar">
									<span class="el_spans">选择文件：</span>
									<!-- 这个没有以javabean的形式，在action中需要进行处理一下 -->
									<input type="file" accept="audio/mp4,video/mp4"
										class="el_modelinput el_chooseFile" name="attach" />
								</div>

								<div class="input-group el_modellist" role="toolbar">
									<span class="el_spans el_chooseSpan">所属部门：</span>
									<ul id="el_chooseDepart"
										class="el_modelinput el_chooseInput log"></ul>
									<img
										src="${pageContext.request.contextPath }/controls/selectDropTree/smallTriangle.png"
										class="el_smallTriangle" width="7" />
									<ul id="treeDemo10" class="ztree"></ul>
									<!-- 隐藏所属部门选中的值  将所属部门选中的值赋给该隐藏域-->
									<input id="trainDeptName" type="hidden"
										name="traincontent.departmentname">
								</div>

								<!--根据试卷等级类型    来决定有没有部门和班的选择。-->
								<div class="input-group el_modellist" role="toolbar">
									<span class="el_spans">资料类型：</span>
									<!--  <select id="trainType" class="selectpicker form-control" title="请选择" name="traincontent.traintype">
                        <option value="">--请选择--</option>
                    </select> -->
									<input id="trainType" type="text"
										class="form-control el_modelinput"
										name="traincontent.traintype" value="mp4" readonly="readonly" />
								</div>

								<div class="input-group el_modellist" role="toolbar">
									<span class="el_spans">知&nbsp;&nbsp;识&nbsp;点：</span> <select
										id="trainKnowledge" class="selectpicker form-control"
										title="请选择" name="traincontent.knowledgetype">
										<option value="">--请选择--</option>
									</select>
								</div>

								<div class="input-group el_modellist" role="toolbar">
									<span class="el_spans">资料说明：</span>
									<textarea class="form-control el_modelinput" rows="3"
										name="traincontent.description"></textarea>
								</div>

								<div class="input-group el_modellist" role="toolbar">
									<span class="el_spans">上&nbsp;传&nbsp; 人：</span> <input
										type="text" value="${session.userinfo.username }"
										readonly="readonly" class="form-control el_modelinput"
										name="traincontent.employeename" />
								</div>

								<div class="input-group el_modellist" role="toolbar">
									<span class="el_spans">上传时间：</span> <input type="text"
										id="test4" class="wicon form-control" readonly
										name="traincontent.uptime" />
								</div>

							</div>

							<div id="myUploadFile" class="modal-footer"
								style="width: 45%; text-align: right; padding-left: 35px;">
								<!--返回按钮-->

								<button type="button" class="btn btn-primary"
									onclick="btnSave()">保存</button>
								<button type="button" class="btn btn-default"
									onclick="javascript:history.back(-1);">取消</button>



							</div>
							<!-- 文件上传的条条 -->
							<div style="top: 400px; display: block;"
								class="zeng_msgbox_layer_wrap">
								<span id="mode_tips_v2"
									style="z-index: 10000; display: none; font-size: 25px; color: blue;"
									class="zeng_msgbox_layer"><span class="gtl_ico_clear"></span>
									<img alt=""
									src="${pageContext.request.contextPath }/image/loading.gif"
									style="width: 50px; margin-top: 0px; height: 40px;">培训资料正在上传，请稍后...<span
									class="gtl_end"></span> </span>
							</div>
						</form>
						<script type="text/javascript">
							
						</script>


					</div>
				</div>
			</div>
		</div>
		<!--放脚-->
		<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>