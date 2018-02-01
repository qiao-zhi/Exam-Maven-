/**
 * Created by yorge on 2017/9/14.
 */

/* 添加部门人员 */
/*
 * qlq写的 添加考试
 */
var departIndex = 0;
var departmentIds = "";// 全部部门ID串
var inner_open = false, out_open = false;// 用于记录两个模态框是否打开
var departmentr4Query = [];// 等价于var departmentr4Query=new Array();
$(function() {
	initSomdeThing();
	$(".el_empList").hover(function() {
		$(this).children(".el_empDelete").show();
	}, function() {
		$(this).children(".el_empDelete").hide();
	})
	$(".el_empList").click(function() {
		$(this).remove();
	})

	/* <!--根据选择的部门类型，确定显示那个模态框--> */
	$(".el_radioBox2").click(function() {
		/*
		 * 判断选择的部门类型。 0：厂内部门 1：外来单位
		 */
		departIndex = $(this).children().val();
	})

	/* 模态复选 员工参加考试 */
	$(".el_checkQuanxuan").change(function() {
		if ($(this).prop("checked") == true) {
			$(".el_checks").prop("checked", "true");
		} else {
			$(".el_checks").removeAttr("checked");
		}
	})

	$("#el_addExamSubmit").click(function() {
		confirm("取消将不保存当前修改信息，确定取消吗？")
	})
	// 内部员工选择的时候模态框的确认 事件
	$("#okInnerEmployee").click(function() {
		seleInnerEmployee();
	});
	// 外部部员工选择的时候模态框的确认 事件
	$("#okOutEmployee").click(function() {
		selectOutEmployee();
	});
	// 添加内部人员的点击事件
	$("#selectEmployeeIn").click(function() {
		queryInnerEmployee();
	});
	// 添加外部人员的点击事件
	$("#selectEmployeeOut").click(function() {
		queryOutEmployee();
	});
	// 保存按钮的点击事件
	$("#saveExamBtn").click(function() {
		saveExam();// 保存试卷
	});

	// 内部员工查询事件
	$("#queryInnerBtn").click(function() {
		queryInnerEmployee();
	});
	// 外部员工查询事件
	$("#queryOutBtn").click(function() {
		queryOutEmployee();
	});
	// 内部模态框关闭的时候将标志字段设为false
	$('#innerEmployeeModal').on('hidden.bs.modal', function() {
		inner_open = false;
	});
	// 外部模态框关闭的时候将标志字段设为false
	$('#outEmployeeModal').on('hidden.bs.modal', function() {
		out_open = false;
	});
	// 点击厂内部门的单选圈的事件(隐藏外来，开启内部)
	$("#openInRadio").click(function() {
		$("#addInDiv").css('display', 'block');// 显示内部信息
		$(".inShow").css('display', '');// 显示内部参考部门信息
		$(".outShow").css('display', 'none');// 隐藏大修名称框
		$("#addOutDiv").css('display', 'none');// 隐藏下面的选择员工框
		$(":radio[value='线上']").prop("disabled", false);
		$(":radio[value='线上']").prop("checked", true);
		$("#inpstart0").val("");
		$("#inpend0").val("");
	});

	// 线上线下的点击方式
	$("[name='examMethod']").click(
			function() {

				if ($(this).val() == "线上") {
					$("#inpstart0").val("")
					$("#inpend0").val("")
				}
				if ($(this).val() == "线下") {
					// 获取现在的毫秒数
					var nowSeconds = getServerDate().getTime();// getTime返回日期的毫秒数
					// 45分钟后的时间
					var endSeconds = nowSeconds + 45 * 60 * 1000;
					// 将时间置为默认现在时间
					$("#inpstart0")
							.val(
									Format(new Date(nowSeconds),
											"yyyy-MM-dd HH:mm:ss"));// 以服务器时间创建Date对象并格式化
					$("#inpend0")
							.val(
									Format(new Date(endSeconds),
											"yyyy-MM-dd HH:mm:ss"))
				}
			});

	// 点击外来部门的单选圈的事件
	$("#openOutRadio").click(
			function() {
				// alert(getServerDate())
				$("#addInDiv").css('display', 'none');// 隐藏内部信息
				$(".inShow").css('display', 'none');// 隐藏内部参考部门信息
				$(".outShow").css('display', '');// 显示大修名称框
				$("#addOutDiv").css('display', '');// 显示下面的选择员工框
				// 点击外来部门的点击事件
				$(":radio[value='线上']").prop("disabled", true);
				$(":radio[value='线下']").prop("checked", true);
				// 初始化日期框
				// 获取现在的毫秒数
				var nowSeconds = getServerDate().getTime();// getTime返回日期的毫秒数
				// 45分钟后的时间
				var endSeconds = nowSeconds + 45 * 60 * 1000;
				// 将时间置为默认现在时间
				$("#inpstart0").val(
						Format(new Date(nowSeconds), "yyyy-MM-dd HH:mm:ss"));// 以服务器时间创建Date对象并格式化
				$("#inpend0").val(
						Format(new Date(endSeconds), "yyyy-MM-dd HH:mm:ss"))

			});
	// 查询大修名字与大修ID
	queryBigNameAndId();
	// 选中外来单位的点击事件
	$("#selectUnitBtn").click(function() {
		addUnit2Input();
	});
	// 点击外部的时候关闭那个模糊查询的框
	$(document).click(function(event) {
		if ($(event.target).attr("class") != "unitName") {
			if ($("#showDiv").css("display") == "block") {
				$("#showDiv").css("display", "none");// 点击外部的时候隐藏名字提示框
			}
		}
	});

})
function initSomdeThing() {
	initEmployeeTypeDic();

	// 获取现在的毫秒数
	var nowSeconds = getServerDate().getTime();// getTime返回日期的毫秒数
	// 45分钟后的时间
	var endSeconds = nowSeconds + 45 * 60 * 1000;
	// 将时间置为默认现在时间
	$("#inpstart0").val(Format(new Date(nowSeconds), "yyyy-MM-dd HH:mm:ss"));// 以服务器时间创建Date对象并格式化
	$("#inpend0").val(Format(new Date(endSeconds), "yyyy-MM-dd HH:mm:ss"))
	// 显示外来的单位div
	$("#addInDiv").css('display', 'none');// 隐藏内部信息
	$(".inShow").css('display', 'none');// 隐藏内部参考部门信息
	$(".outShow").css('display', '');// 显示大修名称框
	$("#addOutDiv").css('display', '');// 显示下面的选择员工框
	// 线上方式置为空
	$(":radio[value='线上']").prop("disabled", true);

}
/** *S 查询内部部门员工 */
var queryInnerEmployee = function() {
	// var departments = $("#el_chooseDepart1").text();// 获取部门名字
	var departments = departmentIds;// 获取部门名字
	// 如果没有选择部门提醒选择部门，否则查询
	if (departments.length > 0) {
		departments = departments.substring(0, departments.length - 1);
		$("input[name='queryInnerEmployeesCondition.departments']").val(
				departments);
		ajaxQueryEmployeeIn(departments);
	} else {
		alert("至少选择一个部门");
		return;
	}
}

