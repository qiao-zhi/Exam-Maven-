package cn.xm.exam.bean.employee.in;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

public class EmployeeIn {
	private String employeeid;

	/**
	 * 员工编号
	 */
	private String employeenumber;

	private String name;

	/**
	 * 身份证号
	 */
	private String idcode;

	private String sex;

	/**
	 * 出生日期
	 */
	private Date birthday;

	/**
	 * 头像路径
	 */
	private String photo;

	private String phone;

	private String email;

	private String address;

	/**
	 * 职务
	 */
	private String duty;

	/**
	 * 所属部门id
	 */
	private String departmentid;

	/**
	 * 指纹
	 */
	private String finger;

	/**
	 * 是否删除(0,1标记)
	 */
	private String isdelete;

	/**
	 * 培训情况(int类型表示通过几级)
	 */
	private Integer trainstatus;

	/**
	 * 按时间排序
	 * 
	 * @return
	 */
	private String sort;
	private String departmentname;// 飞哥加的

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

	@JSON(format = "yyyy-MM-dd")
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty == null ? null : duty.trim();
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid == null ? null : departmentid.trim();
	}

	public String getFinger() {
		return finger;
	}

	public void setFinger(String finger) {
		this.finger = finger == null ? null : finger.trim();
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete == null ? null : isdelete.trim();
	}

	public Integer getTrainstatus() {
		return trainstatus;
	}

	public void setTrainstatus(Integer trainstatus) {
		this.trainstatus = trainstatus;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	@Override
	public String toString() {
		return "EmployeeIn [employeeid=" + employeeid + ", employeenumber=" + employeenumber + ", name=" + name
				+ ", idcode=" + idcode + ", sex=" + sex + ", birthday=" + birthday + ", photo=" + photo + ", phone="
				+ phone + ", email=" + email + ", address=" + address + ", duty=" + duty + ", departmentid="
				+ departmentid + ", finger=" + finger + ", isdelete=" + isdelete + ", trainstatus=" + trainstatus
				+ ", sort=" + sort + "]";
	}

}