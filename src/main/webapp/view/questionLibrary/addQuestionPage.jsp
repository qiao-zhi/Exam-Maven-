<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<%@ include file="/public/tag.jsp"%>
<html>

<head>
    <meta charset="utf-8">
    <title>添加试题</title>
     <link rel="stylesheet" href="<%=path %>/bs/css/bootstrap.css"/>

    <!--试题包-->
    <link rel="stylesheet" type="text/css" href="<%=path %>/controls/exam/wenjuan_ht.css">
    <script src="<%=path %>/js/examParper/addExamparper/jquery17/jquery.min.js"></script>
    <script src="<%=path %>/js/questionLibrary/addQuestion/question.js"></script>

    <!--日历-->
    <script type="text/javascript" src="<%=path %>/controls/calendar/jedate/jquery.jedate.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path %>/controls/calendar/jedate/skin/jedate.css">
    <script src="<%=path %>/controls/calendar/calendar.js"></script>

    <!--富文本框，编辑器-->
    <script charset="utf-8" src="<%=path %>/controls/kindEditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="<%=path %>/controls/kindEditor/zh-CN.js"></script>
    <script src="<%=path %>/js/questionLibrary/addQuestion/addQuestionPage.js"></script><!--富文本框，和基本内容-->
	
	<!--验证-->
	<script src="${pageContext.request.contextPath }/controls/validate/jquery.validate.js"></script>
	<script src="${pageContext.request.contextPath }/controls/validate/messages_zh.js"></script>
	
    <link rel="stylesheet" href="../../css/questionLibrary/addQuestionPage.css">
    <script>
		//从session域中获取员工部门ID
		var departmentIdFromSess = '${session.userinfo.departmentid}';
		//定义一个全局变量
		var basePathUrl = "${pageContext.request.contextPath}";
	</script>
	
	<script src="${pageContext.request.contextPath }/controls/calendar/noCal.js"></script>
</head>

<body>
<!--头-->
<jsp:include page="/view/public/header.jsp"></jsp:include>

<!--中部-->
<div class="html_middle">

    <!--放主界面内容-->
    <div class="el_right">

<div class="container">
    <h4 class="modal-title" id="myMo34dalLabel">添加试题</h4>
    <form id="form_addQuestion">

        <div class="modal-body">

            <div id="el_upInfoPanel">
                <div class="el_title"><h5>基本信息</h5></div>
                <div class="input-group el_modellist" role="toolbar">
                    <span class="el_spans">选择题库：</span>
                    <select id="add_QuestionBankNameList" class="selectpicker form-control" title="请选择" name="question.questionbankid">                  
                    </select>
                </div>

                <div class="input-group el_modellist" role="toolbar">
                    <span class="el_spans">试题类型：</span>
                    <label><input type="radio" class="el_addquestions" name="question.type" value="单选题" checked> 单选题&nbsp;&nbsp;&nbsp;</label>
                    <label><input type="radio" class="el_addquestions" name="question.type" value="多选题"> 多选题&nbsp;&nbsp;&nbsp;</label>
                    <label><input type="radio" class="el_addquestions" name="question.type" value="判断题"> 判断题</label>
                </div>

               <!--  <div class="input-group el_modellist" role="toolbar">
                    <span class="el_spans">知&nbsp;&nbsp;识&nbsp; 点：</span>
                    <select class="combox form-control" id="sel_productTag" name="question.knowledgetype">
                    </select>
                </div> -->


                <!--<div class="input-group el_modellist">
                    <span class="el_spans">试题状态：</span>
                        <label><input type="radio" name="el_state" value="0"> 禁用</label>
                        <label><input type="radio" name="el_state" checked value="1"> 启用</label>

                </div>-->

                <div class="input-group el_modellist" role="toolbar">
                    <span class="el_spans">上&nbsp;&nbsp;传&nbsp; 人：</span>
                    <input type="text" class="form-control el_modelinput" disabled value="${session.userinfo.username}" />
                    <!-- 隐藏员工姓名 -->
                    <input type="hidden" name="question.emplloyeename" value="${session.userinfo.username}"/>
                    <!-- 隐藏员工ID -->
                    <input type="hidden" name="question.employeeid" value="${session.userinfo.employeeid}"/>
                    <!-- 隐藏部门ID -->
                    <input type="hidden" name="departmentId" value="${session.userinfo.departmentid}"/>
                </div>
                <div class="input-group el_modellist" role="toolbar">
                    <span class="el_spans">上传时间：</span>
                    <input type="text" id="test4" class="wicon form-control el_noVlaue"  readonly name="question.uploadtime"/>
                </div>
            </div>

            <!--添加试题区域-->
            <div class="el_addQuestionPanel">

                <div class="el_bigBlock">
                    <div class="el_title"><h5>试题题干</h5></div>
                    <div class="el_questionDepart">
                        <textarea id="editor_id" style="width: 100%;height:140px; min-heighh:100px;" class="el_editorBox" name="question.questionwithtag"></textarea>
                    </div>
                </div>

                <div class="el_bigBlock2">
                    <div class="el_title"><h5>选项设置</h5></div>

                    <div class="el_questionDepart el_questionDepart2">
                        <div class="el_questionContent" id="el_choose">
                        	<span id="options_WarnInfo"></span>
                            <!--单选-->
                            <div class="xxk_xzqh_box danxuan">
                            </div>

                            <!--多选-->
                           <div class="xxk_xzqh_box duoxuan">             						 
                            </div>

                            <!-- 判断-->
                            <div class="panduan">                                
                            </div>
                        </div>
                    </div>
                </div>

               <!-- <div class="el_bigBlock el_bigBlock3">
                    <div class="el_title"><h5>试题解析</h5></div>
                    <div class="el_questionDepart">
                        <textarea id="editor_id2" style="width: 100%;height:140px;" class="el_editorBox" name="question.analysiswithtag"></textarea>
                    </div>
                </div>  -->
            </div>

        </div>

        <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="add_question()">保存</button>
            <input type="button" class="btn btn-default" value="关闭" onclick="javascript:history.back(-1);"/>
          
        </div>
        <br/>
        <br/>
        <br/>
        <div id="options_content">
        </div>
    </form>
</div>


</div>
</div>
<!--放脚-->
<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>

</html>
