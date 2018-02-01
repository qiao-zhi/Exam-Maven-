package cn.xm.exam.action.exam.examPaper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.bean.question.Questions;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.question.QuestionsService;
import cn.xm.exam.utils.ValidateCheck;

@SuppressWarnings("all")
@Controller
@Scope("prototype")
/**
 * 创建试卷的Action(快速自动生成与根据题库添加)
 * 
 * @author QiaoLiQiang
 * @time 2017年9月26日下午9:54:58
 */
public class CreateExampaperAction extends ActionSupport {
	private Logger logger = Logger.getLogger(CreateExampaperAction.class);// 日志记录器
	// private
	private Map<String, Object> result;// 用于存放结果的map
	@Autowired
	private QuestionsService questionsService;// 题服务(用于在生成试卷查询试题)
	/*************** S(用于转发试卷基本信息) **************/
	private String newPaperMethod;// 新建试卷的方式(快速自动quickGene|根据题库生成bankGene)
	private Exampaper exampaper;// 考试试卷的基本信息
	List<Questions> questions;// 试题集合(用于根据题库生成试卷)

	/**
	 * 将数据传到快速自动生成试卷页面与根据题库生成页面
	 * 
	 * @return
	 */
	public String forward2QuickGeneratePaper() {
		result = new HashMap<String, Object>();
		result.put("exampaper", exampaper);
		// 如果是快速自动生成试卷跳到对应的页面
		if (ValidateCheck.isNotNull(newPaperMethod) && "quickGene".equals(newPaperMethod))
			return "quickGene";
		// 如果是根据题库生成试卷跳到对应的页面
		if (ValidateCheck.isNotNull(newPaperMethod) && "bankGene".equals(newPaperMethod))
			return "bankGene";
		return SUCCESS;
	}

	/*********** E用于转发试卷基本信息 *********************/

	/************** S 随机自动生成试卷条件 ******/
	private String quickGenePaperMethod;// 快速自动生成试卷方式(随机自动生成autoGenePaper|根据知识点生成knowledgeGene)
	private String danxuan_num; // 单选题数
	private String danxuan_score;// 单选分数
	private String duoxuan_num;// 多选数量
	private String duonxuan_score;// 多选分数
	private String panduan_num;// 判断数量
	private String panduan_score;// 判断分数

	@SuppressWarnings({ "all" })
	public String suijiGenePaper() throws SQLException {
		result = new HashMap();
		Map condition = new HashMap();// 查询条件
		// 获取Session中的用户信息
		String departmentId = ((User) ServletActionContext.getRequest().getSession().getAttribute("userinfo"))
				.getDepartmentid();// 获取部门ID
		// 查询满足条件的单选题
		if (ValidateCheck.isNotNull(danxuan_num) && ValidateCheck.isNotNull(exampaper.getLevel())) {
			condition.clear();
			if (ValidateCheck.isNotNull(departmentId)) {
				condition.put("departmentId", departmentId);
			}
			condition.put("num", Integer.valueOf(danxuan_num));
			condition.put("level", Integer.valueOf(exampaper.getLevel()));
			condition.put("type", "单选题");

			List<String> questionsIdsForExamPaper = questionsService.getQuestionsIdsForExamPaper(condition);// 查出ID
			List<Questions> danxuan_list = null;
			if (questionsIdsForExamPaper != null && questionsIdsForExamPaper.size() > 0) {
				danxuan_list = questionsService.getQuestionsForExamPaper(questionsIdsForExamPaper);// 查询试题
			}
			result.put("danxuan_list", danxuan_list);
		}
		// 查询满足条件的多选题
		if (ValidateCheck.isNotNull(duoxuan_num) && ValidateCheck.isNotNull(exampaper.getLevel())) {
			condition.clear();
			if (ValidateCheck.isNotNull(departmentId)) {
				condition.put("departmentId", departmentId);
			}
			condition.put("num", Integer.valueOf(duoxuan_num));
			condition.put("level", Integer.valueOf(exampaper.getLevel()));
			condition.put("type", "多选题");

			List<String> questionsIdsForExamPaper = questionsService.getQuestionsIdsForExamPaper(condition);// 查出ID
			List<Questions> duoxuan_list = null;
			if (questionsIdsForExamPaper != null && questionsIdsForExamPaper.size() > 0) {
				duoxuan_list = questionsService.getQuestionsForExamPaper(questionsIdsForExamPaper);// 查询试题
			}
			result.put("duoxuan_list", duoxuan_list);
		}
		// 查询满足条件的判断题
		if (ValidateCheck.isNotNull(panduan_num) && ValidateCheck.isNotNull(exampaper.getLevel())) {
			condition.clear();
			if (ValidateCheck.isNotNull(departmentId)) {
				condition.put("departmentId", departmentId);
			}
			condition.put("num", Integer.valueOf(panduan_num));
			condition.put("level", Integer.valueOf(exampaper.getLevel()));
			condition.put("type", "判断题");

			List<String> questionsIdsForExamPaper = questionsService.getQuestionsIdsForExamPaper(condition);// 查出ID
			List<Questions> panduan_list = null;
			if (questionsIdsForExamPaper != null && questionsIdsForExamPaper.size() > 0) {
				panduan_list = questionsService.getQuestionsForExamPaper(questionsIdsForExamPaper);// 查询试题
			}
			result.put("panduan_list", panduan_list);
		}
		return "quickGene2";
	}

