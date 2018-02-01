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
		var empName = $('input[name="el_chooseBreakRules"]:checked ').parents(
				"tr").children("td").eq(1).text();// 姓名
		// 联系方式
		var phone = $('input[name="el_chooseBreakRules"]:checked ').val();
		// 违章记分
		var score = $('input[name="el_chooseBreakRules"]:checked ').parents(
				"tr").children("td").eq(7).text();
		// 所属单位
		var unitName = $('input[name="el_chooseBreakRules"]:checked ').parents(
				"tr").children("td").eq(5).text();
		// 黑名单
		var isBreak = $('input[name="el_chooseBreakRules"]:checked ').parents(
				"tr").children("td").eq(8).text();
		// 性别
		var sex = $('input[name="el_chooseBreakRules"]:checked ').parents("tr")
				.children("td").eq(2).text();

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
		var empId = $('input[name="el_chooseBreakRules"]:checked')
				.parents("tr").children('input[name="el_ycy"]').eq(0).val();
		// 操作的标记 是左侧的树还是顶部的按条件分页查询
		var allmark = $('input[name="el_chooseBreakRules"]:checked').parents(
				"tr").children('input[name="el_ycy"]').eq(1).val();
		// 部门id
		var departmentid = $('input[name="el_chooseBreakRules"]:checked')
				.parents("tr").children('input[name="el_ycy"]').eq(2).val();
		// 职工的身份证号码
		var idcode = $('input[name="el_chooseBreakRules"]:checked').parents(
				"tr").children('input[name="el_ycy"]').eq(3).val();
		// 职工的部门类型
		var departMentType = $('input[name="el_chooseBreakRules"]:checked')
				.parents("tr").find(".query_departmentType").val();
		// 为员工id的隐藏域赋值
		$("#addEmpID").val(empId);
		// 为操作标记赋值
		$("#allMark").val(allmark);
		// 为身份证赋值
		$("#addIdCard").val(idcode);
		// 为隐藏的部门类型赋值
		$("#employeeDepartmentType").val(departMentType);
		// 为隐藏域赋值
		// 职工id

		// 为添加前的违章总积分隐藏域赋值
		$("#breakScoreSum").val(score);

		// 为违章记分、违章内容初始化，也就是初始化添加违章信息的表单
		// 初始化违章记分
		$("#breakScoreID").val("");
		// 初始化违章内容
		$("#addBreakContent").val("");

		if ($("#addIsBreak").text() == "是") {
			alert("处于永久黑名状态的长委职工不能添加违章积分！")
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

/* 员工详情 */
/*
 * function el_breakRulesInfo() { $('#el_breakRulesInfo').modal(); }
 */
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
// 初始化部门树
function searchDepartmentTree_2() {
	$.ajax({
		type : "post",
		target : "#treeDemo",
		dataType : "json",
		url : "empInbreakrules_initDepartment.action",
		success : getTree_2,
		error : function() {
			alert("请求树失败！");
		}
	});
}

/** *********************生成树信息********************* */
function getTree_2(treeList2) {
	var treeList3 = treeList2.departmentTree;
	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "departmentId",
				pIdKey : "upDepartmentId",
				rootPId : null,
			},
			key : {
				name : "departmentName",
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
	// var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	// treeObj.expandAll(false);
}

/** **************************************树的相关方法**************************************************************** */

$(function() {
	// 初始化树
	searchDepartmentTree_2();

	jQuery.validator.addMethod("isNumber", function(value, element) {
		var length = value.length;
		var tel = /(^\d+$)/;
		return this.optional(element) || (tel.test(value));
	}, "请输入数字");

	// 页面加载的时候初始化一些数据出来
	findSaveBtn();
	// initData();
})

/** *********************请求树信息********************* */

function showIconForTree(treeId, treeNode) {
	return !treeNode.isParent;
}

var clickRes = 0;
var getName;
var selectedDepartmentID;
var selectedOverHaulId;
function beforeClick(treeId, treeNode, clickFlag) {
	clearBtn();
	$("#yeHao").val("1");// 清空页号
	clickRes = 1;
	getName = treeNode.departmentName;// 当前选中的树的名字
	selectedDepartmentID = treeNode.departmentId;// 当前选中的树的id
	selectedOverHaulId = treeNode.upDepartmentId;// 当前选中的树的上级id

	// 隐藏当前选中的部门的部门id
	$("#departmentidTree").val(selectedDepartmentID);

	$("#queryDepartmentId").val(selectedDepartmentID);

	// 点击部门显示其及其子孙部门下的所有内部员工信息及其违章信息
	leftBtn();

	// console.log(selectedOverHaulId);
	return (treeNode.click != false);
}

// *********************页面加载初始化一些数据出来
// 页面初始化的
/*
 * function initData() {
 * 
 * $.ajax({ url : contextPath+"/empInbreakrules_initPageDate.action", data : {
 * "curPage" : $("#yeHao").val(),// 当前页页号 "curTotal" : $("#jiLuShu").val() //
 * 每页显示的记录数 }, dataType : "json", type : "POST", async : true, success :
 * function(data) { // alert("执行了左侧的树的回掉函数") // 每次查询之前都先清空表格中的数据 $("#tbody
 * tr").remove(); var opStr = ""; for (var i = 0; i <
 * data.empInMsgByDepIdLeft.length; i++) {
 * 
 * var name = data.empInMsgByDepIdLeft[i].name;// 姓名 var sex =
 * data.empInMsgByDepIdLeft[i].sex;// 性别 if (sex == "1") { sex = "男"; } else if
 * (sex == "2") { sex = "女"; } var idCode =
 * data.empInMsgByDepIdLeft[i].idCode;// 身份证 var sumBreakScore =
 * data.empInMsgByDepIdLeft[i].sumBreakScore;// 违章总记分 var phone =
 * data.empInMsgByDepIdLeft[i].phone;// 电话 var duty =
 * data.empInMsgByDepIdLeft[i].duty;// 职务 var unitName =
 * data.empInMsgByDepIdLeft[i].departmentName;// 部门名称 var employeeId =
 * data.empInMsgByDepIdLeft[i].employeeId;// 内部员工id var departmentid =
 * data.empInMsgByDepIdLeft[i].departmentId;// 部门id var blackStatus =
 * data.empInMsgByDepIdLeft[i].status;// 黑名单状态
 * 
 * opStr += "<tr>"; // 隐藏域 opStr += "<input name='el_ycy' type='hidden'
 * value="+ employeeId + ">";// 隐藏域，隐藏一个职工id employeeId opStr += "<input
 * name='el_ycy' type='hidden' value='initData'>";//
 * 隐藏域，隐藏一个标记，是左侧的树还是顶部的按条件分页查询 opStr += "<input name='el_ycy' type='hidden'
 * value="+ departmentid + ">";// 隐藏部门id opStr += "<input name='el_ycy'
 * type='hidden' value="+ idCode + ">";// 隐藏身份证
 * 
 * opStr += "<td><input type='radio' name='el_chooseBreakRules'
 * class='el_checks' value="+ phone + "></td>"; opStr += "<td>" + name + "</td>";//
 * 姓名 opStr += "<td>" + sex + "</td>";// 性别 opStr += "<td>" + idCode + "</td>";//
 * 身份证 opStr += "<td>" + sumBreakScore + "</td>";// 违章记分 // 为隐藏职工id的隐藏域赋值 if
 * (blackStatus == "1") { opStr += "<td><font color='red'>" + "是" + "</font></td>"; }
 * else { if (sumBreakScore >= 12) { opStr += "<td><font color='#F9BA0F'>" +
 * "否"+ "</font></td>"; } else { opStr += "<td>" + "否" + "</td>"; } }
 * 
 * opStr += "<td>" + unitName + "</td>";// 所属单位 opStr += " <th>" + duty + "</th>";//
 * 职务
 * 
 * opStr += "<td>";
 * 
 * opStr += "<input type='hidden' value=" + employeeId + ">";// 隐藏域，隐藏一个职工id
 * employeeId opStr += "<input type='hidden' value='initData'/>";//
 * 隐藏域，隐藏式操作左边的树的还是顶部的分页条的"&empOutphone="+empOutphone+ opStr += "<input
 * type='hidden' value=" + unitName + ">";// 隐藏域，隐藏部门名称
 * 
 * opStr += "<a href='javascript:void(0)' onclick='detailInBtn(this)'>详情</a>";
 * opStr += "</td>";
 * 
 * opStr += "</tr>"; } $("#tbody").append(opStr);
 * 
 * 
 * var count = data.count;// 总记录数 var curPage = data.curPage;// 当前页页号 var
 * curTotal = data.curTotal;// 每页显示的记录数 // 参数1：总记录数 参数2：当前页页号 参数3：每页显示的记录条数
 * queryFy4(count, curPage, curTotal); }, error : function() { alert("error"); }
 * }); }
 */

// 与左侧的树相关的，点击左侧的树之后，把其及其旗下的部门的员工显示出来
// 与查看详情相关的方法
function detailInBtn(obj) {
	// 向隐藏的时间文本框赋值
	$("#q_starttime").val($(".query_dep_starttime").val());
	$("#q_endtime").val($(".query_dep_endtime").val());
	$("#detailemployeeId").val(
			$(obj).parents("tr").find('input[name="el_ycy"]').eq(0).val());// 职工id
	$("#detailunitName").val(
			$(obj).parents('td').children('input').eq(5).html());// 部门名称

	$("#detail_breakInfoType").val($("#el_breakType").val());// 违章类型
	// 员工部门类型
	$("#detail_employeeDepartType").val(
			$(obj).parents("tr").find(".query_departmentType").val());

	var unitName = $(obj).parents("tr").find("#yincangunitName").val();// 部门名称
	$("#detailunitName").val(unitName);

	$("#detailInForm").submit();

}

// 新版的分页条(这个分页条是左边的树专用的啊) start
// 参数1：总记录数 参数2：当前页页号 参数3：每页显示的记录条数
function queryFy(resultCount, currentPage, currentTotal) {
	// 分页栏 start
	$('#paginationIDU').pagination(
			{
				// 组件属性
				"total" : resultCount,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : currentTotal,// 数字 每一页显示的数量 10
				"pageNumber" : parseInt(currentPage),// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8, 15, 20 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, b) {
					// 为两个隐藏域赋值 当前页页号 每页显示的记录数
					$("#yeHao").val(pageNumber),// 当前页页号
					$("#jiLuShu").val(b)// 每页显示的记录数
					// 执行左边的树的查询
					leftBtn();
				}
			});
}
// 新版的分页条(最底部的那个) end

