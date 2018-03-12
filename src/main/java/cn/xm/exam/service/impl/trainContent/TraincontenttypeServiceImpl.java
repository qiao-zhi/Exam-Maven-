package cn.xm.exam.service.impl.trainContent;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.trainContent.Traincontent;
import cn.xm.exam.bean.trainContent.TraincontentExample;
import cn.xm.exam.bean.trainContent.Traincontenttype;
import cn.xm.exam.mapper.trainContent.TraincontentMapper;
import cn.xm.exam.mapper.trainContent.TraincontenttypeMapper;
import cn.xm.exam.mapper.trainContent.custom.TraincontenttypeCustomMapper;
import cn.xm.exam.service.trainContent.TraincontenttypeService;
@Service//服务层对象，交由spring管理
@SuppressWarnings("all")//压制警告
public class TraincontenttypeServiceImpl implements TraincontenttypeService {
	
	@Autowired//自动装配(btType)
	private TraincontenttypeMapper traincontenttypeMapper; 
	@Autowired//自动装配(btType)
	private TraincontentMapper traincontentMapper; 
	@Resource//自动装配
	private TraincontenttypeCustomMapper traincontenttypeCustomMapper; 
	@Override
	public boolean addTraincontenttype(Traincontenttype trainContentType) throws SQLException {
		String maxTypeId = traincontenttypeCustomMapper.getMaxTypeIdByUpId(trainContentType.getUpid());
		String typeId = null;
		if("0".equals(maxTypeId)){//不存在
			typeId = trainContentType.getUpid().toString()+"00";
		}else{
			typeId = String.valueOf(Integer.valueOf(maxTypeId)+1);//存在的话最大编号加1
		}
		trainContentType.setTypeid(typeId);
		int result = traincontenttypeMapper.insert(trainContentType);
		return result>0?true:false;
	}

	@Override
	public boolean deleteTraincontenttypeById(String typeId) throws SQLException {
		//1.删除视频
		TraincontentExample traincontentExample = new TraincontentExample();
		TraincontentExample.Criteria criteria = traincontentExample.createCriteria();
		criteria.andKnowledgetypeEqualTo("typeId");
		List<Traincontent> lists = traincontentMapper.selectByExample(traincontentExample);
			//1.1删除服务器视频与数据
		for(Traincontent traincontent:lists){
			traincontentMapper.deleteByPrimaryKey(traincontent.getDocumentid());//删除数据库数据
		}
		//2.删除培训类别
		return traincontenttypeCustomMapper.deleteTraincontenttypeById(typeId)>0?true:false;
	}

	@Override
	public boolean updateTraincontenttypeById(Traincontenttype trainContentType) throws SQLException {
		// TODO Auto-generated method stub
		return traincontenttypeCustomMapper.updateTraincontenttypeById(trainContentType)>0?true:false;
	}
	@Override
	public List<Map<String, Object>> getTraincontenttypeTree() throws SQLException {
		// TODO Auto-generated method stub
		return traincontenttypeCustomMapper.getTraincontenttypeTree();
	}
	@Override
	public Traincontenttype getTraincontenttypeById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return traincontenttypeCustomMapper.selectTraincontenttypeById(id);
	}
}
