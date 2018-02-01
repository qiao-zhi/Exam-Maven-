<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>员工违章详细信息管理</title>

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
function queryFy(resultCount,currentPage,currentTotal){
			//分页栏  start
		    $('#paginationIDU').pagination({
		    	 //			组件属性
		        "total":resultCount,//数字 当分页建立时设置记录的总数量 1
		        "pageSize":currentTotal ,//数字 每一页显示的数量 10
		        "pageNumber": currentPage,//数字 当分页建立时，显示的页数 1
		        "pageList": [10,20],//数组 用户可以修改每一页的大小，   
		        //功能
		        "layout": ['list', 'sep', 'first', 'prev', 'manual', 'next', 'last', 'links'],
		        "onSelectPage": function (pageNumber, b) {
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
		.table thead{
		background-color:#337ab7;
		color:white;
		font-weight:bolder;
	}
	#el_mT{
		font-size:13px;
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
						<span>外来单位管理</span><span>>违章管理</span><span>>员工违章详细信息</span>
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
											<%-- <img src="/file/${headaddress }" width="90" id=""> --%>
											<img src="${headaddress }" width="100%" height="100%" id="">
										</div>
										<div class="">
											<table id="el_mT">
												<tr>
													<td align="right">姓名:</td>
													<td style="padding-left: 35px;" id="topName">${name }</td>
												</tr>
												<tr>
													<td align="right">性别:</td>
													<td style="padding-left: 35px;" id="topSex">${sex}</td>
												</tr>
												<tr>
													<td align="right">身份证:</td>
													<td style="padding-left: 35px;" id="topIdCard">${idCard}</td>
												</tr>
												<%-- <tr>
													<td>联系电话</td>
													<td style="padding-left: 35px;" id="topPhone">${empOutphone}</td>
												</tr> --%>
												<input id="hmdzt" type="hidden" value="${breakSumScore }" />
												<tr>
													<td align="right">黑名单状态:</td>
													<td style="padding-left: 35px;" id="topBlackStatus">${blackStatus}</td>
												</tr>
												<tr>
													<td align="right">所属单位:</td>
													<td style="padding-left: 35px;" id="topUnitName">${unitName}</td>
												</tr>
												<tr>
													<td align="right">违章总积分:</td>
													<td style="padding-left: 35px;" id="topBreakScoreSum">${breakSumScore}</td>
												</tr>
											</table>
										</div>
									</div>

								</form>
							</div>

							<script type="text/javascript">
								/* if($("#topBlackStatus")=="1"){
									$("#topBlackStatus").text("男");
								}else{
									$("#topBlackStatus").text("女");
								} */
							
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
												<shiro:hasPermission name="outempbreak:operating">
												<th width="190">操作</th>
												</shiro:hasPermission>
											</tr>
										</thead>

										<tbody id="tbody">
											<c:forEach items="${breakruleList}" var="breakrules"
												varStatus="varSta">
												<tr>
													<td>${varSta.count }</td>
													<td><fmt:formatDate value="${breakrules.breaktime}"
															pattern="yyyy-MM-dd" /></td>
													<td>${breakrules.minusnum}</td>
													<td>${breakrules.breakcontent}</td>
													<!-- 隐藏域 违章id  -->
													<input type="hidden" value="${breakrules.breakid }" />
													<!-- 隐藏域 大修外来职工id -->
													<input type="hidden" value="${BigEmployeeoutId }" />
													<!-- 隐藏域 大修单位id -->
													<input type="hidden" value="${unitBigHual }" />
													<!-- 隐藏域 隐藏一个当前的违章记分 -->
													<input type="hidden" value="${breakrules.minusnum }" />
													<!-- 隐藏域，隐藏一个大修id -->
													<input type="hidden" value="${bigid }" />
													<!-- 隐藏域，隐藏一个职工id -->
													<input type="hidden" value="${breakrules.employeeid}" />
													<shiro:hasPermission name="outempbreak:operating">
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
								<div class="modal-dialog" data-backdrop="static" data-keyboard="false">
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
													<table class="table table-bordered">
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
															<td>${name }</td>
															<td id="uppsex">${sex }</td>
															<%-- <td >${empOutphone }</td>  --%>
															<td>${unitName }</td>
															<td id="upBreakScore"></td>
															<td id="upIsBreak">{isBreak}</td>
														</tr>
														</tbody>
													</table>
												</div>

												<!-- 隐藏域，隐藏 职工的违章id、职工id、大修外来职工id -->
												<!-- 职工的违章id 6-->
												<input id="upBreakId" type="hidden" value=""
													name="breakrules.breakid" />
												<!-- 职工id  6-->
												<input id="upEmployee" type="hidden" value=""
													name="breakrules.employeeid" />
												<!-- 大修外来职工id -->
												<input id="upBigHaulEmployeeId" type="hidden" value=""
													name="breakrules.bigemployeeoutid" />
												<!-- 隐藏域，隐藏单位大修编号 -->
												<input id="uppUnitBigHaulId" type="hidden"
													name="unitBigHaulId" value="${unitBigHual }" />
												<!-- 隐藏域  隐藏一个当前的(修改前的)违章记分 -->
												<input id="upQianBreakGrade" type="hidden"
													name="upQianBreakScore" />
												<!-- 隐藏域，隐藏一个大修id bigid  6-->
												<input id="upBigid" type="hidden" name="upBigid" value="" />

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">违章时间：</span> <input id="upBreakTime"
														type="text" id="test41" class="wicon form-control"
														name="breakrules.breaktime" readonly />
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">违章积分：</span>
													<!--不得超过12分-->
													<input id="upbreakGrade" type="text"
														class="form-control el_modelinput"
														name="breakrules.minusnum" />
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">违章情况：</span>
													<textarea id="upbreakContent"
														class="form-control el_modelinput" rows="3"
														name="breakrules.breakcontent"></textarea>
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
							<!-- <script type="text/javascript">
								if($("#uppsex")=="1"){
									$("#uppsex").text("男");
								}else{
									$("#uppsex").text("女");
								}
							
							</script> -->
							<!-- 模态框   信息删除确认 -->
							<div class="modal fade" id="delcfmModel">
								<div class="modal-dialog" data-backdrop="static" data-keyboard="false">
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

							<!-- 模态框  用于修改记分变化大于12分的情况 start-->
							<div class="modal fade" id="modifyAlertMsg">
								<div class="modal-dialog" data-backdrop="static" data-keyboard="false">
									<div class="modal-content message_align">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<h4 class="modal-title">提示</h4>
										</div>
										<div class="modal-body">
											<p>当前违章积分变化大于等于12，即将永久加入黑名单，是否继续修改？</p>
										</div>
										<div class="modal-footer">
											<input type="hidden" id="url" />
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<a onclick="modifyAlertMsgBtn()" class="btn btn-success"
												data-dismiss="modal">确定</a>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->
							<!-- 模态框  用于修改记分变化大于12分的情况 end-->

							<!-- 隐藏修改前的违章记分 -->
							<input id="upbreakgradeBefore" type="hidden" value="" />
							<!-- 隐藏域，隐藏是操作左边的树的还是顶部的分页条的 -->
							<input id="allMark" type="hidden" value="" />
							
							<!-- 点击修改按钮之后的操作  -->
							<script type="text/javascript">
								function modifyBreak(obj) {
									//alert("进入修改")
									//职工id 违章id  大修编号 bigid
									
									var breakTime = $(obj).parents("tr").children("td").eq(1).text();//违章时间
									var breakScore = $(obj).parents("tr").children("td").eq(2).text();//修改前违章记分
									var breakContent = $(obj).parents("tr").children("td").eq(3).text();//违章内容
									
									var breakId = $(obj).parents("tr").children("input").eq(0).val();//违章id
									var BigEmployeeoutId = $(obj).parents("tr").children("input").eq(1).val();//大修外来职工id
									var unitBigHual = $(obj).parents("tr").children("input").eq(2).val();//大修单位id
									
									var bigid = $(obj).parents("tr").children("input").eq(4).val();//大修单位id
									var employeeid = $(obj).parents("tr").children("input").eq(5).val();//职工id
									
									//为隐藏域 修改前的违章记分 赋值
									$("#upbreakgradeBefore").val(breakScore);
									
									//为隐藏域复制
									$("#upBreakId").val(breakId);//违章id  6
									$("#upEmployee").val(employeeid);//职工的id   6
									$("#upBigid").val(bigid);//大修id bigid  6
									
									$("#upBigHaulEmployeeId").val(BigEmployeeoutId);//大修外来职工id
									//单位大修编号
								
									
									//初始化表格数据			
									$("#upName").text($("#topName").text());//姓名
									$("#upPhone").text($("#topPhone").text());//联系电话
									$("#upbreakScore").text(breakScore);//违章记分
									$("#upIsBreak").text($("#topBlackStatus").text());//是否黑名单
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
 									if($("#topBlackStatus").text()=="是"){
 										alert("该员工已经列入黑名单,不允许修改!");	
 									}else{
 										//弹出模态框
 										$('#modifyBreak').modal();
 									}
									
								}
								
								//验证输入的违章记分是否为正整数的js代码
								$(function(){
									jQuery.validator.addMethod("isNumber", function(value,element) { 
										  var length = value.length; 
										  var tel = /(^\d+$)/;
										  return this.optional(element) || (tel.test(value)); 
										}, "请输入数字"); 
								})

								//点击修改按钮的提交更改按钮之后的操作，将数据提交到后台保存在数据库中
								function upSaveBtn() {
									
									//获取修改前的违章总记分
									var breakScoreBefore = $("#topBreakScoreSum").text();
									//获取修改后的违章记分
									var breakScoreAfter = $("#upbreakGrade").val();
									
									//获取修改前的违章总积分 用于当累计总积分》=12的时候给用户一个提示 start
									var upBreakScoreBeforeSum = $("#topBreakScoreSum").text();
									//alert("修改前违章总积分"+upBreakScoreBeforeSum)
									//获取本次修改前的违章记分
									var xgq = $("#upbreakgradeBefore").val();
									//alert("修改钱的违章记分"+xgq)
									//本次单次记分的变化  修改后的违章记分-修改前的违章记分
									var upBreakScoreChange =  parseInt(breakScoreAfter)- parseInt(xgq);
									//alert("单词记分变化"+upBreakScoreChange)
									//获取修改前的违章总积分 用于当累计总积分》=12的时候给用户一个提示 end
									
									if(breakScoreAfter>=12){
										//修改后的本次记分》=12分的时候，给出警告提示
										$("#modifyAlertMsg").modal();
									}else{
										if(parseInt(upBreakScoreBeforeSum)+parseInt(upBreakScoreChange)>=12){
											alert("该员工的违章记分累计超过12分");
										} 
										//修改后的本次记分<12,不用给出警告提示
										upSaveBtnAfter();
									} 
								}
								
								//警告提示的确认按钮的点击事件
								function modifyAlertMsgBtn(){
									upSaveBtnAfter();
								}
								
								
								//确认之后的后续操作
								function upSaveBtnAfter(){
									//表单校验
									var isNotNull = $("#upForm").validate({
										ignore : [],
											rules : {
												"breakrules.breaktime" : "required",//违章时间
												"breakrules.minusnum" : {//违章记分
													"required":true,
													"isNumber":true
												},
												"breakrules.breakcontent" : "required"//违章内容
											},
											messages : {
												"breakrules.breaktime" : {//违章时间
													required : "不能为空"
												},//下边与上边对应
												"breakrules.minusnum" : {//违章记分
													required : "不能为空",
													isNumber:"请输入一个数字"
												},
												"breakrules.breakcontent" : {
													required : "不能为空"
												}
											}

										});
									//表单校验结束						
									if (isNotNull.form()) {
										//upForm
										$.ajax({
											url : "${pageContext.request.contextPath}/breakrules_updateBreakrules.action",
											data : $("#upForm").serialize(),
											dataType : "json",
											type : "POST",
											async : true,
											success : function(data) {
												alert(data.result)
												//关闭模态框
												$('#modifyBreak').modal("hide");
												//跳转到
												
												//操作成功之后，刷新页面(当作数据的刷新)
												window.location.reload(); 
												/* if ($("#allMark").val() == "left") {
													alert("left")
													//执行左边的树的查询
													leftBtn();
												} else if ($("#allMark")
														.val() == "top") {
													alert("top")
													//执行顶部的分页的查询
													findSaveBtn();
												} */
											},
											error : function() {
												alert("修改失败，请从新操作")
												//关闭模态框
												$('#modifyBreak').modal("hide");
											}
										});//ajax的括号
										
									}
								}
							</script>

							<!-- 隐藏域  ，隐藏一个当前和删除按钮同一行的职工的违章id,用于删除操作 -->
							<input id="delEmployeeBreakId" type="hidden" value="" />
							<!-- 隐藏域，隐藏一个当前和删除按钮同一行的职工的职工id，用于删除操作 -->
							<input id="delEmployeeId" type="hidden" value="" />
							<!-- 隐藏大修id 用于删除操作的 -->
							<input id="delbigid" type="hidden" value="" />
							<!-- 隐藏域，隐藏一个当前和删除按钮同一行的职工的大修员工ID，用于删除操作 -->
							<input id="delBigEmployeeOutId" type="hidden" value="" />
							<!-- 和删除数据有关的操作 -->
							<script type="text/javascript">
								function delcfm(obj) {
									var breakId = $(obj).parents("tr").children("input").eq(0).val();//违章id
									var BigEmployeeoutId = $(obj).parents("tr").children("input").eq(1).val();//大修外来职工id
									var bigid = $(obj).parents("tr").children("input").eq(4).val();//大修单位id
									var employeeid = $(obj).parents("tr").children("input").eq(5).val();//职工id
									
									//alert("大修id"+bigid)
									
									$("#delEmployeeBreakId").val(breakId);//为隐藏域赋值 违章id
									$("#delbigid").val(bigid);//大修id bigid
									$("#delEmployeeId").val(employeeid);//职工id
									$("#delBigEmployeeOutId").val(BigEmployeeoutId);//大修员工ID
									if($("#topBlackStatus").text()=="是"){
										alert("该员工已经列入黑名单,不允许删除!")
									}else{
										//打开模态框
										$('#delcfmModel').modal();
									}
								}

								//点击确定按钮之后的操作，将该违章信息删除
								function urlSubmit() {
									
									 //alert("进入删除的确定按钮的事件:")
									//alert("单位编号:"+$("#delunitId").val())
									$.ajax({
											url:"${pageContext.request.contextPath}/breakrules_delBreakrules.action",
											data:{
												"delEmployeeId" : $("#delEmployeeId").val(),//职工id
												"delEmployeeBreakId" : $("#delEmployeeBreakId").val(),//违章id
												"delbigid":$("#delbigid").val(), //大修id
												"delBigEmployeeOutId":$("#delBigEmployeeOutId").val()
											},
											dataType : "json",
											type : "POST",
											async : true,
											success : function(data) {
												alert(data.result)
												///操作成功之后，刷新页面(当作数据的刷新)
												window.location.reload(); 
												

												//关闭模态框
												$('#delcfmModel').modal("hide");
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
