package com.example.child_safety_track;

 
 
import java.io.DataInputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

 
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class parent_register extends Activity {
	 Button regbtn ;
	 ProgressDialog pDialog;
	 EditText name,contact,email,pass1,pass2,address,child_no;
	 public static String nam="",fpass="",spass="",cont="",eml="",ran="",childno="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.user_register);
		regbtn = (Button) findViewById(R.id.reg);
		name = (EditText)findViewById(R.id.name);
	    contact = (EditText)findViewById(R.id.con);
	    email = (EditText)findViewById(R.id.email);
	    pass1 = (EditText)findViewById(R.id.pass);
	    pass2 = (EditText)findViewById(R.id.cpass);
	    address = (EditText)findViewById(R.id.addr);
	    child_no = (EditText)findViewById(R.id.child);
	     
		regbtn.setOnClickListener(new OnClickListener() 
	        {

				@Override
				public void onClick(View arg0) {
					nam=name.getText().toString();
					fpass=pass1.getText().toString();
					spass=pass2.getText().toString();
					cont=contact.getText().toString();
					eml=email.getText().toString();
					childno=child_no.getText().toString();
					if(nam.equals(""))
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(fpass.equals(""))
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(spass.equals(""))
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(cont.equals(""))
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(eml.equals(""))
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(childno.equals(""))
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					
					else if(fpass.equals(spass))
					{
						// Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_LONG).show();
		                 Log.i("Pass", "same");
						 new reg().execute();	
					}
					else
					{
						Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();	
						Intent i = new Intent(parent_register.this,parent_register.class); 	 
					   	startActivity(i);
					}
					
					
					//Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
					 							 
					//Intent i = new Intent(Registration.this,MainActivity.class); 	 
				   	//startActivity(i);
					 // Toast.makeText(getApplicationContext(), "start", Toast.LENGTH_LONG).show();
				//mEdit.setText(""+m);

					
				}
	        });
	}
	 public class reg extends AsyncTask<String, String, String> {

	        //capture values from EditText
			 
			// String accno,name1,address,gen,pass,dob;
	  		 
       
	//  String userid=u.getText().toString();
	//  String password=p.getText().toString();
		String rnam=name.getText().toString();
		String	rfpass=pass1.getText().toString();
		String	rspass=pass2.getText().toString();
		String	rcont=contact.getText().toString();
		String	reml=email.getText().toString();
		String	address1=address.getText().toString();
		String childnumber=child_no.getText().toString();
	        @Override
	        protected void onPreExecute() {
	        	// Toast.makeText(getApplicationContext(), "process", Toast.LENGTH_LONG).show();
	            super.onPreExecute();
	            pDialog = new ProgressDialog(parent_register.this);
	            pDialog.setMessage("Requesting " + rnam + ")...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

	       

	        protected String doInBackground(String... args) {

	            String txt = "";
	            	           
	            try {
	                String ur = "http://"+MainActivity.ip+"/parent_register.php?"+ "name=" + rnam + "&pass=" +rfpass+ "&cont=" +rcont+ "&email=" +reml+"&address="+address1+"&child="+childnumber+"" ;
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

	             
	          	Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
	          	finish();
	             // Intent in = new Intent(getApplicationContext(), login.class);
	             // startActivity(in);
	          
	          }
	          	//  muser =uid.getText().toString();
	          

	           else if(file_url.trim().equals("Invalid user")) {
	              Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_LONG).show();
	          }
	          else
	          { Toast.makeText(getApplicationContext(), file_url, Toast.LENGTH_LONG).show();
	          
	         // Toast.makeText(getApplicationContext(), "Please Check Login...!", Toast.LENGTH_LONG).show();
	          
	          }

	          pDialog.dismiss();
	      }
	  
	}
	    
}
