package cn.xm.exam.service.employee.out;

import java.sql.SQLException;

import cn.xm.exam.bean.employee.out.BlackUnit;
import cn.xm.exam.utils.PageBean;

/**
 * 单位黑名单Service
 * @author QiaoLiQiang
 * @time 2018年1月10日下午2:14:15
 */
public interface BlackUnitService {

	/**
	 * 添加单位黑名单
	 * @param BlackUnit
	 * @return
	 * @throws SQLException
	 */
	public boolean addBlackUnit(BlackUnit blackUnit)throws SQLException;
	/**
	 * 删除黑名单单位
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteBlackUnitByBlackId(int  id)throws SQLException;
	/**
	 * 修改黑名單單位
	 * @param blackUnit
	 * @return
	 * @throws SQLException
	 */
	public boolean updateBlackUnitByBlackId(BlackUnit  blackUnit)throws SQLException;
	/**
	 * 分页查询黑名单单位
	 * @param currentPage
	 * @param currentCount
	 * @return
	 * @throws SQLException
	 */
	public  PageBean<BlackUnit> getBlackUnitPage(int currentPage,int currentCount)throws SQLException;
	
	
	
}
