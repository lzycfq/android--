package cn.itcast.musicplayer;

import java.io.File;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service {
	private static final String TAG = "MusicService";
	public MediaPlayer mediaPlayer;

	class MyBinder extends Binder {
		// ��������
		public void plays(String path) {
			play(path);
		}

		// ��ͣ����
		public void pauses() {
			pause();
		}

		// ���²���
		public void replays(String path) {
			replay(path);
		}

		// ֹͣ����
		public void stops() {
			stop();
		}

		// ��ȡ��ǰ���Ž���
		public int getCurrentPosition() {
			return getCurrentProgress();
		}

		// ��ȡ�����ļ��ĳ���
		public int getMusicWidth() {
			return getMusicLength();
		}
	}

	public void onCreate() {
		super.onCreate();
	}

	// ��������
	@SuppressLint("NewApi")
	public void play(String path) {
		try {
			if (mediaPlayer == null) {
				Log.i(TAG, "��ʼ��������");
				// ����һ��MediaPlayer������
				mediaPlayer = new MediaPlayer();
				// ָ������Ϊ��Ƶ�ļ�
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				// ָ�����ŵ�·��
				mediaPlayer.setDataSource(path);
				// ׼������
				mediaPlayer.prepare();
				mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
					@Override
					public void onPrepared(MediaPlayer mp) {
						// TODO Auto-generated method stub
						// ��ʼ����
						mediaPlayer.start();
					}
				});
			} else {
				int position = getCurrentProgress();
				mediaPlayer.seekTo(position);
				try {
					mediaPlayer.prepare();
				} catch (Exception e) {
					e.printStackTrace();
				}
				mediaPlayer.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ͣ����
	public void pause() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			Log.i(TAG, "������ͣ");
			mediaPlayer.pause(); // ��ͣ����
		} else if (mediaPlayer != null && (!mediaPlayer.isPlaying())) {
			mediaPlayer.start();
		}
	}

	// ���²�������
	public void replay(String path) {
		if (mediaPlayer != null) {
			Log.i(TAG, "���¿�ʼ����");
			mediaPlayer.seekTo(0);
			try {
				mediaPlayer.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			mediaPlayer.start();
		}
	}

	// ֹͣ����
	public void stop() {
		if (mediaPlayer != null) {
			Log.i(TAG, "ֹͣ����");
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		} else {
			Toast.makeText(getApplicationContext(), "��ֹͣ", 0).show();
		}
	}

	// ��ȡ��Դ�ļ��ĳ���
	public int getMusicLength() {
		if (mediaPlayer != null) {
			return mediaPlayer.getDuration();
		}
		return 0;
	}

	// ��ȡ��ǰ����
	public int getCurrentProgress() {
		if (mediaPlayer != null & mediaPlayer.isPlaying()) {
			Log.i(TAG, "��ȡ��ǰ����");
			return mediaPlayer.getCurrentPosition();
		} else if (mediaPlayer != null & (!mediaPlayer.isPlaying())) {
			return mediaPlayer.getCurrentPosition();
		}
		return 0;
	}

	public void onDestroy() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
		super.onDestroy();
	}

	public IBinder onBind(Intent intent) {
		// ��һ��ִ��onBind����
		return new MyBinder();
	}
}
