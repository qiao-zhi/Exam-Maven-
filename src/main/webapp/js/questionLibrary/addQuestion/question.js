/***************************动态修改选项的相关操作*******************************/
$(function() {
	var qContent = $("#el_choose");

	qContent.children(".danxuan").load("danxuanOptions.txt",
			function(response) {
				qContent.children(".danxuan").html(response);
				optionsOperate();
				//显示HTML内容
				qContent.children(".danxuan").show();

			});

	/******************动态选择需要添加的试题*********************/

	$(".el_addquestions").click(
			function() {

				var index = $(this).val();
				if (index == "单选题") {
					qContent.children(".panduan").empty();
					qContent.children(".duoxuan").empty();
					$(".el_bigBlock2").css("height", "300px");
					qContent.children(".danxuan").load("danxuanOptions.txt",
							function(response) {
								qContent.children(".danxuan").html(response);
								//显示HTML内容
								qContent.children(".danxuan").show();
								optionsOperate();
							});
				} else if (index == "多选题") {
					qContent.children(".danxuan").empty();
					qContent.children(".panduan").empty();
					$(".el_bigBlock2").css("height", "300px");
					qContent.children(".duoxuan").load("duoxuanOptions.txt",
							function(response) {
								qContent.children(".duoxuan").html(response);
								optionsOperate();
								//显示HTML内容
								qContent.children(".duoxuan").show();

							});
					$(this).children("danxuan").css("checked", "false");
				} else if (index == "判断题") {
					//将其他两种类型的题清空
					qContent.children(".danxuan").empty();
					qContent.children(".duoxuan").empty();
					$(".el_bigBlock2").css("height", "130px");
					qContent.children(".panduan").load("panduan.txt",
							function(response) {
								qContent.children(".panduan").html(response);
								qContent.children(".panduan").show();
							});
				}
			});

	//增加选项
	$(".zjxx")
			.live(
					"click",
					function() {
						var zjxx_html = $(this).prev(".title_itram").html();
						var zjxx_html2 = "<div class='title_itram'>"
								+ zjxx_html + "</div>";
						$(this).before(zjxx_html2);

						/*为了动态的显示内容*/
						$(".xxk_xzqh_box .title_itram")
								.hover(
										function() {
											var html_cz = "<div class='kzqy_czbut'>"
													+ "<a href='javascript:void(0)' class='sy'>上移</a>"
													+ "<a href='javascript:void(0)'  class='xy'>下移</a>"
													+ "<a href='javascript:void(0)' class='del' >删除</a></div>";
											$(this).css({
												"border" : "1px solid #0099ff"
											});
											$(this).children(".kzjxx_iteam")
													.after(html_cz)
										},
										function() {
											$(this).css({
												"border" : "1px solid #fff"
											});
											$(this).children(".kzqy_czbut")
													.remove();
										})
					});

	//删除一行
	$(".del").live(
			"click",
			function() {
				/*如果点击确定，则执行下边*/
				var res = confirm("确认删除吗？");
				if (res) {
					//获取编辑题目的个数
					var zuxxs_num = $(this).parent(".kzqy_czbut").parent(
							".title_itram").parent(".xxk_xzqh_box").children(
							".title_itram").length;
					//当个数小于1时禁止删除
					if (zuxxs_num > 1) {
						$(this).parent(".kzqy_czbut").parent(".title_itram")
								.remove();
					} else {
						alert("手下留情");
					}
				}
			});

	/*
	 * 选项上边增加操作
	 */
	$(".xxk_xzqh_box .title_itram")
			.hover(
					function() {
						var html_cz = "<div class='kzqy_czbut'>"
								+ "<a href='javascript:void(0)' class='sy'>上移</a>"
								+ "<a href='javascript:void(0)'  class='xy'>下移</a>"
								+ "<a href='javascript:void(0)' class='del' >删除</a></div>";
						$(this).css({
							"border" : "1px solid #0099ff"
						});
						$(this).children(".kzjxx_iteam").after(html_cz)
					}, function() {
						$(this).css({
							"border" : "1px solid #fff"
						});
						$(this).children(".kzqy_czbut").remove();
					})

	//下移
	$(".xy").live(
			"click",
			function() {
				//获取选项数量
				var leng = $(this).parent(".kzqy_czbut").parent(".title_itram")
						.parent("").children(".title_itram").length;
				var dqgs = $(this).parent(".kzqy_czbut").parent(".title_itram")
						.index();

				if (dqgs < leng - 1) {
					//本框
					var v1 = $(this).parent(".kzqy_czbut").parent(
							".title_itram");
					v1.next().after(v1);

				} else {
					alert("到底了");
				}
			});

	//上移
	$(".sy").live("click", function() {
		var dqgs = $(this).parents(".title_itram").index();
		if (dqgs > 0) {
			//本框
			var v1 = $(this).parent(".kzqy_czbut").parent(".title_itram");
			v1.prev().before(v1);

		} else {
			alert("到头了");
		}
	});

})
/**************************给每个选项增加操作的函数*******************************/
function optionsOperate() {
	$(".xxk_xzqh_box .title_itram")
			.hover(
					function() {
						var html_cz = "<div class='kzqy_czbut'>"
								+ "<a href='javascript:void(0)' class='sy'>上移</a>"
								+ "<a href='javascript:void(0)'  class='xy'>下移</a>"
								+ "<a href='javascript:void(0)' class='del' >删除</a></div>";
						$(this).css({
							"border" : "1px solid #0099ff"
						});
						$(this).children(".kzjxx_iteam").after(html_cz)
					}, function() {
						$(this).css({
							"border" : "1px solid #fff"
						});
						$(this).children(".kzqy_czbut").remove();
					})
}

