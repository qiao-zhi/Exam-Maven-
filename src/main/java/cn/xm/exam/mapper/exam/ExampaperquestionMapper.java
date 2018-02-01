package cn.xm.exam.mapper.exam;

import cn.xm.exam.bean.exam.Exampaperquestion;
import cn.xm.exam.bean.exam.ExampaperquestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExampaperquestionMapper {
    int countByExample(ExampaperquestionExample example);

    int deleteByExample(ExampaperquestionExample example);

    int deleteByPrimaryKey(String questionid);

    int insert(Exampaperquestion record);

    int insertSelective(Exampaperquestion record);

    List<Exampaperquestion> selectByExample(ExampaperquestionExample example);

    Exampaperquestion selectByPrimaryKey(String questionid);

    int updateByExampleSelective(@Param("record") Exampaperquestion record, @Param("example") ExampaperquestionExample example);

    int updateByExample(@Param("record") Exampaperquestion record, @Param("example") ExampaperquestionExample example);

    int updateByPrimaryKeySelective(Exampaperquestion record);

    int updateByPrimaryKey(Exampaperquestion record);
}