var ajaxQueryEmployeeIn = function(departments) {
	$.ajax({
		url : contextPath + '/exam_getEmployeeIns4Exam.action',
		data : $("#queryInnerForm").serialize(),
		type : 'POST',
		dataType : 'json',
		success : showEmployeeInModal,
		error : function() {
			alert("查询内部员工出错！！！")
		}
	});
}
function showEmployeeInModal(response) {
	$("#innerEmployeeTable").html("");
	var examEmployeeIns = response.examEmployeeIns;
	for (var i = 0, length = examEmployeeIns.length; i < length; i++) {
		var sex = examEmployeeIns[i].sex == '1' ? "男" : "女";
		var tr_inner = '<tr><td>'+'<input type="hidden" class="departmentId" value="'+examEmployeeIns[i].departmentId+'"/>'
				+ '<input type="checkbox" name="employeeIn" value="'
				+ examEmployeeIns[i].idcode + '" class="el_checks" /></td><td>'
				+ examEmployeeIns[i].name + '</td><td>' + sex + '</td><td>'
				+ examEmployeeIns[i].idcode + '</td>' + '<td>'
				+ examEmployeeIns[i].departmentname
				+ '</td></tr>';
		$("#innerEmployeeTable").append(tr_inner);
	}
	checkInHasChecked();// 检验那些元素已经被选中
	// 如果模态框未打开就打开模态框并设置标志字段为已打开
	if (!inner_open) {
		$("#innerEmployeeModal").modal({
			backdrop : 'static',
			keyboard : false
		});
		inner_open = true;
	}

}

/**
 * 校验内部员工哪些已经选中，用于判断员工再次选择员工的时候已经选中的checked为选中 1.获取外部已经选中添加到框中的ID数组inEmployeeIds
 * 2.获取内部多选框集合，判断其value值，如果在inEmployeeIds中能找见将其checked属性设为checked
 */
var checkInHasChecked = function() {
	var inEmployees = $("#department_employee_in").find(".el_empList");
	var inEmployeeIds = [];// 标记外面选中的已经ID
	// 初始化外面ID数组
	for (var i = 0, length = inEmployees.length; i < length; i++) {
		inEmployeeIds[i] = inEmployees[i].id;// 获取到外部的ID
	}
	var modalInEmployees = $("#innerEmployeeTable").find(".el_checks");// 查找模态框所有的多选框
	for (var k = 0, length_1 = inEmployeeIds.length; k < length_1; k++) {
		for (var j = 0, length_2 = modalInEmployees.length; j < length_2; j++) {
			if (inEmployeeIds[k] == modalInEmployees[j].value) {
				modalInEmployees[j].checked = "checked";// 修改元素的选中属性为选中
				break;
			}
		}
	}

}
/*
 * function setColorByUnit() { var modalInEmployees =
 * $("#innerEmployeeTable").find(".el_checks");// 查找模态框所有的多选框 }
 */

