<!-- 监考中心详情 -->
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
<title>个人考试监考中心</title>
<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<%@ include file="/public/cssJs.jsp"%>

    <!--分页-->
    <script src="<%=path %>/js/public/page.js"></script>
    
    <script src="../../js/examParper/exam/invigilatePersonCenter.js"></script>
    <link rel="stylesheet" href="../../css/examParper/examParper.css">
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
        <div class="panel-heading"><span>员工管理</span><span>>用户添加</span></div>

        <div class="panel-body el_main">

            <!--内容-->
            <div class="col-md-12">

                <!--索引-->
                <div class="row el_queryBox">
                    <form action="">
                            <div class="col-md-3 el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">用户姓名：</span>
                                    <input type="text" class="form-control" name=""/>
                                </div>
                            </div>

                            <div class="col-md-3 el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">用户账号：</span>
                                    <input type="text" class="form-control" name=""/>
                                </div>
                            </div>

                        <!--提交查询按钮-->
                        <button type="submit" class="btn btn-default el_queryButton btn-sm">查询</button>
                    </form>

                </div><!--结束 查询表单提交-->

                <!--显示内容-->
                <h4 class="el_mainHead">员工考试信息</h4>
                <!--按钮面板-->
                <div class="el_topButton">
                    <a href="<%=path %>/view/examParper/invigilateCenter.jsp"><button class="btn btn-default btn-sm">
                      	  返回考试中心
                    </button></a>
                </div>

                <div class="panel panel-default el_Mainmain">

                    <div class="panel-body">

                        <!--考试信息-->
                        <div class="el_examInfo">
                            <ul>
                                <li>考试名称：<span>******考试</span></li>
                                <li>考试级别：<span>一级</span></li>
                                <li>考试时长：<span>120分钟</span></li>
                                <li>考试时间：<span>2011-10-12 10:00AM -- 12:00AM	</span></li>
                                <li>在线考试人数：<span>10</span></li>
                            </ul>
                        </div>
                        <!--表格
                           内容都提取到json里边-->
                        <table class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>用户账户</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>所属部门</th>
                                <th>考试状态</th>
                                <th>违规次数</th>
                                <th>开考时间</th>
                                <th>交卷时间</th>
                                <th>登录时间</th>
                                <th>IP地址</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>0325864</td>
                                <td>王菲</td>
                                <td> nan</td>
                                <td>*****部门</td>
                                <td>交卷</td>
                                <td class="success" onclick="breakTimes(this)">1</td>
                                <td>2017-12-10 10:23AM</td>
                                <td>10:40AM</td>
                                <td>2017-12-10 08:00AM</td>
                                <td>192.168.1.1</td>
                                <td><a href="<%=path %>view/lineExam/paperAnaly.jsp" target="_blank">查看试卷</a></td>
                            </tr>
                            <tr>
                                <td>0325864</td>
                                <td>王菲</td>
                                <td> nan</td>
                                <td>*****部门</td>
                                <td>考试中</td>
                                <td>--</td>
                                <td>2017-12-10 10:23AM</td>
                                <td>--</td>
                                <td>2017-12-10 10:40AM</td>
                                <td>192.168.1.1</td>
                                <td><a href="javascript:volid(0);">查看试卷</a></td>
                            </tr>
                            </tbody>
                        </table>

                    </div>
                </div>
                <!--分页-->
                <div id="paginationID"></div>

                <!-- 模态框 违规情况-->
                <div class="modal fade" id="breakTimes" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <!--标题-->
                                <h4 class="modal-title" id="myModalLabel">违规信息</h4>
                            </div>
                            <div class="modal-body">
                                <table class="el_outParperTable">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>违规开始时间</th>
                                        <th>距上次违规时间</th>
                                        <th>违规持续时间</th>
                                        <th>违规定次数</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>2017-12-10 10:20AM</td>
                                        <td>3分钟</td>
                                        <td>1分钟</td>
                                        <td class="el_times">3</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>2017-12-10 10:20AM</td>
                                        <td>3分钟</td>
                                        <td>1分钟</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>2017-12-10 10:20AM</td>
                                        <td>3分钟</td>
                                        <td>1分钟</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            </div>

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
