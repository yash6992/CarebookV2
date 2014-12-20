package com.tokbox.android.opentokrtc;

import com.tokbox.android.opentokrtc.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileViewActivity extends Activity {
	
	TextView firstName, lastName, emailId, phoneNo, address1, address2, address3, age, gender, bloodGroup ;
	
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_profile_view); 
	        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	        // If your minSdkVersion is 11 or higher, instead use:
	         getActionBar().setDisplayHomeAsUpEnabled(true);
	         
	         firstName=(TextView) findViewById(R.id.firstNameView);
	         lastName=(TextView) findViewById(R.id.lastNameView);
	         emailId=(TextView) findViewById(R.id.emailIdView);
	         phoneNo=(TextView) findViewById(R.id.phoneNoView);
	         address1=(TextView) findViewById(R.id.address1View);
	         address2=(TextView) findViewById(R.id.address2View);
	         address3=(TextView) findViewById(R.id.address3View);
	         age=(TextView) findViewById(R.id.ageView);
	         gender=(TextView) findViewById(R.id.genderView);
	         bloodGroup=(TextView) findViewById(R.id.bloodGroupView);

}
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	     // Inflate the menu items for use in the action bar
	     MenuInflater inflater = getMenuInflater();
	     inflater.inflate(R.menu.profile_menu_actions, menu);
	     return super.onCreateOptionsMenu(menu);
	 }

	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     // Handle presses on the action bar items
	     switch (item.getItemId()) {
	         case R.id.action_edit:
	             {
	            	Toast.makeText(getApplicationContext(), "EDIT", Toast.LENGTH_SHORT).show();
	            	 Intent i=new Intent(getApplicationContext(),ProfileEditActivity.class);
	            	 i.putExtra("firstName", firstName.getText().toString());
	            	 i.putExtra("lastName", lastName.getText().toString());
	            	 i.putExtra("phoneNo", phoneNo.getText().toString());
	            	 i.putExtra("emailId", emailId.getText().toString());
	            	 i.putExtra("address1", address1.getText().toString());
	            	 i.putExtra("address2", address2.getText().toString());
	            	 i.putExtra("address3", address3.getText().toString());
	            	 i.putExtra("age", age.getText().toString());
	            	 i.putExtra("gender", gender.getText().toString());
	            	 i.putExtra("bloodGroup", bloodGroup.getText().toString());
	            	 startActivity(i);
	             }
	             return true;
	         default:
	             return super.onOptionsItemSelected(item);
	     }
	 }
}