package cn.xm.exam.mapper.employee.out.custom;

import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.employee.out.Blacklist;
import cn.xm.exam.bean.employee.out.Breakrules;
import cn.xm.exam.bean.employee.out.EmployeeOut;
import cn.xm.exam.bean.haul.Haulemployeeout;
import cn.xm.exam.bean.haul.Haulunit;

public interface BreakrulesCustomMapper {
    //==========lixianyuan start =============
    /**
     *根据BigEmployeeoutId、职工id employeeid 去违章表中查询该大修下该职工的所有违章记录 -->
     */
    List<Breakrules> selBreakRulesByEmployeeId(Map<String,Object>map);
    
    
    /**
     * <!-- 根据单位编号去单位大修表中获取该单位的总积分 该单位的信息(也就是该单位编号对应的积分) -->
     */
    Haulunit selHaulunitByUnitId(String unitId);
    
    /**
     * <!-- 根据大修单位编号获取大修信息 -->
     */
    Haulunit selHaulunitByUnitBigHualId(Map<String,Object> map); 
    
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
    List<Map<String,Object>> selLeftTreeAndConditionBlack(Map<String,Object> map);
    
    /**
     *  <!-- 与左边的树绑定之后的按条件分页查询的总记录数  点击了黑名单之后的情况，也就是“是”黑名单的情况 -->
     */
    int selLeftTreeAndConditionBlackCount(Map<String,Object> map);
    
    /**
     * <!-- 根据职工id去违章表中找到所有职工id等于当前职工id的违章记录，并算出其总违章记分 -->
     */
    Integer selSumBreakScoreByEmpId(Map<String,Object> map);
    Integer selSumBreakScoreByEmpId1(String bigEmployeeOutId);
    
    /**
     * <!-- 根据职工id去黑名单表中删除对应的记录-->
     */
    int delBlacklistByEmployeeId(String employeeid);
    
    /**
     * <!-- 根据职工id获取外来职工表中的信息 -->
     */
    EmployeeOut selEmployeeOutByEmployeeId(String employeeid);
    
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
  	 *  <!-- 根据外部员工id 获取黑名单状态  -->
  	 */
  	String getBlackStatusByEmpoutId(String employeeid);
  	
  	/**
  	 * <!-- 根据大修id获取大修名称 -->
  	 */
     String	selHaulInfoNameByBigId(String bigid);
    //============lixianyuan  end =============
}