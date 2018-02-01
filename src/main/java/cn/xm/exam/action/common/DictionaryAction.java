package cn.xm.exam.action.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.common.Dictionary;
import cn.xm.exam.bean.common.DictionaryExample;
import cn.xm.exam.mapper.common.DictionaryMapper;
import cn.xm.exam.service.common.DictionaryService;
import cn.xm.exam.utils.ValidateCheck;

@Controller
@Scope("prototype")
public class DictionaryAction extends ActionSupport {

	// 通过Struts2将map集合转成json
	public Map<String, Object> map;

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	private Dictionary dictionary;

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	@Resource
	private DictionaryService dictionaryService;

	@Resource
	private DictionaryMapper dictionaryMapper;

	/**
	 * 获取字典树信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findAllDicMsg() throws Exception {
		map = new LinkedHashMap<String, Object>();

		List<Map<String, Object>> dictionaryTree = dictionaryService.getAllDictionary();

		// 通过struts2框架自动将map转成json
		map.put("dictionaryTree", dictionaryTree);

		return "ok";

	}

	/**
	 * 按条件分页查询 根据字典名称(dictionaryName)、字典状态(isUse) 当前页页号(curPage)
	 * 每页显示的记录数(pageSize) 进行模糊分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String selectDictionaryByFY() throws Exception {

		// 获取字典名
		String dictionaryName = dictionary.getDictionaryname();
		// 获取是否可用
		String isUse = dictionary.getIsuse();

		HttpServletRequest request = ServletActionContext.getRequest();
		// 接收当前页页号
		String curPage = request.getParameter("currentPage");
		// 接收每页显示的记录数
		String curCount = request.getParameter("currentTotal");

		// 实例化要转成json的map集合
		map = new LinkedHashMap<String, Object>();

		// 根据字典名称获取字典编号id
		// String upDictionaryId =
		// dictionaryService.getCodeByName(dictionaryName);

		// 按分页条件将当前页要显示的数据从数据库中查找出来
		Map<String, Object> mapFy = new LinkedHashMap<String, Object>();
		mapFy.put("dictionaryName", dictionaryName);
		mapFy.put("isUse", isUse);
		// mapFy.put("upDictionaryId", upDictionaryId);

		mapFy.put("curPage", (Integer.parseInt(curPage) - 1) * (Integer.parseInt(curCount)));// (当前页页号-1)*每页显示的记录数
		mapFy.put("pageSize", Integer.parseInt(curCount));// 每页要显示的记录数
		List<Dictionary> dictionaryList = dictionaryService.selectDictionaryByFY(mapFy);

		// 获取总记录数
		Map<String, Object> mapFy2 = new LinkedHashMap<String, Object>();
		mapFy2.put("dictionaryName", dictionaryName);
		mapFy2.put("isUse", isUse);
		int sumCount = dictionaryService.selectDictionaryCountByFY(mapFy2);

		if (dictionaryList != null) {
			map.put("result", "查询成功");
			// 总记录数
			map.put("sumCount", sumCount);
			// 每页显示的记录数
			map.put("curCount", curCount);
			// 当前页页号
			map.put("curPage", curPage);
			// 通过struts2将map集合自动转换成json
			map.put("dictionaryList", dictionaryList);
		} else {
			map.put("result", "数据库中没有该数据");
		}

		return "ok";
	}

	/**
	 * 根据字典id进行删除 单条删除(包含级联删除，如果有下级字典，则先把下级字典删除，之后再删除当前字典)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delByDicId() throws Exception {
		map = new LinkedHashMap<String, Object>();

		// 获取从jsp中传过来的字典id
		String dictionaryid = ServletActionContext.getRequest().getParameter("dictionaryid");

		// 根据字典编号获取上级字典id
		Dictionary dic = dictionaryService.getDictionaryById(dictionaryid);// 根据字典编号查询字典信息
		String updictionaryid = dic.getUpdictionaryid();// 上级字典id
		// 根据该字典编号获取的上级字典id 来获取上级字典名称
		Dictionary upDic = dictionaryService.getDictionaryById(updictionaryid);// 根据上级字典编号获取上级字典信息
		String upDicName = upDic.getDictionaryname();// 获取上级字典的字典名称

		if (dictionaryid.equals("10")) {
			map.put("result", "警告！不允许这样操作！");
		} else {
			boolean result = dictionaryService.deleteDictionary(dictionaryid);
			if (result) {
				map.put("result", "删除成功");
				map.put("upDictioaryName", upDicName);// 上级字典的名称
			} else {
				map.put("result", "删除失败");
			}
		}
		return "ok";
	}

	/**
	 * 根据字典的id集合进行批量删除字典信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delByDicIdsBatch() throws Exception {
		map = new LinkedHashMap<String, Object>();
		// 接收上传过来的所有id拼接成的字符串
		String ids = ServletActionContext.getRequest().getParameter("ids");
		// 去掉两边空格
		ids = ids.trim();
		// 去掉最后一个","逗号
		ids = ids.substring(0, ids.length() - 1);
		// 将ids以","逗号进行分割
		String[] trainIds = ids.split(",");
		List list = new ArrayList();
		String tip = "";
		for (String id : trainIds) {
			// 根据id进行删除
			if (id.equals("10")) {
				tip = "字典列表  但被保留下来了,不应该删除字典列表";
				continue;
			} else {
				boolean result = dictionaryService.deleteDictionary(id);
				list.add(result);
			}
		}

		if (list.contains(false)) {
			map.put("result", "批量删除失败");
		} else {
			map.put("result", "批量删除成功" + tip);
		}
		return "ok";

	}

	/**
	 * 修改字典
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modifyDic() throws Exception {
		map = new LinkedHashMap<String, Object>();

		// 获取上级字典的名称
		String upDicName = ServletActionContext.getRequest().getParameter("upDicName");
		// 根据上级字典的名称获取上级字典的编号 也就是根据字典名称获取字典编号
		String UpDicCode = dictionaryService.getCodeByName(upDicName);

		// 将上级字典编号设置到当前的字典信息中
		dictionary.setUpdictionaryid(UpDicCode);

		// 更新
		boolean result = dictionaryService.updateDictionary(dictionary);

		// 获取字典名称和字典状态，发送给jsp页面，用于重新查询(当作刷新数据的效果)
		String dicName = dictionary.getDictionaryname();// 字典名称
		String dicIsUse = dictionary.getIsuse();// 字典状态

		if (result) {
			// 修改成功之后的后续操作
			map.put("dicName", dicName);
			map.put("dicIsUse", dicIsUse);
			map.put("result", "修改成功");
		} else {
			map.put("result", "修改失败");
		}

		return "ok";
	}

	/**
	 * 通过字典id获取其上级字典的字典id，然后根据上级字典id获取上级字典名称
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getUpDicNameByDicId() throws Exception {
		map = new LinkedHashMap<String, Object>();
		// 步骤1、：根据字典id先找到其上级字典id
		String dictionaryId = ServletActionContext.getRequest().getParameter("dictionaryId");// 获取字典id
		Dictionary dic = dictionaryService.getDictionaryById(dictionaryId);
		// 获取当前的字典状态
		String isuse = dic.getIsuse();
		// 获取当前的字典名称
		String dicName = dic.getDictionaryname();

		if (dicName.equals("字典列表")) {
			map.put("attention", "警告！字典列表 这个字典 不应该被修改!");
		} else {
			// 获取到字典描述
			String describe = dic.getDiscription();

			// 获取到上级字典编号
			String updictionaryid = dic.getUpdictionaryid();
			// 步骤2：根据上级字典id找到该上级字典id对应的字典名称
			String result = dictionaryService.getDicNameByUpDicId(updictionaryid);
			if (result != null) {
				map.put("result", result);
				map.put("isuse", isuse);
				map.put("dicName", dicName);
				map.put("describe", describe);
			} else {
				map.put("result", "操作失败，请重新操作哦");
			}
		}

		return "ok";
	}

	/**
	 * 添加字典信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addDictionary() throws Exception {
		map = new LinkedHashMap<String, Object>();
		// 获取上级字典的名称
		String upDicName = ServletActionContext.getRequest().getParameter("upDicName");
		// 根据上级字典的名称获取上级字典的id
		String upDicId = dictionaryService.getCodeByName(upDicName);
		// 根据上级字典Id查询下一个要插入的下一级字典的编号Z
		String nextDictionaryId = dictionaryService.getNextDictionaryId(upDicId);

		dictionary.setDictionaryid(nextDictionaryId);// 设置字典id
		dictionary.setUpdictionaryid(upDicId);// 设置上级字典id
		if (ValidateCheck.isNull(dictionary.getDiscription())) {
			dictionary.setDiscription("无");
		}

		// 添加字典信息，将字典信息添加到数据库中
		boolean result = dictionaryService.addDictionary(dictionary);
		if (result) {
			map.put("result", "添加成功");
		} else {
			map.put("result", "添加失败，请重新操作哦");
		}

		return "ok";
	}

	/**
	 * 左边字典树的，(点击以下左边的字典树，在表格中就会显示出当前字典的所有下一级字典的信息)
	 * 
	 * 根据上级字典名称获取该字典的所有下级字典信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getDownDicMsgByDicName() throws Exception {
		// 实例化要通过struts2转成json的map集合
		map = new LinkedHashMap<String, Object>();
		// String dicName = "资料类型";
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获取上传过来的字典名称
		String dicName = request.getParameter("dicName");
		// 当前页页号
		String curPage = request.getParameter("curPage");
		// 每页显示的记录数
		String perCount = request.getParameter("perCount");

		// 根据字典名称获取字典编号
		DictionaryExample dictionaryExample = new DictionaryExample();
		DictionaryExample.Criteria criteria = dictionaryExample.createCriteria();
		// 封装信息
		criteria.andDictionarynameEqualTo(dicName);
		// 执行
		List<Dictionary> dictList = dictionaryMapper.selectByExample(dictionaryExample);
		String id = "";// 作为当前字典名称对应的字典编号id
		for (Dictionary dic : dictList) {
			id = dic.getDictionaryid();
		}

		// 用来存放需要返回给jsp页面的数据的集合
		List<Dictionary> dictionaryList = new ArrayList<Dictionary>();
		// 1、当前字典有下级字典的情况
		// 根据字典编号查询其所有下级字典信息(这个是查询所有下级字典信息的，没有分页)
		// *********************************
		// List<Dictionary> dictionaryByUpDicId =
		// dictionaryService.getDictionaryByUpDicId(id);
		// 封装map条件
		Map<String, Object> mapFyy = new LinkedHashMap<String, Object>();
		mapFyy.put("upDictionaryId", id);// 字典id
		mapFyy.put("curPage", (Integer.parseInt(curPage) - 1) * (Integer.parseInt(perCount)));// 当前页页号
		mapFyy.put("perCount", Integer.parseInt(perCount));// 每页显示的记录数
		List<Dictionary> dictionaryByUpDicId = dictionaryService.getDictionaryByUpDicIdFY(mapFyy);

		// 根据字典编号查询其所有下一级字典信息的总记录数
		int sumCount = dictionaryService.getDictionaryCountByUpDicId(id);

		// 有下级字典的情况，就把下级字典的信息返回给页面
		if (dictionaryByUpDicId.size() > 0) {
			// 当前字典有下级字典的情况
			map.put("dictionaryList", dictionaryByUpDicId);
			// 每页显示的记录数
			map.put("perCount", perCount);
			// 当前页页号
			map.put("curPage", curPage);
			// 总记录数
			map.put("sumCount", sumCount);
		} else {
			// 当前字典没有下级字典的情况

			/*
			 * // 根据字典编号查询字典信息 Dictionary dictionaryById =
			 * dictionaryService.getDictionaryById(id); //
			 * 将该单条字典信息封装在dictionaryList集合中 dictionaryList.add(dictionaryById);
			 * map.put("dictionaryList", dictionaryList);
			 */

