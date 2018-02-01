package cn.xm.exam.mapper.exam;

import cn.xm.exam.bean.exam.Exampaperoption;
import cn.xm.exam.bean.exam.ExampaperoptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExampaperoptionMapper {
    int countByExample(ExampaperoptionExample example);

    int deleteByExample(ExampaperoptionExample example);

    int deleteByPrimaryKey(Integer optionid);

    int insert(Exampaperoption record);

    int insertSelective(Exampaperoption record);

    List<Exampaperoption> selectByExample(ExampaperoptionExample example);

    Exampaperoption selectByPrimaryKey(Integer optionid);

    int updateByExampleSelective(@Param("record") Exampaperoption record, @Param("example") ExampaperoptionExample example);

    int updateByExample(@Param("record") Exampaperoption record, @Param("example") ExampaperoptionExample example);

    int updateByPrimaryKeySelective(Exampaperoption record);

    int updateByPrimaryKey(Exampaperoption record);
}