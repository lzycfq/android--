package com.example.chs9_1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Button btnCamera;
	ImageView imgPicture;
	Uri fileUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnCamera = (Button) findViewById(R.id.btnCamera);
		imgPicture = (ImageView) findViewById(R.id.imgPicture);
		btnCamera.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		//������������ͷ��intent
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// ��������ͼƬ���ļ�
		fileUri = getFilePath();
		// ����ͼƬ�ļ���
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		// ����ͼ�񲶻�Intent
		startActivityForResult(intent, 100);
	}

	// ����ǰʱ��׼���ļ�·��
	private Uri getFilePath() {
		// ϵͳָ����ý����·��
		File picDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		if (!picDir.exists()) {
			picDir.mkdirs();
		}

		// ����ʱ���ʽ�����ļ�
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		// ����ý���ļ�
		File imgFile = new File(picDir.getPath() + File.separator + "IMG_"
				+ timeStamp + ".jpg");
		return Uri.fromFile(imgFile);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 100) {
			if (resultCode == RESULT_OK) {
				//���ѱ����·��������ͼƬ
				Bitmap bm = BitmapFactory.decodeFile(fileUri.getPath());
				//��ͼƬ���õ�ImageView��
				imgPicture.setImageBitmap(bm);
				Toast.makeText(this, "ͼƬ�ѱ���Ϊ:\n" + fileUri.getPath(),
						Toast.LENGTH_LONG).show();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
