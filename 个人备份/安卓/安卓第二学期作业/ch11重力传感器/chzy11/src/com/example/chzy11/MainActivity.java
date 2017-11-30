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
		// 获得传感器管理器
		sensorManager =(SensorManager)getSystemService(SENSOR_SERVICE);
		if (sensorManager != null) {
			// 获得重力传感器
			sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		}
		// 注册
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
		//现在检测时间
		long currentUdateTime=System.currentTimeMillis();
		//两次间隔时间
		long timeInterval=currentUdateTime-lastUpdateTime;
		if(timeInterval<UPTATE_INYERVAL_TIME)
		return;
		lastUpdateTime=currentUdateTime;
		float xValue = event.values[0];// x方向的角度值
		float yValue = event.values[1];// y方向的角度
		float zValue = event.values[2];// z方向的角度值
		
		float deltaX=x-lastX;
		float deltaY=y-lastY;
		float deltaZ=z-lastZ;
		lastX=x;
		lastY=y;
		lastZ=z;
		double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ	* deltaZ) / timeInterval * 10000;
		// 达到速度阀值，生产一个1-6 之间的整数
		if (speed>=SPEED_SHRESHOLD) {
			textMsg.setText(String.format("摇到数字是+%d",Math.abs(new Random().nextInt()% 6))+1);
		}

	}
    
}
