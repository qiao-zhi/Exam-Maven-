package cn.xm.exam.action.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.system.Permission;
import cn.xm.exam.bean.system.Role;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.system.PermissionService;
import cn.xm.exam.service.system.RoleService;
import cn.xm.exam.service.system.UserService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ValidateCheck;

@Controller
@Scope("prototype")
public class RoleAction extends ActionSupport {

	private final String SESSION_USER_INFO="userinfo"; //用户信息的session名
	private final String SESSION_PERMISSION_INFO="permissioninfo"; //权限信息的session名
	private final String SESSION_ERROR="error_session";//session错误的map名
	private final String FACTORY_SYSTEM_MANAGER_SCOPE="systemmanager:factory"; //厂级系统管理的权限code
	private final String ELFACTORY_ID="01";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private PermissionService permissionService;
	
	private String currentPage;
	private String currentCount;
	private String departmentid;
	private String rolename;//组合查询_角色名
	private String rolestatus;//组合查询_角色状态
	private String roleid;
	private Role role;
	private String role_permissionids;
	
	
	private List<String> configPermissionForRole;
	private List<String> roleids;
	private String departmentids;
	private String permissionids;
	private Map<String, Object> result; //用来封装结果为json
	private Map<String, Object> condition;//用来封装查询条件
	
