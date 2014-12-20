package com.tokbox.android.opentokrtc;

import com.tokbox.android.opentokrtc.MainActivity;
import com.tokbox.android.opentokrtc.R;
import com.tokbox.android.opentokrtc.ServiceHandler;
//import com.androidhive.sessions.MainActivity.GetContacts;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	// Email, password edittext
	EditText txtUsername, txtPassword;
	
	// login button
	Button btnLogin;
	
	// Alert Dialog Manager
	AlertDialogManager alert = new AlertDialogManager();
	
	// Session Manager Class
	SessionManager session;
	private ProgressDialog pDialog;
	ArrayList<NameValuePair> parameters;
	

	// URL to get contacts JSON
	private static String url = "http://cbk-carebook.rhcloud.com/users/sign_in";
	
	//Views
	TextView registerTextView; String jsonStr;
	// contacts JSONArray
	JSONArray contacts = null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> contactList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login); 
       
        
        // Session Manager
        session = new SessionManager(getApplicationContext());                
        registerTextView= (TextView) findViewById(R.id.register_textView_button);
        registerTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Get username, password from EditText
				Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
				startActivity(i);
			}
		});
        
        // Email, Password input text
        txtUsername = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password); 
        
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        
        
        // Login button
        btnLogin = (Button) findViewById(R.id.sign_in_button);
        
        contactList = new ArrayList<HashMap<String, String>>();
		parameters= new ArrayList<NameValuePair>();
		
		
		
		// Listview on item click listener
		

		// Calling async task to get json
		//new GetContacts().execute();
        
        
        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Get username, password from EditText
				String username = txtUsername.getText().toString();
				String password = txtPassword.getText().toString();
				
				// Check if username, password is filled				
				if(username.trim().length() > 0 && password.trim().length() > 0){
					
						NameValuePair p1=new BasicNameValuePair("email",username);
						NameValuePair p2=new BasicNameValuePair("password",password);
						
						parameters.add(p1);
						parameters.add(p2);
						// Staring MainActivity


						// Calling async task to get json
						new GetContacts().execute();
				        
//						Intent i = new Intent(getApplicationContext(), MainActivity.class);
//						startActivity(i);
//						finish();
								
				}else{
					// user didn't entered username or password
					// Show alert asking him to enter the details
					alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);
				}
				
			}
		});
    } 

    /**
	 * Async task class to get json by making HTTP call
	 * */
	class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(LoginActivity.this);
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
				jsonStr = sh.makeServiceCall(url, ServiceHandler.POST,parameters);
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
//			 * */
			 Log.e("Response:", jsonStr);
			String status="";
			 if (jsonStr != null) {
		            try {
		                JSONObject jsonObj = new JSONObject(jsonStr);
		               status = jsonObj.getString("status");
		            		
		            } catch (JSONException e) {
		                e.printStackTrace();
		            }
		        } else {
		            Log.e("ServiceHandler", "Couldn't get any data from the url");
		        } 
			 Log.e("Response:", status);
		    if(status.equals("ok")){
		    	
			session.createLoginSession("Carebook",jsonStr,"kucch bhi rakho abhi");
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			//i.putExtra("jsonStr", jsonStr);
			startActivity(i);
			}
		    else
		    {
		    	alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is incorrect", false);
				
		    }
			//finish();
			
			//t1.setText(jsonStr);
		}

	}
	}
