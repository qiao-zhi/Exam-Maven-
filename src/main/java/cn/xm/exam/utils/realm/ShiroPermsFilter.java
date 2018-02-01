package cn.xm.exam.utils.realm;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;





public class ShiroPermsFilter extends PermissionsAuthorizationFilter {


	public static void main(String[] args) {
		System.out.println("jinlaile");
	}
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestedWith = httpServletRequest.getHeader("X-Requested-With");
        if (StringUtils.isNotEmpty(requestedWith) &&
                StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定格式数据
        	httpServletResponse.sendError(httpServletResponse.SC_FORBIDDEN);        	
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
        } else {//如果是普通请求进行重定向
            httpServletResponse.sendRedirect("/unauthorized.jsp");
        }
        return false;
    }
}
