package cn.xm.exam.action.exam.examPaper;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.exam.Bigquestion;
import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.service.exam.examPaper.ExamPaperService;

/**
 * 保存考试试卷
 * 
 * @author QiaoLiQiang
 * @time 2017年10月10日下午9:42:11
 */
@Controller // 控制层
@Scope("prototype") // 多例模式
@SuppressWarnings("all") // 压制警告
public class SavePaper extends ActionSupport {
	private Logger logger = Logger.getLogger(SavePaper.class);// 日志记录器
	@Autowired
	private ExamPaperService examPaperService;// 试卷服务用于添加试题
	private Exampaper exampaper;// 考试试卷的基本信息
	private Bigquestion danxuanDati;// 单选大题
	private Bigquestion duoxuanDati;// 多选大题
	private Bigquestion panduanDati;// 判断大题
	private String saveResult;// 保存结果(转为JSON传到前台)

	/**
	 * 保存试卷(ajax异步保存)
	 * 
	 * @return
	 */
	public String savePaper() {
		Map bigQues = new HashMap();
		if (danxuanDati != null && danxuanDati.getQuestions()!=null) {
			bigQues.put("danxuanDati", danxuanDati);
		}
		if (duoxuanDati != null&& duoxuanDati.getQuestions()!=null) {
			bigQues.put("duoxuanDati", duoxuanDati);
		}
		if (panduanDati != null && panduanDati.getQuestions()!=null) {
			bigQues.put("panduanDati", panduanDati);
		}
		try {
			saveResult = examPaperService.addExamPaper(exampaper, bigQues) ? "试卷保存成功！" : "试卷保存失败，系统出错了！";
		} catch (Exception e) {
			logger.error("保存试卷失败", e);
			saveResult = "试卷保存失败，系统出错了！！！";
		}
		return "add";
	}

	/*
	 * get,set
	 */
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

	public String getSaveResult() {
		return saveResult;
	}

	public void setSaveResult(String saveResult) {
		this.saveResult = saveResult;
	}

}
