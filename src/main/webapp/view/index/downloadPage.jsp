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

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>主页</title>

<%@ include file="/public/indexCssJs.jsp"%>

<style>
nav a {
	position: relative;
	display: inline-block;
	margin: 15px 25px;
	outline: none;
	color: #fff;
	text-decoration: none;
	letter-spacing: 1px;
	font-weight: 400;
	text-shadow: 0 0 1px rgba(255, 255, 255, 0.3);
	font-size: 14px;
	text-align: center;
}

nav a:hover, nav a:focus {
	outline: none;
}

/* Effect 2: 3D rolling links, idea from http://hakim.se/thoughts/rolling-links */
.cl-effect-2 a {
	line-height: 34px;
	-webkit-perspective: 1000px;
	-moz-perspective: 1000px;
	perspective: 1000px;
}

.cl-effect-2 a span {
	position: relative;
	display: inline-block;
	padding: 0 14px;
	background: #2195de;
	-webkit-transition: -webkit-transform 0.3s;
	-moz-transition: -moz-transform 0.3s;
	transition: transform 0.3s;
	-webkit-transform-origin: 50% 0;
	-moz-transform-origin: 50% 0;
	transform-origin: 50% 0;
	-webkit-transform-style: preserve-3d;
	-moz-transform-style: preserve-3d;
	transform-style: preserve-3d;
	border-radius: 5px;
}

/* .csstransforms3d .cl-effect-2 a span::before {
	position: absolute;
	top: 100%;
	left: 0;
	width: 100%;
	height: 100%;
	background: #0965a0;
	content: attr(data-hover);
	-webkit-transition: background 0.3s;
	-moz-transition: background 0.3s;
	transition: background 0.3s;
	-webkit-transform: rotateX(-90deg);
	-moz-transform: rotateX(-90deg);
	transform: rotateX(-90deg);
	-webkit-transform-origin: 50% 0;
	-moz-transform-origin: 50% 0;
	transform-origin: 50% 0;
	color: white;
	border-radius: 5px;
} */

/* .cl-effect-2 a:hover span, .cl-effect-2 a:focus span {
	-webkit-transform: rotateX(90deg) translateY(-22px);
	-moz-transform: rotateX(90deg) translateY(-22px);
	transform: rotateX(90deg) translateY(-22px);
} */

.csstransforms3d .cl-effect-2 a:hover span::before, .csstransforms3d .cl-effect-2 a:focus span::before
	{
	background: #2195de;
	color: white;
	border-radius: 5px;
}

body {
	background-color: rgb(245, 245, 245)
}

.panel-body {
	width: 80%;
	margin: 0 auto;
	margin-top: 115px;
	padding: 30px 80px;
	height: 430px;
	background-color: white;
}

.el_newsCenter {
	margin-top: 20px;
	height: 300px;
	width: 100%;
	height: 300px;
}

#shuoming {
	color: #999;
	font-size: 13px;
	padding-left: 5px;
}

.el_footer {
	position: fixed !important;
	bottom: 0 !important;
}
</style>

<script src="<%=path%>/js/index/modernizr.custom.js"></script>
<script type="text/javascript">
	function down(flag) {
		if (flag == 1) {
			if (confirm("确定下载谷歌浏览器?")) {
				window.location.href = "export_execute.action?name=ChromeStandalone_56.0.2924.87_Setup.exe"
			}
		}
		if (flag == 2) {
			if (confirm("确定下载火狐浏览器?")) {
				window.location.href = "export_execute.action?name=Firefox-57.0.2.6549-setup.exe"
			}
		}
		if (flag == 3) {
			if (confirm("确定下载操作手册?")) {
				window.location.href = "export_execute.action?name=SystemOperating.pdf"
			}
		}
		if (flag == 4) {
			if (confirm("确定下载格式工厂?")) {
				window.location.href = "export_execute.action?name=FormatFactory.rar"
			}
		}
		if (flag == 5) {
			if (confirm("确定下载判卷机软件?")) {
				window.location.href = "export_execute.action?name=panjuanji.rar"
			}
		}
		if (flag == 6) {
			if (confirm("确定下载pdf转换ppt软件?")) {
				window.location.href = "export_execute.action?name=AnyBizSoftPDFConverter.rar"
			}
		}
	}
</script>
</head>
<body>
	<!-- 头 -->
	<jsp:include page="/view/index/indexHeader0.jsp"></jsp:include>

	<div class="panel-body">

		<span>为了方便您更好的使用本系统，我们为您提供了最新的浏览器和操作手册：</span><br />
		<div class="el_newsCenter">
			<span id="shuoming">点击按钮，下载相关内容</span>
			<nav class="cl-effect-2"> <a href="javascript:void(0)"
				onclick="down(1)"><span data-hover="谷歌（Chrome）">谷歌（Chrome）</span></a>
			<a href="javascript:void(0)" onclick="down(2)"><span
				data-hover="火狐（Firefox）">火狐（Firefox）</span></a>
				 <a	href="javascript:void(0)" onclick="down(3)"><span data-hover="操作手册">操作手册</span></a> 
				 <a	href="javascript:void(0)" onclick="down(4)"><span data-hover="格式化工厂">格式化工厂</span></a> 
				 <a	href="javascript:void(0)" onclick="down(5)"><span data-hover="判卷机软件">判卷机软件</span></a> 
				 <a	href="javascript:void(0)" onclick="down(6)"><span data-hover="视频转换软件">pdf转ppt软件</span></a> 
				
				
			</nav>
		</div>
	</div>

	<!-- 脚 -->
	<jsp:include page="/view/index/indexFooter.jsp"></jsp:include>
</body>
</html>