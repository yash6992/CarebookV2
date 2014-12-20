package com.tokbox.android.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tokbox.android.opentokrtc.R;

public class Screen1_SplashScreen extends Activity implements OnClickListener {

	private Button button_scan;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen1_splash_screen);
		button_scan = (Button) findViewById(R.id.button1);
		button_scan.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, DecoderActivity.class);
		startActivity(intent);
	}

}
