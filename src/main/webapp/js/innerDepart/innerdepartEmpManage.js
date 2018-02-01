/**
 * Created by yorge on 2017/9/14.
 */

/**
 * 判断是否只显示管理员
 */
function isShowOnlyManager() {
	var isManager = $("#el_showManager").val();

	$("#el_showManagerInput").val(isManager);

	// 进行查询
	$("#currentPage").val("");// 清空页号
	InnerEmpQuery();
}

/**
 * 批量导入
 */
function extEmpTrain() {
	// 将数据打包发送到后台
	var idcode = $("#hiddenidcode").val();
	if (confirm("确定导出?")) {
		window.location.href = "/Exam/exportTrainDoc.action?idcode=" + idcode;
		// self.location ="/Exam/exportTrainDoc.action?idcode="+idcode;
	}

}

function keyLogin() {

	if (event.keyCode == 13) {
		// 按Enter键的键值为13

		InnerEmpQuery();
		return;
		// alert("点击回车键");
	}
}

/* 没选选择树，a 标签不执行跳转 */
function el_addDictinary() {
	if (clickRes == 0) {
		alert("请选择一个树！");
		$("#el_addUserA").attr('href', 'javascript:void(0)');
	} else {
		$("#el_addUserA").attr('href', 'addEmployee.jsp');
		// 获得的树的名字： getName

	}
}

/* <!-- 模态框 违章--> */
function el_breakRulesCase() {
	$('#el_breakRulesCase').modal();
}

/* 模态框 查看详细信息 */
function allInfo() {
	$('#allInfo').modal();
}

/* 删除 */
function delcfm() {
	$('#delcfmModel').modal();
}
function urlSubmit() {
	var url = $.trim($("#url").val());// 获取会话中的隐藏属性URL
	window.location.href = url;
}

/** *************************分页******************************* */
function fenye(total, pageSize, pageNumber) {
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
					InnerEmpQuery();
				}
			});
}

function fenye2(total, pageSize, pageNumber) {
	$('#paginationIDU2').pagination(
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
					$("#currentPage2").val(pageNumber);
					$("#currentCount2").val(b);
					EmpTrainDoc();
				}
			});
}
/**
 * 初始化
 */

$(function() {

	searchDepartmentTree_1();
	InnerEmpQuery();

});

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
				$("#currentPage").val("");// 给页面隐藏的queryUpUnitId赋值为点击的ID
				$("#queryDepartmentId").val(treeNode.departmentid);// 给页面隐藏的queryUpUnitId赋值为点击的ID
				$("#Myadddepartmentid").val(treeNode.departmentid);
				InnerEmpQuery();
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
function clearPage() {
	/**
	 * 清空管理人员,同时改变隐藏域的值
	 */

	/*
	 * $("#el_showManager option[value='true'] ").attr("selected",false);
	 * $("#el_showManager option[value='false'] ").attr("selected",true);
	 * 
	 * var isManager = $("#el_showManager").val();
	 * $("#el_showManagerInput").val(isManager);
	 */

	$("#currentPage").val("");// 清空页号
	InnerEmpQuery();
}

