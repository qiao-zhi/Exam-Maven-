package cn.xm.exam.mapper.question.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.question.Options;
import cn.xm.exam.bean.question.Questions;
import cn.xm.exam.vo.question.QuestionsQueryVo;

/**
 * 
 * 项目名称：Exam 类名称：QuestionsCustomMapper 类描述： 添加的试题Mapper的方法类 创建人：leilong
 * 创建时间：2017年9月24日 下午7:47:49
 * 
 * @version
 * 
 */
public interface QuestionsCustomMapper {

	/**
	 * 插入选项集合
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int insertOptionsList(List<Options> options) throws SQLException;

	/**
	 * 组合条件查询试题信息
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<QuestionsQueryVo> findQuestionsByCondition(Map<String, Object> map) throws SQLException;

	/**
	 * 根据题库ID查询该题库的试题的id集合
	 * 
	 * @param questionBankId
	 * @return
	 * @throws SQLException
	 */
	public List<String> findQuestionIdsByQuesionBankId(String questionBankId) throws SQLException;

	/**
	 * 根据条件查询满足条件的总数
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Integer getQuestionsCountByCondition(Map<String, Object> map) throws SQLException;

	/**
	 * 根据试题ID查询试题和选项的信息
	 * 
	 * @param questionId
	 * @return
	 * @throws SQLException
	 */
	public QuestionsQueryVo getQuestionAndOptionsByQuesId(String questionId) throws SQLException;

	/**
	 * 根据题库ID查询试题和选项的信息集合
	 * 
	 * @param questionLibId
	 * @return
	 * @throws SQLException
	 */
	public List<QuestionsQueryVo> getQuestionAndOptionsByQuesLibId(Map<String, Object> map) throws SQLException;

	/**
	 * 批量更新试题的题库ID
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public Integer updateQuestionBatchMove(Map<String, Object> map) throws SQLException;

	/************ S(Qlq) **********/
	/**
	 * 根据条件查询指定数量的满足条件的ID集合
	 * 
	 * @param condition
	 *            类型，等级，数量
	 * @return ID集合
	 * @throws SQLException
	 */
	public List<String> getQuestionsIdsForExamPaper(Map<String, Object> condition) throws SQLException;

	/**
	 * 根据ID集合查询出满足条件的试题与选项
	 * 
	 * @param questionIds
	 * @return
	 * @throws SQLException
	 */
	public List<Questions> getQuestionsForExamPaper(List<String> questionIds) throws SQLException;

	/**
	 * 根据题等级，题库名字，类型，知识点等条件组合查询试题用于根据题库 产生试卷
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Questions> getQuestionsForBankGeneExamPaper(Map<String, Object> condition) throws SQLException;

	/************ E(Qlq) **********/
}
