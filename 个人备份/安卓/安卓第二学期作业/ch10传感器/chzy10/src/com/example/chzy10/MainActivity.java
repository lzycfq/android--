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
	//原始图片
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
	// 定义方向传感器		
	Sensor sensor = mSensorManager
			.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	if (sensor != null) {
		// 注册SensorManager,this->接收sensor的实例 ,接收传感器类型,接收的频率
		mSensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}
}
private void setOrientationText(float degree) {
	String message = "";
	// 设置灵敏度，变化大于5度才有更新
	if (Math.abs(decDegree-degree)>=5) {
		decDegree = degree;
		// 正负22度都属于同一方向
		int range = 22;
		// 各方向的计算与设定
		String degreeStr=String.valueOf(decDegree);
		textOrientation.setText(message);
		//定义转换矩阵
		Matrix mt = new Matrix();
		//旋转当前的角度
		mt.postRotate(decDegree);
		//在原图旋转后定义一张新图片
		Bitmap turnBmp = Bitmap.createBitmap(sourceBmp, 0, 0, mWidth,
				mHeight, mt, true);
		//将新图片设置到ImageView
		imgBagua.setImageBitmap(turnBmp);
	}
}

public void onSensorChanged(SensorEvent event) {
	// 接受方向感应器的类型
	if (event.sensor.getType()==Sensor.TYPE_ORIENTATION) {
		//获取X的变化角度
		float x=event.values[SensorManager.DATA_X];
		//执行x变化后的显示
		setOrientationText(x);
	}
}


@Override
public void onAccuracyChanged(Sensor sensor, int accuracy) {
	// TODO Auto-generated method stub
	
}
}

