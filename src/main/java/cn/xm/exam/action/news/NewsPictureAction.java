package cn.xm.exam.action.news;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.news.NewsPicture;
import cn.xm.exam.service.news.NewsPictureService;
import cn.xm.exam.utils.FileUtils;

/**
 * @author yorge
 *
 */
@Controller
@Scope("prototype")
public class NewsPictureAction extends ActionSupport {

	private String newsPId; // id
	private String newsPCurFileName;// 图片途径 src
	private String newsPOldFileName;// 文件原路径
	private String imgSrces; // 图片路径  数组。用于图片存储
	private String imgNames; // 图片名字  数组。发送到业务层

	// 图片上传
	private File[] fileName;
	private String[] fileNameContentType;
	private String[] fileNameFileName;

	private Map<String, Object> map;

	// @Resource
	private NewsPicture newsP;

	@Resource
	private NewsPictureService newsPService;

	/**
	 * 先删除图片，添加图片
	 * 
	 * @return
	 */
	public String delAddBatchNewsP() {

		System.out.println("1234556789"); 

		boolean flag = false;
	
		map = new HashMap<String, Object>();

		try {
			flag = newsPService.del_addNewsPicture(imgNames, imgSrces);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (flag) {
			map.put("result", "轮播图片修改成功");
		} else {
			map.put("result", "轮播图片修改失败，请重新修改");
		}

		return SUCCESS;
	}

	/**
	 * 查询图片
	 * 
	 * @return
	 */
	public String findNewsP() {

		map = new HashMap<String, Object>();
		List<NewsPicture> newsPs = new ArrayList<NewsPicture>();
		try {
			newsPs = newsPService.getNewsPictures();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		map.put("newsPs", newsPs);

		return SUCCESS;
	}

	public String getNewsPId() {
		return newsPId;
	}

	public void setNewsPId(String newsPId) {
		this.newsPId = newsPId;
	}

	public String getNewsPCurFileName() {
		return newsPCurFileName;
	}

	public void setNewsPCurFileName(String newsPCurFileName) {
		this.newsPCurFileName = newsPCurFileName;
	}

	public String getNewsPOldFileName() {
		return newsPOldFileName;
	}

	public void setNewsPOldFileName(String newsPOldFileName) {
		this.newsPOldFileName = newsPOldFileName;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public NewsPicture getNewsP() {
		return newsP;
	}

	public void setNewsP(NewsPicture newsP) {
		this.newsP = newsP;
	}

	public NewsPictureService getNewsPService() {
		return newsPService;
	}

	public void setNewsPService(NewsPictureService newsPService) {
		this.newsPService = newsPService;
	}

	public String getImgSrces() {
		return imgSrces;
	}

	public void setImgSrces(String imgSrces) {
		this.imgSrces = imgSrces;
	}

	public File[] getFileName() {
		return fileName;
	}

	public void setFileName(File[] fileName) {
		this.fileName = fileName;
	}

	public String[] getFileNameContentType() {
		return fileNameContentType;
	}

	public void setFileNameContentType(String[] fileNameContentType) {
		this.fileNameContentType = fileNameContentType;
	}

	public String[] getFileNameFileName() {
		return fileNameFileName;
	}

	public void setFileNameFileName(String[] fileNameFileName) {
		this.fileNameFileName = fileNameFileName;
	}

	public String getImgNames() {
		return imgNames;
	}

	public void setImgNames(String imgNames) {
		this.imgNames = imgNames;
	}

}
