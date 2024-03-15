package com.example.child_safety_track;

  
import java.io.DataInputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
 

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class parent_home extends Activity {
	Button child_track,emergency,logout; 
 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parent_home);
		
		child_track = (Button) findViewById(R.id.button1);
		emergency = (Button) findViewById(R.id.button2);
		 
		logout = (Button) findViewById(R.id.button4);
		

		child_track.setOnClickListener(new OnClickListener() 
        { 
			public void onClick(View arg0) {
				String message="track";
		          
	          	 SmsManager smsManager2 = SmsManager.getDefault();
	             smsManager2.sendTextMessage(parent_login.child_no, null, message, null, null);
	             Toast.makeText(getApplicationContext(), "Childe Mobile Track", Toast.LENGTH_LONG).show();
			  // Intent in = new Intent(getApplicationContext(), parent_child_track.class);
		    // startActivity(in);	  				 
			}
        });

		emergency.setOnClickListener(new OnClickListener() 
        { 
			public void onClick(View arg0) {
				 Intent in = new Intent(getApplicationContext(), parent_child_track.class);
			     startActivity(in);	
			  //Intent in = new Intent(getApplicationContext(), parent_emergency_list.class);
		     // startActivity(in);	  				 
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
