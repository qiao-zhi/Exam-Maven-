package cn.xm.exam.service.haul;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 大修员工Service
 * 
 * @author QiaoLiQiang
 * @time 2017年11月15日下午1:25:30
 */
public interface HaulemployeeoutService {

	/**
	 * 根据大修ID与单位ID查出大修员工的所有编号
	 * 
	 * @param bididAndUnitid
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getBigEmployeeoutIdsByBigidAndUnitid(Map<String, Object> bididAndUnitid) throws SQLException;
}
