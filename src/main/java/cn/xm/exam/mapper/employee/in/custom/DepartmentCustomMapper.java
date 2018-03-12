package cn.xm.exam.mapper.employee.in.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.xm.exam.bean.employee.in.Department;

public interface DepartmentCustomMapper {

	/**************** S qlq **********************/
	/******** S 查询内部部门树 ****************/

	/**
	 * 查询部门id，上级id，部门名称生成内部部门树
	 * 
	 * @return 一个map包含部门的部门id，上级id，部门名称
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getDepartmentTreeForExam(String departmentId) throws SQLException;

	/**
	 * 
	 * @param depNameWords
	 * @return
	 * @throws SQLException
	 */
	@Select("SELECT DISTINCT departmentName FROM department WHERE updepartmentid IN(SELECT departmentid  FROM department WHERE"
			+ " departmentName = '长委单位')   AND departmentType = '1' and departmentName like concat('%',#{depNameWords},'%')")
	public List<String> getChangWeiDepartment(@Param("depNameWords")String depNameWords) throws SQLException;

	/******** E 查询内部部门树 ****************/
	/**** 查询部门信息 */
	/**
	 * 查询部门总数
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int getCountByCondition(Map condition) throws SQLException;

	/**
	 * 查询部门信息
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getDepartmentInfoByCondition(Map condition) throws SQLException;

	@Select("SELECT  departmentId FROM department WHERE departmentName = #{name} AND departmentType = '1'")	
	public List<String> getCWDepartmentIdsByName(@Param("name")String name)throws SQLException;
	
	/**************** E qlq **********************/

	/****** S zhangwenyi **********/
	List<Map<String, Object>> getDepartmentTree(String departmentId);

	int getDepartmentCountByCondition(Map<String, Object> condition);

	List<Department> findDepartmentByCondition(Map<String, Object> condition);

	String getDepartmentNameById(String departmentid);

	String getIdByDepartmentName(String departmentname);

	String getNextDepartmentId(String upDepartmentId);

	int getDepartmentCountByUpId(String departmentId);

	String getMaxDepartmentId(String upDepartmentId);

	int getEmployeeInCountsById(String updepartmentid);

	Department getDepartmentByName(String departmentname);

	/*
	 * 查询出所有员工的身份证
	 */
	List<String> getIdcodeListAll();

	/*
	 * 该部门下所有员工的所有违章数
	 */
	public int getBreakrulesCountByDepartmentId(Map condition);

	/*
	 * 该部门下的所有员工的所有违章记录
	 */
	public List<Map<String, Object>> getBreakrulesCaseInfoByCondition(Map<String, Object> condition);

	/****** E zhangwenyi **********/

	/****** S lxy **********/
	/**
	 * 根据部门名称获取部门id
	 * 
	 * @return
	 */
	public String getDeptIdByDeptName(String departmentId);

	/****** E lxy **********/
	/****** S leilong **********/
	/**
	 * 移动部门时修改所有的部门ID,调用存储过程
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public void updateDepartmentIds(Map<String, Object> condition) throws SQLException;

	/******************* 部门统计 *********************/
	/**
	 * 统计内部正式部门个数
	 * 
	 * @param conditon
	 * @return
	 * @throws SQLException
	 */
	public int getDepartmentInFormalCount(Map<String, Object> conditon) throws SQLException;

	/**
	 * 统计内部长期外委部门个数
	 * 
	 * @param conditon
	 * @return
	 * @throws SQLException
	 */
	public int getDepartmentInToDoCount(Map<String, Object> conditon) throws SQLException;

	/**
	 * 查询内部正式部门统计信息
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getDepartmentInFormalCountInfo(Map<String, Object> condition) throws SQLException;

	/**
	 * 查询内部长期外委单位的统计信息
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getDepartmentInToDoCountInfo(Map<String, Object> condition) throws SQLException;

	/**
	 * 查询内部正式单位和员工数
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getFormalDepartmentAndEmpNum() throws SQLException;

	/**
	 * 查询内部长委单位和员工数
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getToDoDepartmentAndEmpNum() throws SQLException;

	/**
	 * 公共树的查询
	 * 
	 * @param departmentId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getDepartmentTreeCommon(String departmentId) throws SQLException;

	/**
	 * 删除长委单位(及其子单位)
	 * @param string
	 * @return
	 * @throws SQLException
	 */
	@Delete("delete from department where departmentId like '${value}%'")
	public int deleteDepartmentByUpId(String id)throws SQLException;

	/****** E leilong **********/

}
