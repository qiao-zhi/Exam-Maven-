package cn.xm.exam.action.employee.in;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.utils.ResourcesUtil;
import cn.xm.exam.utils.UUIDUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ImportEmployeeIn {
	/**
	 * 保存图片
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	
	//static Properties props= PathUtils.PhotoPath();
	
	public static String savePhoto(String str) throws Exception{
		
	
		
		int bytesum = 0;
		int byteread = 0;
		String name=null;
        File oldfile = new File(str); 
        System.out.println("str"+str);
        System.out.println("oldfile"+oldfile);
        if (oldfile.exists()) { //文件存在时 
           InputStream inStream = new FileInputStream(oldfile); //读入原文件 
           System.out.println("输入的流"+inStream);
           
           
           //String name=UUIDUtil.getUUID2()+photoFileName.substring(photoFileName.lastIndexOf("."));
           //新文件的名称
           name=UUIDUtil.getUUID2()+str.substring(str.lastIndexOf("."));
           System.out.println("name"+name);
           //新文件
         //  FileOutputStream fs = new FileOutputStream("/files/EmployeeIn/"+name); 
           
          // ServletContext servletContext = ServletActionContext.getServletContext();
          // String dir = servletContext.getRealPath("/files/EmployeeIn/"+name);
           
           String dir = ResourcesUtil.getValue("path", "photo")+name;
           System.out.println(dir);
           FileOutputStream outputStream = new FileOutputStream(dir);
           System.out.println("输出流"+outputStream);
           byte[] buffer = new byte[1024]; 
           while ( (byteread = inStream.read(buffer)) != -1) { 
               bytesum += byteread; //字节数 文件大小 
               outputStream.write(buffer, 0, byteread); 
           } 
           inStream.close(); 
           outputStream.close();
          
        }
        System.out.println("保存的图片"+name);
        return name;    
		
		
	}

	public static  List<EmployeeIn> excel2Json(String name) {
		System.out.println("进入Excel2JSON");
       System.out.println("name"+name);
       Sheet sheet;
       Workbook book;
       Cell cell1, cell2, cell3, cell4, cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12;
       //JSONArray array = new JSONArray();
       List<EmployeeIn> employeeIns = new ArrayList<EmployeeIn>();
       try {
           //为要读取的excel文件名  "F://a.xls"
           book = Workbook.getWorkbook(new File(name));

           //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
           sheet = book.getSheet(0);
           System.out.println("sheet"+sheet);
           for (int i = 1; i < sheet.getRows(); i++) {
               //获取每一行的单元格
        	   System.out.println(sheet.getCell(0, i));
        	   System.out.println(sheet.getCell(1, i));
               cell1 = sheet.getCell(0, i);//（列，行）
               cell2 = sheet.getCell(1, i);
               cell3 = sheet.getCell(2, i);
               cell4 = sheet.getCell(3, i);
               cell5 = sheet.getCell(4, i);
               cell6 = sheet.getCell(5, i);
               cell7 = sheet.getCell(6, i);
               cell8 = sheet.getCell(7, i);
               cell9 = sheet.getCell(8, i);
               cell10 = sheet.getCell(9, i);
               cell11 = sheet.getCell(10, i);
               cell12 = sheet.getCell(11, i);
             
               if ("".equals(cell1.getContents())) {//如果读取的数据为空
                   break;
               }
               
               
               EmployeeIn employeein = new EmployeeIn();
               //employeein.setEmployeeid(cell1.getContents());
               //employeein.setEmployeenumber(cell2.getContents());
               //员工id
               String employeeid = UUID.randomUUID().toString().replaceAll("-", "");
               employeein.setEmployeeid(employeeid);
             
               System.out.println("cell1"+cell1.getContents());
               System.out.println("cell2.getContents()"+cell2.getContents());
               System.out.println("cell3.getContents()"+cell3.getContents());
               employeein.setName(cell1.getContents());
               employeein.setIdcode(cell2.getContents());
               
               if("男".equals(cell3.getContents())){
            	   employeein.setSex("1");
               }else if("女".equals(cell3.getContents())){
            	   employeein.setSex("2");
               }
              // employeein.setSex(cell3.getContents());
               
               System.out.println("employeein"+employeein);
               //日期类型
               SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-mm-dd");
               
               
               System.out.println("时间"+cell4.getContents());
               //System.out.println(cell4.getType().toString());
              /* System.out.println(cell4.getCellFormat().getFormat());
               System.out.println(cell4.getCellFormat().getIndentation());
               
               */
              /* if (0 == cell4.getType()) {
            	 //判断是否为日期类型
            	 if(HSSFDateUtil.isCellDateFormatted(cell)){
            	 //用于转化为日期格式
            	 Date d = cell.getDateCellValue();
            	 DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	 str[k] = formater.format(d);
            	 }else{
            	 // 用于格式化数字，只保留数字的整数部分
            	 DecimalFormat df = new DecimalFormat("########");
            	 str[k] = df.format(cell.getNumericCellValue());
            	 }*/
               
               
              
              /*// if (cell4.getType() == Cell.CELL_TYPE_NUMERIC) {  
            	    if (DateUtil.isCellDateFormatted(e))// 判断单元格是否属于日期格式  
            	        System.out.println(cell4.getDateCellValue());//java.util.Date类型  
            	//}  
               */
               
               
   			   Date d = sdf2.parse(cell4.getContents());
   			   System.out.println("d"+d);
               employeein.setBirthday(d);
               
             //图片
               String photo =savePhoto(cell5.getContents());
               employeein.setPhoto(photo);
               
              // employeein.setPhoto(cell7.getContents());
               employeein.setPhone(cell6.getContents());
               employeein.setEmail(cell7.getContents());
               employeein.setAddress(cell8.getContents());
               //employeein.setDuty(cell11.getContents());
               employeein.setDuty(cell9.getContents());
               //上级部门名称
               //employeein.setDepartmentid(cell9.getContents());
               System.out.println("cell10.getContents()"+cell10.getContents());
              
               employeein.setDepartmentid(cell10.getContents());
            
               
               employeein.setFinger(cell11.getContents());
               employeein.setIsdelete(cell12.getContents());
              //int型
              // employeein.setTrainstatus(Integer.parseInt(cell15.getContents()));
               //按时间排序
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-ddHHmmss");
               employeein.setSort(sdf.format(new Date()));
               //员工编号
               SimpleDateFormat sdf3 = new SimpleDateFormat("yyyymmddHHmmss");
               employeein.setEmployeenumber(sdf3.format(new Date()));
              
               //培训状态
               employeein.setTrainstatus(0);
               System.out.println("employeein"+employeein);
              
               
               employeeIns.add(employeein);
               
               
           }
           System.out.println(employeeIns);
           book.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return employeeIns; 
       
       
   	
	}
}