	/************** E 随机自动生成试卷条件 ******/

	/**** S 跟据知识点自动生成试卷 **/
	private String[] knowledges;// 知识点
	private String[] employeeTypes;// 工种

	/**
	 * 用到上面的条件
	 */

	public String knowledgeGenePaper() throws SQLException {
		result = new HashMap();
		// 获取Session中的用户信息
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentId = user.getDepartmentid();// 获取部门ID
		Map condition = new HashMap();// 查询条件
		// 查询满足条件的单选题
		if (ValidateCheck.isNotNull(danxuan_num) && ValidateCheck.isNotNull(exampaper.getLevel())) {
			condition.clear();
			condition.put("num", Integer.valueOf(danxuan_num));
			condition.put("level", Integer.valueOf(exampaper.getLevel()));
			condition.put("type", "单选题");
			if (ValidateCheck.isNotNull(departmentId)) {
				condition.put("departmentId", departmentId);
			}
			List dictionaryIds = new ArrayList();
			if (knowledges != null && knowledges.length > 0) {
				for (String s : knowledges) {
					dictionaryIds.add(s);
				}
			}
			if (employeeTypes != null && employeeTypes.length > 0) {
				for (String s1 : employeeTypes) {
					dictionaryIds.add(s1);
				}
			}
			if (dictionaryIds != null && dictionaryIds.size() > 0) {
				condition.put("dictionaryIds", dictionaryIds);
			}
			// 查询试题ID集合
			List<String> questionsIdsForExamPaper = questionsService.getQuestionsIdsForExamPaper(condition);
			List<Questions> danxuan_list = null;
			// 查询试题集合
			if (questionsIdsForExamPaper != null && questionsIdsForExamPaper.size() > 0) {
				danxuan_list = questionsService.getQuestionsForExamPaper(questionsIdsForExamPaper);
			}
			result.put("danxuan_list", danxuan_list);
		}
		// 查询满足条件的多选题
		if (ValidateCheck.isNotNull(duoxuan_num) && ValidateCheck.isNotNull(exampaper.getLevel())) {
			condition.clear();
			condition.put("num", Integer.valueOf(duoxuan_num));
			condition.put("level", Integer.valueOf(exampaper.getLevel()));
			condition.put("type", "多选题");
			if (ValidateCheck.isNotNull(departmentId)) {
				condition.put("departmentId", departmentId);
			}
			List dictionaryIds = new ArrayList();
			if (knowledges != null && knowledges.length > 0) {
				for (String s : knowledges) {
					dictionaryIds.add(s);
				}
			}
			if (employeeTypes != null && employeeTypes.length > 0) {
				for (String s1 : employeeTypes) {
					dictionaryIds.add(s1);
				}
			}
			if (dictionaryIds != null && dictionaryIds.size() > 0) {
				condition.put("dictionaryIds", dictionaryIds);
			}
			List<String> questionsIdsForExamPaper = questionsService.getQuestionsIdsForExamPaper(condition);
			List<Questions> duoxuan_list = null;
			if (questionsIdsForExamPaper != null && questionsIdsForExamPaper.size() > 0) {
				duoxuan_list = questionsService.getQuestionsForExamPaper(questionsIdsForExamPaper);
			}
			result.put("duoxuan_list", duoxuan_list);
		}
		// 查询满足条件的判断题
		if (ValidateCheck.isNotNull(panduan_num) && ValidateCheck.isNotNull(exampaper.getLevel())) {
			condition.clear();
			condition.put("num", Integer.valueOf(panduan_num));
			condition.put("level", Integer.valueOf(exampaper.getLevel()));
			condition.put("type", "判断题");
			if (ValidateCheck.isNotNull(departmentId)) {
				condition.put("departmentId", departmentId);
			}
			List dictionaryIds = new ArrayList();
			if (knowledges != null && knowledges.length > 0) {
				for (String s : knowledges) {
					dictionaryIds.add(s);
				}
			}
			if (employeeTypes != null && employeeTypes.length > 0) {
				for (String s1 : employeeTypes) {
					dictionaryIds.add(s1);
				}
			}
			if (dictionaryIds != null && dictionaryIds.size() > 0) {
				condition.put("dictionaryIds", dictionaryIds);
			}
			List<String> questionsIdsForExamPaper = questionsService.getQuestionsIdsForExamPaper(condition);
			List<Questions> panduan_list = null;
			if (questionsIdsForExamPaper != null && questionsIdsForExamPaper.size() > 0) {
				panduan_list = questionsService.getQuestionsForExamPaper(questionsIdsForExamPaper);
			}
			result.put("panduan_list", panduan_list);
		}
		return "knowledgeGenePaper";
	}

