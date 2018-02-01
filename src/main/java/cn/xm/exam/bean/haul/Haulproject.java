package cn.xm.exam.bean.haul;

public class Haulproject {
    private Integer haulprojectid;

    private String bigid;

    private String projectid;

    public Integer getHaulprojectid() {
        return haulprojectid;
    }

    public void setHaulprojectid(Integer haulprojectid) {
        this.haulprojectid = haulprojectid;
    }

    public String getBigid() {
        return bigid;
    }

    public void setBigid(String bigid) {
        this.bigid = bigid == null ? null : bigid.trim();
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid == null ? null : projectid.trim();
    }
}