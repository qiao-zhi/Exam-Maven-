/** *************************页面加载函数************************** */

$(function() {
	// 初始化查询表单的下拉框
	queryAllQuestionBankName();
	initKnowledgeDic();
	// 获取地址栏中传递的题库ID
	var questionbankId = window.location.href.split("=")[1];
	$("#hiddenQuestionBankId").val(questionbankId);
	findQuestionsInfo();
});

/** *****************************组合条件查询*************************** */

// 查询所有的题库名称初始化下拉框
function queryAllQuestionBankName() {
	$.ajax({
				url : "questions_getQeustionBankNameListByDeptId.action",
				type : "POST",
				data : {
					"departmentId" : departmentIdFromSess
				},
				dataType : "json",
				async : true,
				success : function(data) {
					var allQuestionBankName = data.questionBankNameList;
					var optionStrQuestionBankName = "<option value=''>--请选择--</option>";
					//获取地址栏中的题库ID
					var questionbankId = window.location.href.split("=")[1];
					for (var i = 0; i < allQuestionBankName.length; i++) {					
						  if(questionbankId==allQuestionBankName[i].questionBankId){
							  optionStrQuestionBankName += "<option value=" +
							  allQuestionBankName[i].questionBankId+" selected>" 
							  +allQuestionBankName[i].questionBankName+"</option>";
						  } else{							  							
							  optionStrQuestionBankName += "<option value="
								  + allQuestionBankName[i].questionBankId + ">"
								  + allQuestionBankName[i].questionBankName
								  + "</option>";
						  }
					}

					// 先将下拉列表清空
					$("#query_QuestionBankNameList").empty();
					$("#move_QuestionBankNameList").empty();
					$("#query_QuestionBankNameList").append(
							optionStrQuestionBankName);
					$("#move_QuestionBankNameList").append(
							optionStrQuestionBankName)					
					$("#move_QuestionBankNameList option[selected]").removeAttr("selected");
				}

			})

}
// 初始化知识点信息
function initKnowledgeDic() {
	$.post(basePathUrl + '/dic_getDicNamesByUpid.action', {
		"upId" : "200"
	}, showKnowledgeDic, 'json');
}
function showKnowledgeDic(response) {
	if (response != null && response.names != null) {
		var names = response.names;// 获取字段返回的值
		$("#query_knowledgeList").append("<option value=''>--请选择--</option>")
		for (var i = 0; i < names.length; i++) {
			$("#query_knowledgeList").append(
					'<option value="' + names[i] + '">' + names[i]
							+ '</option>')
		}
	}
}
// 点击试题管理页面的查询按钮执行的操作
function searchQuestionsInfo() {

	// 清空隐藏域中当前页的数据
	$("#currentPage").val("");
	$("#hiddenQuestionBankId").removeAttr("value");
	// 调用分页查询试题信息的函数
	findQuestionsInfo();
}

// 调用分页查询试题信息的函数
function findQuestionsInfo() {
	$.ajax({
				url : "questions_findQuestionsInfo.action",
				data : $("#form_findQuestionsInfo").serialize(),
				type : "POST",
				dataType : "json",
				success : showQuestionsInfo,
				error : function() {
					alert("请求失败！")
				}
			});
}

