package com.tokbox.android.opentokrtc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tokbox.android.openrtc.adapter.MySimpleArrayAdapter;
import com.tokbox.android.opentokrtc.R;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsFragment extends ListFragment {
   
	
   public SettingsFragment(){}
    
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
 
       View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
         
       String[] values = new String[] { "Profile", "Change Password"};
    	    // use your custom layout 
       MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(getActivity(), values);
       setListAdapter(adapter);
    	    setListAdapter(adapter);
  	 
       
       return rootView;
   }
@Override
public void onListItemClick(ListView l, View v, int position, long id) {
    String item = (String) getListAdapter().getItem(position);
    if(item.equals("Profile"))
    {
    Intent i=new Intent(getActivity(),ProfileViewActivity.class);
    startActivity(i);
    }
    else if(item.equals("Change Password")){
    Intent i=new Intent(getActivity(),ChangePasswordActivity.class);
    startActivity(i);
    }
    Toast.makeText(getActivity(), item + " selected", Toast.LENGTH_LONG).show();
    
  }


 
}