package cn.xm.exam.action.employee.out;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import cn.xm.exam.service.employee.out.EmployeeOutService;
import cn.xm.exam.utils.ValidateCheck;
import cn.xm.exam.vo.employee.out.EmployeeOutBaseInfo;

/**   
*    
* 项目名称：Exam   
* 类名称：ExportExcelEmployeeOutInfoAction   
* 类描述：导出外部员工基本信息的action   
* 创建人：Leilong  
* 创建时间：2017年10月8日 下午6:37:11      
* @version    
*    
*/
@Controller
@Scope("prototype")
public class ExportExcelEmployeeOutInfoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private EmployeeOutService employeeOutService;
	private String fileName;
	private List<EmployeeOutBaseInfo> list;
	//单位ID
	private String unitId;
	//大修ID
	private String bigId;
	//符合条件的身份证
	private String employeeOutIds;
	//符合条件的大修员工ID集合
	private String bigEmployeeOutIds;
	
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
		GenerateExcelEmployeeOutInfo.exportExcelPaper(list, filepath);

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

	//用于员工工作证的导出
	public String findEmployeeOutInfosToExport() throws Exception{
		
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			//通过毫秒数获得唯一的名字
			this.setFileName(String.valueOf(System.currentTimeMillis()));
			condition = generationCondition(condition);
			list  = employeeOutService.getEmpInfoForCertificateWithCondition(condition);
			//设置条件为2表示已经生成工作证
			condition.put("trainStatus", "2");
			//生成工作证后修改大修员工表的状态标记为已生成工作证
			employeeOutService.updateHaulEmployeeOutTrainStatusByCondition(condition);
			this.setList(list);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return super.execute();
	}
	
	//组装查询条件
	private Map<String,Object> generationCondition(Map<String,Object> condition){
		
		
		if(ValidateCheck.isNotNull(unitId)){
			condition.put("unitId", unitId);
		}
		
		if(ValidateCheck.isNotNull(bigId)){
			condition.put("bigId", bigId);
		}
		
		if(ValidateCheck.isNotNull(employeeOutIds)){
			String[] empInfoarr = employeeOutIds.split(",");			
			condition.put("employeeOutIds",Arrays.asList(empInfoarr));
		}
		
		if(ValidateCheck.isNotNull(bigEmployeeOutIds)){
			String[] empInfoarr = bigEmployeeOutIds.split(",");			
			condition.put("bigEmployeeOutIds",Arrays.asList(empInfoarr));
		}
		
		
		return condition;
	}
	
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getBigId() {
		return bigId;
	}

	public void setBigId(String bigId) {
		this.bigId = bigId;
	}

	public String getEmployeeOutIds() {
		return employeeOutIds;
	}

	public void setEmployeeOutIds(String employeeOutIds) {
		this.employeeOutIds = employeeOutIds;
	}

	public List<EmployeeOutBaseInfo> getList() {
		return list;
	}

	public void setList(List<EmployeeOutBaseInfo> list) {
		this.list = list;
	}

	public String getBigEmployeeOutIds() {
		return bigEmployeeOutIds;
	}

	public void setBigEmployeeOutIds(String bigEmployeeOutIds) {
		this.bigEmployeeOutIds = bigEmployeeOutIds;
	}
	
	
	
}
