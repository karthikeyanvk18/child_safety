package com.example.child_safety_track;
 

import java.io.DataInputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
 

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class admin_login extends Activity {
	 ProgressDialog pDialog;

	 Button loginbtn;
	 EditText useremail;
	 EditText password;	
	 TextView ttl;
	 public static String uemail="";
	 TextView register;
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login); 
		loginbtn = (Button) findViewById(R.id.button1);
	    register = (TextView)findViewById(R.id.textView2);
	    ttl = (TextView)findViewById(R.id.textView1);

		useremail = (EditText)findViewById(R.id.u_name);
		password = (EditText)findViewById(R.id.pass);
		useremail.setHint("User Name");
		 //useremail.setText("admin");
		 //password.setText("admin");
		ttl.setText("Admin Login");
		 register.setVisibility(View.INVISIBLE);
		loginbtn.setOnClickListener(new OnClickListener() 
	        {

				@Override
				public void onClick(View arg0) {
				 // new userlogin().execute();	
					 String lname=useremail.getText().toString();
						String	lpass=password.getText().toString();	
						if(lname.equals("admin")&&(lpass.equals("admin"))){
						 
							  Intent in = new Intent(getApplicationContext(), admin_home.class);
					          startActivity(in);
						}
						else
						{
							Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_LONG).show();	
						}
				}
	        });
		
//		 register.setOnClickListener(new OnClickListener() 
//	        {
//
//				@Override
//				public void onClick(View arg0) {
//				 	 
//				  Intent i = new Intent(admin_login.this,user_reg.class);
//			    	 
//
//				   startActivity(i);
//			 
//					
//				}
//	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	     
}
