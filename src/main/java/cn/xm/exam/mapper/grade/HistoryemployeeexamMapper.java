package cn.xm.exam.mapper.grade;

import cn.xm.exam.bean.grade.Historyemployeeexam;
import cn.xm.exam.bean.grade.HistoryemployeeexamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HistoryemployeeexamMapper {
    int countByExample(HistoryemployeeexamExample example);

    int deleteByExample(HistoryemployeeexamExample example);

    int deleteByPrimaryKey(String gradeid);

    int insert(Historyemployeeexam record);

    int insertSelective(Historyemployeeexam record);

    List<Historyemployeeexam> selectByExample(HistoryemployeeexamExample example);

    Historyemployeeexam selectByPrimaryKey(String gradeid);

    int updateByExampleSelective(@Param("record") Historyemployeeexam record, @Param("example") HistoryemployeeexamExample example);

    int updateByExample(@Param("record") Historyemployeeexam record, @Param("example") HistoryemployeeexamExample example);

    int updateByPrimaryKeySelective(Historyemployeeexam record);

    int updateByPrimaryKey(Historyemployeeexam record);
}