package cn.xm.exam.mapper.haul;

import cn.xm.exam.bean.haul.Haulemployeeout;
import cn.xm.exam.bean.haul.HaulemployeeoutExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HaulemployeeoutMapper {
    int countByExample(HaulemployeeoutExample example);

    int deleteByExample(HaulemployeeoutExample example);

    int deleteByPrimaryKey(String bigemployeeoutid);

    int insert(Haulemployeeout record);

    int insertSelective(Haulemployeeout record);

    List<Haulemployeeout> selectByExample(HaulemployeeoutExample example);

    Haulemployeeout selectByPrimaryKey(String bigemployeeoutid);

    int updateByExampleSelective(@Param("record") Haulemployeeout record, @Param("example") HaulemployeeoutExample example);

    int updateByExample(@Param("record") Haulemployeeout record, @Param("example") HaulemployeeoutExample example);

    int updateByPrimaryKeySelective(Haulemployeeout record);

    int updateByPrimaryKey(Haulemployeeout record);
}