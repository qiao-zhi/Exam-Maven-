package cn.xm.exam.bean.employee.out;

import java.util.Date;

public class Blacklist {
    private Integer id;

    private String employeeid;

    private Date time;

    private String description;

    private String blackidcard;

    private String temporaryinstatus;

    private String employeestatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid == null ? null : employeeid.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getBlackidcard() {
        return blackidcard;
    }

    public void setBlackidcard(String blackidcard) {
        this.blackidcard = blackidcard == null ? null : blackidcard.trim();
    }

    public String getTemporaryinstatus() {
        return temporaryinstatus;
    }

    public void setTemporaryinstatus(String temporaryinstatus) {
        this.temporaryinstatus = temporaryinstatus == null ? null : temporaryinstatus.trim();
    }

    public String getEmployeestatus() {
        return employeestatus;
    }

    public void setEmployeestatus(String employeestatus) {
        this.employeestatus = employeestatus == null ? null : employeestatus.trim();
    }
}