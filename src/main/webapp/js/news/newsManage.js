/**
 * Created by yorge on 2017/10/30.
 */

/*
 * $(function(){ // 初始化插件 $("#zyupload").zyUpload({ width : "640px", // 宽度
 * height : "400px", // 宽度 itemWidth : "140px", // 文件项的宽度 itemHeight : "115px", //
 * 文件项的高度 url : "/upload/UploadAction", // 上传文件的路径 fileType :
 * ["jpg","png","js","exe"],// 上传文件的类型 fileSize : 5120000, // 上传文件的大小 multiple :
 * true, // 是否可以多个文件上传 dragDrop : true, // 是否可以拖动上传文件 tailor : false, //
 * 是否可以裁剪图片 del : true, // 是否可以删除文件 finishDel : false, // 是否在上传文件完成后删除预览
 * queueSizeLimit : 2, onInit: function(){ //
 * $("#file_upload-button").css("width","100"); alert("sd") }, 外部获得的回调接口
 * onSelect: function(selectFiles, allFiles){ // 选择文件的回调方法 selectFile:当前选中的文件
 * allFiles:还没上传的全部文件 alert('s') console.info("当前选择了以下文件：");
 * console.info(selectFiles); }, onDelete: function(file, files){ // 删除一个文件的回调方法
 * file:当前删除的文件 files:删除之后的文件 console.info("当前删除了此文件：");
 * console.info(file.name); }, onSuccess: function(file, response){ //
 * 文件上传成功的回调方法 console.info("此文件上传成功："); console.info(file.name);
 * console.info("此文件上传到服务器地址："); console.info(response);
 * $("#uploadInf").append("<p>上传成功，文件地址是：" + response + "</p>"); },
 * onFailure: function(file, response){ // 文件上传失败的回调方法 console.info("此文件上传失败：");
 * console.info(file.name); }, onComplete: function(response){ // 上传完成的回调方法
 * console.info("文件上传完成"); console.info(response); } });
 * 
 * });
 */

$(function() {
	showAllNews(); // 显示表格内容

	$("#newsQueryButton").click(function() {
		$("#currentPage").val("");// 将页面页号清空
		showAllNews(); // 显示表格内容
	})

	// 表格全选事件。
	$("#el_mainCheck").change(function() {
		if ($(this).prop("checked") == true) {
			$(".el_checks").prop("checked", "true");
		} else {
			$(".el_checks").removeAttr("checked");
		}
	})

})

// ===========================显示所有数据====================
function showAllNews() {
	$.ajax({
		url : '${pageContext.request.contextPath}/news_findAllNews.action',
		type : "POST",
		data : $("#newQueryForm").serialize(),
		dataType : "json",
		success : showNewsTable,
		error : function() {
			alert("查询失败！")
		}
	});
}

// 查询 && 初始化
var showNewsTable = function(response) {
	$("#newsTable").children("tbody").html("");// 清空表体

	var news = response.pageBean.productList;// 获取考试信息

	var currentPage = response.pageBean.currentPage;// 当前页
	var currentCount = response.pageBean.currentCount;// 页大小
	var totalCount = response.pageBean.totalCount;// 总数

	var newsTbody_tr = "";
	for (var i = 0, length = news.length; i < length; i++) {
		var index = (currentPage - 1) * currentCount + i + 1;
		newsTbody_tr += '<tr><td><input type="checkbox" name="el_chooseDepart" class="el_checks"/>'
				/*
				 * + '<input type="hidden" class="el_newsContent" value="' +
				 * news[i].newsContent + '">'
				 */
				+ '<input type="hidden" class="el_newsId" value="'
				+ news[i].newsId
				+ '"></td>'
				+ '<td>'
				+ index
				+ '</td>'
				+ '<td>'
				+ news[i].newsTitle
				+ '</td>'
				+ '<td>'
				+ news[i].newsType
				+ '</td>'
				+ '<td>'
				+ news[i].newsPerson
				+ '</td>'
				+ '<td>'
				+ Format(new Date(news[i].newsTime.replace(/T/g, " ").replace(
						/-/g, "/")), "yyyy-MM-dd hh:mm:ss")
				+ '</td>'
				+ '<td>'
				+ '<a href="javascript:void(0)" onclick="showNewsContent(\''
				+ news[i].newsId
				+ '\')">查看内容</a>'
				+ '<a href="'
				+ rootFile
				+ '/news_findNewsById.action?newsId='
				+ news[i].newsId
				+ '">修改</a>'
				+ '<a href="javascript:void(0)"  onclick="delcfm(\''
				+ news[i].newsId + '\')">删除</a>' + '</td></tr>';
	}
	$("#newsTable").children("tbody").append(newsTbody_tr);
	// 动态开启分页组件
	page(currentPage, totalCount, currentCount);
}

// 显示分页
function page(currentPage, totalCount, currentCount) {

	$('#paginationIDU').pagination(
			{
				// 组件属性
				"total" : totalCount,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : currentCount,// 数字 每一页显示的数量 10
				"pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8, 15, 20 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, pageSize) {

					$("#currentPage").val(pageNumber);
					$("#currentCount").val(pageSize);

					// 查询试卷
					showAllNews();
				}
			});
}

// ================================显示新闻内容==========================
function showNewsContent(newsId) {
	$.ajax({
		url : "${pageContext.request.contextPath}/news_findNewsContent.action",
		data : {
			"newsId" : newsId
		},
		type : "POST",
		dataType : "json",
		success : function(data) {
			$("#el_showNewContent").find(".modal-body").html("");
			$("#el_showNewContent").find(".modal-body").append(
					'<p>' + data.news.newsContent + '</p>');
			$("#el_showNewContent").modal();
		},
		error : function() {
			alert("查询失败");
		}
	})
}

// ===============================删除新闻==================
var del_newsId;
function delcfm(newsId) {
	$("#delcfm").modal();
	del_newsId = newsId;
}
function del_buttonClick() {
	$.ajax({
		url : "${pageContext.request.contextPath}/news_delNewsById.action",
		data : {
			"newsId" : del_newsId
		},
		type : "POST",
		dataType : "json",
		success : function(data) {
			alert(data.result);
			showAllNews();
		},
		error : function() {
			alert("删除失败！")
		}
	})
}

// ================================批量删除新闻================
var el_duoEl_newsId;
function el_duoremoveNews() {
	var newsIds = new Array();

	// 判断选择列的数量
	if ($(".el_checks:checked").length < 2) {
		alert("批量删除操作，至少选择两条记录！")
	} else {
		// 获取所选择的列
		$(".el_checks").each(
				function() {
					if ($(this).prop("checked")) {
						var el_newsId = $(this).parent("td").children(
								".el_newsId").val();
						// newsIds = newsIds.concat(el_newsId);
						newsIds.push(el_newsId);
					}
				})
		$("#el_duoremoveNews").modal();

	}

	el_duoEl_newsId = newsIds;

}
// 为确定按钮添加 删除时间
function del_duobuttonClick() {
	$
			.ajax({
				url : "${pageContext.request.contextPath}/news_delNewsBatchByIds.action",
				data : {
					"newsIds" : el_duoEl_newsId.toString()
				},
				type : "POST",
				dataType : "json",
				success : function() {
					alert("删除成功！")
					// 刷新表
					showAllNews();
				},
				error : function() {
					alert("删除失败！")
				}
			})
}
