package cn.xm.exam.mapper.exam;

import cn.xm.exam.bean.exam.Bigquestion;
import cn.xm.exam.bean.exam.BigquestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BigquestionMapper {
    int countByExample(BigquestionExample example);

    int deleteByExample(BigquestionExample example);

    int deleteByPrimaryKey(String bigquertionid);

    int insert(Bigquestion record);

    int insertSelective(Bigquestion record);

    List<Bigquestion> selectByExample(BigquestionExample example);

    Bigquestion selectByPrimaryKey(String bigquertionid);

    int updateByExampleSelective(@Param("record") Bigquestion record, @Param("example") BigquestionExample example);

    int updateByExample(@Param("record") Bigquestion record, @Param("example") BigquestionExample example);

    int updateByPrimaryKeySelective(Bigquestion record);

    int updateByPrimaryKey(Bigquestion record);
}