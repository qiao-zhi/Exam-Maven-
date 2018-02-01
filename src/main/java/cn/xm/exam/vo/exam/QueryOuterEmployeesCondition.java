package cn.xm.exam.vo.exam;

/**
 * 封装查询外部员工查询条件的类
 * 
 * @author QiaoLiQiang
 * @time 2017年10月19日下午10:08:34
 */
public class QueryOuterEmployeesCondition {
	private String units;// 部门串(去掉最后一个逗号之后分隔字符串)
	private String name;// 姓名
	private String sex;// 性别(转换为0，1)
	private String isBlack;// 是否在黑名单
	private String idCode;// 部门串(去掉最后一个逗号之后分隔字符串)
	private String minusNum;// 部门串(去掉最后一个逗号之后分隔字符串)
	private String trainStatus;// 培训情况
	private String bigId;
	private String empType;
	
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
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
	public String getIsBlack() {
		return isBlack;
	}
	public void setIsBlack(String isBlack) {
		this.isBlack = isBlack;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	public String getMinusNum() {
		return minusNum;
	}
	public void setMinusNum(String minusNum) {
		this.minusNum = minusNum;
	}
	public String getTrainStatus() {
		return trainStatus;
	}
	public void setTrainStatus(String trainStatus) {
		this.trainStatus = trainStatus;
	}
	public String getBigId() {
		return bigId;
	}
	public void setBigId(String bigId) {
		this.bigId = bigId;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	
}
