package cn.xm.exam.mapper.log;

import cn.xm.exam.bean.log.Logtable;
import cn.xm.exam.bean.log.LogtableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogtableMapper {
    int countByExample(LogtableExample example);

    int deleteByExample(LogtableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Logtable record);

    int insertSelective(Logtable record);

    List<Logtable> selectByExample(LogtableExample example);

    Logtable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Logtable record, @Param("example") LogtableExample example);

    int updateByExample(@Param("record") Logtable record, @Param("example") LogtableExample example);

    int updateByPrimaryKeySelective(Logtable record);

    int updateByPrimaryKey(Logtable record);
}