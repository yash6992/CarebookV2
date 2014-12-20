package com.tokbox.android.opentokrtc;

import java.util.ArrayList;
import com.tokbox.android.opentokrtc.R;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class DocumentsFragment extends Fragment {
ListView listView;
	
	ArrayList< String>arrayList; // list of the strings that should appear in ListView
	ArrayAdapter<String> arrayAdapter; // a middle man to bind ListView and array list 
	
	String selectedDoctor;
    
   public DocumentsFragment(){}
    
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
 
       View rootView = inflater.inflate(R.layout.fragment_documents, container, false);
       listView = (ListView) rootView.findViewById(R.id.lstDemo);
       selectedDoctor="";
       arrayList = new ArrayList<String>();
       arrayList.add("Dr. Pran Gupta");
       arrayList.add("Dr. Rakesh Kumar");
       arrayList.add("Dr. Stephen Daniel");
       
       arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_single_choice,arrayList);
       listView.setAdapter(arrayAdapter);
       
       
       listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long itemId) {
				
				CheckedTextView textView = (CheckedTextView) view;
				for (int i = 0; i < listView.getCount(); i++) {
					textView= (CheckedTextView) listView.getChildAt(i);
					if (textView != null) {
						//textView.setTextColor(Color.WHITE);
					}
					
				}
				listView.invalidate();
				textView = (CheckedTextView) view;
				if (textView != null) {
					//textView.setTextColor(Color.BLUE);
					selectedDoctor=textView.getText().toString();
				}
				Toast.makeText(getActivity(), "Doctor: " + selectedDoctor, Toast.LENGTH_LONG).show();
				 

			}
			   });
       
       
       
       return rootView;
   }
}