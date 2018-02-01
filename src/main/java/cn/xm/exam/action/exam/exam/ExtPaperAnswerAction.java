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
import cn.xm.exam.service.exam.examPaper.ExamPaperQuestionService;

/**
 * 导出试卷的参考答案 1.获取到试卷List集合 2.写到Excel中 3.打开输入流提供下载
 * 
 * @author QiaoLiQiang
 * @time 2017年10月30日下午3:48:29
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class ExtPaperAnswerAction extends ActionSupport {
	private Logger logger = Logger.getLogger(FindExamAction.class);
	private String fileName;// 导出的Excel名称
	private String paperId;// 导出答案的试卷ID
	@Resource
	private ExamPaperQuestionService examPaperQuestionService;// 试卷题service(用于查询答案)

	// 1.查试卷答案
	private String examId;// 考试ID(用于查询考试员工)

	public List<Map<String, Object>> findPaperAnswerByPaperId() {
		List<Map<String, Object>> answers = null;
		try {
			answers = examPaperQuestionService.getPaperAnswerAndScoreByPaerId(paperId);
		} catch (SQLException e) {
			logger.error("导出考试试卷的答案异常", e);
		}
		return answers;
	}

	// 2.将答案写入Excel
	public boolean writePaperAnswer2Excel(List<Map<String, Object>> paperAnswers, String fileName) {
		// 标题
		String[] title = { "题号", "答案", "得分", "部分分" };
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
		for (int i = 1, length_1 = (paperAnswers.size() + 1); i < length_1; i++) {
			// 创建第i行
			HSSFRow nextRow = sheet.createRow(i);
			// 获取数据(一个答案)
			Map<String, Object> answerAndScore = paperAnswers.get(i - 1);
			for (int j = 0; j < 4; j++) {
				HSSFCell cell2 = nextRow.createCell(j);
				if (j == 0) {
					cell2.setCellValue(i);
				}
				if (j == 1) {
					if (answerAndScore.get("answer").toString() == null)
						continue;
					// 将答案中的123456对应替换为ABCDE
					String answer = MyElFunction.replace(answerAndScore.get("answer").toString(), "12345", "ABCDE");
					// 替换判断题的正确与错误
					if ("正确".equals(answer)) {
						answer = "A";
					}
					if ("错误".equals(answer)) {
						answer = "B";
					}
					cell2.setCellValue(answer);

				}
				if (j == 2) {// 设置分值
					cell2.setCellValue(answerAndScore.get("score").toString());

				}
				if (j == 3) {// 设置分值
					cell2.setCellValue("0");

				}
			}
		}

		// 创建一个文件
		File file = new File(fileName);
		// 获取文件的父文件夹
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
	public InputStream getInputStream() throws Exception {
		this.create();// 创建文件到指定目录下
		String path = ServletActionContext.getServletContext().getRealPath("/files/paperAnswerExcel");
		String filepath = path + "\\" + fileName + ".xls";
		File file = new File(filepath);
		// 只用返回一个输入流
		return FileUtils.openInputStream(file);// 打开文件
	}

	// 产生Excel到文件夹下面
	public void create() {
		// 获取工程下的路径
		String path = ServletActionContext.getServletContext().getRealPath("/files/paperAnswerExcel");
		String filepath = path + "\\" + fileName + ".xls";
		List<Map<String, Object>> answers = this.findPaperAnswerByPaperId();// 查出数据
		this.writePaperAnswer2Excel(answers, filepath);// 写入Excel
	}

	// 文件下载名
	public String getDownloadFileName() {
		String downloadFileName = "";
		String filename = fileName + ".xls";
		try {
			downloadFileName = new String(filename.getBytes("UTF-8"),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	@Override
	public String execute() throws Exception {
		// 先将名字设为秒数产生唯一的名字
		this.setFileName("试卷答案");
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

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

}
