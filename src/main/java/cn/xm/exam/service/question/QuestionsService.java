package cn.xm.exam.service.question;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.question.Checkrecord;
import cn.xm.exam.bean.question.Options;
import cn.xm.exam.bean.question.Questions;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.vo.question.QuestionsQueryVo;

//完

/**
 * 试题service接口
 * 
 * @author QizoLiQiang
 * @time 2017年8月9日上午11:30:41
 */
public interface QuestionsService {

	/**
	 * 从试题库中查询下次添加的试题的id
	 * 
	 * @param questionBankId
	 *            题库id
	 * @return
	 * @throws Exception
	 */
	public String getNextQuestionId(String questionBankId) throws Exception;

	/**
	 * 添加试题，同时将选项解析添加到选项表
	 * 
	 * @param questions
	 *            试题对象
	 * @param option
	 *            选项对象集合
	 * @return
	 * @throws Exception
	 */
	public boolean saveQuestionsAndOptions(Questions questions, List<Options> options) throws Exception;

	/**
	 * 批量添加试题
	 * 
	 * @param list
	 *            试题集合，一个map封装一道题的试题questions与选项options
	 * @return 导入的试题数量
	 * @throws Exception
	 */
	public int saveQuestionBatch(List<HashMap<String, Object>> list) throws Exception;

	// 保存审核状态(将来使用)
	/**
	 * 添加题目的审核状态
	 * 
	 * @param checkRecord
	 * @return
	 * @throws Exception
	 */
	public boolean saveQuestionCheckRecord(Checkrecord checkRecord) throws Exception;

	// 保存批量审核状态(将来使用)
	/**
	 * 批量审核题
	 * 
	 * @param list
	 *            审核记录
	 * @return
	 * @throws Exception
	 */
	public boolean saveQuestionCheckRecordBatch(List<Checkrecord> list) throws Exception;

	/**
	 * 通过id删除试题
	 * 
	 * @param id
	 *            要删除的试题id
	 * @return 是否删除成功
	 * @throws Exception
	 */
	public boolean deleteQuestionById(String id) throws Exception;

	/**
	 * 根据题的id删除题的选项
	 * 
	 * @param questionId
	 *            试题的id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteOptionsByQuestionId(String questionId) throws Exception;

	/**
	 * 批量删除试题
	 * 
	 * @param ids
	 *            要删除的试题id集合(先批量删除选项表中的对应数据)
	 * @return 影响行数
	 * @throws Exception
	 */
	public int deleteQuestionBatch(List<String> ids) throws Exception;

	/**
	 * 修改单个试题
	 * 
	 * @param questions
	 *            修改后的题干的基本信息
	 * @param options
	 *            修改选项
	 * @return 是否成功
	 * @throws Exception
	 */
	public boolean updateQuestion(Questions questions, List<Options> options) throws Exception;

	/**
	 * 批量移动试题，从一个题库移到另一个题库(将题的题库id修改为新的题库id)
	 * 
	 * @param questionIds
	 *            需要移动的试题的id
	 * @param newQuestionBankId
	 *            移动到的题库的id
	 * @return
	 * @throws Exception
	 */
	public boolean updateQuestionBatch(List<String> questionIds, String newQuestionBankId) throws Exception;

	/**
	 * 根据单个试题的id查询试题信息
	 * 
	 * @param questionId
	 *            试题id
	 * @return 试题与选项的组合
	 * 			选项中的字段包括	optionid(选项编号)	optioncontent(选项内容)	optionsequence(选项序号)
	 * @throws Exception
	 */
	public QuestionsQueryVo getQuestionById(String questionId) throws Exception;

	/**
	 * 分页查询试题与选项:根据知识点，试题库，试题类型，审核状态等
	 * 
	 * @param currentPage
	 *            当前页，默认第一页
	 * @param currentTotal
	 *            当前页的数量
	 * @param condition
	 *            查询条件
	 * @return map集合包含试题的基本信息与选项的基本信息
	 * @throws Exception
	 */
	public PageBean<QuestionsQueryVo> findQuestionWithCondition(int currentPage, int currentTotal,
			Map<String, Object> condition) throws Exception;

	/**
	 * 根据题题库的id获取题库的题量
	 * 
	 * @param questionBankId
	 * @return 题目总数
	 * @throws Exception
	 */
	public Integer getCountByQuestionBankId(String questionBankId) throws Exception;

	/**
	 * 根据题库的id与条件统计题库的题数
	 * 
	 * @param questionBankId
	 *            题库的id
	 * @param condition
	 *            条件，包括单选，多选，判断
	 * @return 各类题型的数量，单选，多选，判断
	 * @throws Exception
	 */
	public List<Integer> getCountByBankIdAndCondition(String questionBankId, Map<String, Object> condition)
			throws Exception;

	/**
	 * 获取一个题库中的所有题(包括选项)用于导出题库,只能导出没有图片的
	 * 
	 * @param questionBankId
	 *            题库id
	 * @return 题与选项的集合
	 * @throws Exception
	 */
	public List<QuestionsQueryVo> getQuestionsAndOptions(String questionBankId,List<String> questionType) throws Exception;

	// 将来使用，可能选中一定条件查询试题后导出试题
	/**
	 * 根据条件查询试题，(将来使用)
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Questions> getQuestionsAndOptions(Map<String, Object> condition) throws Exception;

	// 查询详细审核状态(将来使用)
	/**
	 * 根据题的id获取其详细的审核信息(将来审核功能使用)
	 * 
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	public List<Checkrecord> getQuestionCheckRecord(String questionId) throws Exception;

	/******************** 辅助考试管理模块的 **********************/

	/**
	 * 根据试题类型与题库组合查询试题用于生成试卷
	 * 
	 * @param condition
	 *            组合条件，包括试题类型与题库名称(实现层根据题库名称查询题库id然后调用dao查询)
	 * @return 满足条件的试题
	 * @throws Exception
	 */
	public List<Map<String, Object>> getQuestionsForExam(Map<String, String> condition) throws Exception;

	/**
	 * 根据条件查询题库中满足的id集合，用于随机生成试卷
	 * 
	 * @param condition
	 *            封装查询的条件(应该包括试题等级，单选题，多选题，判断题)
	 * @return
	 * @throws Exception
	 */
	public List<String> getQuestionIdForExam(Map<String, Object> condition) throws Exception;

	/**
	 * 根据试题的id集合批量查询试题
	 * 
	 * @param ids
	 *            需要查询的id集合
	 * @return 满足条件的试题，用于产生试卷
	 * @throws Exception
	 */
	public List<Questions> getQuestionByIdsForExam(List<String> ids) throws Exception;

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
	 *            包括等级，题库id，类型，知识点
	 * @return
	 * @throws SQLException
	 */
	public List<Questions> getQuestionsForBankGeneExamPaper(Map<String, Object> condition) throws SQLException;

	/************ E(Qlq) **********/
}
