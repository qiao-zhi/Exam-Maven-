package cn.xm.exam.vo.employee.out;

import java.util.Date;

/**   
*    
* 项目名称：Exam   
* 类名称：EmployeeOutBaseInfo   
* 类描述： 外部员工基本信息视图的实体类employee_out_base_info
* 创建人：Leilong   
* 创建时间：2017年11月12日 下午9:01:49    
* @version    
*    
*/
public class EmployeeOutBaseInfo {
    private String name;

    private String idcard;

    private String sex;

    private String address;

    private Date birthday;

    private String photo;

    private String bigemployeeoutid;

    private String unitid;

    private String employeeid;

    private String bigid;

    private String empoutidcard;

    private String trainstatus;

    private String emptype;

    private String empoutphone;

    private Date bigbegindate;

    private Date bigcreatedate;

    private String bigdescription;

    private Date bigenddate;

    private String bigname;

    private String bigstatus;

    private String departmentname;

    private int minusnum;

    private int minusnumsum;

    private String isblacklist;
    
    private String isinblacklist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getBigemployeeoutid() {
        return bigemployeeoutid;
    }

    public void setBigemployeeoutid(String bigemployeeoutid) {
        this.bigemployeeoutid = bigemployeeoutid == null ? null : bigemployeeoutid.trim();
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid == null ? null : employeeid.trim();
    }

    public String getBigid() {
        return bigid;
    }

    public void setBigid(String bigid) {
        this.bigid = bigid == null ? null : bigid.trim();
    }

    public String getEmpoutidcard() {
        return empoutidcard;
    }

    public void setEmpoutidcard(String empoutidcard) {
        this.empoutidcard = empoutidcard == null ? null : empoutidcard.trim();
    }

    public String getTrainstatus() {
        return trainstatus;
    }

    public void setTrainstatus(String trainstatus) {
        this.trainstatus = trainstatus == null ? null : trainstatus.trim();
    }

    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype == null ? null : emptype.trim();
    }

    public String getEmpoutphone() {
        return empoutphone;
    }

    public void setEmpoutphone(String empoutphone) {
        this.empoutphone = empoutphone == null ? null : empoutphone.trim();
    }

    public Date getBigbegindate() {
        return bigbegindate;
    }

    public void setBigbegindate(Date bigbegindate) {
        this.bigbegindate = bigbegindate;
    }

    public Date getBigcreatedate() {
        return bigcreatedate;
    }

    public void setBigcreatedate(Date bigcreatedate) {
        this.bigcreatedate = bigcreatedate;
    }

    public String getBigdescription() {
        return bigdescription;
    }

    public void setBigdescription(String bigdescription) {
        this.bigdescription = bigdescription == null ? null : bigdescription.trim();
    }

    public Date getBigenddate() {
        return bigenddate;
    }

    public void setBigenddate(Date bigenddate) {
        this.bigenddate = bigenddate;
    }

    public String getBigname() {
        return bigname;
    }

    public void setBigname(String bigname) {
        this.bigname = bigname == null ? null : bigname.trim();
    }

    public String getBigstatus() {
        return bigstatus;
    }

    public void setBigstatus(String bigstatus) {
        this.bigstatus = bigstatus == null ? null : bigstatus.trim();
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname == null ? null : departmentname.trim();
    }   

    public int getMinusnum() {
		return minusnum;
	}

	public void setMinusnum(int minusnum) {
		this.minusnum = minusnum;
	}

	public int getMinusnumsum() {
		return minusnumsum;
	}

	public void setMinusnumsum(int minusnumsum) {
		this.minusnumsum = minusnumsum;
	}

	public String getIsblacklist() {
        return isblacklist;
    }

    public void setIsblacklist(String isblacklist) {
        this.isblacklist = isblacklist == null ? null : isblacklist.trim();
    }

	public String getIsinblacklist() {
		return isinblacklist;
	}

	public void setIsinblacklist(String isinblacklist) {
		this.isinblacklist = isinblacklist;
	}
    
    
}