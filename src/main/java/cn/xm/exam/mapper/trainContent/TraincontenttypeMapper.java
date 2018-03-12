package cn.xm.exam.mapper.trainContent;

import cn.xm.exam.bean.trainContent.Traincontenttype;
import cn.xm.exam.bean.trainContent.TraincontenttypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TraincontenttypeMapper {
    int countByExample(TraincontenttypeExample example);

    int deleteByExample(TraincontenttypeExample example);

    int insert(Traincontenttype record);

    int insertSelective(Traincontenttype record);

    List<Traincontenttype> selectByExample(TraincontenttypeExample example);

    int updateByExampleSelective(@Param("record") Traincontenttype record, @Param("example") TraincontenttypeExample example);

    int updateByExample(@Param("record") Traincontenttype record, @Param("example") TraincontenttypeExample example);
}