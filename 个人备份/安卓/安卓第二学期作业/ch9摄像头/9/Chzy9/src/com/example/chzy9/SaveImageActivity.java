package com.example.chzy9;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SaveImageActivity extends Activity implements OnClickListener{
	Button btnSave;
	Button btnCancel;
	ImageView imgPicture;
	Bitmap bm;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_image);
		Bundle b=getIntent().getExtras();
		imgPicture = (ImageView) findViewById(R.id.imgPicture);
		bm=b.getParcelable("data");
		imgPicture.setImageBitmap(bm);
		btnSave=(Button)findViewById(R.id.btnSave);
		btnSave.setOnClickListener(this);
		
		//点取消按钮直接退出activity
		btnCancel=(Button)findViewById(R.id.butCancel);
		btnCancel.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();				
			}

			
		});		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save_image, menu);
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
	private Uri getFilePath() {
		// 系统指定的媒体存放路径
		File picDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		if (!picDir.exists()) {
			picDir.mkdirs();
		}

		// 按照时间格式命名文件
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		// 创建媒体文件
		File imgFile = new File(picDir.getPath() + File.separator + "IMG_"
				+ timeStamp + ".jpg");
		return Uri.fromFile(imgFile);
	}
	public void onClick(View v) {
		//获取保存图片路径
				Uri fileUri=getFilePath();
				try {
					//定义文件输出流
						FileOutputStream fos=new FileOutputStream(fileUri.getPath());
					//将Bitmap保存为jpg
					bm.compress(CompressFormat.JPEG,100,fos);
					Toast.makeText(this, "图片保存为:\n" + fileUri.getPath(),
							Toast.LENGTH_LONG).show();
					//退出activity
					finish();
				} catch (FileNotFoundException e) {				
					e.printStackTrace();
				}		
			}

	}

