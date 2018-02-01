package cn.xm.exam.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import cn.xm.exam.bean.employee.in.EmployeeIn;

 /*   
  * 项目名称：Exam   
  * 类名称：GenerateEmployeeInTrainFile   
  * 类描述： 内部员工培训档案模版
  * 创建人：LL   
  * 创建时间：2018年1月20日 上午11:58:32     
  * @version    
  *    
  */
public class GenerateEmployeeInTrainFile {

	public static Map<String,Object> generateEmployeeInTrainProfile(String fileName, EmployeeIn employeeIn,
			List<Map<String, Object>> exams) throws IOException {
		// 创建一个工作簿
		HSSFWorkbook workBook = new HSSFWorkbook();
		// 创建一个工作表
		HSSFSheet sheet = workBook.createSheet("员工培训档案");
		// 设置列宽
		setColumnWidth(sheet, 5);
		// 创建第一行
		HSSFRow row1 = sheet.createRow(0);
		// 行高
		row1.setHeightInPoints(40);
		// 创建一个单元格
		HSSFCell cellFirst = row1.createCell(0);
		cellFirst.setCellValue("员工安全培训档案");
		// 设置单元格样式
		setCellStyle(workBook, cellFirst);
		// 合并单元格
		mergeCell(sheet, 0, 0, 0, 4);

		// 创建第二行
		HSSFRow row2 = sheet.createRow(1);
		// 行高
		row2.setHeightInPoints(40);
		String[] title1 = { "姓名", "性别", "联系方式", "所属单位" };
		HSSFCell cell1 = null;
		for (int j = 0, i = 0; j < 4; j++, i++) {
			cell1 = row2.createCell(j);
			setCellStyleSecond(workBook, cell1, 2);
			// 写入数据
			cell1.setCellValue(title1[i]);
		}

		// 创建第三行
		HSSFRow row3 = sheet.createRow(2);
		// 行高
		row3.setHeightInPoints(40);		
		HSSFCell cell2 = null;
		for (int j = 0; j < 4; j++) {
			cell2 = row3.createCell(j);
			setCellStyleSecond(workBook, cell2, 3);			
			// 设置数据
			if (j == 0) {
				cell2.setCellValue(employeeIn.getName());
			}
			if (j == 1) {
				cell2.setCellValue(employeeIn.getSex().replace("1","男").replace("2", "女"));
			}
			if (j == 2) {
				cell2.setCellValue(employeeIn.getPhone());

			}
			if (j == 3) {				
				cell2.setCellValue(employeeIn.getDepartmentid());
			}
		}

		// 为照片合并单元格
		mergeCell(sheet, 1, 4, 2, 4);
		setCellStyleSecond(workBook, row2.createCell(4), 3);
		setCellStyleSecond(workBook, row3.createCell(4), 3);		
		// 第六行
		HSSFRow row6 = sheet.createRow(5);
		// 行高
		row6.setHeightInPoints(26);
		// 创建一个单元格
		HSSFCell cellSecond = row6.createCell(0);
		cellSecond.setCellValue("考试培训详细信息");
		// 设置单元格样式
		setCellStyle(workBook, cellSecond);
		// 合并单元格
		mergeCell(sheet, 5, 0, 5, 4);

		// 第七行
		HSSFRow row7 = sheet.createRow(6);
		// 行高
		row7.setHeightInPoints(26);
		String[] title2 = { "考试名称", "培训内容", "培训学时", "考试时间", "获得成绩" };
		HSSFCell cell3 = null;
		for (int j = 0, i = 0; j < 5; j++, i++) {
			cell3 = row7.createCell(j);
			setCellStyleSecond(workBook, cell3, 2);
			// 写入数据
			cell3.setCellValue(title2[i]);
		}

		// 从第八行开始循环设置数据
		HSSFRow nextRow = null;
		// 从第八行开始追加数据
		for (int i = 7; i < (exams.size() + 7); i++) {
			// 创建第i行
			 nextRow = sheet.createRow(i);
			 //行高
			 nextRow.setHeightInPoints(30);
			// 获取数据
			Map<String, Object> exam = exams.get(i - 7);
			HSSFCell cell4 = null;
			for (int j = 0; j < 5; j++) {

				cell4 = nextRow.createCell(j);
				//单元格格式
				setCellStyleSecond(workBook, cell4, 3);
				if (j == 0) {
					cell4.setCellValue(exam.get("examName").toString());
				}
				if (j == 1) {

					cell4.setCellValue(exam.get("traincontent").toString());
				}
				if (j == 2) {
					cell4.setCellValue(exam.get("xueshi").toString());
				}
				if (j == 3) {
					cell4.setCellValue(exam.get("startTime").toString().substring(0,10)+ "到" + exam.get("endTime").toString().substring(0,10));
				}
				if (j == 4) {
					cell4.setCellValue(exam.get("grade").toString());
				}

			}
		}						
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("workBook", workBook);
		map.put("sheet", sheet);
		return map;
	}

	// 设置一级标题行的字体和样式
	public static void setCellStyle(HSSFWorkbook workbook, HSSFCell cell) {
		// 设置样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		// 设置字体居中
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 16);
		// 粗体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);
	}

	// 合并单元格
	public static void mergeCell(HSSFSheet sheet, int x, int y, int z, int q) {
		// 参数1：行号 参数2：起始列号 参数3：行号 参数4：终止列号
		//Region region1 = new Region(x, (short) y, z, (short) q);

		//参数1：起始行 参数2：终止行 参数3：起始列号 参数4：终止列号
		CellRangeAddress region1 = new CellRangeAddress(x, z, (short) y, (short) q);     
			
		sheet.addMergedRegion(region1);
	}

	// 设置二级标题行和内容的字体和样式
	public static void setCellStyleSecond(HSSFWorkbook workbook, HSSFCell cell, int i) {
		// 设置样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		// 设置字体居中
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置边框
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 13);
		// 二级标题加粗
		if (i == 2) {
			// 粗体
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		if(i==3){
			cellStyle.setWrapText(true);//设置自动换行
		}
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);
	}

	// 导出图片
	public static void exportPhotoToExcel(HSSFWorkbook workbook, HSSFSheet sheet, String filepath) throws IOException {

		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		BufferedImage bufferImg = null;
		
			bufferImg = ImageIO.read(new File(filepath));
			ImageIO.write(bufferImg, "jpg", byteArrayOut);
		

		// 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// anchor主要用于设置图片的属性
		HSSFClientAnchor anchor = new HSSFClientAnchor(20, 5, 1000, 250, (short) 4, 1, (short) 4, 2);
		// 注意：这个方法在新版本的POI中参数类型改成了（AnchorType anchorType）
		anchor.setAnchorType(3);
		patriarch.createPicture(anchor,
				workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

	}

	// 设置Excel文件的列宽
	public static void setColumnWidth(HSSFSheet sheet, int colNum) {
		for (int i = 0; i < colNum; i++) {
			int v = 0;
			// 对照片列进行处理
			if (i == 4) {
				v = Math.round(Float.parseFloat("16.0") * 267.5F);
			} else {
				v = Math.round(Float.parseFloat("20.0") * 267.5F);
			}
			sheet.setColumnWidth(i, v);
		}
	}

	// 生成文件
	public static void generateExcelFile(String filePath, HSSFWorkbook workBook) {
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 写入excel文件
		try {
			// 关闭工作簿
			workBook.close();
			// 将图片写入创建的文件中
			workBook.write(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
