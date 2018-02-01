package cn.xm.exam.mapper.employee;

import cn.xm.exam.bean.employee.Pictureindex;
import cn.xm.exam.bean.employee.PictureindexExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PictureindexMapper {
    int countByExample(PictureindexExample example);

    int deleteByExample(PictureindexExample example);

    int deleteByPrimaryKey(String pictureid);

    int insert(Pictureindex record);

    int insertSelective(Pictureindex record);

    List<Pictureindex> selectByExample(PictureindexExample example);

    Pictureindex selectByPrimaryKey(String pictureid);

    int updateByExampleSelective(@Param("record") Pictureindex record, @Param("example") PictureindexExample example);

    int updateByExample(@Param("record") Pictureindex record, @Param("example") PictureindexExample example);

    int updateByPrimaryKeySelective(Pictureindex record);

    int updateByPrimaryKey(Pictureindex record);
}