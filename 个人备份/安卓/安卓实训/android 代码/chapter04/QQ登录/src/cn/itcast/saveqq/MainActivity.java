package cn.itcast.saveqq;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import cn.itcast.saveqq.utils.Utils;

public class MainActivity extends Activity implements OnClickListener {
	private EditText etNumber;
	private EditText etPassword;
//	private CheckBox cbRemember;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		// ȡ������
		Map<String, String> userInfo = Utils.getUserInfo(this);
		if(userInfo != null) {
			// ��ʾ�ڽ�����
			etNumber.setText(userInfo.get("number"));
			etPassword.setText(userInfo.get("password"));
		}
	}
	
	private void initView() {
		etNumber = (EditText) findViewById(R.id.et_number);
		etPassword = (EditText) findViewById(R.id.et_password);
//		cbRemember = (CheckBox) findViewById(R.id.cb_remember);
		findViewById(R.id.btn_login).setOnClickListener(this);
	}

	public void onClick(View v) {
		// �������¼ʱ,��ȡQQ���� ������
		String number = etNumber.getText().toString().trim();
		String password = etPassword.getText().toString();
		// У�����������Ƿ���ȷ
		if(TextUtils.isEmpty(number)) {
			Toast.makeText(this, "������QQ����", Toast.LENGTH_SHORT).show();
			return;
		}
		if(TextUtils.isEmpty(password)) {
			Toast.makeText(this, "����������", Toast.LENGTH_SHORT).show();
			return;
		}
		// ��¼�ɹ�
		Toast.makeText(this, "��¼�ɹ�", Toast.LENGTH_SHORT).show();
		// �����ȷ, �ж��Ƿ�ѡ�˼�ס����
//		if(cbRemember.isChecked()) {
			Log.i("MainActivity", "��ס����: " + number + ", " + password);
			// �����û���Ϣ
			boolean isSaveSuccess = Utils.saveUserInfo(this, number, password);
			if(isSaveSuccess) {
				Toast.makeText(this, "����ɹ�", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "����ʧ��", Toast.LENGTH_SHORT).show();
			}
//		}
	}
}