// 确认选中员工函数
var seleInnerEmployee = function() {

	$("[name='employeeFlag']").val("111");// 用于验证员工是否为空
	var employeeInnerIdcode = $("input[name='employeeIn']:checked")// 获取选择的内部员工元素
	$("#innerEmployeeDiv").html("");// 清空存放员工ID的多选框的存放ID的标签

	// 清空已经选择的员工，重新添加(将已经选择的部门下面的人数清空)
	var units_in = $("#department_employee_in").find(".panel-body");
	for (var j = 0, length_1 = units_in.length; j < length_1; j++) {
		$(units_in[j]).html("");
	}
	// 清空人数(将已经选择的人数置为0重新添加部门框)
	var units_in_num = $("#department_employee_in").find(".employeeNum");
	for (var k = 0, length_1 = units_in_num.length; k < length_1; k++) {
		$(units_in_num[k]).text("0");
	}

	for (var i = 0, length = employeeInnerIdcode.length; i < length; i++) {
		/** *S 将选择的员工添加到选择员工** */
		var departmentId = $(employeeInnerIdcode[i]).parents('tr').children('td')
				.eq(0).find(".departmentId").val();// 获取部门ID
		var departName = $(employeeInnerIdcode[i]).parents('tr').children('td')
		.eq('4').html();// 获取部门名称
		var employeeInIdCode = $(employeeInnerIdcode[i]).parents('tr')
				.children('td').eq('3').html();// 获取员工身份证号(用于标记)
		var employeeInName = $(employeeInnerIdcode[i]).parents('tr').children(
				'td').eq('1').html();// 获取员工名称
		$("#" + departName + " .panel-body").append(
				'<span class="el_empList" id="' + employeeInIdCode + '">'
						+ employeeInName + '<img class="el_empDelete" src="'
						+ contextPath + '/image/delete.png" /></span>');
		// 修改人数
		$("#" + departName + " .employeeNum").text(
				parseInt($("#" + departName + " .employeeNum").text()) + 1);
		/** *E 将选择的员工添加到选择员工** */
		var idCode = $(employeeInnerIdcode[i]).val();
		// 内部员工身份证号添加到form中
		$("#innerEmployeeDiv").append(
				'<input type="hidden" name="exam.employeeInExams[' + i
						+ '].employeeid" value="' + idCode + '"/>');
		// 将员工姓名加到表单中
		$("#innerEmployeeDiv").append(
				'<input type="hidden" name="exam.employeeInExams[' + i
						+ '].employeename" value="' + employeeInName + '"/>');
		// 将员工的部门ID存到UnitId
		$("#innerEmployeeDiv").append(
				'<input type="hidden" name="exam.employeeInExams[' + i
						+ '].unitid" value="' + departmentId + '"/>');
	}
	$("#outEmployeeDiv").html("");// 清空外部员工文本框
	$('#innerEmployeeModal').modal('hide');// 隐藏模态框

}
/** *E 查询内部部门员工 */

/** *S 查询外部部门员工 */
var queryOutEmployee = function() {
	var units = $("#el_chooseUnit").text();// 获取部门名字
	var examLevel = $("#exam_level").val();// 获取考试级别
	// 如果没有选择部门提醒选择部门，否则查询
	if (examLevel == "") {
		alert("请先选择考试级别");
		return;
	}
	if (units.length > 0) {
		units = units.substring(0, units.length - 1);// 去掉最后一个逗号
		$("input[name='queryOuterEmployeesCondition.units']").val(units);// 给隐藏域设置值
		ajaxQueryEmployeeOut();
	} else {
		alert("至少选择一个外部单位");
		return;
	}
}

