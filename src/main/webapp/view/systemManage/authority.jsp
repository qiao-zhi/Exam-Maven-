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
    <title>权限管理</title>
<%@ include file="/public/cssJs.jsp"%>


    <script src="<%=path %>/js/system/treeSys.js"></script><!--菜单树-->

    <script src="<%=path %>/js/system/authority.js"></script>

    <link rel="stylesheet" href="<%=path %>/css/outDepart/outdepartTree.css">
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
        <div class="panel-heading"><span>系统管理</span><span>>权限管理</span></div>

        <div class="panel-body el_main">
            <!--树-->
            <div class="el_leftTree">
                <!--标题类，添加了一个颜色-->
                <span class="el_treeTitle">菜单</span>
                <ul id="tree_systemmanage_menu" class="ztree"></ul>
            </div>

            <!--内容-->
            <div class="col-md-10 el_qlmContent">

                <!--索引-->
                <div class="row el_queryBox">
                    <form id="form_permissioninfo" action="">
                    
                    	<input type="hidden" id="currentPage" name="currentPage">
						<input type="hidden" id="currentCount" name="currentCount">
						<input type="hidden" id="topmenuid" name="topmenuid">
                        <div class="col-md-3 el_qlmQuery">
                            <div class="input-group" role="toolbar">
                                <span class="el_spans">权限名称：</span>
                                <input type="text" class="form-control" name="permissionname"/>
                            </div>
                        </div>

                        <div class="col-md-3 el_qlmQuery el_qlmQueryR">
                            <div class="input-group" role="toolbar">
                                <span class="el_spans">权限状态：</span>
                                <label class="el_radioBox"><input type="radio" name="permissionstatus" value='1'> 启用</label>&nbsp;
                                <label class="el_radioBox"><input type="radio" name="permissionstatus" value='0'> 禁用</label>
                            </div>
                        </div>

                        <!--提交查询按钮-->
                        <button type="reset" class="btn btn-default el_queryButton0 btn-sm">清空</button>
                        <button type="button" class="btn btn-primary el_queryButton btn-sm" onclick='getPermissionByCondition()'>查询</button>
                    </form>

                </div>   <!--结束 查询表单提交-->

                <!--显示内容-->
                <h4 class="el_mainHead">权限管理</h4>
                <div class="panel panel-default el_Mainmain">

                    <div class="panel-body">

                        <!--表格
                            内容都提取到json里边
                        -->
                        <table class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>权限名称</th>
                                <th>权限状态</th>
                                <th>权限描述</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id='table_permissioninfo'>
                            </tbody>
                        </table>

                        <!--分页-->
                        <div id="paginationIDU" class="paginationID"></div>

                    </div>
                </div>

                <!-- 模态框 修改用户信息-->
                <div class="modal fade" id="modifyUserInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <!--标题-->
                                <h4 class="modal-title" id="myModalLabel24">修改用户信息</h4>
                            </div>
                            <form>
                                <div class="modal-body">

                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">权限名称：</span>
                                        <input type="text" class="form-control el_modelinput" name=""/>
                                        <span></span>
                                    </div>

                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">权限状态：</span>
                                        <label class="el_radioBox"><input type="radio" name="el_state"> 启用</label>&nbsp;
                                        <label class="el_radioBox"><input type="radio" name="el_state"> 禁用</label>
                                    </div>

                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">权限类别：</span>
                                        <select class="selectpicker form-control" title="请选择">
                                            <option>--请选择--</option>
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                        </select>
                                    </div>

                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">权限描述：</span>
                                        <textarea class="form-control el_modelinput" rows="1"></textarea>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button type="button" class="btn btn-primary">保存</button>
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