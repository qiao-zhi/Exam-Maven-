package cn.xm.exam.action.question;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import cn.xm.exam.service.question.QuestionsService;
import cn.xm.exam.vo.question.QuestionsQueryVo;

@Controller
@Scope("prototype")
public class ExportWordQuestionsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private  String fileName;
	private List<QuestionsQueryVo> list;
	@Resource
	private QuestionsService questionsService; 
	private String questionBankId;
	private String questionType;


	public InputStream getInputStream() throws Exception {
		create();
		String path = ServletActionContext.getServletContext().getRealPath("files");
		String filepath = path +"\\" + fileName + ".doc";
		//完整的路径名
		File file = new File(filepath);
		//只用返回一个输入流
		return FileUtils.openInputStream(file);
	}


	//产生模板到文件夹下面
	public  void create(){
		//获取工程下的路径
		String path = ServletActionContext.getServletContext().getRealPath("files");
		//先将该目录下的所有文件清空
		File file = new File(path);
		File[] files = file.listFiles();
		for(int i=0;i<files.length;i++){
			files[i].delete();
		}
		String filepath = path +"\\" + fileName + ".doc";
		ExportTxtPaperUtils.exportTxtPaper(list, filepath);
	}
	
	
	//文件下载名
	public String getDownloadFileName(){
		String downloadFileName = "";
		String filename = fileName + ".doc";
		try {
			downloadFileName = URLEncoder.encode(filename,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	
	
	//导出题库中的试题为word文档
	public String execute() throws Exception {
		this.setFileName(String.valueOf(System.currentTimeMillis()));
		List<String> questionTypeList = new ArrayList<String>();
		if(questionType.indexOf("1")>=0){
			questionTypeList.add("单选题");
		}
		if(questionType.indexOf("2")>=0){
			questionTypeList.add("多选题");
		}
		if(questionType.indexOf("3")>=0){
			questionTypeList.add("判断题");
		}
		//根据题库ID调用service方法查询题库的试题和选项的集合
		list = questionsService.getQuestionsAndOptions(questionBankId,questionTypeList);
		this.setList(list);
		return super.execute();
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public List<QuestionsQueryVo> getList() {
		return list;
	}


	public void setList(List<QuestionsQueryVo> list) {
		this.list = list;
	}


	public String getQuestionBankId() {
		return questionBankId;
	}


	public void setQuestionBankId(String questionBankId) {
		this.questionBankId = questionBankId;
	}


	public String getQuestionType() {
		return questionType;
	}


	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	
	
}
