package cn.xm.exam.service.employee.out;

import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.employee.out.Blacklist;
import cn.xm.exam.bean.employee.out.Breakrules;
import cn.xm.exam.bean.employee.out.EmployeeOut;
import cn.xm.exam.bean.haul.Haulemployeeout;
import cn.xm.exam.bean.haul.Haulunit;

/**
 * 黑名单Service
 * 
 * @author QizoLiQiang
 * @time 2017年9月18日上午8:17:19
 */
public interface BreakrulesService {
	
	/**
	 * 添加违章信息
	 * @param breakRules 违章信息的javabean
	 * @return 是否添加成功
	 */
	public int addBreakrules(Breakrules breakRules);
	
	/**
	 * 修改违章信息  (根据javabean修改违章信息)
 	 * @param breakRules 修改后的违章信息
	 * @return 是否修改成功
	 */
	public int updateBreakrules(Breakrules breakRules);
	
	
	
	/**
	 * 删除违章信息(根据违章信息id删除)
	 * @param breakId  违章信息id
	 * @return 是否删除成功
	 */
	public int delBreakrules(int breakId);
	
	/**
	 * 通过违章id查询单条违章信息
	 * @param breakid
	 * @return 单条违章信息
	 */
	public Breakrules selectByPrimaryKey(Integer breakid);
	
	/**
	 * 根据外来单位表unit的单位编号(也就是树的编号)在参加大修的外来职工表haulEmployeeOut中
	 * 			找到所有职工id，在将找到的所有职工的id去外来职工表employee_out中根据职工id查询出所有外来职工信息。
	 *//*
	public List<EmployeeOut> selEmployeeOutByUnitid(String unitid);
	*/
	/**
     * <!-- 根据单位编号unitid获取外来职工名字、性别、身份证、违章记分，根据当前页页号、每页显示的记录数进行分页查找-->
     * @param map
     * @return
     */
    List<Map<String,Object>> selectLeftToTable(Map<String,Object> map);
    
    /**
     * <!-- 根据单位编号unitid获取外来职工名字、性别、身份证、违章记分，总共有多少条记录数-->
     */
    int selectLeftToTableCount(Map<String,Object> map);
    
    /**
     *根据BigEmployeeoutId、职工id employeeid 去违章表中查询该大修下该职工的所有违章记录 -->
     */
    List<Breakrules> selBreakRulesByEmployeeId(Map<String,Object>map);
    
    /**
     * <!-- 根据单位编号去单位大修表中获取总该单位的信息(也就是该单位编号对应的积分) -->
     */
    Haulunit selHaulunitByUnitId(String unitId);
    
    /**
     * 修改大修单位表的信息
     * @param Haulunit
     * @return
     */
    public int updateHaulUnitByPrimaryKeySelective(Haulunit Haulunit);
    
    /**
     * <!-- 根据大修单位编号获取大修信息 -->
     */
    Haulunit selHaulunitByUnitBigHualId(Map<String,Object> map); 
    
    /**
     * <!-- 根据单位编号unitid、职工姓名、身份证、违章记分、性别，当前页页号，每页显示的记录数 获取外来职工名字、性别、身份证、违章记分，根据当前页页号、每页显示的记录数进行分页查找-->
     */
    List<Map<String,Object>> selectMsgByFyCondition(Map<String,Object> map);
    
    /**
     * <!-- 根据单位编号unitid、职工姓名、身份证、违章记分、性别，查询符合条件的总记录数-->
     * @param map
     * @return
     */
    int selectMsgByFyConditionCount(Map<String,Object> map);
    
    /**
     * <!-- 与左边的树绑定之后的按条件分页查询  点击了黑名单之后的情况，也就是“是”黑名单的情况 -->
     */
    List<Map<String,Object>> selLeftTreeAndConditionBlack(Map<String,Object> map );
    
    /**
     *  <!-- 与左边的树绑定之后的按条件分页查询的总记录数  点击了黑名单之后的情况，也就是“是”黑名单的情况 -->
     */
    int selLeftTreeAndConditionBlackCount(Map<String,Object> map);
    
    /**
     * <!-- 根据职工id去违章表中找到所有职工id等于当前职工id的违章记录，并算出其总违章记分 -->
     */
    Integer selSumBreakScoreByEmpId(Map<String,Object> map);
    Integer selSumBreakScoreByEmpId(String employeeOutId);
    
    /**
     * <!-- 根据职工id去黑名单表中删除对应的记录-->
     */
    int delBlacklistByEmployeeId(String employeeid);
    
    /**
     * <!-- 根据职工id获取外来职工表中的信息 -->
     */
    EmployeeOut selEmployeeOutByEmployeeId(String employeeid);
    
    /**
	 * 往黑名单表中添加黑名单信息
	 * @param blackList
	 * @return
	 */
	public int addBlackList(Blacklist blackList);
	
	/**
     *  <!-- 根据职工id获取黑名单信息 -->
     */
    Blacklist selBlackListByEmpId(String employeeid);
    
    /**
     * <!-- 根据职工id获取大修外来职工表中的信息 -->
     */
    Haulemployeeout  selHaulemployeeoutByUnitId(String employeeid);
    
    /**
     * <!-- 页面初始化要用的数据 -->
     */
    List<Map<String,Object>> initPage(Map<String,Object>map);
   
    /**
     * <!-- 页面初始化数据的总条数 -->
     */
    int initPageCount();
    
    
    /**
     *  <!-- 与左边的树绑定之后黑名单状态为"否"的情况 -->
     */
     List<Map<String,Object>>  blackStatusFalse(Map<String,Object>map);
     
     /**
      * <!-- 与左边的树绑定之后黑名单状态为"否"的情况总条数 -->
      */
     int blackStatusFalseCount(Map<String,Object>map);
     
     /**
      * <!-- 先根据大修id bigid和单位id unitid查询出该大修单位的信息 -->
      */
     Haulunit selHaulUnitByUnitidBigid(Map<String,Object> map);
     
     
     /**
   	 * 加载左边的树
   	 * @return
   	 */
   	List<Map<String,Object>> findLeftTree();
   	
   	/**
	 * 更新黑名单信息(只是用于更新状态)
	 * @param blacklist
	 * @return
	 */
	public int updateBalcklist(Blacklist blacklist);
	

  	/**
  	 *  <!-- 根据外部员工id 获取黑名单状态  -->
  	 */
  	String getBlackStatusByEmpoutId(String employeeid);
  	
  	/**
  	 * <!-- 根据大修id获取大修名称 -->
  	 */
     String	selHaulInfoNameByBigId(String bigid);
}
