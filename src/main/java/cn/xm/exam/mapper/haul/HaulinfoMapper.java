package cn.xm.exam.mapper.haul;

import cn.xm.exam.bean.haul.Haulinfo;
import cn.xm.exam.bean.haul.HaulinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HaulinfoMapper {
    int countByExample(HaulinfoExample example);

    int deleteByExample(HaulinfoExample example);

    int deleteByPrimaryKey(String bigid);

    int insert(Haulinfo record);

    int insertSelective(Haulinfo record);

    List<Haulinfo> selectByExample(HaulinfoExample example);

    Haulinfo selectByPrimaryKey(String bigid);

    int updateByExampleSelective(@Param("record") Haulinfo record, @Param("example") HaulinfoExample example);

    int updateByExample(@Param("record") Haulinfo record, @Param("example") HaulinfoExample example);

    int updateByPrimaryKeySelective(Haulinfo record);

    int updateByPrimaryKey(Haulinfo record);
}