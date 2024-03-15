package com.example.child_safety;
 

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

 

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
public class MainActivity extends Activity {
	private int	notificationId = 1;
	public static String ip="";
	ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //addShortcut();
         setNotification1();
        new Handler().postDelayed(new Runnable() 
		 {   
	            public void run() {
	            	  final Dialog dialog = new Dialog(MainActivity.this);
			             dialog.setContentView(R.layout.server_ip_address);
			             dialog.setCancelable(false);
			             dialog.setTitle("Enter Ip Address");

				           final  EditText hn= (EditText)dialog.findViewById(R.id.editText1);
				           hn.setText("192.168.98.108");
				           
			             // set the custom dialog components - text, image and button
			             Button send = (Button) dialog.findViewById(R.id.button2);


			             Button cancel = (Button) dialog.findViewById(R.id.button1);
			             // if button is clicked, close the custom dialog

			             send.setOnClickListener(new View.OnClickListener() {
			                 @Override
			                 public void onClick(View v) {


			                     String hname= hn.getText().toString().trim();
			                     //ip=hname;
			                     dialog.dismiss();
			                     if(hname.equals(""))
			    				 {
			    			      Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();
			    			     // finish();
			    				 }
			    				 else
			    				 {
			    					 ip=hname+"/android_child_safty";
			    				     new userlogin().execute();  	
			    				 } 	
			                 }
			             });



			             cancel.setOnClickListener(new View.OnClickListener() {
			                 @Override
			                 public void onClick(View v) {
			                     dialog.dismiss();
			                      finish();

			                 }
			             });
			             dialog.show();
//	                final Intent mainIntent = new Intent(MainActivity.this, home.class);
//	                MainActivity.this.startActivity(mainIntent);
//	                MainActivity.this.finish();
	            }
	        }, 3000);
    }

    private void addShortcut() {
    	//Adding shortcut for MainActivity 
    	//on Home screen
		Intent shortcutIntent = new Intent(getApplicationContext(),
				MainActivity.class);
		
		shortcutIntent.setAction(Intent.ACTION_MAIN);

		Intent addIntent = new Intent();
		addIntent
				.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Home Free");
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
			Intent.ShortcutIconResource.fromContext(getApplicationContext(),
						R.drawable.ic_launcher));

		addIntent
				.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
		getApplicationContext().sendBroadcast(addIntent);
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
    	.setContentTitle("Child Safety")
    	.setStyle(new NotificationCompat.BigTextStyle()
    	.bigText("Send Notification"))
    	.setContentText("Emergency").setAutoCancel(true);
    	//mBuilder.setSound(alarmSound);
    	mBuilder.setContentIntent(contentIntent);
    	notificationManager.notify(notificationId, mBuilder.build());

    	} 
    
    public class userlogin extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Connecting...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        

        protected String doInBackground(String... args) {

            String txt = "";
            


            try {
           	 
            	
                String ur = "http://"+ip+"/login.php";
 
               
               
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
      	if ((file_url.trim().equals("202202"))||file_url.trim().equals("202203")||file_url.trim().equals("202204")||file_url.trim().equals("202202")||file_url.trim().equals("202203")||file_url.trim().equals("202204")) {

        	 

               	Toast.makeText(getApplicationContext(), "Connected Successfully", Toast.LENGTH_LONG).show();
            	finish();
                //Intent in = new Intent(getApplicationContext(), home.class);
                Intent in = new Intent(getApplicationContext(), home.class);
           startActivity(in);
          
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
