package cn.itcast.imageview;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
	// ���̴߳�����Ϣ������
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
              //���߳���������,Android4.0�Ժ�������粻�ܷ������߳���
			new Thread() { 
				public void run() {
					// ���ӷ����� get ���� ��ȡͼƬ.
					try {
						URL url = new URL(path);       //����URL����
						// ����url ���� http������.
						HttpURLConnection conn = (HttpURLConnection) url
								.openConnection();
						// ��������ķ�ʽ
						conn.setRequestMethod("GET");
						//���ó�ʱʱ��
						conn.setConnectTimeout(5000);
                            //��������ͷ User-Agent������İ汾
						conn.setRequestProperty(
								"User-Agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; " +
					"SV1; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; " +
					".NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; Shuame)");
						// �õ����������ص���Ӧ��
						int code = conn.getResponseCode();
                            //��������ɹ��󷵻�����200
						if (code == 200) { 
							//��ȡ������
							InputStream is = conn.getInputStream();
							//����ת����Bitmap����
							Bitmap bitmap = BitmapFactory.decodeStream(is);
							//iv.setImageBitmap(bitmap);
							//TODO: �������߳�һ����Ϣ:���Ҹ��Ľ��档����:bitmap
							Message msg = new Message();
							msg.what = CHANGE_UI;
							msg.obj = bitmap;
							handler.sendMessage(msg);
						} else {              
                                //�����벻��200  ���������ʧ��
							Message msg = new Message();
							msg.what = ERROR;
							handler.sendMessage(msg);
						}
					} catch (Exception e) {
						e.printStackTrace();
						Message msg = new Message();
						msg.what = ERROR;
						handler.sendMessage(msg);
					}
				};
			}.start();
		}
	}
}

