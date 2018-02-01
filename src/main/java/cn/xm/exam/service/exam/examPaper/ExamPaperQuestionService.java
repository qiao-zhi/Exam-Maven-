package cn.xm.exam.service.exam.examPaper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.exam.Exampaperquestion;

/**
 * 考试试卷题Service
 * 
 * @author QiaoLiQiang
 * @time 2017年9月18日上午8:23:45
 */
public interface ExamPaperQuestionService {

	/**
	 * 增加考试试题
	 * 
	 * @param exampaperquestion
	 * @return
	 * @throws SQLException
	 */
	public boolean addExamPaperQuestion(Exampaperquestion exampaperquestion) throws SQLException;

	/**
	 * 根据考试题id删除试题
	 * 
	 * @param examPaperQuestionId
	 *            试题ID
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteExamPaperQuestionById(String examPaperQuestionId) throws SQLException;

	/**
	 * 根据试卷ID删除试题
	 * 
	 * @param paperId
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteExamPaperQuestionByPaperId(String paperId) throws SQLException;

	/**
	 * 根据试题ID查询考试试题
	 * 
	 * @param examPaperQuestionId
	 * @return
	 * @throws SQLException
	 */
	public Exampaperquestion getExamPaperQuestionById(String examPaperQuestionId) throws SQLException;

	/**
	 * 根据试卷ID查询试题
	 * 
	 * @param examPaperId
	 * @return
	 * @throws SQLException
	 */
	public List<Exampaperquestion> getExamPaperQuestionByPaperId(String examPaperId) throws SQLException;

	/**
	 * 根据试卷ID获取试卷答案(从1开始排序)
	 * 
	 * @param paperId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getPaperAnswerAndScoreByPaerId(String paperId) throws SQLException;
}
