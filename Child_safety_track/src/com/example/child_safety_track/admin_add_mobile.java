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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin_add_mobile extends Activity {
	EditText e1;
	Button btn;
	ProgressDialog pDialog;
	 
 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_mobile);
		e1 = (EditText) findViewById(R.id.u_name);
	 
		btn = (Button) findViewById(R.id.button1);
		ProgressDialog pDialog;
		 
		btn.setOnClickListener(new OnClickListener() 
        { 
			public void onClick(View arg0) {
				String qry=e1.getText().toString().trim();
			 
				if(qry.isEmpty())
				{
					Toast.makeText(getApplicationContext(), "Enter Mobile No", Toast.LENGTH_LONG).show();
				}
			 
				else
				{
					new userlogin().execute();
				}
				
				
			   	  				 
			}
        });
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public class userlogin extends AsyncTask<String, String, String> {
		 String lname=e1.getText().toString();
		 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(admin_add_mobile.this);
            pDialog.setMessage("Connecting...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected String doInBackground(String... args) {
            String txt = "";
            try {
                String ur = "http://"+MainActivity.ip+"/admin_mobile_update.php?mobile="+URLEncoder.encode(lname,"UTF-8")
                	 
                		;
                URL url = new URL(ur);
                Log.i("URL", ""+url);
                HttpURLConnection uc = (HttpURLConnection) url.openConnection();
                DataInputStream dis = new DataInputStream(uc.getInputStream());
                String t = "";

                while ((t = dis.readLine()) != null) {
                    txt += t;
                }
                Log.i("Read", txt);
              //  m=txt;
                dis.close();
                               
            } catch (Exception e) {
                Log.i("Login Ex", e.getMessage());
            }
            return txt;
        }
        protected void onPostExecute(String file_url) {
      	  Log.i("file_url", file_url);
          if (file_url.trim().equals("success")) {

        	 

               	Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();
            	finish();
            
          
          }
           else if(file_url.trim().equals("failed")) {
              Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
          }
          else
          { Toast.makeText(getApplicationContext(), "Connection Failed - Check Server..", Toast.LENGTH_LONG).show();}

          pDialog.dismiss();
      }
  
}
}
