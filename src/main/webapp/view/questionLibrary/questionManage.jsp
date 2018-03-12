<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<%@ include file="/public/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>试题管理</title>
<%@ include file="/public/cssJs.jsp"%>
   
    <!--分页-->   
   <%--  <script src="<%=path %>/js/public/page.js"></script> --%>

    <!--批量中的题库,索引中的题库-->
    <script src="<%=path %>/js/public/treeQ.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/public/tree.css">

    <script src="<%=path %>/js/questionLibrary/questionManage.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/questionLibrary/questionManage.css">
     <!-- 日期格式转换 -->
    <script src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>
	<script>
		//从session域中获取员工部门ID
		var departmentIdFromSess = '${session.userinfo.departmentid}';
		//定义一个全局变量
		var basePathUrl = "${pageContext.request.contextPath}";
		/* 题库管理权限 */
		var hasQuestionbankManager = false;
	</script>
</head>
<body>
<!--  如果有题库管理权限啊修改全局变量的值-->
<shiro:hasPermission name="questionbank:manager">
	<script>
		hasQuestionbankManager = true;
	</script>
</shiro:hasPermission>
<!--头-->
<jsp:include page="/view/public/header.jsp"></jsp:include>


<!--中部-->
<div class="html_middle">

    <!--放菜单框-->
    <div class="el_left">
		<jsp:include page="/view/public/menu.jsp"></jsp:include>
    </div>

    <!--放主界面内容-->
    <div class="el_right">

