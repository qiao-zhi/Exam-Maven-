<!-- 修改试卷的页面 -->
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
<%@ include file="/public/cssJs.jsp"%>
<title>试卷修改</title>
<!--S qlq写的JS(根据题库生成试卷) -->
<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
	var addQues = 0;//记录手动添加的试题
	var bigNum_q = 0;// 记录手动添加的试题
</script>
<!--右边根据题库产生试题-->
<script
	src="<%=path%>/js/examParper/examPaper/updatePaper_QueryBankQues.js"></script>
<!--E  qlq写的JS(根据题库生成试卷) -->

<script
	src="<%=path%>/js/examParper/addExamparper/jquery17/jquery.min.js"></script>
<!--试题包-->
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/examParper/addExamparper/wenjuan_ht.css">
<script>
	var $7 = $.noConflict(true);
</script>
<script src="<%=path%>/js/examParper/addExamparper/index3question.js"></script>
<!--放编辑试题-->

<!--富文本框-->
<link rel="stylesheet"
	href="<%=path%>/controls/kindEditor/themes/default/default.css" />
<script charset="utf-8"
	src="<%=path%>/controls/kindEditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="<%=path%>/controls/kindEditor/zh-CN.js"></script>

<!--试题编辑框-->
<link rel="stylesheet"
	href="<%=path%>/css/examParper/addExamparper/addExamparperLeft.css">

<link rel="stylesheet"
	href="<%=path%>/css/examParper/addExamparper/addExamparper.css">
<!--试题批量操作js-->
<script src="<%=path%>/js/examParper/addExamparper/quesBatch.js"></script>
<!--题库往试题区拉试题js-->
<script src="<%=path%>/js/examParper/examPaper/update_paper_tiku.js"></script>

