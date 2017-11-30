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

	// ���崫��������
	private SensorManager sensorManager;
	// ���崫��������
	private Sensor accelerometer;
	private TextView textAcc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ȡϵͳ����������
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		// ��ʼ�����ٶȴ�����
		accelerometer = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		textAcc = (TextView) findViewById(R.id.txtAcc);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// ע�ᴫ����
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
		// TODO �Զ����ɵķ������

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float xValue = event.values[0];// x����ĽǶ�ֵ
		float yValue = event.values[1];// y����ĽǶ�
		float zValue = event.values[2];// z����ĽǶ�ֵ
		StringBuilder info = new StringBuilder(String.format("x�᣺ " + xValue
				+ "  y�᣺ " + yValue + "  z�᣺ " + zValue));
		if (xValue > SensorManager.GRAVITY_EARTH - 1) {
			info.append("\n����ָ���豸���");
		} else if (xValue < -SensorManager.GRAVITY_EARTH + 1) {
			info.append("\n����ָ���豸�ұ�");
		} else if (yValue > SensorManager.GRAVITY_EARTH - 1) {
			info.append("\n����ָ���豸�±�");
		} else if (yValue < -SensorManager.GRAVITY_EARTH + 1) {
			info.append("\n����ָ���豸�ϱ�");
		} else if (zValue > SensorManager.GRAVITY_EARTH - 1) {
			info.append("\n��Ļ����");
		} else if (zValue < -SensorManager.GRAVITY_EARTH + 1) {
			info.append("\n��Ļ����");
		}
		textAcc.setText(info.toString());
	}
}
