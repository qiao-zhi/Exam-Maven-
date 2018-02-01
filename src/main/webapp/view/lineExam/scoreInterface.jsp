<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<%@ include file="/public/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成绩分析</title>
<link rel="stylesheet" href="<%=path %>/bs/css/bootstrap.css" />
<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/bs/js/bootstrap.js"></script>

<link rel="stylesheet" href="<%=path %>/css/lineExam/scoreInterface.css" />
<script>
    	$(function(){
    		
    		 //防止页面后退  
            history.pushState(null, null, document.URL);  
            window.addEventListener('popstate', function () {  
                    history.pushState(null, null, document.URL);  
            });  
    		
    		//交卷后进入成绩分析页面将父页面也就是考试答题页面关闭，禁止回退
    		//这一句非要,不然会弹出关闭提示.
    		/* window.opener.opener=null; 
    		window.opener.close();  */
    		
    		var lengthOfExam = parseInt((new Date("${result.onlineExamEmployeeGrade.endtime}") - new Date("${result.onlineExamEmployeeGrade.starttime}"))/1000/60);
    		$("#onlineExamTimeLength").text(lengthOfExam+"分钟");
    		
    		var parameterStr =  '{"idCard":"${result.onlineExamEmployeeGrade.employeeid}","examId":"${result.onlineExamEmployeeGrade.examid}","paperId":"${result.onlineExamEmployeeGrade.paperid}"}';
    		$("#paperAnalyCheck").prop("href","javascript:doPost('onlineExam_echoOnlineExamPaperAndAnswerInfo.action','"+parameterStr+"')");    		
    		
    		//判断考试时间是否结束，若尚未结束不提供查看解析的链接
    	/* 	 var analysis = new Date("${result.onlineExamEmployeeGrade.endtime}")-new Date();
    		if(analysis<0){
    			$("#el_parperAnaly").show();
    		}  */
    	})
    	
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
		</script>
</head>
<body>
	<!--头-->
	<jsp:include page="/view/lineExam/lineHeader.jsp"></jsp:include>

	<div class="container">
		<span>本次考试的成绩</span>
		<table>
			<tr>
				<td>考试名称：</td>
				<td>${result.onlineExamEmployeeGrade.examname}</td>
			</tr>
			<tr>
				<td>考试时长：</td>
				<td>${result.onlineExamEmployeeGrade.answertime}分钟</td>
			</tr>
			<tr>
				<td>试卷总分：</td>
				<td>${result.onlineExamEmployeeGrade.paperscore}</td>
			</tr>
			<tr>
				<td>考生姓名：</td>
				<td>${result.onlineExamEmployeeGrade.employeename}</td>
			</tr>
			<tr>
				<td>您获得的分数：</td>
				<td id="el_getScore">${result.onlineExamEmployeeGrade.grade}</td>
			</tr>
		</table>
	</div>

	<%-- <div class="btn btn-success" id="el_parperAnaly"><a id="paperAnalyCheck" href="onlineExam_echoOnlineExamPaperAndAnswerInfo.action?paperId=${result.onlineExamEmployeeGrade.paperid}&idCard=${result.onlineExamEmployeeGrade.employeeid}&examId=${result.onlineExamEmployeeGrade.examid}">查看试卷解析</a></div> --%>
	<div class="btn btn-success" id="el_parperAnaly" style="display: none">
		<a id="paperAnalyCheck">查看试卷解析</a>
	</div>

	<!--放脚-->
	<jsp:include page="/view/lineExam/lineFooter.jsp"></jsp:include>
</body>
</html>
