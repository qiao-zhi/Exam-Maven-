package cn.xm.exam.mapper.exam.custom;

import java.sql.SQLException;

/**
 * 大题mapper接口
 * 
 * @author QiaoLiQiang
 * @time 2017年10月12日上午11:57:49
 */
public interface BigQuestionCustomMapper {
	/**
	 * 根据试卷ID删除大题
	 * 
	 * @param paperID
	 * @return
	 * @throws SQLException
	 */
	public int deleteBigQuestionsBypaperId(String paperID) throws SQLException;
}
