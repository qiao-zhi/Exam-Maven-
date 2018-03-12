package cn.xm.exam.utils;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * 实现word转换为HTML
 * 
 * @author QiaoLiQiang
 * @time 2018年2月3日下午2:17:43
 */
public class Word2HtmlUtil {

	// 8 代表word保存成html
	public static final int WORD_HTML = 8;

	public static void main(String[] args) {
		String docfile = "C:\\Users\\liqiang\\Desktop\\day12-day15集合.doc";
		String htmlfile = "C:\\Users\\liqiang\\Desktop\\day12-day15集合.html";
		Word2HtmlUtil.wordToHtml(docfile, htmlfile);
	}

	/**
	 * WORD转HTML
	 * 
	 * @param docfile
	 *            WORD文件全路径
	 * @param htmlfile
	 *            转换后HTML存放路径
	 */
	public static boolean wordToHtml(String inPath, String toPath) {

		// 启动word
		ActiveXComponent axc = new ActiveXComponent("Word.Application");

		boolean flag = false;

		try {
			// 设置word不可见
			axc.setProperty("Visible", new Variant(false));

			Dispatch docs = axc.getProperty("Documents").toDispatch();

			// 打开word文档
			Dispatch doc = Dispatch.invoke(docs, "Open", Dispatch.Method,
					new Object[] { inPath, new Variant(false), new Variant(true) }, new int[1]).toDispatch();

			// 作为html格式保存到临时文件
			Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] { toPath, new Variant(8) }, new int[1]);

			Variant f = new Variant(false);
			Dispatch.call(doc, "Close", f);
			flag = true;
			return flag;

		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		} finally {
			axc.invoke("Quit", new Variant[] {});
		}
	}
}
