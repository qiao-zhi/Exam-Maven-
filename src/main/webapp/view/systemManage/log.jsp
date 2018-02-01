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
    <title>日志管理</title>
<%@ include file="/public/cssJs.jsp"%>
 
    <!--分页--> 
    <script src="<%=path %>/js/public/page.js"></script>
    
    
    <script src="<%=path %>/js/system/log.js"></script>

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
        <div class="panel-heading"><span>系统管理</span><span>>日志管理</span></div>

        <div class="panel-body el_main">

            <!--内容-->
            <div class="col-md-12">

                <!--索引-->
                <div class="row el_queryBox">
                    <form action="">
                        <div class="row el_queryBoxrow">
                            <div class="col-md-3 el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">用户姓名：</span>
                                    <input type="text" class="form-control" name=""/>
                                </div>
                            </div>

                            <div class="col-md-3 el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans el_chooseSpan">角&nbsp;&nbsp;色：</span>
                                    <select class="selectpicker form-control" title="请选择">
                                        <option>--请选择--</option>
                                        <option>角色1</option>
                                        <option>角色1</option>
                                        <option>角色1</option>
                                        <option>角色1</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-3 el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">行为：</span>
                                    <select class="selectpicker form-control" title="请选择">
                                        <option>--请选择--</option>
                                        <option>登录</option>
                                        <option>退出</option>
                                        <option>删除</option>
                                        <option>添加</option>
                                        <option>修改</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row el_queryBoxrow">

                            <div class="col-md-3 el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">用户账号：</span>
                                    <input type="text" class="form-control" name=""/>
                                </div>
                            </div>

                            <div class="col-md-3 el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="add-on el_spans">操作时间：</span>
                                    <input type="text" id="test2" class="wicon el_noVlaue form-control" readonly/>
                                </div>
                            </div>

                        </div>

                        <!--提交查询按钮-->
                        <button type="submit" class="btn btn-default el_queryButton">查询</button>
                    </form>

                </div>   <!--结束 查询表单提交-->

                <!--显示内容-->
                <h4 class="el_mainHead">日志管理</h4>
                <div class="panel panel-default el_Mainmain">

                    <div class="panel-body">

                        <!--按钮面板-->
                        <div class="panel panel-default">
                            <div class="panel-body el_MainxiaoMain">

                                <div class="el_topButton">
                                    <button type="button" class="btn btn-primary" onclick="piliangdelcfm()">
                                        批量删除
                                    </button>
                                </div>
                            </div>
                        </div>

                        <!-- 表格  内容都提取到json里边 -->
                        <table class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="el_checkQuanxuan"/></th>
                                <th>用户账号</th>
                                <th>用户姓名</th>
                                <th>角色</th>
                                <th>IP地址</th>
                                <th>日志时间</th>
                                <th>行为</th>
                                <th>操作结果</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><input type="checkbox" class="el_checks"/></td>
                                <td>05011</td>
                                <td>二强</td>
                                <td>部门管理员</td>
                                <td>192.168.1.1</td>
                                <td>2011-12-10 10:00AM</td>
                                <td>用户登录</td>
                                <td>登录成功</td>
                            </tr>
                            </tbody>
                        </table>

                        <!--分页-->
                        <div id="paginationIDU"></div>

                    </div>
                </div>

                <!-- 模态框   批量信息删除确认 -->
                <div class="modal fade" id="delcfmModel2" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content message_align">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">×</span></button>
                                <h4 class="modal-title">提示</h4>
                            </div>
                            <div class="modal-body">
                                <p>您确认要删除该试卷吗？</p>
                            </div>
                            <div class="modal-footer">
                                <input type="hidden" id="url2"/>
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <a onclick="urlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
            </div>

        </div>

    </div>
</div>

</div></div>
<!--放脚-->
<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>