package cn.xm.exam.action.exam.examPaper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.exam.Bigquestion;
import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.service.exam.exam.ExamService;
import cn.xm.exam.service.exam.examPaper.ExamPaperService;
import cn.xm.exam.utils.ValidateCheck;

/**
 * 修改试卷 0.先根据ID查出试卷到页面进行修改 1.根据试卷ID删除试卷 2.重新添加试卷(使用原来的ID)
 * 
 * @author QiaoLiQiang
 * @time 2017年10月16日下午10:44:47
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class UpdatePaperAction extends ActionSupport {
	private Logger logger = Logger.getLogger(UpdatePaperAction.class);
	@Autowired
	private ExamPaperService examPaperService;// 试卷服务
	@Resource
	private ExamService examService;
	private String paperId;// 试卷ID(用于先查询试卷的所有信息)
	private Exampaper exampaper;// 查询出来的考试试卷的所有信息
	private Bigquestion danxuanDati;// 单选大题
	private Bigquestion duoxuanDati;// 多选大题
	private Bigquestion panduanDati;// 判断大题
	private String updateResult;// 修改结果(转为JSON传到前台)
	private String sureResult;// 判断试卷能否被修改

	/**
	 * 根据ID查询试卷
	 * 
	 * @return
	 */
	public String findPaperAllInfoById() {
		try {
			exampaper = examPaperService.getPaperAllInfoByPaperId(paperId);
		} catch (SQLException e) {
			logger.error("查询试卷所有信息出错！！！", e);
		}
		return "findPaperAllInfo";
	}

	/**
	 * 根据使用试卷的考试总数判断试卷能否被修改
	 * 
	 * @return
	 */
	public String findPaperStatus() {
		sureResult = null;
		try {
			if (ValidateCheck.isNotNull(paperId)) {
				sureResult = examService.getExamCountByPaperIdAndStatus(paperId) > 0 ? "0" : "1";
			}
		} catch (SQLException e) {
			logger.error("查询试卷能否修改状态！！！", e);
		}
		return "surerResult";
	}

	/**
	 * 修改试卷(ajax异步修改) 服务层:1.删除试卷 2.保存试卷
	 * 
	 * @return
	 */
	public String updatePaper() {
		Map bigQues = new HashMap();
		if (danxuanDati != null && danxuanDati.getQuestions() != null) {
			bigQues.put("danxuanDati", danxuanDati);
		}
		if (duoxuanDati != null && duoxuanDati.getQuestions() != null) {
			bigQues.put("duoxuanDati", duoxuanDati);
		}
		if (panduanDati != null && panduanDati.getQuestions() != null) {
			bigQues.put("panduanDati", panduanDati);
		}
		try {
			// 修改试卷
			updateResult = examPaperService.updateExamPaperAllInfoById(exampaper, bigQues) ? "试卷修改成功!"
					: "试卷修改失败，系统出错了！";
		} catch (Exception e) {
			logger.error("保存试卷失败", e);
			updateResult = "试卷修改失败，系统出错了！！！";
		}
		return "update";
	}

	private String paperStatus;// 试卷存档状态

	public String updatePaperanswer() {
		try {
			updateResult = examPaperService.updateExampaperPaperanswer(paperId, paperStatus) ? "试卷归档成功!"
					: "试卷修改失败，系统出错了！";
		} catch (Exception e) {
			logger.error("试卷归档失败", e);
			updateResult = "试卷归档错了！！！";
		}
		return "update";
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public Exampaper getExampaper() {
		return exampaper;
	}

	public void setExampaper(Exampaper exampaper) {
		this.exampaper = exampaper;
	}

	public Bigquestion getDanxuanDati() {
		return danxuanDati;
	}

	public void setDanxuanDati(Bigquestion danxuanDati) {
		this.danxuanDati = danxuanDati;
	}

	public Bigquestion getDuoxuanDati() {
		return duoxuanDati;
	}

	public void setDuoxuanDati(Bigquestion duoxuanDati) {
		this.duoxuanDati = duoxuanDati;
	}

	public Bigquestion getPanduanDati() {
		return panduanDati;
	}

	public void setPanduanDati(Bigquestion panduanDati) {
		this.panduanDati = panduanDati;
	}

	public String getUpdateResult() {
		return updateResult;
	}

	public void setUpdateResult(String updateResult) {
		this.updateResult = updateResult;
	}

	public String getSureResult() {
		return sureResult;
	}

	public void setSureResult(String sureResult) {
		this.sureResult = sureResult;
	}

	public String getPaperStatus() {
		return paperStatus;
	}

	public void setPaperStatus(String paperStatus) {
		this.paperStatus = paperStatus;
	}

}
