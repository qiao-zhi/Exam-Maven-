package cn.xm.exam.action.employee.in;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.employee.in.Department;
import cn.xm.exam.bean.employee.in.EmplyinBreakrules;
import cn.xm.exam.bean.employee.in.EmplyinBreakrulesExample;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.mapper.employee.in.EmplyinBreakrulesMapper;
import cn.xm.exam.service.employee.in.DepartmentService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ValidateCheck;

@SuppressWarnings("all")
public class DepartmentAction extends ActionSupport {

	/*
	 * 查询部门树结构
	 */
	public String getDepartmentTree() throws Exception {
		result = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		// 获取Session中的用户信息
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user.getDepartmentid();// 获取部门ID
		boolean permitted = currentUser.isPermitted("departmentmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
		String departmentId = permitted ? null : departmentIdSession;
		List<Map<String, Object>> treeList = departmentService.getDepartmentTree(departmentId);

		result.put("treeList", treeList);
		return SUCCESS;
	}

	/*
	 * 添加部门
	 */

	public String addDepartment() throws Exception {

		result = new HashMap<String, Object>();
		result.put("message", departmentService.addDepartment(department));
		return SUCCESS;
	}

	/*
	 * 修改部门
	 */

	public String updateDepartment() throws Exception {
		result = new HashMap<String, Object>();
		boolean flag = departmentService.updateDepartment(department);

		if (flag) {
			result.put("message", "修改成功");
		} else {
			result.put("message", "修改失败");
		}
		return SUCCESS;
	}

	/*
	 * 删除部门
	 */
	public String deleteDepartment() throws Exception {
		// result = new HashMap<String,Object>();
		result = new HashMap<String, Object>();
		String message = departmentService.deleteDepartmentById(departmentid);

		result.put("message", message);

		return SUCCESS;
	}

	/*
	 * 通过id得到name
	 */
	public String getDepartmentName() {
		result = new HashMap<String, Object>();
		String departmentname = departmentService.getDepartmentNameById(departmentid);
		if ("部门集合".equals(departmentname)) {
			departmentname = "无";
		}
		result.put("departmentname", departmentname);
		return SUCCESS;
	}

	/*
	 * 显示部门违章信息
	 */
	public String getBreakrulesCase() {
		Map<String, Object> condition4 = new HashMap<String, Object>();
		result = new HashMap<String, Object>();

		condition4 = generateCondition4(condition4);
		PageBean<Map<String, Object>> pageBean = departmentService.getBreakrulesCase(condition4);

		result.put("pageBean", pageBean);

		return SUCCESS;
	}

	/*
	 * 将条件判断之后放到一个集合中
	 */
	private Map<String, Object> generateCondition4(Map<String, Object> condition4) {
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
			condition4.put("currentPage", currentPage);
			result.put("currentPage", currentPage);
		}
		if (currentCount == null || "".equals(currentCount.trim())) {
			currentCount = DefaultValue.PAGE_SIZE;
			condition4.put("currentCount", currentCount);
			result.put("currentCount", currentCount);
		}

		if (ValidateCheck.isNotNull(currentCount)) {
			condition4.put("currentCount", currentCount);
			result.put("currentCount", currentCount);
		}
		if (ValidateCheck.isNotNull(fstarttime)) {
			condition4.put("fstarttime", fstarttime);
			result.put("fstarttime", fstarttime);
		}
		if (ValidateCheck.isNotNull(fendtime)) {
			condition4.put("fendtime", fendtime);
			result.put("fendtime", fendtime);
		}
		if (ValidateCheck.isNotNull(currentPage)) {
			condition4.put("currentPage", currentPage);
			result.put("currentPage", currentPage);
		}
		if (ValidateCheck.isNotNull(breakrulesbumen)) {
			condition4.put("breakrulesbumen", breakrulesbumen);
			result.put("breakrulesbumen", breakrulesbumen);
		}

		return condition4;
	}

	/*
	 * 跳转到员工管理
	 */
	/*
	 * public String getEmployeeInById(){ result = new HashMap<String,
	 * Object>(); PageBean }
	 */

	public String toEmployeeIn() {
		ServletContext servletContext = ServletActionContext.getServletContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		String getdepartmentid = request.getParameter("method");
		result = new HashMap<String, Object>();
		result.put("getdepartmentid", getdepartmentid);

		return "innerdepartEmpManage";
		// return SUCCESS;

	}

	/*
	 * 按条件查询所有的部门
	 */
	
