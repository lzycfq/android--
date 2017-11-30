package cn.itcast.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et_username;
	private EditText et_password;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
         //��ʼ���ؼ�
		et_password = (EditText) findViewById(R.id.et_password);
		et_username = (EditText) findViewById(R.id.et_username);
	}
	//HttpURLConnection GET��ʽ
	public void click1(View view) {
		//�õ��û�������û���
		final String username = et_username.getText().toString().trim();
		//�õ�����
		final String password = et_password.getText().toString().trim();
		new Thread() {
			public void run() {
				//����LoginService����ķ������ʷ�����  ���õ����������ص���Ϣ
				final String result = LoginService.loginByGet(username,
						password);
				if (result != null) {
					//UI�̸߳��Ľ���
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, result, 0).show();
						}
					});
				} else {
					// ����ʧ��  UI�̵߳���toast
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "����ʧ��...", 0)
									.show();
						}
					});
				}
			};
		}.start();
	}
	//HttpURLConnection POST��ʽ
	public void click2(View view) {
          //���Ȼ�ȡ�����û�������û���������
		final String username = et_username.getText().toString().trim();
		final String password = et_password.getText().toString().trim();
		new Thread() {//�������̷߳�������
			public void run() {
                   //����LoginService����ķ�����������
				final String result = LoginService.loginByPost(username,
						password);
				if (result != null) {
					//ui�̸߳��Ľ���
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, result, 0).show();
						}
					});
				} else {
					// ����ʧ��,ʹ��UI�̸߳���UI����
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "����ʧ��...", 0)
									.show();
						}
					});
				}
			};
		}.start();
	} 
	//HttpClient GET��ʽ
	public void click3(View view) {
          //�õ�������û���������
		final String username = et_username.getText().toString().trim();
		final String password = et_password.getText().toString().trim();
		new Thread() {//�������̷߳�������
			public void run() {
                   //����LoginService����ķ������������ȡ����
				final String result = LoginService.loginByClientGet(username,
						password);
				if (result != null) {
                        //ʹ��UI�̵߳���toast
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, result, 0).show();
						}
					});
				} else {// ����ʧ�� ʹ��UI�̵߳���toast
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "����ʧ��...", 0)
									.show();
						}
					});
				}
			};
		}.start();
	}
	//HttpClient POST��ʽ
	public void click4(View view) {
         //�õ�������û���������
		final String username = et_username.getText().toString().trim();
		final String password = et_password.getText().toString().trim();
		new Thread() {
			public void run() {
				//ʹ�ù�����LoginService����ķ����������� ���õ��ӷ��������ص���Ϣ
				final String result = LoginService.loginByClientPost(username,
						password);
				if (result != null) {
					//ui�̸߳��Ľ���
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
                                //����toast
							Toast.makeText(MainActivity.this, result, 0).show();
						}
					});
				} else {
					// ����ʧ�� ����toast
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "����ʧ��...", 0)
									.show(); 
						}
					});
				}
			};
		}.start();
	}
}

