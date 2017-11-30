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
	// �������������
	private SensorManager mSensorManager;
	private TextView textOrientation;
	private float decDegree = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textOrientation = (TextView) findViewById(R.id.textOrientation);
		// ��ʼ��������ϵͳ����
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
		// ���巽�򴫸���
		Sensor sensor = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		if (sensor != null) {
			// ע��SensorManager,this->����sensor��ʵ�� ,���մ���������,���յ�Ƶ��
			mSensorManager.registerListener(this, sensor,
					SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// ��������ȷ�ȸı�ʱ�Ĵ����¼�������û��ʹ��
	}

	// ��������ֵ�ı�
	@Override
	public void onSensorChanged(SensorEvent event) {
		// ���ܷ����Ӧ��������
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
			float x = event.values[SensorManager.DATA_X];
			setOrientationText(x);
		}
	}

	// ���ݽǶȼ��㷽��
	private void setOrientationText(float degree) {
		String message = "";
		// ���������ȣ��仯����2�Ȳ��и���
		if (Math.abs(decDegree - degree) >= 2) {
			decDegree = degree;
			//����22�ȶ�����ͬһ����
			int range = 22;
			String degreeStr = String.valueOf(decDegree);

			// ָ������
			if (decDegree > 360 - range || decDegree < 0 + range) {
				message = "���� " + degreeStr + "��";
			}
			// ָ������
			if (decDegree > 90 - range && decDegree < 90 + range) {
				message = "���� " + degreeStr + "��";
			}
			// ָ������
			if (decDegree > 180 - range && decDegree < 180 + range) {
				message = "���� " + degreeStr + "��";
			}
			// ָ������
			if (decDegree > 270 - range && decDegree < 270 + range) {
				message = "���� " + degreeStr + "��";
			}
			// ָ�򶫱�
			if (decDegree > 45 - range && decDegree < 45 + range) {
				message = "���� " + degreeStr + "��";
			}
			// ָ����
			if (decDegree > 135 - range && decDegree < 135 + range) {
				message = "���� " + degreeStr + "��";
			}
			// ָ������
			if (decDegree > 225 - range && decDegree < 225 + range) {
				message = "���� " + degreeStr + "��";
			}
			// ָ������
			if (decDegree > 315 - range && decDegree < 315 + range) {
				message = "���� " + degreeStr + "��";
			}
			textOrientation.setText(message);
		}
	}
}
