package cn.xm.exam.action.trainContent;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.common.Dictionary;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.bean.trainContent.Traincontent;
import cn.xm.exam.mapper.employee.in.custom.DepartmentCustomMapper;
import cn.xm.exam.service.common.DictionaryService;
import cn.xm.exam.service.employee.in.DepartmentService;
import cn.xm.exam.service.trainContent.TraincontentService;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.utils.ValidateCheck;

/**
 * @author 贤元
 *
 */
@Controller
@Scope("prototype")
public class TraincontentAction extends ActionSupport {

	@Resource
	private TraincontentService traincontentService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private DepartmentService departmentService;

	@Resource
	private DepartmentCustomMapper departmentCustomMapper;

	// 实例化要转成json的文本集合
	private Map<String, Object> map;

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	// 培训资料的javabean
	private Traincontent traincontent;

	public Traincontent getTraincontent() {
		return traincontent;
	}

	public void setTraincontent(Traincontent traincontent) {
		this.traincontent = traincontent;
	}

	// ********************分页 start************
	// 当前页页号
	private String currentPage;

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	// 每页显示的记录数
	private String currentTotal;

	public String getCurrentTotal() {
		return currentTotal;
	}

	public void setCurrentTotal(String currentTotal) {
		this.currentTotal = currentTotal;
	}

	// **********************分页 end**************

	// ====文件上传 start=================
	/**
	 * 文件上传的三个条件： 1、表单有file 2、提交方式为post 3、enctype="multipart/form-date"
	 */
	/**
	 * 1）接收上传的内容
	 */
	// 接收上传的文件，名字来自于表单的file的name属性名称
	private File attach;//
	// 2）接收文件类型
	private String attachContentType;
	// 3)接收文件名称
	private String attachFileName;
	// 4）接收描述
	private String info;//

	/**
	 * 注入服务器目录地址
	 */
	private String savePath;

	/**
	 * 注意：一定要给setter方法，这些方法就是给上传文件内容进行赋值的
	 */
	public void setAttach(File attach) {
		this.attach = attach;
	}

	public void setAttachContentType(String attachContentType) {
		this.attachContentType = attachContentType;
	}

	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	// 文件上传的方法 这是个示例方法，到后面的时候这个方法可以直接删掉
	public String upload() throws Exception {
		// 源文件名称 attach就是上传过来的文件
		// upload_c03f093_15adf60d06f__8000_00000000.tmp

		FileInputStream ins = new FileInputStream(attach);
		int bufferSize = ins.available();
		double buSize = bufferSize;
		double sizeMB = buSize / 1024 / 1024;
		DecimalFormat df = new DecimalFormat("######0.00");
		String size = df.format(sizeMB);
		/**
		 * 关键点:把文件保存到服务器硬盘 源文件 目标文件的地址(也就是要将源文件复制在哪个 服务器目录地址+上传的文件名)
		 */
		FileUtils.copyFile(attach, new File(savePath + attachFileName));
		/**
		 * 因为保存文件的时候还会把临时文件存进去，所以要把存进去的临时文件给删除了
		 */

		return null;
	}

	// ===== 文件上传 end====================

