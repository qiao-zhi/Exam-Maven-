package cn.xm.exam.service.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import cn.xm.exam.bean.system.Role;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.utils.PageBean;

/**
 * 
 * @author mafei
 *
 */
public interface RoleService {

	/**
	 * 给角色分配权限
	 * @param roleid
	 * @param permissionids
	 * @return 是否成功
	 */
	public boolean addPermissionForRole(String roleid,List<String> permissionids)throws Exception ;
		
	/**
	 * 添加角色信息以及其权限信息
	 * 将权限编号传入role中的permissionid属性 可同时插入角色的权限信息
	 * @param role
	 * @return
	 */
	public boolean addRoleWithPermission(Role role,List<String> permissionids)throws Exception ;

	/**
	 * 批量添加角色并为所有角色分配一样的权限,角色名格式为 部门名：角色名
	 * @param roles 角色的基本信息
	 * @param permissionids 权限id集合
	 * @return 插入的记录数
	 */
	public boolean addMoreRole(List<Role> roles,List<String> permissionids)throws Exception ;
	
	/**
	 * 根据部门编号查询该部门的所有角色信息
	 * @param condition departmentid 部门id currentPage：开始  currentCount：每页数量
	 * @return map集合：roles:角色对象的list集合
	 * 					totalPage:总页数
	 */
	public PageBean<Role> getRoleByDepartId(String departmentid,int currentPage,int currentCount)throws Exception ;
	
	/**
	 * 根据roleid查询角色信息包括其权限信息
	 * @return 角色信息(List<Role>)
	 * @throws SQLException
	 */
	public List<Role> getRoleWithPerByRoleId(String roleid)throws Exception ;
	
	/**
	 * 根据用户的部门编号查询本部门的角色：用于给用户分配角色
	 * @param departmentid
	 * @return 角色信息集合
	 */
	public List<Role> getIsUseRoleByDepartId(String departmentid)throws Exception ;
		
	/**
	 * 根据用户编号获取该用户角色信息
	 * @param 用户编号 userid
	 * @return 当前用户的角色信息
	 * @throws SQLException
	 */
	public List<Role> getRoleByUserId(String userid)throws Exception ;
	
	/**
	 * 修改角色信息
	 * @param roleInfo 角色信息(role)
	 * @return 修改的记录数 (int)
	 * @throws SQLException
	 */
	public boolean updateRoleInfo(Role role)throws Exception ;
	
	/**
	 * 删除角色信息(同时删除其权限信息)
	 * @param roleId 角色Id(String)
	 * @return 删除的记录数(int)
	 * @throws SQLException
	 */
	public boolean deleteRoleInfo(List<String> roleids)throws Exception ;

	/**
	 * 删除角色的所有权限
	 * @param roleid
	 * @return 删除的记录数
	 * @throws SQLException
	 */
	public boolean deleteRolePermissionByRoleid(List<String> roleids)throws Exception ;
	
	/**
	 * 根据roleid permissionid 删除角色的部分权限
	 * @param Map<String, List<String>> roleid:roleid permissionid:permissionid的集合
	 * @return 删除的记录数
	 */
	public boolean deleteRolePermissionByRoleidAndPermissionid(Map<String, List<String>> rolePermission)throws Exception ;
	
	/**
	 * 根据用户编号查询该用户不扮演的角色
	 * @param userid 用户编号
	 * @return 角色基本信息
	 * @throws SQLException
	 */
	public List<Role> getLeftRoleByUserId(String userid)throws Exception ;
	
	/**
	 * 根据角色编号查询该角色下的所有用户
	 * @param roleid:角色编号 pageBegin：开始  pageSize：每页数量
	 * @return List<User> 用户基本信息 
	 * @throws SQLException
	 */
	public List<User> getUserByRoleId(Map<String, Object> roleid)throws Exception ;

	/**
	 * 获取本部门的角色总数(不包括所属部门)
	 * @param departmentid 部门编号
	 * @return 角色总数
	 */
	public int getRoleCount(String departmentid)throws Exception ;
	
	/**
	 * 根据部门编号获取该部门及其所属部门的角色
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleNameAndIdByDepartid(String departmentid)throws Exception;
	
	/**
	 * 组合查询角色
	 * @param condition 
	 * 					departmentid:部门编号
	 * 					rolename:角色名
	 * 					rolestatus：角色状态
	 * @return
	 * @throws Exception
	 */
	public PageBean<Role> getRoleByCondition(Map<String, Object> condition,int currentPage,int currentCount);
	
	/**
	 * 更新角色的权限信息
	 * @param roleid
	 * @param permissionids
	 * @return
	 * @throws Exception
	 */
	public boolean updatePermissioninfoForRole(String roleid,List<String> permissionids)throws Exception;
	
	/**
	 * 关闭/开启角色
	 * @param roleid 角色编号
	 * @param rolestatus 状态
	 * @return
	 * @throws Exception
	 */
	public boolean updateRoleStatus(String roleid,String rolestatus)throws Exception;
	
	/**
	 * 添加角色信息
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public boolean addRoleinfo(Role role)throws Exception;
	
	/**
	 * 获取权限树，角色拥有的权限，加checked:true
	 * @param roleid
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getisHavePermission(String roleid,String scope)throws Exception;
}
