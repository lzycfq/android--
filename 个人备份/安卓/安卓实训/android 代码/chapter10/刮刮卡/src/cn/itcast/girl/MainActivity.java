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
		//����Դ�ļ��н���һ��bitmap
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.guagua);
		alterbitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		//����һ��canvas����
		Canvas canvas = new Canvas(alterbitmap);
		//�������ʶ���
		Paint paint = new Paint();
		//Ϊ����������ɫ
	    paint.setColor(Color.BLACK);
	    paint.setAntiAlias(true);
	    //����Matrix����
		Matrix matrix = new Matrix();
		//��alterBitmap�ϻ�ͼ
		canvas.drawBitmap(bitmap, matrix, paint);
		//����ImageView�ı���
		mImageView.setImageBitmap(alterbitmap);
		mImageView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				try {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						Toast.makeText(MainActivity.this, "��ָ����", 0).show();
						break;
					case MotionEvent.ACTION_MOVE:
						Toast.makeText(MainActivity.this, "��ָ�ƶ�("+event.getX()+","+event.getY()+")", 0).show();
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
						Toast.makeText(MainActivity.this, "��ָ�ɿ�", 0).show();
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;//���ѵ��ô����¼�
			}
		});
	}
}
