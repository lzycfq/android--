package com.example.chs28_1;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	LocationClient mLocClient;// ��λ����
	LocationMode mCurrentMode;// ��λ��ʽ
	MapView mMapView;// ��ͼ�ؼ�
	BaiduMap mBaiduMap;// ��ͼ��
	boolean isFirstLoc = true; // �Ƿ��״ζ�λ
	MyLocationListenner myListener = new MyLocationListenner();// ����λ�ø���������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SDKInitializer.initialize(getApplicationContext());// ��ʼ��SDK
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initLocationMap();// ��ʼ����λ�͵�ͼ�����
	}

	// ��ʼ����λ�͵�ͼ
	private void initLocationMap() {
		mCurrentMode = LocationMode.NORMAL;
		// ��ͼ��ʼ��
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// ������λͼ��
		mBaiduMap.setMyLocationEnabled(true);
		// ��λ��ʼ��
		mLocClient = new LocationClient(this);
		// ע������������ȡ��λ����Ϣʱ����myListener������
		mLocClient.registerLocationListener(myListener);
		// ���ö�λѡ��
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // ��gps
		option.setCoorType("bd09ll"); // ������������
		option.setScanSpan(1000);// ��λˢ�¼��ʱ��
		mLocClient.setLocOption(option);// ��ѡ���趨����λ����
		mLocClient.start();// ������λ
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * ��λSDK������
	 */
	public class MyLocationListenner implements BDLocationListener {
		// λ����Ϣ���´���
		@Override
		public void onReceiveLocation(BDLocation location) {
			// ��ȡ����λ����Ϣ��map view ���ٶ�������
			if (location == null || mMapView == null) {
				return;
			}
			Toast.makeText(
					getApplicationContext(),
					String.format("%f,%f", location.getLatitude(),
							location.getLongitude()), Toast.LENGTH_LONG).show();
			// ���ݶ�λ��Ϣ������һ��λ�����ݣ�MyLocationData������
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			// ����λ��Ϣ���õ���ͼ��ʾ
			mBaiduMap.setMyLocationData(locData);
			// ������״ζ�λ������LatLng�������õ�ͼ������λ��Ϊ��λ�㣬��ʾ��ͼ
			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatus.Builder builder = new MapStatus.Builder();
				builder.target(ll).zoom(18.0f);
				mBaiduMap.animateMapStatus(MapStatusUpdateFactory
						.newMapStatus(builder.build()));
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// �˳�ʱ���ٶ�λ
		mLocClient.stop();
		// �رն�λͼ��
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
	}

}
