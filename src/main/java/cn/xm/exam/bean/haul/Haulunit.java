package cn.xm.exam.bean.haul;

public class Haulunit {
    private String unitbigid;

    private String unitid;

    private String bigid;

    private Integer unitminismum;

    private String isnewbig;

    private String projectnames;

    private String manager;

    private String managerphone;

    private String secure;

    private String securephone;

    public String getUnitbigid() {
        return unitbigid;
    }

    public void setUnitbigid(String unitbigid) {
        this.unitbigid = unitbigid == null ? null : unitbigid.trim();
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public String getBigid() {
        return bigid;
    }

    public void setBigid(String bigid) {
        this.bigid = bigid == null ? null : bigid.trim();
    }

    public Integer getUnitminismum() {
        return unitminismum;
    }

    public void setUnitminismum(Integer unitminismum) {
        this.unitminismum = unitminismum;
    }

    public String getIsnewbig() {
        return isnewbig;
    }

    public void setIsnewbig(String isnewbig) {
        this.isnewbig = isnewbig == null ? null : isnewbig.trim();
    }

    public String getProjectnames() {
        return projectnames;
    }

    public void setProjectnames(String projectnames) {
        this.projectnames = projectnames == null ? null : projectnames.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getManagerphone() {
        return managerphone;
    }

    public void setManagerphone(String managerphone) {
        this.managerphone = managerphone == null ? null : managerphone.trim();
    }

    public String getSecure() {
        return secure;
    }

    public void setSecure(String secure) {
        this.secure = secure == null ? null : secure.trim();
    }

    public String getSecurephone() {
        return securephone;
    }

    public void setSecurephone(String securephone) {
        this.securephone = securephone == null ? null : securephone.trim();
    }
}