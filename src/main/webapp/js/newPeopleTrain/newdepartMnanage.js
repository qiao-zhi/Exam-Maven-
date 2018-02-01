/**
 * Created by yorge on 2017/9/14.
 */
/* 工程管理 */
var break_open = false, user_open = false;// 用于记录两个模态框是否打开
function addexam() {
	// 累计选择的个数，若大于1，才执行，否则提示
	var nums = 0;
	$.each($(".el_checks"), function(i, el_check) {
		if ($(this).prop("checked")) {
			nums++;
		}
	});
	if (nums == 1) {
		$("#el_project").attr("href",
				"<%=path %>/view/outDepart/projectManage.jsp");
	} else {
		alert("只能选择一个试卷，才能执行此操作！")
	}
}

/* 模态违章信息 */
function el_bRInfor() {
	$('#el_bRInfor').modal();
}

/** **S QLQ************ */
/** **S 查询检修部门树**** */
$(document).ready(function() {
		// 初始化检修部门树
		queryHaulUnitTree();
		// 查询单位信息
		queryHaulUnit();
		// 查询的点击事件
		$("#queryHualunitBtn").click(function() {
			$("#currentPage").val("");// 清空页数
			queryHaulUnit();
		});
	
		
	    // 违章模态框关闭设置全局变量为关闭
	    $('#el_bRInfor').on('hidden.bs.modal', function() {
	    	break_open = false;
	    });
	    // 违章模态框关闭设置全局变量为关闭
	    $('#employeeModal').on('hidden.bs.modal', function() {
	    	user_open = false;
	    });
		
		$(document).click(function(event) {
			if ($(event.target).attr("class") != "unitName") {
				if ($("#showDiv").css("display") == "block") {
					$("#showDiv").css("display", "none");// 点击外部的时候隐藏名字提示框
				}
			}
		});
});

// 查询检修部门树
function queryHaulUnitTree() {
	$.ajax({
		url : contextPath + '/employeeOutPerson_getDepartmentAndOverHaulTree.action',
		dataType : 'JSON',
		async : true,
		data:{"markTrainType":"0"},
		type : 'POST',
		success : showHaulUnitTree,
		error : function() {
			alert("查询检修部门树出错!");
		}
	});
}
// 显示检修部门树
function showHaulUnitTree(response) {
	var setting = {
		view : {
			selectedMulti : false
		},
		check : {
			enable : false
		},
		data : {
			simpleData : {
				enable : true,
				enable : true,
				idKey : "id",
				pIdKey : "upid",
				rootPId : null
			},
			key : {
				name : "name",
			}
		},
		callback : {
			onClick : zTreeOnClick
		}
	};
	var treeNodes = response.departmentAndOverHaulTree;// 节点信息
	$.fn.zTree.init($("#treeDemo"), setting, treeNodes);
	openAddMoadl();// 如果是从检修过来的开启模态框
}
// 鼠标点击树事件(动态设置检修的id与名字)
function zTreeOnClick(event, treeId, treeNode) {
	// alert(treeNode.id + ", " + treeNode.name);
	// 如果树的等级是零则证明是检修，设置检修信息，否则清空检修信息
	if (treeNode.level == 0) {
		$("#bigname").val(treeNode.name);
		$("#bigid").val(treeNode.id);
		$("[name='bigId']").val(treeNode.id);// 设置地址之后进行查询
		queryHaulUnit();
	} else {
		$("#bigname").val("");
		$("#bigid").val("");
	}
}

