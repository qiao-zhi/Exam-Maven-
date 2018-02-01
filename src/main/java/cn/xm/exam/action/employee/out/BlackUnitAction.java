package cn.xm.exam.action.employee.out;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.xm.exam.bean.employee.out.BlackUnit;
import cn.xm.exam.service.employee.out.BlackUnitService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;

@Controller
@Scope("prototype")
public class BlackUnitAction extends ActionSupport implements ModelDriven<BlackUnit>{
	private BlackUnit blackUnit = new BlackUnit();
	private String result;
	//当前页
	private String currentPage;
	//当前页显示条数
	private String currentCount;
	//返回值
	private Map<String,Object> listInfo;
	private static final long serialVersionUID = 1L;
	@Resource
	 private BlackUnitService blackUnitService;
	/**
	 * 添加单位黑名单信息
	 * @return
	 */
	public String addBlackUnitInfo(){
		result="";
		try {
			result= blackUnitService.addBlackUnit(blackUnit)?"添加成功!":"添加失败!";
		} catch (SQLException e) {
			result = "添加失败!";
			e.getMessage();
		}
		return SUCCESS;
	}
	
	/**
	 * 修改单位黑名单信息
	 * @return
	 */
	public String updateBlackUnitInfo(){
		result="";
		try {
			result= blackUnitService.updateBlackUnitByBlackId(blackUnit)?"修改成功!":"修改失败!";
		} catch (SQLException e) {
			result = "修改失败!";
			e.getMessage();
		}
		return SUCCESS;
		
	}
	
	/**
	 * 删除单位黑名单信息
	 * @return
	 */
	private String blackUnitId;
	public String deleteBlackUnitInfo(){
		 result="";
		try {
			result= blackUnitService.deleteBlackUnitByBlackId(Integer.valueOf(blackUnitId))?"刪除成功!":"刪除失败!";
		} catch (SQLException e) {
			result = "刪除失败!";
			e.getMessage();
		}
		return SUCCESS;
		
	}
	
	/**
	 * 查询单位黑名单信息
	 * @return
	 */
	public String findBlackUnitInfos(){
		
		// 对当前页信息进行设置
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		// 对当前页显示的信息进行设置
		if (currentCount == null || "".equals(currentCount.trim())) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
		
		listInfo = new HashMap<String,Object>();
		
		try {
			PageBean<BlackUnit> pageBean = blackUnitService.getBlackUnitPage(Integer.valueOf(currentPage), Integer.valueOf(currentCount));
			listInfo.put("pageBean", pageBean);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "ok";
		
	}

	@Override
	public BlackUnit getModel() {
		return blackUnit;
	}

	public String getBlackUnitId() {
		return blackUnitId;
	}

	public void setBlackUnitId(String blackUnitId) {
		this.blackUnitId = blackUnitId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(String currentCount) {
		this.currentCount = currentCount;
	}

	public Map<String, Object> getListInfo() {
		return listInfo;
	}

	public void setListInfo(Map<String, Object> listInfo) {
		this.listInfo = listInfo;
	}
	
	

}
