package cn.xm.exam.mapper.employee.out;

import cn.xm.exam.bean.employee.out.Employeeoutdistribute;
import cn.xm.exam.bean.employee.out.EmployeeoutdistributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeoutdistributeMapper {
    int countByExample(EmployeeoutdistributeExample example);

    int deleteByExample(EmployeeoutdistributeExample example);

    int deleteByPrimaryKey(Integer distributeid);

    int insert(Employeeoutdistribute record);

    int insertSelective(Employeeoutdistribute record);

    List<Employeeoutdistribute> selectByExample(EmployeeoutdistributeExample example);

    Employeeoutdistribute selectByPrimaryKey(Integer distributeid);

    int updateByExampleSelective(@Param("record") Employeeoutdistribute record, @Param("example") EmployeeoutdistributeExample example);

    int updateByExample(@Param("record") Employeeoutdistribute record, @Param("example") EmployeeoutdistributeExample example);

    int updateByPrimaryKeySelective(Employeeoutdistribute record);

    int updateByPrimaryKey(Employeeoutdistribute record);
}