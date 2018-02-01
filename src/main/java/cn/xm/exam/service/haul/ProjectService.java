package cn.xm.exam.service.haul;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.haul.Project;

public interface ProjectService {
	/**
	 * 根据检修ID查询工程信息
	 * @param haulId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getProjectInfoByHaulId(String haulId)throws SQLException;
}
