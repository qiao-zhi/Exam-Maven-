<!-- 自动创建试卷(随机，根据知识点生成试卷) -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
<title>快速自动生成试卷</title>
<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>

<%@ include file="/public/cssJs.jsp"%>

<link href="<%=path%>/bs/css/bootstrap-select.css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/bs/js/bootstrap-select.js"></script>

<%-- <script src="<%=path%>/js/examParper/autoCreateExam.js"></script> --%>
<!-- qlq写的 -->
<script src="${baseurl}/js/examParper/examPaper/autoCreateExamPaper.js"></script>
<link rel="stylesheet"
	href="<%=path%>/css/examParper/autoCreateExam.css">

<%@ include file="/public/validate.jsp"%>
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
				<!--选择操作-->
				<div class="panel panel-default">
					<div class="panel-heading">
						<span>培训管理</span><span>>创建试卷</span><span>>快速生成试卷</span>
					</div>
					<div class="el_section">
						<div class="el_navButton">
							<a href="#profile" aria-controls="profile" role="tab"
								data-toggle="tab" style="text-decoration: none"><span
								class="btn btn-primary el_createButton">随机自动生成试卷</span></a> <a href="#home"
								aria-controls="home" role="tab" data-toggle="tab"> <span
								class="btn btn-default el_createButton">根据工种与知识点生成试卷</span>
							</a>
						</div>
					</div>
				</div>

				<div class="tab-content">

					<!--根据知识点自动生成-->
					<div role=tabpanel " class="tab-pane col-md-12" id="home">
						<!--添加类型-->
						<form action="/Exam/createPaper_knowledgeGenePaper.action"
							method="post" id="knowledgeGenePaperForm">
							<div class="panel panel-default el_addSet">
								<div class="el_section">
									<div class="panel-body" style="overflow:visible">
										<!--单选框-->
										<div class="el_danxuanH">工种：</div>
										<input type="hidden" name="ss" />
										<!--异步添加知识点字典(工种)  -->
										<div class="checkbox" id="employeeTypes">
											<!-- 	<label class="el_duoxuan"><input type="checkbox"
												value="安全生产法律法规" name="knowledges">安全生产法律法规</label> 
												<label class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="安全生产基础知识">安全生产基础知识</label>
												 <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="安全生产管理知识">安全生产管理知识</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="安全生产技术知识">安全生产技术知识</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="电力人身安全风险防控">电力人身安全风险防控</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="电力安全规程制度">电力安全规程制度</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="防止电力生产事故的二十五项重点要求">防止电力生产事故的二十五项重点要求</label>
											<label class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="公共安全知识">公共安全知识</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="职业健康类知识">职业健康类知识</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="现场急救知识">现场急救知识</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="事故案例分析">事故案例分析</label> <label
												class="el_duoxuan"></label>
 -->
										</div>
										<br/>
										<!--单选框-->
										<div class="el_danxuanH">试题知识点：</div>
										<input type="hidden" name="ss" />
										<!--异步添加知识点字典  -->
										<div class="checkbox" id="knowledgeType">
											<!-- 												<label class="el_duoxuan"><input type="checkbox"
												value="安全生产法律法规" name="knowledges">安全生产法律法规</label> 
												<label class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="安全生产基础知识">安全生产基础知识</label>
												 <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="安全生产管理知识">安全生产管理知识</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="安全生产技术知识">安全生产技术知识</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="电力人身安全风险防控">电力人身安全风险防控</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="电力安全规程制度">电力安全规程制度</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="防止电力生产事故的二十五项重点要求">防止电力生产事故的二十五项重点要求</label>
											<label class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="公共安全知识">公共安全知识</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="职业健康类知识">职业健康类知识</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="现场急救知识">现场急救知识</label> <label
												class="el_duoxuan"><input type="checkbox"
												name="knowledges" value="事故案例分析">事故案例分析</label> <label
												class="el_duoxuan"></label>
 -->
										</div>
										<!-- <input type="reset" id="el_cancelButton"
											class="btn btn-default btn-sm" value="清除选择"> -->
									</div>
								</div>
							</div>


							<!--主要内容-->
							<!--总统计-->
							<div class="row">
								<div class="panel panel-default el_knowledgePointMainPanel">
									<div class="panel-heading">统计</div>								
									<span>请填写试卷的单选题，多选题和判断题的数量及每道题的分数，将根据您选择的工种和知识点自动生成试卷。</span><br /><br />
									<div class="el_section">
										<span class="el_zongfen">总分：</span><u>${result.exampaper.paperscore }</u>
										<div class="panel-body el_allStatisticPanelbody">
											<ul>
												<li class="el_knoeledgePointul1">
													<ul>
														<li>单选题：</li>
														<li><input type="text" name="danxuan_num"
															id="danxuan_num" value="0" />道</li>
														<li><input type="text" name="danxuan_score"
															id="danxuan_score" value="0" />分数(每道题)</li>
													</ul>
												</li>
												<li class="el_knoeledgePointul1">
													<ul>
														<li>多选题：</li>
														<li><input type="text" name="duoxuan_num"
															id="duoxuan_num" value="0" />道</li>
														<li><input type="text" name="duonxuan_score"
															id="duoxuan_score" value="0" />分数(每道题)</li>
													</ul>
												</li>
												<li class="el_knoeledgePointul1">
													<ul>
														<li>判断题：</li>
														<li><input type="text" name="panduan_num"
															id="panduan_num" value="0" />道</li>
														<li><input type="text" name="panduan_score"
															id="panduan_score" value="0" />分数(每道题)</li>
													</ul>
												</li>
											</ul>
											<!--隐藏试卷基本信息  -->
											<input type="hidden" name="exampaper.paperscore"
												value="${result.exampaper.paperscore }"> <input
												type="hidden" name="exampaper.maketime"
												value="<fmt:formatDate value="${result.exampaper.maketime}"    pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>">
											<input type="hidden" name="exampaper.level"
												value="${result.exampaper.level}"> <input
												type="hidden" name="exampaper.employeename"
												value="${result.exampaper.employeename}"> <input
												type="hidden" name="exampaper.title"
												value="${result.exampaper.title}"> <input
												type="hidden" name="exampaper.description"
												value="${result.exampaper.description}">
											<!-- 隐藏提交方式 -->
											<input type="hidden" name="quickGenePaperMethod"
												id="knowGenePaperMethod">
										</div>
									</div>
								</div>
						</form>
					</div>

					<!--单种-->
					<div class="row" id="el_MainPanel"></div>


					<!--生成试卷-->
					<div class="row el_generateRow">
						<div class="el_section">
							<input type="button" class="btn btn-primary" value="生成试卷"
								id="knowledgeGenepaperBtn" /> <input type="button"
								class="btn btn-danger" name="back" value="返回"
								onclick="javascript:history.back(-1);" />
						</div>
					</div>
					<br /> <br /> <br /> <br />
				</div>

				<!--自动生成-->
				<div role=tabpanel class="tab-pane active col-md-12 el_autoAddPanel"
					id="profile">

					<span>请填写试卷的单选题，多选题和判断题的数量及每道题的分数，将随机自动生成试卷。</span><br />
					<br />
					<div class="el_section">
						<span class="el_zongfen">总分：</span><u id="totalScore">${result.exampaper.paperscore }</u>
						<div class="panel-body el_allStatisticPanelbody">
							<form action="${baseurl }/createPaper_suijiGenePaper.action"
								method="post" id="autoGenePaperForm">
								<ul>
									<li class="el_knoeledgePointul1">
										<ul>
											<li>单选题：</li>
											<li><input type="text" name="danxuan_num"
												id="danxuan_num" value="0" />道</li>
											<li><input type="text" name="danxuan_score"
												id="danxuan_score" value="0" />分数(每道题)</li>
										</ul>
									</li>
									<li class="el_knoeledgePointul1">
										<ul>
											<li>多选题：</li>
											<li><input type="text" name="duoxuan_num"
												id="duoxuan_num" value="0" />道</li>
											<li><input type="text" name="duonxuan_score"
												id="duoxuan_score" value="0" />分数(每道题)</li>
										</ul>
									</li>
									<li class="el_knoeledgePointul1">
										<ul>
											<li>判断题：</li>
											<li><input type="text" name="panduan_num"
												id="panduan_num" value="0" />道</li>
											<li><input type="text" name="panduan_score"
												id="panduan_score" value="0" />分数(每道题)</li>
										</ul>
									</li>
								</ul>
								<!--隐藏试卷基本信息  -->
								<input type="hidden" name="exampaper.paperscore"
									value="${result.exampaper.paperscore }"> <input
									type="hidden" name="exampaper.maketime"
									value="<fmt:formatDate value="${result.exampaper.maketime}"    pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>">
								<input type="hidden" name="exampaper.level"
									value="${result.exampaper.level}"> <input type="hidden"
									name="exampaper.employeename"
									value="${result.exampaper.employeename}"> <input
									type="hidden" name="exampaper.title"
									value="${result.exampaper.title}"> <input type="hidden"
									name="exampaper.description"
									value="${result.exampaper.description}">
								<!-- 隐藏提交方式 -->
								<input type="hidden" name="quickGenePaperMethod"
									id="quickGenePaperMethod">
							</form>
						</div>
					</div>
					<div class="row el_generateRow">
						<div class="el_section">
							<input type="button" class="btn btn-primary" value="生成试卷"
								id="autoGenePaper" /> <input type="button"
								class="btn btn-danger" name="back" value="返回"
								onclick="javascript:history.back(-1);" />
						</div>
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
