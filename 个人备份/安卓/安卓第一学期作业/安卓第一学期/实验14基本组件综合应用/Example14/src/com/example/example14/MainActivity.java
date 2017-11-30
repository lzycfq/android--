package com.example.example14;

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
	Spinner spinner1=null;
	AutoCompleteTextView actnumber=null;
	ArrayAdapter<String> aadfenlei2=null;
	Spinner spfenlei2=null;
	RadioButton rbyes=null;
	RadioButton rbno=null;
	RadioGroup rg=null;
	ToggleButton tb=null;
	Button btbaocun=null;
	TextView tvnumber=null;
	EditText edname=null;
	EditText edauthor=null;
	EditText edpress=null;
	EditText edtime=null;
	EditText edprice=null;
	EditText edjieshao=null;
	String fenlei1,fenlei2;
	RatingBar ratpinjia=null;
	CheckBox cbjingdian=null;
	CheckBox cbrexiao=null;
	CheckBox cbzhiding=null;
	String shangjia;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        spinner1=(Spinner) findViewById(R.id.spfenlei1);
        actnumber=(AutoCompleteTextView) findViewById(R.id.actnumber);
        
        String[] strnumber=getResources().getStringArray(R.array.number);
        ArrayAdapter<String> adnumber=new ArrayAdapter<String>(this,R.layout.list_item,strnumber);
        actnumber.setAdapter(adnumber);
        
        String[] strfenlei1=getResources().getStringArray(R.array.fenlei1);
        ArrayAdapter<String> adfenlei=new ArrayAdapter<String>(this, R.layout.list_item, strfenlei1);
        spinner1.setAdapter(adfenlei);
        
        spfenlei2=(Spinner) findViewById(R.id.spfenlei2);
        
        rg=(RadioGroup) findViewById(R.id.rd);
        rbyes=(RadioButton) findViewById(R.id.rbyes);
        rbno=(RadioButton) findViewById(R.id.rbno);
        tb=(ToggleButton) findViewById(R.id.tb);
        btbaocun=(Button) findViewById(R.id.btbaocun);
        tvnumber=(TextView) findViewById(R.id.tvnumber);
        edname=(EditText) findViewById(R.id.edname);
        edauthor=(EditText) findViewById(R.id.edauthor);
        edpress=(EditText) findViewById(R.id.edpress);
        edtime=(EditText) findViewById(R.id.edtime);
        edprice=(EditText) findViewById(R.id.edprice);
        edjieshao=(EditText) findViewById(R.id.edjieshao);
        ratpinjia=(RatingBar) findViewById(R.id.ratpinjia);
        cbjingdian=(CheckBox) findViewById(R.id.cbjingdian);
        cbrexiao=(CheckBox) findViewById(R.id.cbrexiao);
        cbzhiding=(CheckBox) findViewById(R.id.cbzhiding);
        		
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> src, View arg1,
					int pos, long arg3) {
				String[] computer=getResources().getStringArray(R.array.comuter);
				String[] jingguan=getResources().getStringArray(R.array.jingguan);
				String[] wenxue=getResources().getStringArray(R.array.wenxue);
				
				switch (pos) {
				case 0:
					aadfenlei2=new ArrayAdapter<String>(getBaseContext(),R.layout.list_item,computer);
					fenlei1=src.getItemAtPosition(pos).toString();
					break;
				case 1:
					aadfenlei2=new ArrayAdapter<String>(getBaseContext(),R.layout.list_item,jingguan);
					fenlei1=src.getItemAtPosition(pos).toString();
					break;
				case 2:
					aadfenlei2=new ArrayAdapter<String>(getBaseContext(),R.layout.list_item,wenxue);
					fenlei1=src.getItemAtPosition(pos).toString();
					break;

				default:
					break;
				}
				spfenlei2.setAdapter(aadfenlei2);
			}
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
        
        spfenlei2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> src, View arg1,
					int pos, long arg3) {
				switch (pos) {
				case 0:
					fenlei2=src.getItemAtPosition(pos).toString();
					break;
				case 1:
					fenlei2=src.getItemAtPosition(pos).toString();
					break;
				case 2:
					fenlei2=src.getItemAtPosition(pos).toString();
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
				if(rbyes.isChecked()){
					tb.setChecked(true);
					shangjia="确认";
				}
				if(rbno.isChecked()){
					tb.setChecked(false);
					shangjia="不";
				}
			}
		});
        
        
       
       
        
        btbaocun.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String number=actnumber.getText().toString();
				String name=edname.getText().toString();
				String author=edauthor.getText().toString();
				String press=edpress.getText().toString();
				String time=edtime.getText().toString();
				String price=edprice.getText().toString();
				String jieshao=edjieshao.getText().toString();
				float pingjia=ratpinjia.getRating();
				String tuijian="";
				
				if(cbjingdian.isChecked()){
					tuijian+="经典";
				}if(cbrexiao.isChecked()){
					tuijian+="热销";
				}if(cbzhiding.isChecked()){
					tuijian+="置顶";
				}
				Toast toast=new Toast(getBaseContext());
				toast.makeText(getBaseContext(), "图书编号:"+number+"  图书名称："+name+"   作者："+author+"   出版社："+press
						+"  出版时间："+time+"  图书价格："+price+"  图书分类1："+fenlei1+"  图书分类2："+fenlei2
						+"  图书介绍："+jieshao+"  图书评价："+pingjia+"颗星"+"  图书推荐："+tuijian
						+shangjia+"上架", Toast.LENGTH_LONG).show();
			}
		});
        
       
    }

  

		
	};
    

