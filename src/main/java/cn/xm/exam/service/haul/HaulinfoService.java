package cn.xm.exam.service.haul;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.haul.Haulinfo;
import cn.xm.exam.utils.PageBean;

/**
 * 大修基本信息的service
 * 
 * @author QiaoLiQiang
 * @time 2017年11月9日下午10:08:40
 */
public interface HaulinfoService {
	/**
	 * 增加大修基本信息
	 * 
	 * @param haulInfo
	 * @param list  工程名字
	 * @return
	 * @throws SQLException
	 */
	public boolean addHaulinfo(Haulinfo haulInfo,List<String> list) throws SQLException;

	/**
	 * 根据大修ID删除大写
	 * 
	 * @param haulId
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean deleteHaulinfoByHaulId(String haulId) throws SQLException, Exception;

	/**
	 * 根据大修id查询大修信息
	 * 
	 * @param haulId
	 * @return
	 * @throws SQLException
	 */
	public Haulinfo getHaulinfoByHaulId(String haulId) throws SQLException;

	/**
	 * 修改大修基本信息(根据大修的ID修改)
	 * 
	 * @param haulinfo
	 *            修改后的大修
	 * @return
	 * @throws SQLException
	 */
	public boolean updateHaulinfoById(Haulinfo haulinfo) throws SQLException;

	/**
	 * 分页查询大修信息
	 * 
	 * @param currentPage
	 *            当前页
	 * @param currentCount
	 *            页大小
	 * @param condition
	 *            组合条件
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Map<String, Object>> getHaulinfoPageByCondition(int currentPage, int currentCount,
			Map<String, Object> condition) throws SQLException;

	/**
	 * 查询大修的基本信息与参加大修的部门的信息
	 * 
	 * @param haulId
	 *            大修ID
	 * @return map中包含大修基本信息与参加大修的部门的信息
	 * @throws SQLException
	 */
	public Map<String, Object> getHaulinfoWithUnitInfo(String haulId) throws SQLException;

	/**
	 * 查询大修和部门的树结构
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getDepartmentAndOverHaulTree() throws SQLException;

	/**
	 * 根据大修ID查询所有的大修部门ID
	 * 
	 * @param haulId
	 * @return
	 * @throws SQLException
	 */
	public List<String> getHaulUnitByHaulid(String haulId) throws SQLException;

	/**
	 * 查询未结束的大修的名字与ID
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getHaulNameAndIdsForExam() throws SQLException;
	/**
	 * 根据大修ID查找标段信息
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Map<String, Object>> getProjectInfoByBigId(int currentPage,int currentCount,Map<String,Object> condition) throws SQLException;
	/**
	 * 根据大修ID查找标段、单位、人数信息
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Map<String,Object>> getProjectUnitPerNumInfoByBigId(int currentPage,int currentCount,Map<String,Object> condition)throws SQLException;
	/**
	 * 增加单个标段
	 * @param bigId
	 * @param projectname
	 * @return
	 * @throws SQLException
	 */
	public boolean addOnebiaoduan(String bigId,String projectname)throws SQLException;
	/**
	 * 修改单个标段
	 * @param bigId
	 * @param projectname
	 * @return
	 * @throws SQLException
	 */
	public boolean updateOnebiaoduan(String projectId,String projectname)throws SQLException;
	/**
	 * 删除单个标段
	 * @param projectId
	 * @return
	 * @throws SQLException
	 */
	public boolean daleteOnebiaoduan(String projectId)throws SQLException;
	/**
	 * 获取所有正在进行的检修的统计信息
	 * @param condition	过滤掉的检修ID，一个是内部员工培训，一个是常委培训
	 * @return
	 * @throws SQLException
	 */
	public Map getAllHaulInfo(Map condition)throws SQLException;
}
