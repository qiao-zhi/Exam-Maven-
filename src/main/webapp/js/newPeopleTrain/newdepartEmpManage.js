/** *************************页面加载函数************************** */

$(function() {
	initEmployeeTypeDic();
	findEmployeeOutBaseInfo();
});

/** ************************组合条件查询********************************** */
// 初始化工种信息
function initEmployeeTypeDic() {
	$.post(basePathUrl + '/dic_getDicNamesByUpid.action', {
		"upId" : "100"
	}, showEmployeeTypeDic, 'json');
}
function showEmployeeTypeDic(response) {
	if (response != null && response.names != null) {
		var names = response.names;// 获取字段返回的值
		$("#query_empType").append("<option value=''>--请选择--</option>")
		for (var i = 0; i < names.length; i++) {
			$("#query_empType").append(
					'<option value="' + names[i] + '">' + names[i]
							+ '</option>')
		}
	}
}

// 点击查询按钮执行的操作
function queryEmployeeOutBaseInfo() {
	// 执行查询操作前先将隐藏域中的值清空当前页清空或改为第一页
	$("#currentPage").val("");
	// 调用查询的方法
	findEmployeeOutBaseInfo();
}

function findEmployeeOutBaseInfo() {
	$.ajax({
		url : "employeeOutPerson_getEmployeeOutBaseInfoList.action",
		data : $("#form_findEmployeeOutBaseInfo").serialize(),
		type : "post",
		dataType : "json",
		success : showEmployeeBaseInfo,
		error : function() {
			alert("请求失败！")
		}
	})
}
// 显示员工的基本信息
function showEmployeeBaseInfo(data) {
	var employeeOutBaseInfoList = data.pageBean.productList;
	var showEmployeeOutBaseInfoList = '';
	for (var i = 0; i < employeeOutBaseInfoList.length; i++) {
		var index = i + 1;
		showEmployeeOutBaseInfoList += "<tr><td><input type='radio' name='el_chooseEmp' class='el_checks' vaule='"
				+ employeeOutBaseInfoList[i].idcard
				+ "'/></td><td>"
				+ (index + (data.pageBean.currentPage - 1) * 8)
				+ "<input class='find_employeeOutBirthday' type='hidden' value='"
				+ employeeOutBaseInfoList[i].birthday
				+ "'/>"
				+ "<input class='find_employeeOutPhoto' type='hidden' value='"
				+ employeeOutBaseInfoList[i].photo
				+ "'/>"
				+ "<input class='find_employeeOutId' type='hidden' value='"
				+ employeeOutBaseInfoList[i].employeeid
				+ "'/>"
				+ "<input class='find_bigEmployeeOutId' type='hidden' value='"
				+ employeeOutBaseInfoList[i].bigemployeeoutid
				+ "'/>"
				+ "<input class='find_bigId' type='hidden' value='"
				+ employeeOutBaseInfoList[i].bigid
				+ "'/>"
				+ "</td><td>"
				+ employeeOutBaseInfoList[i].name
				+ "</td><td>"
				+ (employeeOutBaseInfoList[i].sex > 1 ? '女' : '男')
				+ "</td><td>"
				+ employeeOutBaseInfoList[i].idcard
				+ "</td><td>"
				+ employeeOutBaseInfoList[i].departmentname
				+ "</td><td>"
				+ employeeOutBaseInfoList[i].emptype
				+ "</td><td onclick='el_breakRulesCase(this)' class='success'>"
				+ employeeOutBaseInfoList[i].minusnum + "</td>";
		if (employeeOutBaseInfoList[i].isblacklist == '是') {
			showEmployeeOutBaseInfoList += "<td style='color:red;'>"
					+ employeeOutBaseInfoList[i].isblacklist + "</td><td>";
		} else if (employeeOutBaseInfoList[i].isinblacklist == '是') {
			showEmployeeOutBaseInfoList += "<td style='color:blue;'>"
					+ employeeOutBaseInfoList[i].isblacklist + "</td><td>";
		} else {
			showEmployeeOutBaseInfoList += "<td>"
					+ employeeOutBaseInfoList[i].isblacklist + "</td><td>";
		}
		/*showEmployeeOutBaseInfoList += employeeOutBaseInfoList[i].trainstatus
				.toString().replace("0", "未参加培训").replace("1", "通过一级考试")
				.replace("2", "通过二级考试").replace("3", "通过三级考试")
				+ "</td><td>"
				+ "<a href='javascript:void(0)' onclick='allInfo(this)'>详细信息</a>&nbsp;";*/
		//将培训情况字段内容注释掉
		showEmployeeOutBaseInfoList +="<a href='javascript:void(0)' title='查看详情' onclick='allInfo(this)'><span class='glyphicon glyphicon-search'></span></a>&nbsp;";
		if (employeeOutBaseInfoList[i].bigstatus != "已结束" && hasOperatingEmpout) {
			showEmployeeOutBaseInfoList += "<a href='javascript:void(0)' title='修改员工信息' onclick='el_modifyEmp(this)'><span class='glyphicon glyphicon-pencil'></span></a>&nbsp;"
					+ "<a class='el_delButton' title='删除员工' onClick='delcfm(this)'><span class='glyphicon glyphicon-trash'></span></a><br />"
					+ "</td></tr>";
		} else {
			showEmployeeOutBaseInfoList += "</td></tr>";
		}
	}
	$("#employeeOutBaseInfoList").empty();
	$("#employeeOutBaseInfoList").append(showEmployeeOutBaseInfoList);

	// 当前页
	var currentPage = data.pageBean.currentPage;
	// 总条数
	var totalCount = data.pageBean.totalCount;
	// 页大小
	var currentCount = data.pageBean.currentCount;
	// 调用分页函数
	outdepartEmpManage_page(currentPage, totalCount, currentCount);
}

