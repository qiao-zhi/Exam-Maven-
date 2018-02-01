package cn.xm.exam.service.impl.exam.examPaper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.exam.Exampaperquestion;
import cn.xm.exam.mapper.exam.ExampaperquestionMapper;
import cn.xm.exam.mapper.exam.custom.ExampaperquestionCustomMapper;
import cn.xm.exam.service.exam.examPaper.ExamPaperQuestionService;

/**
 * 试卷题服务实现
 * 
 * @author QiaoLiQiang
 * @time 2017年10月11日上午8:39:24
 */
@Service
public class ExamPaperQuestionServiceImpl implements ExamPaperQuestionService {
	@Autowired
	private ExampaperquestionMapper exampaperquestionMapper;
	@Autowired
	private ExampaperquestionCustomMapper exampaperquestionCustomMapper;

	@Override
	public boolean addExamPaperQuestion(Exampaperquestion exampaperquestion) throws SQLException {
		int result = exampaperquestionMapper.insert(exampaperquestion);
		return result > 0 ? true : false;
	}

	@Override
	public boolean deleteExamPaperQuestionById(String examPaperQuestionId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteExamPaperQuestionByPaperId(String paperId) throws SQLException {
		return exampaperquestionCustomMapper.deleteQuestionsBypaperId(paperId) > 0 ? true : false;
	}

	@Override
	public Exampaperquestion getExamPaperQuestionById(String examPaperQuestionId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exampaperquestion> getExamPaperQuestionByPaperId(String examPaperId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String,Object>> getPaperAnswerAndScoreByPaerId(String paperId) throws SQLException {
		return exampaperquestionCustomMapper.getPaperAnswerAndScoreByPaerId(paperId);
	}

}
