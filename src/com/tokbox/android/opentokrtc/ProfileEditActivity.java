package com.tokbox.android.opentokrtc;

import com.tokbox.android.opentokrtc.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileEditActivity extends Activity{
	
	EditText firstName, lastName, emailId, phoneNo, address1, address2, address3, age, gender, bloodGroup ;
	
	Button confirmChanges;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_profile_edit); 
	        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	        // If your minSdkVersion is 11 or higher, instead use:
	         getActionBar().setDisplayHomeAsUpEnabled(true);
	         Toast.makeText(getApplicationContext(), "Remember to confirm changes.", Toast.LENGTH_LONG).show();
	         Intent r=getIntent();
	         
	         firstName=(EditText) findViewById(R.id.firstNameEdit);
	         lastName=(EditText) findViewById(R.id.lastNameEdit);
	         emailId=(EditText) findViewById(R.id.emailIdEdit);
	         phoneNo=(EditText) findViewById(R.id.phoneNoEdit);
	         address1=(EditText) findViewById(R.id.address1Edit);
	         address2=(EditText) findViewById(R.id.address2Edit);
	         address3=(EditText) findViewById(R.id.address3Edit);
	         age=(EditText) findViewById(R.id.ageEdit);
	         gender=(EditText) findViewById(R.id.genderEdit);
	         bloodGroup=(EditText) findViewById(R.id.bloodGroupEdit);
	         
	         firstName.setText(r.getStringExtra("firstName"));
	         lastName.setText(r.getStringExtra("lastName"));
	         emailId.setText(r.getStringExtra("emailId"));
	         phoneNo.setText(r.getStringExtra("phoneNo"));
	         address1.setText(r.getStringExtra("address1"));	         
	         address2.setText(r.getStringExtra("address2"));
	         address3.setText(r.getStringExtra("address3"));
	         age.setText(r.getStringExtra("age"));
	         gender.setText(r.getStringExtra("gender"));
	         bloodGroup.setText(r.getStringExtra("bloodGroup"));
	         
	         confirmChanges= (Button) findViewById(R.id.confirmChanges);
	         
	         confirmChanges.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {
						// Switching to Register screen
						Toast.makeText(getApplicationContext(), "confirm changes", Toast.LENGTH_SHORT).show();
					}
				});

	 
	 }

}
