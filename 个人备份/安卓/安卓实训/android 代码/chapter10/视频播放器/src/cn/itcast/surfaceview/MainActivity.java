package cn.itcast.surfaceview;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import cn.itcast.surfaceview.R;

public class MainActivity extends Activity implements OnSeekBarChangeListener,
		Callback {
	private SurfaceView sv;
	private SurfaceHolder holder;
	private MediaPlayer mediaplayer;
	private int position;
	private RelativeLayout rl;
	private Timer timer;
	private TimerTask task;
	private SeekBar sbar;
	private ImageView play;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sbar = (SeekBar) findViewById(R.id.sbar);
		play = (ImageView) findViewById(R.id.play);
		sbar.setOnSeekBarChangeListener(this);
		sv = (SurfaceView) findViewById(R.id.sv);
		// ��ʼ����ʱ��
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				if (mediaplayer != null && mediaplayer.isPlaying()) {
					int progress = mediaplayer.getCurrentPosition();
					int total = mediaplayer.getDuration();
					sbar.setMax(total);
					sbar.setProgress(progress);
				}
			}
		};
		timer.schedule(task, 500, 500);
		rl = (RelativeLayout) findViewById(R.id.rl);
		holder = sv.getHolder();// �õ�SurfaceView��������������������ʾ����������ġ�
		// ��ʱ��api������д�����4.0���ϵ�ϵͳ����д��ȫû���⣬ 4.0һ�µ�ϵͳ����Ҫд
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		// surfaceView ����������Ҫ����һ����ʱ��ġ�
		// ��oncreate����ִ�е�ʱ�� surfaceViewHolder��û����ȫ����������
		holder.addCallback(this);
	}

	// ��Ļ�����¼�
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (rl.getVisibility() == View.INVISIBLE) {
				rl.setVisibility(View.VISIBLE);
				// ����ʱ3��
				CountDownTimer cdt = new CountDownTimer(3000, 3000) {
					@Override
					public void onTick(long millisUntilFinished) {
						System.out.println(millisUntilFinished);
					}

					@Override
					public void onFinish() {
						rl.setVisibility(View.INVISIBLE);
					}
				};
				cdt.start();
			} else if (rl.getVisibility() == View.VISIBLE) {
				rl.setVisibility(View.INVISIBLE);
			}
			break;
		}
		return super.onTouchEvent(event);
	}

	// Activityע��ʱ��Timer��TimerTask������Ϊ��
	@Override
	protected void onDestroy() {
		timer.cancel();
		task.cancel();
		timer = null;
		task = null;
		super.onDestroy();
	}

	// ������ͣ��ť�ĵ���¼�
	public void click(View view) {
		if (mediaplayer != null && mediaplayer.isPlaying()) {
			mediaplayer.pause();
			play.setImageResource(android.R.drawable.ic_media_play);
		} else {
			mediaplayer.start();
			play.setImageResource(android.R.drawable.ic_media_pause);
		}
	}

	// ���ȷ����仯ʱ����
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

	}

	// ��������ʼ�϶�ʱ����
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	// �������϶�ֹͣʱ����
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		int position = seekBar.getProgress();
		if (mediaplayer != null && mediaplayer.isPlaying()) {
			mediaplayer.seekTo(position);
		}
	}

	// SurfaceHolder�������ʱ����
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			mediaplayer = new MediaPlayer();
			mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaplayer.setDataSource("/sdcard/fengjing.f4v");
			mediaplayer.setDisplay(holder);
			mediaplayer.prepareAsync();
			mediaplayer.setOnPreparedListener(new OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mp) {
					mediaplayer.start();
					if (position > 0) {
						mediaplayer.seekTo(position);
					}
				}
			});
		} catch (Exception e) {
			Toast.makeText(MainActivity.this, "����ʧ��", 0).show();
			e.printStackTrace();
		}

	}

	// SurfaceHolder��С�仯ʱ����
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	// SurfaceHolderע��ʱ����
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		position = mediaplayer.getCurrentPosition();// ��¼�ϴβ��ŵ�λ�ã�Ȼ��ֹͣ��
		mediaplayer.stop();
		mediaplayer.release();
		mediaplayer = null;

	}
}
