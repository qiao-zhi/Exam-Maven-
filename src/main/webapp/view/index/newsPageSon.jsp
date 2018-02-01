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

<%@ include file="/public/indexCssJs.jsp"%>

<link rel="stylesheet" href="<%=path%>/css/index/newsPageSon.css">
<script src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>


</head>
<body>
	<!-- 头 -->
	<jsp:include page="/view/index/indexHeader0.jsp"></jsp:include>
	
	<div class="panel-body">
		<h2>
			${map.news.newsTitle} 
		</h2>
		<div id="time_person">
			时间：
			<span id="el_time"><fmt:formatDate value="${map.news.newsTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
			</span>
			发布人：<span>${map.news.newsPerson} </span>
		</div>
		<div class="el_newsCenter">
			${map.news.newsContent} 
			<!-- <p>全球瞩目中，习近平出席亚太经合组织工商领导人峰会并发表题为《抓住世界经济转型机遇
				谋求亚太更大发展》的主旨演讲。这是十九大胜利闭幕后，习近平首次出席国际多边会议，向世界发出新时代中国声音。
				演讲中，习近平洞察世界经济大势，描绘亚太发展蓝图，阐述中国特色社会主义新时代的五大新征程。</p>
			<p>
				中国国际问题研究院常务副院长阮宗泽在接受人民网记者采访时谈到，此次峰会前，国际社会普遍关注两个问题：一是中国能否为亚太甚至是世界面临的问题提出解决方案；二是十九大后，中国领导人治国理政有哪些新理念、新思想。
				“可以清晰地看到，习近平主席的主旨演讲，对这两个问题都做出具体阐释。”阮宗泽指出，演讲以问题为导向，切中要害，谈了许多非常重要的问题，阐明了对内对外政策，回应了国际社会关切，大大增进了世界对新时代中国的深入理解。
			</p> -->
		</div>
	</div>
	<!-- 脚 -->
	<jsp:include page="/view/index/indexFooter.jsp"></jsp:include>
</body>
</html>