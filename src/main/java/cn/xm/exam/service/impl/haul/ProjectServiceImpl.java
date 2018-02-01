package cn.xm.exam.service.impl.haul;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xm.exam.mapper.haul.custom.ProjectCustomMapper;
import cn.xm.exam.service.haul.ProjectService;
@Service
public class ProjectServiceImpl implements ProjectService {
	@Resource
	private ProjectCustomMapper projectCustomMapper ;
	@Override
	public List<Map<String,Object>> getProjectInfoByHaulId(String haulId) throws SQLException {
		return projectCustomMapper.getProjectsByBigId(haulId);
	}

}
