package cn.xm.exam.mapper.employee.out.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.employee.out.BlackUnit;

/**
 * 黑名单单位mapper
 * @author LL
 *
 */
public interface BlackUnitCustomMapper {
	
	/**
	 * 查询单位黑名单信息分页显示
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<BlackUnit> getBlackUnitPage(Map<String,Object> condition)throws SQLException;
}
