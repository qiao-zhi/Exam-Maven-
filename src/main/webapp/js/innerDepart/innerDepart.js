/**
 * Created by yorge on 2017/9/14.
 */

/** *************************分页******************************* */
function fenye(total, pageSize, pageNumber) {
	// alert(total+" "+pageSize)
	$('#paginationIDU').pagination(
			{
				// 组件属性
				"total" : total,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : pageSize,// 数字 每一页显示的数量 10
				"pageNumber" : pageNumber,// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8, 15, 20 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, b) {
					$("#currentPage").val(pageNumber);
					$("#currentCount").val(b);
					query();
				}
			});
}

function fenye2(total, pageSize, pageNumber) {
	// alert(total+" "+pageSize)
	$('#paginationIDU2').pagination(
			{
				// 组件属性
				"total" : total,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : pageSize,// 数字 每一页显示的数量 10
				"pageNumber" : pageNumber,// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, b) {
					// alert("pageNumber=" + pageNumber);
					// alert("pageSize=" + b);
					$("#currentPage3").val(pageNumber);
					$("#currentCount3").val(b);
					getEmpCase();
				}
			});
}

function fenye3(total, pageSize, pageNumber) {
	// alert(total+" "+pageSize)
	$('#paginationIDU3').pagination(
			{
				// 组件属性
				"total" : total,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : pageSize,// 数字 每一页显示的数量 10
				"pageNumber" : pageNumber,// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, b) {
					$("#currentPage2").val(pageNumber);
					$("#currentCount2").val(b);
					getBreakrulesCase();
				}
			});
}
/*
 * 页面初始化
 */

$(function() {
	searchDepartmentTree_1();

	query();
});
/*
 * 查询
 */

function keyLogin() {
	if (event.keyCode == 13) {
		// 按Enter键的键值为13
		query();
		return;
		// alert("点击回车键");
	}
}
/** *********************请求树信息********************* */

function searchDepartmentTree_1() {
	$.ajax({
		type : "post",
		dataType : "json",
		url : "/Exam/department_getDepartmentTree.action",
		success : getTree_1,
		error : function() {
			alert("请求树失败！");
		}
	});
}

/** *********************生成树信息********************* */
function getTree_1(result) {
	var treeList = result.treeList;
	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "departmentid",
				pIdKey : "updepartmentid",
				rootPId : null,
			},
			key : {
				name : "departmentname",
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode) {
				$("#queryUpDepartmentId").val(treeNode.departmentid);// 给页面隐藏的queryUpUnitId赋值为点击的ID
				$("#currentPage").val("");
				var departmentid = treeNode.departmentid;
				getDepartmentName(departmentid)
				query();

			}
		}
	};
	var zNodes = treeList;
	// 添加 树节点的 点击事件；
	var log, className = "dark";
	function onClick(event, treeId, treeNode, clickFlag) {
		clickOnTree(event, treeId, treeNode, clickFlag);
	}
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	treeObj.expandAll(false);
}

/**
 * 条件查询
 */
function clearPageNum() {
	$("#currentPage").val("1");
	query();
}
function query() {

	$.ajax({
		url : '/Exam/department_findDepartment.action',
		data : $("#queryForm").serialize(),
		type : 'POST',
		dataType : 'json',
		async : true,
		success : successList,

	});

}

