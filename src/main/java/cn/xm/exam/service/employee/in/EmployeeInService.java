package cn.xm.exam.service.employee.in;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.vo.exam.ExamEmployeeInQueryVo;

//完

/**
 * 内部员工service接口
 * 
 * @author QiaoLiQiang
 * @time 2017年8月9日上午9:59:31
 */
public interface EmployeeInService {
	
	/*************** S zhangwenyi *************/
	/*** -----------------------基本的增删改查-------------------------------- *****/

	/**
	 * 根据部门号查出下次要插入的员工ID
	 * 
	 * @param departmentId
	 *            部门ID
	 * @return 下次要插入的员工ID
	 * @throws Exception
	 */
	public String getNextEmployeeInId(String departmentId) throws Exception;

	/**
	 * 添加外部员工(员工ID用上面方法获取)
	 * 
	 * @param emplyeeIn
	 *            员工对象
	 * @return
	 * @throws Exception
	 */
	public boolean addEmployeeIn(EmployeeIn emplyeeIn) throws Exception;

	/**
	 * 通过id删除内部员工
	 * 
	 * @param employeeInId
	 *            需要删除的内部员工的id(dao层首先删除其关联的子表)
	 * @return
	 * @throws Exception
	 */
	public boolean deleteEmployeeInById(String employeeInId) throws Exception;

	/**
	 * 修改内部员工信息
	 * 
	 * @param emplyeeIn
	 *            将修改过的员工对象重新传到dao层，dao自动根据对象的id重新赋值(dao层修改其关联的子表)
	 * @return
	 * @throws Exception
	 */
	public boolean updateEmployeeIn(EmployeeIn emplyeeIn) throws Exception;

	/**
	 * 通过id查询内部员工
	 * 
	 * @param employeeInId
	 *            需要查询的内部员工id
	 * @return
	 * @throws Exception
	 */
	public EmployeeIn getEmployeeInById(String employeeInId) throws Exception;

	/************* 这个功能用下边的可以实现 *********************/
	/**
	 * 通过单位ID查询员工信息
	 * 
	 * @param departmentId
	 *            单位id
	 * @return 一个单位员工集合
	 * @throws Exception
	 *//*
		 * public List<EmployeeIn> getEmployeeInBydepartmentId(String
		 * departmentId) throws Exception;
		 */

	/*** -----------------------组合条件的分页查询-------------------------------- *****/

	/**
	 * 分页查询:可以按照姓名、身份证、性别、安全培训情况等条件的组合查询，并以列表显示查询结果。
	 * 
	 * @param currentPage
	 *            当前页
	 * @param currentTotal
	 *            当前页数
	 * @param condition
	 *            封装组合条件(部门号，当前页，每页记录数是必须传入的参数，默认第一页)
	 * @return 返回的是一个PageBean对象，里面封装的有分页显示需要的当前页，当前页显示的条数，总条数，总页数以及返回到页面显示的数据集合。
	 * @throws Exception
	 */
	public PageBean<EmployeeIn> findEmployeeInWithCondition(Map<String, Object> condition) throws Exception;

	public boolean batchImportEmployeeIn(List<EmployeeIn> list);

	public List<String> getALLEmployeeInByDepartmentId(String departmentid);
	
	public boolean isIdCode(String myIdcode);
	public boolean addEmployeeInBatch(List<EmployeeIn> employeeInList) throws Exception;

	public String isBlackList(String idcode);

	//判断是否是内部部门
	public boolean isNeibu(String yincangbumenid);

	//显示员工信息
	public PageBean<Map<String, Object>> getEmpCase(Map<String, Object> condition3);
	
	//通过身份证得到员工信息
	public EmployeeIn getEmployeeInByIdcode(String idcode);
		
	/*************** E zhangwenyi *************/
	
	/*************** S QLQ *************/
	/**
	 * 条件根据查询内部参考员工
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getExamEmployeeIns(Map condition) throws SQLException;
	/*************** E QLQ *************/

	

	
}
