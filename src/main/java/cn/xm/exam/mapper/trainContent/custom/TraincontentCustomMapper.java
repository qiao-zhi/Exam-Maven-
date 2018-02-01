package cn.xm.exam.mapper.trainContent.custom;

import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.trainContent.Traincontent;

public interface TraincontentCustomMapper {
	// ====== lixianyuan 9.16 start ============
	// 用于根据条件进行分页查询
	List<Traincontent> selectTraincontentWithFYCondition(Map map);

	// <!-- 根据分页的条件查询一共有多少条记录数 -->
	int selectTraincontentCountWithFYCondition(Map map);
	// ====== lixinayuan 9.16 end =============

	// 以下是和学习中心相关的操作 start

	// 根据资料等级、资料类型(视频资料)、知识点、 当前页页号、每页显示的记录数 的分页查询
	List<Traincontent> findStudyTraincontentByFy(Map map);

	// 根据资料等级、资料类型(视频资料)、知识点、查询总共有多少条记录数
	int findStudyTraincontentByFyCount(Map map);

	// 根据资料等级、资料类型(非视频资料)、知识点、 当前页页号、每页显示的记录数 的分页查询
	List<Traincontent> findStudyTraincontentByFyDoc(Map map);

	// 根据资料等级、资料类型(非视频资料)、知识点、查询总共有多少条记录数
	int findStudyTraincontentByFyDocCount(Map map);

	// 和学习中心相关的操作 end
}
