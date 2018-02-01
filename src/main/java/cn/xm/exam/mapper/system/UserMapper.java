package cn.xm.exam.mapper.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.system.Permission;
import cn.xm.exam.bean.system.User;

public interface UserMapper {

	   /**
     * 根据身份证号码查询用户信息极其所属部门及其部门信息及其角色信息
     * @param name
     * @return EmployeeIn
     */
    public User getUserByUseridcard(String useridcard) throws SQLException;
    
    /**
     * 根据用户名查询角色
     * @param userid
     * @return 角色 permissioncode Set集合
     */
    public Set<String> getRoleByUserid(String userid)throws SQLException;
    
    /**
     * 根据用户ID查询角色 permissioncode
     * @param userid
     * @return 权限 permissioncode List集合
     */
    public List<String> getPermissionByUseridlist(String userid)throws SQLException;
    
    /**
     * 根据用户名查询权限
     * @param name
     * @return 权限 permissioncode set集合
     */
    public Set<String> getPermissionByUserid(String userid) throws SQLException;	
	
	/**
	 * 根据用户Id查询用户的登录密码
	 * @param userId 用户Id
	 * @return 系统登录密码
	 * @throws SQLException
	 */
	public String getPasswordByUseridcard(String useridcard) throws SQLException;
	
	
	/**
	 * 给用户分配角色
	 * @param userRole (employeeid,roleId)
	 * @return 操作的记录数
	 * @throws SQLException
	 */
	public int addRoleForUser(@Param("userid")String userid,@Param("roleids")List<String> roleids)throws SQLException;
	
	/**
	 * 关闭/开启用户
	 * @param EmployeeIns map(employeeid 员工编号,status 状态)
	 * @return 操作的记录数
	 */
	public int updateUserStatus(@Param("userids")List<String> userids,@Param("status")String status)throws SQLException;
	
	/**
	 * 撤销分配给用户的角色
	 * @param userRole map里面包含（用户编号 角色编号 ）(employeeId,roleId)
	 * @return 操作的记录数
	 * @throws SQLException
	 */
	public int deleteRoleFromUser(@Param("userid")String userid,@Param("roleids")List<String> roleids)throws SQLException;
		
	/**
	 * 根据用户编号删除用户
	 * @param userids
	 * @return
	 * @throws SQLException
	 */
	public int deleteUserById(@Param("userids")List<String> userids)throws SQLException;
	
	/**
	 * 根据用户编号 删除用户的所有角色
	 * @param userid 用户编号
	 * @return
	 * @throws Exception
	 */
	public int deleteAllRoleFromUserByUserId(@Param("userids")List<String> userids)throws SQLException;
	
	/**
	 * 根据用户编号获取权限的对象集合 
	 * @param userid:用户编号
	 * @return 权限对象的集合
	 * @throws Exception
	 */
	public List<Permission> getObjectPermissionByUserid(String userid) throws SQLException; 
	
	/**
	 * 批量添加账号
	 * @param users 用户
	 * @param defaultRoleids 默认角色
	 * @return 是否添加成功
	 * @throws Exception
	 */
	public int addUser(List<User> users)throws SQLException;
	
	/**
	 * 根据部门编号获取该部门下的所有未注册系统账号的员工
	 * @param departmentid 部门编号
	 * @return
	 * @throws Exception
	 */
	public List<EmployeeIn> getEmployeeInWithoutUserByDepartId(@Param("departmentid")String departmentid)throws SQLException;
	
	/**
	 * 根据部门编号获取该部门下的所有系统用户,带分页
	 * @param departmentid 部门编号
	 * @param rowBegin：开始
	 * @param currentCount：每页数量
	 * @return 用户对象的集合
	 * @throws Exception
	 */
	public List<User> getUserByDepartId(@Param("departmentid")String departmentid,@Param("rowBegin")int rowBegin,@Param("currentCount")int currentCount)throws Exception;
	
	/**
	 * 组合查询记录数
	 * @param condition 
	 * 					departmentid:部门编号
	 * 					username:用户姓名
	 * 					useridcard：用户身份证号码
	 * 					roleid:角色编号
	 * @return
	 * @throws SQLException
	 */
	public int getCountByCondition(Map<String, Object> condition)throws SQLException;
	
	/**
	 * 组合查询记录数
	 * @param condition 
	 * 					departmentid:部门编号
	 * 					username:用户姓名
	 * 					useridcard：用户身份证号码
	 * 					roleid:角色编号
	 * @return
	 * @throws SQLException
	 */
	public int getLikeCountByCondition(@Param("condition")Map<String, Object> condition)throws SQLException;
	
	/**
	 * 组合查询用户
	 * @param condition 
	 * 					departmentid:部门编号
	 * 					username:用户姓名
	 * 					useridcard：用户身份证号码
	 * 					roleid:角色编号
	 * @return
	 * @throws SQLException
	 */
	public List<User> getUserByCondition(@Param("condition")Map<String, Object> condition,@Param("rowBegin")int rowBegin,@Param("currentCount")int currentCount)throws SQLException;
	
	/**
	 * 根据部门编号获取本部门及其所属部门的部门树
	 * @param departmentid 部门编号	
	 * @param permissioncode 权限code集合
	 * @return list：map ：
	 * 				      departmentid：部门编号
	 * 				      updepartmentid：上级部门编号
	 * 				      departmentname：部门名字
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getDepartTreeByDepartId(@Param("departmentid")String departmentid)throws SQLException;
	
	/**
	 * 修改用户密码
	 * @param useridcard 身份证号
	 * @param password 密码
	 * @return
	 * @throws SQLException
	 */
	public int updatePassword(@Param("useridcard")String useridcard,@Param("password")String password)throws SQLException;
	
	
	/**
	 * 根据员工编号集合，获取员工信息，包括部门名
	 * @param employeeids
	 * @return List<EmployeeIn>
	 * @throws SQLException
	 */
	public List<EmployeeIn> getEmployeeinsById(@Param("employeeids")List<String> employeeids)throws SQLException ;
	
	/**
	 * 根据部门id获取部门名字
	 * @param departmentid
	 * @return
	 * @throws SQLException
	 */
	public String selectDepartNameByDepartId(String departmentid)throws SQLException;
	
	/**
	 * 根据部门编号和员工姓名获取用户
	 * @param departmentid 部门编号
	 * @param name 员工姓名
	 * @return List<EmployeeIn>
	 * @throws Exception
	 */
	public List<EmployeeIn> getEmployeeBynameAnddepartmentid(@Param("departmentid")String departmentid,@Param("name")String name)throws SQLException;
	
	/**
	 * 根据身份证号获取员工信息
	 * @param useridcard
	 * @return
	 * @throws SQLException
	 */
	public EmployeeIn getEmployeeByEmployeeid(String employeeid)throws SQLException;
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public boolean updateUserInfo(User user)throws SQLException;
	
	/**
	 * 根据身份证号获取员工编号
	 * @param useridcard
	 * @return
	 * @throws SQLException
	 */
	public String getEmployeeidByIdcard(String useridcard)throws SQLException;
	
	/**
	 * 根据身份证号码获取用户ID
	 * @param useridcard
	 * @return
	 * @throws SQLException
	 */
	public String getUseridByIdcard(String useridcard)throws SQLException;
}
