package cn.xm.exam.service.trainContent;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.trainContent.Traincontenttype;

/**
 * 培训内容类别service
 * @author QiaoLiQiang
 * @time 2018年3月6日下午8:11:49
 */
public interface TraincontenttypeService {
	/**
	 * 增加培训类别
	 * @param trainContentType
	 * @return
	 */
	public boolean addTraincontenttype(Traincontenttype trainContentType)throws SQLException;
	/**
	 * 删除培训内容类别(删除自己以及自己的下级的视频以及删除类别)
	 * @param typeId
	 * @return
	 */
	public boolean deleteTraincontenttypeById(String typeId) throws SQLException;
	/**
	 * 修改培训内容类别
	 * @param trainContentType
	 * @return
	 */
	public boolean updateTraincontenttypeById(Traincontenttype trainContentType)throws SQLException;
	/**
	 * 获取培训内容类别树
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getTraincontenttypeTree()throws SQLException;
	/**
	 * 根据编号查询培训内容类别
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Traincontenttype getTraincontenttypeById(String id)throws SQLException;
}
