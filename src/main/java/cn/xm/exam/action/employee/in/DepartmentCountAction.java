package cn.xm.exam.action.employee.in;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.service.employee.in.DepartmentService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;

 /*      
  * 项目名称：Exam   
  * 类名称：DepartmentCountAction   
  * 类描述： 内部部门统计的action
  * 创建人：LL   
  * 创建时间：2018年1月22日 下午11:32:53     
  * @version    
  *    
  */
@Controller
@Scope("prototype")
public class DepartmentCountAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Resource
	private DepartmentService departmentService;
	private Map<String, Object> result;
	// 当前页
	private String currentPage;
	// 当前页显示的条数
	private String currentCount;

	// 统计内部正式单位的详细信息
	public String findDepartmentInFormalCountInfo() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition = generateCondition(condition);
		PageBean<Map<String, Object>> pageBean = null;
		try {
			pageBean = departmentService.getDepartmentInFormalCountInfo(Integer.valueOf(currentPage),Integer.valueOf(currentCount), condition);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		result = new HashMap<String, Object>();
		result.put("pageBean", pageBean);
		return SUCCESS;
	}

	// 统计内部长委单位的详细信息
	public String findDepartmentInToDoCountInfo() {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition = generateCondition(condition);
		PageBean<Map<String, Object>> pageBean = null;
		try {
			pageBean = departmentService.getDepartmentInToDoCountInfo(Integer.valueOf(currentPage),Integer.valueOf(currentCount), condition);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		result = new HashMap<String, Object>();
		result.put("pageBean", pageBean);		
		return SUCCESS;
	}
	
	//统计正式单位员工总数
	public String findFormalDepartmentAndEmpNum(){
		result = new HashMap<String,Object>();
		Map<String, Object> map = null;
		try {
			map = departmentService.getFormalDepartmentAndEmpNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("map", map);
		return SUCCESS;
	}
	//统计内部长委单位及员工数
	public String findToDoDepartmentAndEmpNum(){
		result = new HashMap<String,Object>();
		Map<String, Object> map = null;
		try {
			map = departmentService.getToDoDepartmentAndEmpNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("map", map);	
		return SUCCESS;
	}
	
	// 组装查询条件
	private Map<String, Object> generateCondition(Map<String, Object> condition) {
		// 对当前页信息进行设置
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		// 对当前页显示的信息进行设置
		if (currentCount == null || "".equals(currentCount.trim())) {
			currentCount = DefaultValue.PAGE_SIZE;
		}

		return condition;
	}

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
	
	
}
