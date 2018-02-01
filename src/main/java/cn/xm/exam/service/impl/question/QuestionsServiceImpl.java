package cn.xm.exam.service.impl.question;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xm.exam.bean.common.Dictionary;
import cn.xm.exam.bean.common.DictionaryExample;
import cn.xm.exam.bean.question.Checkrecord;
import cn.xm.exam.bean.question.Options;
import cn.xm.exam.bean.question.OptionsExample;
import cn.xm.exam.bean.question.Questionbank;
import cn.xm.exam.bean.question.Questions;
import cn.xm.exam.bean.question.QuestionsExample;
import cn.xm.exam.mapper.common.DictionaryMapper;
import cn.xm.exam.mapper.question.OptionsMapper;
import cn.xm.exam.mapper.question.QuestionbankMapper;
import cn.xm.exam.mapper.question.QuestionsMapper;
import cn.xm.exam.mapper.question.custom.QuestionsCustomMapper;
import cn.xm.exam.service.question.QuestionbankService;
import cn.xm.exam.service.question.QuestionsService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.vo.question.QuestionsQueryVo;

@Service
public class QuestionsServiceImpl implements QuestionsService {

	@Resource
	private QuestionbankMapper questionBankMapper;
	@Resource
	private QuestionsMapper questionsMapper;
	@Resource
	private OptionsMapper optionsMapper;
	@Resource
	private QuestionsCustomMapper questionsCustomMapper;
	@Resource
	private QuestionbankService questionBankService;// 题库的service
	@Resource
	private DictionaryMapper dictionaryMapper;// 题库的service

	/**
	 * 根据题库ID查询下次添加的试题的id
	 * 
	 * @param questionBankId
	 *            题库id
	 * @return
	 * @throws Exception
	 */
	public String getNextQuestionId(String questionBankId) throws Exception {
		String questionId = null;
		Questionbank questionBankInfo = questionBankMapper.selectByPrimaryKey(questionBankId);
		if (questionBankInfo != null) {
			questionId = UUIDUtil.getUUID2();
		}
		return questionId;
	}

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
	public boolean saveQuestionsAndOptions(Questions questions, List<Options> options) throws Exception {

		boolean isInsert = false;

		int isInsertQuestion = questionsMapper.insert(questions);
		// 判断插入试题是否成功
		if (isInsertQuestion > 0) {
			// 判断选项集合是否有值
			if (options.size() > 0 && options != null) {
				int countOptions = questionsCustomMapper.insertOptionsList(options);
				if (countOptions == options.size()) {
					isInsert = true;
				}
			}
		}

		return isInsert;
	}

	/**
	 * 批量添加试题
	 * 
	 * @param list
	 *            试题集合，一个map封装一道题的试题questions与选项options
	 * @return 导入的试题数量
	 * @throws Exception
	 */
	public int saveQuestionBatch(List<HashMap<String, Object>> list) throws Exception {
		for (HashMap<String, Object> hashMap : list) {
			Questions question = (Questions) hashMap.get("question");
			@SuppressWarnings("unchecked")
			List<Options> options = (List<Options>) hashMap.get("options");
			saveQuestionsAndOptions(question, options);

		}
		return list.size();
	}

	// 保存审核状态(将来使用)
	/**
	 * 添加题目的审核状态
	 * 
	 * @param checkRecord
	 * @return
	 * @throws Exception
	 */
	public boolean saveQuestionCheckRecord(Checkrecord checkRecord) throws Exception {

		return false;
	}

	// 保存批量审核状态(将来使用)
	/**
	 * 批量审核题
	 * 
	 * @param list
	 *            审核记录
	 * @return
	 * @throws Exception
	 */
	public boolean saveQuestionCheckRecordBatch(List<Checkrecord> list) throws Exception {

		return false;
	}

	/**
	 * 通过id删除试题 要删除试题，需要先删除该题对应的选项，调用deleteOptionsByQuestionId()的方法 然后在删除该题
	 * 
	 * @param id
	 *            要删除的试题id
	 * @return 是否删除成功
	 * @throws Exception
	 */
	public boolean deleteQuestionById(String id) throws Exception {
		boolean isDelete = false;

		boolean isDeleteOptions = deleteOptionsByQuestionId(id);
		if (isDeleteOptions) {
			int isDeleteQuestion = questionsMapper.deleteByPrimaryKey(id);
			if (isDeleteQuestion > 0) {
				isDelete = true;
			}
		}
		return isDelete;
	}

