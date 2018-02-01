package cn.xm.exam.action.grade;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.grade.EmployeeExamService;
import cn.xm.exam.utils.GetDayOfMonth;
import cn.xm.exam.utils.ValidateCheck;
import cn.xm.exam.vo.grade.EmployeeExamGrade;

/**   
*    
* 项目名称：Exam   
* 类名称：ExportExcelEmployeeGradeAction   
* 类描述：导出员工成绩单的action   
* 创建人：Leilong  
* 创建时间：2017年10月8日 下午6:37:11      
* @version    
*    
*/
@Controller
@Scope("prototype")
public class ExportExcelEmployeeGradeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private EmployeeExamService employeeExamService;
	private String fileName;
	private List<EmployeeExamGrade> list;
	private String examId;
	private Map<String, Object> result;
	//考试名称
	private String examName;
	//考试等级
	private String examLevel;
	//考试时间
	private String examTime;
	//员工姓名
	private String employeeName;
	//员工性别
	private String employeeSex;
	//员工身份证号
	private String employeeIdCard;
	//是否通过
	private String isPass;
	
	//获得文件输入流
	public InputStream getInputStream() throws Exception {
		create();
		String path = ServletActionContext.getServletContext().getRealPath("files");
		String filepath = path + "\\" + fileName + ".xls";
		File file = new File(filepath);
		// 只用返回一个输入流
		return FileUtils.openInputStream(file);
	}
	
	// 产生模板到文件夹下面
	public void create() {
		// 获取工程下的路径
		String path = ServletActionContext.getServletContext().getRealPath("files");
		//先将该目录下的所有文件清空
		File file = new File(path);
		File[] files = file.listFiles();
		for(int i=0;i<files.length;i++){
			files[i].delete();
		}
		String filepath = path + "\\" + fileName + ".xls";
		// 同一个工作簿创建的才可以创建不同的工作表到同一个文件
		// 通过毫秒数获取唯一的名字
		GenerateExcelEmployeeGrade.exportExcelPaper(list, filepath);

	}

	// 文件下载名
	public String getDownloadFileName() {
		String downloadFileName = "";
		String filename = fileName + ".xls";
		try {
			downloadFileName = URLEncoder.encode(filename, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	//用于成绩管理页面考试成绩单的导出
	public String getEmployeeGradesToExport(){
		//通过毫秒数获得唯一的名字
		this.setFileName(String.valueOf(System.currentTimeMillis()));
		try {
			list = employeeExamService.getEmployeeGradeByExamId(examId);
			for (EmployeeExamGrade employeeExamGrade : list) {
				if(employeeExamGrade.getEmployeetype().equals("1")){
					employeeExamGrade.setEmployeetype("外部员工");
				}else{
					employeeExamGrade.setEmployeetype("内部员工");
				}
			}
			this.setList(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//用于员工成绩页面组合条件查询的员工成绩的导出
	public String findEmployeeGradesToExport() throws Exception{
		
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			//通过毫秒数获得唯一的名字
			this.setFileName(String.valueOf(System.currentTimeMillis()));
			condition = generationCondition(condition);
			list = employeeExamService.findEmployeeGradeByCondition(condition);
			for (EmployeeExamGrade employeeExamGrade : list) {
				if(employeeExamGrade.getEmployeetype().equals("1")){
					employeeExamGrade.setEmployeetype("外部员工");
				}else{
					employeeExamGrade.setEmployeetype("内部员工");
				}
			}
			this.setList(list);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return super.execute();
	}
	
	//组装查询条件
	private Map<String,Object> generationCondition(Map<String,Object> condition){
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
		// 获取用户信息
		Subject currentUser = SecurityUtils.getSubject();
		boolean permitted = currentUser.isPermitted("exammanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
		String departmentId = permitted ? null : departmentIdSession;
		condition.put("department_Id", departmentId);
		
		if(ValidateCheck.isNotNull(examName)){
			condition.put("exam_Name", examName);
		}
		
		if(ValidateCheck.isNotNull(examLevel)){
			condition.put("exam_level",examLevel);
		}
		
		if(ValidateCheck.isNotNull(examTime)){
			Integer year = Integer.valueOf(examTime.substring(0, 4));
			Integer month = Integer.valueOf(examTime.substring(5));
			String startTime = GetDayOfMonth.getFirstDayOfMonth(year, month);
			String endTime = GetDayOfMonth.getLastDayOfMonth(year, month);
			condition.put("exam_startTime",startTime+" 00:00:00");
			condition.put("exam_endTime",endTime+" 23:59:59");		
		}
		
		if(ValidateCheck.isNotNull(employeeName)){
			condition.put("exam_EmployeeName",employeeName);
		}
		
		if(ValidateCheck.isNotNull(employeeSex)){
			condition.put("exam_EmployeeSex",employeeSex);
		}
		
		if(ValidateCheck.isNotNull(employeeIdCard)){
			condition.put("exam_EmployeeIdCard",employeeIdCard);
		}
		
		if(ValidateCheck.isNotNull(isPass)){
			condition.put("exam_isPass",isPass);
		}
		
		if(ValidateCheck.isNotNull(examId)){
			condition.put("examId",examId);
		}
		
		
		return condition;
	}
	
	public List<EmployeeExamGrade> getList() {
		return list;
	}

	public void setList(List<EmployeeExamGrade> list) {
		this.list = list;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamLevel() {
		return examLevel;
	}

	public void setExamLevel(String examLevel) {
		this.examLevel = examLevel;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeSex() {
		return employeeSex;
	}

	public void setEmployeeSex(String employeeSex) {
		this.employeeSex = employeeSex;
	}

	public String getEmployeeIdCard() {
		return employeeIdCard;
	}

	public void setEmployeeIdCard(String employeeIdCard) {
		this.employeeIdCard = employeeIdCard;
	}

	public String getIsPass() {
		return isPass;
	}

	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
	
}
