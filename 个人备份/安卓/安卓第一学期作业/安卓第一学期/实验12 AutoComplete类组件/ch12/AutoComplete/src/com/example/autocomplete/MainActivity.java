package com.example.autocomplete;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView.Tokenizer;

public class MainActivity extends Activity {
	AutoCompleteTextView autotext = null;
	MultiAutoCompleteTextView multiautotext = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		autotext = (AutoCompleteTextView) findViewById(R.id.autotext);
		multiautotext = (MultiAutoCompleteTextView) findViewById(R.id.multiautotext);
		Resources res = getResources();
		String[] city = res.getStringArray(R.array.city);
		ArrayAdapter<String> multiadapter = new ArrayAdapter<String>(this,R.layout.list_item, city);
		ArrayAdapter<String> adapterauto = new ArrayAdapter<String>(this,R.layout.list_item, city);
		autotext.setAdapter(adapterauto);
		multiautotext.setAdapter(multiadapter);
		multiautotext.setTokenizer( new MultiAutoCompleteTextView.CommaTokenizer());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
