$(function() {
	/*回到顶部*/
	$(window).scroll(function() {
		if ($(window).scrollTop() > 500) {
			$('#el_returnTop').css('display', 'block');
		} else {
			$('#el_returnTop').css('display', 'none');
		}
	});

	//点击交卷按钮执行的操作
	$("#el_examSubmit").click(function() {
		var res = confirm("确认交卷吗？");
		if (res == false) {
			return false;
		} else {
			$(this).prop("disabled", true);
			$("#tempSave").prop("disabled", true);
			checkOnlineExamInfoAnswer();
		}
	});

	//考生作答后，对应右侧提示栏的信息发生变化
	$(".el_qusSelect").children().children().children("input").change(
			function() {
				//判断是否选中
				var res = $(this).prop("checked");

				//获得题号
				var questionNum = $(this).parents(".el_questionMain").children(
						".el_questionTitle").children(".nmb").val();
				//获得题型
				var questionClassName = $(this).parents(".el_questionMain")
						.children(".el_questionTitle").prop("className");
				var lastIndex = questionClassName.lastIndexOf(' ');
				var lastClassName = questionClassName.substring(lastIndex + 1);

				if (res) {
					if (lastClassName == "danxuanti") {
						//获取右侧对应的试题
						var dan = $(".el_danxuan").children("li").eq(
								questionNum - 1);
						//给对应题号，添加橘色背景
						dan.css("background-color", "orangered");
					} else if (lastClassName == "duoxuanti") {
						//获取右侧对应的试题
						var duo = $(".el_duoxuan").children("li").eq(
								questionNum - 1);
						//给对应题号，添加橘色背景
						duo.css("background-color", "orangered");
					} else {
						//获取右侧对应的试题
						var $panduan = $(".el_panduan").children("li").eq(
								questionNum - 1);
						//给对应题号，添加橘色背景
						$panduan.css("background-color", "orangered");
					}
				} else {
					if (lastClassName == "danxuanti") {
						//获取右侧对应的试题
						var dan2 = $(".el_danxuan").children("li").eq(
								questionNum - 1);
						//给对应题号，添加橘色背景
						dan2.css("background-color", "none");
					} else if (lastClassName == "duoxuanti") {
						//获取右侧对应的试题
						var duo2 = $(".el_duoxuan").children("li").eq(
								questionNum - 1);
						var $options = $(this).parents(".el_qusSelect")
								.children().children().children("input");
						var count = 0;
						for (var i = 0; i < $options.length; i++) {
							if ($options.eq(i).prop("checked")) {
								count++;
							}
						}
						if (count == 0) {
							//给对应题号，添加橘色背景
							duo2.css("background-color", "");
						}
					} else {
						//获取右侧对应的试题
						var $panduan = $(".el_panduan").children("li").eq(
								questionNum - 1);
						//给对应题号，添加橘色背景
						$panduan.css("background-color", "none");
					}
				}
			})

	/*出线警告*/

	/* var conH = $(".container").css("height");
	 conH = parseInt(conH.substring(0,conH.length-2)) + 120 + "px";
	 $("#bodySon").css("height",conH);

	 $("#bodySon").mouseleave(function () {
	     alert("警告：你离开考试现场3次，系统将自动提交试卷！（你还有2次机会）")
	 })*/

});

/***************************页面加载函数***************************/
//开始答题时间获取系统当前时间
var startAnswerTime = Format(getServerDate(), "yyyy-MM-dd HH:mm:ss");
//规定答题时间
var lengthAnswerTime;
var timeOut;
$(function() {
	initialization();
	savaOnlineExamInfo();
	//倒计时方法
	//timeOver = setInterval("timer()", 1000);
});
//初始化函数
function initialization() {
	//计算考试时长,去掉小数
	//var lengthOfExam = parseInt((examInfo.endTime.time-examInfo.startTime.time)/1000/60);
	$("#exam_lengthTime").text(examInfo.AnswerTime);
	$("#el_examTitle").text(examInfo.examName);
	$("#onlineExam_exmaId").val(examInfo.examId);

}

//倒计时
function timer() {
	/*//每执行一次-1000毫秒
	ts = ts-1000;*/
	//获取的时间为考试的结束时间
	//var ts = (new Date(examInfo.endTime.time)) - (new Date());//计算剩余的毫秒数
	//判断若考试的结束时间的时间戳小于考生答题结束时间的时间戳，以考试结束时间开始倒计时操作
	if (new Date(examInfo.endTime.time) - lengthAnswerTime > 0) {
		var ts = (new Date(lengthAnswerTime)) - (getServerDate());//计算剩余的毫秒数
	} else {
		var ts = (new Date(examInfo.endTime.time)) - (getServerDate());//计算剩余的毫秒数
	}
	var hh = parseInt(ts / 1000 / 60 / 60, 10);//计算剩余的小时数      
	var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数      
	var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数     
	hh = checkTime(hh);
	mm = checkTime(mm);
	ss = checkTime(ss);
	if (ts > 0 && hh > 0) {
		$("#timer").text(hh + "时" + mm + "分" + ss + "秒");
	} else if (ts > 0 && mm > 0) {
		$("#timer").text(mm + "分" + ss + "秒");
	} else if (ts > 0 && ss > 0) {
		$("#timer").text(ss + "秒");
	} else {
		clearInterval(timeOut);
		checkOnlineExamInfoAnswer();
	}

}

