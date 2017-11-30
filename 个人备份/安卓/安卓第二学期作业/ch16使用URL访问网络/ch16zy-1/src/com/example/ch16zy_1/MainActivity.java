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
		//��ȡ�̻߳ش���ͼƬ
		Bitmap bitmap=(Bitmap)msg.obj;
		
		//��ʾͼƬ
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

}
