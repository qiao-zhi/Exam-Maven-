package cn.xm.exam.action.system;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.system.Permission;
import cn.xm.exam.bean.system.Role;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.system.RoleService;
import cn.xm.exam.service.system.UserService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ValidateCheck;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {

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
	
	private Map<String, Object> result; //用来封装结果为json
	private Map<String, Object> condition;//用来封装查询条件
	private String currentPage; //当前页
	private String currentCount;//每页显示的记录数
	private String departmentid;
	private String username;
	private String password;
	private String useridcard;
	private String roleid;
	private String userid;//用户id
	private String user_type;//用户登录类型 1：员工 2：管理员
	private String employeename;//员工姓名
	private String isRememberme;
	
	private List<String> userids;//checkbox 的 用户id
	private List<String> employeeids;
	private List<String> configRoleForUser; //配置角色 的 角色编号集合
	

	/**
	 * 登录
	 * @return
	 */
	public String login(){
		Subject currentUser=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username, password);
//		boolean a=currentUser.isPermitted(FACTORY_SYSTEM_MANAGER_SCOPE);
		result=new HashMap<>();
		Map<String, Object> logininfo=new HashMap<>();
		try {
			logininfo=userService.login(username, password,user_type);
			String login_result=(String) logininfo.get("errorinfo");		
			result.put("login_result", login_result);	
			result.put("user_type", user_type);
			if ("2".equals(user_type)) {//初始化默认跳转的页面
				String login_url=(String) logininfo.get("urlinfo");
				result.put("login_url", login_url);
			}
			if (("error").equals(login_result.substring(0, 5))) {
				return SUCCESS;
			}
		if ("yes".equals(isRememberme)) {
			String usernameandpassword=username+","+password;
			Cookie cookie=new Cookie("logininfo", usernameandpassword);
			cookie.setMaxAge(30*24*60*60);
			response.addCookie(cookie);
		}else {
			Cookie[] cookies=request.getCookies();
			for (Cookie cookie : cookies) {
				String name=cookie.getName();
				if ("logininfo".equals(name)) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
			//shiro的使用需要先把用户的信息放入session 需要先用shiro的方法进行登录 但是登录方法已经写好 这里在登录成功之后用shiro的方法再登录一次
			try {
				currentUser.login(token);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			User user=(User) logininfo.get("userinfo");
			@SuppressWarnings("unchecked")
			List<Permission> permissions=(List<Permission>) logininfo.get("permissioninfo");
			HttpSession session=request.getSession();
			user.setLogintime(new Date());
			session.setAttribute(SESSION_USER_INFO, user);
			session.setAttribute(SESSION_PERMISSION_INFO, permissions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
		//获取session中的用户信息
		public String getUsernameBySession(){
		result=new HashMap<>();
		User user=new User();
		try {
			user=getUserInfoFromSession();
		} catch (Exception e) {
			e.printStackTrace();
			result.put(SESSION_ERROR, "session time out");
			return SUCCESS;
		}
		result.put("username", user.getUsername());
		return SUCCESS;
	}
	/**
	 * 根据部门编号 获取未注册账户的员工
	 * @return
	 */
	@RequiresPermissions("systemmanager:user")
	public String getEmployeeWithoutUser(){
		Subject currentUser=SecurityUtils.getSubject();
		currentUser.isPermitted("systemmanager:user");
		result=new HashMap<>();
		List<EmployeeIn> employeeIns=new ArrayList<>();
		try {
			employeeIns=userService.getEmployeeInWithoutUserByDepartId(departmentid);
			result.put("employees", employeeIns);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 根据员工编号 添加用户
	 * @return
	 */
	@RequiresPermissions("systemmanager:user")
	public String addUser(){
		try {
			userService.addUsers(employeeids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 根据部门编号查询该部门下的用户
	 * @return
	 */
	@RequiresPermissions("systemmanager:user")
	public String getUserByDepartId(){
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
		PageBean<User> pageBean=new PageBean<>();
		try {
			pageBean=userService.getUserByDepartId(departmentid,toInteger(currentPage), toInteger(currentCount));
			result.put("pageBean", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;	
	}
	/**
	 * 组合查询用户
	 * @return
	 */
	@RequiresPermissions("systemmanager:user")
	public String getUserByCondition(){
		result=new HashMap<>();
		User user =new User();
		condition=generationCondition();
		try {
			user=getUserInfoFromSession();
			String departmentid=user.getDepartmentid();
			List<String>  permissioncodes=user.getPermissions();
			if (havePermissionCode(permissioncodes)) {
				departmentid=ELFACTORY_ID;
			}			
			condition.put("departmentid", departmentid);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		PageBean<User> pageBean=new PageBean<>();
		try {
			pageBean=userService.getUserByCondition(condition, toInteger(currentPage), toInteger(currentCount));
			result.put("pageBean", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化组合查询的角色下拉列表框
	 * @return
	 */
	
	public String getRolenameAndRoleidByDepartid(){
		result=new HashMap<>();
		List<Role> roles=new ArrayList<>();
     	User user=new User();
		try {
			user = getUserInfoFromSession();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			roles=roleService.getRoleNameAndIdByDepartid(user.getDepartmentid());
			result.put("roles", roles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
	/**
	 * 根据用户编号删除用户
	 * @return
	 */
	@RequiresPermissions("systemmanager:user")
	public String deleteUsersById(){
		if (userids==null || userids.size()==0) {
			userids=new ArrayList<>();
			userids.add(userid);
		}
		try {
			userService.deleteUserById(userids) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 获取启用的角色信息，用于 配置角色
	 * @return
	 */
	@RequiresPermissions("systemmanager:user")
	public String getIsUseRoleByDepartId(){
		result=new HashMap<>();
		List<Role> roles=new ArrayList<>();
		try {
			roles=roleService.getIsUseRoleByDepartId(departmentid);
			result.put("roles", roles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 配置角色：修改用户的角色信息
	 * @return
	 */
	@RequiresPermissions("systemmanager:user")
	public String updateRoleinfoForUser(){
		try {
			userService.updateRoleinfoForUser(userid, configRoleForUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 获取部门树
	 * @return
	 */
	
	public String getDepTreeInfo(){
		result=new HashMap<>();
		User user=new User();
		List<String> permissioncodes=new ArrayList<>();
		try {
			user = getUserInfoFromSession();
			permissioncodes=removeNull(user.getPermissions());
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
		List<Map<String, Object>> DepTree=new ArrayList<>();
		try {
			//判断是否有厂级系统管理权限，若有则可得到全厂的部门树，若没有则可得到本部门及其所属部门的部门树
			if (permissioncodes!=null) {
				if (havePermissionCode(permissioncodes)) {
					DepTree=userService.getDepartTreeByPermAndDepartId(ELFACTORY_ID);
				}else {
					DepTree=userService.getDepartTreeByPermAndDepartId(user.getDepartmentid());
				}
			} 	
			result.put("DepTree", DepTree);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 更新密码
	 * @return
	 */
	@RequiresPermissions("systemmanager:user")
	public String updatePassword(){
		try {
			userService.updatePassword(useridcard, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//根据用户身份证号码 从员工表更新其用户信息
	@RequiresPermissions("systemmanager:user")
	public String updateUserInfo(){
		try {
			userService.updateUserInfo(useridcard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@RequiresPermissions("systemmanager:user")
	public String getEmployeeBynameAnddepartmentid(){
		result=new HashMap<>();
		List<EmployeeIn> employeeIns=new ArrayList<>();
		try {
			employeeIns=userService.getEmployeeBynameAnddepartmentid(departmentid, employeename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("employees", employeeIns);
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
		
		if(ValidateCheck.isNotNull(username)){
			condition.put("username", username);
		}
		if(ValidateCheck.isNotNull(useridcard)){
			condition.put("useridcard", useridcard);
		}
		if(ValidateCheck.isNotNull(roleid)){
			System.err.println(roleid);
			condition.put("roleid", roleid);
		}
		
		return condition;
	}

	//从session中获取用户信息
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
	
	
	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
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

	
		public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseridcard() {
		return useridcard;
	}

	public void setUseridcard(String useridcard) {
		this.useridcard = useridcard;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

		private int toInteger(String string){
			int i=Integer.parseInt(string);
			return i;
		}
		public List<String> getUserids() {
			return userids;
		}
		public void setUserids(List<String> userids) {
			this.userids = userids;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public List<String> getConfigRoleForUser() {
			return configRoleForUser;
		}
		public void setConfigRoleForUser(List<String> configRoleForUser) {
			this.configRoleForUser = configRoleForUser;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUser_type() {
			return user_type;
		}

		public void setUser_type(String user_type) {
			this.user_type = user_type;
		}
		public List<String> getEmployeeids() {
			return employeeids;
		}

		public void setEmployeeids(List<String> employeeids) {
			this.employeeids = employeeids;
		}

		public String getEmployeename() {
			return employeename;
		}

		public void setEmployeename(String employeename) {
			this.employeename = employeename;
		}


		public String getIsRememberme() {
			return isRememberme;
		}


		public void setIsRememberme(String isRememberme) {
			this.isRememberme = isRememberme;
		}	
		
		
}
