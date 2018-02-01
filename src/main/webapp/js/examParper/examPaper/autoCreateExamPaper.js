/**
 * 快速生成试卷的JS qlq
 */
// jQuery函数
$(function() {
	// 初始化字典信息
	initEmployeeTypeDic();
	initKnowledgeDic();
	// 随机自动生成试卷的生成试卷点击事件
	$("#autoGenePaper").click(function() {
		validateCheckSuiji();// 验证通过后提交

	});
	// 根据知识点自动生成试卷的生成试卷点击事件
	$("#knowledgeGenepaperBtn").click(function() {
		validateCheckKnow();// 验证通过后提交
	});
	
	//创建方式按钮效果
	$(".el_createButton").click(function(){
		$(".el_createButton").removeClass("btn-default");
		$(".el_createButton").removeClass("btn-primary");
		$(".el_createButton").addClass("btn-default");
		$(this).addClass("btn-primary");
	})
});

var validateCheckSuiji = function() {
	// 进行数据验证
	var isNotNull = $("#autoGenePaperForm").validate({
		ignore : [],
		rules : {
			"danxuan_num" : {
				required : true,
				digits : true,
				min : 0,
				max : 200
			},// 验证文本框的。前边是 name 属性
			"danxuan_score" : {
				required : true,
				number : true,
				min : 0,
				max : 200
			},
			"duoxuan_num" : {
				required : true,
				digits : true,
				min : 0,
				max : 200
			},
			"duonxuan_score" : {
				required : true,
				number : true,
				min : 0,
				max : 200
			},
			"panduan_num" : {
				required : true,
				digits : true,
				min : 0,
				max : 200
			},
			"panduan_score" : {
				required : true,
				number : true,
				min : 0,
				max : 200
			}
		},
		messages : {
			"danxuan_num" : {
				required : "数目不能为空",
				digits : "必须输入整数",
				min : "最小是0",
				max : "最大是200"
			},// 验证文本框的。前边是 name 属性
			"danxuan_score" : {
				required : "分数不能为空",
				number : "必须输入数字",
				min : "最小是0",
				max : "最大是200"
			},
			"duoxuan_num" : {
				required : "数目不能为空",
				digits : "必须输入整数",
				min : "最小是0",
				max : "最大是200"
			},
			"duonxuan_score" : {
				required : "分数不能为空",
				number : "必须输入数字",
				min : "最小是0",
				max : "最大是200"
			},
			"panduan_num" : {
				required : "数目不能为空",
				digits : "必须输入整数",
				min : "最小是0",
				max : "最大是200"
			},
			"panduan_score" : {
				required : "分数不能为空",
				number : "必须输入数字",
				min : "最小是0",
				max : "最大是200"
			}
		}

	});

	// 如果验证通过可以提交数据
	if (isNotNull.form()) {
		$("#autoGenePaperForm").submit();// 提交表单
	}
}

// 验证根据知识点产生试卷
var validateCheckKnow = function() {
	// 进行数据验证
	var isNotNull = $("#knowledgeGenePaperForm").validate({
		ignore : [],
		rules : {
			"danxuan_num" : {
				required : true,
				digits : true,
				min : 0,
				max : 200
			},// 验证文本框的。前边是 name 属性
			"danxuan_score" : {
				required : true,
				number : true,
				min : 0,
				max : 200
			},
			"duoxuan_num" : {
				required : true,
				digits : true,
				min : 0,
				max : 200
			},
			"duonxuan_score" : {
				required : true,
				number : true,
				min : 0,
				max : 200
			},
			"panduan_num" : {
				required : true,
				digits : true,
				min : 0,
				max : 200
			},
			"panduan_score" : {
				required : true,
				number : true,
				min : 0,
				max : 200
			}
		},
		messages : {
			"danxuan_num" : {
				required : "数目不能为空",
				digits : "必须输入整数",
				min : "最小是0",
				max : "最大是200"
			},// 验证文本框的。前边是 name 属性
			"danxuan_score" : {
				required : "分数不能为空",
				number : "必须输入数字",
				min : "最小是0",
				max : "最大是200"
			},
			"duoxuan_num" : {
				required : "数目不能为空",
				digits : "必须输入整数",
				min : "最小是0",
				max : "最大是200"
			},
			"duonxuan_score" : {
				required : "分数不能为空",
				number : "必须输入数字",
				min : "最小是0",
				max : "最大是200"
			},
			"panduan_num" : {
				required : "数目不能为空",
				digits : "必须输入整数",
				min : "最小是0",
				max : "最大是200"
			},
			"panduan_score" : {
				required : "分数不能为空",
				number : "必须输入数字",
				min : "最小是0",
				max : "最大是200"
			}
		}

	});

	// 如果验证通过可以提交数据
	if (isNotNull.form()) {
		$("#knowledgeGenePaperForm").submit();// 提交表单
	}

}
/** ****S 初始化知识点字典********* */
function initKnowledgeDic() {
	$.post(contextPath + '/dic_getDictionaryIdAndNamesByUpId.action', {
		"upId" : "200"
	}, showKnowledgeDic, 'json');
};
function showKnowledgeDic(response) {
	if (response != null && response.dictionary != null) {
		var dictionary = response.dictionary;// 获取字段返回的值
		for (var i = 0; i < dictionary.length; i++) {
			$("#knowledgeType").append(
					'<label class="el_duoxuan"><input type="checkbox" value="'
							+ dictionary[i].dictionaryId
							+ '" name="knowledges">'
							+ dictionary[i].dictionaryName + '</label>');
		}
	}
}
/** ****E 初始化知识点字典********* */
/** ****S 初始化工种字典********* */
function initEmployeeTypeDic() {
	$.post(contextPath + '/dic_getDictionaryIdAndNamesByUpId.action', {
		"upId" : "100"
	}, showEmployeeTypeDic, 'json');
};
function showEmployeeTypeDic(response) {
	if (response != null && response.dictionary != null) {
		var dictionary = response.dictionary;// 获取字段返回的值
		for (var i = 0; i < dictionary.length; i++) {
			$("#employeeTypes").append(
					'<label class="el_duoxuan"><input type="checkbox" value="'
							+ dictionary[i].dictionaryId
							+ '" name="employeeTypes">'
							+ dictionary[i].dictionaryName + '</label>');
		}
	}
}
/** ****E 初始化工种字典********* */
