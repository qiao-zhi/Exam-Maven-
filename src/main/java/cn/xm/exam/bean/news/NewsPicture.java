package cn.xm.exam.bean.news;

public class NewsPicture {

	private String newsPId;
	private String newsPCurFileName;
	private String newsPOldFileName;
	
	public NewsPicture() {
		super();
	}
	public NewsPicture(String newsPCurFileName, String newsPOldFileName) {
		super();
		this.newsPCurFileName = newsPCurFileName;
		this.newsPOldFileName = newsPOldFileName;
	}
	public NewsPicture(String newsPId, String newsPCurFileName, String newsPOldFileName) {
		super();
		this.newsPId = newsPId;
		this.newsPCurFileName = newsPCurFileName;
		this.newsPOldFileName = newsPOldFileName;
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

	
}
