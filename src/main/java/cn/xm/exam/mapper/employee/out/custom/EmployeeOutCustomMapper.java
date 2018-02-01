package cn.xm.exam.mapper.employee.out.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.common.Dictionary;
import cn.xm.exam.bean.employee.out.Breakrules;
import cn.xm.exam.bean.employee.out.EmployeeOut;
import cn.xm.exam.bean.employee.out.Employeeoutdistribute;
import cn.xm.exam.bean.haul.Haulemployeeout;
import cn.xm.exam.vo.employee.out.EmployeeOutBaseInfo;
import cn.xm.exam.vo.exam.ExamEmployeeOutQueryVo;

@SuppressWarnings("all")
public interface EmployeeOutCustomMapper {

	/**************** S qlq **********************/
	/******** S 查询外部参考员工 ****************/

	/**
	 * 条件根据查询外部参考员工
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getExamEmployeeOuts(Map condition) throws SQLException;

	/******** E 查询外部参考员工 ****************/
	/**************** E qlq **********************/

	/**
	 * 为大修部门树准备数据
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getDepartAndOverHaulInfoTree(Map<String,Object> condition) throws SQLException;

	/**
	 * 根据组合条件查询外部员工的总数，用于分页显示
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int findEmployeeOutWithCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 根据组合条件查询外部员工的基本信息，用于分页显示
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	/*public List<EmployeeOutBaseInfo> findEmployeeOutBaseInfoEWithCondition(Map<String, Object> condition)
	throws SQLException;*/
	public List<Map<String, Object>> findEmployeeOutBaseInfoEWithCondition(Map<String, Object> condition)
			throws SQLException;

	/**
	 * 根据外来员工的身份证号获取培训考试信息
	 * 
	 * @param idCard
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getExamsInfoByEmployeeOutIdCard(Map<String, Object> condition) throws SQLException;

	/**
	 * 根据外来员工的身份证号统计该员工参加培训考试的总数
	 * 
	 * @param idCard
	 * @return
	 * @throws SQLException
	 */
	public int getCountExamsInfoByEmployeeOutIdCard(String idCard) throws SQLException;

	/**
	 * 根据外来单位员工ID和大修员工ID查询违章信息
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Breakrules> getBreakRulesInfoByCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 根据条件查询员工的信息用于生成工作证 通过三级培训考试的员工
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<EmployeeOutBaseInfo> getEmpInfoForCertificateWithCondition(Map<String, Object> condition)
			throws SQLException;

	public List<EmployeeOutBaseInfo> getEmpInfoForCertificateWithCondition1(Map<String,Object> condition)
			throws SQLException;
	
	/**
	 * 批量加入外部员工的基本信息
	 * 
	 * @param employeeOutList
	 * @return
	 * @throws SQLException
	 */
	public int addEmployeeOutBatch(List<EmployeeOut> employeeOutList) throws SQLException;

	/**
	 * 批量加入参加大修外部员工的基本信息
	 * 
	 * @param haulemployeeoutList
	 * @return
	 * @throws Exception
	 */
	public int addHaulEmployeeOutBatch(List<Haulemployeeout> haulemployeeoutList) throws SQLException;

	/**
	 * 根据单个身份证号查询员工的基本信息
	 * 
	 * @param idCard
	 * @return
	 * @throws SQLException
	 */
	public List<EmployeeOutBaseInfo> getEmployeeOutBaseInfo(String idCard) throws SQLException;

	/**
	 * 根据参加大修员工身份证集合查询员工的id集合
	 * 
	 * @param idCards
	 * @return
	 * @throws SQLException
	 */
	public List<String> findEmployeeOutIdByIdCards(List<Haulemployeeout> haulemployeeoutList) throws SQLException;

	/**
	 * 根据身份证号和大修ID修改外来单位员工的工种信息
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int updateHaulEmployeeOutInfoByCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 根据大修ID和身份证号查询员工的基本信息
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public EmployeeOutBaseInfo getEmployeeOutInfoByCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 根据大修ID和身份证号查询员工所有参加的考试ID
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<String> getExamIdsByCondition(Map<String, Object> condition) throws SQLException;
	
	/**
	 * 初始化外来单位员工分配表
	 * @param distributeInfoList
	 * @return
	 * @throws SQLException
	 */
	public int addEmpOutDistributeInfoList(List<Employeeoutdistribute> distributeInfoList) throws SQLException;
	
	/**
	 * 根据字典编号查询字典信息
	 * @param dictionaryId
	 * @return
	 * @throws SQLException
	 */
	public String getDictionaryInfoById(String dictionaryId) throws SQLException;
	
	/**
	 * 根据身份证号在外来单位员工分配表中设置外来单位员工的姓名
	 * @param empOutIdCard
	 * @return
	 * @throws SQLException
	 */
	public int updateEmployeeOutNameByIdCard() throws SQLException;
	
	/**
	 * 根据大修员工ID和状态码修改大修员工表培训状态
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int updateHaulEmployeeOutTrainStatusByCondition(Map<String,Object> condition) throws SQLException;
	
}
