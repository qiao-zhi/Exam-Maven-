package cn.xm.exam.mapper.exam.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.exam.Exampaperquestion;

public interface ExampaperquestionCustomMapper {

	/**
	 * 根据条件查询试题与选项
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Exampaperquestion> getExampaperquestionsByCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 根据试卷ID删除试题
	 * 
	 * @param paperID
	 * @return
	 * @throws SQLException
	 */
	public int deleteQuestionsBypaperId(String paperID) throws SQLException;

	/**
	 * 根据试卷ID获取试卷答案和分值(从1开始排序)
	 * 
	 * @param paperId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getPaperAnswerAndScoreByPaerId(String paperId) throws SQLException;

}
