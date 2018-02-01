package cn.xm.exam.bean.employee.out;

public class Employeeoutdistribute {
    private Integer distributeid;

    private String bigid;

    private String haulempid;

    private String unitid;

    private String departmentid;

    private String outempname;

    private String empoutidcard;

    private String empoutexamstatus;

    private String empouttraingrade;

    public Integer getDistributeid() {
        return distributeid;
    }

    public void setDistributeid(Integer distributeid) {
        this.distributeid = distributeid;
    }

    public String getBigid() {
        return bigid;
    }

    public void setBigid(String bigid) {
        this.bigid = bigid == null ? null : bigid.trim();
    }

    public String getHaulempid() {
        return haulempid;
    }

    public void setHaulempid(String haulempid) {
        this.haulempid = haulempid == null ? null : haulempid.trim();
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }

    public String getOutempname() {
        return outempname;
    }

    public void setOutempname(String outempname) {
        this.outempname = outempname == null ? null : outempname.trim();
    }

    public String getEmpoutidcard() {
        return empoutidcard;
    }

    public void setEmpoutidcard(String empoutidcard) {
        this.empoutidcard = empoutidcard == null ? null : empoutidcard.trim();
    }

    public String getEmpoutexamstatus() {
        return empoutexamstatus;
    }

    public void setEmpoutexamstatus(String empoutexamstatus) {
        this.empoutexamstatus = empoutexamstatus == null ? null : empoutexamstatus.trim();
    }

    public String getEmpouttraingrade() {
        return empouttraingrade;
    }

    public void setEmpouttraingrade(String empouttraingrade) {
        this.empouttraingrade = empouttraingrade == null ? null : empouttraingrade.trim();
    }
}