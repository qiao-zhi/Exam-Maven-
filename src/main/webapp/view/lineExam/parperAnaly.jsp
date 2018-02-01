<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<%@ include file="/public/tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试卷分析</title>
	<link rel="stylesheet" href="<%=path %>/bs/css/bootstrap.css"/>
    <script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path %>/bs/js/bootstrap.js"></script>
	
    <link rel="stylesheet" href="<%=path %>/css/lineExam/parperAnaly.css" />
    <script src="<%=path %>/js/lineExam/parperAnaly.js"></script>

</head>
<body>
<!--顶部锚-->
<a name="goTop" href></a>
<!--回到顶部-->
<a href="#goTop">
    <div id="el_returnTop">
        <span>	&#9650; 回到顶部</span>
    </div>
</a>

<!--头-->
<jsp:include page="/view/lineExam/lineHeader.jsp"></jsp:include>


<!--考试标题-->
<h2 id="el_examTitle">${result.specificPaperInfo.title }</h2>
<!--主要内容-->
<div class="container">
    
	 <c:forEach items="${result.specificPaperInfo.bigQuestions}" var="bigQuestion" varStatus="bigQuestion_vs">
				<div class="row">
					<!--大题的内容-->
					<div class="elquesTitle">
						${bigQuestion.bigquestionname}
					</div>
					<c:forEach items="${bigQuestion.questions}" var="question" varStatus="question_vs">
						<div class="row el_questionMain">					
								<div class="el_questionTitle">
									<!-- 题目的序号和内容-->
									<span class="nmb">${question.questionsequence}</span>. <span class="btwenzi">${question.questioncontent}</span>?
								</div>							
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
								<!--答案，解析,分值-->
									<div class="el_answerAnaly">
										<ul>
											<li>
												<span>您的答案：</span>
												<c:if test="${question.employeeanswer !=question.answer}">
													<span style="color:red">${fn:replace((fn:replace((fn:replace((fn:replace((fn:replace(question.employeeanswer,"1","A")),"2","B")),"3","C")),"4","D")),"5","E")}</span>
												</c:if>										
												<c:if test="${question.employeeanswer == question.answer}">
													<span style="color:green">${fn:replace((fn:replace((fn:replace((fn:replace((fn:replace(question.employeeanswer,"1","A")),"2","B")),"3","C")),"4","D")),"5","E")}</span>
												</c:if>
											</li>
											<li>
												<span>正确答案：</span>
												<span>${fn:replace((fn:replace((fn:replace((fn:replace((fn:replace(question.answer,"1","A")),"2","B")),"3","C")),"4","D")),"5","E")}</span>
											</li>
											<li>
												<span>分值：</span>
												<span>${question.score}</span>
											</li>
											<%-- <li>
												<span>解析：</span>
												<span>${question.analysis}</span>
											</li> --%>
										</ul>
									</div>
						</div>
					</c:forEach>
		
				</div>
		</c:forEach>
	
</div>

<!--放脚-->
<jsp:include page="/view/lineExam/lineFooter.jsp"></jsp:include>
</body>
</html>
