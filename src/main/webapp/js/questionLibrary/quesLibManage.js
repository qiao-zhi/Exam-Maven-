/** *************************页面加载函数************************** */

$(function() {

	findQuestionBankInfo();

});

/** **************************组合条件查询************************** */

// 点击页面的查询按钮执行的操作
function searchQuestionBankInfo() {
	// 执行查询操作前先将隐藏域中的值清空当前页清空或改为第一页
	$("#currentPage").val("");
	// 调用查询方法
	findQuestionBankInfo();
}

function findQuestionBankInfo() {
	$.ajax({
				url : 'quesitonBank_findQuestionBankInfoByCondition.action',
				data : $("#form_findQuestionBankInfo").serialize(),
				type : 'POST',
				dataType : 'json',
				async : true,
				success : showQuestionBankInfo,
				error : function() {
					alert("请求失败！");
				}
			});
}

function showQuestionBankInfo(data) {
	var result = data;
	// 从数据库中查询出来的数据集合
	var questionBankInfoList = result.pageBean.productList;
	// 当前页
	var currentPage = result.pageBean.currentPage;
	// 总条数
	var totalCount = result.pageBean.totalCount;
	// 总条数
	var currentCount = result.pageBean.currentCount;
	var showQuestionBankListInfo = "";
	if (questionBankInfoList != null) {
		for (var i = 0; i < questionBankInfoList.length; i++) {
			var index = (currentPage - 1) * currentCount + i + 1;
			showQuestionBankListInfo += "<tr><td><input type='radio' name='el_exam' class='el_checks' value='"
					+ questionBankInfoList[i].questionbankid
					+ "'/></td><td>"
					+ index
					+ "<input class='find_questionbankid' type='hidden' value='"
					+ questionBankInfoList[i].questionbankid
					+ "'/>"
					+ "<input class='find_departmentid' type='hidden' value='"
					+ questionBankInfoList[i].departmentid
					+ "'/>"
					+ "<input class='find_countsingle' type='hidden' value='"
					+ questionBankInfoList[i].countsingle
					+ "'/>"
					+ "<input class='find_countmultiple' type='hidden' value='"
					+ questionBankInfoList[i].countmultiple
					+ "'/>"
					+ "<input class='find_counttrueorfalse' type='hidden' value='"
					+ questionBankInfoList[i].counttrueorfalse
					+ "'/>"
					+ "<input class='find_description' type='hidden' value='"
					+ questionBankInfoList[i].description
					+ "'/>"
					+ "<input class='find_categorynameid' type='hidden' value='"
					+ questionBankInfoList[i].categorynameid
					+ "'/>"
					+ "</td><td>"
					+ questionBankInfoList[i].questionbankname
					+ "</td><td>"
					+ questionBankInfoList[i].typename
					+ "</td><td>"
					+ "<a title='点击查看具体的试题信息' href='questionManage.jsp?questionbankId="
					+ questionBankInfoList[i].questionbankid
					+ "'>"
					+ questionBankInfoList[i].sumquestions
					+ "</a></td><td>"					
					+ questionBankInfoList[i].employeename
					+ "</td><td>"
					+ Format(new Date(questionBankInfoList[i].createtime
							.replace(/T/g, " ").replace(/-/g, "/")),
							"yyyy-MM-dd HH:mm:ss")
					+ "</td><td>";
			showQuestionBankListInfo +=  "<a href='javascript:void(0)' onclick='el_statisticAnalyze(this)'>统计</a>&nbsp;|"
				+ " <a href='javascript:void(0)' onclick='el_export(this)'>题库导出</a>";
			if(hasQuestionbankManager){
				showQuestionBankListInfo += " <a href='javascript:void(0)' onclick='modifyQL(this)'>修改</a>&nbsp;|"
					+ " <a href='javascript:void(0)' class='el_delButton' onClick='delcfm(this)'>删除题库</a>"
			}
			showQuestionBankListInfo += "</td></tr>";
		}
		// 清空表格
		$("#questionBankListInfo").empty();
		// 添加信息
		$("#questionBankListInfo").append(showQuestionBankListInfo);

		// 调用分页函数
		questionLib_page(currentPage, totalCount, currentCount);

	}

}

