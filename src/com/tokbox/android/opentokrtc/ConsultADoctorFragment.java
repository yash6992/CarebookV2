package com.tokbox.android.opentokrtc;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tokbox.android.opentokrtc.R;
import com.tokbox.android.qrcode.DecoderActivity;

public class ConsultADoctorFragment extends Fragment {

	ImageView qrCodeImageButton;

	public ConsultADoctorFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_consult_a_doctor,
				container, false);

		qrCodeImageButton = (ImageView) rootView.findViewById(R.id.qrCodeImage);
		qrCodeImageButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getActivity(), DecoderActivity.class);
				startActivity(i);
			}
		});

		return rootView;
	}
}