/** *******************************分页*************************************** */
// 员工基本信息的分页函数
function outdepartEmpManage_page(currentPage, totalCount, currentCount) {
	$('#paginationIDU').pagination(
			{
				// 组件属性
				"total" : totalCount,// 数字 当分页建立时设置记录的总数量
				"pageSize" : currentCount,// 数字 每一页显示的数量 10
				"pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8, 15, 20 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, b) {
					// alert("pageNumber=" + pageNumber);
					// alert("pageSize=" + b);
					// 向隐藏域中设置值
					$("#currentPage").val(pageNumber);
					$("#currentCount").val(b);
					// 调用查找函数
					findEmployeeOutBaseInfo();
				}
			});

}

// 培训档案的分页函数
function trainStatus_page(currentPage, totalCount, employeeOutIdCard) {
	$('#paginationID2').pagination(
			{
				// 组件属性
				"total" : totalCount,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : 8,// 数字 每一页显示的数量 10
				"pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, b) {
					// 设置当前页，当前页显示条数
					showEmployeeOutExamsInfoList(employeeOutIdCard, pageNumber,
							b);
				}
			});
}

/** *********************点击查询表单的清空按钮执行的操作********************* */
function clearQueryInfo() {
	$(".curSelectedNode").removeClass("curSelectedNode");
	// 清空隐藏域中的大修和部门信息
	$("#query_bigId").val('');
	$("#query_unitId").val('');
}

/** ***********************************添加员工的相关操作*********************************** */
// 初始化工种信息 在打开模态框之前初始化
function initEmployeeTypeDic_add() {
	$.post(basePathUrl + '/dic_getDicNamesByUpid.action', {
		"upId" : "100"
	}, showEmployeeTypeDic_add, 'json');
}
function showEmployeeTypeDic_add(response) {
	$("#add_employeeOutType").empty();
	if (response != null && response.names != null) {
		var names = response.names;// 获取字段返回的值
		$("#add_employeeOutType").append("<option value='0'>--请选择--</option>")
		for (var i = 0; i < names.length; i++) {
			$("#add_employeeOutType").append(
					'<option value="' + names[i] + '">' + names[i]
							+ '</option>')
		}
	}
}
// 点击页面的添加员工执行的操作
function el_addEmp() {
	if (clickRes == 0) {
		alert("请选择要添加员工的部门！");
	} else {
		$.post(basePathUrl + '/haul_getHaulinfoByHaulid.action', {
			'haulId' : selectedOverHaulId
		}, showAddunitModal, 'json');
	}
}
// 根据查询的结果判断大修是否已经节结束
function showAddunitModal(response) {
	if (response != null && response.haulinfo != null) {
		var haulinfo = response.haulinfo;
		if (haulinfo.bigstatus == '已结束') {
			alert("该大修已经结束，您不可以操作该大修下的部门！");
			return;
		}
		// 清空表格中的信息
		$("#addEmployeeOutInfoList").empty();
		initAddModel();
		// 清空提交表单中的信息
		$("#form_addEmployeeOutInfo").empty();
		// 将数组的信息初始化
		idCardArrays.splice(1, idCardArrays.length - 1);
		// 初始化工种信息
		initEmployeeTypeDic_add();
		$("#el_addEmp").modal();
	}
}