var ajaxQueryEmployeeOut = function() {
	// alert("开始查询外来员工")
	$.ajax({
		url : contextPath + '/exam_getEmployeeOuts4Exam.action',
		data : $("#queryOutForm").serialize(),
		type : 'POST',
		dataType : 'json',
		success : showEmployeeOutModal,
		error : function() {
			alert("查询外来员工出错！！！")
		}
	});
}
function showEmployeeOutModal(response) {
	// alert(JSON.stringify(response));// 转换为JSON串输出
	$("#outEmployeeTable").html("");// 清空表格
	var examEmployeeOuts = response.examEmployeeOuts;
	for (var i = 0, length = examEmployeeOuts.length; i < length; i++) {
		var sex = examEmployeeOuts[i].sex == '1' ? "男" : "女";
		var trainInt = examEmployeeOuts[i].trainStatus;
		var tarinStr;
		/*
		 * if (trainInt == 0) { tarinStr = "未参加考试"; } if (trainInt == 1) {
		 * tarinStr = "通过一级考试"; } if (trainInt == 2) { tarinStr = "通过二级考试"; } if
		 * (trainInt == 3) { tarinStr = "通过三级考试"; }
		 */
		var tr_out = '<tr><td>'
				+ '<input type="checkbox" name="employeeOut" value="'
				+ examEmployeeOuts[i].idCode
				+ '" class="el_checks" /></td><td>'
				+ examEmployeeOuts[i].name
				+ '</td><td>'
				+ sex
				+ '</td><td>'
				+ examEmployeeOuts[i].idCode
				+ '</td><td>'
				+ examEmployeeOuts[i].unitname
				+ '</td><td>'
				+ examEmployeeOuts[i].empType
				+ '</td><td>'
				+ examEmployeeOuts[i].minusNum
				+ '<input type="hidden" class="unitId" value="'
				+ examEmployeeOuts[i].unitId
				+ '"/>'
				+ '<input type="hidden" class="bigemployeeoutid" value="'
				+ examEmployeeOuts[i].bigemployeeoutid
				+ '"/>'
				+ '<input type="hidden" class="distributeid" value="'
				+ examEmployeeOuts[i].distributeid + '"/> ' + '</td></tr>';
		$("#outEmployeeTable").append(tr_out);
	}
	checkOutHasChecked();// 判断选中的打上对勾
	if (!out_open) {
		$("#outEmployeeModal").modal({
			backdrop : 'static',
			keyboard : false
		});
		out_open = true;
	}
}
/**
 * 校验哪些已经选中，用于判断员工再次选择员工的时候已经选中的checked为选中 1.获取外部已经选中添加到框中的ID数组outEmployeeIds
 * 2.获取内部多选框集合，判断其value值，如果在outEmployeeIds中能找见将其checked属性设为checked
 */
var checkOutHasChecked = function() {
	var outEmployees = $("#department_employee_out").find(".el_empList");
	var outEmployeeIds = [];// 标记外面选中的已经ID
	// 初始化外面ID数组
	for (var i = 0, length = outEmployees.length; i < length; i++) {
		outEmployeeIds[i] = outEmployees[i].id;// 获取到外部的ID
	}
	var modalOutEmployees = $("#outEmployeeTable").find(".el_checks");// 查找模态框所有的多选框
	for (var k = 0, length_1 = outEmployeeIds.length; k < length_1; k++) {
		for (var j = 0, length_2 = modalOutEmployees.length; j < length_2; j++) {
			if (outEmployeeIds[k] == modalOutEmployees[j].value) {
				modalOutEmployees[j].checked = "checked";// 修改元素的选中属性为选中
				break;
			}
		}
	}

}

// 确认选中外部员工函数
var selectOutEmployee = function() {
	$("[name='employeeFlag']").val("111");// 用于验证员工是否为空
	var employeeOutIdcode = $("input[name='employeeOut']:checked")// 获取选择的外部员工元素
	// alert(employeeOutIdcode.length)
	$("#outEmployeeDiv").html("");// 清空存放员工ID的多选框的存放ID的标签

	// 清空已经选择的员工，重新添加(将已经选择的部门下面的人数清空)
	var units_out = $("#department_employee_out").find(".panel-body");
	for (var j = 0, length_1 = units_out.length; j < length_1; j++) {
		$(units_out[j]).html("");
	}
	// 清空人数(将已经选择的人数置为0重新添加部门框)
	var units_out_num = $("#department_employee_out").find(".employeeNum");
	for (var k = 0, length_1 = units_out.length; k < length_1; k++) {
		$(units_out_num[k]).text("0");
	}

	for (var i = 0, length = employeeOutIdcode.length; i < length; i++) {
		/** *S 将选择的员工添加到选择员工** */
		var unitName = $(employeeOutIdcode[i]).parents('tr').children('td').eq(
				'4').html();// 获取部门名称
		var employeeIdCode = $(employeeOutIdcode[i]).parents('tr').children(
				'td').eq('3').html();// 获取员工身份证号(用于标记)
		var distributeid = $(employeeOutIdcode[i]).parents('tr').children(
				'td:last').find(".distributeid").val();// 获取员工分配编号
		var bigemployeeoutid = $(employeeOutIdcode[i]).parents('tr').children(
				'td:last').find(".bigemployeeoutid").val();// 获取员工分配编号
		var unitId = $(employeeOutIdcode[i]).parents('tr').children('td:last')
				.find(".unitId").val();// 获取员工分配编号
		var employeeOutName = $(employeeOutIdcode[i]).parents('tr').children(
				'td').eq('1').html();// 获取员工姓名
		$("#" + unitName + " .panel-body").append(
				'<span class="el_empList" id="' + employeeIdCode + '">'
						+ employeeOutName + '<img class="el_empDelete" src="'
						+ contextPath + '/image/delete.png" /></span>');
		// 修改人数
		$("#" + unitName + " .employeeNum").text(
				parseInt($("#" + unitName + " .employeeNum").text()) + 1);
		/** *E 将选择的员工添加到选择员工表单** */
		var idCode = $(employeeOutIdcode[i]).val();
		// 外部员工身份证号添加到form中
		$("#outEmployeeDiv").append(
				'<input type="hidden" name="exam.employeeOutExams[' + i
						+ '].employeeid" value="' + idCode + '"/>');
		// 将员工姓名加到表单中
		$("#outEmployeeDiv").append(
				'<input type="hidden" name="exam.employeeOutExams[' + i
						+ '].employeename" value="' + employeeOutName + '"/>');

		// 将员工的单位ID和分配编号加到成绩表
		$("#outEmployeeDiv").append(
				'<input type="hidden" name="exam.employeeOutExams[' + i
						+ '].unitid" value="' + unitId + '"/>');
		// 将员工的分配编号加到成绩表
		$("#outEmployeeDiv").append(
				'<input type="hidden" name="exam.employeeOutExams[' + i
						+ '].distributeid" value="' + distributeid + '"/>');
		// 将员工的分配编号加到成绩表
		$("#outEmployeeDiv").append(
				'<input type="hidden" name="exam.employeeOutExams[' + i
						+ '].bigemployeeoutid" value="' + bigemployeeoutid
						+ '"/>');

	}
	$("#innerEmployeeDiv").html("");// 清空内部员工文本框
	$("#outEmployeeModal").modal('hide');
}
/** *E 查询外部部门员工 */

