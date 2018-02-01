package cn.xm.exam.bean.employee.in;

import java.util.Date;

public class EmployeeInInfo {
	
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
	private String birthday;

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

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getEmployeenumber() {
		return employeenumber;
	}

	public void setEmployeenumber(String employeenumber) {
		this.employeenumber = employeenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	/*public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}*/

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getFinger() {
		return finger;
	}

	public void setFinger(String finger) {
		this.finger = finger;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}

	public Integer getTrainstatus() {
		return trainstatus;
	}

	public void setTrainstatus(Integer trainstatus) {
		this.trainstatus = trainstatus;
	}

	
	
	public EmployeeInInfo() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "EmployeeInInfo [employeeid=" + employeeid + ", employeenumber=" + employeenumber + ", name=" + name
				+ ", idcode=" + idcode + ", sex=" + sex + ", birthday=" + birthday + ", photo=" + photo + ", phone="
				+ phone + ", email=" + email + ", address=" + address + ", duty=" + duty + ", departmentid="
				+ departmentid + ", finger=" + finger + ", isdelete=" + isdelete + ", trainstatus=" + trainstatus + "]";
	}

	public EmployeeInInfo(String employeeid, String employeenumber, String name, String idcode, String sex,
			String birthday, String photo, String phone, String email, String address, String duty, String departmentid,
			String finger, String isdelete, Integer trainstatus) {
		super();
		this.employeeid = employeeid;
		this.employeenumber = employeenumber;
		this.name = name;
		this.idcode = idcode;
		this.sex = sex;
		this.birthday = birthday;
		this.photo = photo;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.duty = duty;
		this.departmentid = departmentid;
		this.finger = finger;
		this.isdelete = isdelete;
		this.trainstatus = trainstatus;
	}


	

}
