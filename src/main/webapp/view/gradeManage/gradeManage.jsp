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
<title>成绩管理</title>

<%@ include file="/public/cssJs.jsp"%>
	<script>
		//获取项目名称
		var basePathUrl = "${pageContext.request.contextPath}";
	</script>
    <!--分页-->
    <script src="<%=path %>/js/public/page.js"></script>

 	<!--图表-->
    <script src="<%=path %>/controls/echarts/echarts.min.js"></script>
	<!-- 日期格式转换 -->
    <script src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>
    
    <script src="<%=path %>/js/gradeManage/gradeManage.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/gradeManage/gradeManage.css">
    
    <style>
    	#el_importInfo{
    	width:100%;
    	min-height:280px;
    	overflow:auto;
    		font-size:13px;
    		position:relative;
    		border-top:1px solid #ccc;
    	}
    	#el_importInfo_left p,#el_importInfo_right p{
    		text-align:center;
    		margin:0 auto;
    		font-size:14px !important;
    		margin-top:5px;
    		margin-bottom:5px;
    	}
    	#el_importInfo table{
    		font-size:13px;
    	}
	    	#el_importInfo_left{
	    	width:40%;
	    	position:absolute;
	    	left:10px;
    	}
    	#el_importInfo_right{
    	width:55%;
    	position:absolute;
    	right:10px;
    	}
    	
    	#el_importUploadButton{
    		position:absolute;
    		right:30px;
    		top:50px;
    	}
    	.prompt{

    		color: #888;
			font-size: 12px;
			margin-left: 250px;
    	}
    	
    	.prompt1{

    		color: #888;
			font-size: 12px;
			margin-left: 450px;
    	}
    </style>
    
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
        <div class="panel-heading"><span>培训管理</span><span>>成绩管理</span></div>
        <div class="panel-body el_main">
            <!--内容-->
            <div class=" col-md-12">

                <!--索引-->
                <div class="row el_queryBox">
                    <form id="form_findExamGradesInfo">
                        <div class="row el_queryBoxrow">

                            <div class="el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">考试名称：</span>
                                    <input type="text" class="form-control" name="examName"/>
                                </div>
                            </div>

                            <div class="col-md-3 el_qlmQuery">
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

                            <div class="col-md-3 el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">考试时间：</span>
                                    <input type="text" id="test" class="wicon el_noVlaue form-control" readonly name="examTime"/>
                                </div>
                            </div>
							
                        </div>
						
						<!-- 隐藏当前页和显示条数 -->
                        <input type="hidden" name="currentPage" id="currentPage" />
						<input type="hidden" name="currentCount" id="currentCount" />
						
                        <!--提交查询按钮-->
                        <button type="button" class="btn btn-primary el_queryButton btn-sm" onclick="searchExamGradeInfo()">查询</button>
                    	<button type="reset" class="btn btn-default el_queryButton0 btn-sm">清空</button>
                    </form>

                </div><!--结束 查询表单提交-->

                <!--显示内容-->
                <h4 class="el_mainHead">成绩信息</h4>
                <div class="panel panel-default el_Mainmain">

                    <!--按钮面板-->
                    <div class="panel-body">

                        <div class="panel panel-default">
                            <div class="panel-body el_MainxiaoMain">

                                <div class="el_topButton">
                                    <button class="btn btn-primary" onclick="gradeInput()" id="gradeImportButton" style="display:none;">
                                        成绩导入
                                    </button>
                                    <button type="button" class="btn btn-primary el_btnStatic" onclick="gradeAnaly()">
                                        成绩分析
                                    </button>
                                    <select class="btn btn-primary" id="el_findExamGradeType" style="font-size: 13px;"
														title="请选择">																												
														<option value="1">按部门查看成绩</option>
														<option value="0">按考试查看成绩</option>
													</select>

									<span class="glyphicon glyphicon-hand-right prompt dept">  如需导入成绩请将按钮切换到<strong style="font-weight:bolder">【按考试查看成绩】</strong>，选择自己创建的线下考试导入成绩。</span>
                					<span class="glyphicon glyphicon-hand-right prompt1 exam">  表格中灰色阴影的表示线下类型的考试。</span>
								
                                </div>
                            </div>
                    </div>

                        <!--表格
                            内容都提取到json里边
                        -->
                        <table class="table  table-hover table-bordered">
                            <thead>
	                            <tr>
		                            <th>选择</th>
		                            <th>序号</th>
		                            <th>考试名称</th>
		                            <th>考试级别</th>
		                            <th id="departmentName">部门名称</th>
		                            <th>考试人数</th>
		                            <th>及格人数</th>		                            
		                            <th>考试状态</th>
		                            <th>试卷总分</th>		                           
		                            <th>考试时间</th>
		                            <th width="160" id="operation">操作</th>
	                            </tr>
                            </thead>
                            <tbody id="examGradesListInfo">                           
                            </tbody>
                        </table>
						<!-- 分页 -->
                        <div id="gradeManage_paginationIDU" class="paginationID"></div>						                    	                    	
                    </div>
                              			
                </div>

                <!-- 模态框 成绩录入-->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog" data-backdrop="static" data-keyboard="false">
                        <div class="modal-content" style="height:500px;">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <!--标题-->
                                <h4 class="modal-title" id="myModalLabel">成绩导入</h4>
                            </div>
                           
								<form  enctype="multipart/form-data" id="form_inputEmployeeGrade">
                                <div class="modal-body">
								
                                    <div class="expScoreTitle">
                                        <span>考试名称：</span>
                                        <span id="into_examName"></span>
                                    </div>
                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">导入成绩单：</span>
                                        <input type="file" id="exampleInputEmail12" name="fileName" accept=".xls"/>
                                        <input type="hidden" id="input_examId" name="examId">
                                    </div>
                                    <div id="el_importUploadButton">
                                    	<button type="button" id="importButton"  class="btn-default btn btn-sm" onclick="importExcelFile()">上传</button>
                                    </div>
                                 
                                    <div id="el_importInfo">
                                    	<div id="el_importInfo_left">
                                    		<p>线下考试员工名单</p>
                                    		<span>
                                    			统计：
                                    			<span id="count_lift"></span>
                                    		</span>
                                    		<table class="table  table-hover table-bordered">
                                    			<thead>
                                    			<tr><th>员工考号</th><th>姓名</th></tr>
                                    			</thead>
                                    			<tbody id="employeeOutInfoList">                                    		
                                    			</tbody>
                                    		</table>
                                    	</div>
                                    	<div id="el_importInfo_right">
                                    		<p>上传文件考试员工名单</p>
                                    		<span>
                                    			统计：
                                    			<span id="count_right"></span>
                                    		</span>
                                    		<table class="table  table-hover table-bordered">
                                    			<thead>
                                    			<tr><th>员工考号</th><th>姓名</th><th>成绩</th></tr>
                                    			</thead>
                                    			<tbody id="excelGradeInfoList">                                    				
                                    			</tbody>
                                    		</table>
                                    	</div>
                                    </div>
                                </div>

                                <div class="modal-footer" id="importEmployeeOutButton">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <!-- <button type="button" id="importEmployeeOutButton" class="btn btn-primary" >导入</button> -->
                                </div>
                            </form>  
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>

                <!-- 模态框 成绩导出-->
                <div class="modal fade" id="el_scoreExport" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
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
                                    <div class="form-group">
                                        <span>考试名称：</span><span id="export_ExamName"></span>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <!-- <button type="button" class="btn btn-primary">确认导出</button> -->
                                    <a id="exportExcelEmGrade"  class="btn btn-primary" onclick="exportModelcolse()">确认导出</a>
                                </div>
                            </form>

                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>

                <!-- 模态框 成绩统计-->
                <div class="modal fade" id="el_scoreStatistisModel" tabindex="-1" role="dialog"
                     aria-labelledby="myModalLabel" style="height:580px;"
                     aria-hidden="true" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <h4 class="modal-title" id="myModalLabel2">成绩统计</h4>
                                <!--标题-->
                            </div>
                            <div class="modal-body">
								<form id="form_gradeAnalyse">
	                                <div class="el_setGrade">
	                                    <span>配置参数：</span><br/>
	                                    <span>优： <input type="text" class="el_setGradeInput" name="excellentGrade" value="95"/>  ---
	                                    <input class="el_setGradeInput" type="text" value="100" disabled/> 分</span>
	                                   <!--  <span>良： <input type="text" class="el_setGradeInput" value="60" />  ---
	                                    <input type="text" class="el_setGradeInput" value="80" /> 分</span> -->
	                                    <span>差： <input type="text" class="el_setGradeInput" value="0" disabled/>  ---
	                                    <input type="text" class="el_setGradeInput" name="passGrade" value="90"/> 分</span>
	                                    <button class="el_setGradeTrue btn-sm btn btn-info" type="button" onclick="examGradeAnalyse()">确定</button>
	                                </div>
	                                <!-- 隐藏考试和部门ID -->
	                               <input type="hidden" id="gradeAnalyse_examId" name="examId"/>
								   <input type="hidden" id="gradeAnalyse_unitId" name="unitId"/>
								</form>
                                <!--统计-->
                                <div class="el_threePanelChart">
                                    <!--图表-->
                                    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                                    <div id="main" style="width: 600px;height:400px;"></div>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            </div>

                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>

				<!-- 模态框 查看部门员工成绩详情-->
					<div class="modal fade" id="unitEmployeeInfos" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<!--关闭符号-->
									<!--标题-->
									<h4 class="modal-title" id="myModalLabel1">成绩详情</h4>
								</div>
								<form>
									<div class="modal-body">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th>序号</th>
													<th>员工姓名</th>
													<th>考试成绩</th>
													<th>是否通过</th>													
												</tr>
											</thead>
											<tbody id="unitEmployeeGradeListInfo">
											</tbody>
										</table>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">关闭</button>										
									</div>
								</form>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal -->
					</div>
       			<!-- 结束模态框 -->        
				
				
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
