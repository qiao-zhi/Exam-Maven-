package cn.xm.exam.vo.exam;

/**
 * 用于封装外部人员参加考试的映射结果
 * 
 * @author QiaoLiQiang
 * @time 2017年10月16日上午12:52:14
 */
public class ExamEmployeeOutQueryVo {
	private String employeeId;
	private String idCode;
	private String name;
	private String unitName;
	private String sex;
	private Float minusNum;
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
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Float getMinusNum() {
		return minusNum;
	}
	public void setMinusNum(Float minusNum) {
		this.minusNum = minusNum;
	}
	public int getTrainStatus() {
		return trainStatus;
	}
	public void setTrainStatus(int trainStatus) {
		this.trainStatus = trainStatus;
	}
	@Override
	public String toString() {
		return "ExamEmployeeOutQueryVo [employeeId=" + employeeId + ", idCode=" + idCode + ", name=" + name
				+ ", unitName=" + unitName + ", sex=" + sex + ", minusNum=" + minusNum + ", trainStatus=" + trainStatus
				+ "]";
	}
	
}
