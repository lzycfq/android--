package cn.itcast.passdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Activity01 extends Activity{
	private Button btn_send;
	private EditText et_name;
	private RadioButton manRadio;
	private RadioButton womanRadio;
	private EditText et_password;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity01);
		et_name = (EditText) findViewById(R.id.et_name);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_send = (Button) findViewById(R.id.btn_send);
		manRadio = (RadioButton) findViewById(R.id.radioMale);
		womanRadio = (RadioButton) findViewById(R.id.radioFemale);
		//������Ͱ�ť�������ݴ���
		btn_send.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				passDate();
			}
		});
	}
	 //��������
	public void passDate() {
		//����Intent��������Activity02
		Intent intent = new Intent(this, Activity02.class);
		//�����ݴ���Intent����
		intent.putExtra("name", et_name.getText().toString().trim());
		intent.putExtra("password", et_password.getText().toString().trim());
		String str = "";
		if(manRadio.isChecked()){
			str = "��";
		}else if(womanRadio.isChecked()){
			str = "Ů";
		}
		intent.putExtra("sex", str);
		startActivity(intent);
	}
}
