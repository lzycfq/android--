package com.example.chs2_1;

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

public class AddGestureActivity extends Activity {
	
	GestureOverlayView gestureView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_gesture);
		// ��ȡ���Ʊ༭��ͼ
		gestureView=(GestureOverlayView)findViewById(R.id.gesture);
		// �������ƵĻ�����ɫ
		gestureView.setGestureColor(Color.BLUE);
		// �������ƵĻ��ƿ��
		gestureView.setGestureStrokeWidth(4);
		// Ϊgesture����������¼����¼�������
		gestureView.addOnGesturePerformedListener(new OnGesturePerformedListener() {
			
			@Override
			public void onGesturePerformed(GestureOverlayView overlay,final Gesture gesture) {
				//����Ի���
				View saveDialog=getLayoutInflater().inflate(R.layout.dialog_save, null);
				//ͼƬ�ؼ�
				ImageView image=(ImageView)saveDialog.findViewById(R.id.imgGesture);
				//�ļ���
				final EditText textName=(EditText)saveDialog.findViewById(R.id.textName);
				//�����ƺۼ����ͼƬ
				Bitmap bitmap=gesture.toBitmap(128, 128, 10, Color.RED);
				image.setImageBitmap(bitmap);
				//��ʾ����Ի���
				new AlertDialog.Builder(AddGestureActivity.this).setView(saveDialog).setPositiveButton("����", new OnClickListener() {					
					
					public void onClick(DialogInterface dialog, int which) {
						//�������Ƶ��ļ�
						GestureLibrary gestureLib=GestureLibraries.fromFile("/sdcard/mygestures");
						//���һ������
						gestureLib.addGesture(textName.getText().toString(), gesture);
						//�����ļ�
						if(gestureLib.save()){
							Toast.makeText(getBaseContext(), "����ɹ�", Toast.LENGTH_LONG).show();
						}
					}
				}).setNegativeButton("ȡ��", null).show();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_gesture, menu);
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
