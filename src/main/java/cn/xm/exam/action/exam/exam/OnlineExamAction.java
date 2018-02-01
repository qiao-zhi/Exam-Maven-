package cn.xm.exam.action.exam.exam;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.exam.Bigquestion;
import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.bean.exam.Exampaperoption;
import cn.xm.exam.bean.exam.Exampaperquestion;
import cn.xm.exam.bean.exam.Onlineexamanswerinfor;
import cn.xm.exam.bean.exam.Onlineexaminfor;
import cn.xm.exam.service.exam.exam.OnlineExamAnswerInfoService;
import cn.xm.exam.service.exam.exam.OnlineExamService;
import cn.xm.exam.service.grade.EmployeeExamService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.RemoveHtmlTag;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.utils.ValidateCheck;
import cn.xm.exam.vo.grade.OnlineExamEmployeeInfo;
import net.sf.json.JSONObject;

/**
 * 
 * 项目名称：Exam 类名称：OnlineExamAction 类描述： 在线考试的action 创建人：Leilong 创建时间：2017年10月28日
 * 下午5:24:28
 * 
 * @version
 * 
 */
@Controller
@Scope("prototype")
public class OnlineExamAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private OnlineExamService onlineExamService;
	@Resource
	private OnlineExamAnswerInfoService onlineExamAnswerInfoService;
	@Resource
	private EmployeeExamService employeeExamService;
	private Map<String, Object> result;
	// 身份证号
	private String idCard;
	// 考试ID
	private String examId;
	// 在线考试的答案集合
	private List<Onlineexamanswerinfor> onlineExamAnswerInfos;
	// 试卷ID
	private String paperId;
	// 在线考试表的员工信息
	private Onlineexaminfor onlineExamInfor;
	// 考试名称
	private String examName;
	// 考试等级
	private String examLevel;
	// 考试状态
	private String examStatus;
	// 考试时间
	private String examTime;
	// 当前页
	private String currentPage;
	// 当前页显示的条数
	private String currentCount;

	// 根据员工考号获取所有未开始的考试信息
	public String findAllNotStartExamInfo() {
		try {
			result = new HashMap<String, Object>();
			List<OnlineExamEmployeeInfo> notStartExamInfo = onlineExamService.getNotStartExamInfoByIdCard(idCard);
			result.put("notStartExamInfo", notStartExamInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 开始考试后保存在线考试的员工的基本信息
	public String saveOnlineExamEmployeeInfo() {
		try {
			onlineExamInfor.setOnlineexamid(UUIDUtil.getUUID2());
			onlineExamService.saveOnlineExamInfo(onlineExamInfor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 根据考试ID和身份证号获得在线考试的考试界面的所有信息
	public String findExamAndSpecificPaperInfo() {
		try {
			result = new HashMap<String, Object>();
			Map<String, Object> condition = new HashMap<String, Object>();
			Map<String, Object> examInfo = onlineExamService.findExamInfoByExamId(examId);
			String paperId = (String) examInfo.get("paperId");
			condition.put("paperId", paperId);
			condition.put("idCard", idCard);
			condition.put("examId", examId);
			Exampaper specificPaperInfo = onlineExamService.findExamPaperInfoByPaperId(condition);
			specificPaperInfo.setExamid(examId);
			// 将map转换成json格式字符串
			JSONObject jsonObjectFromMap = JSONObject.fromObject(examInfo);
			result.put("examInfo", jsonObjectFromMap.toString());
			result.put("specificPaperInfo", setQuestionsSeq(specificPaperInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}

	// 临时保存在线考试员工的答题信息,先将表中的数据清空
	public String saveOnlineExamAnswer() {
		try {
			String paperId = onlineExamAnswerInfos.get(101).getPaperid();
			String employeeId = onlineExamAnswerInfos.get(101).getEmployeeid();
			String onlineAnswerExamid = onlineExamAnswerInfos.get(101).getOnlineanswerexamid();
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("paperId", paperId);
			condition.put("idCard", employeeId);
			condition.put("onlineAnswerExamid", onlineAnswerExamid);
			onlineExamAnswerInfoService.deleteOnlineExamAnswerInfoBatch(condition);
			// 去除集合中的所有null元素
			onlineExamAnswerInfos.removeAll(Collections.singleton(null));
			for (Onlineexamanswerinfor onlineExamAnswerInfo : onlineExamAnswerInfos) {
				onlineExamAnswerInfo.setAnswerinfoid(UUIDUtil.getUUID2());
				if (onlineExamAnswerInfo.getSelectoption() != null) {
					onlineExamAnswerInfo
							.setSelectoption(onlineExamAnswerInfo.getSelectoption().replace(",", "").replace(" ", ""));
				}
				onlineExamAnswerInfo.setScore(0f);
			}
			boolean isSave = onlineExamAnswerInfoService.saveOnlineExamAnswerInfo(onlineExamAnswerInfos);

			result = new HashMap<String, Object>();
			if (isSave) {
				result.put("result", "保存成功！");
			} else {
				result.put("result", "保存失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 交卷后对考生的答案进行判断并设置分数
	public String checkOnlineExamInfoAnswer() {

		try {
			String paperId = onlineExamAnswerInfos.get(101).getPaperid();
			String employeeId = onlineExamAnswerInfos.get(101).getEmployeeid();
			String onlineAnswerExamid = onlineExamAnswerInfos.get(101).getOnlineanswerexamid();
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("paperId", paperId);
			condition.put("idCard", employeeId);
			condition.put("onlineAnswerExamid", onlineAnswerExamid);
			onlineExamAnswerInfoService.deleteOnlineExamAnswerInfoBatch(condition);
			// 去除集合中的所有null元素
			onlineExamAnswerInfos.removeAll(Collections.singleton(null));
			for (Onlineexamanswerinfor onlineExamAnswerInfo : onlineExamAnswerInfos) {
				onlineExamAnswerInfo.setAnswerinfoid(UUIDUtil.getUUID2());
				if (onlineExamAnswerInfo.getSelectoption() != null) {
					onlineExamAnswerInfo
							.setSelectoption(onlineExamAnswerInfo.getSelectoption().replace(",", "").replace(" ", ""));
				}
				onlineExamAnswerInfo.setScore(0f);
			}
			boolean isSave = onlineExamAnswerInfoService.saveOnlineExamAnswerInfo(onlineExamAnswerInfos);
			result = new HashMap<String, Object>();
			if (isSave) {
				condition.put("examId", examId);
				boolean isUpdate = onlineExamAnswerInfoService.updateEmployeeInScoreBatch(condition);
				if (isUpdate) {
					boolean isSet = employeeExamService.updateEmployeeInScoreByIdCard(condition);
					if (isSet) {
						result.put("result", "成功交卷！");
					} else {
						result.put("result", "交卷失败！");
					}
				} else {
					result.put("result", "交卷失败！");
				}
			} else {
				result.put("result", "交卷失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	// 交卷后查询当前考生的成绩信息并回显
	public String getOnlineExamEmployeeGradeInfo() {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition = generationCondition(condition);
			OnlineExamEmployeeInfo onlineExamEmployeeGrade = employeeExamService
					.getExamGardeByEmployeeIdAndExamId(condition);
			result = new HashMap<String, Object>();
			result.put("onlineExamEmployeeGrade", onlineExamEmployeeGrade);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "gradeInfo";
	}

	// 根据考试编号和身份证号查询在线考试员工的开始答题时间用于倒计时操作
	public String getOnlineExamStartAnswerTime() {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition = generationCondition(condition);
			OnlineExamEmployeeInfo onlineExamStartAnswerInfo = employeeExamService
					.getExamGardeByEmployeeIdAndExamId(condition);
			result = new HashMap<String, Object>();
			result.put("onlineExamStartAnswerInfo", onlineExamStartAnswerInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 点击查询试卷解析进行试卷及答案信息的回显
	public String echoOnlineExamPaperAndAnswerInfo() {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition = generationCondition(condition);
			Exampaper specificPaperInfo = onlineExamService.findExamPaperInfoByPaperId(condition);
			result = new HashMap<String, Object>();
			result.put("specificPaperInfo", setQuestionsSeq(specificPaperInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "paperAndAnswerInfo";
	}

	// 组合条件查询考试信息分页显示
	public String getOnlineExamInfoByCondition() {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition = generationCondition(condition);
			PageBean<OnlineExamEmployeeInfo> onlineExamInfo = onlineExamService.findOnlineExamInfoWithCondition(
					Integer.valueOf(currentPage), Integer.valueOf(currentCount), condition);
			result = new HashMap<String, Object>();
			result.put("pageBean", onlineExamInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 获取服务器时间
	public String getNowServerTime() {
		result = new HashMap<String, Object>();
		result.put("nowTime", new Date());
		return SUCCESS;
	}

	// 组装查询条件
	private Map<String, Object> generationCondition(Map<String, Object> condition) {

		// 对当前页信息进行设置
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		// 对当前页显示的信息进行设置
		if (currentCount == null || "".equals(currentCount.trim())) {
			currentCount = DefaultValue.PAGE_SIZE;
		}

		if (ValidateCheck.isNotNull(examId)) {
			condition.put("examId", examId);
		}

		if (ValidateCheck.isNotNull(idCard)) {
			condition.put("idCard", idCard);
		}

		if (ValidateCheck.isNotNull(paperId)) {
			condition.put("paperId", paperId);
		}

		if (ValidateCheck.isNotNull(examName)) {
			condition.put("examName", examName);
		}

		if (ValidateCheck.isNotNull(examLevel)) {
			condition.put("examLevel", examLevel);
		}

		if (ValidateCheck.isNotNull(examStatus)) {
			condition.put("examStatus", examStatus);
		}

		if (ValidateCheck.isNotNull(examTime)) {
			condition.put("examTime", examTime);
		}

		return condition;
	}

	// 设置考试试题的序号
	private Exampaper setQuestionsSeq(Exampaper examPaper) {
		List<Bigquestion> bigQuestions = examPaper.getBigQuestions();
		Integer quesSequence = 1;
		for (int i = 0, length_1 = bigQuestions.size(); i < length_1; i++) {
			Bigquestion big = bigQuestions.get(i);
			// 对大题序号进行处理
			if (big != null && i == 0) {
				big.setBigquestionname("一" + RemoveHtmlTag.removeBigQues(big.getBigquestionname()).substring(1));
			}
			if (big != null && i == 1) {
				big.setBigquestionname("二" + RemoveHtmlTag.removeBigQues(big.getBigquestionname()).substring(1));
			}
			if (big != null && i == 2) {
				big.setBigquestionname("三" + RemoveHtmlTag.removeBigQues(big.getBigquestionname()).substring(1));
			}
			List<Exampaperquestion> questions = big.getQuestions();
			for (int j = 0, length_2 = questions.size(); j < length_2; j++) {
				Exampaperquestion ques = questions.get(j);
				ques.setQuestionsequence(quesSequence);// 修改题号
				quesSequence++;
				List<Exampaperoption> options = ques.getOptions();
				for (int k = 0, length_3 = options.size(); k < length_3; k++) {
					Exampaperoption opt = options.get(k);
					// 对单选与多选题的选项进行替换:01234-ABCDE
					if (ques.getType() != null && !"判断题".equals(ques.getType())) {
						opt.setOptionsequence(
								MyElFunction.replace(opt.getOptionsequence().toString(), "01234", "ABCDE"));
					}
					// 判断题的序号设为空
					if (ques.getType() != null && "判断题".equals(ques.getType())) {
						opt.setOptionsequence("");
					}
				}

			}
		}
		return examPaper;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public List<Onlineexamanswerinfor> getOnlineExamAnswerInfos() {
		return onlineExamAnswerInfos;
	}

	public void setOnlineExamAnswerInfos(List<Onlineexamanswerinfor> onlineExamAnswerInfos) {
		this.onlineExamAnswerInfos = onlineExamAnswerInfos;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public Onlineexaminfor getOnlineExamInfor() {
		return onlineExamInfor;
	}

	public void setOnlineExamInfor(Onlineexaminfor onlineExamInfor) {
		this.onlineExamInfor = onlineExamInfor;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamLevel() {
		return examLevel;
	}

	public void setExamLevel(String examLevel) {
		this.examLevel = examLevel;
	}

	public String getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(String examStatus) {
		this.examStatus = examStatus;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(String currentCount) {
		this.currentCount = currentCount;
	}

}