package com.example.chzy10;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
	private TextView textOrientation;
	private ImageView imgBagua;
	private float decDegree = 0;
	private SensorManager mSensorManager;
	//ԭʼͼƬ
	private Bitmap sourceBmp;
	private int mWidth ;
	private int mHeight ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sourceBmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.bagua);
		mWidth = sourceBmp.getWidth();
		mHeight = sourceBmp.getHeight();

		textOrientation = (TextView) findViewById(R.id.textOrientation);
		imgBagua = (ImageView) findViewById(R.id.imgBagua);
		imgBagua.setImageBitmap(sourceBmp);
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

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
private void setOrientationText(float degree) {
	String message = "";
	// ���������ȣ��仯����5�Ȳ��и���
	if (Math.abs(decDegree-degree)>=5) {
		decDegree = degree;
		// ����22�ȶ�����ͬһ����
		int range = 22;
		// ������ļ������趨
		String degreeStr=String.valueOf(decDegree);
		textOrientation.setText(message);
		//����ת������
		Matrix mt = new Matrix();
		//��ת��ǰ�ĽǶ�
		mt.postRotate(decDegree);
		//��ԭͼ��ת����һ����ͼƬ
		Bitmap turnBmp = Bitmap.createBitmap(sourceBmp, 0, 0, mWidth,
				mHeight, mt, true);
		//����ͼƬ���õ�ImageView
		imgBagua.setImageBitmap(turnBmp);
	}
}

public void onSensorChanged(SensorEvent event) {
	// ���ܷ����Ӧ��������
	if (event.sensor.getType()==Sensor.TYPE_ORIENTATION) {
		//��ȡX�ı仯�Ƕ�
		float x=event.values[SensorManager.DATA_X];
		//ִ��x�仯�����ʾ
		setOrientationText(x);
	}
}


@Override
public void onAccuracyChanged(Sensor sensor, int accuracy) {
	// TODO Auto-generated method stub
	
}
}

