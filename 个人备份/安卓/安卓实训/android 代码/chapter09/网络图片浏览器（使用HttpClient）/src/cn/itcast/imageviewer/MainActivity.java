package cn.itcast.imageviewer;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	protected static final int CHANGE_UI = 1;
	protected static final int ERROR = 2;
	private EditText et_path;
	private ImageView iv;
	//1. 主线程创建消息处理器
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == CHANGE_UI){
				Bitmap bitmap = (Bitmap) msg.obj;
				iv.setImageBitmap(bitmap);
			}else if(msg.what == ERROR){
				Toast.makeText(MainActivity.this, "显示图片错误", 0).show();
			}
		};
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_path = (EditText) findViewById(R.id.et_path);
		iv = (ImageView) findViewById(R.id.iv);
	}
	public void click(View view) {
		final String path = et_path.getText().toString().trim();
		if (TextUtils.isEmpty(path)) {
			Toast.makeText(this, "图片路径不能为空", 0).show();
		} else {
			new Thread() {
				public void run() {
					// 连接服务器 get 请求 获取图片
					getImageByClient(path);//使用HttpClient访问网络	
				};
			}.start();
		}
	}
    //使用HttpClient访问网络
    protected void getImageByClient(String path) {
		HttpClient client = new DefaultHttpClient();//获取HttpClient对象
		HttpGet httpGet = new HttpGet(path);        //用get方式请求网络
		try {
			HttpResponse httpResponse = client.execute(httpGet); 
              //获取返回的HttpResponse对象
			if(httpResponse.getStatusLine().getStatusCode() == 200){
              //检验服务器返回的状态码是否为200
              HttpEntity entity = httpResponse.getEntity();//拿到HttpEntity对象
              InputStream content = entity.getContent();  //拿到输入流
              //拿到bitmap对象
              Bitmap bitmap = BitmapFactory.decodeStream(content); 
			  //TODO  通知主线程更改Ui界面
                Message message = new Message();
                message.what = CHANGE_UI;
                message.obj = bitmap;
                handler.sendMessage(message);
			}else{                                       
                  //状态码不为200  访问服务器不成功
				Message message = new Message();
				message.what = ERROR;
				handler.sendMessage(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message();
			message.what = ERROR;
			handler.sendMessage(message);
		}
	}
}

