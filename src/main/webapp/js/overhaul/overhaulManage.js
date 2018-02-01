/***  大修信息管理JS*******/
/**
 * 页面初始化函数
 */
var biaoduan_open = false, pernum_open = false;// 用于记录两个模态框是否打开
var updateBiaoDuan = false;// 用于标记是否修改标段
$(function() {
	// 页面初始化查询大修信息
	queryHaulFun();
//	查询统计信息
	queryAllhaulinfo();
	// 查询的点击事件
	$("#haulQueryButton").click(function() {
		$("#currentPage").val("");// 清空页数
		queryHaulFun();
	});
	// 修改大修的修改按钮的点击事件
	$("#update_haul_btn").click(function() {
		// ajax异步修改
		updateHaulinfo();
	});
	// 删除大修模态框的确定按钮的点击事件
	$("#deleteHaulBtn").click(function() {
		// ajax异步修改
		deleteHaulinfo();
	});
	// 内部模态框关闭的时候将标志字段设为false
	$('#biaoduan_modal').on('hidden.bs.modal', function() {
		biaoduan_open = false;
	});
	// 外部模态框关闭的时候将标志字段设为false
	$('#pernum_modal').on('hidden.bs.modal', function() {
		pernum_open = false;
	});

	// 修改模态框关闭的时候根据是否修改标段判断是否需要二次查询
	$('#el_modifyOverhaul').on('hidden.bs.modal', function() {
		if (updateBiaoDuan) {
			queryHaulFun();
		}
	});

});

// (创建大修的点击事件)打开添加大修模态框
var el_addOverhaul = function() {
	$(".clearAdd").val("");// 清空模态框
	$("#biaoduanTbody").html("");// 清空表格
	$("#el_addOverhaul").modal({
		backdrop : 'static',
		keyboard : false
	});
}
/** *S 验证保存大修信息*** */
// 大修创建的提交事件
function addNewsSibmitButton() {
	// 判断标段是否为空
	if ($("#biaoduanTbody").text() == "") {
		alert("检修标段不能为空!");
		return;
	} else {
		// 获取到标段
		var trs = $("#biaoduanTbody").children("tr");
		var hidden_biaoduan = "";
		for (var i = 0; i < trs.length; i++) {
			hidden_biaoduan += $(trs[i]).children("td:eq(1)").text() + ",";
		}
		// 想隐藏的标段赋值
		$("[name='hidden_biaoduan']").val(
				hidden_biaoduan.substring(0, hidden_biaoduan.length - 1));
	}
	// addHaulForm是表单的ID
	var isNotNull = $("#addHaulForm").validate({
		ignore : [],
		rules : {
			"bigname" : {
				"required" : true,
				"maxlength" : 50
			},
			"bigbegindate" : {
				required : true
			},
			"bigenddate" : "required",// 验证文本框的。前边是 name 属性
			"bigdescription" : {
				required : true
			}
		},
		messages : {
			"bigname" : {
				"required" : "名字不能为空",
				"maxlength" : "最长不能超过五十个字符"
			},// 发现日期
			"bigbegindate" : {
				"required" : "开始日期不能为空"
			},
			"bigenddate" : {
				"required" : "结束日期不能为空"
			},// 验证文本框的。前边是 name 属性
			"bigdescription" : {
				required : "大修描述不能为空"
			}
		}

	});
	if (isNotNull.form()) {
		if (confirm("确认保存?")) {
			$.ajax({
				url : contextPath + '/addHaul.action',
				type : "POST",
				async : true,
				data : $("#addHaulForm").serialize(),
				dataType : "json",
				success : function(response) {
					alert(response.result)
					// 添加成功重新加载页面
					if (response.result == "添加成功!") {
						window.location.reload();
					}
				},
				error : function() {
					alert("添加大修失败!!!");
				}
			});
		}
	}
}
/** *E 保存大修信息*** */

