package cn.xm.exam.mapper.haul.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 大修员工mapper
 * 
 * @author QiaoLiQiang
 * @time 2017年11月15日下午1:37:38
 */
public interface HaulemployeeoutCustomMapper {
	/**
	 * 根据大修ID与单位ID查出大修员工的所有编号
	 * 
	 * @param bididAndUnitid
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getBigEmployeeoutIdcardsByBigidAndUnitid(Map<String, Object> bididAndUnitid) throws SQLException;
}
