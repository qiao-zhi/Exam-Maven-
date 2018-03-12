/*
 * var obj = eval("("+employees+")"); alert(obj)
 */
var zNodes10, zNodes11;// 内部部门与外部部门节点、
var departmentIds = "";// 全部部门ID串
var inner_open = false, out_open = false;// 用于记录两个模态框是否打开
// 初始化工作
function init() {
	// 进行内部的初始化工作
	if ("内部考试" == examType) {
		searchDepartmentTree();// 生成内部部门树
	}
	// 进行外部考试的初始化工作
	if ("外部考试" == examType) {
		queryBigNameAndId();// 查询大修信息
		queryHaulUnit();// 初始化部门信息
		queryExamEmployees();// 生成部门之后根据考试ID查询考试员工信息
	}
	searchPaper();// 查询试卷
}
$(function() {
	init();
	// 查询试卷按钮的点击事件
	$("#querBtn").click(function() {
		$("#currentPage").val("")
		searchPaper();
	});
	//线上线下的点击方式
	$("[name='examMethod']").click(function(){
		if($(this).val()=="线上"){
			$("#inpstart0").val("")
			$("#inpend0").val("")
		}
		if($(this).val()=="线下"){
			$("#inpstart0").val( Format(getServerDate(),"yyyy-MM-dd HH:mm:ss"))
			$("#inpend0").val( Format(getServerDate(),"yyyy-MM-dd HH:mm:ss"))
		}
	});
	// 参考部门后面的点击事件
	$("#el_chooseDepart1").click(function() {
		$("#treeDemo10").toggle();
	});
	// 修改内部人员的点击事件
	$("#selectEmployeeIn").click(function() {
		queryInnerEmployee();
	});
	// 修改外部人员的点击事件
	$("#selectEmployeeOut").click(function() {
		queryOutEmployee();
	});
	// 外部员工查询事件
	$("#queryOutBtn").click(function() {
		queryOutEmployee();
	});
	// 内部员工查询事件
	$("#queryInnerBtn").click(function() {
		queryInnerEmployee();
	});
	// 内部模态框关闭的时候将标志字段设为false
	$('#innerEmployeeModal').on('hidden.bs.modal', function() {
		inner_open = false;
	});
	// 外部模态框关闭的时候将标志字段设为false
	$('#outEmployeeModal').on('hidden.bs.modal', function() {
		out_open = false;
	});
	// 内部员工选择的时候模态框的确认 事件
	$("#okInnerEmployee").click(function() {
		seleInnerEmployee();
	});
	// 外部部员工选择的时候模态框的确认 事件
	$("#okOutEmployee").click(function() {
		selectOutEmployee();
	});
	// 保存按钮的点击事件
	$("#updateExamBtn").click(function() {
		updateExam();// 修改试卷
	});

});

/** ********* S 生成内部部门树*********** */
// 查询内部部门结构
var searchDepartmentTree = function() {
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
				idKey : "departmentid",
				pIdKey : "updepartmentid",
				rootPId : null
			},
			key : {
				name : "departmentname",
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
	queryExamEmployees();// 生成树之后根据考试ID查询考试员工信息
}
// 鼠标点击树事件(打印点击的id与名字)
function zTreeOnClick(event, treeId, treeNode) {
	// alert(treeNode.departmentid + ", " + treeNode.departmentname);
	// alert($("#el_chooseDepart1").text());
}
// 鼠标点击前面复选框d事件
function zTreeOnCheck(event, treeId, treeNode) {
};

var el_chooseDepart1, className10 = "dark", el_id;
// 点击前面的复选框之前的事件
function beforeCheck(treeId, treeNode) {
	className10 = (className10 === "dark" ? "" : "dark");
	el_id = treeNode.departmentname;
	// 判断点击的节点是否被选中，返回false 和 true
	if (!treeNode.checked) {// 选中
		departmentIds += treeNode.departmentid + ",";
		showLog10(treeNode.departmentname + ',');
		$("#department_employee_in")
				.append(
						// 添加部门到下面的选择员工
						'<div class="panel panel-default el_departPersons" id="'
								+ treeNode.departmentname
								+ '">'
								+ '<div class="panel-heading"><span class="el_addDepart" >'
								+ treeNode.departmentname
								+ '</span>&nbsp;&nbsp;'
								+ '(人数：<span class="employeeNum">0</span>)</div>'
								+ '<div class="panel-body"></div>' + '</div>');
	} else { // 点击选中，向让其未选中
		departmentIds = departmentIds.replace(treeNode.departmentid + ",", "");
		$("#" + treeNode.departmentname).remove();// 删除部门
		noshowLog10(treeNode.departmentname + ',', treeNode);
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
				var el_id0 = childrenNodes.departmentName;
				el_chooseDepart1.children("#" + el_id0).remove();

				// result += ',' + childrenNodes[i].id;
				// result = getChildNodes(childrenNodes[i], result);
			}
		}
	}

}

