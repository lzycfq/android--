package cn.itcast.shake;

import java.util.Map;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.itcast.shake.ShakeListener.OnShakeListener;

public class ShakeActivity extends Activity {

	ShakeListener mShakeListener = null;
	Vibrator mVibrator;
	private RelativeLayout mImgUp;
	private RelativeLayout mImgDn;
	private SoundPool sndPool;
	private Map<Integer, Integer> loadSound;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shake_activity);
		// ��ʼ������
		init();
		// ���ù����෽����assetsĿ¼�µ����������map�У�����һ��HashMap
		loadSound = Utils.loadSound(sndPool, this);
		// �������ٶȼ������Ķ���
	}

	@Override
	protected void onResume() {
		super.onResume();
		mShakeListener = new ShakeListener(this);
		// ���ٶȴ��������ﵽ�ٶȷ�ֵ�����Ŷ���
		mShakeListener.setOnShakeListener(new OnShakeListener() {
			public void onShake() {
				Utils.startAnim(mImgUp, mImgDn); // ��ʼ ҡһҡ���ƶ���
				mShakeListener.stop();// ֹͣ���ٶȴ�����
				sndPool.play(loadSound.get(0), (float) 1, (float) 1, 0, 0,
						(float) 1.2);// ҡһҡʱ����map�д�ŵĵ�һ������

				startVibrato();// ��
				new Handler().postDelayed(new Runnable() {
					public void run() {
						sndPool.play(loadSound.get(1), (float) 1, (float) 1, 0,
								0, (float) 1.0);// ҡһҡ�����󲥷�map�д�ŵĵڶ�������
						Toast.makeText(getApplicationContext(),
								"��Ǹ����ʱû���ҵ�\n��ͬһʱ��ҡһҡ���ˡ�\n����һ�ΰɣ�", 10).show();
						mVibrator.cancel();// �𶯹ر�
						mShakeListener.start();// �ٴο�ʼ�����ٶȴ�����ֵ
					}
				}, 2000);
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mShakeListener != null) {
			mShakeListener.stop();
		}
	}

	private void init() {
		mVibrator = (Vibrator) getApplication().getSystemService(
				VIBRATOR_SERVICE);
		mImgUp = (RelativeLayout) findViewById(R.id.shakeImgUp);
		mImgDn = (RelativeLayout) findViewById(R.id.shakeImgDown);
		sndPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 5);
	}

	public void startVibrato() { // ������
		mVibrator.vibrate(new long[] { 500, 200, 500, 200 }, -1); // ��һ�����������ǽ������飬
	}

	public void shake_activity_back(View v) { // ������ ���ذ�ť
		this.finish();
	}

	public void linshi(View v) { // ������
		Utils.startAnim(mImgUp, mImgDn);
	}

}