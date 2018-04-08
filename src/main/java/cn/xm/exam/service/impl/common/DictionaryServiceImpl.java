package cn.xm.exam.service.impl.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xm.exam.annotation.LogAnno;
import cn.xm.exam.bean.common.Dictionary;
import cn.xm.exam.bean.common.DictionaryExample;
import cn.xm.exam.mapper.common.DictionaryMapper;
import cn.xm.exam.mapper.common.custom.DictionaryCustomMapper;
import cn.xm.exam.service.common.DictionaryService;

/**
 * 字典表的实现类
 * 
 * @author 贤元
 *
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Resource
	private DictionaryMapper dictionaryMapper;
	@Resource
	private DictionaryCustomMapper dictionaryCustomMapper;

	/**
	 * 1、添加字典信息
	 */
	@LogAnno(operateType = "添加了一个字典项")
	@Override
	public boolean addDictionary(Dictionary dictionary) throws SQLException {
		int result = dictionaryMapper.insert(dictionary);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 2、根据字典表的主键id 删除字典信息 单条删除(包含级联删除)
	 */
	@Override
	public boolean deleteDictionary(String dictionaryOptionId) throws Exception {
		// 当前字典id
		String upDictionaryId = dictionaryOptionId;

		// 判断当前字典 是否有下级字典
		List<Dictionary> downDicList = dictionaryCustomMapper.getDictionaryByUpDicId(upDictionaryId);
		// System.out.println("集合的大小:" + downDicList.size());// 0
		if (downDicList.size() == 0) {
			// 没有下级字典
			// System.out.println("当前字典没有下级字典");
			int result = dictionaryMapper.deleteByPrimaryKey(upDictionaryId);// 删除当前字典
			// System.out.println("result:=" + result);
			// System.out.println("删除成功 当前字典没下级字典");
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			// 有下级字典
			// System.out.println("当前字典有下级字典");
			delDicDiGui(upDictionaryId);// 删除当前字典的所有下级字典
			int result = dictionaryMapper.deleteByPrimaryKey(upDictionaryId);// 删除当前的字典
			// System.out.println("result:=" + result);
			// System.out.println("删除成功 当前字典有下级字典");
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 2.1 这个方法用于当前字典的所有下级字典 //当前字典还有有下级字典的情况
	 * 
	 * @param dictionaryid
	 *            当前字典的id
	 * @throws Exception
	 */
	public void delDicDiGui(String upDictionaryId) throws Exception {
		// 根据字典编号查询其所有下级字典信息,如果没有下级字典信息，则会返回空对象null
		List<Dictionary> dicList = dictionaryCustomMapper.getDictionaryByUpDicId(upDictionaryId);
		// System.out.println(dicList.size());
		// 判断是否有下级字典
		if (dicList.size() == 0) {
			// 当前字典没有下级字典的情况 这是退出递归的条件
			// System.out.println("递归结束");
			dictionaryMapper.deleteByPrimaryKey(upDictionaryId);
		} else {
			// 当前字典有下级字典的情况
			for (Dictionary dic : dicList) {
				String curDicId = dic.getDictionaryid();
				delDicDiGui(curDicId);
			}
		}
	}

	/**
	 * 3、根据多个字典编号进行批量删除
	 */
	@Override
	public boolean deleteDictionaryBatch(List<Integer> dictionaryOptionIds) throws Exception {
		List<Boolean> list = new ArrayList<Boolean>();
		for (Integer id : dictionaryOptionIds) {
			boolean result = deleteDictionary(String.valueOf(id));// 根据当前字典的id进行删除
			list.add(result);
		}
		if (list.contains(false)) {
			return false;
		} else {
			return false;
		}
	}

	/**
	 * 4、修改字典信息
	 */
	@Override
	public boolean updateDictionary(Dictionary dictionary) throws Exception {
		// 根据字典信息的javabean修改字典信息
		int result = dictionaryMapper.updateByPrimaryKeySelective(dictionary);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 5、根据上级字典编号查询所有下级字典信息 upDictionaryOptionId：代表存储上级字典编号的key
	 * 
	 * Map中的参数：@param
	 * upDictionaryOptionId(上级字典编号)、pageBegin(起始记录数)、pageSize(返回的记录数)
	 */
	@Override
	public List<Dictionary> getDictionaryByUpDicId(String upDictionaryId) throws Exception {

		// 从map集合中获取上级字典编号
		List<Dictionary> dictionaryList = dictionaryCustomMapper.getDictionaryByUpDicId(upDictionaryId);
		if (dictionaryList.size() > 0) {
			return dictionaryList;
		} else {
			return null;
		}
	}

	/**
	 * 6、查询字典树信息 List 返回的map包括(dictionaryOptionId,dictionaryOptionName, `
	 * upDictionaryOptionId) (字典编号，字典名称，上级字典编号)
	 */
	@Override
	public List<Dictionary> getDictionaryTree() throws Exception {
		List<Dictionary> dictionaryTreeList = null;// dictionaryCustomMapper.getDictionaryTree();
		if (dictionaryTreeList.size() > 0) {
			return dictionaryTreeList;
		} else {
			return null;
		}
	}

	/**
	 * 7、 根据字典名称、字典状态、当前页页号、每页要显示的记录数 进行分页查询#{curPage},#{pageSize} map集合中有
	 * 字典名称、字典状态、当前页页号(curPage)、每页要显示的记录数(pageSize) 进行分页查询
	 * 
	 * @return 当前页要显示的字典信息
	 * @throws Exception
	 */
	@Override
	public List<Dictionary> selectDictionaryByFY(Map<String, Object> map) throws Exception {

		List<Dictionary> dictionaryList = dictionaryCustomMapper.selectDictionaryByFY(map);
		if (dictionaryList != null) {
			return dictionaryList;
		} else {
			return null;
		}
	}

	/**
	 * 7.5 根据 字典名称、字典状态 进行模糊查询 统计一共有多少条记录数
	 * 
	 * @param map
	 *            该集合里面有 字典名称、字典状态
	 * @return 当前页要显示的所有字典信息的总记录数
	 * @throws SQLException
	 */
	@Override
	public int selectDictionaryCountByFY(Map<String, Object> map) throws Exception {
		int sumCount = dictionaryCustomMapper.selectDictionaryCountByFY(map);
		return sumCount;
	}

	/**
	 * 8、 根据字典名称查询字典编码(dictionaryId)
	 */
	@Override
	public String getCodeByName(String dictionaryName) throws Exception {
		String dictionaryId = dictionaryCustomMapper.getDictionaryIdByDictionaryName(dictionaryName);
		if (dictionaryId != null) {
			return dictionaryId;
		} else {
			return null;
		}
	}

	/**
	 * 9、根据 上级字典编号upDictionaryId 获取 该上级字典名称dictionaryName
	 * 
	 * @param upDictionaryOptionId
	 *            上级字典编号
	 */
	@Override
	public String getDicNameByUpDicId(String upDictionaryOptionId) throws Exception {
		String dictionaryName = dictionaryCustomMapper.getDictionaryNameByDictionaryId(upDictionaryOptionId);
		if (dictionaryName != null) {
			return dictionaryName;
		} else {
			return null;
		}
	}

	/**
	 * 根据字典编号查询字典信息 根据字典的主键id dictionaryId 获取该条字典信息
	 */
	@Override
	public Dictionary getDictionaryById(String dictionaryOptionId) throws Exception {
		Dictionary dictionary = dictionaryMapper.selectByPrimaryKey(dictionaryOptionId);
		if (dictionary != null) {
			return dictionary;
		} else {
			return null;
		}
	}

	/**
	 * 获取全部字典列表 字典树信息
	 */
	@Override
	public List<Map<String, Object>> getAllDictionary() throws Exception {
		List<Map<String, Object>> dictionaryTree = dictionaryCustomMapper.getDictionaryTree();
		if (dictionaryTree.size() > 0) {
			return dictionaryTree;
		} else {
			return null;
		}
	}

	/**
	 * 根据上级字典Id查询下一个要插入的下一级字典的编号Z 前提的约定：最顶层的"字典列表"的字典编号为"10",
	 * 下级字典编号为=上级字典编号+"三位数"(例如：001、002、003)。
	 * 
	 * @param upDictionaryOptionId
	 *            上级字典id
	 */
	@Override
	public String getNextDictionaryId(String upDictionaryOptionId) throws Exception {
		// 上级字典id
		String upDicId = upDictionaryOptionId;

		// System.out.println("开始");
		// 根据上级字典id查询下一级字典的最大编号
		String maxDictionaryId = dictionaryCustomMapper.getMaxDictionaryId(upDicId);

		String nextDictionaryId = null;

		// 如果没有下级字典的情况(也就是只有一个最顶层字典的情况) 根据上级字典查询其所有下一级字典信息
		List<Dictionary> dictionaryByUpDicId = dictionaryCustomMapper.getDictionaryByUpDicId(upDicId);

		// 根据上级字典Id查询下一个要插入的下一级字典的编号Z
		// 如果上一级字典 是 “字典列表 的情况”
		if (upDicId.equals("10")) {
			// 上级字典是顶层的"字典列表的时候的情况"

			// 如果没有下级字典 则第一个为字典id="100"
			if (dictionaryByUpDicId.size() == 0) {
				nextDictionaryId = "100";
			} else {
				// 有下一级字典的情况
				nextDictionaryId = String.valueOf(Integer.parseInt(maxDictionaryId) + 100);
			}
			System.out.println("要插入的下一级字典的编号为:" + nextDictionaryId);
		} else if (maxDictionaryId == null) {
			// 上级字典不是 "字典列表的情况 " 第二级 字典 的情况 且 该字典没有下一级字典的情况

			if (dictionaryByUpDicId.size() == 0) {
				// 下一级字典编号 为 = 该字典编号+"001"
				nextDictionaryId = String.valueOf(upDicId) + "001";
			}
			System.out.println("要插入的下一级字典的编号:" + nextDictionaryId);
		} else if (maxDictionaryId != null) {
			// //上级字典不是 "字典列表的情况 " 第二级 字典 的情况 且 该字典有下一级字典的情况
			nextDictionaryId = String.valueOf(Integer.parseInt(maxDictionaryId) + 1);
			System.out.println("要插入的下一级字典的编号:" + nextDictionaryId);
		}

		return nextDictionaryId;

	}

	/**
	 * 根据上级字典Id查询最新的一个下级字典信息
	 */
	@Override
	public Dictionary getNewDictionary(String upDictionaryOptionId) throws Exception {

		// 根据上级字典Id查询他的下级字典的最大字典编号
		String nextDictionaryId = dictionaryCustomMapper.getMaxDictionaryId(upDictionaryOptionId);
		// 然后根据最大的字典编号查询该字典信息
		Dictionary dictionary = dictionaryMapper.selectByPrimaryKey(nextDictionaryId);
		if (dictionary != null) {
			return dictionary;
		} else {
			return null;
		}
	}

	@Override
	public List<String> getDomain(String dictionaryOptionName) throws Exception {

		return null;
	}

	@Override
	public List<String> getInstitutionList(Map<String, Object> institutionCon) throws Exception {

		return null;
	}

	/**
	 * 根据条件查询字典的总的记录数(字典编号，字典名称)
	 * 
	 * @param map集合封装了字典编号和字典名称
	 *            dictionaryId, dictionaryName
	 */
	@Override
	public int getDicCountByConditon(Map<String, Object> condition) throws Exception {
		int result = dictionaryCustomMapper.getCountDictionary(condition);
		if (result > 0) {
			return result;
		} else {
			return 0;
		}
	}

	/**
	 * 根据条件查询字典信息(字典编号，字典名称， 起始记录数，每页的记录数)
	 */
	@Override
	public List<Dictionary> getDictionaryByConditon(Map<String, Object> condition) throws Exception {

		return null;
	}

	/**
	 * 根据上级字典id查询其所有下一级字典信息的总记录数
	 */
	@Override
	public int getDictionaryCountByUpDicId(String upDictionaryId) throws Exception {
		int sumCount = dictionaryCustomMapper.getDictionaryCountByUpDicId(upDictionaryId);
		return sumCount;
	}

	/**
	 * <!-- 根据上级字典编号upDictinonaryId curPage当前页页号 perCount每页显示的记录数 查询所有下级字典信息 -->
	 * 
	 * @param upDictionaryId
	 *            上级字典编号
	 * @return 下级字典信息
	 * @throws Exception
	 */
	@Override
	public List<Dictionary> getDictionaryByUpDicIdFY(Map<String, Object> map) throws Exception {
		List<Dictionary> dictionaryList = dictionaryCustomMapper.getDictionaryByUpDicIdFY(map);
		if (dictionaryList != null) {
			return dictionaryList;
		} else {
			return null;
		}
	}

	@Override
	public List<Dictionary> getDictionarynamesByUpDicId(String upId) throws SQLException {
		DictionaryExample example = new DictionaryExample();
		DictionaryExample.Criteria criteria = example.createCriteria();
		criteria.andUpdictionaryidEqualTo(upId);
		List<Dictionary> selectByExample = dictionaryMapper.selectByExample(example);
		return selectByExample;
	}

	/*** S qlq */
	@Override
	public List<Map<String, Object>> getDictionaryIdAndNamesByUpId(String upDicId) throws SQLException {
		return dictionaryCustomMapper.getDictionaryIdAndNamesByUpId(upDicId);
	}
	/*** E qlq */
}
