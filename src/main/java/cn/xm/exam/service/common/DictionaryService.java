package cn.xm.exam.service.common;

import java.sql.SQLException;

//字典管理service  原系统的

import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.common.Dictionary;

public interface DictionaryService {

	/**
	 * 添加字典信息
	 * 
	 * @param dictionary
	 * @return
	 * @throws Exception
	 */
	public boolean addDictionary(Dictionary dictionary) throws Exception;

	/**
	 * 根据字典Id删除字典信息
	 * 
	 * @param dictionaryOptionId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteDictionary(String dictionaryOptionId) throws Exception;

	/**
	 * 根据字典编码查询字典信息
	 * 
	 * @param dictionaryOptionId
	 * @return
	 * @throws Exception
	 */
	public Dictionary getDictionaryById(String dictionaryOptionId) throws Exception;

	/**
	 * 根据条件查询字典信息(字典编号，字典名称， 起始记录数，每页的记录数)
	 * 
	 * @param condition(dictionaryOptionId
	 *            字典编号,dictionaryOptionName 字典名称, pageBegin 起始记录数,pageSize
	 *            每页的记录数,dictionaryOptionId 上级字典编号)
	 * @return
	 * @throws Exception
	 */
	public List<Dictionary> getDictionaryByConditon(Map<String, Object> condition) throws Exception;

	/**
	 * 根据条件查询字典的总的记录数(字典编号，字典名称)
	 * 
	 * @param condition(dictionaryOptionId
	 *            字典编号,dictionaryOptionName 字典名称)
	 * @return
	 * @throws Exception
	 */
	public int getDicCountByConditon(Map<String, Object> condition) throws Exception;

	/**
	 * 修改字典信息
	 * 
	 * @param dictionary
	 * @return
	 * @throws Exception
	 */
	public boolean updateDictionary(Dictionary dictionary) throws Exception;

	/**
	 * 根据上级字典编号查询所有下一级字典信息
	 * 
	 * @param upDictionaryOptionId(上级字典编号)、pageBegin(起始记录数)、pageSize(返回的记录数)
	 * @return List<Dictionary> (下级字典列表)
	 * @throws Exception
	 */
	public List<Dictionary> getDictionaryByUpDicId(String upDictionaryId) throws Exception;

	/**
	 * 根据上级字典编号查询所有下一级字典信息的总记录数
	 * 
	 * @param upDictionaryOptionId(上级字典编号)、pageBegin(起始记录数)、pageSize(返回的记录数)
	 * @return 所有下一级字典信息的总记录数
	 * @throws Exception
	 */
	public int getDictionaryCountByUpDicId(String upDictionaryId) throws Exception;

	/**
	 * 查询全部字典列表
	 * 
	 * @return List<Dictionary> (全部字典列表)
	 * @throws Exception
	 */
	public List<Map<String, Object>> getAllDictionary() throws Exception;

	/**
	 * 查询字典树信息
	 * 
	 * @return List<Map<String,Object>>
	 *         返回的map包括(dictionaryOptionId,dictionaryOptionName,
	 *         upDictionaryOptionId) (字典编号，字典名称，上级字典编号)
	 * @throws Exception
	 */
	public List<Dictionary> getDictionaryTree() throws Exception;

	/**
	 * 根据上级字典Id查询下一个要插入的下一级字典的编号
	 * 
	 * @param upDictionaryOptionId
	 *            上级字典Id
	 * @return
	 * @throws Exception
	 */
	public String getNextDictionaryId(String upDictionaryOptionId) throws Exception;

	/**
	 * 根据上级字典Id查询最新的一个下级字典信息
	 * 
	 * @param upDictionaryOptionId
	 *            上级字典Id
	 * @return 最新的一个下级字典信息
	 * @throws Exception
	 */
	public Dictionary getNewDictionary(String upDictionaryOptionId) throws Exception;

	/**
	 * 得到领域
	 * 
	 * @param dictionaryOptionName
	 *            ("领域")
	 * @return 领域列表
	 * @throws Exception
	 */
	public List<String> getDomain(String dictionaryOptionName) throws Exception;

	/**
	 * 根据字典名称查询字典编码
	 * 
	 * @param dictionaryName
	 *            字典名称
	 * @return 字典编码
	 * @throws Exception
	 */
	public String getCodeByName(String dictionaryName) throws Exception;

	/**
	 * 根据多个字典编号进行批量删除
	 * 
	 * @param dictionaryOptionIds
	 *            字典编号的集合
	 * @return
	 * @throws Exception
	 */
	public boolean deleteDictionaryBatch(List<Integer> dictionaryOptionIds) throws Exception;

	/**
	 * 根据字典名称、字典状态、当前页页号、每页要显示的记录数 进行分页查询#{curPage},#{pageSize}
	 * 
	 * @return 当前页要显示的字典信息
	 * @throws Exception
	 */
	public List<Dictionary> selectDictionaryByFY(Map<String, Object> map) throws Exception;

	/**
	 * 根据 字典名称、字典状态 进行模糊查询 统计一共有多少条记录数
	 * 
	 * @param map
	 *            该集合里面有 字典名称、字典状态
	 * @return 当前页要显示的所有字典信息的总记录数
	 * @throws SQLException
	 */
	public int selectDictionaryCountByFY(Map<String, Object> map) throws Exception;

	public List<String> getInstitutionList(Map<String, Object> institutionCon) throws Exception;

	/**
	 * 根据 上级字典编号upDictionaryId 获取 该上级字典名称dictionaryName
	 * 
	 * @param upDictionaryOptionId
	 *            上级字典名称
	 * @return
	 * @throws Exception
	 */
	public String getDicNameByUpDicId(String upDictionaryOptionId) throws Exception;

	/**
	 * <!-- 根据上级字典编号upDictinonaryId curPage当前页页号 perCount每页显示的记录数 查询所有下级字典信息 -->
	 * 
	 * @param upDictionaryId
	 *            上级字典编号
	 * @return 下级字典信息
	 * @throws Exception
	 */
	public List<Dictionary> getDictionaryByUpDicIdFY(Map<String, Object> map) throws Exception;

	/**
	 * 根据字典上级编号查询字典名称
	 * 
	 * @param upId
	 * @return
	 * @throws SQLException
	 */
	public List<Dictionary> getDictionarynamesByUpDicId(String upId) throws SQLException;
	

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