// 新版的分页条(这个分页条是左边的树与分页条件绑定之后专用的 记住：这个是点击黑名单为"是"的情况) start
// 参数1：总记录数 参数2：当前页页号 参数3：每页显示的记录条数
function queryFy3(resultCount, currentPage, currentTotal) {
	// 分页栏 start
	$('#paginationIDU').pagination(
			{
				// 组件属性
				"total" : resultCount,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : currentTotal,// 数字 每一页显示的数量 10
				"pageNumber" : parseInt(currentPage),// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8, 15, 20 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, b) {
					$("#yeHao").val(pageNumber),// 当前页页号
					$("#jiLuShu").val(b)// 每页显示的记录数
					findSaveBtn();
				}
			});
}
// 新版的分页条(最底部的那个) end

// 新版的分页条(用于页面初始化) start
// 参数1：总记录数 参数2：当前页页号 参数3：每页显示的记录条数
/*
 * function queryFy4(resultCount, currentPage, currentTotal) { // 分页栏 start
 * $('#paginationIDU').pagination({ // 组件属性 "total" : resultCount,// 数字
 * 当分页建立时设置记录的总数量 1 "pageSize" : currentTotal,// 数字 每一页显示的数量 10 "pageNumber" :
 * parseInt(currentPage),// 数字 当分页建立时，显示的页数 1 "pageList" : [ 8, 15, 20 ],// 数组
 * 用户可以修改每一页的大小， // 功能 "layout" : [ 'list', 'sep', 'first', 'prev', 'manual',
 * 'next','last', 'links' ], "onSelectPage" : function(pageNumber, b) { //
 * 设置当前页号 $("#yeHao").val(pageNumber); // 设置每页显示的记录数 $("#jiLuShu").val(b); //
 * 刷新数据 initData(); } }); }
 */
