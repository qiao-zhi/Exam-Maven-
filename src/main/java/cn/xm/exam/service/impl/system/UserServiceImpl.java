package cn.xm.exam.service.impl.system;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.system.Permission;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.mapper.system.RoleMapper;
import cn.xm.exam.mapper.system.UserMapper;
import cn.xm.exam.service.system.UserService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.utils.ValidateCheck;


@Service("userService")
public class UserServiceImpl implements UserService {

	private  final String dianchangid="001";
	private final String FACTORY_SYSTEM_MANAGER_SCOPE="systemmanager:factory"; 
	private final String DEFAULT_PASSWORD="123456";
	private final String DEFAULT_ISUSE="1";
	@Resource
	private UserMapper userMapper;
	@Resource
	private RoleMapper roleMapper;

	
	@Override
	public User getUserByUseridcard(String useridcard) {
		if (ValidateCheck.isNull(useridcard)) {
			return null;
		}
		User user=null;
		try {
			user= userMapper.getUserByUseridcard(useridcard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	
	@Override
	public Set<String> getRoleByUserid(String userid) {
		if (ValidateCheck.isNull(userid)) {
			return null;
		}
		Set<String> role=null;
		try {
			role= userMapper.getRoleByUserid(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}
	

	@Override
	public Set<String> getPermissionByUserid(String userid) {
		if (ValidateCheck.isNull(userid)) {
			return null;
		}
		Set<String> function =null;
		try {
			function= userMapper.getPermissionByUserid(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return function;
	}

	@Transactional
	@Override
	public boolean deleteUserById(List<String> userids) {
		if (userids==null) {
			return false;
		}
		try {
			int i= userMapper.deleteUserById(userids);		
			if (i==userids.size()) {
			userMapper.deleteAllRoleFromUserByUserId(userids);
			return true;	
			}else {
				throw new RuntimeException("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean addRoleForUser(List<String> userids,List<String> roleids) {
		if (userids==null || roleids==null) {
			return false;
		}
		try {
			for (String userid : userids) {
				int i=userMapper.addRoleForUser(userid,roleids);
				if (i!=roleids.size()) {
					return false;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			return true;
		
	}


	@Override
	public boolean deleteRoleFromUser(String userid,List<String> roleids) {
		int i=0;
		try {
			i= userMapper.deleteRoleFromUser(userid,roleids);
		} catch (Exception e) {
			e.printStackTrace();
			i=-1;
		}
		if (i==(-1)) {
			return false;
		}else {
			return true;
		}
	}


	@Override
	public String getPasswordByUseridcard(String useridcard) {
		String password=null;
		try {
			password=userMapper.getPasswordByUseridcard(useridcard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}


	@Transactional
	@Override
	public boolean updateUserStatus(List<String> userids,String status) {
		try{
			int i=userMapper.updateUserStatus(userids,status);
			if (i==userids.size()) {
				return true;
			}else {
				throw new RuntimeException("修改用户状态失败");
			}
		
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	
	}


	/**
	 * 传入用户名 username、密码 password进行登录 user_type 1:员工 2：管理员
	 * @param username 用户名
	 * @param password 密码
	 * @return success:登录成功
	 * 		   error  :未知错误
	 * 		   error001:该账号不存在
	 * 		   error002：密码错误
	 * 		   error003:该账号没有任何权限，不能使用该系统，请先分配权限
	 */
	@Override
	public Map<String, Object> login(String username, String password,String user_type) {
		Map<String, Object> logininfo=new HashMap<>();
		User user=new User();
		List<Permission> permissions=new ArrayList<>();
		String initializeUrl="";
		try {
			user=userMapper.getUserByUseridcard(username);
			if (user==null) {
				logininfo.put("errorinfo", "error001");
				return logininfo;
			}
			if (!user.getPassword().equals(password)) {
				logininfo.put("errorinfo", "error002");
				return logininfo;
			}
			permissions=removeNull(userMapper.getObjectPermissionByUserid(user.getUserid()));
			if ("2".equals(user_type)) {
				if (permissions==null ||permissions.size()==0) {
					logininfo.put("errorinfo", "error003");
					return logininfo;
				}
			}		
			if ("1".equals(user_type)) {
				logininfo.put("errorinfo", "success_employee");
			}else if ("2".equals(user_type)) {	
				for (Permission permission : permissions) {
					for (Permission permissionson : permissions) {
						if (permission.getPermissionid().equals(permissionson.getParentid())&&ValidateCheck.isNotNull(permissionson.getUrl())) {
							initializeUrl=permissionson.getUrl();
							break;
						}
					}
					if (!"".equals(initializeUrl)) {
						break;
					}
				}
				logininfo.put("urlinfo", initializeUrl);
				logininfo.put("errorinfo", "success_manager");
			} 
			logininfo.put("userinfo", user);
			logininfo.put("permissioninfo", permissions);
			
		} catch (Exception e) {
			 e.printStackTrace();
			 logininfo.put("errorinfo", "error");
		}
		return logininfo;
	}


	/**
	 * 根据用户编号获取权限的对象集合 
	 * @param userid:用户编号
	 * @return 权限对象的集合
	 * @throws Exception
	 */
	@Override
	public List<Permission> getObjectPermissionByUserid(String userid) throws Exception {
			List<Permission> permissions=null;
		try {
			permissions=userMapper.getObjectPermissionByUserid(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return permissions;
	}

	/**
	 * 批量添加账号
	 * @param users 用户
	 * @param defaultRoleids 默认角色
	 * @return 是否添加成功
	 * @throws Exception
	 */
	@Transactional
	@Override
	public boolean addUser(List<User> users, List<String> defaultRoleids) throws Exception {
		List<String> uuids=new ArrayList<>();
		try {
			for (int i = 0; i < users.size(); i++) {
				String uuid=UUIDUtil.getUUID();
				uuids.add(uuid);
				users.get(i).setUserid(uuid);
			}
			int i=userMapper.addUser(users);
			if (i!=users.size()) {
				throw new RuntimeException("账户添加失败");
			}
			this.addRoleForUser(uuids, defaultRoleids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}


	/**
	 * 根据部门编号获取该部门下的所有未注册系统账号的员工
	 * @param departmentid 部门编号
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<EmployeeIn> getEmployeeInWithoutUserByDepartId(String departmentid) throws Exception {
		List<EmployeeIn> employeeIns=new ArrayList<>();
		try {
			employeeIns=userMapper.getEmployeeInWithoutUserByDepartId(departmentid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeIns;
	}


	/**
	 * 根据部门编号获取该部门下的所有系统用户,带分页
	 * @param departmentid 部门编号
	 * @param currentPage 当前页码
	 * @param currentCount 每页显示的总条数
	 * @return 用户对象的集合
	 * @throws Exception
	 */
	@Override
	public PageBean<User> getUserByDepartId(String departmentid, int currentPage, int currentCount) throws Exception {
		PageBean<User> pageBean=new PageBean<>();
		Map<String, Object> condition=new HashMap<>();
		try {
			condition.put("departmentid", departmentid);
			int totalCount=userMapper.getCountByCondition(condition);
			pageBean.setTotalCount(totalCount);
			int rowBegin=(currentPage - 1) *currentCount;
			pageBean.setCurrentPage(currentPage);
			pageBean.setCurrentCount(currentCount);
			int totalPage=(totalCount - 1) / currentCount + 1;
			pageBean.setTotalPage(totalPage);
			List<User> user=userMapper.getUserByDepartId(departmentid, rowBegin, currentCount);
			pageBean.setProductList(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageBean;
	}


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
	@Override
	public PageBean<User> getUserByCondition(Map<String, Object> condition,int currentPage,int currentCount) throws SQLException {
		PageBean<User> pageBean=new PageBean<>();
		try {
			int totalCount=userMapper.getLikeCountByCondition(condition);
			pageBean.setTotalCount(totalCount);
			int rowBegin=(currentPage - 1) *currentCount;
			pageBean.setCurrentPage(currentPage);
			pageBean.setCurrentCount(currentCount);
			int totalPage=(totalCount - 1) / currentCount + 1;
			pageBean.setTotalPage(totalPage);
			List<User> user=userMapper.getUserByCondition(condition, rowBegin, currentCount);
			pageBean.setProductList(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageBean;
	}


	/**
	 * 根据用户编号 删除用户的所有角色
	 * @param userid 用户编号
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deleteAllRoleFromUserByUserId(List<String> userids) throws Exception {
		try {
			userMapper.deleteAllRoleFromUserByUserId(userids);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * 更新用户的角色信息
	 * @param userid 用户编号
	 * @param roleid 角色编号集合
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@Override
	public boolean updateRoleinfoForUser(String userid, List<String> roleids) throws Exception {
		List<String> userids=new ArrayList<>();
		userids.add(userid);
		try {
			userMapper.deleteAllRoleFromUserByUserId(userids);
			userMapper.addRoleForUser(userid, roleids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 根据登录用户的所属部门及其权限获取相应的部门树
	 * 厂级用户管理权限：systemmanager:factory
	 * @param departmentid 部门编号	
	 * @param permissioncode 权限code集合
	 * @return list：map ：
	 * 				      departmentid：部门编号
	 * 				      updepartmentid：上级部门编号
	 * 				      departmentname：部门名字
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> getDepartTreeByPermAndDepartId(String departmentid)
			throws Exception {
		List<Map<String, Object>> DepTreeOfSystem =userMapper.getDepartTreeByDepartId(departmentid);
		return DepTreeOfSystem;
	}
	
	/**
	 * 判断是否拥有厂级系统管理权限
	 * @param permission 需要的权限
	 * @param permissioncodes 用户拥有的权限
	 * @return
	 */
		public boolean havePermissionCode(List<String> permissioncodes){
			if (permissioncodes==null || permissioncodes.size()==0) {
				return false;
			}
			for (String permissioncode : permissioncodes) {
				if (permissioncode.equals(FACTORY_SYSTEM_MANAGER_SCOPE)) {
					return true;
				}
			}
			return false;
		}


	/**
	 * 修改用户密码
	 * @param useridcard 身份证号
	 * @param password 密码
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updatePassword(String useridcard, String password) throws Exception {
		if (useridcard==null || password==null) {
			return false;
		}
		int i=userMapper.updatePassword(useridcard, password);
		if (i==1) {
			return true;
		}
		else {
			 return false;
		}
	}

	/**
	 * 批量添加账号
	 * @param employeeids 员工编号
	 * @return 是否添加成功
	 * @throws Exception
	 */
	@Override
	public boolean addUsers(List<String> employeeids) throws Exception {
		List<EmployeeIn> employeeIns=userMapper.getEmployeeinsById(employeeids);
		List<User> users=new ArrayList<>();
		for (EmployeeIn employeeIn : employeeIns) {
			User user=new User();
			
			user.setDatatime(new Date());
			user.setUserid(UUIDUtil.getUUID());
			user.setUseridcard(employeeIn.getIdcode());
			user.setPassword(DEFAULT_PASSWORD);
			user.setUsername(employeeIn.getName());
			user.setDepartmentname(employeeIn.getDepartmentname());
			user.setEmployeeid(employeeIn.getEmployeeid());
			user.setUserphoto(employeeIn.getPhoto());
			user.setIsuse(DEFAULT_ISUSE);
			user.setDepartmentid(employeeIn.getDepartmentid());
			user.setPhone(employeeIn.getPhone());
			users.add(user);
		}

			int i=userMapper.addUser(users);
			if (i==employeeids.size()) {
				return true;
			}else {
				return false;
			}
	
	}


	@Override
	public List<EmployeeIn> getEmployeeBynameAnddepartmentid(String departmentid, String name) throws Exception {
		return userMapper.getEmployeeBynameAnddepartmentid(departmentid, name);
	}


	@Override
	public boolean updateUserInfo(String useridcard) throws Exception {
		String employeeid=userMapper.getEmployeeidByIdcard(useridcard);
		EmployeeIn employeeIn=userMapper.getEmployeeByEmployeeid(employeeid);
		String userid =userMapper.getUseridByIdcard(useridcard);
		User currentuser=userMapper.getUserByUseridcard(useridcard);
		if (!currentuser.getDepartmentid().equals(employeeIn.getDepartmentid())) {
			List<String> userids=new ArrayList<>();
			userids.add(userid);
			userMapper.deleteAllRoleFromUserByUserId(userids);
		}
		User user=new User();
		user.setUserid(userid);
		user.setUseridcard(employeeIn.getIdcode());
		user.setUsername(employeeIn.getName());
		user.setDepartmentname(employeeIn.getDepartmentname());
		user.setDepartmentid(employeeIn.getDepartmentid());
		user.setUserphoto(employeeIn.getPhoto());
		user.setPhone(employeeIn.getPhone());
		userMapper.updateUserInfo(user);
		return true;
	}
	
	//去除list中的null元素
		public List<Permission> removeNull(List<Permission> list){
			List<String> list2=new ArrayList<>();
			list2.add(null);
			list.removeAll(list2);
			return list;
		}
}
