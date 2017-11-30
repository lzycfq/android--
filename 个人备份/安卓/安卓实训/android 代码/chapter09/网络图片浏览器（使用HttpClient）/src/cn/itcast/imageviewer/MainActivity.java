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
	//1. ���̴߳�����Ϣ������
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == CHANGE_UI){
				Bitmap bitmap = (Bitmap) msg.obj;
				iv.setImageBitmap(bitmap);
			}else if(msg.what == ERROR){
				Toast.makeText(MainActivity.this, "��ʾͼƬ����", 0).show();
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
			Toast.makeText(this, "ͼƬ·������Ϊ��", 0).show();
		} else {
			new Thread() {
				public void run() {
					// ���ӷ����� get ���� ��ȡͼƬ
					getImageByClient(path);//ʹ��HttpClient��������	
				};
			}.start();
		}
	}
    //ʹ��HttpClient��������
    protected void getImageByClient(String path) {
		HttpClient client = new DefaultHttpClient();//��ȡHttpClient����
		HttpGet httpGet = new HttpGet(path);        //��get��ʽ��������
		try {
			HttpResponse httpResponse = client.execute(httpGet); 
              //��ȡ���ص�HttpResponse����
			if(httpResponse.getStatusLine().getStatusCode() == 200){
              //������������ص�״̬���Ƿ�Ϊ200
              HttpEntity entity = httpResponse.getEntity();//�õ�HttpEntity����
              InputStream content = entity.getContent();  //�õ�������
              //�õ�bitmap����
              Bitmap bitmap = BitmapFactory.decodeStream(content); 
			  //TODO  ֪ͨ���̸߳���Ui����
                Message message = new Message();
                message.what = CHANGE_UI;
                message.obj = bitmap;
                handler.sendMessage(message);
			}else{                                       
                  //״̬�벻Ϊ200  ���ʷ��������ɹ�
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

