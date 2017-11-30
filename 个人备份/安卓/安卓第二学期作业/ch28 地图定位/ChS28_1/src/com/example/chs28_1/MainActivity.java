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

	LocationClient mLocClient;// 定位对象
	LocationMode mCurrentMode;// 定位方式
	MapView mMapView;// 地图控件
	BaiduMap mBaiduMap;// 地图类
	boolean isFirstLoc = true; // 是否首次定位
	MyLocationListenner myListener = new MyLocationListenner();// 定义位置更新侦听器

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SDKInitializer.initialize(getApplicationContext());// 初始化SDK
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initLocationMap();// 初始化定位和地图相关类
	}

	// 初始化定位和地图
	private void initLocationMap() {
		mCurrentMode = LocationMode.NORMAL;
		// 地图初始化
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		// 定位初始化
		mLocClient = new LocationClient(this);
		// 注册侦听器，当取得位置信息时，由myListener对象处理
		mLocClient.registerLocationListener(myListener);
		// 设置定位选项
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);// 定位刷新间隔时间
		mLocClient.setLocOption(option);// 将选项设定到定位对象
		mLocClient.start();// 启动定位
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
	 * 定位SDK监听类
	 */
	public class MyLocationListenner implements BDLocationListener {
		// 位置信息更新处理
		@Override
		public void onReceiveLocation(BDLocation location) {
			// 获取不到位置信息或map view 销毁都不处理
			if (location == null || mMapView == null) {
				return;
			}
			Toast.makeText(
					getApplicationContext(),
					String.format("%f,%f", location.getLatitude(),
							location.getLongitude()), Toast.LENGTH_LONG).show();
			// 根据定位信息，构造一个位置数据（MyLocationData）对象
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			// 将定位信息设置到地图显示
			mBaiduMap.setMyLocationData(locData);
			// 如果是首次定位，构造LatLng对象，设置地图的中心位置为定位点，显示地图
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
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
	}

}