/** **E 查询检修部门树**** */
/** ******S 模态框中操作以及保存单位******************** */
/* 选择树 */
function addUnit() {
	// 判断是否已经选择了树,跟据上边的NodeNums
	// alert($("#bigid").val()=="")
	if ($("#bigid").val() == "") {
		alert("请先选择一个培训类型!")
	} else {
		// 要根据检修ID查询之后判断的检修状态
		var bigId = $(".queryIsFinish").val();
			$.post(contextPath + '/haul_getHaulinfoByHaulid.action', {
				'haulId' : bigId
			}, showAddunitModal, 'json');
	}
}
// 根据查询的结果判断检修是否已经节结束
function showAddunitModal(response){
	if (response != null && response.haulinfo != null) {
		var haulinfo = response.haulinfo;
		if(haulinfo.bigstatus == '已结束'){
			alert("该检修已经结束，您不可以进行操作!!!");
			return;
		}
		// 如果检修未结束
		/* 给模态框中，添加默认部门 */
		$(".addUnitInput").val("");// 打开模态框的时候清除残留数据
		$("#validateName").text("");
		$('#myModal').modal({
			backdrop : 'static',
			keyboard : false
		});
	}
}
// 模糊查询姓名
function findNames(obj) {
	$("#validateName").text("");// 清除验证消息内容
	$("#showDiv").html("");
	// 1.获取表单的值
	var word = $(obj).val();
	var content = "";
	// 2.异步ajax去数据库模糊查询
	$
			.post(
					contextPath + "/addUnit_getNamesByName.action", // 请求地址
					{
						"nameWord" : word
					}, // 请求传递的参数，也可以是JSON
					function(response) { // data表示传递回来的数据，只有在成功的时候才有
						if (response.unitsName != null
								&& response.unitsName.length > 0) {
							for (var i = 0; i < response.unitsName.length; i++) {
								content += "<div style='padding:5px;cursor:pointer;' class='unitName' onclick='clickFn(this)' onmouseover='overFun(this);' onmouseout='outFn(this);'>"
										+ response.unitsName[i] + "</div>";
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
	var selectName = $(obj).html();
	$("#addUnitname").val(selectName);// 设置到上面
	$("#showDiv").css("display", "none");
// queryUnitinfoByName(selectName);
}
// 根据输入的单位名字判断单位是否已经存在对应的检修下面
function validateExistsUnit() {
	$("#validateName").text("");
	// 1.获取表单的值
	var word = $("#addUnitname").val();
	// 获取树对象
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	/** 获取所有树节点 */
	var nodes = treeObj.transformToArray(treeObj.getNodes());
	// 遍历树节点设置树节点为选中
	for (var k = 0, length_1 = nodes.length; k < length_1; k++) {
		if ($("#bigid").val() == nodes[k].id) {// 定位到对应的树节点
			var node = nodes[k];
			for (var i = 0; node.children != null && i < node.children.length; i++) {
				if (word == node.children[i].name) {
					$("#validateName").text("该检修已经存在该部门!");
					return false;
				}
			}
		}
	}
	return true;
}
// 根据单位名称查询单位信息，查询到后显示到模态框中
function queryUnitinfoByName(unitName) {
	$.post(contextPath + "/addUnit_getUnitByUnitName.action", // 请求地址
	// "name=qlq&password=qlq", //请求参数
	{
		"unitName" : unitName
	}, // 请求传递的参数，也可以是JSON
	function(response) { // data表示传递回来的数据，只有在成功的时候才有
		if (response.unit != null) {
			var unit = response.unit;
			$("#addAddress").val(unit.name);// 设置单位名称
			$("#addContact").val(unit.contact);// 设置单位联系人
			$("#addPhone").val(unit.phone);// 设置单位联系电话
		}
	}, "json" // 表示返回内容的格式,json会将传回来的自动解析成json对象
	);
}
// 联系电话(手机/电话皆可)验证
/*
 * jQuery.validator.addMethod("isPhone", function(value,element) { var length =
 * value.length; var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/; var tel =
 * /^\d{3,4}-?\d{7,9}$/; return this.optional(element) || (tel.test(value) ||
 * mobile.test(value)); }, "请正确填写您的联系电话");
 */


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
							|| (mobile.test(value) || (tel2.test(value)));

				}, "请正确填写您的联系电话");
// 验证并且保存单位信息到数据库
function saveUnit() {
	var isNotNull = $("#addUnitForm").validate({
        ignore : [],
        rules : {
            "name" : {
            	required:true
            	},// 发现日期
            "haulUnit.manager" : {
                required : true
            },
            "haulUnit.managerphone" : {
            	"isMobile":true,
                required : true
            },
            "haulUnit.secure":{
            	required : true
            },
            "haulUnit.securephone" : {
            	"isMobile":true,
                required : true
            },
            "haulUnit.projectnames" : {
            	required:true
            }
        },
        messages : {
            "name" : {
            	required:"单位名称不能为空"
            	},// 发现日期
            "haulUnit.manager" : {
                required : "项目经理不能为空"
            },
            "haulUnit.managerphone" : {
            	"isMobile":"请输入合法的电话",
                required : "电话不能为空"
            },
            "haulUnit.secure":{
            	required : "安全员不能为空"
            },
            "haulUnit.securephone" : {
            	"isMobile":"请输入合法的电话",
                required : "安全员电话不能为空"
            },
            "haulUnit.projectnames" : {
            	required:"工程名字不能为空"
            }
        }
    });
	
	if(isNotNull.form()){
		// 验证检修下面是否已经存在对应的单位姓名，如果存在结束方法
		if (!validateExistsUnit()) {
			return;
		}
		if (confirm("确认保存单位信息?")) {
			$.post(contextPath + "/addUnit_addUnit.action",// url
			$("#addUnitForm").serialize(), // data
			function(response) {
				if (response.addResult != null) {
					alert(response.addResult);
				}
				// 添加成功之后重新加载页面
				if (response.addResult != null && response.addResult == "添加成功!") {
					window.location.reload();
				}
			}, 'json')
		}
	}
}
/** ******E 模态框中操作以及保存单位******************** */
/** S 查询单位信息**************** */
// 查询部门信息
function queryHaulUnit() {
	$.post(contextPath + '/unit_getHaulUnitPage.action',
			$("#queryHaulunitForm").serialize(), showUnitTale, 'json');
}
// 添加信息到表格中
function showUnitTale(response) {
	// 如果为空结束方法
	if (response.pageBean == null) {
		return;
	}
	var currentPage = response.pageBean.currentPage;
	var currentCount = response.pageBean.currentCount;
	var totalCount = response.pageBean.totalCount;
	$("#haunUnitTbody").html("");// 清空表体
	var units = response.pageBean.productList;// 获取所有的单位
	for (var i = 0, length_1 = units.length; i < length_1; i++) {
		// 先列出所有的操作
		var delUpdate="--";
			if(hasOutunitOperating){
				delUpdate='<a href="javascript:void(0)" onclick="openUpdateModal(this)">修改</a>&nbsp;'
					+ ' <a href="javascript:void(0)" onclick="deleteUnit(this)">删除</a><br />';
			}
			
		// 如果大修已经结束就把操作隐藏掉
		var operation=units[i].bigStatus =="已结束"?"--":delUpdate;
		$("#haunUnitTbody")
				.append(
						'<tr><td><input type="radio" name="el_chooseDepart"	class="el_checks"/><input type="hidden" name="unitId" value="'
								+ units[i].unitId
								+ '"/><input type="hidden" name="bigId" value="'
								+ units[i].bigId
								+ '"/><input type="hidden" name="haulUnitId" value="'+units[i].unitBigId
								+'"/>'+ '</td><td>'
								+ units[i].name
								+ '</td><td>'
								+ units[i].bigName
								+ '</td><td>'
								+ units[i].manager
								+ '</td><td>'
								+ units[i].managerPhone
								+ '</td><td>'
								+ units[i].secure
								+ '</td><td>'
								+ units[i].securePhone
								+ '</td><td>'
								+ units[i].projectNames
								+ '</td><td><a href="javascript:void(0)" onclick="queryEmployeeBreakrule(this)">'
								+ units[i].unitMinisMum
								+ '</a></td><td><a href="javascript:void(0)" onclick="initVariable(this)">'
								+ units[i].personNum
								+ '</a></td><td>'+units[i].jiaquan+'</td><td>'
								+operation
								+ '</td></tr>');
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
				"pageList" : [ 8,15,20 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, pageSize) {
					$("#currentPage").val(pageNumber);
					$("#currentCount").val(pageSize);
					// 查询检修
					queryHaulUnit();
				}
			});
}

/** E 查询单位信息**************** */
// 清空表单与隐藏域
function clearForm(obj) {
	var form = $(obj).parents("form");
	$(form)[0].reset();// 手动清空表单
	$("[name='bigId']").val("");// 清空隐藏域
}
/** *S 修改单位信息** */
function openUpdateModal(obj) {
// $("label.error").remove();
// $(".clearFormInput").val("");
	var $tds = $(obj).parent().parent().children();
	var update_unitid = $($tds[0]).children("input:hidden:eq(0)").val();// 获取到部门ID
	var update_haulUnitid = $($tds[0]).children("input:hidden:eq(2)").val();// 获取到大修部门ID
	var update_name = $($tds[1]).text();// 获取到部门名称
	var update_big = $($tds[2]).text();// 获取到部门违章记分
	var update_manager = $($tds[3]).text();// 获取到部门地址
	var update_managerphone = $($tds[4]).text();// 获取到部门联系人
	var update_secure = $($tds[5]).text();// 获取到部门电话
	var update_securephone = $($tds[6]).text();// 获取到检修名称
	var update_projectnames = $($tds[7]).text();// 获取到部门违章记分
	var update_nuitMinus = $($tds[8]).text();// 获取到部门违章记分
	$("#update_big").val(update_big);
	$("#update_unitid").val(update_unitid);
	$("#update_haulUnitid").val(update_haulUnitid);
	$("#update_name").val(update_name);
	$("#update_manager").val(update_manager);
	$("#update_managerphone").val(update_managerphone);
	$("#update_secure").val(update_secure);
	$("#update_securephone").val(update_securephone);
	$("#update_nuitMinus").val(update_nuitMinus);
	$("#update_projectnames").val(update_projectnames);
	$("#myModal2").modal({
		backdrop : 'static',
		keyboard : false
	}); // 手动开启
}
function updateUnit() {
	var isNotNull = $("#updateForm").validate({
        ignore : [],
        rules : {
            "name" : {
            	required:true
            	},// 发现日期
            "haulUnit.manager" : {
                required : true
            },
            "haulUnit.managerphone" : {
            	"isMobile":true,
                required : true
            },
            "haulUnit.secure":{
            	required : true
            },
            "haulUnit.securephone" : {
            	"isMobile":true,
                required : true
            },
            "haulUnit.projectnames" : {
            	required:true
            }
        },
        messages : {
            "name" : {
            	required:"单位名称不能为空"
            	},// 发现日期
            "haulUnit.manager" : {
                required : "项目经理不能为空"
            },
            "haulUnit.managerphone" : {
            	"isMobile":"请输入合法电话",
                required : "经理电话不能为空"
            },
            "haulUnit.secure":{
            	required : "安全员不能为空"
            },
            "haulUnit.securephone" : {
            	"isPhone":"请输入合法的电话号码",
            	"isMobile":"请输入合法电话",
                required : "安全员电话不能为空"
            },
            "haulUnit.projectnames" : {
            	required:"工程名字不能为空"
            }
        }
    });
	
	if(isNotNull.form()){
		if(!confirm("确认修改?")){
			return;
		}
		$.post(contextPath + '/updateUnit.action', $("#updateForm").serialize(),
				function(response) {
					if (response != null && response.updateResult != null) {
						alert(response.updateResult);
						if (response.updateResult == '修改成功!') {
							window.location.reload();
						}
					}
				}, 'json')
	}
}
/** *E 修改单位信息** */

/** *S 根据单位与检修编号查询人数*** */
// 初始化数据
function initVariable(obj){
	var $tds = $(obj).parent().parent().children();
	var unitid = $($tds[0]).children("input:hidden:eq(0)").val();// 获取到部门ID
	var bigid = $($tds[0]).children("input:hidden:eq(1)").val();// 获取到部门ID
	$("#q_bigId").val(bigid);
	$("#q_unitId").val(unitid);
	queryEmployeeOut();
}
// 查询内部员工
function queryEmployeeOut() {
	$.post(contextPath + '/unit_getEmployeesByHaulidAndUnitId.action', {
		"currentPage":$("#currentPage2").val(),
		"currentCount":$("#currentCount2").val(),
		"bigId" : 	$("#q_bigId").val(),
		"unitId" : $("#q_unitId").val()
	}, showEmployeeModal, 'json')
}

function showEmployeeModal(response) {
	$("#employeeTbody").html("");
	if (response != null && response.pageBean != null) {
		var currentPage = response.pageBean.currentPage;
		var currentCount = response.pageBean.currentCount;
		var totalCount = response.pageBean.totalCount;
		var employees = response.pageBean.productList;
		for (var i = 0; employees != null && i < employees.length; i++) {
			var sex = (employees[i].sex == '1') ? "男" : "女";
			// 对员工的扣分进行非空处理
			var minusNum = (employees[i].minusNum == null || employees[i].minusNum == undefined) ? '0'
					: employees[i].minusNum;
			$("#employeeTbody").append(
					'<tr><td>' + employees[i].name + '</td><td>'
							+ employees[i].idCode + '</td><td>' + sex
							+ '</td><td>' + employees[i].address + '</td><td>'
							+ employees[i].empType + '</td></tr>');
			/* + '</td><td>' + minusNum */
		}
	}
	page3(currentPage, totalCount, currentCount);
	if(!user_open){
		$("#employeeModal").modal({
			keyboard : false,
			backdrop : 'static'
		});
	}
}
// 显示分页
function page3(currentPage, totalCount, currentCount) {
	// 修改分页的基本属性
	$('#paginationIDU2').pagination(
			{
				// 组件属性
				"total" : totalCount,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : currentCount,// 数字 每一页显示的数量 10
				"pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8,15,20 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, pageSize) {
					$("#currentPage2").val(pageNumber);
					$("#currentCount2").val(pageSize);
					// 查询检修员工
					queryEmployeeOut();
				}
			});
}


