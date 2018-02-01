package cn.xm.exam.service.news;

import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.news.News;
import cn.xm.exam.utils.PageBean;

/**
 * 新闻信息的服务层操作
 * @author yorge
 * @time 2017年10月30日下午4:00:20
 */
public interface NewsService {

	/**
	 * 获取所有新闻
	 * @return
	 * @throws Exception
	 */
	public PageBean<Map<String, Object>> getNews(int currentPage, int currentCount, Map<String, Object> map) throws Exception;
	
	/**
	 * 通过 id 获取新闻内容
	 * @param newsId
	 * @return
	 * @throws Exception
	 */
	public News getNewsContentById(String newsId) throws Exception;
	
	/**
	 * 添加新闻
	 * @param news
	 * @return
	 * @throws Exception
	 */
	public boolean addNews(News news) throws Exception;
	
	/**
	 * 通过 id 删除新闻
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteNewsById(String newsId) throws Exception;
	
	/**
	 * 通过 id 批量删除新闻
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAllNewsById(String newsIds) throws Exception;
	
	/**
	 * 通过新闻的javabean 修改新闻
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean updateNewsByNews(News news) throws Exception;

	/**
	 * 通过 id 查询 news ，用于modify的显示
	 * @param newsId
	 * @return
	 */
	public News getNewsById(String newsId);

	
	/**
	 * 分页查询所有的新闻信息
	 * @param currentPage
	 * @param currentTotal
	 * @param condition
	 * @return
	 * @throws Exception
	 */
}
