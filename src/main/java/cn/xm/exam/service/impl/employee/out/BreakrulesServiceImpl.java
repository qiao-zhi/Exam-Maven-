package cn.xm.exam.service.impl.employee.out;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xm.exam.bean.employee.out.Blacklist;
import cn.xm.exam.bean.employee.out.Breakrules;
import cn.xm.exam.bean.employee.out.EmployeeOut;
import cn.xm.exam.bean.haul.Haulemployeeout;
import cn.xm.exam.bean.haul.Haulunit;
import cn.xm.exam.mapper.employee.out.BlacklistMapper;
import cn.xm.exam.mapper.employee.out.BreakrulesMapper;
import cn.xm.exam.mapper.employee.out.EmployeeOutMapper;
import cn.xm.exam.mapper.employee.out.custom.BreakrulesCustomMapper;
import cn.xm.exam.mapper.haul.HaulemployeeoutMapper;
import cn.xm.exam.mapper.haul.HaulunitMapper;
import cn.xm.exam.service.employee.out.BreakrulesService;

/**
 * 违章Service的实现类
 * 
 * @author QizoLiQiang
 * @time 2017年9月18日上午8:17:19
 */
@Service
public class BreakrulesServiceImpl implements BreakrulesService {

	@Resource
	private BreakrulesMapper breakrulesMapper;
	
	@Resource
	private BreakrulesCustomMapper breakrulesCustomMapper;

	@Resource
	private HaulemployeeoutMapper haulemployeeoutMapper;

	@Resource
	private EmployeeOutMapper employeeOutMapper;

	@Resource
	private HaulunitMapper haulunitMapper;

	@Resource
	private BlacklistMapper blacklistMapper;

	/**
	 * 添加违章信息
	 */
	@Override
	public int addBreakrules(Breakrules breakRules) {
		int result = breakrulesMapper.insert(breakRules);
		return result;
	}

	/**
	 * 修改违章信息
	 */
	@Override
	public int updateBreakrules(Breakrules breakRules) {
		int result = breakrulesMapper.updateByPrimaryKeySelective(breakRules);
		return result;
	}

	/**
	 * 删除违章信息
	 */
	@Override
	public int delBreakrules(int breakId) {
		int result = breakrulesMapper.deleteByPrimaryKey(breakId);
		return result;
	}

	/**
	 * 根据违章id查询单条违章信息
	 */
	@Override
	public Breakrules selectByPrimaryKey(Integer breakid) {
		Breakrules result = breakrulesMapper.selectByPrimaryKey(breakid);
		if (result != null) {
			return result;
		} else {
			return null;
		}
	}

	

	/**
	 * <!-- 根据单位编号unitid获取外来职工名字、性别、身份证、违章记分，根据当前页页号、每页显示的记录数进行分页查找-->
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectLeftToTable(Map<String, Object> map) {
		List<Map<String, Object>> selectLeftToTable = breakrulesCustomMapper.selectLeftToTable(map);
		if (selectLeftToTable.size() > 0) {
			return selectLeftToTable;
		} else {
			return null;
		}
	}

	/**
	 * <!-- 根据单位编号unitid获取外来职工名字、性别、身份证、违章记分，总共有多少条记录数-->
	 */
	@Override
	public int selectLeftToTableCount(Map<String, Object> map) {
		int count = breakrulesCustomMapper.selectLeftToTableCount(map);
		return count;
	}

	/**
	 * <!-- 根据员工id去违章记录表中查找符合条件的所有违章信息 -->
	 */
	@Override
	public List<Breakrules> selBreakRulesByEmployeeId(Map<String,Object> map) {
		List<Breakrules> breakruleList = breakrulesCustomMapper.selBreakRulesByEmployeeId(map);
		if (breakruleList!=null) {
			return breakruleList;
		} else {
			return null;
		}
	}

	/**
	 * <!-- 根据单位编号去单位大修表中获取总该单位的信息(也就是该单位编号对应的积分) -->
	 */
	@Override
	public Haulunit selHaulunitByUnitId(String unitId) {
		Haulunit haulUnit = breakrulesCustomMapper.selHaulunitByUnitId(unitId);
		if (haulUnit != null) {
			return haulUnit;
		} else {
			return null;
		}
	}

