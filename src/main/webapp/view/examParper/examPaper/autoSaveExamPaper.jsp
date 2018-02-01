<!-- 根据知识点与随机抽取试题生成试题之后的页面 -->
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
<title>随机自动生成试卷试卷</title>
<!-- 设置一个JS全局变量记录项目名字 -->
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
	var addQues = 0;//记录手动添加的试题
</script>
<%@ include file="/public/cssJs.jsp"%>

<!--试题包-->
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/examParper/addExamparper/wenjuan_ht.css">
<script
	src="<%=path%>/js/examParper/addExamparper/jquery17/jquery.min.js"></script>
<script>
	var $7 = $.noConflict(true);
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script src="<%=path%>/js/examParper/addExamparper/index3question.js"></script>
<!--放编辑试题-->

<!--富文本框-->
<link rel="stylesheet"
	href="<%=path%>/controls/kindEditor/themes/default/default.css" />
<script charset="utf-8"
	src="<%=path%>/controls/kindEditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="<%=path%>/controls/kindEditor/zh-CN.js"></script>

<!--试题批量操作js-->
<script src="<%=path%>/js/examParper/indexExam/quesBatch.js"></script>

<!--试题保存-->
<link rel="stylesheet" href="<%=path%>/css/examParper/indexExam.css">
<script src="<%=path%>/js/examParper/examPaper/autoSaveExamPaper.js"></script>
<script>
	/* 回到顶部 */
	$(function() {
		$(window).scroll(function() {
			if ($(window).scrollTop() > 500) {
				$('#el_returnTop').css('display', 'block');
			} else {
				$('#el_returnTop').css('display', 'none');
			}
		});

		/* 导航全选 */
		$(".el_Inputlulli1 input").change(function() {
			if ($(this).prop("checked") == true) {
				$(".el_Inputlulli input").prop("checked", "true");
			} else {
				$(".el_Inputlulli input").removeAttr("checked")
			}
		});

	});
