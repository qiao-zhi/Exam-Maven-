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
<title>Insert title here</title>

<!-- 脚 -->
<link rel="stylesheet" href="<%=path%>/css/home/footer.css" />
<script type="text/javascript" src="${baseurl}/js/public/footer.js"></script>
<script type="text/javascript">
	/* 记录项目名字 /Exam */
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<!-- 缩进菜单的图标 -->
		<a style="position: fixed; left: 6px; bottom: 50px; width: 40px;border:none;color:rgb(51,122,183);"
		title="点此隐藏菜单栏" id="toggleMenu"><span class="glyphicon glyphicon-arrow-left"
		></span></a>

	<footer class="footer" style="font-size:12px !important;">
	<div class="center">Copyright © 2017-2018
		&nbsp;&nbsp;大唐集团&nbsp;阳城国际发电有限责任公司 &nbsp;&nbsp; All Rights Reserved.</div>
	</footer>

</body>
</html>