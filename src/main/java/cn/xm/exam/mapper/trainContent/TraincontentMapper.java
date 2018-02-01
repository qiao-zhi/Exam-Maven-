package cn.xm.exam.mapper.trainContent;

import cn.xm.exam.bean.trainContent.Traincontent;
import cn.xm.exam.bean.trainContent.TraincontentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TraincontentMapper {
    int countByExample(TraincontentExample example);

    int deleteByExample(TraincontentExample example);

    int deleteByPrimaryKey(Integer documentid);

    int insert(Traincontent record);

    int insertSelective(Traincontent record);

    List<Traincontent> selectByExample(TraincontentExample example);

    Traincontent selectByPrimaryKey(Integer documentid);

    int updateByExampleSelective(@Param("record") Traincontent record, @Param("example") TraincontentExample example);

    int updateByExample(@Param("record") Traincontent record, @Param("example") TraincontentExample example);

    int updateByPrimaryKeySelective(Traincontent record);

    int updateByPrimaryKey(Traincontent record);
}