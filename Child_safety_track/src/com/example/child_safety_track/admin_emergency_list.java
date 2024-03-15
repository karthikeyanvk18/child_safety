package com.example.child_safety_track;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class admin_emergency_list extends Activity {
	 ProgressDialog pd;
		public static String id="";
		TextView register;
	  List<String> categories = new ArrayList<String>();
		ListView listView;
		Dialog dialog;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_status);
		 listView = (ListView) findViewById(R.id.listView1);
		 register = (TextView) findViewById(R.id.seldets);
			// register.setText("My Queries");

			        pd = new ProgressDialog(this);
			        pd.setMessage("Loading please wait");
			        pd.setCancelable(false);
				    commonRequestThread();  
				    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
		                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {  
		                     String test = (String) listView.getAdapter().getItem(arg2);  
		                     Log.i("Selected Item in list", test);  
		                     String[] arrSplit = test.split(",");
		                     Log.i("id", arrSplit[0]);  
		                     Toast.makeText(admin_emergency_list.this,arrSplit[0].trim() ,Toast.LENGTH_LONG);
		                     id=arrSplit[0].trim();
		                     parent_child_track.id=id;
		                     Intent i = new Intent(admin_emergency_list.this,user_location_view_1.class); 	 
							 startActivity(i);
		                }  
		           });	
		 
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 public void commonRequestThread() {
	        status = "Please try again later";

	        pd.show();
	        final Handler handler = new Handler() {
	            public void handleMessage(Message msg) {
	                Runnable runnable = new Runnable() {
	                    public void run() {
	                        pd.dismiss();
	                        if(user_id.size()<1){
	                            if(isError) {
	                                toast("No result found");
	                                finish();
	                            }else{
	                                toast("No data found");
	                            }
	                            finish();
	                        }else{
	    						adapter = new ArrayAdapter<String>(context,
	    								android.R.layout.simple_list_item_1,
	    								android.R.id.text1, values);
	    					 	listView.setAdapter(adapter);
	    					 
	    					 
	                        }
	                    }
	                };
	                try {
	                    runOnUiThread(runnable);
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	            }
	        };
	        Thread checkUpdate = new Thread() {
	            public void run() {
	                try {
	                    commonRequest();
	                } catch (Exception e) {
	                    System.out.println("Error in fetching json : "
	                            + e.toString());
	                }
	                handler.sendEmptyMessage(0);
	            }
	        };
	        checkUpdate.start();
	    }


	    Boolean isError = true;
	    public void commonRequest()
	    {
	        isError = true;
	        System.out.println("Common request sent : ");
	        // Create a new HttpClient and Post Header
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://"+MainActivity.ip+"/emergency_list1.php");
	        InputStream is = null;
	        String result = "";
	        try {
	            // Add your data
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	            nameValuePairs.add(new BasicNameValuePair("email",parent_login.uemail));
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            // Execute HTTP Post Request
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity httpEntity = response.getEntity();
	            is = httpEntity.getContent();
	        } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	            System.out.println("Error 1 : "+e.toString());
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            System.out.println("Error 2 : "+e.toString());
	        }

	        try {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    is, "iso-8859-1"), 8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "n");
	            }
	            is.close();
	            result = sb.toString();
	        } catch (Exception e) {
	            System.out.println("Error 2 : "+e.toString());
	        }
	        System.out.println("result : "+result);
	        res = result;
	        JSONObject food_object;

//		        TextView txtFirstName = (TextView) rootView.findViewById(R.id.txtFirstName);
//		        txtFirstName.setText(""+res);

	        try {
	            //food_object = new JSONObject(result);
	            if(result.contains("result")){
	                isError = false;
	            }
	            food_object = new JSONObject(result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1));
	            JSONArray foo_array = food_object.getJSONArray("result");

				values = new String[foo_array.length()];
	            for (int i = 0; i < foo_array.length(); i++) {
	                JSONObject js = foo_array.getJSONObject(i);

	              user_id.add(js.getString("first"));
	              String sts=js.getString("first");
	              
	            	  
	              
	              
	              String d=js.getString("first");

					values[i] = d;
				      categories.add(d);
				  //   categories1.add(js.getString("tname"));

			      System.out.println("value q : "+values[i]);

	            }
	            
	        } catch (JSONException e) {
	            // TODO Auto-generated catch block
	            System.out.println("Error 3 : "+e.toString());
	            e.printStackTrace();
	        }
	    }

	    String res = "", status = "";

		ArrayAdapter<String> adapter;
		String values[];

	    List<String> user_id = new ArrayList<String>();
	    List<String> ownername = new ArrayList<String>();
	    
	    Context context = this;
	    
	    Toast toast;
	    public void toast(String str) {
	        try {
	            toast.cancel();
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
	        toast.setGravity(Gravity.BOTTOM, 0, 0);
	        toast.show();
	    }

}