/***************************页面加载函数*******************************/

$(function() {
	queryQuestionBankNameByDeptId();
	initKnowLedgeDic();
	//设置validate校验隐藏标签中的值
	$.extend($.validator.defaults, {
		ignore : ""
	});
});

/***************************添加试题*********************************/

//初始化添加试题的下拉框
function queryQuestionBankNameByDeptId() {
	//alert("ceshi"+departmentIdFromSess)
	$
			.ajax({
				url : basePathUrl +"/questions_getQeustionBankNameListByDeptId.action",
				type : "POST",
				data : {
					"departmentId" : departmentIdFromSess
				},
				dataType : "json",
				async : true,
				success : function(data) {
					//alert(data.questionBankNameList.length)
					var allQuestionBankName = data.questionBankNameList;
					//console.log(allQuestionBankName)
					var optionStrQuestionBankName = "";
					for (var i = 0; i < allQuestionBankName.length; i++) {
						if (i == 0)
							//设置默认选中第一条  value值设置题库ID，标签中间设置题库名称
							optionStrQuestionBankName += "<option value='"
									+ allQuestionBankName[i].questionBankId
									+ "' selected>"
									+ allQuestionBankName[i].questionBankName
									+ "</option>";
						else
							optionStrQuestionBankName += "<option value="
									+ allQuestionBankName[i].questionBankId
									+ ">"
									+ allQuestionBankName[i].questionBankName
									+ "</option>";
					}
					//先将下拉列表清空
					$("#add_QuestionBankNameList").empty();
					$("#add_QuestionBankNameList").append(
							optionStrQuestionBankName);

				}

			})
}

//初始化知识点信息
function initKnowLedgeDic() {
	$.post(basePathUrl + '/dic_getDicNamesByUpid.action', {
		"upId" : "200"
	}, showKnowLedgeDic, 'json');
}
function showKnowLedgeDic(response) {
	if (response != null && response.names != null) {
		var names = response.names;// 获取字段返回的值		
		for (var i = 0; i < names.length; i++) {
			$("#sel_productTag").append(
					'<option value="' + names[i] + '">' + names[i]
							+ '</option>')
		}
	}
}
//点击添加试题页面执行的操作
function add_question() {

	var isNotNull = $("#form_addQuestion").validate({
		rules : {
			"question.questionbankid" : "required",
			"question.emplloyeename" : "required",
			"question.uploadtime" : "required",
			"question.questionwithtag" : "required",
			"question.analysiswithtag" : "required",
			"question_answer" : "required"

		},
		messages : {
			"question.questionbankid" : {
				required : "请先创建一个题库！"
			},
			"question.emplloyeename" : {
				required : "不能为空"
			},
			"question.uploadtime" : {
				required : "不能为空"
			},
			"question.questionwithtag" : {
				required : "不能为空"
			},
			"question.analysiswithtag" : {
				required : "不能为空"
			},
			"question_answer" : {
				required : "至少选择两个答案"
			}
		},
		errorPlacement : function(error, element) { //指定错误信息位置
			if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
				var eid = element.attr('name'); //获取元素的name
				error.prependTo(element.parents("#el_choose")); //将错误信息添加当前元素的父结点后面
			} else {
				error.insertAfter(element);
			}
		}
	});

	if (isNotNull.form()) {
		var optionsIsNotNull = false;
		//定义一个数组,存放试题答案
		var answer = [];
		var obj = document.getElementsByName("question_answer");
		for (var i = 0; i < obj.length; i++) {
			if (obj[i].checked) {
				answer.push(i + 1)
			}
		}
		var optionListObj = $(".input_wenbk");
		//判断当长度为0时证明是多选题，不做处理
		if (optionListObj.length == 0) {
			optionsIsNotNull = true;
		}
		//拼接options的<input>标签信息
		var optionListInfo = "";
		for (var i = 0; i < optionListObj.length; i++) {
			if (optionListObj.eq(i).val().length > 0) {
				var j = i + 1;
				optionListInfo += "<input name='options[" + i
						+ "].optioncontent' type='hidden' value='"
						+ optionListObj.eq(i).val() + "'/>"
						+ "<input name='options[" + i
						+ "].optionsequence' type='hidden' value='" + j + "'/>";
				optionsIsNotNull = true;
			} else {
				alert("第" + (i + 1) + "个选项不能为空！");
				optionsIsNotNull = false;
				optionListInfo = "";
				break;
			}
		}
		if (optionsIsNotNull) {
			//判断当存储答案的数组中有值时进行拼接操作
			if (answer.length > 0) {
				//拼接试题答案
				optionListInfo += "<input name='question.answer' type='hidden' value='"
						+ answer + "'/>";
			}
			$("#options_content").append(optionListInfo);
			$
					.ajax({
						url : '${pageContext.request.contextPath }/questions_addQuestion.action',
						data : $("#form_addQuestion").serialize(),
						type : 'POST',
						dataType : 'json',
						async : true,
						success : function(data) {
							alert(data.result);
							//重定向到试题管理页面
							window.location.href = "questionManage.jsp"
						}
					});
		}
	}

}