// 员工序号
var employeeOutSeq = 0;
// 存放所有添加的身份证号
var idCardArrays = [ '123' ];
//点击添加员工的模态框中的添加按钮执行的操作
function addEmployeeOutInfo() {
	var added = false;
	var employeeOutTypeValue = $("#add_employeeOutType option:selected").val();
	var idCard = $("#certNumber").val();
	
	/**
	 * 判断该员工的年龄是否达到要求 出生日期年龄范围是 18<= age <= 55
	 */
	var date = new Date();// 获得今天的时间
	var birthday = $("#birthday").val();//获取出生日期
	var startDate = new Date(birthday);
	var newDate = date.getTime() - startDate.getTime();
	var age = Math.ceil(newDate / 1000 / 60 / 60 / 24 / 365);// 获得年龄
	var aged = true;
	if (age < 18) {
		alert("年龄不到，不能入场！");
		aged = false;
	}
	if (age > 55) {
		alert("年龄超龄，不能入场！");
		aged = false;
	}
	
	/**
	 *	如果年龄符合，即aged为true ，则执行下边内容。
	 */
	if(aged){
		// 判断该身份证是否已经添加
		for (var i = 0; i < idCardArrays.length; i++) {
			if (idCardArrays[i] == idCard) {
				added = true;
			}
		}

		if (employeeOutTypeValue == 0) {
			alert("请为该员工选择一个工种！")
		} else if (added) {
			alert("该员工已经添加到表格中！")
		} else if (idCard.length <= 0) {
			alert("请点击读取身份证信息将员工身份证信息读出！")
		} else {
			// 若尚未添加将员工信息保存到数组中
			// idCardArrays.push(idCard);
			var name = $("#personName").val();
			var sex = $("#gender").val();
			var birthday = $("#birthday").val();
			var address = $("#address").val();
			var departmentId = $("#add_departmentId").val();
			var bigId = $("#add_bigId").val();
			var employeeOutType = $("#add_employeeOutType option:selected").text();
			var idCardImageStr = $("#idCardImageStr").val();
			$
					.ajax({
						url : "employeeOutPerson_checkAddEmployeeOutStatuss.action",
						data : {
							"employeeOutIdCard" : idCard,
							"bigId" : bigId,
							"unitId":departmentId
						},
						dataType : "json",
						type : "post",
						success : function(data) {
							// 若该员工没进入黑名单执行的操作
							if (data.status == 3) {
								alert("该员工已进入黑名单，不能添加！")
							} else if (data.status == 4) {
								alert("该员工已经添加到这次大修的该单位中，不能添加！")
							} else if (data.status == 5) {
								alert("该员工违章已经累计超过12分，积分周期内不能添加！")
							} else {
								// 若尚未添加将员工信息保存到数组中
								idCardArrays.push(idCard);
								// 将员工信息添加到表格中
								var showAddEmployeeOutInfo = "<tr><td>"
										+ employeeOutType
										+ "</td><td>"
										+ name
										+ "</td><td>"
										+ sex
										+ "</td><td>"
										+ idCard
										+ "</td><td>"
										+ "<a class='el_delButton' onClick='deleteAddInfo(this)'>删除</a><br/>"
										+ "</td></tr>";
								$("#addEmployeeOutInfoList").append(
										showAddEmployeeOutInfo);

								if (data.status == 1) {
									// 将图片信息保存到文件中
									$
											.ajax({
												url : "employeeOutPerson_saveEmployeePhoto.action",
												data : {
													"employeeOutIdCard" : idCard,
													"photoStr" : idCardImageStr
												},
												type : "post"
											})
									sex = sex.toString().replace("男", "1").replace(
											"女", "2");
									// 将没有来过的员工信息拼接到表单中
									var add_employeeOutInfo = "<input name='employeeOutList["
											+ employeeOutSeq
											+ "].name' type='hidden' value='"
											+ name
											+ "'/>"
											+ "<input name='employeeOutList["
											+ employeeOutSeq
											+ "].idcode' type='hidden' value='"
											+ idCard
											+ "'/>"
											+ "<input name='employeeOutList["
											+ employeeOutSeq
											+ "].sex' type='hidden' value='"
											+ sex
											+ "'/>"
											+ "<input name='employeeOutList["
											+ employeeOutSeq
											+ "].address' type='hidden' value='"
											+ address
											+ "'/>"
											+ "<input name='employeeOutList["
											+ employeeOutSeq
											+ "].birthday' type='hidden' value='"
											+ birthday + "'/>";
									$("#form_addEmployeeOutInfo").append(
											add_employeeOutInfo);
									employeeOutSeq = employeeOutSeq + 1;
								}
							}
							$("#add_employeeOutType option:selected").removeAttr(
									"selected");
						}
					});
		}
	}
}

