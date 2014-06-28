package com.example.neon_stingray_test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.neon_stingray_test.global.Constants;
import com.example.neon_stingray_test.global.FragmentHelper;
import com.example.neon_stingray_test.global.FragmentPool;
import com.example.neon_stingray_test.global.Variables;
import com.example.neon_stingray_test.thread.WebRequest;

public class MainScreen extends Activity {

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_main);

		Variables.activity = this;

		FragmentHelper.initFragmentManager();
//		FragmentPool.initFragments();

		final WebRequest webRequest = new WebRequest(Constants.API_SCENARIOS);
		webRequest.execute(new Bundle());

	}
}