// 新版的分页条(最底部的那个) end
// 点击左侧的树之后的 分页查询
function leftBtn() {
	$
			.ajax({
				url : contextPath
						+ "/empInbreakrules_clickLeftTreeShowMsg.action",
				data : {
					"departmentid" : $("#departmentidTree").val(),// 部门id
					"curPage" : $("#yeHao").val(),// 当前页页号
					"curTotal" : $("#jiLuShu").val(),// 每页显示的记录数
					"empBreakInfoType" : $("#el_breakType").val()
				},
				dataType : "json",
				type : "POST",
				async : true,
				success : function(data) {
					// 每次查询之前都先清空表格中的数据
					$("#tbody tr").remove();
					var opStr = "";
					// alert("总数量"+data.selectLeftToTable.length)
					for (var i = 0; i < data.empInMsgByDepIdLeft.length; i++) {

						/*
						 * var name = data.empInMsgByDepIdLeft[i].name;// 姓名 var
						 * sex = data.empInMsgByDepIdLeft[i].sex;// 性别 if (sex ==
						 * "1") { sex = "男"; } else if (sex == "2") { sex = "女"; }
						 * var idCode = data.empInMsgByDepIdLeft[i].idCode;//
						 * 身份证 var sumBreakScore =
						 * data.empInMsgByDepIdLeft[i].sumBreakScore;// 违章总记分
						 * var phone = data.empInMsgByDepIdLeft[i].phone;// 电话
						 * var duty = data.empInMsgByDepIdLeft[i].duty;// 职务 var
						 * unitName =
						 * data.empInMsgByDepIdLeft[i].departmentName;// 部门名称
						 * var employeeId =
						 * data.empInMsgByDepIdLeft[i].employeeId;// 内部员工id var
						 * departmentid =
						 * data.empInMsgByDepIdLeft[i].departmentId;// 部门id var
						 * blackStatus = data.empInMsgByDepIdLeft[i].status;//
						 * 黑名单状态
						 * 
						 * opStr += "<tr>"; // 隐藏域 opStr += "<input
						 * name='el_ycy' type='hidden' value="+ employeeId +
						 * ">";// 隐藏域，隐藏一个职工id employeeId opStr += "<input
						 * name='el_ycy' type='hidden' value='left'>";//
						 * 隐藏域，隐藏一个标记，是左侧的树还是顶部的按条件分页查询 opStr += "<input
						 * name='el_ycy' type='hidden' value="+ departmentid +
						 * ">";// 隐藏部门id opStr += "<input name='el_ycy'
						 * type='hidden' value="+ idCode + ">";// 隐藏身份证
						 * 
						 * opStr += "<td><input type='radio'
						 * name='el_chooseBreakRules' class='el_checks' value="+
						 * phone + "></td>"; opStr += "<td>" + name + "</td>";//
						 * 姓名 opStr += "<td>" + sex + "</td>";// 性别 opStr += "<td>" +
						 * idCode + "</td>";// 身份证 opStr += "<td>" +
						 * sumBreakScore + "</td>";// 违章记分 // 为隐藏职工id的隐藏域赋值 if
						 * (blackStatus == "1") { opStr += "<td><font
						 * color='red'>" + "是" + "</font></td>"; } else { if
						 * (sumBreakScore >= 12) { opStr += "<td><font
						 * color='#F9BA0F'>" + "否"+ "</font></td>"; } else {
						 * opStr += "<td>" + "否" + "</td>"; } }
						 * 
						 * opStr += "<td>" + unitName + "</td>";// 所属单位
						 * opStr += " <th>" + duty + "</th>";// 职务
						 * 
						 * opStr += "<td>";
						 * 
						 * opStr += "<input type='hidden' value=" + employeeId +
						 * ">";// 隐藏域，隐藏一个职工id employeeId opStr += "<input
						 * type='hidden' value='left'/>";//
						 * 隐藏域，隐藏式操作左边的树的还是顶部的分页条的"&empOutphone="+empOutphone+
						 * opStr += "<input type='hidden' value=" + unitName +
						 * ">";// 隐藏域，隐藏部门名称
						 * 
						 * opStr += "<a href='javascript:void(0)'
						 * onclick='detailInBtn(this)'>详情</a>"; opStr += "</td>";
						 * 
						 * opStr += "</tr>";
						 */

						var name = data.empInMsgByDepIdLeft[i].name;// 姓名
						var sex = data.empInMsgByDepIdLeft[i].sex;// 性别
						if (sex == "1") {
							sex = "男";
						} else if (sex == "2") {
							sex = "女";
						}
						var idCode = data.empInMsgByDepIdLeft[i].idCode;// 身份证

						var sumBreakScore = data.empInMsgByDepIdLeft[i].sumBreakScore;// 违章总记分
						var phone = data.empInMsgByDepIdLeft[i].phone;// 电话
						var duty = data.empInMsgByDepIdLeft[i].duty;// 职务
						var unitName = data.empInMsgByDepIdLeft[i].departmentName;// 部门名称
						var employeeId = data.empInMsgByDepIdLeft[i].employeeId;// 内部员工id
						var departmentid = data.empInMsgByDepIdLeft[i].departmentId;// 部门id
						var blackStatus = data.empInMsgByDepIdLeft[i].status;// 黑名单状态
						var birthday = data.empInMsgByDepIdLeft[i].birthday;// 出生日期

						opStr += "<tr>";
						// 隐藏域
						opStr += "<input name='el_ycy' type='hidden' value="
								+ employeeId + ">";// 隐藏域，隐藏一个职工id employeeId
						opStr += "<input name='el_ycy' type='hidden' value='top'>";// 隐藏域，隐藏一个标记，是左侧的树还是顶部的按条件分页查询
						opStr += "<input name='el_ycy' type='hidden' value="
								+ departmentid + ">";// 隐藏部门id
						opStr += "<input name='el_ycy' type='hidden' value="
								+ idCode + ">";// 隐藏身份证
						opStr += "<input  type='hidden' class='query_departmentType' value="
								+ data.empInMsgByDepIdLeft[i].departmentType
								+ ">";// 隐藏部门类型
						opStr += "<input  type='hidden' class='query_address' value="
								+ data.empInMsgByDepIdLeft[i].address + ">";// 隐藏家庭住址

						opStr += "<td><input type='radio' name='el_chooseBreakRules' class='el_checks' value="
								+ employeeId + "></td>";
						opStr += "<td>" + name + "</td>";// 姓名
						opStr += "<td>" + sex + "</td>";// 性别

						opStr += "<td>"
								+ Format(new Date(birthday.replace(/T/g, " ")
										.replace(/-/g, "/")), "yyyy-MM-dd")
								+ "</td>";// 出生日期
						opStr += "<td>" + phone + "</td>";// 电话
						opStr += "<td>" + unitName + "</td>";
						opStr += "<td>" + duty + "</td>";// 职务

						/*
						 * opStr += "<td><a href='javascript:void(0)'
						 * class='el_delButton' onclick='detailInBtn(this)>"+
						 * sumBreakScore+ "</a></td>";//违章记分
						 */
						// opStr += "<td>"+ sumBreakScore+ "</td>";//违章记分
						opStr += "<td><a href='javascript:void(0)' class='el_delButton' onclick='detailInBtn(this)'>"
								+ sumBreakScore + "</a></td>";// 违章记分
						// 为隐藏职工id的隐藏域赋值
						// 为隐藏职工id的隐藏域赋值
						if (blackStatus == "1") {
							opStr += "<td><font color='red'>" + "是"
									+ "</font></td>";
						} else {
							if (sumBreakScore >= 12) {
								opStr += "<td><font color='#F9BA0F'>" + "否"
										+ "</font></td>";
							} else {
								opStr += "<td>" + "否" + "</td>";
							}
						}
						opStr += "<td>";

						opStr += "<input type='hidden' value=" + employeeId
								+ ">";// 隐藏域，隐藏一个职工id employeeId
						opStr += "<input type='hidden' value='top'/>";// 隐藏域，隐藏式操作左边的树的还是顶部的分页条的"&empOutphone="+empOutphone+
						opStr += "<input type='hidden' id='yincangunitName' value="
								+ unitName + ">";// 隐藏域，隐藏部门名称

						// 员工的隐藏域
						opStr += "<input type='hidden' id='employeeid' value= '"
								+ employeeId + "'/>";

						opStr += "<input type='hidden' id='myidCode' value="
								+ idCode + ">";

						opStr += "<input type='hidden' id='departmentid' value='"
								+ departmentid + "'/>";
						opStr += "<input type='hidden' id='employeephoto' value='"
								+ data.empInMsgByDepIdLeft[i].photo + "'/>";
						opStr += "<input type='hidden' id='employeebirthday' value='"
								+ birthday + "'/>";
						opStr += "<input type='hidden' id='zhiwu' value='"
								+ duty + "'/>";

						var finger;
						if (data.empInMsgByDepIdLeft[i] != null) {
							finger = data.empInMsgByDepIdLeft[i].finger;
						} else {
							finger = "无";
						}
						opStr += "<input type='hidden' id='employeefinger' value='"
								+ finger + "'/>";
						opStr += "<input type='hidden' id='employeeminusnum' value='"
								+ data.empInMsgByDepIdLeft[i].trainstatus
								+ "'/>";

						opStr += "<a onclick='allInfo(this)' title='查看详情' class='el_delButton'><span class='glyphicon glyphicon-search'></span></a>&nbsp;";
						if (hasOperatingEmpin) {
							opStr += "<a title='修改员工信息' href='/Exam/employeein_toUpdateEmployeeIn.action?method="
									+ employeeId
									+ "'><span class='glyphicon glyphicon-pencil'></span></a>&nbsp;";
						}
						if (hasOperatingEmpin) {
							opStr += "<a class='el_delButton' title='删除员工' onclick='deleteEmployeeIn(this)' ><span class='glyphicon glyphicon-trash'></span></a>";
						}
						opStr += "</td>";

						opStr += "</tr>";

					}
					$("#tbody").append(opStr);

					var count = data.count;// 总记录数
					var curPage = data.curPage;// 当前页页号
					var curTotal = data.curTotal;// 每页显示的记录数
					// 参数1：总记录数 参数2：当前页页号 参数3：每页显示的记录条数
					queryFy(count, curPage, curTotal);
				},
				error : function() {
					alert("error");
				}
			});
}

