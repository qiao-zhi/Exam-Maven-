<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/public/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内部员工违章详细信息管理</title>

<%@ include file="/public/cssJs.jsp"%>

<link rel="stylesheet"
	href="<%=path%>/css/outDepart/BreakRulesInfoManage.css">

<link rel="stylesheet" href="<%=path%>/css/outDepart/outdepartTree.css">

<!--验证-->
<script
	src="${pageContext.request.contextPath }/controls/validate/jquery.validate.js"></script>
<script
	src="${pageContext.request.contextPath }/controls/validate/messages_zh.js"></script>
<script>
	/* 	$(function(){
	 queryFy(10,1,5);
	 }) */

	//新版的分页条(这个分页条是左边的树专用的啊)  start
	//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
	function queryFy(resultCount, currentPage, currentTotal) {
		//分页栏  start
		$('#paginationIDU').pagination(
				{
					//			组件属性
					"total" : resultCount,//数字 当分页建立时设置记录的总数量 1
					"pageSize" : currentTotal,//数字 每一页显示的数量 10
					"pageNumber" : currentPage,//数字 当分页建立时，显示的页数 1
					"pageList" : [ 8, 15, 20 ],//数组 用户可以修改每一页的大小，   
					//功能
					"layout" : [ 'list', 'sep', 'first', 'prev', 'manual',
							'next', 'last', 'links' ],
					"onSelectPage" : function(pageNumber, b) {
						//为两个隐藏域赋值  当前页页号   每页显示的记录数
						// alert("左侧的树的分页")
						$("#yeHao").val(pageNumber);//当前页页号
						$("#jiLuShu").val(b);//每页显示的记录数

						//直接调用分页查询的方法

						//执行左边的树的查询
						leftBtn();
					}
				});
	}
	//新版的分页条(最底部的那个) end
