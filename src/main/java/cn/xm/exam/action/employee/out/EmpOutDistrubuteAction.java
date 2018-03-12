package cn.xm.exam.action.employee.out;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.common.Dictionary;
import cn.xm.exam.bean.employee.out.Employeeoutdistribute;
import cn.xm.exam.bean.haul.Haulemployeeout;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.common.DictionaryService;
import cn.xm.exam.service.employee.out.EmployeeOutService;
import cn.xm.exam.service.employee.out.EmpoutDistributeService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ValidateCheck;

/**
 * 分配员工controller
 * 
 * @author QiaoLiQiang
 * @time 2017年12月30日下午2:41:11
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class EmpOutDistrubuteAction extends ActionSupport {

	private Logger logger = Logger.getLogger(EmpOutDistrubuteAction.class);// 日志记录器
	private Map<String, Object> response;
	@Resource
	private EmpoutDistributeService empoutDistributeService;
	private String markTrainType;// 标记外来还是内部常委
	//大修状态标记 1表示查看所有检修
	private String bigStatusMark;

	/**
	 * 跟句当前用户的ID查询大修单位数
	 * 
	 * @return
	 */
	public String getHaulunitTreeByDepartmentId() {
		response = new HashMap<String, Object>();
		List<Map<String, Object>> haulunitTree = null;
		try {
			User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
			String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
			Map condition = new HashMap();
			// 获取用户信息
			Subject currentUser = SecurityUtils.getSubject();
			boolean permitted = currentUser.isPermitted("trainStatus:factory");// 判断是否有查看所有人培训信息权限
			String departmentId = permitted ? "01002" :departmentIdSession ;//有权限将01002设为当前部门
			condition.put("departmentId", departmentId);
			// 培训类型标记
			if (ValidateCheck.isNotNull(markTrainType)) {
				// 判断标记字段的值，0表示内部正式员工和长委，1表示外来单位
				if (markTrainType.equals("0")) {
					condition.put("markTrainType_In", markTrainType);
				} else {
					condition.put("markTrainType_Out", markTrainType);
				}
				// 正式新员工培训大修ID
				condition.put("regular_train", DefaultValue.REGULAR_EMPLOYEE_TRAIN);
				// 长委新员工培训大修ID
				condition.put("longterm_train", DefaultValue.LONGTERM_EMPLOYEE_TRAIN);
			}
			if(ValidateCheck.isNotNull(bigStatusMark)){
				//1表示查询已结束的检修，0代表查询进行中的检修
				if(bigStatusMark.equals("1")){				
					condition.put("bigStatus", "已结束");
				}else{
					condition.put("bigStatus", "进行中");
				}
			}
			haulunitTree = empoutDistributeService.getHaulunitTreeByDepartmentId(condition);
		} catch (SQLException e) {
			logger.error("查询分配单位大修树出错", e);
		}
		if (haulunitTree != null) {
			response.put("haulunitTree", haulunitTree);
		}
		return SUCCESS;
	}

	// 大修ID和單位ID
	private String bigId;
	private String unitId;

	/**
	 * 根据单位id和大修id查询单位信息
	 * 
	 * @return
	 */
	public String getUnitInfoByBigIdAndUnitId() {
		response = new HashMap<String, Object>();
		Map<String, Object> unitInfo = null;
		try {
			unitInfo = empoutDistributeService.getUintInfoByHaulIdAndUnitId(bigId, unitId);
		} catch (SQLException e) {
			logger.error("根据大修ID和单位ID查询单位信息失败", e);
		}
		if (unitInfo != null) {
			response.put("unitInfo", unitInfo);
		}
		return SUCCESS;
	}

	private String currentPage;
	private String currentCount;
	private String distributeStatus;
	private String employeeOutName;
	private String employeeOutIdCard;
	private String employeeOutSex;
	@Autowired
	private DictionaryService dictionaryService;
	/**
	 * 分页查询分配信息
	 * 
	 * @return
	 */
	public String getDistributeInfoWithCondition() {
		response = new HashMap<String, Object>();
		Map condition = generateCondition();
		PageBean<Map<String, Object>> pageBean = null;
		try {
			pageBean = empoutDistributeService.getDistributeInfoWithCondition(Integer.parseInt(currentPage),
					Integer.parseInt(currentCount), condition);
		} catch (NumberFormatException | SQLException e) {
			logger.error("查询员工分配信息出错", e);
		}
		response.put("pageBean", pageBean);
		return SUCCESS;
	}

	private Map generateCondition() {
		Map<String, Object> condition = new HashMap<String, Object>();
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
		// 获取用户信息
		Subject currentUser = SecurityUtils.getSubject();
		boolean permitted = currentUser.isPermitted("trainStatus:factory");// 判断是否有查看所有人培训信息权限
		String departmentId = permitted ? "01002" :departmentIdSession ;//有权限将01002设为当前部门
		//如果是具有厂级权限的部门来查看将部门ID设为01
		if (ValidateCheck.isNotNull(departmentIdSession)) {
			condition.put("departmentId", departmentId);
		}
		if (ValidateCheck.isNotNull(distributeStatus)) {
			condition.put("distributeStatus", distributeStatus);
		}
		if (ValidateCheck.isNotNull(employeeOutIdCard)) {
			condition.put("employeeOutIdCard", employeeOutIdCard);
		}
		// 培训类型标记
		if (ValidateCheck.isNotNull(markTrainType)) {
			// 判断标记字段的值，0表示内部正式员工和长委，1表示外来单位
			if (markTrainType.equals("0")) {
				condition.put("markTrainType_In", markTrainType);
			} else {
				condition.put("markTrainType_Out", markTrainType);
			}
			// 正式新员工培训大修ID
			condition.put("regular_train", DefaultValue.REGULAR_EMPLOYEE_TRAIN);
			// 长委新员工培训大修ID
			condition.put("longterm_train", DefaultValue.LONGTERM_EMPLOYEE_TRAIN);
		}
		if(ValidateCheck.isNotNull(bigStatusMark)){
			//1表示查询已结束的检修，0代表查询进行中的检修
			if(bigStatusMark.equals("1")){				
				condition.put("bigStatus", "已结束");
			}else{
				condition.put("bigStatus", "进行中");
			}
		}
		if (ValidateCheck.isNotNull(unitId)) {
			condition.put("unitId", unitId);
		}
		if (ValidateCheck.isNotNull(bigId)) {
			condition.put("bigId", bigId);
		}
		if (ValidateCheck.isNotNull(employeeOutName)) {
			condition.put("employeeOutName", employeeOutName);
		}
		if (ValidateCheck.isNotNull(employeeOutSex)) {
			condition.put("employeeOutSex", employeeOutSex);
		}
		if (ValidateCheck.isNull(currentCount)) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
		if (ValidateCheck.isNull(currentPage)) {
			currentPage = "1";
		}
		return condition;
	}

	/**
	 * 查詢分配树
	 * 
	 * @return
	 */
	public String getDepartmentTreeForFenpei() {
		// 获取Session中的用户信息
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user.getDepartmentid();// 获取部门ID
		String departmentId = "01002".equals(departmentIdSession) ? "01" : departmentIdSession;
		response = new HashMap<String, Object>();
		List<Map<String, Object>> departmentTree = null;
		try {
			departmentTree = empoutDistributeService.getDepartmentTreeForFenpei(departmentId);
		} catch (SQLException e) {
			logger.error("查询分配树出错", e);
		}
		if (departmentTree != null) {
			response.put("departmentTree", departmentTree);
		}
		return SUCCESS;
	}

	/**
	 * 添加分配信息
	 * 
	 * @return
	 */
	private String departmentids;
	private List<Employeeoutdistribute> employeeoutdistributes;

	public String addFenpeiInfo() {
		response = new HashMap<String, Object>();
		String[] departmentId_str = null;
		if (ValidateCheck.isNotNull(departmentids)) {
			departmentId_str = departmentids.split(",");
		}
		String result = "";
		try {
			for (int i = 0; i < departmentId_str.length; i++) {
				for (Employeeoutdistribute employeeoutdistribute : employeeoutdistributes) {
					employeeoutdistribute.setDepartmentid(departmentId_str[i]);
				}
				result = empoutDistributeService.addFenpeiInfoBatch(employeeoutdistributes) ? "添加成功" : "添加失败";
			}
		} catch (SQLException e) {
			result = "添加失败";
			logger.error("添加分配信息失败", e);
		}
		response.put("result", result);
		return SUCCESS;
	}

	/**
	 * 添加分配信息
	 * 
	 * @return
	 */
	private Employeeoutdistribute employeeoutdistribute;

	public String updateFenpeiInfo() {
		response = new HashMap<String, Object>();
		String result = "";
		try {
			result = empoutDistributeService.updateFenpeiInfo(departmentids, employeeoutdistribute) ? "修改成功" : "修改失败";
		} catch (SQLException e) {
			result = "修改失败";
			logger.error("修改分配信息失败", e);
		}
		response.put("result", result);
		return SUCCESS;
	}
	
	
	
	
	
	private List<Haulemployeeout> haulemployeeouts;//二次分配的大修员工信息
	public String reDistribute(){
		response = new HashMap<String, Object>();
		String result = "";
		try {
				result = empoutDistributeService.addSecondFenpeiInfoBatch(haulemployeeouts,employeeoutdistributes) ? "二次分配成功" : "二次分配失败";
		} catch (SQLException e) {
			result = "二次分配失败";
			logger.error("二次分配失败", e);
		}
		response.put("result", result);
		return SUCCESS;
	}
	
	
	/**
	 * 重新分配检修单位
	 * @return
	 */
	public String reDistributeUnit(){
		response = new HashMap<String, Object>();
		String result = "";
		try {
			result = empoutDistributeService.addSecondFenpeiUnitBatch(haulemployeeouts,employeeoutdistributes) ? "分配单位成功" : "二次分配失败";
		} catch (SQLException e) {
			result = "二次分配失败";
			logger.error("二次分配失败", e);
		}
		response.put("result", result);
		return SUCCESS;
	}
	
	
	
	/**
	 * 生成工作证的操作
	 * 
	 * @return
	 */
	@Resource
	private EmployeeOutService employeeOutService;
	/**
	 * 大修员工ID(转为list传到Mapper)
	 */
	private String bigEmployeeOutIds;

	public String generateWordCard() {
		Map condition = new HashMap();
		response = new HashMap<String, Object>();
		if (ValidateCheck.isNotNull(bigEmployeeOutIds)) {
			// 分割为数组
			String[] bigEmployeeOutIds_array = bigEmployeeOutIds.split(",");
			// 转为list
			List<String> bigEmployeeOutIds = Arrays.asList(bigEmployeeOutIds_array);
			condition.put("bigEmployeeOutIds", bigEmployeeOutIds);
		}
		condition.put("trainStatus", "2");
		String result = null;
		try {
			result = employeeOutService.updateHaulEmployeeOutTrainStatusByCondition(condition) > 0 ? "生成工作证成功!"
					: "生成工作证失败!";
		} catch (Exception e) {
			logger.error("生成工作证出错！！！", e);
			result = "生成工作证出错！！！";
		}
		response.put("result", result);
		return SUCCESS;
	}

	/**
	 * 回收工作证
	 * 
	 * @return
	 */
	public String revokeWordCard() {
		Map condition = new HashMap();
		response = new HashMap<String, Object>();
		if (ValidateCheck.isNotNull(bigEmployeeOutIds)) {
			// 分割为数组
			String[] bigEmployeeOutIds_array = bigEmployeeOutIds.split(",");
			// 转为list
			List<String> bigEmployeeOutIds = Arrays.asList(bigEmployeeOutIds_array);
			condition.put("bigEmployeeOutIds", bigEmployeeOutIds);
		}
		condition.put("trainStatus", "3");
		String result = null;
		try {
			result = employeeOutService.updateHaulEmployeeOutTrainStatusByCondition(condition) > 0 ? "回收工作证成功!"
					: "回收工作证失败!";
		} catch (Exception e) {
			logger.error("回收工作证出错！！！", e);
			result = "回收工作证出错！！！";
		}
		response.put("result", result);
		return SUCCESS;
	}

	private String distributeId;//分配ID
	public String updateDistributeForMiankao(){
		Map condition = new HashMap();
		response = new HashMap<String, Object>();
		String result = null;
		if(ValidateCheck.isNotNull(distributeId)){
			try {
				result = empoutDistributeService.updateDistributeForMiankao(distributeId)? "免培训成功，请到待分配下将该员工分配到下级":"免培训失败";
			} catch (SQLException e) {
				logger.error("免培训失败!", e);
				e.printStackTrace();
			}
		}
		response.put("result", result);
		return SUCCESS;
	}
	// get,set
	public Map<String, Object> getResponse() {
		return response;
	}

	public void setResponse(Map<String, Object> response) {
		this.response = response;
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

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(String currenCount) {
		this.currentCount = currenCount;
	}

	public List<Employeeoutdistribute> getEmployeeoutdistributes() {
		return employeeoutdistributes;
	}

	public void setEmployeeoutdistributes(List<Employeeoutdistribute> employeeoutdistributes) {
		this.employeeoutdistributes = employeeoutdistributes;
	}

	public String getDepartmentids() {
		return departmentids;
	}

	public void setDepartmentids(String departmentids) {
		this.departmentids = departmentids;
	}

	public Employeeoutdistribute getEmployeeoutdistribute() {
		return employeeoutdistribute;
	}

	public void setEmployeeoutdistribute(Employeeoutdistribute employeeoutdistribute) {
		this.employeeoutdistribute = employeeoutdistribute;
	}

	public String getDistributeStatus() {
		return distributeStatus;
	}

	public void setDistributeStatus(String distributeStatus) {
		this.distributeStatus = distributeStatus;
	}

	public String getEmployeeOutName() {
		return employeeOutName;
	}

	public void setEmployeeOutName(String employeeOutName) {
		this.employeeOutName = employeeOutName;
	}

	public String getEmployeeOutIdCard() {
		return employeeOutIdCard;
	}

	public void setEmployeeOutIdCard(String employeeOutIdCard) {
		this.employeeOutIdCard = employeeOutIdCard;
	}

	public String getEmployeeOutSex() {
		return employeeOutSex;
	}

	public void setEmployeeOutSex(String employeeOutSex) {
		this.employeeOutSex = employeeOutSex;
	}

	public String getMarkTrainType() {
		return markTrainType;
	}

	public void setMarkTrainType(String markTrainType) {
		this.markTrainType = markTrainType;
	}

	public String getBigEmployeeOutIds() {
		return bigEmployeeOutIds;
	}

	public void setBigEmployeeOutIds(String bigEmployeeOutIds) {
		this.bigEmployeeOutIds = bigEmployeeOutIds;
	}

	public List<Haulemployeeout> getHaulemployeeouts() {
		return haulemployeeouts;
	}

	public void setHaulemployeeouts(List<Haulemployeeout> haulemployeeouts) {
		this.haulemployeeouts = haulemployeeouts;
	}

	public String getBigStatusMark() {
		return bigStatusMark;
	}

	public void setBigStatusMark(String bigStatusMark) {
		this.bigStatusMark = bigStatusMark;
	}

	public String getDistributeId() {
		return distributeId;
	}

	public void setDistributeId(String distributeId) {
		this.distributeId = distributeId;
	}
	

}
