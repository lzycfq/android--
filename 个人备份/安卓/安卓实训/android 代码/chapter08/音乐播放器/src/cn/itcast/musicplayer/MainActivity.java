package cn.itcast.musicplayer;

import java.io.File;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import cn.itcast.musicplayer.MusicService.MyBinder;

public class MainActivity extends Activity implements OnClickListener {
	private EditText path;
	private Intent intent;
	private myConn conn;
	MyBinder binder;
	private SeekBar mSeekBar;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 100:
				int currentPosition = (Integer) msg.obj;
				mSeekBar.setProgress(currentPosition);
				break;
			default:
				break;
			}
		};
	};
	private Thread mThread;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		path = (EditText) findViewById(R.id.et_inputpath);
		findViewById(R.id.bt_play).setOnClickListener(this);
		findViewById(R.id.bt_pause).setOnClickListener(this);
		findViewById(R.id.bt_replay).setOnClickListener(this);
		findViewById(R.id.bt_stop).setOnClickListener(this);
		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		conn = new myConn();
		intent = new Intent(this, MusicService.class);
		bindService(intent, conn, BIND_AUTO_CREATE);
	}

	// ��ʼ���������ĳ���,��ȡ�����ļ��ĳ���
	private void initSeekBar() {
		// TODO Auto-generated method stub
		int musicWidth = binder.getMusicWidth();
		mSeekBar.setMax(musicWidth);
	}

	// �������ֲ��ŵĽ���
	private void UpdateProgress() {
		mThread = new Thread() {
			public void run() {
				while (!interrupted()) {
					// ���÷����еĻ�ȡ��ǰ���Ž���
					int currentPosition = binder.getCurrentPosition();
					Message message = Message.obtain();
					message.obj = currentPosition;
					message.what = 100;
					handler.sendMessage(message);
				}
			};
		};
		mThread.start();
	}

	private class myConn implements ServiceConnection {
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = (MyBinder) service;
		}

		public void onServiceDisconnected(ComponentName name) {
		}
	}

	public void onClick(View v) {
		String pathway = path.getText().toString().trim();
		File SDpath = Environment.getExternalStorageDirectory();
		File file = new File(SDpath, pathway);
		String path = file.getAbsolutePath();
		switch (v.getId()) {
		case R.id.bt_play:
			if (file.exists() && file.length() > 0) {
				binder.plays(path);
				initSeekBar();
				UpdateProgress();
			}else{
				Toast.makeText(this, "�Ҳ��������ļ�", 0).show();
			}
			break;
		case R.id.bt_pause:
			binder.pauses();
			break;
		case R.id.bt_replay:
			binder.replays(pathway);
			break;
		case R.id.bt_stop:
			// ֹͣ����֮ǰ����Ҫ�˳����߳�
			mThread.interrupt();
			if (mThread.isInterrupted()) {
				binder.stops();
			}
			break;
		default:
			break;
		}
	}

	protected void onDestroy() {
		// ����߳�û���˳�,���˳�
		if (mThread != null & !mThread.isInterrupted()) {
			mThread.interrupt();
		}
		unbindService(conn);
		super.onDestroy();
	}
}
