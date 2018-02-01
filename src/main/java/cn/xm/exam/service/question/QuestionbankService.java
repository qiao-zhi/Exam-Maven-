package cn.xm.exam.service.question;

import java.util.List;
import java.util.Map;
import cn.xm.exam.bean.question.Questionbank;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.vo.question.QuestionbankQuestionsDepartment;

//完

/**
 * 试题库service层接口
 * 
 * @author QizoLiQiang
 * @time 2017年8月9日上午11:08:39
 */
public interface QuestionbankService {

	/**
	 * 根据部门的id获取题库下次添加的id
	 * 
	 * @param unitId
	 *            部门id
	 * @return 下次添加时的题库id
	 * @throws Exception
	 */
	public String getNextQuestionBankId(String unitId) throws Exception;

	/**
	 * 增加题库(id用getNextQuestionBankId(unitId)生成的id)
	 * 
	 * @param questionBank
	 * @return
	 * @throws Exception
	 */
	public boolean addQuestionBank(Questionbank questionBank) throws Exception;

	/**
	 * 根据id删除题库(实现层首先删除题库中的题)
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteQuestionBankById(String id) throws Exception;

	/**
	 * 修改题库基本信息
	 * 
	 * @param questionBank
	 *            修改后的对象
	 * @return
	 * @throws Exception
	 */
	public boolean updateQuestionBanK(Questionbank questionBank) throws Exception;

	/**
	 * 根据题库的id查询题库的具体信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Questionbank getQuestionbankById(String id) throws Exception;

	// 为添加试题服务，添加题的时候根据题库的名字获取部门的id，然后根据id判断题的登记
	/**
	 * 通过题库的名字获取其所属部门的id
	 * 
	 * @param bankName
	 * @return 部门id
	 * @throws Exception
	 */
	public String getUnitIdByBankName(String bankName) throws Exception;

	/**
	 * 分页查询题库:根据单位名称，题库名称，题库级别，所属部门组合查询
	 * 
	 * @param currentPage
	 *            当前页，默认第一页
	 * @param currentTotal
	 *            当前页的数量
	 * @param condition
	 *            查询条件
	 * @return
	 * @throws Exception
	 */
	public PageBean<QuestionbankQuestionsDepartment> findQuestionBankWithCondition(int currentPage, int currentCount,
			Map<String, Object> condition) throws Exception;

	/********************* 辅助考试管理模块 *******************/
	/**
	 * 根据题库的名称获取题库的id(将来生成试卷根据题库id查询题)
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String getQuestionBankIdByName(String name) throws Exception;
	
	
	/********************* leilong添加的 *******************/
	
	/**
	 * 获取所有的题库名称和ID，初始化下拉列表使用
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getQuestionBankNameList() throws Exception;
	
	
	
	/**
	 * 根据部门ID查询题库名称和ID
	 * @param departmentId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getQuestionBankNameListByDeptId(String departmentId) throws Exception;
	
}
