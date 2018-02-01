package cn.xm.exam.service.employee.out;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import cn.xm.exam.bean.employee.out.Breakrules;
import cn.xm.exam.bean.employee.out.EmployeeOut;
import cn.xm.exam.bean.employee.out.Employeeoutdistribute;
import cn.xm.exam.bean.haul.Haulemployeeout;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.vo.employee.out.EmployeeOutBaseInfo;


//完

/**
 * 外来员工管理接口
 * 
 * @author QizoLiQiang
 * @time 2017年8月8日上午8:43:08
 */
public interface EmployeeOutService {
	/*** -----------------------基本的增删改查-------------------------------- *****/

	/**
	 * 根据单位号查出下次要插入的员工ID
	 * 
	 * @param unitId
	 *            部门ID
	 * @return 下次要插入的员工ID
	 * @throws Exception
	 */
	public String getNextEmployeeOutId(String unitId) throws Exception;

	/**
	 * 添加外部员工
	 * 
	 * @param emplyeeOut
	 *            员工对象
	 * @return
	 * @throws Exception
	 */
	public boolean addEmployeeOut(EmployeeOut emplyeeOut) throws Exception;

	/**
	 * 通过id删除外来员工
	 * 
	 * @param employeeOutId
	 *            需要删除的外来员工的id(dao层首先删除其关联的子表)
	 * @return
	 * @throws Exception
	 */
	public boolean deleteEmployeeOutById(String employeeOutId) throws Exception;

	/**
	 * 修改外来员工信息
	 * 
	 * @param emplyeeOut
	 *            将修改过的员工对象重新传到dao层，dao自动根据对象的id重新赋值(dao层修改其关联的子表)
	 * @return
	 * @throws Exception
	 */
	public boolean updateEmployeeOut(EmployeeOut emplyeeOut) throws Exception;

	/**
	 * 通过id查询外来员工
	 * 
	 * @param employeeOutId
	 *            需要查询的外来员工id
	 * @return
	 * @throws Exception
	 */
	public EmployeeOut getEmployeeOutById(String employeeOutId) throws Exception;

	/************* 这个功能用下边的可以实现 *********************/
	/**
	 * 通过单位ID查询外来员工信息
	 * 
	 * @param unitId
	 *            单位id
	 * @return 一个单位员工集合
	 * @throws Exception
	 *//*
		 * public List<EmployeeOut> getEmployeeOutByUnitId(String unitId) throws
		 * Exception;
		 */

	/*** -----------------------组合条件的查询-------------------------------- *****/

	/**
	 * 分页查询:按照姓名、身份证、性别、累积积分和是否进入“黑名单”等条件的组合查询，
	 * 并以列表显示查询结果。(黑名单得查另一个表，dao层根据传入的条件进行判断 员工表的员工id=黑名单的员工id)
	 * 
	 * @param condition
	 *            封装查询条件(部门号，当前页，每页记录数是必须传入的参数) * @param currentPage
	 *            当前页(第一次默认为第一页)
	 * @param currentTotal
	 *            这一显示的数据
	 * @return 返回的是一个PageBean对象，里面封装的有分页显示需要的当前页，当前页显示的条数，总条数，总页数以及返回到页面显示的数据集合。
	 * @throws Exception
	 */
	/*public PageBean<EmployeeOutBaseInfo> findEmployeeOutWithCondition(int currentPage, int currentTotal,
			Map<String, Object> condition) throws Exception;*/
	public PageBean<Map<String,Object>> findEmployeeOutWithCondition(int currentPage, int currentTotal,
			Map<String, Object> condition) throws Exception;

	/**
	 * 根据工程id查询参与工程的员工
	 * 
	 * @param projectId
	 *            工程id
	 * @param currentPage
	 *            当前页(第一次默认为第一页)
	 * @param currentTotal
	 *            这一显示的数据
	 * @return 返回的是一个PageBean对象，里面封装的有分页显示需要的当前页，当前页显示的条数，总条数，总页数以及返回到页面显示的数据集合。
	 * @throws Exception
	 */
	public PageBean<EmployeeOut> findEmployeeOutByProjectId(int currentPage, int currentTotal, String projectId)
			throws Exception;
	
	/******************************************leilong添加的***********************************************/
	
	/**
	 * 查询大修和部门的树结构
	 * 参数：培训标记类型，正式员工和外来单位长期员工的大修ID
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getDepartmentAndOverHaulTree(Map<String,Object> condition) throws Exception ;
	
	/**
	 * 根据外来单位员工的身份证号查询该员工所有的考试信息
	 * @param idCard
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getExamsInfoByEmployeeOutIdCard(String idCard) throws Exception;
	
	
	/**
	 * 根据身份证号查询外来单位员工的所有考试信息，分页显示
	 * @param currentPage
	 * @param currentCount
	 * @param idCard
	 * @return
	 * @throws Exception
	 */
	public PageBean<Map<String,Object>> getExamsInfoByEmployeeOutIdCard(int currentPage, int currentCount,String idCard) throws Exception;
	
	/**
	 * 根据员工ID和参加员工大修ID查询员工的违章信息
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Breakrules> getBreakRulesInfoByCondition(Map<String,Object> condition) throws Exception;
	
	/**
	 * 根据条件查询符合条件的员工信息用于生成工作证
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<EmployeeOutBaseInfo> getEmpInfoForCertificateWithCondition(Map<String,Object> condition) throws Exception;
	
	/**
	 * 批量加入外部员工的基本信息及参加大修外部员工的基本信息
	 * @param employeeOutList
	 * @param haulemployeeoutList
	 * @return
	 * @throws Exception
	 */
	public int addEmployeeOutBatch(List<EmployeeOut> employeeOutList,List<Haulemployeeout> haulemployeeoutList) throws Exception;
	
	/**
	 * 根据身份证号查询该员工的状态
	 * 返回：1、表示没有来过   2、表示来过 3、表示进入黑名单 4、表示已经添加到这次大修的其他部门中
	 * @param idCard bigId
	 * @return
	 * @throws Exception
	 */
	public int findEmployeeOutStatus(String idCard,String bigId,String unitId) throws Exception;
	
	/**
	 * 根据参加大修员工身份证集合查询员工的id集合
	 * @param idCards
	 * @return
	 * @throws Exception
	 */
	public List<String> findEmployeeOutIdByIdCards(List<Haulemployeeout> haulemployeeoutList) throws Exception;
	
	/**
	 * 根据员工身份证号和大修ID删除一次大修中的员工信息
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public boolean deleteHaulEmployeeOutInfoByCondition(Map<String,Object> condition) throws Exception;
	
	/**
	 * 根据大修ID和身份证号修改外来单位员工的信息
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public boolean updateHaulEmployeeOutInfoByCondition(Map<String,Object> condition) throws Exception;
	
	/**
	 * 初始化外来单位员工分配表
	 * @param distributeInfoList
	 * @return
	 * @throws Exception
	 */
	public int addEmpOutDistributeInfoList(List<Employeeoutdistribute> distributeInfoList) throws Exception;
	
	/**
	 * 根据大修员工ID集合和状态码修改大修员工表中的培训状态字段
	 * trainStatus bigEmployeeOutIds
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int updateHaulEmployeeOutTrainStatusByCondition(Map<String,Object> condition) throws Exception;
	
	/********** S Qlq *****************/
	/**
	 * 条件根据查询外部参考员工
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getExamEmployeeOuts(Map condition) throws SQLException;
	/********** E Qlq *****************/
}
