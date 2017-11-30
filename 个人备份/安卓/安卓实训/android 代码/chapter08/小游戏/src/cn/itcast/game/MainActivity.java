package cn.itcast.game;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import cn.itcast.alipay.IService;

public class MainActivity extends Activity {
	private Intent service;
	private IService iService;
	private MyConn conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		service = new Intent();
		service.setAction("cn.itcast.alipay");
	}

	public void bind(View view) {
		conn = new MyConn();
		bindService(service, conn, BIND_AUTO_CREATE);
	}

	public void call(View view) {
		try {
			if (iService != null) {
				iService.callALiPayService();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private class MyConn implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			iService = IService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
		}
	}

	public void unbind(View view) {
		if (conn != null) {
			unbindService(conn);
			conn = null;
			iService = null;
		}
	}
}