var successList = function List(result) {

	var departments = result.pageBean.productList;
	var departmentnames = result.updepartmentname;
	var employeeInCounts = result.employeeInCounts;
	var departmentMinus = result.departmentMinus;

	$("#tbody").html("");// 清空表体
	// 获取到这个表格
	var t_body = $("#tbody");
	// 循环添加每一行
	for (var i = 0; i < departments.length; i++) {
		var departmentType_1 = departments[i].departmenttype == "0" ? "内部单位"
				: "长期外来单位"
		str = '<tr><td>' + departments[i].departmentname + '</td><td>'
				+ departmentType_1 + '</td><td>' + departments[i].upDepartName
				+ '</td><td>' + departments[i].employeeName + '</td><td>'
				+ departments[i].phone
				+ '</td><td><a title="点击查看部门员工信息" onclick="openEmpCaseModal(this)">'
				+ departments[i].perNum + '</a></td><td>'
				+ '<a title="点击查看部门违章信息" onclick="openBreakrulesModal(this)">'
				+ departments[i].totalMinus + '</a></td><td>'
				+ departments[i].jiaquan + "</td><td>";
		// 有删除修改权限就显示连接
		if (hasOperatingDepart) {
			str += '<a onclick="updateDepartment(this)" class="el_delButton" title="修改部门信息"><span class="glyphicon glyphicon-pencil"></span></a>&nbsp;';
		} else {
			str += "-";
		}

		str += "<input type='hidden' id='count' value='"
				+ departments[i].perNum + "' />";
		str += "<input type='hidden' id='departmentid' class='depart_id' value= '"
				+ departments[i].departmentid
				+ "' name='department.departmentid'/>"

		if (departments[i].departprojectnames == null) {
			departments[i].departprojectnames = "";
		}

		str += "<input type='hidden' id='departprojectnames' value='"
				+ departments[i].departprojectnames + "'/>";
		str += "<input type='hidden' class='up_Id' value='"
				+ departments[i].updepartmentId + "'>";
		str += "<input type='hidden' id='departmenttype' value='"
				+ departments[i].departmenttype + "'/>";
		// 有删除修改权限就显示连接
		if (hasOperatingDepart) {
			str += "<a class='el_delButton' onclick='deleteDepartment(this)' title='删除部门'><span class='glyphicon glyphicon-trash'></span></a>";
		} else {
			str += "-";
		}
		str += "</td></tr>";
		t_body.append(str);

	}

	fenye(result.pageBean.totalCount, result.pageBean.currentCount,
			result.pageBean.currentPage);// 分页显示

}

/**
 * 打开违章信息的模态框
 */
function openBreakrulesModal(obj) {
	$("#currentPage3").val("1");
	var departmentid = $(obj).parents("tr").find("#departmentid").val();
	$("#breakrulesbumen").val(departmentid);
	getBreakrulesCase();
	$("#el_BreakrulesCase").modal();
}

/**
 * 显示违章信息
 */
function getBreakrulesCase() {
	// 向隐藏的时间文本框赋值
	$("#q_starttime").val($(".query_dep_starttime").val());
	$("#q_endtime").val($(".query_dep_endtime").val());
	$.ajax({
		url : '/Exam/department_getBreakrulesCase.action',
		data : $("#BreakrulesCaseForm").serialize(),
		type : 'POST',
		dataType : 'json',
		async : true,
		success : function(result) {
			var myBreakrulesCases = result.pageBean.productList;

			$("#BreakrulesCase_tbody").html("");// 清空表体
			// alert(myBreakrulesCases.length);
			// 获取到这个表格
			var EmpCase_tbody = $("#BreakrulesCase_tbody");
			for (var i = 0; i < myBreakrulesCases.length; i++) {
				var BreakrulesName;

				if (myBreakrulesCases[i].name != null) {
					BreakrulesName = myBreakrulesCases[i].name;
				} else {
					BreakrulesName = "无";
				}
				var str = "<tr><td>" + BreakrulesName + "</td>";
				var Breakrulescontent;

				if (myBreakrulesCases[i].empInBreakContent != null) {
					Breakrulescontent = myBreakrulesCases[i].empInBreakContent;
				} else {
					Breakrulescontent = "无";
				}

				str += "<td>" + Breakrulescontent + "</td>";
				var Breakrulestime;

				if (myBreakrulesCases[i].empInBreakTime != null) {
					Breakrulestime = myBreakrulesCases[i].empInBreakTime;
				} else {
					Breakrulestime = "无";
				}
				str += "<td>"
						+ Format(new Date(Breakrulestime.replace(/T/g, " ")
								.replace(/-/g, "/")), "yyyy-MM-dd") + "</td>";
				var Breakrulesminus;

				if (myBreakrulesCases[i].empInMinusNum != null) {
					Breakrulesminus = myBreakrulesCases[i].empInMinusNum;
				} else {
					Breakrulesminus = "无";
				}

				str += "<td>" + Breakrulesminus + "</td></tr>";

				EmpCase_tbody.append(str);
			}
			fenye3(result.pageBean.totalCount, result.pageBean.currentCount,
					result.pageBean.currentPage);// 分页显示
		}
	});
}
/**
 * 打开员工信息模态框
 */
function openEmpCaseModal(obj) {
	$("#currentPage3").val("1");
	var departmentid = $(obj).parents("tr").find("#departmentid").val();
	$("#empbumen").val(departmentid);

	getEmpCase();
	$("#el_EmpCase").modal();
}

