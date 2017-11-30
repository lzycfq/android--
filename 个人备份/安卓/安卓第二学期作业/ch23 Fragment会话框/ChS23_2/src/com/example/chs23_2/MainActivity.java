package com.example.chs23_2;

import com.example.chs23_2.LoginFrament.LoginListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements LoginListener,OnClickListener {

	TextView textMsg;
	Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textMsg = (TextView) findViewById(R.id.textMsg);
		btnLogin=(Button)findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(this);
		
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

	@Override
	public void onLoginInput(String name, String password) {
		textMsg.setText(String.format("”√ªß√˚£∫%s √‹¬Î£∫%s", name, password));
	}

	@Override
	public void onClick(View v) {
		LoginFrament login=new LoginFrament();
		login.show(getFragmentManager(), "login");
		
	}
}
