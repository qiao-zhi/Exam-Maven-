package cn.xm.exam.action.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.xm.exam.bean.question.Questionbank;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.employee.in.DepartmentService;
import cn.xm.exam.service.question.QuestionbankService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ValidateCheck;
import cn.xm.exam.vo.question.QuestionbankQuestionsDepartment;

/**
 * 
 * 项目名称：Exam 
 * 类名称：QuestionbankAction 
 * 类描述： 题库管理的action 
 * 创建人：Leilong 
 * 创建时间：2017年10月8日下午5:51:00
 * @version
 * 
 */
@Controller
@Scope("prototype")
// 控制层，多例模式
public class QuestionbankAction extends ActionSupport implements ModelDriven<Questionbank> {
	private static final long serialVersionUID = 1L;

	@Resource
	private DepartmentService departmentService;
	@Resource
	private QuestionbankService questionBankService;
	private Map<String, Object> result;
	private Questionbank questionBank = new Questionbank();
	private String questionBankId;
	// 题库名
	private String questionBankName;
	// 部门编号
	private String departmentId;
	//题库类别
	private String questionBackTypeId;
	// 当前页
	private String currentPage;
	// 当前页显示的条数
	private String currentCount;

	
	// 模型驱动的方法
	public Questionbank getModel() {

		return questionBank;
	}

	// 加载部门树
	public String onloadDepartmentTree() {
		Subject currentUser = SecurityUtils.getSubject();
		// 获取Session中的用户信息
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user.getDepartmentid();// 获取部门ID
		boolean permitted = currentUser.isPermitted("bankmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
		String departmentId = permitted ? null : departmentIdSession;
		try {
			result = new HashMap<String, Object>();
			List<Map<String, Object>> departmentTree = departmentService.getDepartmentTreeCommon(departmentId);
			result.put("departmentTree", departmentTree);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	// 添加题库信息
	public String addQuestionBankInfo() {

		try {
			// 默认设置为启用状态
			questionBank.setIsuse("1");
			questionBank.setDepartmentid("1");	
			boolean isAdd = questionBankService.addQuestionBank(questionBank);
			result = new HashMap<String, Object>();
			if (isAdd) {
				result.put("result", "添加成功！");
			} else {
				result.put("result", "添加失败！");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	// 修改题库信息
	public String updateQuestionBank() {
		try {
			// 默认设置为启用状态
			questionBank.setIsuse("1");
			questionBank.setDepartmentid("1");	
			boolean isUpdate = questionBankService.updateQuestionBanK(questionBank);
			result = new HashMap<String, Object>();
			if (isUpdate) {
				result.put("result", "修改成功！");
			} else {
				result.put("result", "修改失败！");

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 根据题库姓名获取部门ID前台判断题库名是否存在
	public String getDepartmentIdByBankName() {

		try {
			result = new HashMap<String, Object>();
			String departmentId = questionBankService.getQuestionBankIdByName(questionBankName);
			result.put("departmentId", departmentId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	// 根据题库ID删除题库信息
	public String deleteQuestionBankById() {
		try {
			boolean isDelete = questionBankService.deleteQuestionBankById(questionBankId);
			result = new HashMap<String, Object>();
			if (isDelete) {
				result.put("result", "删除成功！");
			} else {
				result.put("result", "删除失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 根据条件查询题库信息
	public String findQuestionBankInfoByCondition() {

		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			result = new HashMap<String, Object>();
			condition = generationCondition(condition);
			PageBean<QuestionbankQuestionsDepartment> pageBean = questionBankService.findQuestionBankWithCondition(
					Integer.valueOf(currentPage), Integer.valueOf(currentCount), condition);

			result.put("pageBean", pageBean);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return SUCCESS;
	}

	// 组装查询条件
	private Map<String, Object> generationCondition(Map<String, Object> condition) {

		// 对当前页信息进行设置
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		// 对当前页显示的信息进行设置
		if (currentCount == null || "".equals(currentCount.trim())) {
			currentCount = DefaultValue.PAGE_SIZE;
		}

		if (ValidateCheck.isNotNull(questionBankName)) {
			condition.put("questionBank_Name", questionBankName);
			result.put("questionBank_Name", questionBankName);
		}

		if (ValidateCheck.isNotNull(departmentId)) {
			condition.put("department_Id", departmentId);
		}/*else{
			User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
			String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
			// 获取用户信息
			Subject currentUser = SecurityUtils.getSubject();
			boolean permitted = currentUser.isPermitted("bankmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
			String departmentId = permitted ? null : departmentIdSession;
			condition.put("department_Id", departmentId);
		}*/
		
		if (ValidateCheck.isNotNull(questionBackTypeId)) {
			condition.put("questionBackType_Id", questionBackTypeId);
		}
		return condition;
	}
	
	public String getQuestionBankId() {
		return questionBankId;
	}

	public void setQuestionBankId(String questionBankId) {
		this.questionBankId = questionBankId;
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

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getQuestionBankName() {
		return questionBankName;
	}

	public void setQuestionBankName(String questionBankName) {
		this.questionBankName = questionBankName;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getQuestionBackTypeId() {
		return questionBackTypeId;
	}

	public void setQuestionBackTypeId(String questionBackTypeId) {
		this.questionBackTypeId = questionBackTypeId;
	}
	
	
}