/** *******************************添加题库信息****************************** */
$(function() {

	// 动态选择需要设置的题库类型
	$(".select_backType").click(function() {
		var typeId = $(this).val();
		initDictionaryInfo(typeId);
	})
})
// 题库类别明细信息
function initDictionaryInfo(typeId) {
	$.post(basePathUrl + '/dic_getDicNamesAndIdByUpid.action', {
		"upId" : typeId
	}, showDictionaryInfo, 'json');
}
function showDictionaryInfo(response) {
	if (response != null && response.names != null) {
		var names = response.names;// 获取字段返回的值
		// 将下拉列表框清空
		$(".addAndUpdate_questionBankType").empty();
		for (var i = 0; i < names.length; i++) {
			$(".addAndUpdate_questionBankType").append(
					'<option value="' + names[i].dictionaryid + '">'
							+ names[i].dictionaryname + '</option>')
		}
	}
}

// 点击添加题库执行的操作
function el_addDictinary() {
		// 调用初始化函数
		addQuestionBank_initForm();
		// 默认选择工种，初始化工种信息
		initDictionaryInfo("100");
		// 将span中的数据清空
		$(".isQustionBankNameInfo").html("");

		/* 给模态框中，添加默认部门 */
		//$("#addDefaultDepart").prop("value", selectedDepartmentName);
		//$("#questionbank_Departmentid").prop("value", selectedDepartmentID);
		$('#myModal').modal();
	
}

// 初始化添加题库模态框的操作
function addQuestionBank_initForm() {
	$("#add_questionBankName").val("");
	$("#add_description").val("");
	$("#add_employeeName").val("");
	// 默认选择题库工种类别
	$("#select_backTypeId").prop("checked", true);
}

// 点击添加题库的保存按钮执行的操作
function submitQuestionBankInfo() {
	// 非空校验
	var isNotNull = $("#form_AddQuestionBank").validate({
		rules : {
			questionbankname : "required",
			employeename : "required",
			description : "required",
			createtime : "required"
		},
		messages : {
			questionbankname : {
				required : "不能为空"
			},
			employeename : {
				required : "不能为空"
			},
			description : {
				required : "不能为空"
			},
			createtime : {
				required : "不能为空"
			}

		}
	});
	// 校验不为空且题库名不存在，执行提交操作
	if (isNotNull.form() && !isExistQuestionbankName) {
		// 将span中的数据清空
		$(".isQustionBankNameInfo").html("");

		$.ajax({
			url : basePathUrl + '/quesitonBank_addQuestionBankInfo.action',
			data : $("#form_AddQuestionBank").serialize(),
			type : 'POST',
			dataType : 'json',
			async : true,
			success : function(data) {
				alert(data.result);
				// 关闭模态框
				$('#myModal').modal('hide');
				// 刷新当前页面
				// window.location.reload();
				// 调用查询方法，默认显示第一页
				searchQuestionBankInfo()

			}
		});

	}

}

// 定义一个全局变量，测试题库名是否存在
var isExistQuestionbankName = false;

// 题库名输入框的离焦事件，判断题库名是否存在
function existQuestionbankName(obj) {

	// alert(obj.value);
	// 获取输入到的题库名称
	var questionBankNameInput = obj.value;

	$
			.ajax({
				url : "quesitonBank_getDepartmentIdByBankName.action",
				data : {
					"questionBankName" : questionBankNameInput
				},
				type : 'POST',
				dataType : "json",
				async : true,
				success : function(data) {
					var isQustionBankNameInfo = "";
					// 判断题库名是否已经存在
					isExistQuestionbankName = data.departmentId != null;
					// alert(isExistQuestionbankName)
					if (data.departmentId != null) {
						isQustionBankNameInfo = "该题库名已存在，请重新输入！";
						$(".isQustionBankNameInfo").css("color", "red");
					} else {
						// isQustionBankNameInfo = "该题库名可以使用！";
						isQustionBankNameInfo = "";
						$(".isQustionBankNameInfo").css("color", "green");
					}
					$(".isQustionBankNameInfo").html(isQustionBankNameInfo);
				}

			});

}

