package cn.xm.exam.service.log;

import java.sql.SQLException;

import cn.xm.exam.bean.log.Logtable;

/**
 * 日志Service
 * 
 * @author liqiang
 *
 */
public interface LogtableService {
	/**
	 * 增加日志
	 * @param log
	 * @return
	 * @throws SQLException
	 */
	public boolean addLog(Logtable log) throws SQLException;
}
