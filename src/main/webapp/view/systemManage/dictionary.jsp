<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/public/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>字典管理</title>
<%@ include file="/public/cssJs.jsp"%>

<!--分页-->
<%--  <script src="<%=path %>/js/public/page.js"></script> --%>

<!--字典左侧树 & 修改模态框中的上级字典-->
<%-- <script src="<%=path %>/js/system/treeSys.js"></script> --%>

<!--表单验证-->
<script src="<%=path%>/controls/validate/jquery.validate.js"></script>
<script src="<%=path%>/controls/validate/messages_zh.js"></script>

<script src="<%=path%>/js/system/dictionary.js"></script>
<!-- 修改的 下拉树 -->
<script src="<%=path%>/js/system/dictionaryTree.js"></script>

<link rel="stylesheet" href="<%=path%>/css/systemManage/dictionary.css">
<link rel="stylesheet" href="<%=path%>/css/outDepart/outdepartTree.css">
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
						<span>系统管理</span><span>>字典管理</span>
					</div>

					<div class="panel-body el_main">
						<!--树    左侧的树-->
						<div class="el_leftTree">
							<!--标题类，添加了一个颜色-->
							<span class="el_treeTitle">字典</span>
							<ul id="treeDemo5" class="ztree"></ul>
							<!-- 左侧的字典树 -->
							<!-- 隐藏域  隐藏当前选中的字典树的值 -->
							<input id="dicTree" type="hidden" />
							<!-- 隐藏域  隐藏当前选中的字典树的等级 -->
							<input id="dicTreeLevel" type="hidden" />
						</div>
						<!-- <script type="text/javascript">
				alert("隐藏域中的值"+$("#dicTree").val())
			</script> -->
						<!--内容-->
						<div class="el_qlmContent">

							<!--索引-->
							<div class="row el_queryBox">
								<form id="findForm">
									<div class="col-md-3 el_qlmQuery">
										<div class="input-group" role="toolbar">
											<span class="el_spans">字典名称：</span> <input id="zdmc"
												type="text" class="form-control"
												name="dictionary.dictionaryname" />
										</div>
									</div>

									<div class="col-md-3 el_qlmQuery el_qlmQueryR">
										<div class="input-group" role="toolbar">
											<span class="el_spans">字典状态：</span> <label
												class="el_radioBox"><input type="radio" value="启用"
												name="dictionary.isuse"> 启用</label>&nbsp; <label
												class="el_radioBox"><input type="radio" value="禁用"
												name="dictionary.isuse"> 禁用</label>
										</div>
									</div>
									<!-- 隐藏域 隐藏当前页页号 -->
									<input id="yeHao" type="hidden" name="currentPage" value="1" />
									<!-- 隐藏域  隐藏每页显示的记录数 -->
									<input id="jiLuShu" type="hidden" name="currentTotal" value="8" />

									<!-- 清空按钮 -->
									<button id="clearBtn" type="reset"
										class="btn btn-default el_queryButton0 btn-sm">清空</button>
									<!--提交查询按钮-->
									<button type="button" onclick="btnFindFy()"
										class="btn btn-primary el_queryButton btn-sm">查询</button>
								</form>

								<!-- 点击清空 按钮发生的事件 -->
								<script type="text/javascript">
									$("#clearBtn").click(function() {
										//点击清空按钮之后应该把 当前页页号  每页显示的记录数 恢复到初始的值
										$("#yeHao").val("1");
										$("#jiLuShu").val("8");
									});
								</script>


								<!-- 查询按钮的事件 start -->
								<script type="text/javascript">
									function btnFindFy() {
										$("#yeHao").val("1");
										//alert("进入按钮的点击事件")
										$
												.ajax({
													url : "${pageContext.request.contextPath}/dic_selectDictionaryByFY.action",
													data : $("#findForm")
															.serialize(),
													dataType : "json",
													type : "POST",
													async : true,
													success : function(data) {
														//数据显示之前 要先清空表格中的所有数据
														$("#tBody tr").remove();
														var tdStr = "";
														// 当前页
														var totalCount = data.sumCount;
														var currentPage = data.curPage;
														var currentCount = data.curCount;

														for (var i = 0; data.dictionaryList != null
																&& i < data.dictionaryList.length; i++) {
															var index = (currentPage - 1)
																	* currentCount
																	+ i + 1;
															//字典id  dictionaryId
															var dictionaryid = data.dictionaryList[i].dictionaryid;
															//字典名称  dictionaryName
															var dictionaryname = data.dictionaryList[i].dictionaryname;
															//上级字典id upDictionaryId
															var updictionaryid = data.dictionaryList[i].updictionaryid;
															//是否可用 isUse
															var isuse = data.dictionaryList[i].isuse;
															//描述 discription
															var discription = data.dictionaryList[i].discription;
															//alert(i)
															//
															tdStr += "<tr>";
															//按照DOM规则动态的添加到表格中
															//隐藏域 隐藏字典的主键id
															tdStr += "<input type='hidden' name='documentId' value="+dictionaryid+" class='docID'/>";
															tdStr += "<td><input type='checkbox' class='el_checks'/></td>";
															tdStr += "<td>"
																	+ index
																	+ "</td>";//序号
															tdStr += "<td>"
																	+ dictionaryname
																	+ "</td>";//字典名称
															tdStr += "<td>"
																	+ isuse
																	+ "</td>";
															tdStr += "<td>"
																	+ discription
																	+ "</td>";
															//
															tdStr += "<td>";
															tdStr += "<a href='javascript:void(0)' onclick='modifyUserInfo(this)' >修改</a>"
															tdStr += "<a href='javascript:void(0)' class='el_delButton' onClick='delcfm(this)'>删除</a>";
															tdStr += "</td>";
															//
															tdStr += "</tr>";
														}
														//将结果拼成标签后按照DOM规则动态的添加到tbody标签下，作为其子节点
														$("#tBody").append(
																tdStr);
														queryFy(data.sumCount,
																data.curPage,
																data.curCount);
													},
													error : function() {
														alert("查询失败，请重新操作")
													}
												});

									}
								</script>
								<!-- 查询按钮的事件   end -->
							</div>
							<!--结束 查询表单提交-->

							<!--显示内容-->
							<h4 class="el_mainHead">字典管理</h4>
							<div class="panel panel-default el_Mainmain">

								<div class="panel-body">
									<input id="menu400Name" type="hidden" />
									<!--按钮面板-->
									<div class="panel panel-default">
										<div class="panel-body el_MainxiaoMain">

											<div class="el_topButton" id="dynamicBtn">
												<button class="btn btn-primary" onclick="el_addDictinary()">
													添加字典</button>

												<button type="button" class="btn btn-primary"
													onclick="piliangdelcfm()">批量删除</button>
											</div>
										</div>
									</div>

									<!--表格
                            内容都提取到json里边
                        -->
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th><input type="checkbox" id="el_checkQuanxuan" /></th>
												<th>序号</th>
												<th>字典名称</th>
												<th>字典状态</th>
												<th>描述</th>
												<th width="150">操作</th>
											</tr>
										</thead>
										<tbody id="tBody">
										</tbody>
									</table>


									<div id="paginationIDU" class="paginationID"></div>
									<script type="text/javascript">
										//最新的分页 start
										//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
										function queryFy(resultCount,
												currentPage, currentTotal) {
											$('#paginationIDU')
													.pagination(
															{
																//			组件属性
																"total" : resultCount,//数字 当分页建立时设置记录的总数量 1
																"pageSize" : currentTotal,//数字 每一页显示的数量 10
																"pageNumber" : parseInt(currentPage),//数字 当分页建立时，显示的页数 1
																"pageList" : [
																		8, 15,
																		20 ],//数组 用户可以修改每一页的大小，
																//功能
																"layout" : [
																		'list',
																		'sep',
																		'first',
																		'prev',
																		'manual',
																		'next',
																		'last',
																		'links' ],
																"onSelectPage" : function(
																		pageNumber,
																		b) {
																	//为两个隐藏域赋值  当前页页号   每页显示的记录数
																	$("#yeHao")
																			.val(
																					pageNumber);
																	$(
																			"#jiLuShu")
																			.val(
																					b);
																	//分页查询 关键点(如果不加上下面一行代码的话，导航栏中分页的 当前页页号、每页显示的记录数 只改变这两个就不会进行换页显示数据了)
																	btnFindFy();
																}
															});
										}
										//最新的分页  end
									</script>
								</div>
							</div>

							<!-- 模态框 添加字典-->
							<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel2">添加字典</h4>
										</div>
										<form id="addForm">
											<div class="modal-body">

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">上级字典：</span>
													<!-- 得将用户选中的那个字典树的名称，赋值给 该标签中 -->
													<!--  <input type="text" class="form-control el_modelinput el_chooseInput"
                                               id="addDefaultDepart" name="upDicName" disabled/> -->
													<input type="text"
														class="form-control el_modelinput el_chooseInput"
														id="addUpDicName" name="upDicName" readonly="readonly" />
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">字典名称：</span> <input type="text"
														class="form-control" name="dictionary.dictionaryname" />
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">字典状态：</span> <label
														class="el_radioBox"><input type="radio" value="启用"
														name="dictionary.isuse" checked>启用</label>&nbsp; <label
														class="el_radioBox"><input type="radio" value="禁用"
														name="dictionary.isuse"> 禁用</label>
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">字典描述：</span>
													<textarea class="form-control el_modelinput" rows="2"
														name="dictionary.discription"></textarea>
												</div>

											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button onclick="addBtnSave()" type="button"
													class="btn btn-primary">保存</button>
											</div>
										</form>
										<!-- 保存按钮的点击事件 -->
										<script type="text/javascript">
											function addBtnSave() {
												var isNotNull = $("#addForm")
														.validate(
																{
																	ignore : [],
																	rules : {
																		"dictionary.dictionaryname" : "required"//字典名称        验证文本框的。前边是 name 属性
																	},
																	messages : {
																		"dictionary.dictionaryname" : {//字典名称
																			required : "不能为空"
																		},//下边与上边对应
																	}
																});
												//以上是表单校验的操作
												//以下是添加字典的保存操作
												if (isNotNull.form()) {
													//alert("进入添加字典的保存事件  addBtnSave()");
													$
															.ajax({
																url : "${pageContext.request.contextPath}/dic_addDictionary.action",
																data : $(
																		"#addForm")
																		.serialize(),
																dataType : "json",
																type : "POST",
																success : function(
																		data) {
																	$(
																			'#myModal')
																			.modal(
																					'hide');

																	//添加成功之后重新刷新页面
																	//当前页页号   以下的代码是从showAllDicmsgByDicName方法中复制过来的 用于刷新数据的 **********
																	$
																			.ajax({
																				url : "${pageContext.request.contextPath}/dic_getDownDicMsgByDicName.action",
																				data : {
																					"dicName" : $(
																							"#addUpDicName")
																							.val(),
																					"curPage" : $(
																							"#yeHao")
																							.val(),
																					"perCount" : $(
																							"#jiLuShu")
																							.val()
																				},
																				dataType : "json",
																				type : "POST",
																				async : true,
																				success : function(
																						data) {
																					//alert("进入回掉函数")
																					//数据显示之前 要先清空表格中的所有数据
																					$(
																							"#tBody tr")
																							.remove();
																					// 当前页
																					var totalCount = data.sumCount;
																					var currentPage = data.curPage;
																					var currentCount = data.curCount;
																					var tdStr = "";
																					for (var i = 0; i < data.dictionaryList.length; i++) {
																						var index = (currentPage - 1)
																								* currentCount
																								+ i
																								+ 1;
																						//字典id  dictionaryId
																						var dictionaryid = data.dictionaryList[i].dictionaryid;
																						//字典名称  dictionaryName
																						var dictionaryname = data.dictionaryList[i].dictionaryname;
																						//上级字典id upDictionaryId
																						var updictionaryid = data.dictionaryList[i].updictionaryid;
																						//是否可用 isUse
																						var isuse = data.dictionaryList[i].isuse;
																						//描述 discription
																						var discription = data.dictionaryList[i].discription;
																						//
																						tdStr += "<tr>";
																						//按照DOM规则动态的添加到表格中
																						//隐藏域 隐藏字典的主键id
																						tdStr += "<input type='hidden' name='documentId' value="+dictionaryid+" class='docID'/>";
																						tdStr += "<td><input type='checkbox' class='el_checks'/></td>";
																						tdStr += "<td>"
																								+ index
																								+ "</td>";//序号
																						tdStr += "<td>"
																								+ dictionaryname
																								+ "</td>";//字典名称
																						tdStr += "<td>"
																								+ isuse
																								+ "</td>";//字典状态(是否可用)
																						tdStr += "<td>"
																								+ discription
																								+ "</td>";//字典描述
																						//
																						tdStr += "<td>";
																						tdStr += "<a href='javascript:void(0)' onclick='modifyUserInfoTree(this)' >修改</a>"
																						tdStr += "<a href='javascript:void(0)' class='el_delButton' onClick='delcfmTree(this)'>删除</a>";
																						tdStr += "</td>";
																						//
																						tdStr += "</tr>";
																					}
																					//将结果拼成标签后按照DOM规则动态的添加到tbody标签下，作为其子节点
																					$(
																							"#tBody")
																							.append(
																									tdStr);
																					window.location
																							.reload(true);

																					//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
																					queryFy2(
																							data.sumCount,
																							data.curPage,
																							data.perCount);
																					//回显的结果
																					//alert(data.result);
																				},
																				error : function() {
																					alert("查询失败，数据库中没有该数据");
																				}
																			});//小ajax的括号	
																	//以上的代码是从showAllDicmsgByDicName方法中复制过来的用于刷新数据的***************
																},
																error : function() {
																	alert("添加操作失败，请重新操作")
																	$(
																			'#myModal')
																			.modal(
																					'hide');
																}
															});//ajax的括号
												}//if的括号
											}//方法的括号
										</script>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>




							<!-- 模态框 修改字典信息-->
							<div class="modal fade" id="modifyUserInfo" tabindex="-1" data-backdrop="static" data-keyboard="false"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel24">修改字典信息</h4>
										</div>
										<form id="upForm">
											<div class="modal-body">
												<!-- 隐藏一个字典id  -->
												<input id="updateDicId" type="hidden"
													name="dictionary.dictionaryid">

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">上级字典：</span> <input
														id="updateUpDicName" type="text" readonly="readonly"
														class="form-control" value="资料类型" name="upDicName" />
													<!--  <ul id="el_chooseDepart" class="el_modelinput el_chooseInput log"></ul>
                                        <img src="../../controls/selectDropTree/smallTriangle.png" class="el_smallTriangle"
                                             width="7"/>
                                        <ul id="treeDemo10" class="ztree"></ul> -->
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">字典名称：</span> <input id="dicNameUp"
														type="text" class="form-control"
														name="dictionary.dictionaryname" />
												</div>

												<div class="input-group el_modellist" role="toolbar"
													id="updateDicDiv">
													<span class="el_spans">字典状态：</span> <label
														class="el_radioBoxUp"><input type="radio"
														value="启用" name="dictionary.isuse"> 启用</label>&nbsp; <label
														class="el_radioBoxUp"><input type="radio"
														value="禁用" name="dictionary.isuse"> 禁用</label>
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">字典描述：</span>
													<textarea id="dicDescUp" class="form-control el_modelinput"
														rows="2" name="dictionary.discription"></textarea>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" onclick="upBtnSave()"
													class="btn btn-primary">保存</button>
											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>

							<!-- 模态框 修改字典信息  有下拉树-->
							<div class="modal fade" id="modifyUserInfo2" tabindex="-1" data-backdrop="static" data-keyboard="false"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel24">修改字典信息</h4>
										</div>
										<form id="upForm2">
											<div class="modal-body">
												<!-- 隐藏一个字典id  -->
												<input id="updateDicId2" type="hidden"
													name="dictionary.dictionaryid">

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">上级字典：</span> <input
														id="updateUpDicName2" type="text" readonly="readonly"
														class="form-control" value="资料类型" name="upDicName" />
												</div>


												<div class="input-group el_modellist inShow" role="toolbar">
													<span class="el_spans el_chooseSpan">字典名称：</span> <input
														type="text" id="dicNameUp2" class="form-control"
														name="dictionary.dictionaryname" /> <img
														src="${baseurl }/controls/selectDropTree/smallTriangle.png"
														class="el_smallTriangle" width="7" />
													<ul id="treeDemo10" class="ztree form-control"
														style="display: none"></ul>

												</div>

												<div class="input-group el_modellist" role="toolbar"
													id="updateDicDiv">
													<span class="el_spans">字典状态：</span> <label
														class="el_radioBoxUp2"><input type="radio"
														value="启用" name="dictionary.isuse"> 启用</label>&nbsp; <label
														class="el_radioBoxUp2"><input type="radio"
														value="禁用" name="dictionary.isuse"> 禁用</label>
												</div>

												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">字典描述：</span>
													<textarea id="dicDescUp2" readonly
														class="form-control el_modelinput" rows="2"
														name="dictionary.discription"></textarea>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" onclick="upBtnSave2()"
													class="btn btn-primary">保存</button>
											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>

							<!-- 修改按钮的点击事件  -->
							<script type="text/javascript">
								function modifyUserInfo(obj) {
									var dictionaryId = $(obj).parent().parent()
											.children("input").val();
									//alert(dictionaryId)
									//为修改模态框中 隐藏的字典id的input标签赋值
									$("#updateDicId").val(dictionaryId);
									//用于通过字典id获取其上级字典的字典id，然后根据上级字典id获取上级字典名称
									$
											.ajax({
												url : "${pageContext.request.contextPath}/dic_getUpDicNameByDicId.action",
												data : {
													"dictionaryId" : dictionaryId
												},
												type : "POST",
												async : true,
												success : function(data) {
													if (data.attention == "警告！字典列表 这个字典 不应该被修改!") {
														alert(data.attention);
													} else {
														//alert(data.result);
														//为模态框--》修改  中的上级字典 赋值
														$("#updateUpDicName")
																.val(
																		data.result);
														//为模态框 --》修改   字典状态 赋值
														var isUse = data.isuse;//字典状态
														$(".el_radioBoxUp")
																.each(
																		function() {
																			var checkValue = $(
																					this)
																					.children(
																							"input")
																					.attr(
																							"value");
																			//alert(checkValue);

																			if (isUse == checkValue) {
																				$(
																						this)
																						.children(
																								"input")
																						.prop(
																								"checked",
																								"true");
																			}
																		});
														//为 字典状态赋值  结束***
														//为模态框--》修改  字典名称   赋值
														//alert("字典名称："+data.dicName);
														$("#dicNameUp").val(
																data.dicName);

														//为模态框--》修改  字典描述  赋值
														//alert("字典描述:"+data.describe);
														$("#dicDescUp").val(
																data.describe);
														//开启模态框
														$('#modifyUserInfo')
																.modal();
													}
												},
												error : function() {
													alert("操作失败，请重新操作哦");
												}
											});
								}
							</script>

							<!-- 模态框中的 修改字典信息中的 保存按钮的点击事件 -->
							<script type="text/javascript">
								function upBtnSave() {
									var isNotNull = $("#upForm")
											.validate(
													{
														ignore : [],
														rules : {
															"dictionary.dictionaryname" : "required",//字典名称        验证文本框的。前边是 name 属性
															"dictionary.discription" : "required",//字典描述
														},
														messages : {
															"dictionary.dictionaryname" : {//字典名称
																required : "不能为空"
															},//下边与上边对应
															"dictionary.discription" : {//字典描述
																required : "不能为空"
															}
														}
													});
									//以上是表单校验的操作
									//以下是修改字典的保存操作
									if (isNotNull.form()) {
										//alert("进入与树无关的  修改字典信息的保存按钮的点击事件")
										$
												.ajax({
													url : "${pageContext.request.contextPath}/dic_modifyDic.action",
													data : $("#upForm")
															.serialize(),
													dataType : "json",
													type : "POST",
													async : true,
													success : function(data) {
														alert(data.result);
														// 修改成功后刷新页面
														window.location.href = "${baseurl}/view/systemManage/dictionary.jsp";

													},
													error : function() {
														alert("修改失败,请您重新操作");
														//关闭模态框
														$('#modifyUserInfo')
																.modal('hide');
													}
												});//ajax的括号
									}//if的括号
								}//方法的括号

								//带 下拉树的 修改
								function upBtnSave2() {
									var isNotNull = $("#upForm2")
											.validate(
													{
														ignore : [],
														rules : {
															"dictionary.dictionaryname" : "required",//字典名称        验证文本框的。前边是 name 属性
															"dictionary.discription" : "required",//字典描述
														},
														messages : {
															"dictionary.dictionaryname" : {//字典名称
																required : "不能为空"
															},//下边与上边对应
															"dictionary.discription" : {//字典描述
																required : "不能为空"
															}
														}
													});
									//以上是表单校验的操作
									//以下是修改字典的保存操作
									if (isNotNull.form()) {
										//alert("进入与树无关的  修改字典信息的保存按钮的点击事件")
										$
												.ajax({
													url : "${pageContext.request.contextPath}/dic_modifyDic.action",
													data : $("#upForm2")
															.serialize(),
													dataType : "json",
													type : "POST",
													async : true,
													success : function(data) {
														alert(data.result);
														// 修改成功后刷新页面
														window.location.href = "${baseurl}/view/systemManage/dictionary.jsp";

													},
													error : function() {
														alert("修改失败,请您重新操作");
														//关闭模态框
														$('#modifyUserInfo2')
																.modal('hide');
													}
												});//ajax的括号
									}//if的括号
								}//方法的括号
							</script>


							<!-- 单条删除的操作 -->
							<!-- 删除  单条删除   -->
							<script type="text/javascript">
								//删除按钮的点击事件
								var dicId = "";
								function delcfm(obj) {
									//当前选中的培训资料的主键id
									dicId = $(obj).parent().siblings("input")
											.val();
									//打开模态框
									$('#delcfmModel').modal();
								}
							</script>
							<!-- 单条信息删除确认的   确定按钮的事件 -->
							<script type="text/javascript">
								function urlSubmit() {
									//alert("dicId:="+dicId)
									$
											.ajax({
												url : "${pageContext.request.contextPath}/dic_delByDicId.action",
												data : {
													"dictionaryid" : dicId
												},
												dataType : "json",
												type : "POST",
												async : true,
												success : function(data) {
													alert(data.result)
													//关闭模态框
													$('#delcfmModel').modal(
															'hide');

													//alert("还有值吗"+$("#addUpDicName").val())

													//单条删除成功之后 刷新当前页面数据
													btnFindFy();
												},
												error : function() {
													alert("删除操作失败")
													//关闭模态框
													$('#delcfmModel').modal(
															'hide');
												}

											});

									/* var url = $.trim($("#url").val());//获取会话中的隐藏属性URL
									window.location.href = url;  */
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
											<h4 class="modal-title">删除提示</h4>
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

							<!-- 模态框   批量信息删除确认 -->
							<div class="modal fade" id="delcfmModel2" data-backdrop="static" data-keyboard="false">
								<div class="modal-dialog">
									<div class="modal-content message_align">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
											<h4 class="modal-title">批量删除提示</h4>
										</div>
										<div class="modal-body">
											<p>您确认要删除这些字典吗？</p>
										</div>
										<div class="modal-footer">
											<input type="hidden" id="url2" />
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<a onclick="urlSubmit2()" class="btn btn-success"
												data-dismiss="modal">确定</a>
										</div>
										<!-- 确定按钮的点击事件  进行批量删除 -->
										<script type="text/javascript">
											function urlSubmit2() {
												var checkArray = $(".el_checks:checked");//获取到了所有被选中的复选框
												//alert("数组长度:"+checkArray.length)
												//定义一个数组，用来存放获取到的主键id

												//思路：将所有获取到的培训资料的id弄成一个字符串，然后在后台进行将id分割出来,每个id之间用","逗号分隔
												var ids = "";
												checkArray.each(function() {
													ids += $(this).parent()
															.parent().children(
																	"input")
															.val()
															+ ",";
												});

												//将获取到的ids字符串发送到后台进行分割
												$
														.ajax({
															url : "${pageContext.request.contextPath}/dic_delByDicIdsBatch.action",
															data : {
																"ids" : ids
															},
															dataType : "json",
															type : "POST",
															async : true,
															success : function(
																	data) {
																//给用户显示是否批量删除成功
																alert(data.result)
																//关闭模态框
																$(
																		'#delcfmModel2')
																		.modal(
																				'hide');

																//批量删除成功之后 刷新当前页面
																window.location
																		.reload();

															},
															error : function() {
																alert("批量删除操作失败")
																//关闭模态框
																$(
																		'#delcfmModel2')
																		.modal(
																				'hide');
															}

														});

												/*  var url = $.trim($("#url2").val());//获取会话中的隐藏属性URL
												 window.location.href = url; */
											}
										</script>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->


							<!-- ---------------------------------------------------- -->
							<!-- 一下是左边树的  修改、删除的点击事件-->
							<!-- 修改的操作 -->
							<!-- 修改按钮的点击事件  -->
							<script type="text/javascript">
								function modifyUserInfoTree(obj) {
									var dictionaryId = $(obj).parent().parent()
											.children("input").val();
									//为修改模态框中 隐藏的字典id的input标签赋值
									$("#updateDicId").val(dictionaryId);
									//用于通过字典id获取其上级字典的字典id，然后根据上级字典id获取上级字典名称
									$
											.ajax({
												url : "${pageContext.request.contextPath}/dic_getUpDicNameByDicId.action",
												data : {
													"dictionaryId" : dictionaryId
												},
												type : "POST",
												async : true,
												success : function(data) {
													if (data.attention == "警告！字典列表 这个字典 不应该被修改!") {
														alert(data.attention);
													} else {
														//alert(data.result);
														//为模态框--》修改  中的上级字典 赋值
														$("#updateUpDicName")
																.val(
																		data.result);
														//为模态框 --》修改   字典状态 赋值
														var isUse = data.isuse;//字典状态
														$(".el_radioBoxUp")
																.each(
																		function() {
																			var checkValue = $(
																					this)
																					.children(
																							"input")
																					.attr(
																							"value");
																			//alert(checkValue);

																			if (isUse == checkValue) {
																				$(
																						this)
																						.children(
																								"input")
																						.prop(
																								"checked",
																								"true");
																			}
																		});
														//为 字典状态赋值  结束***
														//为模态框--》修改  字典名称   赋值
														//alert("字典名称："+data.dicName);
														$("#dicNameUp").val(
																data.dicName);

														//为模态框--》修改  字典描述  赋值
														//alert("字典描述:"+data.describe);
														$("#dicDescUp").val(
																data.describe);
														//开启模态框
														$('#modifyUserInfo')
																.modal();
													}
												},
												error : function() {
													alert("操作失败，请重新操作哦");
												}
											});
								}

								//下拉树的修改事件
								function modifyUserInfoTree2(obj) {

									var dictionaryId = $(obj).parent().parent()
											.children("input").val();
									//alert(dictionaryId)
									//为修改模态框中 隐藏的字典id的input标签赋值
									$("#updateDicId2").val(dictionaryId);

									var tr = $(obj).parents("tr");

									//上级字典
									$("#updateUpDicName2").val(
											$("#menu400Name").val());

									//为模态框 --》修改   字典状态 赋值
									var isUse = tr.children("td").eq(3).text();//字典状态
									$(".el_radioBoxUp2").each(
											function() {
												var checkValue = $(this)
														.children("input")
														.attr("value");

												if (isUse == checkValue) {
													$(this).children("input")
															.prop("checked",
																	"true");
												}
											});
									//字典名称，树
									$("#dicNameUp2").val(
											tr.children("td").eq(2).text());

									//字典描述
									/* $("#dicDescUp2").val(
											tr.find(".docID").val()); */
									$("#dicDescUp2").val(
											tr.children("td").eq(4).text());

									//开启模态框
									$('#modifyUserInfo2').modal();

									/**
									 * 获取当前列的内容，放到修改框中
									 * 条用genedistionaryTree 方法，
									 */
									var zNodes10;
									$
											.ajax({
												url : '${pageContext.request.contextPath}/exam_getDepartmentTree.action',
												async : true,
												dataType : 'json',
												success : function(response) {
													zNodes10 = response.departmentTrees;
													// 生成树结构
													genedistionaryTree(zNodes10);
												},
												error : function() {
													alert("查询内部部门树失败！！！")
												}
											});

								}
							</script>
							<!-- 模态框中的 修改字典信息中的 保存按钮的点击事件 -->
							<script type="text/javascript">
								function upBtnSave() {
									var isNotNull = $("#upForm")
											.validate(
													{
														ignore : [],
														rules : {
															"dictionary.dictionaryname" : "required",//字典名称        验证文本框的。前边是 name 属性
															"dictionary.discription" : "required",//字典描述
														},
														messages : {
															"dictionary.dictionaryname" : {//字典名称
																required : "不能为空"
															},//下边与上边对应
															"dictionary.discription" : {//字典描述
																required : "不能为空"
															}
														}
													});
									//以上是表单校验的操作
									//以下是修改字典的保存操作
									if (isNotNull.form()) {
										//alert("进入与树有关的  修改字典信息的保存按钮的点击事件")
										$
												.ajax({
													url : "${pageContext.request.contextPath}/dic_modifyDic.action",
													data : $("#upForm")
															.serialize(),
													dataType : "json",
													type : "POST",
													async : true,
													success : function(data) {
														alert(data.result);
														//修改成功之后刷新页面
														window.location
																.reload(true)
													},
													error : function() {
														alert("修改失败,请您重新操作");
														//关闭模态框
														$('#modifyUserInfo')
																.modal('hide');
													}
												});//ajax的括号
									}//if的括号
								}//方法的括号
							</script>


							<!-- -----------------单条删除相关的操作------------- -->
							<!-- 删除  单条删除   -->
							<script type="text/javascript">
								//删除按钮的点击事件
								var dicId = "";
								function delcfmTree(obj) {
									//alert("哈哈")
									//alert("和树有关的   字典的主键id:"+$(obj).parent().siblings("input").val())
									//当前选中的培训资料的主键id
									dicId = $(obj).parent().siblings("input")
											.val();
									//打开模态框
									$('#delcfmModel').modal();
								}
							</script>
							<!-- 单条信息删除确认的   确定按钮的事件 -->
							<script type="text/javascript">
								function urlSubmit() {
									//alert("dicId:="+dicId)
									$
											.ajax({
												url : "${pageContext.request.contextPath}/dic_delByDicId.action",
												data : {
													"dictionaryid" : dicId
												},
												dataType : "json",
												type : "POST",
												async : true,
												success : function(data) {
													alert(data.result)
													//关闭模态框
													$('#delcfmModel').modal(
															'hide');

													var upDictioaryName = data.upDictioaryName;//上级字典名称
													//当前页页号   以下的代码是从showAllDicmsgByDicName方法中复制过来的 用于刷新数据的 **********
													$
															.ajax({
																url : "${pageContext.request.contextPath}/dic_getDownDicMsgByDicName.action",
																data : {
																	"dicName" : upDictioaryName,//这里应该是上级字典的名称
																	"curPage" : $(
																			"#yeHao")
																			.val(),
																	"perCount" : $(
																			"#jiLuShu")
																			.val()
																},
																dataType : "json",
																type : "POST",
																async : true,
																success : function(
																		data) {
																	//alert("进入回掉函数")
																	//数据显示之前 要先清空表格中的所有数据
																	$(
																			"#tBody tr")
																			.remove();
																	var tdStr = "";
																	for (var i = 0; i < data.dictionaryList.length; i++) {
																		//字典id  dictionaryId
																		var dictionaryid = data.dictionaryList[i].dictionaryid;
																		//字典名称  dictionaryName
																		var dictionaryname = data.dictionaryList[i].dictionaryname;
																		//上级字典id upDictionaryId
																		var updictionaryid = data.dictionaryList[i].updictionaryid;
																		//是否可用 isUse
																		var isuse = data.dictionaryList[i].isuse;
																		//描述 discription
																		var discription = data.dictionaryList[i].discription;
																		//
																		tdStr += "<tr>";
																		//按照DOM规则动态的添加到表格中
																		//隐藏域 隐藏字典的主键id
																		tdStr += "<input type='hidden' name='documentId' value="+dictionaryid+" class='docID'/>";
																		tdStr += "<td><input type='checkbox' class='el_checks'/></td>";
																		tdStr += "<td>"
																				+ (i + 1)
																				+ "</td>";//序号
																		tdStr += "<td>"
																				+ dictionaryname
																				+ "</td>";//字典名称
																		tdStr += "<td>"
																				+ isuse
																				+ "</td>";//字典状态(是否可用)
																		tdStr += "<td>"
																				+ discription
																				+ "</td>";//字典描述
																		//
																		tdStr += "<td>";
																		tdStr += "<a href='javascript:void(0)' onclick='modifyUserInfoTree(this)' >修改</a>"
																		tdStr += "<a href='javascript:void(0)' class='el_delButton' onClick='delcfmTree(this)'>删除</a>";
																		tdStr += "</td>";
																		//
																		tdStr += "</tr>";
																	}
																	//将结果拼成标签后按照DOM规则动态的添加到tbody标签下，作为其子节点
																	$("#tBody")
																			.append(
																					tdStr);
																	/*//每页显示的记录数
																	map.put("perCount", perCount);
																	//当前页页号
																	map.put("curPage", curPage);
																	//总记录数
																	map.put("sumCount", sumCount);*/
																	//alert("测试")
																	//alert(data.sumCount)//5
																	//alert(data.curPage)//1
																	//alert(data.perCount)//10
																	//为两个隐藏域赋值  当前页页号   每页显示的记录数
																	//$("#yeHao").val(data.curPage);
																	//$("#jiLuShu").val(data.perCount);
																	//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
																	queryFy2(
																			data.sumCount,
																			data.curPage,
																			data.perCount);
																	//回显的结果
																	//alert(data.result);
																},
																error : function() {
																	alert("查询失败，数据库中没有该数据");
																}
															})
													//以上的代码是从showAllDicmsgByDicName方法中复制过来的用于刷新数据的***************

												},
												error : function() {
													alert("删除操作失败")
													//关闭模态框
													$('#delcfmModel').modal(
															'hide');
												}

											});

									/* var url = $.trim($("#url").val());//获取会话中的隐藏属性URL
									window.location.href = url;  */
								}
							</script>
							<!-- ---------------单条删除相关的操作    end---------- -->

							<!-- 以下是左边树的   end -->
							<!-- -------------------------------------------------------------------------------- -->




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