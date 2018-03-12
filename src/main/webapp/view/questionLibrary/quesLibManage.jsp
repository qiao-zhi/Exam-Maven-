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
    <title>题库管理</title>
<%@ include file="/public/cssJs.jsp"%>
   
    <script src="<%=path %>/js/public/page.js"></script>
    
    <!--左边+索引+模态框的树-->
    <%-- <script src="<%=path %>/js/public/tree.js"></script> --%>
    <link rel="stylesheet" href="<%=path %>/css/public/tree.css">
    
    <!--验证-->
	<script src="${pageContext.request.contextPath }/controls/validate/jquery.validate.js"></script>
	<script src="${pageContext.request.contextPath }/controls/validate/messages_zh.js"></script>
    
    <!-- 日期格式转换 -->
    <script src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>
    
    <!--图表-->
    <script src="<%=path %>/controls/echarts/echarts.min.js"></script>
    
    <script src="<%=path %>/js/questionLibrary/quesLibManage.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/questionLibrary/quesLibManage.css">
    <script>
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
        <div class="panel-heading"><span>资料管理</span><span>>题库管理</span></div>

        <div class="el_main">
            <!--树-->
           <!--  <div class="el_leftTree">
               
                <span class="el_treeTitle">部门</span>
                <ul id="departmentTree_QLM1" class="ztree"></ul>
            </div> -->

            <!--内容-->
            <div class="el_qlmContent" style="width: 100%;">
                <!--索引-->
                <div class="row el_queryBox">
                    <form id="form_findQuestionBankInfo">

                        <div class="row el_queryBoxrow">

                            <div class="col-md-3 el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">题库名称：</span>
                                    <input type="text" class="form-control" name="questionBankName"/>
                                </div>
                            </div>

                            <!-- <div class="col-md-3 el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans el_chooseSpan">所属部门：</span>
                                    <ul id="log" class="el_modelinput el_chooseInput log"></ul>
                                    <input type="hidden" id="questionBankfind_DepartId" name="departmentId"/>
                                    <img src="../../controls/selectDropTree/smallTriangle.png" class="el_smallTriangle"
                                         width="7"/>
                                    <ul id="departmentTree_QLM2" class="ztree"></ul>
                                </div>
                            </div> -->
                            <div class="col-md-3 el_qlmQuery el_qlmQueryRadio">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">题库类别：</span>
                                    <label class="el_radioBox"><input type="radio"  name="questionBackTypeId" value="100"/> 工种</label>
                                    <label class="el_radioBox"><input type="radio"  name="questionBackTypeId" value="200"/> 知识点</label>                               
                                </div>
                            </div>        
                        </div>                        
                        <!-- 隐藏当前页和显示条数 -->
                        <input type="hidden" name="currentPage" id="currentPage" />
						<input type="hidden" name="currentCount" id="currentCount" />
                        <!--提交查询按钮-->
                       
                       <button type="reset" class="btn btn-default el_queryButton0 btn-sm" onclick="clear_department()">清空</button>
                       <button type="button" class="btn btn-primary el_queryButton btn-sm" onclick="searchQuestionBankInfo()">查询</button>
                       
                    </form>

                </div>   <!--结束 查询表单提交-->

                <!--显示内容-->
                <h3 class="el_mainHead">题库信息</h3>
                <div class="el_Mainmain">

                    <div class="panel-body">
                        <!--按钮区-->
                        <div class="panel panel-default">
                            <div class="panel-body el_MainxiaoMain">

                                <div class="el_topButton">
                               	 <shiro:hasPermission name="questionbank:manager">
                                    <!-- 按钮触发模态框1 -->
                                        <button class="btn btn-primary" onclick="el_addDictinary()">
                                            	添加题库
                                        </button>
                                        
	                                    <button class="btn btn-primary" onclick="questionsBatchImport()">
	                                        	试题批量导入
	                                    </button>
								    </shiro:hasPermission>
	                                    <button class="btn btn-primary" onclick="batchModalLoad()">
									                        试题批量导入模版下载
									    </button>
                                </div>

                            </div>
                        </div>

                        <table class="table table-hover  table-bordered">
                            <thead>
                            <tr>
                            	<th>选择</th>
                            	<th>序号</th>
                                <th>题库名称</th>
                                <th>所属类别</th>
                                <th>试题数量</th>                                          
                                <th>创建人</th>
                                <th>创建时间</th>
                                <th width="140">操作</th>
                            </tr>
                            </thead>
                            <tbody id="questionBankListInfo">
                            
                            </tbody>
                        </table>

                        <!--分页-->
                        <div id="questionLib_paginationIDU" class="paginationID"></div>

                    </div>
                </div>
            </div>

            <!-- 模态框 题库添加-->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true" >&times;</button><!--关闭符号-->
                            <!--标题-->
                            <h4 class="modal-title" id="myModalLabel">添加题库</h4>
                        </div>
                        <form id="form_AddQuestionBank">
                            <div class="modal-body">

                                <div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans">题库名称：</span>
                                    <input type="text" class="form-control el_modelinput" id="add_questionBankName" name="questionbankname" onblur="existQuestionbankName(this)"/>
                               		<span class="isQustionBankNameInfo"></span>
                                </div>
                                <div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans">题库类别：</span>
                                  
                                   	<label><input type="radio" id="select_backTypeId" class="select_backType" checked name="type" value="100"/>&nbsp;工种</label> &nbsp;&nbsp;
                                    <label><input type="radio" class="select_backType" name="type" value="200"/>&nbsp;知识点</label>
                               		
                                </div>
                                <div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans">类别明细：</span>
                                    <select class="combox form-control addAndUpdate_questionBankType" name="categorynameid">
                               		 </select>                               		
                                </div>

                                <!-- <div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans el_chooseSpan">选择部门：</span>
                                    <input type="text" class="form-control el_modelinput el_chooseInput"
                                           id="addDefaultDepart"  disabled/>
                                    <input type="hidden" id="questionbank_Departmentid" name="departmentid"/>
                                </div>  -->                             

                                <div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans">题库说明：</span>
                                    <textarea class="form-control el_modelinput" rows="3" name="description" id="add_description"></textarea>
                                </div>

                                <div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans">创&nbsp;&nbsp;建&nbsp;人：</span>
                                    <input type="text" class="form-control el_modelinput"  value="${session.userinfo.username}" disabled/>
									<input type="hidden" name="employeename" value="${session.userinfo.username}"/>                                
                                </div>

                                <div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans">创建时间：</span>
                                    <input type="text" id="test4" class="wicon form-control el_modelinput" readonly name="createtime"/>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button" class="btn btn-primary" onclick="submitQuestionBankInfo()">保存</button>
                            </div>
                        </form>

                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>

				<!-- 模态框 试题批量录入-->
                <div class="modal fade" id="questionBatchImportModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <!--标题-->
                                <h4 class="modal-title" id="myModalLabel">批量导入题库试题</h4>
                            </div>
                            <form  enctype="multipart/form-data" id="form_inputQuestions">

                                <div class="modal-body">

                                    <div class="expScoreTitle">
                                        <span>题库名称：</span>
                                        <span id="input_questionBankName"></span>
                                    </div>
                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">导入试题信息：</span>
                                        <input id="importQuestionsWord" type="file"  name="fileName" accept=".xls"/>
                                        <input type="hidden" id="input_questionBankId" name="questionBankId">
                                        <input type="hidden" id="input_departmentId" name="departmentId">
                                        <input type="hidden" id="input_upLoadPersonName" name="upLoadPersonName" value='${session.userinfo.username}'>
                                        <input type="hidden" id="input_upLoadPersonId" name="upLoadPersonId" value='${session.userinfo.employeeid}'>                                       
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button type="button" id="importBtn" class="btn btn-primary" onclick="inputQuestions()">导入</button>
                                </div>
                            </form>

                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>
                
            <!-- 模态框 题库修改-->
            <div class="modal fade" id="modifyQL" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;</button><!--关闭符号-->
                            <!--标题-->
                            <h4 class="modal-title" id="myModalLabel32">修改题库</h4>
                        </div>
                        <form id="form_UpdateQuestionBank">
                            <div class="modal-body">

                                <div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans">题库名称：</span>
                                    <input id="update_questionBankName" type="text" class="form-control el_modelinput" name="questionbankname" onblur="existQuestionbankName_update(this)"/>
                                	<span class="isQustionBankNameInfo"></span>
                                	<input id="update_questionBankName_old" type="hidden" />
                                </div>
								<div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans">题库类别：</span>
                                  
                                   	<label><input type="radio" class="select_backType" name="type" value="100" id="questionType_01"/>&nbsp;工种</label> &nbsp;&nbsp;
                                    <label><input type="radio" class="select_backType" name="type" value="200" id="questionType_02"/>&nbsp;知识点</label>
                               		
                                </div>
                                <div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans">类别明细：</span>
                                    <select class="combox form-control addAndUpdate_questionBankType" name="categorynameid">
                               		 </select>                               		
                                </div>
                               <!--  <div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans el_chooseSpan">所属部门：</span>                                   
                                    <input type="text" class="form-control el_modelinput" id="update_departmentName"  disabled/>
                                    <input type="hidden" name="departmentid" id="update_departmentId"/>
                                </div> -->                               

                                <div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans">题库说明：</span>
                                    <textarea class="form-control el_modelinput" rows="1" name="description" id="update_description"></textarea>
                                </div>

                                <div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans">创&nbsp;&nbsp;建&nbsp;人：</span>
                                    <input type="text" class="form-control el_modelinput" readonly name="employeename" id="update_employeeName"/>
                                </div>

                                <div class="input-group el_modellist" role="toolbar">
                                    <span class="el_spans">创建时间：</span>
                                    <input type="text" id="test41" class="wicon form-control el_modelinput" name="createtime"  readonly/>
                                </div>
								<input type="hidden" name="questionbankid" id="update_questionbankId"/>
								
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                               <button type="button" class="btn btn-primary" onclick="submitUpdateQLibInfo()">保存</button>
                            </div>
                        </form>

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
                            <p>您确认要删除该题库吗？</p><br/><strong style="color:red">注：删除该题库，题库内相关试题也将被删除！</strong>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" id="delete_questionBankId"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <a onclick="deleteQuestionBankById()" class="btn btn-success" data-dismiss="modal">确定</a>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

            <!-- 模态框 题库导出-->
            <div class="modal fade" id="el_export" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;</button><!--关闭符号-->
                            <!--标题-->
                            <h4 class="modal-title" id="myModalLabel0">题库导出</h4>
                        </div>
                        <form>
                            <div class="modal-body">
                                <div class="el_exportTitle"><span>题库名称：</span><span id="export_questionLibName"></span></div>
                                <div class="el_ExportSelect">
                                    <span>选择导出格式：</span>                      
                                    <label><input type="radio" id="wordFormat" class="el_ExportForm" name="el_ExportForm" value="1">Word</label>&nbsp;
                                    <label><input type="radio" class="el_ExportForm" name="el_ExportForm" value="2">Txt</label>
                                </div>

                                <div class="el_ExportSelect">
                                    <span>选择导出题型：</span>
                                    <label><input type="checkbox" name="el_QuestionType" value="1">单选题</label>&nbsp;
                                    <label><input type="checkbox" name="el_QuestionType" value="2">多选题</label>&nbsp;
                                    <label><input type="checkbox" name="el_QuestionType" value="3">判断题</label>
                                </div>
								<input type="hidden" id="questionBankIdExport">
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <!-- <button type="button" class="btn btn-primary">确认导出</button> -->
                                <a id="export_questionLib" class="btn btn-primary" onclick="exportQuestionsWithCondition()">确认导出</a>
                            </div>
                        </form>

                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>

            <!-- 模态框 数据统计分析-->
            <div class="modal fade" id="el_statisticAnalyze" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;</button><!--关闭符号-->
                            <!--标题-->
                            <h4 class="modal-title" id="myModalLabel3">统计分析</h4>
                        </div>

                        <form>
                            <div class="modal-body el_parperInfo">
                                <table>
                                    <tr>
                                        <td>题库名称</td>
                                        <td id="statistics_questionBankName"></td>
                                    </tr>
                                    <tr>
                                        <td>试题数量</td>
                                        <td id="statistics_sumquestions"></td>
                                    </tr>
                                    <tr>
                                        <td>题库描述</td>
                                        <td id="statistics_description"></td>
                                    </tr>
                                </table>
                            </div>
                            <div style="width:90%;margin:0 auto;">
                                <!--图表-->
                                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                                <div id="main" style="width:500px; height:200px;"></div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            </div>
                        </form>

                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>

        </div>
    </div>
</div>

</div>
</div>
<!--放脚-->
<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>

</html>