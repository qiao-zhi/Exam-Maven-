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
<title>检修项目管理</title>

<%@ include file="/public/cssJs.jsp"%>

<!--验证-->
<script src="<%=path%>/controls/validate/jquery.validate.js"></script>
<script src="<%=path%>/controls/validate/messages_zh.js"></script>

<link rel="stylesheet" href="<%=path%>/css/news/newsManage.css">
<!-- 日期格式 -->
<script src="<%=path%>/js/questionLibrary/dateformat.js"></script>
<!-- 检修管理的js -->
<script src="<%=path%>/js/overhaul/overhaulManage.js"></script>
<style type="text/css">
/*validate中不成功显示的样式设置*/
label.error {
	background: url(${baseurl }/controls/validate/unchecked.gif) no-repeat
		10px 3px;
	padding-left: 30px;
	font-family: georgia;
	font-size: 15px;
	font-style: normal;
	color: red;
}

label.success {
	background: url(${baseurl }/controls/validate/checked.gif) no-repeat
		10px 3px;
	padding-left: 30px;
}

.workinput {
	width: 145px !important;
	margin-right: 20px !important;
}

#inpend0, #inpend1 {
	margin-right: 0 !important;
}
</style>
<script>
	/* 定义一个全局变量为项目名字 */
	var hasOperatingJianxiu = false;//是否有修改删除检修的权限
	var contextPath = "${baseurl}";
	$(function() {

		//日历
		var start0 = {
			format : 'YYYY-MM-DD',
			minDate : '2004-06-16', //设定最小日期为当前日期
			festival : false,
			ishmsVal : false,
			choosefun : function(elem, val, date) {
				end0.minDate = date; //开始日选好后，重置结束日的最小日期
				endDates0();
			}
		};
		var end0 = {
			format : 'YYYY-MM-DD',
			minDate : $.nowDate({
				DD : "-60"
			}), //设定最小日期为当前日期
			festival : false,
			maxDate : '2299-06-16', //最大日期
			choosefun : function(elem, val, date) {
				start0.maxDate = date; //将结束日的初始值设定为开始日的最大日期
			}
		};
		//这里是日期联动的关键        
		function endDates0() {
			//将结束日期的事件改成 false 即可
			end0.trigger = false;
			$("#inpend0").jeDate(end0);
		}

		$('#inpstart0').jeDate(start0);
		$('#inpend0').jeDate(end0);

		/* 修改 */
		var start1 = {
			format : 'YYYY-MM-DD',
			minDate : '2004-06-16', //设定最小日期为当前日期
			isinitVal : true,
			festival : false,
			ishmsVal : false,
			maxDate : $.nowDate({
				DD : "+60"
			}), //最大日期
			choosefun : function(elem, val, date) {
				end1.minDate = date; //开始日选好后，重置结束日的最小日期
				endDates1();
			}
		};
		var end1 = {
			format : 'YYYY-MM-DD',
			minDate : $.nowDate({
				DD : "-60"
			}), //设定最小日期为当前日期
			festival : false,
			maxDate : '2299-06-16', //最大日期
			choosefun : function(elem, val, date) {
				start1.maxDate = date; //将结束日的初始值设定为开始日的最大日期
			}
		};
		//这里是日期联动的关键        
		function endDates1() {
			//将结束日期的事件改成 false 即可
			end1.trigger = false;
			$("#inpend1").jeDate(end1);
		}

		$('#inpstart1').jeDate(start1);
		$('#inpend1').jeDate(end1);
	})
</script>
<!-- 如果有删除修改检修的权限执行下面代码修改全局变量 -->
<shiro:hasPermission name="jianxiu:operating">
	<script>
		hasOperatingJianxiu = true;
	</script>
