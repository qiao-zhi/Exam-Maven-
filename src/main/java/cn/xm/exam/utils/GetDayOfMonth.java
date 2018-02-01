package cn.xm.exam.utils;
import java.text.SimpleDateFormat;  
import java.util.Calendar;

/**
 * 获取指定月的第一天和最后一天的工具
 * 
 * @author leilong
 * @time 2017年10月8日 下午5:04:26   
 */
public class GetDayOfMonth {
	/** 
	* 获得该月第一天 
	* @param year 
	* @param month 
	* @return 
	*/  
	public static String getFirstDayOfMonth(int year,int month){  
	        Calendar cal = Calendar.getInstance();  
	        //设置年份  
	        cal.set(Calendar.YEAR,year);  
	        //设置月份  
	        cal.set(Calendar.MONTH, month-1);  
	        //获取某月最小天数  
	        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);  
	        //设置日历中月份的最小天数  
	        cal.set(Calendar.DAY_OF_MONTH, firstDay);  
	        //格式化日期  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String firstDayOfMonth = sdf.format(cal.getTime());  
	        return firstDayOfMonth;  
	    }  
	  
	/** 
	* 获得该月最后一天 
	* @param year 
	* @param month 
	* @return 
	*/  
	public static String getLastDayOfMonth(int year,int month){  
	        Calendar cal = Calendar.getInstance();  
	        //设置年份  
	        cal.set(Calendar.YEAR,year);  
	        //设置月份  
	        cal.set(Calendar.MONTH, month-1);  
	        //获取某月最大天数  
	        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
	        //设置日历中月份的最大天数  
	        cal.set(Calendar.DAY_OF_MONTH, lastDay);  
	        //格式化日期  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String lastDayOfMonth = sdf.format(cal.getTime());  
	        return lastDayOfMonth;  
	    }  
}
