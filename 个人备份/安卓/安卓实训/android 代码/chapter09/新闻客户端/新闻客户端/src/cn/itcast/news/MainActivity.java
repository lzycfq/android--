package cn.itcast.news;

import java.io.ByteArrayInputStream;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cn.itcast.news.bean.NewsInfo;
import cn.itcast.news.utils.NewsInfoService;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

public class MainActivity extends Activity {
	private ListView lv_news;
	private LinearLayout loading;
	private List<NewsInfo> newsInfos;
     //ListView������ 
	private class NewsAdapter extends BaseAdapter {
		//listView��item��
		public int getCount() {
			return newsInfos.size();
		}
		//�õ�listview ��Ŀ��ͼ
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(MainActivity.this, R.layout.news_item,
					null);
			SmartImageView siv = (SmartImageView) view
					.findViewById(R.id.siv_icon);
			TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
			TextView tv_description = (TextView) view
					.findViewById(R.id.tv_description);
			TextView tv_type = (TextView) view.findViewById(R.id.tv_type);
			NewsInfo newsInfo = newsInfos.get(position);
               //SmartImageView����ָ��·��ͼƬ
			siv.setImageUrl(newsInfo.getIconPath(), R.drawable.ic_launcher,
					R.drawable.ic_launcher);
              //�������ű���
			tv_title.setText(newsInfo.getTitle());
              //������������
			tv_description.setText(newsInfo.getDescription());
			int type = newsInfo.getType(); // 1. һ������ 2.ר�� 3.live
              //��ͬ�����������ò�ͬ����ɫ�Ͳ�ͬ������
			switch (type) {
			case 1:
				tv_type.setText("����:" + newsInfo.getComment());
				break;
			case 2:
				tv_type.setTextColor(Color.RED);
				tv_type.setText("ר��");
				break;
			case 3:
				tv_type.setTextColor(Color.BLUE);
				tv_type.setText("LIVE");
				break;
			}
			return view;
		}
		//��Ŀ����
		public Object getItem(int position) {
			return null;
		}
		//��Ŀid
		public long getItemId(int position) {
			return 0;
		}
	}
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv_news = (ListView) findViewById(R.id.lv_news);
		loading = (LinearLayout) findViewById(R.id.loading);
		fillData2();
	}
    //ʹ��AsyncHttpClient��������
	private void fillData2() {
         //����AsyncHttpClientʵ��
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
         //ʹ��GET��ʽ����
	     asyncHttpClient.get(MainActivity.this.getString(R.string.serverurl),	
			 new AsyncHttpResponseHandler() {
					public void onSuccess(String content) {
                            //���ʳɹ�
						super.onSuccess(content);
                            //���ַ���ת����Byte����
						byte[] bytes = content.getBytes();
                            //��Byte����ת����������
						ByteArrayInputStream bais = new ByteArrayInputStream(
								bytes); 
                            //����NewsInfoService���������xml�ļ�
						newsInfos = NewsInfoService.getNewsInfos(bais);
						if (newsInfos == null) {
							// ����ʧ�� ����toast
							Toast.makeText(MainActivity.this, 
                                        "����ʧ��", 0).show();
						} else {
							// ���½���.
							loading.setVisibility(View.INVISIBLE);
							lv_news.setAdapter(new NewsAdapter());
						}
					}
                         //����ʧ��
					public void onFailure(Throwable error, String content) {	
						super.onFailure(error, content);
						Log.e("error",MainActivity.this.getString(R.string.serverurl));
						Log.e("error", error.toString());
						Toast.makeText(MainActivity.this, "����ʧ��", 0).show();
					}
				});
	}
}