function InnerEmpQuery() {

	$
			.ajax({
				url : '/Exam/employeein_findEmployeeIn.action',
				data : $("#InnerEmpQueryForm").serialize(),
				type : 'POST',
				dataType : 'json',
				async : true,
				success : function(result) {

					var employeeIns = result.pageBean.productList;
					var departmentnames = result.departmentnameList;
					var trainstatus = result.trainstatusList;

					$("#t_body").html("");// 清空表体
					// 获取到这个表格
					var t_body = $("#t_body");
					// 循环添加每一行
					for (var i = 0; i < employeeIns.length; i++) {
						var str = "<tr><td><input type='radio' name='el_chooseDepart' class='el_checks'/></td>";
						var name;
						if (employeeIns[i].name != null) {
							name = employeeIns[i].name;
						} else {
							name = "无";
						}
						str += "<td>" + name + "</td>";

						var sex;
						if (employeeIns[i].sex != null) {
							if (employeeIns[i].sex == "1") {
								sex = "男";
							} else if (employeeIns[i].sex == "2") {
								sex = "女";
							}

						} else {
							sex = "无";
						}
						str += "<td>" + sex + "</td>";
						var birthday;
						if (employeeIns[i].birthday != null) {
							birthday = employeeIns[i].birthday;
						} else {
							birthday = "无";
						}
						str += "<td>" + birthday + "</td>";

						var phone;
						if (employeeIns[i].phone != null) {
							phone = employeeIns[i].phone
						} else {
							phone = "无";
						}
						str += "<td>" + phone + "</td>";
						var departmentname;
						if (departmentnames[i] != null) {
							departmentname = departmentnames[i];
						} else {
							departmentname = "无";
						}
						// str+="<td>"+employeeIns[i].departmentid+"</td>";
						str += "<td>" + departmentname + "</td>";
						// 职务
						str += "<td>" + employeeIns[i].duty + "</td>";
						var idcode;
						if (employeeIns[i].idcode != null) {
							idcode = employeeIns[i].idcode;
						} else {
							idcode = "无";
						}
						str += "<td>" + idcode + "</td>";

						str += "<td><a onclick='allInfo(this)' class='el_delButton'>详情</a>&nbsp;";
						// str+="<a
						// onclick='updateEmployeeIn(this)'>修改</a>&nbsp;"
						if (hasOperatingEmpin) {
							str += "<a href='/Exam/employeein_toUpdateEmployeeIn.action?method="
									+ employeeIns[i].employeeid
									+ "'>修改</a>&nbsp;";
						}
						str += "<input type='hidden' id='employeeid' value= '"
								+ employeeIns[i].employeeid + "'/>";

						str += "<input type='hidden' id='departmentid' value='"
								+ employeeIns[i].departmentid + "'/>";
						str += "<input type='hidden' id='employeephoto' value='"
								+ employeeIns[i].photo + "'/>";
						str += "<input type='hidden' id='employeebirthday' value='"
								+ employeeIns[i].birthday + "'/>";
						str += "<input type='hidden' id='zhiwu' value='"
								+ employeeIns[i].duty + "'/>";
						var finger;
						if (employeeIns[i].finger != null) {
							finger = employeeIns[i].finger;
						} else {
							finger = "无";
						}
						str += "<input type='hidden' id='employeefinger' value='"
								+ finger + "'/>";
						str += "<input type='hidden' id='employeeminusnum' value='"
								+ employeeIns[i].trainstatus + "'/>";
						if (hasOperatingEmpin) {
							str += "<a class='el_delButton' onclick='deleteEmployeeIn(this)' >删除</a>";
						}
						str += "</td></tr>";
						t_body.append(str);

					}

					// fenye(result.pageBean.totalCount,result.pageBean.currentCount,result.pageBean.currentPage);//分页显示

					// 分页显示
					fenye(result.pageBean.totalCount,
							result.pageBean.currentCount,
							result.pageBean.currentPage);

				}
			});

}

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

			$("#add_departmentName").val(result.departmentname);
		}
	});

}
/**
 * 职务下拉菜单
 */
function selectDuty() {

	$.ajax({
		url : '/Exam/employeein_getDuty.action',
		data : {
			"dictionaryid" : "300"
		},
		type : 'POST',
		dataType : 'json',
		async : true,
		success : function(result) {

			var dictionarys = result.dictionarys;
			// alert(dictionarys);

			$("#addEmployeeInDuty").empty();
			var duty = $("#addEmployeeInDuty");
			// duty.append("<option value=''>无</option>");
			// console.log("ceshi"+dictionarys)
			// alert(dictionarys.length);
			for (var i = 0; i < dictionarys.length; i++) {
				// alert(dictionarys[i].dictionaryid);

				if (dictionarys[i].dictionaryname == "无") {

					var str = "<option selected value='"
							+ dictionarys[i].dictionaryid + "'>"
							+ dictionarys[i].dictionaryname + "</option>";
				} else {
					var str = "<option value='" + dictionarys[i].dictionaryid
							+ "'>" + dictionarys[i].dictionaryname
							+ "</option>";

				}

				// var str = "<option>nihao</option>";
				// alert("nihao");

				duty.append(str);
			}

		}
	});
}