	public String getRolesByDepartmentId(){
		result=new HashMap<>();
		User user=new User();
		try {
			user=getUserInfoFromSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (departmentid==null || "".equals(departmentid)) {
			departmentid=user.getDepartmentid();
		}	
		condition=generationCondition();
		PageBean<Role> pageBean=new PageBean<>();
		try {
			pageBean=roleService.getRoleByDepartId(departmentid,toInteger(currentPage), toInteger(currentCount));
			result.put("pageBean", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return SUCCESS;
	}
	
	/**
	 * 组合查询角色
	 * @return
	 */
	public String getRoleByCondition(){
		result=new HashMap<>();
		User user =new User();
		condition=generationCondition();
		try {
			user=getUserInfoFromSession();
			String departmentid=user.getDepartmentid();
			List<String>  permissioncodes=removeNull(user.getPermissions());
			if (havePermissionCode(permissioncodes)) {
				departmentid=ELFACTORY_ID;
			}			
			condition.put("departmentid", departmentid);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		PageBean<Role> pageBean=new PageBean<>();
		try {
			pageBean=roleService.getRoleByCondition(condition, toInteger(currentPage), toInteger(currentCount));
			result.put("pageBean", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 获取启用的权限信息，用于 配置权限
	 * @return
	 */
	public String getIsUsePermission(){
		result=new HashMap<>();
		List<Permission> permissions=new ArrayList<>();
		User user =new User();
		try {
			user=getUserInfoFromSession();
			List<String> permissioncodes=removeNull(user.getPermissions());
			if (havePermissionCode(permissioncodes) || ELFACTORY_ID.equals(user.getDepartmentid())) {
				permissions=permissionService.getAllIsusePermission();
			}else {
				permissions=permissionService.selectDepartIsuserPermission();
			}		
			result.put("permissions", permissions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 配置角色：修改用户的角色信息
	 * @return
	 */
	public String updatePermissioninfoForRole(){
		try {
			String[] permissionid=role_permissionids.split(",");
			List<String> configPermissionForRole=Arrays.asList(permissionid);
			roleService.updatePermissioninfoForRole(roleid, configPermissionForRole);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 根据角色编号删除角色
	 * @return
	 */
	public String deleteRoleById(){
		try {
			roleService.deleteRoleInfo(roleids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 关闭/开启角色
	 * @return
	 */
	public String updateRoleStatus(){
		try {
			roleService.updateRoleStatus(roleid, rolestatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String updateRoleInfo(){
		try {
			roleService.updateRoleInfo(role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 判断是否拥有厂级系统管理权限
	 * @param permission 需要的权限
	 * @return
	 */
	public boolean havePermissionCode(List<String> permissioncodes){
		for (String permissioncode : permissioncodes) {
			if (permissioncode.equals(FACTORY_SYSTEM_MANAGER_SCOPE)) {
				return true;
			}
		}
		return false;
	}
	
	private int toInteger(String string){
		int i=Integer.parseInt(string);
		return i;
	}
	
	//添加角色信息
	public String addRoleinfo(){
		try {
			roleService.addRoleinfo(role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//批量添加角色
	public String addMoreRole(){
		List<Role> roles=new ArrayList<>();
		String[] departmentid=departmentids.split(",");
		String[] permissions=permissionids.split(",");
		List<String> department=Arrays.asList(departmentid);
		List<String> permission=Arrays.asList(permissions);
		try {
			for (int i=0;i<department.size();i++) {
				Role temprole=new Role();
				temprole.setDepartmentid(department.get(i));
				temprole.setRolename(role.getRolename());
				if(role.getDescription() == null || "".equals(role.getDescription())){
					role.setDescription("无");
				}
				temprole.setDescription(role.getDescription());
				
				temprole.setRolestatus(role.getRolestatus());
				temprole.setDatetime(new Date());
				roles.add(temprole);
			}
			roleService.addMoreRole(roles, permission);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getisHavePermission(){
		result=new HashMap<>();
		User user =new User();
		user=getUserInfoFromSession();
		List<Map<String, Object>> allPermission=new ArrayList<>();
		List<String> permissioncodes=removeNull(user.getPermissions());
		if (havePermissionCode(permissioncodes) || ELFACTORY_ID.equals(user.getDepartmentid())) {
			try {
				allPermission=roleService.getisHavePermission(roleid, "factory");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				allPermission=roleService.getisHavePermission(roleid, "-1");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		result.put("permissionTree", allPermission);
		return SUCCESS;
	}
	
	//组装查询条件
		private Map<String,Object> generationCondition(){	
			condition=new HashMap<>();
			//对当前页信息进行设置
			if (currentPage == null || "".equals(currentPage.trim())) {
				currentPage="1";
			}
			//对当前页显示的信息进行设置
			if (currentCount == null || "".equals(currentCount.trim())) {
				currentCount="8";
			}
			if(ValidateCheck.isNotNull(departmentid)){
				condition.put("departmentid", departmentid);
			}
			
			if(ValidateCheck.isNotNull(rolename)){
				condition.put("rolename", rolename);
			}
			if(ValidateCheck.isNotNull(rolestatus)){
				condition.put("rolestatus", rolestatus);
			}
			
			return condition;
		}
	
	public User getUserInfoFromSession(){
		HttpSession session =request.getSession();
		User user=(User) session.getAttribute(SESSION_USER_INFO);
		return user;
	}
	//从session中获取权限信息
		public List<Permission> getPermissioninfoFromSession(){
			HttpSession session =request.getSession();
			@SuppressWarnings("unchecked")
			List<Permission> permissioninfo=(List<Permission>) session.getAttribute(SESSION_PERMISSION_INFO);
			return permissioninfo;
		}
	//去除list中的null元素
	public List<String> removeNull(List<String> list){
		List<String> list2=new ArrayList<>();
		list2.add(null);
		list.removeAll(list2);
		return list;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(String currentCount) {
		this.currentCount = currentCount;
	}

	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getRolestatus() {
		return rolestatus;
	}

	public void setRolestatus(String rolestatus) {
		this.rolestatus = rolestatus;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public List<String> getConfigPermissionForRole() {
		return configPermissionForRole;
	}

	public void setConfigPermissionForRole(List<String> configPermissionForRole) {
		this.configPermissionForRole = configPermissionForRole;
	}

	public List<String> getRoleids() {
		return roleids;
	}

	public void setRoleids(List<String> roleids) {
		this.roleids = roleids;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getDepartmentids() {
		return departmentids;
	}

	public void setDepartmentids(String departmentids) {
		this.departmentids = departmentids;
	}

	public String getPermissionids() {
		return permissionids;
	}

	public void setPermissionids(String permissionids) {
		this.permissionids = permissionids;
	}

	public String getRole_permissionids() {
		return role_permissionids;
	}

	public void setRole_permissionids(String role_permissionids) {
		this.role_permissionids = role_permissionids;
	}
	
	
	
}
