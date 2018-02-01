package cn.xm.exam.service.impl.news;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.news.News;
import cn.xm.exam.mapper.common.custom.IndexNewsCustomMapper;
import cn.xm.exam.service.news.IndexNewsService;
import cn.xm.exam.utils.PageBean;

@Service
@SuppressWarnings("all")
public class IndexNewsServiceImpl implements IndexNewsService {

	@Autowired
	private IndexNewsCustomMapper indexNewsMapper;

	@Override
	public List<News> getNews(Map<String, Object> codition) throws Exception {
		List<News> list = null;

		list = indexNewsMapper.selectTypeNews(codition);

		return list;
	}

	@Override
	public News getNewsById(Map<String, Object> codition) throws Exception {

		News news = null;

		news = indexNewsMapper.selectNewsById(codition);

		return news;
	}

	@Override
	public PageBean<News> getNewsPageBean(Integer currentPage, Integer currentCount, Map<String, Object> condition) {
		PageBean pageBean = new PageBean<News>();
		pageBean.setCurrentCount(currentCount);// 页大小
		pageBean.setCurrentPage(currentPage);// 当前页
		Integer totalCount = indexNewsMapper.getNewsPagePageTotal(condition);// 查询总数
		pageBean.setTotalCount(totalCount);// 总数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);// 计算总页数
		pageBean.setTotalPage(totalPage);// 总页数
		/**
		 * 页数 大小 第一页 0 8 第二页 8 8
		 */
		int index = (currentPage - 1) * currentCount;
		condition.put("index", index);
		condition.put("currentCount", currentCount);
		List<News> list = indexNewsMapper.getNewsPagePage(condition);
		pageBean.setProductList(list);
		return pageBean;
	}

}
