package cn.xm.exam.mapper.trainContent.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.xm.exam.bean.trainContent.Traincontenttype;

/**
 * 培训类别手写的mapper
 *
 * @author QiaoLiQiang
 * @time 2018年3月7日上午10:27:17
 */
public interface TraincontenttypeCustomMapper {

	/**
	 * 获取培训内容类别树
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getTraincontenttypeTree()throws SQLException;
	
	/**
	 * 根据上级编号获取最大的编号(如果不存在返回0)
	 * @param upId
	 * @return
	 * @throws SQLException
	 */
	public String getMaxTypeIdByUpId(@Param("upId")String upId)throws SQLException;
	/**
	 * 
	 * @param Traincontenttype
	 * @return
	 * @throws SQLException
	 */
	public int updateTraincontenttypeById(Traincontenttype Traincontenttype)throws SQLException;
	/**
	 * 根据编号查询培训内容类别
	 * @param Traincontenttype
	 * @return
	 * @throws SQLException
	 */
	public Traincontenttype selectTraincontenttypeById(String id)throws SQLException;
	/**
	 * 根据主键删除类别
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deleteTraincontenttypeById(@Param("id")String id)throws SQLException;
}
