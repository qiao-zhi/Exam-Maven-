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
<link rel="stylesheet" type="text/css"
	href="${baseurl}/controls/easyUI/themes/default/easyui.css" />
<link rel="stylesheet" href="${baseurl}/controls/easyUI/themes/icon.css" />
<script type="text/javascript"
	src="${baseurl}/controls/easyUI/jquery.easyui.min.js"></script>
<script src="${baseurl}/controls/easyUI/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" href="<%=path%>/css/index/studyMainpage2.css">
<script src="<%=path%>/js/index/studyMainpage2.js"></script>
<!-- 自己写的js代码 start-->
<script>

	//******页面加载的时候初始化一些数据出来(把所有数据查询出来) start
	function initPageData() {

		$("#selknow").val("");//知识点
		$("#selLevel").val("");//资料等级
		$("#currentPage").val("1");//当前页页号
		$("#resultCount").val("8");//每页显示的记录数

		Fy();

	}
	//******页面加载的时候初始化一些数据出来 end

	//查询全部数据 视频  和 文档都查询出来
	function allKnowledgeClickBtn() {
		$("#selknow").val("");//知识点
		$("#selLevel").val("");//资料等级
		$("#currentPage").val("1");//当前页页号
		$("#resultCount").val("8");//每页显示的记录数
		Fy();
	}

	//*****视频专区的分页条 *********
	//新版的分页条(最底部的那个)  start
	//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
	function queryFyVideo(resultCount, currentPage, currentTotal) {
		//分页栏  start
		$('#paginationIDU').pagination(
				{
					//			组件属性
					"total" : resultCount,//数字 当分页建立时设置记录的总数量 1
					"pageSize" : currentTotal,//数字 每一页显示的数量 10
					"pageNumber" : currentPage,//数字 当分页建立时，显示的页数 1
					"pageList" : [ 8,15,20 ],//数组 用户可以修改每一页的大小，   
					//功能
					"layout" : [ 'list', 'sep', 'first', 'prev', 'manual','next', 'last', 'links' ],
					"onSelectPage" : function(pageNumber, b) {
						//alert("pageNumber=" + pageNumber);//当前页页号
						//alert("pageSize=" + b);//每页显示的记录数
						//为两个隐藏域赋值  当前页页号   每页显示的记录数
						$("#currentPage").val(pageNumber);//当前页页号
						$("#resultCount").val(b);//每页显示的记录数

						//直接调用分页查询的方法
						Fy();
					}
				});
	}
	//新版的分页条(最底部的那个) end
	
</script>