/**
 * 与左边的树绑定之后的点击查询按钮的点击事件 未点击黑名单 和点击了黑名单的情况
 */
function clearPagenum() {
	$("#yeHao").val("1");
	findSaveBtn();
}
function findSaveBtn() {
	// 设置违章积分类型
	var type = $("#el_breakType").val();
	$("#breakInfo_Type").val(type);
	$
			.ajax({
				url : contextPath
						+ "/empInbreakrules_leftTreeAndConditionShowMsg.action",
				data : $("#findForm").serialize(),
				dataType : "json",
				type : "POST",
				async : true,
				success : function(data) {

					$("#tbody tr").remove();
					var opStr = "";
					for (var i = 0; i < data.empInMsgByDepIdLeft.length; i++) {
						/*
						 * var name = data.empInMsgByDepIdLeft[i].name;//姓名 var
						 * sex = data.empInMsgByDepIdLeft[i].sex;//性别 if (sex ==
						 * "1") { sex = "男"; } else if (sex == "2") { sex = "女"; }
						 * var idCode = data.empInMsgByDepIdLeft[i].idCode;//身份证
						 * var sumBreakScore =
						 * data.empInMsgByDepIdLeft[i].sumBreakScore;//违章总记分 var
						 * phone = data.empInMsgByDepIdLeft[i].phone;//电话 var
						 * duty = data.empInMsgByDepIdLeft[i].duty;//职务 var
						 * unitName =
						 * data.empInMsgByDepIdLeft[i].departmentName;//部门名称 var
						 * employeeId =
						 * data.empInMsgByDepIdLeft[i].employeeId;//内部员工id var
						 * departmentid =
						 * data.empInMsgByDepIdLeft[i].departmentId;//部门id var
						 * blackStatus =
						 * data.empInMsgByDepIdLeft[i].status;//黑名单状态
						 * 
						 * opStr += "<tr>"; //隐藏域 opStr += "<input
						 * name='el_ycy' type='hidden'
						 * value="+employeeId+">";//隐藏域，隐藏一个职工id employeeId
						 * opStr += "<input name='el_ycy' type='hidden'
						 * value='top'>";//隐藏域，隐藏一个标记，是左侧的树还是顶部的按条件分页查询 opStr += "<input
						 * name='el_ycy' type='hidden'
						 * value="+departmentid+">";//隐藏部门id opStr += "<input
						 * name='el_ycy' type='hidden'
						 * value="+idCode+">";//隐藏身份证
						 * 
						 * opStr += "<td><input type='radio'
						 * name='el_chooseBreakRules' class='el_checks'
						 * value="+phone+"></td>"; opStr += "<td>" + name+ "</td>";//姓名
						 * opStr += "<td>" + sex+ "</td>";//性别 opStr += "<td>"+
						 * idCode+ "</td>";//身份证 opStr += "<td>"+
						 * sumBreakScore+ "</td>";//违章记分 //为隐藏职工id的隐藏域赋值
						 * //为隐藏职工id的隐藏域赋值 if (blackStatus == "1") { opStr += "<td><font
						 * color='red'>"+ "是"+ "</font></td>"; } else { if
						 * (sumBreakScore >= 12) { opStr += "<td><font
						 * color='#F9BA0F'>"+ "否"+ "</font></td>"; } else {
						 * opStr += "<td>"+ "否"+ "</td>"; } }
						 * 
						 * opStr += "<td>" + unitName+ "</td>";//所属单位 opStr += "
						 * <th>" + duty+ "</th>";//职务
						 * 
						 * opStr += "<td>";
						 * 
						 * opStr += "<input type='hidden'
						 * value="+employeeId+">";//隐藏域，隐藏一个职工id employeeId
						 * opStr += "<input type='hidden'
						 * value='top'/>";//隐藏域，隐藏式操作左边的树的还是顶部的分页条的"&empOutphone="+empOutphone+
						 * opStr += "<input type='hidden'
						 * value="+unitName+">";//隐藏域，隐藏部门名称
						 * 
						 * opStr += "<a href='javascript:void(0)'
						 * class='el_delButton' onclick='detailInBtn(this)'>详情</a>";
						 * opStr += "</td>";
						 * 
						 * opStr += "</tr>";
						 */

						var name = data.empInMsgByDepIdLeft[i].name;// 姓名
						var sex = data.empInMsgByDepIdLeft[i].sex;// 性别
						if (sex == "1") {
							sex = "男";
						} else if (sex == "2") {
							sex = "女";
						}
						var idCode = data.empInMsgByDepIdLeft[i].idCode;// 身份证

						var sumBreakScore = data.empInMsgByDepIdLeft[i].sumBreakScore;// 违章总记分
						var phone = data.empInMsgByDepIdLeft[i].phone;// 电话
						var duty = data.empInMsgByDepIdLeft[i].duty;// 职务
						var unitName = data.empInMsgByDepIdLeft[i].departmentName;// 部门名称
						var employeeId = data.empInMsgByDepIdLeft[i].employeeId;// 内部员工id
						var departmentid = data.empInMsgByDepIdLeft[i].departmentId;// 部门id
						var blackStatus = data.empInMsgByDepIdLeft[i].status;// 黑名单状态
						var birthday = data.empInMsgByDepIdLeft[i].birthday;// 出生日期

						opStr += "<tr>";
						// 隐藏域
						opStr += "<input name='el_ycy' type='hidden' value="
								+ employeeId + ">";// 隐藏域，隐藏一个职工id employeeId
						opStr += "<input name='el_ycy' type='hidden' value='top'>";// 隐藏域，隐藏一个标记，是左侧的树还是顶部的按条件分页查询
						opStr += "<input name='el_ycy' type='hidden' value="
								+ departmentid + ">";// 隐藏部门id
						opStr += "<input name='el_ycy' type='hidden' value="
								+ idCode + ">";// 隐藏身份证
						opStr += "<input  type='hidden' class='query_departmentType' value="
								+ data.empInMsgByDepIdLeft[i].departmentType
								+ ">";// 隐藏部门类型
						opStr += "<input  type='hidden' class='query_address' value="
								+ data.empInMsgByDepIdLeft[i].address + ">";// 隐藏家庭住址
						opStr += "<td><input type='radio' name='el_chooseBreakRules' class='el_checks' value="
								+ phone + "></td>";
						opStr += "<td>" + name + "</td>";// 姓名
						opStr += "<td>" + sex + "</td>";// 性别
						// opStr += "<td>" + birthday+ "</td>";//出生日期

						opStr += "<td>"
								+ Format(new Date(birthday.replace(/T/g, " ")
										.replace(/-/g, "/")), "yyyy-MM-dd")
								+ "</td>";// 出生日期
						opStr += "<td>" + phone + "</td>";// 电话
						opStr += "<td>" + unitName + "</td>";
						opStr += "<td>" + duty + "</td>";// 职务

						opStr += "<td><a href='javascript:void(0)' class='el_delButton' onclick='detailInBtn(this)'>"
								+ sumBreakScore + "</a></td>";// 违章记分

						// opStr += "<td><a href='javascript:void(0)'
						// class='el_delButton'
						// onclick='detailInBtn(this)>vjfjkgl</a></td>";//违章记分

						// /opStr += "<td><a a href='javascript:void(0)'
						// class='el_delButton'
						// onclick='detailInBtn(this)'>vjfjkgl</a></td>";//违章记分

						// 为隐藏职工id的隐藏域赋值
						// 为隐藏职工id的隐藏域赋值
						if (blackStatus == "1") {
							opStr += "<td><font color='red'>" + "是"
									+ "</font></td>";
						} else {
							if (sumBreakScore >= 12) {
								opStr += "<td><font color='#F9BA0F'>" + "否"
										+ "</font></td>";
							} else {
								opStr += "<td>" + "否" + "</td>";
							}
						}
						opStr += "<td>";

						opStr += "<input type='hidden' value=" + employeeId
								+ ">";// 隐藏域，隐藏一个职工id employeeId
						opStr += "<input type='hidden' value='top'/>";// 隐藏域，隐藏式操作左边的树的还是顶部的分页条的"&empOutphone="+empOutphone+
						opStr += "<input type='hidden' id='yincangunitName' value="
								+ unitName + ">";// 隐藏域，隐藏部门名称

						// 员工的隐藏域
						opStr += "<input type='hidden' id='employeeid' value= '"
								+ employeeId + "'/>";
						opStr += "<input type='hidden' id='myidCode' value="
								+ idCode + ">";

						opStr += "<input type='hidden' id='departmentid' value='"
								+ departmentid + "'/>";
						opStr += "<input type='hidden' id='employeephoto' value='"
								+ data.empInMsgByDepIdLeft[i].photo + "'/>";
						opStr += "<input type='hidden' id='employeebirthday' value='"
								+ birthday + "'/>";
						opStr += "<input type='hidden' id='zhiwu' value='"
								+ duty + "'/>";

						var finger;
						if (data.empInMsgByDepIdLeft[i] != null) {
							finger = data.empInMsgByDepIdLeft[i].finger;
						} else {
							finger = "无";
						}
						opStr += "<input type='hidden' id='employeefinger' value='"
								+ finger + "'/>";
						opStr += "<input type='hidden' id='employeeminusnum' value='"
								+ data.empInMsgByDepIdLeft[i].trainstatus
								+ "'/>";

						opStr += "<a onclick='allInfo(this)' class='el_delButton' title='查看详情'><span class='glyphicon glyphicon-search'></span></a>&nbsp;";
						if (hasOperatingEmpin) {
							opStr += "<a title='修改员工信息' href='/Exam/employeein_toUpdateEmployeeIn.action?method="
									+ employeeId
									+ "'><span class='glyphicon glyphicon-pencil'></span></a>&nbsp;";
							opStr += "<a class='el_delButton' onclick='deleteEmployeeIn(this)' title='删除员工' ><span class='glyphicon glyphicon-trash'></span></a>";
						}

						opStr += "</td>";

						opStr += "</tr>";
					}
					$("#tbody").append(opStr);

					var count = data.count;// 总记录数
					var curPage = data.curPage;// 当前页页号
					var curTotal = data.curTotal;// 每页显示的记录数

					// 参数1：总记录数 参数2：当前页页号 参数3：每页显示的记录条数
					queryFy3(count, curPage, curTotal);

				},// success回掉函数的括号
				error : function() {
					alert("error");
				}// error的括号
			});// ajax的括号
}// 方法的括号