/** **************************数据统计************************** */

function el_statisticAnalyze(obj) {
	var tds = $(obj).parents("tr").children("td");
	$("#statistics_questionBankName").text(tds.eq(2).html());
	$("#statistics_sumquestions").text(tds.eq(4).children("a").html());
	// 获得题库描述
	var description = $(obj).parents("tr").find(".find_description").val();
	$("#statistics_description").text(description);

	var countsingle = $(obj).parents("tr").find(".find_countsingle").val();
	var countmultiple = $(obj).parents("tr").find(".find_countmultiple").val();
	var counttrueorfalse = $(obj).parents("tr").find(".find_counttrueorfalse")
			.val();

	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));

	// 指定图表的配置项和数据
	var option = {
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b}: {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : [ '多选题', '单选题', '判断题' ]
		},
		series : [ {
			name : '访问来源',
			type : 'pie',
			radius : [ '60%', '85%' ],
			avoidLabelOverlap : false,
			label : {
				normal : {
					show : false,
					position : 'center'
				},
				emphasis : {
					show : true,
					textStyle : {
						fontSize : '20',
						fontWeight : 'bold'
					}
				}
			},
			labelLine : {
				normal : {
					show : false
				}
			},
			data : [ {
				value : countmultiple,
				name : '多选题'
			}, {
				value : countsingle,
				name : '单选题'
			}, {
				value : counttrueorfalse,
				name : '判断题'
			} ]
		} ]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);

	$('#el_statisticAnalyze').modal();
}

/** *******************************题库导出*************************************** */
function el_export(obj) {

	var quesLibName = $(obj).parents("tr").children("td").eq(2).html();
	$("#export_questionLibName").html(quesLibName);
	var questionBankId = $(obj).parents("tr").find(".find_questionbankid")
			.val();
	$("#questionBankIdExport").val(questionBankId);
	// 将复选框的值清空
	var obj = document.getElementsByName("el_QuestionType");
	for (k in obj) {
		if (obj[k].checked)
			$(obj[k]).prop("checked", false);
	}
	$("#wordFormat").prop("checked", true);
	$('#el_export').modal();
}

function exportQuestionsWithCondition() {
	var questionBankId = $("#questionBankIdExport").val();
	var form;
	// 获取用户选中的导出格式
	$.each($(".el_ExportForm"), function(i, el_check) {
		if ($(this).prop("checked")) {
			form = $(this).val();
		}
	});
	// 获取选中的试题类型
	var obj = document.getElementsByName("el_QuestionType");
	var questionType = [];
	var num = 0;
	for (k in obj) {
		if (obj[k].checked) {
			questionType.push(obj[k].value);
			num++;
		}
	}
	if (num > 0) {
		if (form == 1) {
			location.href = "exportWordQuestionsAction.action?questionBankId="
					+ questionBankId + "&questionType=" + questionType;
		} else {
			location.href = "exportTxtQuestionsAction.action?questionBankId="
					+ questionBankId + "&questionType=" + questionType;
		}
		$('#el_export').modal("hide");
	} else {
		alert("请至少选择一种题型！")
	}

}

/** *******************************修改题库*************************************** */

