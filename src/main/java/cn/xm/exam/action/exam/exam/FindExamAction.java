package cn.xm.exam.action.exam.exam;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.exam.exam.ExamService;
import cn.xm.exam.service.grade.EmployeeExamService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ValidateCheck;

/**
 * 分页查询考试信息(ajax+json)
 * 
 * @author QiaoLiQiang
 * @time 2017年10月23日下午8:26:51
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class FindExamAction extends ActionSupport {

	private Logger logger = Logger.getLogger(FindExamAction.class);
	private String currentPage;
	private String currentCount;
	private String examName;// 考试名称
	private String level;// 级别
	private String status;// 状态
	private String month;// 月份
	private String bigId;// 月份
	@Resource
	private ExamService examService;
	@Resource
	private EmployeeExamService employeeExamService;
	private Map response;// 用于包装所有回传的ajax数据

	public String findExam() {
		response = new HashMap();
		Map<String, Object> condition = new HashMap();
		condition = generateCondition(condition);
		PageBean<Map<String, Object>> pageBean = null;
		try {
			pageBean = examService.findExamsWithCondition(Integer.valueOf(currentPage), Integer.valueOf(currentCount),
					condition);
		} catch (NumberFormatException e) {
			logger.error("转换数字异常", e);
		} catch (Exception e) {
			logger.error("查询考试异常", e);
		}
		response.put("pageBean", pageBean);
		return SUCCESS;
	}

	/**
	 * 封装查询条件
	 * 
	 * @param condition
	 * @return
	 */
	private Map<String, Object> generateCondition(Map<String, Object> condition) {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
		// 获取用户信息
		Subject currentUser = SecurityUtils.getSubject();
		boolean permitted = currentUser.isPermitted("exammanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
		String departmentId = permitted ? null : departmentIdSession;

		if (ValidateCheck.isNull(currentPage)) {
			currentPage = "1";
		}
		if (ValidateCheck.isNull(currentCount)) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
		if (ValidateCheck.isNotNull(examName)) {
			condition.put("examName", examName);
		}
		if (ValidateCheck.isNotNull(departmentId)) {
			condition.put("departmentId", departmentId);
		}
		if (ValidateCheck.isNotNull(level)) {
			condition.put("level", Integer.valueOf(level));
		}
		if (ValidateCheck.isNotNull(status)) {
			condition.put("status", status);
		}
		if (ValidateCheck.isNotNull(month)) {
			condition.put("month", month + "-05");
		}
		if (ValidateCheck.isNotNull(bigId)) {
			condition.put("bigId", bigId);
		}
		return condition;
	}

	/**
	 * 根据考试ID查询考试员工
	 * 
	 * @return
	 */
	private String examId;// 考试ID

	public String findExamEmployeeByExamId() {
		response = new HashMap();
		List<Map<String, Object>> employees = null;
		try {
			employees = employeeExamService.getEmployeeexamsByExamId(examId);
		} catch (SQLException e) {
			logger.error("查询考试人员异常", e);
		}
		response.put("employees", employees);
		return SUCCESS;
	}

	// get,set
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

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public Map getResponse() {
		return response;
	}

	public void setResponse(Map response) {
		this.response = response;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getBigId() {
		return bigId;
	}

	public void setBigId(String bigId) {
		this.bigId = bigId;
	}

}
