package cn.xm.exam.utils;

import java.io.FileOutputStream;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

import cn.xm.exam.bean.exam.Bigquestion;
import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.bean.exam.Exampaperoption;
import cn.xm.exam.bean.exam.Exampaperquestion;

/**
 * 将试卷写入pdf的工具类
 * 
 * @author QiaoLiQiang
 * @time 2018年1月6日上午11:32:25
 */
public class ExportPaperPdfUtil {
	private static Font headfont;// 设置字体大小
	private static Font keyfont;// 设置字体大小
	private static Font textfont;// 设置字体大小

	static {
		BaseFont bfChinese;
		try {
			// bfChinese =
			// BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			headfont = new Font(bfChinese, 12, Font.BOLD);// 设置字体大小
			keyfont = new Font(bfChinese, 11, Font.BOLD);// 设置字体大小
			textfont = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将一份试卷写到指定的位置为pdf
	 * 
	 * @param exampaper
	 *            数据源
	 * @param url
	 *            写到位置的pdf的全路径
	 * @throws Exception
	 */
	public static void writeExampaperPdf(Exampaper exampaper, String url) throws Exception {
		// 1.新建document对象
		// 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		// 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		// 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(url));
		// 3.添加页号打开文档
		//3.1添加页号
		HeaderFooter footer = new HeaderFooter(new Phrase("第",textfont), new Phrase("页",textfont));      
		footer.setBorder(Rectangle.NO_BORDER);    
		document.setFooter(footer);  
		//3.2打开文档
		document.open();
		// 4.向文档中添加内容
		// 通过 com.lowagie.text.Paragraph 来添加文本。可以用文本及其默认的字体、颜色、大小等等设置来创建一个默认段落
		Paragraph pt = new Paragraph(exampaper.getTitle(), headfont);// 将标题写进去
		pt.setAlignment(1);// 设置文字居中 0靠左 1，居中 2，靠右
		document.add(pt);
		List<Bigquestion> bigQuestions = exampaper.getBigQuestions();
		for (int i = 0; bigQuestions != null && i < bigQuestions.size(); i++) {
			document.add(new Paragraph("\n"));// 添加段落分隔符 换行
			document.add(new Paragraph(bigQuestions.get(i).getBigquestionname(),keyfont));// 大题题干写进去
			List<Exampaperquestion> questions = bigQuestions.get(i).getQuestions();
			for (int j = 0; questions != null && j < questions.size(); j++) {
//				document.add(new Paragraph("\n"));// 添加段落分隔符 换行
				document.add(new Paragraph(questions.get(j).getQuestionsequence().toString() + ".\t"
						+ questions.get(j).getQuestioncontent(),textfont));// 将小题的题干与序号写进去
				List<Exampaperoption> options = questions.get(j).getOptions();// 获取选项
				for (int k = 0; options != null && k < options.size(); k++) {
//					document.add(new Paragraph("\n"));// 添加段落分隔符 换行
					document.add(new Paragraph("\t"+
							options.get(k).getOptionsequence() + "\t" + options.get(k).getOptioncontent(),textfont));// 将选项序号与题干写进去
				}
			}

		}
		// 5.关闭文档
		document.close();
	}
}
