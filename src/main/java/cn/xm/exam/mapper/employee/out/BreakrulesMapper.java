package cn.xm.exam.mapper.employee.out;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.xm.exam.bean.employee.out.Blacklist;
import cn.xm.exam.bean.employee.out.Breakrules;
import cn.xm.exam.bean.employee.out.BreakrulesExample;
import cn.xm.exam.bean.employee.out.EmployeeOut;
import cn.xm.exam.bean.haul.Haulemployeeout;
import cn.xm.exam.bean.haul.Haulunit;

public interface BreakrulesMapper {
	
	/**
	 * 查询违章表中记录的总条数
	 * @param example
	 * @return 违章记录的总条数
	 */
    int countByExample(BreakrulesExample example);

    int deleteByExample(BreakrulesExample example);

    /**
     * 通过主键删除违章信息
     * @param breakid
     * @return
     */
    int deleteByPrimaryKey(Integer breakid);
    
    
    /**添加违章信息
     * @param record 违章的javabean
     * @return  是否添加成功的标记
     */
    int insert(Breakrules record);
    
    int insertSelective(Breakrules record);

    List<Breakrules> selectByExample(BreakrulesExample example);

    /**
     * 通过id查询单条违章信息
     * @param breakid
     * @return 该条违章信息的javabean
     */
    Breakrules selectByPrimaryKey(Integer breakid);
    
    int updateByExampleSelective(@Param("record") Breakrules record, @Param("example") BreakrulesExample example);
    
    int updateByExample(@Param("record") Breakrules record, @Param("example") BreakrulesExample example);
    
    /**
     * 更新违章信息
     * @param record 更新后的违章javabean
     * @return 是否更新成功的标记
     */
    int updateByPrimaryKeySelective(Breakrules record);

    int updateByPrimaryKey(Breakrules record);
    
    
    

}