// 点击添加员工的模态框的删除按钮执行的操作
function deleteAddInfo(obj) {
	var deleteIdCard = $(obj).parents("tr").children("td").eq(3).text();
	console.log(idCardArrays)
	// 将身份证号从数组中移除
	for (var i = 0; i < idCardArrays.length; i++) {
		if (idCardArrays[i] == deleteIdCard) {
			idCardArrays.splice(i, 1);
		}
	}
	console.log("sahnchuhou", idCardArrays)

	$(obj).parents("tr").remove();

}
// 初始化添加员工的模态框
function initAddModel() {
	$("#personName").val('');
	$("#gender").val('');
	$("#birthday").val('');
	$("#certNumber").val('');
	$("#address").val('');
	$("#add_employeeOutType option:selected").removeAttr("selected");
	$("#id_img_pers").prop("src", basePathUrl + "/image/userImage.png")
	$("#idCardImageStr").val('');
}

// 点击添加员工的模态框的保存按钮执行的操作
function saveEmployeeAndHaulInfo() {
	var departmentId = $("#add_departmentId").val();
	var bigId = $("#add_bigId").val();
	var $trs = $("#addEmployeeOutInfoList").children("tr");
	if ($trs.length > 0) {
		for (var i = 0; i < $trs.length; i++) {
			var $tds = $trs.eq(i).children("td");
			var empType = $tds.eq(0).text();
			var idCard = $tds.eq(3).text();
			var haulEmployeeOutInfoList = "<input name='haulEmployeeOutList["
					+ i + "].unitid' type='hidden' value='" + departmentId
					+ "'/>" + "<input name='haulEmployeeOutList[" + i
					+ "].bigid' type='hidden' value='" + bigId + "'/>"
					+ "<input name='haulEmployeeOutList[" + i
					+ "].empoutidcard' type='hidden' value='" + idCard + "'/>"
					+ "<input name='haulEmployeeOutList[" + i
					+ "].emptype' type='hidden' value='" + empType + "'/>";
			// 添加到form表单中
			$("#form_addEmployeeOutInfo").append(haulEmployeeOutInfoList);
		}
		$.ajax({
			url : "employeeOutPerson_addEmployeeOutBatch.action",
			data : $("#form_addEmployeeOutInfo").serialize(),
			dataType : "json",
			type : "post",
			success : function(data) {
				alert(data.result);
				$("#el_addEmp").modal("hide");
				// 调用查询员工信息的方法
				queryEmployeeOutBaseInfo();
			}
		});
	} else {
		alert("请将要添加的员工信息添加到表格中！")
	}
}

