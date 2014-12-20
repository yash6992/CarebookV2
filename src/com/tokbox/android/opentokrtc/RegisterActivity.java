package com.tokbox.android.opentokrtc;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tokbox.android.opentokrtc.R;
import com.tokbox.android.opentokrtc.LoginActivity.GetContacts;

public class RegisterActivity extends Activity {
	
	EditText fullName,emailId,phoneNo,passwordEditText;
	Button signUpButton;
	CheckBox agree;
	AlertDialogManager alert = new AlertDialogManager();
	private ProgressDialog pDialog;
	ArrayList<NameValuePair> parameters;
	String jsonStr;
	
	// URL to get contacts JSON
		private static String url = "http://cbk-carebook.rhcloud.com/users.json";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        parameters= new ArrayList<NameValuePair>();

        fullName=(EditText) findViewById(R.id.fullName_new);
        emailId=(EditText) findViewById(R.id.emailId_new);
        phoneNo=(EditText) findViewById(R.id.phoneNo_new);
        passwordEditText=(EditText) findViewById(R.id.password_new);
        
        signUpButton=(Button) findViewById(R.id.signUpButton);
        
        agree=(CheckBox) findViewById(R.id.terms_and_policy);

        signUpButton.setOnClickListener(new View.OnClickListener() {
        	    		

        
			@Override
			public void onClick(View arg0) {
				Log.d("Response: ","just clicked");
				// Get username, password from EditText
				String username = emailId.getText().toString();
				String password = passwordEditText.getText().toString();
				Log.d("Response: ","data read");
				// Check if username, password is filled				
				if(username.trim().length() > 0 && password.trim().length() > 0&&agree.isChecked()){
					
					Log.d("Response: ","inside if");
						NameValuePair p1=new BasicNameValuePair("email",username);
						NameValuePair p2=new BasicNameValuePair("password",password);
						Log.d("Response: ","name value pair made");
						
						parameters.add(p1);
						parameters.add(p2);
						// Staring MainActivity

						Log.d("Response: ","name value pair added");
						// Calling async task to get json
						new GetContacts().execute();
				        
//						Intent i = new Intent(getApplicationContext(), MainActivity.class);
//						startActivity(i);
//						finish();
								
				}else{
					// user didn't entered username or password
					// Show alert asking him to enter the details
					alert.showAlertDialog(RegisterActivity.this, "Registration failed..", "Please enter details and agree to policies", false);
				}
				
			}
		});
        
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
                                // Closing registration screen
				// Switching to Login Screen/closing register screen
				finish();
			}
		});
    }
    class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			Log.d("Response: ","in pre execute");
			pDialog = new ProgressDialog(RegisterActivity.this);
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
				jsonStr = sh.makeServiceCall(url, ServiceHandler.POST,parameters);
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
//			 * */
			 Log.e("Response:", jsonStr);
			String status="";int code=2;JSONObject jsonObj=null;
			 if (jsonStr != null) {
		            try {
		                jsonObj = new JSONObject(jsonStr);
		              JSONObject stateObject = jsonObj.getJSONObject("state");
		              //String code=stateObject.getString("code");
		              code=stateObject.getInt("code");
		            		
		            } catch (JSONException e) {
		                e.printStackTrace();
		            }
		        } else {
		            Log.e("ServiceHandler", "Couldn't get any data from the url");
		        } 
			 Log.e("Response:", ""+code);
		    if(code==0){
		    	String authenticationToken="";
		    	try {
					JSONObject dataObject = jsonObj.getJSONObject("data");
					authenticationToken=dataObject.getString("authentication_token");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
			Intent i = new Intent(getApplicationContext(), LoginActivity.class);
			Log.e("Response:", authenticationToken);
		    
			//i.putExtra("jsonStr", jsonStr);
			startActivity(i);
			}
		    else if(code==1)
		    {
		    	String errorMessage="";
		    	try {
					JSONObject stateObject = jsonObj.getJSONObject("state");
					errorMessage=stateObject.getString("messages");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	              
		    	
		    	alert.showAlertDialog(RegisterActivity.this, "Registration failed..", errorMessage, false);
				
		    }
			//finish();
			
			//t1.setText(jsonStr);
		}

	}
}