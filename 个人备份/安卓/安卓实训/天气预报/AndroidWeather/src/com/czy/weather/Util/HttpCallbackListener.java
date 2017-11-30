package com.czy.weather.Util;

public interface HttpCallbackListener {
	
	void onFinish(String response);

	
	void onError(Exception e);

}