/**
 * 显示员工信息
 */
function getEmpCase() {

	$.ajax({
		url : '/Exam/employeein_getEmpCase.action',
		data : $("#EmpCaseForm").serialize(),
		type : 'POST',
		dataType : 'json',
		async : true,
		success : function(result) {

			var myEmpCases = result.pageBean.productList;

			$("#EmpCase_tbody").html("");// 清空表体
			// 获取到这个表格
			var EmpCase_tbody = $("#EmpCase_tbody");

			for (var i = 0; i < myEmpCases.length; i++) {

				var EmpName;

				if (myEmpCases[i].name != null) {
					EmpName = myEmpCases[i].name;
				} else {
					EmpName = "无";
				}

				var str = "<tr><td>" + EmpName + "</td>";
				var EmpSex;
				if (myEmpCases[i].sex != null) {
					if (myEmpCases[i].sex == "1") {
						EmpSex = "男";
					} else if (myEmpCases[i].sex == "2") {
						EmpSex = "女";
					}
					// EmpSex = myEmpCases[i].sex;

				} else {
					EmpSex = "无";
				}

				str += "<td>" + EmpSex + "</td>";
				var EmpDuty;
				if (myEmpCases[i].duty != null) {
					EmpDuty = myEmpCases[i].duty;
				} else {
					EmpDuty = "无";
				}
				str += "<td>" + EmpDuty + "</td>";
				var EmpPhone;
				if (myEmpCases[i].phone != null) {
					EmpPhone = myEmpCases[i].phone;
				} else {
					EmpPhone = "无";
				}
				str += "<td>" + EmpPhone + "</td></tr>";

				/*
				 * var EmpTrain; if(myEmpCases[i].trainStatus!=null){
				 * if(myEmpCases[i].trainStatus=="0"){ EmpTrain="未通过"; }else
				 * if(myEmpCases[i].trainStatus=="1"){ EmpTrain="通过一级考试"; }else
				 * if(myEmpCases[i].trainStatus=="2"){ EmpTrain="通过二级考试"; }else
				 * if(myEmpCases[i].trainStatus=="3"){ EmpTrain="通过三级考试"; }
				 * 
				 * //EmpTrain = myEmpCases[i].trainStatus; }else{ EmpTrain =
				 * "无"; } str+="<td>"+EmpTrain+"</td>";
				 */

				EmpCase_tbody.append(str);
			}
			fenye2(result.pageBean.totalCount, result.pageBean.currentCount,
					result.pageBean.currentPage);// 分页显示
		}
	});

}

/**
 * 添加单位
 */
function getDepartmentName(departmentid) {

	$.ajax({
		url : '/Exam/department_getDepartmentName.action',
		data : {
			"departmentid" : departmentid
		},
		type : 'POST',
		dataType : 'json',
		async : true,
		success : function(result) {
			// 将获取到的name赋值到这个标签中

			$("#Addupdepartmentid").val(result.departmentname);
		}
	});

}

function el_addDepartment() {
	if ($("#queryUpDepartmentId").val() == "") {
		alert("请选择一个上级部门！");
	} else {
		// 打开之前先清空文本框
		$("#AddDepartmentName").val("");
		$("#AddEmployeename").val("");
		$("#AddPhone").val("");
		$("#UpdateDepartprojectnames").val("");
		// 上级部门
		$("#yincangbumenid").val($("#queryUpDepartmentId").val());

		// 如果上级部门是外来单位，默认选择外来单位
		var yincangbumenid = $("#yincangbumenid").val();
		/*
		 * var
		 * val=$("input:radio[name='department.departmenttype'][value='0']:checked").val();
		 * alert(val);
		 */
		$.ajax({
			url : '/Exam/employeein_isNeibu.action',
			data : {
				"yincangbumenid" : yincangbumenid
			},
			type : 'POST',
			dataType : 'json',
			async : true,
			success : function(result) {
				// 得到的true说明上级部门就是内部部门
				if (result.flag == true) {
					$("#first").find("input[type='radio']:eq(0)").prop(
							"checked", true);
					$("#first").find("input[type='radio']:eq(0)").prop(
							"disabled", false);
					// 隐藏工程文本框
					$("#projectnamediv").hide();

				} else {
					$("#first").find("input[type='radio']:eq(1)").prop(
							"checked", "true");
					$("#projectnamediv").show();
					// 清空工程文本框
					$("#AddDepartprojectnames").val('');
					$("#first").find("input[type='radio']:eq(0)").prop(
							"disabled", true);
				}
				$('#myModal').modal();
			}
		});

		// 隐藏工程文本框
		$("#projectnamediv").hide();
		// 清空工程文本框
		$("#AddDepartprojectnames").val('');
		$('#myModal').modal();
	}

}

