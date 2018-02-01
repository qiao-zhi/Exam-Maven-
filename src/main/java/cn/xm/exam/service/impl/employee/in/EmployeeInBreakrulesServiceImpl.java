package cn.xm.exam.service.impl.employee.in;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.employee.in.EmplyinBreakrules;
import cn.xm.exam.bean.employee.out.Blacklist;
import cn.xm.exam.mapper.employee.in.EmplyinBreakrulesMapper;
import cn.xm.exam.mapper.employee.in.custom.EmplyinBreakrulesCustomMapper;
import cn.xm.exam.mapper.employee.out.BlacklistMapper;
import cn.xm.exam.service.employee.in.EmployeeInBreakrulesService;

@Service
public class EmployeeInBreakrulesServiceImpl implements EmployeeInBreakrulesService {

	@Resource
	private EmplyinBreakrulesMapper emplyinBreakrulesMapper;
	
	@Resource
	private EmplyinBreakrulesCustomMapper emplyinBreakrulesCustomMapper;

	@Resource
	private BlacklistMapper blacklistMapper;

	/**
	 * <!-- 查询部门表的所有信息，用于初始化左侧的部门树 -->
	 */
	@Override
	public List<Map<String, Object>> initLeftDepartmentTree(String departmentId) throws SQLException {
		List<Map<String, Object>> initLeftDepartmentTree = emplyinBreakrulesCustomMapper.initLeftDepartmentTree(departmentId);
		if (initLeftDepartmentTree != null) {
			return initLeftDepartmentTree;
		} else {
			return null;
		}
	}

	/**
	 * 添加内部员工的违章记录
	 */
	@Override
	public int addEmpInBreakrules(EmplyinBreakrules emplyinBreakrules) {
		int insert = emplyinBreakrulesMapper.insert(emplyinBreakrules);
		if (insert > 0) {
			return insert;
		} else {
			return 0;
		}
	}

	/**
	 * 修改内部员工的违章记录，根据javabean修改
	 */
	@Override
	public int updateByPrimaryKeySelective(EmplyinBreakrules record) {
		int result = emplyinBreakrulesMapper.updateByPrimaryKeySelective(record);
		if (result > 0) {
			return result;
		} else {
			return 0;
		}
	}

	/**
	 * 删除内部员工的违章记录，根据违章id(违章记录表的主键)删除
	 */
	@Override
	public int deleteByPrimaryKey(Integer empinbreakid) {
		int result = emplyinBreakrulesMapper.deleteByPrimaryKey(empinbreakid);
		if (result > 0) {
			return result;
		} else {
			return 0;
		}
	}

	/**
	 * 根据部门id获取当前部门旗下的所有员工及其违章信息
	 */
	@Override
	public List<Map<String, Object>> getEmpInMsgByDepIdLeft(Map<String, Object> map) {
		List<Map<String, Object>> empInMsgByDepIdLeft = emplyinBreakrulesCustomMapper.getEmpInMsgByDepIdLeft(map);
		if (empInMsgByDepIdLeft != null) {
			return empInMsgByDepIdLeft;
		} else {
			return null;
		}
	}

	/**
	 * 根据部门id获取当前部门旗下的所有员工及其违章信息 的总条数
	 */
	@Override
	public int getEmpInMsgByDepIdLeftCount(Map<String, Object> map) {
		int count = emplyinBreakrulesCustomMapper.getEmpInMsgByDepIdLeftCount(map);
		if (count > 0) {
			return count;
		} else {
			return 0;
		}
	}

	/**
	 * <!-- 页面加载的时候把所有部门下的所有员工及其违章信息显示出来,用于页面初始化 -->
	 */
	@Override
	public List<Map<String, Object>> initPageDate(Map<String, Object> map) {
		List<Map<String, Object>> initPageDate = emplyinBreakrulesCustomMapper.initPageDate(map);
		if (initPageDate != null) {
			return initPageDate;
		} else {
			return null;
		}
	}

	/**
	 * <!-- 页面加载的时候把所有部门下的所有员工及其违章信息的 总记录数
	 */
	@Override
	public int initPageDateCount(String departmentId) {
		int count = emplyinBreakrulesCustomMapper.initPageDateCount(departmentId);
		if (count > 0) {
			return count;
		} else {
			return 0;
		}
	}