// 题库类别明细信息的回显
function initDictionaryInfoUpdate(typeId, categorynameid) {
	$.post(basePathUrl + '/dic_getDicNamesAndIdByUpid.action', {
		"upId" : typeId
	}, function(response) {
		if (response != null && response.names != null) {
			var names = response.names;// 获取字段返回的值
			// 将下拉列表框清空
			$(".addAndUpdate_questionBankType").empty();
			for (var i = 0; i < names.length; i++) {
				if (categorynameid != names[i].dictionaryid) {
					$(".addAndUpdate_questionBankType").append(
							'<option value="' + names[i].dictionaryid + '">'
									+ names[i].dictionaryname + '</option>')
				} else {
					$(".addAndUpdate_questionBankType").append(
							'<option value="' + names[i].dictionaryid
									+ '" selected>' + names[i].dictionaryname
									+ '</option>')
				}
			}
		}
	}, 'json');
}

function modifyQL(obj) {
	// 从当前行中获取值
	var tds = $(obj).parents("tr").children("td");
	$("#update_questionBankName").val(tds.eq(2).html());
	$("#update_questionBankName_old").val(tds.eq(2).html());
	//$("#update_departmentName").val(tds.eq(5).html());
	$("#update_employeeName").val(tds.eq(5).html());
	$("#test41").val(tds.eq(6).html());

	// alert(tds.eq(5).html())
	var departmentId = $(obj).parents("tr").find(".find_departmentid").val();
	var questionbankId = $(obj).parents("tr").find(".find_questionbankid")
			.val();
	var description = $(obj).parents("tr").find(".find_description").val();
	var categorynameid = $(obj).parents("tr").find(".find_categorynameid")
			.val();
	var typeId = categorynameid.substring(0, 3);
	if (typeId == 100) {
		$("#questionType_01").prop("checked", true);
	} else {
		$("#questionType_02").prop("checked", true);
	}
	initDictionaryInfoUpdate(typeId, categorynameid);
	//$("#update_departmentId").val(departmentId);
	$("#update_description").val(description);
	$("#update_questionbankId").val(questionbankId);

	// 将span中的数据清空
	$(".isQustionBankNameInfo").html("");

	$('#modifyQL').modal();
}

// 点击修改模态框的保存按钮执行的操作
function submitUpdateQLibInfo() {
	// 非空校验
	var isNotNull = $("#form_UpdateQuestionBank").validate({
		rules : {
			questionbankname : "required",
			employeename : "required",
			description : "required",
			createtime : "required"

		},
		messages : {
			questionbankname : {
				required : "不能为空"
			},
			employeename : {
				required : "不能为空"
			},
			description : {
				required : "不能为空"
			},
			createtime : {
				required : "不能为空"
			}
		}
	});

	// 校验不为空且修改后的题库名不存在，执行提交操作
	if (isNotNull.form() && !isExistQuestionbankName) {
		// 将span中的数据清空
		$(".isQustionBankNameInfo").html("");

		$
				.ajax({
					url : '${pageContext.request.contextPath }/quesitonBank_updateQuestionBank.action',
					data : $("#form_UpdateQuestionBank").serialize(),
					type : 'POST',
					dataType : 'json',
					async : true,
					success : function(data) {
						alert(data.result);
						// 关闭模态框
						$('#modifyQL').modal('hide');
						// 刷新当前页面
						// window.location.reload();
						// 调用查询的方法
						findQuestionBankInfo();
					}
				});

	}
}

// 修改模态框的题库名称的离焦事件，判断输入的题库名是否存在
function existQuestionbankName_update(obj) {
	// 获取修改的题库名称
	var questionBankNameUpdate = $(obj).val();
	if (questionBankNameUpdate != $("#update_questionBankName_old").val()) {
		$
				.ajax({
					url : "quesitonBank_getDepartmentIdByBankName.action",
					data : {
						"questionBankName" : questionBankNameUpdate
					},
					type : 'POST',
					dataType : "json",
					async : true,
					success : function(data) {
						var isQustionBankNameInfo = "";
						// 判断题库名是否已经存在
						isExistQuestionbankName = data.departmentId != null;
						// alert(isExistQuestionbankName)
						if (data.departmentId != null) {
							isQustionBankNameInfo = "该题库名已存在，请重新输入！";
							$(".isQustionBankNameInfo").css("color", "red");
						} else {
							isQustionBankNameInfo = "该题库名可以使用！";
							$(".isQustionBankNameInfo").css("color", "green");
						}
						$(".isQustionBankNameInfo").html(isQustionBankNameInfo);
					}

				});
	} else {
		// 防止出现用户修改后又将其修改成原来的名称，去除提示信息
		// 将span中的数据清空
		$(".isQustionBankNameInfo").html("");
	}
}

