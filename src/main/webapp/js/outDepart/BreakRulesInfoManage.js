/**
 * Created by yorge on 2017/9/14.
 */

/* 添加违章信息 */
function el_addBreakInfo() {
	// 累计选择的个数，若等于1，才执行，否则提示
	var nums = 0;
	$.each($(".el_checks"), function(i, el_check) {
		if ($(this).prop("checked")) {
			nums++;
		}
	});
	if (nums != 1) {
		alert("请选中员工之后再添加违章信息！");
		return false;
	} else {
		// 为模态框表格中的数据初始化

		// 获取被选中按钮的值
		// 姓名
		var empName = $('input[name="el_chooseBreakRules"]:checked ').parents("tr").children("td").eq(1).text();// 姓名
		// 联系方式
		var phone = $('input[name="el_chooseBreakRules"]:checked ').val();
		// 违章记分
		var score = $('input[name="el_chooseBreakRules"]:checked ').parents("tr").children("td").eq(4).text();
		// 所属单位
		var unitName = $('input[name="el_chooseBreakRules"]:checked ').parents("tr").children("td").eq(6).text();
		// 黑名单
		var isBreak = $('input[name="el_chooseBreakRules"]:checked ').parents("tr").children("td").eq(5).text();
		// 性别
		var sex = $('input[name="el_chooseBreakRules"]:checked ').parents("tr").children("td").eq(2).text();

		// 为表格中的数据初始化
		// 姓名
		$("#addName").text(empName);
		// 联系方式
		$("#addPhone").text(phone);
		// 违章记分
		$("#addbreakScore").text(score);
		// 所属单位
		$("#addunitName").text(unitName);
		// 是否黑名单
		$("#addIsBreak").text(isBreak);
		// 性别
		$("#addSex").text(sex);

		// 获取职工id
		var empId = $('input[name="el_chooseBreakRules"]:checked').parents("tr").children('input[name="el_ycy"]').eq(0).val();
		// 获取参加大修职工id
		var haulEmpId = $('input[name="el_chooseBreakRules"]:checked').parents("tr").children('input[name="el_ycy"]').eq(1).val();
		// 职工的违章id
		var breakid = $('input[name="el_chooseBreakRules"]:checked').parents("tr").children('input[name="el_ycy"]').eq(2).val();
		// 操作的标记 是左侧的树还是顶部的按条件分页查询
		var allmark = $('input[name="el_chooseBreakRules"]:checked').parents("tr").children('input[name="el_ycy"]').eq(3).val();
		// 大修id（bigid）
		var addBigId = $('input[name="el_chooseBreakRules"]:checked').parents("tr").children('input[name="el_ycy"]').eq(4).val();
		// 单位id unitid
		var addUnitidM = $('input[name="el_chooseBreakRules"]:checked').parents("tr").children('input[name="el_ycy"]').eq(5).val();
		$("#addUnitidM").val(addUnitidM);// 为隐藏域添加单位编号unitid
		// 为隐藏域大修id赋值
		if (allmark == "initData") {
			$("#addBigHaulId").val(addBigId);
		}
		
		// 为标记的隐藏域赋值 是左侧的树还是顶部的按条件分页查询
		$("#allMark").val(allmark);

		// 为隐藏域赋值
		// 职工id
		$("#addEmployee").val(empId);
		// 参加大修的职工id
		$("#addBigHaulEmployeeId").val(haulEmpId);
		// 职工的违章id
		$("#addEmpBreakId").val(breakid);

		// 为违章记分、违章内容初始化，也就是初始化添加违章信息的表单
		// 初始化违章记分
		$("#breakScoreID").val("");
		// 初始化违章内容
		$("#addBreakContent").val("");

		// 为添加前的违章记分的隐藏域赋值
		$("#beforeBreakScore").val(score);

		
		if ($("#addIsBreak").text() == "是") {
			alert("处于黑名单的外来职工不能添加违章积分")
		} else {
			// 打开模态框
			$("#el_addBreakInfo").modal();
		}

	}
}

/* 选择树 */
function el_selectTree() {
	// 获得的树的名字： getName
}


/*
 * 修改违章信息 function modifyBreak() {
 * 
 * //弹出模态框 $('#modifyBreak').modal(); }
 */
/* 删除 */
/*
 * function delcfm() { $('#delcfmModel').modal(); }
 */
function urlSubmit() {
	var url = $.trim($("#url").val());// 获取会话中的隐藏属性URL
	window.location.href = url;
}

/** ******************lixianyuan start *********** */

/** **************************************树的相关方法**************************************************************** */

$(function() {
	// 加载左侧的树
	searchDepartmentAndOverHualTree();

	// 用于验证数字
	jQuery.validator.addMethod("isNumber", function(value, element) {
		var length = value.length;
		var tel = /(^\d+$)/;
		return this.optional(element) || (tel.test(value));
	}, "请输入数字");

	/*// 页面加载的时候初始化一些数据出来
	initData();*/
	
	if($("#dataFlushReturn").val()=="0"){
		//alert("标记为0")
		// 页面加载的时候初始化一些数据出来
		initData();
	}else{
		//alert("标记不为0")
		// 用于返回页面数据的不变
		findSaveBtn();
	}
	
	
})

/** *********************请求树信息********************* */

function searchDepartmentAndOverHualTree() {
	$.ajax({
		type : "post",
		target : "#treeDemo",
		dataType : "json",
		url : "breakrules_unloadTree.action",
		success : getTree_1,
		error : function() {
			alert("请求树失败！");
		}
	});
}

/** *********************生成树信息********************* */
function getTree_1(treeList2) {
	var treeList3 = treeList2.leftTree;
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
			beforeClick : beforeClick
		}
	};
	var zNodes = treeList3;
	// 添加 树节点的 点击事件；
	var log, className = "dark";
	function onClick(event, treeId, treeNode, clickFlag) {
		clickOnTree(event, treeId, treeNode, clickFlag);
	}
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	// var treeObj = $.fn.zTree.getZTreeObj("#treeDemo");
	// treeObj.expandAll(false);
}

function showIconForTree(treeId, treeNode) {
	return !treeNode.isParent;
}

