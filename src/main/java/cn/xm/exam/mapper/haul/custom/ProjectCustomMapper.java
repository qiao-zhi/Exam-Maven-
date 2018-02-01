package cn.xm.exam.mapper.haul.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.haul.Project;

/**
 * 工程mapper
 * @author QiaoLiQiang
 * @time 2018年1月22日下午10:08:52
 */
public interface ProjectCustomMapper {
	/**
	 * 批量加入工程
	 * @param projectnames
	 * @return
	 * @throws SQLException
	 */
	public int insertProjectBatch(List<Project> projectnames)throws SQLException;
	/**
	 * 根据大修ID查询标段信息
	 * @return
	 * @pram haulId检修ID
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getProjectsByBigId(String haulId)throws SQLException;
}
