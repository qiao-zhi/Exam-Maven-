package cn.xm.exam.action.question;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 项目名称：Exam
 * 类名称：ExportBatchModelAction
 * 类描述： 导出试题批量导入模版 
 * 创建人：Leilong
 * 创建时间：2017年11月22日 上午3:18:24
 * @version
 * 
 */
@Controller
@Scope("prototype")
public class ExportBatchModelAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	// spring属性注入方式注入服务器目录地址
	private String serverPath;// 注入要下载的文件的地址
	// 接收文件名
	private String name;// 这次是从jsp中的那个a标签中来的

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public String getServerPath() {
		return serverPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 需要提供给struts写出数据的输入流
	public InputStream getInputStream() {
		try {
			// 转码：解决中文乱码问题
			// 先用ISO8859-1编码 再使用UTF-8解码
			// String filename = new
			// String(name.getBytes("ISO8859-1"),"UTF-8");//中名名称.后缀名
			String filename = name;
			String path = ServletActionContext.getServletContext().getRealPath(serverPath);
			FileInputStream fis = new FileInputStream(new File(path + "\\" + filename));// 服务器目录地址+文件名
			name = new String(name.getBytes("UTF-8"), "ISO8859-1");
			return fis;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	// 下载方法
	public String execute() {
		return SUCCESS;
	}
}