function saveDepartmentButton() {

	// 手机号码验证
	jQuery.validator
			.addMethod(
					"isMobile",
					function(value, element) {
						var length = value.length;
						var mobile = /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\(\d{3}\))|(\d{3}\-))?(1[358]\d{9})$)/;
						var tel = /^\d{3,4}-?\d{7,9}$/;
						var tel2 = /^\d{7,9}$/;
						return this.optional(element) || (tel.test(value))
								|| (mobile.test(value)) || (tel2.test(value));

					}, "请正确填写您的联系电话");

	var isNotNull = $("#addDepartmentForm").validate({
		rules : {
			"department.departmentname" : {
				required : true

			},
			"department.employeename" : {
				required : true
			},
			"department.phone" : {
				required : true,
				"isMobile" : true
			},
			"department.updepartmentid" : "required"
		},
		messages : {
			"department.departmentname" : {
				required : "请输入部门名称"
			},

			"department.employeename" : {
				required : "请输入负责人"
			},
			"department.phone" : {
				required : "请输入您的联系方式",
				"isMobile" : "请输入正确的联系电话"
			},
			"department.updepartmentid" : {
				required : "不能为空"
			}
		}

	});

	if (isNotNull.form()) {
		$.ajax({
			url : '/Exam/department_addDepartment.action',
			data : $("#addDepartmentForm").serialize(),
			type : 'POST',
			dataType : 'json',
			async : true,
			success : function(result) {
				// 弹出是否录入成功
				alert(result.message);
				$('#myModal').modal('hide');
				// 全局刷新当前页面
				window.location.reload();

			}
		});
	}

}

/**
 * 修改单位
 */

function updateDepartment(obj) {
	// 先将历史记录清空
	$("#two").find("input[type='radio']:eq(0)").prop("disabled", false);
	// 获取到这一行的所有内容的对象
	$tds = $(obj).parents('tr').children('td');
	// 分别将这些数据放到修改模态框中
	$("#updateDepartmentName").val($tds.eq(0).html());
	// $("#updateManager").val($tds.eq(1).html());
	$("#updateEmployeename").val($tds.eq(3).html());
	$("#updatePhone").val($tds.eq(4).html());
	$("#update_updepartmentid").val($tds.eq(8).find(".up_Id").val())
	$("#updateUpdepartmentid").val($tds.eq(2).html());

	// 显示工程文本框
	var departprojectnames = $(obj).parents("tr").find("#departprojectnames")
			.val();
	$("#UpdateDepartprojectnames").val(departprojectnames);

	var departmenttype = $(obj).parents("tr").find("#departmenttype").val();
	// var departmenttype = $(obj).parents("tr").find("#departmenttype").val();

	// 显示按钮
	if (departmenttype == "1") {

		// $("input[name='department.departmenttype'][value='1']").attr("checked","checked");
		$("#two").find("input[type='radio']:eq(1)").prop("checked", "true");

		$("#projectnamediv2").show();

	} else if (departmenttype == "0") {

		// $("input[name='department.departmenttype'][value='0']").attr("checked","checked");
		$("#two").find("input[type='radio']:eq(0)").prop("checked", "true");

		$("#projectnamediv2").hide();

	}

	var up_departmentId = $tds.eq(8).find(".depart_id").val();
	$("#update_departmentid").val(up_departmentId)
	// var departmentId = $(obj).parents("tr").find("#departmentid").val();

	bumenshu2();
	// 根据上级部门ID判断上级部门是否是内部部门
	$.ajax({
		url : "employeein_isNeibu.action",
		data : {
			"yincangbumenid" : $tds.eq(8).find(".up_Id").val()
		},
		type : "post",
		dataType : "json",
		success : function(data) {
			if (!data.flag) {
				$("#two").find("input[type='radio']:eq(0)").prop("disabled",
						"true");
			}
			// 弹出模态框
			$('#myModal2').modal();
		}
	})

}

