<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%-- <%@ include file="/public/tag.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<%-- <%@ include file="/public/indexCssJs.jsp"%> --%>

<!--底部footer-->
<style>
.el_footer {
	text-align: center;
	width: 100%;
	font-size: 12px;
	background-color: #333;
	color: #ccc;
	height:40px;
	line-height:42px;
}

.el_footerContent {
	font-size: 12px;
	background-color: #333;
}
</style>

</head>
<body>

	<!--底部-->
	<div class="el_footer">
	
	<div class="el_footerContent">Copyright © 2017-2018  &nbsp;&nbsp;大唐集团&nbsp;阳城国际发电有限公司 &nbsp;&nbsp; All Rights
		Reserved. </div>
	</div>

</body>
</html>