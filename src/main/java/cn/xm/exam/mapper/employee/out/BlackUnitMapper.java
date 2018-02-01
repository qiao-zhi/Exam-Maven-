package cn.xm.exam.mapper.employee.out;

import cn.xm.exam.bean.employee.out.BlackUnit;
import cn.xm.exam.bean.employee.out.BlackUnitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlackUnitMapper {
    int countByExample(BlackUnitExample example);

    int deleteByExample(BlackUnitExample example);

    int deleteByPrimaryKey(Integer blackunitid);

    int insert(BlackUnit record);

    int insertSelective(BlackUnit record);

    List<BlackUnit> selectByExample(BlackUnitExample example);

    BlackUnit selectByPrimaryKey(Integer blackunitid);

    int updateByExampleSelective(@Param("record") BlackUnit record, @Param("example") BlackUnitExample example);

    int updateByExample(@Param("record") BlackUnit record, @Param("example") BlackUnitExample example);

    int updateByPrimaryKeySelective(BlackUnit record);

    int updateByPrimaryKey(BlackUnit record);
}