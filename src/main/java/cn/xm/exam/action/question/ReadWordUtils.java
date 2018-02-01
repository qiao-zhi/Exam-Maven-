package cn.xm.exam.action.question;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import cn.xm.exam.bean.question.Options;
import cn.xm.exam.bean.question.Questions;
import cn.xm.exam.utils.UUIDUtil;

/**   
*    
* 项目名称：Exam   
* 类名称：ReadWordUtils   
* 类描述：读取word文档中的信息封装到实体类中
* 创建人：Leilong
* 创建时间：2017年10月18日 上午10:39:54      
* @version    
*    
*/
public class ReadWordUtils {
	public static List<HashMap<String, Object>> readWordData(String fileName) throws Exception{
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		FileInputStream fis=new FileInputStream(fileName); //打开文件输入流
		Reader in = new BufferedReader(new InputStreamReader(fis, "utf-8"));
		 char[] cbuf = new char[1024];  
         //用于保存实际读取的字符数  
         int hasRead = 0;  
         StringBuffer sb = new StringBuffer();
         //使用循环读取数据  
         while((hasRead = in.read(cbuf)) > 0){  
               
         //将字符数组转化为字符串输出  
    	 //System.out.println(new String(cbuf,0,hasRead)); 
         sb.append(new String(cbuf,0,hasRead));
               
         } 
         in.close();      
         //将读出的数据进行处理
         String all = sb.toString();
         String s[] = all.split("\t");
         //System.out.println("共有:" + (s.length) + "道题");
 		// 对每一道题进行处理
		for (int i = 0; i < s.length; i++) {
 			Questions questionObj= new Questions();
 			// 将题按照格式进行截取划分
 			String[] question = s[i].split("\r\n");
			//System.out.println("第" + (i + 1) + "道题共有" + question.length + "项");
 			//创建选项集合
			List<Options> optionsObjList = new ArrayList<Options>();
 			if (question.length == 7) {
 				//设置试题的id
 				String questionObjId = UUIDUtil.getUUID2();
 				questionObj.setQuestionid(questionObjId);
 				//处理题干
 				String tigan = question[1].substring(4);
 				questionObj.setQuestion(tigan);
 				questionObj.setQuestionwithtag(tigan);
 				//处理类型
 				String leixing = question[2].substring(4);
 				questionObj.setType(leixing);
 				//知识点
 				String knowledge = question[3].substring(5);
 				questionObj.setKnowledgetype(knowledge);
 				//处理选项
 				String xuanxiang = question[4].substring(4);
 				//对选项进行分割处理
 				String xuanxiangarr[] = xuanxiang.split(",");			
 				int cd = xuanxiangarr.length;
 				for(int k=0;k<cd;k++){
 					Options option = new Options();
 					option.setOptioncontent(xuanxiangarr[k].substring(3, xuanxiangarr[k].length()-1));
 					option.setOptionsequence(String.valueOf(k+1));
 					//设置选项的id
 					option.setOptionid(UUIDUtil.getUUID2());
 					//设置试题的id
 					option.setQuestionid(questionObjId);
 					optionsObjList.add(option);
 				}
 				//处理答案
 				String daan = question[5].substring(4);
 				questionObj.setAnswer(daan);
 				//处理解析
 				String jiexi = question[6].substring(4);
 				questionObj.setAnalysis(jiexi);
 				questionObj.setAnalysiswithtag(jiexi);
 			}
 			if (question.length == 6) {
 				String questionObjId = UUIDUtil.getUUID2();
 				//设置试题的id
 				questionObj.setQuestionid(questionObjId);
 				//处理题干
 				String tigan = question[1].substring(4);
 				questionObj.setQuestion(tigan);
 				questionObj.setQuestionwithtag(tigan);
 				//处理类型
 				String leixing = question[2].substring(4);
 				questionObj.setType(leixing);
 				//知识点
 				String knowledge = question[3].substring(5);
 				questionObj.setKnowledgetype(knowledge);
 				//处理答案
 				String daan = question[4].substring(4);
 				questionObj.setAnswer(daan);
 				//处理解析
 				String jiexi = question[5].substring(4);
 				questionObj.setAnalysis(jiexi);
 				questionObj.setAnalysiswithtag(jiexi);
 				//选项集合判断
 				Options option = new Options();
				option.setOptioncontent("正确");
				option.setOptionsequence("1");
				option.setOptionid(UUIDUtil.getUUID2());
				option.setQuestionid(questionObjId);
				optionsObjList.add(option);
				Options option2 = new Options();
				option2.setOptioncontent("错误");
				option2.setOptionsequence("2");
				option2.setOptionid(UUIDUtil.getUUID2());
				option2.setQuestionid(questionObjId);
				optionsObjList.add(option2);
 				
 			}
 			HashMap<String,Object> map = new HashMap<String,Object>();
 			map.put("question", questionObj);
 			map.put("options", optionsObjList);
 			list.add(map);
 		}
 		return list;
         
	}
	
}
