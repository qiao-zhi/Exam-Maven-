package cn.xm.exam.bean.grade;

public class Historyemployeeexam {
    private String gradeid;

    private String examid;

    private String employeeid;

    private String employeename;

    private Float grade;

    private String exammethod;

    private String employeetype;

    public String getGradeid() {
        return gradeid;
    }

    public void setGradeid(String gradeid) {
        this.gradeid = gradeid == null ? null : gradeid.trim();
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
}