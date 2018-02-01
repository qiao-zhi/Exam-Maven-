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

/**   
*    
* 项目名称：Exam   
* 类名称：ExportTxtQuestionsAction   
* 类描述：  导出题库中的试题为txt格式的文件
* 创建人：Leilong  
* 创建时间：2017年10月16日 下午12:52:47    
* @version    
*    
*/
@Controller
@Scope("prototype")
public class ExportTxtQuestionsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private QuestionsService questionsService;
	private String fileName;
	private List<QuestionsQueryVo> list;
	private String questionBankId;
	private String questionType;
	
	

	public InputStream getInputStream() throws Exception {
		create();
		String path = ServletActionContext.getServletContext().getRealPath("files");
		String filepath = path + "\\" + fileName + ".txt";
		File file = new File(filepath);
		// 只用返回一个输入流
		return FileUtils.openInputStream(file);
	}

	//在服务器端生成要导出的文件
	public void create() {
		// 获取工程下的路径
		String path = ServletActionContext.getServletContext().getRealPath("files");
		//先将该目录下的所有文件清空
		File file = new File(path);
		File[] files = file.listFiles();
		for(int i=0;i<files.length;i++){
			files[i].delete();
		}
		String filepath = path + "\\" + fileName + ".txt";
		ExportTxtPaperUtils.exportTxtPaper(list, filepath);
	}

	// 文件下载名
	public String getDownloadFileName() {
		String downloadFileName = "";
		String filename = fileName + ".txt";
		try {
			downloadFileName = URLEncoder.encode(filename, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	//题库导出的action执行的函数
	public String execute() throws Exception {
		//设置文件名称
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

	public String getQuestionBankId() {
		return questionBankId;
	}

	public void setQuestionBankId(String questionBankId) {
		this.questionBankId = questionBankId;
	}
	
	public List<QuestionsQueryVo> getList() {
		return list;
	}

	public void setList(List<QuestionsQueryVo> list) {
		this.list = list;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	
}
