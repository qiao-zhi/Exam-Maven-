package cn.xm.exam.utils;


import java.util.List;
import java.util.Map;

public class List2Array {
	
	/**
	 * Java基础实现List集合转换为Array类型
	 * @param headerAlias
	 * @param data
	 * @return
	 */
	public static Object[][] convert(String[] headerAlias, List<Map<String, Object>> dataList) {
		
		int rows = dataList.size();
		int columns = headerAlias.length;
		
		Object[][] result = new Object[rows][columns];
		Map<String, Object> data = null;
		
		for (int i = 0; i < rows; i++) {
			data = dataList.get(i);
			for (int j = 0; j < columns; j++) {
				result[i][j] = data.get(headerAlias[j]);
			}
		}
		
		return result;
	}
	
	/**
	 * Java8实现List集合转换为Array类型
	 * @param headerAlias
	 * @param data
	 * @return
	 */
	public static Object[][] map(String[] headerAlias, List<Map<String, Object>> data) {
		
		
		return null;
	}
	
}
