package cn.itcast.girl;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView mImageView;
	private Bitmap alterbitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (ImageView) findViewById(R.id.imgv);
		//从资源文件中解析一张bitmap
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.guagua);
		alterbitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		//创建一个canvas对象
		Canvas canvas = new Canvas(alterbitmap);
		//创建画笔对象
		Paint paint = new Paint();
		//为画笔设置颜色
	    paint.setColor(Color.BLACK);
	    paint.setAntiAlias(true);
	    //创建Matrix对象
		Matrix matrix = new Matrix();
		//在alterBitmap上画图
		canvas.drawBitmap(bitmap, matrix, paint);
		//设置ImageView的背景
		mImageView.setImageBitmap(alterbitmap);
		mImageView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				try {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						Toast.makeText(MainActivity.this, "手指触下", 0).show();
						break;
					case MotionEvent.ACTION_MOVE:
						Toast.makeText(MainActivity.this, "手指移动("+event.getX()+","+event.getY()+")", 0).show();
						int x = (int) event.getX();
						int y = (int) event.getY();
						for(int i=-10;i<10;i++){
							for(int j=-10;j<10;j++){
								if(Math.sqrt((i*i)+(j*j))<=10){
									alterbitmap.setPixel(x+i, y+j, Color.TRANSPARENT);
								}
							}
						}
						mImageView.setImageBitmap(alterbitmap);
						break;
					case MotionEvent.ACTION_UP:
						Toast.makeText(MainActivity.this, "手指松开", 0).show();
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;//消费掉该触摸事件
			}
		});
	}
}
