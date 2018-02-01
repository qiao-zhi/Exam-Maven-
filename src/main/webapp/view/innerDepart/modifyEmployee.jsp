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
<title>修改员工信息</title>

<%@ include file="/public/cssJs.jsp"%>

    <!-- 树 -->
    <script src="<%=path %>/js/innerDepart/modifyEmployee.js"></script>
   <%--  <script src="<%=path %>/js/public/tree.js"></script>--%>
    <link rel="stylesheet" href="<%=path %>/css/public/tree.css"> 

    <!--上传图片一 -->
    <script src="<%=path %>/js/innerDepart/addEmployee.js"></script>
 
    <!--/*上传个人照片*/-->
    <link rel="stylesheet" href="<%=path %>/css/innerDepart/addEmployee.css">
    
    <!-- 验证 -->
    <script src="<%=path %>/controls/validate/jquery.validate.js"></script>
    <script src="<%=path %>/controls/validate/messages_zh.js"></script>
   
   
   <!-- 样式 -->
   <style type="text/css">
	 em.success{
	  background:url("images/tips_arrow.gif") no-repeat left 0px;
	  padding-left:16px;
	  margin-left:2px;
	 }
	 em.error{
	  background:url("images/tips_arrow.gif") no-repeat left -51px;
	  display:inline;
	  padding-left:10px;
	  font-style:normal;
	  font-size:11px;
	  margin-left:2px;
	  font-family:12px/162% Arial, Helvetica, sans-serif;

 }
 #treeDemo9{
 display: none;
 border:none !important;
 position:relative;
 top:-5px;
 margin-left: 89px;
 width: 327px !important;
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
        <div class="panel-body">

            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">修改员工信息</h4>
            </div>
            <form id="updateEmployeeInForm" method="post" enctype="multipart/form-data" action="/Exam/employeein_updateEmployeeIn.action" onsubmit="return check(this);">
                <div class="modal-body">

                    <!--头像一-->
                    <div class="input-group el_modellist" role="toolbar">
                        <span class="el_spans">个人照片：</span>
                        <div class="col-sm-9 big-photo">
                            <div id="preview"
                            style="padding-left: 0px;
							   padding-bottom: 0px;
							   padding-top: 0px;
							   padding-right: 0px;">
                           <%--  ${pageContext.request.contextPath } --%>
                                <img id="imghead" border="0" src="/files/EmployeeIn/${result.employeeIn.photo }" width="91" height="121"
                                     onclick="$('#previewImg').click();"  >
                                     
                            </div>
                           <!--  <input type="file" style="display: none;" onchange="previewImage(this)" id="previewImg" name="photo"/> -->
                        </div>
                    </div>
                    

                    <div class="input-group el_modellist" role="toolbar">
                        <span class="el_spans">员工姓名：</span>
                        <input type="text" class="form-control el_modelinput" disabled onblur="checkName()" onfocus="yincang1()" name="employeeIn.name" id="updateEmployeeInName" value="${result.employeeIn.name }"/>
                   		<input type="hidden" name="employeeIn.employeeid" value="${result.employeeIn.employeeid }">
                   		
                    </div>
                    <div id="message1" style="display: block;margin-left: 99px; color:red;"></div>

                    <div class="input-group el_modellist">
                        <span class="el_spans"> 员工性别：</span>
                        <div>
                            <label><input type="radio" disabled name="employeeIn.sex" <c:if test="${result.employeeIn.sex eq'1'}">checked="checked"</c:if> value="1" > 男</label>
                            <label><input type="radio" disabled name="employeeIn.sex" <c:if test="${result.employeeIn.sex eq'2'}">checked="checked"</c:if> value="2"> 女</label>
                        </div>
                    </div>

                    <div class="input-group el_modellist" role="toolbar">
                        <span class="el_spans">出生日期：</span>
                        <input type="text" name="employeeIn.birthday" disabled onblur="checkBirthday()" onfocus="yincang2()" 
                        id="updateEmployeeInBirthday" class="form-control" 
                        value='<fmt:formatDate pattern='yyyy-MM-dd' value='${result.employeeIn.birthday}'/>'
                        />
                    </div>
                     <div id="message2" style="display: block;margin-left: 99px; color:red;"></div>
                     
                     <div class="input-group el_modellist" role="toolbar">
                        <span class="el_spans">身&nbsp;&nbsp;份&nbsp;证：</span>
                        <input type="text" class="form-control el_modelinput" disabled onblur="checkIdcode()" onfocus="yincang4()" name="employeeIn.idcode" id="updateEmployeeInIdcode" value="${result.employeeIn.idcode }"/>
                    </div>
                     <div id="message4" style="display: block;margin-left: 99px; color:red;"></div>
                     

                    <div class="input-group el_modellist" role="toolbar">
                        <span class="el_spans">联系方式：</span>
                        <input type="text" class="form-control el_modelinput" onblur="checkPhone()" onfocus="yincang3()" name="employeeIn.phone" id="updateEmployeeInPhone" value="${result.employeeIn.phone }"/>
                    	
                    </div>
                     <div id="message3" style="display: block;margin-left: 99px; color:red;"></div>

					<div class="input-group" role="toolbar">
                        <span class="el_spans" style="padding-left: 20px;">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：</span>
                        <select class="selectpicker form-control" title="请选择" name="employeeIn.duty" id="EmployeeInDuty"   style="width: 312px;">
                            
                        </select>
                        <input type="hidden" id="yincangzhiwu"  value="${result.employeeIn.duty }">
                    </div>
                    
                    <div class="input-group el_modellist" role="toolbar">
                        <span class="el_spans el_chooseSpan">选择部门：</span>
                        <input type="hidden"  id="updateEmployeeInDepartmentId" name="employeeIn.departmentid" value="${result.employeeIn.departmentid }"/>
                        <input type="hidden"  id="updateEmployeeInDepartmentName" value="${result.departmentName }"/>
                        <ul id="el_chooseDepart" class="el_modelinput el_chooseInput log" ></ul>
                        <img src="<%=path %>/controls/selectDropTree/smallTriangle.png" class="el_smallTriangle"
                             width="7"/>
                        <!-- <ul id="treeDemo9" class="ztree" style="display:none"></ul> -->
                    </div>
                    <div>
                    <div>
									
                       <ul id="treeDemo9" class="ztree"></ul>	
							
					</div>
                    </div>
                </div>
                <div class="modal-footer" style="width:45%">
                    <button type="submit" class="btn btn-primary">保存</button>
                    <input type="button" name="back" class="btn btn-default" value="返回" onclick="javascript:history.back(-1);"/>
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
