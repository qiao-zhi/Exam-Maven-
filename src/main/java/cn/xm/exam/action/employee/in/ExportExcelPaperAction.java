package cn.xm.exam.action.employee.in;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import cn.xm.exam.bean.employee.in.Department;
import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.service.employee.in.DepartmentService;
import cn.xm.exam.service.employee.in.EmployeeInService;
import cn.xm.exam.service.exam.exam.ExamService;
import cn.xm.exam.utils.GenerateEmployeeInTrainFile;
import cn.xm.exam.utils.ResourcesUtil;

@Controller
@Scope("prototype")
public class ExportExcelPaperAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	// 查数据的身份证编号
	private String idcode;

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	@Autowired
	private ExamService examService;

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	// 导出的Excel名称
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// 得到员工信息
	@Autowired
	private EmployeeInService employeeInService;

	public EmployeeInService getEmployeeInService() {
		return employeeInService;
	}

	public void setEmployeeInService(EmployeeInService employeeInService) {
		this.employeeInService = employeeInService;
	}

	@Autowired
	private DepartmentService departmentService;

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	// 获取数据
	public List<Map<String, Object>> findExamEmployeeByExamId() throws Exception {

		List<Map<String, Object>> myexam = examService.getExamInfoByEmployeeId(idcode);

		return myexam;
	}
	
	//ll 生成Excel文件
	public boolean writeExamEmployees2Excel(List<Map<String, Object>> exams, String fileName) throws Exception {

		EmployeeIn employeeIn = employeeInService.getEmployeeInByIdcode(idcode);
		
		Map<String, Object> map = GenerateEmployeeInTrainFile.generateEmployeeInTrainProfile(fileName, employeeIn, exams);
		HSSFWorkbook workbook = (HSSFWorkbook) map.get("workBook");
	
		HSSFSheet sheet = (HSSFSheet) map.get("sheet");
		
		//查询部门名称
		Department department = departmentService.getDepartmentById(employeeIn.getDepartmentid());		
		HSSFRow row = sheet.getRow(2);
		HSSFCell cell = row.getCell(3);
		//设置部门名称
		cell.setCellValue(department.getDepartmentname());
	
		//导出图片操作
		String path = ResourcesUtil.getValue("path", "photo");
		String filepath = path + "\\" + idcode + ".jpg";
		
	    ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();   
	    BufferedImage bufferImg = ImageIO.read(new File(filepath));   
	    ImageIO.write(bufferImg, "jpg", byteArrayOut);
	    
	    //画图的顶级管理器，一个sheet只能获取一个
	    HSSFPatriarch patriarch = sheet.createDrawingPatriarch();   
	    //anchor主要用于设置图片的属性
	    HSSFClientAnchor anchor = new HSSFClientAnchor(20, 5, 1000, 250, (short) 4, 1, (short) 4, 2);   
	    //注意：这个方法在新版本的POI中参数类型改成了（AnchorType anchorType）　
	 	anchor.setAnchorType(3); 
	    patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
	
		// 创建一个文件
		File file = new File(fileName);
		// 如果存在就删除
		if (file.exists()) {
			file.delete();
		}

		try {
			workbook.close();
			file.createNewFile();
			// 打开文件流
			FileOutputStream outputStream = FileUtils.openOutputStream(file);

			workbook.write(outputStream);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}
	
	

	// 3.打开文件的流提供下载
	public InputStream getInputStream() throws Exception {
		this.create();// 创建文件到指定目录下
		// String path =
		// ServletActionContext.getServletContext().getRealPath("/files/examEmployeesExcel");
		String filepath = ResourcesUtil.getValue("path", "excel") + fileName + ".xls";

		File file = new File(filepath);
		// 只用返回一个输入流

		return FileUtils.openInputStream(file);// 打开文件
	}

	// 产生Excel到文件夹下面
	public void create() throws Exception {
		// 获取工程下的路径
		// String path =
		// ServletActionContext.getServletContext().getRealPath("/files/examEmployeesExcel");
		String filepath = ResourcesUtil.getValue("path", "excel") + fileName + ".xls";
		// 获取文件
		List<Map<String, Object>> examEmployees = this.findExamEmployeeByExamId();
		this.writeExamEmployees2Excel(examEmployees, filepath);
	}

	// 文件下载名
	public String getDownloadFileName() {
		String downloadFileName = "";
		// String filename = fileName + ".xls";
		String filename = idcode + ".xls";

		try {
			downloadFileName = URLEncoder.encode(filename, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	@Override
	public String execute() throws Exception {
		// 先将名字设为秒数产生唯一的名字
		// this.setFileName(String.valueOf(System.currentTimeMillis()));
		this.setFileName(idcode);
		return super.execute();
	}
	
	
}