	// ***********页面初始化 start********
	/**
	 * 初始化部门
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initDepartment() throws Exception {
		map = new LinkedHashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		// 获取Session中的用户信息
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user.getDepartmentid();// 获取部门ID
		boolean permitted = currentUser.isPermitted("trainmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
		String departmentId = permitted ? null : departmentIdSession;
		// 将部门树的所有信息查询出来
		//List<Map<String, Object>> departmentTree = departmentCustomMapper.getDepartmentTreeCommon(departmentId);
		List<Map<String, Object>> departmentTree = departmentService.getDepartmentTreeCommon(departmentId);
		
		// struts2自动将map集合转成json
		map.put("departmentTree", departmentTree);

		return "ok";
	}

	/**
	 * 初始化资料类型
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initTrainType() throws Exception {
		map = new LinkedHashMap<String, Object>();
		// 1、根据字典名称查询字典编号
		String dictionaryId = dictionaryService.getCodeByName("资料类型");
		// 2、根据字典编号查询其所有下级字典信息
		List<Dictionary> dictionaryList = dictionaryService.getDictionaryByUpDicId(dictionaryId);
		// strtus自动装map集合转成json
		map.put("dictionaryList", dictionaryList);

		return "ok";
	}

	/**
	 * 初始化知识点
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initKnowledgeType() throws Exception {
		map = new LinkedHashMap<String, Object>();
		// 1、根据字典名称查询字典编号
		String dictionaryId = dictionaryService.getCodeByName("知识点");
		// 2、根据字典编号查询其所有下级字典信息
		// List<Dictionary> dictionaryList =
		// dictionaryService.getDictionaryByUpDicId(dictionaryId);
		List<Map<String, Object>> dictionaryList = dictionaryService.getDictionaryIdAndNamesByUpId("200");

		// strtus自动装map集合转成json
		map.put("dictionaryList", dictionaryList);
		return "ok";
	}

	/**
	 * 初始化资料级别
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initTrainLevel() throws Exception {
		map = new LinkedHashMap<String, Object>();
		// 1、根据字典名称查询字典编号
		String dictionaryId = dictionaryService.getCodeByName("资料级别");
		// 2、根据字典编号查询其所有下级字典信息
		List<Dictionary> dictionaryList = dictionaryService.getDictionaryByUpDicId(dictionaryId);
		// strtus自动装map集合转成json
		map.put("dictionaryList", dictionaryList);
		return "ok";
	}

	// ***********页面初始化 end********

	/**
	 * 增加培训资料
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addTrainContent() throws Exception {
		map = new LinkedHashMap<String, Object>();

		
		
		boolean flag = false;
		
		// 判断文件是否存在，也就是判断用户是否上传了文件
		if (attach != null) {
			// 获取上传过来的文件的后缀名
			int lastIndexOf = attachFileName.lastIndexOf('.');
			String endName = attachFileName.substring(lastIndexOf);//获取文件后缀名 .mp4
			
			//如果上传的不是mp4文件，则不进行后续操作
			if(!".mp4".equals(endName)){
				map.put("result", "请上传mp4文件");
				return "ok";
			}
			
			// 通过uuid获得当前文件名
			String currentName = UUIDUtil.getUUID2();
			currentName = currentName + endName;
			// 保存原文件名(上传过来的文件的原来的名称)
			traincontent.setOriginalname(attachFileName);
			// 保存当前文件名(uuid形式)
			traincontent.setCurrentname(currentName);
			// 获取文件大小
			FileInputStream ins = new FileInputStream(attach);
			int bufferSize = ins.available();
			String size = "";
			if (bufferSize < 1024) {
				// 文件大小小宇1KB的，单位则用字节表示
				size = String.valueOf(bufferSize) + "Byte";
			} else if (bufferSize < 1048576) {
				// 文件大小小于1MB的，单位则用KB表示
				size = String.valueOf(bufferSize / 1024) + "KB";
			} else {
				// 文件大小大于1MB的，单位则用MB表示
				double buSize = bufferSize;
				double sizeMB = buSize / 1024 / 1024;
				DecimalFormat df = new DecimalFormat("######0.00");
				size = df.format(sizeMB) + "MB";
			}
			traincontent.setSize(size);// 将文件大小保存在javabean中

			// 将上传过来的文件保存在指定的硬盘目录中
			// attachFileName原文件名 currentName当前文件名
			FileUtils.copyFile(attach, new File(savePath + currentName));

			// 根据部门名称找到对应的部门编号，将部门编号添加到培训资料表中
			String deptId = departmentCustomMapper.getDeptIdByDeptName(traincontent.getDepartmentname());
			traincontent.setDepartmentid(deptId);// 添加部门编号


			// 将浏览量设置为0，浏览量默认为0
			traincontent.setBrowsetimes(0);

			// 将培训资料保存在培训资料表中
			flag = traincontentService.addTrainContent(traincontent);
		}
		
		if (flag) {
			map.put("result", "保存成功");
		} else {
			if (attach == null) {
				map.put("result", "您未选择文件，请重新选择文件之后再提交数据！");
			} else {
				map.put("result", "保存失败");
			}
		}
		return "ok";
	}

	/**
	 * 修改按钮点击之后跳转到这
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modifyButton() throws Exception {
		// 接收从jsp页面上传过来的培训资料主键id
		HttpServletRequest request = ServletActionContext.getRequest();
		String trainId = request.getParameter("trainContentId");
		// 根据培训资料的主键id查询该条培训信息
		Map<String,Object> trainContent = traincontentService.getTrainContentById2(Integer.parseInt(trainId));
		// 将日期转换成指定格式
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = sd.format(trainContent.get("uptime"));
		// 将培训资料信息保存在request域中，在修改培训资料信息的jsp页面中回显出来
		request.setAttribute("traincontent", trainContent);
		request.setAttribute("dateStr", dateStr);

		return "modifyJsp";// 跳转到修改培训资料信息的jsp页面
	}

	/**
	 * 修改培训资料 通过javabean修改培训资料
	 * 
	 * 要判断一下用户有没有上传文件过来，如果有，则需要删除原来的文件，将新文件保存在服务器硬盘中
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modifyTrain() throws Exception {

		map = new LinkedHashMap<String, Object>();

		// 判断文件是否存在，也就是判断用户是否修改了文件，即重新上传了文件
		boolean exists = (attachFileName != null);
		if (exists) {
			// 要先删除原来的文件
			/**
			 * 文件的保存路径1:d:/images/6fea9a80935a49b9be59d596aeb3fdf9.pdf
			 */
			String oldCurName = ServletActionContext.getRequest().getParameter("oldCurName");// 获取该培训资料原来(未修改前的)对应的文件
			File fileDel = new File(savePath + oldCurName);
			boolean res = fileDel.delete();// 删除原文件