/** ***********************************修改员工的相关操作*********************************** */
// 初始化工种信息
function initEmployeeTypeDic_update(empType) {
	$.post(basePathUrl + '/dic_getDicNamesByUpid.action', {
		"upId" : "100"
	}, function(response) {
		if (response != null && response.names != null) {
			var names = response.names;// 获取字段返回的值
			$("#update_employeeOutType").empty();
			for (var i = 0; i < names.length; i++) {
				if (names[i] == empType) {
					$("#update_employeeOutType").append(
							'<option value="' + names[i] + '" selected>'
									+ names[i] + '</option>')
				} else {
					$("#update_employeeOutType").append(
							'<option value="' + names[i] + '">' + names[i]
									+ '</option>')
				}
			}
		}
	}, 'json');
}

function el_modifyEmp(obj) {
	var $tds = $(obj).parents("tr").children("td");
	$("#update_employeeOutName").val($tds.eq(2).text());
	$("#update_employeeOutIdCard").val($tds.eq(4).text());
	$("#update_departmentName").val($tds.eq(5).text());
	$("#update_employeeOutSex").val($tds.eq(3).text());
	var sex = $tds.eq(3).text();
	if (sex == "男") {
		$("#update_employeeOutSex1").prop("checked", true);
	} else {
		$("#update_employeeOutSex2").prop("checked", true);
	}
	// 获取隐藏域中的值
	var employeeOutBirthday = $(obj).parents("tr").find(
			".find_employeeOutBirthday").val();
	var employeeOutPhoto = $(obj).parents("tr").find(".find_employeeOutPhoto")
			.val();
	var bigId = $(obj).parents("tr").find(".find_bigId").val();
	$("#update_employeeOutBirthday").val(
			Format(new Date(employeeOutBirthday.replace(/T/g, " ").replace(
					/-/g, "/")), "yyyy-MM-dd"));
	$("#update_employeeOutPhoto").prop("src", employeeOutPhoto);
	$("#update_employeeOutType").val($tds.eq(6).text());
	initEmployeeTypeDic_update($tds.eq(6).text());
	// 向修改表单的隐藏域中设置值
	$("#updateAndDelete_employeeOutIdCard").val($tds.eq(4).text());
	$("#updateAndDelete_bigId").val(bigId);
	//大修员工ID
	var bigEmployeeOutId = $(obj).parents("tr").find(".find_bigEmployeeOutId").val();
	$("#updateAndDelete_bigemployeeOutId").val(bigEmployeeOutId);
	$("#el_modifyEmp").modal();
}

// 点击修改模态框的保存按钮执行的操作
function updateEmployeeOutInfo() {
	var empType = $("#update_employeeOutType option:selected").text()
	$("#updateAndDelete_employeeType").val(empType);
	$.ajax({
		url : "employeeOutPerson_updateHaulEmployeeOutInfo.action",
		data : $("#form_updateAndDelete").serialize(),
		type : "post",
		dataType : "json",
		success : function(data) {
			alert(data.result)
			// 调用查询方法
			findEmployeeOutBaseInfo();
			$("#el_modifyEmp").modal('hide');
		}
	})
}

/** ***********************************生成工作证的相关操作*********************************** */

