package cn.xm.exam.action.system;

import java.util.ArrayList;
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
import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.system.PermissionService;
import cn.xm.exam.service.system.RoleService;
import cn.xm.exam.service.system.UserService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ValidateCheck;

@Controller
@Scope("prototype")
public class PermissionAction extends ActionSupport {

	private final String SESSION_USER_INFO="userinfo"; //用户信息的session名
	private final String SESSION_PERMISSION_INFO="permissioninfo"; //权限信息的session名
	private final String SESSION_ERROR="error_session";//session错误的map名
	private final String FACTORY_SYSTEM_MANAGER_SCOPE="systemmanager:factory"; //厂级系统管理的权限code
	private final String ELFACTORY_ID="01";
	private final String DEFAULT_MENUTOP="000";
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
	
	private Map<String, Object> result; //用来封装结果为json
	private Map<String, Object> condition;//用来封装查询条件
	private String currentPage; //当前页
	private String currentCount;//每页显示的记录数
	private String permissionname;
	private String permissionstatus;
	private String permissionid;
	private String topmenuid;
	/**
	 * 组合查询权限
	 * @return
	 */
	public String getPermissionByCondition(){
		result=new HashMap<>();
		PageBean<Permission> permissions=new PageBean<>();
		User user=new User();
		user=getUserInfoFromSession();
		List<String> permissioncodes=user.getPermissions();
		condition=generationCondition();
		if (havePermissionCode(removeNull(permissioncodes)) || user.getDepartmentid().equals(ELFACTORY_ID)) {
			try {
				permissions=permissionService.getPermissioninfoByCondition(condition, toInteger(currentPage), toInteger(currentCount));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				condition.put("el_factory_system", "scope");
				permissions=permissionService.getPermissioninfoByCondition(condition, toInteger(currentPage), toInteger(currentCount));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		result.put("pageBean", permissions);
		return SUCCESS;
	}

	/**
	 * 获取顶级菜单
	 * @return
	 */
	public String getmenuTreeInfo(){
		result=new HashMap<>();
		List<Map<String, Object>> MenuTree=new ArrayList<>();
		try {
			MenuTree=permissionService.getMenutop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("MenuTree", MenuTree);
		return SUCCESS;
	}
	
	//根据顶级菜单查询其所属权限
	public String getPermissionBymenutop(){	
		if (topmenuid==null || "".equals(topmenuid)) {
			topmenuid=DEFAULT_MENUTOP;
		}
		result=new HashMap<>();
		PageBean<Permission> permissions=new PageBean<>();
		User user=new User();
		user=getUserInfoFromSession();
		List<String> permissioncodes=user.getPermissions();
		condition=generationCondition();
		if (havePermissionCode(removeNull(permissioncodes)) || user.getDepartmentid().equals(ELFACTORY_ID)) {
			try {
				permissions=permissionService.getNextPermissionById(topmenuid, toInteger(currentPage), toInteger(currentCount));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				permissions=permissionService.getNextPermissionById_Without_ELSCOPE(topmenuid,  toInteger(currentPage), toInteger(currentCount));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		result.put("pageBean", permissions);
		return SUCCESS;
	}
	
	public String updatePermissionStatus(){
		try {
			permissionService.updatePermissionStatus(permissionid, permissionstatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getPermissionTree(){
		result=new HashMap<>();
		List<Map<String, Object>> permissiontree=new ArrayList<>();
		User user=new User();
		user=getUserInfoFromSession();
		List<String> permissioncodes=user.getPermissions();
		if (havePermissionCode(removeNull(permissioncodes))) {
			try {
				permissiontree=permissionService.getPermissionTree("factory");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				permissiontree=permissionService.getPermissionTree("0");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		result.put("permissionTree", permissiontree);
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
		if(ValidateCheck.isNotNull(permissionname)){
			condition.put("permissionname", permissionname);
		}
		
		if(ValidateCheck.isNotNull(permissionstatus)){
			condition.put("permissionstatus", permissionstatus);
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
	public String getPermissionname() {
	return permissionname;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}

	public String getPermissionstatus() {
		return permissionstatus;
	}

	public void setPermissionstatus(String permissionstatus) {
		this.permissionstatus = permissionstatus;
	}

	private int toInteger(String string){
		int i=Integer.parseInt(string);
		return i;
	}

	public String getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(String permissionid) {
		this.permissionid = permissionid;
	}

	public String getTopmenuid() {
		return topmenuid;
	}

	public void setTopmenuid(String topmenuid) {
		this.topmenuid = topmenuid;
	}	
}