var clickRes = 0;
var getName;
var selectedDepartmentID;
var selectedOverHaulId;
function beforeClick(treeId, treeNode, clickFlag) {
	// 清空页号
	$("#yeHao").val("1");
	clickRes = 1;
	getName = treeNode.name;// 当前选中的树的名字
	selectedDepartmentID = treeNode.id;// 当前选中的树的id
	selectedOverHaulId = treeNode.upid;// 当前选中的树的上级id(大修id)
	
	// 为隐藏域赋值
	// $("#leftTreeName").val(treeNode.name);//当前选中的树的名字(只能选部门)
	// $("#unitBigHual").val(treeNode.upid);//单位编号
	// $("#uppUnitBigHaulId").val(treeNode.upid);//单位大修编号 (用于修改操作的)
	// $("#unitBH").val(treeNode.id);//单位编号

	// 为添加的模态框中 隐藏的 大修单位表的id
	$("#addBigHaulId").val(treeNode.upid);// 单位大修表id

	if (treeNode.upid == null) {
		

		// 当前选中的id就是大修的id 用于左侧的数的操作
		$("#unitBigHual").val(treeNode.id);// 单位大修编号
		$("#unitBH").val("");// 部门编号(单位编号)

		// $("#addUnitidM").val();//添加操作的 单位编号

		// 用于左侧的树和查询条件绑定后的操作的
		$("#findUnitBigBH").val(treeNode.id);// 单位大修编号
		$("#ffunitId").val("");// 单位编号

		// 为添加的模态框中 隐藏的 大修单位表的id
		$("#addBigHaulId").val(treeNode.id);// 单位大修表id

		// 为删除的模态框中 隐藏的 大修单位表的id
		$("#delunitId").val(treeNode.id);// 大修单位表的id

		// 为修改模态框中 隐藏的 大修单位编号
		$("#uppUnitBigHaulId").val(treeNode.id);

		// 将该大修下的每个部门的每一个工程显示出来
		// 步骤：通过大修id找到该大修下的所有部门，在找该大修下的每个部门下的所有工程
	} else {
		$("#unitBigHual").val(treeNode.upid);
		// 当前选中的id就是部门的id
		$("#unitBH").val(treeNode.id);

		// 用于左侧的树和查询条件绑定后的操作的
		$("#findUnitBigBH").val(treeNode.upid);// 单位大修编号
		$("#ffunitId").val(treeNode.id);// 单位编号

		// 为添加的模态框中 隐藏的 大修单位表的id
		$("#addBigHaulId").val(treeNode.upid);// 单位大修表id

		// 为删除的模态框中 隐藏的 大修单位表的id
		$("#delunitId").val(treeNode.upid);// 大修单位表的id

		// 为修改模态框中 隐藏的 大修单位编号
		$("#uppUnitBigHaulId").val(treeNode.upid);

	}
	// 将左侧树旗下的职工信息显示出来
	leftBtn();
	console.log(selectedOverHaulId);
	return (treeNode.click != false);
}

// *********************页面加载初始化一些数据出来
function initData() {
	$.ajax({
		url : contextPath+"/breakrules_initPage.action",
		data : {
			"curPage" : $("#yeHao").val(),// 当前页页号
			"curTotal" : $("#jiLuShu").val(),
			"empBreakInfoType":$("#el_breakType").val()
		// 每页显示的记录数
		},
		dataType : "json",
		type : "POST",
		async : true,
		success : function(data) {
			// 每次查询之前都先清空表格中的数据
			$("#tbody tr").remove();
			var opStr = "";
			
			for (var i = 0; i < data.selectLeftToTable.length; i++) {

				var name = data.selectLeftToTable[i].name;// 姓名
				var sex = data.selectLeftToTable[i].sex;// 性别
				if (sex == "1") {
					sex = "男";
				} else if (sex == "2") {
					sex = "女";
				}
				var idCode = data.selectLeftToTable[i].idCode;// 身份证
				var sumBreakScore = data.selectLeftToTable[i].sumBreakScore;// 违章总记分
				var empType = data.selectLeftToTable[i].empType;// 工种
				var employeeId = data.selectLeftToTable[i].employeeId;// 外来职工表id
				var BigEmployeeoutId = data.selectLeftToTable[i].BigEmployeeoutId;// 大修外来员工id
				var empOutphone = data.selectLeftToTable[i].empOutphone;// 联系电话
				var unitName = data.selectLeftToTable[i].depName;// (所属部门名称)所属单位名称
				// //unit.name
				// AS
				// depName部门
				var bigid = data.selectLeftToTable[i].bigId;// 大修id
				var unitid = data.selectLeftToTable[i].unitId;// 单位id
				var status = data.selectLeftToTable[i].status;// 黑名单状态
				// addUnitidM
				// 为添加违章信息的表格初始化数据 end
				// 解决underfined问题
				if (sumBreakScore >= 0) {

				} else {
					sumBreakScore = "0";
				}

				opStr += "<tr>";
				// 隐藏域
				opStr += "<input name='el_ycy' type='hidden' value="
						+ employeeId + ">";// 隐藏域，隐藏一个职工id employeeId
				opStr += "<input name='el_ycy' type='hidden' value="
						+ data.selectLeftToTable[i].BigEmployeeoutId
						+ ">";// 隐藏域，隐藏一个大修外来职工id BigEmployeeoutId
				opStr += "<input name='el_ycy' type='hidden' value="+ "breakId" + ">";// 隐藏域，隐藏一个违章id
				opStr += "<input name='el_ycy' type='hidden' value='initData'>";// 隐藏域，隐藏一个标记，是左侧的树还是顶部的按条件分页查询
				opStr += "<input name='el_ycy' type='hidden' value="+ data.selectLeftToTable[i].bigId + ">";// 隐藏大修id
				opStr += "<input name='el_ycy' type='hidden' value="+ unitid + ">";// 隐藏单位id

				opStr += "<td><input type='radio' name='el_chooseBreakRules' class='el_checks' value="+ empOutphone + "></td>";
				opStr += "<td>" + name + "</td>";// 姓名
				opStr += "<td>" + sex + "</td>";// 性别
				opStr += "<td>" + idCode + "</td>";// 身份证
				opStr += "<td>" + sumBreakScore + "</td>";// 违章记分
				// 为隐藏职工id的隐藏域赋值
				$("#findEmpId").val(employeeId);
				if (status == "1") {
					$("#findIsBreak").val("是");
				} else {
					$("#findIsBreak").val("否");
				}
				var mark = $("#findIsBreak").val();// 是否黑名单的标记，用于查看详情操作

				// 黑名单状态有问题：应该这样处理：根据职工id去黑名单查询，如果有结果，则显示为"是"，否则显示为"否"
				if (status == "1") {
					opStr += "<td><font color='red'>" + "是"	+ "</font></td>";
				} else {
					if (sumBreakScore >= 12) {
						opStr += "<td><font color='blue'>" + "否"+ "</font></td>";
					} else {
						opStr += "<td>" + "否" + "</td>";
					}

				}
				// 为大修的隐藏域赋值
				$("#haulInfoBigId").val(bigid);
				// 根据大修id获取大修名字 hualInfoName
				$.ajax({
					url : contextPath+"/breakrules_getHaulInfoNameByBigId.action",
					data : {
						"bigid" : $("#haulInfoBigId").val()
					// 大修id
					},
					dataType : "json",
					type : "POST",
					async : false,
					success : function(data) {
						$("#hualInfoName").val(data.haulInfoName);
					}
				});
				// opStr +="<td>"+$("#findIsBreak").val()+"</td>";
				opStr += "<td>" + unitName + "</td>";// 所属单位
				opStr += " <td>" + empType + "</td>";// 工种
				opStr += " <td>" + $("#hualInfoName").val() + "</td>";// 所属大修

				opStr += "<td>";

				opStr += "<input type='hidden' value=" + "empOutphone"+ ">";// 隐藏域，隐藏一个联系电话 empOutphone
				opStr += "<input type='hidden' value=" + "breakId"+ ">";// 隐藏域，隐藏一个职工的违章id
				opStr += "<input type='hidden' value=" + employeeId+ ">";// 隐藏域，隐藏一个职工id employeeId
				opStr += "<input type='hidden' value="+ BigEmployeeoutId + ">";// 隐藏域，隐藏一个大修外来职工id
				// BigEmployeeoutId
				opStr += "<input type='hidden' value=" + "breaktime"+ ">";// 隐藏域，隐藏违章时间
				opStr += "<input type='hidden' value=" + "breakcontent"	+ ">";// 隐藏域，隐藏违章内容
				opStr += "<input type='hidden' value='initData'/>";// 隐藏域，隐藏式操作左边的树的还是顶部的分页条的"&empOutphone="+empOutphone+
				// 9
				opStr += "<input type='hidden' value=" + name + ">";// 隐藏域，隐藏
				// 姓名
				opStr += "<input type='hidden' value=" + sex + ">";// 隐藏域，隐藏
				// 性别
				opStr += "<input type='hidden' value=" + mark + ">";// 隐藏域，隐藏
				// 是否黑名单
				opStr += "<input type='hidden' value=" + idCode + ">";// 隐藏域，隐藏
				// 身份证号
				opStr += "<input type='hidden' value=" + unitName + ">";// 隐藏域，隐藏
				// 部门名称
				opStr += "<input type='hidden' value=" + bigid + ">";// 隐藏域，隐藏大修id
				opStr += "<input type='hidden' value=" + employeeId	+ ">";// 隐藏域，隐藏 员工id
				opStr += "<input type='hidden' value="+ BigEmployeeoutId + ">";// 隐藏域，隐藏
				// BigEmployeeoutId
				opStr += "<input type='hidden' value="+ $("#unitBigHual").val() + ">";// 隐藏域，隐藏

				//查看详细信息
				opStr += "<a href='javascript:void(0)' onclick='detailBtn(this)'>详情</a>";
				
				opStr += "</td>";

				opStr += "</tr>";
			}
			$("#tbody").append(opStr);

			// //封装总记录数
			// map.put("count", count);
			// 当前页页号
			// map.put("curPage", curPage);
			// 每页显示的记录数
			// map.put("curTotal", curTotal);
			var count = data.count;// 总记录数
			var curPage = data.curPage;// 当前页页号
			var curTotal = data.curTotal;// 每页显示的记录数
			
			// 参数1：总记录数 参数2：当前页页号 参数3：每页显示的记录条数
			queryFy4(count, curPage, curTotal);
		},
		error : function() {
			alert("error");
		}
	});
}

