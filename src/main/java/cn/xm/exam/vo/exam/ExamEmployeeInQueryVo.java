package cn.xm.exam.vo.exam;

/**
 * 用于封装查询内部考试人员的映射结果
 * 
 * @author QiaoLiQiang
 * @time 2017年10月16日上午12:51:21
 */
public class ExamEmployeeInQueryVo {

	private String employeeId;
	private String idCode;
	private String name;
	private String sex;
	private String departmentName;
	private int trainStatus;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getTrainStatus() {
		return trainStatus;
	}

	public void setTrainStatus(int trainStatus) {
		this.trainStatus = trainStatus;
	}

	@Override
	public String toString() {
		return "ExamEmployeeInQueryVo [employeeId=" + employeeId + ", idCode=" + idCode + ", name=" + name + ", sex="
				+ sex + ", departmentName=" + departmentName + ", trainStatus=" + trainStatus + "]";
	}


}
