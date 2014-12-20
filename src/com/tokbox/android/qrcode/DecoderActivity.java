package com.tokbox.android.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView.OnQRCodeReadListener;
import com.tokbox.android.opentokrtc.R;
import com.tokbox.android.opentokrtc.HomeActivity;

public class DecoderActivity extends Activity implements OnQRCodeReadListener {

	private QRCodeReaderView mydecoderview;
	private ImageView line_image;
	public static final String RESULT = "result";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_decoder);

		mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
		mydecoderview.setOnQRCodeReadListener(this);

		line_image = (ImageView) findViewById(R.id.red_line_image);

		TranslateAnimation mAnimation = new TranslateAnimation(
				TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE,
				0f, TranslateAnimation.RELATIVE_TO_PARENT, 0f,
				TranslateAnimation.RELATIVE_TO_PARENT, 0.5f);
		mAnimation.setDuration(1000);
		mAnimation.setRepeatCount(-1);
		mAnimation.setRepeatMode(Animation.REVERSE);
		mAnimation.setInterpolator(new LinearInterpolator());
		line_image.setAnimation(mAnimation);

	}

	// Called when a QR is decoded
	// "text" : the text encoded in QR
	// "points" : points where QR control points are placed
	@Override
	public void onQRCodeRead(String text, PointF[] points) {
		Intent intent = new Intent(this, HomeActivity.class);
		intent.putExtra(RESULT, text.trim());
		DecoderActivity.this.finish();
		startActivity(intent);
	}

	// Called when your device have no camera
	@Override
	public void cameraNotFound() {

	}

	// Called when there's no QR codes in the camera preview image
	@Override
	public void QRCodeNotFoundOnCamImage() {

	}

	@Override
	protected void onResume() {
		super.onResume();
		mydecoderview.getCameraManager().startPreview();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mydecoderview.getCameraManager().stopPreview();
	}
}
