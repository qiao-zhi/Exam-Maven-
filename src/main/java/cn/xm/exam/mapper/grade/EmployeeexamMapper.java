package cn.xm.exam.mapper.grade;

import cn.xm.exam.bean.grade.Employeeexam;
import cn.xm.exam.bean.grade.EmployeeexamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeexamMapper {
    int countByExample(EmployeeexamExample example);

    int deleteByExample(EmployeeexamExample example);

    int deleteByPrimaryKey(Integer gradeid);

    int insert(Employeeexam record);

    int insertSelective(Employeeexam record);

    List<Employeeexam> selectByExample(EmployeeexamExample example);

    Employeeexam selectByPrimaryKey(Integer gradeid);

    int updateByExampleSelective(@Param("record") Employeeexam record, @Param("example") EmployeeexamExample example);

    int updateByExample(@Param("record") Employeeexam record, @Param("example") EmployeeexamExample example);

    int updateByPrimaryKeySelective(Employeeexam record);

    int updateByPrimaryKey(Employeeexam record);
}