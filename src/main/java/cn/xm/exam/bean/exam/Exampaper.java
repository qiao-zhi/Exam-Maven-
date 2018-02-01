package cn.xm.exam.bean.exam;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

public class Exampaper {
	private String paperid;

	private Float paperscore;

	private Date maketime;

	private String level;

	private String employeename;

	private String title;

	private String paperanswer;

	private Integer usetimes;

	private String description;

	private String departmentid;
	
	private List<Bigquestion> bigQuestions;

	//增加考试ID
	private String examid;
	
	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid == null ? null : paperid.trim();
	}

	public Float getPaperscore() {
		return paperscore;
	}

	public void setPaperscore(Float paperscore) {
		this.paperscore = paperscore;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getMaketime() {
		return maketime;
	}

	public void setMaketime(Date maketime) {
		this.maketime = maketime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level == null ? null : level.trim();
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename == null ? null : employeename.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getPaperanswer() {
		return paperanswer;
	}

	public void setPaperanswer(String paperanswer) {
		this.paperanswer = paperanswer == null ? null : paperanswer.trim();
	}

	public Integer getUsetimes() {
		return usetimes;
	}

	public void setUsetimes(Integer usetimes) {
		this.usetimes = usetimes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public List<Bigquestion> getBigQuestions() {
		return bigQuestions;
	}

	public void setBigQuestions(List<Bigquestion> bigQuestions) {
		this.bigQuestions = bigQuestions;
	}
    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }

	public String getExamid() {
		return examid;
	}

	public void setExamid(String examid) {
		this.examid = examid;
	}
    
}