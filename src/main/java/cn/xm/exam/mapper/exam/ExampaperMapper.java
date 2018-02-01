package cn.xm.exam.mapper.exam;

import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.bean.exam.ExampaperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExampaperMapper {
    int countByExample(ExampaperExample example);

    int deleteByExample(ExampaperExample example);

    int deleteByPrimaryKey(String paperid);

    int insert(Exampaper record);

    int insertSelective(Exampaper record);

    List<Exampaper> selectByExample(ExampaperExample example);

    Exampaper selectByPrimaryKey(String paperid);

    int updateByExampleSelective(@Param("record") Exampaper record, @Param("example") ExampaperExample example);

    int updateByExample(@Param("record") Exampaper record, @Param("example") ExampaperExample example);

    int updateByPrimaryKeySelective(Exampaper record);

    int updateByPrimaryKey(Exampaper record);
}