	/**
	 * 修改大修单位表信息
	 */
	@Override
	public int updateHaulUnitByPrimaryKeySelective(Haulunit Haulunit) {
		int result = haulunitMapper.updateByPrimaryKeySelective(Haulunit);
		if (result > 0) {
			return result;
		} else {
			return 0;
		}
	}

	/**
	 * <!-- 根据大修单位编号获取大修信息 -->
	 */
	@Override
	public Haulunit selHaulunitByUnitBigHualId(Map<String, Object> map) {
		Haulunit haulUnit = breakrulesCustomMapper.selHaulunitByUnitBigHualId(map);
		if (haulUnit != null) {
			return haulUnit;
		} else {
			return null;
		}
	}

	/**
	 * <!-- 根据单位编号unitid、职工姓名、身份证、违章记分、性别，当前页页号，每页显示的记录数
	 * 获取外来职工名字、性别、身份证、违章记分，根据当前页页号、每页显示的记录数进行分页查找-->
	 */
	@Override
	public List<Map<String, Object>> selectMsgByFyCondition(Map<String, Object> map) {
		List<Map<String, Object>> selectMsgByFyCondition = breakrulesCustomMapper.selectMsgByFyCondition(map);
		if (selectMsgByFyCondition != null) {
			return selectMsgByFyCondition;
		} else {
			return null;
		}
	}

	/**
	 * <!-- 根据单位编号unitid、职工姓名、身份证、违章记分、性别，查询符合条件的总记录数-->
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public int selectMsgByFyConditionCount(Map<String, Object> map) {
		int count = breakrulesCustomMapper.selectMsgByFyConditionCount(map);
		if (count > 0) {
			return count;
		} else {
			return 0;
		}
	}

	/**
	 * <!-- 与左边的树绑定之后的按条件分页查询 点击了黑名单之后的情况，也就是“是”黑名单的情况 -->
	 */
	@Override
	public List<Map<String, Object>> selLeftTreeAndConditionBlack(Map<String, Object> map) {
		List<Map<String, Object>> selLeftTreeAndConditionBlack = breakrulesCustomMapper.selLeftTreeAndConditionBlack(map);
		if (selLeftTreeAndConditionBlack != null) {
			return selLeftTreeAndConditionBlack;
		} else {
			return null;
		}
	}

	/**
	 * <!-- 与左边的树绑定之后的按条件分页查询的总记录数 点击了黑名单之后的情况，也就是“是”黑名单的情况 -->
	 */
	@Override
	public int selLeftTreeAndConditionBlackCount(Map<String, Object> map) {
		int count = breakrulesCustomMapper.selLeftTreeAndConditionBlackCount(map);
		if (count > 0) {
			return count;
		} else {
			return 0;
		}
	}

	/**
	 * <!-- 根据职工id去违章表中找到所有职工id等于当前职工id的违章记录，并算出其总违章记分 -->
	 */
	@Override
	public Integer selSumBreakScoreByEmpId(Map<String,Object> map) {
		Integer sumBreakScore = breakrulesCustomMapper.selSumBreakScoreByEmpId(map);
		if(sumBreakScore!=null && sumBreakScore>0){
			return sumBreakScore;
		}else{
			return 0;
		}
	}
	@Override
	public Integer selSumBreakScoreByEmpId(String employeeOutId) {
		Integer sumBreakScore = breakrulesCustomMapper.selSumBreakScoreByEmpId1(employeeOutId);
		if(sumBreakScore!=null && sumBreakScore>0){
			return sumBreakScore;
		}else{
			return 0;
		}
	}

	/**
	 * <!-- 根据职工id去黑名单表中删除对应的记录-->
	 */
	@Override
	public int delBlacklistByEmployeeId(String employeeid) {
		int result = breakrulesCustomMapper.delBlacklistByEmployeeId(employeeid);
		if (result > 0) {
			return result;
		} else {
			return 0;
		}
	}

	/**
	 * <!-- 根据职工id获取外来职工表中的信息 -->
	 */
	@Override
	public EmployeeOut selEmployeeOutByEmployeeId(String employeeid) {
		EmployeeOut result = breakrulesCustomMapper.selEmployeeOutByEmployeeId(employeeid);
		if (result != null) {
			return result;
		} else {
			return null;
		}
	}

