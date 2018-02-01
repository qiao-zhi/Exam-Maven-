package cn.xm.exam.mapper.employee.out.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UnitCustomMapper {

	/**************** S qlq **********************/
	/******** S 查询外部部门树 ****************/

	/**
	 * 查询部门id，上级id，部门名称生成内部部门树
	 * 
	 * @return 一个map包含部门的部门id，上级id，部门名称
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getUnitTreeForExam() throws SQLException;

	/******** E 查询外部部门树 ****************/

	/**
	 * 模糊查询部门名称
	 * 
	 * @param name
	 *            部门名称模糊字符串
	 * @return 部门名称集合
	 * @throws SQLException
	 */
	public List<String> getUnitsName(String name) throws SQLException;

	/**
	 * 根据条件查询满足条件的总数
	 * 
	 * @param condition
	 *            组合条件
	 * @return 满足条件的总数
	 * @throws SQLException
	 */
	public int getHaulunitTotalByCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 组合条件查询大修单位信息
	 * 
	 * @param condition
	 *            组合条件
	 * @return 单位大修信息(一个map对应一条记录)
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getHaulunitByCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 根据大修ID和单位ID查询员工信息
	 * 
	 * @param haulIdAndUnitId
	 *            单位ID和大修ID
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getEmployeeOutsByUaulIdAndUnitId(Map<String, Object> haulIdAndUnitId)
			throws SQLException;

	/**
	 * 获取外来 单位员工的总数
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int getEmployeeOutsTotalByUaulIdAndUnitId(Map<String,Object> condition)throws SQLException;
	/**
	 * 根据大修ID和单位ID查询某次大修的所有违章员工的信息
	 * 
	 * @param haulIdAndUnitId
	 *            单位ID和大修ID
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getEmployeeOutsBreakrulesByUaulIdAndUnitId(Map<String, Object> haulIdAndUnitId)
			throws SQLException;

	/**
	 * 根据单位的ID查出对应的Id与名称
	 * 
	 * @param Unitids
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getUnitidsAndNamesByUnitids(List<String> Unitids) throws SQLException;

	/**
	 * 查询总数
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int getEmployeeOutsBreakrulesTotal(Map condition) throws SQLException;
	/**************** E qlq **********************/

}
