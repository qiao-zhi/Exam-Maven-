package cn.xm.exam.bean.exam;

import java.util.Date;
import java.util.List;

import cn.xm.exam.bean.grade.Employeeexam;

public class Exam {
	private String examid;

	private String examname;

	private String paperid;

	private Date starttime;

	private Date endtime;

	private String traincontent;

	private String status;

	private String xueshi;

	private String bigid;

	private String bigstatus;

	private String examlevel;

	private String employeename;

	private Integer employeenum;

	private Date createtime;

	private String examtype;

	private String departmentid;

	private Integer answertime;

	private List<Employeeexam> employeeInExams;// 内部员工
	private List<Employeeexam> employeeOutExams;// 外部员工

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

	public String getTraincontent() {
		return traincontent;
	}

	public void setTraincontent(String traincontent) {
		this.traincontent = traincontent == null ? null : traincontent.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getXueshi() {
		return xueshi;
	}

	public void setXueshi(String xueshi) {
		this.xueshi = xueshi == null ? null : xueshi.trim();
	}

	public String getBigid() {
		return bigid;
	}

	public void setBigid(String bigid) {
		this.bigid = bigid == null ? null : bigid.trim();
	}

	public String getBigstatus() {
		return bigstatus;
	}

	public void setBigstatus(String bigstatus) {
		this.bigstatus = bigstatus == null ? null : bigstatus.trim();
	}

	public String getExamlevel() {
		return examlevel;
	}

	public void setExamlevel(String examlevel) {
		this.examlevel = examlevel == null ? null : examlevel.trim();
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename == null ? null : employeename.trim();
	}

	public Integer getEmployeenum() {
		return employeenum;
	}

	public String getExamtype() {
		return examtype;
	}

	public void setExamtype(String examtype) {
		this.examtype = examtype;
	}

	public void setEmployeenum(Integer employeenum) {
		this.employeenum = employeenum;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public List<Employeeexam> getEmployeeInExams() {
		return employeeInExams;
	}

	public void setEmployeeInExams(List<Employeeexam> employeeInExams) {
		this.employeeInExams = employeeInExams;
	}

	public List<Employeeexam> getEmployeeOutExams() {
		return employeeOutExams;
	}

	public void setEmployeeOutExams(List<Employeeexam> employeeOutExams) {
		this.employeeOutExams = employeeOutExams;
	}

	public Integer getAnswertime() {
		return answertime;
	}

	public void setAnswertime(Integer answertime) {
		this.answertime = answertime;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid == null ? null : departmentid.trim();
	}
}