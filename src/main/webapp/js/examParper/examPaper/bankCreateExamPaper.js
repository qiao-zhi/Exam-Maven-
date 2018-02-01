/**
 * qlq写的 根据题库生成试卷的JS 用于生成查询所有右边带复选框的题选择题
 */
// 页面加载后执行的函数
var isLoad = false;// 全局变量记录是否已经加载
$(function() {
	/**
	 * 题库试题的提交按钮的点击事件
	 */
	$("#queryBankBtn").click(function() {
		searchQuestions();
	});

});
// 查询试题
var searchQuestions = function() {
	$.ajax({
				url : contextPath + '/createPaper_bankGenePaper.action',
				data : {
					'level' : $("[name='exampaper.level']").val(),
					'type' : $("select[name='type'] option:selected").val(),
					'dictionaryId' : $("select[name='dictionaryId'] option:selected")
							.val()
				},
				async : true,
				dataType : 'json',
				type : 'POST',
				success : showQuestions,
				error : function() {
					alert("ajax查询试题失败！")
				}
			});
}
// 显示到右边题库试题栏
var showQuestions = function(questions) {
	$("#bankQuestions").html("");// 清空题
	for (var i = 0, length = questions.length; i < length; i++) {
		// 拼接单选题
		if (questions[i].type == '单选题') {
			var danxuan = "<li class='list-group-item el_drag' style='height: 30px;'>"
					+ "<input type='checkbox' onclick='el_tiku_checkedButtonF(this)' class='el_tiku_checkedButton'> "
					+ "<input type='hidden' id='"
					+ questions[i].questionid
					+ "' class='ques_id'> <!--放 id -->"
					+ "<!--单选题  class = 'dan'--><div class='movie_box dan'><div class='tm_btitlt'>"
					+ questions[i].question + "</div><ul class='wjdc_list'>";
			// 拼接单选题选项
			for (var j = 0, options_length = questions[i].options.length; j < options_length; j++) {
				danxuan += "<li><label> <input type='radio' value=''><span>"
						+ questions[i].options[j].optioncontent
						+ "</span></label></li>";
			}
			danxuan += '</ul>'
					+ '答案: <input type="hidden" class="ques_answer" value="'
					+ questions[i].answer + '">'
					+ updateAnswerFormat(questions[i].answer, "12345", "ABCDE")
					+ '<br />'
					/*+ '解析:<input type="hidden" class="ques_analy" value="'
					+ questions[i].analysis + '">' + questions[i].analysis*/
					+ '</div> <span class="el_unflod"> &or;</span></li>';
			$("#bankQuestions").append(danxuan);
		}
		// 拼接多选题
		if (questions[i].type == '多选题') {
			var duoxuan = "<li class='list-group-item el_drag' style='height: 30px;'>"
					+ "<input type='checkbox' onclick='el_tiku_checkedButtonF(this)' class='el_tiku_checkedButton'>"
					+ "<input type='hidden' id='"
					+ questions[i].questionid
					+ "' class='ques_id'> <!--放 id -->"
					+ "<!--单选题  class = 'dan'--><div class='movie_box duo'><div class='tm_btitlt'>"
					+ questions[i].question + "</div><ul class='wjdc_list'>";
			// 拼接多选题选项
			for (var j = 0, options_length = questions[i].options.length; j < options_length; j++) {
				duoxuan += "<li><label> <input type='checkbox' value=''><span>"
						+ questions[i].options[j].optioncontent
						+ "</span></label></li>";
			}
			duoxuan += '</ul>'
					+ '答案: <input type="hidden" class="ques_answer" value="'
					+ questions[i].answer + '">'
					+ updateAnswerFormat(questions[i].answer, "12345", "ABCDE")
					+ '<br />'
					/*+ '解析:<input type="hidden" class="ques_analy" value="'
					+ questions[i].analysis + '">' + questions[i].analysis*/
					+ '</div> <span class="el_unflod"> &or;</span></li>';
			$("#bankQuestions").append(duoxuan);
		}
		// 拼接判断题
		if (questions[i].type == '判断题') {
			var panduan = "<li class='list-group-item el_drag' style='height: 30px;'>"
					+ "<input type='checkbox' onclick='el_tiku_checkedButtonF(this)' class='el_tiku_checkedButton'> "
					+ "<input type='hidden' id='"
					+ questions[i].questionid
					+ "' class='ques_id'><!--放 id -->"
					+ "<!--单选题  class = 'dan'--><div class='movie_box pan'><div class='tm_btitlt'>"
					+ questions[i].question + "</div><ul class='wjdc_list'>";
			// 拼接判断题选项
			for (var j = 0, options_length = questions[i].options.length; j < options_length; j++) {
				panduan += "<li><label> <input type='radio' value=''><span>"
						+ questions[i].options[j].optioncontent
						+ "</span></label></li>";
			}
			panduan += '</ul>'
					+ '答案: <input type="hidden" class="ques_answer" value="'
					+ questions[i].answer + '">'
					+ updateAnswerFormat(questions[i].answer, "12345", "ABCDE")
					+ '<br />'
					/*+ '解析:<input type="hidden" class="ques_analy" value="'
					+ questions[i].analysis + '">' + questions[i].analysis*/
					+ '</div> <span class="el_unflod"> &or;</span></li>';
			$("#bankQuestions").append(panduan);

		}
	}

	validateCheckedQuestion();// 验证哪些选中打上对勾
}

/**
 * 将source字符串按照s1-s2替换，例如:s1:1234,s2:ABCD则为将source中1换为A，2换为B```
 */
function updateAnswerFormat(source, s1, s2) {
	for (var i = 0, length_1 = s1.length; i < length_1; i++) {
		source = source.replace(s1.charAt(i), s2.charAt(i));
	}
	return source;
}

// 验证已经选择的题，如果为已经选中的打上对勾
function validateCheckedQuestion() {
	var $leftIds = $(".el_addEPleft").find(".ques_id")// 查到左边隐藏的ID元素
	var leftIds = [];// 获取到左边已经选择的试题ID数组
	for (var i = 0, length_1 = $leftIds.length; i < length_1; i++) {
		leftIds[i] = $($leftIds[i]).attr("id");
	}
	// 根据左边的元素与右边查找如果已经选中的话打上对勾
	for (var k = 0, length_2 = leftIds.length; k < length_2; k++) {
		$("#bankQuestions").find("[id='" + leftIds[k] + "']").siblings(
				".el_tiku_checkedButton").attr("checked", "true");
	}

}
