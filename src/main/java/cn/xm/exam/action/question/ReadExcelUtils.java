package cn.xm.exam.action.question;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.xm.exam.bean.question.Options;
import cn.xm.exam.bean.question.Questions;
import cn.xm.exam.utils.UUIDUtil;

/**   
*    
* 项目名称：Exam   
* 类名称：ReadExcelUtils   
* 类描述：将Excel文件中的内容读取出来封装到实体类中
* 创建人：Leilong 
* 创建时间：2017年10月8日 下午6:35:45      
* @version    
*    
*/
public class ReadExcelUtils {

	/**
	 * 将Excel数据转为JSON格式数组
	 *@param name Excel路径
	 *@return Json格式数组
	 */
    public static List<HashMap<String, Object>> readExcelData(String name) {
        Sheet sheet,sheet1,sheet2;
        Workbook book;
        Cell cell1, cell2, cell3, cell4, cell5, cell6, cell7;
        //一条记录对应一道试题，存放试题对象和选项集合
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
        try {
            //为要读取的excel文件名
            book = Workbook.getWorkbook(new File(name));
            
            /********************单选题**************************/
            //获得第一个工作表对象即单选题
            sheet = book.getSheet(0);
            //excel文件从第3行开始读取数据
            for (int i = 2; i < sheet.getRows(); i++) {
            	//试题对象
            	Questions questionObj= new Questions();
            	//选项集合
            	List<Options> optionsObjList = new ArrayList<Options>();
                //获取每一行的单元格
                cell1 = sheet.getCell(0, i);//（列，行）题干
                cell2 = sheet.getCell(1, i);//第一个选项
                cell3 = sheet.getCell(2, i);//第二个选项
                cell4 = sheet.getCell(3, i);//第三个选项   
                cell5 = sheet.getCell(4, i);//第四个选项 
                cell6 = sheet.getCell(5, i);//答案  
                if ("".equals(cell1.getContents())) {//如果读取的数据为空
                    break;
                }
                //试题对象中设置的信息：试题ID，题目，带标签的题目，题型，答案
                //设置试题的id
 				String questionObjId = UUIDUtil.getUUID2();
 				questionObj.setQuestionid(questionObjId);
 				//设置试题的题干
 				questionObj.setQuestion(cell1.getContents());
 				questionObj.setQuestionwithtag(cell1.getContents());
 				//设置试题的类型
 				questionObj.setType("单选题");
 				//设置试题的答案
 				questionObj.setAnswer(cell6.getContents());
 				
 				//选项集合中每个选项设置的信息：试题ID，选项ID，选项序号，选项内容
 				//如果第一个选项不为空
 				 if (!"".equals(cell2.getContents())) {
 					Options option1 = new Options();
 					//设置选项内容
 					option1.setOptioncontent(cell2.getContents());
 					option1.setOptionsequence("1");
 					//设置选项的id
 					option1.setOptionid(UUIDUtil.getUUID2());
 					//设置试题的id
 					option1.setQuestionid(questionObjId);
 					optionsObjList.add(option1);
                 }
 				//如果第二个选项不为空
 				 if (!"".equals(cell3.getContents())) {
 					Options option2 = new Options();
 					//设置选项内容
 					option2.setOptioncontent(cell3.getContents());
 					option2.setOptionsequence("2");
 					//设置选项的id
 					option2.setOptionid(UUIDUtil.getUUID2());
 					//设置试题的id
 					option2.setQuestionid(questionObjId);
 					optionsObjList.add(option2);
                 }
 				//如果第三个选项不为空
 				 if (!"".equals(cell4.getContents())) {
 					Options option3 = new Options();
 					//设置选项内容
 					option3.setOptioncontent(cell4.getContents());
 					option3.setOptionsequence("3");
 					//设置选项的id
 					option3.setOptionid(UUIDUtil.getUUID2());
 					//设置试题的id
 					option3.setQuestionid(questionObjId);
 					optionsObjList.add(option3);
                 }
 				//如果第四个选项不为空
 				 if (!"".equals(cell5.getContents())) {
 					Options option4 = new Options();
 					//设置选项内容
 					option4.setOptioncontent(cell5.getContents());
 					option4.setOptionsequence("4");
 					//设置选项的id
 					option4.setOptionid(UUIDUtil.getUUID2());
 					//设置试题的id
 					option4.setQuestionid(questionObjId);
 					optionsObjList.add(option4);
                 }
 				HashMap<String,Object> map = new HashMap<String,Object>();
 	 			map.put("question", questionObj);
 	 			map.put("options", optionsObjList);
 	 			list.add(map);
            }
            
            /********************多选题**************************/
            
            //获得第二个工作表对象即多选题
            sheet1 = book.getSheet(1);
            //excel文件从第3行开始读取数据
            for (int i = 2; i < sheet1.getRows(); i++) {
            	//试题对象
            	Questions questionObj= new Questions();
            	//选项集合
            	List<Options> optionsObjList = new ArrayList<Options>();
                //获取每一行的单元格
                cell1 = sheet1.getCell(0, i);//（列，行）题干
                cell2 = sheet1.getCell(1, i);//第一个选项
                cell3 = sheet1.getCell(2, i);//第二个选项
                cell4 = sheet1.getCell(3, i);//第三个选项   
                cell5 = sheet1.getCell(4, i);//第四个选项 
                cell6 = sheet1.getCell(5, i);//第五个选项
                cell7 = sheet1.getCell(6, i);//答案  
                if ("".equals(cell1.getContents())) {//如果读取的数据为空
                    break;
                }
                //试题对象中设置的信息：试题ID，题目，带标签的题目，题型，答案
                //设置试题的id
 				String questionObjId = UUIDUtil.getUUID2();
 				questionObj.setQuestionid(questionObjId);
 				//设置试题的题干
 				questionObj.setQuestion(cell1.getContents());
 				questionObj.setQuestionwithtag(cell1.getContents());
 				//设置试题的类型
 				questionObj.setType("多选题");
 				//设置试题的答案
 				questionObj.setAnswer(cell7.getContents());
 				
 				//选项集合中每个选项设置的信息：试题ID，选项ID，选项序号，选项内容
 				//如果第一个选项不为空
 				 if (!"".equals(cell2.getContents())) {
 					Options option1 = new Options();
 					//设置选项内容
 					option1.setOptioncontent(cell2.getContents());
 					option1.setOptionsequence("1");
 					//设置选项的id
 					option1.setOptionid(UUIDUtil.getUUID2());
 					//设置试题的id
 					option1.setQuestionid(questionObjId);
 					optionsObjList.add(option1);
                 }
 				//如果第二个选项不为空
 				 if (!"".equals(cell3.getContents())) {
 					Options option2 = new Options();
 					//设置选项内容
 					option2.setOptioncontent(cell3.getContents());
 					option2.setOptionsequence("2");
 					//设置选项的id
 					option2.setOptionid(UUIDUtil.getUUID2());
 					//设置试题的id
 					option2.setQuestionid(questionObjId);
 					optionsObjList.add(option2);
                 }
 				//如果第三个选项不为空
 				 if (!"".equals(cell4.getContents())) {
 					Options option3 = new Options();
 					//设置选项内容
 					option3.setOptioncontent(cell4.getContents());
 					option3.setOptionsequence("3");
 					//设置选项的id
 					option3.setOptionid(UUIDUtil.getUUID2());
 					//设置试题的id
 					option3.setQuestionid(questionObjId);
 					optionsObjList.add(option3);
                 }
 				//如果第四个选项不为空
 				 if (!"".equals(cell5.getContents())) {
 					Options option4 = new Options();
 					//设置选项内容
 					option4.setOptioncontent(cell5.getContents());
 					option4.setOptionsequence("4");
 					//设置选项的id
 					option4.setOptionid(UUIDUtil.getUUID2());
 					//设置试题的id
 					option4.setQuestionid(questionObjId);
 					optionsObjList.add(option4);
                 }
 				//如果第五个选项不为空
 				 if (!"".equals(cell6.getContents())) {
 					Options option5 = new Options();
 					//设置选项内容
 					option5.setOptioncontent(cell6.getContents());
 					option5.setOptionsequence("5");
 					//设置选项的id
 					option5.setOptionid(UUIDUtil.getUUID2());
 					//设置试题的id
 					option5.setQuestionid(questionObjId);
 					optionsObjList.add(option5);
                 }
 				HashMap<String,Object> map = new HashMap<String,Object>();
 	 			map.put("question", questionObj);
 	 			map.put("options", optionsObjList);
 	 			list.add(map);
            }           
            
            /********************判断题**************************/
            //获得第三个工作表对象即单选题
            sheet2 = book.getSheet(2);
            //excel文件从第3行开始读取数据
            for (int i = 2; i < sheet2.getRows(); i++) {
            	//试题对象
            	Questions questionObj= new Questions();
            	//选项集合
            	List<Options> optionsObjList = new ArrayList<Options>();
                //获取每一行的单元格
                cell1 = sheet2.getCell(0, i);//（列，行）题干
                cell2 = sheet2.getCell(1, i);//答案
               
                if ("".equals(cell1.getContents())) {//如果读取的数据为空
                    break;
                }
                //试题对象中设置的信息：试题ID，题目，带标签的题目，题型，答案
                //设置试题的id
 				String questionObjId = UUIDUtil.getUUID2();
 				questionObj.setQuestionid(questionObjId);
 				//设置试题的题干
 				questionObj.setQuestion(cell1.getContents());
 				questionObj.setQuestionwithtag(cell1.getContents());
 				//设置试题的类型
 				questionObj.setType("判断题");
 				//设置试题的答案
 				questionObj.setAnswer(cell2.getContents());
 				
 				//选项集合中每个选项设置的信息：试题ID，选项ID，选项序号，选项内容
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
 				
 				HashMap<String,Object> map = new HashMap<String,Object>();
 	 			map.put("question", questionObj);
 	 			map.put("options", optionsObjList);
 	 			list.add(map);
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}