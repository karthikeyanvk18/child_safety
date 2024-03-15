package com.example.child_safety;
 

 

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
 

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
public class user_locaion_send extends Activity {
	 GPSTracker  gps = new GPSTracker(this);
	 private int notificationId = 1;
	 TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_send);
       t1 = (TextView)findViewById(R.id.textView2);
        setNotification1();
        new update_locaion().execute();	
       
    }

    public class update_locaion extends AsyncTask<String, String, String> {

        String smobile=read_contact("first");
        String smobile1=read_contact("second");
        String admin=read_contact("admin");
       // String email=read_contact("email");
        String ss="";
       
 
        @Override
        protected void onPreExecute() {
        	// Toast.makeText(getApplicationContext(), "process", Toast.LENGTH_LONG).show();
            super.onPreExecute();
 
        }

       

        protected String doInBackground(String... args) {

            String txt = "";
           
            
            gps.getLocation();
    	    double lat=gps.latitude;      
    	    double land=gps.longitude;
    	    String a=""+lat;
    	    String b=""+land;
    	    if((lat==0)||(lat==0.0))
    	    {
    	    	 a=read_contact("latitude");
        	     b=read_contact("longitude");	
    	    }
    	    String first=read_contact("first").toString().trim();
        	String second=read_contact("second").toString().trim();
    	    String message="child is unsafe : "+first+"\nLocation : "+a+","+b;
    	    ss=message;
    	    SmsManager smsManager1 = SmsManager.getDefault();
            smsManager1.sendTextMessage(smobile.toString().trim(), null, message, null, null);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(smobile1.toString().trim(), null, message, null, null);
            
            SmsManager smsManager2 = SmsManager.getDefault();
            smsManager2.sendTextMessage(admin.toString().trim(), null, message, null, null);
              
    	  

            try {
            	
            	 String ur = "http://"+MainActivity.ip+"/user_location_update.php?"+"first="+first+"&second="+second+"&l1="+a+"&l2="+b;
            	 
              //  String ur = "http://extazee.in/women_security/register1.php?"+"first="+first+"&second="+second+"&l1="+a+"&l2="+b;
                Log.i("URL", ur);
                URL url = new URL(ur);
                HttpURLConnection uc = (HttpURLConnection) url.openConnection();
                DataInputStream dis = new DataInputStream(uc.getInputStream());
                String t = "";

                while ((t = dis.readLine()) != null) {
                    txt += t;
                }
                //Log.i("Read", txt);
               // m=txt;
                dis.close();
                               
            } catch (Exception e) {
                Log.i("Login Ex", e.getMessage());
            }
            return txt;
        }
        protected void onPostExecute(String file_url) {
        	
      	  Log.i("file_url", file_url);
      	  String tmp=file_url;
      	  
          if (file_url.trim().equals("success")) {

        	 // t1.setText(ss);
          	Toast.makeText(getApplicationContext(), "Message Send", Toast.LENGTH_LONG).show();
          	
             // Intent in = new Intent(getApplicationContext(), login.class);
             // startActivity(in);
          
          }
          	//  muser =uid.getText().toString();
          

           else if(file_url.trim().equals("failed")) {
              Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
          }
          else
          { 
        	  Toast.makeText(getApplicationContext(), "Track", Toast.LENGTH_LONG).show();
          
         // Toast.makeText(getApplicationContext(), "Please Check Login...!", Toast.LENGTH_LONG).show();
          
          }
          finish();
         // pDialog.dismiss();
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
    public void setNotification1() {

    	//**add this line**
    	int requestID = (int) System.currentTimeMillis();

    	//Uri alarmSound = getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
     	//mNotificationManager  = getApplication().getSystemService(Context.NOTIFICATION_SERVICE);
     	 NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    	Intent notificationIntent = new Intent(getApplicationContext(), user_locaion_send.class);

    	//**add this line**
    	notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP); 

    	//**edit this line to put requestID as requestCode**
    	PendingIntent contentIntent = PendingIntent.getActivity(this, requestID,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    	NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
    	.setSmallIcon(R.drawable.ic_launcher)
    	.setContentTitle("Women Security")
    	.setStyle(new NotificationCompat.BigTextStyle()
    	.bigText("Send Notification"))
    	.setContentText("Emergency").setAutoCancel(true);
    	//mBuilder.setSound(alarmSound);
    	mBuilder.setContentIntent(contentIntent);
    	notificationManager.notify(notificationId, mBuilder.build());

    	} 
    
}
