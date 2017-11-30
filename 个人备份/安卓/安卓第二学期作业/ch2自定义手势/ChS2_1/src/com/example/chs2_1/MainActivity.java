package com.example.chs2_1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements
		OnGesturePerformedListener {

	GestureOverlayView gestureView;
	GestureLibrary mLibrary;
	TextView txtMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtMsg = (TextView) findViewById(R.id.textMsg);
		//�������ƿ�
		mLibrary = GestureLibraries.fromFile("/sdcard/mygestures");
		if (!mLibrary.load()) {
			finish();
		}
		// ��ȡ���Ʊ༭��ͼ
		gestureView = (GestureOverlayView) findViewById(R.id.gesture);
		// �������ƵĻ�����ɫ
		gestureView.setGestureColor(Color.GREEN);
		// �������ƵĻ��ƿ��
		gestureView.setGestureStrokeWidth(4);
		// Ϊgesture����������¼����¼�������
		gestureView.addOnGesturePerformedListener(this);
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

	public void btnAdd_Click(View view) {
		//����������ƴ���
		Intent intent = new Intent(getBaseContext(), AddGestureActivity.class);
		startActivity(intent);
	}

	@Override
	public void onGesturePerformed(GestureOverlayView overlay,
			final Gesture gesture) {
		//������������
		ArrayList<Prediction> predictions = mLibrary.recognize(gesture);
		String msg = "";
		if (predictions.size() > 0) {
			Prediction prediction = (Prediction) predictions.get(0);
			if (prediction.score > 1.0) {
				msg += String.format("������[%s] ���ƶ� %f;", prediction.name,
						prediction.score);
			} else {
				msg = "û���ҵ����Ƶ�����";
			}
		}
		txtMsg.setText(msg);
	}
}
