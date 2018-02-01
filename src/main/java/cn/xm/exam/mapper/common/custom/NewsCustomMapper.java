package cn.xm.exam.mapper.common.custom;

import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.news.News;

/**
* news
*/
public interface NewsCustomMapper {
	
	//查询总条数
	int getNewsTotal(Map<String, Object> map);
	
    //多条件查询表格信息
	List<Map<String, Object>> selectNewsConditions(Map<String, Object> map);
	 
	News selectNewsContentById(String newsId);
    
    //添加一条新闻
    int insertNews(News news);

    //修改新闻内容
    int updateNews(News news);
    
    //删除一条新闻
    int deleteNews(String newsId);
    
    //批量删除新闻
    int deleteAllNews(String[] newsIds);

    //通过id 获取news ，用于修改
	News selectNewsById(String newsId);
}
