package cn.xm.exam.service.impl.employee.in;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.employee.in.EmplyinBreakrules;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.mapper.employee.in.EmployeeInMapper;
import cn.xm.exam.mapper.employee.in.custom.OnlineExamEmployeeInCustomMapper;
import cn.xm.exam.service.employee.in.OnlineExamEmployeeInService;
import cn.xm.exam.utils.PageBean;

/**   
*    
* 项目名称：Exam   
* 类名称：OnlineExamEmployeeInServiceImpl   
* 类描述： 内部员工个人中心Service的实现类 
* 创建人：Leilong  
* 创建时间：2017年11月1日 下午10:25:26      
* @version    
*    
*/
@Service
@Transactional
public class OnlineExamEmployeeInServiceImpl implements OnlineExamEmployeeInService {
	
	@Resource
	private EmployeeInMapper employeeInMapper;
	@Resource
	private OnlineExamEmployeeInCustomMapper onlineExamEmployeeInCustomMapper;
	
	/**
	 * 根据员工ID查询员工的基本信息
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	public EmployeeIn getOnlineExamEmployeeInInfoById(String employeeId) throws Exception {
		return employeeInMapper.selectByPrimaryKey(employeeId);
	}

	/**
	 * 修改员工的基本信息
	 * @param employeeIn
	 * @return
	 * @throws Exception
	 */
	public boolean updateOnlineExamEmployeeInInfo(EmployeeIn employeeIn) throws Exception {
		int isUpdate = employeeInMapper.updateByPrimaryKeySelective(employeeIn);
		return isUpdate>0 ? true : false;
	}

	/**
	 * 根据员工ID查询用户表中的相关信息
	 * 登录密码
	 * @param employeeInId
	 * @return
	 * @throws Exception
	 */
	public User getOnlineExamUserInfoByEmployeeInId(String employeeInId) throws Exception {
		
		return onlineExamEmployeeInCustomMapper.getOnlineExamUserInfoByEmployeeInId(employeeInId);
	}

	/**
	 * 根据用户ID修改用户的登录密码
	 * map中包括用户ID，用户的新密码
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean updateOnlineExamUserInfo(Map<String, Object> map) throws Exception {
		int isUpate = onlineExamEmployeeInCustomMapper.updateOnlineExamUserInfo(map);
		return isUpate>0 ? true:false;
	}
	
	/**
	 * 根据时间段查询在线考试员工的违章信息,分页显示
	 * @param currentPage
	 * @param currentTotal
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean<EmplyinBreakrules> getOnlineEmployeeBreakInfoByCondition(int currentPage, int currentTotal,
			Map<String, Object> condition) throws Exception {
		
		PageBean<EmplyinBreakrules> pageBean = new PageBean<EmplyinBreakrules>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentTotal);
		int totalCount = onlineExamEmployeeInCustomMapper.getOnlineEmployeeBreakInfoCountByCondition(condition);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(totalCount*1.0/currentTotal);
		pageBean.setTotalPage(totalPage);
		int index = (currentPage-1)*currentTotal;
		condition.put("index", index);
		condition.put("currentCount",currentTotal);
		List<EmplyinBreakrules> productList = onlineExamEmployeeInCustomMapper.getOnlineEmployeeBreakInfoByCondition(condition);
		pageBean.setProductList(productList);
		return pageBean;
	}

}