	/**
	 * 往黑名单表中添加黑名单信息
	 * 
	 * @param blackList
	 * @return
	 */
	@Override
	public int addBlackList(Blacklist blackList) {
		int result = blacklistMapper.insert(blackList);
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
	public Blacklist selBlackListByEmpId(String employeeid) {
		Blacklist blacklist = breakrulesCustomMapper.selBlackListByEmpId(employeeid);
		if (blacklist != null) {
			return blacklist;
		} else {
			return null;
		}
	}

	/**
	 * <!-- 根据职工id获取大修外来职工表中的信息 -->
	 */
	@Override
	public Haulemployeeout selHaulemployeeoutByUnitId(String employeeid) {
		Haulemployeeout haulEmployeeOut = breakrulesCustomMapper.selHaulemployeeoutByUnitId(employeeid);
		if (haulEmployeeOut != null) {
			return haulEmployeeOut;
		} else {
			return null;
		}
	}

	/**
	 * <!-- 页面初始化要用的数据 -->
	 */
	@Override
	public List<Map<String, Object>> initPage(Map<String, Object> map) {
		List<Map<String, Object>> breakrulesList = breakrulesCustomMapper.initPage(map);
		if (breakrulesList != null) {
			return breakrulesList;
		} else {
			return null;
		}
	}

	/**
	 * <!-- 页面初始化数据的总条数 -->
	 */
	@Override
	public int initPageCount() {
		int count = breakrulesCustomMapper.initPageCount();
		if (count > 0) {
			return count;
		} else {
			return 0;
		}
	}

	
	/**
	 * <!-- 与左边的树绑定之后黑名单状态为"否"的情况 -->
	 */
	@Override
	public List<Map<String, Object>> blackStatusFalse(Map<String, Object> map) {
		List<Map<String, Object>> blackStatusFalse = breakrulesCustomMapper.blackStatusFalse(map);
		if(blackStatusFalse!=null){
			return blackStatusFalse;
		}else{
			return null;
		}
	}

	/**
	 * <!-- 与左边的树绑定之后黑名单状态为"否"的情况总条数 -->
	 */
	@Override
	public int blackStatusFalseCount(Map<String, Object> map) {
		int count = breakrulesCustomMapper.blackStatusFalseCount(map);
		if(count>0){
			return count;
		}else{
			return 0;
		}
	}

	 /**
     * <!-- 先根据大修id bigid和单位id unitid查询出该大修单位的信息 -->
     */
	@Override
	public Haulunit selHaulUnitByUnitidBigid(Map<String, Object> map) {
		Haulunit haulunit = breakrulesCustomMapper.selHaulUnitByUnitidBigid(map);
		if(haulunit!=null){
			return haulunit;
		}else{
			return null;
		}
	}

	/**
  	 * 加载左边的树
  	 * @return
  	 */
	@Override
	public List<Map<String, Object>> findLeftTree() {
		List<Map<String, Object>> leftTree = breakrulesCustomMapper.findLeftTree();
		return leftTree;
	}

	/**
	 * 更新黑名单信息(只是用于更新状态)
	 * @param blacklist
	 * @return
	 */
	@Override
	public int updateBalcklist(Blacklist blacklist){
		int res = blacklistMapper.updateByPrimaryKeySelective(blacklist);
		if(res>0){
			return res;
		}else{
			return 0;
		}
	}


  	/**
  	 *  <!-- 根据外部员工id 获取黑名单状态  -->
  	 */
	@Override
	public String getBlackStatusByEmpoutId(String employeeid) {
		String blackStatus = breakrulesCustomMapper.getBlackStatusByEmpoutId(employeeid);
		if("1".equals(blackStatus)){
			return "是";
		}else{
			return "否";
		}
	}

	/**
	 * 根据大修id获取大修名字
	 */
	@Override
	public String selHaulInfoNameByBigId(String bigid) {
		String haulInfoName = breakrulesCustomMapper.selHaulInfoNameByBigId(bigid);
		if(haulInfoName!=null){
			return haulInfoName;
		}else{
			return null;
		}
	}
}
