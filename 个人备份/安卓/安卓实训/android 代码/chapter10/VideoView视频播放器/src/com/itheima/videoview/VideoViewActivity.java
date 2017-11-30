package com.itheima.videoview;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends Activity implements OnClickListener {
	private EditText et_path;
	private ImageView bt_play;
	private VideoView videoView;
	private MediaController controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.videoview);
		et_path = (EditText) findViewById(R.id.et_path);
		bt_play = (ImageView) findViewById(R.id.bt_play);
		videoView = (VideoView) findViewById(R.id.sv);
		controller = new MediaController(this);
		videoView.setMediaController(controller);
		bt_play.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_play:
			play();
			break;
		}
	}

	/**
	 * 播放视频
	 * 
	 * @param currentPosition
	 */

	private void play() {
		if (videoView != null && videoView.isPlaying()) {
			bt_play.setImageResource(android.R.drawable.ic_media_play);
			videoView.stopPlayback();
			return;
		}
		videoView.setVideoPath(et_path.getText().toString());
		videoView.start();
		bt_play.setImageResource(android.R.drawable.ic_media_pause);
		videoView.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				bt_play.setImageResource(android.R.drawable.ic_media_play);
			}
		});
	}
}
