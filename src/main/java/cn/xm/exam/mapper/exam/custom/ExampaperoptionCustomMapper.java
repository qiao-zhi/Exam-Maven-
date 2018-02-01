package cn.xm.exam.mapper.exam.custom;

import java.sql.SQLException;

/**
 * 选项dao接口
 * 
 * @author QiaoLiQiang
 * @time 2017年10月12日上午11:37:31
 */
public interface ExampaperoptionCustomMapper {
	/**
	 * 根据试卷ID删除选项
	 * 
	 * @param paperID
	 *            试卷ID
	 * @return
	 * @throws SQLException
	 */
	public int deleteOptionsBypaperId(String paperID) throws SQLException;
}
