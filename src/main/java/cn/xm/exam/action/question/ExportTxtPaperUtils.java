package cn.xm.exam.action.question;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;


import cn.xm.exam.vo.question.QuestionsQueryVo;

/**   
*    
* 项目名称：Exam   
* 类名称：ExportTxtPaperUtils   
* 类描述：将试题和选项的内容导出到文本文件的工具类   
* 创建人：Leilong
* 创建时间：2017年10月16日 下午12:55:16     
* @version    
*    
*/
public class ExportTxtPaperUtils {


/**
 * 将题目导入到文本文件中
 *@param list 数据 
 *@param fileName  文本文件
 */
	public static void  exportTxtPaper(List<QuestionsQueryVo> list,String fileName) {
		File outFile = new File(fileName);
		Writer out;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile,true), "utf-8"), 10240);
			for (int i = 0; i < list.size(); i++) {
				if(!list.get(i).getType().equals("判断题")){
					String optionsStr = "";
					for(int j=0;j<list.get(i).getOptions().size();j++){
						optionsStr += "\r\n\t"+list.get(i).getOptions().get(j).getOptioncontent();
					}
					out.write(
							(i+1)+".  "+list.get(i).getQuestion()+"("+list.get(i).getType()+")"
							+ optionsStr							
							+ "\r\n等级:"+list.get(i).getLevel()												
							+ "\r\n答案:"+list.get(i).getAnswer()
							+"\r\n");
					out.write("\r\n");
				}else{
					out.write(
							(i+1)+".  "+list.get(i).getQuestion()+"("+list.get(i).getType()+")"
							+ "\r\n答案:"+list.get(i).getAnswer()
							+"\r\n");
					out.write("\r\n");
				}
				
			}
			out.flush();
			out.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	
}