// 增加的全局变量
// 是否是内部部门标记
var departmentIsIn = true;
// 是否移动的下级部门标记
var departmentIdEqual = true;
function saveUpdateButton() {
	var val = $("#two").find("input:radio[value='0']:checked").val();
	if (val == '0') {
		$("#UpdateDepartprojectnames").val('');
	}

	/*
	 * //姓名验证 jQuery.validator.addMethod("isName", function(value, element) {
	 * return this.optional(element) || /^[\u4E00-\u9FA5A-Za-z]+$/.test(value); },
	 * "只能包括中文和英文");
	 */
	// 手机号码验证
	jQuery.validator
			.addMethod(
					"isMobile",
					function(value, element) {
						var length = value.length;
						var mobile = /(^(0[0-9]{2,3}\-)?([2-9][0-9]86,7})+(\-[0-9]{1,4})?$)|(^((\(\d{3}\))|(\d{3}\-))?(1[358]\d{9})$)/;
						var tel = /^\d{3,4}-?\d{7,9}$/;
						var tel2 = /^\d{7,9}$/;
						return this.optional(element) || (tel.test(value))
								|| (mobile.test(value)) || (tel2.test(value));
					}, "请正确填写您的联系电话");

	var isNotNull = $("#updateDepartmentForm").validate({
		rules : {
			"department.departmentname" : {
				required : true
			},
			"department.employeename" : {
				required : true
			},
			"department.phone" : {
				required : true,
				"isMobile" : true
			},
			"department.updepartmentid" : "required"
		},
		messages : {
			"department.departmentname" : {
				required : "请输入部门名称 "
			},

			"department.employeename" : {
				required : "请输入负责人"
			},
			"department.phone" : {
				required : "请输入联系方式",
				"isMobile" : "请正确填写您的联系方式"
			},
			"department.updepartmentid" : {
				required : "不能为空"
			}

		}

	});

	if (isNotNull.form() && departmentIdEqual && departmentIsIn) {
		$.ajax({
			url : '/Exam/department_updateDepartment.action',
			data : $("#updateDepartmentForm").serialize(),
			type : 'POST',
			dataType : 'json',
			async : true,
			success : function(result) {
				// 弹出是否修改成功
				alert(result.message);
				$('#myModal2').modal('hide');
				// 刷新当前页面
				window.location.reload();
			}
		});

	}

}
/*
 * 确认按钮
 */
function confirmButton(count) {
	if (confirm('该部门下面还有' + count + '名员工，确定删除吗?')) {
		return true;
	}
	return false;
}

function deleteDepartment(obj) {
	$tds = $(obj).parents('tr').children('td');
	// 获取单位编号
	var departmentId = $(obj).parents("tr").find("#departmentid").val();
	var count = $(obj).parents("tr").find("#count").val();
	// var count = $tds.eq(2).html()

	$("#url").val(departmentId);

	if (count == 0) {

		$('#delcfmModel').modal();
	} else {

		if (confirmButton(count)) {
			// $('#delcfmModel').modal();
			urlSubmit();
		}
	}

	// 打开删除模态框

}

/*
 * 点击删除模态框的确定按钮实现删除事件
 */
function urlSubmit() {
	// 获取单位id
	var departmentid = $("#url").val();

	$.ajax({
		url : '/Exam/department_deleteDepartment.action',
		data : {
			"departmentid" : departmentid
		},
		type : 'POST',
		dataType : 'json',
		async : true,
		success : function(result) {
			alert(result.message);
			// 关闭模态框
			$('#delcfmModel').modal('hide');
			if (result.message == "该部门还有下级部门，不能删除") {
				return;
			}
			// query();
			window.location.reload();
		}
	});

}

// 树文本框点击事件
function treeTextClick() {
	// $("#treeDemo11").toggle();
	if ($("#treeDemo11").is(":hidden")) {
		$("#treeDemo11").show();
	} else {
		$("#treeDemo11").hide();
	}
}

