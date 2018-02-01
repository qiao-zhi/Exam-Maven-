package cn.xm.exam.mapper.haul;

import cn.xm.exam.bean.haul.Haulunit;
import cn.xm.exam.bean.haul.HaulunitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HaulunitMapper {
    int countByExample(HaulunitExample example);

    int deleteByExample(HaulunitExample example);

    int deleteByPrimaryKey(String unitbigid);

    int insert(Haulunit record);

    int insertSelective(Haulunit record);

    List<Haulunit> selectByExample(HaulunitExample example);

    Haulunit selectByPrimaryKey(String unitbigid);

    int updateByExampleSelective(@Param("record") Haulunit record, @Param("example") HaulunitExample example);

    int updateByExample(@Param("record") Haulunit record, @Param("example") HaulunitExample example);

    int updateByPrimaryKeySelective(Haulunit record);

    int updateByPrimaryKey(Haulunit record);
}