/**
 * 页面加载的时候查询试卷
 */
$(function() {
	searchDepartmentTree();// 生成内部部门树
	// 点击参考部门的时候生成树
	$("#el_chooseDepart1").click(function() {
		if ($("#treeDemo10").css('display') == 'none') {
			$("#treeDemo10").show();
			/*
			 * if ($("[name='el_departType']:checked").val() == 1) {
			 * $("#department_employee_out").html(""); } if
			 * ($("[name='el_departType']:checked").val() == 0) {
			 * $("#department_employee_in").html(""); }
			 */
			/*
			 * $("#el_chooseDepart1").html("");// 清空上面已经选择的内容
			 */} else {
			// 自己的处理
			$("#treeDemo10").hide();
		}
	});

	// 初始化的时候查询试卷
	searchPaper();
	// 查询按钮的点击事件
	$("#querBtn").click(function() {
		$("#currentPage").val("")
		searchPaper();
	});
});

/** *S 点击外来单位查询对应的大修部门且开启模态框 ****** */
function queryHaulUnit() {
	var bigId = $("#bigName").val();// 需要查询的大修ID
	$("[name='queryOuterEmployeesCondition.bigId']").val(bigId);
	$
			.post(
					contextPath + '/unit_getUnitidsAndNamesByHaulId.action',
					{
						'bigId' : bigId
					},
					function(response) {
						// 每次请求一次都应该清除上次的残留数据
						$("#haulUnitDiv").html("");
						if (response != null
								&& response.unitidsAndNames != null) {
							var unitidsAndNames = response.unitidsAndNames;
							for (var i = 0, length_1 = unitidsAndNames.length; i < length_1; i++) {
								$("#haulUnitDiv")
										.append(
												'<span><input type="checkbox" value="'
														+ unitidsAndNames[i].unitId
														+ '" style="margin-left: 20px;" /><span>'
														+ unitidsAndNames[i].name
														+ '</span></span>');
							}
						}
					}, 'json')

}
function openUnitModal() {
	$("#unitModal").modal({
		keyboard : false,
		backdrop : 'static'
	})
}
/** *E 点击外来单位查询对应的大修部门且开启模态框 ****** */

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
			'level' : $("#paper_level").val(),
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
	var currentPage = result.pageBean.currentPage; // 当前页
	var totalCount = result.pageBean.totalCount; // 数据总数
	var currentCount = result.pageBean.currentCount;
	$("#paperTableBody").html("");// 清空表
	var papers = result.pageBean.productList;
	for (var i = 0, length = papers.length; i < length; i++) {
		// var index = (result.pageBean.currentPage - 1) * 8 + i + 1;
		$("#paperTableBody")
				.append(
						"<tr><td>"
								+ "<input type='radio' onclick='setPaperIdValue()' name='exam.paperid_1' value='"
								+ papers[i].paperid
								+ "'/>"
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
								+ "</td><td>"
								+ "<a href='/Exam/findPaper_findPaperAllInfoById.action?paperId="
								+ papers[i].paperid + "' target='_blank'>试卷预览</a>"
								+ "</td></tr>");
	}
	/**
	 * 显示分页
	 */
	// 动态开启分页组件
	page(currentPage, totalCount, currentCount);
}
var setPaperIdValue = function() {
	$("[name='exam.paperid']").val($("[name='exam.paperid_1']:checked").val());// 从下面试卷列表获取选中的试卷添加到隐藏域
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

/**
 * 跟据选择的是厂内部门还是外来单位生成对应的树结构,0代表内部部门，1代表外来单位
 */
/** ********* S 生成内部部门树*********** */
// 查询内部部门结构
var searchDepartmentTree = function() {
	var zNodes10;
	$.ajax({
		url : contextPath + '/exam_getDepartmentTree.action',
		async : true,
		dataType : 'json',
		success : function(response) {
			zNodes10 = response.departmentTrees;
			// 生成树结构
			geneDepartmentTree(zNodes10);

		},
		error : function() {
			alert("查询内部部门树失败！！！")
		}
	});
}

// 生成内部部门树
function geneDepartmentTree(departmentTrees) {
	$("#treeDemo10").html(""); // 清空树结构
	var setting = {
		view : {
			selectedMulti : false
		},
		check : {
			enable : true,
			chkboxType : {
				"Y" : "",
				"N" : ""
			}
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "departmentId",
				pIdKey : "upDepartmentId",
				rootPId : null
			},
			key : {
				name : "departmentName",
			}
		},
		callback : {
			beforeCheck : beforeCheck,
			onClick : zTreeOnClick,
			onCheck : zTreeOnCheck
		}
	};
	var treeNodes = departmentTrees;
	$.fn.zTree.init($("#treeDemo10"), setting, treeNodes);
}
// 鼠标点击树事件(打印点击的id与名字)
function zTreeOnClick(event, treeId, treeNode) {
	// alert(treeNode.departmentId + ", " + treeNode.departmentName);
	/* alert($("#el_chooseDepart1").text()); */
}
// 鼠标点击前面复选框d事件
function zTreeOnCheck(event, treeId, treeNode) {
};