// 用于详细信息提交数据的
function detailBtn(obj) {
	// 用于返回页面数据的更新的标志
	$("#dataFlushReturn").val("2");
	// 获取
	
	$("#detailName").val($(obj).parents('td').children('input').eq(7).val());// 姓名
	$("#detailSex").val($(obj).parents('td').children('input').eq(8).val());// 性别
	$("#detailIsBreak").val($(obj).parents('td').children('input').eq(9).val());// 是否黑名单状态
	$("#detailIdCard").val($(obj).parents('td').children('input').eq(10).val());// 身份证号
	$("#detailUnitName").val($(obj).parents('td').children('input').eq(11).val());// 部门名称
	$("#detailBigId").val($(obj).parents('td').children('input').eq(12).val());// 大修id
	$("#detailEmployeeId").val($(obj).parents('td').children('input').eq(13).val());// 职工id
	$("#detailBigEmployeeoutId").val($(obj).parents('td').children('input').eq(14).val());// BigEmployeeoutId
	$("#detailUnitBigHual").val($(obj).parents('td').children('input').eq(15).val());// unitBigHaul

	$("#q_starttime").val($(".query_dep_starttime").val());
	$("#q_endtime").val($(".query_dep_endtime").val());
	
	$("#detail_breakInfoType").val($("#el_breakType").val());
	$("#detailForm").submit();// 提交form表单

}


/**
 *  点击左侧的树中的部门的时候，查询该部门下的所有外来职工的信息
 */
