package cn.xm.exam.service.impl.question;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xm.exam.bean.employee.in.Department;
import cn.xm.exam.bean.question.Questionbank;
import cn.xm.exam.mapper.employee.in.DepartmentMapper;
import cn.xm.exam.mapper.question.QuestionbankMapper;
import cn.xm.exam.mapper.question.custom.QuestionbankCustomMapper;
import cn.xm.exam.mapper.question.custom.QuestionsCustomMapper;
import cn.xm.exam.service.question.QuestionbankService;
import cn.xm.exam.service.question.QuestionsService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.vo.question.QuestionbankQuestionsDepartment;

/**   
*    
* 项目名称：Exam   
* 类名称：QuestionbankServiceImpl   
* 类描述：  题库service的实现类 
* 创建人：Leilong   
* 创建时间：2017年9月21日 下午9:50:03   
* 修改备注：   
* @version    
*    
*/
@Transactional
@Service
public class QuestionbankServiceImpl implements QuestionbankService {
	
	@Resource 
	private QuestionbankMapper questionBankMapper;
	@Resource
	private DepartmentMapper departmentMapper;
	@Resource
	private QuestionbankCustomMapper questionBankCustomMapper;
	@Resource
	private QuestionsCustomMapper questionCustomMapper;
	@Resource
	private QuestionsService questionService;
	
	
	/**
	 * 根据部门的id获取题库下次添加的id
	 * 
	 * @param unitId
	 *            部门id
	 * @return 下次添加时的题库id
	 * @throws Exception
	 */
	public String getNextQuestionBankId(String unitId) throws Exception {
		String questionBankId = null;
		Department departmentInfo = departmentMapper.selectByPrimaryKey(unitId);
		//当该部门存在时生成题库的id
		if(departmentInfo != null){
			questionBankId = UUIDUtil.getUUID2();
		}
		return questionBankId;
	}
	
	/**
	 * 增加题库(id用getNextQuestionBankId(unitId)生成的id)
	 * 
	 * @param questionBank
	 * @return
	 * @throws Exception
	 */
	public boolean addQuestionBank(Questionbank questionBank) throws Exception {
		//设置题库的主键
		questionBank.setQuestionbankid(UUIDUtil.getUUID2());
		int isInsert = questionBankMapper.insert(questionBank);
		return isInsert>0 ? true : false;
	}
	
	/**
	 * 根据id删除题库
	 * 
	 * 级联删除，删除题库中的题目选项以及审核表中的信息
	 * 然后删除该题库
	 * @param id  题库的id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteQuestionBankById(String id) throws Exception {
		boolean isDelete = false;
		
		List<String> questionIds = questionCustomMapper.findQuestionIdsByQuesionBankId(id);
		//判断该题库是否有题
		if(questionIds.size()>0){			
			//根据题目的id集合批量删除题目信息
			int count = questionService.deleteQuestionBatch(questionIds);
			//判断试题是否删除成功
			if(count!= questionIds.size()){
				return false;
			}		
		}
		//删除题库信息
		int isDeleteQuestionBank = questionBankMapper.deleteByPrimaryKey(id);
		if(isDeleteQuestionBank>0){
			isDelete = true;
		}
		return isDelete;
	}
	
	/**
	 * 修改题库基本信息
	 * 
	 * @param questionBank
	 *            修改后的对象
	 * @return
	 * @throws Exception
	 */
	public boolean updateQuestionBanK(Questionbank questionBank) throws Exception {
		int isUpdate = questionBankMapper.updateByPrimaryKey(questionBank);
		return isUpdate >0? true : false;
	}
	
	/**
	 * 根据题库的id查询题库的具体信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Questionbank getQuestionbankById(String id) throws Exception {
		 
		return questionBankMapper.selectByPrimaryKey(id);		

	}
	
	// 为添加试题服务，添加题的时候根据题库的名字获取部门的id，然后根据id判断题的等级
		/**
		 * 通过题库的名字获取其所属部门的id
		 * 
		 * @param bankName
		 * @return 部门id
		 * @throws Exception
		 */
	public String getUnitIdByBankName(String bankName) throws Exception {
		return questionBankCustomMapper.getDepartmentIdByName(bankName);
	}
	
	/**
	 * 分页查询题库:根据 题库名称，所属部门组合查询
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
			Map<String, Object> condition) throws Exception {
		//目的封装一个pageBean并返回
		PageBean<QuestionbankQuestionsDepartment> pageBean = new PageBean<QuestionbankQuestionsDepartment>();
		//当前页
		pageBean.setCurrentPage(currentPage);
		//当前页显示条数
		pageBean.setCurrentCount(currentCount);
		//总条数
		int totalCount = 0;
		totalCount = questionBankCustomMapper.getQuestionBankCountByCondition(condition);
		pageBean.setTotalCount(totalCount);
		//总页数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);
		//每页显示的数据
		//设置索引，当前页-1乘上当前页显示的条数
		int index = (currentPage - 1) * currentCount;
		condition.put("index", index);
		condition.put("currentCount", currentCount);
		List<QuestionbankQuestionsDepartment> questionBanks = questionBankCustomMapper.findQuestionBankByCondition(condition);
		pageBean.setProductList(questionBanks);
		
		return pageBean;
	}
	
	/********************* 辅助考试管理模块 *******************/
	/**
	 * 根据题库的名称获取题库的id(将来生成试卷根据题库id查询题)
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String getQuestionBankIdByName(String name) throws Exception {
		
		return questionBankCustomMapper.getQuestionBankIdByName(name);
	}

	/**
	 * 获取所有的题库名称和ID，初始化下拉列表使用
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getQuestionBankNameList() throws Exception {
		
		return questionBankCustomMapper.getQuestionBankNameList();
	}

	/**
	 * 根据部门ID查询题库名称和ID
	 * @param departmentId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getQuestionBankNameListByDeptId(String departmentId) throws Exception {
	
		return questionBankCustomMapper.getQuestionBankNameListByDeptId(departmentId);
	}

}
