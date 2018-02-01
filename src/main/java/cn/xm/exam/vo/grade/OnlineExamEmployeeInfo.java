package cn.xm.exam.vo.grade;

import java.util.Date;

/**   
*    
* 项目名称：Exam   
* 类名称：OnlineExamEmployeeInfo   
* 类描述：内部员工的在线考试的相关信息的实体类
* 创建人：Leilong
* 创建时间：2017年11月1日 上午9:16:19    
* @version    
*    
*/
public class OnlineExamEmployeeInfo {
    private String examid;

    private String examname;

    private Date starttime;

    private Date endtime;

    private String status;

    private String paperid;
    
    private String answertime;
    
    private String level;

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

    private Double singlesum;

    private Double singlescore;

    private Double multiplesum;

    private Double multiplescore;

    private Double trueorfalsesum;

    private Double trueorfalsescore;

    private Date startanswertime;

    private Date endanswertime;

    private Date logintime;

    private String ipaddress;

    private String answerstatus;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid == null ? null : paperid.trim();
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

    public Double getSinglesum() {
        return singlesum;
    }

    public void setSinglesum(Double singlesum) {
        this.singlesum = singlesum;
    }

    public Double getSinglescore() {
        return singlescore;
    }

    public void setSinglescore(Double singlescore) {
        this.singlescore = singlescore;
    }

    public Double getMultiplesum() {
        return multiplesum;
    }

    public void setMultiplesum(Double multiplesum) {
        this.multiplesum = multiplesum;
    }

    public Double getMultiplescore() {
        return multiplescore;
    }

    public void setMultiplescore(Double multiplescore) {
        this.multiplescore = multiplescore;
    }

    public Double getTrueorfalsesum() {
        return trueorfalsesum;
    }

    public void setTrueorfalsesum(Double trueorfalsesum) {
        this.trueorfalsesum = trueorfalsesum;
    }

    public Double getTrueorfalsescore() {
        return trueorfalsescore;
    }

    public void setTrueorfalsescore(Double trueorfalsescore) {
        this.trueorfalsescore = trueorfalsescore;
    }

    public Date getStartanswertime() {
        return startanswertime;
    }

    public void setStartanswertime(Date startanswertime) {
        this.startanswertime = startanswertime;
    }

    public Date getEndanswertime() {
        return endanswertime;
    }

    public void setEndanswertime(Date endanswertime) {
        this.endanswertime = endanswertime;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress == null ? null : ipaddress.trim();
    }

    public String getAnswerstatus() {
        return answerstatus;
    }

    public void setAnswerstatus(String answerstatus) {
        this.answerstatus = answerstatus == null ? null : answerstatus.trim();
    }

	public String getAnswertime() {
		return answertime;
	}

	public void setAnswertime(String answertime) {
		this.answertime = answertime;
	}
    
    
}