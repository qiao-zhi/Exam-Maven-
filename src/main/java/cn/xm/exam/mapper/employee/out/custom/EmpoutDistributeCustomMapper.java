package cn.xm.exam.mapper.employee.out.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.xm.exam.bean.employee.out.Employeeoutdistribute;

/**
 * 分配员工的mapper
 * @author QiaoLiQiang
 * @time 2017年12月30日下午2:25:42
 */
public interface EmpoutDistributeCustomMapper {

	/**
	 *根据本部门的ID查询大修部门树
	 * @param departmentId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getHaulunitTreeByDepartmentId(Map condition) throws SQLException ;
	/**
	 * 根據大修ID和单位ID查询大修单位信息
	 * @param bigId
	 * @param unitId
	 * @return
	 * @throws SQLException
	 */
	public Map<String,Object> getUintInfoByHaulIdAndUnitId(String bigId,String unitId)throws SQLException;
	
	/**********S 查询分配信息**************/
	/**
	 * 组合条件查询分配信息
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getEmpoutDistributeInfo(Map<String,Object> condition)throws SQLException;
	/**
	 * 获取分配信息总数
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int getEmpoutDistributeInfoCountByCondition(Map<String,Object> condition)throws SQLException;
	/**********E 查询分配信息**************/
	
	/**
	 * 查询部门id，上级id，部门名称生成内部部门树
	 * 
	 * @return 一个map包含部门的部门id，上级id，部门名称
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getDepartmentTreeForFenpei(String departmentId) throws SQLException;
	/***
	 * 批量添加分配信息
	 * @param employeeoutdistribute
	 * @return
	 * @throws SQLException
	 */
	public int addFenpeiInfoBatch(@Param("list")List<Employeeoutdistribute> employeeoutdistribute)throws SQLException;
	
	/**
	 * 根据大修员工ID和部门级别删除分配信息
	 * @param haulempid
	 * @param departmentLevel
	 * @return
	 * @throws SQLException
	 */
	public int deleteFenpeiInfoByHaulempIdAndDepartmentLevel(Map condition)throws SQLException;
	
}
