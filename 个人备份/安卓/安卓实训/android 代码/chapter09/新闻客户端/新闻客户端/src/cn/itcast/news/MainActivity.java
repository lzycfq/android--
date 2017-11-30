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
     //ListView适配器 
	private class NewsAdapter extends BaseAdapter {
		//listView的item数
		public int getCount() {
			return newsInfos.size();
		}
		//得到listview 条目视图
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
               //SmartImageView加载指定路径图片
			siv.setImageUrl(newsInfo.getIconPath(), R.drawable.ic_launcher,
					R.drawable.ic_launcher);
              //设置新闻标题
			tv_title.setText(newsInfo.getTitle());
              //设置新闻描述
			tv_description.setText(newsInfo.getDescription());
			int type = newsInfo.getType(); // 1. 一般新闻 2.专题 3.live
              //不同新闻类型设置不同的颜色和不同的内容
			switch (type) {
			case 1:
				tv_type.setText("评论:" + newsInfo.getComment());
				break;
			case 2:
				tv_type.setTextColor(Color.RED);
				tv_type.setText("专题");
				break;
			case 3:
				tv_type.setTextColor(Color.BLUE);
				tv_type.setText("LIVE");
				break;
			}
			return view;
		}
		//条目对象
		public Object getItem(int position) {
			return null;
		}
		//条目id
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
    //使用AsyncHttpClient访问网络
	private void fillData2() {
         //创建AsyncHttpClient实例
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
         //使用GET方式请求
	     asyncHttpClient.get(MainActivity.this.getString(R.string.serverurl),	
			 new AsyncHttpResponseHandler() {
					public void onSuccess(String content) {
                            //访问成功
						super.onSuccess(content);
                            //将字符串转换成Byte数组
						byte[] bytes = content.getBytes();
                            //将Byte数组转换成输入流
						ByteArrayInputStream bais = new ByteArrayInputStream(
								bytes); 
                            //调用NewsInfoService工具类解析xml文件
						newsInfos = NewsInfoService.getNewsInfos(bais);
						if (newsInfos == null) {
							// 解析失败 弹出toast
							Toast.makeText(MainActivity.this, 
                                        "解析失败", 0).show();
						} else {
							// 更新界面.
							loading.setVisibility(View.INVISIBLE);
							lv_news.setAdapter(new NewsAdapter());
						}
					}
                         //请求失败
					public void onFailure(Throwable error, String content) {	
						super.onFailure(error, content);
						Log.e("error",MainActivity.this.getString(R.string.serverurl));
						Log.e("error", error.toString());
						Toast.makeText(MainActivity.this, "请求失败", 0).show();
					}
				});
	}
}

