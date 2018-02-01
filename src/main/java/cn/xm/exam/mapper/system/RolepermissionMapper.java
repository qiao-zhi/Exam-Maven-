package cn.xm.exam.mapper.system;

import cn.xm.exam.bean.system.RolepermissionExample;
import cn.xm.exam.bean.system.RolepermissionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolepermissionMapper {
    int countByExample(RolepermissionExample example);

    int deleteByExample(RolepermissionExample example);

    int deleteByPrimaryKey(RolepermissionKey key);

    int insert(RolepermissionKey record);

    int insertSelective(RolepermissionKey record);

    List<RolepermissionKey> selectByExample(RolepermissionExample example);

    int updateByExampleSelective(@Param("record") RolepermissionKey record, @Param("example") RolepermissionExample example);

    int updateByExample(@Param("record") RolepermissionKey record, @Param("example") RolepermissionExample example);
}