			// 获取上传过来的文件的后缀名
			int lastIndexOf = attachFileName.lastIndexOf('.');
			String endName = attachFileName.substring(lastIndexOf);
			// 通过uuid获得当前文件名
			String currentName = UUIDUtil.getUUID2();
			currentName = currentName + endName;
			// 保存原文件名(上传过来的文件的原来的名称)
			traincontent.setOriginalname(attachFileName);// 原文件名
			// 保存当前文件名(uuid形式)
			traincontent.setCurrentname(currentName);// 当前文件名
			// 获取文件大小
			FileInputStream ins = new FileInputStream(attach);
			int bufferSize = ins.available();
			String size = "";
			if (bufferSize < 1024) {
				// 文件大小小宇1KB的，单位则用字节表示
				size = String.valueOf(bufferSize) + "Byte";
			} else if (bufferSize < 1048576) {
				// 文件大小小于1MB的，单位则用KB表示
				size = String.valueOf(bufferSize / 1024) + "KB";
			} else {
				// 文件大小大于1MB的，单位则用MB表示
				double buSize = bufferSize;
				double sizeMB = buSize / 1024 / 1024;
				DecimalFormat df = new DecimalFormat("######0.00");
				size = df.format(sizeMB) + "MB";
			}
			traincontent.setSize(size);// 将文件大小保存在javabean中
			// 将上传过来的文件保存在服务器指定的硬盘中
			FileUtils.copyFile(attach, new File(savePath + currentName));

		}

		// 根据部门名称找到对应的部门编号，将部门编号添加到培训资料表中
		String deptId = departmentCustomMapper.getDeptIdByDeptName(traincontent.getDepartmentname());
		traincontent.setDepartmentid(deptId);// 添加部门编号


		// 将修改过的javabean保存在培训资料表中
		boolean flag = traincontentService.updateTrainContent(traincontent);

		// 将上传过来的文件保存在指定的硬盘目录中
		// attachFileName原文件名 currentName当前文件名
		if (flag) {
			map.put("result", "修改成功");
		} else {
			map.put("result", "修改失败");
		}
		return "ok";// 这个视图通过struts2将map集合转成json
	}

	private String documentName;//文档名称
	private String departmentName;//部门名称
	private String typeId;//类别编号
	
	/**
	 * 根据资料名称、所属部门、资料级别、知识点、当前页页号、每页显示记录数进行分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findTrainByFYCondiction() throws Exception {
		map = new LinkedHashMap<String, Object>();
		// 封装查询条件
		Map<String,Object> condition = new HashMap<String,Object>();//封装条件的map
		if(ValidateCheck.isNotNull(documentName)){
			condition.put("documentName", documentName);
		}
		if(ValidateCheck.isNotNull(departmentName)){
			condition.put("departmentName", departmentName);
		}
		if(ValidateCheck.isNotNull(typeId)){
			condition.put("typeId", typeId);
		}
		int current_page = Integer.parseInt(currentPage);//当前页
		int current_total = Integer.parseInt(currentTotal);//页大小
		/******S    PageHelper分页*********/
		PageHelper.startPage(current_page,current_total);//开始分页
		List<Map<String,Object>> traincontentList = traincontentService.selectTraincontentWithFYCondition(condition);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(traincontentList);
		/******E    PageHelper分页*********/
		
		map.put("pageInfo", pageInfo);
		
		return "ok";
	}
