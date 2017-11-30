package cn.itcast.savefile;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private EditText et_info;
	private Button btn_save;
	private Button btn_read;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��ȡ�����ļ��еĿؼ�
		et_info = (EditText) findViewById(R.id.et_info);
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_read = (Button) findViewById(R.id.btn_read);
		btn_save.setOnClickListener(new ButtonListener());
		btn_read.setOnClickListener(new ButtonListener());
	}

	// ����Button��ť�ĵ���¼�
	private class ButtonListener implements OnClickListener {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_save:
				String saveinfo = et_info.getText().toString().trim();
				FileOutputStream fos;
				try {
					fos = openFileOutput("data.txt", Context.MODE_APPEND);
					fos.write(saveinfo.getBytes());
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Toast.makeText(MainActivity.this, "���ݱ���ɹ�", 0).show();
				break;
			case R.id.btn_read:
				String content = "";
				try {
					FileInputStream fis = openFileInput("data.txt");
					byte[] buffer = new byte[fis.available()];
					fis.read(buffer);
					content = new String(buffer);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Toast.makeText(MainActivity.this, "����������ǣ�" + content, 0)
						.show();
				break;
			default:
				break;
			}
		}
	}

	public void read(View view) {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
