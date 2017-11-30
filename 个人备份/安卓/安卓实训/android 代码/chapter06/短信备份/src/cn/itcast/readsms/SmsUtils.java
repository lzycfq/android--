package cn.itcast.readsms;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.os.Environment;
import android.util.Xml;
import android.widget.Toast;

public class SmsUtils {
	//����Ϣ��Ϣ������sdcardĿ¼�µ�backup2.xml�ļ���
	public static void backUpSms(List<SmsInfo> smsInfos,Context context) {
		try {
			XmlSerializer serializer = Xml.newSerializer();
			File file = new File(Environment.getExternalStorageDirectory(),
					"sms.xml");
			FileOutputStream os = new FileOutputStream(file);
			// ��ʼ�� ���к��� ָ��xml����д�뵽�ĸ��ļ� ����ָ���ļ��ı��뷽ʽ
			serializer.setOutput(os, "utf-8");
			serializer.startDocument("utf-8", true);
              //�������ڵ�
			serializer.startTag(null, "smss");
			for (SmsInfo info : smsInfos) {
                   //�������ڵ㿪ʼ��ǩ
				serializer.startTag(null, "sms");
				serializer.attribute(null, "id", info.getId() + "");
                  //�����ӽڵ�body
				serializer.startTag(null, "body");
				serializer.text(info.getBody());
				serializer.endTag(null, "body");
                  //�����ӽڵ�address
				serializer.startTag(null, "address");
				serializer.text(info.getAddress());
				serializer.endTag(null, "address");
                   //�����ӽڵ�type
				serializer.startTag(null, "type");
				serializer.text(info.getType() + "");
				serializer.endTag(null, "type");
                   //�����ӽ�date
				serializer.startTag(null, "date");
				serializer.text(info.getDate() + "");
				serializer.endTag(null, "date");
                   //���ڵ������ǩ
				serializer.endTag(null, "sms");
			}
			serializer.endTag(null, "smss");
			serializer.endDocument();
			os.close();
			Toast.makeText(context, "���ݳɹ�", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(context, "����ʧ��", 0).show();
		}
	}
}
