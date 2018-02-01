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
<meta name="renderer" content="webkit|ie-comp|ie-stand">

<title>在线考试个人中心</title>

<%@ include file="/public/cssJs.jsp"%>
	
   <!--分页-->
    <script src="<%=path %>/js/public/page.js"></script>
    	
    <!--提出来的样式-->
    <link rel="stylesheet" href="<%=path %>/css/lineExam/examInterface.css" />
    <!--提出来的页面JS文件-->
    <script type="text/javascript" src="<%=path %>/js/lineExam/examInterface.js"></script>
    
    <!-- 日期格式转换 -->
    <script src="${pageContext.request.contextPath }/js/questionLibrary/dateformat.js "></script>
     <!--验证-->
	<script src="${pageContext.request.contextPath }/controls/validate/jquery.validate.js"></script>
	<script src="${pageContext.request.contextPath }/controls/validate/messages_zh.js"></script>
    
    
    <script>
    	//定义一个全局变量，从域对象中获取
    	var employeeInIdCard = '${session.userinfo.useridcard}';
    	var employeeInId = '${session.userinfo.employeeid}';
    	var basePathUrl = "${pageContext.request.contextPath}";
    	var loginTime = '${session.userinfo.logintime}';    	
    </script>
</head>
<body>

<!--头-->
<jsp:include page="/view/lineExam/lineHeader.jsp"></jsp:include>