/**
 * 添加员工
 */

function el_addEmployeeIn() {
	if ($("#queryDepartmentId").val() == "") {
		alert("请选择员工所在部门！");
	} else {
		$("#addEmployeeOutInfoList").empty();

		initAddModel();

		// 清空提交表单中的信息
		$("#form_addEmployeeOutInfo").empty();

		// 将数组的信息初始化
		idCardArrays.splice(1, idCardArrays.length - 1);
		$("#message3").hide();
		var queryDepartmentId = $("#queryDepartmentId").val();

		getDepartmentName(queryDepartmentId)
		selectDuty();
		$('#el_addEmployeeIn').modal();

	}
}

// 员工序号
var employeeOutSeq = 0;
// 存放所有添加的身份证号
var idCardArrays = [ '123' ];
// 点击添加员工的模态框中的添加按钮执行的操作
function addEmployeeInInfo() {

	var added = false;
	var idCard = $("#certNumber").val();
	// 判断员工信息是否已经录入
	if (idCard != "") {

		/**
		 * 判断该员工的年龄是否达到要求 出生日期年龄范围是 18<= age <= 55
		 */
		var date = new Date();// 获得今天的时间
		var birthday = $("#birthday").val();
		var startDate = new Date(birthday);
		var newDate = date.getTime() - startDate.getTime();
		var age = Math.ceil(newDate / 1000 / 60 / 60 / 24 / 365);// 获得年龄
		var aged = true;
		if (age < 18) {
			alert("该员工年龄小于18岁！");
			aged = false;
		}
		if (age > 55) {
			alert("该员工年龄大于55岁！");
			aged = false;
		}

		/**
		 * 如果年龄符合，即aged为true ，则执行下边内容。
		 */
		//if (aged) {

			// 判断该身份证是否已经添加
			for (var i = 0; i < idCardArrays.length; i++) {
				if (idCardArrays[i] == idCard) {
					added = true;
				}
			}

			if (added) {
				alert("该员工已经添加到表格中！")
			} else {

				var name = $("#personName").val();
				var sex = $("#gender").val();
				var birthday = $("#birthday").val();
				var address = $("#address").val();

				var phone = $("#addEmployeeInPhone").val();
				// var duty = $("#addEmployeeInDuty").val();

				var options = $("#addEmployeeInDuty option:selected");

				var add_departmentName = $("#add_departmentName").val();

				var departmentid = $("#queryDepartmentId").val();

				var idCardImageStr = $("#idCardImageStr").val();

				// 判断该员工是否进入黑名单
				$
						.ajax({
							url : "/Exam/employeein_isBlackList.action",
							data : {
								"myIdcode" : idCard
							},
							dataType : "json",
							type : "post",
							success : function(data) {

								if (data.flag == "0") {

									$
											.ajax({
												url : '/Exam/employeein_isIdCode.action',
												data : {
													"myIdcode" : idCard
												},
												type : 'POST',
												dataType : 'json',
												async : true,
												success : function(result) {

													if (result.flag) {
														// 若尚未添加将员工信息保存到数组中
														idCardArrays
																.push(idCard);
														var showAddEmployeeInInfo = "<tr><td>"
																+ name
																+ "</td><td>"
																+ sex
																+ "</td><td>"
																+ idCard
																+ "</td><td>"
																+ options
																		.text()
																+ "</td><td>"
																+ phone
																+ "</td><td>"
																+ add_departmentName
																+ "</td><td>"
																+ "<input type='hidden' class='address' value='"
																+ address
																+ "'/>"
																+ "<input type='hidden' class='idCardImageStr' value='"
																+ idCardImageStr
																+ "'/>"
																+ "<input type='hidden' class='departmentid' value='"
																+ departmentid
																+ "'/>"
																+ "<input type='hidden' class='birthday' value='"
																+ birthday
																+ "'/>"
																+ "<a class='el_delButton' onClick='deleteAddInfo(this)'>删除</a><br/>"
																+ "</td></tr>";

														$(
																"#addEmployeeOutInfoList")
																.append(
																		showAddEmployeeInInfo);

														$
																.ajax({
																	url : "/Exam/employeein_saveEmployeePhoto.action",
																	data : {
																		"employeeInIdCard" : idCard,
																		"photoStr" : idCardImageStr
																	},
																	type : "post"
																});

													} else {
														idCardArrays.splice(i,
																1);
														alert("该员工已经添加，不能重复添加");
													}
												}
											});
								} else if (data.flag == "2") {

									alert("该员工的违章积分已经累计达到12分,不能添加");
								} else if (data.flag == "1") {
									alert("该员工已进入永久黑名单，不能添加");
								}

							}
						});

				// 将联系方式文本框清空
				$("#addEmployeeInPhone").val('');
				$("#message3").hide();
				selectDuty();

			}
		//}
	} else {
		alert("请录入员工信息");
	}

}
// 初始化添加员工的模态框
function initAddModel() {
	$("#personName").val('');
	$("#gender").val('');
	$("#birthday").val('');
	$("#certNumber").val('');
	$("#address").val('');
	$("#message3").hide();
	// $("#add_employeeOutType option:selected").removeAttr("selected");

	$("#id_img_pers").prop("src", basePathUrl + "/image/userImage.png")
	$("#idCardImageStr").val('');

	$("#addEmployeeInPhone").val('');
	$("#addEmployeeInDuty").empty();
	var duty = $("#addEmployeeInDuty");
	// $("select:option").remove();

}

