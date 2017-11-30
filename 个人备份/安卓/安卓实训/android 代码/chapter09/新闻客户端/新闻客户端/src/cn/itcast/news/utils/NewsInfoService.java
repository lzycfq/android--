package cn.itcast.news.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;
import cn.itcast.news.bean.NewsInfo;

public class NewsInfoService {
	 //�������������ص�xml��Ϣ ��ȡ������������ʵ��.
	public static List<NewsInfo> getNewsInfos(InputStream is) {
        //��ȡXmlPullParser����      
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(is, "utf-8");
             //��ȡָ��
			int type = parser.getEventType();
			List<NewsInfo> newsInfos = null;
			NewsInfo newsInfo = null;
             //type�����ĵ�����
			while (type != XmlPullParser.END_DOCUMENT) { 
				switch (type) {
				case XmlPullParser.START_TAG: //��ʼ��ǩ
                      //�õ���ǩ�����ж�
					if ("news".equals(parser.getName())) { 
						newsInfos = new ArrayList<NewsInfo>();
					} else if ("newsInfo".equals(parser.getName())) {
						newsInfo = new NewsInfo();
					} else if ("icon".equals(parser.getName())) {
                           //��ȡ��������ǰָ��Ԫ�ص���һ���ı��ڵ��ֵ
						String icon = parser.nextText();  
						newsInfo.setIconPath(icon);
					} else if ("title".equals(parser.getName())) {
						String title = parser.nextText();
						newsInfo.setTitle(title);
					} else if ("content".equals(parser.getName())) {
						String description = parser.nextText();
						newsInfo.setDescription(description);
					} else if ("type".equals(parser.getName())) {
						String newsType = parser.nextText();
						newsInfo.setType(Integer.parseInt(newsType));
					} else if ("comment".equals(parser.getName())) {
						String comment = parser.nextText();
						newsInfo.setComment(Long.parseLong(comment));
					}
					break;
				case XmlPullParser.END_TAG:   //��ǩ����
					if ("newsInfo".equals(parser.getName())) {
						newsInfos.add(newsInfo);
						newsInfo = null;
					}
					break;
				}
				type = parser.next();
			}
			return newsInfos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