/**
 * 清空按钮的点击事件
 */
function clearBtn() {
	$("#inpstart2").val("");// 清空姓名
	$("#inpend2").val("");// 清空身份证
	$("#initName").val("");// 清空姓名
	$("#initIdCard").val("");// 清空身份证
	$("#el_breakSelect option:first").prop("selected", 'selected');// 清空违章记分
	$(".initsex").attr("checked", false); // 清空性别
	$(".initBlack").attr("checked", false);// 清空是否黑名单
	// 清空时间段
	$("#inpstart2").val("");
	// 清空时间段
	$("#inpend2").val("");
	// 清空之后让黑名单选项可以选中
	$("#el_blackCheckbox").find("input").attr("disabled", false);

	//
	$("#departmentidTree").val("");
	$(".curSelectedNode").removeClass("curSelectedNode");
}

/**
 * 添加违章信息的保存按钮的点击事件及关联操作 start
 */
// <!-- 添加违章信息的保存按钮的点击事件-->
function addSaveBtn() {
	// 添加前的违章总积分
	var addBeforeBreakScore = $("#breakScoreSum").val();

	// 员工的部门类型
	var employeeDepartmentType = $("#employeeDepartmentType").val();
	// alert(addBeforeBreakScore+"添加前")

	if ($("#breakScoreID").val() >= 12 && employeeDepartmentType == 1) {
		// 弹出警告的模态框
		$("#addyAlertModel2").modal();
	} else {
		if (parseInt(addBeforeBreakScore) + parseInt($("#breakScoreID").val()) >= 12) {
			alert("该员工的违章记分累计超过12分");
		}
		// 如果本次添加的违章记分<12分，就不给警告提示
		addSaveAfterBtn();
	}
}

