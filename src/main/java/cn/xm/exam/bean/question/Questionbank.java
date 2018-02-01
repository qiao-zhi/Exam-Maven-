package cn.xm.exam.bean.question;

import java.util.Date;

public class Questionbank {
    private String questionbankid;

    private String questionbankname;

    private String departmentid;

    private String employeename;

    private Date createtime;

    private String isuse;

    private String description;

    private String categorynameid;

    public String getQuestionbankid() {
        return questionbankid;
    }

    public void setQuestionbankid(String questionbankid) {
        this.questionbankid = questionbankid == null ? null : questionbankid.trim();
    }

    public String getQuestionbankname() {
        return questionbankname;
    }

    public void setQuestionbankname(String questionbankname) {
        this.questionbankname = questionbankname == null ? null : questionbankname.trim();
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename == null ? null : employeename.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse == null ? null : isuse.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCategorynameid() {
        return categorynameid;
    }

    public void setCategorynameid(String categorynameid) {
        this.categorynameid = categorynameid == null ? null : categorynameid.trim();
    }
}