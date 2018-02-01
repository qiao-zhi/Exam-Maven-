package cn.xm.exam.bean.news;

import java.util.Date;

public class News {

	private String newsId;
	private String newsTitle;
	private String newsContent;
	private String newsPerson;
	private Date newsTime;
	private String newsType;
	
	public News() {
		super();
	}
	
	public News(String newsTitle, String newsContent, String newsPerson, Date newsTime,
			String newsType) {
		super();
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsPerson = newsPerson;
		this.newsTime = newsTime;
		this.newsType = newsType;
	}
	
	public News(String newsId, String newsTitle, String newsContent, String newsPerson, Date newsTime,
			String newsType) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsPerson = newsPerson;
		this.newsTime = newsTime;
		this.newsType = newsType;
	}
	
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public String getNewsPerson() {
		return newsPerson;
	}
	public void setNewsPerson(String newsPerson) {
		this.newsPerson = newsPerson;
	}
	public Date getNewsTime() {
		return newsTime;
	}
	public void setNewsTime(Date newsTime) {
		this.newsTime = newsTime;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	
	
}
