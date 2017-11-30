package com.example.chzy9;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream.GetField;
import java.sql.Date;
import java.text.SimpleDateFormat;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract.Contacts.Data;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SaveImageActivity implements OnClickListener{
	Button btnSave;
	Button btnCancel;
	ImageView imgPicture;
	Bitmap bm;
	File fileUri;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saveimageactivity);
		//获取activity传递的参数
		Bundle b=getIntent().getExtras();
		imgPicture=(ImageView) findViewById(R.id.imgPicture);	
		//将Bundle数据转换为Bitmap
		bm=b.getParcelable("data");
		//设置显示Bitmap
		imgPicture.setImageBitmap(bm);		
		btnSave=(Button)findViewById(R.id.btnSave);
		btnSave.setOnClickListener(this);		
		//点取消按钮直接退出activity
		btnCancel=(Button)findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();				
			}		
		});		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Uri fileuri=getFilePath();
		//定义文件输出流
	FileOutputStream fos=new FileOutputStream(fileUri.getPath());
		//将Bitmap保存为jpg
	bm.compress(CompressFormat.JPEG, 100, fos);
		Toast.makeText(this, "图片保存为:\n" + fileUri.getPath(),
				Toast.LENGTH_LONG).show();
		//退出activity
     finsh();
	}catch(FileNotFoundException e) {				
		e.printStackTrace();
	}	
	}
	}
	
	

