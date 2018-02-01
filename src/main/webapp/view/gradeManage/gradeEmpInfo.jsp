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
<title>员工成绩管理</title>

<%@ include file="/public/cssJs.jsp"%>
	<script>
	   		var selectExamName = "${param.name}";	   		
  	</script>
    <!--分页-->
    <script src="<%=path %>/js/public/page.js"></script>
   
    <script src="<%=path %>/js/gradeManage/gradeEmpInfo.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/gradeManage/gradeEmpInfo.css">
    <!-- 日期格式转换 -->
    <script src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>
     	 	
        
       
</head>
<body>

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
        <div class="panel-heading"><span>成绩管理</span><span>>员工成绩</span></div>

        <div class="row">

            <!--内容-->
            <div class=" col-md-12">

                <!--索引-->
                <div class="row el_queryBox">
                    <form id="form_findEmployeeGradesInfo" action="${pageContext.request.contextPath }/exportGrade_findEmployeeGradesToExport.action" method="post">

                        <div class="row el_queryBoxrow">

                            <div class="el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">考试名称：</span>
                                    <input type="text" class="form-control" name="examName" />
                                </div>
                            </div>

                            <div class="el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">考试级别：</span>
                                    <select class="selectpicker form-control" title="请选择" name="examLevel">
                                        <option value="">--请选择--</option>
                                        <option value="1">厂级</option>
                                        <option value="2">部门级</option>
                                        <option value="3">班组级</option>
                                    </select>
                                </div>
                            </div>

                            <div class="el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">考试时间：</span>
                                    <input type="text" id="test" class="wicon el_noVlaue form-control" readonly name="examTime"/>
                                </div>
                            </div>
                        </div>

                        <div class="row el_queryBoxrow">
                            <div class="el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">员工姓名：</span>
                                    <input type="text" class="form-control" name="employeeName"/>
                                </div>
                            </div>

                            <div class="el_qlmQuery el_qlmQueryR">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">员工性别：</span>
                                    <label class="el_radioBox"><input type="radio" name="employeeSex" value="1"> 男 </label>
                                    <label class="el_radioBox"><input type="radio" name="employeeSex" value="2"> 女 </label>
                                </div>
                            </div>

                            <div class="el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">是否及格：</span>
                                    <label class="el_radioBox"><input type="radio" name="isPass" value="是"> 是</label>
                                    <label class="el_radioBox"><input type="radio" name="isPass" value="否"> 否</label>
                                </div>
                            </div>
                            
                        </div>

                        <div class="row el_queryBoxrow">
                        
                        
                            <div class="el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">身&nbsp;&nbsp;份&nbsp;证：</span>
                                    <input type="text" class="form-control" name="employeeIdCard"/>
                                </div>
                            </div>
                            <div class="el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">答题方式：</span>
                                    <select class="selectpicker form-control" title="请选择" name="examMethod">
                                        <option value="">--请选择--</option>
                                        <option value="线上">线上</option>
                                        <option value="线下">线下</option>                                        
                                    </select>
                                </div>
                            </div>
                        </div>
                        
                        	
						
						<!-- 隐藏当前页和显示条数 -->
                        <input type="hidden" name="currentPage" id="currentPage" />
						<input type="hidden" name="currentCount" id="currentCount" />
						<input type="hidden" name="examId" id="examId" value="${param.examId }" />
						
                        <!--提交查询按钮-->
                        <button type="button" class="btn btn-primary el_queryButton btn-sm" onclick="searchEmployeeGradeInfo()">查询</button>
                    	<button type="reset" class="btn btn-default el_queryButton0 btn-sm">清空</button>
                    </form>

                </div><!--结束 查询表单提交-->

                <!--显示内容-->
                <h4 class="el_mainHead">员工成绩信息</h4>
                <div class="panel panel-default el_Mainmain">

                    <!--按钮面板-->
                    <div class="panel-body">

 					<div class="panel panel-default">
                            <div class="panel-body el_MainxiaoMain">

                                <div class="el_topButton">
                                    <button class="btn btn-primary" onclick="employeeGradeExportModel()">
                                        	成绩导出
                                    </button>
                                </div>
                            </div>
                        </div>
                        
                        <!--表格
                            内容都提取到json里边
                        -->
                        <table class="table table-hover  table-bordered">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>答题方式</th>
                                <th>成绩</th>
                                <th>是否通过</th>
                                <th>考试名称</th>
                                <th>考试级别</th>
                                <th>考试时间</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="employeeGradesListInfo" class="paginationID">
                                        
                            </tbody>
                        </table>

                        <!--分页-->
                        <div id="employeeGradeManage_paginationIDU" class="paginationID"></div>

                    </div>
                </div>
                
				<!-- 模态框 成绩导出-->
                <div class="modal fade" id="employGradeExportModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <!--标题-->
                                <h4 class="modal-title" id="myModalLabel1">导出成绩单</h4>
                            </div>
                            <form>

                                <div class="modal-body">
                                    <div class="modal-body">
			                            <p>您确认要导出查询的员工成绩吗？</p>
			                        </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>                                    
                                    <a id="exportExcelEmGrade"   class="btn btn-primary" onclick="employeeGradeExportExcel()">确认导出</a>
                                </div>
                            </form>

                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>
                
                <!-- 模态框 成绩详情--在线考试-->
                <div class="modal fade" id="el_scoreAllContent" tabindex="-1" role="dialog"
                     aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <!--标题-->
                                <h4 class="modal-title" id="myModalLabel132">成绩详情</h4>
                            </div>
                            <form>
                                <div class="modal-body">

                                    <!--考试信息-->
                                    <div class="el_modelTitle">
                                        <span>考试信息及员工考试信息</span>
                                    </div>
                                    <!--员工考试信息-->
                                    <div class="el_examEmpInfo">
                                        <table class="table table-hover  table-bordered">
                                            <thead>
                                            <tr>
                                                <th>姓名</th>
                                                <th>性别</th>
                                                <th>身份证</th>
                                                <th>所属部门</th>
                                                <th>考试成绩</th>
                                                <th>是否合格</th>
                                                <th>考试方式</th>
                                                <th>答题时间</th>
                                                <th>交卷时间</th>
                                                <th>登录时间</th>
                                                <th>登录IP</th>
                                            </tr>
                                            </thead>
                                            <tbody id="online_employeeGradeInfo">
                                          
                                            </tbody>
                                        </table>
                                    </div>

                                    <!--成绩试卷内容-->
                                    <div class="el_modelTitle">
                                        <span>试卷答题结果</span>
                                    </div>
                                    <div class="el_examparperInfo">
                                        <table>
                                            <thead>
                                            <tr>
                                                <th>试题类型</th>
                                                <th>总分/成绩</th>                                    
                                            </tr>
                                            </thead>
                                            <tbody id="onlineExam_scoreInfo">                                       
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                </div>
                            </form>

                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>

                <!-- 模态框 成绩详情--线下考试-->
                <div class="modal fade" id="el_scoreAllContent2" tabindex="-1" role="dialog"
                     aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content" style="height:270px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <!--标题-->
                                <h4 class="modal-title" id="myModalLabel13">成绩详情</h4>
                            </div>
                            <form>
                                <div class="modal-body" style="padding:3px;margin-left: 15px;">

                                    <!--员工考试信息-->
                                    <div class="el_modelTitle">
                                        <span>员工考试信息</span>
                                    </div>
                                    <div class="el_examEmpInfo el_examEmpInfo2">
                                        <table class="table table-hover  table-bordered">
                                            <thead>
                                            <tr>
                                                <th>姓名</th>
                                                <th>性别</th>
                                                <th>身份证</th>
                                                <th>所属部门</th>
                                                <th>考试成绩</th>
                                                <th>是否合格</th>
                                                <th>考试方式</th>
                                                <th>考试时间</th>
                                            </tr>
                                            </thead>
                                            <tbody id="offline_employeeGradeInfo">                                            
                                            </tbody>
                                        </table>
                                    </div>

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
</div>

<!--放脚-->
<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>
