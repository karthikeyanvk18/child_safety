package com.example.child_safety_track;

 
 

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class home extends Activity {
  
    
Button admin,parent,child,logout;
 
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
				
		admin = (Button) findViewById(R.id.button1);
		parent = (Button) findViewById(R.id.button2);
		child = (Button) findViewById(R.id.button3);
		logout = (Button) findViewById(R.id.button4);
	 
	 
		admin.setOnClickListener(new OnClickListener() 
        { 
			public void onClick(View arg0) {
			  Intent in = new Intent(getApplicationContext(), admin_login.class);
		      startActivity(in);	  				 
			}
        });
		
	 
		
		parent.setOnClickListener(new OnClickListener() 
        {

			 
			public void onClick(View arg0) {
				 
				 Intent in = new Intent(getApplicationContext(),parent_login.class);
		         startActivity(in);		 
			} 
        });
		
		 
		
		child.setOnClickListener(new OnClickListener() 
        {

			 
			public void onClick(View arg0) {
				 
				// Intent in = new Intent(getApplicationContext(),child_login.class);
		       //  startActivity(in);		 
			} 
        });
		logout.setOnClickListener(new OnClickListener() 
        {

			 
			public void onClick(View arg0) {
				 
				 finish(); 
			} 
        });
		
		 
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
