package cn.xm.exam.service.impl.system;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xm.exam.bean.system.Role;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.mapper.system.PermissionMapper;
import cn.xm.exam.mapper.system.RoleMapper;
import cn.xm.exam.mapper.system.UserMapper;
import cn.xm.exam.service.system.RoleService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.PageConstants;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.utils.realm.MyRealm;


@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper roleMapper;
	@Resource
	private PermissionMapper permissionMapper;
	@Resource
	private UserMapper userMapper;
	

	/**
	 * 给角色分配权限
	 * @param roleid
	 * @param permissionids
	 * @return 是否成功
	 */
	@Transactional
	@Override
	public boolean addPermissionForRole(String roleid,List<String> permissionids) {
		
		try {
			 int i=roleMapper.addPermissionForRole(roleid,permissionids);
			 if(i==permissionids.size()){
				 return true;
			 }else {
				throw new RuntimeException("为角色分配权限失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	/**
	 * 添加角色信息以及其权限信息
	 * 将权限编号传入role中的permissionid属性 可同时插入角色的权限信息
	 * @param role
	 * @return
	 */
	@Transactional
	@Override
	public boolean addRoleWithPermission(Role role,List<String> permissionids) {		
		try {	
			role.setRoleid(UUIDUtil.getUUID());
			List<Role> roles=new ArrayList<>();
			roles.add(role);
			roleMapper.addRole(roles);
			if (permissionids!=null) {
				int i=roleMapper.addPermissionForRole(role.getRoleid(),permissionids);
					if (i!=permissionids.size()) {
						throw new RuntimeException("为角色分配权限时失败");
					}			
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * 根据roleid查询角色信息包括其权限信息
	 * @return 角色信息(List<Role>)
	 * @throws SQLException
	 */
	@Override
	public List<Role> getRoleWithPerByRoleId(String roleid){
		List<Role> role=null;
		try {
			role= roleMapper.getRoleByRoleId(roleid);
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return role;
	}

	/**
	 * 根据用户编号获取该用户角色信息
	 * @param 用户编号 userid
	 * @return 当前用户的角色信息
	 * @throws SQLException
	 */
	@Override
	public List<Role> getRoleByUserId(String userid){
		List<Role> role=null;
		try {
			role=roleMapper.getRoleByUserId(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	/**
	 * 修改角色信息
	 * @param roleInfo 角色信息(role)
	 * @return 修改的记录数 (int)
	 * @throws SQLException
	 */
	@Override
	public boolean updateRoleInfo(Role role){
		boolean result=false;
		try {
			 roleMapper.updateRoleInfo(role);
			 result=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除角色信息
	 * @param roleId 角色Id(String)
	 * @return 删除的记录数(int)
	 * @throws SQLException
	 */
	@Transactional
	@Override
	public boolean deleteRoleInfo(List<String> roleid){
		try {
			 int i=roleMapper.deleteRoleInfo(roleid);
			 if (i!=roleid.size()) {
				throw new RuntimeException("删除角色基本信息失败");
			}
			  roleMapper.deleteRolePermissionByRoleid(roleid);
			  
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 删除角色的所有权限
	 * @param roleid
	 * @return 删除的记录数
	 * @throws SQLException
	 */
	@Override
	public boolean deleteRolePermissionByRoleid(List<String> roleids){
		MyRealm myRealm=new MyRealm();
		myRealm.clearCache();
		try {
			 roleMapper.deleteRolePermissionByRoleid(roleids);
			 roleMapper.deleteRoleFromUserByRoleid(roleids);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Role> getLeftRoleByUserId(String employeeid){
		List<Role> role=null;
		try {
			role=roleMapper.getLeftRoleByUserId(employeeid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public List<User> getUserByRoleId(Map<String, Object> roleid){
		List<User> users=null;
		try {
			users=roleMapper.getUserByRoleId(roleid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	/**
	 * 根据用户的部门编号查询本部门的角色：用于给用户分配角色
	 * @param departmentid
	 * @return 角色信息集合
	 */
	@Override
	public List<Role> getIsUseRoleByDepartId(String departmentid) {
		List<Role> role=null;
		try {
			role=roleMapper.getIsUseRoleByRoleId(departmentid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}


	/**
	 * 根据部门编号查询该部门的所有角色信息
	 * @param condition departmentid 部门id currentPage：开始  currentCount：每页数量
	 * @return map集合：roles:角色对象的list集合
	 * 					totalPage:总页数
	 */
	@Override
	public PageBean<Role> getRoleByDepartId(String departmentid,int currentPage,int currentCount) {
		List<Role> roles=null;
		PageBean<Role> pageBean=new PageBean<>();
		Integer rowBegin=null;
		try {
				int totalCount=roleMapper.getRoleCount(departmentid);
				pageBean.setTotalCount(totalCount);
				rowBegin=(currentPage - 1) *currentCount;
				pageBean.setCurrentPage(currentPage);
				pageBean.setCurrentCount(currentCount);
				int totalPage=(totalCount - 1) / currentCount + 1;
				pageBean.setTotalPage(totalPage);
				roles=roleMapper.getRoleByDepartId(departmentid,rowBegin,currentCount);
				pageBean.setProductList(roles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageBean;
	}


	@Override
	public boolean deleteRolePermissionByRoleidAndPermissionid(Map<String, List<String>> rolePermission) {
		MyRealm myRealm=new MyRealm();
		myRealm.clearCache();
		boolean result=false;
		try {
			roleMapper.deleteRolePermissionByRoleidAndPermissionid(rolePermission);
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 批量添加角色并为所有角色分配一样的权限
	 * @param roles 角色的基本信息
	 * @param permissionids 权限id集合
	 * @return 插入的记录数
	 */
	@Transactional
	@Override
	public boolean addMoreRole(List<Role> roles,List<String> permissionids) {
		List<String> roleids=new ArrayList<>();
		try {			
			for (int i = 0; i < roles.size(); i++) {
				String roleid=UUIDUtil.getUUID();
				roles.get(i).setRoleid(roleid);
				roleids.add(roleid);
				String depname=roleMapper.getDepartNameByDepartId(roles.get(i).getDepartmentid());
				String rolename=roles.get(i).getRolename();
				roles.get(i).setRolename(rolename+"("+depname+")");
			}			
			int i=roleMapper.addRole(roles);
			if (i!=roles.size()) {
				throw new RuntimeException("添加角色信息时失败");
			}
			for (String roleid : roleids) {
				boolean result=this.addPermissionForRole(roleid, permissionids);
				if (!result) {
					throw new RuntimeException("为角色分配权限时失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;	
	}
	@Override
	public int getRoleCount(String departmentid) {
		try {
			return roleMapper.getRoleCount(departmentid);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<Role> getRoleNameAndIdByDepartid(String departmentid) throws Exception {
		List<Role> roles=new ArrayList<>();
		try {
			roles=roleMapper.getRoleNameAndIdByDepartid(departmentid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
	}

	/**
	 * 组合查询用户
	 * @param condition 
	 * 					departmentid:部门编号
	 * 					rolename:角色名
	 * 					rolestatus：角色状态
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean<Role> getRoleByCondition(Map<String, Object> condition, int currentPage, int currentCount) {
		PageBean<Role> pageBean=new PageBean<>();
		try {
			int totalCount=roleMapper.getRoleCountByCondition(condition);
			pageBean.setTotalCount(totalCount);
			int rowBegin=(currentPage - 1) *currentCount;
			pageBean.setCurrentPage(currentPage);
			pageBean.setCurrentCount(currentCount);
			int totalPage=(totalCount - 1) / currentCount + 1;
			pageBean.setTotalPage(totalPage);
			List<Role> roles=roleMapper.getRoleByCondition(condition, rowBegin, currentCount);
			pageBean.setProductList(roles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageBean;
	}

	@Override
	public boolean updatePermissioninfoForRole(String roleid, List<String> permissionids) throws Exception {
		List<String> roleids=new ArrayList<>();
		roleids.add(roleid);
		roleMapper.deleteRolePermissionByRoleid(roleids);
		int i=roleMapper.addPermissionForRole(roleid, permissionids);
		if (i==permissionids.size()) {
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean updateRoleStatus(String roleid, String rolestatus) throws Exception {
		roleMapper.updateRoleStatus(roleid, rolestatus);
		return true;
	}

	@Override
	public boolean addRoleinfo(Role role) throws Exception {
		String roleid=UUIDUtil.getUUID();
		role.setRoleid(roleid);
		role.setDatetime(new Date());
		if(role.getDescription() == null || "".equals(role.getDescription())){
			role.setDescription("无");
		}
		roleMapper.addRoleinfo(role);
		return true;
	}

	@Override
	public List<Map<String, Object>> getisHavePermission(String roleid,String scope) throws Exception {
		List<Map<String, Object>> allPermission=permissionMapper.getPermissionTree(scope);
		List<String> haspermissionids=roleMapper.getPermissionIDbyRole(roleid);
		for(int i=0;i<allPermission.size();i++){
			for (String permissionid : haspermissionids) {
				if (permissionid.equals(allPermission.get(i).get("permissionid"))) {
					allPermission.get(i).put("checked", true);
				}
			}
			}
		return allPermission;
	}


}
