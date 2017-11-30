package com.example.ch16zy_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private Thread Thread;

Button btn;
EditText edittext;
ImageView imgPrcture;
Handler handler = new Handler() {
	public void handleMessage(android.os.Message msg) {
		//获取线程回传的图片
		Bitmap bitmap=(Bitmap)msg.obj;
		
		//显示图片
		imgPrcture.setImageBitmap(bitmap);

		try {
			FileOutputStream out = new FileOutputStream(getFilePath()
					.getPath());
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	};
};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.btn);
        imgPrcture=(ImageView)findViewById(R.id.imgPicture);
        edittext=(EditText)findViewById(R.id.edittext);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				
				NetworkThread net=new NetworkThread(handler);
                 
                 net.start();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

}
