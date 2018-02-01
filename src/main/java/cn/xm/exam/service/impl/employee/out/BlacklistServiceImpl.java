package cn.xm.exam.service.impl.employee.out;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xm.exam.bean.employee.out.BlackUnit;
import cn.xm.exam.mapper.employee.out.BlackUnitMapper;
import cn.xm.exam.mapper.employee.out.custom.BlackUnitCustomMapper;
import cn.xm.exam.service.employee.out.BlackUnitService;
import cn.xm.exam.utils.PageBean;
/**
 * 黑名单单位服务
 * @author QiaoLiQiang
 * @time 2018年1月10日下午2:20:52
 */
@Service
public class BlacklistServiceImpl implements BlackUnitService {
	@Resource
	private BlackUnitMapper blackUnitMapper;//导出的mapper
	@Resource
	private BlackUnitCustomMapper blackUnitCustomMapper;
	@Override
	public boolean addBlackUnit(BlackUnit blackUnit) throws SQLException {
		// TODO Auto-generated method stub
		return blackUnitMapper.insert(blackUnit)>0?true:false;
	}

	@Override
	public boolean deleteBlackUnitByBlackId(int id) throws SQLException {
		// TODO Auto-generated method stub
		return blackUnitMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public boolean updateBlackUnitByBlackId(BlackUnit blackUnit) throws SQLException {
		// TODO Auto-generated method stub
		return blackUnitMapper.updateByPrimaryKeySelective(blackUnit)>0?true:false;
	}

	@Override
	public PageBean<BlackUnit> getBlackUnitPage(int currentPage, int currentCount) throws SQLException {
		PageBean<BlackUnit> pageBean = new PageBean<BlackUnit>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		int totalCount = blackUnitMapper.countByExample(null);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(totalCount*1.0/currentCount);
		pageBean.setTotalPage(totalPage);
		int index = (currentPage-1)*currentCount;
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("index", index);
		condition.put("currentCount",currentCount);
		List<BlackUnit> productList = blackUnitCustomMapper.getBlackUnitPage(condition);
		pageBean.setProductList(productList);
		return pageBean;
	}

}