<div class="container-fluid">
    <div class="panel panel-default">
        <!--菜单连接标题-->
        <div class="panel-heading"><span>题库管理</span><span>>试题管理</span></div>

        <!--内容-->
        <div class="col-md-12">

            <!--索引-->
            <div class="row el_queryBox">
                <form id="form_findQuestionsInfo">
                    <div class="row el_queryBoxrow">
                    
                        <div class="col-md-3 el_qlmQuery">
                            <div class="input-group" role="toolbar">
                                <span class="el_spans">试题题目：</span>
                                <input type="text" class="form-control" name="keyWord"/>
                            </div>
                        </div>

                        <div class="col-md-3 el_qlmQuery">
                            <div class="input-group" role="toolbar">
                                <span class="el_spans el_chooseSpan">选择题库：</span>
                                <select class="combox form-control" id="query_QuestionBankNameList" name="questionBankId">
                                </select>
                               <!--  <ul id="log" class="el_modelinput el_chooseInput log"></ul>
                                <img src="../../controls/selectDropTree/smallTriangle.png" class="el_smallTriangle"
                                     width="7"/>
                                <ul id="treeDemo2" class="ztree"></ul> -->
                            </div>
                        </div>

                        <div class="col-md-3 el_qlmQuery">
                            <div class="input-group" role="toolbar">
                                <span class="el_spans">试题类型：</span>
                                <select class="selectpicker form-control" name="type">
                                    <option value="">--请选择--</option>
                                    <option value="单选题">单选题</option>
                                    <option value="多选题">多选题</option>
                                    <option value="判断题">判断题</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- <div class="row el_queryBoxrow">
                        <div class="col-md-3 el_qlmQuery">
                            <div class="input-group" role="toolbar">
                                <span class="el_spans">知识点：</span>
                                <select class="combox form-control" name="knowledge" id="query_knowledgeList">
                                	
                                </select>
                            </div>
                        </div>                       
                    </div> -->
					 <!-- 隐藏当前页和显示条数 -->
                        <input type="hidden" name="currentPage" id="currentPage" />
						<input type="hidden" name="currentCount" id="currentCount" />
						<!-- 隐藏域中隐藏从题库管理页面传递过来的题库ID -->
						<input type="hidden" name="hiddenQuestionBankId" id="hiddenQuestionBankId" />
                    <!--提交查询按钮-->
                    <button type="button" class="btn btn-primary el_queryButton btn-sm" onclick="searchQuestionsInfo()">查询</button>
                	<button onclick="initclear()" type="reset" class="btn btn-default el_queryButton0 btn-sm" >清空</button>
                </form>

            </div>   <!--结束 查询表单提交-->

            <!--显示内容-->
            <h3 class="el_mainHead">试题信息</h3>
            <div class="panel panel-default el_Mainmain">

                <!--按钮面板-->
                <div class="panel-body">

                    <div class="panel panel-default">
                        <div class="panel-body el_MainxiaoMain">

                            <div class="el_topButton">
                                <div class="col-md-2">
                                <shiro:hasPermission name="questionbank:manager">
                                    <a href="<%=path %>/view/questionLibrary/addQuestionPage.jsp">
                                        <button class="btn btn-primary">添加试题</button>
                                    </a>
                                </shiro:hasPermission>
                                </div>
                            </div>

                        </div>
                    </div>

                    <!--表格 内容都提取到json里边-->
                    <table class="table table-hover  table-bordered">
                        <thead>
                        <tr>
                            <th><input type="checkbox" id="el_checkQuanxuan"/></th>
                            <th>序号</th>
                            <th width="30%">试题题目</th>
                            <th>所属题库</th>
                            <th>试题类型</th>                          
                            <th>上传人</th>
                            <th>上传时间</th>
                            <th width="170">操作</th>
                        </tr>
                        </thead>
                        <tbody id="questionsListInfo">             
                        </tbody>
                    </table>

                    <!--
                    批量操作
                    -->
                    <div id="el_allOptionPanel">
                        <div>
                            <select id="el_allOption" class="form-control">
                                <option value="" id="el_default">=批量操作=</option>
                                <option value="batch_delete">批量删除</option>
                                <!-- <option value="batch_status">批量设置状态</option> -->
                                <option value="batch_move">批量移动</option>
                            </select>
                        </div>
                    </div>

                    <!--题库的批量移动-->
                    <div class="col-md-3" id="el_setAllMove">
                        <div class="input-group" role="toolbar">
                            <span class="el_spans el_chooseSpan">选择题库：</span>
                           <select class="combox form-control" id="move_QuestionBankNameList" style="width:200px;"> </select>
                            <input type="button" id="el_setAllMoveSubmit" class="btn btn-default btn-sm" value="移动" onclick="moveQuestions_batch()" />
                        </div>
                    </div>

                  
                    <!--设置审核状态-->
                    <div id="setCheckState">
                            <span class="el_CheckChooseTitle">请选择操作：</span>
                            <label><input type="radio" name="el_checkStateChoose"> 启用</label>
                            <label><input type="radio" name="el_checkStateChoose"> 禁用</label>
                            <input type="button" value="提交" class="btn btn-default btn-sm"/>
                    </div>

                    <!-- 模态框   批量信息删除确认 -->
                    <div class="modal fade" id="delcfmModel_batch">
                        <div class="modal-dialog" data-backdrop="static" data-keyboard="false">
                            <div class="modal-content message_align">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">×</span></button>
                                    <h4 class="modal-title">提示</h4>
                                </div>
                                <div class="modal-body">
                                    <p>您确认要删除选中的试题吗？</p>
                                </div>
                                <div class="modal-footer">
                                    <input type="hidden" id="url2"/>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    <a onclick="deleteQuestions_batch()" class="btn btn-success" data-dismiss="modal">确定</a>
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->
                   

                </div>                
                 <!--分页-->
                 <div id="questions_paginationIDU" class="paginationID"></div>
            </div>

            <!-- 模态框 查看详细信息 预览-->
            <div class="modal fade" id="el_questionInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
                 aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;</button><!--关闭符号-->
                            <!--标题-->
                            <h4 class="modal-title" id="detailQuestion_model">试题详细信息</h4>
                        </div>

                        <div class="modal-body panel panel-default el_qusContentInfo">
                           <!--  <div class="panel-heading">题目</div> -->
                            <div class="panel-body" id="detail_questionContent">                             
                            </div>
                            <!-- <div class="panel-heading">选项</div> -->
                            	
                            <div class="panel-body">
                               <table>
			                       <!--  <thead>
			                        <tr>			                            
			                            <th>序号</th>
			                            <th>选项内容</th>			                          
			                        </tr>
			                        </thead> -->
			                        <tbody id="optionsListInfo">             
			                        </tbody>
			                    </table>
                            </div>
                            <div class="panel-heading">试题答案</div>
                            <div class="panel-body" id="detail_questionAnswer">                               
                            </div>
                            <!-- <div class="panel-heading">试题解析</div>
                            <div class="panel-body" id="detail_analysis">
                            </div> -->
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>

                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>

            <!-- 模态框   信息删除确认 -->
            <div class="modal fade" id="delcfmModel">
                <div class="modal-dialog" data-backdrop="static" data-keyboard="false">
                    <div class="modal-content message_align">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">×</span></button>
                            <h4 class="modal-title">提示</h4>
                        </div>
                        <div class="modal-body">
                            <p>您确认要删除这道试题吗？</p>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" id="delete_questionId"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <a onclick="deleteQuestionById()" class="btn btn-success" data-dismiss="modal">确定</a>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

        </div>


    </div>
</div>

</div>
</div>
<!--放脚-->
<jsp:include page="/view/public/footer.jsp"></jsp:include>

</body>
</html>