/**
 * 给出"正式员工积分12分为厂级下岗，8分为部门内部下岗，"的提醒 的方法
 */
function addAlertMsg() {
	// $("#addEmpID").val(empId);//当天添加记分的员工id
	$.ajax({
		url : contextPath
				+ "/empInbreakrules_getSingleEmplyInBreakScoreSum.action",
		data : {
			"employeeid" : $("#addEmpID").val()
		},
		dataType : "json",
		type : "POST",
		async : true,
		success : function(data) {
			// 给出提示
			if ((data.result >= 8) && (data.result < 12)) {
				alert("该员工部门内部下岗");
			} else if (data.result >= 12) {
				alert("该员工厂级下岗");
			}
		},
	});
}

// 点击了警告提示框 的确定按钮之后的事件
function addAlertModelBtn2() {
	// 执行添加违章记分的后续操作
	addSaveAfterBtn();
}

// 进入添加模态框 之后点击确定时的后续操作
function addSaveAfterBtn() {
	// 表单校验
	var isNotNull = $("#addForm").validate({
		ignore : [],
		rules : {
			"emplyinBreakrules.empinbreaktime" : "required",// 违章时间
			"emplyinBreakrules.empinminusnum" : {// 违章记分
				"required" : true,
				"isNumber" : true
			},
			"emplyinBreakrules.empinbreakcontent" : "required"// 违章内容
		},
		messages : {
			"emplyinBreakrules.empinbreaktime" : {// 违章时间
				required : "不能为空"
			},// 下边与上边对应
			"emplyinBreakrules.empinminusnum" : {// 违章记分
				required : "不能为空",
				isNumber : "请输入一个数字"
			},
			"emplyinBreakrules.empinbreakcontent" : {
				required : "不能为空"
			}
		}
	});
	// 表单校验结束
	if (isNotNull.form()) {
		$.ajax({
			url : contextPath + "/empInbreakrules_addEmpInBreakrules.action",
			data : $("#addForm").serialize(),
			dataType : "json",
			type : "POST",
			async : true,
			success : function(data) {
				alert(data.result);
				// 操作成功之后，再次分页查询数据(当作数据的刷新)
				if ($("#allMark").val() == "left") {
					// alert("left")
					// 执行左边的树的查询
					leftBtn();
				} else if ($("#allMark").val() == "top") {
					// 执行顶部的分页的查询
					findSaveBtn();
				}/*
					 * else if($("#allMark").val()=="initData"){ initData(); }
					 */
				// 记分添加完成之后，给出"正式员工积分12分为厂级下岗，8分为部门内部下岗，"的提醒
				addAlertMsg();
			},
			error : function() {
				alert("添加失败，请重新添加")
			}
		});
		// 关闭模态框
		$('#el_addBreakInfo').modal("hide");
	}

}
/**
 * 添加违章信息的保存按钮的点击事件及关联操作 end
 */

