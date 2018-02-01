package cn.xm.exam.mapper.employee.in.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.employee.in.EmplyinBreakrules;
import cn.xm.exam.bean.employee.out.Blacklist;

public interface EmplyinBreakrulesCustomMapper {
	// ********************lixianyuan start
	/**
	 * <!-- 查询部门表的所有信息，用于初始化左侧的部门树 -->
	 */
	List<Map<String, Object>> initLeftDepartmentTree(String departmentId) throws SQLException;

	/**
	 * 根据部门id获取当前部门旗下的所有员工及其违章信息
	 */
	List<Map<String, Object>> getEmpInMsgByDepIdLeft(Map<String, Object> map);

	/**
	 * <!-- 根据部门id获取该部门下的所有员工及其违章信息 的总条数 -->
	 */
	int getEmpInMsgByDepIdLeftCount(Map<String, Object> map);

	/**
	 * <!-- 页面加载的时候把所有部门下的所有员工及其违章信息显示出来,用于页面初始化 -->
	 */
	List<Map<String, Object>> initPageDate(Map<String, Object> map);

	/**
	 * <!-- 页面加载的时候把所有部门下的所有员工及其违章信息的 总记录数 -->
	 */
	int initPageDateCount(String departmentId);

	/**
	 * <!-- #根据职工id去违章记录表中查询该内部员工的违章总积分 employeeid -->
	 */
	Integer selSumBreakScoreByEmpId(String employeeid);

	/**
	 * QLQ写的
	 * 
	 * @param condition
	 * @return
	 */
	Integer selSumBreakScoreByEmpId1(Map condition);
	/**
	 * <!-- 根据职工id获取职工信息 -->
	 */
	EmployeeIn selEmployeeInByEmpId(String employeeid);

	/**
	 * <!-- 根据职工id获取该职工的所有违章信息 -->
	 */
	List<EmplyinBreakrules> selEmpInBreakrulesByEmpId(String employeeid);
	/**
	 * <!-- 根据职工id获取该职工的所有违章信息 -->
	 */
	List<EmplyinBreakrules> selEmpInBreakrulesByEmpId1(Map condition);

	/**
	 * <!-- 根据职工id从黑名单中删除该职工id的黑名单信息 -->
	 */
	int delBlacklistByEmpId(String employeeid);

	/**
	 * <!-- 根据职工id获取黑名单信息 -->
	 */
	List<Blacklist> getBlacklistByEmpInId(String employeeid);

	/**
	 * <!-- 点击左侧的部门树的时候，将该部门及该部门下的所有子部门下的内部员工的信息及其违章信息显示出来-->
	 */
	List<Map<String, Object>> clickLeftShowEmpInMsg(Map<String, Object> map);

	/**
	 * <!-- 点击左侧的部门树的时候，查询该部门及该部门下的所有子部门下的内部员工的信息及其违章信息 的总记录数-->
	 */
	int clickLeftShowEmpInMsgCount(Map<String, Object> map);

	/**
	 * <!-- 左侧的树和条件绑定之后的查询，显示该部门及旗下所有子孙部门下的内部员工的信息及其违章信息 -->
	 */
	List<Map<String, Object>> leftTreeAndConditionMsg(Map<String, Object> map);

	/**
	 * <!-- 左侧的树和条件绑定之后的查询，显示该部门及旗下所有子孙部门下的内部员工的信息及其违章信息 的总记录数 -->
	 */
	int leftTreeAndConditionMsgCount(Map<String, Object> map);

	/**
	 * <!-- 左侧的树和条件绑定之后 黑名单状态为"否"的情况的查询，的查询，显示该部门及旗下所有子孙部门下的内部员工的信息及其违章信息 -->
	 */
	List<Map<String, Object>> leftTreeAndConditionNoBlacklistMsg(Map<String, Object> map);

	/**
	 * <!-- 左侧的树和条件绑定之后黑名单状态为"否"的情况的查询，的查询，显示该部门及旗下所有子孙部门下的内部员工的信息及其违章信息 -->
	 */
	int leftTreeAndConditionNoBlacklistMsgCount(Map<String, Object> map);

	/**
	 * <!-- 左侧的树和条件绑定之后 黑名单状态为"是"的情况的查询，显示该部门及旗下所有子孙部门下的内部员工的信息及其违章信息 -->
	 */
	List<Map<String, Object>> leftTreeAndConditionIsBlacklistMsg(Map<String, Object> map);

	/**
	 * <!-- 左侧的树和条件绑定之 后黑名单状态为"是"的情况的查询，的查询，显示该部门及旗下所有子孙部门下的内部员工的信息及其违章信息 -->
	 */
	int leftTreeAndConditionIsBlacklistMsgCount(Map<String, Object> map);

	/**
	 * <!-- 根据内部员工id 获取黑名单状态 -->
	 */
	String getBlackStatusByEmpInId(String employeeid);
	/**
	 * <!-- 根据内部员工id获取该员工的违章总积分 -->
	 */
	int getSingleEmplyInBreakScoreSum(Map<String,Object> map);

	// *******************lixianyuan end

}
