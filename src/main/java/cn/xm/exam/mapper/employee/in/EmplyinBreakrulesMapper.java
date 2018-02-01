package cn.xm.exam.mapper.employee.in;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.employee.in.EmplyinBreakrules;
import cn.xm.exam.bean.employee.in.EmplyinBreakrulesExample;
import cn.xm.exam.bean.employee.out.Blacklist;

public interface EmplyinBreakrulesMapper {
	int countByExample(EmplyinBreakrulesExample example);

	int deleteByExample(EmplyinBreakrulesExample example);

	int deleteByPrimaryKey(Integer empinbreakid);

	int insert(EmplyinBreakrules record);

	int insertSelective(EmplyinBreakrules record);

	List<EmplyinBreakrules> selectByExample(EmplyinBreakrulesExample example);

	EmplyinBreakrules selectByPrimaryKey(Integer empinbreakid);

	int updateByExampleSelective(@Param("record") EmplyinBreakrules record,
			@Param("example") EmplyinBreakrulesExample example);

	int updateByExample(@Param("record") EmplyinBreakrules record, @Param("example") EmplyinBreakrulesExample example);

	int updateByPrimaryKeySelective(EmplyinBreakrules record);

	int updateByPrimaryKey(EmplyinBreakrules record);


}