/** *****************lixianyuan end ********* */
// 查看历史违章信息
function historyBreakInfoFind() {
	var type = $("#el_breakType").val();
	$("#breakInfo_Type").val(type);
	$("#yeHao").val("1");
	findSaveBtn();
}

/** ***********************************zwy start******* */
/**
 * 删除员工
 */
/*
 * 点击删除按钮
 */

function deleteEmployeeIn(obj) {

	// 获取单位编号
	// var employeeid = $(obj).parents("tr").find(input[name="el_ycy"]).val();

	var employeeid = $(obj).parents("tr").find('input[name="el_ycy"]').eq(0)
			.val();

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
	var idcode = $(obj).parents("tr").children('input[name="el_ycy"]').eq(3)
			.val();
	// 家庭住址
	var address = $(obj).parents("tr").find(".query_address").val();
	$("#InfoAddress").text(address);
	// 图片
	// $("[href='default.htm']")contextPath+

	$("#myimg").attr("src", "/files/EmployeeIn/" + employeephoto);

	$("#InfoName").html($tds.eq(1).html());
	$("#InfoSex").html($tds.eq(2).html());
	$("#InfoBirthday").html($tds.eq(3).html());
	$("#InfoPhone").html($tds.eq(4).html());
	// $("#InfoIdcode").html($tds.eq(7).html());
	$("#InfoIdcode").html(idcode);
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

// 得到部门名称
function getDepartmentName2(departmentid) {

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
			$("#detailunitName").val(result.departmentname);
		}
	});

}

