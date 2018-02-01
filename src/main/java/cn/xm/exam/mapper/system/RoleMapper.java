package cn.xm.exam.mapper.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.system.Role;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.utils.PageBean;



public interface RoleMapper {
    
	/**
	 * 为角色分配权限
	 * @param role
	 * @return
	 */
	public int addPermissionForRole(@Param("roleid")String roleid,@Param("permissionids")List<String> permissionids) throws SQLException ;
		
	/**
	 * 插入角色信息
	 * @param role
	 * @return
	 */
	public int addRole(List<Role> roles) throws SQLException;
	
	
	/**
	 * 查询本部门的所有角色信息
	 * @param  departmentid 部门id rowBegin：开始  currentCount：每页数量
	 * @return role 对象集合
	 */
	public List<Role> getRoleByDepartId(@Param("departmentid")String departmentid,@Param("rowBegin")int rowBegin,@Param("currentCount")int currentCount)  throws SQLException;
	
	/**
	 * 根据roleid查询角色信息包括其权限信息
	 * @return 角色信息(List<TBaseRoleInfo>)
	 * @throws SQLException
	 */
	public List<Role> getRoleByRoleId(String roleid) throws SQLException;
	
//	/**
//	 * 查询所有角色:1判断其权限 若有厂级系统管理权限，传入空值''或null
//	 * 			    2若没有厂级权限 则传入用户的departmentid
//	 * @return 角色信息集合
//	 * @throws SQLException
//	 */
//	public List<Role> getAllRoleByRoleId(String departmentid) throws SQLException;
	
	/**
	 * 根据用户的部门编号查询本部门的角色：用于给用户分配角色
	 * @param departmentid
	 * @return 角色信息集合
	 */
	public List<Role> getIsUseRoleByRoleId(String departmentid) throws SQLException;
		
	/**
	 * 根据用户编号获取该用户角色信息
	 * @param userId 用户编号
	 * @return 当前用户的角色信息
	 * @throws SQLException
	 */
	public List<Role> getRoleByUserId(String employeeid) throws SQLException;
	
	
	/**
	 * 修改角色信息
	 * @param role 角色信息(role)
	 * @return 修改的记录数 (int)
	 * @throws SQLException
	 */
	public int updateRoleInfo(Role role) throws SQLException;
	
	/**
	 * 删除角色信息
	 * @param roleId 角色Id(String)
	 * @return 删除的记录数(int)
	 * @throws SQLException
	 */
	public int deleteRoleInfo(List<String> roleid) throws SQLException;

	/**
	 * 删除角色的所有权限
	 * @param roleid
	 * @return 删除的记录数
	 * @throws SQLException
	 */
	public int deleteRolePermissionByRoleid(List<String> roleids) throws SQLException;
	
	/**
	 * 根据roleid permissionid 删除角色的部分权限
	 * @param Map<String, List<String>> roleid:roleid permissionid:permissionid的集合
	 * @return 删除的记录数
	 */
	public int deleteRolePermissionByRoleidAndPermissionid(Map<String, List<String>> rolePermission) throws SQLException;
	
	/**
	 * 根据用户编号查询该用户不扮演的角色
	 * @param employeeid 用户编号
	 * @return 角色基本信息
	 * @throws SQLException
	 */
	public List<Role> getLeftRoleByUserId(String employeeid) throws SQLException;
	
	/**
	 * 根据角色编号查询该角色下的所有用户
	 * @param roleid:角色编号 rowBegin：开始  currentCount：每页数量
	 * @return List<User> 用户基本信息 
	 * @throws SQLException
	 */
	public List<User> getUserByRoleId(Map<String, Object> roleid) throws SQLException;
	
	/**
	 * 获取本部门的角色总数(不包括所属部门)
	 * @param departmentid 部门编号
	 * @return 角色总数
	 */
	public int getRoleCount(String departmentid);
	
	/**
	 * 根据部门id获取部门名字
	 * @param departmentId 部门id
	 * @return departmentName 部门名字
	 */
	public String getDepartNameByDepartId(String  departmentId) throws SQLException;
	
	/**
	 * 根据部门编号获取该部门及其所属部门的角色
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleNameAndIdByDepartid(String departmentid)throws SQLException;
	
	/**
	 * 组合查询角色的记录数
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int getRoleCountByCondition(Map<String, Object> condition)throws SQLException;
	
	/**
	 * 组合查询角色
	 * @param condition 
	 * 					departmentid:部门编号
	 * 					rolename:角色名
	 * 					rolestatus：角色状态
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleByCondition(@Param("condition")Map<String, Object> condition,@Param("rowBegin")int rowBegin,@Param("currentCount")int currentCount)throws SQLException;
	
	/**
	 * 关闭/开启角色
	 * @param roleid 角色编号
	 * @param rolestatus 状态
	 * @return
	 * @throws Exception
	 */
	public boolean updateRoleStatus(@Param("roleid")String roleid,@Param("rolestatus")String rolestatus)throws SQLException;
	
	/**
	 * 添加角色信息
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public int addRoleinfo(Role role)throws SQLException;
	
	/**
	 * 根据角色编号获取其权限编号的集合
	 * @param roleid
	 * @return
	 * @throws SQLException
	 */
	public List<String> getPermissionIDbyRole(String roleid)throws SQLException;
	
	/**
	 * 删除角色时，删除所有用户下的该角色
	 * @param roleid
	 * @return
	 * @throws SQLException
	 */
	public int deleteRoleFromUserByRoleid(List<String> roleids)throws SQLException;
	
	
}