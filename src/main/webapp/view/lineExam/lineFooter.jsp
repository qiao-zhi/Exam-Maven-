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
<title>添加考试</title>

<style>
footer {
	width: 100%;
	background-color: rgb(51, 122, 183);
	padding-top: 3px;
	padding-bottom: 3px;
	margin: 0 auto;
	text-align: center;
	font-size: 10px;
	clear: both;
	color: #ededed;
	display: block;
	position:fixed;
	bottom:0;
}
</style>
</head>
<body>
	<!--放脚-->
	<footer class="footer">
	<div class="center">Copyright © 2017-2018  &nbsp;&nbsp;大唐集团&nbsp;阳城国际发电有限公司 &nbsp;&nbsp; All Rights
		Reserved. </div>
	</footer>
</body>
</html>
