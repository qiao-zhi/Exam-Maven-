package cn.xm.exam.action.exam.exam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.MyElFunction.MyElFunction;
import cn.xm.exam.service.exam.exam.ExamService;
import cn.xm.exam.service.grade.EmployeeExamService;

/**
 * 导出参考人信息 1.查出数据 2.写入Excel 3.打开流，提供下载
 * 
 * @author QiaoLiQiang
 * @time 2017年10月29日下午10:29:51
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class ExtExamEmployeesAction extends ActionSupport {
	private Logger logger = Logger.getLogger(FindExamAction.class);
	private String fileName;// 导出的Excel名称
	@Resource
	private ExamService examService;// 考试service
	@Resource
	private EmployeeExamService employeeExamService;// 员工成绩service

	// 1.查数据
	private String examId;// 考试ID(用于查询考试员工)

	public List<Map<String, Object>> findExamEmployeeByExamId() {
		List<Map<String, Object>> employees = null;
		try {
			employees = employeeExamService.getEmployeeexamsByExamId(examId);
		} catch (SQLException e) {
			logger.error("导出考试人员异常", e);
		}
		return employees;
	}

	// 2.写入Excel
	public boolean writeExamEmployees2Excel(List<Map<String, Object>> examEmployees, String fileName) {
		// 标题
		String[] title = { "身份证号", "姓名", "所属部门" };
		// 创建一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建一个工作表sheet
		HSSFSheet sheet = workbook.createSheet();
		// 设置列宽
		setColumnWidth(sheet, 7);
		// 创建第一行
		HSSFRow row = sheet.createRow(0);
		// 创建一个单元格
		HSSFCell cell = null;
		// 创建表头
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			// 设置样式
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置字体居中
			// 设置字体
			HSSFFont font = workbook.createFont();
			font.setFontName("宋体");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
			// font.setFontHeight((short)12);
			font.setFontHeightInPoints((short) 13);
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(title[i]);
		}

		// 写入数据
		// 从第二行开始追加数据
		for (int i = 1; i < (examEmployees.size() + 1); i++) {
			// 创建第i行
			HSSFRow nextRow = sheet.createRow(i);
			// 获取数据
			Map<String, Object> employees = examEmployees.get(i - 1);
			for (int j = 0; j < 7; j++) {
				HSSFCell cell2 = nextRow.createCell(j);
				if (j == 0) {
					cell2.setCellValue(employees.get("idCode").toString());
				}
				if (j == 1) {
					cell2.setCellValue(employees.get("name").toString());
				}
				if (j == 2) {
					cell2.setCellValue(employees.get("unitName").toString());
				}
			}
		}

		// 创建一个文件
		File file = new File(fileName);
		// 获取文件的父文件夹并删除文件夹下面的文件
		File parentFile = file.getParentFile();
		// 获取父文件夹下面的所有文件
		File[] listFiles = parentFile.listFiles();
		if (parentFile != null && parentFile.isDirectory()) {
			for (File fi : listFiles) {
				// 删除文件
				fi.delete();
			}
		}

		// 如果存在就删除
		if (file.exists()) {
			file.delete();
		}
		try {
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

	// 设置列宽()
	private static void setColumnWidth(HSSFSheet sheet, int colNum) {
		for (int i = 0; i < colNum; i++) {
			int v = 0;
			v = Math.round(Float.parseFloat("15.0") * 37F);
			v = Math.round(Float.parseFloat("20.0") * 267.5F);
			sheet.setColumnWidth(i, v);
		}
	}

	// 3.打开文件的流提供下载
	public InputStream getInputStream() {
		this.create();// 创建文件到指定目录下
		String path = ServletActionContext.getServletContext().getRealPath("/files/examEmployeesExcel");
		String filepath = path + "\\" + fileName + ".xls";
		File file = new File(filepath);
		// 只用返回一个输入流
		try {
			return FileUtils.openInputStream(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 打开文件
		return null;
	}

	// 产生Excel到文件夹下面
	public void create() {
		// 获取工程下的路径
		String path = ServletActionContext.getServletContext().getRealPath("/files/examEmployeesExcel");
		String filepath = path + "\\" + fileName + ".xls";
		// 获取文件
		List<Map<String, Object>> examEmployees = this.findExamEmployeeByExamId();
		this.writeExamEmployees2Excel(examEmployees, filepath);
	}

	// 文件下载名
	public String getDownloadFileName() {
		String downloadFileName = "";
		String filename = fileName + ".xls";
		try {
			downloadFileName = new String(filename.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	@Override
	public String execute() throws Exception {
		// 先将名字设为秒数产生唯一的名字
		this.setFileName("参考人员");
		// this.setFileName(String.valueOf(System.currentTimeMillis()));
		return super.execute();
	}

	// get，set方法
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

}
