package cn.xm.exam.action.employee.in;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.employee.in.EmplyinBreakrules;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.employee.in.OnlineExamEmployeeInService;
import cn.xm.exam.utils.DateHandler;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ValidateCheck;

/**   
*    
* 项目名称：Exam   
* 类名称：OnlineExamEmployeeInAction   
* 类描述：内部员工个人中心的action
* 创建人：Leilong  
* 创建时间：2017年11月2日 上午8:45:34     
* @version    
*    
*/
@Controller
@Scope("prototype")
public class OnlineExamEmployeeInAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Resource
	private OnlineExamEmployeeInService onlineExamEmployeeInService;
	private Map<String,Object> result;
	private EmployeeIn employeeIn;
	//内部员工的id
	private String employeeInId;
	//新的登录密码
	private String newPassword;
	//违章时间段左侧数据
	private String breakTimeLeft;
	//违章时间段右侧数据
	private String breakTimeRight;
	//当前页
	private String currentPage;
	//当前页显示的条数
	private String currentCount;
	
	
	//查询内部员工的基本信息包括登录信息
	public String getOnlineExamEmployeeInInfo(){
		try {
			EmployeeIn employeeBaseInfo = onlineExamEmployeeInService.getOnlineExamEmployeeInInfoById(employeeInId);
			User employeeUserInfo = onlineExamEmployeeInService.getOnlineExamUserInfoByEmployeeInId(employeeInId);
			result = new HashMap<String,Object>();
			result.put("employeeBaseInfo",employeeBaseInfo);
			result.put("employeeUserInfo",employeeUserInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//修改员工的基本信息
	public String updateOnlineExamEmployeeInInfo(){
		try {
			boolean isUpdate = onlineExamEmployeeInService.updateOnlineExamEmployeeInInfo(employeeIn);
			result = new HashMap<String,Object>();
			if(isUpdate){
				result.put("result", "修改成功！");
			}else{
				result.put("result", "修改失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//修改当前登录用户的密码
	public String updateOnlineExamUsersPassword(){
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("employeeInId", employeeInId);
			condition.put("newPassword", newPassword);
			boolean isUpdate = onlineExamEmployeeInService.updateOnlineExamUserInfo(condition);
			result = new HashMap<String,Object>();
			if(isUpdate){
				result.put("result", "修改成功！");
			}else{
				result.put("result", "修改失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	//查询当前用户的违章信息
	public String getEmployeeBreakInfoList(){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition = generationCondition(condition);
		PageBean<EmplyinBreakrules> pageBean = null;
		try {
			pageBean = onlineExamEmployeeInService.getOnlineEmployeeBreakInfoByCondition(Integer.valueOf(currentPage),Integer.valueOf(currentCount), condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = new HashMap<String,Object>();
		result.put("pageBean", pageBean);
		return SUCCESS;
	}
	
	//组装查询条件
	private Map<String,Object> generationCondition(Map<String,Object> condition){
		//对当前页信息进行设置
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		//对当前页显示的信息进行设置
		if (currentCount == null || "".equals(currentCount.trim())) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
		
		//获取系统当前时间为积分周期为一年封装条件
		String currentYear = DateHandler.dateToString(new Date(), "yyyy");
		
		if(ValidateCheck.isNotNull(currentYear)){
			condition.put("current_Year", currentYear);
		}
		
		if(ValidateCheck.isNotNull(employeeInId)){
			condition.put("employeeIn_Id", employeeInId);
		}
		
		if(ValidateCheck.isNotNull(breakTimeLeft)){
			condition.put("breakTime_left", breakTimeLeft+" 00:00:00");
		}
		
		if(ValidateCheck.isNotNull(breakTimeRight)){
			condition.put("breakTime_right", breakTimeRight+" 23:59:59");
		}
		
		return condition;
	}
	
	public Map<String, Object> getResult() {
		return result;
	}



	public void setResult(Map<String, Object> result) {
		this.result = result;
	}



	public EmployeeIn getEmployeeIn() {
		return employeeIn;
	}



	public void setEmployeeIn(EmployeeIn employeeIn) {
		this.employeeIn = employeeIn;
	}



	public String getEmployeeInId() {
		return employeeInId;
	}



	public void setEmployeeInId(String employeeInId) {
		this.employeeInId = employeeInId;
	}



	public String getNewPassword() {
		return newPassword;
	}



	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getBreakTimeLeft() {
		return breakTimeLeft;
	}

	public void setBreakTimeLeft(String breakTimeLeft) {
		this.breakTimeLeft = breakTimeLeft;
	}

	public String getBreakTimeRight() {
		return breakTimeRight;
	}

	public void setBreakTimeRight(String breakTimeRight) {
		this.breakTimeRight = breakTimeRight;
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
