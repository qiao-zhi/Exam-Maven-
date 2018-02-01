package cn.xm.exam.action.grade;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.io.File;

/**   
*    
* 项目名称：Exam   
* 类名称：Excel2JSON   
* 类描述：将Excel文件中的内容读取出来以json格式的字符串显示   
* 创建人：Leilong 
* 创建时间：2017年10月8日 下午6:35:45      
* @version    
*    
*/
public class Excel2JSON {

	/**
	 * 将Excel数据转为JSON格式数组
	 *@param name Excel路径
	 *@return Json格式数组
	 */
    public static String excel2Json(String name) {
        Sheet sheet;
        Workbook book;
        Cell cell1, cell2, cell3;
        JSONArray array = new JSONArray();
        try {
        	File importFile = new File(name);
            //为要读取的excel文件名
            book = Workbook.getWorkbook(importFile);

            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet = book.getSheet(0);
            //excel文件从第2行开始读取数据
            for (int i = 1; i < sheet.getRows(); i++) {
                //获取每一行的单元格
                cell1 = sheet.getCell(1, i);//（列，行）
                cell2 = sheet.getCell(2, i);
                cell3 = sheet.getCell(4, i);                
                if ("".equals(cell1.getContents())) {//如果读取的数据为空
                    break;
                }
                JSONObject object = new JSONObject();
                object.put("employeeid",cell1.getContents());
                object.put("employeename",cell2.getContents());
                if ("".equals(cell3.getContents())) {//如果读取的数据为空
                	object.put("grade","0");
                }else{
                	 object.put("grade",cell3.getContents());  
                }               
                object.put("exammethod","线下");
                object.put("employeetype","1");                
                array.add(object);
            }           
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array.toString();
    }
}