<script type="text/javascript">
	//点击资料类型 之后的事件  通过资料等级、知识点查询出视频和非视频文件
	function knowledgeClick(obj) {
		//alert("点击成功红")
		//获取点击的知识点
		var knowledge = $(obj).text();//点击的知识点
		//alert(knowledge)
		//获取该资料类型是属于哪一级的
		var dataLevel = $(obj).parents("dl").attr("title");
		//alert(dataLevel)

		//为隐藏域赋值
		$("#selknow").val(knowledge);//知识点
		$("#selLevel").val(dataLevel);//资料等级

		//alert(("#selknow").val()+"知识点")
		//alert(("#selLevel").val())
		//当前页页号
		var currentPage = $("#currentPage").val();
		//alert("当前页页号:"+currentPage)
		//每页显示的记录数
		var resultCount = $("#resultCount").val();
		//alert("每页显示的记录数:"+resultCount)

		Fy();
	}

	//视频专区的
	function Fy() {
		$.ajax({
					url : "train_findStudyTraincontentByFy.action",
					data : {
						"currentPage" : $("#currentPage").val(),
						"resultCount" : $("#resultCount").val()
					},
					dataType : "json",
					type : "POST",
					async : true,
					success : function(data) {

						//data.traincontentList[i]代表视频的   data.traincontentListDoc[i]代表非视频也就是文档的
						var list = data.pageInfo.list;
						//---====视频专区
						//数据显示之前 要先清空视频专区的的所有数据
						$("#videoList li").remove();
						var videoStr = "";
						//alert("视频的数量:"+data.traincontentList.length)
						for (var i = 0; i < list.length; i++) {

							//培训资料的id
							var documentid = list[i].documentid;
							//资料名称
							var documentname = list[i].documentname;
							//资料级别
							var typename = list[i].typename;
							//资料类型
							var traintype = list[i].traintype;
							//文件大小
							var size = list[i].size;
							//浏览量
							var browsetimes = list[i].browsetimes;
							//所属部门
							var departmentname = list[i].departmentname;
							//上传时间
							var uptime = list[i].uptime;
							//上传人
							var employeename = list[i].employeename;
							//资料描述
							var description = list[i].description;
							//当前文件名
							var currentname = list[i].currentname;

							videoStr += "<li>";
							videoStr += "<div class='pricing pricing--yama'>";
							videoStr += "<div class='pricing__item'>";
							videoStr += "<ul class='ul'>";
							videoStr += "<li class='li_xuhao'>" + (i + 1)
									+ "</li>";
							videoStr += "<li>";
							videoStr += "<div class='pricing__price'>"
									+ documentname + "</div>";
							videoStr += "</li>";
							videoStr += "<li class='pricing__feature2'>" + typename
									+ "</li>";
							videoStr += "<li class='pricing__feature'>" + size
									+ "</li>";
							videoStr += "<li class='pricing__feature2'>"
									+ departmentname + "</li>";
/* 							videoStr += "<li class='pricing__feature'>"
									+ traintype + "</li>"; */
							/* videoStr +="<li class='pricing__feature'>"+uptime+"</li>"; */

							videoStr += "<li class='el_optionButton'>";
							//videoStr +="<a class='pricing__action' href='${pageContext.request.contextPath}/train_videoPlay.action?trainContentId="+documentid+"' target='_blank'>播放</a>";
							videoStr += "<a href='${pageContext.request.contextPath}/train_videoPlay.action?trainContentId="
									+ documentid
									+ "' target='_blank'>"
									+ "<img src='${pageContext.request.contextPath}/image/bofang.png' width='30'/></a>";
							videoStr += "</li>";

							videoStr += "</li>";
							videoStr += "</ul>";
							videoStr += "</div>";
							videoStr += "</div>"
							videoStr += "</li>";
						}
						$("#videoList").append(videoStr);//添加到视频的列表中
						//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
						queryFyVideo(data.pageInfo.total, data.pageInfo.pageNum,
								data.pageInfo.pageSize);

					},
					error : function() {
						alert("查询失败")
					}
				});
	}//Fy()方法的括号
</script>

<script type="text/javascript"	src="<%=path%>/js/index/selectmenu.min.js"></script>
<!-- 获取选中的 知识点 及其资料等级 -->
<script type="text/javascript">
	//知识点查询的点击事件
	function el_query(obj) {
		/* //为隐藏域赋值
		$("#selknow").val(knowledge);//知识点
		$("#selLevel").val(dataLevel);//资料等级 */
		//知识点
		$(obj).text();
		//级别
		var thisBank = "一级";
		$("#currentPage").val("1");
		$("#resultCount").val("8");//每页显示的记录数
		//alert($(obj).text()+" "+thisBank)
		//为知识点和资料等级的隐藏域赋值
		$("#selknow").val($(obj).text());//知识点
		$("#selLevel").val(thisBank);//资料等级
		Fy();
	}

	function el_query1(obj) {
		//知识点
		$(obj).text();
		//级别
		$("#currentPage").val("1");//当前页页号
		$("#resultCount").val("8");//每页显示的记录数
		var thisBank = "二级";
		//alert($(obj).text()+" "+thisBank)
		$("#selknow").val($(obj).text());//知识点
		$("#selLevel").val(thisBank);//资料等级
		Fy();
	}

	function el_query2(obj) {
		//知识点
		$(obj).text();
		//级别
		$("#currentPage").val("1");//当前页页号
		$("#resultCount").val("8");//每页显示的记录数
		var thisBank = "三级";
		//alert($(obj).text()+" "+thisBank)
		$("#selknow").val($(obj).text());//知识点
		$("#selLevel").val(thisBank);//资料等级
		Fy();
	}

	//一级 全部
	function el_query51(obj) {
		//知识点
		$(obj).text();
		$("#currentPage").val("1");//当前页页号
		$("#resultCount").val("8");//每页显示的记录数
		//级别
		var thisBank = "一级";
		//alert($(obj).text()+" "+thisBank)
		$("#selknow").val("");//知识点
		$("#selLevel").val(thisBank);//资料等级
		Fy();
	}

	//二级 全部
	function el_query52(obj) {
		$("#currentPage").val("1");//当前页页号
		$("#resultCount").val("8");//每页显示的记录数
		//知识点
		$(obj).text();
		//级别
		var thisBank = "二级";
		//alert($(obj).text()+" "+thisBank)
		$("#selknow").val("");//知识点
		$("#selLevel").val(thisBank);//资料等级
		Fy();
	}

	//三级 全部
	function el_query53(obj) {
		//知识点
		$("#currentPage").val("1");//当前页页号
		$("#resultCount").val("8");//每页显示的记录数
		$(obj).text();
		//级别
		var thisBank = "三级";
		//alert($(obj).text()+" "+thisBank)
		$("#selknow").val("");//知识点
		$("#selLevel").val(thisBank);//资料等级
		Fy();
	}
