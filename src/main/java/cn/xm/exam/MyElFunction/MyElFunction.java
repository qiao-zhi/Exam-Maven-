package cn.xm.exam.MyElFunction;

/**
 * 自定义EL函数，方便在JSP中处理一些复杂的字符串替换函数
 * 
 * @author QiaoLiQiang
 * @time 2017年10月29日下午9:09:47
 */
public class MyElFunction {
	/**
	 * 将source字符串按照s1-s2替换，例如:s1:1234,s2:ABCD则为将source中1换为A，2换为B```
	 * 
	 * @param source
	 *            需要被替换的字符串
	 * @param s1
	 *            替换前:1 2 3 4 5
	 * @param s2
	 *            替换后:A B C D E
	 * @return
	 */
	public static String replace(String source, String s1, String s2) {
		for (int i = 0, length_1 = s1.length(); i < length_1; i++) {
			source = source.replace(s1.charAt(i), s2.charAt(i));
		}
		return source;
	}
}
