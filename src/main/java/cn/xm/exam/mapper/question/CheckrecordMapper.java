package cn.xm.exam.mapper.question;

import cn.xm.exam.bean.question.Checkrecord;
import cn.xm.exam.bean.question.CheckrecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckrecordMapper {
    int countByExample(CheckrecordExample example);

    int deleteByExample(CheckrecordExample example);

    int deleteByPrimaryKey(String checkid);

    int insert(Checkrecord record);

    int insertSelective(Checkrecord record);

    List<Checkrecord> selectByExample(CheckrecordExample example);

    Checkrecord selectByPrimaryKey(String checkid);

    int updateByExampleSelective(@Param("record") Checkrecord record, @Param("example") CheckrecordExample example);

    int updateByExample(@Param("record") Checkrecord record, @Param("example") CheckrecordExample example);

    int updateByPrimaryKeySelective(Checkrecord record);

    int updateByPrimaryKey(Checkrecord record);
}