function leftBtn() {
	//获取当前选中的违章信息类型
	var type = $("#el_breakType").val();
	$.ajax({
		url : contextPath+"/breakrules_selEmployeeOutByUnitid.action",
		data : {
			"unitBigHual" : $("#unitBigHual").val(),//单位大修编号
			"unitBH" : $("#unitBH").val(),//单位编号(部门编号)
			"curPage" : $("#yeHao").val(),//当前页页号
			"curTotal" : $("#jiLuShu").val(),
			"empBreakInfoType":type
		//每页显示的记录数
		},
		dataType : "json",
		type : "POST",
		async : true,
		success : function(data) {
			//alert("执行了左侧的树的回掉函数")
			//每次查询之前都先清空表格中的数据
			$("#tbody tr").remove();
			var opStr = "";
			//alert("总数量"+data.selectLeftToTable.length)
			for (var i = 0; i < data.selectLeftToTable.length; i++) {

				var name = data.selectLeftToTable[i].name;//姓名
				var sex = data.selectLeftToTable[i].sex;//性别
				if (sex == "1") {
					sex = "男";
				} else if (sex == "2") {
					sex = "女";
				}
				var idCode = data.selectLeftToTable[i].idCode;//身份证
				var sumBreakScore = data.selectLeftToTable[i].sumBreakScore;//违章总记分
				var empType = data.selectLeftToTable[i].empType;//工种
				var employeeId = data.selectLeftToTable[i].employeeId;//外来职工表id
				var BigEmployeeoutId = data.selectLeftToTable[i].BigEmployeeoutId;//大修外来员工id
				var empOutphone = data.selectLeftToTable[i].empOutphone;//联系电话
				var unitName = data.selectLeftToTable[i].depName;//(所属部门名称)所属单位名称                  //unit.name AS depName部门
				var bigid = data.selectLeftToTable[i].bigId;//大修id
				var unitid = data.selectLeftToTable[i].unitId;//单位id
				var status = data.selectLeftToTable[i].status;//黑名单状态
				

				if (sumBreakScore >= 0) {

				} else {
					sumBreakScore = "0";
				}

				opStr += "<tr>";
				//隐藏域
				opStr += "<input name='el_ycy' type='hidden' value="+employeeId+">";//隐藏域，隐藏一个职工id employeeId
				opStr += "<input name='el_ycy' type='hidden' value="+data.selectLeftToTable[i].BigEmployeeoutId+">";//隐藏域，隐藏一个大修外来职工id BigEmployeeoutId
				opStr += "<input name='el_ycy' type='hidden' value="+"breakId"+">";//隐藏域，隐藏一个违章id
				opStr += "<input name='el_ycy' type='hidden' value='left'>";//隐藏域，隐藏一个标记，是左侧的树还是顶部的按条件分页查询
				opStr += "<input name='el_ycy' type='hidden' value="+bigid+">";//隐藏大修id
				opStr += "<input name='el_ycy' type='hidden' value="+unitid+">";//隐藏单位id

				opStr += "<td><input type='radio' name='el_chooseBreakRules' class='el_checks' value="+empOutphone+"></td>";
				opStr += "<td>" + name + "</td>";//姓名
				opStr += "<td>" + sex + "</td>";//性别
				opStr += "<td>" + idCode + "</td>";//身份证
				opStr += "<td>" + sumBreakScore + "</td>";//违章记分
				//为隐藏职工id的隐藏域赋值
				$("#findEmpId").val(employeeId);
				if (status == "1") {
					$("#findIsBreak").val("是");
				} else {
					$("#findIsBreak").val("否");
				}
				var mark = $("#findIsBreak").val();//是否黑名单的标记，用于查看详情操作
				
				//黑名单状态有问题：应该这样处理：根据职工id去黑名单查询，如果有结果，则显示为"是"，否则显示为"否"
				if (status == "1") {
					opStr += "<td><font color='red'>" + "是"	+ "</font></td>";
				} else {
					if (sumBreakScore >= 12) {
						opStr += "<td><font color='blue'>" + "否"+ "</font></td>";
					} else {
						opStr += "<td>" + "否" + "</td>";
					}

				}
				//为大修的隐藏域赋值
				$("#haulInfoBigId").val(bigid);
				//根据大修id获取大修名字 hualInfoName
				$.ajax({
					url : contextPath+"/breakrules_getHaulInfoNameByBigId.action",
					data : {
						"bigid" : $("#haulInfoBigId").val() //大修id
					},
					dataType : "json",
					type : "POST",
					async : false,
					success : function(data) {
						$("#hualInfoName").val(data.haulInfoName);
					}
				});
				//opStr +="<td>"+$("#findIsBreak").val()+"</td>";
				opStr += "<td>" + unitName + "</td>";//所属单位
				opStr += " <th>" + empType + "</th>";//工种
				opStr += " <th>" + $("#hualInfoName").val()	+ "</th>";//所属大修
				opStr += "<td>";

				opStr += "<input type='hidden' value="+"empOutphone"+">";//隐藏域，隐藏一个联系电话 empOutphone
				opStr += "<input type='hidden' value="+"breakId"+">";//隐藏域，隐藏一个职工的违章id
				opStr += "<input type='hidden' value="+employeeId+">";//隐藏域，隐藏一个职工id employeeId
				opStr += "<input type='hidden' value="+BigEmployeeoutId+">";//隐藏域，隐藏一个大修外来职工id BigEmployeeoutId
				opStr += "<input type='hidden' value="+"breaktime"+">";//隐藏域，隐藏违章时间
				opStr += "<input type='hidden' value="+"breakcontent"+">";//隐藏域，隐藏违章内容
				opStr += "<input type='hidden' value='initData'/>";//隐藏域，隐藏式操作左边的树的还是顶部的分页条的"&empOutphone="+empOutphone+
				//9
				opStr += "<input type='hidden' value="+name+">";//隐藏域，隐藏 姓名
				opStr += "<input type='hidden' value="+sex+">";//隐藏域，隐藏 性别
				opStr += "<input type='hidden' value="+mark+">";//隐藏域，隐藏 是否黑名单
				opStr += "<input type='hidden' value="+idCode+">";//隐藏域，隐藏 身份证号
				opStr += "<input type='hidden' value="+unitName+">";//隐藏域，隐藏 部门名称
				opStr += "<input type='hidden' value="+bigid+">";//隐藏域，隐藏大修id
				opStr += "<input type='hidden' value="+employeeId+">";//隐藏域，隐藏  员工id
				opStr += "<input type='hidden' value="+BigEmployeeoutId+">";//隐藏域，隐藏  BigEmployeeoutId
				opStr += "<input type='hidden' value="+ $("#unitBigHual").val() + ">";//隐藏域，隐藏 unitBigHaul
				//下一行代码是原来的
				opStr += "<a href='javascript:void(0)' onclick='detailBtn(this)'>查看详情</a>";
				
				opStr += "</td>";

				opStr += "</tr>";
			}
			$("#tbody").append(opStr);

			
			var count = data.count;//总记录数
			var curPage = data.curPage;//当前页页号
			var curTotal = data.curTotal;//每页显示的记录数
			
			//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
			queryFy(count, curPage, curTotal);
		},
		error : function() {
			alert("error");
		}
	});
}


/**
 * 分页条start
 */
//新版的分页条(这个分页条是左边的树专用的啊)  start
//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
function queryFy(resultCount, currentPage, currentTotal) {
	//分页栏  start
	$('#paginationIDU').pagination({
			//	组件属性
			"total" : resultCount,//数字 当分页建立时设置记录的总数量 1
			"pageSize" : currentTotal,//数字 每一页显示的数量 10
			"pageNumber" : parseInt(currentPage),//数字 当分页建立时，显示的页数 1
			"pageList" : [ 8, 15, 20 ],//数组 用户可以修改每一页的大小，   
			//功能
			"layout" : [ 'list', 'sep', 'first', 'prev', 'manual','next', 'last', 'links' ],
			"onSelectPage" : function(pageNumber, b) {
				//为两个隐藏域赋值  当前页页号   每页显示的记录数
				// alert("左侧的树的分页")
				$("#yeHao").val(pageNumber);//当前页页号
				$("#jiLuShu").val(b);//每页显示的记录数
				//执行左边的树的查询
				leftBtn();

			}
		});
}
//新版的分页条(最底部的那个) end

