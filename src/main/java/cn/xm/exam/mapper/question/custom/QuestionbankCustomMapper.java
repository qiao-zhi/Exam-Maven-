package cn.xm.exam.mapper.question.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.vo.question.QuestionbankQuestionsDepartment;


/**   
*    
* 项目名称：Exam   
* 类名称：QuestionbankCustomMapper   
* 类描述：  添加的题库Mapper方法类 
* 创建人：Leilong 
* @version    
*    
*/
public interface QuestionbankCustomMapper {
	
	/**
	 * 根据题库名称查询题库ID
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String getQuestionBankIdByName(String name) throws SQLException;
	
	/**
	 * 根据题库名称查询所属部门的id
	 * 
	 * @param questionBankName
	 * @return
	 * @throws Exception
	 */
	public String getDepartmentIdByName(String questionBankName) throws SQLException;
	
	/**
	 * 根据条件查询满足条件的总数
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Integer getQuestionBankCountByCondition(Map<String, Object> map) throws SQLException;

	/**
	 * 组合条件查询符合条件的题库信息
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<QuestionbankQuestionsDepartment> findQuestionBankByCondition(Map<String, Object> map) throws SQLException;

	/**
	 * 获取所有的题库名称和ID
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getQuestionBankNameList() throws SQLException;


	/**
	 * 根据部门ID查询题库名称和ID
	 * @param departmentId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getQuestionBankNameListByDeptId(String departmentId) throws SQLException;

}
