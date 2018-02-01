package cn.xm.exam.vo.grade;

import java.util.Date;

/**   
*    
* 项目名称：Exam   
* 类名称：EmployeeExamGrade   
* 类描述： 视图employee_exam_grade的实体类
* 存放员工成绩的相关信息
* 创建人：Leilong  
* 创建时间：2017年10月9日 下午8:57:24   
* @version    
*    
*/
public class EmployeeExamGrade {
	private String examid;
	
    private String examname;

    private Date starttime;

    private Date endtime;
    
    private String status;
    
    private String paperid;
    
    private String bigstatus;
    
    private String bigid;

    private String level;
    
    private String departmentid;
    
    private Float paperscore;

    private String employeeid;

    private String employeename;

    private String employeetype;

    private String exammethod;

    private Float grade;

    private String ispass;

    private String sex;

    private String idcode;

    private String departmentname;

    public String getExamname() {
        return examname;
    }

    public void setExamname(String examname) {
        this.examname = examname == null ? null : examname.trim();
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Float getPaperscore() {
        return paperscore;
    }

    public void setPaperscore(Float paperscore) {
        this.paperscore = paperscore;
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

    public String getEmployeetype() {
        return employeetype;
    }

    public void setEmployeetype(String employeetype) {
        this.employeetype = employeetype == null ? null : employeetype.trim();
    }

    public String getExammethod() {
        return exammethod;
    }

    public void setExammethod(String exammethod) {
        this.exammethod = exammethod == null ? null : exammethod.trim();
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getIspass() {
        return ispass;
    }

    public void setIspass(String ispass) {
        this.ispass = ispass == null ? null : ispass.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getIdcode() {
        return idcode;
    }

    public void setIdcode(String idcode) {
        this.idcode = idcode == null ? null : idcode.trim();
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname == null ? null : departmentname.trim();
    }

	public String getExamid() {
		return examid;
	}

	public void setExamid(String examid) {
		this.examid = examid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public String getBigstatus() {
		return bigstatus;
	}

	public void setBigstatus(String bigstatus) {
		this.bigstatus = bigstatus;
	}

	public String getBigid() {
		return bigid;
	}

	public void setBigid(String bigid) {
		this.bigid = bigid;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
    
    
}