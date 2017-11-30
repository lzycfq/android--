package com.example.chs11_1;

import com.example.chs11_1.R;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

	// 定义传感器服务
	private SensorManager sensorManager;
	// 定义传感器对象
	private Sensor accelerometer;
	private TextView textAcc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取系统传感器服务
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		// 初始化加速度传感器
		accelerometer = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		textAcc = (TextView) findViewById(R.id.txtAcc);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 注册传感器
		sensorManager.registerListener(this, accelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
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

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float xValue = event.values[0];// x方向的角度值
		float yValue = event.values[1];// y方向的角度
		float zValue = event.values[2];// z方向的角度值
		StringBuilder info = new StringBuilder(String.format("x轴： " + xValue
				+ "  y轴： " + yValue + "  z轴： " + zValue));
		if (xValue > SensorManager.GRAVITY_EARTH - 1) {
			info.append("\n重力指向设备左边");
		} else if (xValue < -SensorManager.GRAVITY_EARTH + 1) {
			info.append("\n重力指向设备右边");
		} else if (yValue > SensorManager.GRAVITY_EARTH - 1) {
			info.append("\n重力指向设备下边");
		} else if (yValue < -SensorManager.GRAVITY_EARTH + 1) {
			info.append("\n重力指向设备上边");
		} else if (zValue > SensorManager.GRAVITY_EARTH - 1) {
			info.append("\n屏幕朝上");
		} else if (zValue < -SensorManager.GRAVITY_EARTH + 1) {
			info.append("\n屏幕朝下");
		}
		textAcc.setText(info.toString());
	}
}
