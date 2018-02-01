package cn.xm.exam.mapper.exam;

import cn.xm.exam.bean.exam.Onlineexamanswerinfor;
import cn.xm.exam.bean.exam.OnlineexamanswerinforExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OnlineexamanswerinforMapper {
    int countByExample(OnlineexamanswerinforExample example);

    int deleteByExample(OnlineexamanswerinforExample example);

    int deleteByPrimaryKey(String answerinfoid);

    int insert(Onlineexamanswerinfor record);

    int insertSelective(Onlineexamanswerinfor record);

    List<Onlineexamanswerinfor> selectByExample(OnlineexamanswerinforExample example);

    Onlineexamanswerinfor selectByPrimaryKey(String answerinfoid);

    int updateByExampleSelective(@Param("record") Onlineexamanswerinfor record, @Param("example") OnlineexamanswerinforExample example);

    int updateByExample(@Param("record") Onlineexamanswerinfor record, @Param("example") OnlineexamanswerinforExample example);

    int updateByPrimaryKeySelective(Onlineexamanswerinfor record);

    int updateByPrimaryKey(Onlineexamanswerinfor record);
}