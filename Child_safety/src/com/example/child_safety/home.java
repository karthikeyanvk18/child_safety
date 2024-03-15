package com.example.child_safety;

 
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
 

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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

import java.sql.Date;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

public class home extends Activity {
	TextView t1,t2,t3;
	private int notificationId = 1;
	 ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        t1 = (TextView)findViewById(R.id.textView1);
        t2 = (TextView)findViewById(R.id.textView2);
        t3 = (TextView)findViewById(R.id.textView3);
    	new userlogin1().execute();
      //  setNotification1();
      //  sendNotification();
       // cancelNotification();
        t1.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0) {
        		 Intent in = new Intent(getApplicationContext(), user_number_update.class);
			       startActivity(in);
			       
        	  }
        });
        t2.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0) {
        		 Intent in = new Intent(getApplicationContext(), user_location_track.class);
			       startActivity(in);
        	  }
        });
        t3.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0) {
        		 Intent in = new Intent(getApplicationContext(), user_location_view_1.class);
			       startActivity(in);
        	  }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }  
    
    public void sendNotification() {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.journaldev.com/"));
        
 
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        
        builder.setContentIntent(pendingIntent);
         
        builder.setContentTitle("Women Security");
        builder.setContentText("Your notification content here.");
        builder.setSubText("Tap to Send Location.");

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());
    }

    
    public void cancelNotification() {

        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
        nMgr.cancel(1);


    }

public void onNotificationAction(Context context, Date actionTime, String pushType, String actionType, String actionValue) {
	 Toast.makeText(getApplicationContext(), "clicke success", Toast.LENGTH_LONG).show(); 
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
public class userlogin1 extends AsyncTask<String, String, String> {

    
	 
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(home.this);
	            pDialog.setMessage("Loading...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

	        

	        protected String doInBackground(String... args) {

	            String txt = "";
	            


	            try {
	           	 
	            	
	                String ur = "http://"+MainActivity.ip+"/get_admin.php";
	 
	               
	               
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
	      	 try {
	      String admin_no=file_url.toString().trim();
	      File file1=new File(Environment.getExternalStorageDirectory() + File.separator +"women_security/");
			if(!file1.exists()) {                                 
			  file1.mkdirs();
			}
			 final File file = new File(file1, "admin.txt");
			
				file.createNewFile();
			
		       FileOutputStream fOut = new FileOutputStream(file);
		       OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
		       myOutWriter.append(""+admin_no);

		       myOutWriter.close();

		       fOut.flush();
		       fOut.close();
	      	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          pDialog.dismiss();
	      }
	  
	}
}
