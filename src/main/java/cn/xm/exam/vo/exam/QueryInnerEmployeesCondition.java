package cn.xm.exam.vo.exam;

/**
 * 封装查询内部员工的条件(所有的条件都要根据数据库的字段类型进行对应的转换)
 * 
 * @author QiaoLiQiang
 * @time 2017年10月19日下午10:08:12
 */
public class QueryInnerEmployeesCondition {

	private String departments;// 部门串(去掉最后一个逗号之后分隔字符串)
	private String name;// 姓名
	private String sex;// 性别(转换为0，1)
	private String idCode;// 部门串(去掉最后一个逗号之后分隔字符串)
	private String trainStatus;// 培训情况

	public String getDepartments() {
		return departments;
	}

	public void setDepartments(String departments) {
		this.departments = departments;
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

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getTrainStatus() {
		return trainStatus;
	}

	public void setTrainStatus(String trainStatus) {
		this.trainStatus = trainStatus;
	}

}
