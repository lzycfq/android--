package cn.itcast.frameanimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	private ImageView iv_flower;
	private Button btn_start;
	private AnimationDrawable animation; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_flower = (ImageView) findViewById(R.id.iv_flower);
		btn_start = (Button) findViewById(R.id.btn_play);
		btn_start.setOnClickListener(this);
		//拿到AnimationDrawable对象
		 animation = (AnimationDrawable)iv_flower.getBackground();		 
	}

	public void onClick(View v) {
		//播放动画
		if(!animation.isRunning()){
			animation.start();
			btn_start.setBackgroundResource(android.R.drawable.ic_media_pause);
		}else{
			animation.stop();
			btn_start.setBackgroundResource(android.R.drawable.ic_media_play);
		}
	}
}
