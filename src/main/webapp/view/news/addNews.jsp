<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@ include file="/public/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加新闻</title>

<%@ include file="/public/cssJs.jsp"%>
 
<!--富文本框，编辑器-->
<script charset="utf-8"
	src="<%=path%>/controls/kindEditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="<%=path%>/controls/kindEditor/zh-CN.js"></script>

<!--验证-->
<script src="<%=path%>/controls/validate/jquery.validate.js"></script>
<script src="<%=path%>/controls/validate/messages_zh.js"></script> 

<style>
	.el_modellist{
	width:60%;}
	#el_submitButton{
		margin-left:8%;
	}
</style>
 <script type="text/javascript">
	
	$(function(){		
		var editor; // 文本编辑器的内容
		KindEditor.ready(function(K) {
			editor = K.create('#editor_id', {
				resizeType : 2,
				height:500,
				items : [ 'fontsize', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter',
						'justifyright' ]
			});
		});
		
		$("#el_submitButton").click(function(){
			//验证
			$("#addNewsForm").validate({
				rules : {
					"news.newsTitle" : "required",
					"news.newsType" : "required",
					"news.newsPerson" : "required",
					"news.newsTime" : "required",
					"news.newsContent" : "required",
				},
				messages : {
					"news.newsTitle" : {
						required : "不能为空"
					},
					"news.newsType" : {
						required : "不能为空"
					},
					"news.newsPerson" : {
						required : "不能为空"
					},
					"news.newsTime" : {
						required : "不能为空"
					},
					"news.newsContent" : {
						required : "不能为空"
					}
				}
			})

			//获取富文本框中的内容
			editor.sync();
			var editor_Content = $('#editor_id').val();
			$("#add_newsContent").val(editor_Content);

			$("#addNewsForm").submit();
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
				<div class="panel panel-default">
					<!--菜单连接标题-->
					<div class="panel-heading">
						<span>新闻管理</span><span><a href="<%=path%>/view/news/newsManage.jsp">>新闻信息管理</a></span><span>>添加</span>
					</div>

					<div class="el_main" style="height: auto; padding: 0 0 80px 0">

						<form id="addNewsForm"
							action="${pageContext.request.contextPath }/news_addNews.action"
							method="post">

							<div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</span> <input type="text"
									class="form-control el_modelinput" id="add_newsTitle"
									name="news.newsTitle" />
							</div>

							<div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</span>
								<select class="selectpicker form-control" id="add_newsType"
									name="news.newsType" title="请选择">
									<option value="">--请选择--</option>
									<option value="新闻">新闻</option>
									<option value="通知">通知</option>
								</select>
							</div>

							<div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">发 &nbsp;布 人：</span> <input type="text"
									class="form-control" id="add_newsPerson" name="news.newsPerson" readonly value="${session.userinfo.username }"/>
							</div>

							<div class="input-group el_modellist" role="toolbar">
								<span class="el_spans">发布时间：</span> <input type="text"
									class="wicon form-control el_noVlaue" id="test4"
									name="news.newsTime" readonly />
							</div>

							<div class="input-group el_modellist" role="toolbar">
								<input type="hidden" id="add_newsContent"
									name="news.newsContent" /> 
									<span class="el_spans" style="vertical-align:top">内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容：</span>
								<textarea id="editor_id" style="width: 100%;"
									class="el_editorBox"></textarea>
							</div>

							<button type="button" id="el_submitButton"
								class="btn btn-primary">提交</button>
						</form>

					</div>
				</div>

			</div>
		</div>

		<!--放脚-->
		<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>