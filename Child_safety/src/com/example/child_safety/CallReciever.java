package com.example.child_safety;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CallReciever extends BroadcastReceiver {	
    @Override
    public void onReceive(Context context, Intent intent) {
    	 
    	 System.out.println("Receiver start");
         Toast.makeText(context," Receiver start ",Toast.LENGTH_SHORT).show();
//         String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
//         String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
//         if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
//             Toast.makeText(context,"Ringing State Number is - " + incomingNumber, Toast.LENGTH_SHORT).show();
//         }
         String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
         if (!intent.getExtras().containsKey(TelephonyManager.EXTRA_INCOMING_NUMBER)) {
             Log.i("Call receiver", "skipping intent=" + intent + ", extras=" + intent.getExtras() + " - no number was supplied");
             return;
         }
         String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
         Toast.makeText(context,"Ringing State Number is - " + number, Toast.LENGTH_SHORT).show();
         String sender=read_contact("second").toString().trim();
         SmsManager sm=SmsManager.getDefault();
			 PendingIntent dd=null;
			 String txt="Incoming Call : "+number;
			 sm.sendTextMessage(sender,null,txt,dd,dd);
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