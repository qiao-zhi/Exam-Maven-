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
<title>新闻管理</title>

<%@ include file="/public/cssJs.jsp"%>

<!--富文本框，编辑器-->
<script charset="utf-8"
	src="<%=path%>/controls/kindEditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="<%=path%>/controls/kindEditor/zh-CN.js"></script>

<!--验证-->
<script src="<%=path%>/controls/validate/jquery.validate.js"></script>
<script src="<%=path%>/controls/validate/messages_zh.js"></script>
<script>
	var rootFile = "${pageContext.request.contextPath}";
</script>
<!--图片上传-->
<%-- <script src="<%=path%>/controls/upImages/uploadImg.js" type="text/javascript" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/controls/upImages/upImage.css"/>
		<script type="text/javascript"> 
			$(function(){

		    	//============================================轮播图片管理======================================
				imgUpload({
					inputId:'file', //input框id
					imgBox:'imgBox', //图片容器id
					buttonId:'btn', //提交按钮id
					upUrl:'',  //提交地址
					data:'file1' //参数名
				})
				
				//轮播图片标准化
				$.ajax({
					url:"${pageContext.request.contextPath}/newsP_findNewsP.action",
					data:"",
					dataType:"json",
					type:"POST",
					success:function(data){
						//循环获取图片的name src
						for(var i = 0; i < data.newsPs.length; i++) {
							imgSrc.push(data.newsPs[i].newsPCurFileName);	//上传图片的src
						}
						addNewContent(imgBox);//图片展示  在上传图片时调用这个方法，此为输出化，拿来用
					},
					error:function(){
						alert("初始化失败！")
					}
				})
				
			})
			
			//弹出模态框按钮
	    	function el_upLoad(){
	    		$("#el_upLoad").modal();
	    	}
			
			//上传(将文件流数组传到后台)
			function submitPicture() {
				//判断图片数量，若大于4，提示
				if($(".imgContainer").length > 4) {
					alert("轮播图片，不能大于4张！");
				} else if ($(".imgContainer").length < 2){
					alert("轮播图片，不能少于两张！");
				} else {					
					var imgSrces = new Array(); //图片路径数组
					var imgNames = new Array(); //图片名字数组
					//获取图片名字
					$(".imgContainer").each(function(){
						var imgSrc = $(this).children("img").prop("src");
			            var imgName = imgSrc.substring(imgSrc.lastIndexOf("/")+1,imgSrc.length);
						imgNames.push(imgName);
					})
					//获取图片src
					$(".imgContainer").each(function(){
						var imgSrc = $(this).children("img").prop("src");
						imgSrces.push(imgSrc);
					})
					$.ajax({
						url: "${pageContext.request.contextPath}/newsP_delAddBatchNewsP.action",
						type: "post",
						data: {"imgSrces":imgSrces.toString(), "imgNames":imgNames.toString()},
						dataType:"json",
						success: function() {
							alert("成功")
						},
						error:function(){
							alert("失败")
						}
					});
				}

			}
		</script>

<link rel="stylesheet" href="<%=path%>/css/news/newsManage.css">
 --%>

<script src="<%=path%>/js/questionLibrary/dateformat.js"></script>

<script src="<%=path%>/js/news/newsManage.js"></script>

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
						<span>新闻管理</span><span>>新闻信息管理</span>
					</div>

					<div class="el_main">

						<!--内容-->
						<div>

							<!--索引-->
							<div class="row el_queryBox">
								<form action="" id="newQueryForm">

									<input type="hidden" name="currentPage" id="currentPage">
									<input type="hidden" name="currentCount" id="currentCount">
									<div class="row">

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">新闻标题：</span> <input type="text"
													class="form-control" name="newsTitle" />
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">新闻类型：</span> <select
													class="selectpicker form-control" name="newsType"
													title="请选择">
													<option value="">--请选择--</option>
													<option value="新闻">新闻</option>
													<option value="通知">通知</option>
												</select>
											</div>
										</div>
									</div>

									<!--提交查询按钮-->
									<button type="reset"
										class="btn btn-default el_queryButton0 btn-sm">清空</button>
									<button type="button" id="newsQueryButton"
										class="btn btn-primary el_queryButton btn-sm">查询</button>
								</form>

							</div>
							<!--结束 查询表单提交-->

							<!--显示内容-->
							<h4 class="el_mainHead">新闻信息</h4>
							<div class="panel panel-default el_Mainmain">

								<!--按钮面板-->
								<div class="panel-body">

									<div class="panel panel-default">
										<div class="panel-body el_MainxiaoMain">

											<div class="el_topButton">
												<!-- <button class="btn btn-primary" onclick="el_addNews()">
													添加新闻</button> -->
												<a href="<%=path%>/view/news/addNews.jsp">
													<button class="btn btn-primary">
													添加新闻</button>
												</a>
												<!-- <button class="btn btn-primary" onclick="el_upLoad()">
													联播图片管理</button> -->
												<button class="btn btn-primary" onclick="el_duoremoveNews()">
													批量删除</button>
											</div>
										</div>
									</div>

									<!-- 表格 内容都提取到json里边 -->
									<table class="table table-hover table-bordered" id="newsTable">
										<thead>
											<tr>
												<th><input type="checkbox" name="el_chooseDepart"
													id="el_mainCheck" /></th>
												<th>编号</th>
												<th>标题</th>
												<th>类型</th>
												<th>发布人</th>
												<th>发布时间</th>
												<th width="350">操作</th>
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>

									<!--分页-->
									<div id="paginationIDU" class="paginationID"></div>
								</div>
							</div>

							<!-- 模态框 查看新闻-->
							<div class="modal fade" id="el_showNewContent" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel42">查看新闻内容</h4>
										</div>
										<form>
											<div class="modal-body"
												style="min-height: 350px; overflow-y: auto;">
												<!-- <p>新闻内容 签为 input 元素定义标注（标记）。 label
													元素不会向用户呈现任何特殊效果。不过，它为鼠标用户改进了可用性。如 果您在 label
													元素内点击文本，就会触发此控件。就是说，当用户选择该标签时，浏览器就会自动将焦点转到和标签相关的表单控件上。 标签的
													for 属性应当与相关元素的 id 属性相同。</p> -->
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

							
							<!-- 模态框 联播图片上传-->
							<div class="modal fade" id="el_upLoad" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog" style="width: 60%; min-width: 680px;">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel432">查看新闻内容</h4>
										</div>
										<%-- <form action="${pageContext.request.contextPath}/newsP_delAddBatchNewsP.action" method="post" id="upImageForm"> --%>
										<form >
											<div class="modal-body" style="height:450px;overflow-y:auto;">
												<div id="upBox">
													 <div id="inputBox"><input type="file" title="请选择图片" id="file" multiple accept="image/png,image/jpg,image/gif,image/JPEG"/>点击选择图片</div>
												     <div id="imgBox"></div>
												     <button type="button" id="btn">上传</button>
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

							<!-- 模态框   信息删除确认 -->
							<div class="modal fade" id="delcfm">
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
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<a href="javascript:void(0)" class="btn btn-success" onclick="del_buttonClick()" id="del_button"
												data-dismiss="modal">确定</a>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->

							<!-- 模态框   信息批量删除确认 -->
							<div class="modal fade" id="el_duoremoveNews">
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
											<p>您确认要删除所选信息吗？</p>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<a id="del_duobutton" onclick="del_duobuttonClick()" class="btn btn-success"
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