/*// 选中一次大修或部门，点击生成工作证按钮执行的操作
function el_empCardModel() {
	if (clickBigAndDepTree == 0) {
		alert("请选择一次大修！");
	} else {
		$
				.ajax({
					url : "employeeOutPerson_getEmpInfoforCertificate.action",
					data : {
						"unitId" : selectedDepartmentID,
						"bigId" : selectedOverHaulId
					},
					type : "post",
					dataType : "json",
					success : function(data) {
						var employeeOutInfoList = data.employeeOutInfoList;
						var showEmployeeOutInfoList = "";
						$("#empInfoListForCertificate").empty();
						for (var i = 0; i < employeeOutInfoList.length; i++) {
							showEmployeeOutInfoList = "<tr><td><input type='checkbox' class='el_checks3' value='"
									+ employeeOutInfoList[i].idcard
									+ "' /></td><td>"
									+ employeeOutInfoList[i].departmentname
									+ "</td><td>"
									+ employeeOutInfoList[i].name
									+ "</td><td>"
									+ (employeeOutInfoList[i].sex > 1 ? '女'
											: '男')
									+ "</td><td>"
									+ employeeOutInfoList[i].emptype
									+ "</td></tr>";
							$("#empInfoListForCertificate").append(
									showEmployeeOutInfoList);
						}
						// 将全选按钮的值清空
						$("#el_checkQuanxuan3").prop("checked", false);
						$('#el_empCardModel').modal();
					}
				});

	}

}

// 生成工作证模态框中全选按钮的操作
$(function() {
	$("#el_checkQuanxuan3").change(function() {
		if ($(this).prop("checked") == true) {
			$(".el_checks3").prop("checked", "true");
		} else {
			$(".el_checks3").removeAttr("checked");
		}
	})
})

// 点击导出工作证信息执行的操作
function exportEmployeeOutInfo() {
	var checkBoxValue = "";
	var test = $(".el_checks3:checked");
	test.each(function() {
		checkBoxValue += $(this).val() + ",";
	})
	checkBoxValue = checkBoxValue.substring(0, checkBoxValue.length - 1);
	if (checkBoxValue.length > 0) {
		window.location.href = "exportEmpInfo_findEmployeeOutInfosToExport.action?unitId="
				+ selectedDepartmentID
				+ "&bigId="
				+ selectedOverHaulId
				+ "&employeeOutIds=" + checkBoxValue;
		$('#el_empCardModel').modal('hide');
	} else {
		alert("请选择要需要生成工作证的员工！");
	}
}
*/
/** *********************外来单位的员工的培训档案********************* */
function el_empTrainDoc() {
	if ($(".el_checks:checked").length == "0") {
		alert("请选择要查看的员工！");
	} else {

		var $tds = $(".el_checks:checked").parents("tr").children("td");
		var showEmployeeOutTrainBaseInfo = "<tr><td>" + $tds.eq(2).text()
				+ "</td><td>"
				+ $tds.eq(3).text() + "</td><td>" + $tds.eq(5).text()
				+ "</td><td>" + $tds.eq(7).text() + "</td>" +
				"<td>" + $tds.eq(8).text() + "</td></tr>";
		var employeeOutIdCard = $tds.eq(4).text()
		$("#employeeOutTrainBaseInfo").empty();
		$("#employeeOutTrainBaseInfo").append(showEmployeeOutTrainBaseInfo);
		// 分页显示员工的培训档案
		showEmployeeOutExamsInfoList(employeeOutIdCard, 1, 8);
	}
}

// 员工培训档案分页显示
function showEmployeeOutExamsInfoList(employeeOutIdCard, currentPage,
		totalCount) {
	$.ajax({
		url : "employeeOutPerson_getExamsInfoByEmployeeOutIdCardLimit.action",
		data : {
			"employeeOutIdCard" : employeeOutIdCard,
			"currentPage" : currentPage,
			"currentCount" : totalCount
		},
		dataType : "json",
		type : "post",
		success : function(data) {
			var examInfoList = data.pageBean.productList;
			var showExamInfoList = "";
			$("#employeeOutExamInfos").empty();
			for (var i = 0; i < examInfoList.length; i++) {
				var index = i + 1;
				showExamInfoList = "<tr><td>"
						+ (index + (data.pageBean.currentPage - 1) * 5)
						+ "</td><td>"
						+ examInfoList[i].bigName
						+ "</td><td>"
						+ examInfoList[i].employeeOutType
						+ "</td><td>"
						+ examInfoList[i].examName
						+ "</td><td>"
						+ examInfoList[i].level.toString().replace("1", "厂级")
								.replace("2", "部门级").replace("3", "班组级")
						+ "</td><td>"
						+ Format(new Date(examInfoList[i].startTime.replace(
								/T/g, " ").replace(/-/g, "/")),
								"yyyy-MM-dd HH:mm")
						+ "到"
						+ Format(new Date(examInfoList[i].endTime.replace(/T/g,
								" ").replace(/-/g, "/")), "yyyy-MM-dd HH:mm")
						+ "</td><td>" + examInfoList[i].paperScore
						+ "</td><td>" + examInfoList[i].grade + "</td><td>"
						+ examInfoList[i].isPass + "</td></tr>";
				$("#employeeOutExamInfos").append(showExamInfoList);
			}
			// 当前页
			var currentPage = data.pageBean.currentPage;
			// 总条数
			var totalCount = data.pageBean.totalCount;
			// 调用分页函数
			trainStatus_page(currentPage, totalCount, employeeOutIdCard);

			$('#el_empTrainDoc').modal();
		}
	});
}