/** *E 根据单位与检修编号查询人数*** */
/** *S 根据单位与检修编号查询违章员工信息*** */
function queryEmployeeBreakrule(obj) {
	var $tds = $(obj).parent().parent().children();
	var unitid = $($tds[0]).children("input:hidden:eq(0)").val();// 获取到部门ID
	var bigid = $($tds[0]).children("input:hidden:eq(1)").val();// 获取到部门ID
	var fstarttime=$("[name='fstarttime']").val();
	var fendtime=$("[name='fendtime']").val();
	$("#query_unitid").val(unitid);
	$("#query_bigid").val(bigid);
	$("#query_fstarttime").val(fstarttime);
	$("#query_fendtime").val(fendtime);
	query_break();
}
function query_break(){
	$.post(contextPath
			+ '/unit_getEmployeeOutsBreakrulesByUaulIdAndUnitId.action',$("#query_break_form").serialize(), showEmployeeBreakrulesModal, 'json')
}
function showEmployeeBreakrulesModal(response) {
	var currentPage=response.pageBean.currentPage;
	var currentCount=response.pageBean.currentCount;
	var totalCount=response.pageBean.totalCount;
	$("#breakrulesTbody").html("");
	if (response != null && response.pageBean != null) {
		var employeeBreakrules = response.pageBean.productList;
		for (var i = 0; employeeBreakrules != null
				&& i < employeeBreakrules.length; i++) {
			var sex = (employeeBreakrules[i].sex == '1') ? "男" : "女";
			$("#breakrulesTbody").append(
					'<tr><td>'
							+ employeeBreakrules[i].name
							+ '</td><td>'
							+ sex
							+ '</td><td>'
							+ employeeBreakrules[i].idCode
							+ '</td><td>'
							+ employeeBreakrules[i].breakContent
							+ '</td><td>'
							+ Format(new Date(employeeBreakrules[i].breakTime.replace(/T/g," ").replace(/-/g,"/")),
									"yyyy-MM-dd") + '</td><td>'
							+ employeeBreakrules[i].minusNum + '</td></tr>');

		}
	}
	
	
	page1(currentPage, totalCount, currentCount);
	if(!break_open){
		$("#el_bRInfor").modal({
			keyboard : false,
			backdrop : 'static'
		});
	}
}
// 显示分页
function page1(currentPage, totalCount, currentCount) {
	// 修改分页的基本属性
	$('#paginationIDU1').pagination(
			{
				// 组件属性
				"total" : totalCount,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : currentCount,// 数字 每一页显示的数量 10
				"pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8,15,20 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, pageSize) {
					$("#currentPage1").val(pageNumber);
					$("#currentCount1").val(pageSize);
					// 查询检修
					query_break();
				}
			});
}
/** *E 根据单位与检修编号查询违章员工信息*** */
/** ****S 删除检修单位********* */
// 打开删除模态框
function deleteUnit(obj) {
	var $tds = $(obj).parent().parent().children();
	var unitid = $($tds[0]).children("input:hidden:eq(0)").val();// 获取到部门ID
	var bigid = $($tds[0]).children("input:hidden:eq(1)").val();// 获取到检修ID
	$("#deleteBigId").val(bigid);
	$("#deleteUnitId").val(unitid);
	// 打开删除模态框
	$('#delcfmModel').modal({
		backdrop : 'static',
		keyboard : false
	});
}
// 开始删除
function deleteSubmit() {
	$.post(contextPath + "/deleteUnit.action",
			$("#deleteUnitForm").serialize(), function(response) {
				if(response!=null && response.deleteResult != null){
					alert(response.deleteResult);
					if(response.deleteResult == '删除成功!'){
						window.location.reload();
					}
				}
			}, 'json');
}
/** ****E 删除检修单位********* */
/** ****S 删除检修单位********* */
// 如果是从检修页面调过来的打开模态框
function openAddMoadl() {
	if (haulId != "") {
		　　// 获取树对象
	    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	    /** 获取所有树节点 */
	    var nodes = treeObj.transformToArray(treeObj.getNodes());
	    for(var i=0;nodes != null && i<nodes.length;i++ ){
	    	if(nodes[i].id == haulId){
	    		$("#bigname").val(nodes[i].name);// 设置名字
	    		$("#bigid").val(haulId);// 设置检修的ID
	    		/* 给模态框中，添加默认部门 */
	    		$(".addUnitInput").val("");// 打开模态框的时候清除残留数据
	    		$("#validateName").text("");
	    		$('#myModal').modal({
	    			backdrop : 'static',
	    			keyboard : false
	    		});
	    		break;
	    	}
	    }
	}
}
/** ****E 删除检修单位********* */
/** **E QLQ************ */
