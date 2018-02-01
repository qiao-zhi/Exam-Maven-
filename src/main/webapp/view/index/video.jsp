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
<title>主页</title>

<%@ include file="/public/indexCssJs.jsp"%><%-- 
    <link href="<%=path %>/controls/Video/css/jplayer.blue.monday.min.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="<%=path %>/controls/Video2/js/jquery.jplayer.min.js"></script>
    <script type="text/javascript" src="<%=path %>/controls/Video2/js/jplayer.playlist.min.js"></script> --%>


<link rel="stylesheet" href="<%=path %>/css/index/video.css">
<%-- <script src="<%=path %>/js/index/video.js"></script> --%>

<!-- 取消该video标签的下载按钮 -->
<style type="text/css">
video::-internal-media-controls-download-button {
	display: none;
}

video::-webkit-media-controls-enclosure {
	overflow: hidden;
}

video::-webkit-media-controls-panel {
	width: calc(100% + 30px);
}
</style>
</head>
<body>

	<!-- 头 -->
	<jsp:include page="/view/index/indexHeader0.jsp"></jsp:include>

	<div class="panel-body">

		<div id="videoLeft">

			<div class="panel-heading">
				<h4>培训视频</h4>
			</div>
			<!--视频-->

			<!-- 在线播放视频 -->


			<div id="videoRight">
				<!--资料信息-->
				<table height="100%" width="100%">
					<tbody align="center">
						<tr>
							<th>资料名称</th>
							<td>${traincontent.documentname}</td>
						</tr>
						<%-- <tr>
            		 	<th>上传时间</th>
            			<td>${dateStr}</td>
            		</tr>
            		<tr>
            		 	<th>上传人</th>
           				<td>${traincontent.employeename}</td>
            		</tr> --%>
						<tr>
							<th>浏览量</th>
							<td>${traincontent.browsetimes}</td>
						</tr>
						<tr>
							<th>资料所属级别</th>
							<td>${traincontent.level}</td>
						</tr>
						<tr>
							<th>所属部门</th>
							<td>${traincontent.departmentname}</td>
						</tr>
						<tr>
							<th>资料简介</th>
							<td colspan='3'>${traincontent.description}</td>
						</tr>

					</tbody>
				</table>

			</div>

			<video id="showVideo" class="el_video"
				style="display:block;height:100%;width:100%; margin:0 auto;"
				src="/file/${traincontent.currentname }" controls="controls">
			your browser does not support the video tag </video>

			<!-- 取消鼠标的右键事件，当鼠标移动到视频区域时，禁用鼠标的右键 -->
			<script type="text/javascript">
				$('#showVideo').bind('contextmenu',function() { return false; });

			</script>
		</div>

	</div>
	<!-- 脚 -->
	<jsp:include page="/view/index/indexFooter.jsp"></jsp:include>
</body>
</html>