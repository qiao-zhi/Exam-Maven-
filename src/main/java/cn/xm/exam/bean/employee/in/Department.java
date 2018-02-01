package cn.xm.exam.bean.employee.in;

public class Department {
    private String departmentid;

    private String updepartmentid;

    private String departmentname;

    private String employeename;

    private String phone;

    private String description;

    private String sort;

    private String departprojectnames;

    private String departmenttype;

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }

    public String getUpdepartmentid() {
        return updepartmentid;
    }

    public void setUpdepartmentid(String updepartmentid) {
        this.updepartmentid = updepartmentid == null ? null : updepartmentid.trim();
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname == null ? null : departmentname.trim();
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename == null ? null : employeename.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getDepartprojectnames() {
        return departprojectnames;
    }

    public void setDepartprojectnames(String departprojectnames) {
        this.departprojectnames = departprojectnames == null ? null : departprojectnames.trim();
    }

    public String getDepartmenttype() {
        return departmenttype;
    }

    public void setDepartmenttype(String departmenttype) {
        this.departmenttype = departmenttype == null ? null : departmenttype.trim();
    }
}