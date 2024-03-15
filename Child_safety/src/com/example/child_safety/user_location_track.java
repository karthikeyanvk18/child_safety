package com.example.child_safety;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

 

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class user_location_track extends Activity {
	EditText e1,e2;
	Button b1,b2;
	 GPSTracker  gps = new GPSTracker(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_track);
		 e1=(EditText)findViewById(R.id.editText1);
		 e2=(EditText)findViewById(R.id.editText2);
		 
		 
		 b1=(Button)findViewById(R.id.button2);
		 b2=(Button)findViewById(R.id.button1);
		 
		 
		 
	    gps.getLocation();
	    double lat=gps.latitude;      
	    double land=gps.longitude;
	    e1.setText(""+lat);
	    e2.setText(""+land);
        
	    b1.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0) {
        	    gps.getLocation();
        	    double lat=gps.latitude;      
        	    double land=gps.longitude;
        	    e1.setText(""+lat);
        	    e2.setText(""+land);
//        		String a=e1.getText().toString().trim();
//        		String b=e2.getText().toString().trim();
//        		
//        		  File file1=new File(Environment.getExternalStorageDirectory() + File.separator +"women_security/");
//        			if(!file1.exists()) {                                 
//        			  file1.mkdirs();
//        			}
//        			 final File file = new File(file1, "latitude.txt");
//        			   final File file2 = new File(file1, "longitude.txt");
//        			   try
//        			   {
//        			       file.createNewFile();
//        			       FileOutputStream fOut = new FileOutputStream(file);
//        			       OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
//        			       myOutWriter.append(a);
//
//        			       myOutWriter.close();
//
//        			       fOut.flush();
//        			       fOut.close();
//        			       //////////
//        			       file2.createNewFile();
//        			       FileOutputStream fOut1 = new FileOutputStream(file2);
//        			       OutputStreamWriter myOutWriter1 = new OutputStreamWriter(fOut1);
//        			       myOutWriter1.append(b);
//
//        			       myOutWriter1.close();
//
//        			       fOut1.flush();
//        			       fOut1.close();
//        			       
//        			       Toast.makeText(getApplicationContext(), "update success", Toast.LENGTH_LONG).show(); 
//        			       Intent in = new Intent(getApplicationContext(), home.class);
//        			       startActivity(in);
//        			       
//        			       
//        			   }
//        			   catch (IOException e)
//        			   {
//        			 	  	 Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
//
//        			       Log.e("Exception", "File write failed: " + e.toString());
//        			   } 
        		
    }
        });

	    b2.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0) {
        	    
        	  
        		String a=e1.getText().toString().trim();
        		String b=e2.getText().toString().trim();
        		
        		  File file1=new File(Environment.getExternalStorageDirectory() + File.separator +"women_security/");
        			if(!file1.exists()) {                                 
        			  file1.mkdirs();
        			}
        			 final File file = new File(file1, "latitude.txt");
        			   final File file2 = new File(file1, "longitude.txt");
        			   try
        			   {
        			       file.createNewFile();
        			       FileOutputStream fOut = new FileOutputStream(file);
        			       OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
        			       myOutWriter.append(a);

        			       myOutWriter.close();

        			       fOut.flush();
        			       fOut.close();
        			       //////////
        			       file2.createNewFile();
        			       FileOutputStream fOut1 = new FileOutputStream(file2);
        			       OutputStreamWriter myOutWriter1 = new OutputStreamWriter(fOut1);
        			       myOutWriter1.append(b);

        			       myOutWriter1.close();

        			       fOut1.flush();
        			       fOut1.close();
        			       
        			       Toast.makeText(getApplicationContext(), "update success", Toast.LENGTH_LONG).show(); 
        			       Intent in = new Intent(getApplicationContext(), home.class);
        			       startActivity(in);
        			       
        			       
        			   }
        			   catch (IOException e)
        			   {
        			 	  	 Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();

        			       Log.e("Exception", "File write failed: " + e.toString());
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
	String read_contact(String fname)
	{
		 String    m1="";
		StringBuilder text = new StringBuilder();
	    try {
	    File sdcard = Environment.getExternalStorageDirectory();
		  File file1=new File(Environment.getExternalStorageDirectory() + File.separator +"women_security/");

	    File file = new File(file1,fname+".txt");

	        BufferedReader br = new BufferedReader(new FileReader(file));  
	        String line;   
	        while ((line = br.readLine()) != null)
	        {
	                   text.append(line);
	                   // Log.i("Test", "text : "+text+" : end");
	                   text.append("");
	        } 
	        br.close();
	         m1=""+text.toString().trim();
	      
	       
	        }
	    catch (IOException e) {
	        e.printStackTrace();                    

	    }
	    return m1;
	}
	
}
