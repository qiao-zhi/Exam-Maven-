<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
	String userIP = request.getRemoteAddr();
%>
<%!public String getIpAddr(HttpServletRequest request) {    
    String ip = request.getHeader("x-forwarded-for");       
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {       
        ip = request.getHeader("Proxy-Client-IP");       
    }       
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {       
        ip = request.getHeader("WL-Proxy-Client-IP");       
    }       
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {       
        ip = request.getRemoteAddr();       
    }       
    return ip;       
}%>  
<%@ include file="/public/tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html   oncontextmenu=self.event.returnValue=false onselectstart="return false">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开始考试</title>

    <link rel="stylesheet" href="<%=path %>/bs/css/bootstrap.css"/>
    <script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path %>/bs/js/bootstrap.js"></script>

    <link rel="stylesheet" href="<%=path %>/css/lineExam/ExamParper.css">
    <script>
		//定义一个全局变量
		var basePathUrl = "${pageContext.request.contextPath}";
	</script>
     <!-- 日期格式转换 -->
    <script src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>
    <script src="<%=path %>/js/lineExam/ExamParper.js"></script>
    
    <script>    	
    	//考试的基本信息
    	var examInfo = ${result.examInfo};
    	//从域对象中获取身份证号，员工登录时间
    	var employeeInIdCard = '${session.userinfo.useridcard}';
    	var loginTime = "${session.userinfo.logintime}";
    	console.log("测试",loginTime)
    	loginTime = Format(new Date(loginTime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm:ss")
    	//开始答题时间获取系统当前时间
    	/* //规定答题时间
    	var lengthAnswerTime = new Date(startAnswerTime).getTime()+120*60*1000; */
	</script>
    
</head>

<body id="body" oncontextmenu="self.event.returnValue=false" onpaste="return false" oncopy="return false">

<div id="bodySon">
    <!--回到顶部-->
    <a href="#body">
        <div id="el_returnTop">
            <span>	&#9650; 回到顶部</span>
        </div>
    </a>
    <!--右侧提示栏-->
    <div id="el_tip">
        <div>
            <span class="el_tipTime">考试时间：<span id="exam_lengthTime"> </span><span>分钟</span></span>
           <!--  <span class="el_tipTime">离考试结束时间还有：<br/><span> </span><span>分钟</span></span> -->
        </div>
       
    	<c:forEach items="${result.specificPaperInfo.bigQuestions}" var="bigQuestion">
	        <div class="el_tipQuestion">
	            <span class="el_tipTitle">${bigQuestion.questions[0].type}</span>
					<c:if test="${bigQuestion.questions[0].type == '单选题'}">
						<ul class="el_tipUL el_danxuan">
							<c:forEach items="${bigQuestion.questions}" var="question" varStatus="question_vs">
								<c:if test="${!empty question.employeeanswer}">
									<li class="el_tipLi1" style="background-color:orangered;">${question.questionsequence}</li>
								</c:if>
								<c:if test="${empty question.employeeanswer}">
									<li class="el_tipLi1">${question_vs.count}</li>
								</c:if>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${bigQuestion.questions[0].type == '多选题'}">
						<ul class="el_tipUL el_duoxuan">
							<c:forEach items="${bigQuestion.questions}" var="question" varStatus="question_vs">
								<c:if test="${!empty question.employeeanswer}">
									<li class="el_tipLi1" style="background-color:orangered;">${question.questionsequence}</li>
								</c:if>
								<c:if test="${empty question.employeeanswer}">
									<li class="el_tipLi1">${question.questionsequence}</li>
								</c:if>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${bigQuestion.questions[0].type == '判断题'}">
						<ul class="el_tipUL el_panduan">
							<c:forEach items="${bigQuestion.questions}" var="question" varStatus="question_vs">
								<c:if test="${!empty question.employeeanswer}">
									<li class="el_tipLi1" style="background-color:orangered;">${question.questionsequence}</li>
								</c:if>
								<c:if test="${empty question.employeeanswer}">
									<li class="el_tipLi1">${question.questionsequence}</li>
								</c:if>
							</c:forEach>
						</ul>
					</c:if>
	        </div>
		</c:forEach>
    </div>

    <!--头-->
    <div id="el_header">

            <div class="el_logoName">
                <img src="../../image/logo.png" width="40"/>
                <a href="../../index.jsp">
                    阳城国际发电有限公司
                    安全培训管理系统
                </a>
                <div class="el_userName">
                    <span>考生姓名：</span>
                    <div id="examName">
                    	${session.userinfo.username }
                    </div>
                </div>
                <div class="el_downTime">
                    <span>剩余时间：</span>
                    <div id="timer" style="color: yellow;">
                    </div>
                </div>
            </div>
        <div id="el_headerRightinfo">

            <div class="el_statistic">
				 
            </div>

            <div id="el_opration">
                <!--保存交卷按钮-->                
                <button class="btn btn-info btn-sm" type="button" id="tempSave" onclick="saveOnlineExamInfo()">临时保存</button>
                <button id="el_examSubmit" type="button" class="btn btn-success btn-sm">交卷</button>
            </div>
        </div>
    </div>
	
    <!--主要内容-->
    <div class="container">
    	<form id="form_onlineExamPaper">
	        <!--考试标题-->
	        <h2 id="el_examTitle"></h2>
			
	        <!-- 隐藏考试编号 -->
	        <input type="hidden" name="examId" id="onlineExam_exmaId"/>
			 <c:forEach items="${result.specificPaperInfo.bigQuestions}" var="bigQuestion" varStatus="bigQuestion_vs">
			        <div class="row">
			            <!--大题的内容-->
			            <div class="elquesTitle">
			                ${bigQuestion.bigquestionname}
			            </div>
						<c:forEach items="${bigQuestion.questions}" var="question" varStatus="question_vs">
							<div class="row el_questionMain">
								<!-- 隐藏信息，试题ID，试卷ID，题目类型，正确答案，题目分值，以及从域对象中获取身份证号 -->
								<input type="hidden" value="${question.questionid }" name="onlineExamAnswerInfos[${bigQuestion_vs.count*100 + question_vs.count}].questionid"/>
								<input type="hidden" value="${question.paperid }" name="onlineExamAnswerInfos[${bigQuestion_vs.count*100 + question_vs.count}].paperid"/>
								<input type="hidden" value="${result.specificPaperInfo.examid }" name="onlineExamAnswerInfos[${bigQuestion_vs.count*100 + question_vs.count}].onlineanswerexamid"/>
								<input type="hidden" value="${question.type }" name="onlineExamAnswerInfos[${bigQuestion_vs.count*100 + question_vs.count}].questiontype"/>
								<input type="hidden" value="${question.answer }" name="onlineExamAnswerInfos[${bigQuestion_vs.count*100 + question_vs.count}].answer"/>
								<input type="hidden" value="${question.score }" name="onlineExamAnswerInfos[${bigQuestion_vs.count*100 + question_vs.count}].questionscore"/>
								<input type="hidden" value='${session.userinfo.useridcard}' name="onlineExamAnswerInfos[${bigQuestion_vs.count*100 + question_vs.count}].employeeid"/>
								<c:if test="${question.type =='单选题' }">
									<div class="el_questionTitle danxuanti">
										<!-- 题目的序号和内容-->
										<input type="hidden" class="nmb" value="${question_vs.count}"/>
										<span>${question.questionsequence}</span>. <span class="btwenzi">${question.questioncontent}</span>?
									</div>
								</c:if>
								<c:if test="${question.type =='多选题' }">
									<div class="el_questionTitle duoxuanti">
										<!-- 题目的序号和内容-->
										<input type="hidden" class="nmb" value="${question_vs.count}"/>
										<span>${question.questionsequence}</span>. <span class="btwenzi">${question.questioncontent}</span>?
									</div>
								</c:if>
								<c:if test="${question.type =='判断题' }">
									 <div class="el_questionTitle panduanti">
									 	<!-- 题目的序号和内容-->
									 	<input type="hidden" class="nmb" value="${question_vs.count}"/>
										<span>${question.questionsequence}</span>. <span class="btwenzi">${question.questioncontent}</span>?
									</div>
								</c:if>							
								
									
									<ul class="el_qusSelect">
										<c:forEach items="${question.options}" var="option" varStatus="option_vs">
											<!-- 选项内容 -->
											<li>
												<label>
													<c:if test="${question.type =='单选题' }">
														<c:if test="${question.employeeanswer == option_vs.count}">
															<input name="onlineExamAnswerInfos[${bigQuestion_vs.count*100 + question_vs.count}].selectoption" type="radio" checked value="${option_vs.count}">
														</c:if>	
														<c:if test="${question.employeeanswer != option_vs.count}">
															<input name="onlineExamAnswerInfos[${bigQuestion_vs.count*100 + question_vs.count}].selectoption" type="radio" value="${option_vs.count}">
														</c:if>	
													</c:if>
													<c:if test="${question.type =='多选题' }">
														<c:if test="${fn:indexOf(question.employeeanswer, option_vs.count)>=0}">
															<input name="onlineExamAnswerInfos[${bigQuestion_vs.count*100 + question_vs.count}].selectoption" type="checkbox" checked value="${option_vs.count}">
														</c:if>
														<c:if test="${fn:indexOf(question.employeeanswer, option_vs.count)<0}">
															<input name="onlineExamAnswerInfos[${bigQuestion_vs.count*100 + question_vs.count}].selectoption" type="checkbox" value="${option_vs.count}">
														</c:if>
													</c:if>
													<c:if test="${question.type =='判断题' }">	
														<c:if test="${question.employeeanswer == option.optioncontent}">
															<input name="onlineExamAnswerInfos[${bigQuestion_vs.count*100 + question_vs.count}].selectoption" type="radio" checked value="${option.optioncontent}">
														</c:if>
														<c:if test="${question.employeeanswer != option.optioncontent}">
															<input name="onlineExamAnswerInfos[${bigQuestion_vs.count*100 + question_vs.count}].selectoption" type="radio" value="${option.optioncontent}">
														</c:if>
													</c:if>
													<span>${option.optionsequence}</span>
													<span>${option.optioncontent}</span></label>
											</li>
										</c:forEach>	
									</ul>
			
							</div>
						</c:forEach>
			
			        </div>
			</c:forEach>
			
	        <br/>
	        <br/>
	        <br/>
	        <br/>
       	</form>
       	<!-- 在线考试信息表中的数据 -->
       	<form id="form_OnlineEmployeeInfo">
       		<input type="hidden" name="onlineExamInfor.examid" id="onlineExamInfor_examid"/>
       		<input type="hidden" name="onlineExamInfor.employeeid" id="onlineExamInfor_employeeid"/>
       		<%-- <input type="hidden" name="onlineExamInfor.logintime"  id="onlineExamInfor_logintime"/> --%>
       		<input type="hidden" name="onlineExamInfor.logintime"  value="<fmt:formatDate value="${session.userinfo.logintime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
       		<input type="hidden" name="onlineExamInfor.starttime" id="onlineExamInfor_starttime"/>
       		<input type="hidden" name="onlineExamInfor.endtime" id="onlineExamInfor_endtime"/>
       		<input type="hidden" name="onlineExamInfor.ipaddress" value="<%=getIpAddr(request)%>"/>
       		<input type="hidden" name="onlineExamInfor.examstatus" value="正在答题" id="onlineExamInfor_examstatus"/>
       	</form>
    </div>

</div>

</body>
</html>