//新版的分页条(这个分页条是左边的树与分页条件绑定之后专用的    这个是点击查询之后的分页条，包括点击和未点击黑名单状态的情况)  start
//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
function queryFy2(resultCount, currentPage, currentTotal) {
	//分页栏  start
	$('#paginationIDU').pagination(
			{
				//组件属性
				"total" : resultCount,//数字 当分页建立时设置记录的总数量 1
				"pageSize" : currentTotal,//数字 每一页显示的数量 10
				"pageNumber" : parseInt(currentPage),//数字 当分页建立时，显示的页数 1
				"pageList" : [ 8, 15, 20 ],//数组 用户可以修改每一页的大小，   
				//功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual','next', 'last', 'links' ],
				"onSelectPage" : function(pageNumber, b) {
					//为两个隐藏域赋值  当前页页号   每页显示的记录数
					$("#yeHao").val(pageNumber);//当前页页号
					$("#jiLuShu").val(b);//每页显示的记录数
					//直接调用分页查询的方法
					findSaveBtn();
				}
			});
}
//新版的分页条(最底部的那个) end



//新版的分页条(用于页面初始化)  start
//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
function queryFy4(resultCount, currentPage, currentTotal) {
	//分页栏  start
	$('#paginationIDU').pagination({
		//组件属性
		"total" : resultCount,//数字 当分页建立时设置记录的总数量 1
		"pageSize" : currentTotal,//数字 每一页显示的数量 10
		"pageNumber" : parseInt(currentPage),//数字 当分页建立时，显示的页数 1
		"pageList" : [ 8, 15, 20 ],//数组 用户可以修改每一页的大小，   
		//功能
		"layout" : [ 'list', 'sep', 'first', 'prev', 'manual','next', 'last', 'links' ],
		"onSelectPage" : function(pageNumber, b) {
			//为两个隐藏域赋值  当前页页号   每页显示的记录数
			$("#yeHao").val(pageNumber);//当前页页号
			$("#jiLuShu").val(b);//每页显示的记录数

			//直接调用分页查询的方法
			initData();
		}
	});
}
//新版的分页条(最底部的那个) end
/**
 * 分页条 end
 */


/**
 * 清空按钮的点击事件 
 */
function clearBtn() {
	//alert("进入清空按钮的点击事件，实现代码还没写")
	$("#initName").val("");//清空姓名
	$("#initIdCard").val("");//清空身份证
	$("#el_breakSelect option:first").prop("selected", 'selected');//清空违章记分  
	$(".initsex").attr("checked", false); //清空性别
	$(".initBlack").attr("checked", false);//清空是否黑名单
	//清空时间段
	$("#inpstart2").val("");
	//清空时间段
	$("#inpend2").val("");
	//清空之后让黑名单选项可以选中
	$("#el_blackCheckbox").find("input").attr("disabled", false);
	
	//把用于返回页面数据的更新标记清空 设置为"0"
	$("#dataFlushReturn").val("0");
	
	//
	$("#findUnitBigBH").val("");
	$("#ffunitId").val("");
}

/**
 * 与左边的树绑定之后的点击查询按钮的点击事件  未点击黑名单  和点击了黑名单的情况
 */