/** ********* E 生成内部部门树*********** */

/** S 查询试卷 */
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
								+ papers[i].level
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
								+ papers[i].paperid
								+ "' target='_blank'>试卷预览</a>" + "</td></tr>");
	}
	// 初始化试卷(选择考试选中的试卷)
	initPaper();
	/**
	 * 显示分页
	 */
	var currentPage = result.pageBean.currentPage; // 当前页
	var totalCount = result.pageBean.totalCount; // 数据总数
	var currentCount = result.pageBean.currentCount;
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
// 初始化试卷
var initPaper = function() {
	var papers = $("[name='exam.paperid_1']");// 获取页面中的试卷(DOM 元素数组)
	for (var i = 0, length = papers.length; i < length; i++) {
		if ($(papers[i]).val() == examPaperId)// 选择考试的试卷
			$(papers[i]).attr("checked", "checked");

	}
}
/** E 查询试卷 */

/** *S 根据考试ID查询参考人员信息以及初始化(以及部门信息)** */
var queryExamEmployees = function() {
	$.ajax({
		url : contextPath + '/findExam_findExamEmployeeByExamId.action',
		data : {
			"examId" : examId
		},
		type : 'POST',
		async : true,
		dataType : 'json',
		success : showExamEmployees,
		error : function() {
			alert("查询考试员工信息失败！！！")
		}
	});
}
function showExamEmployees(response) {
	var employees = response.employees;
	if (employees != null && employees.length > 0) {
		if (employees[0].employeeType == '0') {
			// 显示内部部门信息(初始化部门)
			showInDepartment(employees);
		} else {
			// 显示外部部门信息
			showOutUnit(employees);
		}
	}
}
// 显示内部部门信息
var showInDepartment = function(employees) {
	var departmentName = [];// 声明一个部门数组用于拼接部门名称
	var department_add = 0;
	// 遍历出部门名称
	for (var i = 0, length_1 = employees.length; i < length_1; i++) {
		if (i == 0) {
			departmentName[department_add] = employees[0].unitName;
			department_add++;
			continue;
		}
		if (employees[i].unitName != employees[i - 1].unitName) {
			departmentName[department_add] = employees[i].unitName;
			department_add++;
		}
	}
	// 获取树对象
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo10");
	// 获取所有树节点
	var nodes = treeObj.transformToArray(treeObj.getNodes());
	// 遍历出部门添加到文本框同时显示到选择员工下面，同时动态设置树节点的选中
	for (var j = 0, length_2 = departmentName.length; j < length_2; j++) {
		// 将部门拼接到添加到外来单位人员下面
		$("#department_employee_in")
				.append(
						// 添加部门到下面的选择员工
						'<div class="panel panel-default el_departPersons" id="'
								+ departmentName[j]
								+ '">'
								+ '<div class="panel-heading"><span class="el_addDepart" >'
								+ departmentName[j]
								+ '</span>&nbsp;&nbsp;'
								+ '(人数：<span class="employeeNum">0</span>)</div>'
								+ '<div class="panel-body"></div>' + '</div>');
		// //将部门拼接到添加到参考部门右边
		$("#el_chooseDepart1").append(
				'<li id="' + departmentName[j] + '" class="">'
						+ departmentName[j] + ',</li>');
		// 遍历树节点设置根据部门名称设置部门树的选中状态
		for (var k = 0, length_3 = nodes.length; k < length_3; k++) {
			if (departmentName[j] == nodes[k].departmentName) {
				nodes[k].checked = "true";
				departmentIds += nodes[k].departmentId + ",";// 全部部门ID串
				// alert("选中");
				break;
			}
		}
	}
	// 显示员工到对应的下面
	showInEmployees(employees);
}
// 显示外部员工信息
var showInEmployees = function(employees) {
	for (var i = 0, length_1 = employees.length; i < length_1; i++) {
		// 添加员工
		$("#" + employees[i].unitName + " .panel-body").append(
				'<span class="el_empList" id="' + employees[i].idCode + '">'
						+ employees[i].name + '<img class="el_empDelete" src="'
						+ contextPath + '/image/delete.png" /></span>');
		// 修改人数
		$("#" + employees[i].unitName + " .employeeNum").text(
				parseInt($("#" + employees[i].unitName + " .employeeNum")
						.text()) + 1);
	}
}
// 显示外部部门信息
var showOutUnit = function(employees) {
	var unitName = [];// 声明一个部门数组用于拼接部门名称
	var unit_add = 0;
	// 遍历出部门名称
	for (var i = 0, length_1 = employees.length; i < length_1; i++) {
		if (i == 0) {
			unitName[unit_add] = employees[0].unitName;
			unit_add++;
			continue;
		}
		if (employees[i].unitName != employees[i - 1].unitName) {
			unitName[unit_add] = employees[i].unitName;
			unit_add++;
		}
	}

	// 遍历出部门添加到文本框同时显示到选择员工下面，同时动态设置树节点的选中
	for (var j = 0, length_2 = unitName.length; j < length_2; j++) {
		// 将部门拼接到添加到外来单位人员下面
		$("#department_employee_out")
				.append(
						// 添加部门到下面的选择员工
						'<div class="panel panel-default el_departPersons" id="'
								+ unitName[j]
								+ '">'
								+ '<div class="panel-heading"><span class="el_addDepart" >'
								+ unitName[j]
								+ '</span>&nbsp;&nbsp;'
								+ '(人数：<span class="employeeNum">0</span>)</div>'
								+ '<div class="panel-body"></div>' + '</div>');
		// 添加到input框中
		$("#el_chooseUnit").append(
				"<li class='" + unitName[j] + "' id='" + unitName[j] + "'>"
						+ unitName[j] + ',' + "</li>");
		// 选中模态框中部们
		$("#haulUnitDiv :checkbox").each(function(i) {
			if ($(this).siblings("span").text() == unitName[j]) {
				$(this).attr("checked", "true");
			}
		});

	}
	// 显示员工到对应的下面
	showOutEmployees(employees);
}
// 显示外部员工信息
var showOutEmployees = function(employees) {
	console.log(employees)
	for (var i = 0, length_1 = employees.length; i < length_1; i++) {
		// 添加员工
		$(
				"#department_employee_out" + " #" + employees[i].unitName
						+ " .panel-body").append(
				'<span class="el_empList" id="' + employees[i].idCode + '">'
						+ employees[i].name + '<img class="el_empDelete" src="'
						+ contextPath + '/image/delete.png" /></span>');

		// 修改人数
		$(
				"#department_employee_out" + " #" + employees[i].unitName
						+ " .employeeNum").text(
				parseInt($(
						"#department_employee_out" + " #"
								+ employees[i].unitName + " .employeeNum")
						.text()) + 1);
	}
}

