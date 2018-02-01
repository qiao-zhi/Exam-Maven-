package cn.xm.exam.mapper.common.custom;

import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.news.News;

public interface IndexNewsCustomMapper {

	// 为主页分类不同类型的新闻
	List<News> selectTypeNews(Map<String, Object> codition);

	// 通过id 显示新闻，用于显示新闻文章
	News selectNewsById(Map<String, Object> codition);

	/***** S 分页 **********/
	/**
	 * 获取总数
	 * 
	 * @param condition
	 * @return
	 */
	int getNewsPagePageTotal(Map<String, Object> condition);

	/**
	 * 获取树据
	 * 
	 * @param condition
	 * @return
	 */
	List<News> getNewsPagePage(Map<String, Object> condition);
	/***** E 分页 **********/
}