	/**
	 * 根据题的id删除题的选项
	 * 
	 * @param questionId
	 *            试题的id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteOptionsByQuestionId(String questionId) throws Exception {

		OptionsExample optionExample = new OptionsExample();
		OptionsExample.Criteria criteria = optionExample.createCriteria();
		criteria.andQuestionidEqualTo(questionId);
		int isDelete = optionsMapper.deleteByExample(optionExample);

		return isDelete > 0 ? true : false;
	}

	/**
	 * 批量删除试题
	 * 
	 * @param ids
	 *            要删除的试题id集合(先批量删除选项表中的对应数据)
	 * @return 影响行数
	 * @throws Exception
	 */
	public int deleteQuestionBatch(List<String> ids) throws Exception {
		int countDeleteQuesions = 0;

		OptionsExample optionExample = new OptionsExample();
		OptionsExample.Criteria optionsCriteria = optionExample.createCriteria();
		optionsCriteria.andQuestionidIn(ids);
		int isDelete = optionsMapper.deleteByExample(optionExample);
		if (isDelete > 0) {
			QuestionsExample questionsExample = new QuestionsExample();
			QuestionsExample.Criteria questionCriteria = questionsExample.createCriteria();
			questionCriteria.andQuestionidIn(ids);
			countDeleteQuesions = questionsMapper.deleteByExample(questionsExample);
		}

		return countDeleteQuesions;
	}

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
	public boolean updateQuestion(Questions questions, List<Options> options) throws Exception {

		boolean isUdapte = false;
		int isUpdateQuestion = questionsMapper.updateByPrimaryKeySelective(questions);
		if (isUpdateQuestion > 0) {
			// 判断选项集合是否有值
			if (options.size() > 0 && options != null) {
				int countOptions = questionsCustomMapper.insertOptionsList(options);
				if (countOptions == options.size()) {
					isUdapte = true;
				}
			}
		}

		return isUdapte;
	}

	/**
	 * 批量移动试题，从一个题库移到另一个题库(将题的题库id修改为新的题库id)
	 * 
	 * @param questionIds
	 *            需要移动的试题的id集合
	 * @param newQuestionBankId
	 *            移动到的题库的id
	 * @return
	 * @throws Exception
	 */
	public boolean updateQuestionBatch(List<String> questionIds, String newQuestionBankId) throws Exception {

		// 定义试题的等级
		//String newLevel = null;

		/*// 根据新的题库ID查询出部门ID
		Questionbank questionBankInfo = questionBankService.getQuestionbankById(newQuestionBankId);
		String departmentId = questionBankInfo.getDepartmentid();
		// 根据部门的命名规则，长度为2代表厂级，5代表部门级，8代表班组级
		int length = departmentId.length();
		switch (length) {
		case 2:
			newLevel = "1";
			break;
		case 5:
			newLevel = "2";
			break;
		case 8:
			newLevel = "3";
			break;
		default:
			newLevel = "3";
		}*/
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("questionIds", questionIds);
		map.put("newQuestionBankId", newQuestionBankId);
		map.put("newLevel", "1");
		Integer isUpdate = questionsCustomMapper.updateQuestionBatchMove(map);

		return isUpdate > 0 ? true : false;
	}

	/**
	 * 根据单个试题的id查询试题信息
	 * 
	 * @param questionId
	 *            试题id
	 * @return 试题与选项的组合 选项中的字段包括 optionid(选项编号) optioncontent(选项内容)
	 *         optionsequence(选项序号)
	 * @throws Exception
	 */
	public QuestionsQueryVo getQuestionById(String questionId) throws Exception {
		return questionsCustomMapper.getQuestionAndOptionsByQuesId(questionId);
	}

	/**
	 * 分页查询试题与选项:根据知识点，试题库，试题类型，关键字
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
			Map<String, Object> condition) throws Exception {
		// 目的封装一个pageBean并返回
		PageBean<QuestionsQueryVo> pageBean = new PageBean<QuestionsQueryVo>();
		// 当前页
		pageBean.setCurrentPage(currentPage);
		// 当前页显示条数
		pageBean.setCurrentCount(currentTotal);
		// 总条数
		int totalCount = 0;
		totalCount = questionsCustomMapper.getQuestionsCountByCondition(condition);
		pageBean.setTotalCount(totalCount);
		// 总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentTotal);
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据
		// 设置索引，当前页-1乘上当前页显示的条数
		int index = (currentPage - 1) * currentTotal;
		condition.put("index", index);
		condition.put("currentCount", currentTotal);
		List<QuestionsQueryVo> questions = questionsCustomMapper.findQuestionsByCondition(condition);
		pageBean.setProductList(questions);

		return pageBean;
	}

	/******************************** 视图已经实现 ******************************/
	/**
	 * 根据题库的id获取题库的题量
	 * 
	 * @param questionBankId
	 * @return 题目总数
	 * @throws Exception
	 */
	public Integer getCountByQuestionBankId(String questionBankId) throws Exception {

		return null;
	}

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
			throws Exception {

		return null;
	}

	/******************************************************************************/

