package cn.xm.exam.bean.employee.out;

import java.util.Date;

public class EmployeeOut {
    private String employeeid;

    private String employeenumber;

    private String name;

    private String idcode;

    private String sex;

    private Date birthday;

    private String address;

    private String finger;

    private String headaddress;

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid == null ? null : employeeid.trim();
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber == null ? null : employeenumber.trim();
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getFinger() {
        return finger;
    }

    public void setFinger(String finger) {
        this.finger = finger == null ? null : finger.trim();
    }

    public String getHeadaddress() {
        return headaddress;
    }

    public void setHeadaddress(String headaddress) {
        this.headaddress = headaddress == null ? null : headaddress.trim();
    }
}