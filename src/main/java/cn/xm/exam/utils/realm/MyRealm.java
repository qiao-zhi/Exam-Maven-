package cn.xm.exam.utils.realm;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.system.UserService;
public class MyRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;
	
	/**
	 * 为当前登陆成功的用户授予角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userid=((User)(principals.getPrimaryPrincipal())).getUserid();		
		SimpleAuthorizationInfo simpleAuthorizationInfo=null;
		Set<String> permissions;
		try {
			simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			permissions = userService.getPermissionByUserid(userid);
			if (permissions!=null) {
				simpleAuthorizationInfo.setStringPermissions(permissions);
			}else {		
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return simpleAuthorizationInfo;
	}

	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String useridcard=(String)token.getPrincipal();	
		User user = new User();
		try {
			user = userService.getUserByUseridcard(useridcard);
		} catch (Exception e) {	
			e.printStackTrace();
		}
			AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());			
			return authenticationInfo;
		
	}
	
	//清除缓存
	public void clearCache(){
		PrincipalCollection principalCollection=SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principalCollection);
	}

}
