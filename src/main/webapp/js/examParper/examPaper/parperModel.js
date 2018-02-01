/**
 * 试卷预览 Created by yorge on 2017/9/15.
 */
/* 回到顶部 */
$(function() {
	$(window).scroll(function() {
		if ($(window).scrollTop() > 500) {
			$('#el_returnTop').css('display', 'block');
		} else {
			$('#el_returnTop').css('display', 'none');
		}
	});

	/* 导航全选 */
	$(".el_Inputlulli1 input").change(function() {
		if ($(this).prop("checked") == true) {
			$(".el_Inputlulli input").prop("checked", "true");
		} else {
			$(".el_Inputlulli input").removeAttr("checked")
		}
	});

});

/* 导出 */
function output() {
	$('#outputModel').modal();
}
function urlSubmit() {
	var url = $.trim($("#url").val());// 获取会话中的隐藏属性URL
	window.location.href = url;
}

function extPaperAnswer() {
	if (confirm("确定导出试卷答案?")) {
		window.location.href = contextPath + "/extPaperAnswer.action?paperId="
				+ $("[name='paperId']").val();
	}
}
function extPaper() {
	if (confirm("确定导出试卷?")) {
		window.location.href = contextPath + "/extPaper.action?paperId="
				+ $("[name='paperId']").val();
	}
}