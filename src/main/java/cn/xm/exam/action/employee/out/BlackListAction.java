package cn.xm.exam.action.employee.out;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.service.employee.out.BlackListEmpOutService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
@Controller
@Scope("prototype")
public class BlackListAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Resource
	private BlackListEmpOutService blackListEmpOutService;
	
	//当前页
	private String currentPage;
	//当前页显示条数
	private String currentCount;
	//返回值
	private Map<String,Object> result;
	//黑名单ID
	private String blackId;
	//员工ID
	private String employeeId;
	//员工类型
	private String employeeType;
	/**
	 * 查询黑名单员工信息
	 * @return
	 */
	public String findBlackListEmpOutInfo(){
		// 对当前页信息进行设置
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		// 对当前页显示的信息进行设置
		if (currentCount == null || "".equals(currentCount.trim())) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
				
		result = new HashMap<String,Object>();
		try {
			PageBean<Map<String, Object>> pageBean = blackListEmpOutService.getBlackEmployeePage(Integer.valueOf(currentPage), Integer.valueOf(currentCount));
			result.put("pageBean", pageBean);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//删除黑名单信息
	public String deleteBlackListInfo(){
		result = new HashMap<String,Object>();
		boolean delete = false;
		try {
			delete = blackListEmpOutService.deleteBlackListInfoById(blackId);			
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		if(delete){
			result.put("result", "删除成功！");
		}else{
			result.put("result", "删除失败！");
		}
		return SUCCESS;
	}
	
	
	//查询违章详情
	public String selectBreakRulesInfo(){
		result = new HashMap<String,Object>();
		try {
			Map<String, Object> map = blackListEmpOutService.getBreakRulesInfoList(employeeId, employeeType);
			result.put("breakListInfo", map);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return SUCCESS;
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


	public Map<String, Object> getResult() {
		return result;
	}


	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getBlackId() {
		return blackId;
	}

	public void setBlackId(String blackId) {
		this.blackId = blackId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	
	
}
