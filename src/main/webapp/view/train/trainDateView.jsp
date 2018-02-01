<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";

//视频文件的名字(下面一行代码只是测试的时候用的，到最后是要删掉的)
String fileName = "88a1f29dff9144148e026c465167e8fc.mp4";

%>
<%@ include file="/public/tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>培训资料视频查看</title>
    <%@ include file="/public/cssJs.jsp"%>
    
    <link href="<%=path%>/js/jquery.min.js"/>
    <link rel="stylesheet" href="<%=path %>/bs/css/bootstrap.css"/><!-- s -->

    <script type="text/javascript" src="<%=path %>/bs/js/bootstrap.js"></script><!-- s -->

    <link rel="stylesheet" href="<%=path %>/css/train/trainDateView.css">
    
  <!-- 取消该video标签的下载按钮 -->  
 <style type="text/css">
	 video::-internal-media-controls-download-button {
	    display:none;
	}
	
	
	video::-webkit-media-controls-enclosure {
	    overflow:hidden;
	}
	
	
	video::-webkit-media-controls-panel {
	    width: calc(100% + 30px); 
	} 
 </style>
</head>
<body>
<!--头-->
<jsp:include page="/view/public/header.jsp"></jsp:include>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-body">
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
                </table>
                <button class="btn btn-info returnButton" onclick="javascript:window.history.back()">返回</button>
            </div>
			
			<br/><br/>
			<!-- 在线播放视频 -->
			
			<video id="showVideo" class="el_video" style="width:100%;height:100%;"
								src="/file/${traincontent.currentname }" controls="controls">
				your browser does not support the video tag
			</video>	
			
			<!-- 取消鼠标的右键事件，当鼠标移动到视频区域时，禁用鼠标的右键 -->
			<script type="text/javascript">
				$('#showVideo').bind('contextmenu',function() { return false; });
			</script>
			
        </div>
    </div>
</div>
<br/>
<br/>
<br/>
<br/>
<br/>

<!--放脚-->
<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>