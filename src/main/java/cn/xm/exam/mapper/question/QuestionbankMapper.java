package cn.xm.exam.mapper.question;

import cn.xm.exam.bean.question.Questionbank;
import cn.xm.exam.bean.question.QuestionbankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionbankMapper {
    int countByExample(QuestionbankExample example);

    int deleteByExample(QuestionbankExample example);

    int deleteByPrimaryKey(String questionbankid);

    int insert(Questionbank record);

    int insertSelective(Questionbank record);

    List<Questionbank> selectByExample(QuestionbankExample example);

    Questionbank selectByPrimaryKey(String questionbankid);

    int updateByExampleSelective(@Param("record") Questionbank record, @Param("example") QuestionbankExample example);

    int updateByExample(@Param("record") Questionbank record, @Param("example") QuestionbankExample example);

    int updateByPrimaryKeySelective(Questionbank record);

    int updateByPrimaryKey(Questionbank record);
}