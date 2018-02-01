package cn.xm.exam.bean.grade;

public class Employeeexam {
    private Integer gradeid;

    private String examid;

    private String employeeid;

    private String employeename;

    private Float grade;

    private String exammethod;

    private String employeetype;

    private String unitid;

    private Integer distributeid;

    private String bigemployeeoutid;

    public Integer getGradeid() {
        return gradeid;
    }

    public void setGradeid(Integer gradeid) {
        this.gradeid = gradeid;
    }

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid == null ? null : examid.trim();
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

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getExammethod() {
        return exammethod;
    }

    public void setExammethod(String exammethod) {
        this.exammethod = exammethod == null ? null : exammethod.trim();
    }

    public String getEmployeetype() {
        return employeetype;
    }

    public void setEmployeetype(String employeetype) {
        this.employeetype = employeetype == null ? null : employeetype.trim();
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public Integer getDistributeid() {
        return distributeid;
    }

    public void setDistributeid(Integer distributeid) {
        this.distributeid = distributeid;
    }

    public String getBigemployeeoutid() {
        return bigemployeeoutid;
    }

    public void setBigemployeeoutid(String bigemployeeoutid) {
        this.bigemployeeoutid = bigemployeeoutid == null ? null : bigemployeeoutid.trim();
    }
}