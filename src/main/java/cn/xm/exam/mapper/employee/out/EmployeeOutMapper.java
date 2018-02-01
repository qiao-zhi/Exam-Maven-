package cn.xm.exam.mapper.employee.out;

import cn.xm.exam.bean.employee.out.EmployeeOut;
import cn.xm.exam.bean.employee.out.EmployeeOutExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeOutMapper {
    int countByExample(EmployeeOutExample example);

    int deleteByExample(EmployeeOutExample example);

    int deleteByPrimaryKey(String employeeid);

    int insert(EmployeeOut record);

    int insertSelective(EmployeeOut record);

    List<EmployeeOut> selectByExample(EmployeeOutExample example);

    EmployeeOut selectByPrimaryKey(String employeeid);

    int updateByExampleSelective(@Param("record") EmployeeOut record, @Param("example") EmployeeOutExample example);

    int updateByExample(@Param("record") EmployeeOut record, @Param("example") EmployeeOutExample example);

    int updateByPrimaryKeySelective(EmployeeOut record);

    int updateByPrimaryKey(EmployeeOut record);
}