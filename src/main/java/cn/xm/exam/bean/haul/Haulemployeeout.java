package cn.xm.exam.bean.haul;

/**
 * 参加大修的外部员工
 * 
 * @author QiaoLiQiang
 * @time 2017年11月9日下午9:11:38
 */
public class Haulemployeeout {
	private String bigemployeeoutid;// 编号

	private String unitid;// 单位ID

	private String employeeid;// 员工id

	private String bigid;// 大修ID

	private String empoutidcard;// 身份证号

	private String trainstatus;// 培训状态

	private String emptype;// 员工工种

	private String empoutphone;// 电话

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
}