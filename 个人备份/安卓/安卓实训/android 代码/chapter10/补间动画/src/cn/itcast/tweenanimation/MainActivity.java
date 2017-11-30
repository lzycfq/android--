package cn.itcast.tweenanimation;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private ImageView img01;
	private ImageView img02;
	private ImageView img03;
	private ImageView img04;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img01 = (ImageView) findViewById(R.id.img01);
		img02 = (ImageView) findViewById(R.id.img02);
		img03 = (ImageView) findViewById(R.id.img03);
		img04 = (ImageView) findViewById(R.id.img04);
		img01.setOnClickListener(this);
		img02.setOnClickListener(this);
		img03.setOnClickListener(this);
		img04.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img01:
			Animation ani1 = AnimationUtils.loadAnimation(this,
					R.anim.alpha_animation);
			img01.startAnimation(ani1);
			break;

		case R.id.img02:
			Animation ani2 = AnimationUtils.loadAnimation(this,
					R.anim.scale_animation);
			img02.startAnimation(ani2);
			break;

		case R.id.img03:
			Animation ani3 = AnimationUtils.loadAnimation(this,
					R.anim.translate_animation);
			img03.startAnimation(ani3);
			break;

		case R.id.img04:
			Animation ani4 = AnimationUtils.loadAnimation(this,
					R.anim.rotate_animation);
			img04.startAnimation(ani4);
			break;

		}
	}

}