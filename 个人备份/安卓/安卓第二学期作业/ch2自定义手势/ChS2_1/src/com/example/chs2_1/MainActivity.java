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
		//加载手势库
		mLibrary = GestureLibraries.fromFile("/sdcard/mygestures");
		if (!mLibrary.load()) {
			finish();
		}
		// 获取手势编辑视图
		gestureView = (GestureOverlayView) findViewById(R.id.gesture);
		// 设置手势的绘制颜色
		gestureView.setGestureColor(Color.GREEN);
		// 设置手势的绘制宽度
		gestureView.setGestureStrokeWidth(4);
		// 为gesture的手势完成事件绑定事件监听器
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
		//弹出添加手势窗口
		Intent intent = new Intent(getBaseContext(), AddGestureActivity.class);
		startActivity(intent);
	}

	@Override
	public void onGesturePerformed(GestureOverlayView overlay,
			final Gesture gesture) {
		//获得相符的手势
		ArrayList<Prediction> predictions = mLibrary.recognize(gesture);
		String msg = "";
		if (predictions.size() > 0) {
			Prediction prediction = (Prediction) predictions.get(0);
			if (prediction.score > 1.0) {
				msg += String.format("与手势[%s] 相似度 %f;", prediction.name,
						prediction.score);
			} else {
				msg = "没有找到相似的手势";
			}
		}
		txtMsg.setText(msg);
	}
}
