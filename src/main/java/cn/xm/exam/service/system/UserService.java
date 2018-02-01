package cn.xm.exam.service.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.system.Permission;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.utils.PageBean;

/**
 * 用户管理接口：登录、给用户分配角色、删除用户、关闭用户
 * @author mafei
 */

public interface UserService {


    /**
     * 根据用户身份证号码查询用户信息及其角色信息及其权限信息
     * @param useridcard
     * @return user
     */
    public User getUserByUseridcard(String useridcard) throws Exception;
    
	/**
	 * 批量添加账号
	 * @param users 用户
	 * @param defaultRoleids 默认角色
	 * @return 是否添加成功
	 * @throws Exception
	 */
	public boolean addUser(List<User> users,List<String> defaultRoleids)throws Exception ;
	
	/**
	 * 批量添加账号
	 * @param employeeids 员工编号
	 * @return 是否添加成功
	 * @throws Exception
	 */
	public boolean addUsers(List<String> employeeids)throws Exception ;
	
    /**
     * 根据用户编号查询角色code的集合
     * @param userid
     * @return 角色集合
     */
    public Set<String> getRoleByUserid(String userid) throws Exception;	
    
    /**
     * 根据用户编号查询权限code的集合
     * @param name
     * @return 角色集合
     */
    public Set<String> getPermissionByUserid(String userid) throws Exception;	
     
	
	/**
	 * 根据用户身份证号码查询用户的登录密码
	 * @param useridcard 用户身份证号码
	 * @return 系统登录密码
	 * @throws SQLException
	 */
	public String getPasswordByUseridcard(String useridcard) throws Exception;
	
	/**
	 * 修改用户密码
	 * @param useridcard 身份证号
	 * @param password 密码
	 * @return
	 * @throws Exception
	 */
	public boolean updatePassword(String useridcard,String password)throws Exception;
	
	/**
	 * 给用户分配角色
	 * @param userids,roleids
	 * @return 是否操作成功
	 * @throws SQLException
	 */
	public boolean addRoleForUser(List<String> userids,List<String> roleids)throws Exception;
	
	/**
	 * 批量关闭/开启用户
	 * @param  userids:用户编号的集合,status:修改的目标状态
	 * @return 是否操作成功
	 */
	public boolean updateUserStatus(List<String> userids,String status)throws Exception;
	
	/**
	 * 撤销分配给用户的角色
	 * @param userRole map里面包含（用户编号 角色编号 ）(employeeId,roleId)
	 * @return 是否操作成功
	 * @throws SQLException
	 */
	public boolean deleteRoleFromUser(String userid,List<String> roleids)throws Exception;
		
	/**
	 * 根据用户编号删除用户
	 * @param userid
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteUserById(List<String> userids)throws Exception;
	
	/**
	 * 根据用户编号 删除用户的所有角色
	 * @param userid 用户编号
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAllRoleFromUserByUserId(List<String> userids)throws Exception;
	
	/**
	 * 传入用户名 username、密码 password进行登录、user_type员工类型
	 * @param username 用户名
	 * @param password 密码
	 * @return map:	errorinfo:	success_employee:登录成功_员工
	 * 							success_manager:登录成功_管理员
	 * 		   					error  :未知错误
	 * 		   					error001:该账号不存在
	 * 		   					error002：密码错误
	 * 		 					error003:该账号没有任何权限，不能使用该系统，请先分配权限
	 * 				userinfo:   user对象
	 * 				permissioninfo: permission对象
	 */
	public  Map<String, Object> login(String username,String password,String user_type)throws Exception;
	
	/**
	 * 根据用户编号获取权限的对象集合 
	 * @param userid:用户编号
	 * @return 权限对象的集合
	 * @throws Exception
	 */
	public List<Permission> getObjectPermissionByUserid(String userid)throws Exception;
	
	/**
	 * 根据部门编号获取该部门下的所有未注册系统账号的员工
	 * @param departmentid 部门编号
	 * @return
	 * @throws Exception
	 */
	public List<EmployeeIn> getEmployeeInWithoutUserByDepartId(String departmentid)throws Exception;
	
	/**
	 * 根据部门编号获取该部门下的所有系统用户,带分页
	 * @param departmentid 部门编号
	 * @param currentPage 当前页码
	 * @param currentCount 每页显示的总条数
	 * @return 用户对象的集合
	 * @throws Exception
	 */
	public PageBean<User> getUserByDepartId(String departmentid,int currentPage,int currentCount)throws Exception;
	
	/**
	 * 组合查询用户
	 * @param condition 
	 * 					departmentid:部门编号
	 * 					username:用户姓名
	 * 					useridcard：用户身份证号码
	 * 					roleid:角色编号
	 * @return
	 * @throws Exception
	 */
	public PageBean<User> getUserByCondition(Map<String, Object> condition,int currentPage,int currentCount)throws Exception;
	
	/**
	 * 更新用户的角色信息
	 * @param userid 用户编号
	 * @param roleid 角色编号集合
	 * @return
	 * @throws Exception
	 */
	public boolean updateRoleinfoForUser(String userid,List<String> roleids)throws Exception;
	
	/**
	 * 根据登录用户的所属部门及其权限获取相应的部门树
	 * @param departmentid 部门编号	
	 * @param permissioncode 权限code集合
	 * @return list：map ：
	 * 				      departmentid：部门编号
	 * 				      updepartmentid：上级部门编号
	 * 				      departmentname：部门名字
	 * @throws Exception
	 */
	public List<Map<String, Object>> getDepartTreeByPermAndDepartId(String departmentid)throws Exception;
	
	/**
	 * 根据部门编号和员工姓名获取用户
	 * @param departmentid 部门编号
	 * @param name 员工姓名
	 * @return List<EmployeeIn>
	 * @throws Exception
	 */
	public List<EmployeeIn> getEmployeeBynameAnddepartmentid(String departmentid,String name)throws Exception;
	
	/**
	 * 从员工表跟新其用户信息
	 * @param useridcard
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserInfo(String useridcard)throws Exception;
}
