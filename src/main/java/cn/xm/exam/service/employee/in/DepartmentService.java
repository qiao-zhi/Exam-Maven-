package cn.xm.exam.service.employee.in;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.xm.exam.bean.employee.in.Department;
import cn.xm.exam.utils.PageBean;

//完

/**
 * 内部部门service层接口
 * 
 * @author QiaoLiQiang
 * @time 2017年8月9日上午9:45:31
 */
public interface DepartmentService {
	
	/** S zwy *********/
	/*** -----------------------基本的增删改查-------------------------------- *****/

	/**
	 * 根据上级编号获取数据库中下级部门最大id值，在service实现类中转为Integer加1后再变为String传到控制层作为添加时的部门id
	 * 
	 * @param upDepartmentId
	 *            上级部门(根据这个查询下级部门最大的Id值)
	 * @return
	 * @throws Exception
	 */
	public String getNextDepartmentId(String upDepartmentId) throws Exception;

	/**
	 * 保存部门的基本信息(dao根据上级编号添加到对应的部门下面,且保存的Id通过sql获取到最大值后加1)
	 * 
	 * @param department
	 *            保存的部门对象
	 * @return 是否保存成功，根据返回的影响的行数判断
	 * @throws Exception
	 */
	public String addDepartment(Department department) throws Exception;

	/**
	 * 通过id删除部门
	 * 
	 * @param departmentId
	 *            需要删除的部门的id(dao层首先删除其关联的子表)
	 * @return 是否删除成功，根据返回的影响的行数判断
	 * @throws Exception
	 */
	public String deleteDepartmentById(String departmentId) throws Exception;

	/**
	 * 修改部门信息
	 * 
	 * @param department
	 *            修改过的单位对象重新传到dao层，dao自动根据对象的id重新赋值(dao层修改其关联的子表)
	 * @return 是否修改成功
	 * @throws Exception
	 */
	public boolean updateDepartment(Department department) throws Exception;

	/**
	 * 通过id查询部门。
	 * 
	 * @param departmentId
	 *            需要查询的部门id
	 * @return 部门
	 * @throws Exception
	 */
	public Department getDepartmentById(String departmentId) throws Exception;

	/**
	 * 跟据部门名称获取部门ID(添加培训资料要将资料放到对应的部门下面)
	 * 
	 * @param departmentName
	 *            部门名称
	 * @return 部门的ID
	 * @throws Exception
	 */
	public String getDepartmentIdByName(String departmentName) throws Exception;

	/**
	 * 通过上级部门Id查询部门。在点击树的时候需要在右边显示所有的下级部门
	 * 
	 * @param upDepartmentId
	 *            上级部门id
	 * @return 部门集合
	 * @throws Exception
	 */
	public List<Department> getDepartmentByUpDepartmentId(String upDepartmentId) throws Exception;

	/*** -----------------------组合件的查询-------------------------------- *****/
	// （4）查询：可以按照单位名称、违章积分范围等条件进行查询，并以列表形式展示。其中部门违章积分由其各级人员违章积分累积之和计算得到。
	/**
	 * 分页查询:根据单位的名称，违章积分范围等进行查询
	 * 
	 * @param condition
	 *            封装查询条件。如果封装的查询条件为空默认查询所有的部门信息。 map中应该包括的有 name:单位名称
	 *            ;currentPage(int):当前页 ;currentCount(int):当前页的条数;
	 *            maxScore(int):违章范围最大值 ;minScore(int):违章范围最大值;
	 *            upDepartmentId:上级单位编号(左边树的单位ID)。dao层根据条件查出满足的总数后进行分页计算
	 * @return 返回的是一个PageBean对象，里面封装的有分页显示需要的当前页，当前页显示的条数，总条数，总页数以及返回到页面显示的数据集合。
	 * @throws Exception
	 */
	public PageBean<Map<String, Object>> findDepartmentsWithCondition(Map<String, Object> condition) throws Exception;

	// 辅助考试管理模块
	/**
	 * 根据部门的名称获取部门的id集合(加到考试部门中间表)
	 * 
	 * @param departmentNames
	 *            部门的名称
	 * @return
	 * @throws Exception
	 */

	public List<String> getDepartmentIdsByNames(List<String> departmentNames) throws Exception;

	public String getDepartmentNameById(String departmentid);

	public int getEmployeeInCountsById(String updepartmentid);

	/**
	 * 查询部门树结构，返回list集合后在Action层中转为JSON串回传给前台(也就是查出所有部门的ID,上级ID，部门名称构成一棵树)
	 * 
	 * @return map建应该为单位id，上级id，单位名称
	 * @throws Exception
	 */
	public List<Map<String, Object>> getDepartmentTree(String departmentId) throws Exception;
	
	/**
	 * 判断是否是内部部门
	 */
	public boolean isDepartment(String departmentid);
	
	/**
	 * 通过部门id得到所有的下级员工id
	 */
	public List<String> getEmpIdByDepartmentid(String departmentid);
	
	/**
	 * 显示部门违章积分
	 */
	public PageBean<Map<String, Object>> getBreakrulesCase(Map<String, Object> condition4);

	/** E zwy *********/

	/** S qlq *********/

	/**
	 * 查询部门id，上级id，部门名称生成内部部门树
	 * 
	 * @return 一个map包含部门的部门id，上级id，部门名称
	 * @throws SQLException
	 */
	//public List<Map<String, Object>> getDepartmentTreeForExam(String departmentId) throws SQLException;
	public List<String> getChangWeiDepartment(String depNameWords) throws SQLException;
	/** E qlq *********/
	
	/** S ll *********/
	
	/**
	 * 统计内部正式单位的信息分页显示
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public PageBean<Map<String, Object>> getDepartmentInFormalCountInfo(int currentPage, int currentTotal,Map<String,Object> condition) throws Exception;
	
	/**
	 * 统计内部长委单位的信息分页显示
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public PageBean<Map<String, Object>> getDepartmentInToDoCountInfo(int currentPage, int currentTotal,Map<String,Object> condition) throws Exception;
	
	/**
	 * 获取内部正式单位部门和员工数
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getFormalDepartmentAndEmpNum() throws Exception;
	
	/**
	 * 获取内部常委单位单位和部门数
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getToDoDepartmentAndEmpNum() throws Exception;
	
	
	/**
	 * 公共树的查询
	 * @param departmentId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getDepartmentTreeCommon(String departmentId) throws SQLException;
	
	/** E ll *********/
	/**
	 * 根据长委单位名称删除长委单位
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public String deleteCWDepartmentById(String name)throws Exception;
}
