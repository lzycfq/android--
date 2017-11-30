package com.example.ch41;


import com.example.ch40.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	EditText editName,editPhone,editTitle,editId;
	Button btnAdd;
	DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper=new DBHelper (this);
        dbhelper.getWritableDatabase();
        
        editName=(EditText)findViewById(R.id.editName);
        editPhone=(EditText)findViewById(R.id.editPhone);
        editTitle=(EditText)findViewById(R.id.editTitle);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		SQLiteDatabase db=dbhelper.getWritableDatabase();
    	ContentValues values=new ContentValues();
    	values.put("name", editName.getText().toString());
    	values.put("phone", editPhone.getText().toString());
    	values.put("title", editTitle.getText().toString());
    	Long r=db.insert("friends", null, values);
    	if(r>0){Toast.makeText(getBaseContext(), "Ìí¼Ó³É¹¦", 0).show();}
    	db.close();
	}
    
}
