package com.example.ch18listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
  ListView list;
  String[] id={"001", "002", "003", "004", "005", "006", "007", "008","009", "010"};
  String[] name={"¼×", "ÒÒ", "±û", "¶¡", "Îì", "ËÈ", "¸ý", "ÐÁ", "ÈÉ", "¹ï"};
   Map<String,String> student;
   void initData(){
   	student=new HashMap<String,String>();
   	for(int i=0;i<id.length;i++){
   		student.put(id[i],name[i]);
   	}
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       list=(ListView)findViewById(R.id.list);
       initData(); 
      ArrayAdapter<String> idA=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,id);
      list.setAdapter(idA);
      list.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
				long arg3) {
			String str=String.format("Ñ§ºÃ:%","ÐÕÃû£º%",id[pos],student.get(id[pos]));
			Toast.makeText(getApplicationContext(),str,1).show();			
		}
	});
    }

private List<String> getData(){
	ArrayList<String> str=new ArrayList<String>();
	str.add("1");
	str.add("2");
	str.add("3");
	str.add("4");
	return str;
}



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
