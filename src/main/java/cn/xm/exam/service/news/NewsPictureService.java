package cn.xm.exam.service.news;

import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.news.NewsPicture;

/**
 * 联播图片服务层操作
 * @author yorge
 *
 */
public interface NewsPictureService {

	/**
	 * 获取所有联播图片
	 * @return
	 * @throws Exception
	 */
	public List<NewsPicture> getNewsPictures() throws Exception;
	
	/**
	 * 添加联播图片
	 * @param imgSrces 
	 * @param news
	 * @return
	 * @throws Exception
	 */
	public boolean del_addNewsPicture(String imgNames, String imgSrces) throws Exception;
	
}