<div class="el_mainContent">
    <!--菜单-->
    <div class="el_leftMenu">

        <div class="el_Menu">
            <div class="el_MenuTitle">
                <span class="el_ico1"></span>
                <span>考试中心</span>
            </div>
            <div class="el_MenuContent">
                <a href="#home" aria-controls="profile" role="tab" data-toggle="tab">
                   	 当前考试
                </a>
            </div>
            <div class="el_MenuContent">
                <a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">
                    	所有考试
                </a>
            </div>
            <!-- <div class="el_MenuContent">
                <a href="#breakInfo" aria-controls="profile" role="tab" data-toggle="tab">
                    	个人违章信息管理
                </a>
            </div> -->
        </div>

        <div class="el_Menu">
            <div class="el_MenuTitle">
                <span class="el_ico2"></span>
                <span>个人中心</span>
            </div>
            <div class="el_MenuContent">
                <a href="#el_modifyPersonInfo" aria-controls="profile" role="tab" data-toggle="tab">
                    个人资料
                </a>
            </div>
            <div class="el_MenuContent">
                <a href="#messages" aria-controls="profile" role="tab" data-toggle="tab" onclick="clearOldInfo()">
                    修改密码
                </a>
            </div>
            <div class="el_MenuContent">
                <a href="#breakInfo" aria-controls="profile" role="tab" data-toggle="tab">
                    	个人违章信息管理
                </a>
            </div>
        </div>

    </div>

    <!--内容-->
    <div class="tab-content">
        <!--待参加的考试-->
        <div role="tabpanel" class="tab-pane active el_stayExam" id="home">

            <div class="panel-body">

            	
                <h4 class="el_smallTitle">
     			<img src="<%=path %>/image/img/clock.png"  width="19" style="position:relative; top:4px;"/>           
                	当前考试</h4>

                <!--使用提示-->
                <div class="el_examStandard">
                		<img src="<%=path %>/image/img/deng.png" width="13" style="position:relative; top:7px;right:5px;"/>
                		考试注意事项：
                    <span class="el_tipContent">1、开考时间到达后，<strong style="font-weight:bolder">【开始考试】</strong>按钮将解锁，请点击此按钮进入考试界面。</span>
                    <span class="el_tipContent">2、为了避免网络异常、断电等情况，请在考试界面中，点击<strong style="font-weight:bolder">【临时保存】</strong>按钮，及时保存您已填写的内容。</span>
                     <span class="el_tipContent">3、检查无误后，您可以点击<strong style="font-weight:bolder">【交卷】</strong>按钮结束本次考试。
                    	<strong class="text-danger">注意：交卷后将不能再次进入考试界面。</strong></span>
                     <span class="el_tipContent">4、为了保证您的考试顺利进行，建议使用 火狐（Firefox）或 谷歌（Chrome)浏览器进行答题。
                     <a href="<%=path%>/view/index/downloadPage.jsp">下载链接</a></span>
                </div>              
                <div class="col-md-12" id="showNotStartExamInfoList">
                   <div class="el_noExamTip">
                            <span>
                                暂无待参加的考试
                            </span>
                        <br/>
                        <br/>
                        <a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" id="el_queryOldExam">查看参加过的考试</a>
                    </div>             
                </div>
			
            </div>

        </div>

        
        <!--所有考试情况-->
        <div role="tabpanel" class="tab-pane el_stayExam" id="profile">
            <!--员工培训档案-->
            <div class="panel-body">

                <h4 class="el_smallTitle">所有考试</h4>

                <div class="row el_queryBox">
                    <form id="form_onlineExamEmployeeInfo">

                        <div class="row el_queryBoxrow">

                            <div class="el_qlmQuery">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">考试名称：</span>
                                    <input type="text" class="form-control" name="examName"/>
                                </div>
                            </div>

                            <div class="el_qlmQuery" style="margin-left: 80px;">
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

                            
                        </div>

                        <div class="row el_queryBoxrow">
                            <div class="el_qlmQuery" style="padding-top: 5px;">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">考试时间：</span>
                                    <input type="text" id="test" class="wicon el_noVlaue form-control" readonly name="examTime"/>
                                </div>
                            </div>
                            <div class="el_qlmQuery" style="margin-left: 80px;">
                                <div class="input-group" role="toolbar">
                                    <span class="el_spans">考试状态：</span>                                    
                                    <label class="el_radioBox" style="margin-right: 0px;">
                                    <input type="radio" name="examStatus" value="未开始"> 未开始</label>&nbsp;
                                    <label class="el_radioBox" style="margin-right: 0px;"><input type="radio" name="examStatus" value="已结束"> 已结束</label>
                              </div>
                            </div>
                        </div>
                                 	              
						<!-- 身份证号 -->
						<input type="hidden" name="idCard" value='${session.userinfo.useridcard}'/>
						<!-- 隐藏当前页和显示条数 -->
                        <input type="hidden" name="currentPage" id="currentPage" />
						<input type="hidden" name="currentCount" id="currentCount" />
                        <!--提交查询按钮-->
                        <button type="button" class="btn btn-primary el_queryButton btn-sm" onclick="queryOnlineExamInfo()">查询</button>
                    	<button type="reset" class="btn btn-default el_queryButton0 btn-sm">清空</button>
                    </form>
                </div>
                <table class="table  table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>考试名称</th>
                        <th>考试时间</th>
                        <th>考试级别</th>
                        <th>试卷总分</th>
                        <th>考试状态</th>
                        <th>获得成绩</th>
                        <th>是否通过</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="onlineExamInfoList">
                    
                    </tbody>
                </table>

                 <!--分页-->
                 <div id="examInterface_paginationIDU"></div>
            </div>
        </div>

		<!-- 个人违章记分管理 -->
 		<div role="tabpanel" class="tab-pane el_stayExam" id="breakInfo">
 			<!--员工培训档案-->
            <div class="panel-body">

                <h4 class="el_smallTitle">个人违章信息</h4>

                <div class="row el_queryBox">
                    <form id="form_onlineBreakInfo">                       
							<div class="col-md-6" id="el_breakTimeIndex">
								<div class="input-group" id="el_startEndTime" role="toolbar">
									<span class="el_spans">违章时间：</span> 
									<input type="text"
										class=" form-control query_dep_starttime" placeholder="开始时间" name="breakTimeLeft"
										id="inpstart2" readonly> 
									<input type="text"
										class="form-control query_dep_endtime" id="inpend2"  placeholder="结束时间"
										name="breakTimeRight" readonly>
								</div>
							</div>
							<!-- 隐藏员工ID -->
							<input type="hidden" name="employeeInId" value="${session.userinfo.employeeid}"  />
							<!-- 隐藏当前页和显示条数 -->
	                        <input type="hidden" name="currentPage" id="currentPage_break" />
							<input type="hidden" name="currentCount" id="currentCount_break" />
                       
	                        <!--提交查询按钮-->
	                        <button type="button" class="btn btn-primary el_queryButton btn-sm" onclick="selectPersonBreakInfos()">查询</button>
	                    	<button type="reset" class="btn btn-default el_queryButton0 btn-sm">清空</button>
                    
                    </form>
                </div>
                <table class="table table-hover table-bordered">
					<thead>
						<tr>
							<th>序号</th>
							<th>违章时间</th>
							<th>违章积分</th>
							<th>违章内容</th>
						</tr>
					</thead>
					<tbody id="breakRulesInfoList">						
					</tbody>
				</table>

				<!--分页 -->
				<div id="paginationID"></div>
                
            </div>
        </div>
        
        <!--修改个人资料-->
        <div role="tabpanel" class="tab-pane el_stayExam" id="el_modifyPersonInfo">
        <!--<div class="panel panel-default tab-pane el_stayExam" id="el_modifyPersonInfo" role="tabpanel">-->
            <div class="panel-body">

                <h4 class="el_smallTitle">个人信息</h4>

                <form id="form_employeeInInfo">
                    <div class="modal-body">

                        <!--头像一-->
                        <div class="input-group el_modellist" role="toolbar">
                            <span class="el_spans">个人照片：</span>
                            <div class="col-sm-9 big-photo">
                                <div id="preview">
                                    <!-- <img id="imghead" border="0" src="../../image/yicun.jpg" width="75" height="105"
                                         onclick="$('#previewImg').click();"> -->
                                    <img id="imghead" border="0"  width="100%" height="100%"/>
                                </div>
                                <input type="file" onchange="previewImage(this)" style="display: none;" id="previewImg">
                            </div>
                        </div>

                        <div class="input-group el_modellist" role="toolbar">
                            <span class="el_spans">员工姓名：</span>
                            <input type="text" class="form-control el_modelinput" id="employeeIn_name" disabled/>
                        </div>

                        <div class="input-group el_modellist">
                            <span class="el_spans"> 员工性别：</span>
                            <!-- <div>
                                <label><input type="radio" name="el_gender" id="employeeIn_sex1" disabled> 男</label>
                                <label><input type="radio" name="el_gender" id="employeeIn_sex2" disabled> 女</label>
                            </div> -->
                            <input type="text" class="form-control el_modelinput" id="employeeIn_sex" disabled/>
                        </div>

                        <div class="input-group el_modellist" role="toolbar">
                            <span class="el_spans">出生日期：</span>
                            <input type="text" class="wicon form-control el_noVlaue el_modelinput" id="employeeIn_birthday" disabled/>
                            <!-- <input type="text" id="test21" class="wicon form-control el_modelinput" readonly/> -->
                        </div>

                        <div class="input-group el_modellist" role="toolbar">
                            <span class="el_spans">联系电话：</span>
                            <input type="text" class="form-control el_modelinput" name="employeeIn.phone" id="employeeIn_phone"/>
                            <!-- 隐藏用户的ID -->
                            <input type="hidden" name="employeeIn.employeeid" class="employeeIn_employeeId"/>
                        </div>

                        <div class="input-group el_modellist" role="toolbar">
                            <span class="el_spans">身&nbsp;&nbsp;份&nbsp;证：</span>
                            <input type="text" class="form-control el_modelinput" id="employeeIn_IdCard" disabled/>
                        </div>

                       <!--  <div class="input-group el_modellist" role="toolbar">
                            <span class="el_spans">指纹信息：</span>
                            <input type="text" class="form-control el_modelinput" name="" placeholder="0"/>
                        </div> -->
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="saveEmployeeInfo()">&nbsp;&nbsp;保&nbsp;&nbsp;存&nbsp;&nbsp;</button>
                    </div>
                </form>

            </div><!-- /.modal-content -->
            <br/>
            <br/>
            <br/>
        </div>

        <!--修改密码-->
        <div role="tabpanel" class="tab-pane el_stayExam" id="messages">

            <div class="panel-body">
                <h4 class="el_smallTitle">修改密码</h4>

                <span class="el_tipContent">请修改密码后，确保不丢失。</span>
                <form id="form_changePassword" class="el_modifyPasswordForm">

                    <div class="el_modifyPassword">
                        <span>原密码：</span>
                        <input type="password" class="form-control" id="user_inputPassword" onblur="existPassword(this)" name="oldPassword">
                        <span id="isExistPassword"></span>
                        <!-- 隐藏用户的ID -->
                        <input type="hidden" class="employeeIn_employeeId" name="employeeInId"/>
                    </div>

                    <div class="el_modifyPassword">
                        <span>新密码：</span>
                        <input type="password" class="form-control" name="newPassword" id="user_newPassword" onkeyup="pwStrength(this.value)">
                        <ul class="pass_set">
                            <li id="strength_L">弱</li>
                            <li id="strength_M">中</li>
                            <li id="strength_H">强</li>                      
                        </ul>
                        <input type="hidden" id="user_oldPassword"/>
                    </div>
                    <div class="el_modifyPassword el_modifyPassword2">
                        <span>密码确认：</span>
                        <input type="password" class="form-control"  id="user_checkPassword" onblur="checkNewPassword(this)" name="checkPassword">
                        <span id="isCheckNewPassword"></span>
                    </div>

                    <hr/>
                    <div class="el_modifyPasswordTrueButton">
                        <input type="button" id="el_MPB" class="btn btn-info"
                               value="&nbsp;&nbsp;确&nbsp;&nbsp;认&nbsp;&nbsp; " onclick="changePassword()"/>
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>

<!--放脚-->
<jsp:include page="/view/lineExam/lineFooter.jsp"></jsp:include>
</body>
</html>
