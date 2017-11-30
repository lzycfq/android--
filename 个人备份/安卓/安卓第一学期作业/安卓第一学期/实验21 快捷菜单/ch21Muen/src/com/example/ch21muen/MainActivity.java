package com.example.ch21muen;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.TextureView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    TextView se;
    private boolean ture;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        se=(TextView)findViewById(R.id.se);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
    	SubMenu subMenu=menu.addSubMenu(0,1,1,"����");
    	
    	subMenu.setHeaderTitle("��ѡ�������С");
    	subMenu.setHeaderIcon(android.R.drawable.ic_menu_info_details);
    	subMenu.add(0, 1, 1, "18������");
    	subMenu.add(0, 2, 2, "14������");
    	subMenu.add(0, 3 ,3,  "10������");
SubMenu sub=menu.addSubMenu(0,2,2,"��ɫ");
    	
    	subMenu.setHeaderTitle("��ѡ��������ɫ");
    	sub.setHeaderIcon(android.R.drawable.ic_menu_info_details);
    	sub.add(0, 4, 4, "��ɫ");
    	sub.add(0, 5, 5, "��ɫ");
    	sub.add(0, 6 ,6,  "��ɫ");
        return true;
    }

    public boolean onOptionItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case 1:
    	     se.setTextSize(18);
    		break;
    	case 2:
    		se.setTextSize(14);
    		break;
    	case 3:
    		se.setTextSize(10);
    		break;
    	case 4: se.setTextColor(Color.RED);
    		break;
    		
    	case 5:se.setTextColor(Color.BLUE);
    		break;
    		
    	case 6:se.setTextColor(Color.BLUE);
    		break;
    	}
		return super.onOptionsItemSelected(item);
    }
}