/*	public String findTrainByFYCondiction() throws Exception {
		map = new LinkedHashMap<String, Object>();
		// 获取资料名称
		String documentName = traincontent.getDocumentname();
		// 获取部门名称
		String departmentName = traincontent.getDepartmentname();
		// 获取级别类型
		String level = traincontent.getLevel();
		// 获取知识点
		String knowledgeType = traincontent.getKnowledgetype();
		
		Subject currentUser = SecurityUtils.getSubject();
		// 获取Session中的用户信息
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user.getDepartmentid();// 获取部门ID
		boolean permitted = currentUser.isPermitted("trainmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
		String departmentId = permitted ? null : departmentIdSession;
		// 该Map集合用于封装分页查询的条件
		Map<String, Object> mapFY = new LinkedHashMap<String, Object>();
		// 将条件封装在mapFy集合中
		// 资料名称
		mapFY.put("documentName", documentName);
		// 将部门ID传下去
		mapFY.put("departmentId", departmentId);
		// 部门名称
		mapFY.put("departmentName", departmentName);
		// 等级
		mapFY.put("level", level);
		// 知识点
		mapFY.put("knowledgeType", knowledgeType);
		
		Map<String, Object> map2 = new LinkedHashMap<String, Object>();
		map2.put("documentName", documentName);// 资料名称
		map2.put("departmentName", departmentName);// 部门名称
		map2.put("level", level);// 等级
		map2.put("knowledgeType", knowledgeType);// 知识点
		map2.put("departmentId", departmentId);// 部门
		
		// 根据 资料名称、部门名称、等级、知识点查询总记录数
		int resultCount = traincontentService.selectTraincontentCountWithFYCondition(map2);
		// 总页数 = 总记录数/每页显示的记录数
		// 当前页页号 (这里应该填 (当前页页号-1)*每页显示的记录数)
		int currentPageInt = (Integer.parseInt(currentPage) - 1) * Integer.parseInt(currentTotal);
		mapFY.put("currentPage", currentPageInt);
		// 每页显示的记录数
		mapFY.put("currentTotal", Integer.parseInt(currentTotal));
		
		List<Map> traincontentList = traincontentService.selectTraincontentWithFYCondition(mapFY);
		
		// 返回总页数 数据的页数 = 数据的总条数/每页显示的记录数
		
		if (traincontentList != null) {
			// 封装当前页要显示的全部培训资料信息
			map.put("traincontentList", traincontentList);
			// 封装总页数
			// map.put("sumPages", sumPages);
			// 总记录数
			map.put("resultCount", resultCount);
			// 每页显示的记录条数
			map.put("currentTotal", Integer.parseInt(currentTotal));
			// 当前页页号
			map.put("currentPage", Integer.parseInt(currentPage));
			map.put("result", "查询成功");
		} else {
			map.put("result", "查询失败");
		}
		
		return "ok";
	}
*/
	/**
	 * 根据培训资料表的主键id 删除单条培训信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delByTrainID() throws Exception {
		map = new LinkedHashMap<String, Object>();
		// 接收从jsp页面传过来的参数 根据参数名接收参数值
		String trainId = ServletActionContext.getRequest().getParameter("documentId");
		Traincontent trainContent = traincontentService.getTrainContentById(Integer.parseInt(trainId));
		String curName = trainContent.getCurrentname();
		// 先删除保存在服务器中的文件
		File fileDel = new File(savePath + curName);
		boolean delResult = fileDel.delete();// 删除原文件
		// 最后删除在数据库中的培训信息
		boolean result = traincontentService.deleteTrainById(Integer.parseInt(trainId));
		if (result) {
			map.put("result", "删除成功");
		} else {
			map.put("result", "删除失败");
		}

		return "ok";
	}

	/**
	 * 批量删除 根据前台传送过来的 数组(该数组中装着要删除的全部id)进行分割，然后转成int类型，然后根据id进行逐个逐个删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delByTrainIDsBatch() throws Exception {
		map = new LinkedHashMap<String, Object>();
		String ids = ServletActionContext.getRequest().getParameter("ids");
		// 去掉两边空格
		ids = ids.trim();
		// 去掉最后一个","逗号
		ids = ids.substring(0, ids.length() - 1);
		// 将ids以","逗号进行分割
		String[] trainIds = ids.split(",");

		List list = new ArrayList();
		for (String id : trainIds) {
			// 要先删除文件，在删除在培训资料表中的数据
			// 获取当前保存在服务器中的文件名，然后删除保存在服务器中的文件
			Traincontent trainContent = traincontentService.getTrainContentById(Integer.parseInt(id));
			String curName = trainContent.getCurrentname();
			// 删除保存在服务器中的文件
			File fileDel = new File(savePath + curName);
			boolean delResult = fileDel.delete();// 删除原文件

			// 根据id进行删除
			boolean result = traincontentService.deleteTrainById(Integer.parseInt(id));

			list.add(result);
			list.add(delResult);
		}

		if (list.contains(false)) {
			map.put("result", "批量删除失败");
		} else {
			map.put("result", "批量删除成功");
		}
		return "ok";
	}

	/**
	 * 查看详情
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findDetail() throws Exception {
		// 接收从jsp页面上传过来的培训资料主键id
		HttpServletRequest request = ServletActionContext.getRequest();
		String trainId = request.getParameter("trainContentId");

		/**
		 * 处理浏览次数，每点击一次查看详情，就让浏览次数+1
		 */
		// 根据培训资料的主键id查询该条培训信息
		Traincontent trainContent = traincontentService.getTrainContentById(Integer.parseInt(trainId));
		Integer browsetimes = trainContent.getBrowsetimes();
		if (browsetimes == null) {
			browsetimes = 0;
		}
		browsetimes = browsetimes + 1;
		trainContent.setBrowsetimes(browsetimes);
		// 将浏览次数进行更新，也就是更新数据
		boolean resul = traincontentService.updateTrainContent(trainContent);

		Traincontent trainContent2 = traincontentService.getTrainContentById(Integer.parseInt(trainId));
		// 将培训资料信息转发给显示详细信息的jsp页面
		request.setAttribute("traincontent", trainContent2);

		// 文件的全路径
		String filePath = savePath + trainContent2.getCurrentname();
		// 将文件的全路径保存在request域对象中，用于将视频播放的时候使用
		request.setAttribute("filePath", filePath);

		// 将日期转换成指定格式
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = sd.format(trainContent2.getUptime());
		request.setAttribute("dateStr", dateStr);

		/**
		 * 视图要判断一下，如果是视频的话，跳转到trainDateView.jsp中
		 * 如果是非视频的话，就跳转到trainDateText.jsp页面中
		 */
		// 获取原文件名
		String originName = trainContent2.getOriginalname();
		/*if (originName.endsWith(".mp4")) {
			return "detailVideo";
		} else {
			return "detailText";// 该视图跳转到显示详细信息的jsp页面
		}*/
		return "detailVideo";
	}

	// =============================以下是 和 培训中心
	// 相关的操作=========================================
	/**
	 * 根据资料等级、资料类型(视频资料或者非视频资料)、知识点、 当前页页号、每页显示的记录数 的分页查询
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String findStudyTraincontentByFy() throws Exception {
		// 要转成json的map集合
		map = new LinkedHashMap<String, Object>();

		

		// 接收从jsp页面传过来的数据
		HttpServletRequest request = ServletActionContext.getRequest();
		// 当前页页号
		String currentPage = request.getParameter("currentPage");
		// 每页显示的记录数
		String resultCount = request.getParameter("resultCount");

		// 当前页页号
		int curPage = Integer.parseInt(currentPage);
		// 每页显示的记录数
		int curTotal = Integer.parseInt(resultCount);

		/******S    PageHelper分页*********/
		PageHelper.startPage(curPage,curTotal);//开始分页
		List<Map<String,Object>> traincontentList = traincontentService.selectTraincontentWithFYCondition(null);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(traincontentList);
		/******E    PageHelper分页*********/
		
		map.put("pageInfo", pageInfo);

		return "ok";
	}

	/**
	 * 点击视频框的时候跳转到视频播放的页面 video.jsp
	 * 
	 * @return
	 * @throws Exception
	 */
	public String videoPlay() throws Exception {
		// 接收从jsp页面上传过来的培训资料主键id
		HttpServletRequest request = ServletActionContext.getRequest();
		String trainId = request.getParameter("trainContentId");

		/**
		 * 处理浏览次数，每点击一次查看详情，就让浏览次数+1
		 */
		// 根据培训资料的主键id查询该条培训信息
		Traincontent trainContent = traincontentService.getTrainContentById(Integer.parseInt(trainId));
		Integer browsetimes = trainContent.getBrowsetimes();
		if (browsetimes == null) {
			browsetimes = 0;
		}
		browsetimes = browsetimes + 1;
		trainContent.setBrowsetimes(browsetimes);
		// 将浏览次数进行更新，也就是更新数据
		boolean resul = traincontentService.updateTrainContent(trainContent);

		Traincontent trainContent2 = traincontentService.getTrainContentById(Integer.parseInt(trainId));
		// 将培训资料信息转发给显示详细信息的jsp页面
		request.setAttribute("traincontent", trainContent2);

		// 文件的全路径
		String filePath = savePath + trainContent2.getCurrentname();
		// 将文件的全路径保存在request域对象中，用于将视频播放的时候使用
		request.setAttribute("filePath", filePath);

		// 将日期转换成指定格式
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = sd.format(trainContent2.getUptime());
		request.setAttribute("dateStr", dateStr);

		// 获取原文件名
		String originName = trainContent2.getOriginalname();

		return "videoPlay";

	}

	
	
	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
}
