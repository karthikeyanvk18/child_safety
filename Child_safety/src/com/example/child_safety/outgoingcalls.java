package com.example.child_safety;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class outgoingcalls extends BroadcastReceiver {
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		String a="";
		
		GPSTracker gps = new GPSTracker(context);

		
		String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
	
		Log.d(outgoingcalls.class.getSimpleName(), intent.toString() + ", call to: " + phoneNumber);
		 		
	 	Toast.makeText(context, "Outgoing call catched: " + phoneNumber +"" , Toast.LENGTH_LONG).show();
		//TODO: Handle outgoing call event here
	 
	 	
	 	try{
	 		/*File myFile = new File("/sdcard/child.txt");
			FileInputStream fIn = new FileInputStream(myFile);
			BufferedReader myReader = new BufferedReader(
					new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while((aDataRow = myReader.readLine()) != null) {
				aBuffer += aDataRow + "\n";
			}
			
			a=aBuffer;
			myReader.close();
			*/
			
	 		gps.getLocation();
	         double b=gps.getLatitude();
	          double c=gps.getLongitude();

 			 SmsManager sm=SmsManager.getDefault();
 			 PendingIntent dd=null;
 			 //a=a.toString().trim();
 			a=read_contact("second").toString().trim();
 			Toast.makeText(context, a+"Outgoing calls"+phoneNumber  +"" , Toast.LENGTH_LONG).show();
			// String txt="Outgoing Call : "+phoneNumber+"Location Longitute:"+b+"Latitude :"+c;
 			 String txt="Outgoing Call : "+phoneNumber;
 			 if(phoneNumber.equals("*2020#"))
 			 {
 				PackageManager p =context.getPackageManager();
 				ComponentName componentName = new ComponentName("com.example.child_safety_track","com.example.child_safety_track.MainActivity");

p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

 }
 			 else if(phoneNumber.equals("*0202#")) 
 			 {
 			       PackageManager p = context.getPackageManager();
 				ComponentName componentName = new ComponentName("com.example.child_safety_track","com.example.child_safety_track.MainActivity"); 
 				p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
 			
 			 }
 			 else
 			 {
 				sm.sendTextMessage(a,null,txt,dd,dd);
 	 			// Toast.makeText(context, a+"Outgoing calls"+phoneNumber  +"" , Toast.LENGTH_LONG).show();
 	 			 
 			 }
 			 
 		 }
 		 catch(Exception e)
 		 {
 			 Toast.makeText(context, "" + e.getMessage() +"" , Toast.LENGTH_LONG).show();
  		 }
	 	
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
	

	
