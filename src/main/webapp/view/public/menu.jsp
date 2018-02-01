<%@page import="cn.xm.exam.bean.system.Permission"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@page import="java.util.List"%>
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
<!--菜单-->
<link rel="stylesheet" href="<%=path%>/css/home/menu.css" />
<style>
a:hover {
	color: black;
}
</style>



</head>
<body>
	<div class="htmleaf-container">
		<ul class=mtree style="opacity: 1; margin-top: 0px;">
			<c:forEach var="per" items="${permissioninfo}">
				<c:if test="${per.type=='menutop' }">
					<li><a href="javascript:void(0)">${per.name}</a>
						<ul>
							<c:forEach var="per1" items="${permissioninfo}">

								<c:if
									test="${per1.parentid==per.permissionid and per1.type=='menu'}">
									<li><a href="${baseurl }/view/${per1.url}">
											${per1.name} </a> <c:set var="a" value="False"></c:set> <c:forEach
											var="per3" items="${permissioninfo}">
											<c:if
												test="${per3.type=='menu1' and per3.parentid==per1.permissionid }">
												<c:set var="a" value="True"></c:set>
											</c:if>
										</c:forEach> <c:if test="${a=='True'}">
											<ul>
												<c:forEach var="per2" items="${permissioninfo}">
													<c:if
														test="${per2.parentid==per1.permissionid and per2.type=='menu1'}">
														<li><a href="${baseurl }/view/${per2.url}">
																${per2.name} </a></li>
													</c:if>
												</c:forEach>
											</ul>
										</c:if></li>
								</c:if>
							</c:forEach>
						</ul></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
	<!-- 引到开头不起作用因此放在这里 -->
	<script type="text/javascript" src="${baseurl}/js/public/menu.js"></script>
</body>
</html>