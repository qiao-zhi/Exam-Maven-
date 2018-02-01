package cn.xm.exam.action.exam.exam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.service.exam.examPaper.ExamPaperService;
import cn.xm.exam.utils.ExportPaperPdfUtil;
import cn.xm.exam.utils.RemoveHtmlTag;
import cn.xm.exam.utils.Word2PdfUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 导出试卷 1.查出数据 2.Word 3.打开流，提供下载
 * 
 * @author QiaoLiQiang
 * @time 2017年10月31日下午10:29:51
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class ExtExamPaperAction extends ActionSupport {
	private Logger logger = Logger.getLogger(FindExamAction.class);
	private String fileName;// 导出的Excel名称
	@Autowired
	private ExamPaperService examPaperService;
	// 1.查数据
	private String paperId;

	public Exampaper findPaperAllInfoById() {
		Exampaper paper = null;
		try {
			paper = RemoveHtmlTag.removePaperTag(examPaperService.getPaperAllInfoByPaperId(paperId));
		} catch (SQLException e) {
			logger.error("查询试卷所有信息出错！！！", e);
		}
		return paper;
	}

	// 2.写入Word
	public void writeExamPaper2Word(Exampaper paper) {
		// 获取路径
		String path = ServletActionContext.getServletContext().getRealPath("/files/papers");
		// 用于携带数据的map
		String filePath = path + "\\" + fileName + ".pdf";
		// Configuration用于读取ftl文件
		// 输出文档路径及名称
		File outFile = new File(filePath);
		// 获取文件的父文件夹并删除文件夹下面的文件
		File parentFile = outFile.getParentFile();
		// 获取父文件夹下面的所有文件
		File[] listFiles = parentFile.listFiles();
		if (parentFile != null && parentFile.isDirectory()) {
			for (File fi : listFiles) {
				// 删除文件
				fi.delete();
			}
		}
		try {
			// 调用工具类写到pdf
			ExportPaperPdfUtil.writeExampaperPdf(paper, filePath);
		} catch (Exception e) {
			logger.error("试卷写入pdf出错", e);
		}
	}

	// 3.打开文件的流提供下载
	public InputStream getInputStream() throws Exception {
		Exampaper paper = this.findPaperAllInfoById();// 查数据
		this.writeExamPaper2Word(paper);// 写入数据
		String path = ServletActionContext.getServletContext().getRealPath("/files/papers");
		String destPath = path + "\\" + fileName + ".pdf";
		File file = new File(destPath);
		// 只用返回一个输入流
		return FileUtils.openInputStream(file);// 打开文件
	}

	// 文件下载名
	public String getDownloadFileName() {
		String downloadFileName = "";
		String filename = fileName + ".pdf";
		try {
			downloadFileName = new String(filename.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	@Override
	public String execute() throws Exception {
		// 先将名字设为秒数产生唯一的名字
		// this.setFileName(String.valueOf(System.currentTimeMillis()));
		this.setFileName("考试试卷");
		return super.execute();
	}

	// get，set方法
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

}