	public String findDepartment() {
		Map<String, Object> condition = new HashMap<String, Object>();
		// result是用来返回到jsp中
		result = new HashMap<String, Object>();
		// condition是用来充当查询的条件
		condition = generateCondition(condition);
		PageBean<Map<String, Object>> pageBean = null;

		try {

			pageBean = departmentService.findDepartmentsWithCondition(condition);
			result.put("pageBean", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("pageBean", pageBean);
		return SUCCESS;
	}

	/*
	 * 通过部门id查询出该部门下的员工的违章积分
	 */
	@Resource
	private EmplyinBreakrulesMapper emplyinBreakrulesMapper;
	private String fstarttime;// 每页显示的记录数
	private String fendtime;// 每页显示的记录数

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

	public int getempsMinus(String departmentid) {
		// 通过部门id得到该部门的所有员工
		List<String> empIdList = departmentService.getEmpIdByDepartmentid(departmentid);
		int departmentsum = 0;
		for (String empId : empIdList) {
			int empsum = 0;

			EmplyinBreakrulesExample emplyinBreakrulesExample = new EmplyinBreakrulesExample();
			EmplyinBreakrulesExample.Criteria criteria = emplyinBreakrulesExample.createCriteria();
			criteria.andEmpinemployeeidEqualTo(empId);
			if (ValidateCheck.isNotNull(fstarttime)) {
				try {
					criteria.andEmpinbreaktimeGreaterThanOrEqualTo(
							(new SimpleDateFormat("yyyy-MM-dd")).parse(fstarttime));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (ValidateCheck.isNotNull(fendtime)) {
				try {
					criteria.andEmpinbreaktimeLessThanOrEqualTo((new SimpleDateFormat("yyyy-MM-dd")).parse(fendtime));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			List<EmplyinBreakrules> emplyinBreakruleslist = emplyinBreakrulesMapper
					.selectByExample(emplyinBreakrulesExample);

			for (int i = 0; i < emplyinBreakruleslist.size(); i++) {
				empsum = empsum + emplyinBreakruleslist.get(i).getEmpinminusnum();
			}
			departmentsum = departmentsum + empsum;
		}
		return departmentsum;
	}

	/*
	 * 将条件判断之后放到一个集合中
	 */
	
	private String departType;
	
	public String getDepartType() {
		return departType;
	}

	public void setDepartType(String departType) {
		this.departType = departType;
	}

	private Map<String, Object> generateCondition(Map<String, Object> condition) {
		/** S QLQ 范围管理 */
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
		// 获取用户信息
		Subject currentUser = SecurityUtils.getSubject();
		boolean permitted = currentUser.isPermitted("departmentmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
		String departmentId = permitted ? null : departmentIdSession;
		if (ValidateCheck.isNotNull(departmentId)) {
			condition.put("departmentId", departmentId);
		}
		if (ValidateCheck.isNotNull(departType)) {
			condition.put("departType", departType);
		}
		/** S QLQ 范围管理 */

		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
			condition.put("currentPage", currentPage);
			result.put("currentPage", currentPage);
		}
		if (currentCount == null || "".equals(currentCount.trim())) {
			currentCount = DefaultValue.PAGE_SIZE;
			condition.put("currentCount", currentCount);
			result.put("currentCount", currentCount);
		}
		if (ValidateCheck.isNotNull(currentCount)) {
			condition.put("currentCount", currentCount);
			result.put("currentCount", currentCount);
		}
		if (ValidateCheck.isNotNull(currentPage)) {
			condition.put("currentPage", currentPage);
			result.put("currentPage", currentPage);
		}

		if (ValidateCheck.isNotNull(departmentname)) {
			condition.put("departmentname", departmentname);
			result.put("departmentname", departmentname);
		}

		if (ValidateCheck.isNotNull(updepartmentid)) {
			condition.put("updepartmentid", updepartmentid);
			result.put("updepartmentid", updepartmentid);
		}
		if (ValidateCheck.isNotNull(fstarttime)) {
			condition.put("fstarttime", fstarttime);
			result.put("fstarttime", fstarttime);
		}
		if (ValidateCheck.isNotNull(fendtime)) {
			condition.put("fendtime", fendtime);
			result.put("fendtime", fendtime);
		}
		return condition;
	}

	@Autowired
	private DepartmentService departmentService;

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	// 将action中得到的值返回到jsp中
	private Map<String, Object> result;

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	// jsp中传递过来的值
	private String departmentname;
	private String currentPage;
	private String currentCount;
	private String updepartmentid;

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
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

	public String getUpdepartmentid() {
		return updepartmentid;
	}

	public void setUpdepartmentid(String updepartmentid) {
		this.updepartmentid = updepartmentid;
	}

	// 内部单位
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	// 删除的id
	private String departmentid;

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	// 显示部门违章信息
	private String breakrulesbumen;

	public String getBreakrulesbumen() {
		return breakrulesbumen;
	}

	public void setBreakrulesbumen(String breakrulesbumen) {
		this.breakrulesbumen = breakrulesbumen;
	}

}
