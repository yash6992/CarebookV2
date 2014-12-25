package com.tokbox.android.opentokrtc;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.tokbox.android.opentokrtc.RegisterActivity.GetContacts;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class MakeAccountActivity extends Activity {
	AlertDialogManager alert = new AlertDialogManager();
	private ProgressDialog pDialog;
	ArrayList<NameValuePair> parameters;
	String jsonStr,authentication_token,email,userId,fullName,phoneNo;
	TextView t1;
	
	
	// URL to get contacts JSON
		private static String url = "http://cbk-carebook.rhcloud.com/api/patients.json";
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_makeaccount);
	        
	        
	        parameters= new ArrayList<NameValuePair>();

	        Intent i= getIntent();
	        
	        authentication_token=i.getStringExtra("authentication_token");
	        email=i.getStringExtra("email");
	        userId=i.getStringExtra("user_id");
	        fullName=i.getStringExtra("name");
	        phoneNo=i.getStringExtra("phone_no");
	        t1=(TextView) findViewById(R.id.makeAccount);
	        
	        NameValuePair p1=new BasicNameValuePair("user_id",userId);
			NameValuePair p2=new BasicNameValuePair("name",fullName);
			NameValuePair p3=new BasicNameValuePair("mobile",phoneNo);
			
			Log.d("Response: ","name value pair made");
			
			parameters.add(p1);
			parameters.add(p2);
			parameters.add(p3);
			// Staring MainActivity

			Log.d("Response: ","name value pair added");
			// Calling async task to get json
			new GetContacts().execute();
	        
	        
	  }
	  class GetContacts extends AsyncTask<Void, Void, Void> {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				// Showing progress dialog
				Log.d("Response: ","in pre execute");
				pDialog = new ProgressDialog(MakeAccountActivity.this);
				pDialog.setMessage("Please wait...");
				pDialog.setCancelable(false);
				pDialog.show();

			}

			@Override
			protected Void doInBackground(Void... arg0) {
				// Creating service handler class instance
				ServiceHandler sh = new ServiceHandler();

				// Making a request to url and getting response
				try {
					Log.d("Response: ","in try 1");
					jsonStr = sh.makeServiceCallWithPatient(url, ServiceHandler.POST,parameters,authentication_token,email);
					Log.d("Response: ","in try 2");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Log.d("Response: ", "> " + jsonStr);

				if (jsonStr != null) {
				} else {
					Log.e("ServiceHandler", "Couldn't get any data from the url");
				}

				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				// Dismiss the progress dialog
				if (pDialog.isShowing())
					pDialog.dismiss();
				/**
				 * Updating parsed JSON data into ListView
//				 * */
				
			    	
				Intent i = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(i);
				
				
				
			}

		}
	  
}
