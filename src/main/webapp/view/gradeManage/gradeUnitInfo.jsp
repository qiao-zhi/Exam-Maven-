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
    
    <script src="<%=path %>/js/gradeManage/gradeUnitInfo.js"></script>
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
        <div class="panel-heading"><span>成绩管理</span><span>>培训成绩管理</span></div>
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

                        <!-- <div class="panel panel-default">
                            <div class="panel-body el_MainxiaoMain">

                                <div class="el_topButton">
                                    <button class="btn btn-primary" onclick="gradeInput()" id="gradeImportButton" style="display:none;">
                                        成绩导入
                                    </button>
                                    <button type="button" class="btn btn-primary el_btnStatic" onclick="gradeAnaly()">
                                        成绩分析
                                    </button>
                                </div>
                            </div>
                        </div>
 -->
                        <!--表格
                            内容都提取到json里边
                        -->
                        <table class="table  table-hover table-bordered">
                            <thead>    
                            	<tr>                   
		                            <th>序号</th>
		                            <th>考试名称</th>                           
		                            <th>考试级别</th>                           
		                            <th>部门名称</th>
		                            <th>考试人数</th>
		                            <th>及格人数</th> 
		                            <th>考试状态</th>
		                            <th>试卷总分</th>
		                            <th>考试时间</th> 
	                            </tr>                          
                            </thead>
                            <tbody id="examGradesListInfo">                           
                            </tbody>
                        </table>
						<!-- 分页 -->
                        <div id="gradeManage_paginationIDU" class="paginationID"></div>

                    </div>     
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
