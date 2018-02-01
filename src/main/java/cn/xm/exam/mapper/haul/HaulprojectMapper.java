package cn.xm.exam.mapper.haul;

import cn.xm.exam.bean.haul.Haulproject;
import cn.xm.exam.bean.haul.HaulprojectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HaulprojectMapper {
    int countByExample(HaulprojectExample example);

    int deleteByExample(HaulprojectExample example);

    int deleteByPrimaryKey(Integer haulprojectid);

    int insert(Haulproject record);

    int insertSelective(Haulproject record);

    List<Haulproject> selectByExample(HaulprojectExample example);

    Haulproject selectByPrimaryKey(Integer haulprojectid);

    int updateByExampleSelective(@Param("record") Haulproject record, @Param("example") HaulprojectExample example);

    int updateByExample(@Param("record") Haulproject record, @Param("example") HaulprojectExample example);

    int updateByPrimaryKeySelective(Haulproject record);

    int updateByPrimaryKey(Haulproject record);
}