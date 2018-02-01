package cn.xm.exam.bean.haul;

public class Haulunitproject {
    private Integer haulunitprojectid;

    private String bigid;

    private String unitid;

    private String projectid;

    public Integer getHaulunitprojectid() {
        return haulunitprojectid;
    }

    public void setHaulunitprojectid(Integer haulunitprojectid) {
        this.haulunitprojectid = haulunitprojectid;
    }

    public String getBigid() {
        return bigid;
    }

    public void setBigid(String bigid) {
        this.bigid = bigid == null ? null : bigid.trim();
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid == null ? null : projectid.trim();
    }
}