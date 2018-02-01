package cn.xm.exam.service.trainContent;

import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.trainContent.Traincontent;
import cn.xm.exam.utils.PageBean;

//完

/**
 * 培训资料Servicre接口
 * 
 * @author QizoLiQiang
 * @time 2017年8月9日上午10:19:42
 */
public interface TraincontentService {

	/****************** 基本的增删改查 *********************/
	/*
	 * `documentId`,`documentName`,`trainType`,`originalName`,`currentName`,`
	 * upTime`,`size`,`employeeId`,`level`,`description`,`departmentId`,`
	 * browseTimes`
	 */

	// id自增(int型)

	/**
	 * 增加培训内容
	 * 
	 * @param trainContent
	 *            要增加的培训记录
	 * @return 是否增加成功
	 * @throws Exception
	 */
	public boolean addTrainContent(Traincontent trainContent) throws Exception;

	/**
	 * 删除培训资料
	 * 
	 * @param id
	 *            资料Id
	 * @return 是否删除成功
	 * @throws Exception
	 */
	public boolean deleteTrainById(Integer id) throws Exception;

	/**
	 * 批量删除培训内容
	 * 
	 * @param ids
	 *            删除的培训内容的id集合
	 * @return 是否成功
	 * @throws Exception
	 */
	public boolean deleteTrainContentBatch(List<Integer> ids) throws Exception;

	/**
	 * 修改培训内容
	 * 
	 * @param trainContent
	 *            修改后的对象
	 * @return
	 * @throws Exception
	 */
	public boolean updateTrainContent(Traincontent trainContent) throws Exception;

	/**
	 * 根据id查询培训内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Traincontent getTrainContentById(Integer id) throws Exception;

	/**
	 * 根据文件id获取文件唯一的名字用于在Action根据路径与名字打开输入流提供下载
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getTrainContentNameById(Integer id) throws Exception;

	/**
	 * 分页查询:根据组合条件进行分页查询
	 * 
	 * @param currentPage
	 *            当前页，默认第一页
	 * @param currentTotal
	 *            当前页的数量
	 * @param condition
	 *            查询条件
	 * @return
	 * @throws Exception
	 */
	PageBean<Traincontent> findTraincontentWithCondition(int currentPage, int currentTotal,
			Map<String, Object> condition) throws Exception;

	//=================lixianyuan 9.19 start
	/**
	 * lixianyuan 9.19
	 * 根据条件进行分页查询：
	 * @param map	封装的条件  包括：当前页页号(int)、每页显示的记录条数(int)、资料名称、所属部门、资料级别、知识点
	 * @return  返回本页要显示的所有记录的信息
	 * @throws Exception
	 */
	List<Traincontent> selectTraincontentWithFYCondition(Map map) throws Exception;
	//=======================lixianyuan 9.19 end
	
	/**
	 *  根据分页的条件查询一共有多少条记录数 -->
	 * @param map 封装的条件  包括：当前页页号(int)、每页显示的记录条数(int)、资料名称、所属部门、资料级别、知识点
	 * @return 返回要显示的总的记录的条数
	 */
	int selectTraincontentCountWithFYCondition(Map map);
	
	
	
	//----------以下是和 学习中心   相关的操作 start
	//根据资料等级、资料类型(视频资料或者非视频资料)、知识点、 当前页页号、每页显示的记录数  的分页查询
	List<Traincontent> findStudyTraincontentByFy(Map map);
	//根据资料等级、资料类型(视频资料)、知识点、查询总共有多少条记录数
	int findStudyTraincontentByFyCount(Map map);
	
	
	//根据资料等级、资料类型(非视频资料)、知识点、 当前页页号、每页显示的记录数  的分页查询
	List<Traincontent> findStudyTraincontentByFyDoc(Map map);
	//根据资料等级、资料类型(非视频资料)、知识点、查询总共有多少条记录数
	int findStudyTraincontentByFyDocCount(Map map);
	
	
	
	
	//----------和  学习中心   相关的操作   end
}