<!--试题保存-->
<link rel="stylesheet" href="<%=path%>/css/examParper/indexExam.css">
<!-- 修改JS，相当于重新添加 -->
<script src="<%=path%>/js/examParper/examPaper/updateExamPaper.js"></script>

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
				<div class="panel panel-default">
					<!--菜单连接标题-->
					<div class="panel-heading">
						<span>试卷管理</span><span>>修改试卷</span>
						<ul class="nav navbar-nav navbar-right">
							<li><button type="button" class="btn btn-default navbar-btn"
									onclick="conutAllInfo()" style="margin-top: -6px;">统计</button></li>

							<li><button id="el_saveModify" type="button"
									class="btn btn-default navbar-btn" style="margin-top: -6px;">保存</button></li>
						</ul>
					</div>
					<div class="panel-body">

						<!--内容-->
						<div class="el_addEPleft">

							<!-- 最终提交表单的form -->
							<form action="" id="saveForm">
								<table class="table">
									<thead>
										<tr>
											<th>试卷基本信息</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>试卷名称</td>
											<td><input type="text" class="" name="exampaper.title"
												value="${exampaper.title}" /> <%--注释内容 --%> <input
												type="hidden" class="" name="exampaper.paperid"
												value="${exampaper.paperid}" /></td>
										</tr>
										<tr>
											<td>试卷级别</td>
											<td><select name="" id="exampaper.level"
												disabled="disabled" style="background-color: #FFFFFF;">
													<option value="">--请选择--</option>
													<option
														<c:if test="${'1' eq exampaper.level}">selected</c:if>
														value="1">一级(厂级)</option>
													<option
														<c:if test="${'2' eq exampaper.level}">selected</c:if>
														value="2">二级（部门级）</option>
													<option
														<c:if test="${'3' eq exampaper.level}">selected</c:if>
														value="3">三级（班级）</option>
											</select> <input type="hidden" name="exampaper.level"
												value="${exampaper.level}" /></td>
										</tr>

										<tr>
											<td>试卷分数</td>
											<td><input type="text" name="exampaper.paperscore"
												value="${exampaper.paperscore }"></td>
										</tr>
										<tr>
											<td>使用次数</td>
											<td><input type="text" readonly="readonly"
												name="exampaper.usetimes" value="${exampaper.usetimes }"></td>
										</tr>
										<tr>
											<td>试卷描述</td>
											<td><textarea class="" rows="2" cols="20"
													name="exampaper.description">${exampaper.description }</textarea>
											</td>
										</tr>
										<tr>
											<td>创&nbsp;&nbsp;建&nbsp;&nbsp;人</td>
											<td><input type="text" class=""
												name="exampaper.employeename"
												value="${exampaper.employeename }" readonly="readonly" /></td>
										</tr>
										<tr>
											<td>创建时间</td>
											<td><input type="text" id="test4"
												name="exampaper.maketime"
												class="wicon form-control el_noVlaue"
												value='<fmt:formatDate value="${exampaper.maketime }" pattern="yyyy-MM-dd HH:mm:ss"/>'
												readonly /></td>
										</tr>
									</tbody>
								</table>

								<!-- 单选，多选，判断 -->
								<div class="el_addEPtikuang danxuan_content"
									style="display: none;"></div>
								<div class="el_addEPtikuang duoxuan_content"
									style="display: none;"></div>
								<div class="el_addEPtikuang panduan_content"
									style="display: none;"></div>

								<!-- <div class="col-md-2 el_addEPtongji">
									<button type="button" id="el_resetButton"
										class="btn btn-default btn-sm">统计刷新</button>
									<a href="javascript:void(0)" id="el_saveA">
										<button type="button" id="el_saveModify"
											class="btn btn-primary el_addPopration btn-sm">保存</button>
									</a> &nbsp;&nbsp; <input type="button"
										class="btn btn-default btn-sm" name="back" value="返回"
										onclick="javascript:history.back(-1);" />
								</div> -->
							</form>
							<div>
								<br />
							</div>

							<!--题型
                    点击题型，右侧的题库，也跟着解锁相应类型的试题
                -->
							<div class="el_addEPtixing">
								<!--添加试题的文本域
                        自动生成分数框，题，可删除
                    -->


								<%--遍历大题 --%>
								<c:forEach items="${exampaper.bigQuestions }" var="bigQuestion"
									varStatus="status">
									<!--单选-->
									<%-- bigQuestion不为空并且其内部第一道题是单选题--%>
									<c:if
										test="${!empty bigQuestion && bigQuestion.questions[0].type eq '单选题' }">
										<div class="el_addEPtikuang">
											<!--大题标题-->
											<div class="elquesTitle movie_box_title">
												<h4 class="el_BigTitle danxuanBigTitle"
													style="margin-top: 0px; padding-top: 0px;">
													${bigQuestion.bigquestionname }</h4>
											</div>

											<!-- 上下移动 -->
											<div class="el_tiBoxMainDelBox">
												<a href='javascript:void(0)' class='bigss'><span
													title='点击收起题目' class='glyphicon glyphicon-chevron-up'></span></a>
												<a href='javascript:void(0)' class='bigsy'>上移</a> <a
													href='javascript:void(0)' class='bigxy'>下移</a>&nbsp; <a
													href="javascript:void(0)" class="el_tiBoxMainDel">删除所有</a>
											</div>

											<!-- 小题 -->
											<div class="yd_box el_danxuan">
												<c:forEach items="${bigQuestion.questions }" var="question">
													<div class="movie_box">
														<!-- 题干与序号 -->
														<div class="tm_btitlt">
															<i class="nmb">${question.questionsequence + 1 }</i>. <i
																class="btwenzi">${question.questioncontent }</i>
														</div>
														<ul class="wjdc_list">
															<!-- 遍历选项 -->
															<c:forEach items="${question.options }" var="option">
																<li><label> <input name="a2" type="radio"
																		value=""> <span>${option.optioncontent }</span>
																</label></li>
															</c:forEach>
														</ul>
														<input type="hidden" id="${question.questionid }"
															class="ques_id"> 答案: <input type="hidden"
															class="ques_answer" value="${question.answer }">${my:replace(question.answer,"12345","ABCDE") }<br />
														<%-- 解析:<input type="hidden" class="ques_analy"
															value="${question.analysis }">${question.analysis } --%>
													</div>
												</c:forEach>
											</div>
										</div>
									</c:if>
									<!--多选-->
									<%-- bigQuestion不为空并且其内部第一道题是多选题--%>
									<c:if
										test="${!empty bigQuestion && bigQuestion.questions[0].type eq '多选题' }">
										<div class="el_addEPtikuang">
											<!--大题标题-->
											<div class="elquesTitle movie_box_title">
												<h4 class="el_BigTitle duoxuanBigTitle"
													style="margin-top: 0px; padding-top: 0px;">
													${bigQuestion.bigquestionname }</h4>
											</div>

											<!-- 上下移动 -->
											<div class="el_tiBoxMainDelBox">
												<a href='javascript:void(0)' class='bigss'><span
													title='点击收起题目' class='glyphicon glyphicon-chevron-up'></span></a>
												<a href='javascript:void(0)' class='bigsy'>上移</a> <a
													href='javascript:void(0)' class='bigxy'>下移</a>&nbsp; <a
													href="javascript:void(0)" class="el_tiBoxMainDel">删除所有</a>
											</div>

											<!-- 小题 -->
											<div class="yd_box el_duoxuan">
												<c:forEach items="${bigQuestion.questions }" var="question">
													<div class="movie_box">
														<!-- 题干与序号 -->
														<div class="tm_btitlt">
															<i class="nmb">${question.questionsequence + 1}</i>. <i
																class="btwenzi">${question.questioncontent }</i>
														</div>
														<ul class="wjdc_list">
															<!-- 遍历选项 -->
															<c:forEach items="${question.options }" var="option">
																<li><label> <input name="a2"
																		type="checkbox" value=""> <span>${option.optioncontent }</span>
																</label></li>
															</c:forEach>
														</ul>
														<input type="hidden" id="${question.questionid }"
															class="ques_id"> 答案: <input type="hidden"
															class="ques_answer" value="${question.answer }">${my:replace(question.answer,"12345","ABCDE") }<br />
														<%-- 	解析:<input type="hidden" class="ques_analy"
															value="${question.analysis }">${question.analysis } --%>
													</div>
												</c:forEach>
											</div>
										</div>
									</c:if>
									<!--判断-->
									<%-- bigQuestion不为空并且其内部第一道题是判断题--%>
									<c:if
										test="${!empty bigQuestion && bigQuestion.questions[0].type eq '判断题' }">
										<div class="el_addEPtikuang">
											<!--大题标题-->
											<div class="elquesTitle movie_box_title">
												<h4 class="el_BigTitle panduanBigTitle"
													style="margin-top: 0px; padding-top: 0px;">
													${bigQuestion.bigquestionname }</h4>
											</div>

											<!-- 上下移动 -->
											<div class="el_tiBoxMainDelBox">
												<a href='javascript:void(0)' class='bigss'><span
													title='点击收起题目' class='glyphicon glyphicon-chevron-up'></span></a>
												<a href='javascript:void(0)' class='bigsy'>上移</a> <a
													href='javascript:void(0)' class='bigxy'>下移</a>&nbsp; <a
													href="javascript:void(0)" class="el_tiBoxMainDel">删除所有</a>
											</div>

											<!-- 小题 -->
											<div class="yd_box el_panduan">
												<c:forEach items="${bigQuestion.questions }" var="question">
													<div class="movie_box">
														<!-- 题干与序号 -->
														<div class="tm_btitlt">
															<i class="nmb">${question.questionsequence + 1 }</i>. <i
																class="btwenzi">${question.questioncontent }</i>
														</div>
														<ul class="wjdc_list">
															<!-- 遍历选项 -->
															<c:forEach items="${question.options }" var="option">
																<li><label> <input name="a2" type="radio"
																		value=""> <span>${option.optioncontent }</span>
																</label></li>
															</c:forEach>
														</ul>
														<input type="hidden" id="${question.questionid }"
															class="ques_id"> 答案: <input type="hidden"
															class="ques_answer" value="${question.answer }">${question.answer }<br />
														<%-- 	解析:<input type="hidden" class="ques_analy"
															value="${question.analysis }">${question.analysis } --%>
													</div>
												</c:forEach>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>



							<!--试题区域
                    通过点击编辑按钮，弹出试题编辑区域
                -->
							<div class="all_660">
								<!--放试题的区域-->
								<div class="yd_box"></div>

								<!--选项卡区域  模板区域---------------------------------------------------------------------------------------------------------------------------------------->
								<div class="xxk_box">
									<div class="xxk_conn hide">

										<!--单选----------------------------------------------------------------------------------------------------------------------------------------->
										<div class="xxk_xzqh_box danxuan">

											<!--标题-->
											<textarea id="editor_id1" name="content"
												style="width: 100%; height: 100px;"
												class="input_wenbk btwen_text">
                                        单选题目(题干)</textarea>

											<!--选项-->
											<div class="title_itram">
												<div class="kzjxx_iteam">
													<span class="el_chooseButton"> <label><input
															name="el_answerRaido" class="el_answerRadio" type="radio" />设置为答案</label>
													</span> <input name="" type="text" class="input_wenbk" value=""
														placeholder="选项"> <input type="hidden" value="1"
														class="option_num">
													<!--序号-->
												</div>

												<div class="kzjxx_iteam">
													<span class="el_chooseButton"> <label><input
															name="el_answerRaido" class="el_answerRadio" type="radio" />设置为答案</label>
													</span> <input name="" type="text" class="input_wenbk" value=""
														placeholder="选项"> <input type="hidden" value="2"
														class="option_num">
													<!--序号-->
												</div>

												<div class="kzjxx_iteam">
													<span class="el_chooseButton"><label><input
															name="el_answerRaido" class="el_answerRadio" type="radio" />设置为答案</label></span>
													<input name="" type="text" class="input_wenbk" value=""
														placeholder="选项"> <input type="hidden" value="3"
														class="option_num">
													<!--序号-->
												</div>

												<div class="kzjxx_iteam">
													<span class="el_chooseButton"><label> <input
															name="el_answerRaido" type="radio" class="el_answerRadio" />设置为答案
													</label> </span> <input name="" type="text" class="input_wenbk" value=""
														placeholder="选项"> <input type="hidden" value="4"
														class="option_num">
													<!--序号-->
												</div>
											</div>

											<!--增加选项-->
											<a href="javascript:void(0)" class="zjxx">增加选项</a>


											<!--完成编辑-->
											<div class="bjqxwc_box">
												<a href="javascript:void(0);" class="qxbj_but">取消编辑</a> <a
													href="javascript:void(0);" class="swcbj_but"> 完成编辑</a>
											</div>
										</div>

										<!--多选----------------------------------------------------------------------------------------------------------------------------------------->
										<div class="xxk_xzqh_box duoxuan hide">

											<textarea id="editor_id2" name="content"
												class="input_wenbk btwen_text"
												style="width: 100%; height: 100px;">
                            多选题目asdf</textarea>

											<div class="title_itram">
												<div class="kzjxx_iteam">
													<span class="el_chooseButton"><label><input
															name="el_answerCheck" class="el_answerCheck"
															type="checkbox" />设置为答案</label></span> <input name="" type="text"
														class="input_wenbk" value="选项" placeholder="选项"> <input
														type="hidden" value="1" class="option_num">
													<!--序号-->
												</div>

												<div class="kzjxx_iteam">
													<span class="el_chooseButton"><label><input
															name="el_answerCheck" class="el_answerCheck"
															type="checkbox" />设置为答案</label></span> <input name="" type="text"
														class="input_wenbk" value="选项" placeholder="选项"> <input
														type="hidden" value="2" class="option_num">
													<!--序号-->
												</div>

												<div class="kzjxx_iteam">
													<span class="el_chooseButton"><label><input
															name="el_answerCheck" class="el_answerCheck"
															type="checkbox" />设置为答案</label></span> <input name="" type="text"
														class="input_wenbk" value="选项" placeholder="选项"> <input
														type="hidden" value="3" class="option_num">
													<!--序号-->
												</div>

												<div class="kzjxx_iteam">
													<span class="el_chooseButton"><label><input
															name="el_answerCheck" class="el_answerCheck"
															type="checkbox" />设置为答案</label></span> <input name="" type="text"
														class="input_wenbk" value="选项" placeholder="选项"> <input
														type="hidden" value="4" class="option_num">
													<!--序号-->
												</div>
											</div>

											<a href="javascript:void(0)" class="zjxx">增加选项</a>


											<!--完成编辑-->
											<div class="bjqxwc_box">
												<a href="javascript:void(0);" class="qxbj_but">取消编辑</a> <a
													href="javascript:void(0);" class="swcbj_but"> 完成编辑</a>
											</div>
										</div>

										<!-- 判断----------------------------------------------------------------------------------------------------------------------------------------->
										<div class="xxk_xzqh_box panduan hide">
											<textarea id="editor_id3" name="content" cols="" rows=""
												class="input_wenbk btwen_text btwen_text_tk"
												style="width: 100%; height: 100px;">答题asdfasdf区</textarea>
											<div class="title_itram">
												<div class="kzjxx_iteam el_panduan_ti">
													<ul id="el_panduanChoose">
														<li><span class="el_chooseButton"> <label><input
																	name="el_answerPanduan" class="el_answerPanduan"
																	type="radio" /> 设置为答案</label>
														</span> <span>&nbsp;&nbsp;正确</span> <input type="hidden"
															value="1" class="option_num"> <!--序号--></li>
														<li><span class="el_chooseButton"> <label><input
																	name="el_answerPanduan" class="el_answerPanduan"
																	type="radio" /> 设置为答案</label>
														</span> <span>&nbsp;&nbsp;错误</span> <input type="hidden"
															value="2" class="option_num"> <!--序号--></li>
													</ul>
												</div>
											</div>

											<!--完成编辑-->
											<div class="bjqxwc_box">
												<a href="javascript:void(0);" class="qxbj_but">取消编辑</a> <a
													href="javascript:void(0);" class="swcbj_but"> 完成编辑</a>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>

						<!--显示题库按钮图标-->
						<img src="<%=path%>/image/show.png" title="显示题库" id="show_tiku" />
						<!--题库-->
						<div class="el_addEPtiku">
							<!--标题-->
							<div class="panel panel-default">
								<p id="el_tikuTitle">题库试题</p>
								<img src="<%=path%>/image/hide.png" title="隐藏题库" id="hide_tiku" />
								<!--索引-->
								<div class="panel-body el_addEPquery">
									<form>
										<div class="el_qlmQuery">
											<div class="input-group" role="toolbar">
												<span class="el_spans"> <input type="radio"
													name="selectType" onclick="initEmployeeTypeDic()">工种&nbsp;&nbsp;&nbsp;
													<input type="radio" name="selectType"
													onclick="initKnowledgeDic()">知识点
												</span> <select class="combox form-control" id="knowSelect"
													name="dictionaryId">
													<option value="">--请选择--</option>
												</select> <br />
											</div>
										</div>
										<br />
										<!-- <div style="float:left"></div> -->

										<div class="el_qlmQuery2">
											<div class="input-group" role="toolbar">
												<span class="el_spans">试题类型</span> <select
													class="form-control" name="type">
													<option value="">--请选择--</option>
													<option value="单选题">单选题</option>
													<option value="多选题">多选题</option>
													<option value="判断题">判断题</option>
												</select>
											</div>
										</div>

										<div class="el_qlmQuery3">
											<!--清空按钮-->
											<button type="button" class="btn btn-primary btn-sm"
												id="queryBankBtn" style="margin-right: 50px;">查询</button>
											<button type="reset"
												class="btn btn-default el_queryButton0 btn-sm"
												style="border-bottom-width: 1px; bottom: 1px; right: 52px; margin-right: -58px;">清空</button>
										</div>
									</form>
								</div>

								<ul class="list-group el_queryQuestions" id="bankQuestions">

								</ul>

							</div>
						</div>

					</div>

					<br /> <br /> <br /> <br /> <br />
				</div>
			</div>

			<div id="dongtaijs"></div>
			<!--统计模态框-->
			<div class="modal fade" tabindex="-1" role="dialog" id="myModal"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">试题统计</h4>
						</div>
						<div class="modal-body el_ulParent">
							<ul class="el_lul">
								<li class="el_lulli">单选题</li>
								<li class="el_lulli2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题数:&nbsp;<u
									id="dan_num">0</u></li>
								<li class="el_lulli2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分值:&nbsp;<u
									id="dan_score">0</u></li>
								<li class="el_lulli2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总分:&nbsp;<u
									id="dan_total">0</u>&nbsp;&nbsp;&nbsp;
								</li>
							</ul>
							<ul class="el_lul">
								<li class="el_lulli">多选题</li>
								<li class="el_lulli2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题数:&nbsp;<u
									id="duo_num">0</u></li>
								<li class="el_lulli2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分值:&nbsp;<u
									id="duo_score">0</u></li>
								<li class="el_lulli2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总分:&nbsp;<u
									id="duo_total">0</u></li>
							</ul>
							<ul class="el_lul">
								<li class="el_lulli">判断题</li>
								<li class="el_lulli2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题数:&nbsp;<u
									id="pan_num">0</u></li>
								<li class="el_lulli2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分值:&nbsp;<u
									id="pan_score">0</u></li>
								<li class="el_lulli2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总分:&nbsp;<u
									id="pan_total">0</u></li>
							</ul>
							<div class="el_zongfen">
								总分:<u id="all_total">0</u>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!--放脚-->
		</div>
	</div>
	<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>