	/**
	 * <!-- #根据职工id去违章记录表中查询该内部员工的违章总积分 employeeid -->
	 */
	@Override
	public Integer selSumBreakScoreByEmpId(String employeeid) {
		Integer sumBreakScore = emplyinBreakrulesCustomMapper.selSumBreakScoreByEmpId(employeeid);
		if ((sumBreakScore!=null) && (sumBreakScore > 0)) {
			return sumBreakScore;
		} else {
			return 0;
		}
	}
	@Override
	public Integer selSumBreakScoreByEmpId(Map condition) {
		Integer sumBreakScore = emplyinBreakrulesCustomMapper.selSumBreakScoreByEmpId1(condition);
		if ((sumBreakScore!=null) && (sumBreakScore > 0)) {
			return sumBreakScore;
		} else {
			return 0;
		}
	}

	/**
	 * 将员工信息添加到黑名单中
	 */
	@Override
	public int addBlacklist(Blacklist blacklist) {
		int result = blacklistMapper.insert(blacklist);
		if (result > 0) {
			return result;
		} else {
			return 0;
		}
	}

	/**
	 * <!-- 根据职工id获取职工信息 = #{employeeid}-->
	 */
	@Override
	public EmployeeIn selEmployeeInByEmpId(String employeeid) {
		EmployeeIn employeeIn = emplyinBreakrulesCustomMapper.selEmployeeInByEmpId(employeeid);
		if (employeeIn != null) {
			return employeeIn;
		} else {
			return null;
		}
	}

	/**
	 * 根据职工id获取该职工的所有违章信息
	 */
	@Override
	public List<EmplyinBreakrules> selEmpInBreakrulesByEmpId(String employeeid) {
		List<EmplyinBreakrules> emplyinBreakrulesList = emplyinBreakrulesCustomMapper.selEmpInBreakrulesByEmpId(employeeid);
		if (emplyinBreakrulesList != null) {
			return emplyinBreakrulesList;
		} else {
			return null;
		}
	}
	/**
	 * 根据职工id获取该职工的所有违章信息
	 */
	@Override
	public List<EmplyinBreakrules> selEmpInBreakrulesByEmpId(Map condition) {
		List<EmplyinBreakrules> emplyinBreakrulesList = emplyinBreakrulesCustomMapper.selEmpInBreakrulesByEmpId1(condition);
		if (emplyinBreakrulesList != null) {
			return emplyinBreakrulesList;
		} else {
			return null;
		}
	}

	/**
	 * <!-- 根据职工id从黑名单中删除该职工id的黑名单信息 -->
	 */
	@Override
	public int delBlacklistByEmpId(String employeeid) {
		int result = emplyinBreakrulesCustomMapper.delBlacklistByEmpId(employeeid);
		if (result > 0) {
			return result;
		} else {
			return 0;
		}
	}

	/**
	 * <!-- 根据职工id获取黑名单信息 -->
	 */
	@Override
	public Blacklist getBlacklistByEmpInId(String employeeid) {
		List<Blacklist> blacklistList = emplyinBreakrulesCustomMapper.getBlacklistByEmpInId(employeeid);
		// 取出内部员工
		if (blacklistList != null) {
			for (Blacklist bl : blacklistList) {
				if (bl.getEmployeestatus().equals("0")) {
					return bl;// 返回内部部门的员工
				}
			}
		}
		return null;
	}

	/**
	 * <!-- 点击左侧的部门树的时候，将该部门及该部门下的所有子部门下的内部员工的信息及其违章信息显示出来-->
	 */
	@Override
	public List<Map<String, Object>> clickLeftShowEmpInMsg(Map<String, Object> map) {
		List<Map<String, Object>> clickLeftShowEmpInMsgList = emplyinBreakrulesCustomMapper.clickLeftShowEmpInMsg(map);
		if (clickLeftShowEmpInMsgList != null) {
			return clickLeftShowEmpInMsgList;
		} else {
			return null;
		}
	}

	/**
	 * <!-- 点击左侧的部门树的时候，查询该部门及该部门下的所有子部门下的内部员工的信息及其违章信息 的总记录数-->
	 */
	@Override
	public int clickLeftShowEmpInMsgCount(Map<String, Object> map) {
		int count = emplyinBreakrulesCustomMapper.clickLeftShowEmpInMsgCount(map);
		if (count > 0) {
			return count;
		} else {
			return 0;
		}
	}

	/**
	 * <!-- 左侧的树和条件绑定之后的查询，显示该部门及旗下所有子孙部门下的内部员工的信息及其违章信息 -->
	 */
	@Override
	public List<Map<String, Object>> leftTreeAndConditionMsg(Map<String, Object> map) {
		List<Map<String, Object>> leftTreeAndConditionMsg = emplyinBreakrulesCustomMapper.leftTreeAndConditionMsg(map);
		if(leftTreeAndConditionMsg!=null){
			return leftTreeAndConditionMsg;
		}else{
			return null;
		}
	}