/* QLQ写的清空页号 */
function clearPagenum() {
	$("#yeHao").val("1"),//当前页页号
	findSaveBtn();
}
/* QLQ写的清空页号 */
//findForm
function findSaveBtn() {
	//如果单位大修编号为空，则不能进行按条件查询(也就意味着还没有和左侧的树绑定)
	//if ($("#unitBigHual").val() == "") {
	//	alert("请先选中检修之后再查询")
	//} else {
		$.ajax({
				url : contextPath+"/breakrules_findMsgByCondictionFy.action",
				data : $("#findForm").serialize(),
				dataType : "json",
				type : "POST",
				async : true,
				success : function(data) {
					if (data.isBreak == "no") {
						//这个是没有点击黑名单的情况
						
						//每次查询之前都先清空表格中的数据
						$("#tbody tr").remove();
						var opStr = "";
						//alert("总数量"+data.selectLeftToTable.length)
						for (var i = 0; i < data.selectLeftToTable.length; i++) {

							var name = data.selectLeftToTable[i].name;//姓名
							var sex = data.selectLeftToTable[i].sex;//性别
							if (sex == "1") {
								sex = "男";
							} else if (sex == "2") {
								sex = "女";
							}
							var idCode = data.selectLeftToTable[i].idCode;//身份证
							var sumBreakScore = data.selectLeftToTable[i].sumBreakScore;//违章总记分
							var empType = data.selectLeftToTable[i].empType;//工种
							var employeeId = data.selectLeftToTable[i].employeeId;//外来职工表id
							var BigEmployeeoutId = data.selectLeftToTable[i].BigEmployeeoutId;//大修外来员工id
							var empOutphone = data.selectLeftToTable[i].empOutphone;//联系电话
							var unitName = data.selectLeftToTable[i].depName;//(所属部门名称)所属单位名称                  //unit.name AS depName部门
							var bigid = data.selectLeftToTable[i].bigId;//大修id
							var unitid = data.selectLeftToTable[i].unitId;//单位id
							var status = data.selectLeftToTable[i].status;//黑名单状态
							
							//解决underfined问题
							if (sumBreakScore >= 0) {

							} else {
								sumBreakScore = "0";
							}

							opStr += "<tr>";
							//隐藏域
							opStr += "<input name='el_ycy' type='hidden' value="+employeeId+">";//隐藏域，隐藏一个职工id employeeId
							opStr += "<input name='el_ycy' type='hidden' value="+data.selectLeftToTable[i].BigEmployeeoutId+">";//隐藏域，隐藏一个大修外来职工id BigEmployeeoutId
							opStr += "<input name='el_ycy' type='hidden' value="+"breakId"+">";//隐藏域，隐藏一个违章id
							opStr += "<input name='el_ycy' type='hidden' value='top'>";//隐藏域，隐藏一个标记，是左侧的树还是顶部的按条件分页查询
							opStr += "<input name='el_ycy' type='hidden' value="+bigid+">";//隐藏大修id
							opStr += "<input name='el_ycy' type='hidden' value="+data.selectLeftToTable[i].unitId+">";//隐藏单位id

							opStr += "<td><input type='radio' name='el_chooseBreakRules' class='el_checks' value="+empOutphone+"></td>";
							opStr += "<td>"	+ name+ "</td>";//姓名
							opStr += "<td>"	+ sex+ "</td>";//性别
							opStr += "<td>"	+ idCode+ "</td>";//身份证
							opStr += "<td>"+ sumBreakScore + "</td>";//违章记分
							//为隐藏职工id的隐藏域赋值
							$("#findEmpId").val(employeeId);
							if (status == "1") {
								$("#findIsBreak").val("是");
							} else {
								$("#findIsBreak").val("否");
							}
							var mark = $("#findIsBreak").val();//是否黑名单的标记，用于查看详情操作
							
							//黑名单状态有问题：应该这样处理：根据职工id去黑名单查询，如果有结果，则显示为"是"，否则显示为"否"
							if (status == "1") {
								opStr += "<td><font color='red'>"+ "是"+ "</font></td>";
							} else {
								if (sumBreakScore >= 12) {
									opStr += "<td><font color='blue'>"+ "否"+ "</font></td>";
								} else {
									opStr += "<td>"	+ "否"+ "</td>";
								}

							}
							//为大修的隐藏域赋值
							$("#haulInfoBigId").val(bigid);
							//根据大修id获取大修名字 hualInfoName
							$.ajax({
								url : contextPath+"/breakrules_getHaulInfoNameByBigId.action",
								data : {
									"bigid" : $("#haulInfoBigId").val()//大修id
								},
								dataType : "json",
								type : "POST",
								async : false,
								success : function(data) {
									$("#hualInfoName").val(data.haulInfoName);
								}
							});
							//opStr +="<td>"+$("#findIsBreak").val()+"</td>";
							opStr += "<td>"	+ unitName+ "</td>";//所属单位
							opStr += "<th>"	+ empType	+ "</th>";//工种
							opStr += "<th>"	+ $("#hualInfoName").val()+ "</th>";//工种

							opStr += "<td>";

							opStr += "<input type='hidden' value="+"empOutphone"+">";//隐藏域，隐藏一个联系电话 empOutphone
							opStr += "<input type='hidden' value="+"breakId"+">";//隐藏域，隐藏一个职工的违章id
							opStr += "<input type='hidden' value="+employeeId+">";//隐藏域，隐藏一个职工id employeeId
							opStr += "<input type='hidden' value="+BigEmployeeoutId+">";//隐藏域，隐藏一个大修外来职工id BigEmployeeoutId
							opStr += "<input type='hidden' value="+"breaktime"+">";//隐藏域，隐藏违章时间
							opStr += "<input type='hidden' value="+"breakcontent"+">";//隐藏域，隐藏违章内容
							opStr += "<input type='hidden' value='top'/>";//隐藏域，隐藏式操作左边的树的还是顶部的分页条的"&empOutphone="+empOutphone+
							//9
							opStr += "<input type='hidden' value="+name+">";//隐藏域，隐藏 姓名
							opStr += "<input type='hidden' value="+sex+">";//隐藏域，隐藏 性别
							opStr += "<input type='hidden' value="+mark+">";//隐藏域，隐藏 是否黑名单
							opStr += "<input type='hidden' value="+idCode+">";//隐藏域，隐藏 身份证号
							opStr += "<input type='hidden' value="+unitName+">";//隐藏域，隐藏 部门名称
							opStr += "<input type='hidden' value="+bigid+">";//隐藏域，隐藏大修id
							opStr += "<input type='hidden' value="+employeeId+">";//隐藏域，隐藏  员工id
							opStr += "<input type='hidden' value="+BigEmployeeoutId+">";//隐藏域，隐藏  BigEmployeeoutId
							opStr += "<input type='hidden' value="+ $("#unitBigHual").val()+ ">";//隐藏域，隐藏 unitBigHaul

							//查看详情
							opStr += "<a href='javascript:void(0)' onclick='detailBtn(this)'>查看详情</a>";
							
							opStr += "</td>";

							opStr += "</tr>";
						}
						$("#tbody").append(opStr);

						
						var count = data.count;//总记录数
						var curPage = data.curPage;//当前页页号
						var curTotal = data.curTotal;//每页显示的记录数
						//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
						queryFy2(count,curPage,curTotal);

					} else {
						//点击了黑名单的情况
						//每次查询之前都先清空表格中的数据
						$("#tbody tr").remove();
						var opStr = "";
						
						for (var i = 0; i < data.selectLeftToTable.length; i++) {

							var name = data.selectLeftToTable[i].name;//姓名
							var sex = data.selectLeftToTable[i].sex;//性别
							if (sex == "1") {
								sex = "男";
							} else if (sex == "2") {
								sex = "女";
							}
							var idCode = data.selectLeftToTable[i].idCode;//身份证
							var sumBreakScore = data.selectLeftToTable[i].sumBreakScore;//违章总记分
							var empType = data.selectLeftToTable[i].empType;//工种
							var employeeId = data.selectLeftToTable[i].employeeId;//外来职工表id
							var BigEmployeeoutId = data.selectLeftToTable[i].BigEmployeeoutId;//大修外来员工id
							var empOutphone = data.selectLeftToTable[i].empOutphone;//联系电话
							var unitName = data.selectLeftToTable[i].depName;//(所属部门名称)所属单位名称                  //unit.name AS depName部门
							var bigid = data.selectLeftToTable[i].bigId;//大修id
							var unitid = data.selectLeftToTable[i].unitId;//单位id
							var status = data.selectLeftToTable[i].status;//黑名单状态
							

							opStr += "<tr>";
							//隐藏域
							opStr += "<input name='el_ycy' type='hidden' value="+employeeId+">";//隐藏域，隐藏一个职工id employeeId
							opStr += "<input name='el_ycy' type='hidden' value="+data.selectLeftToTable[i].BigEmployeeoutId+">";//隐藏域，隐藏一个大修外来职工id BigEmployeeoutId
							opStr += "<input name='el_ycy' type='hidden' value="+"breakId"+">";//隐藏域，隐藏一个违章id
							opStr += "<input name='el_ycy' type='hidden' value='top'>";//隐藏域，隐藏一个标记，是左侧的树还是顶部的按条件分页查询
							opStr += "<input name='el_ycy' type='hidden' value="+bigid+">";//隐藏大修id
							opStr += "<input name='el_ycy' type='hidden' value="+data.selectLeftToTable[i].unitId+">";//隐藏单位id

							opStr += "<td><input type='radio' name='el_chooseBreakRules' class='el_checks' value="+empOutphone+"></td>";
							opStr += "<td>"+ name+ "</td>";//姓名
							opStr += "<td>"+ sex+ "</td>";//性别
							opStr += "<td>"	+ idCode+ "</td>";//身份证
							opStr += "<td>"	+ sumBreakScore+ "</td>";//违章记分
							//为隐藏职工id的隐藏域赋值
							$("#findEmpId").val(employeeId);
							if (status == "1") {
								$("#findIsBreak").val("是");
							} else {
								$("#findIsBreak").val("否");
							}

							var mark = $("#findIsBreak").val();//是否黑名单的标记，用于查看详情操作

							//黑名单状态
							if (status == "1") {
								opStr += "<td><font color='red'>"+ "是"	+ "</font></td>";
							} else {
								if (sumBreakScore >= 12) {
									opStr += "<td><font color='blue'>"+ "否"+ "</font></td>";
								} else {
									opStr += "<td>"+ "否"+ "</td>";
								}

							}

							//根据大修id获取大修名字 hualInfoName
							$.ajax({
									url : contextPath+"/breakrules_getHaulInfoNameByBigId.action",
									data : {
										"bigid" : $("#haulInfoBigId").val()
									//大修id
									},
									dataType : "json",
									type : "POST",
									async : false,
									success : function(data) {
										$("#hualInfoName").val(data.haulInfoName);
									}
								});

							//黑名单状态有问题：应该这样处理：根据职工id去黑名单查询，如果有结果，则显示为"是"，否则显示为"否"
							//opStr +="<td>"+$("#findIsBreak").val()+"</td>";
							opStr += "<td>"	+ unitName+ "</td>";//所属单位
							opStr += "<th>"	+ empType + "</th>";//工种
							opStr += " <th>"+ $("#hualInfoName").val()+ "</th>";//所属大修

							opStr += "<td>";

							opStr += "<input type='hidden' value="+empOutphone+">";//隐藏域，隐藏一个联系电话 empOutphone
							opStr += "<input type='hidden' value="+"breakId"+">";//隐藏域，隐藏一个职工的违章id
							opStr += "<input type='hidden' value="+employeeId+">";//隐藏域，隐藏一个职工id employeeId
							opStr += "<input type='hidden' value="+BigEmployeeoutId+">";//隐藏域，隐藏一个大修外来职工id BigEmployeeoutId
							opStr += "<input type='hidden' value="+"breaktime"+">";//隐藏域，隐藏违章时间
							opStr += "<input type='hidden' value="+"breakcontent"+">";//隐藏域，隐藏违章内容
							opStr += "<input type='hidden' value='top'/>";//隐藏域，隐藏式操作左边的树的还是顶部的分页条的"&empOutphone="+empOutphone+
							//9
							opStr += "<input type='hidden' value="+name+">";//隐藏域，隐藏 姓名
							opStr += "<input type='hidden' value="+sex+">";//隐藏域，隐藏 性别
							opStr += "<input type='hidden' value="+mark+">";//隐藏域，隐藏 是否黑名单
							opStr += "<input type='hidden' value="+idCode+">";//隐藏域，隐藏 身份证号
							opStr += "<input type='hidden' value="+unitName+">";//隐藏域，隐藏 部门名称
							opStr += "<input type='hidden' value="+bigid+">";//隐藏域，隐藏大修id
							opStr += "<input type='hidden' value="+employeeId+">";//隐藏域，隐藏  员工id
							opStr += "<input type='hidden' value="+BigEmployeeoutId+">";//隐藏域，隐藏  BigEmployeeoutId
							opStr += "<input type='hidden' value="+ $("#unitBigHual").val()	+ ">";//隐藏域，隐藏 unitBigHaul

							//查看详情
							opStr += "<a href='javascript:void(0)' onclick='detailBtn(this)'>查看详情</a>";
							
							opStr += "</td>";

							opStr += "</tr>";
						}
						$("#tbody").append(opStr);

						
						var count = data.count;//总记录数
						var curPage = data.curPage;//当前页页号
						var curTotal = data.curTotal;//每页显示的记录数
						
						//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
						queryFy2(count,	curPage,curTotal);

					}//else的括号								

				},//success回掉函数的括号
				error : function() {
					alert("error");
				}//error的括号
			});//ajax的括号
	//}//else的括号，用于判断单位大修编号是否为空的那个else啊
}

