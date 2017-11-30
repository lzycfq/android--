package cn.itcast.alipay;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ALiPayService extends Service {
	private static final String TAG = "ALiPayService";
	@Override
	public IBinder onBind(Intent intent) {
		Log.v(TAG, "��֧������׼������");
		return new MyBinder();
	}
	private class MyBinder extends IService.Stub {
		@Override
		public void callALiPayService() {
			methodInService();
		}
	}
	private void methodInService() {
		Log.v(TAG, "��ʼ���ѣ�����װ��");
	}
	@Override
	public void onCreate() {
		Log.v(TAG, "����֧�����ɹ�");
		super.onCreate();
	}
	@Override
	public void onDestroy() {
		Log.v(TAG, "�ر�֧����");
		super.onDestroy();
	}
	@Override
	public boolean onUnbind(Intent intent) {
		Log.v(TAG, "ȡ������");
		return super.onUnbind(intent);
	}
}
