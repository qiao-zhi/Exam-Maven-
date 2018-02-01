package cn.xm.exam.mapper.employee.in.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.employee.in.EmplyinBreakrules;
import cn.xm.exam.bean.system.User;

/**   
*    
* 项目名称：Exam   
* 类名称：OnlineExamEmployeeInCustomMapper   
* 类描述： 内部员工个人中心相关操作的mapper
* 创建人：Leilong  
* 创建时间：2017年11月1日 下午10:31:49     
* @version    
*    
*/
public interface OnlineExamEmployeeInCustomMapper {
	 
	/**
	 * 根据用户ID从user表中查询相关信息
	 * @param employeeInId
	 * @return
	 * @throws SQLException
	 */
	public User getOnlineExamUserInfoByEmployeeInId(String employeeInId) throws SQLException;
	
	/**
	 * 根据用户ID修改用户的登录密码
	 *  map中包括用户ID，用户的新密码
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateOnlineExamUserInfo(Map<String, Object> map) throws SQLException;
	
	/**
	 * 根据条件查询单个员工的违章信息用于分页显示
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<EmplyinBreakrules> getOnlineEmployeeBreakInfoByCondition(Map<String,Object> condition) throws SQLException;

	/**
	 * 根据条件查询统计单个员工的违章信息条数
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int getOnlineEmployeeBreakInfoCountByCondition(Map<String,Object> condition) throws SQLException;
}