// 员工培训档案显示不分页
function showEmployeeOutExamsInfoAllList(employeeOutIdCard) {
	$.ajax({
		url : "employeeOutPerson_getExamsInfoByEmployeeOutIdCard.action",
		data : {
			"employeeOutIdCard" : employeeOutIdCard
		},
		dataType : "json",
		type : "post",
		success : function(data) {
			var examInfoList = data.examInfos;
			var showExamInfoList = "";
			$("#employeeOutExamInfos").empty();
			for (var i = 0; i < examInfoList.length; i++) {
				showExamInfoList = "<tr><td>"
						+ examInfoList[i].bigName
						+ "</td><td>"
						+ examInfoList[i].employeeOutType
						+ "</td><td>"
						+ examInfoList[i].examName
						+ "</td><td>"
						+ examInfoList[i].level.toString().replace("1", "厂级")
								.replace("2", "部门级").replace("3", "班组级")
						+ "</td><td>"
						+ Format(new Date(examInfoList[i].startTime.replace(
								/T/g, " ").replace(/-/g, "/")),
								"yyyy-MM-dd HH:mm")
						+ "到"
						+ Format(new Date(examInfoList[i].endTime.replace(/T/g,
								" ").replace(/-/g, "/")), "yyyy-MM-dd HH:mm")
						+ "</td><td>" + examInfoList[i].paperScore
						+ "</td><td>" + examInfoList[i].grade + "</td><td>"
						+ examInfoList[i].isPass + "</td></tr>";
				$("#employeeOutExamInfos").append(showExamInfoList);
			}
			$('#el_empTrainDoc').modal();
		}
	});
}

/** ***********************************删除员工执行的操作*********************************** */

function delcfm(obj) {
	var $tds = $(obj).parents("tr").children("td");
	// 向修改表单的隐藏域中设置值
	$("#updateAndDelete_employeeOutIdCard").val($tds.eq(4).text());
	var bigId = $(obj).parents("tr").find(".find_bigId").val();
	$("#updateAndDelete_bigId").val(bigId);
	//大修员工ID
	var bigEmployeeOutId = $(obj).parents("tr").find(".find_bigEmployeeOutId").val();
	$("#updateAndDelete_bigemployeeOutId").val(bigEmployeeOutId);
	$('#delcfmModel').modal();
}
// 点击删除模态框的确定按钮执行的操作
function urlSubmit() {
	$.ajax({
		url : "employeeOutPerson_deleteHaulEmployeeOutInfo.action",
		data : $("#form_updateAndDelete").serialize(),
		dataType : "json",
		type : "post",
		success : function(data) {
			alert(data.result)
			// 调用查询方法
			findEmployeeOutBaseInfo();
			$('#delcfmModel').modal('hide');
		},
		error : function() {
			alert("请求失败！")
		}
	})
}

/** *********************外来单位的员工详细信息********************* */
function allInfo(obj) {
	var $tds = $(obj).parents("tr").children("td");
	$("#details_employeeOutName").text($tds.eq(2).text());
	$("#details_employeeOutSex").text($tds.eq(3).text());
	$("#details_employeeOutIdCard").text($tds.eq(4).text());
	$("#details_employeeOutIsBlack").text($tds.eq(8).text());
	//$("#details_employeeOutTrainStatus").text($tds.eq(9).text());
	$("#details_departmentName").text($tds.eq(5).text());
	$("#details_employeeType").text($tds.eq(6).text());
	// 获取隐藏域中的值
	var employeeOutBirthday = $(obj).parents("tr").find(
			".find_employeeOutBirthday").val();
	var employeeOutPhoto = $(obj).parents("tr").find(".find_employeeOutPhoto")
			.val();
	$("#details_employeeOutBirthday").text(
			Format(new Date(employeeOutBirthday.replace(/T/g, " ").replace(
					/-/g, "/")), "yyyy-MM-dd"));
	$("#details_employeeOutPhoto").prop("src", employeeOutPhoto);
	$('#allInfo').modal();
}

/** *********************违章情况的详细信息********************* */

