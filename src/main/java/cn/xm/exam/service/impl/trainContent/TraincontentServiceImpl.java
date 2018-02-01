package cn.xm.exam.service.impl.trainContent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xm.exam.bean.trainContent.Traincontent;
import cn.xm.exam.mapper.trainContent.TraincontentMapper;
import cn.xm.exam.mapper.trainContent.custom.TraincontentCustomMapper;
import cn.xm.exam.service.trainContent.TraincontentService;
import cn.xm.exam.utils.PageBean;

/**
 * 培训资料的实现类
 * 
 * @author 贤元
 *
 */
@Service
public class TraincontentServiceImpl implements TraincontentService {

	@Resource
	private TraincontentMapper traincontentMapper;
	@Resource
	private TraincontentCustomMapper traincontentCustomMapper;

	// 增加培训资料
	// trainContent ： 要增加的培训记录
	// return 是否增加成功
	@Override
	public boolean addTrainContent(Traincontent trainContent) throws Exception {
		int result = traincontentMapper.insert(trainContent);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 删除培训资料，培训资料表没有被其它表引用，所以可以直接根据主键documentId文件编号删除
	@Override
	public boolean deleteTrainById(Integer id) throws Exception {
		// 根据主键删除
		int result = traincontentMapper.deleteByPrimaryKey(id);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 批量删除
	@Override
	public boolean deleteTrainContentBatch(List<Integer> ids) throws Exception {
		// 用来存放每一条培训资料是否被删除的标记
		List<Integer> resultList = new ArrayList<Integer>();
		// 将培训内容的ids集合遍历出来，每遍历一个id，就根据id删除该id对应的培训资料内容
		for (Integer id : ids) {
			int result = traincontentMapper.deleteByPrimaryKey(id);
			// 将标记存入另外一个集合中
			resultList.add(result);
		}
		if (resultList.contains(0)) {
			return false;
		} else {
			return true;
		}
	}

	// 修改培训内容
	// trainContent 修改后的对象
	@Override
	public boolean updateTrainContent(Traincontent trainContent) throws Exception {
		int result = traincontentMapper.updateByPrimaryKeySelective(trainContent);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 根据培训资料表的主键--文件编号 获取培训资料表信息
	@Override
	public Traincontent getTrainContentById(Integer id) throws Exception {
		Traincontent trainContent = traincontentMapper.selectByPrimaryKey(id);
		if (trainContent != null) {
			return trainContent;
		} else {
			return null;
		}
	}

	/**
	 * 根据条件进行分页查询： map 封装的条件 包括：当前页页号(int)、每页显示的记录条数(int)、资料名称、所属部门、资料级别、知识点
	 * 返回本页要显示的所有记录数
	 */
	@Override
	public List<Traincontent> selectTraincontentWithFYCondition(Map map) throws Exception {
		List<Traincontent> trainContentList = traincontentCustomMapper.selectTraincontentWithFYCondition(map);
		if (trainContentList!=null) {
			return trainContentList;
		} else {
			return null;
		}
	}

	/**
	 *  根据分页的条件查询一共有多少条记录数 -->
	 * @param map 封装的条件  包括：当前页页号(int)、每页显示的记录条数(int)、资料名称、所属部门、资料级别、知识点
	 * @return 返回要显示的总的记录的条数
	 */
	@Override
	public int selectTraincontentCountWithFYCondition(Map map) {
		int resultCount = traincontentCustomMapper.selectTraincontentCountWithFYCondition(map);
		if(resultCount!=0){
			return resultCount;
		}else{
			return 0;
		}
	}
	
	
	@Override
	public String getTrainContentNameById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Traincontent> findTraincontentWithCondition(int currentPage, int currentTotal,
			Map<String, Object> condition) throws Exception {
		return null;
	}

	//--============================一下是和 培训中心  相关的操作
	
	
	/**
	 * 根据资料等级、资料类型(视频资料)、知识点、 当前页页号、每页显示的记录数  的分页查询
	 */
	@Override
	public List<Traincontent> findStudyTraincontentByFy(Map map) {
		List<Traincontent> traincontentList = traincontentCustomMapper.findStudyTraincontentByFy(map);
		System.out.println("记录数："+traincontentList.size());
		if(traincontentList.size()>0){
			return traincontentList;
		}else{
			return null;
		}
	}

	/**
	 * 根据资料等级、资料类型(非视频资料)、知识点、 当前页页号、每页显示的记录数  的分页查询
	 * @param map 资料等级、资料类型(非视频资料)、知识点、 当前页页号、每页显示的记录数
	 * @return
	 */
	@Override
	public List<Traincontent> findStudyTraincontentByFyDoc(Map map) {
		List<Traincontent> traincontentList = traincontentCustomMapper.findStudyTraincontentByFyDoc(map);
		System.out.println("记录数："+traincontentList.size());
		if(traincontentList.size()>0){
			return traincontentList;
		}else{
			return null;
		}
	}

	/**
	 * //根据资料等级、资料类型(视频资料)、知识点、查询总共有多少条记录数
	 * @param map
	 * @return
	 */
	@Override
	public int findStudyTraincontentByFyCount(Map map) {
		int count = traincontentCustomMapper.findStudyTraincontentByFyCount(map);
		return count;
	}

	/**
	 * //根据资料等级、资料类型(非视频资料)、知识点、查询总共有多少条记录数
	 * @param map
	 * @return
	 */
	@Override
	public int findStudyTraincontentByFyDocCount(Map map) {
		int count = traincontentCustomMapper.findStudyTraincontentByFyDocCount(map);
		return count;
	}



}
