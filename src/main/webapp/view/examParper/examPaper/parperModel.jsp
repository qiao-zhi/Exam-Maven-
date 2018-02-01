<!-- 试卷预览(考试，试卷管理中试卷预览) -->
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
<title>试卷预览</title>

<%@ include file="/public/cssJs.jsp"%>
<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<style type="text/css">
/* 去除边框 */
input {
	border-style: none;
}
</style>

<link rel="stylesheet" href="<%=path%>/css/examParper/parperModel.css">
<script src="<%=path%>/js/examParper/examPaper/parperModel.js"></script>
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
				<!--顶部锚-->
				<a name="goTop"></a>
				<!--回到顶部-->
				<a id="el_returnTop" href="javascript:scroll(0,0)">
					<span> &#9650; 回到顶部</span>
				</a>

				<!--导航-->
				<nav class="navbar navbar-default">
				<div class="container-fluid" style="padding: 0 !important;">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<span class="navbar-brand">&nbsp;考试试卷</span>
					</div>

					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">试题导出&nbsp;&nbsp;&nbsp; <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li>
									<button type="button" class="btn btn-block btn-default" onclick="extPaper()">导出试卷</button>
								</li>
								<li>
									<button type="button" class="btn btn-block btn-default" onclick="extPaperAnswer()">导出试卷答案</button>
								</li>
								<!-- <li class="el_Inputlulli"><input type="checkbox" />参考人员信息</li> -->
								<!-- <li class="el_Inputlulli2 btn btn-success btn-sm"
									onClick="output()">导出</li> -->
							</ul>
						</li>

						<li class="el_statistic"><a
							href="javascript:history.back(-1);">返回</a></li>
					</ul>
				</div>
				<!-- /.container-fluid --> </nav>

				<!--试卷信息-->
				<div class="el_parperInfo">
					<table>
						<caption>试卷基本信息</caption>
						<!-- 隐藏试卷的ID -->
						<input type="hidden" name="paperId"
							value="${result.paper.paperid }" />
						<tr>
							<td>试卷标题</td>
							<td>${result.paper.title }</td>
						</tr>
						<tr>
							<td>试卷级别</td>
							<td><c:if test="${result.paper.level eq 1}">一级</c:if> <c:if
									test="${result.paper.level eq 2}">二级</c:if> <c:if
									test="${result.paper.level eq 3}">三级</c:if></td>
						</tr>
						<tr>
							<td>试卷分数</td>
							<td>${result.paper.paperscore}</td>
						</tr>
						<tr>
							<td>试卷使用次数</td>
							<td>${result.paper.usetimes}</td>
						</tr>
						<tr>
							<td>试卷描述</td>
							<td>${result.paper.description}</td>
						</tr>
						<tr>
							<td>创建人</td>
							<td>${result.paper.employeename}</td>
						</tr>
						<tr>
							<td>出题时间</td>
							<td><fmt:formatDate value="${result.paper.maketime}"
									pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
						</tr>
					</table>
				</div>



				<!--主要内容-->
				<div class="container" style="margin-bottom: 50px;">
					<!--右侧事件-->

					<!--考试标题-->
					<h3 id="el_examTitle">${result.paper.title }</h3>

					<!-- <div>
						<span>考试时间：</span><span>120 </span><span>分钟</span>
					</div> -->

					<%-- <c:set var="num" value="1" scope="page"></c:set> --%>


					<!--S  显示试题 -->
					<c:if test="${!empty result.paper.bigQuestions }">
						<!-- 遍历大题 -->
						<c:forEach items="${result.paper.bigQuestions }" var="bigQues">
							<div>
								<b>${bigQues.bigquestionname }</b>
							</div>
							<c:if test="${!empty  bigQues.questions}">
								<!-- 遍历小题 -->
								<c:forEach items="${bigQues.questions }" var="ques">
									<br />
									<div>${ques.questionsequence}.
										&nbsp;&nbsp;${ques.questioncontent }<br />
										<c:if test="${!empty  ques.options}">
											<!-- 遍历选项-->
											<c:forEach items="${ques.options }" var="opt">
												<div>
<%-- 													<c:if test="${ques.type eq '多选题' }">
														<input name="" type="checkbox" value="">
													</c:if>
													<c:if test="${ques.type ne '多选题' }">
														<input name="a" type="radio" value="">
													</c:if> --%>
													${opt.optionsequence } &nbsp;&nbsp;${opt.optioncontent }<br />
												</div>
											</c:forEach>
										</c:if>
										答案:${my:replace(ques.answer,"12345","ABCDE") }<br />
										<%-- 解析:${ques.analysis } --%>
									</div>
								</c:forEach>
								<br />
							</c:if>

						</c:forEach>

					</c:if>
					<!--S  显示试题 -->

				</div>


				<!-- 模态框   导出提示框 -->
				<div class="modal fade" id="outputModel">
					<div class="modal-dialog">
						<div class="modal-content message_align">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
								<h4 class="modal-title">提示信息</h4>
							</div>
							<div class="modal-body">
								<span>您确认要导出<span>试卷</span>、<span>答案</span><span>和参考人员的信息</span>吗？
								</span>
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

			</div>
			<!-- /.modal -->

		</div>
		<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
	</div>
	</div>

	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<!--放脚-->
	<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>
