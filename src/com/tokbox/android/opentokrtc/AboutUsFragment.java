package com.tokbox.android.opentokrtc;

import com.tokbox.android.opentokrtc.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutUsFragment extends Fragment {
    
   public AboutUsFragment(){}
    
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
 
       View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);
         
       return rootView;
   }
}