var el_chooseDepart1, className10 = "dark", el_id;
// 点击前面的复选框之前的事件
function beforeCheck(treeId, treeNode) {
	// alert(departmentIds);
	className10 = (className10 === "dark" ? "" : "dark");
	el_id = treeNode.departmentId;
	// 判断点击的节点是否被选中，返回false 和 true
	if (!treeNode.checked) {// 选中
		departmentIds += treeNode.departmentId + ",";
		showLog10(treeNode.departmentName + ',');
		$("#department_employee_in")
				.append(
						// 添加部门到下面的选择员工
						'<div class="panel panel-default el_departPersons" id="'
								+ treeNode.departmentName
								+ '">'
								+ '<div class="panel-heading"><span class="el_addDepart" >'
								+ treeNode.departmentName
								+ '</span>&nbsp;&nbsp;'
								+ '(人数：<span class="employeeNum">0</span>)</div>'
								+ '<div class="panel-body"></div>' + '</div>');
	} else { // 点击选中，向让其未选中
		departmentIds = departmentIds.replace(treeNode.departmentId + ",", "");
		$("#" + treeNode.departmentName).remove();// 删除部门
		noshowLog10(treeNode.departmentName + ',', treeNode);
		var parentzTree = treeNode.getParentNode();
	}
	return (treeNode.doCheck !== false);
}
function showLog10(str) {
	if (!el_chooseDepart1)
		el_chooseDepart1 = $("#el_chooseDepart1");
	el_chooseDepart1.append("<li class='" + className10 + "' id='" + el_id
			+ "'>" + str + "</li>");
}

function noshowLog10(str, tNode) {
	if (!el_chooseDepart1)
		el_chooseDepart1 = $("#el_chooseDepart1");
	// 删除当前选中的树的名字
	el_chooseDepart1.children("#" + el_id).remove();
	// 删除当前子的树的名字
	if (tNode.isParent) {
		var childrenNodes = tNode.children;
		if (childrenNodes) {
			for (var i = 0; i < childrenNodes.length; i++) {
				var el_id0 = childrenNodes.departmentId;
				el_chooseDepart1.children("#" + el_id0).remove();

				// result += ',' + childrenNodes[i].id;
				// result = getChildNodes(childrenNodes[i], result);
			}
		}
	}

}

/** ********* E 生成内部部门树*********** */

function checkNode10(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo10"), type = e.data.type, nodes = zTree
			.getSelectedNodes();
	if (type.indexOf("All") < 0 && nodes.length == 0) {
		alert("请先选择一个节点");
	}

	if (type == "checkAllTrue") {
		zTree.checkAllNodes(true);
	} else if (type == "checkAllFalse") {
		zTree.checkAllNodes(false);
	} else {
		var callbackFlag = $("#callbackTrigger").attr("checked");
		for (var i = 0, l = nodes.length; i < l; i++) {
			if (type == "checkTrue") {
				zTree.checkNode10(nodes[i], true, false, callbackFlag);
			} else if (type == "checkFalse") {
				zTree.checkNode10(nodes[i], false, false, callbackFlag);
			} else if (type == "toggle") {
				zTree.checkNode10(nodes[i], null, false, callbackFlag);
			} else if (type == "checkTruePS") {
				zTree.checkNode10(nodes[i], true, true, callbackFlag);
			} else if (type == "checkFalsePS") {
				zTree.checkNode10(nodes[i], false, true, callbackFlag);
			} else if (type == "togglePS") {
				zTree.checkNode10(nodes[i], null, true, callbackFlag);
			}
		}
	}
}

