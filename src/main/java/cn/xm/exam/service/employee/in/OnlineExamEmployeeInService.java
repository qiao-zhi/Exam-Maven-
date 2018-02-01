package cn.xm.exam.service.employee.in;

import java.util.Map;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.employee.in.EmplyinBreakrules;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.utils.PageBean;

/**   
*    
* 项目名称：Exam   
* 类名称：OnlineExamEmployeeInService   
* 类描述： 在线考试员工界面个人中心的service  
* 创建人：Leilong  
* 创建时间：2017年11月1日 下午10:09:41   
* @version    
*    
*/
public interface OnlineExamEmployeeInService {
	
	
	/**
	 * 根据员工ID查询员工的基本信息
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	public EmployeeIn getOnlineExamEmployeeInInfoById(String employeeId) throws Exception;
	
	/**
	 * 修改员工的基本信息
	 * @param employeeIn
	 * @return
	 * @throws Exception
	 */
	public boolean updateOnlineExamEmployeeInInfo(EmployeeIn employeeIn) throws Exception;
	
	/**
	 * 根据员工ID查询用户表中的相关信息
	 * 登录密码
	 * @param employeeInId
	 * @return
	 * @throws Exception
	 */
	public User getOnlineExamUserInfoByEmployeeInId(String employeeInId) throws Exception;
	
	/**
	 * 根据用户ID修改用户的登录密码
	 * map中包括用户ID，用户的新密码
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean updateOnlineExamUserInfo(Map<String,Object> map) throws Exception;
	
	/**
	 * 根据时间段查询在线考试员工的违章信息
	 * @param currentPage
	 * @param currentTotal
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public PageBean<EmplyinBreakrules> getOnlineEmployeeBreakInfoByCondition(int currentPage, int currentTotal,Map<String,Object> condition) throws Exception;

	
}
