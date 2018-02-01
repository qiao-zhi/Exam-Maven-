package cn.xm.exam.action.question;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.question.Questions;
import cn.xm.exam.service.question.QuestionsService;

@Controller
@Scope("prototype")
public class ImportQuestionsAction  extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Resource
	private QuestionsService questionsService; 
	//文件上传的相关属性
	private File fileName;
	private String fileNameContentType;
	private String fileNameFileName;
	//部门ID
	private String departmentId;
	//上传人姓名
	private String upLoadPersonName;
	//上传人ID
	private String upLoadPersonId;
	//题库编号
	private String questionBankId;
	private Map<String,Object> result;
	
	//word格式试题信息的导入
	public String importQuestionsWithWord(){
		
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
			//List<HashMap<String, Object>> list = ReadWordUtils.readWordData(dir);
			List<HashMap<String, Object>> list = ReadExcelUtils.readExcelData(dir);
			//将上传的文件删除
			File file = new File(dir);
			file.delete();
			for (HashMap<String, Object> hashMap : list) {
				Questions questionObj = (Questions) hashMap.get("question");
				//设置试题对应的题库ID
				questionObj.setQuestionbankid(questionBankId);
				//设置上传时间
				questionObj.setUploadtime(new Date());
				//上传人姓名和ID
				questionObj.setEmplloyeename(upLoadPersonName);
				questionObj.setEmployeeid(upLoadPersonId);
				questionObj.setHaspicture("0");
				/*//根据部门的命名规则，长度为2代表厂级，5代表部门级，8代表班组级
				int length = departmentId.length();
				switch(length){
					case 2 : questionObj.setLevel("1");
							break;
					case 5 : questionObj.setLevel("2");
							break;
					case 8 : questionObj.setLevel("3");
							break;
					default:questionObj.setLevel("3");
				}*/
			}
			int count;
				count = questionsService.saveQuestionBatch(list);
			result = new HashMap<String,Object>();
			if(count==list.size()){
				result.put("result", "添加成功！");
			} else {
				result.put("result", "添加失败！");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
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

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getUpLoadPersonName() {
		return upLoadPersonName;
	}

	public void setUpLoadPersonName(String upLoadPersonName) {
		this.upLoadPersonName = upLoadPersonName;
	}

	public String getUpLoadPersonId() {
		return upLoadPersonId;
	}

	public void setUpLoadPersonId(String upLoadPersonId) {
		this.upLoadPersonId = upLoadPersonId;
	}

	public String getQuestionBankId() {
		return questionBankId;
	}

	public void setQuestionBankId(String questionBankId) {
		this.questionBankId = questionBankId;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	
	
}
