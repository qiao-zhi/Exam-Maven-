package cn.xm.exam.action.grade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.grade.Employeeexam;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.grade.EmployeeExamService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.GetDayOfMonth;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ValidateCheck;
import cn.xm.exam.vo.grade.EmployeeExamGrade;
import cn.xm.exam.vo.grade.ExamEmployeeexamExampaper;
import cn.xm.exam.vo.grade.OnlineExamEmployeeInfo;

/**   
*    
* 项目名称：Exam   
* 类名称：EmployeeExamAction   
* 类描述： 成绩管理的action
* 创建人：Leilong 
* 创建时间：2017年10月8日 下午5:49:58      
* @version    
*    
*/
@Controller
@Scope("prototype")
public class EmployeeExamAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource
	private EmployeeExamService employeeExamService;
	private Map<String, Object> result;
	//当前页
	private String currentPage;
	//当前页显示的条数
	private String currentCount;
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
	//及格分数
	private String passGrade;
	//优秀分数	
	private String excellentGrade;
	//考试ID
	private String examId;
	//考试方式
	private String examMethod;
	//单位ID
	private String unitId;
	
	//线下考试员工成绩集合
	private String employeeOutGradeInfoStr;
	
	private File fileName;
	private String fileNameContentType;
	private String fileNameFileName;
	
	//根据条件查询考试的成绩信息
	public String findExamGradesInfoWithCondition(){
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			result = new HashMap<String,Object>();
			condition = generationCondition(condition);
			PageBean<ExamEmployeeexamExampaper> pageBean = employeeExamService.getExamGradesInfoWithCondition(Integer.valueOf(currentPage), Integer.valueOf(currentCount), condition);
			result.put("pageBean", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//根据条件分析考试成绩，查询优良差的人数
	public String findExamGradeAnalyseInfoByCondition(){
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			result = new HashMap<String,Object>();
			condition = generationCondition(condition);
			Map<String, Object> ExamGradeAnalyseInfo = employeeExamService.getExamGradeAnalyseInfoByCondition(condition);
			result.put("ExamGradeAnalyseInfo", ExamGradeAnalyseInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//根据条件查询员工的考试成绩信息
	public String findEmployeeGradeInfoByCondition(){
		
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			result = new HashMap<String,Object>();
			condition = generationCondition(condition);
			PageBean<EmployeeExamGrade> pageBean = employeeExamService.findExamGradesWithCondition(Integer.valueOf(currentPage), Integer.valueOf(currentCount), condition);
			result.put("pageBean", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	//根据考试ID查询线下考试的员工信息
	public String getEmployeeOutInfoByExamId(){
		try {
			result = new HashMap<String,Object>();
			List<Employeeexam> employeeOutInfoList = employeeExamService.getEmployeeOutInfoByExamId(examId);
			result.put("employeeOutInfoList", employeeOutInfoList);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//将上传文件中的内容回显到模态框中
	public String getExcelEmployeeGradeInfo(){
		try {
			ServletContext servletContext = ServletActionContext.getServletContext();
			//fileNameFileName表示文件上传时候的名字，也可以自己用UUID定义一个新的名字
			String dir = servletContext.getRealPath(fileNameFileName);
			//文件输出流，写到dir指定的目录与名字
			FileOutputStream outputStream = new FileOutputStream(dir);
			//打开上传的文件的输入流
			FileInputStream inputStream = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			int len = 0;
			//从此输入流中将最多 b.length 个字节的数据读入一个 byte 数组中.读入缓冲区的字节总数，如果因为已经到达文件末尾而没有更多的数据，则返回 -1。
			while((len = inputStream.read(buffer))!=-1){
				//将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此文件输出流。
				outputStream.write(buffer, 0, len);
			}
			inputStream.close();
			outputStream.close();
			result = new HashMap<String,Object>();
			String excel2Json = Excel2JSON.excel2Json(dir);
			List<Employeeexam> list = (List<Employeeexam>)JSON2BeanList.json2list(excel2Json);
			for (Employeeexam grade : list) {
				grade.setExamid(examId);
			}
			//将上传的文件删除
			File file = new File(dir);
			file.delete();
			result.put("ExcelGradeInfo", list);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//批量导入线下考试员工的成绩信息
	public  String importEmployeeGrade() {
		//根据考试ID批量删除外部员工在成绩表中的信息,修改为采用update语句进行修改
		boolean isDelete = true;
		try {
			List<Employeeexam> employeeOutGradeInfo = (List<Employeeexam>)JSON2BeanList.json2list(employeeOutGradeInfoStr);
			result = new HashMap<String,Object>();
			//isDelete = employeeExamService.deleteEmployeeOutGradeBatch(examId);
			if(isDelete){
				//调用service方法批量插入外部员工成绩
				int isAdd = employeeExamService.addEmployeeOutGradeBatch(employeeOutGradeInfo);	
				if(isAdd==employeeOutGradeInfo.size()){
					result.put("result", "添加成功！");
				} else {
					result.put("result", "添加失败！");				
				}
			}else {
				result.put("result", "添加失败！");				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return SUCCESS;
	}
	
	//根据考试编号和员工考号获取在线考试员工的所有信息
	public String getOnlineExamEmployeeInAllInfo(){
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("idCard", employeeIdCard);
			condition.put("examId", examId);
			OnlineExamEmployeeInfo onlineExamEmployeeInAllInfo = employeeExamService.getExamGardeByEmployeeIdAndExamId(condition);
			result = new HashMap<String,Object>();
			result.put("onlineExamEmployeeInAllInfo", onlineExamEmployeeInAllInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//根据条件查询部门考试信息
	public String getUnitExamGradesByCondition(){
		Map<String,Object> condition = new HashMap<String,Object>();
		result = new HashMap<String,Object>();
		condition = generationCondition(condition);
		PageBean<Map<String, Object>> pageBean = null;
		try {
			pageBean = employeeExamService.getUnitExamInfosByCondition(Integer.valueOf(currentPage), Integer.valueOf(currentCount), condition);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		result.put("pageBean", pageBean);
		return SUCCESS;
	}
	
	//根据考试ID和部门ID查询该部门参加考试的人员成绩详情
	public String getEmployeeGradeInfosByIds(){
		Map<String,Object> condition = new HashMap<String,Object>();
		result = new HashMap<String,Object>();
		condition = generationCondition(condition);
		List<Employeeexam> employeeGradeInfos = null;
		try {
			employeeGradeInfos = employeeExamService.getEmployeeGradeInfosByIds(condition);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		result.put("employeeGradeInfos", employeeGradeInfos);
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
		
		if(ValidateCheck.isNotNull(excellentGrade)){
			condition.put("excellentGrade",excellentGrade.trim());
		}
		
		if(ValidateCheck.isNotNull(passGrade)){
			condition.put("passGrade",passGrade);
		}
		
		if(ValidateCheck.isNotNull(examMethod)){
			condition.put("examMethod",examMethod);
		}
		
		if(ValidateCheck.isNotNull(unitId)){
			condition.put("unitId",unitId);
		}
		
		return condition;
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

	public String getPassGrade() {
		return passGrade;
	}

	public void setPassGrade(String passGrade) {
		this.passGrade = passGrade;
	}

	public String getExcellentGrade() {
		return excellentGrade;
	}

	public void setExcellentGrade(String excellentGrade) {
		this.excellentGrade = excellentGrade;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
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

	public File getFileName() {
		return fileName;
	}

	public void setFileName(File fileName) {
		this.fileName = fileName;
	}

	public String getFileNameContentType() {
		return fileNameContentType;
	}

	public void setFileNameContentType(String fileNameContentType) {
		this.fileNameContentType = fileNameContentType;
	}

	public String getFileNameFileName() {
		return fileNameFileName;
	}

	public void setFileNameFileName(String fileNameFileName) {
		this.fileNameFileName = fileNameFileName;
	}

	public String getEmployeeOutGradeInfoStr() {
		return employeeOutGradeInfoStr;
	}

	public void setEmployeeOutGradeInfoStr(String employeeOutGradeInfoStr) {
		this.employeeOutGradeInfoStr = employeeOutGradeInfoStr;
	}

	public String getExamMethod() {
		return examMethod;
	}

	public void setExamMethod(String examMethod) {
		this.examMethod = examMethod;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	
			
}