function checkTime(i) {
	if (i < 10) {
		i = "0" + i;
	}
	return i;
}

//点击临时保存按钮执行的操作
function saveOnlineExamInfo() {
	$.ajax({
		url : "onlineExam_saveOnlineExamAnswer.action",
		data : $("#form_onlineExamPaper").serialize(),
		type : "post",
		dataType : "json",
		success : function(data) {
			alert(data.result)
		},
		error : function() {
			alert("请求失败！")
		}
	});
}

//点击确认交卷执行的方法
function checkOnlineExamInfoAnswer() {
	$("#onlineExamInfor_endtime").val(
			Format(getServerDate(), "yyyy-MM-dd HH:mm:ss"));
	$("#onlineExamInfor_examstatus").val("已交卷");
	//a标签采用post方式提交数据
	savaOnlineExamInfo(1);
	$
			.ajax({
				url : "onlineExam_checkOnlineExamInfoAnswer.action",
				data : $("#form_onlineExamPaper").serialize(),
				type : "post",
				dataType : "json",
				success : function(data) {
					alert(data.result);
					//location.href="onlineExam_getOnlineExamEmployeeGradeInfo.action?examId="+examInfo.examId+"&idCard="+employeeInIdCard;
					//savaOnlineExamInfo();
					var parameterStr = '{"idCard":"' + employeeInIdCard
							+ '","examId":"' + examInfo.examId + '"}';
					location.href = "javascript:doPost('onlineExam_getOnlineExamEmployeeGradeInfo.action','"
							+ parameterStr + "')";
				},
				error : function() {
					alert("请求失败!")
				}
			});
}

//保存在线考试员工基本信息
function savaOnlineExamInfo(a) {
	$("#onlineExamInfor_examid").val(examInfo.examId);
	$("#onlineExamInfor_employeeid").val(employeeInIdCard);
	$("#onlineExamInfor_logintime").val(loginTime);
	$("#onlineExamInfor_starttime").val(startAnswerTime);
	$.ajax({
		url : "onlineExam_saveOnlineExamEmployeeInfo.action",
		data : $("#form_OnlineEmployeeInfo").serialize(),
		type : "post",
		dataType : "json",
		success : function() {
			if (a != 1) {
				//调用查询考生答题时间的方法
				getOnlineExamStartAnswerTime();
				//倒计时方法
				timeOut = setInterval("timer()", 1000);
			}
		}
	})
}

//获取员工开始答题时间
function getOnlineExamStartAnswerTime() {
	$.ajax({
		url : "onlineExam_getOnlineExamStartAnswerTime.action",
		data : {
			"examId" : examInfo.examId,
			"idCard" : employeeInIdCard
		},
		type : "post",
		dataType : "json",
		success : function(data) {
			lengthAnswerTime = new Date(
					data.onlineExamStartAnswerInfo.startanswertime.replace(/T/g, " ").replace(/-/g, "/")).getTime()+ examInfo.AnswerTime * 60 * 1000;
		},
		error : function() {
			alert("请求失败！")
		}
	});
}

/*************************a标签post提交方法******************************/
//to:提交动作（action）,p:向后台传递的数据
function doPost(to, p) {
	//将json字符串转换为json对象
	p = eval("(" + p + ")");
	var myForm = document.createElement("form");
	myForm.method = "post";
	myForm.action = to;
	//遍历json对象
	for ( var i in p) {
		var myInput = document.createElement("input");
		myInput.setAttribute("name", i); // 为input对象设置name  
		myInput.setAttribute("value", p[i]); // 为input对象设置value  
		myForm.appendChild(myInput);
	}
	document.body.appendChild(myForm);
	myForm.submit();
	document.body.removeChild(myForm); // 提交后移除创建的form  	
}

/************获取服务器时间**********/
function getServerDate(){
	var nowTimeStr;
	$.ajax({
		url:basePathUrl+"/onlineExam_getNowServerTime.action?date="+Format(new Date(),"HH:mm:ss"),
		async:false,
		datatype:"json",
		success:function(data){			
			nowTimeStr = data.nowTime.replace(/T/g," ").replace(/-/g,"/");
		}
	})	
	return new Date(nowTimeStr);
}