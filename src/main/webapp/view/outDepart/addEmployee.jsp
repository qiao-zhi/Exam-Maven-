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
<title>添加考试</title>

<%@ include file="/public/cssJs.jsp"%>
    
    <!-- 树 -->
    <script src="<%=path %>/js/public/tree.js"></script>
    <link rel="stylesheet" href="../../css/public/tree.css">

    <!--上传图片一 -->
    <script src="<%=path %>/js/innerDepart/addEmployee.js"></script>

    <!--/*上传个人照片*/-->
    <link rel="stylesheet" href="<%=path %>/css/innerDepart/addEmployee.css">
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
        <div class="panel-body">

            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">添加员工信息</h4>
            </div>
            <form action="outdepartEmpManage.html">
                <div class="modal-body">

                    <!--头像一-->
                    <div class="input-group el_modellist" role="toolbar">
                        <span class="el_spans">个人照片：</span>
                        <div class="col-sm-9 big-photo">
                            <div id="preview">
                                <img id="imghead" border="0" src="../../image/yicun.jpg" width="75" height="105"
                                     onclick="$('#previewImg').click();">
                            </div>
                            <input type="file" onchange="previewImage(this)" style="display: none;" id="previewImg">
                        </div>
                    </div>

                    <div class="input-group el_modellist" role="toolbar">
                        <span class="el_spans">员工姓名：</span>
                        <input type="text" class="form-control el_modelinput" name=""/>
                    </div>

                    <div class="input-group el_modellist">
                        <span class="el_spans"> 员工性别：</span>
                        <div>
                            <label><input type="radio" name="el_gender" checked value="0"> 男</label>
                            <label><input type="radio" name="el_gender" value="1"> 女</label>
                        </div>
                    </div>

                    <div class="input-group el_modellist" role="toolbar">
                        <span class="el_spans">出生日期：</span>
                        <input type="text" id="test2" class="wicon form-control" readonly/>
                    </div>

                    <div class="input-group el_modellist" role="toolbar">
                        <span class="el_spans">联系方式：</span>
                        <input type="text" class="form-control el_modelinput" name=""/>
                    </div>

                    <div class="input-group el_modellist" role="toolbar">
                        <span class="el_spans">身&nbsp;&nbsp;份&nbsp;证：</span>
                        <input type="text" class="form-control el_modelinput" name=""/>
                    </div>

                    <div class="input-group el_modellist" role="toolbar">
                        <span class="el_spans el_chooseSpan">选择单位：</span>
                        <ul id="el_chooseDepart" class="el_modelinput el_chooseInput log"></ul>
                        <img src="../../controls/selectDropTree/smallTriangle.png" class="el_smallTriangle"
                             width="7"/>
                        <ul id="treeDemo10" class="ztree"></ul>
                    </div>

                    <div class="input-group el_modellist" role="toolbar">
                        <span class="el_spans">指纹信息：</span>
                        <input type="text" class="form-control el_modelinput" name="" placeholder="0"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" name="back" class="btn btn-default" value="返回" onclick="javascript:history.back(-1);"/>
                    <button type="submit" class="btn btn-primary">完成添加</button>
                </div>
            </form>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


    </div>
</div>

<!--放脚-->
<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>
