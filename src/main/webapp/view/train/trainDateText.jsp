<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--导入jsp的核心标签库 --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/public/tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>培训资料视频查看  查看详情</title>
<%@ include file="/public/cssJs.jsp"%>

<link rel="stylesheet" href="<%=path%>/css/train/trainDateText.css">
<script src="<%=path%>/js/train/trainDateText.js"></script>
</head>
<body>
	<!--头-->
	<jsp:include page="/view/public/header.jsp"></jsp:include>
	
<%-- 	<c:forEach items="${traincontent }" var="traincontent">
		<!-- 资料名称 -->
		${traincontent.documentname }
		<!-- 上传时间 -->
		${traincontent.uptime }
		<!-- 上传人 -->
		${traincontent.employeename }
		<!-- 浏览量 -->
		${traincontent.browsetimes }
		<!-- 资料所属级别 -->
		${traincontent.level }
		<!-- 所属部门 -->
		${traincontent.departmentname }
		<!-- 资料简介 -->
		${traincontent.description }
	</c:forEach> --%>
	
	<div class="container">
		<div class="panel panel-default">
		<br/>
			<h4>基本信息</h4>

			<!--资料信息-->
			<div class="el_parperInfo">
				<table>
					<tr>
						<td>资料名称</td>
						<td>${traincontent.documentname}</td>
					</tr>
					<tr>
						<td>上传时间</td>
						<td>${dateStr}</td>
					</tr>
					<tr>
						<td>上传人</td>
						<td>${traincontent.employeename}</td>
					</tr>
					<tr>
						<td>浏览量</td>
						<td>${traincontent.browsetimes}</td>
					</tr>
					<tr>
						<td>资料所属级别</td>
						<td>${traincontent.level}</td>
					</tr>
					<tr>
						<td>所属部门</td>
						<td>${traincontent.departmentname}</td>
					</tr>
					<tr>
						<td>资料简介</td>
						<td>${traincontent.description}</td>
					</tr>
					<div class="el_loadOpration">
						<span>文件类型：</span><span>${traincontent.traintype }</span>
						<!-- <button class="btn btn-primary">下载</button> -->
						<a href="${pageContext.request.contextPath}/down_down.action?name=${traincontent.currentname}">下载</a> 
					</div>
				</table>
			</div>
			<!--视频-->

		</div>
	</div>

	<!--放脚-->
	<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>