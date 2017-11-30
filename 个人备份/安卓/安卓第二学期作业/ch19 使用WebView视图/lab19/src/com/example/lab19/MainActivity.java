package com.example.lab19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	EditText editUrl;
	WebView wvBrowser;
	Button btnGo, btnForward, btnBack, btnHistory;
	ListView list;
	TextView listText;
	List<String> lt=new ArrayList<String>();
	private class HelloWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return true;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editUrl = (EditText) findViewById(R.id.editUrl);
		wvBrowser = (WebView) findViewById(R.id.wvBrowser);
		btnGo = (Button) findViewById(R.id.btnGo);
		btnForward = (Button) findViewById(R.id.btnForward);
		btnBack = (Button) findViewById(R.id.btnBack);
		btnHistory = (Button) findViewById(R.id.btnHistory);

		wvBrowser.setWebViewClient(new HelloWebViewClient());
		btnGo.setOnClickListener(this);
		btnForward.setOnClickListener(this);
		btnBack.setOnClickListener(this);
		btnHistory.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_SEARCH) {
			go();
		}
		if ((keyCode == KeyEvent.KEYCODE_BACK) && wvBrowser.canGoBack()) {
			wvBrowser.goBack(); // goBack()表示返回WebView的上一页面
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	protected void go() {
		String url = editUrl.getText().toString();
		wvBrowser.loadUrl(url);
		lt.add(url);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnGo:
			go();
			break;
		case R.id.btnForward:
			if (wvBrowser.canGoForward())
				wvBrowser.goForward();
			break;
		case R.id.btnBack:
			if (wvBrowser.canGoBack()) {
				wvBrowser.goBack();
			}
		case R.id.btnHistory:
			showDialog(1);
			break;
		default:
			break;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case 1:
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			LinearLayout dialogLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_list, null);
			builder.setTitle("历史记录");
			builder.setView(dialogLayout);
			list = (ListView) dialogLayout.findViewById(R.id.list);
			bindAdapter();
			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					//Toast.makeText(getBaseContext(),getUrl().get(arg2) , 0).show();
					editUrl.setText(lt.get(arg2));
				}
			});
			bindAdapter();
			return builder.create();

		}
		return super.onCreateDialog(id);
	}

	public void bindAdapter(){
		ArrayAdapter adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,lt);
		list.setAdapter(adapter);
	}
}
