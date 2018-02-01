package cn.xm.exam.mapper.common.custom;

import java.util.List;

import cn.xm.exam.bean.news.NewsPicture;

/**
 * 联动图片
 */
public interface NewsPictureCustomMapper {

    //查询
    List<NewsPicture> selectAllnewsPicture();
    
    //批量添加
    int insertnewsPictureBatch(List<NewsPicture> news);
    
    //批量删除
    int deletenewsPicturesBatch();
    
    //查询图片数量
    int newsPicturesNums();
    
}
