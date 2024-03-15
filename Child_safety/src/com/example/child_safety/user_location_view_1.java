package com.example.child_safety;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

public class user_location_view_1 extends Activity{
	 ProgressDialog pDialog;
	 GPSTracker  gps = new GPSTracker(this);
	  double lat=0;      
	    double land=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		  gps.getLocation();
		     lat=gps.latitude;      
		     land=gps.longitude;
		//lat=Double.parseDouble(MainActivity.l1);
		//lon=Double.parseDouble(MainActivity.l2);
		  new ViewMap().execute(new String[]{"",""});
		 /* 	Geocoder geocoder = new Geocoder(this, Locale.getDefault());
      try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                    
                }
                
                String strAdd = strReturnedAddress.toString();
                Log.w("My Current loction address", "" + strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        */
		
     // Toast.makeText(getApplicationContext(), ""+lat+"\n"+land, Toast.LENGTH_LONG).show();

       
	}
	 class ViewMap extends AsyncTask<String, Void, String>
	    {

			 

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				 super.onPreExecute();
		            pDialog = new ProgressDialog(user_location_view_1.this);
		            pDialog.setMessage("Loading...");
		            pDialog.setIndeterminate(false);
		            pDialog.setCancelable(true);
		            pDialog.show();
			}
			 
			@Override
			protected String doInBackground(String... params) {
				String txt=""; 
				try
					{							
					  WebView webView = (WebView) findViewById(R.id.webView1);
			    	  webView.getSettings().setJavaScriptEnabled(true);
			         // webView.loadUrl("http://maps.google.com/maps?q="+lat+","+lon+"");    
			          webView.loadUrl("http://"+MainActivity.ip+"/map.php?l1="+lat+"&l2="+land);    
			          }
					catch(Exception e)
					{
						Log.i("addvehicel ",e.getMessage()+"");
					}
//				 try {
//		                String ur = "http://"+MainActivity.sip+"/map.php?id=2"  ;
//		                Log.i("URL", ur);
//		                URL url = new URL(ur);
//		                HttpURLConnection uc = (HttpURLConnection) url.openConnection();
//		                DataInputStream dis = new DataInputStream(uc.getInputStream());
//		                String t = "";
//
//		                while ((t = dis.readLine()) != null) {
//		                    txt += t;
//		                }
//		                //Log.i("Read", txt);
//		               // m=txt;
//		                dis.close();
//		                               
//		            } catch (Exception e) {
//		                Log.i("Login Ex", e.getMessage());
//		            }
				return txt;
			}
 			 protected void onPostExecute(String file_url) {
//		      	 // Log.i("file_url", file_url);
//		          if (file_url.trim().equals("success")) {
//
//		             
//		          	Toast.makeText(getApplicationContext(), "update Success!", Toast.LENGTH_LONG).show();
//		          	//  muser =uid.getText().toString();
//		           //  Intent in = new Intent(getApplicationContext(), home.class);
//		          //startActivity(in);
//		          }
//		           else if(file_url.trim().equals("failed")) {
//		              Toast.makeText(getApplicationContext(), "failed reach", Toast.LENGTH_LONG).show();
//		          }
//		          else
//		          { Toast.makeText(getApplicationContext(), "failed!", Toast.LENGTH_LONG).show();}
//
 		          pDialog.dismiss();
 		      }
			    	
	    }
}
