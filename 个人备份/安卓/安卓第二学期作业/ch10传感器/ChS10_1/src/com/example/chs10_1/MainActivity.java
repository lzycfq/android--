package com.example.chs10_1;

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
	// 传感器服务对象
	private SensorManager mSensorManager;
	private TextView textOrientation;
	private float decDegree = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textOrientation = (TextView) findViewById(R.id.textOrientation);
		// 初始化传感器系统服务
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

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
	protected void onResume() {
		super.onResume();
		// 定义方向传感器
		Sensor sensor = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		if (sensor != null) {
			// 注册SensorManager,this->接收sensor的实例 ,接收传感器类型,接收的频率
			mSensorManager.registerListener(this, sensor,
					SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// 传感器精确度改变时的处理事件，本例没有使用
	}

	// 传感器数值改变
	@Override
	public void onSensorChanged(SensorEvent event) {
		// 接受方向感应器的类型
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
			float x = event.values[SensorManager.DATA_X];
			setOrientationText(x);
		}
	}

	// 根据角度计算方向
	private void setOrientationText(float degree) {
		String message = "";
		// 设置灵敏度，变化大于2度才有更新
		if (Math.abs(decDegree - degree) >= 2) {
			decDegree = degree;
			//正负22度都属于同一方向
			int range = 22;
			String degreeStr = String.valueOf(decDegree);

			// 指向正北
			if (decDegree > 360 - range || decDegree < 0 + range) {
				message = "正北 " + degreeStr + "°";
			}
			// 指向正东
			if (decDegree > 90 - range && decDegree < 90 + range) {
				message = "正东 " + degreeStr + "°";
			}
			// 指向正南
			if (decDegree > 180 - range && decDegree < 180 + range) {
				message = "正南 " + degreeStr + "°";
			}
			// 指向正西
			if (decDegree > 270 - range && decDegree < 270 + range) {
				message = "正西 " + degreeStr + "°";
			}
			// 指向东北
			if (decDegree > 45 - range && decDegree < 45 + range) {
				message = "东北 " + degreeStr + "°";
			}
			// 指向东南
			if (decDegree > 135 - range && decDegree < 135 + range) {
				message = "东南 " + degreeStr + "°";
			}
			// 指向西南
			if (decDegree > 225 - range && decDegree < 225 + range) {
				message = "西南 " + degreeStr + "°";
			}
			// 指向西北
			if (decDegree > 315 - range && decDegree < 315 + range) {
				message = "西北 " + degreeStr + "°";
			}
			textOrientation.setText(message);
		}
	}
}