function setAutoTrigger10(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo10");
	zTree.setting10.check.autoCheckTrigger = $("#autoCallbackTrigger").attr(
			"checked");
	$("#autoCheckTriggerValue").html(
			zTree.setting10.check.autoCheckTrigger ? "true" : "false");
}

/** *S 保存考试 */
var saveExam = function() {
	// 进行数据验证
	$.validator.methods.compareDate = function(value, element, param) {
		var startDate = $("#inpstart0").val(); // 开始日期
		var d1 = new Date(startDate);
		var d2 = new Date(value);
		return d1 <= d2;
	};

	var isNotNull = $("#examForm").validate({
		ignore : [],
		rules : {
			"exam.paperid" : "required",// 发现日期
			"exam.examname" : {
				required : true
			},
			"exam.traincontent" : {
				required : true
			},
			"exam.xueshi" : {
				required : true,
				digits : true
			},
			"exam.answertime" : {
				required : true,
				digits : true,
				range : [ 20, 400 ]
			},
			"exam.address" : "required",// 验证文本框的。前边是 name 属性
			"exam.examlevel" : "required",// 验证文本框的。前边是 name 属性
			"exam.starttime" : "required",
			"exam.endtime" : {
				required : true/*,
				compareDate : "#inpend0"*/
			},
			"exam.employeename" : {
				required : true
			},
			"employeeFlag" : "required"
		},
		messages : {
			"exam.paperid" : {
				required : "必须选择一份考试试卷"
			},// 下边与上边对应
			"exam.examname" : {
				required : "考试名称不能为空"
			},
			"exam.traincontent" : {
				required : "培训内容不能为空"
			},
			"exam.xueshi" : {
				required : "培训时长不能为空",
				digits : "必须输入整数"
			},
			"exam.examlevel" : {
				"required" : "考试等级不能为空"
			},// 验证文本框的。前边是 name
			"exam.answertime" : {
				required : "考试时长不能为空",
				digits : "考试时长必须是整数",
				range : "考试时长必须在20到400分钟范围内"
			},
			"exam.address" : "考试地址不能为空",// 验证文本框的。前边是 name 属性
			"exam.starttime" : "开始时间不能为空",
			"exam.endtime" : {
				required : "结束时间不能为空"/*,
				compareDate : "结束日期不能小于开始日期"*/
			},
			"exam.employeename" : {
				required : "创建人不能为空"
			},
			"employeeFlag" : "必须选择参考员工"
		}

	});

	// 如果验证通过可以提交数据
	if (isNotNull.form()) {
		if (confirm("确认保存考试并退出?")) {
			$("#saveExamBtn").prop("disabled", true);
			$.ajax({
				url : contextPath + '/exam_addExam.action',
				data : $("#examForm").serialize(),
				type : 'POST',
				dataType : 'json',
				async : true,
				success : function(response) { //
					alert(response.result);
					if (response.result == '添加成功！') {
						window.location.href = contextPath
								+ '/view/examParper/exam/examManage.jsp';
					}
				},
				error : function() {
					alert("保存试卷失败！！！")
				}
			});
		}
	}
}
/** *E 保存考试 */
/** S 改变考试等级的时候改变试卷等级** */
function queryPaper(obj) {
	var value = $(obj).children("option:selected").val();
	// 设置隐藏的查询考试培训情况的等级
	$("#paper_level").val(value);// 将隐藏的试卷ID删除
	$("[name='exam.paperid']").val("");
	$("#level option[value='" + value + "']").prop("selected", "selected");
	$("[name='queryOuterEmployeesCondition.trainStatus']").val(
			(parseInt(value)).toString());// 设置通过考试的级别等于考试级别
	/*
	 * $("#defaultTrainStatus option[value='" + ((parseInt(value) -
	 * 1).toString()) + "']").prop( "selected", "true");// 设置通过考试的级别等于考试级别
	 */
	// 根据试卷等级查询试卷
	searchPaper();
}
/** E 改变考试等级的时候改变试卷等级** */
/** S 查询大修的ID与名字************** */
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
	}
}
/** E 查询大修的ID与名字************** */
/** *S 选中部门的时候进行的处理**** */
function addUnit2Input() {
	// 遍历所有的部门
	$("#haulUnitDiv :checkbox")
			.each(
					function(i) {
						var unitId = $(this).val();// 获取到选择的部门ID
						var unitName = $(this).siblings("span").text();// 获取到选择的部门名称
						var $divs = $("#department_employee_out").children(
								"#" + unitName);// 获取到已经添加的部门的ID
						var $el_chooseUnit = $("#el_chooseUnit").children(
								"#" + unitId);// 获取外来单位框的
						// 未选中
						if ($(this).prop("checked") == false) {
							if ($divs.length == 1 && $el_chooseUnit.length == 1) {
								// 如果存在下面就从下面删除就删除
								$("#department_employee_out").children(
										"#" + unitName).remove();// 场添加员工删除
								$("#el_chooseUnit").children("#" + unitId)
										.remove();// 从文本框中删除
							}
						}
						// 选中的
						else {
							// 如果不存在下面添加到下面
							if ($divs.length == 0 && $el_chooseUnit.length == 0) {
								$("#department_employee_out")
										.append(
												'<div class="panel panel-default el_departPersons" id="'
														+ unitName
														+ '">'
														+ '<div class="panel-heading"><span class="el_addDepart" >'
														+ unitName
														+ '</span>&nbsp;&nbsp;'
														+ '(人数：<span class="employeeNum">0</span>)</div>'
														+ '<div class="panel-body"></div>'
														+ '</div>');
								$("#el_chooseUnit").append(
										"<li class='" + unitName + "' id='"
												+ unitId + "'>" + unitName
												+ ',' + "</li>");

							}

						}

					});
	$("#unitModal").modal("hide");// 隐藏模态框
}
/** *E 选中部门的时候进行的处理**** */
/** ****S 初始化查询员工工种字典********* */
function initEmployeeTypeDic() {
	$.post(contextPath + '/dic_getDicNamesByUpid.action', {
		"upId" : "100"
	}, showEmployeeTypeDic, 'json');
}
function showEmployeeTypeDic(response) {
	if (response != null && response.names != null) {
		var names = response.names;// 获取字段返回的值
		for (var i = 0; i < names.length; i++) {
			$("#employType").append(
					'<option value="' + names[i] + '">' + names[i]
							+ '</option>')
		}
	}
}
/** ****E 初始化查询员工工种字典********* */
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

