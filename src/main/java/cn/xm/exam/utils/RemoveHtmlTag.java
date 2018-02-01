package cn.xm.exam.utils;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import cn.xm.exam.MyElFunction.MyElFunction;
import cn.xm.exam.bean.exam.Bigquestion;
import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.bean.exam.Exampaperoption;
import cn.xm.exam.bean.exam.Exampaperquestion;

/**
 * 移除HTML标签
 * 
 * @author QiaoLiQiang
 * @time 2017年10月31日上午8:47:16
 */
public class RemoveHtmlTag {
	/**
	 * <span class="bigNum">二</span>、
	 * <span><input class="el_modifiedTitle" value="多选题" type="text"> </span>
	 * <span>（每到题 <input class="el_modifiedGrade" value="2" type="text"> </span>
	 * <span> 分；共</span><span class="numTotal">4分/</span>
	 * <span class="numQues">2题)</span>
	 * 
	 * @param html
	 * @return
	 */
	// 去掉大题的标签
	public static String removeBigQues(String html) {
		StringBuffer sb = new StringBuffer();
		Document doc = Jsoup.parse(html);
		sb.append(doc.select(".bigNum").get(0).text() + ".  ");
		sb.append(doc.select(".el_modifiedTitle").get(0).attr("value"));
		sb.append(doc.select("span").get(2).text() + doc.select(".el_modifiedGrade").get(0).attr("value"));
		sb.append(doc.select("span").get(3).text());
		sb.append(doc.select("span").get(4).text());
		sb.append(doc.select("span").get(5).text());
		return sb.toString();
	}

	// 去掉大题的标签
	public static String removeQuesTag(String html) {
		Document doc = Jsoup.parse(html);
		return doc.text();
	}

	// 去掉选项的标签
	public static String removeOptionTag(String html) {
		Document doc = Jsoup.parse(html);
		return doc.text();
	}

	/**
	 * 去除一份试卷的标签,同时修改试题的编号
	 * 
	 * @param examPaper
	 * @return
	 */
	public static Exampaper removePaperTag(Exampaper examPaper) {
		List<Bigquestion> bigQuestions = examPaper.getBigQuestions();
		Integer quesSequence = 1;
		for (int i = 0; bigQuestions != null && i < bigQuestions.size(); i++) {
			Bigquestion big = bigQuestions.get(i);
			// 对大题序号进行处理
			if (big != null && i == 0) {
				big.setBigquestionname("一" + removeBigQues(big.getBigquestionname()).substring(1));
			}
			if (big != null && i == 1) {
				big.setBigquestionname("二" + removeBigQues(big.getBigquestionname()).substring(1));
			}
			if (big != null && i == 2) {
				big.setBigquestionname("三" + removeBigQues(big.getBigquestionname()).substring(1));
			}
			List<Exampaperquestion> questions = big.getQuestions();
			for (int j = 0, length_2 = questions.size(); j < length_2; j++) {
				Exampaperquestion ques = questions.get(j);
				ques.setQuestioncontent(removeQuesTag(ques.getQuestioncontent()));
				ques.setQuestionsequence(quesSequence);// 修改题号
				quesSequence++;
				List<Exampaperoption> options = ques.getOptions();
				for (int k = 0, length_3 = options.size(); k < length_3; k++) {
					Exampaperoption opt = options.get(k);
					opt.setOptioncontent(removeOptionTag(opt.getOptioncontent()));
					// 对单选与多选题的选项进行替换:01234-ABCDE
					// if (ques.getType() != null &&
					// !"判断题".equals(ques.getType())) {
					if (ques.getType() != null) {
						opt.setOptionsequence(
								MyElFunction.replace(opt.getOptionsequence().toString(), "01234", "ABCDE"));
					}
					// 判断题的序号设为空

					/*if (ques.getType() != null && "判断题".equals(ques.getType())) {
						opt.setOptionsequence("");
					}
*/
					opt.setOptioncontent(removeOptionTag(opt.getOptioncontent()));
				}

			}
		}
		return examPaper;
	}
}
