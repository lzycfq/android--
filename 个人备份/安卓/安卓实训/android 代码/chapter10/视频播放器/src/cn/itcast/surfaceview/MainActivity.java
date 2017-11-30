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
		// 初始化计时器
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
		holder = sv.getHolder();// 得到SurfaceView的容器，界面内容是显示在容器里面的。
		// 过时的api，必须写，如果4.0以上的系统，不写完全没问题， 4.0一下的系统必须要写
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		// surfaceView 被创建是需要花费一定的时间的。
		// 在oncreate方法执行的时候 surfaceViewHolder还没有完全创建出来。
		holder.addCallback(this);
	}

	// 屏幕触摸事件
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (rl.getVisibility() == View.INVISIBLE) {
				rl.setVisibility(View.VISIBLE);
				// 倒计时3秒
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

	// Activity注销时把Timer和TimerTask对象置为空
	@Override
	protected void onDestroy() {
		timer.cancel();
		task.cancel();
		timer = null;
		task = null;
		super.onDestroy();
	}

	// 播放暂停按钮的点击事件
	public void click(View view) {
		if (mediaplayer != null && mediaplayer.isPlaying()) {
			mediaplayer.pause();
			play.setImageResource(android.R.drawable.ic_media_play);
		} else {
			mediaplayer.start();
			play.setImageResource(android.R.drawable.ic_media_pause);
		}
	}

	// 进度发生变化时触发
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

	}

	// 进度条开始拖动时触发
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	// 进度条拖动停止时触发
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		int position = seekBar.getProgress();
		if (mediaplayer != null && mediaplayer.isPlaying()) {
			mediaplayer.seekTo(position);
		}
	}

	// SurfaceHolder创建完成时触发
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
			Toast.makeText(MainActivity.this, "播放失败", 0).show();
			e.printStackTrace();
		}

	}

	// SurfaceHolder大小变化时触发
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	// SurfaceHolder注销时触发
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		position = mediaplayer.getCurrentPosition();// 记录上次播放的位置，然后停止。
		mediaplayer.stop();
		mediaplayer.release();
		mediaplayer = null;

	}
}
