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

<!--头-->
<link rel="stylesheet" href="<%=path%>/css/home/header.css" />

<!--时钟 要改成获取服务器时间-->
<script type="text/javascript" src="<%=path%>/js/home/header.js"></script>

</head>
<body>

	<!--头-->
	<div id="el_header">
	   <%-- <div id="el_headerLeftLogo">
			<img src="<%=path%>/image/logo.png" width="36" /> 
			<a href="<%=path%>/view/index/studyMainpage2.jsp"> 阳城国际发电有限责任公司 大唐阳城发电有限责任公司 </a>
		</div>
		<div id="el_headerLeftLogo1">
			<a href="<%=path%>/view/index/studyMainpage2.jsp">安全培训管理系统 </a>
		</div>  --%>
		 
		<div id="el_headerLeftLogo">
			<img src="<%=path%>/image/backgroundlogo.png" width="500px" height="47px"  style="margin-top:-8px"/> 			
		</div>
		
		<div id="el_headerRightinfo">
			<div id="el_systemTime">
				<P id="time" style="font-size: 12px;"></P>
			</div>

			<div id="el_opration">
				<ul>
					<li style="font-size: 13px;"><img
						src="${baseurl}/image/user_2.png" width="15" alt=""> <span>当前用户：</span>
						<span id="el_currentUser"></span></li>
					<li style="font-size: 13px;"><a id="el_indexPage"
						href="javascript:void(0)" onclick="logout1()">退出</a></li>
				</ul>
			</div>
		</div>
	</div>

	<script type="text/javascript">
function logout1(){
	if(confirm("确定退出?")){
		self.location="${baseurl}/logout.action";
			}
		}
	</script>
</body>
</html>