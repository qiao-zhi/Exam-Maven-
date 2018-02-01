package cn.xm.exam.action.exam.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.xm.exam.bean.exam.Exam;
import cn.xm.exam.service.exam.exam.ExamService;
import cn.xm.exam.service.grade.EmployeeExamService;
import cn.xm.exam.utils.ValidateCheck;

/**
 * 修改考试信息(修改考试基本信息与参考员工) 1.通过ID将考试信息显示到界面 2.接收前台改过的数据(根据考试ID修改考试基本信息)
 * 3.删除成绩表重新添加参考员工
 * 
 * @author QiaoLiQiang
 * @time 2017年10月25日下午9:50:25
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
// 模型驱动
public class UpdateExamAction extends ActionSupport implements ModelDriven<Exam> {
	private Logger logger = Logger.getLogger(UpdateExamAction.class);
	@Resource
	private ExamService examService;
	@Resource
	private EmployeeExamService employeeExamService;
	private String examId;
	private Map response;// 用于包装所有回传的ajax数据
	private Exam exam = new Exam();

	/**
	 * 根据ID显示信息并传到前台
	 * 
	 * @return
	 */
	public String getExamInfo() {
		response = new HashMap();
		Exam examBaseInfo = null;
		List<Map<String, Object>> employees = null;
		try {
			if (ValidateCheck.isNotNull(examId)) {
				examBaseInfo = examService.getExamInfoByExamId(examId);// 考试基本信息
				employees = employeeExamService.getEmployeeexamsByExamId(examId);// 考试参考员工信息
			}
		} catch (Exception e) {
			logger.error("查询考试基本信息出错!", e);
		}
		response.put("examBaseInfo", examBaseInfo);
		response.put("employees", employees);
		return "findExam";
	}

	/**
	 * ajax异步修改考试信息
	 * 
	 * @return
	 */
	private String examMethod;
	public String update() {
		response = new HashMap();
		String updateResult = "";
		try {
			updateResult = examService.updateExamById(exam,examMethod) ? "修改成功!" : "修改失败";
		} catch (Exception e) {
			updateResult = "修改失败";
			logger.error("修改考试失败!", e);
		}
		response.put("updateResult", updateResult);
		return SUCCESS;
	}

	// get,set
	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public Map getResponse() {
		return response;
	}

	public void setResponse(Map response) {
		this.response = response;
	}

	@Override
	public Exam getModel() {
		// TODO Auto-generated method stub
		return exam;
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

}