function showQuestionsInfo(data) {
	var result = data;
	// 当前页
	var currentPage = result.pageBean.currentPage;
	// 总条数
	var totalCount = result.pageBean.totalCount;
	// 总条数
	var currentCount = result.pageBean.currentCount;
	var questionsListInfo = result.pageBean.productList
	var showQuestionListInfo = "";
	if (questionsListInfo != null) {

		for (var i = 0; i < questionsListInfo.length; i++) {
			var index = (currentPage - 1) * currentCount + i + 1;
			showQuestionListInfo += "<tr><td><input type='checkbox' class='el_checks' name='questionIdBatch' value='"
					+ questionsListInfo[i].questionid
					+ "'/></td><td>"
					+ index
					+ "<input class='find_questionid' type='hidden' value='"
					+ questionsListInfo[i].questionid
					+ "'/>"
					+ "<input class='find_answer' type='hidden' value='"
					+ questionsListInfo[i].answer
					+ "'/>"
					+ "<input class='find_analysis' type='hidden' value='"
					+ questionsListInfo[i].analysiswithtag
					+ "'/>"
					+ "</td><td>"
					+ questionsListInfo[i].questionwithtag
					+ "</td><td>"
					+ questionsListInfo[i].questionbankname
					+ "</td><td>"
					+ questionsListInfo[i].type
					+ "</td><td>"
					+ questionsListInfo[i].emplloyeename
					+ "</td><td>"
					+ Format(new Date(questionsListInfo[i].uploadtime.replace(
							/T/g, " ").replace(/-/g, "/")),
							"yyyy-MM-dd HH:mm:ss")
					+ "</td><td>";
			if(hasQuestionbankManager){
				showQuestionListInfo += "<a href='questions_modifyQuestionInfoById.action?questionId="
					+ questionsListInfo[i].questionid
					+ "'>修改</a>&nbsp;&nbsp;"
					+ "<a href='javascript:void(0)' class='el_delButton' onClick='delcfm(this)'>删除</a>&nbsp;&nbsp;"
			}
			showQuestionListInfo += "<a href='javascript:void(0)' onClick='el_questionInfo(this)'>预览</a>"
					+ "</td></tr>";
		}
	}

	// 清空表格
	$("#questionsListInfo").empty();
	// 添加信息
	$("#questionsListInfo").append(showQuestionListInfo);
	questions_page(currentPage, totalCount, currentCount)
}

/** *******************************批量操作*************************************** */

$(function() {
	// 全选和全不选操作
	$("#el_checkQuanxuan").change(function() {
		if ($(this).prop("checked") == true) {
			$(".el_checks").prop("checked", "true");
		} else {
			$(".el_checks").removeAttr("checked");
		}
	})

	/* 批量操作 */
	$("#el_setAllMove").hide();
	$("#setCheckState").hide();
	$("#el_allOption").change(function() {
		// 累计选择的个数，若大于1，才执行，否则提示
		var nums = 0;
		$.each($(".el_checks"), function(i, el_check) {
			if ($(this).prop("checked")) {
				nums++;
			}
		});
		if (nums >= 1) {
			var optionContent = $(this).find("option:selected");
			if (optionContent.val() == "batch_delete") { // 批量删除
				$('#delcfmModel_batch').modal();
			} else if (optionContent.val() == "batch_status") { // 批量状态设置
				$("#setCheckState").show()
				$("#el_setAllMove").hide();
			} else if (optionContent.val() == "batch_move") { // 批量移动	
				//清空下拉框
				$("#move_QuestionBankNameList").val('');
				$("#el_setAllMove").show();
				$("#setCheckState").hide();
			}

		} else {
			$(this).find("");
			$("#el_default").prop("selected", "true");
			alert("您还没选择操作列，请先选择需要操作的列。")
		}
	})

})
// 点击批量删除的模态框的确认按钮执行的操作
function deleteQuestions_batch() {
	var obj = document.getElementsByName("questionIdBatch");
	var questionIds = [];
	for (k in obj) {
		if (obj[k].checked)
			questionIds.push(obj[k].value);
	}
	$.ajax({
				url : "questions_deleteQuestionByIds.action",
				data : {
					"questionIds" : questionIds
				},
				traditional : true,
				type : "POST",
				dataType : "json",
				success : function(data) {
					alert(data.result);
					$("#el_default").prop("selected",true);
					// 调用查询当前页面的函数
					findQuestionsInfo();
				},
				error : function() {
					alert("请求失败！")
				}
			});

}

