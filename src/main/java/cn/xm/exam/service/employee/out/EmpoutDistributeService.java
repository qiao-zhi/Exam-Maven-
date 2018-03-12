package cn.xm.exam.service.employee.out;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.employee.out.Employeeoutdistribute;
import cn.xm.exam.bean.haul.Haulemployeeout;
import cn.xm.exam.utils.PageBean;

/**
 * 分配員工接口
 * 
 * @author QiaoLiQiang
 * @time 2017年12月30日下午2:40:51
 */
public interface EmpoutDistributeService {
	/**
	 * 根据本部门的ID查询大修部门树
	 * 
	 * @param departmentId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getHaulunitTreeByDepartmentId(Map condition) throws SQLException;

	/**
	 * 分页查询员工分配信息
	 * 
	 * @param currentPage
	 *            当前页
	 * @param currentCount
	 *            当前页大小
	 * @param condition
	 *            组合条件
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Map<String, Object>> getDistributeInfoWithCondition(int currentPage, int currentCount,
			Map condition) throws SQLException;

	/**
	 * 根據大修ID和单位ID查询大修单位信息
	 * 
	 * @param bigId
	 *            大修ID
	 * @param unitId
	 *            單位ID
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getUintInfoByHaulIdAndUnitId(String bigId, String unitId) throws SQLException;

	/**
	 * 查询部门id，上级id，部门名称生成内部部门树
	 * 
	 * @return 一个map包含部门的部门id，上级id，部门名称
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getDepartmentTreeForFenpei(String departmentId) throws SQLException;

	/***
	 * 批量添加分配信息
	 * 
	 * @param Employeeoutdistribute
	 * @return
	 * @throws SQLException
	 */
	public boolean addFenpeiInfoBatch(List<Employeeoutdistribute> employeeoutdistribute) throws SQLException;

	/***
	 * 批量添加分配信息
	 * 
	 * @param Employeeoutdistribute
	 * @param haulEmpId
	 * @return
	 * @throws SQLException
	 */
	public boolean updateFenpeiInfo(String departmentIds, Employeeoutdistribute employeeoutdistribute)
			throws SQLException;

	/**
	 * 二次分配员工
	 * 
	 * @param haulemployeeouts大修员工信息
	 * @param employeeoutdistributes大修员工分配信息
	 * @return
	 * @throws SQLException
	 */
	public boolean addSecondFenpeiInfoBatch(List<Haulemployeeout> haulemployeeouts,
			List<Employeeoutdistribute> employeeoutdistributes) throws SQLException;
	
	/**
	 * 二次分配检修单位，免考一级
	 * @param haulemployeeouts
	 * @param employeeoutdistributes
	 * @return
	 * @throws SQLException
	 */
	public boolean addSecondFenpeiUnitBatch(List<Haulemployeeout> haulemployeeouts,
			List<Employeeoutdistribute> employeeoutdistributes) throws SQLException;
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public boolean updateDistributeForMiankao(String distributeId)throws SQLException;

}
