package cn.xm.exam.mapper.employee.in.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.employee.in.EmployeeInInfo;
import cn.xm.exam.vo.exam.ExamEmployeeInQueryVo;

@SuppressWarnings("all")
public interface EmployeeInCustomMapper {

	/**************** S qlq **********************/
	/******** S 查询参加考试的人 ****************/

	/**
	 * 条件根据查询内部参考员工
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getExamEmployeeIns(Map condition) throws SQLException;

	/******** E 查询参加考试的人 ****************/
	/**************** E qlq **********************/
	
	/**************** S zhangwenyi **********************/
	List<EmployeeIn> findEmployeeInByCondition(Map<String, Object> condition);

	int getEmployeeInCountByCondition(Map<String, Object> condition);

	EmployeeIn findEmployeeInById(String employeeId);

	int getCountByDepartmentId(String departmentId);

	boolean batchImportEmployeeIn(EmployeeInInfo employeeInInfo);

	List<String> getALLEmployeeInByDepartmentId(String departmentid);

	/**
	 * 用于获取长委单位的所有员工
	 * @param departmentid
	 * @return
	 */
	List<String> getALLEmployeeInByDepartmentId2(String departmentid);
	
	public int addEmployeeInBatch(List<EmployeeIn> employeeInList);
	//判断该员工是否在永久黑名单中
	public String isBlackList(String idcode);
	
	//判断该部门是否是内部部门
	public String isNeibu(String yincangbumenid);
	
	//得到员工信息
	public List<Map<String, Object>> getEmpCaseInfoByCondition(Map<String, Object> condition);


	//通过身份证得到员工信息
	public EmployeeIn getEmployeeInByIdcode(String idcode);

	public void deleteBlackrulesById(String idCode);

	/**************** E zhangwenyi **********************/
}
