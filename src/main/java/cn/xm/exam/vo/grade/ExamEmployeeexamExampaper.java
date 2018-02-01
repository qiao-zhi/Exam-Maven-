package cn.xm.exam.vo.grade;

import java.util.Date;

/**   
*    
* 项目名称：Exam   
* 类名称：ExamEmployeeexamExampaper   
* 类描述： 视图exam_employeeExam_examPaper的实体类
* 考试相关信息，参加考试的总人数，及格人数  
* 创建人：Leilong  
* 创建时间：2017年10月8日 下午3:01:00   
* @version    
*    
*/
public class ExamEmployeeexamExampaper {
    private String examid;

    private String examname;

    private String paperid;

    private Date starttime;

    private Date endtime;

    private String address;

    private String status;

    private String employeeid;

    private String employeename;
    
    private String bigstatus;

    private String answer;

    private String description;

    private String createpapername;

    private String level;

    private Date maketime;

    private Float paperscore;

    private String title;

    private Integer usetimes;

    private Integer sumperson;

    private Integer countpassperson;
    
    private String departmentid;
    
    private String exammethod;
    
    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid == null ? null : examid.trim();
    }

    public String getExamname() {
        return examname;
    }

    public void setExamname(String examname) {
        this.examname = examname == null ? null : examname.trim();
    }

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid == null ? null : paperid.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid == null ? null : employeeid.trim();
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename == null ? null : employeename.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCreatepapername() {
        return createpapername;
    }

    public void setCreatepapername(String createpapername) {
        this.createpapername = createpapername == null ? null : createpapername.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Date getMaketime() {
        return maketime;
    }

    public void setMaketime(Date maketime) {
        this.maketime = maketime;
    }

    public Float getPaperscore() {
        return paperscore;
    }

    public void setPaperscore(Float paperscore) {
        this.paperscore = paperscore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getUsetimes() {
        return usetimes;
    }

    public void setUsetimes(Integer usetimes) {
        this.usetimes = usetimes;
    }

	public Integer getSumperson() {
		return sumperson;
	}

	public void setSumperson(Integer sumperson) {
		this.sumperson = sumperson;
	}

	public Integer getCountpassperson() {
		return countpassperson;
	}

	public void setCountpassperson(Integer countpassperson) {
		this.countpassperson = countpassperson;
	}

	public String getBigstatus() {
		return bigstatus;
	}

	public void setBigstatus(String bigstatus) {
		this.bigstatus = bigstatus;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getExammethod() {
		return exammethod;
	}

	public void setExammethod(String exammethod) {
		this.exammethod = exammethod;
	}
	
	
    
}