/** *S 分页查询大修信息*** */
// 查询大修
var queryHaulFun = function() {
	$.ajax({
		url : contextPath + "/haul_queryPageHaul.action",
		data : $("#haulQueryForm").serialize(),
		dataType : "JSON",
		async : true,
		type : "POST",
		success : showHaulTable,
		error : function() {
			alert("查询大修失败!!!");
		}

	});
}
// 大修分页信息
function showHaulTable(response) {
	$("#haulTbody").html("");// 清空表格
	var haulinfos = response.pageBean.productList;// 获取到大修JSON对象
	var currentCount = response.pageBean.currentCount;// 页大小
	var totalCount = response.pageBean.totalCount;// 页总数
	var currentPage = response.pageBean.currentPage;// 当前页
	for (var i = 0, length_1 = haulinfos.length; i < length_1; i++) {
		var tr = '<tr><td>'
				+ '<input type="hidden" value="'
				+ haulinfos[i].bigId
				+ '"/>'
				+ +(parseInt(currentCount) * parseInt(currentPage - 1) + (i + 1))
				+ '</td><td>'
				+ haulinfos[i].bigName
				+ '</td><td>'
				+ Format(new Date(haulinfos[i].bigBeginDate.replace(/T/g, " ")
						.replace(/-/g, "/")), "yyyy-MM-dd")
				+ '  到  '
				+ Format(new Date(haulinfos[i].bigEndDate.replace(/T/g, " ")
						.replace(/-/g, "/")), "yyyy-MM-dd") + '</td><td>'
				+ haulinfos[i].bigStatus + '</td><td>'
				+ '<a href=javascript:void(0) onclick="selectBiaoduan(this)">'
				+ haulinfos[i].biaoduan + '</a>' + '</td><td>'
				+'<a  title="查看具体的检修单位信息" href="' + contextPath
				+ '/view/overhaul/overhaulInfo.jsp?haulId='
				+ haulinfos[i].bigId + '">' + haulinfos[i].unitNum + '</a>'
				+ '</td><td>'
				+ '<a href=javascript:void(0) onclick="selectPernum(this)">'
				+ haulinfos[i].perNum + '</a>' + '</td><td>';

		tr += '<a  title="查看具体的检修单位信息" href="' + contextPath
				+ '/view/overhaul/overhaulInfo.jsp?haulId='
				+ haulinfos[i].bigId
				+ '"><span class="glyphicon glyphicon-search"></span></a>';
		if (hasOperatingJianxiu) {
			tr += '<a title="修改检修" href="javascript:void(0)" onclick="el_modifyOverhaul(this)"><span class="glyphicon glyphicon-pencil"></span></a>'
			tr += '<a title="删除检修" href="javascript:void(0)" onclick="showDeleteModal(this)"><span class="glyphicon glyphicon-trash"></span></a>';
		}
		tr += '</td></tr>';
		// 填充表格
		$("#haulTbody").append(tr);
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
				"pageList" : [ 8, 15, 20 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, pageSize) {
					$("#currentPage").val(pageNumber);
					$("#currentCount").val(pageSize);
					// 查询大修
					queryHaulFun();
				}
			});
}
/** *E 分页查询大修信息*** */

/** **S 修改大修***** */
// 初始化修改大修模态框信息
function el_modifyOverhaul(obj) {
	var $tds = $(obj).parent().parent().children();// 获取到所有列
	var up_id = $($tds[0]).children("input").val();// 获取隐藏的ID
	var up_name = $($tds[1]).text();// 获取名字
	var up_startTime = $($tds[2]).text().toString().substring(0, 10);// 取开始时间
	var up_endTime = $($tds[2]).text().toString().substring(15);// 获取结束时间
	var up_desc = $($tds[4]).text();// 获取描述
	$("#update_bigid").val(up_id);// 设置隐藏的ID值
	$("#update_haulName").val(up_name);// 设置大写名字
	$("#inpstart1").val(up_startTime);// 设置开始时间
	$("#inpend1").val(up_endTime);// 设置结束时间
	queryBiaoduan(up_id);

	/*
	 * $("#update_desc").val(up_desc);// 设置描述
	 */
	updateBiaoDuan = false;//是否修改标记标为否
	$("#el_modifyOverhaul").modal({
		backdrop : 'static',
		keyboard : false
	});
}
// 查询标段信息
function queryBiaoduan(bigId) {
	// 查到大修下面的标段并在界面显示
	$
			.post(
					contextPath + '/addUnit_getProjectInfoByHaulId.action',
					{
						"bigid" : bigId
					},
					function(response) {
						var projects = response.projects;
						$("#updatebiaoduanTbody").html("");
						for (var i = 0; i < projects.length; i++) {
							$("#updatebiaoduanTbody")
									.append(
											'<tr><td>'
													+ (i + 1)
													+ '<input type="hidden" value="'
													+ projects[i].projectid
													+ '"/>'
													+ '</td><td>'
													+ projects[i].projectname
													+ '</td><td>'
													+ '<a href=javascript:void(0) onclick="updateBiaoduan(this)" ><span class="glyphicon glyphicon-pencil"  title="点击修改标段"></span></a>'
													+ '<a href=javascript:void(0) onclick="deleteBiaoduan(this)" ><span class="glyphicon glyphicon-trash" title="点击删除标段"></span></a>'
													+ '</td></tr>');
						}
					}, 'json');
}

