package com.tokbox.android.opentokrtc;

import android.app.Activity;
import android.os.Bundle;

public class ChangePasswordActivity extends Activity {

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_profile_view); 
	        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	        // If your minSdkVersion is 11 or higher, instead use:
	         getActionBar().setDisplayHomeAsUpEnabled(true);
	         

}
}