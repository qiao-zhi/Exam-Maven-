/**
 * Created by yorge on 2017/9/15.
 */
/* 使用按钮 */
/*function el_use() {
 $('#el_use').modal();
 }*/
/**
 * qlq jQuery函数
 */
$(function() {

	// 快速自动生成试卷的点击事件
	$("#quickGeneBtn").click(function() {
		$("#newPaperMethod").val("quickGene");// 将提交方式的隐藏域值设为quickGene
		validateQuick();// 验证并提交表单
	});

	// 根据题库生成试卷的点击事件
	$("#bankGeneBtn").click(function() {
		$("#newPaperMethod").val("bankGene");// 将提交方式的隐藏域值设为quickGene
		validateQuick();// 验证并提交表单
	});

	// 创建方式按钮效果
	$(".el_createButton").click(function() {
		$(".el_createButton").removeClass("btn-default");
		$(".el_createButton").removeClass("btn-primary");
		$(".el_createButton").addClass("btn-default");
		$(this).addClass("btn-primary");
	})

});

// 快速自动生成试卷的验证
var validateQuick = function() {
	// 进行数据验证
	var isNotNull = $("#createPaperForm").validate({
		ignore : [],
		rules : {
			"exampaper.title" : "required",// 发现日期
			"exampaper.level" : {
				required : true
			},
			"exampaper.paperscore" : {
				required : true,
				number : true,
				min : 20,
				max : 200
			},// 验证文本框的。前边是 name 属性
			// "exampaper.description" : "required",
			"exampaper.employeename" : {
				required : true
			},
			"exampaper.maketime" : {
				required : true
			}
		},
		messages : {
			"exampaper.title" : {
				required : "试卷标题不能为空"
			},// 下边与上边对应
			"exampaper.level" : {
				required : "试卷等级不能为空"
			},
			"exampaper.paperscore" : {
				required : "试卷分数不能为空",
				number : "请填入数字",
				min : "最小值是20",
				max : "最大值是200"
			},
			// "exampaper.description" : "试卷描述不能为空",
			"exampaper.employeename" : {
				required : "创建人不能为空"
			},
			"exampaper.maketime" : {
				required : "创建时间不能为空"
			}
		}

	});

	// 如果验证通过可以提交数据
	if (isNotNull.form()) {
		$("#createPaperForm").submit();// 提交表单
	}

}

/** *****华丽的风格线**** */
/** *****S 使用历史试卷**** */
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
			'paperStatus' : $("#paperStatus option:selected").val()
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
	var currentPage = result.pageBean.currentPage;
	var totalCount = result.pageBean.totalCount;
	var currentCount = result.pageBean.currentCount;
	$("#paperTableBody").html("");// 清空表
	var papers = result.pageBean.productList;
	for (var i = 0, length = papers.length; i < length; i++) {
		var index = (currentPage - 1) * currentCount + i + 1;
		$("#paperTableBody").append(
				"<tr><td>" + index + "</td><td>" + papers[i].title
						+ "</td><td>" + replaceLevel(papers[i].level)
						+ "</td><td>" + papers[i].paperscore + "</td><td>"
						+ papers[i].usetimes + "</td><td>"
						+ papers[i].description + "</td><td>"
						+ papers[i].employeename + "</td><td>"
						+ papers[i].maketime + "</td><td>" + "<a href='"
						+ contextPath
						+ '/usePaper_findPaperAllInfoById.action?paperId='
						+ papers[i].paperid + "'>使用试卷</a>"
						+ "<a target='_blank' href='" + contextPath
						+ "/findPaper_findPaperAllInfoById.action?paperId="
						+ papers[i].paperid + "'>试卷预览</a>" + "</td></tr>");
	}
	page(currentPage, totalCount, currentCount);
}
function page(currentPage, totalCount, currentCount) {
	$('#paginationID').pagination(
			{

				"total" : totalCount,
				"pageSize" : currentCount,
				"pageNumber" : currentPage,
				"pageList" : [ 8, 15, 20 ],
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, pageSize) {
					$("#currentPage").val(pageNumber);
					$("#currentCount").val(pageSize);
					searchPaper();
				}
			});
}

/*
 * 替换考试与试卷级别
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