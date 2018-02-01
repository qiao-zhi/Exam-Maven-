package cn.xm.exam.action.grade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cn.xm.exam.bean.grade.Employeeexam;

/**   
*    
* 项目名称：Exam   
* 类名称：JSON2BeanList   
* 类描述：将json字符串转换成相应的list集合，并对其进行排序   
* 创建人：Leilong   
* 创建时间：2017年10月6日 下午9:53:15    
* @version    
*    
*/
public class JSON2BeanList {
	/**
	 * 将字符串转为list集合对象
	 *@param json json数组字符串
	 *@return list集合
	 */
	@SuppressWarnings("unchecked")
	public static  List<Employeeexam> json2list(String json){
		List<Employeeexam> list = new ArrayList<Employeeexam>();
		Gson gson = new Gson();
		list = (List<Employeeexam>) gson.fromJson(json, new TypeToken<List<Employeeexam>>(){}.getType());
		//对集合中的元素进行排序，按照考号即身份证号进行排序
		Collections.sort(list,new Comparator<Employeeexam>(){

			@Override
			public int compare(Employeeexam o1, Employeeexam o2) {
				//身份证号最后一位是校验位，可能出现X的情况，将最后一位剔除后进行对比
				String str1 = o1.getEmployeeid().substring(0,17);
				Long first = Long.valueOf(str1);
				String str2 = o2.getEmployeeid().substring(0,17);
				Long second = Long.valueOf(str2);
				Long i = first - second;
				if(i>0){
					return 1;
				}else{					
					return -1;
				}
			}
			
		});
		return list;
	}
	
}
