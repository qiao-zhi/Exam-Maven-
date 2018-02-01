package cn.xm.exam.mapper.exam;

import cn.xm.exam.bean.exam.Onlineexaminfor;
import cn.xm.exam.bean.exam.OnlineexaminforExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OnlineexaminforMapper {
    int countByExample(OnlineexaminforExample example);

    int deleteByExample(OnlineexaminforExample example);

    int deleteByPrimaryKey(String onlineexamid);

    int insert(Onlineexaminfor record);

    int insertSelective(Onlineexaminfor record);

    List<Onlineexaminfor> selectByExample(OnlineexaminforExample example);

    Onlineexaminfor selectByPrimaryKey(String onlineexamid);

    int updateByExampleSelective(@Param("record") Onlineexaminfor record, @Param("example") OnlineexaminforExample example);

    int updateByExample(@Param("record") Onlineexaminfor record, @Param("example") OnlineexaminforExample example);

    int updateByPrimaryKeySelective(Onlineexaminfor record);

    int updateByPrimaryKey(Onlineexaminfor record);
}