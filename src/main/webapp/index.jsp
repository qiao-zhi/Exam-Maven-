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
<!-- 转发到另一个首页 -->
<%
	response.sendRedirect("view/index/studyMainpage2.jsp");
%>



<%@ include file="/public/indexCssJs.jsp"%>

<script src="<%=path%>/controls/lunbo/lunbo.js"></script>
<script src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>
<script src="${pageContext.request.contextPath }/js/index/index.js "></script>
<link rel="stylesheet" href="<%=path%>/css/index/index.css">
<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script>
	$(function(){
		/* 新闻显示 */
		$.ajax({
			url:"${pageContext.request.contextPath }/newsIP_getTypeNews.action",
			data:{newsType:"新闻",newsNums:"13"},
			dataType:"json",
			type:"POST",
			success:function(data){
				//把新闻映射到列表中。
				var newsList = data.newsList;
				var lis = "";
				for(var i = 0; i < newsList.length; i ++) {
					if(i >= 12){
						break;
					}
					lis += '<li class="el_newsList">'
					+'<a href="newsIP_getNewsById.action?newsId=' + newsList[i].newsId +'&newsType='+ 0
					+'" class="el_newsA" title="'+newsList[i].newsTitle+'">' + newsList[i].newsTitle + '</a>'
					+'<span class="el_newsTime">'+ Format(new Date(newsList[i].newsTime.replace(/T/g," ").replace(/-/g,"/")), "yyyy-MM-dd") +'</span></li>';
				}

				$(".el_MiddleContentLi").children("ul").html("");
				$(".el_MiddleContentLi").children("ul").append(lis);
				$(".el_MiddleContentLi").children("ul").append('<li><a href="view/index/newPage.jsp">更多</a></li>');
			}/*,
			 error:function(){
				alert("新闻初始化失败")
			} */
		})
		
		/* 通知显示 */
		 $.ajax({
			url:"${pageContext.request.contextPath }/newsIP_getTypeNews.action",
			data:{newsType:"通知",newsNums:"5"},
			dataType:"json",
			type:"POST",
			success:function(data){
				
				//把新闻映射到列表中。
				var newsList = data.newsList;
				var lis = "";
				for(var i = 0; i < newsList.length; i ++) {
					if(i >= 5){
						break;
					}
					lis += '<li class="el_notifyList">'
					+'<a href="newsIP_getNewsById.action?newsId=' + newsList[i].newsId +'&newsType='+ 1
					+'" class="el_notifyA" title="'+newsList[i].newsTitle+'">'+ newsList[i].newsTitle +'</a>'
					+' <span class="el_notifyTime">'+ Format(new Date(newsList[i].newsTime.replace(/T/g," ").replace(/-/g,"/")), "yyyy-MM-dd") +'</span></li>'
				}
				$(".el_notifyListUl").html("");
				$(".el_notifyListUl").append(lis);
			}
		}) 
	})

</script>
</head>
<body>

	<!-- 头 -->
	<jsp:include page="/view/index/indexHeader0.jsp"></jsp:include>

	<!--轮播-->
	<div class="banner">
		<!--轮播-->
		<div class="slideShow2">
			<!--图片布局开始-->
			<ul>
				<li><a href="#"><img src="<%=path%>/image/img/a1.jpg" /></a></li>
				<li><a href="#"><img src="<%=path%>/image/img/a2.jpg" /></a></li>
				<li><a href="#"><img src="<%=path%>/image/img/a3.jpg" /></a></li>
				<li><a href="#"><img src="<%=path%>/image/img/a4.jpg" /></a></li>
			</ul>
			<div class="showNav2">
				<span class="active"></span> <span></span> <span></span> <span></span>
			</div>
		</div>
	</div>
	<div id="banner_bottom">
		<span>新 闻 通 知</span>
	</div>

	<!--<新闻消息>-->
	<div class="el_news">
		<!--新闻，通知-->

		<!--左边新闻-->
		<div class="el_leftContent">
			<!--轮播-->
			<div class="slideShow">
				<!--图片布局开始-->
				<h4>电厂在线考试现场</h4>
				<ul>
					<li><a href="#"><img src="<%=path%>/image/img/news1.jpg" /></a></li>
					<li><a href="#"><img src="<%=path%>/image/img/news2.jpg" /></a></li>
					<li><a href="#"><img src="<%=path%>/image/img/news3.jpg" /></a></li>
					<li><a href="#"><img src="<%=path%>/image/img/news4.jpg" /></a></li>
				</ul>
				<div class="showNav">
					<span class="active"></span> <span></span> <span></span> <span></span>
				</div>
			</div>
		</div>


		<!--中间新闻-->
		<div class="el_MiddleContentLi">
			<h4 class="el_middleTitle"><img src="<%=path %>/image/newsLogo.jpg" height="30"></h4>
			<ul>
				<!-- <li class="el_newsList"><a href="#"> 这个新闻超级有趣，欢迎大家点击 </a><span
					class="el_newsTime">2017.08.01</span></li> -->
				
			</ul>
		</div>

		<!--通知内容-->
		<div class="el_rightContent">
			<div class="el_rightContentHead">
				<h4 class="el_rightTitle">通知消息</h4>
				<a href="<%=path%>/view/index/newPage.jsp"><span>>更多</span></a>
			</div>
			<ul class="el_notifyListUl">
				<!-- <li class="el_notifyList"><a href="#">这个新闻超级有趣</a> <span
					class="el_notifyTime">2017.08.01</span></li> -->
				
			</ul>
		</div>
	</div>

<div class="el_ContentMainPage">

		<h4>培训资料</h4>
		<!--视频区-->
		<div class="con" id="el_videodiv">

			<!-- 一类知识点 试题 -->
			<ul class="conBox" id="videoList">
				

				<li><a href="${pageContext.request.contextPath }/view/index/studyMainpage2.jsp">更多</a></li>
			</ul>

		</div>
		

		
	</div>

	<!-- *********当页面加载的时候初始化培训资料 视频  start*************** -->
	<script type="text/javascript">
		$(function(){
			
			Fy();			
		})
	
		
	   
	    
	    
	  
	</script>
	<!-- 隐藏选中的资料的当前名称(也就是uuid的) -->
	<input id="curDocName" type="hidden"/>
	
	<!-- 脚 -->
	<jsp:include page="/view/index/indexFooter.jsp"></jsp:include>


</body>

</html>