/** *******************************题库删除*************************************** */
function delcfm(obj) {
	var questionBankid = $(obj).parents("tr").find(".find_questionbankid")
			.val();
	$("#delete_questionBankId").val(questionBankid);
	$('#delcfmModel').modal();
}
function deleteQuestionBankById() {
	var questionBankid = $.trim($("#delete_questionBankId").val());
	$
			.ajax({
				url : "quesitonBank_deleteQuestionBankById.action",
				data : {
					"questionBankId" : questionBankid
				},
				type : "POST",
				dataType : "json",
				success : function(data) {
					alert(data.result);
					findQuestionBankInfo();
				}
			});
}

/** *******************************分页*************************************** */

function questionLib_page(currentPage, totalCount, currentCount) {
	$('#questionLib_paginationIDU').pagination(
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
					// 向隐藏域中设置值
					$("#currentPage").val(pageNumber);
					$("#currentCount").val(b);
					// 调用查找函数
					findQuestionBankInfo();
				}
			});

}

/** *****************************试题批量导入**************************** */
// 点击页面上的试题批量导入执行的操作
function questionsBatchImport() {
	$("#importBtn").removeAttr("disabled");
	// 累计选择的个数，若等于1，才执行，否则提示
	var nums = 0;
	$.each($(".el_checks"), function(i, el_check) {
		if ($(this).prop("checked")) {
			// 将题库ID传递到批量试题导入的模态框中
			$("#input_questionBankId").val($(this).val());
			var tds = $(this).parents("tr").children("td");
			$("#input_questionBankName").text(tds.eq(2).html());
			var departmentId = $(this).parents("tr").find(".find_departmentid")
					.val();
			$("#input_departmentId").val(departmentId);
			nums++;
		}
	});
	if (nums == 1) {
		$("#questionBatchImportModal").modal();
	} else {
		alert("请先选择需要导入的题库！")
	}

}

// 批量导入试题模版的方法
function batchModalLoad() {
	var right = confirm("你想要下载试题批量导入模版吗？")
	if (right == true) {
		location.href = "exportBatchModel.action?name=批量导入试题模版.xls";
	}

}

// 点击批量导入模态框的导入按钮执行的操作
function inputQuestions() {
	if ($("#importQuestionsWord").val().length > 0) {
		$("#importBtn").prop("disabled", true);
		var formData = new FormData($("#form_inputQuestions")[0]);
		$.ajax({
			url : basePathUrl
					+ "/importQuestions_importQuestionsWithWord.action",
			type : "post",
			dataType : "json",
			/**
			 * 必须false才会自动加上正确的Content-Type
			 */
			contentType : false,
			/**
			 * 必须false才会避开jQuery对 formdata 的默认处理 XMLHttpRequest会对 formdata
			 * 进行正确的处理
			 */
			processData : false,
			data : formData,
			success : function(data) {
				alert(data.result)
				$('#questionBatchImportModal').modal("hide");
				// 调用查询函数
				findQuestionBankInfo();
				// 将上传的文件清空
				$("#importQuestionsWord").val("");
			},
			error : function() {
				alert("请求失败！")
			}
		});
	} else {
		alert("请选择要上传的文件！")
	}
}

/** *****************************页面刷新事件**************************** */

// 模态框右上角关闭符号执行页面刷新事件当点击模态框中的取消和关闭按钮时进行的操作
function closeModal_symbol() {
	// 刷新当前页面
	window.location.reload();
}
// 清空部门的方法
function clear_department() {
	$("#log").empty();
	$("#questionBankfind_DepartId").val('');
}


