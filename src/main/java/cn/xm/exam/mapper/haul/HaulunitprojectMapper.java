package cn.xm.exam.mapper.haul;

import cn.xm.exam.bean.haul.Haulunitproject;
import cn.xm.exam.bean.haul.HaulunitprojectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HaulunitprojectMapper {
    int countByExample(HaulunitprojectExample example);

    int deleteByExample(HaulunitprojectExample example);

    int deleteByPrimaryKey(Integer haulunitprojectid);

    int insert(Haulunitproject record);

    int insertSelective(Haulunitproject record);

    List<Haulunitproject> selectByExample(HaulunitprojectExample example);

    Haulunitproject selectByPrimaryKey(Integer haulunitprojectid);

    int updateByExampleSelective(@Param("record") Haulunitproject record, @Param("example") HaulunitprojectExample example);

    int updateByExample(@Param("record") Haulunitproject record, @Param("example") HaulunitprojectExample example);

    int updateByPrimaryKeySelective(Haulunitproject record);

    int updateByPrimaryKey(Haulunitproject record);
}