</script>


<!-- 自己写的js代码  end -->

</head>
<body>

	<!-- 隐藏域 start -->
	<!-- 隐藏视频的当前页页号 、 每页显示的记录数 -->
	<input id="currentPage" type="hidden" value="1">
	<!-- 当前页页号 默认为1-->
	<input id="resultCount" type="hidden" value="8">
	<!-- 每页显示的记录数 默认为10 -->

	<!-- 隐藏文档的当前页页号、每页显示的记录数 -->
	<!-- <input id="docPage" type="hidden" value="1">当前页页号 默认为1
	<input id="docCount" type="hidden" value="10">每页显示的记录数 默认为10 -->

	<!-- 隐藏选中的资料的当前名称(也就是uuid的) -->
	<input id="curDocName" type="hidden" />

	<!-- 隐藏当前选中的知识点、资料等级 -->
	<input id="selknow" type="hidden">
	<!-- //知识点 -->
	<input id="selLevel" type="hidden">
	<!-- //资料等级 -->

	<!-- 隐藏域  隐藏资料等级 -->
	<!-- <input class="grade" type="hidden" value="一级"> -->
	<!-- 隐藏域   end -->


	<!-- 头 -->
	<jsp:include page="/view/index/indexHeader0.jsp"></jsp:include>

	<div class="banner">
		<img src="<%=path%>/image/img/studyBanner1.jpg" alt="">
	</div>



	<!-- 轮播 菜单 -->

	<div class="hc_lnav">


<!-- 		<div class="row-fluid">
			<button type="button"
				class="btn btn-default hvr-sweep-to-right button20"
				id="baseMenuArrow">一级培训内容</button>
			<button type="button"
				class="btn btn-default hvr-shutter-in-horizontal button20"
				id="baseMenuCenter">二级培训内容</button>
			<button type="button"
				class="btn btn-default hvr-sweep-to-left button20"
				id="baseMenuRight">三级培训内容</button>
		</div> -->
	</div>
	<!-- 结束菜单 -->

	<!--标签页-->
	<div class="el_section">
		<div class="row el_querylabelPage">
			<ul class="">
				<li id="el_videoLi" class="active">资料专区</li>
			</ul>
		</div>

		<center><h1 id="showMessage">请以学员身份登录查看视频!</h1></center>


		<!--内容-->
		<div class="row el_ContentMainPage">
			<!--视频区-->
			<div class="row con" id="el_videodiv">

				<!-- 一类知识点 试题 -->
				<ul class="conBox" id="videoList">
					<!-- <h4>安全类信息</h4> -->
					<li>
						<div class="pricing pricing--yama">
							<div class="pricing__item"></div>

						</div>
					</li>

				</ul>

				<!--分页-->
				<div class="paginationParent">
					<div id="paginationIDU"></div>
				</div>
			</div>




		</div>

	</div>


	<!-- 脚 -->
	<jsp:include page="/view/index/indexFooter.jsp"></jsp:include>

</body>
</html>