	/**** E 跟据知识点自动生成试卷 **/

	/**** S 根据题库生成试卷抽题 *******/
	String level;
	String type;
	String dictionaryId;

	public String bankGenePaper() {
		Map condition = new HashMap();
		condition = generateCondition(condition);// 组装查询条件
		try {
			questions = questionsService.getQuestionsForBankGeneExamPaper(condition);
		} catch (SQLException e) {
			logger.error("根据题库生成试卷出错", e);
		}
		return "bankGenePaperWithQues";
	}

	private Map generateCondition(Map condition) {
		if (ValidateCheck.isNotNull(level)) {
			condition.put("level", level);
		}
		if (ValidateCheck.isNotNull(type)) {
			condition.put("type", type);
		}
		// 获取Session中的用户信息
		String departmentId = ((User) ServletActionContext.getRequest().getSession().getAttribute("userinfo"))
				.getDepartmentid();// 获取部门ID
		if (ValidateCheck.isNotNull(departmentId)) {
			condition.put("departmentId", departmentId);
		}
		if (ValidateCheck.isNotNull(dictionaryId)) {
			condition.put("dictionaryId", dictionaryId);
		}
		return condition;
	}

	/**** E 根据题库生成试卷抽题 *******/

	// get,set方法
	public Exampaper getExampaper() {
		return exampaper;
	}

	public void setExampaper(Exampaper exampaper) {
		this.exampaper = exampaper;
	}

	public String getNewPaperMethod() {
		return newPaperMethod;
	}

	public void setNewPaperMethod(String newPaperMethod) {
		this.newPaperMethod = newPaperMethod;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getQuickGenePaperMethod() {
		return quickGenePaperMethod;
	}

	public void setQuickGenePaperMethod(String quickGenePaperMethod) {
		this.quickGenePaperMethod = quickGenePaperMethod;
	}

	public String getDanxuan_num() {
		return danxuan_num;
	}

	public void setDanxuan_num(String danxuan_num) {
		this.danxuan_num = danxuan_num;
	}

	public String getDanxuan_score() {
		return danxuan_score;
	}

	public void setDanxuan_score(String danxuan_score) {
		this.danxuan_score = danxuan_score;
	}

	public String getDuoxuan_num() {
		return duoxuan_num;
	}

	public void setDuoxuan_num(String duoxuan_num) {
		this.duoxuan_num = duoxuan_num;
	}

	public String getDuonxuan_score() {
		return duonxuan_score;
	}

	public void setDuonxuan_score(String duonxuan_score) {
		this.duonxuan_score = duonxuan_score;
	}

	public String getPanduan_num() {
		return panduan_num;
	}

	public void setPanduan_num(String panduan_num) {
		this.panduan_num = panduan_num;
	}

	public String getPanduan_score() {
		return panduan_score;
	}

	public void setPanduan_score(String panduan_score) {
		this.panduan_score = panduan_score;
	}

	public String[] getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(String[] knowledges) {
		this.knowledges = knowledges;
	}

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public String getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getEmployeeTypes() {
		return employeeTypes;
	}

	public void setEmployeeTypes(String[] employeeTypes) {
		this.employeeTypes = employeeTypes;
	}

}
