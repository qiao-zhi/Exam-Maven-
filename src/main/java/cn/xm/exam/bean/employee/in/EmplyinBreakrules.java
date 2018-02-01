package cn.xm.exam.bean.employee.in;

import java.util.Date;

public class EmplyinBreakrules {
    private Integer empinbreakid;

    private String empinemployeeid;

    private String empinbreakcontent;

    private Date empinbreaktime;

    private Integer empinminusnum;

    public Integer getEmpinbreakid() {
        return empinbreakid;
    }

    public void setEmpinbreakid(Integer empinbreakid) {
        this.empinbreakid = empinbreakid;
    }

    public String getEmpinemployeeid() {
        return empinemployeeid;
    }

    public void setEmpinemployeeid(String empinemployeeid) {
        this.empinemployeeid = empinemployeeid == null ? null : empinemployeeid.trim();
    }

    public String getEmpinbreakcontent() {
        return empinbreakcontent;
    }

    public void setEmpinbreakcontent(String empinbreakcontent) {
        this.empinbreakcontent = empinbreakcontent == null ? null : empinbreakcontent.trim();
    }

    public Date getEmpinbreaktime() {
        return empinbreaktime;
    }

    public void setEmpinbreaktime(Date empinbreaktime) {
        this.empinbreaktime = empinbreaktime;
    }

    public Integer getEmpinminusnum() {
        return empinminusnum;
    }

    public void setEmpinminusnum(Integer empinminusnum) {
        this.empinminusnum = empinminusnum;
    }
}