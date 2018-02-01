package cn.xm.exam.service.news;

import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.news.News;
import cn.xm.exam.utils.PageBean;

/**
 * 主页新闻显示
 * 
 * @author yorge
 *
 */
public interface IndexNewsService {

	/**
	 * 为主页分配新闻
	 * 
	 * @param newsType
	 *            新闻类型
	 * @param newsNums
	 *            数量
	 * @return
	 * @throws Exception
	 */
	public List<News> getNews(Map<String, Object> codition) throws Exception;

	/**
	 * 根据主页传过来的id 查询出该条新闻的详细内容
	 * 
	 * @param newsId
	 *            条件：id 、type
	 * @return
	 * @throws Exception
	 */
	public News getNewsById(Map<String, Object> codition) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param currentPage
	 * @param currentCount
	 * @param condition
	 * @return
	 */
	public PageBean<News> getNewsPageBean(Integer currentPage, Integer currentCount, Map<String, Object> condition);

}