	/**
	 * <!-- 左侧的树和条件绑定之后的查询，显示该部门及旗下所有子孙部门下的内部员工的信息及其违章信息 的总记录数 -->
	 */
	@Override
	public int leftTreeAndConditionMsgCount(Map<String, Object> map) {
		int count = emplyinBreakrulesCustomMapper.leftTreeAndConditionMsgCount(map);
		if(count>0){
			return count;
		}else{
			return 0;
		}
	}
	
	/**
	 * 更新黑名单信息 (主要用于更新黑名单状态)
	 * @param blacklist
	 * @return
	 */
	@Override
	public int updateBlacklist(Blacklist blacklist){
		int result = blacklistMapper.updateByPrimaryKeySelective(blacklist);
		if(result>0){
			return result;
		}else{
			return 0;
		}
	}

	/**
     * <!-- 根据内部员工id 获取黑名单状态  -->
     */
	@Override
	public String getBlackStatusByEmpInId(String employeeid) {
		String blackStatus = emplyinBreakrulesCustomMapper.getBlackStatusByEmpInId(employeeid);
		if("1".equals(blackStatus)){
			return "是";
		}else{
			return "否";
		}
	}

	
	
  
    
    
    /**
     *  <!-- 左侧的树和条件绑定之后 黑名单状态为"否"的情况的查询，的查询，显示该部门及旗下所有子孙部门下的内部员工的信息及其违章信息 -->
     */
	@Override
	public List<Map<String, Object>> leftTreeAndConditionNoBlacklistMsg(Map<String, Object> map) {
		List<Map<String, Object>> leftTreeAndConditionNoBlacklistMsg = emplyinBreakrulesCustomMapper.leftTreeAndConditionNoBlacklistMsg(map);
		if(leftTreeAndConditionNoBlacklistMsg!=null){
			return leftTreeAndConditionNoBlacklistMsg;
		}else{
			return null;
		}
	}

   /**
     *  <!-- 左侧的树和条件绑定之后黑名单状态为"否"的情况的查询，的查询，显示该部门及旗下所有子孙部门下的内部员工的信息及其违章信息 -->
     */
	@Override
	public int leftTreeAndConditionNoBlacklistMsgCount(Map<String, Object> map) {
		int leftTreeAndConditionNoBlacklistMsgCount = emplyinBreakrulesCustomMapper.leftTreeAndConditionNoBlacklistMsgCount(map);
		if(leftTreeAndConditionNoBlacklistMsgCount>0){
			return leftTreeAndConditionNoBlacklistMsgCount;
		}else{
			return 0;
		}
	}

	/**
     *  <!-- 左侧的树和条件绑定之后 黑名单状态为"是"的情况的查询，显示该部门及旗下所有子孙部门下的内部员工的信息及其违章信息 -->
     */
	@Override
	public List<Map<String, Object>> leftTreeAndConditionIsBlacklistMsg(Map<String, Object> map) {
		List<Map<String, Object>> leftTreeAndConditionIsBlacklistMsg = emplyinBreakrulesCustomMapper.leftTreeAndConditionIsBlacklistMsg(map);
		if(leftTreeAndConditionIsBlacklistMsg!=null){
			return leftTreeAndConditionIsBlacklistMsg;
		}else{
			return null;
		}
	}

	/**
     *  <!-- 左侧的树和条件绑定之 后黑名单状态为"是"的情况的查询，的查询，显示该部门及旗下所有子孙部门下的内部员工的信息及其违章信息 -->
     */
	@Override
	public int leftTreeAndConditionIsBlacklistMsgCount(Map<String, Object> map) {
		int leftTreeAndConditionIsBlacklistMsgCount = emplyinBreakrulesCustomMapper.leftTreeAndConditionIsBlacklistMsgCount(map);
		if(leftTreeAndConditionIsBlacklistMsgCount>0){
			return leftTreeAndConditionIsBlacklistMsgCount;
		}else{
			return 0;
		}
	}
	@Override
	public int getSingleEmplyInBreakScoreSum(Map<String, Object> map) {
		int sumBreakScore = emplyinBreakrulesCustomMapper.getSingleEmplyInBreakScoreSum(map);
		if(sumBreakScore>0){
			return sumBreakScore;
		}else{
			return 0;
		}
	}
	

}
