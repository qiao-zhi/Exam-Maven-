package cn.xm.exam.service.impl.haul;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.mapper.haul.custom.HaulemployeeoutCustomMapper;
import cn.xm.exam.service.haul.HaulemployeeoutService;

@Service
public class HaulemployeeoutServiceImpl implements HaulemployeeoutService {
	@Autowired
	private HaulemployeeoutCustomMapper haulemployeeoutCustomMapper;

	@Override
	public List<Map<String,Object>> getBigEmployeeoutIdsByBigidAndUnitid(Map<String, Object> bididAndUnitid) throws SQLException {
		return haulemployeeoutCustomMapper.getBigEmployeeoutIdcardsByBigidAndUnitid(bididAndUnitid);
	}

}