</script>
</head>
<body>

	<!--头-->
	<jsp:include page="/view/public/header.jsp"></jsp:include>

	<!--中部-->
	<div class="html_middle">

		<!--放菜单框-->
		<div class="el_left">
			<jsp:include page="/view/public/menu.jsp"></jsp:include>
			<!--回到顶部-->
			<a href="#goTop">
				<div id="el_returnTop">
					<span> &#9650; 回到顶部</span>
				</div>
			</a>
		</div>

		<!--放主界面内容-->
		<div class="el_right">
			<!--顶部锚-->
			<a name="goTop"></a>
			<div class="container-fluid">

				<div class="panel panel-default">
					<!--菜单连接标题-->
					<div class="panel-heading">
						<span>创建试卷</span><span>>随机自动生成试卷</span>
						<ul class="nav navbar-nav navbar-right">
							<li><button type="button" class="btn btn-default navbar-btn"
									onclick="conutAllInfo()" style="margin-top: -6px;">统计</button></li>

							<li><button id="el_saveModify" type="button"
									class="btn btn-default navbar-btn" style="margin-top: -6px;">保存</button></li>
						</ul>
					</div>
				</div>

				<!--试卷基本信息-->
				<div class="el_paperInfo">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"></button>
						<!--标题-->
						<h4 class="modal-title">请检查试卷基本信息</h4>
					</div>
					<form action="1.action" id="saveForm" method="post"
						class="el_examInfo">

						<div class="input-group el_modellist" role="toolbar">
							<span class="el_spans">试卷标题：</span> <input type="text" readonly
								class="form-control el_modelinput" name="exampaper.title"
								value="${exampaper.title}" />
						</div>
						<div class="input-group el_modellist" role="toolbar">
							<span class="el_spans">试卷总分：</span> <input type="text" readonly
								class="form-control el_modelinput" name="exampaper.paperscore"
								value="${exampaper.paperscore}" />
						</div>

						<div class="input-group el_modellist" role="toolbar">

							<span class="el_spans">试卷级别：</span> <select
								class="selectpicker form-control" title="请选择" readonly
								disabled="disabled">
								<option>--请选择--</option>
								<option value="1"
									<c:if test="${'1' eq exampaper.level}">selected</c:if>>一级（厂级）</option>
								<option value="2"
									<c:if test="${'2' eq exampaper.level}">selected</c:if>>二级（部门级）</option>
								<option value="3"
									<c:if test="${'3' eq exampaper.level}">selected</c:if>>三级（班级）</option>
							</select> <input type="hidden" name="exampaper.level"
								value="${exampaper.level}" />
						</div>

						<div class="input-group el_modellist" role="toolbar">
							<span class="el_spans">试卷描述：</span>
							<textarea readonly class="form-control" rows="1"
								name="exampaper.description">${exampaper.description}</textarea>
						</div>

						<div class="input-group el_modellist" role="toolbar">
							<span class="el_spans">创&nbsp;&nbsp;建&nbsp;人：</span> <input
								type="text" class="form-control el_modelinput" readonly
								name="exampaper.employeename" value="${exampaper.employeename}" />
						</div>

						<div class="input-group el_modellist" role="toolbar">
							<span class="el_spans">创建时间：</span> <input type="text"
								class="form-control el_modelinput" readonly
								value="<fmt:formatDate value='${exampaper.maketime }' pattern='yyyy-MM-dd HH:mm:ss'></fmt:formatDate>"
								name="exampaper.maketime" />
						</div>

						<!-- 单选，多选，判断 -->
						<div class="el_addEPtikuang_1 danxuan_content"
							style="display: none;"></div>
						<div class="el_addEPtikuang_1 duoxuan_content"
							style="display: none;"></div>
						<div class="el_addEPtikuang_1 panduan_content"
							style="display: none;"></div>

					</form>
				</div>
				<!-- /.modal-content -->

				<!--主要内容-->
				<div class="tab-content">
					<!--第二-->
					<div role="tabpanel" class="tab-pane active">

						<!--考试标题-->
						<h2>
							<input class="el_modifiedTitle" type="text" readonly="readonly"
								disabled="disabled" value="${exampaper.title }"
								style="background-color: #ffffff" />
						</h2>

						<!-- 单选题 -->
						<c:if test="${!empty result.danxuan_list }">
							<div class="elaquesTitle_father el_addEPtikuang">
								<div class="elquesTitle movie_box_title">
									<h4 class="el_BigTitle danxuanBigTitle">
										<span class="bigNum"></span>、 <span><input
											class="el_modifiedTitle" type="text" value="单选题"
											onblur="updateInputBigQuesName(this)"> </span> <span>（每道题<input
											type="text" class="el_modifiedGrade"
											value="${danxuan_score }" onchange="updateTotalScore(this)"
											onblur="updateInputEveryQuesScore(this)"></span> <span>
											分；共</span> <span class="numTotal">${danxuan_score*fn:length(result.danxuan_list)}分/</span>
										<span class="numQues">${fn:length(result.danxuan_list)}题)</span>
									</h4>
								</div>

								<div class="el_tiBoxMainDelBox">
									<a href='javascript:void(0)' class='bigss'><span  title='点击收起题目' class='glyphicon glyphicon-chevron-up'></span></a>
									<a href='javascript:void(0)' class='bigsy'>上移</a>
									 <a
										href='javascript:void(0)' class='bigxy'>下移</a>&nbsp; <a
										href="javascript:void(0)" class="el_tiBoxMainDel">删除所有</a>
								</div>

								<div class="yd_box el_danxuan">

									<c:forEach items="${result.danxuan_list }" var="danxuanti"
										varStatus="status">
										<div class="movie_box">
											<div class="tm_btitlt">
												<i class="nmb">${status.count }</i>. <i class="btwenzi">${danxuanti.question }</i>
											</div>
											<ul class="wjdc_list">
												<c:forEach items="${danxuanti.options }" var="option">
													<li><label>
															${my:replace(option.optionsequence,"12345","ABCDE") }&nbsp;&nbsp;
															<span>${option.optioncontent }</span>
													</label></li>
												</c:forEach>

											</ul>
											答案: <input type="hidden" class="ques_answer"
												value="${danxuanti.answer }">
											${my:replace(danxuanti.answer,"12345","ABCDE") }<br />
											<%-- 解析:<input type="hidden" class="ques_analy"
													value="${danxuanti.analysis }">${danxuanti.analysis } --%>
										</div>
									</c:forEach>
								</div>
							</div>
						</c:if>



						<!--多选题-->
						<c:if test="${!empty result.duoxuan_list }">
							<div class="elaquesTitle_father el_addEPtikuang">
								<div class="elquesTitle movie_box_title">
									<h4 class="el_BigTitle duoxuanBigTitle">
										<span class="bigNum"></span>、 <span><input
											class="el_modifiedTitle" type="text" value="多选题"
											onblur="updateInputBigQuesName(this)"> </span> <span>(每道题
											<input type="text" class="el_modifiedGrade"
											value="${duonxuan_score }" onchange="updateTotalScore(this)"
											onblur="updateInputEveryQuesScore(this)">
										</span><span>分;共</span><span class="numTotal">${danxuan_score*fn:length(result.duoxuan_list)}分/</span><span
											class="numQues">${fn:length(result.duoxuan_list)}题)</span>
									</h4>
								</div>

								<div class="el_tiBoxMainDelBox">
									<a href='javascript:void(0)' class='bigss'><span  title='点击收起题目' class='glyphicon glyphicon-chevron-up'></span></a>
									<a href='javascript:void(0)' class='bigsy'>上移</a> <a
										href='javascript:void(0)' class='bigxy'>下移</a>&nbsp; <a
										href="javascript:void(0)" class="el_tiBoxMainDel">删除所有</a>
								</div>

								<div class="yd_box el_duoxuan">
									<c:forEach items="${result.duoxuan_list }" var="duoxuanti"
										varStatus="status">
										<div class="movie_box">
											<div class="tm_btitlt">
												<i class="nmb">${status.count }</i>. <i class="btwenzi">${duoxuanti.question }</i>
											</div>
											<ul class="wjdc_list">
												<c:forEach items="${duoxuanti.options }" var="option">
													<li><label>${my:replace(option.optionsequence,"12345","ABCDE") }&nbsp;&nbsp;
															<span>${option.optioncontent }</span>
													</label></li>
												</c:forEach>
											</ul>
											答案: <input type="hidden" class="ques_answer"
												value="${duoxuanti.answer }">${my:replace(duoxuanti.answer,"12345","ABCDE") }<br />
											<%-- 解析:<input type="hidden" class="ques_analy"
													value="${duoxuanti.analysis }">${duoxuanti.analysis } --%>
										</div>
									</c:forEach>
								</div>

							</div>
						</c:if>
						<!--判断题-->
						<c:if test="${!empty result.panduan_list }">
							<div class="elaquesTitle_father el_addEPtikuang">
								<div class="elquesTitle movie_box_title">
									<h4 class="el_BigTitle panduanBigTitle">
										<span class="bigNum"></span>、 <span><input
											class="el_modifiedTitle" type="text" value="判断题"
											onblur="updateInputBigQuesName(this)"> </span> <span>（每道题
											<input type="text" class="el_modifiedGrade"
											value="${panduan_score }" onchange="updateTotalScore(this)"
											onblur="updateInputEveryQuesScore(this)">
										</span><span> 分；共</span><span class="numTotal">${panduan_score*fn:length(result.panduan_list)}分/</span>
										<span class="numQues">${fn:length(result.panduan_list)}题)</span>
									</h4>
								</div>

								<div class="el_tiBoxMainDelBox">
									<a href='javascript:void(0)' class='bigss'><span  title='点击收起题目' class='glyphicon glyphicon-chevron-up'></span></a>
									<a href='javascript:void(0)' class='bigsy'>上移</a> <a
										href='javascript:void(0)' class='bigxy'>下移</a>&nbsp; <a
										href="javascript:void(0)" class="el_tiBoxMainDel">删除所有</a>
								</div>

								<div class="yd_box el_panduan">
									<c:forEach items="${result.panduan_list }" var="panduanti"
										varStatus="status">
										<div class="movie_box">
											<div class="tm_btitlt">
												<i class="nmb">${status.count }</i>. <i class="btwenzi">${panduanti.question}</i>
											</div>
											<ul class="wjdc_list">
												<%-- <c:forEach items="panduanti.options" var="option"> --%>
												<ul class="wjdc_list">
													<li><label> A &nbsp;&nbsp;<span>正确</span>
													</label></li>
													<li><label> B &nbsp;&nbsp;<span>错误</span>
													</label></li>
												</ul>
												<%-- </c:forEach> --%>
											</ul>
											答案: <input type="hidden" class="ques_answer"
												value="${panduanti.answer }">${my:replace(panduanti.answer,"12345","ABCDE") }<br />
											<%-- 解析:<input type="hidden" class="ques_analy"
													value="${panduanti.analysis }">${panduanti.analysis } --%>
										</div>
									</c:forEach>
								</div>
							</div>
						</c:if>
						<br /> <br /> <br />


					</div>
				</div>

				<!--试题区域-->
				<div class="all_660">
					<!--放试题的区域-->
					<div class="yd_box"></div>

					<!--选项卡区域  模板区域---------------------------------------------------------------------------------------------------------------------------------------->
					<div class="xxk_box">
						<div class="xxk_conn hide">
							<!--单选----------------------------------------------------------------------------------------------------------------------------------------->
							<div class="xxk_xzqh_box danxuan">

								<!--标题-->
								<textarea id="editor_id1" name="content" cols="" rows=""
									class="input_wenbk btwen_text">
                单选题目(题干)</textarea>

								<!--选项-->
								<div class="title_itram">
									<div class="kzjxx_iteam">
										<span class="el_chooseButton"><label><input
												class="el_answerRadio" name="el_answerRaido" type="radio" />设置为答案</label></span>
										<input name="" type="text" class="input_wenbk" value=""
											placeholder="选项">
									</div>

									<div class="kzjxx_iteam">
										<span class="el_chooseButton"><label><input
												class="el_answerRadio" name="el_answerRaido" type="radio" />设置为答案</label></span>
										<input name="" type="text" class="input_wenbk" value=""
											placeholder="选项">
									</div>

									<div class="kzjxx_iteam">
										<span class="el_chooseButton"><label><input
												class="el_answerRadio" name="el_answerRaido" type="radio" />设置为答案</label></span>
										<input name="" type="text" class="input_wenbk" value=""
											placeholder="选项">
									</div>

									<div class="kzjxx_iteam">
										<span class="el_chooseButton"><label><input
												class="el_answerRadio" name="el_answerRaido" type="radio" />设置为答案</label></span>
										<input name="" type="text" class="input_wenbk" value=""
											placeholder="选项">
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

								<textarea id="editor_id2" name="content" cols="" rows=""
									class="input_wenbk btwen_text"
									style="width: 100%; height: 200px;">
                多选题目asdfasdfasdfasdf</textarea>
								<div class="title_itram">
									<div class="kzjxx_iteam">
										<span class="el_chooseButton"><label><input
												class="el_answerCheck" name="el_answerCheck" type="checkbox" />设置为答案</label></span>
										<input name="" type="text" class="input_wenbk"
											placeholder="选项">
									</div>

									<div class="kzjxx_iteam">
										<span class="el_chooseButton"><label><input
												class="el_answerCheck" name="el_answerCheck" type="checkbox" />设置为答案</label></span>
										<input name="" type="text" class="input_wenbk"
											placeholder="选项">
									</div>

									<div class="kzjxx_iteam">
										<span class="el_chooseButton"><label><input
												class="el_answerCheck" name="el_answerCheck" type="checkbox" />设置为答案</label></span>
										<input name="" type="text" class="input_wenbk"
											placeholder="选项">
									</div>

									<div class="kzjxx_iteam">
										<span class="el_chooseButton"><label><input
												class="el_answerCheck" name="el_answerCheck" type="checkbox" />设置为答案</label></span>
										<input name="" type="text" class="input_wenbk"
											placeholder="选项">
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
									style="width: 100%; height: 200px;">
                答题asdfasdf区</textarea>
								<div class="title_itram">
									<div class="kzjxx_iteam">
										<ul id="el_panduanChoose">
											<li><span><label><input
														class="el_answerPanduan" name="el_answerPanduan"
														type="radio" /> 设置为答案</label></span> <span>&nbsp;&nbsp;正确</span></li>
											<li><span><label><input
														class="el_answerPanduan" name="el_answerPanduan"
														type="radio" /> 设置为答案</label></span> <span>&nbsp;&nbsp;错误</span></li>
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

			<br /> <br /> <br /> <br /> <br />
		</div>
	</div>



	<!--放脚-->
	<jsp:include page="/view/public/footer.jsp"></jsp:include>
</body>
</html>