</shiro:hasPermission>
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
						<span>短委员工管理</span><span>>检修项目管理</span>
					</div>

					<div class="el_main">

						<!--内容-->
						<div class="el_qlmContent">

							<!--索引-->
							<div class="row el_queryBox">
								<form id="haulQueryForm">
									<!--隐藏当前页与页大小  -->
									<input type="hidden" name="currentPage" id="currentPage">
									<input type="hidden" name="currentCount" id="currentCount">

									<div class="row">

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">检修名称：</span> <input type="text"
													class="form-control" name="bigName" />
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">检修时间：</span> <input type="text"
													name="startMonth" id="test"
													class="wicon el_noVlaue form-control" readonly />
											</div>
										</div>

										<div class="col-md-3 el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans">检修状态：</span> <select
													class="selectpicker form-control" name="bigStatus"
													title="请选择">
													<option value="">--请选择--</option>
													<option value="未开始">未开始</option>
													<option value="进行中">进行中</option>
													<option value="已结束">已结束</option>
												</select>
											</div>
										</div>

									</div>


									<!--提交查询按钮-->
									<button type="reset"
										class="btn btn-default el_queryButton0 btn-sm">清空</button>
									<button type="button" id="haulQueryButton"
										class="btn btn-primary el_queryButton btn-sm">查询</button>
								</form>

							</div>
							<!--结束 查询表单提交-->

							<!--显示内容-->
							<h4 class="el_mainHead">检修信息</h4>
							<div class="panel panel-default el_Mainmain">

								<!--按钮面板-->
								<div class="panel-body">

									<div class="panel panel-default">
										<div class="panel-body el_MainxiaoMain">

											<div class="el_topButton">
												<shiro:hasPermission name="jianxiu:add">
													<button class="btn btn-primary" onclick="el_addOverhaul()">
														创建检修</button>
												</shiro:hasPermission>
											</div>
										</div>
									</div>

									<!-- 表格 内容都提取到json里边 -->
									<table class="table table-hover table-bordered" id="newsTable">
										<thead>
											<tr>
												<th>序号</th>
												<th>检修名称</th>
												<th>时间</th>
												<th>状态</th>
												<th>描述</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="haulTbody">
										</tbody>
									</table>

									<!--分页-->
									<div id="paginationIDU" class="paginationID"></div>
								</div>
							</div>

							<!-- 模态框 检修添加-->
							<div class="modal fade" id="el_addOverhaul" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel2">添加检修信息</h4>
										</div>
										<form id="addHaulForm">
											<div class="modal-body">
												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">检修名称：</span> <input type="text"
														class="form-control el_modelinput clearAdd" name="bigname" />
												</div>
												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">检修时间：</span> <input type="text"
														placeholder="开始时间"
														class="workinput form-control el_noVlaue wicon"
														id="inpstart0" placeholder="开始时间" name="bigbegindate"
														readonly> <input type="text" name="bigenddate"
														placeholder="结束时间"
														class="workinput el_noVlaue form-control wicon clearAdd"
														id="inpend0" readonly>
												</div>
												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">标段名称：</span>
													<input name="XXXXXX"/>
												</div>
												<!-- <div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">检修简介：</span>
													<textarea name="bigdescription"
														class="form-control el_modelinput clearAdd" rows="5"></textarea>
												</div> -->
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" class="btn btn-primary"
													onclick="addNewsSibmitButton()">提交</button>
											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>

							<!-- 模态框 检修 修改-->
							<div class="modal fade" id="el_modifyOverhaul" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<!--关闭符号-->
											<!--标题-->
											<h4 class="modal-title" id="myModalLabel24">修改检修信息</h4>
										</div>
										<form id="modifyHaulForm">
											<div class="modal-body">
												<!--隐藏检修的Id  -->
												<input type="hidden" name="bigid" id="update_bigid" />
												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">检修名称：</span> <input type="text"
														class="form-control el_modelinput" id="update_haulName"
														name="bigname" />
												</div>
												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">检修时间：</span> <input type="text"
														class="workinput form-control el_noVlaue wicon"
														id="inpstart1" placeholder="开始时间" name="bigbegindate"
														readonly> <input type="text" placeholder="结束时间"
														name="bigenddate"
														class="workinput el_noVlaue form-control wicon"
														id="inpend1" readonly>
												</div>
												<div class="input-group el_modellist" role="toolbar">
													<span class="el_spans">检修简介：</span>
													<textarea class="form-control el_modelinput"
														name="bigdescription" id="update_desc" rows="5"></textarea>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button type="button" id="update_haul_btn"
													class="btn btn-primary">修改</button>
											</div>
										</form>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>

							<!-- 模态框   信息删除确认 -->
							<div class="modal fade" id="delcfmOverhaul">
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
											<!-- 隐藏需要删除的id -->
											<input type="hidden" id="deleteHaulId" />
											<p style="font-size: 25px">您确认要删除该检修信息吗?</p>
											<br /> <br />
											<p style="font-size: 18px; color: red">如果删除,该检修下的部门、员工、员工考试以及成绩信息也将被删除！</p>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<button type="button" class="btn btn-primary"
												id="deleteHaulBtn">确认</button>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->


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
