package cn.xm.exam.mapper.haul.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 单位大修Mapper
 * 
 * @author QiaoLiQiang
 * @time 2017年11月17日下午12:23:53
 */
public interface HaulunitCustomMapper {
	/**
	 * 通过大修Id查询参加本次大修的部门ID
	 * 
	 * @param haulId
	 *            大修ID
	 * @return 参加本次大修的外来单位的ID集合
	 * @throws SQLException
	 */
	public List<String> selectUnitidsByHaulId(@Param("haulId")String haulId,@Param("departmentId")String departmentId) throws SQLException;

}