</script>
<style>
	#el_mT{
		font-size:13px;
	}
	.el_mainHead{
		margin-top:40px;
	}
	.table thead{
		background-color:#337ab7;
		color:white;
		font-weight:bolder;
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
					<div class="panel-heading">
						<span>内部单位管理</span><span>>违章管理</span><span>>内部员工违章详细信息</span>
					</div>
					<div class="el_main">

						<!--内容-->
						<div class="el_qlmContent" style="width: 100%;">
							<!-- 显示员工基本信息 -->
							<div>
								<h4 style="padding-top: 7px;" class="" id="" align="center">员工基本信息</h4>
								<br>
								<form>
									<div class="" style="width: 800px" align="center">

										<div class="input-group el_empPhoto">
											<!-- 这个现实图片的路径到时候改一下 -->
											<%-- <img src="/file/" width="90" id=""> --%>
											<img src="/files/EmployeeIn/${employeeIn.photo }"
												width="100%" height="100%" id="">
										</div>
										<div class="">
											<table id="el_mT">
												<tr>
													<td align="right">姓名:</td>
													<td style="padding-left: 35px;" id="topName">${employeeIn.name }</td>
												</tr>
												<tr>
													<td align="right">性别:</td>
													<td style="padding-left: 35px;" id="topSex">${employeeIn.sex}</td>
												</tr>
												<tr>
													<td align="right">身份证:</td>
													<td style="padding-left: 35px;" id="topIdCard">${employeeIn.idcode}</td>
												</tr>
												<input id="hmdzt" type="hidden" value="${sumBreakScore }" />
												<tr>
													<td align="right">黑名单状态:</td>
													<td style="padding-left: 35px;" id="topBlackStatus">${blackStatus}</td>
												</tr>
												<tr>
													<td align="right">所属单位:</td>
													<td style="padding-left: 35px;" id="topUnitName">${departmentName}</td>
												</tr>
												<tr>
													<td align="right">违章总积分:</td>
													<td style="padding-left: 35px;" id="topBreakScoreSum">${sumBreakScore}</td>
												</tr>
												
											</table>
											
										</div>
									</div>

								</form>
							</div>

							<script type="text/javascript">
								if ($("#topSex").text() == "1") {
									$("#topSex").text("男");
								} else if ($("#topSex").text() == "2") {
									$("#topSex").text("女");
								}
							</script>


							<!--显示员工违章信息-->
							<h3 class="el_mainHead">员工违章信息</h3>
							<div class="panel panel-default el_Mainmain">

								<!--按钮面板-->
								<div class="panel-body">

									<div class="panel panel-default">
										<div class="panel-body el_MainxiaoMain"></div>
									</div>

									<!--表格
                            内容都提取到json里边
                        -->
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>序号</th>
												<th>违章时间</th>
												<th>违章积分</th>
												<th>违章内容</th>

												<shiro:hasPermission name="breakrules:operating">
													<th width="190">操作</th>
												</shiro:hasPermission>
											</tr>
										</thead>

										<tbody id="tbody">
											<c:forEach items="${emplyinBreakrulesList}" var="breakrules"
												varStatus="varSta">
												<tr>
													<td>${varSta.count }</td>
													<td><fmt:formatDate
															value="${breakrules.empinbreaktime}" pattern="yyyy-MM-dd" /></td>
													<td>${breakrules.empinminusnum}</td>
													<td>${breakrules.empinbreakcontent}</td>
													<!-- 隐藏域 违章id  -->
													<input type="hidden" value="${breakrules.empinbreakid }" />
													<!-- 隐藏域 隐藏一个当前的违章记分 -->
													<input type="hidden" value="${breakrules.empinminusnum }" />
													<!-- 隐藏域，隐藏一个职工id -->
													<input type="hidden" value="${employeeid}" />
													<shiro:hasPermission name="breakrules:operating">
														<td><a href="javascript:void(0)"
															onclick="modifyBreak(this)">修改</a>&nbsp; <a
															class="el_delButton" onClick="delcfm(this)">删除</a> <br />
														</td>
													</shiro:hasPermission>
												</tr>
											</c:forEach>
										</tbody>
									</table>

									<!--分页  用于条件查询的-->
									<!-- <div id="paginationIDU"></div> -->


								</div>
							</div>

							<!-- 模态框 违章信息修改-->
							<div class="modal fade" id="modifyBreak" tabindex="-1" data-backdrop="static" data-keyboard="false"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel2">修改违章信息</h4>
										</div>
										<form id="upForm">
											<div class="modal-body">
												<span>员工信息：</span>
												<div class="el_threeScoreList">
													<table class="table table-bordered table-hover">
														<thead>
														<tr>
															<td>姓名</td>
															<td>性别</td>
															<!-- <td>联系方式</td>  -->
															<td>所属单位</td>
															<td>违章记分</td>
															<td>黑名单</td>
														</tr>
														</thead>
														<tbody>
														<tr>
															<td>${employeeIn.name }</td>
															<td id="upsex">${employeeIn.sex }</td>
															<%-- <td >${empOutphone }</td>  --%>
															<td>${departmentName }</td>
															<td id="upBreakScore">asdf</td>
															<td id="upIsBreak"></td>
														</tr>
														</tbody>
													</table>
												</div>

												<!-- 隐藏域，隐藏 职工的违章id、职工id、大修外来职工id -->
												<!-- 职工的违章id 6-->
												<input id="upBreakId" type="hidden" value=""
													name="emplyinBreakrules.empinbreakid" />
												<!-- 职工id  6-->
												<input id="upEmployee" type="hidden" value=""
													name="emplyinBreakrules.empinemployeeid" />
													
												<!-- 隐藏当前员工的部门类型 -->
												<input type="hidden" id="employeeDepartmentType" name="employeeDepartmentType" value="${employeeDepartmentType}"></input>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">违章时间：</span> <input id="upBreakTime"
														type="text" id="test41" class="wicon form-control"
														name="emplyinBreakrules.empinbreaktime" readonly />
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">违章积分：</span>
													<!--不得超过12分-->
													<input id="upbreakGrade" type="text"
														class="form-control el_modelinput"
														name="emplyinBreakrules.empinminusnum" />
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">违章情况：</span>
													<textarea id="upbreakContent"
														class="form-control el_modelinput" rows="3"
														name="emplyinBreakrules.empinbreakcontent"></textarea>
												</div>

											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" onclick="upSaveBtn()"
													class="btn btn-primary">提交更改</button>
											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>
							<script type="text/javascript">
								if ($("#upsex").text() == "1") {
									$("#upsex").text("男");
								} else if ($("#upsex").text() == "2") {
									$("#upsex").text("女");
								}
							</script>
							<!-- 模态框   信息删除确认 -->
							<div class="modal fade" id="delcfmModel" data-backdrop="static" data-keyboard="false">
								<div class="modal-dialog">
									<div class="modal-content message_align">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<h4 class="modal-title">提示</h4>
										</div>
										<div class="modal-body">
											<p>您确认要删除该条信息吗？</p>
										</div>
										<div class="modal-footer">
											<input type="hidden" id="url" />
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<a onclick="urlSubmit()" class="btn btn-success"
												data-dismiss="modal">确定</a>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->


							<!-- 模态框，用于提示修改的记分变化>=12分的情况 -->
							<div class="modal fade" id="modifyAlertModel2" data-backdrop="static" data-keyboard="false">
								<div class="modal-dialog">
									<div class="modal-content message_align">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<h4 class="modal-title">提示</h4>
										</div>
										<div class="modal-body">
											<p>本次修改的积分变化大于等于12分，该员工将被永久加入黑名单，是否继续？</p>
										</div>
										<div class="modal-footer">
											<input type="hidden" id="url" />
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<a onclick="modifyAlertModelBtn()" class="btn btn-success"
												data-dismiss="modal">确定</a>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->


							<!-- 隐藏域  隐藏修改前的记分 -->
							<input id="upBreakScoreBefore" type="hidden" value="" />
							<!-- 隐藏域，隐藏是操作左边的树的还是顶部的分页条的 -->
							<input id="allMark" type="hidden" value="" />
							<!-- 点击修改按钮之后的操作  -->
							<script type="text/javascript">
								function modifyBreak(obj) {
									//alert("进入修改")
									//职工id 违章id  大修编号 bigid

									var breakTime = $(obj).parents("tr")
											.children("td").eq(1).text();//违章时间
									var breakScore = $(obj).parents("tr")
											.children("td").eq(2).text();//修改前违章记分
									var breakContent = $(obj).parents("tr")
											.children("td").eq(3).text();//违章内容

									var breakId = $(obj).parents("tr")
											.children("input").eq(0).val();//违章id

									var employeeid = $(obj).parents("tr")
											.children("input").eq(2).val();//职工id
											//为用于查询今年该员工的总违章记分的员工id隐藏域赋值
											$("#empid").val(employeeid);
									//alert("违章id"+breakId + " 职工id"+employeeid)

									//为修改前的违章记分 的隐藏域赋值
									$("#upBreakScoreBefore").val(breakScore);

									//为隐藏域复制
									$("#upBreakId").val(breakId);//违章id  
									$("#upEmployee").val(employeeid);//职工的id  

									//单位大修编号

									//初始化表格数据			
									$("#upName").text($("#topName").text());//姓名
									$("#upPhone").text($("#topPhone").text());//联系电话
									if ($("#hmdzt").val() >= 12) {
										$("#upIsBreak").text("是");
									} else {
										$("#upIsBreak").text("否");
									}
									$("#upBreakScore").text(breakScore);//违章记分
									//修改前的违章记分
									$("#upQianBreakGrade").val(breakScore);

									//初始化表单数据
									//违章时间
									$("#upBreakTime").val(breakTime);
									//违章记分
									$("#upbreakGrade").val(breakScore);
									//违章情况
									$("#upbreakContent").text(breakContent);
									if ($("#topBlackStatus").text() == "是") {
										alert("该员工已经列入黑名单,不允许修改!")
									} else {
										//弹出模态框
										$('#modifyBreak').modal();
									}

								}

								//用于验证输入的记分是否为整数的
								$(function() {
									jQuery.validator.addMethod("isNumber",
											function(value, element) {
												var length = value.length;
												var tel = /(^\d+$)/;
												return this.optional(element)
														|| (tel.test(value));
											}, "请输入数字");
								})

								//点击修改按钮的提交更改按钮之后的操作，将数据提交到后台保存在数据库中
								function upSaveBtn() {
									//获取修改前的记分
									var beforeBreakScore = $(
											"#upBreakScoreBefore").val();
									//获取修改后的记分
									var nowBrekScore = $("#upbreakGrade").val();

									//修改前的违章总积分 用于累计总积分》=12的时候用于提示的 start
									var beforeUpBreakScoreSum = $(
											"#topBreakScoreSum").text();
									//alert("修改前违章总积分:"+beforeUpBreakScoreSum)
									//记分变化
									var changeScore = parseInt(nowBrekScore)
											- parseInt(beforeBreakScore);
									//alert("记分变化"+changeScore)
									//修改前的违章总积分 用于累计总积分》=12的时候用于提示的 end
									
									//获取隐藏域中当前员工的部门类型
									var employeeDepartmentType = $("#employeeDepartmentType").val();
									
									//如果修改前违章记分>=12，则不允许修改
									if (beforeBreakScore >= 12) {
										//不允许修改
									} else {
										//修改前违章记分<12，才允许修改
										//当前记分大于等于12，就弹出警告模态框 且部门类型为长委单位
										if ((nowBrekScore) >= 12 && employeeDepartmentType==1) {
											//当本次记分》=12分，弹出警告模态框
											$("#modifyAlertModel2").modal();
										} else {
											//如果修改后累计积分>=12，给用户一个了几记分》=12分的提示
											if ((parseInt(beforeUpBreakScoreSum) + parseInt(changeScore)) >= 12) {
												alert("该员工的违章记分累计超过12分");
											}

											//当本次积分《12分，不弹出警告框
											upSaveBtnAfter2();
										}
									}

								}

								//点击了警告提示的 确定按钮之后的事件
								function modifyAlertModelBtn() {
									//让其继续进行修改操作
									upSaveBtnAfter2();
								}

								//点击修改按钮之后的后续操作
								function upSaveBtnAfter2() {
									//表单校验
									var isNotNull = $("#upForm")
											.validate(
													{
														ignore : [],
														rules : {
															"emplyinBreakrules.empinbreaktime" : "required",//违章时间
															"emplyinBreakrules.empinminusnum" : {//违章记分
																"required" : true,
																"isNumber" : true
															},
															"emplyinBreakrules.empinbreakcontent" : "required"//违章内容
														},
														messages : {
															"emplyinBreakrules.empinbreaktime" : {//违章时间
																required : "不能为空"
															},//下边与上边对应
															"emplyinBreakrules.empinminusnum" : {//违章记分
																required : "不能为空",
																isNumber : "请输入一个数字"
															},
															"emplyinBreakrules.empinbreakcontent" : {
																required : "不能为空"
															}
														}
													});
									//表单校验结束						
									if (isNotNull.form()) {
										$
												.ajax({
													url : "${pageContext.request.contextPath}/empInbreakrules_updateEmpInBreakrules.action",
													data : $("#upForm")
															.serialize(),
													dataType : "json",
													type : "POST",
													async : true,
													success : function(data) {
														alert(data.result)
														//关闭模态框
														$('#modifyBreak')
																.modal("hide");
														//跳转到

														//操作成功之后，刷新页面(当作数据的刷新)
														window.location
																.reload();

													},
													error : function() {
														alert("修改失败，请从新操作")
														//关闭模态框
														$('#modifyBreak')
																.modal("hide");
													}
												});//ajax的括号

									}
								}
							</script>



							<!-- 隐藏域，隐藏员工id，用于查询该员工今年的违章总积分 -->
							<input id="empid" type="hidden" value="">
							
							<script type="text/javascript">
								/**
								 * 给出"正式员工积分12分为厂级下岗，8分为部门内部下岗，"的提醒 的方法
								 */
								function addAlertMsg(){
									//$("#addEmpID").val(empId);//当天添加记分的员工id
									$.ajax({
										url:"${pageContext.request.contextPath}/empInbreakrules_getSingleEmplyInBreakScoreSum.action",
										data:{"employeeid":$("#empid").val()},
										dataType:"json",
										type:"POST",
										async:true,
										success:function(data){
											//给出提示
											if((data.result>=8) && (data.result<12)){
												alert("部门内部下岗");
											}else if(data.result>=12){
												alert("厂级下岗");
											}
										},
									});
								}
							</script>
							
							
							<!-- 以下是删除的操作 -->
							<!-- 隐藏域  ，隐藏一个当前和删除按钮同一行的职工的违章id,用于删除操作 -->
							<input id="delEmployeeBreakId" type="hidden" value="" />
							<!-- 隐藏域，隐藏一个当前和删除按钮同一行的职工的职工id，用于删除操作 -->
							<input id="delEmployeeId" type="hidden" value="" />
							<!-- 和删除数据有关的操作 -->
							<script type="text/javascript">
								function delcfm(obj) {
									var breakId = $(obj).parents("tr")
											.children("input").eq(0).val();//违章id
									var employeeid = $(obj).parents("tr")
											.children("input").eq(2).val();//职工id

											
									//为用于查询今年该员工的总违章记分的员工id隐藏域赋值
									$("#empid").val(employeeid);
									$("#delEmployeeId").val(employeeid);//职工id
									$("#delEmployeeBreakId").val(breakId);//为隐藏域赋值 违章id
									if ($("#topBlackStatus").text() == "是") {
										alert("该员工已经列入黑名单,不允许删除!")
									} else {
										//打开模态框
										$('#delcfmModel').modal();
									}

								}

								//点击确定按钮之后的操作，将该违章信息删除
								function urlSubmit() {

									//alert("进入确定按钮的事件:")
									//alert("单位编号:"+$("#delunitId").val())
									$
											.ajax({
												url : "${pageContext.request.contextPath}/empInbreakrules_delEmpInBreakrules.action",
												data : {
													"delEmployeeId" : $(
															"#delEmployeeId")
															.val(),//职工id
													"delEmployeeBreakId" : $(
															"#delEmployeeBreakId")
															.val(),//违章id
												},
												dataType : "json",
												type : "POST",
												async : true,
												success : function(data) {
													//alert("进入回掉函数")
													alert(data.result)
													///操作成功之后，刷新页面(当作数据的刷新)
													window.location.reload();

													//关闭模态框
													$('#delcfmModel').modal(
															"hide");
												},
												error : function() {
													alert("删除失败，请重新新操作");
												}
											});
								}
							</script>
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