// 得到部门名称
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
		// if (aged) {
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
													idCardArrays.push(idCard);
													var showAddEmployeeInInfo = "<tr><td>"
															+ name
															+ "</td><td>"
															+ sex
															+ "</td><td>"
															+ idCard
															+ "</td><td>"
															+ options.text()
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

													$("#addEmployeeOutInfoList")
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
													idCardArrays.splice(i, 1);
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
		// }
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

/**
 * 员工培训档案
 */

function el_empTrainDoc() {

	if ($("input[name='el_chooseBreakRules']:checked").length == "0") {
		alert("请选择一名员工！");

	} else {

		$tds = $("input[name='el_chooseBreakRules']:checked").parents('tr')
				.find('td');
		// 分别将这些数据放到修改模态框中
		/*
		 * var employeeminusnum =
		 * $("input[name='el_chooseBreakRules']:checked").parents('tr').find("#employeeminusnum").val();
		 */
		// 员工信息
		$("#TrainName").html($tds.eq(1).html());
		$("#TrainSex").html($tds.eq(2).html());

		$("#TrainPhone").html($tds.eq(4).html());
		// 显示头像

		var employeephoto = $("input[name='el_chooseBreakRules']:checked")
				.parents("tr").find("#employeephoto").val();
		$("#myimg2").attr("src", "/files/EmployeeIn/" + employeephoto);
		/* $("#TrainblackkList").html("黑名单"); */

		$("#TrainUnit").html($tds.eq(5).html());
		/* $("#TrainMinusnum").html(employeeminusnum); */
		$("#el_empTrainDoc").modal();
		var employeeid = $("input[name='el_chooseBreakRules']:checked")
				.parents("tr").find("#employeeid").val();
		// alert(employeeid);

		var idcode = $("input[name='el_chooseBreakRules']:checked").parents(
				"tr").find("#myidCode").val();
		// 身份证

		// var idcode = $tds.eq(7).html()

		$("#hiddenidcode").val(idcode);
		EmpTrainDoc();

	}

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

/**
 * 判断是否只显示管理员
 */
function isShowOnlyManager() {
	var isManager = $("#el_showManager").val();

	$("#el_showManagerInput").val(isManager);

	// 进行查询
	$("#currentPage").val("");// 清空页号
	findSaveBtn();
}

/** *******************************zwy end********************** */
/** ***************** S 消息框 QLQ********************* */
$(function() {
	showMessage();
});
function showMessage() {

	var msg = document.getElementById("msg");
	queryOlderPerson();
	msg.firstElementChild.onclick = function() { // this->a
		msg.style.bottom = "-300px";
		setTimeout(function() {
			queryOlderPerson();
		}, 60*60*1000);
	}
}
/**
 * 查询内部超过55岁的人
 */
function queryOlderPerson() {
	$.post(contextPath + '/message_getUnreadMessageByEmpType.action', {
		"empType" : "1"
	}, showOlderTable, 'json');
}
// 查询结果填入表格
function showOlderTable(response) {
	$("#olderTable").html("");
	var msg = document.getElementById("msg");
	var messages = response.messages;
	if (messages == null) {
		msg.style.bottom = "-300px";
		return;
	}
	for (var i = 0, length_1 = messages.length; i < length_1; i++) {
		$("#olderTable")
				.append(
						'<tr><td>'
								+ messages[i].name
								+ '<input type="hidden" class="messageid" value="'
								+ messages[i].messageid
								+ '">'
								+ '</td><td>'
								+ messages[i].idcode
								+ '</td><td>'
								+ messages[i].birthday
								+ '</td><td><a class="button" href=javascript:void(0) onclick="readMessage(this)">删除消息</a></td></tr>');
	}
	if ($("#olderTable").find("tr").length > 0) {
		msg.style.bottom = "0";
	}
}

function readMessage(obj) {
	var messageid = $(obj).parents("tr").children(":first").find(".messageid")
			.val();// 消息ID
	$.post(contextPath + '/message_updateMessageByMessageId.action', {
		"messageid" : messageid
	}, function(response) {
		if (response.result == "读取成功") {
			alert("已经成功处理！");
			queryOlderPerson();
		}
	}, 'json')

}

/** *****************E 消息框 QLQ********************* */
/** *****************S QLQ 显示与隐藏查询条件******************** */
function toggleQueryDiv() {
	// 点击显示查询条件与分割符
	if ($("#queryDiv").css("display") == "none") {
		$("#queryDiv").show(
				"slow",
				function() {
					// 删除class属性(切换上下箭头)
					$(".glyphicon-chevron-down").removeClass().addClass(
							"glyphicon glyphicon-chevron-up").prop("title",
							"点击隐藏查询条件");
				});
	} else {//隱藏条件与分割符
		$("#queryDiv").hide(
				"slow",
				function() {
					//删除class属性(切换上下箭头)
					$(".glyphicon-chevron-up").removeClass().addClass(
							"glyphicon glyphicon-chevron-down").prop("title",
							"点击显示查询条件");
				});
	}
}
/*******************E QLQ 显示与隐藏查询条件*********************/