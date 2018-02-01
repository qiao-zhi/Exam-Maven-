package cn.xm.exam.action.employee.out;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import cn.xm.exam.vo.employee.out.EmployeeOutBaseInfo;

/**   
*    
* 项目名称：Exam   
* 类名称：GenerateExcelEmployeeOutInfo   
* 类描述：按照指定的格式生成Excel文件   
* 创建人：LeiLong  
* 创建时间：2017年10月8日 下午6:38:45      
* @version    
*    
*/
public class GenerateExcelEmployeeOutInfo {
	/**
	 * 将List<EmployeeExamGrade>导出到Excel
	 *@param list  数据
	 *@param fileName   导出文件名字
	 */
	public static void exportExcelPaper(List<EmployeeOutBaseInfo> list,String fileName) {
		// 标题
		String[] title = { "部门名称","员工姓名","员工性别","身份证号","工种"};
		// 创建一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建一个工作表sheet
		HSSFSheet sheet = workbook.createSheet();
		// 设置列宽
		setColumnWidth(sheet, 8);
		// 创建第一行
		HSSFRow row = sheet.createRow(0);
		// 创建一个单元格
		HSSFCell cell = null;
		// 创建表头
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			// 设置样式
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置字体居中
			// 设置字体
			HSSFFont font = workbook.createFont();
			font.setFontName("宋体");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
			// font.setFontHeight((short)12);
			font.setFontHeightInPoints((short) 13);
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);

			cell.setCellValue(title[i]);
		}

		// 从第二行开始追加数据
		for (int i = 1; i < (list.size() + 1); i++) {
			// 创建第i行
			HSSFRow nextRow = sheet.createRow(i);
			for (int j = 0; j < 5; j++) {
				EmployeeOutBaseInfo eEmployeeOutInfo = list.get(i-1);
				HSSFCell cell2 = nextRow.createCell(j);
				if (j == 0) {
					cell2.setCellValue(eEmployeeOutInfo.getDepartmentname());
				}
				if (j == 1) {
					
					cell2.setCellValue( eEmployeeOutInfo.getName());
				}
				if (j == 2) {
					cell2.setCellValue(eEmployeeOutInfo.getSex().replace("1","男").replace("2","女"));
				}
				if (j == 3) {
					cell2.setCellValue(eEmployeeOutInfo.getEmpoutidcard());
				}
				if (j == 4) {
					cell2.setCellValue(eEmployeeOutInfo.getEmptype());
				}
				
			}
		}
		File file = new File(fileName);
		try {
			//关闭工作簿
			workbook.close();
			file.createNewFile();
			// 打开文件流
			FileOutputStream outputStream = FileUtils.openOutputStream(file);
			workbook.write(outputStream);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 设置列宽()
	private static void setColumnWidth(HSSFSheet sheet, int colNum) {
		for (int i = 0; i < colNum; i++) {
			int v = 0;
			v = Math.round(Float.parseFloat("15.0") * 37F);
			v = Math.round(Float.parseFloat("20.0") * 267.5F);
			sheet.setColumnWidth(i, v);
		}
	}

}
