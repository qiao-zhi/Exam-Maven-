package cn.xm.exam.mapper.common.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.common.Dictionary;

public interface DictionaryCustomMapper {
	 //=============lixianyuan  9.20 start
    /**
     * 查询字典树信息
     * @return 整个字典树的信息
     * @throws Exception
     */
    public List<Map<String,Object>> getDictionaryTree()throws SQLException;
    
    /**
     * 根据上级字典编号upDictinonaryId查询所有下级字典信息 
     * @param upDictionaryId 上级字典编号
     * @return 下级字典信息
     * @throws Exception
     */
    public List<Dictionary> getDictionaryByUpDicId(String upDictionaryId)throws SQLException;
    
    /**
     * <!-- 根据上级字典编号upDictinonaryId   curPage当前页页号   perCount每页显示的记录数   查询所有下级字典信息 -->
     * @param upDictionaryId 上级字典编号
     * @return 下级字典信息
     * @throws Exception
     */
    public List<Dictionary> getDictionaryByUpDicIdFY(Map<String,Object> map) throws SQLException;
    /**
     * 根据上级字典编号upDictinonaryId查询所有下一级字典信息 
     * @param upDictionaryId 上级字典编号
     * @return 所有下一级字典信息的总记录数
     * @throws Exception
     */
    public int getDictionaryCountByUpDicId(String upDictionaryId)throws SQLException;
    
    /**
	 * 根据条件查询字典信息
	 * @param condition 条件 (dictionaryId、dictionaryName)
	 * @return 字典信息
	 * @throws SQLException
	 */
	public List<Dictionary> getDictionaryByCondition(Map<String,Object> condition) throws SQLException;
	
	/**
	 * 根据上级字典Id查询下一个要插入的下一级字典的编号 
	 * @param upDictionaryId
	 * @return
	 * @throws SQLException
	 */
	public String getMaxDictionaryId(String upDictionaryId) throws SQLException;
    
	/**
	 * 根据字典名称、字典状态 每页显示的记录数  当前页页号 进行模糊的 分页查询
	 * @param map 该集合里面有 字典名称、字典状态 每页显示的记录数  当前页页号#{curPage},#{pageSize}
	 * @return 当前页要显示的所有字典信息
	 * @throws SQLException
	 */
	public List<Dictionary> selectDictionaryByFY(Map<String,Object> map)throws SQLException;
	
	/**
	 *  根据 字典名称、字典状态 进行模糊查询 统计一共有多少条记录数 
	 * @param map 该集合里面有 字典名称、字典状态 
	 * @return 当前页要显示的所有字典信息的总记录数
	 * @throws SQLException
	 */
	public int selectDictionaryCountByFY(Map<String,Object> map)throws SQLException;
	
	
	/**
	 *查询字典表的总记录数 (字典编号，字典名称)
	 * @throws SQLException
	 */
	public int getCountDictionary(Map<String,Object> map)throws SQLException;
	
	/**
	 * 根据字典名称获取字典编号
	 * @param dictionaryName 字典名称
	 * @return
	 * @throws SQLException
	 */
	public String getDictionaryIdByDictionaryName(String dictionaryName)throws SQLException;
	
	
	/**
	 * 根据字典编号获取字典名称
	 * @param dictionaryId 字典编号
	 * @return
	 * @throws Exception
	 */
	public String getDictionaryNameByDictionaryId(String dictionaryId)throws Exception;
	
	
	
    //============lixianyuan 9.20 end
	
	/*** S QLQ *****/
	/**
	 * 根据字典的上级编号查询字典的名称与编号(获取知识点与工种的ID与名称)
	 * 
	 * @param upDicId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getDictionaryIdAndNamesByUpId(String upDicId) throws SQLException;
	/*** E QLQ *****/
	
	
	
}
