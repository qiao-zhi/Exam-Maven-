<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<%@ include file="/public/tag.jsp"%>
<!-- 引入标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <meta charset="utf-8">
    <title>修改试题</title>
    <link rel="stylesheet" href="<%=path %>/bs/css/bootstrap.css"/>

    <!--试题包-->
    <link rel="stylesheet" type="text/css" href="<%=path %>/controls/exam/wenjuan_ht.css">
    <script src="<%=path %>/js/examParper/addExamparper/jquery17/jquery.min.js"></script>
    <script src="<%=path %>/js/questionLibrary/modifyQuestion/modifyquestion.js"></script>

    <!--日历-->
    <script type="text/javascript" src="<%=path %>/controls/calendar/jedate/jquery.jedate.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path %>/controls/calendar/jedate/skin/jedate.css">
    <script src="<%=path %>/controls/calendar/calendar.js"></script>

    <!--富文本框，编辑器-->
    <script charset="utf-8" src="<%=path %>/controls/kindEditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="<%=path %>/controls/kindEditor/zh-CN.js"></script>
    <link rel="stylesheet" href="../../css/questionLibrary/addQuestionPage.css">
    
     <!-- 日期格式转换 -->
    <script src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>
    <!--验证-->
	<script src="${pageContext.request.contextPath }/controls/validate/jquery.validate.js"></script>
	<script src="${pageContext.request.contextPath }/controls/validate/messages_zh.js"></script>
	
    <script>
    	//全局变量题库的id
    	var questionBankId_Old = "${questionOptionsInfo.questionbankid}";
    	//从session域中获取员工部门ID
		var departmentIdFromSess = '${session.userinfo.departmentid}';
		var knowledge_old = "${questionOptionsInfo.knowledgetype}";
		//定义一个全局变量
    	var basePathUrl = "${pageContext.request.contextPath}";
    	$(function(){
    		
    		 if("${questionOptionsInfo.type}" =='单选题'){
    			$("#danxuan_radio").attr("checked",true);
    		}else if("${questionOptionsInfo.type}" =='多选题'){
    			$("#duoxuan_radio").attr("checked",true);
    		}else{
    			$("#panduan_radio").attr("checked",true);
    			//设定高
            	$(".el_bigBlock2").css("height", "130px");
    		}  
    		 var sd = "${questionOptionsInfo.uploadtime }";
    		//上传时间的格式处理以及信息的回显
    		//var uploadTime =  Format(new Date(sd.replace(/T/g," ").replace(/-/g,"/")),'yyyy-MM-dd HH:mm:ss');
    		var uploadTime =  Format(new Date(sd),'yyyy-MM-dd HH:mm:ss');
    		//$("#test4").val(uploadTime); 		
    	

    		//试题框的高
    		var height1 = $("#editor_id").css("height");
    		height1 = parseInt(height1.substring(0,height1.length-2)) + 7 + "px";
    		$("#editor_id").parents(".el_bigBlock").css("height",height1);
    		
    		/* //解析框的高
    		var height2 = $("#editor_id2").css("height");
    		height2 = parseInt(height2.substring(0,height2.length-2)) + 7 + "px";
    		$("#editor_id2").parents(".el_bigBlock").css("height",height2); */
    	})
    </script>
    
</head>

<body>
<!--头-->
<jsp:include page="/view/public/header.jsp"></jsp:include>

<!--中部-->
<div class="html_middle">

  
    <!--放主界面内容-->
    <div class="el_right">

