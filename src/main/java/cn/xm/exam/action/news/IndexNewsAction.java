package cn.xm.exam.action.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.news.News;
import cn.xm.exam.service.news.IndexNewsService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ValidateCheck;

@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class IndexNewsAction extends ActionSupport {

	private Logger log = Logger.getLogger(getClass());
	private String newsType;// 新闻种类 0 消息 1 通知
	// 查详细信息的
	private String newsId;

	@Resource
	private IndexNewsService service;

	// 实例化要转成json的文本集合
	private Map<String, Object> map;
	private News news;

	/**
	 * 分页查询消息
	 * 
	 * @return
	 */
	public String getTypeNews() {
		log.debug("in..............");
		// 初始化要转成json的Map集合
		map = new HashMap<String, Object>();

		Map<String, Object> codition = new HashMap<String, Object>();

		codition.put("newsType", newsType);

		List<News> list = new ArrayList<News>();
		try {
			list = service.getNews(codition);
		} catch (Exception e) {
			e.printStackTrace();
		}

		map.put("newsList", list);

		return SUCCESS;
	}

	/****** S 分页处查询消息通知 *******/
	// 查询消息的分页
	private String selectType;// 新闻种类 0 新闻 1 通知
	private String currentPage;// 当前页
	private String currentCount;// 页大小

	public String getNewsPage() {
		// 初始化要转成json的Map集合
		map = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();
		String type = "";
		if (selectType != null && "0".equals(selectType)) {
			type = "新闻";
		}
		if (selectType != null && "1".equals(selectType)) {
			type = "通知";
		}
		condition.put("type", type);
		if (ValidateCheck.isNull(currentPage)) {
			currentPage = "1";
		}
		if (ValidateCheck.isNull(currentCount)) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
		// 调用service查询数据
		PageBean<News> pageBean = service.getNewsPageBean(Integer.valueOf(currentPage), Integer.valueOf(currentCount),
				condition);
		map.put("pageBean", pageBean);
		return SUCCESS;
	}

	/****** E 分页处查询消息通知 *******/

	/**
	 * 分页查询通知
	 * 
	 * @return
	 */
	public String getTypeNoting() {
		log.debug("in..............");
		// 初始化要转成json的Map集合
		map = new HashMap<String, Object>();

		Map<String, Object> codition = new HashMap<String, Object>();

		codition.put("newsType", newsType);

		List<News> list = new ArrayList<News>();
		try {
			list = service.getNews(codition);
		} catch (Exception e) {
			e.printStackTrace();
		}

		map.put("newsList", list);

		return SUCCESS;
	}

	/**
	 * 通过id 和新闻类型，查询新闻，用于文章显示 查询到文章后，跳转到newsPageSon。jsp 页面
	 * 
	 * @return
	 */
	public String getNewsById() {

		map = new HashMap<String, Object>();

		News news = new News();

		Map<String, Object> codition = new HashMap<String, Object>();

		codition.put("newsId", newsId);
		codition.put("newsType", newsType.equals("0")?"新闻":"通知");

		try {
			news = service.getNewsById(codition);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("news", news);

		return "newsPageSon";
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public IndexNewsService getService() {
		return service;
	}

	public void setService(IndexNewsService service) {
		this.service = service;
	}

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

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
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

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

}
