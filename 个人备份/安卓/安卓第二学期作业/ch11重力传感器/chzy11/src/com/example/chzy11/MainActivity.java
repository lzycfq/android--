package com.example.chzy11;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import java.util.Random;

import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
    private static final int SPEED_SHRESHOLD=2000;
    private static final int UPTATE_INYERVAL_TIME=30;
    private SensorManager sensorManager;
    private Sensor sensor;
    private float lastX;
    private float lastY;
    private float lastZ;
    private long lastUpdateTime;
    TextView textMsg;
	private float x;
	private float y;
	private float z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textMsg=(TextView)findViewById(R.id.textMsg);
        /*sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);*/
        setSensor();
    }
    protected void setSensor() {
		// ��ô�����������
		sensorManager =(SensorManager)getSystemService(SENSOR_SERVICE);
		if (sensorManager != null) {
			// �������������
			sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		}
		// ע��
		if (sensor != null) {
			sensorManager.registerListener(this,sensor,
					SensorManager.SENSOR_DELAY_NORMAL);

		}
	}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		//���ڼ��ʱ��
		long currentUdateTime=System.currentTimeMillis();
		//���μ��ʱ��
		long timeInterval=currentUdateTime-lastUpdateTime;
		if(timeInterval<UPTATE_INYERVAL_TIME)
		return;
		lastUpdateTime=currentUdateTime;
		float xValue = event.values[0];// x����ĽǶ�ֵ
		float yValue = event.values[1];// y����ĽǶ�
		float zValue = event.values[2];// z����ĽǶ�ֵ
		
		float deltaX=x-lastX;
		float deltaY=y-lastY;
		float deltaZ=z-lastZ;
		lastX=x;
		lastY=y;
		lastZ=z;
		double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ	* deltaZ) / timeInterval * 10000;
		// �ﵽ�ٶȷ�ֵ������һ��1-6 ֮�������
		if (speed>=SPEED_SHRESHOLD) {
			textMsg.setText(String.format("ҡ��������+%d",Math.abs(new Random().nextInt()% 6))+1);
		}

	}
    
}