<div class="container">
    <h4 class="modal-title" id="myMo34dalLabel">修改试题</h4>
    <form id="form_modifyQuestion">

        <div class="modal-body">

            <div id="el_upInfoPanel">
                <div class="el_title"><h5>基本信息</h5></div>
                <div class="input-group el_modellist" role="toolbar">
                    <span class="el_spans">选择题库：</span>
                    <select id="modify_QuestionBankNameList" class="selectpicker form-control" title="请选择" name="question.questionbankid" disabled>                    
                    </select>
                </div>

                <div class="input-group el_modellist" role="toolbar">
                    <span class="el_spans">试题类型：</span>                  
                        <label><input id="danxuan_radio" type="radio" class="el_addquestions" name="question.type" value="单选题"> 单选题&nbsp;&nbsp;&nbsp;</label>
	                    <label><input id="duoxuan_radio" type="radio" class="el_addquestions" name="question.type" value="多选题"> 多选题&nbsp;&nbsp;&nbsp;</label>
	                    <label><input id="panduan_radio" type="radio" class="el_addquestions" name="question.type" value="判断题"> 判断题</label>
                </div>

                <!-- <div class="input-group el_modellist" role="toolbar">
                    <span class="el_spans">知&nbsp;&nbsp;识&nbsp; 点：</span>
                    <select class="combox form-control" id="modify_knowledgetypeList" name="question.knowledgetype">
                    </select>
                </div> -->

                <div class="input-group el_modellist" role="toolbar">
                    <span class="el_spans">上&nbsp;&nbsp;传&nbsp;人：</span>
                    <input type="text" class="form-control el_modelinput" name="question.emplloyeename" value="${questionOptionsInfo.emplloyeename }" disabled/>
					<!-- 隐藏试题的ID -->
					<input type="hidden" name="question.questionid" value="${questionOptionsInfo.questionid}"/>
                	
                </div>
                <div class="input-group el_modellist" role="toolbar">
                    <span class="el_spans">上传时间：</span>    
                    <input type="text" id="test4" class="wicon form-control" readonly name="question.uploadtime" value="<fmt:formatDate value="${questionOptionsInfo.uploadtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled/>
                </div>
            </div>

            <!--添加试题区域-->
            <div class="el_addQuestionPanel">

                <div class="el_bigBlock">
                    <div class="el_title"><h5>试题题干</h5></div>
                    <div class="el_questionDepart">
                        <textarea id="editor_id" style="width: 100%;height:140px;" class="el_editorBox" name="question.questionwithtag"></textarea>
                    </div>
                </div>

                <div class="el_bigBlock2">
                    <div class="el_title"><h5>选项设置</h5></div>

                    <div class="el_questionDepart el_questionDepart2">
                        <div class="el_questionContent" id="el_choose">
                            <!--单选-->
                            <div class="xxk_xzqh_box danxuan">
								<c:if test="${questionOptionsInfo.type =='单选题'}">
									<c:forEach items="${questionOptionsInfo.options }" var="option" varStatus="vs">
		                                <div class="title_itram">
		                                    <div class="kzjxx_iteam">
		                                    	<c:if test="${questionOptionsInfo.answer == option.optionsequence}">		                            
                                            		<span class="el_chooseButton"><label><input name="question_answer" type="radio"  checked/>设置为答案</label></span>
                                            	</c:if>
                                            	<c:if test="${questionOptionsInfo.answer != option.optionsequence}">
                                              		 <span class="el_chooseButton"><label><input name="question_answer" type="radio" />设置为答案</label></span>
                                                </c:if>
		                                       
		                                        <input  type="text" class="input_wenbk" value="${option.optioncontent }" placeholder="选项">
		                                    	
		                                    </div>
		                                </div>
	                                </c:forEach>
                              
                                	<a href="javascript:void(0)" class="zjxx">增加选项</a> 
                                 </c:if>
                            </div>

                            <!--多选-->
                            <div class="xxk_xzqh_box duoxuan">
								<c:if test="${questionOptionsInfo.type =='多选题'}">
									<c:forEach items="${questionOptionsInfo.options }" var="option" varStatus="vs">
		                                 <div class="title_itram">
		                                    <div class="kzjxx_iteam">
		                                    	<c:if test="${fn:indexOf(questionOptionsInfo.answer, option.optionsequence)>=0}">
                                            		 <span class="el_chooseButton"><label><input name="question_answer" type="checkbox" checked/>设置为答案</label></span>
                                            	</c:if>
                                            	<c:if test="${fn:indexOf(questionOptionsInfo.answer, option.optionsequence)<0}">
                                              		 <span class="el_chooseButton"><label><input name="question_answer" type="checkbox" />设置为答案</label></span>
                                                </c:if>		                                       		                                       
		                                        <input  type="text" class="input_wenbk" value="${option.optioncontent }" placeholder="选项">
		                                    	
		                                    </div>
		                                 </div>
                                      </c:forEach>
                              
                               		<a href="javascript:void(0)" class="zjxx">增加选项</a> 
                                </c:if>
                            </div>

                            <!-- 判断-->
                            <div class="panduan">
                            	<c:if test="${questionOptionsInfo.type =='判断题'}">
	                                 <div class="title_itram">
	                                    <div class="kzjxx_iteam">                                    	
		                                        <ul id="el_panduanChoose">
		                                            <li>
		                                            	<c:if test="${questionOptionsInfo.answer == '正确'}">
		                                            	<span><label><input name="question.answer" type="radio" value="正确" checked/> 设置为答案</label></span>
		                                            	</c:if>
		                                            	<c:if test="${questionOptionsInfo.answer == '错误'}">
		                                              	 <span><label><input name="question.answer" type="radio" value="正确"/> 设置为答案</label></span>
		                                                </c:if>
		                                                 <input name="options[0].optioncontent" type="hidden" value="正确"/>
	                									 <input name="options[0].optionsequence" type="hidden" value="1"/>
		                                                <span>&nbsp;&nbsp;正确</span>
		                                            </li>
		                                            <li>
		                                            	<c:if test="${questionOptionsInfo.answer == '错误'}">
		                                            		<span><label><input name="question.answer" type="radio" value="错误" checked/> 设置为答案</label></span>
		                                            	</c:if>
		                                            	<c:if test="${questionOptionsInfo.answer == '正确'}">
		                                              	 	<span><label><input name="question.answer" type="radio" value="错误"/> 设置为答案</label></span>
		                                                </c:if>	                                                
		                                                <input name="options[1].optioncontent" type="hidden" value="错误"/>
	                									<input name="options[1].optionsequence" type="hidden" value="2"/>
		                                                <span>&nbsp;&nbsp;错误</span>
		                                            </li>
		                                        </ul>                                       
	                                    </div>
	                                </div> 
                              </c:if>
                            </div>
                        </div>
                    </div>
                </div>

               <!--  <div class="el_bigBlock el_bigBlock3">
                    <div class="el_title"><h5>试题解析</h5></div>
                    <div class="el_questionDepart">
                        <textarea id="editor_id2" style="width: 100%;height:140px;" class="el_editorBox" name="question.analysiswithtag"></textarea>
                    </div>
                </div> -->
            </div>

        </div>

        <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="modify_question()">保存</button>
            <input type="button" class="btn btn-default" value="关闭" onclick="javascript:history.back(-1);"/>           
        </div>
        <br/>
        <br/>
        <br/>
        <div id="updateOptions_content">
        </div>
    </form>
</div>

</div>
</div>
<!--放脚-->
<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>

<script>
	KindEditor.ready(function (K) {
	    var editor = K.create('#editor_id', {
	        resizeType: 0,
	        items: [
	            'table', '|', 'fontsize', 'bold', 'italic', 'underline',
	            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', '|', 'image'],
	        afterBlur:function(){
	        	//将编辑器的内容同步到textarea中
	        	this.sync();
	        }  
	    });
	
	    // 设置HTML内容
	    editor.html("${questionOptionsInfo.questionwithtag}");
			
	    var editor2 = K.create('#editor_id2', {
	        resizeType: 0,
	        items: [
	            'table', '|', 'fontsize', 'bold', 'italic', 'underline',
	            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', '|', 'image'],
	         afterBlur:function(){
	         	//将编辑器的内容同步到textarea中
	         	this.sync();
	         }  
	    });
	    
	 	/* // 设置HTML内容
	    editor2.html("${questionOptionsInfo.analysiswithtag}"); */
	});

</script>

</html>