// 修改上级部门的部门树
function bumenshu2() {
	if ($("#treeDemo11").is(":hidden")) {
		// 如果树显示。则关闭树。
		$("#treeDemo11").hide();
	}

	searchDepartmentTree_4();

	var hiV = $("#updateUpdepartmentid").val();
	if ($("#el_chooseUpdateDepart > li").length == 0) {

		$("#el_chooseUpdateDepart").append("<li class='dark'>" + hiV + "</li>");

	} else {
		$("#el_chooseUpdateDepart").empty();
		$("#el_chooseUpdateDepart").append("<li class='dark'>" + hiV + "</li>");
	}

	/*
	 * if ($("#el_chooseUpdateDepart > li").length > 0) {//先清空
	 * $("#el_chooseUpdateDepart").children("li").remove(); }
	 */

}

/** *********************请求树信息********************* */
// 0
function searchDepartmentTree_4() {
	$.ajax({
		type : "post",
		dataType : "json",
		url : "/Exam/department_getDepartmentTree.action",
		success : getTree_4,
		error : function() {
			alert("请求树失败！");
		}
	});
}

/** *********************生成树信息**********************0 */
function getTree_4(result) {
	var treeList = result.treeList;
	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "departmentid",
				pIdKey : "updepartmentid",
				rootPId : null,
			},
			key : {
				name : "departmentname",
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode) {
				// leilong s
				// 获取当前部门的id，与选中的部门ID进行判断
				var departmentId_ll = $("#update_departmentid").val();
				var upDepartmentId_ll = treeNode.departmentid;
				if (upDepartmentId_ll.length >= departmentId_ll.length) {
					if (upDepartmentId_ll.substring(0, departmentId_ll.length) == departmentId_ll) {
						departmentIdEqual = false;
						alert("不能将部门移动到自己的下级部门中！");
					} else {
						departmentIdEqual = true;
					}
				} else {
					departmentIdEqual = true;
				}
				if (departmentIdEqual) {

					$.ajax({
						url : "employeein_isNeibu.action",
						data : {
							"yincangbumenid" : departmentId_ll
						},
						type : "post",
						async : false,
						dataType : "json",
						success : function(data) {
							if (data.flag) { // 外部部门
								$.ajax({
									url : "employeein_isNeibu.action",
									data : {
										"yincangbumenid" : upDepartmentId_ll
									},
									type : "post",
									async : false,
									dataType : "json",
									success : function(result) {
										if (!result.flag) {
											departmentIsIn = false;
											alert("不能将内部部门移动到外来长期单位中！");
										} else {
											departmentIsIn = true;
										}

									}
								})
							}
						}
					})
				}
				// leilong e

				/*
				 * $("#update_updepartmentid").val(treeNode.departmentid);
				 * $("#updateUpdepartmentid").val(treeNode.departmentname); var
				 * hiV = $("#updateUpdepartmentid").val();
				 */
				if ($("#el_chooseUpdateDepart > li").length > 0) {// 先清空
					$("#el_chooseUpdateDepart").children("li").remove();
				}
				if (departmentIdEqual && departmentIsIn) {
					$("#update_updepartmentid").val(treeNode.departmentid);
					$("#updateUpdepartmentid").val(treeNode.departmentname);
					var hiV = $("#updateUpdepartmentid").val();
					// 插入值
					if ($("#el_chooseUpdateDepart > li").length == 0) {
						$("#el_chooseUpdateDepart").append(
								"<li class='" + className + "'>" + hiV
										+ "</li>");
					}
				}
				/* 判断是否插入进入，若插入进入，关闭树框 */
				if ($("#el_chooseUpdateDepart").children("li").length > 0) {

					$("#treeDemo11").hide();
				}
			}
		}
	};
	var zNodes = treeList;
	// 添加 树节点的 点击事件；
	var log, className = "dark";
	function onClick(event, treeId, treeNode, clickFlag) {
		clickOnTree(event, treeId, treeNode, clickFlag);
	}
	$.fn.zTree.init($("#treeDemo11"), setting, zNodes);
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo11");
	treeObj.expandAll(false);

}

// 修改模态框右上角关闭符号当点击模态框中的取消和关闭按钮时进行的操作
function closeModal_symbol() {
	// 是否是内部部门标记
	departmentIsIn = true;
	// 是否移动的下级部门标记
	departmentIdEqual = true;
}



/**
 * 内部部门查询按类型
 */
function queryDepartIn(){
	$("[name='departType']").val($("#el_departType option:selected").val());
//	清空页号然后进行查询
	clearPageNum();
}

function el_departmentCount(){
	window.location.href="innerdepartCount.jsp";
}




