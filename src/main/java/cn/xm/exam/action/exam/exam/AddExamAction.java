package cn.xm.exam.action.exam.exam;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.exam.Exam;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.employee.in.DepartmentService;
import cn.xm.exam.service.employee.in.EmployeeInService;
import cn.xm.exam.service.employee.out.EmployeeOutService;
import cn.xm.exam.service.employee.out.UnitService;
import cn.xm.exam.service.exam.exam.ExamService;
import cn.xm.exam.utils.ValidateCheck;
import cn.xm.exam.vo.exam.ExamEmployeeInQueryVo;
import cn.xm.exam.vo.exam.ExamEmployeeOutQueryVo;
import cn.xm.exam.vo.exam.QueryInnerEmployeesCondition;
import cn.xm.exam.vo.exam.QueryOuterEmployeesCondition;

/**
 * 增加考试Action
 * 
 * @author QiaoLiQiang
 * @time 2017年10月14日下午4:55:27
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class AddExamAction extends ActionSupport {
	private Logger logger = Logger.getLogger(AddExamAction.class);
	@Autowired
	private DepartmentService departmentService;// 内部部门服务(用于生成部门树)
	@Autowired
	private UnitService unitService;// 外部部门服务(用于生成部门树)
	@Autowired
	private EmployeeInService employeeInService;// 内部员工服务(用于查找内部参考员工)
	@Autowired
	private EmployeeOutService employeeOutService;// 外部员工服务(用于查找外部参考员工)
	@Autowired
	private ExamService examService;// 考试服务(用于添加考试)

	private Exam exam;// 用于接收参数
	private Map response;// 用于包装所有回传的ajax数据

	/**
	 * 生成内部部门树结构
	 * 
	 * @return
	 */
	public String getDepartmentTree() {
		Subject currentUser = SecurityUtils.getSubject();
		// 获取Session中的用户信息
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user.getDepartmentid();// 获取部门ID
		boolean permitted = currentUser.isPermitted("exammanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
		String departmentId = permitted ? null : departmentIdSession;
		response = new HashMap();
		List<Map<String, Object>> departmentTrees = null;
		try {
			departmentTrees = departmentService.getDepartmentTreeForExam(departmentId);
		} catch (SQLException e) {
			logger.error("查询内部部门树出错！", e);
		}
		response.put("departmentTrees", departmentTrees);
		return SUCCESS;
	}

	/**
	 * 查询外部单位树结构
	 * 
	 * @return
	 */
	public String getUnitTree() {
		response = new HashMap();
		List<Map<String, Object>> unitTrees = null;
		try {
			unitTrees = unitService.getUnitTreeForExam();
		} catch (SQLException e) {
			logger.error("查询外部部门树出错！", e);
		}
		response.put("unitTrees", unitTrees);
		return SUCCESS;
	}

	/**
	 * 查询内部参考员工
	 * 
	 * @return
	 */
	private QueryInnerEmployeesCondition queryInnerEmployeesCondition;// 内部员工查询条件(对象驱动)

	public String getEmployeeIns4Exam() {
		response = new HashMap();
		List<Map<String,Object>> examEmployeeIns = null;
		Map condition = new HashMap();
		condition = generateInnerCondition(condition);
		try {
			examEmployeeIns = employeeInService.getExamEmployeeIns(condition);
		} catch (SQLException e) {
			logger.error("查询内部部门员工出错！", e);
		}
		response.put("examEmployeeIns", examEmployeeIns);
		return SUCCESS;
	}

	/**
	 * 封装内部查询条件
	 * 
	 * @param condition
	 * @return
	 */
	private Map generateInnerCondition(Map condition) {
		if (ValidateCheck.isNotNull(queryInnerEmployeesCondition.getDepartments())) {
			String[] departments = queryInnerEmployeesCondition.getDepartments().split(",");
			List<String> department_list = Arrays.asList(departments);
			condition.put("departmentNames", department_list);
		}
		if (ValidateCheck.isNotNull(queryInnerEmployeesCondition.getIdCode())) {
			condition.put("idCode", queryInnerEmployeesCondition.getIdCode());
		}
		if (ValidateCheck.isNotNull(queryInnerEmployeesCondition.getTrainStatus())) {
			condition.put("trainStatus", queryInnerEmployeesCondition.getTrainStatus());
		}
		if (ValidateCheck.isNotNull(queryInnerEmployeesCondition.getName())) {
			condition.put("name", queryInnerEmployeesCondition.getName());
		}
		if (ValidateCheck.isNotNull(queryInnerEmployeesCondition.getSex())) {
			condition.put("sex", queryInnerEmployeesCondition.getSex());
		}
		return condition;
	}

	/**
	 * 查询外部参考员工
	 * 
	 * @return
	 */
	private QueryOuterEmployeesCondition queryOuterEmployeesCondition;// 外部员工查询条件(对象驱动)

	public String getEmployeeOuts4Exam() {
		response = new HashMap();
		List<Map<String, Object>> examEmployeeOuts = null;
		Map condition = new HashMap();
		condition = generateOutCondition(condition);
		try {
			examEmployeeOuts = employeeOutService.getExamEmployeeOuts(condition);
		} catch (SQLException e) {
			logger.error("查询外部部门员工出错！", e);
		}
		response.put("examEmployeeOuts", examEmployeeOuts);
		return SUCCESS;
	}

	private Map generateOutCondition(Map condition) {
		// 將本部门的ID放入查询条件
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
		condition.put("departmentId", departmentIdSession);
		if (ValidateCheck.isNotNull(queryOuterEmployeesCondition.getUnits())) {
			String[] units = queryOuterEmployeesCondition.getUnits().split(",");
			List<String> units_list = Arrays.asList(units);
			condition.put("unitNames", units_list);
		}
		if (ValidateCheck.isNotNull(queryOuterEmployeesCondition.getIdCode())) {
			condition.put("idCode", queryOuterEmployeesCondition.getIdCode());
		}
		if (ValidateCheck.isNotNull(queryOuterEmployeesCondition.getEmpType())) {
			condition.put("empType", queryOuterEmployeesCondition.getEmpType());
		}
		if (ValidateCheck.isNotNull(queryOuterEmployeesCondition.getBigId())) {
			condition.put("bigId", queryOuterEmployeesCondition.getBigId());
		}
		if (ValidateCheck.isNotNull(queryOuterEmployeesCondition.getTrainStatus())) {
			condition.put("trainStatus", queryOuterEmployeesCondition.getTrainStatus());
		}
		if (ValidateCheck.isNotNull(queryOuterEmployeesCondition.getName())) {
			condition.put("name", queryOuterEmployeesCondition.getName());
		}
		if (ValidateCheck.isNotNull(queryOuterEmployeesCondition.getSex())) {
			condition.put("sex", queryOuterEmployeesCondition.getSex());
		}
		if (ValidateCheck.isNotNull(queryOuterEmployeesCondition.getMinusNum())) {
			String[] strs = queryOuterEmployeesCondition.getMinusNum().split("-");
			condition.put("mixMinus", strs[0]);
			condition.put("maxMinus", strs[1]);
		}
		if (ValidateCheck.isNotNull(queryOuterEmployeesCondition.getIsBlack())) {// 是黑名单
			if (queryOuterEmployeesCondition.getIsBlack().equals("1")) {
				condition.put("isBlack", "1");
			}
		}
		return condition;
	}

	private String nameWord;

	/**
	 * 
	 * @return
	 */
	public String getExamNameAndLevelByWord() {
		response = new HashMap();
		List<Map<String, Object>> nameAndLevel = null;
		try {
			nameAndLevel = examService.getExamNameAndLevelByName(nameWord);
		} catch (Exception e) {
			logger.error("查询考试名称和级别信息失败", e);
		}
		response.put("nameAndLevel", nameAndLevel);
		return SUCCESS;
	}

	/**
	 * 增加考试(ajax异步增加)
	 * 
	 * @return 增加结果，转为JSON返回前台
	 */
	private String innerEmployeeIdcodes;// 参考的内部员工身份证号
	private String outEmployeeIdcodes;// 参考的外部员工身份证号
	private String examMethod;// 参考的外部员工身份证号

	public String addExam() {
		response = new HashMap();
		String result = null;
		try {
			result = examService.addExam(exam, examMethod) ? "添加成功！" : "添加失败!";
		} catch (Exception e) {
			logger.error("添加考试信息失败", e);
			result = "添加失败!";
		}
		response.put("result", result);
		return SUCCESS;
	}

	// get,set
	public Map getResponse() {
		return response;
	}

	public void setResponse(Map response) {
		this.response = response;
	}

	public QueryInnerEmployeesCondition getQueryInnerEmployeesCondition() {
		return queryInnerEmployeesCondition;
	}

	public void setQueryInnerEmployeesCondition(QueryInnerEmployeesCondition queryInnerEmployeesCondition) {
		this.queryInnerEmployeesCondition = queryInnerEmployeesCondition;
	}

	public QueryOuterEmployeesCondition getQueryOuterEmployeesCondition() {
		return queryOuterEmployeesCondition;
	}

	public void setQueryOuterEmployeesCondition(QueryOuterEmployeesCondition queryOuterEmployeesCondition) {
		this.queryOuterEmployeesCondition = queryOuterEmployeesCondition;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}

	public EmployeeInService getEmployeeInService() {
		return employeeInService;
	}

	public void setEmployeeInService(EmployeeInService employeeInService) {
		this.employeeInService = employeeInService;
	}

	public EmployeeOutService getEmployeeOutService() {
		return employeeOutService;
	}

	public void setEmployeeOutService(EmployeeOutService employeeOutService) {
		this.employeeOutService = employeeOutService;
	}

	public String getInnerEmployeeIdcodes() {
		return innerEmployeeIdcodes;
	}

	public void setInnerEmployeeIdcodes(String innerEmployeeIdcodes) {
		this.innerEmployeeIdcodes = innerEmployeeIdcodes;
	}

	public String getOutEmployeeIdcodes() {
		return outEmployeeIdcodes;
	}

	public void setOutEmployeeIdcodes(String outEmployeeIdcodes) {
		this.outEmployeeIdcodes = outEmployeeIdcodes;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getExamMethod() {
		return examMethod;
	}

	public void setExamMethod(String examMethod) {
		this.examMethod = examMethod;
	}

	public String getNameWord() {
		return nameWord;
	}

	public void setNameWord(String nameWord) {
		this.nameWord = nameWord;
	}

}