	/**
	 * 获取一个题库中的所有题(包括选项)用于导出题库,只能导出没有图片的
	 * 
	 * @param questionBankId
	 *            题库id
	 * @return 题与选项的集合
	 * @throws Exception
	 */
	public List<QuestionsQueryVo> getQuestionsAndOptions(String questionBankId, List<String> questionType)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("questionBankId", questionBankId);
		map.put("questionTypeList", questionType);
		return questionsCustomMapper.getQuestionAndOptionsByQuesLibId(map);
	}

	// 将来使用，可能选中一定条件查询试题后导出试题
	/**
	 * 根据条件查询试题，(将来使用)
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Questions> getQuestionsAndOptions(Map<String, Object> condition) throws Exception {

		return null;
	}

	// 查询详细审核状态(将来使用)
	/**
	 * 根据题的id获取其详细的审核信息(将来审核功能使用)
	 * 
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	public List<Checkrecord> getQuestionCheckRecord(String questionId) throws Exception {

		return null;
	}

	/******************** 辅助考试管理模块的 **********************/

	/**
	 * 根据试题类型与题库组合查询试题用于生成试卷
	 * 
	 * @param condition
	 *            组合条件，包括试题类型与题库名称(实现层根据题库名称查询题库id然后调用dao查询)
	 * @return 满足条件的试题
	 * @throws Exception
	 */
	public List<Map<String, Object>> getQuestionsForExam(Map<String, String> condition) throws Exception {

		return null;
	}

	/**
	 * 根据条件查询题库中满足的id集合，用于随机生成试卷
	 * 
	 * @param condition
	 *            封装查询的条件(应该包括试题等级，单选题，多选题，判断题)
	 * @return
	 * @throws Exception
	 */
	public List<String> getQuestionIdForExam(Map<String, Object> condition) throws Exception {

		return null;
	}

	/**
	 * 根据试题的id集合批量查询试题
	 * 
	 * @param ids
	 *            需要查询的id集合
	 * @return 满足条件的试题，用于产生试卷
	 * @throws Exception
	 */
	public List<Questions> getQuestionByIdsForExam(List<String> ids) throws Exception {

		return null;
	}

	/*********** S(Qlq) 用于快速生成试卷根据条件与题数查询题库 **************/
	@Override
	public List<String> getQuestionsIdsForExamPaper(Map<String, Object> condition) throws SQLException {
		// 1.根据上级字典编号为400的查询字典，
		DictionaryExample dictionaryExample = new DictionaryExample();
		DictionaryExample.Criteria criteria = dictionaryExample.createCriteria();
		criteria.andUpdictionaryidEqualTo("400");
		List<Dictionary> dictionaryList = dictionaryMapper.selectByExample(dictionaryExample);
		Dictionary dictionary = null;
		if (dictionaryList != null && dictionaryList.size() > 0) {
			dictionary = dictionaryList.get(0);
		}
		// 2.如果查到并且描述为现在内部员工的部门ID，则将其是厂级负责设一个值(如果试卷级别为1级，查一下是否有一级权限)
		String isManageChangji = null;
		if (condition.get("level") != null && "1".equals(condition.get("level"))) {
			String departmentId = (String) condition.get("departmentId");
			if (dictionary != null && departmentId != null && departmentId.equals(dictionary.getDiscription())) {
				isManageChangji = "1";
			}
		}
		// 3. 如果是厂级负责就讲起传入mapper
		if (isManageChangji != null) {
			condition.put("isManageChangji", isManageChangji);
		}
		return questionsCustomMapper.getQuestionsIdsForExamPaper(condition);
	}

	@Override
	public List<Questions> getQuestionsForExamPaper(List<String> questionIds) throws SQLException {
		return questionsCustomMapper.getQuestionsForExamPaper(questionIds);
	}

	@Override
	public List<Questions> getQuestionsForBankGeneExamPaper(Map<String, Object> condition) throws SQLException {
		// 1.根据上级字典编号为400的查询字典，
		DictionaryExample dictionaryExample = new DictionaryExample();
		DictionaryExample.Criteria criteria = dictionaryExample.createCriteria();
		criteria.andUpdictionaryidEqualTo("400");
		List<Dictionary> dictionaryList = dictionaryMapper.selectByExample(dictionaryExample);
		Dictionary dictionary = null;
		if (dictionaryList != null && dictionaryList.size() > 0) {
			dictionary = dictionaryList.get(0);
		}
		// 2.如果查到并且描述为现在内部员工的部门ID，则将其是厂级负责设一个值(如果试卷级别为1级，查一下是否有一级权限)
		String isManageChangji = null;
		if (condition.get("level") != null && "1".equals(condition.get("level"))) {
			String departmentId = (String) condition.get("departmentId");
			if (dictionary != null && departmentId != null && departmentId.equals(dictionary.getDiscription())) {
				isManageChangji = "1";
			}
		}
		// 3. 如果是厂级负责就讲起传入mapper
		if (isManageChangji != null) {
			condition.put("isManageChangji", isManageChangji);
		}
		return questionsCustomMapper.getQuestionsForBankGeneExamPaper(condition);
	}
	/*********** E(Qlq) **************/

}
