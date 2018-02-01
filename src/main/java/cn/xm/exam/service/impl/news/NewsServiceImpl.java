package cn.xm.exam.service.impl.news;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.news.News;
import cn.xm.exam.mapper.common.custom.NewsCustomMapper;
import cn.xm.exam.service.news.NewsService;
import cn.xm.exam.utils.PageBean;
@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	private NewsCustomMapper newsMapper;
	
	@Override
	public PageBean<Map<String, Object>> getNews(int currentPage, int currentCount, Map<String, Object> map) throws Exception {

		//List<News> news = newsMapper.selectNewsConditions(map);
		
		PageBean pageBean = new PageBean<Map<String, Object>>();
		pageBean.setCurrentCount(currentCount);// 页大小
		pageBean.setCurrentPage(currentPage);// 当前页
		
		Integer totalCount = newsMapper.getNewsTotal(map);// 查询总数
		pageBean.setTotalCount(totalCount);// 总数
		
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);// 计算总页数
		pageBean.setTotalPage(totalPage);// 总页数
		
		int index = (currentPage - 1) * currentCount;
		map.put("index", index);
		map.put("currentCount", currentCount);
		
		
		List<Map<String, Object>> news = newsMapper.selectNewsConditions(map);
		pageBean.setProductList(news);
		
		return pageBean;
	}

	/**
	 * 添加新闻信息
	 */
	@Override
	public boolean addNews(News news) throws Exception {
		
		boolean insertNewsResult = false;
		
		int res = newsMapper.insertNews(news);
		
		if(res == 1) {
			insertNewsResult = true;
		}
		
		return insertNewsResult;
	}

	/**
	 * 通过id删除新闻
	 */
	public boolean deleteNewsById(String newsId) throws Exception {

		boolean deleteNewsResult = false;

		int res = newsMapper.deleteNews(newsId);
		
		if(res == 1) {
			deleteNewsResult = true;
		}
		
		return deleteNewsResult;
	}

	/**
	 * 通过新闻的javabean修改新闻
	 */
	@Override
	public boolean updateNewsByNews(News news) throws Exception {
		
		
		boolean updateNewsResult = false;
		
		int res = newsMapper.updateNews(news);

		if(res == 1) {
			updateNewsResult = true;
		}
		
		return updateNewsResult;
	}

	/**
	 * 批量删除新闻  根据新闻的id
	 */
	@Override
	public boolean deleteAllNewsById(String newsId) throws Exception {
		
		boolean deleteAllNewsResult = false;
		
		String[] newsIds = newsId.split(",");
		
		int res = newsMapper.deleteAllNews(newsIds);
		
		int idsLength = newsIds.length; 
		
		if(res == idsLength) {
			deleteAllNewsResult = true;
		}
		//否则回显
		
		return deleteAllNewsResult;
	}

	@Override
	public News getNewsContentById(String newsId) throws Exception {

		return newsMapper.selectNewsContentById(newsId);
	}

	@Override
	public News getNewsById(String newsId) {

		return newsMapper.selectNewsById(newsId);
	}

}
