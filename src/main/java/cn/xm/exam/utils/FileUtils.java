package cn.xm.exam.utils;

import java.util.UUID;

import org.junit.Test;

/**
 * 产生唯一文件名的工具
 * 
 * @author QiaoLiQiang
 * @time 2017年9月18日上午9:08:15
 */
public class FileUtils {
	/**
	 * 获取文件的新名称
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件新生成的名称
	 */
	public static String getNewFileName(String fileName) {
		StringBuffer newFileName = new StringBuffer();
		String extension = fileName.substring(fileName.lastIndexOf('.'));
		newFileName.append(UUID.randomUUID().toString());
		newFileName.append(extension);
		return newFileName.toString();
	}

	/**
	 * 用时间产生文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getNewFileNameWithTimeStamp(String fileName) {
		StringBuffer newFileName = new StringBuffer();
		String extension = fileName.substring(fileName.lastIndexOf('.'));
		newFileName.append(System.currentTimeMillis());
		newFileName.append(extension);
		return newFileName.toString();
	}
	
/*	@Test
	public void test1(){
		String newFileName = getNewFileNameWithTimeStamp("下怒焰.docx");
		System.out.println(newFileName);
	}*/
	
}