/** *E 根据考试ID查询参考人员信息以及初始化(以及部门信息)** */

/** *S 查询外部部门员工 */
var queryOutEmployee = function() {
	// alert("添加外部员工")
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
	$("#outEmployeeTable").html("");// 清空表格
	var examEmployeeOuts = response.examEmployeeOuts;
	for (var i = 0, length = examEmployeeOuts.length; i < length; i++) {
		var sex = examEmployeeOuts[i].sex == '1' ? "男" : "女";
		var trainInt = examEmployeeOuts[i].trainStatus;
		var tarinStr;
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

//确认选中外部员工函数
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
		var unitId = $(employeeOutIdcode[i]).parents('tr').children(
		'td:last').find(".unitId").val();// 获取员工分配编号
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
		
		//将员工的单位ID和分配编号加到成绩表
		$("#outEmployeeDiv").append(
				'<input type="hidden" name="exam.employeeOutExams[' + i
				+ '].unitid" value="' + unitId + '"/>');
		//将员工的分配编号加到成绩表
		$("#outEmployeeDiv").append(
				'<input type="hidden" name="exam.employeeOutExams[' + i
				+ '].distributeid" value="' + distributeid + '"/>');
		//将员工的分配编号加到成绩表
		$("#outEmployeeDiv").append(
				'<input type="hidden" name="exam.employeeOutExams[' + i
				+ '].bigemployeeoutid" value="' + bigemployeeoutid + '"/>');
		
		
	}
	$("#innerEmployeeDiv").html("");// 清空内部员工文本框
	$("#outEmployeeModal").modal('hide');
}
/** *E 查询外部部门员工 */