// 保存修改的信息
function updateHaulinfo() {
	var isNotNull = $("#modifyHaulForm").validate({
		ignore : [],
		rules : {
			"bigname" : {
				"required" : true,
				"maxlength" : 50
			},
			"bigbegindate" : {
				required : true
			},
			"bigenddate" : "required"// 验证文本框的。前边是 name 属性
		/*
		 * "bigdescription" : { required : true }
		 */
		},
		messages : {
			"bigname" : {
				"required" : "名字不能为空",
				"maxlength" : "最长不能超过五十个字符"
			},// 发现日期
			"bigbegindate" : {
				"required" : "开始日期不能为空"
			},
			"bigenddate" : {
				"required" : "结束日期不能为空"
			}
		// 验证文本框的。前边是 name 属性
		/*
		 * "bigdescription" : { required : "大修描述不能为空" }
		 */
		}

	});
	if (isNotNull.form()) {
		if (confirm("确认修改?")) {
			// 开始修改.......
			$.ajax({
				url : contextPath + '/updateHaul.action',
				data : $("#modifyHaulForm").serialize(),
				async : true,
				dataType : '',
				type : 'POST',
				success : function(response) {
					alert(response.result)
					// 添加成功重新加载页面
					if (response.result == "修改成功!") {
						window.location.reload();
					}
				},
				error : function() {
					alert("修改大修基本信息出错!!!")
				}
			});
		}
	}

}

/** **E 修改大修***** */
/** **S 删除大修***** */
// 打开询问是否删除的模态框并设置需要删除的大修的ID
function showDeleteModal(obj) {
	var $tds = $(obj).parent().parent().children();// 获取到所有列
	var delete_id = $($tds[0]).children("input").val();// 获取隐藏的ID
	$("#deleteHaulId").val(delete_id);// 将模态框中需要删除的大修的ID设为需要删除的ID
	$("#delcfmOverhaul").modal({
		backdrop : 'static',
		keyboard : false
	});
}
// 传到数据库进行删除
function deleteHaulinfo() {
	var deleteBigid = $("#deleteHaulId").val();
	$.post(contextPath + '/deleteHaul.action', {
		"bigId" : deleteBigid
	}, function(response) {
		if (response != null && response.deleteResult != null) {
			alert(response.deleteResult);
			if (response.deleteResult == "删除成功!") {
				window.location.reload();
			}
		}
	}, 'json');
}

/** **E 删除大修***** */

