/**
 * 考试管理 Created by yorge on 2017/9/14.
 */

/* <!-- 模态框 查看已考考试的参考员工信息--> */
function examPersons() {
	$('#examPersons').modal();
}

/* <!-- 模态框 查看未考考试的参考员工信息--> */
function examPersons2() {
	$('#examPersons2').modal();
}

/* 删除模态框 */
function delcfm() {
	$('#delcfmModel').modal();
}
function urlSubmit() {
	var url = $.trim($("#url").val());// 获取会话中的隐藏属性URL
	window.location.href = url;
}

/* 索引考试时间 */
$(function() {
	/* $("#search_begindate").simpleCanleder(); */

	queryExam();// 查询试卷
	queryBigNameAndId();// 初始化大修信息

	$("#queryExamBtn").click(function() {
		$("#currentPage").val("");// 将页面页号清空
		queryExam();
	});
	$("#outPutBtn").click(
			function() {
				if (confirm("确认导出参考人员信息?")) {
					window.location.href = contextPath
							+ "/exportExamEmployees.action?examId="
							+ $("#examId").val();
				}
			});

});

/**
 * S qlq
 */
/** ******S 分页查询考试信息******* */
var queryExam = function() {
	$.ajax({
		url : contextPath + '/findExam_findExam.action',
		data : $("#queryExam").serialize(),
		type : 'POST',
		async : true,
		dataType : 'json',
		success : showExamTable,
		error : function() {
			alert("查询考试信息失败！！！")
		}
	});
}

var showExamTable = function(response) {
	$("#examTableBody").html("");// 清空表体
	var exams = response.pageBean.productList;// 获取考试信息
	var currentPage = response.pageBean.currentPage;// 当前页
	var currentCount = response.pageBean.currentCount;// 页大小
	var totalCount = response.pageBean.totalCount;// 总数
	for (var i = 0, length = exams.length; i < length; i++) {
		var index = (currentPage - 1) * currentCount + i + 1;
		var exam_tr = '<tr><td>'
				+ index
				+ '</td><td>'
				+ exams[i].examName
				+ '</td><td>'
				+ exams[i].bigName
				+ '</td><td>'
				+ replaceLevel(exams[i].examLevel)
				+ '</td><td>'
				+ exams[i].totalTime
				+ '分钟</td><td>'
				+ exams[i].examType
				+ '</td><td>'
				+ '<a title="点击查看具体的参考人员信息" class="el_delButton" onClick="queryExamEmployees(\''
				+ exams[i].examId
				+ '\')">'
				+ exams[i].employeeNum
				+ '</a>'
				+ '</td><td>'
				+ Format(new Date(exams[i].startTime.replace(/T/g, " ")
						.replace(/-/g, "/")), "yyyy-MM-dd HH:mm  ")
				+ '到'
				+ Format(new Date(exams[i].endTime.replace(/T/g, " ").replace(
						/-/g, "/")), "  yyyy-MM-dd HH:mm") + '</td><td>'
				+ exams[i].status;
		// 拼接连接(根据不同的状态吸纳是后面不同的按钮)
		if (exams[i].status == '未开始') {
			exam_tr += '</td><td>'
					+ '<a href="'
					+ contextPath
					+ '/UpdateExam_getExamInfo.action?examId='
					+ exams[i].examId
					+ '">修改</a><a class="el_delButton" onClick="deleteExamById(\''
					+ exams[i].examId + '\')">删除</a>' + ' <a href="'
					+ contextPath
					+ '/findPaper_findPaperAllInfoById.action?paperId='
					+ exams[i].paperId + '">预览试卷</a>' + '</td></tr>';
		}else if(exams[i].status == '已结束'){
			exam_tr += '</td><td>'+'<a class="el_delButton" onClick="deleteExamById(\''
			+ exams[i].examId + '\')">删除</a>' + ' <a href="'
			+ contextPath
			+ '/findPaper_findPaperAllInfoById.action?paperId='
			+ exams[i].paperId + '">预览试卷</a>' + '</td></tr>';
			
		}
		else {
			exam_tr += '</td><td>' + ' <a href="' + contextPath
					+ '/findPaper_findPaperAllInfoById.action?paperId='
					+ exams[i].paperId + '">预览试卷</a>' + '</td></tr>'
		}

		$("#examTableBody").append(exam_tr);
	}
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
					queryExam();
				}
			});
}
/** ******E 分页查询考试信息******* */

/** *S 根据考试ID查询参考人员信息** */
var queryExamEmployees = function(examId) {
	$("#examId").val(examId);
	$.ajax({
		url : contextPath + '/findExam_findExamEmployeeByExamId.action',
		data : {
			"examId" : examId
		},
		type : 'POST',
		async : true,
		dataType : 'json',
		success : showExamEmployeesModal,
		error : function() {
			alert("查询考试员工信息失败！！！")
		}
	});
}
function showExamEmployeesModal(response) {
	// alert(JSON.stringify(response))
	$("#examEmployeesTable").html("");// 清空表体
	var employees = response.employees;
	for (var i = 0, length = employees.length; i < length; i++) {
		var employeeType = employees[i].employeeType == '1' ? "外部员工" : "内部员工";
		$("#examEmployeesTable").append(
				'<tr><td>' + (i + 1) + '</td><td>' + employees[i].name
						+ '</td><td>' + employees[i].idCode + '</td><td>'
						+ (employees[i].sex == '1' ? '男' : '女') + '</td><td>'
						+ employees[i].unitName + '</td><td>' + employeeType
						+ '</td><td>' + employees[i].examMethod + '</td></tr>')
	}
	$("#examPersons").modal({
		backdrop : 'static',
		keyboard : false
	}); // 手动开启
}
/** *E 根据考试ID查询参考人员信息** */

/** *S 根据考试ID删除考试信息** */
var deleteExamById = function(examId) {
	if (confirm("确认删除考试？")) {
		$.ajax({
			url : contextPath + '/deleteExam.action',
			data : {
				"examId" : examId
			},
			type : 'POST',
			async : true,
			dataType : 'json',
			success : function(response) {
				alert(response.result);
				if ("删除成功!" == response.result) {
					window.location.reload();// 刷新页面
				}
			},
			error : function() {
				alert("删除考试失败！！！")
			}
		});
	}
}
/** *E 根据考试ID删除考试信息** */
/** ***S 填充大修名称 ********* */
/** *S 查询并初始化大修信息****** */
function queryBigNameAndId() {
	$.post(contextPath + '/haul_getHaulNameAndIdsForExam.action', add2Select,
			'json')
}
function add2Select(response) {
	if (response != null && response.haulNameAndIds != null) {
		var nameIds = response.haulNameAndIds;
		for (var i = 0, length_1 = nameIds.length; i < length_1; i++) {
			$("#bigName").append(
					'<option value="' + nameIds[i].bigId + '">'
							+ nameIds[i].bigName + '</option>')
		}
		var value = $(":hidden[name='exam.bigid']").val();// 获取大修
		$("#bigName").val(value);// 初始化大修的值
	}
}
/** *E 查询大修信息****** */
/** ***E 填充大修名称 ********* */
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