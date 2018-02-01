<!-- 发布考试 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>发布考试</title>
<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<%@ include file="/public/cssJs.jsp"%>

<!--复选框 树-->
<script src="<%=path%>/js/public/treeC.js"></script>

<script src="<%=path%>/js/examParper/exam/addExam.js"></script>
<link rel="stylesheet" href="<%=path%>/css/examParper/releaseExam.css">

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
				<!--添加考试基本内容-->
				<div class="el_mainContent">
					<div class="el_head">
						<h4>填写考试基本信息</h4>
					</div>

					<form action="examManage.html">

						<div class="input-group el_modellist" role="toolbar">
							<span class="el_spans">考试名称：</span> <input type="text"
								class="form-control el_modelinput" name="" />
						</div>

						<div class="input-group el_modellist" role="toolbar">
							<span class="el_spans">考试地点：</span> <input type="text"
								class="form-control el_modelinput" name="" />
						</div>

						<div class="input-prepend input-group el_modellist">
							<span class="add-on el_spans">考试时间：</span> <input type="text"
								class="wicon form-control" id="inpstart" readonly> <input
								type="text" class="wicon form-control" id="inpend" readonly>
						</div>

						<div class="input-group el_modellist" role="toolbar">
							<span class="el_spans">考试描述：</span>
							<textarea class="form-control" rows="1"></textarea>
						</div>

						<div class="input-group el_modellist" role="toolbar">
							<span class="el_spans">创&nbsp;&nbsp;建&nbsp;人：</span> <input
								type="text" class="form-control el_modelinput" name="" />
						</div>

						<div class="input-group el_modellist" role="toolbar">
							<span class="el_spans">创建时间：</span> <input type="text" id="test4"
								class="wicon form-control" readonly />
						</div>

						<!--根据选择的部门类型，
                动态的加载相应部门类型的部门
                再调用相应部门类型的添加部门一人的模态-->
						<div class="input-group el_modellist" role="toolbar">
							<span class="el_spans">部门类型：</span> <label
								class="el_radioBox el_radioBox2"> <input type="radio"
								name="el_departType" checked value="0">厂内部门
							</label> <label class="el_radioBox el_radioBox2"> <input
								type="radio" name="el_departType" value="1">外来单位
							</label>
						</div>

						<div class="input-group el_modellist" role="toolbar">
							<span class="el_spans el_chooseSpan">参考部门：</span>
							<ul id="el_chooseDepart1" class="form-control"></ul>
							<img src="../../controls/selectDropTree/smallTriangle.png"
								class="el_smallTriangle" width="7" />
							<ul id="treeDemo10" class="ztree"></ul>
						</div>

						<div class="el_modellist el_modellist2">
							<div class="el_examPersonLeft">
								<span class="el_spans">选择员工：</span>
							</div>
							<div class="el_examPersons el_examPersons2">
								<div class="el_chooseDepartButton">
									<input type="button" class="btn-sm btn btn-default"
										value="添加部门人员" onclick="el_chooseDepart()">
								</div>

								<div class="panel panel-default el_departPersons">
									<div class="panel-heading">
										<span class="el_addDepart">部门1</span>&nbsp;&nbsp;(人数：<span>1</span>)
									</div>
									<div class="panel-body">
										<span class="el_empList">李明 <img class="el_empDelete"
											src="../../image/delete.png" /></span>
									</div>
								</div>

								<div class="panel panel-default el_departPersons">
									<div class="panel-heading">
										<span class="el_addDepart">部门1</span>&nbsp;&nbsp;(人数：<span>2</span>)
									</div>
									<div class="panel-body">
										<span class="el_empList">李明1 <img class="el_empDelete"
											src="../../image/delete.png" />
										</span> <span class="el_empList">李明2 <img class="el_empDelete"
											src="../../image/delete.png" />
										</span>
									</div>
								</div>
							</div>
						</div>

						<div class="el_optionButton">
							<!--提交-->
							<input type="button" class="btn btn-default" id="el_return"
								name="back" value="取消" onclick="javascript:history.back(-1);" />
							<a href="<%=path %>/view/examParper/examManage.jsp"><button
									type="button" class="btn btn-primary">确定</button></a>
						</div>

					</form>

					<!-- 模态框 查询添加员工-->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<!--关闭符号-->
									<!--标题-->
									<h4 class="modal-title" id="myModalLabel">添加考试员工</h4>
								</div>
								<form>
									<!--索引-->
									<div class="el_queryBox">
										<form action="">

											<div class="row el_queryBoxrow">

												<div class="el_qlmQuery">
													<div class="input-group" role="toolbar">
														<span class="el_spans">姓名：</span> <input type="text"
															style="width: 100px;" class="form-control" name="" />
													</div>
												</div>

												<div class="el_qlmQuery">
													<div class="input-group" role="toolbar">
														<span class="el_spans" style="padding-right: 0;">性别：</span>
														<label class="el_radioBox"><input type="radio"
															name="el_gender"> 男</label> <label class="el_radioBox"><input
															type="radio" name="el_gender"> 女</label>
													</div>
												</div>

												<div class="el_qlmQuery">
													<div class="input-group" role="toolbar">
														<span class="el_spans">违章积分：</span> <select
															class="selectpicker form-control" title="请选择">
															<option>--请选择--</option>
															<option>3分以下</option>
															<option>4-6分</option>
															<option>7-9分</option>
															<option>9-11分</option>
															<option>12分及以上</option>
														</select>
													</div>
												</div>

												<div class="el_qlmQuery">
													<div class="input-group" role="toolbar">
														<span class="el_spans">黑名单：</span> <label
															class="el_radioBox"><input type="radio"
															name="el_blackList"> 是</label> <label class="el_radioBox"><input
															type="radio" name="el_blackList"> 否</label>
													</div>
												</div>

												<div class="el_qlmQuery">
													<div class="input-group" role="toolbar">
														<span class="el_spans">身份证：</span> <input type="text"
															class="form-control" name="" width="120" />
													</div>
												</div>


												<div class="el_qlmQuery">
													<div class="input-group" role="toolbar">
														<span class="el_spans">考试情况：</span> <select
															class="selectpicker form-control" title="请选择">
															<option>--请选择--</option>
															<option>通过一级考试</option>
															<option>通过二级考试</option>
															<option>通过三级考试</option>
															<option>未通过</option>
														</select>
													</div>
												</div>

												<!--提交查询按钮-->

												<div class="el_addPersonsQuery">
													<button type="submit"
														class="btn btn-default el_queryButton2 btn-sm">查询
													</button>
												</div>
											</div>

										</form>

									</div>
									<!--结束 查询表单提交-->

									<!--表格内容-->
									<table class="table el_AddPersonTable">
										<thead>
											<tr>
												<th><input type="checkbox" id="el_checkQuanxuan" /></th>
												<th>姓名</th>
												<th>性别</th>
												<th>身份证</th>
												<th>所属部门</th>
												<th>考试情况</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><input type="checkbox" class="el_checks" /></td>
												<td>王菲</td>
												<td>nan</td>
												<td>1838235486</td>
												<td>234234234</td>
												<td>通过一级考试</td>
											</tr>
											<tr>
												<td><input type="checkbox" class="el_checks" /></td>
												<td>王菲</td>
												<td>nan</td>
												<td>1838235486</td>
												<td>234234234</td>
												<td>通过一级考试</td>
											</tr>
										</tbody>
									</table>
									<script>
                            $("#el_checkQuanxuan").change(function () {
                                if ($(this).prop("checked") == true) {
                                    $(".el_checks").prop("checked", "true");
                                } else {
                                    $(".el_checks").removeAttr("checked");
                                }
                            })
                        </script>

									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">关闭</button>
										<button type="button" class="btn btn-primary">确定</button>
									</div>
								</form>

							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal -->
					</div>

				</div>

			</div>


		</div>
	</div>

	<!--放脚-->
	<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>