/** **************************S 1.22****************** */
/** *****s 检修标段信息部分 ******** */
// 添加检修标段(将标段添加到表格中)
function addBiaoduan() {
	var biaoduanName = $(".add_biaoduan").val();
	if (biaoduanName == "") {
		alert("标段名称不能为空!");
		return;
	}
	var length = $("#biaoduanTbody").children("tr").length;
	var trs = $("#biaoduanTbody").children("tr");
	// 判断此标段名称是否在下面存在
	for (var i = 0; i < trs.length; i++) {
		if (biaoduanName == $(trs[i]).children("td:eq(1)").text()) {
			alert("不能重复添加标段");
			return;
		}
	}
	// 清空到表格
	$("#biaoduanTbody")
			.append(
					'<tr><td>'
							+ (length + 1)
							+ '</td><td>'
							+ biaoduanName
							+ '</td><td>'
							+ '<a href=javascript:void(0) onclick="deleteBiaoduanFromTable(this)"><span	class="glyphicon glyphicon-trash" title="删除标段"></span></a>'
							+ '</td></tr>');
	// 清空数据
	$(".add_biaoduan").val("");
}

// 从表格中删除检修标段
function deleteBiaoduanFromTable(obj) {
	if (confirm("确认删除此标段?")) {
		// 删除掉标段
		var tr = $(obj).parent().parent();
		tr.remove();
		var trs = $("#biaoduanTbody").children("tr");
		// 重新排序
		for (var i = 0; i < trs.length; i++) {
			$(trs[i]).children("td:eq(0)").text(i + 1);
		}

	}
}

/** *******S 点击标段数字*************** */
function selectBiaoduan(obj) {
	$("#currentPage3").val("");
	$("#haulId_biaoduan").val(
			$(obj).parent().parent().find("input:hidden").val());
	query_biaoduan();
}
function query_biaoduan() {
	$.post(contextPath + '/haul_queryBiaoDuan.action', $("#query_biaoduan")
			.serialize(), showBiaoduan, 'json')
}

function showBiaoduan(response) {
	$("#biaoduan_click_tbody").html("");
	var biaoduans = response.pageBean.productList;// 获取到大修JSON对象
	var currentCount = response.pageBean.currentCount;// 页大小
	var totalCount = response.pageBean.totalCount;// 页总数
	var currentPage = response.pageBean.currentPage;// 当前页
	for (var i = 0, length_1 = biaoduans.length; i < length_1; i++) {
		$("#biaoduan_click_tbody")
				.append(
						'<tr><td>'
								+ (parseInt(currentCount)
										* parseInt(currentPage - 1) + (i + 1))
								+ '</td><td>' + biaoduans[i].projectname
								+ '</td><td>' + biaoduans[i].perNum
								+ '</td></tr>');

	}
	// 动态开启分页组件
	page3(currentPage, totalCount, currentCount);
	// 如果模态框未打开就打开模态框并设置标志字段为已打开
	if (!biaoduan_open) {
		$("#biaoduan_modal").modal({
			backdrop : 'static',
			keyboard : false
		});
		biaoduan_open = true;
	}

}
// 显示分页
function page3(currentPage, totalCount, currentCount) {
	// 修改分页的基本属性
	$('#paginationIDU3').pagination(
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
					$("#currentPage3").val(pageNumber);
					$("#currentCount3").val(pageSize);
					// 查询大修
					query_biaoduan();
				}
			});
}
/** *E 点击标段数量*** */

/** *******S 点击标段数字*************** */
function selectPernum(obj) {
	$("#currentPage4").val("");
	$("#haulId_pernum")
			.val($(obj).parent().parent().find("input:hidden").val());
	query_pernum();
}
function query_pernum() {
	$.post(contextPath + '/haul_queryUnitBiaoduanPerNum.action', $(
			"#query_pernum").serialize(), showPernum, 'json')
}

function showPernum(response) {
	$("#pernum_click_tbody").html("");
	var biaoduans = response.pageBean.productList;// 获取到大修JSON对象
	var currentCount = response.pageBean.currentCount;// 页大小
	var totalCount = response.pageBean.totalCount;// 页总数
	var currentPage = response.pageBean.currentPage;// 当前页
	for (var i = 0, length_1 = biaoduans.length; i < length_1; i++) {
		$("#pernum_click_tbody")
				.append(
						'<tr><td>'
								+ (parseInt(currentCount)
										* parseInt(currentPage - 1) + (i + 1))
								+ '</td><td>' + biaoduans[i].projectname
								+ '</td><td>' + biaoduans[i].name + '</td><td>'
								+ biaoduans[i].perNum + '</td></tr>');

	}
	// 动态开启分页组件
	page4(currentPage, totalCount, currentCount);
	// 如果模态框未打开就打开模态框并设置标志字段为已打开
	if (!pernum_open) {
		$("#pernum_modal").modal({
			backdrop : 'static',
			keyboard : false
		});
		pernum_open = true;
	}

}
// 显示分页
function page4(currentPage, totalCount, currentCount) {
	// 修改分页的基本属性
	$('#paginationIDU4').pagination(
			{
				// 组件属性
				"total" : totalCount,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : currentCount,// 数字 每一页显示的数量 10
				"pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, pageSize) {
					$("#currentPage4").val(pageNumber);
					$("#currentCount4").val(pageSize);
					// 查询大修
					query_pernum();
				}
			});
}
/** *E 点击标段数量*** */

