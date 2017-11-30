package com.example.ch9lx_1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
  Button button;
  ImageView image;
  Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button) ;
        image=(ImageView)findViewById(R.id.image);
        button.setOnClickListener(this);
        		}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		uri=getFilePath();
		i.putExtra(MediaStore.ACTION_IMAGE_CAPTURE, uri);
		startActivityForResult(i, 100);
	}


	private Uri getFilePath() {
		// TODO Auto-generated method stub
		File pic=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		if(!pic.exists()){
			pic.mkdirs();
		}
		String ts=new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
		File IF=new File(pic.getPath()+File.separator+"IMG_"+ts+".jpg");
		return Uri.fromFile(IF);
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 100) {
			if (resultCode == RESULT_OK) {
				//从已保存的路径中载入图片
				Bitmap bm = BitmapFactory.decodeFile(uri.getPath());
				//将图片设置到ImageView中
				image.setImageBitmap(bm);
				Toast.makeText(this, "图片已保存为:\n" + uri.getPath(),
						Toast.LENGTH_LONG).show();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
    

