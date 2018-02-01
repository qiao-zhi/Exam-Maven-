package cn.xm.exam.service.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.system.Permission;
import cn.xm.exam.utils.PageBean;

/**
 * 
 * @author mafei
 *
 */
public interface PermissionService {

	
    /**
     * 查询所有的权限
     * @return 权限集合
     */
    public 	List<Permission> getAllpermission()throws Exception;
    
	 /**
     * 查询所有启用的权限：厂级角色
     * @return 所有启用的权限集合
     */
	public List<Permission> getAllIsusePermission()throws Exception;
	
	/**
	 * 查询启用的权限（不包括 厂级的权限）：部门级角色
	 * @return 不包括厂级权限的所有权限的集合
	 */
	public List<Permission> selectDepartIsuserPermission()throws Exception;

    /**
     * 更新权限 是否启用
     * @param permission
     * @return
     */
	public int updatePermission(Permission permission)throws Exception;
	
	/**
	 * 根据permissionid查询权限信息
	 * @param 权限id
	 * @return
	 * 
	 */
	public Permission getPermissionById(String permissionid) throws Exception;

	/**
	 * 根据权限id查询下一级所有权限信息
	 * @param 权限id
	 * @return
	 *
	 */
	public PageBean<Permission> getNextPermissionById(String permissionid,int currentPage,int currentCount)throws Exception;
	
	/**
	 * 根据权限id查询下一级所有权限信息 不包括厂级范围权限
	 * @param 权限id
	 * @return
	 *
	 */
	public PageBean<Permission> getNextPermissionById_Without_ELSCOPE(String permissionid,int currentPage,int currentCount)throws Exception;
	
	/**
	 * 组合查询权限信息
	 * @param condition
	 * @param currentPage
	 * @param currentCount
	 * @return
	 * @throws Exception
	 */
	public PageBean<Permission> getPermissioninfoByCondition(Map<String, Object> condition,int currentPage,int currentCount)throws Exception;
	/**
	 * 根据 roleid 查询所拥有的权限
	 * @param rolename
	 * @return
	 * @throws SQLException
	 */
	public List<Permission> getPermissionByRoleId(String roleid)throws Exception;
	
	/**
	 * 获取顶级菜单的id，name
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getMenutop()throws Exception;
	
	/**
	 * 更新权限的状态
	 * @param permissionid
	 * @param permissionstatus
	 * @return
	 * @throws Exception
	 */
	public boolean updatePermissionStatus(String permissionid,String permissionstatus)throws Exception;
	
	/**
	 * 获取权限树
	 * @param scope
	 * @return
	 */
	public List<Map<String, Object>> getPermissionTree(String scope)throws Exception;
}