/**
 * 添加违章信息的保存按钮的点击事件及关联操作 start
 */
//<!-- 添加违章信息的保存按钮的点击事件-->
function addSaveBtn(){
	//添加前的违章总积分
	var addBreakScoreBefore = $("#beforeBreakScore").val();
	
	var addBreakScoreNow = $("#breakScoreID").val();//获取当前用户添加的违章记分，本次添加的
	
	if(addBreakScoreNow>=12){
		//给出一个警告提示
		//弹出警告的模态框
		$("#addAlertMsg").modal();
	}else{
		if(parseInt(addBreakScoreBefore)+parseInt(addBreakScoreNow)>=12){
			alert("该员工的违章记分累计超过12分");
		}
		
		//不给出警告提示
		addSaveBtnAfter();
	} 
}

//点击了警告模态框的 确定 按钮的点击事件
function addAlertMsgBtn(){
	addSaveBtnAfter();//执行添加信息的后续操作
}

//添加违章信息的后续操作
function addSaveBtnAfter(){
	//表单校验
	var isNotNull = $("#addForm").validate({
	ignore : [],
	rules : {
		"breakrules.breaktime" : "required",//违章时间
		"breakrules.minusnum" : {//违章记分
			"required":true,
			"isNumber":true
		},
		"breakrules.breakcontent" : "required"//违章内容
	},
	messages : {
		"breakrules.breaktime" : {//违章时间
			required : "不能为空"
		},//下边与上边对应
		"breakrules.minusnum" : {//违章记分
			required : "不能为空",
			isNumber:"请输入一个数字"
		},
		"breakrules.breakcontent":{
			required : "不能为空"
		}
	}

});
//表单校验结束						
	if(isNotNull.form()){	
		$.ajax({
			url:contextPath+"/breakrules_addBreakrule.action",
			data:$("#addForm").serialize(),
			dataType:"json",
			type:"POST",
			async:true,
			success:function(data){
				alert(data.result);
				//操作成功之后，再次分页查询数据(当作数据的刷新)
				if($("#allMark").val()=="left"){
					
					//执行左边的树的查询
					leftBtn();
				}else if($("#allMark").val()=="top"){
					
					//执行顶部的分页的查询
					findSaveBtn();
				}else if($("#allMark").val()=="initData"){
					initData();
				}
			},
			error:function(){
				alert("添加失败，请从新添加")
			}
		});
		//关闭模态框
		$('#el_addBreakInfo').modal("hide");
	}
}
/**
 * 添加违章信息的保存按钮的点击事件及关联操作 end
 */

