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
<!-- 分页 -->
<link rel="stylesheet" type="text/css" href="<%=path %>/controls/easyUI/themes/default/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/controls/easyUI/themes/icon.css"/>
<script type="text/javascript" src="<%=path %>/controls/easyUI/jquery.easyui.min.js"></script>
<script src="<%=path %>/controls/easyUI/easyui-lang-zh_CN.js"></script>
<script
	src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>
<link rel="stylesheet" href="<%=path%>/css/index/newPage.css">
</head>
<body>
	<!-- 头 -->
	<jsp:include page="/view/index/indexHeader0.jsp"></jsp:include>

	<div class="banner">
		<img src="<%=path%>/image/img/newsBanner.jpg">
	</div>

	<!--<新闻消息>-->
	<div class="el_news">
		<div class="el_MiddleContentLi">
			<form id="queryNewForm">
				<input type="hidden" name="currentPage" id="newCurrentPage">
				<input type="hidden" name="currentCount" id="newCurrentCount">
				<input type="hidden" name="selectType" value="0">
			</form>
			<h4 class="el_middleTitle">
				<img src="<%=path%>/image/newsLogo.jpg" height="30">
			</h4>
			<ul>
				<!-- <li class="el_newsList"><a href="#"> 这个新闻超级有趣，欢迎大家点击 </a><span
					class="el_newsTime">2017.08.01</span></li>
				<li class="el_newsList"><a href="#"> 这个新闻超级有趣，欢迎大家点击 </a><span
					class="el_newsTime">2017.08.01</span></li>
				<li class="el_newsList"><a href="#"> 这个新闻超级有趣，欢迎大家点击 </a><span
					class="el_newsTime">2017.08.01</span></li> -->

			</ul>
			<!--分页-->
			<div class="paginationParent">
				<div id="paginationIDU"></div>
			</div>
		</div>

		<!--通知内容-->
		<div class="el_rightContent">
			<form id="queryNotingForm">
				<input type="hidden" name="currentPage" id="notCurrentPage">
				<input type="hidden" name="currentCount" id="notCurrentCount">
				<input type="hidden" name="selectType" value="1">
			</form>
			<h4 class="el_middleTitle">
				<img src="<%=path%>/image/img/noticeLogo.png" height="30">
			</h4>
			<ul class="el_notifyListUl">
				<!-- 
				<li class="el_notifyList"><a href="#">这个新闻超级有趣</a> <span
					class="el_notifyTime">2017.08.01</span></li>
				<li class="el_notifyList"><a href="#">这个新闻超级有趣</a> <span
					class="el_notifyTime">2017.08.01</span></li> -->
			</ul>
			<!--分页-->
			<div class="paginationParent">
				<div id="paginationID2"></div>
			</div>
		</div>
	</div>

	<!-- 脚 -->
	<jsp:include page="/view/index/indexFooter.jsp"></jsp:include>
</body>
</html>
<script>
/**
 * 查询新闻
 */
$(function() {
    // 页面初始化查询大修信息
    queryNews();
    queryNoting();
    
});
/** *S 分页查询大修信息*** */
// 查询新闻
var queryNews = function() {
    $.ajax({
        url : "${baseurl}" + "/newsIP_getNewsPage.action",
        data : $("#queryNewForm").serialize(),
        dataType : "JSON",
        async : true,
        type : "POST",
        success : showNews,
        error : function() {
            alert("查询大修失败!!!");
        }

    });
}
// 显示大修分页信息到表格
function showNews(response) {
    var newsList = response.pageBean.productList;// 获取到大修JSON对象
    var lis = "";
    for (var i = 0; i < newsList.length; i++) {
		lis += '<li class="el_newsList">'
				+ '<a href="newsIP_getNewsById.action?newsId='
				+ newsList[i].newsId
				+ '&newsType='
				+ 0
				+ '">'
				+ newsList[i].newsTitle
				+ '</a>'
				+ '<span class="el_newsTime">'
				+ Format(new Date(newsList[i].newsTime.replace(/T/g," ").replace(/-/g,"/")),
						"yyyy-MM-dd hh:mm:ss")
				+ '</span></li>';
	}

	$(".el_MiddleContentLi").children("ul").html("");
	$(".el_MiddleContentLi").children("ul").append(lis);
    
    var currentCount = response.pageBean.currentCount;// 页大小
    var totalCount = response.pageBean.totalCount;// 页总数
    var currentPage = response.pageBean.currentPage;// 当前页
   console.log(response.pageBean)
   
   if(response.pageBean.totalCount != 0){
    	// 动态开启分页组件
    	page1(currentPage, totalCount, currentCount);
   }
}
// 显示分页
function page1(currentPage, totalCount, currentCount) {
    // 修改分页的基本属性
    $('#paginationIDU').pagination(
            {
                // 组件属性
                "total" : totalCount,// 数字 当分页建立时设置记录的总数量 1
                "pageSize" : currentCount,// 数字 每一页显示的数量 10
                "pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
                "pageList" : [8],// 数组 用户可以修改每一页的大小，
                // 功能
                "layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
                        'last', 'links' ],
                "onSelectPage" : function(pageNumber, pageSize) {
                    $("#notCurrentPage").val(pageNumber);
                    $("#newCurrentCount").val(pageSize);
                    // 查询新闻
                    queryNews();
                }
            });
}
/** *E 分页查询大修信息*** */



/** *E 分页查询通知信息*** */

var queryNoting = function() {
    $.ajax({
        url : "<%=path %>" + "/newsIP_getNewsPage.action",
        data : $("#queryNotingForm").serialize(),
        dataType : "JSON",
        async : true,
        type : "POST",
        success : showNoting,
        error : function() {
            alert("查询大修失败!!!");
        }

    });
}
// 显示大修分页信息到表格
function showNoting(response) {
    var newsList = response.pageBean.productList;// 获取到大修JSON对象
    var lis = "";
    for (var i = 0; i < newsList.length; i++) {
		lis += '<li class="el_notifyList">'
				+ '<a href="newsIP_getNewsById.action?newsId='
				+ newsList[i].newsId
				+ '&newsType='
				+ 1
				+ '">'
				+ newsList[i].newsTitle
				+ '</a>'
				+ ' <span class="el_notifyTime">'
				+ Format(new Date(newsList[i].newsTime.replace(/T/g," ").replace(/-/g,"/")),
						"yyyy-MM-dd hh:mm:ss")
				+ '</span></li>'
	}
	$(".el_notifyListUl").html("");
	$(".el_notifyListUl").append(lis);
    
    var currentCount = response.pageBean.currentCount;// 页大小
    var totalCount = response.pageBean.totalCount;// 页总数
    var currentPage = response.pageBean.currentPage;// 当前页

    if(response.pageBean.totalCount != 0){
	    // 动态开启分页组件
	   page(currentPage, totalCount, currentCount);
    }
}
// 显示分页
function page(currentPage, totalCount, currentCount) {
    // 修改分页的基本属性
    $('#paginationID2').pagination(
            {
                // 组件属性
                "total" : totalCount,// 数字 当分页建立时设置记录的总数量 1
                "pageSize" : currentCount,// 数字 每一页显示的数量 10
                "pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
                "pageList" : [8],// 数组 用户可以修改每一页的大小，
                // 功能
                "layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
                        'last', 'links' ],
                "onSelectPage" : function(pageNumber, pageSize) {
                    $("#notCurrentPage").val(pageNumber);
                    $("#newCurrentCount").val(pageSize);
                    // 查询新闻
                    queryNoting();
                }
            });
}
/** *E 分页查询大修信息*** */



</script>