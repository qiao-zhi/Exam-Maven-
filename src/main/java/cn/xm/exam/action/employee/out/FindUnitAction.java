package cn.xm.exam.action.employee.out;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.employee.out.UnitService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ValidateCheck;

/**
 * 查询单位action
 * 
 * @author QiaoLiQiang
 * @time 2017年11月14日上午10:08:59
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class FindUnitAction extends ActionSupport {
	private Map response;
	private Logger logger = Logger.getLogger(FindUnitAction.class);
	@Resource
	private UnitService unitService;
	private String currentPage;
	private String currentCount;
	private String unitName;// 单位姓名
	private String unitMinus;// 单位积分
	private String projectName;// 参与项目
	private String fstarttime;// 开始时间
	private String fendtime;// 结束时间
	private String bigId;// 大修ID
	private String unitId;// 单位ID
	private String markTrainType;//培训类型标记

	public String getHaulUnitPage() {
		response = new HashMap();
		PageBean<Map<String, Object>> pageBean = null;
		Map<String, Object> condition = generateCondition();
		try {
			pageBean = unitService.findUnitsWithCondition(Integer.valueOf(currentPage), Integer.valueOf(currentCount),
					condition);
		} catch (NumberFormatException e) {
			logger.error("数字转换异常", e);
		} catch (Exception e) {
			logger.error("查询大修单位异常", e);
		}
		response.put("pageBean", pageBean);
		return SUCCESS;
	}
	
	//查询长委和正式工培训单位信息分页显示
	public String getHaulUnitPage2() {
		response = new HashMap();
		PageBean<Map<String, Object>> pageBean = null;
		Map<String, Object> condition = generateCondition();
		try {
			pageBean = unitService.findUnitsWithCondition2(Integer.valueOf(currentPage), Integer.valueOf(currentCount),
					condition);
		} catch (NumberFormatException e) {
			logger.error("数字转换异常", e);
		} catch (Exception e) {
			logger.error("查询大修单位异常", e);
		}
		response.put("pageBean", pageBean);
		return SUCCESS;
	}

	/**
	 * 根据大修ID与单位ID查询员工信息
	 * 
	 * @return
	 */
	public String getEmployeesByHaulidAndUnitId() {
		response = new HashMap();
		Map condition = new HashMap();
		if (ValidateCheck.isNull(currentCount)) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
		if (ValidateCheck.isNull(currentPage)) {
			currentPage = "1";
		}
		if (ValidateCheck.isNotNull(bigId)) {
			condition.put("bigId", bigId);
		}
		if (ValidateCheck.isNotNull(unitId)) {
			condition.put("unitId", unitId);
		}
		
		PageBean<Map<String, Object>> pageBean = null;
		try {
			pageBean = unitService.getEmployeeOutsByUaulIdAndUnitId(Integer.valueOf(currentPage),
					Integer.valueOf(currentCount),condition);
		} catch (SQLException e) {
			logger.error("查询大修单位员工异常", e);
		}
		response.put("pageBean", pageBean);
		return SUCCESS;
	}
	
	/**
	 * 根据大修ID与单位ID查询内部新员工培训的员工信息
	 * 
	 * @return
	 */
	public String getEmployeesByHaulidAndUnitId2() {
		response = new HashMap();
		Map condition = new HashMap();
		if (ValidateCheck.isNull(currentCount)) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
		if (ValidateCheck.isNull(currentPage)) {
			currentPage = "1";
		}
		if (ValidateCheck.isNotNull(bigId)) {
			condition.put("bigId", bigId);
		}
		if (ValidateCheck.isNotNull(unitId)) {
			condition.put("unitId", unitId);
		}
		
		PageBean<Map<String, Object>> pageBean = null;
		try {
			pageBean = unitService.getEmployeeOutsByUaulIdAndUnitId2(Integer.valueOf(currentPage),
					Integer.valueOf(currentCount),condition);
		} catch (SQLException e) {
			logger.error("查询大修单位员工异常", e);
		}
		response.put("pageBean", pageBean);
		return SUCCESS;
	}

	/**
	 * 根据大修ID与单位ID查询大修单位的违章的员工信息
	 * 
	 * @return
	 */

	public String getEmployeeOutsBreakrulesByUaulIdAndUnitId() {

		response = new HashMap();
		Map condition = new HashMap();
		if (ValidateCheck.isNotNull(bigId)) {
			condition.put("bigId", bigId);
		}
		if (ValidateCheck.isNotNull(unitId)) {
			condition.put("unitId", unitId);
		}
		if (ValidateCheck.isNotNull(fstarttime)) {
			condition.put("fstarttime", fstarttime);
		}
		if (ValidateCheck.isNotNull(fendtime)) {
			condition.put("fendtime", fendtime);
		}
		if (ValidateCheck.isNull(currentCount)) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
		if (ValidateCheck.isNull(currentPage)) {
			currentPage = "1";
		}
		/*
		 * if (ValidateCheck.isNull(currentPage)) { currentPage = "1"; }
		 */
		PageBean<Map<String, Object>> pageBean = null;
		try {
			pageBean = unitService.getEmployeeOutsBreakrulesByUaulIdAndUnitId(Integer.valueOf(currentPage),
					Integer.valueOf(currentCount), condition);
		}
		catch (SQLException e) {
			logger.error("查询大修单位的员工违章信息异常", e);
		}
		response.put("pageBean", pageBean);
		return SUCCESS;
	}

	/**
	 * 根据大修ID查询部门姓名和ID
	 * 
	 * @return
	 */
	public String getUnitidsAndNamesByHaulId() {
		response = new HashMap();
		List<Map<String, Object>> unitidsAndNames = null;
		if (ValidateCheck.isNotNull(bigId)) {
			try {
				User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
				String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
				unitidsAndNames = unitService.getUnitidsAndNamesByHaulId(bigId,departmentIdSession);
			} catch (SQLException e) {
				logger.error("查询单位姓名和ID错误", e);
			}
		}
		if (unitidsAndNames != null) {
			response.put("unitidsAndNames", unitidsAndNames);
		}
		return SUCCESS;
	}

	private Map<String, Object> generateCondition() {
		Map condition = new HashMap();
		if (ValidateCheck.isNotNull(fstarttime)) {
			condition.put("fstarttime", fstarttime);
		}
		if (ValidateCheck.isNotNull(fendtime)) {
			condition.put("fendtime", fendtime);
		}
		if (ValidateCheck.isNull(currentPage)) {
			currentPage = "1";
		}
		if (ValidateCheck.isNull(currentCount)) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
		if (ValidateCheck.isNotNull(bigId)) {
			condition.put("bigId", bigId);
		}
		if (ValidateCheck.isNotNull(unitName)) {
			condition.put("unitName", unitName);
		}
		if (ValidateCheck.isNotNull(projectName)) {
			condition.put("projectName", projectName);
		}
		if (ValidateCheck.isNotNull(unitMinus)) {
			String[] minus = unitMinus.split("-");
			condition.put("minMinus", Float.valueOf(minus[0]));
			condition.put("maxMinus", Float.valueOf(minus[1]));
		}
		
		//培训类型标记
		if(ValidateCheck.isNotNull(markTrainType)){
			//判断标记字段的值，0表示内部正式员工和长委，1表示外来单位
			if(markTrainType.equals("0")){
				condition.put("markTrainType_In", markTrainType);				
			}else{
				condition.put("markTrainType_Out", markTrainType);
			}
			//正式新员工培训大修ID
			condition.put("regular_train", DefaultValue.REGULAR_EMPLOYEE_TRAIN);
			//长委新员工培训大修ID
			condition.put("longterm_train", DefaultValue.LONGTERM_EMPLOYEE_TRAIN);
		}
		
		return condition;
	}

	// get set
	public Map getResponse() {
		return response;
	}

	public void setResponse(Map response) {
		this.response = response;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitMinus() {
		return unitMinus;
	}

	public void setUnitMinus(String unitMinus) {
		this.unitMinus = unitMinus;
	}

	public String getBigId() {
		return bigId;
	}

	public void setBigId(String bigId) {
		this.bigId = bigId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getFstarttime() {
		return fstarttime;
	}

	public void setFstarttime(String fstarttime) {
		this.fstarttime = fstarttime;
	}

	public String getFendtime() {
		return fendtime;
	}

	public void setFendtime(String fendtime) {
		this.fendtime = fendtime;
	}

	public String getMarkTrainType() {
		return markTrainType;
	}

	public void setMarkTrainType(String markTrainType) {
		this.markTrainType = markTrainType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
}
