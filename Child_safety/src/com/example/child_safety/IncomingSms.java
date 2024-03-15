package com.example.child_safety;

import java.util.Calendar;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;



import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.CallLog;
import android.app.Activity;
import android.database.Cursor;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;
public class IncomingSms extends BroadcastReceiver {

    
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
   
    public void onReceive(Context context, Intent intent) {
    	String a="";
    	 GPSTracker gps = new GPSTracker(context);
        final Bundle bundle = intent.getExtras();
        try {
        	a=read_contact("second").trim();
        /*	File myFile = new File("/sdcard/child.txt");
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
            if (bundle != null) {
                 
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                 
                for (int i = 0; i < pdusObj.length; i++) {
                     
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();                     
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();   
                    int l1=phoneNumber.length();
                    int l2=a.length();
                    if(l1>10)
                    {
                    	phoneNumber=phoneNumber.substring(3, l1);
                    }
                    
                    if(l2>10)
                    {
                    	a=a.substring(3, l1);
                    }
            	 	Toast.makeText(context, "SMS Received from : " + senderNum +" : " + message+"" , Toast.LENGTH_LONG).show();
            	 	 message=message.toLowerCase().toString().trim();
                     if((phoneNumber.equals(a))&&(message.equals("track")))
                     {
                     	gps.getLocation();
            	         double b=gps.getLatitude();
            	          double c=gps.getLongitude();
                     	String txt="Location Longitute:"+b+"-Latitude :"+c;
                     	 SmsManager sm=SmsManager.getDefault();
             			 PendingIntent dd=null;
             			 //a=a.toString().trim();
             			 
             			 sm.sendTextMessage(a,null,txt,dd,dd);
                     	
                     }
                     else
                     {
                     	Toast.makeText(context, "SMS Received from : " + senderNum +" : " + message+"" , Toast.LENGTH_LONG).show();
                 	 	String txt="From : "+senderNum+"\n Message : "+message;
                 	 	 SmsManager sm=SmsManager.getDefault();
             			 PendingIntent dd=null;
             			 //a=a.toString().trim();
             			 
             			 sm.sendTextMessage(a,null,txt,dd,dd);
             			
                     }
//            	 	String txt="From : "+senderNum+"\n Message : "+message;
//            	 	 SmsManager sm=SmsManager.getDefault();
//        			 PendingIntent dd=null;
//        			 a=a.toString().trim();
//        			 sm.sendTextMessage(a,null,txt,dd,dd);
//        			 Toast.makeText(context, a+""+phoneNumber  +"" , Toast.LENGTH_LONG).show();
   
                  //  context.startService(new Intent(context, MessageService.class));     			 
                } 
              } 
 
        } catch (Exception e) {
            //Log.e("SmsReceiver", "Exception smsReceiver" +e);
    	 	Toast.makeText(context, "SMS Received from : " + e+"" , Toast.LENGTH_LONG).show();             
    
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
