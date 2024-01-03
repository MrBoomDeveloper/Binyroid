package com.mrboomdev.binyroid.visual_test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mrboomdev.binyroid.Binyroid;
import com.mrboomdev.binyroid.app.BinyApp;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Binyroid.setCacheEnabled(true);

		findViewById(R.id.checkAppContext).setOnClickListener(_view -> {
			boolean areEquals = BinyApp.getAppContext() == getApplicationContext();
			BinyApp.showShortToast(areEquals ? "Got context successfully!" : "Failed to get app context!");
		});

		findViewById(R.id.checkPackageManager).setOnClickListener(_view -> {
			BinyApp.showShortToast("Orientation: " + (BinyApp.isLandscape() ? "landscape" : "portrait"));
		});

		System.out.println(BinyApp.getActivity(getClass()) == this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Binyroid.clearCache();
	}
}