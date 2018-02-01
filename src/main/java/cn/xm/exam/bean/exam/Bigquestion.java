package cn.xm.exam.bean.exam;

import java.util.List;

import org.apache.struts2.views.jsp.SetTag;

public class Bigquestion {
	private String bigquertionid;

	private String paperid;

	private String bigquestionname;

	private Integer bigquestionsequence;
	// 大题试题集合
	private List<Exampaperquestion> questions;

	public String getBigquertionid() {
		return bigquertionid;
	}

	public void setBigquertionid(String bigquertionid) {
		this.bigquertionid = bigquertionid == null ? null : bigquertionid.trim();
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid == null ? null : paperid.trim();
	}

	public String getBigquestionname() {
		return bigquestionname;
	}

	public void setBigquestionname(String bigquestionname) {
		this.bigquestionname = bigquestionname == null ? null : bigquestionname.trim();
	}

	public Integer getBigquestionsequence() {
		return bigquestionsequence;
	}

	public void setBigquestionsequence(Integer bigquestionsequence) {
		this.bigquestionsequence = bigquestionsequence;
	}

	public List<Exampaperquestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Exampaperquestion> questions) {
		this.questions = questions;
	}

}