package cn.xm.exam.mapper.employee.in;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.employee.in.EmployeeInExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeInMapper {
    int countByExample(EmployeeInExample example);

    int deleteByExample(EmployeeInExample example);

    int deleteByPrimaryKey(String employeeid);

    int insert(EmployeeIn record);

    int insertSelective(EmployeeIn record);

    List<EmployeeIn> selectByExample(EmployeeInExample example);

    EmployeeIn selectByPrimaryKey(String employeeid);

    int updateByExampleSelective(@Param("record") EmployeeIn record, @Param("example") EmployeeInExample example);

    int updateByExample(@Param("record") EmployeeIn record, @Param("example") EmployeeInExample example);

    int updateByPrimaryKeySelective(EmployeeIn record);

    int updateByPrimaryKey(EmployeeIn record);
}