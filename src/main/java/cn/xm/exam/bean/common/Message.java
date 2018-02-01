package cn.xm.exam.bean.common;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;
/**
 * 查询年龄大于55岁的人()
 * @author QiaoLiQiang
 * @time 2018年1月25日下午4:03:02
 */
public class Message {
    private String messageid;

    private String name;

    private String idcode;

    private String sex;

    private Date birthday;

    private String emptype;

    private String isdispose;

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid == null ? null : messageid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcode() {
        return idcode;
    }

    public void setIdcode(String idcode) {
        this.idcode = idcode == null ? null : idcode.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
    @JSON(format="yyyy-MM-dd")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype == null ? null : emptype.trim();
    }

    public String getIsdispose() {
        return isdispose;
    }

    public void setIsdispose(String isdispose) {
        this.isdispose = isdispose == null ? null : isdispose.trim();
    }
}