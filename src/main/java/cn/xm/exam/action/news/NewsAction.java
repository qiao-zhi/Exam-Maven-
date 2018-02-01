package cn.xm.exam.action.news;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.news.News;
import cn.xm.exam.service.news.NewsService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ValidateCheck;

@Controller
@Scope("prototype")
public class NewsAction extends ActionSupport {

	private Logger logger = Logger.getLogger(NewsAction.class);
	private String currentPage;
	private String currentCount;
	private String newsTitle; // 新闻标题
	private String newsType; // 新闻类型
	private String newsId; // 考试ID
	private String newsIds; // 批量删除id数据

	// 实例化要转成json的文本集合
	private Map<String, Object> map;
	private News news;

	@Resource
	private NewsService newsService;

	/**
	 * 添加新闻
	 * 
	 * @return
	 */
	public String addNews() {
		// 初始化要转成json的Map集合
		map = new LinkedHashMap<String, Object>();

		boolean flag = false;
		try {
			flag = newsService.addNews(news);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!flag) {
			return "addNews";
		}

		return "newsManage";
	}

	/**
	 * 修改新闻
	 * 
	 * @return
	 */
	public String updateNews() {
		// 初始化要转成json的Map集合
		map = new LinkedHashMap<String, Object>();

		boolean flag = false;
		try {
			flag = newsService.updateNewsByNews(news);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!flag) {
			return "modifyNews";
		}

		return "newsManage";
	}

	/**
	 * 根据id删除单条新闻
	 * 
	 * @return
	 */
	public String delNewsById() {
		// 初始化要转成json的Map集合
		map = new LinkedHashMap<String, Object>();

		boolean flag = false;

		try {
			flag = newsService.deleteNewsById(newsId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (flag) {
			map.put("result", "该条新闻删除成功");
		} else {
			map.put("result", "该条新闻删除失败，请重新删除");
		}

		return SUCCESS;
	}

	/**
	 * 批量删除新闻 (通过获取的好几个新闻的id)
	 * 
	 * @return
	 */
	public String delNewsBatchByIds() {
		// 将要转成json的Map集合实例化
		map = new LinkedHashMap<String, Object>();

		boolean flag = false;
		try {
			flag = newsService.deleteAllNewsById(newsIds);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (flag) {
			map.put("result", "批量删除成功");
		} else {
			map.put("result", "批量删除失败");
		}

		return SUCCESS;
	}

	/**
	 * 通过id查询新闻内容
	 * 
	 * @return
	 */
	public String findNewsContent() {
		map = new LinkedHashMap<String, Object>();
		News news = new News();

		try {
			news = newsService.getNewsContentById(newsId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("news", news);

		return SUCCESS;
	}

	/**
	 * 通过 id 查询news 返回给 modifyNews
	 * 
	 * @return
	 */
	public String findNewsById() {
		map = new LinkedHashMap<String, Object>();
		News news = new News();

		try {
			news = newsService.getNewsById(newsId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 修改news 对象的 newsContent 格式
		String el_newsContent = news.getNewsContent();

		System.out.println("...." + el_newsContent);
		el_newsContent = el_newsContent.replace("\r\n", "");
		System.out.println(".." + el_newsContent);

		news.setNewsContent(el_newsContent);

		map.put("news", news);

		return "modifyNews";
	}

	/**
	 * 查询所有的新闻
	 * 
	 * @return
	 */
	public String findAllNews() {

		// 实例化要转成json的Map集合
		map = new LinkedHashMap<String, Object>();
		map = generateCondition(map);

		PageBean<Map<String, Object>> pageBean = null;
		try {
			pageBean = newsService.getNews(Integer.valueOf(currentPage), Integer.valueOf(currentCount), map);
		} catch (NumberFormatException e) {
			logger.error("转换数字异常", e);
		} catch (Exception e) {
			logger.error("查询新闻异常", e);
		}

		// news.get
		map.put("newsTitle", newsTitle);
		map.put("newsType", newsType);

		map.put("pageBean", pageBean);

		return SUCCESS;
	}

	/**
	 * 封装查询条件
	 * 
	 * @param condition
	 * @return
	 */
	private Map<String, Object> generateCondition(Map<String, Object> condition) {
		if (ValidateCheck.isNull(currentPage)) {
			currentPage = "1";
		}
		if (ValidateCheck.isNull(currentCount)) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
		if (ValidateCheck.isNotNull(newsTitle)) {
			condition.put("newsTitle", newsTitle);
		}
		if (ValidateCheck.isNotNull(newsType)) {
			condition.put("newsType", newsType);
		}
		return condition;
	}

	// get,set
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getNewsIds() {
		return newsIds;
	}

	public void setNewsIds(String newsIds) {
		this.newsIds = newsIds;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(String currentCount) {
		this.currentCount = currentCount;
	}
}