function el_breakRulesCase(obj) {
	var $tds = $(obj).parents("tr").children("td");
	var showEmployeeOutBreakRulesBaseInfo = "<tr><td>" + $tds.eq(2).text()
			+ "</td><td>" + $tds.eq(3).text()
			+ "</td><td>" + $tds.eq(5).text() + "</td><td>" + $tds.eq(7).text() + "</td><td>" + $tds.eq(8).text()
			+ "</td></tr>";
	$("#employeeOutBreakRulesBaseInfo").empty();
	$("#employeeOutBreakRulesBaseInfo").append(
			showEmployeeOutBreakRulesBaseInfo);
	var employeeOutId = $(obj).parents("tr").find(".find_employeeOutId").val();
	var bigEmployeeOutId = $(obj).parents("tr").find(".find_bigEmployeeOutId")
			.val();
	$.ajax({
		url : "employeeOutPerson_getBreakRulesByCondition.action",
		data : {
			"employeeOutId" : employeeOutId,
			"bigEmployeeOutId" : bigEmployeeOutId
		},
		dataType : "json",
		type : "post",
		success : function(data) {
			var breakRulesList = data.breakRules;
			var showBreakRulesList = "";
			$("#employeeOutBreakRuleList").empty();
			for (var i = 0; i < breakRulesList.length; i++) {
				var index = i + 1;
				showBreakRulesList = "<tr><td>"
						+ index
						+ "</td><td>"
						+ Format(new Date(breakRulesList[i].breaktime.replace(
								/T/g, " ").replace(/-/g, "/")),
								"yyyy-MM-dd HH:mm") + "</td><td>"
						+ breakRulesList[i].minusnum + "</td><td>"
						+ breakRulesList[i].breakcontent + "</td></tr>";
				$("#employeeOutBreakRuleList").append(showBreakRulesList);
			}
			$('#el_breakRulesCase').modal();
		}
	})

}

/****************************************树的相关方法**************************************************************** */
$(function() {
	searchDepartmentAndOverHualTree();
})

/***********************请求树信息**********************/

function searchDepartmentAndOverHualTree() {
	$.ajax({
		type : "post",
		target : "#departmentAndOverHaulTree",
		dataType : "json",
		data:{"markTrainType":"0"},
		url : "employeeOutPerson_getDepartmentAndOverHaulTree.action",
		success : getTree_1,
		error : function() {
			alert("请求树失败！");
		}
	});
}

/** *********************生成树信息********************* */
function getTree_1(treeList2) {
	var treeList3 = treeList2.departmentAndOverHaulTree;
	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "upid",
				rootPId : null,
			},
			key : {
				name : "name",
			}
		},
		callback : {
			onClick : onClick
		}
	};
	var zNodes = treeList3;
	// 添加 树节点的 点击事件；
	var log, className = "dark";
	$.fn.zTree.init($("#departmentAndOverHaulTree"), setting, zNodes);
	var treeObj = $.fn.zTree.getZTreeObj("departmentAndOverHaulTree");
	treeObj.expandAll(false);
}

var clickRes = 0;
var clickBigAndDepTree = 0;
var selectedDepartmentName;
var selectedDepartmentID;
var selectedOverHaulId;
function onClick(event, treeId, treeNode) {
	// 清空页号:
	$("#currentPage").val("");
	clickBigAndDepTree = 1;
	selectedOverHaulId = treeNode.id;
	selectedDepartmentID = treeNode.upid;
	$("#query_bigId").val(selectedOverHaulId);
	$("#query_unitId").val(selectedDepartmentID);
	if (selectedDepartmentID == null) {
		// 调用查询员工信息的方法
		queryEmployeeOutBaseInfo();
	}
	// 如果树的等级是零则证明是部门，设置部门信息
	if (treeNode.level == 1) {
		clickRes = 1;
		// 设置部门名称
		$("#add_departmentName").val(treeNode.name);
		$("#add_departmentId").val(treeNode.id);
		$("#add_bigId").val(treeNode.upid);
		selectedDepartmentID = treeNode.id;
		selectedOverHaulId = treeNode.upid;
		$("#query_bigId").val(selectedOverHaulId);
		$("#query_unitId").val(selectedDepartmentID);
		// 调用查询员工信息的方法
		queryEmployeeOutBaseInfo();
	} else {
		clickRes = 0;
	}

	return (treeNode.click != false);
}
