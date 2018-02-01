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
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
<%@ include file="/public/cssJs.jsp"%>
    <!--分页-->
<%--     <script src="<%=path %>/js/public/page.js"></script> --%>
	<script src="<%=path %>/js/home/header.js"></script>
	
	<!--验证-->
<script src="${pageContext.request.contextPath }/controls/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath }/controls/validate/messages_zh.js"></script>
	
    <!--还有左侧部门树0 --> <%-- 
    <script src="<%=path %>/js/system/role/treeR.js"></script> --%>
  <%--   <!--批量添加部门  复选框树10-->
    <script src="<%=path %>/js/public/treeC.js"></script> --%>
    <link rel="stylesheet" href="<%=path %>/css/public/tree.css">
    <!--权限树  修改、添加、批量添加、查看权限-->
<%--     <script src="<%=path %>/js/system/role/authorityTree.js"></script> --%>
  <%--   <!-- 、查看权限-->
    <script src="<%=path %>/js/system/role/lookTree.js"></script> --%>
    
    <script src="<%=path %>/js/system/role/role.js"></script>
    
    <!--选择树-->
    <link rel="stylesheet" href="<%=path %>/css/systemManage/role.css">
    
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
        <div class="panel-heading"><span>系统管理</span><span>>角色管理</span></div>

        <div class="panel-body el_main">

            <!--树-->
            <div class="el_leftTree">
                <!--标题类，添加了一个颜色-->
                <span class="el_treeTitle">部门</span>
                <ul id="tree_systemmanage_department" class="ztree"></ul>
            </div>

            <!--内容-->
            <div class="col-md-10 el_qlmContent">

                <!--索引-->
                <div class="row el_queryBox">
                    <form id="form_conditionfind_role" action="">
						<input type="hidden" id="currentPage" name="currentPage">
						<input type="hidden" id="currentCount" name="currentCount">
						<input type="hidden" id="departmentid" name="departmentid">
                        <div class="col-md-3 el_qlmQuery">
                            <div class="input-group" role="toolbar">
                                <span class="el_spans el_chooseSpan">角&nbsp;&nbsp;色：</span>
                                <select name='rolename' id='select_rolename' class="selectpicker form-control" title="请选择">
                                   
                                </select>
                            </div>
                        </div>

                        <div class="col-md-3 el_qlmQuery el_qlmQueryR">
                            <div class="input-group" role="toolbar">
                                <span class="el_spans">角色状态：</span>
                                <label class="el_radioBox"><input type="radio" name="rolestatus" value="1"> 启用</label>
                                <label class="el_radioBox"><input type="radio" name="rolestatus" value="0"> 禁用</label>
                            </div>
                        </div>

                        <!--提交查询按钮-->
                        <button type="reset" class="btn btn-default el_queryButton0 btn-sm">清空</button>
                        <button type="button" class="btn btn-primary el_queryButton btn-sm" onclick="getRoleByCondition()">查询</button>
                    </form>

                </div>   <!--结束 查询表单提交-->

                <!--显示内容-->
                <h4 class="el_mainHead">角色管理</h4>
                <div class="panel panel-default el_Mainmain">

                    <div class="panel-body">

                        <!--按钮面板-->
                        <div class="panel panel-default">
                            <div class="panel-body el_MainxiaoMain">

                                <div class="el_topButton">
                                    <button class="btn btn-primary" onclick="el_addrole()">
                                        	添加角色
                                    </button>

                                    <button type="button" class="btn btn-primary" onclick="deleteRoles()">
                                        	批量删除
                                    </button>

                                    <button type="button" class="btn btn-primary"   onclick='modal_addmorerole()'>
                                        	批量添加角色
                                    </button>

									
                                </div>

                            </div>
                        </div>

                        <!--表格
                            内容都提取到json里边
                        -->
                        <table class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="el_checkQuanxuan"/></th>
                                <th>序号</th>
                                <th>角色名称</th>
                                <th>角色状态</th>
                                <th>权限</th>
                                <th>描述</th>
                                <th width="160">操作</th>
                            </tr>
                            </thead>
                            <tbody id="table_roleinfo">
                           
                            </tbody>
                        </table>

                        <!--分页-->
                        <div id="paginationIDU" class="paginationID"></div>

                    </div>
                </div>

                <!-- 模态框 添加角色-->
                <div class="modal fade" id="el_addrole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <!--标题-->
                                <h4 class="modal-title" id="myModalLabel2">添加角色</h4>
                            </div>
                            <form id="form_addroleinfo">
                                <div class="modal-body">

                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">角色名称：</span>
                                        <input type="text" class="form-control el_modelinput" id="form_rolename" name="role.rolename"/>
                                        <span></span>
                                    </div>

                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">角色状态：</span>
                                        <label class="el_radioBox"><input type="radio" id="rolestatus_use" name="role.rolestatus" value='1' checked> 启用</label>&nbsp;
                                        <label class="el_radioBox"><input type="radio" name="role.rolestatus" value='0'> 禁用</label>
                                    </div>

                                

                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">角色描述：</span>
                                        <textarea class="form-control el_modelinput" name='role.description' id="form_roledescription" rows="1"></textarea>
                                    </div>

                                    <input type="hidden" name='role.departmentid' id='hidden_departmentid' >

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button type="button" class="btn btn-primary" onclick="addRoleinfo()">保存</button>
                                </div>
                            </form>

                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>

                <!-- 模态框 批量添加角色-->
                <div class="modal fade" id="batchAddRoles" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <!--标题-->
                                <h4 class="modal-title" id="myModalLabel242">批量添加权限</h4>
                            </div>
                            <form id="form_addmorerole">
                                <div class="modal-body">

                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">角色名称：</span>
                                        <input type="text" class="form-control el_modelinput" name="role.rolename"/>
                                        <span></span>
                                    </div>

                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">角色状态：</span>
                                        <label class="el_radioBox"><input type="radio" name="role.rolestatus" checked value='1'> 启用</label>&nbsp;
                                        <label class="el_radioBox"><input type="radio" name="role.rolestatus" value='0'> 禁用</label>
                                    </div>

                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">角色描述：</span>
                                        <textarea class="form-control el_modelinput" name="role.description" rows="1"></textarea>
                                    </div>
									
									<input type="hidden" name="permissionids" id="hidden_permissionids">
									<input type="hidden" name="departmentids" id="hidden_departmentids">
                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans el_chooseSpan">选择部门：</span>
                                        <ul id="el_chooseDepart1" onclick="treeClick()" class="form-control" 	></ul>
                                        <img src="../../controls/selectDropTree/smallTriangle.png" class="el_smallTriangle"
                                             width="7"/>
                                        <ul id="treeDemo10" style="border:none !important;width:300px !important;top:20px !important;" class="ztree"></ul>
                                    </div>

                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans el_chooseSpan" style="vertical-align: top;">配置权限：</span>
                                        <ul id="el_chooseDepart13" class="form-control"  onclick="treeClick13()"></ul>
                                        <img src="../../controls/selectDropTree/smallTriangle.png" class="el_smallTriangle"
                                             width="7"/>
                                        <ul id="treeDemo13" style="border:none !important;height:200px !important;width:250px !important;top:20px !important;" class="ztree"></ul>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button type="button" class="btn btn-primary" onclick='addMoreRoleWithPermission()'>保存</button>
                                </div>
                            </form>

                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>

                <!-- 模态框 修改角色信息-->
                <div class="modal fade" id="modifyUserInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <!--标题-->
                                <h4 class="modal-title" id="myModalLabel24">修改角色</h4>
                            </div>
                            <form id="form_updateroleinfo">
                                <div class="modal-body">
                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">角色名称：</span>
                                        <input type="text" class="form-control el_modelinput" id="updateroleinfo_rolename" name="role.rolename"/>
                                        <span></span>
                                    </div>

                                 <input type="hidden" id="hidden_update_roleid" name="role.roleid"> 	 

                                    

                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans">角色描述：</span>
                                        <textarea class="form-control el_modelinput" id="updateroleinfo_description" name="role.description" rows="1"></textarea>
                                    </div>

                                    
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button type="button" class="btn btn-primary" onclick="updateroleinfo_submit()">保存</button>
                                </div>
                            </form>

                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>

                <!-- 模态框   信息删除确认 -->
                <div class="modal fade" id="delcfmModel" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content message_align">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">×</span></button>
                                <h4 class="modal-title">提示</h4>
                            </div>
                            <div class="modal-body">
                                <p>您确认要删除该条信息吗？</p>
                            </div>
                            <div class="modal-footer">
                                <input type="hidden" id="url"/>
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <a onclick="urlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->

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
                                <p>您确认要删除所选角色吗？</p>
                            </div>
                            <div class="modal-footer">
                                <input type="hidden" id="url2"/>
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <a onclick="urlSubmit2()" class="btn btn-success" data-dismiss="modal">确定</a>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
				
				<!-- 模态框 查看所有权限-->
                <div class="modal fade" id="modal_permissioninfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <!--标题-->
                                <h4 class="modal-title" id="myModalLabe">查看该角色的所有权限信息</h4>
                            </div>
                            <div class="modal-body">
                                <table class="el_AuthInfo table table-border">
                                    <thead>
                                    <tr>
                                        <th>权限</th>
                                        <th>状态</th>
                                    </tr>
                                    </thead>
                                    <tbody id="permissionsinfo">
                                    
                                    </tbody>
                                </table>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            </div>

                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>
				
				  <!-- 模态框 配置角色-->
                <div class="modal fade" id="el_configPermissions" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <!--标题-->
                                <h4 class="modal-title" id="myModalLabel24">配置用户权限</h4>
                            </div>
                            <form id="updatePermissioninfo">
                            	<input type="hidden" id="updatepermissioninfo_roleid" name="roleid">
                            	<input type="hidden" id="hidden_rolepermissionids" name='role_permissionids'>
                                <div class="modal-body">
                                    <div class="input-group el_modellist" role="toolbar">
                                        <span class="el_spans el_chooseSpan">选择权限：</span>
                                        <span id="checkbox_selectpermission"></span>    
                                    </div>
                                </div>
                                <ul id="treeDemo_permission" class="ztree" style="width:auto !important;height:auto !important;border:none !important;"></ul>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button type="button" class="btn btn-primary" onclick='updatePermissioninfo()'>保存</button>
                                </div>
                            </form>

                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>
				
				
                <!-- 模态框 查看权限-->
                <div class="modal fade" id="searchAuth" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button><!--关闭符号-->
                                <!--标题-->
                                <h4 class="modal-title" id="myModalLabe">查看权限</h4>
                            </div>
                            <div class="modal-body">
                                <!--权限选择下拉树-->
                                <div class="el_seletTreeDepart">
                                    <ul id="treeDemo3" class="ztree"></ul>
                                </div><!--结束树-->
                                <br/>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            </div>

                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div><!--modal over-->

            </div>

        </div>

    </div>
</div>

</div></div>
<!--放脚-->
<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>