// 点击添加员工的模态框的删除按钮执行的操作
function deleteAddInfo(obj) {
	var deleteIdCard = $(obj).parents("tr").children("td").eq(2).text();

	console.log(idCardArrays)
	// 将身份证号从数组中移除
	for (var i = 0; i < idCardArrays.length; i++) {
		if (idCardArrays[i] == deleteIdCard) {
			idCardArrays.splice(i, 1);
		}
	}
	console.log("sahnchuhou", idCardArrays)
	// 移除该行
	$(obj).parents("tr").remove();

}

// 点击添加员工的模态框的保存按钮执行的操作
function saveEmployeeAndHaulInfo() {

	for (var i = 0; i < $("#addEmployeeOutInfoList").children("tr").length; i++) {

		var name = $("#addEmployeeOutInfoList").children("tr").eq(i).children(
				"td").eq(0).text();
		var idCard = $("#addEmployeeOutInfoList").children("tr").eq(i)
				.children("td").eq(2).text();
		var sex = $("#addEmployeeOutInfoList").children("tr").eq(i).children(
				"td").eq(1).text();
		// var address
		// =$("#addEmployeeOutInfoList").children("tr").eq(i).children("td").eq(1).text();
		// var birthday
		// =$("#addEmployeeOutInfoList").children("tr").eq(i).children("td").eq(1).text();
		var phone = $("#addEmployeeOutInfoList").children("tr").eq(i).children(
				"td").eq(4).text();
		var duty = $("#addEmployeeOutInfoList").children("tr").eq(i).children(
				"td").eq(3).text();

		var address = $("#addEmployeeOutInfoList").children("tr").eq(i).find(
				".address").val();
		var idCardImageStr = $("#addEmployeeOutInfoList").children("tr").eq(i)
				.find(".idCardImageStr").val();
		var departmentid = $("#addEmployeeOutInfoList").children("tr").eq(i)
				.find(".departmentid").val();
		var birthday = $("#addEmployeeOutInfoList").children("tr").eq(i).find(
				".birthday").val();

		// 先进行转换
		sex = sex.toString().replace("男", "1").replace("女", "2");

		var add_employeeOutInfo = "<input name='employeeInList[" + i
				+ "].name' type='hidden' value='" + name + "'/>"
				+ "<input name='employeeInList[" + i
				+ "].idcode' type='hidden' value='" + idCard + "'/>"
				+ "<input name='employeeInList[" + i
				+ "].sex' type='hidden' value='" + sex + "'/>"
				+ "<input name='employeeInList[" + i
				+ "].address' type='hidden' value='" + address + "'/>"
				+ "<input name='employeeInList[" + i
				+ "].birthday' type='hidden' value='" + birthday + "'/>"
				+ "<input name='employeeInList[" + i
				+ "].phone' type='hidden' value='" + phone + "'/>"
				+ "<input name='employeeInList[" + i
				+ "].duty' type='hidden' value='" + duty + "'/>"
				+ "<input name='employeeInList[" + i
				+ "].departmentid' type='hidden' value='" + departmentid
				+ "'/>";
		$("#form_addEmployeeOutInfo").append(add_employeeOutInfo);

	}

	var departmentId = $("#add_departmentId").val();
	var idCard = $("#certNumber").val();

	var idCardImageStr = $("#idCardImageStr").val();

	var $trs = $("#addEmployeeOutInfoList").children("tr");
	if ($trs.length > 0) {

		/*
		 * $.ajax({ url:"/Exam/employeein_saveEmployeePhoto.action",
		 * data:{"employeeInIdCard":idCard,"photoStr":idCardImageStr},
		 * type:"post" });
		 */

		$.ajax({
			url : "/Exam/employeein_addEmployeeInBatch.action",
			data : $("#form_addEmployeeOutInfo").serialize(),
			dataType : "json",
			type : "post",
			success : function(result) {
				alert(result.flag);
				$("#el_addEmployeeIn").modal("hide");
				// 调用查询员工信息的方法
				InnerEmpQuery();

			}
		});
	} else {
		alert("请将要添加的员工信息添加到表格中！")
	}

}