/** **********获取服务器时间********* */
function getServerDate() {
	var nowTimeStr;
	$.ajax({
		url : "onlineExam_getNowServerTime.action?date="
				+ Format(new Date(), "HH:mm:ss"),
		async : false,
		datatype : "json",
		success : function(data) {
			nowTimeStr = data.nowTime.replace(/T/g, " ").replace(/-/g, "/");
		}
	})
	return new Date(nowTimeStr);
	// return new Date();
}

/** S 模糊查询考试姓名和级别******** */
// 模糊查询姓名
function findNames(obj) {
	$("#showDiv").html("");
	// 1.获取表单的值
	var word = $(obj).val();
	var content = "";
	// 2.异步ajax去数据库模糊查询
	$
			.post(
					contextPath + "/exam_getExamNameAndLevelByWord.action", // 请求地址
					{
						"nameWord" : word
					}, // 请求传递的参数，也可以是JSON
					function(response) { // data表示传递回来的数据，只有在成功的时候才有
						if (response.nameAndLevel != null
								&& response.nameAndLevel.length > 0) {
							for (var i = 0; i < response.nameAndLevel.length; i++) {
								content += "<div style='padding:5px;cursor:pointer;' class='unitName' onclick='clickFn(this)' onmouseover='overFun(this);' onmouseout='outFn(this);'>"
										+ response.nameAndLevel[i].examName
										+ "<input class='examlevel' type='hidden' value='"
										+ response.nameAndLevel[i].examlevel
										+ "'/>"
										+ "<input class='traincontent' type='hidden' value='"
										+ response.nameAndLevel[i].traincontent
										+ "'/>"
										+ "<input class='xueshi' type='hidden' value='"
										+ response.nameAndLevel[i].xueshi
										+ "'/>" + "</div>";
							}
							$("#showDiv").css("display", "block");
							$("#showDiv").html(content);
						}
					}, "json" // 表示返回内容的格式,json会将传回来的自动解析成json对象
			);

}
// 鼠标悬浮上去
function overFun(obj) {
	$(obj).css("background", "#C0C1C5");
}
// 鼠标移走
function outFn(obj) {
	$(obj).css("background", "#fff");
}

// 点击的时候将值加到上面的文本框事件同时根据名字去数据库 查询信息
function clickFn(obj) {
	// 考试名称
	var selectName = $(obj).text();
	$("[name='exam.examname']").val(selectName);// 设置到上面
	// 考试级别//
	var examlevel = $(obj).find(".examlevel").val();
	$("[name='exam.examlevel'] option[value='" + examlevel + "']").prop(
			"selected", true);
	// 培训内容
	var traincontent = $(obj).find(".traincontent").val();
	$("[name='exam.traincontent']").val(traincontent);
	// 培训学时
	var xueshi = $(obj).find(".xueshi").val();
	$("[name='exam.xueshi']").val(xueshi);

	$("#showDiv").css("display", "none");
	queryPaper($("#exam_level"));
}
