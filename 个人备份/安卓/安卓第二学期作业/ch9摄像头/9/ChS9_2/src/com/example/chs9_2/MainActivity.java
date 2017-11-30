package com.example.chs9_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
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
		// ����ͼ�񲶻�Intent
		startActivityForResult(intent, 100);
	}

	private Uri getFilePath() {
		File picDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		if (!picDir.exists()) {
			picDir.mkdirs();
		}

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File imgFile = new File(picDir.getPath() + File.separator + "IMG_"
				+ timeStamp + ".jpg");
		return Uri.fromFile(imgFile);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 100) {
			if (resultCode == RESULT_OK && data != null) {
				//��data����������ת��ΪBitmap
				Bitmap bm = data.getParcelableExtra("data");
				//����ImageView��ʾbm
				imgPicture.setImageBitmap(bm);
				//��ȡ����ͼƬ·��
				fileUri = getFilePath();
				try {
					//�����ļ������
					FileOutputStream fos=new FileOutputStream(fileUri.getPath());
					//��Bitmap����Ϊjpg
					bm.compress(CompressFormat.JPEG, 100, fos);
					Toast.makeText(this, "ͼƬ����Ϊ:\n" + fileUri.getPath(),
							Toast.LENGTH_LONG).show();
				} catch (FileNotFoundException e) {				
					e.printStackTrace();
				}			
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
