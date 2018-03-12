package cn.xm.exam.service.employee.out;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.employee.out.Unit;
import cn.xm.exam.bean.haul.Haulunit;
import cn.xm.exam.utils.PageBean;

//完

/**
 * 外来单位service接口
 * 
 * @author QizoLiQiang
 * @time 2017年8月7日下午5:31:57
 */
public interface UnitService {

	/*** -----------------------基本的增删改查-------------------------------- *****/

	/**
	 * 根据上级编号获取数据库中下级部门最大id值，在service实现类中转为Integer加1后再变为String传到控制层作为添加时的部门id
	 * 
	 * @param upUnitId
	 *            上级部门(根据这个查询下级部门最大的Id值)
	 * @return
	 * @throws Exception
	 */
	public String getNextUnitId(String upUnitId) throws Exception;

	/**
	 * 保存外来单位的基本信息(dao根据上级编号添加到对应的外来单位下面,且保存的Id通过sql获取到最大值后加1)
	 * 
	 * @param unit
	 *            保存的外来单位对象
	 * @param bigId
	 *            保存的外来单位对象对应的大修ID
	 * @param projectnames
	 *            保存的外来单位对象的工程信息
	 * @param projectids
	 *            工程ID
	 * @return 是否保存成功，根据返回的影响的行数判断
	 * @throws Exception
	 */
	public boolean addUnit(Unit unit, String bigId, Haulunit hulunit,String projectids) throws Exception;
	
	public boolean addUnit2(Unit unit, String bigId, Haulunit hulunit) throws Exception;
	/**
	 * 通大修ID与单位ID删除单位
	 * 
	 * @param id
	 *            需要删除的外来单位的id(dao层首先删除其关联的子表)
	 * @return 是否删除成功，根据返回的影响的行数判断
	 * @throws Exception
	 */
	public boolean deleteUnitByBigIdAndHaulId(Map<String, Object> bigidAndUnitid) throws Exception;

	/**
	 * 修改外来单位信息
	 * 
	 * @param unit
	 *            修改过的单位对象重新传到dao层，dao自动根据对象的id重新赋值(dao层修改其关联的子表)
	 * @return 是否修改成功
	 * @throws Exception
	 */
	public boolean updateUnit(Unit unit, Haulunit haulUnit,String projectids) throws Exception;
	
	public boolean updateUnit(Unit unit, Haulunit haulUnit) throws Exception;

	/**
	 * 通过id查询外来单位。
	 * 
	 * @param id
	 *            需要查询的单位id
	 * @return 外来单位
	 * @throws Exception
	 */
	public Unit getUnitById(String id) throws Exception;

	/**
	 * 根据单位的名称获取单位的Id
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String getUnitIdByName(String name) throws Exception;

	/**
	 * 通过上级Id查询单位。在点击树的时候需要在右边显示所有的下级部门
	 * 
	 * @param upUnitId
	 *            上级单位id
	 * @return 单位集合
	 * @throws Exception
	 *//*
		 * 下边的分页查询条件可以实现 public List<Unit> getUnitByUpUnitId(String upUnitId)
		 * throws Exception;
		 */

	/**
	 * 查询部门树结构，返回list集合后在Action层中转为JSON串回传给前台(也就是查出所有单位的ID,上级ID，单位名称构成一棵树)
	 * 
	 * @return map建应该为单位id，上级id，单位名称
	 * @throws Exception
	 */
	public List<Map<String, Object>> getUnitTree() throws Exception;

	/*** -----------------------组合条件的查询-------------------------------- *****/
	// （4）查询：可以按照单位名称、违章积分范围等条件进行查询，并以列表形式展示。其中外来单位违章积分由其各级人员违章积分累积之和计算得到。
	/**
	 * 分页查询:根据单位的名称，违章积分范围等进行查询
	 * 
	 * @param condition
	 *            封装查询条件封装组合条件(部门号，当前页，每页记录数是必须传入的参数，默认第一页)。 map中应该包括的有
	 *            name:单位名称 ;currentPage(int):当前页 ;currentCount(int):当前页的条数;
	 *            maxScore(int):违章范围最大值 ;minScore(int):违章范围最大值;
	 *            upUnitId:上级单位编号(左边树的单位ID)。dao层根据条件查出满足的总数后进行分页计算
	 * @return 返回的是一个PageBean对象，里面封装的有分页显示需要的当前页，当前页显示的条数，总条数，总页数以及返回到页面显示的数据集合。
	 * @throws Exception
	 */
	public PageBean<Map<String, Object>> findUnitsWithCondition(int currentPage, int currentCount,
			Map<String, Object> condition) throws Exception;
	
	public PageBean<Map<String, Object>> findUnitsWithCondition2(int currentPage, int currentCount,
			Map<String, Object> condition) throws Exception;

	/**
	 * 根据单位id查询单位的工程
	 * 
	 * @param condition
	 *            需要包装的查询条件。应该包括单位的Id，其他筛选条件也可以加。如果什么条件都不加，默认查询单位的所有工程信息
	 * @return 封装有分页信息显示需要的PageBean对象，返回的数据在其list集合中
	 * @throws Exception
	 */

	/**************** S qlq **********************/
	/******** S 查询外部部门树 ****************/

	/**
	 * 查询部门id，上级id，部门名称生成外部部门树
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
	 * 根据单位名称查询单位信息
	 * 
	 * @param unitName
	 *            单位名称
	 * @return 单位信息
	 * @throws SQLException
	 */
	public Unit getUnitByUnitName(String unitName) throws SQLException;

	/**
	 * 根据大修ID和单位ID查询员工信息
	 * 
	 * @param haulIdAndUnitId
	 *            单位ID和大修ID
	 * @return
	 * @throws SQLException
	 */
	PageBean<Map<String, Object>> getEmployeeOutsByUaulIdAndUnitId( int currentPage,
			int currentCount,Map<String, Object> haulIdAndUnitId)
			throws SQLException;
	
	//查询内部新员工培训的员工信息
	PageBean<Map<String, Object>> getEmployeeOutsByUaulIdAndUnitId2( int currentPage,
			int currentCount,Map<String, Object> haulIdAndUnitId)
			throws SQLException;

	/**
	 * 根据大修ID和单位ID查询某次大修的所有违章员工的信息
	 * 
	 * @param haulIdAndUnitId
	 *            单位ID和大修ID
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Map<String, Object>> getEmployeeOutsBreakrulesByUaulIdAndUnitId(int currentPage, int currentCount,
			Map<String, Object> haulIdAndUnitId) throws SQLException;

	/**
	 * 根据大修ID查出参加大修的部门名称与部门ID
	 * 
	 * @param haulId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getUnitidsAndNamesByHaulId(String haulId,String departmentId) throws SQLException;
	/**************** E qlq **********************/
}
