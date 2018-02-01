package cn.xm.exam.bean.employee.out;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;


public class Breakrules {
    private Integer breakid;

    private String employeeid;
    
    private String bigemployeeoutid;

    private String breakcontent;

    private Date breaktime;

    private Integer minusnum;

    public Integer getBreakid() {
        return breakid;
    }

    public void setBreakid(Integer breakid) {
        this.breakid = breakid;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid == null ? null : employeeid.trim();
    }

    public String getBigemployeeoutid() {
        return bigemployeeoutid;
    }

    public void setBigemployeeoutid(String bigemployeeoutid) {
        this.bigemployeeoutid = bigemployeeoutid == null ? null : bigemployeeoutid.trim();
    }

    public String getBreakcontent() {
        return breakcontent;
    }

    public void setBreakcontent(String breakcontent) {
        this.breakcontent = breakcontent == null ? null : breakcontent.trim();
    }
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public Date getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(Date breaktime) {
        this.breaktime = breaktime;
    }

    public Integer getMinusnum() {
        return minusnum;
    }

    public void setMinusnum(Integer minusnum) {
        this.minusnum = minusnum;
    }
}