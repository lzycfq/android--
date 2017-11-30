package com.example.ch_14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	Spinner spinner1 = null;
	AutoCompleteTextView actNumber = null;
	ArrayAdapter<String> aadFL2 = null;
	Spinner spFL2 = null;
	RadioButton rbYes = null;
	RadioButton rbNo = null;
	RadioGroup rg = null;
	ToggleButton tb = null;
	Button btSave = null;
	TextView tvNumber = null;
	EditText edName = null;
	EditText edAuthor = null;
	EditText edPress = null;
	EditText edTime = null;
	EditText edPrice = null;
	EditText edJS = null;
	String fenlei1, fenlei2;
	RatingBar ratPJ = null;
	CheckBox cbJD = null;
	CheckBox cbRX = null;
	CheckBox cbZD = null;
	String SJ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		spinner1 = (Spinner) findViewById(R.id.spFL1);
		actNumber = (AutoCompleteTextView) findViewById(R.id.actNumber);

		String[] strnumber = getResources().getStringArray(R.array.number);
		ArrayAdapter<String> adnumber = new ArrayAdapter<String>(this,
				R.layout.list_item, strnumber);
		actNumber.setAdapter(adnumber);

		String[] strfenlei1 = getResources().getStringArray(R.array.FL1);
		ArrayAdapter<String> adfenlei = new ArrayAdapter<String>(this,
				R.layout.list_item, strfenlei1);
		spinner1.setAdapter(adfenlei);

		spFL2 = (Spinner) findViewById(R.id.spFL2);

		rg = (RadioGroup) findViewById(R.id.rd);
		rbYes = (RadioButton) findViewById(R.id.rbYes);
		rbNo = (RadioButton) findViewById(R.id.rbNo);
		tb = (ToggleButton) findViewById(R.id.tb);
		btSave = (Button) findViewById(R.id.btSave);
		tvNumber = (TextView) findViewById(R.id.tvNumber);
		edName = (EditText) findViewById(R.id.edName);
		edAuthor = (EditText) findViewById(R.id.edAuthor);
		edPress = (EditText) findViewById(R.id.edPress);
		edTime = (EditText) findViewById(R.id.edTime);
		edPrice = (EditText) findViewById(R.id.edPrice);
		edJS = (EditText) findViewById(R.id.edJS);
		ratPJ = (RatingBar) findViewById(R.id.ratPJ);
		cbJD = (CheckBox) findViewById(R.id.cbJD);
		cbRX = (CheckBox) findViewById(R.id.cbRX);
		cbZD = (CheckBox) findViewById(R.id.cbZD);

		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> src, View arg1, int pos,
					long arg3) {
				String[] computer = getResources().getStringArray(
						R.array.comuter);
				String[] jingguan = getResources().getStringArray(
						R.array.JG);
				String[] wenxue = getResources().getStringArray(R.array.WX);

				switch (pos) {
				case 0:
					aadFL2 = new ArrayAdapter<String>(getBaseContext(),
							R.layout.list_item, computer);
					fenlei1 = src.getItemAtPosition(pos).toString();
					break;
				case 1:
					aadFL2 = new ArrayAdapter<String>(getBaseContext(),
							R.layout.list_item, jingguan);
					fenlei1 = src.getItemAtPosition(pos).toString();
					break;
				case 2:
					aadFL2 = new ArrayAdapter<String>(getBaseContext(),
							R.layout.list_item, wenxue);
					fenlei1 = src.getItemAtPosition(pos).toString();
					break;

				default:
					break;
				}
				spFL2.setAdapter(aadFL2);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		spFL2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> src, View arg1, int pos,
					long arg3) {
				switch (pos) {
				case 0:
					fenlei2 = src.getItemAtPosition(pos).toString();
					break;
				case 1:
					fenlei2 = src.getItemAtPosition(pos).toString();
					break;
				case 2:
					fenlei2 = src.getItemAtPosition(pos).toString();
					break;

				default:
					break;
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (rbYes.isChecked()) {
					tb.setChecked(true);
					SJ = "ȷ��";
				}
				if (rbNo.isChecked()) {
					tb.setChecked(false);
					SJ = "��";
				}
			}
		});

		btSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String number = actNumber.getText().toString();
				String name = edName.getText().toString();
				String author = edAuthor.getText().toString();
				String press = edPress.getText().toString();
				String time = edTime.getText().toString();
				String price = edPrice.getText().toString();
				String jieshao = edJS.getText().toString();
				float pingjia = ratPJ.getRating();
				String tuijian = "";

				if (cbJD.isChecked()) {
					tuijian += "����";
				}
				if (cbRX.isChecked()) {
					tuijian += "����";
				}
				if (cbZD.isChecked()) {
					tuijian += "�ö�";
				}
				Toast toast = new Toast(getBaseContext());
				toast.makeText(
						getBaseContext(),
						"ͼ����:" + number + "  ͼ�����ƣ�" + name + "   ���ߣ�" + author
								+ "   �����磺" + press + "  ����ʱ�䣺" + time
								+ "  ͼ��۸�" + price + "  ͼ�����1��" + fenlei1
								+ "  ͼ�����2��" + fenlei2 + "  ͼ����ܣ�" + jieshao
								+ "  ͼ�����ۣ�" + pingjia + "����" + "  ͼ���Ƽ���"
								+ tuijian + SJ + "�ϼ�", Toast.LENGTH_LONG)
						.show();
			}
		});

	}

};
