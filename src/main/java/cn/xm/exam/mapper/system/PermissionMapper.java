package cn.xm.exam.mapper.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.xm.exam.bean.system.Permission;
import cn.xm.exam.utils.PageBean;


public interface PermissionMapper {
    
    /**
     * 查询所有的权限
     * @return 权限对象的集合
     */
    public List<Permission> getAllPermission() throws SQLException;
    
    /**
     * 查询所有已启用的的权限
     * @return 所有启用的权限对象的集合
     */
    public List<Permission> getAllIsusePermission() throws SQLException;
    
	/**
	 * 查询启用的权限（不包括 厂级的权限）：部门级角色
	 * @return 不包括厂级权限的所有权限的集合
	 */
	public List<Permission> getDepartIsuserPermission() throws SQLException;
    
    /**
     * 更新权限：是否启用,描述
     * @param permission permissionid,status(true,false)
     * @return 操作的记录数
     */
	public int updatePermission(Permission permission) throws SQLException;
	
	/**
	 * 根据permissionid查询功能信息
	 * @param 权限id
	 * @return 权限集合
	 * 
	 */
	public Permission getPermissionById(String permissionid) throws SQLException ;

	/**
	 * 根据permissionid查询下一级所有功能信息
	 * @param 功能id
	 * @return
	 *
	 */
	public List<Permission> getNextPermissionById(@Param("permissionid")String permissionid,@Param("rowBegin")int rowBegin,@Param("currentCount")int currentCount)  throws SQLException;
	
	/**
	 * 根据permissionid查询下一级所有功能信息的记录数
	 * @param 功能id
	 * @return
	 *
	 */
	public int getCountBymenutopid(String permissionid)throws SQLException;
	
	/**
	 * 根据权限id查询下一级所有权限信息 不包括厂级范围权限
	 * @param 权限id
	 * @return
	 *
	 */
	public List<Permission> getNextPermissionById_Without_ELSCOPE(@Param("permissionid")String permissionid,@Param("rowBegin")int rowBegin,@Param("currentCount")int currentCount)throws SQLException;
	
	/**
	 * 根据权限id查询下一级所有权限信息 不包括厂级范围权限 的记录数
	 * @param 权限id
	 * @return
	 *
	 */
	public int getCountById_Without_ELSCOPE(String permissionid)throws SQLException;
	
	/**
	 * 根据 roleid 查询所拥有的权限
	 * @param rolename
	 * @return
	 * @throws SQLException
	 */
	public List<Permission> getPermissionByRoleId(String roleid) throws SQLException;
	
	/**
	 * 获取顶级菜单的id，name
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getMenutop()throws SQLException;
	
	/**
	 * 组合查询权限信息
	 * @param condition
	 * @param currentPage
	 * @param currentCount
	 * @return
	 * @throws Exception
	 */
	public List<Permission> getPermissioninfoByCondition(@Param("condition")Map<String, Object> condition,@Param("rowBegin")int rowBegin,@Param("currentCount")int currentCount)throws SQLException;
	
	/**
	 * 组合查询的记录数
	 * @param condition
	 * @return
	 */
	public int getCountByCondition(Map<String, Object> condition)throws SQLException;
	
	/**
	 * 更新权限的状态
	 * @param permissionid
	 * @param permissionstatus
	 * @return
	 * @throws Exception
	 */
	public boolean updatePermissionStatus(@Param("permissionid")String permissionid,@Param("permissionstatus")String permissionstatus)throws SQLException;
	
	/**
	 * 更新子权限的状态
	 * @param permissionid
	 * @param permissionstatus
	 * @return
	 * @throws Exception
	 */
	public boolean updatePermissionStatusByParentid(@Param("permissionid")String permissionid,@Param("permissionstatus")String permissionstatus)throws SQLException;
	/**
	 * 获取权限树
	 * @param scope
	 * @return
	 */
	public List<Map<String, Object>> getPermissionTree(String scope)throws SQLException;
	
	/**
	 * 更新权限状态
	 * @return
	 * @throws SQLException
	 */
	public boolean updatePermissionsStatus(@Param("permissionids")List<String> permissionids,@Param("status")String status)throws SQLException;
	
}