/** **S 修改检修的操作************** */
// 增加标段
function addOneBiaoduan() {
	// 提示用户输入信息的框
	var name = prompt("请输入标段名字");
	var trs = $("#updatebiaoduanTbody").children("tr");
	var canAdd = trs.length == 0 ? true : false;
	// 判断此标段名称是否在下面存在
	for (var i = 0; i < trs.length; i++) {
		if (name == $(trs[i]).children("td:eq(1)").text()) {
			alert("不能重复添加标段");
			return;
		} else {
			canAdd = true;
		}
	}
	if (canAdd) {
		$.post(contextPath + "/upHaul_addOneBiaoduan.action", {
			"bigId" : $("#update_bigid").val(),
			"name" : name
		}, showResult, 'json');
	}
}

function showResult(response) {
	var result = response.result;
	if (result == '添加成功!') {
		if (updateBiaoDuan == false) {
			updateBiaoDuan = true;
		}
		queryBiaoduan($("#update_bigid").val());
	}

}

// 删除标段:
function deleteBiaoduan(obj) {
	var projectId = $(obj).parent().parent().children("td:eq(0)").find("input")
			.val();// 要删除的ID
	if ($("#updatebiaoduanTbody").find("tr").length == 1) {
		alert("最少保留一个标段");
		return;
	}
	if (confirm("您确认删除该标段信息?")) {
		$.post(contextPath + "/upHaul_deleteOneBiaoduan.action", {
			"projectId" : projectId,
		}, function(response) {
			var result = response.result;
			alert(result);
			if (result == '删除成功!') {
				if (updateBiaoDuan == false) {
					updateBiaoDuan = true;
				}
				queryBiaoduan($("#update_bigid").val());
			}
		}, 'json')
	}
}

// 修改标段:
function updateBiaoduan(obj) {
	var projectName = $(obj).parent().parent().children("td:eq(1)").text();
	var projectId = $(obj).parent().parent().children("td:eq(0)").find("input")
			.val();// 要修改的ID
	var name = prompt("请输入标段名字", projectName);
	if (name != null) {// 点击确定
		$.post(contextPath + "/upHaul_updateOneBiaoduan.action", {
			"projectId" : projectId,
			"name" : name
		}, function(response) {
			var result = response.result;
			alert(result);
			queryBiaoduan($("#update_bigid").val());
		}, 'json')
	}
}

/****************************查询统计信息**********************************/
function queryAllhaulinfo(){
	$.post(contextPath+'/haul_getAllhaulinfo.action',function(response){
		var haulnum = response.allHaulInfo.haulnum;
		var biaoduanNum = response.allHaulInfo.biaoduanNum;
		var perNum = response.allHaulInfo.perNum;
		var unitNnm = response.allHaulInfo.unitNnm;
		var span = $("#countSpan");
		span.children("u:eq(0)").html(haulnum);
		span.children("u:eq(1)").html(biaoduanNum);
		span.children("u:eq(2)").html(unitNnm);
		span.children("u:eq(3)").html(perNum);
	},'json');
}

/*****************检修状态改变的时候显示与隐藏统计信息*******************************/
function changeCountInfo(){
	if($("[name='bigStatus']").val()!="进行中"){
		$("#countSpan").css("display","none");
	}else{
		$("#countSpan").css("display","block");
	}
}