			// 每页显示的记录数
			map.put("perCount", perCount);
			// 当前页页号
			map.put("curPage", curPage);
			// 总记录数
			map.put("sumCount", sumCount);
		}

		return "ok";
	}

	/****** S QLQ *****/
	private String upId;

	public String getUpId() {
		return upId;
	}

	public void setUpId(String upId) {
		this.upId = upId;
	}

	public String getDicNamesByUpid() {
		map = new HashMap<String, Object>();
		List<Dictionary> dictionary = null;
		try {
			dictionary = dictionaryService.getDictionarynamesByUpDicId(upId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List names = new ArrayList();
		if (dictionary != null && dictionary.size() > 0) {
			for (Dictionary dic : dictionary) {
				names.add(dic.getDictionaryname());
			}
		}
		if (names != null && names.size() > 0) {
			map.put("names", names);
		}
		return "ok";
	}

	/****** S leilong *****/
	// 根据上级字典编号查询下级字典名称和编号
	public String getDicNamesAndIdByUpid() {
		map = new HashMap<String, Object>();
		List<Dictionary> dictionary = null;
		try {
			dictionary = dictionaryService.getDictionarynamesByUpDicId(upId);
			map.put("names", dictionary);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "ok";
	}

	/* S qlq */
	public String getDictionaryIdAndNamesByUpId() {
		map = new HashMap<String, Object>();
		List<Map<String, Object>> dictionary = null;
		try {
			dictionary = dictionaryService.getDictionaryIdAndNamesByUpId(upId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (dictionary != null && dictionary.size() > 0) {
			map.put("dictionary", dictionary);
		}
		return "ok";
	}
	/* S qlq */

}
