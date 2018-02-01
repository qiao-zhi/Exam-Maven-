package cn.xm.exam.utils;
import java.io.FileOutputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;

/**   
*    
* 项目名称：Exam   
* 类名称：BSASE64   
* 类描述：图片解密   
* 创建人：wangyueyue 
* 创建时间：2017年11月15日 下午9:20:51     
* @version    
*    
*/
public class BSASE64 {
	public static boolean generateImage(String imgStr, String path) {
		if (imgStr == null)
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
        try{
        	//解密
        	byte[] b=decoder.decodeBuffer(imgStr);
        	//
        	for(int i=0;i<b.length;i++){
        		if(b[i]<0){
        			b[i]+=256;
        		}
        	}
        	OutputStream out = new FileOutputStream(path);
        	out.write(b);
        	out.flush();
        	out.close();
        	return true;
        }catch(Exception e){
        	return false;
        	
        }
		
		}
}
