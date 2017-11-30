package com.example.chzy9;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
Button btnCamera;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCamera=(Button)findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, 100);
			
			}
		});
    }
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {   	
        	
        	if (requestCode == 100) {
    			if (resultCode == RESULT_OK && data != null) {
    				//创建保存图片Activity的intent
    				Intent intent=new Intent(MainActivity.this,SaveImageActivity.class);
    			   //将数据转换为Bundle对象
    				Bundle b= data.getExtras();
    				//将Bundle作为附带参数
    			    intent.putExtras(b);
    				//启动图片效果activity
    				startActivity(intent);
    			}
    		}
        	super.onActivityResult(requestCode, resultCode, data);    	
        }

    

    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    }


