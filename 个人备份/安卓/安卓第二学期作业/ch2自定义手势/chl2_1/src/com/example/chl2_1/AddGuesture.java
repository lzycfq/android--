package com.example.chl2_1;
import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddGuesture extends Activity{
	EditText editText;
	GestureOverlayView gestureView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture);
		// 获取手势编辑视图
				gestureView=(GestureOverlayView)findViewById(R.id.gesture);
				
				// 设置手势的绘制颜色
				gestureView.setGestureColor(Color.BLUE);
				// 设置手势的绘制宽度
				gestureView.setGestureStrokeWidth(4);
				gestureView.addOnGesturePerformedListener(new OnGesturePerformedListener() {
					
					@Override
					public void onGesturePerformed(GestureOverlayView overlay,final Gesture gesture) {
						//保存对话框
						View saveDialog=getLayoutInflater().inflate(R.layout.dialog_save, null);
						//图片控件
						ImageView image=(ImageView)saveDialog.findViewById(R.id.imgGesture);
						//文件名
						final EditText textName=(EditText)saveDialog.findViewById(R.id.textName);
						//将手势痕迹变成图片
						Bitmap bitmap=gesture.toBitmap(128, 128, 10, Color.RED);
						image.setImageBitmap(bitmap);
						//显示保存对话框
						new AlertDialog.Builder(AddGestureActivity.this).setView(saveDialog).setPositiveButton("保存", new OnClickListener() {					
							
							public void onClick(DialogInterface dialog, int which) {
								//保存手势的文件
								GestureLibrary gestureLib=GestureLibraries.fromFile("/sdcard/mygestures");
								//添加一个手势
								gestureLib.addGesture(textName.getText().toString(), gesture);
								//保存文件
								if(gestureLib.save()){
									Toast.makeText(getBaseContext(), "保存成功", Toast.LENGTH_LONG).show();
								}
							}
						}).setNegativeButton("取消", null).show();
						
					}
				});
			}

			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu, menu);
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
		
	}

