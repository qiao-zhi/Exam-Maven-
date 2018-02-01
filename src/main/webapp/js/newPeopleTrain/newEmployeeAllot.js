$(function() {
	searchDepartmentAndOverHualTree();
	queryDistributeInfo();
	// 树d信息
	searchDepartmentTree();
	searchDepartmentTree1();// 修改模态框的树
	// 多选框联动
	$("#empCheckAll").click(function() {
		$("input[name='checkBox']").prop("checked", $(this).prop("checked"));
	})
})

var checkTree = 0, checkTree1 = 0;// 标记选中几个部门，如果是0个部门不能提交

/** *************************分配员工************************** */
function el_empTrainDoc() {

	var chooseEmpNum = 0;// 判断是否有员工被选中

	$(".el_checks").each(function() { // 获取选择的员工

		if ($(this).prop("checked")) {// 如果选中。。。
			chooseEmpNum++;

		}
	})

	if (chooseEmpNum != 0) {
		$("#el_empTrainDoc").modal();

	} else {
		alert("请先选择员工！")
	}
}

/** ********* S 生成内部部门树*********** */
// 查询内部部门结构
var searchDepartmentTree = function() {
	var zNodes10;
	$.ajax({
		url : baseurl + '/distribute_getDepartmentTreeForFenpei.action',
		async : true,
		dataType : 'json',
		success : function(response) {
			zNodes10 = response.departmentTree;
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
	$("#treeDemo_permission").html(""); // 清空树结构
	var setting = {
		view : {
			selectedMulti : true
		},
		check : {
			enable : true,
//			chkboxType : {
//				"Y" : "",
//				"N" : ""
//			}
			chkStyle: "radio",
			radioType: "all"
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
			onCheck : onCheck,
			onClick : zTreeOnClick
		}
	};
	var treeNodes = departmentTrees;
	$.fn.zTree.init($("#treeDemo_permission"), setting, treeNodes);
	$.fn.zTree.init($("#reDistribueTree"), setting, treeNodes);//生成二次分配树
}
// 鼠标点击树事件(打印点击的id与名字)
function zTreeOnClick(event, treeId, treeNode) {
}
// 单选checkBox的点击事件
function onCheck(event, treeId, treeNode) {
	if (!treeNode.checked) {// 选中
		checkTree += 1;
	}
	if (treeNode.checked) {// 取消选中
		checkTree -= 1;
	}
}

/** **************************************大修单位树的相关方法**************************************************************** */

/** *********************请求树信息********************* */

function searchDepartmentAndOverHualTree() {
	$.ajax({
		type : "post",
		target : "#departmentAndOverHaulTree",
		dataType : "json",
		url : "distribute_getHaulunitTreeByDepartmentId.action?markTrainType=0",
		success : getTree_1,
		error : function() {
			alert("请求树失败！");
		}
	});
}

/** *********************生成树信息********************* */
function getTree_1(response) {
	var treeList3 = response.haulunitTree;
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

/*
 * var clickRes = 0; var clickBigAndDepTree = 0; var selectedDepartmentName; var
 * selectedDepartmentID; var selectedOverHaulId;
 */
function clearPageQuery() {
	$("#currentPage").val("");
	queryDistributeInfo();
}
function onClick(event, treeId, treeNode) {
	// 如果点的是大修将单位信息隐藏
	if (treeNode.level == 0) {
		$("#unitInfoDiv").css("display", "none");
		$("#query_unitId").val("");
		$("#query_bigId").val(treeNode.id);
		clearPageQuery();
	}
	// 如果树的等级是零则证明是部门，设置部门信息
	if (treeNode.level == 1) {
		var unitId = treeNode.id;
		var bigId = treeNode.upid;
		$.post(baseurl + '/distribute_getUnitInfoByBigIdAndUnitId.action', {
			"unitId" : unitId,
			"bigId" : bigId
		}, function(response) {
			var unit = response.unitInfo;
			$("#unitTbody").html("");
			$("#unitTbody").append(
					'<tr><td>' + treeNode.name + '</td><td>' + unit.manager
							+ '</td><td>' + unit.managerPhone + '</td><td>'
							+ unit.secure + '</dh><td>' + unit.securePhone
							+ '</td><td>' + unit.projectNames + '</td><td>'
							+ unit.perNum + '</td><td>' + unit.unitMinisMum
							+ '</td></tr>');
			$("#unitInfoDiv").css("display", "block");
		}, 'json')
		$("#query_bigId").val(bigId);
		$("#query_unitId").val(unitId);
		clearPageQuery();
	}
}

/**
 * ***********************************S
 * 查询分配信息相关方法****************************************************
 */
function queryDistributeInfo() {
	$.post(baseurl + '/distribute_getDistributeInfoWithCondition.action', $(
			"#queryDistributeInfoForm").serialize(), showFenpeiTable, 'json')
}
function showFenpeiTable(response) {
	var distributeinfos = response.pageBean.productList;// 获取到大修JSON对象
	var currentCount = response.pageBean.currentCount;// 页大小
	var totalCount = response.pageBean.totalCount;// 页总数
	var currentPage = response.pageBean.currentPage;// 当前页
	$("#employeeOutBaseInfoList").html("");
	for (var i = 0; distributeinfos != null && i < distributeinfos.length; i++) {
		var sex = distributeinfos[i].sex == "1" ? "男" : "女";
		var minusNum = distributeinfos[i].minusNum;
		if (minusNum == null) {
			minusNum = "0";
		}
		var str = '<tr><td><input class="el_checks" name="checkBox"type="checkbox"></td><td>'
				+ (parseInt(currentCount) * parseInt(currentPage - 1) + (i + 1))
				+ '</td><td>'
				+ distributeinfos[i].outempname
				+ '</td><td>'
				+ sex
				+ '</td><td>'
				+ distributeinfos[i].empOutIdCard
				+ '</td><td>'
				+ distributeinfos[i].bigName
				+ '</td><td>'
				+ distributeinfos[i].name
				+ '</td><td>'
				+ distributeinfos[i].empType
				+ '</td><td>'
				+ minusNum
				+ '</td><td>'
				+ distributeinfos[i].fenpeibumen
				+ '</td><td>'
				+ distributeinfos[i].fenpeibanzu
				+ '</td><td>'
				+ updateOperating();
		// 隐藏一些必须的信息
		str += '<input type="hidden" class="bigid" value="'
				+ distributeinfos[i].bigid + '"/>'
				+ '<input type="hidden" class="employeeid" value="'
				+ distributeinfos[i].employeeid + '"/>'
				+ '<input type="hidden" class="unitid" value="'
				+ distributeinfos[i].unitid + '"/>'
				+ '<input type="hidden" class="haulempid" value="'
				+ distributeinfos[i].haulempid + '"/>';
		str += '</td></tr>';
		$("#employeeOutBaseInfoList").append(str);
	}
	// 动态开启分页组件
	page(currentPage, totalCount, currentCount);
}
// 显示分页
function page(currentPage, totalCount, currentCount) {
	// 修改分页的基本属性
	$('#paginationIDU').pagination(
			{
				// 组件属性
				"total" : totalCount,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : currentCount,// 数字 每一页显示的数量 10
				"pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8, 15, 50 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, pageSize) {
					$("#currentPage").val(pageNumber);
					$("#currentCount").val(pageSize);
					queryDistributeInfo();
				}
			});
}

/** ******************S 保存分配信息的方法********************************* */
function saveFenpei() {
	if (checkTree == 0) {
		alert("请先选择要分配的部门");
		return;
	}
	var checkedEmp = $("#employeeOutBaseInfoList").find(":checkBox:checked");
	// 遍历选中的人添加到表单中
	$("#submitFenpeiForm").html("");
	checkedEmp.each(function(i) {

		var outempname = $(this).parent().parent().children("td:eq(2)").html();// 姓名
		var empoutidcard = $(this).parent().parent().children("td:eq(4)")
				.html();// 身份证号
		var hidden_input = $(this).parent().parent().children("td:eq(11)");
		var bigid = hidden_input.find(".bigid").val();
		var unitid = hidden_input.find(".unitid").val();
		var haulempid = hidden_input.find(".haulempid").val();

		$("#submitFenpeiForm").append(
				'<input type="hidden" value="' + bigid
						+ '" name="employeeoutdistributes[' + i + '].bigid"/>'
						+ '<input type="hidden" value="' + unitid
						+ '" name="employeeoutdistributes[' + i + '].unitid"/>'
						+ '<input type="hidden" value="' + outempname
						+ '" name="employeeoutdistributes[' + i
						+ '].outempname"/>' + '<input type="hidden" value="'
						+ empoutidcard + '" name="employeeoutdistributes[' + i
						+ '].empoutidcard"/>' + '<input type="hidden" value="'
						+ haulempid + '" name="employeeoutdistributes[' + i
						+ '].haulempid"/>');

	})

	var departmentid = "";
	/* S 获取所有的部门信息 */
	// 获取树对象
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo_permission");
	var nodes = treeObj.transformToArray(treeObj.getNodes());
	// 遍历树节点设置树节点为选中
	for (var k = 0, length_3 = nodes.length; k < length_3; k++) {
		if (nodes[k].checked) {
			departmentid += nodes[k].departmentId + ","
		}
	}
	var departmentIds = '<input type="hidden" value="'
			+ departmentid.substring(0, departmentid.length - 1)
			+ '" name="departmentids"/>';
	$("#submitFenpeiForm").append(departmentIds);
	/* E 获取所有的部门信息 */
	$.post(baseurl + '/distribute_addFenpeiInfo.action', $("#submitFenpeiForm")
			.serialize(), function(response) {
		alert(response.result);
		$("#el_empTrainDoc").modal("hide");
		if (response.result == "添加成功") {
			queryDistributeInfo();
		}
	}, 'json')
}

/** *************************S 修改分配信息******************** */
/** ********* S 生成内部部门树修改莫泰框*********** */
// 查询内部部门结构
var searchDepartmentTree1 = function() {
	var zNodes11;
	$.ajax({
		url : baseurl + '/distribute_getDepartmentTreeForFenpei.action',
		async : true,
		dataType : 'json',
		success : function(response) {
			zNodes11 = response.departmentTree;
			// 生成树结构
			geneDepartmentTree1(zNodes11);

		},
		error : function() {
			alert("查询内部部门树失败！！！")
		}
	});
}

// 生成内部部门树
function geneDepartmentTree1(departmentTrees) {
	$("#treeDemo_permission1").html(""); // 清空树结构
	var setting = {
		view : {
			selectedMulti : true
		},
		check : {
			enable : true,
/*			chkboxType : {
				"Y" : "",
				"N" : ""
			}*/
			chkStyle: "radio",
			radioType: "all"
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
			onCheck : onCheck1,
			onClick : zTreeOnClick1
		}
	};
	var treeNodes = departmentTrees;
	$.fn.zTree.init($("#treeDemo_permission1"), setting, treeNodes);
}
// 鼠标点击树事件(打印点击的id与名字)
function zTreeOnClick1(event, treeId, treeNode) {
}
// 单选checkBox的点击事件
function onCheck1(event, treeId, treeNode) {
	if (!treeNode.checked) {// 选中
		checkTree1 += 1;
	}
	if (treeNode.checked) {// 取消选中
		checkTree1 -= 1;
	}
}

function updateFenpei(obj) {
	$("#employeeOutUpdateInfo").html("");
	$("#updateFenpeiForm").html("");
	var outempname = $(obj).parent().parent().children("td:eq(2)").html();// 姓名
	var empoutidcard = $(obj).parent().parent().children("td:eq(4)").html();// 身份证号
	var bigName = $(obj).parent().parent().children("td:eq(5)").html();// 大修名字
	var unitName = $(obj).parent().parent().children("td:eq(6)").html();// 單位名字
	var sex = $(obj).parent().parent().children("td:eq(3)").html();// 身份证号
	var hidden_input = $(obj).parent().parent().children("td:eq(11)");
	var bigid = hidden_input.find(".bigid").val();
	var unitid = hidden_input.find(".unitid").val();
	var haulempid = hidden_input.find(".haulempid").val();
	$("#employeeOutUpdateInfo").append(
			'<tr><td>' + outempname + '</td><td>' + sex + '</td><td>'
					+ empoutidcard + '</td><td>' + bigName + '</td><td>'
					+ unitName + '</td><tr>');
	$("#updateFenpeiForm").append(
			'<input type="hidden" value="' + bigid
					+ '" name="employeeoutdistribute.bigid"/>'
					+ '<input type="hidden" value="' + unitid
					+ '" name="employeeoutdistribute.unitid"/>'
					+ '<input type="hidden" value="' + outempname
					+ '" name="employeeoutdistribute.outempname"/>'
					+ '<input type="hidden" value="' + empoutidcard
					+ '" name="employeeoutdistribute.empoutidcard"/>'
					+ '<input type="hidden" value="' + haulempid
					+ '" name="employeeoutdistribute.haulempid"/>');
	$("#el_changeAllot").modal("show");
}

function updateFenpeiInfo() {
	if (checkTree1 == 0) {
		alert("请先选择要分配的部门");
		return;
	}

	var departmentid = "";
	/* S 获取所有的部门信息 */
	// 获取树对象
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo_permission1");
	var nodes = treeObj.transformToArray(treeObj.getNodes());
	// 遍历树节点设置树节点为选中
	for (var k = 0, length_3 = nodes.length; k < length_3; k++) {
		if (nodes[k].checked) {
			departmentid += nodes[k].departmentId + ","
		}
	}
	var departmentIds = '<input type="hidden" value="'
			+ departmentid.substring(0, departmentid.length - 1)
			+ '" name="departmentids"/>';
	$("#updateFenpeiForm").append(departmentIds);
	/* E 获取所有的部门信息 */
	$.post(baseurl + '/distribute_updateFenpeiInfo.action', $(
			"#updateFenpeiForm").serialize(), function(response) {
		alert(response.result);
		$("#el_changeAllot").modal("hide");
		if (response.result == "修改成功") {
			queryDistributeInfo();

		}
	}, 'json')
}

/** ****************S 查询不同状态的人的******************************* */
function selectFenpeiInfo() {
	var distributeStatus = $("#el_showManager option:selected").val();
	// 只有未分配的才能出来未分配按钮，其他隐藏此按钮
	if (distributeStatus == '1') {
		$("#el_lookTrainDocument").css("display", "");
	} else {
		$("#el_lookTrainDocument").css("display", "none");
	}

	// 动态显示与隐藏生成工作证
	if (distributeStatus == '3') {
		$("#generateWork").css("display", "");
	} else {
		$("#generateWork").css("display", "none");
	}
	// 动态显示与隐藏二次分配
	if (distributeStatus == '4') {
		$("#reDistributeDepart").css("display", "");
	} else {
		$("#reDistributeDepart").css("display", "none");
	}
	$("[name='distributeStatus']").val(distributeStatus);
	queryDistributeInfo();
}

/** ************清空的方法********** */
function clearQueryInfo() {
	// 判断当前是否有树选中
	var value = $("#query_bigId").val();
	// 清除选中的节点
	if(value!=""){
		$(".curSelectedNode").removeClass("curSelectedNode");
	}
	$(".clearInput").val("");
}


/** S 动态修改操作那边的按钮* */
function updateOperating() {
	var distributeStatus = $("#el_showManager option:selected").val()
	if (distributeStatus == '2') {
		return '<a href=javascript:void(0) onclick="updateFenpei(this)">修改</a>';
	} else {
		return "--";
	}
}
/** ***********************************生成工作证的相关操作*********************************** */

//选中一次大修或部门，点击生成工作证按钮执行的操作
function el_empCardModel() {
	var chooseEmpNum = 0;// 判断是否有员工被选中
	$(".el_checks").each(function() { // 获取选择的员工
		if ($(this).prop("checked")) {// 如果选中。。。
			chooseEmpNum++;
		}
	})
	if (chooseEmpNum != 0) {
		$("#empInfoListForCertificate").html("");
		$(".el_checks")
				.each(
						function() { // 获取选择的员工
							if ($(this).prop("checked")) {// 如果选中。。。
								var tds = $(this).parent().parent().children();
								var haulempid = tds.eq(11).children(
										".haulempid").val();
								$("#empInfoListForCertificate")
										.append(
												'<tr><td>'
														+ tds.eq(2).html()
														+ '</td><td>'
														+ tds.eq(3).html()
														+ '</td><td>'
														+ tds.eq(4).html()
														+ '</td><td>'
														+ tds.eq(6).html()
														+ '<input type="hidden" class="haulempid_export" value="'
														+ haulempid
														+ '"/></td></tr>')
							}
						})

		$("#el_empCardModel").modal();
	} else {
		alert("请先选择员工！")
	}
}

//点击生成工作证信息执行的操作
function exportEmployeeOutInfo() {
	var haulempids = [];
	$(".haulempid_export").each(function(i) {
		haulempids[i] = $(this).val();
	});

	$.post(baseurl + '/distribute_generateWordCard.action', 
			{
			"bigEmployeeOutIds" : haulempids.toString()
			}, 
			function(response) {
				if(response.result=="生成工作证成功!"){
					alert("入职成功!");
					// 关闭模态框并查询
					$("#el_empCardModel").modal("hide");
					queryDistributeInfo();
				}
				}, 
				'json');

}


/** *************************S 二次分配相关操作**************************** */
function reDstributeDepart(){
	var chooseEmpNum = 0;// 判断是否有员工被选中
	$(".el_checks").each(function() { // 获取选择的员工
		if ($(this).prop("checked")) {// 如果选中。。。
			chooseEmpNum++;
		}
	})

	if (chooseEmpNum != 0) {
		$("#reDistributeDepartModal").modal();

	} else {
		alert("请先选择员工！")
	}
}
function saveReDis(){
	// 1.根据树获取选中的节点的ID
	var reDistributeDepartmentId="";
    var treeObj = $.fn.zTree.getZTreeObj("reDistribueTree");
    /** 获取所有树节点 */
    var nodes = treeObj.transformToArray(treeObj.getNodes());
    //获取选中的节点
    for (var k = 0, length_3 = nodes.length; k < length_3; k++) {
            if(nodes[k].checked==true){
            	reDistributeDepartmentId = nodes[k].departmentId;
            }
    }
	if(reDistributeDepartmentId == ""){
		alert("分配部门不能为空!");
		return ;
	}
	// 2.拼接表单
	var checkedEmp = $("#employeeOutBaseInfoList").find(":checkBox:checked");
	// 遍历选中的人添加到表单中
	$("#reDistributeForm").html("");
	checkedEmp.each(function(i) {

		var outempname = $(this).parent().parent().children("td:eq(2)").html();// 姓名
		var empoutidcard = $(this).parent().parent().children("td:eq(4)")
				.html();// 身份证号
		var emptype = $(this).parent().parent().children("td:eq(7)").html();// 员工工种
		var hidden_input = $(this).parent().parent().children("td:eq(11)");
		var bigid = hidden_input.find(".bigid").val();//大修ID
		var unitid = hidden_input.find(".unitid").val();//单位ID
		var empid = hidden_input.find(".employeeid").val();//员工ID
		$("#reDistributeForm").append(
//				拼接分配信息
				'<input type="hidden" value="' + bigid+ '" name="employeeoutdistributes[' + i + '].bigid"/>'
				+ '<input type="hidden" value="' + unitid+ '" name="employeeoutdistributes[' + i + '].unitid"/>'
				+ '<input type="hidden" value="' + outempname+ '" name="employeeoutdistributes[' + i+ '].outempname"/>'
				+ '<input type="hidden" value="'+ empoutidcard + '" name="employeeoutdistributes[' + i+ '].empoutidcard"/>' 
				+ '<input type="hidden" value="'+ reDistributeDepartmentId + '" name="employeeoutdistributes[' + i	+ '].departmentid"/>'
//				拼接大修员工信息
				+ '<input type="hidden" value="'+ bigid + '" name="haulemployeeouts[' + i+ '].bigid"/>'
				+ '<input type="hidden" value="'+ unitid + '" name="haulemployeeouts[' + i+ '].unitid"/>'
				+ '<input type="hidden" value="'+ empid + '" name="haulemployeeouts[' + i+ '].employeeid"/>'
				+ '<input type="hidden" value="'+ emptype + '" name="haulemployeeouts[' + i+ '].emptype"/>'
				+ '<input type="hidden" value="'+ empoutidcard + '" name="haulemployeeouts[' + i+ '].empoutidcard"/>'
		);

	})
	// 3.提交表单
	$.post(baseurl + '/distribute_reDistribute.action', $("#reDistributeForm")
			.serialize(), function(response) {
		alert(response.result);
		$("#reDistributeDepartModal").modal("hide");
		if (response.result == "二次分配成功") {
			queryDistributeInfo();
		}
	}, 'json')

	
}
/** *************************E 二次分配相关操作**************************** */


/** *********************外来单位的员工的培训档案********************* */
function lookTrainInfo() {
	if ($(".el_checks:checked").length == "0") {
		alert("请选择要查看的员工！");
		return;
	} 
	if($(".el_checks:checked").length > 1){
		alert("请选择一个员工进行查看！");
	}else {
		var employeeOutIdCard = $(".el_checks:checked").parents("tr").children("td:eq(4)").text();
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
						+ (index + (data.pageBean.currentPage - 1) * 8)
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
						+ "</td><td>" + examInfoList[i].grade 						
						+ "</td><td>" + examInfoList[i].isPass 
						+ "</td><td>" + examInfoList[i].xueshi
						+ "</td><td>" + examInfoList[i].traincontent
						+ "</td></tr>";
				$("#employeeOutExamInfos").append(showExamInfoList);
			}
			// 当前页
			var currentPage = data.pageBean.currentPage;
			// 总条数
			var totalCount = data.pageBean.totalCount;
			// 调用分页函数
			trainStatus_page(currentPage, totalCount, employeeOutIdCard);

			$('#el_empTrainDoc_1').modal();
		}
	});
}
//培训档案的分页函数
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