/** *************************************************************************************************** */
/**
 * 员工详细信息
 */

function allInfo(obj) {
	// 获取到这一行的所有内容的对象
	$tds = $(obj).parents('tr').children('td');
	// 分别将这些数据放到修改模态框中
	var employeephoto = $(obj).parents("tr").find("#employeephoto").val();
	// var employeebirthday =
	// $(obj).parents("tr").find("#employeebirthday").val();
	var employeefinger = $(obj).parents("tr").find("#employeefinger").val();
	var employeephoto = $(obj).parents("tr").find("#employeephoto").val();
	var zhiwu = $(obj).parents("tr").find("#zhiwu").val();

	// 图片
	// $("[href='default.htm']")contextPath+

	$("#myimg").attr("src", "/files/EmployeeIn/" + employeephoto);

	$("#InfoName").html($tds.eq(1).html());
	$("#InfoSex").html($tds.eq(2).html());
	$("#InfoBirthday").html($tds.eq(3).html());
	$("#InfoPhone").html($tds.eq(4).html());
	$("#InfoIdcode").html($tds.eq(7).html());
	// $("#InfoTrainstatus").html($tds.eq(7).html());
	$("#InfoDepartmentid").html($tds.eq(5).html());
	// $("#InfoFinger").html(employeefinger);
	$("#Infozhiwu").html(zhiwu);

	var employeeid = $(obj).parents("tr").find("#employeeid").val();
	$("#InfoEmployeeInForm").append(
			"<input type='hidden' id='employeeid' value= '" + employeeid
					+ "' name='employeeIn.employeeid'/>");
	$('#allInfo').modal();
}

/**
 * 员工培训档案
 */

function el_empTrainDoc() {

	if ($("input[name='el_chooseDepart']:checked").length == "0") {
		alert("请选择一名员工！");

	} else {

		$tds = $("input[name='el_chooseDepart']:checked").parents('tr').find(
				'td');
		// 分别将这些数据放到修改模态框中
		/*
		 * var employeeminusnum =
		 * $("input[name='el_chooseDepart']:checked").parents('tr').find("#employeeminusnum").val();
		 */
		// 员工信息
		$("#TrainName").html($tds.eq(1).html());
		$("#TrainSex").html($tds.eq(2).html());

		$("#TrainPhone").html($tds.eq(4).html());
		// 显示头像

		var employeephoto = $("input[name='el_chooseDepart']:checked").parents(
				"tr").find("#employeephoto").val();
		$("#myimg2").attr("src", "/files/EmployeeIn/" + employeephoto);
		/* $("#TrainblackkList").html("黑名单"); */

		$("#TrainUnit").html($tds.eq(5).html());
		/* $("#TrainMinusnum").html(employeeminusnum); */
		$("#el_empTrainDoc").modal();
		var employeeid = $("input[name='el_chooseDepart']:checked").parents(
				"tr").find("#employeeid").val();
		// alert(employeeid);

		// var idcode =
		// $("input[name='el_chooseDepart']:checked").parents("tr").find("#employeeid").val();
		// 身份证

		var idcode = $tds.eq(7).html()

		$("#hiddenidcode").val(idcode);
		EmpTrainDoc();

	}

}