/**
 * 点击修改之后的操作
 */
function modifyBreak(obj) {
	//初始化table表格中的数据
	
	var name = $(obj).parents("tr").children("td").eq(1).text();//姓名
	var sex = $(obj).parents("tr").children("td").eq(2).text();//性别
	var breakScore = $(obj).parents("tr").children("td").eq(4).text();//违章记分
	var isBreak = $(obj).parents("tr").children("td").eq(5).text();//黑名单状态
	var unitName = $(obj).parents("tr").children("td").eq(6).text();//所属部门(单位)
	//违章时间

	//违章记分upQianBreakGrade

	//违章内容
	/* 
	opStr +="<input type='hidden' value="+empOutphone+">";//隐藏域，隐藏一个联系电话 empOutphone
	opStr +="<input type='hidden' value="+breakId+">";//隐藏域，隐藏一个职工的违章id
	opStr +="<input type='hidden' value="+employeeId+">";//隐藏域，隐藏一个职工id employeeId
	opStr +="<input type='hidden' value="+employeeId+">";//隐藏域，隐藏一个大修外来职工id BigEmployeeoutId */
	var phone = $(obj).parent().children("input").eq(0).val();//联系电话
	var breakId = $(obj).parent().children("input").eq(1).val();//职工的违章id
	var employeeId = $(obj).parent().children("input").eq(2).val();//职工id
	var bigHaulemplyeeId = $(obj).parent().children("input").eq(3).val();//大修外来职工id
	var breakTime = $(obj).parent().children("input").eq(4).val();//违章时间
	var breakContent = $(obj).parent().children("input").eq(5).val();//违章内容

	var allmark = $(obj).parent().children("input").eq(6).val();//操作的标记，是左边的树还是顶部的按条件分页查询的标记
	$("#allMark").val(allmark);
	
	//为隐藏域赋值
	/*  <input id="upBreakId" type="hidden" value="" name=""/>
				<input id="upEmployee" type="hidden" value="" name=""/>
				<input id="upBigHaulEmployeeId" type="hidden" value="" name=""/> */
	$("#upBreakId").val(breakId);//职工的违章id
	$("#upEmployee").val(employeeId);//职工的id
	$("#upBigHaulEmployeeId").val(bigHaulemplyeeId);//大修外来职工id

	//初始化表格数据			
	$("#upName").text(name);//姓名
	$("#upPhone").text(phone);//联系电话
	$("#upbreakScore").text(breakScore);//违章记分
	$("#upunitName").text(unitName);//所属单位
	$("#upIsBreak").text(isBreak);//是否黑名单
	$("#upSex").text(sex);//性别
	

	//修改前的违章记分
	$("#upQianBreakGrade").val(breakScore);

	//初始化表单数据
	//违章时间
	$("#upBreakTime").val(breakTime);
	//违章记分
	$("#upbreakGrade").val(breakScore);
	//违章情况
	$("#upbreakContent").text(breakContent);

	//弹出模态框
	$('#modifyBreak').modal();
}

//点击修改按钮的提交更改按钮之后的操作，将数据提交到后台保存在数据库中
function upSaveBtn() {
	//表单校验
	//表单校验
	var isNotNull = $("#upForm").validate(
			{
				ignore : [],
				rules : {
					"breakrules.breaktime" : "required",//违章时间
					"breakrules.minusnum" : "required",//违章记分
					"breakrules.breakcontent" : "required"//违章内容
				},
				messages : {
					"breakrules.breaktime" : {//违章时间
						required : "不能为空"
					},//下边与上边对应
					"breakrules.minusnum" : {//违章记分
						required : "不能为空"
					},
					"breakrules.breakcontent" : {
						required : "不能为空"
					}
				}

			});
	//表单校验结束						
	if (isNotNull.form()) {
		//upForm
		$.ajax({
			url : contextPath+"/breakrules_updateBreakrules.action",
			data : $("#upForm").serialize(),
			dataType : "json",
			type : "POST",
			async : true,
			success : function(data) {
				alert(data.result)

				//操作成功之后，再次分页查询数据(当作数据的刷新)
				if ($("#allMark").val() == "left") {
					
					//执行左边的树的查询
					leftBtn();
				} else if ($("#allMark").val() == "top") {
					
					//执行顶部的分页的查询
					findSaveBtn();
				} else if ($("#allMark").val() == "initData") {
					initData();
				}
			},
			error : function() {
				alert("修改失败，请从新操作")
			}
		});
		//关闭模态框
		$('#modifyBreak').modal("hide");
	}
}


/**
 * 删除按钮的点击事件
 */
function delcfm(obj) {
	//获取违章id
	var delbreakId = $(obj).parent().children("input").eq(1).val();//职工的违章id
	var delemployeeId = $(obj).parent().children("input").eq(2).val();//职工的id
	

	
	 var allmark = $(obj).parent().children("input").eq(6).val();//操作的标记，是左边的树还是顶部的按条件分页查询的标记
	 //为标记隐藏域赋值
    $("#allMark").val(allmark);
	
	 
	$("#delEmployeeId").val(delemployeeId);//为隐藏域复制，职工的id
	$("#delEmployeeBreakId").val(delbreakId);//为隐藏域赋值 职工的违章id
	//打开模态框
    $('#delcfmModel').modal();
}

//点击确定按钮之后的操作，将该违章信息删除
function urlSubmit(){
	$.ajax({
		url:contextPath+"/breakrules_delBreakrules.action",
		data:{
			"delEmployeeId":$("#delEmployeeId").val(),//职工id
			"delEmployeeBreakId":$("#delEmployeeBreakId").val(),//职工违章id
			"unitBigHualId":$("#delunitId").val()	//单位大修编号
		},
		dataType:"json",
		type:"POST",
		async:true,
		success:function(data){
			alert(data.result)
			//操作成功之后，再次分页查询数据(当作数据的刷新)
			if($("#allMark").val()=="left"){
				alert("left")
				//执行左边的树的查询
				leftBtn();
			}else if($("#allMark").val()=="top"){
				alert("top")
				//执行顶部的分页的查询
				findSaveBtn();
			}else if($("#allMark").val()=="initData"){
				initData();
			}
			
			//关闭模态框
			$('#delcfmModel').modal("hide");
		},
		error:function(){
			alert("删除失败，请重新新操作");
		}
	});
	
}



/** *****************lixianyuan end ********* */
//查看历史违章信息
function historyBreakInfoFind(){
	var type = $("#el_breakType").val();
	$("#breakInfo_Type").val(type);
	$("#yeHao").val("1");
	findSaveBtn();
}