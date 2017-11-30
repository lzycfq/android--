package com.example.ch28zy;
  import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

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

public class MainActivity extends Activity {
     LocationClient mLocCilent;//定位对象
     LocationMode mCurrentMode;//定位方式
     MapView mMapView;//地图控件
     BaiduMap mBaiduMap;//地图类
     boolean isFirstLoc=true;
     MyLocationListenner myListener = new MyLocationListenner();// 定义位置更新侦听器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLocationMap();
    }


    private void initLocationMap() {
		// TODO Auto-generated method stub
		mCurrentMode=LocationMode.NORMAL;
		mMapView=(MapView)findViewById(R.id.bmapView);
		mBaiduMap=mMapView.getMap();
		mBaiduMap.setMyLocationEnabled(true);
		mLocCilent=new LocationClient(this);
		mLocCilent.registerLocationListener(myListener);
	    LocationClientOption op=new LocationClientOption();
	    op.setOpenGps(true);
	    op.setCoorType("bd09ll");
	    op.setScanSpan(1000);
	    mLocCilent.setLocOption(op);
	    mLocCilent.start();
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public class MyLocatonListenner implements BDLocationListener{

		@Override
		public void onReceiveLocation(BDLocation arg0) {
			// TODO Auto-generated method stub
			if(location==null||mMapView==null){
				return
			}
			Toast.makeText(getApplicationContext(),String.format("%f%Tf", location.getLatitude(),location.getLatitude()),Toast.LENGTH_LONG).show();
			MyLocationData da=new MyLocationData().Builder().acctac
		}
    	
    }
}