function EmpTrainDoc() {

	// var idcode=$("#hiddenidcode").val();
	// 培训信息
	$.ajax({
		url : '/Exam/employeein_getEmpTrainDoc.action',

		data : $("#hiddenidcodeForm").serialize(),
		type : 'POST',
		dataType : 'json',
		async : true,
		success : function(result) {

			var myexams = result.pageBean.productList;
			$("#empTrainDoc_body").html("");// 清空表体
			// 获取到这个表格
			var empTrainDoc_body = $("#empTrainDoc_body");

			for (var j = 0; j < myexams.length; j++) {

				var examName;
				if (myexams[j].examName != null) {
					examName = myexams[j].examName;
				} else {
					examName = "无";
				}
				var str = "<tr><td>" + examName + "</td>";
				var examLevel;
				if (myexams[j].examLevel != null) {
					if (myexams[j].examLevel == 1) {
						examLevel = "一级考试";
					} else if (myexams[j].examLevel == 2) {
						examLevel = "二级考试";
					} else if (myexams[j].examLevel == 3) {
						examLevel = "三级考试";
					}

				} else {
					examLevel = "无";
				}
				str += "<td>" + examLevel + "</td>";
				str += "<td>" + myexams[j].traincontent + "</td>";
				str += "<td>" + myexams[j].xueshi + "</td>";
				var startTime;
				if (myexams[j].startTime != null) {
					startTime = myexams[j].startTime;
				} else {
					startTime = "无";
				}
				var endTime;
				if (myexams[j].endTime != null) {
					endTime = myexams[j].endTime;
				} else {
					endTime = "无";
				}

				str += "<td>"
						+ Format(new Date(startTime.replace(/T/g, " ").replace(
								/-/g, "/")), "yyyy-MM-dd HH:ss")
						+ "到"
						+ Format(new Date(endTime.replace(/T/g, " ").replace(
								/-/g, "/")), "yyyy-MM-dd HH:ss") + "</td>";

				var paperScore;
				if (myexams[j].paperScore != null) {
					paperScore = myexams[j].paperScore;
				} else {
					paperScore = "无";
				}
				str += "<td>" + paperScore + "</td>";
				var grade;
				if (myexams[j].grade != null) {
					grade = myexams[j].grade;
				} else {
					grade = "无";
				}
				str += "<td>" + grade + "</td></tr>";

				empTrainDoc_body.append(str);
			}

			fenye2(result.pageBean.totalCount, result.pageBean.currentCount,
					result.pageBean.currentPage);// 分页显示
		}
	});
}

/**
 * 删除员工
 */
/*
 * 点击删除按钮
 */

function deleteEmployeeIn(obj) {

	// 获取单位编号
	var employeeid = $(obj).parents("tr").find("#employeeid").val();

	$("#url").val(employeeid);

	// 打开删除模态框
	$('#delcfmModel').modal();
}
/*
 * 点击删除模态框的确定按钮实现删除事件
 */
function urlSubmit() {
	// 获取单位id
	var employeeId = $("#url").val();

	$.ajax({
		url : '/Exam/employeein_deleteEmployeeIn.action',
		data : {
			"employeeId" : employeeId
		},
		type : 'POST',
		dataType : 'json',
		async : true,
		success : function(result) {
			alert(result.message);
			// 关闭模态框
			$('#delcfmModel').modal('hide');
			// 删除成功后刷新页面
			window.location.reload();
		}
	});

}
