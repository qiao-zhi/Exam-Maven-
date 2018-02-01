package cn.xm.exam.action.exam.examPaper;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.service.exam.exam.ExamService;
import cn.xm.exam.service.exam.examPaper.ExamPaperService;

/**
 * 删除试卷Action(ajax删除)
 * 
 * @author QiaoLiQiang
 * @time 2017年10月12日下午12:42:00
 */
@Controller
@Scope("prototype")
public class DeletePaperAction extends ActionSupport {
	private Logger logger = Logger.getLogger(DeletePaperAction.class);
	@Resource
	private ExamPaperService examPaperService;
	@Resource
	private ExamService examService;// 考试service(用于查询试卷使用次数)
	private String paperId;
	private String deleteResult;

	public String deletePaper() {
		/**
		 * 试卷使用次数
		 */
		int useTime = 0;
		try {
			useTime = examService.getPaperUseTimesByPaperId(paperId);
		} catch (SQLException e1) {
			logger.error("查询试卷使用次数出错", e1);
		}
		// 试卷使用次数大于0的时候提醒用户已经有考试使用试卷
		if (useTime > 0) {
			deleteResult = "已经有考试使用本试卷，你不能删除试卷！";
			return SUCCESS;
		}
		try {
			deleteResult = examPaperService.deleteExamPaperAllInfoByPaperId(paperId) ? "删除成功！" : "删除失败！！！";
		} catch (SQLException e) {
			logger.error("删除试卷出错", e);
			deleteResult = "删除失败！！！";
		}
		return SUCCESS;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getDeleteResult() {
		return deleteResult;
	}

	public void setDeleteResult(String deleteResult) {
		this.deleteResult = deleteResult;
	}

}
