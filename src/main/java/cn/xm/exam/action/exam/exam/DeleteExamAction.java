package cn.xm.exam.action.exam.exam;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.service.exam.exam.ExamService;

/**
 * 删除考试信息
 * 
 * @author QiaoLiQiang
 * @time 2017年10月24日上午12:27:39
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class DeleteExamAction extends ActionSupport {
	private Logger logger = Logger.getLogger(FindExamAction.class);
	private Map response;// 用于包装所有回传的ajax数据
	@Resource
	private ExamService examService;
	private String examId;// 考试id

	@Override
	public String execute() {
		response = new HashMap();
		String result = null;// 删除结果
		try {
			result = examService.deleteExamById(examId) ? "删除成功!" : "删除失败!";
		} catch (Exception e) {
			result = "删除失败!";
			logger.error("删除考试出错", e);
		}
		response.put("result", result);
		return SUCCESS;
	}

	public Map getResponse() {
		return response;
	}

	public void setResponse(Map response) {
		this.response = response;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}
	
}