// 点击批量移动进行的操作
function moveQuestions_batch() {
	var obj = document.getElementsByName("questionIdBatch");
	var questionIds = [];
	for (k in obj) {
		if (obj[k].checked)
			questionIds.push(obj[k].value);
	}
	var questionBankId = $("#move_QuestionBankNameList").val();
	if(questionBankId==""){
		alert("请选择一个目标题库！")
	}else{		
		$.ajax({
			url : "questions_batchMoveQuestionsByIds.action",
			data : {
				"questionIds" : questionIds,
				"questionBankId" : questionBankId
			},
			traditional : true,
			type : "POST",
			dataType : "json",
			success : function(data) {				
				$("#el_default").prop("selected",true);				
				alert(data.result);
				$("#el_setAllMove").hide();
				// 调用查询当前页面的函数
				findQuestionsInfo();
			},
			error : function() {
				alert("请求失败！")
			}
		});
	}
}

/** *******************************预览*************************************** */
function el_questionInfo(obj) {
	// 获取当前行中的值
	var tds = $(obj).parents("tr").children("td");
	$("#detail_questionContent").html($(tds).eq(2).html());
	// 获取隐藏域中的值
	var questionAnswer = $(obj).parents("tr").find(".find_answer").val();
	var analysis = $(obj).parents("tr").find(".find_analysis").val();
	var questionId = $(obj).parents("tr").find(".find_questionid").val();
	// 将答案转换成英文字母显示
	questionAnswer = questionAnswer.toString().replace("1", "A").replace("2",
			"B").replace("3", "C").replace("4", "D").replace("5", "E");
	$("#detail_questionAnswer").html(questionAnswer);
	$("#detail_analysis").html(analysis);

	$
			.ajax({
				url : "questions_getQuestionAndOptionsInfo.action",
				data : {
					"questionId" : questionId
				},
				type : "POST",
				dataType : "json",
				success : function(data) {
					// alert(data.questionInfo.options.length)
					var optionList = data.questionInfo.options
					var showOptionsInfo = "";
					for (var i = 0; i < optionList.length; i++) {
						if (optionList.length > 2) {
							showOptionsInfo += "<tr><td>"
									+ optionList[i].optionsequence.toString()
											.replace("1", "A")
											.replace("2", "B")
											.replace("3", "C")
											.replace("4", "D")
											.replace("5", "E") + "</td><td>"
									+ optionList[i].optioncontent
									+ "</td></tr>";
						} else {
							showOptionsInfo += "<tr><td>"
									+ optionList[i].optioncontent
									+ "</td></tr>";
						}
					}
					// 清空表格
					$("#optionsListInfo").empty();
					// 添加信息
					$("#optionsListInfo").append(showOptionsInfo);
				}
			});

	$('#el_questionInfo').modal();
}

/** *******************************删除*************************************** */
function delcfm(obj) {
	var questionId = $(obj).parents("tr").find(".find_questionid").val();
	$("#delete_questionId").val(questionId);
	$('#delcfmModel').modal();
}
// 点击删除模态框的确定按钮执行的操作
function deleteQuestionById() {
	var questionId = $.trim($("#delete_questionId").val());
	$
			.ajax({
				url : "questions_deleteQuestionById.action",
				data : {
					"questionId" : questionId
				},
				type : "POST",
				dataType : "json",
				success : function(data) {
					alert(data.result);
					// 调用查询当前页面的函数
					findQuestionsInfo();
				},
				error : function() {
					alert("请求失败！")
				}
			});
}

/** *******************************分页*************************************** */

function questions_page(currentPage, totalCount, currentCount) {
	$('#questions_paginationIDU').pagination(
			{
				// 组件属性
				"total" : totalCount,// 数字 当分页建立时设置记录的总数量
				"pageSize" : currentCount,// 数字 每一页显示的数量 10
				"pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8, 15, 50],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, b) {
					// 向隐藏域中设置值
					$("#currentPage").val(pageNumber);
					$("#currentCount").val(b);
					// 调用查找函数
					findQuestionsInfo();
				}
			});

}

//清除下拉框选中的值
function initclear(){	
	$("#query_QuestionBankNameList option[selected]").removeAttr("selected");
}