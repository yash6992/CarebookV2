package com.tokbox.android.opentokrtc;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tokbox.android.opentokrtc.R;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tokbox.android.openrtc.*;

public class HomeFragment extends Fragment {
	
	
   public HomeFragment(){}
    
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
 
       View rootView = inflater.inflate(R.layout.fragment_home, container, false);
         
       TextView t1= (TextView) rootView.findViewById(R.id.textViewTesting);
       
       
       
       t1.setText(".."+MainActivity.authentication_token_user+"..");
       
       
       
       return rootView;
   }
}