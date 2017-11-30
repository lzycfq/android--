package com.example.lab16;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	EditText edit;
	Button btn;
    ImageView img;
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			Bitmap bitmap=(Bitmap)msg.obj;
			img.setImageBitmap(bitmap);
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
        
        edit=(EditText)findViewById(R.id.edit);
        btn=(Button)findViewById(R.id.btn);
        img=(ImageView)findViewById(R.id.img);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NetWorkThread nwt=new NetWorkThread(handler);
				nwt.setUrl(edit.getText().toString());
				nwt.start();
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