/** *S 查询内部部门员工 */
var queryInnerEmployee = function() {
	var departments = $("#el_chooseDepart1").text();// 获取部门名字
	// 如果没有选择部门提醒选择部门，否则查询
	if (departments.length > 0) {
		// departments = departments.substring(0, departments.length - 1);
		departments = departmentIds.substring(0, departmentIds.length - 1);
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
	// alert(JSON.stringify(response));// 转换为JSON串输出
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
	// alert(JSON.stringify(data));//转换为JSON串输出
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

// 确认选中员工函数
var seleInnerEmployee = function() {
	if (confirm("确认选择这些员工?")) {
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
			var departName = $(employeeInnerIdcode[i]).parents('tr').children(
					'td').eq('4').html();// 获取部门名称
			var employeeInIdCode = $(employeeInnerIdcode[i]).parents('tr')
					.children('td').eq('3').html();// 获取员工身份证号(用于标记)
			var employeeInName = $(employeeInnerIdcode[i]).parents('tr')
					.children('td').eq('1').html();// 获取员工名称
			$("#" + departName + " .panel-body").append(
					'<span class="el_empList" id="' + employeeInIdCode + '">'
							+ employeeInName
							+ '<img class="el_empDelete" src="' + contextPath
							+ '/image/delete.png" /></span>');
			// 修改人数
			$("#" + departName + " .employeeNum").text(
					parseInt($("#" + departName + " .employeeNum").text()) + 1);
			/** *E 将选择的员工添加到选择员工** */
			var idCode = $(employeeInnerIdcode[i]).val();
			// alert(idCode)
			// 内部员工身份证号添加到form中
			$("#innerEmployeeDiv").append(
					'<input type="hidden" name="exam.employeeInExams[' + i
							+ '].employeeid" value="' + idCode + '"/>');
			// 将员工姓名加到表单中
			$("#innerEmployeeDiv").append(
					'<input type="hidden" name="exam.employeeInExams[' + i
							+ '].employeename" value="' + employeeInName
							+ '"/>');
			// 将员工的部门ID存到UnitId
			$("#innerEmployeeDiv").append(
					'<input type="hidden" name="exam.employeeInExams[' + i
							+ '].unitid" value="' + departmentId + '"/>');
		}
		$("#outEmployeeDiv").html("");// 清空外部员工文本框
		$('#innerEmployeeModal').modal('hide');// 隐藏模态框
	}
}
/** *E 查询内部部门员工 */

/** *S 确认修改试卷 */
var updateExam = function() {
	if (confirm("确认修改考试并退出?")) {
		$.validator.methods.compareDate = function(value, element, param) {
			var startDate = $("#inpstart0").val(); // 开始日期
			var d1 = new Date(startDate);
			var d2 = new Date(value);
			return d1 <= d2;
		};

		// 进行数据验证
		var isNotNull = $("#updateForm").validate({
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
				"exam.starttime" : "required",
				"exam.endtime" : {
					required : true,
					compareDate : "#inpend0"
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
				"exam.answertime" : {
					required : "考试时长不能为空",
					digits : "考试时长必须是整数",
					range : "考试时长必须在20到400分钟范围内"
				},
				"exam.address" : "考试地址不能为空",// 验证文本框的。前边是 name 属性
				"exam.starttime" : "开始时间不能为空",
				"exam.endtime" : {
					required : "结束时间不能为空",
					compareDate : "结束日期不能小于开始日期"
				},
				"exam.employeename" : {
					required : "创建人不能为空"
				},
				"employeeFlag" : "必须选择参考员工"
			}

		});

		// 如果验证通过可以提交数据
		if (isNotNull.form()) {
			$("#updateExamBtn").prop("disabled", true);
			$.ajax({
				url : contextPath + '/UpdateExam_update.action',
				data : $("#updateForm").serialize(),
				type : 'POST',
				dataType : 'json',
				async : true,
				success : function(response) { //
					alert(response.updateResult);
					if (response.updateResult == '修改成功!') {
						window.location.href = contextPath
								+ '/view/examParper/exam/examManage.jsp';
					}
				},
				error : function() {
					alert("修改考试失败！！！")
				}
			});
		}

	}
}
/** *E 保存试卷 */
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
/** *S 点击外来单位查询对应的大修部门且开启模态框 ****** */
function queryHaulUnit() {
	var bigId = $(":hidden[name='exam.bigid']").val();// 需要查询的大修ID
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
								"#" + unitName);// 获取外来单位框的
						// 未选中
						if ($(this).prop("checked") == false) {
							if ($divs.length == 1 && $el_chooseUnit.length == 1) {
								// 如果存在下面就从下面删除就删除
								$("#department_employee_out").children(
										"#" + unitName).remove();// 从添加员工删除
								$("#el_chooseUnit").children("#" + unitName)
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
												+ unitName + "'>" + unitName
												+ ',' + "</li>");

							}

						}

					});
	$("#unitModal").modal("hide");// 隐藏模态框
}
/** *E 选中部门的时候进行的处理**** */

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
/** ****S 初始化查询员工工种字典********* */
+function initEmployeeTypeDic() {
	$.post(contextPath + '/dic_getDicNamesByUpid.action', {
		"upId" : "100"
	}, showEmployeeTypeDic, 'json');
}();
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

/************获取服务器时间**********/
function getServerDate(){
	var nowTimeStr;
	$.ajax({
		url:"onlineExam_getNowServerTime.action?date="+Format(new Date(),"HH:mm:ss"),
		async:false,
		datatype:"json",
		success:function(data){			
			nowTimeStr = data.nowTime.replace(/T/g," ").replace(/-/g,"/");
		}
	})	
	return new Date(nowTimeStr);
	//return new Date();
}

function allSecectAndNotSelect(obj){
	$(".el_checks").prop("checked",$(obj).prop("checked"));
}









