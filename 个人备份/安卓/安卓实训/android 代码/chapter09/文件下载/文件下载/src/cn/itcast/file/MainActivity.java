package cn.itcast.file;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// ��������Դ��ַ
	private static final String path = "http://172.16.26.58:8080/EditPlus.exe";
	private TextView mFileTV; // ����չʾ��������Դ�ļ��Ĵ�С
	private TextView mThread1TV; // ������ʾthread��Ҫ���ص��ļ�����
	private TextView mThread3CompleteTV; // thread�������ʱ��ʾ
	protected static int threadCount; // �̸߳���
	// ���ڸ���UI�����Handler
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case 100: // ��������Դ�ļ��Ĵ�С
				mFileTV.setText("��������Դ�ļ���СΪ:" + (Long) msg.obj);
				break;
			case 101: // ����ÿ���߳���Ҫ���ض���
				String string = mThread1TV.getText().toString();
				mThread1TV.setText(string + (String) msg.obj);
				break;
			case 102:// �鿴�Ǹ��߳����ص����
				String string1 = mThread3CompleteTV.getText().toString();
				mThread3CompleteTV.setText(string1 + (String) msg.obj);
				break;
			case 300:
				Toast.makeText(MainActivity.this, "��ȡ�����������ļ�", 0).show();
				break;
			}
		};
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	// ��ʼ���ؼ�
	private void initView() {
		mFileTV = (TextView) findViewById(R.id.file);
		mThread1TV = (TextView) findViewById(R.id.thread1);
		mThread3CompleteTV = (TextView) findViewById(R.id.thread3_complete);
	}

	// Button����¼������ķ���
	public void downLoad(View view) {
		// 1���ش���һ���ļ���С���������Դ��Сһ��
		new Thread() {
			public void run() {
				try {
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(3000);
					conn.setReadTimeout(2000);
					// ��ȡ��������Դ�ļ��Ĵ�С
					long contentLength = conn.getContentLength();
					if (contentLength <= 0) {
						Message msg = new Message();
						msg.what = 300;
						handler.sendMessage(msg);
						return;
					}
					// ʹ��Handler������Ϣ���Ľ���
					Message msg = new Message();
					msg.what = 100;
					msg.obj = new Long(contentLength);
					handler.sendMessage(msg);
					// ���ش���һ������ļ����ƶ�����
					RandomAccessFile raf = new RandomAccessFile(
							"/sdcard/temp.exe", "rwd");
					// ���ñ����ļ��Ĵ�С
					raf.setLength(contentLength);
					// �̵߳�����
					threadCount = 3;
					// ÿ���߳����ص�����Ĵ�С
					long blocksize = contentLength / threadCount;
					// �������ÿ���߳� ���صĿ�ʼ�ͽ�����λ��.
					for (int threadId = 1; threadId <= threadCount; threadId++) {
						long startPos = (threadId - 1) * blocksize;
						long endPos = threadId * blocksize - 1;
						if (threadId == threadCount) {
							// ���һ���߳�
							endPos = contentLength;
						}
						Message message = new Message();
						message.what = 101;
						message.obj = "�߳�" + threadId + "������" + startPos + "-"
								+ endPos + "\n";
						handler.sendMessage(message);
						// �����߳̿�ʼ�����ļ�
						new DownLoadThread(startPos, endPos, threadId, path)
								.start();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

	// �Զ�һ���߳����������ļ�
	class DownLoadThread extends Thread {
		private long startPos;
		private long endPos;
		private long threadId;
		private String path;

		public DownLoadThread(long startPos, long endPos, long threadId,
				String path) {
			super();
			this.startPos = startPos;
			this.endPos = endPos;
			this.threadId = threadId;
			this.path = path;
		}

		public void run() {
			try {
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setRequestMethod("GET"); // �������󷽷�
				conn.setConnectTimeout(5000); // ���ó�ʱʱ��
				// ���󲿷����� ����ɹ�����206
				conn.setRequestProperty("Range", "bytes=" + startPos + "-"
						+ endPos);
				InputStream is = conn.getInputStream();
				RandomAccessFile raf = new RandomAccessFile("/sdcard/temp.exe",
						"rwd");
				// ����ָ��ĳ���̱߳����ļ��Ŀ�ʼλ�� ������������ص�λ��һ��
				raf.seek(startPos);
				// ������д��raf��
				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = is.read(buffer)) != -1) {
					raf.write(buffer, 0, len);
				}
				is.close();
				raf.close();
				// ʹ��handler�����̷߳�����Ϣ
				Message msg = new Message();
				msg.what = 102;
				msg.obj = new String("�߳�" + threadId + "�������" + "\n");
				handler.sendMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
