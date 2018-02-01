package cn.xm.exam.service.impl.news;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.news.NewsPicture;
import cn.xm.exam.mapper.common.custom.NewsPictureCustomMapper;
import cn.xm.exam.service.news.NewsPictureService;

@Service
public class NewsPictureServiceImpl implements NewsPictureService {

	@Autowired
	private NewsPictureCustomMapper newsPMapper;

	@Override
	public List<NewsPicture> getNewsPictures() throws Exception {

		List<NewsPicture> newsP = newsPMapper.selectAllnewsPicture();

		return newsP;
	}

	@Override
	public boolean del_addNewsPicture(String imgNames, String imgSrces) throws Exception {

		boolean addNewsPResult = false;
		//删除数据库中的图片，删除数量   res1  .数据库图片数量 res2 == 0
		int res1 = newsPMapper.deletenewsPicturesBatch();
		int res2 = newsPMapper.newsPicturesNums();
		
		//如果删除大于零
		if (res1 > 0 || res2 == 0) {
			
			List<NewsPicture> list = new ArrayList<NewsPicture>();
			//转化字符串为数组
			String[] imgNameArr = imgNames.split(",");
			
			/** 根据 imgName: img.jpg
			 *  通过UUID  替换img 和   新文件夹路径  拼接成CurFileName
			 *  设定新文件夹路径为  E:/a
			 */
			for(String imgName : imgNameArr) {
				UUID uid = UUID.randomUUID();
				
				//获取  imgName 的后缀
				int dotIndex = imgName.indexOf(".");
				String imgSuffix = imgName.substring(dotIndex, imgName.length());
				
				String curFileName = "E:/a/" + uid + imgSuffix;
				NewsPicture newsPicture = new NewsPicture(curFileName, imgName);

				/**把图片路径字符串，转换成数组.
				 * 根据图片原路径，把图片复制到 E:/a 路径下。
				 * 根据imgSreArr 获
				 * 根据上边获取的到的uid + imgSuffix 修改图片名称。
				 */
				String[] imgSreArr = imgSrces.split(",");
				for(String catalog : imgSreArr) {

					File file = new File(catalog);
					/*
					FileOutputStream fos = new FileOutputStream("E:/a");  
					fos.write();
					fos.write(formFile.getFileData());//struts上传FormFile  
					org.apache.commons.io.FileUtils.copyFileToDirectory(d, "E:/a");*/
					//org.apache.commons.io.FileUtils.copyFileToDirectory(file, "D:/a");
					
					int num2 = catalog.lastIndexOf("/");
					catalog.substring(0, num2);
					//org.apache.commons.io.FileUtils.copyFileToDirectory(d, "E:/a");
				}
				
				//添加图片
				list.add(newsPicture);
			}
			
			int res = newsPMapper.insertnewsPictureBatch(list);

			int newsLength = imgNameArr.length;

			//如果插入的图片数量 无差错
			if (res == newsLength) {
				
				addNewsPResult = true;
			}
		}
		// 否则回显

		return addNewsPResult;
	}
	

}
