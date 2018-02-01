/**
 * 试卷管理 Created by yorge on 2017/9/14.
 */

/* 删除模态框 */
function delcfm() {
	$('#delcfmModel').modal();
}
function urlSubmit() {
	var url = $.trim($("#url").val());// 获取会话中的隐藏属性URL
	window.location.href = url;
}
/**
 * 页面加载的时候查询试卷
 */
$(function() {
	// 初始化查询试卷
	searchPaper();
	// 查询按钮的点击事件
	$("#querBtn").click(function() {
		$("#currentPage").val("");// 点击查询的时候将页号清空
		searchPaper();
	});
});

/**
 * 查询试卷
 */
function searchPaper() {
	$.ajax({
		url : 'findPaper_findPapers.action',
		data : {
			'currentPage' : $("#currentPage").val(),
			'currentCount' : $("#currentCount").val(),
			'title' : $("#title").val(),
			'level' : $("#level option:selected").val(),
			'paperStatus' : $("#paperStatus option:selected").val(),
		},
		type : 'POST',
		async : true,
		dataType : 'text',
		success : showTable,
		error : function() {
			alert("请求失败！");
		}
	});
}
// 填充表格
function showTable(data) {
	var result = eval("(" + data + ")");
	$("#paperTableBody").html("");// 清空表
	var currentPage = result.pageBean.currentPage; // 当前页
	var totalCount = result.pageBean.totalCount; // 数据总数
	var currentCount = result.pageBean.currentCount;
	var papers = result.pageBean.productList;
	for (var i = 0, length = papers.length; i < length; i++) {
		var index = (currentPage - 1) * currentCount + i + 1;
		var str = "<tr><td>"
			+ index
			+ "</td><td>"
			+ papers[i].title
			+ "</td><td>"
			+ replaceLevel(papers[i].level)
			+ "</td><td>"
			+ papers[i].paperscore
			+ "</td><td>"
			+ papers[i].usetimes
			+ "</td><td>"
			+ papers[i].description
			+ "</td><td>"
			+ papers[i].employeename
			+ "</td><td>"
			+ papers[i].maketime
			+ "</td><td>"+
			"<a title='预览试卷' href='"+ contextPath+ "/findPaper_findPaperAllInfoById.action?paperId="
				+ papers[i].paperid + "'><span class='glyphicon glyphicon-search'></span></a>";
		//使用次数大于0的不能修改和删除
		if(papers[i].usetimes=='0'){
			str	+= "<a title='修改试卷' href='javascript:void(0)' onclick='updatePaper(\""
			+ papers[i].paperid
			+ "\")'><span class='glyphicon glyphicon-pencil'></span></a><a title='删除试卷' href='javascript:void(0)' onclick='deletePaper(\""
			+ papers[i].paperid
			+ "\")'><span class='glyphicon glyphicon-trash'></span></a>";
			
		}
		//未归档的需要归档
		if(papers[i].paperanswer=="0"){
			str +="<a title='归档试卷' href='javascript:void(0)' onclick='archivePaper(\""
				+ papers[i].paperid
				+ "\")'><span class='glyphicon glyphicon-log-in'></span></a>";
		}else{
			str +="<a title='撤销试卷归档' href='javascript:void(0)' onclick='noArchivePaper(\""
				+ papers[i].paperid
				+ "\")'><span class='glyphicon glyphicon-log-out'></span></a>";
		}
		str +="</td></tr>";
		$("#paperTableBody").append(str);
	}
	/**
	 * 显示分页
	 */
	// 动态开启分页组件
	page(currentPage, totalCount, currentCount);
}
// 显示分页
function page(currentPage, totalCount, currentCount) {
	// 修改分页的基本属性
	$('#paginationID').pagination(
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
					searchPaper();
				}
			});
}

// ajax异步删除试卷
function deletePaper(paperId) {
	if (confirm("确认删除试卷?删除后将不可恢复！")) {
		$.ajax({
			url : contextPath + '/deletePaper.action',
			data : {
				'paperId' : paperId
			},
			type : 'POST',
			dataType : 'json',
			async : true,
			success : function(data) {
				alert(data);
				if (data == '已经有考试使用本试卷，你不能删除试卷！') {
					return;
				}
				// 删除成功后刷新页面
				window.location.reload();
			},
			error : function() {
				alert("请求失败");
			}
		});
	}
}
/** S *修改试卷******* */
function updatePaper(paperId) {
	$.post(contextPath + '/update_findPaperStatus.action', {
		"paperId" : paperId
	}, sureResult, 'json');
	function sureResult(result) {
		if (result == 0) {
			alert("该试卷已经被考试用过且考试已经开始,不允许修改，建议您去新创建试卷!")
			return;
		}
		self.location = contextPath
				+ '/update_findPaperAllInfoById.action?paperId=' + paperId;
	}

}
/** E *修改试卷******* */
/**
 * 替換世界級別
 */
function replaceLevel(level) {
	if (level == '1') {
		return "厂级";
	}
	if (level == '2') {
		return "部门级";
	}
	if (level == '3') {
		return "班组级";
	}
}

/***************S   试卷归档，撤销归档操作***************/
//归档
function archivePaper(paperId){
	if(confirm("您确认归档?")){
		$.post(contextPath+"/update_updatePaperanswer.action",
				{"paperId":paperId,
			"paperStatus":"1"},
			function(data){
				alert(data);
				searchPaper();
			}
			,'json')
	}
}
function noArchivePaper(paperId){
	if(confirm("您确认取消归档?")){
		$.post(contextPath+"/update_updatePaperanswer.action",
				{"paperId":paperId,
			"paperStatus":"0"},
			function(data){
				alert(data);
				searchPaper();
			}
		,'json')
	}
}
/***************E   试卷归档操作***************/