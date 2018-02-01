package cn.xm.exam.action.exam.examPaper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.exam.examPaper.ExamPaperService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.RemoveHtmlTag;
import cn.xm.exam.utils.ValidateCheck;

@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class FindPaperAction extends ActionSupport {
	private Logger logger = Logger.getLogger(FindPaperAction.class);
	@Autowired
	private ExamPaperService examPaperService;
	private Map<String, Object> result;
	private String currentPage;
	private String currentCount;
	private String title;
	private String level;
	private String paperId;
	private String paperStatus;

	/**
	 * 分页查询试卷(ajax查询)
	 * 
	 * @return
	 */
	public String findPapers() {
		result = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition = generateCondition(condition);
		PageBean<Exampaper> pageBean = null;
		try {
			pageBean = examPaperService.findExampapersWithCondition(Integer.valueOf(currentPage),
					Integer.valueOf(currentCount), condition);
		} catch (SQLException e) {
			logger.error("分页查询试卷出错！！！", e);
		}
		result.put("pageBean", pageBean);
		logger.info("查询成功！！！");
		return SUCCESS;
	}

	public String findPaperAllInfoById() {
		result = new HashMap<String, Object>();
		Exampaper paper = null;
		try {
			paper = RemoveHtmlTag.removePaperTag(examPaperService.getPaperAllInfoByPaperId(paperId));
		} catch (SQLException e) {
			logger.error("查询试卷所有信息出错！！！", e);
		}
		result.put("paper", paper);
		return "findPaperAllInfo";
	}

	/**
	 * 组装查询条件
	 * 
	 * @param condition
	 * @return
	 */
	private Map<String, Object> generateCondition(Map<String, Object> condition) {
		if (ValidateCheck.isNull(currentPage)) {
			currentPage = "1";
		}
		if (ValidateCheck.isNull(currentCount)) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
		// 获取用户信息
		Subject currentUser = SecurityUtils.getSubject();
		boolean permitted = currentUser.isPermitted("exammanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
		String departmentId = permitted ? null : departmentIdSession;
		if (ValidateCheck.isNotNull(departmentId)) {
			condition.put("departmentId", departmentId);
		}
		if (ValidateCheck.isNotNull(title)) {
			condition.put("title", title);
		}
		if (ValidateCheck.isNotNull(level)) {
			condition.put("level", Integer.valueOf(level));
		}
		if (ValidateCheck.isNotNull(paperStatus)) {
			condition.put("paperStatus", Integer.valueOf(paperStatus));
		}
		return condition;
	}

	/********* get,set ************/
	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getPaperStatus() {
		return paperStatus;
	}

	public void setPaperStatus(String paperStatus) {
		this.paperStatus = paperStatus;
	}

}
