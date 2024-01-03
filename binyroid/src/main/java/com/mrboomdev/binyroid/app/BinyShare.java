package com.mrboomdev.binyroid.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

public class BinyShare {

	public static void share(String title, String text) {
		Context context = BinyApp.getAppContext();
		Intent intent = new Intent(Intent.ACTION_SEND);

		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, title);
		intent.putExtra(Intent.EXTRA_TEXT, text);

		Intent chooser = Intent.createChooser(intent, title);
		chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		context.startActivity(chooser);
	}

	public static void copyToClipboard(String text) {
		Context context = BinyApp.getAppContext();
		ClipboardManager manager = context.getSystemService(ClipboardManager.class);

		ClipData clipData = ClipData.newPlainText(text, text);
		manager.setPrimaryClip(clipData);
	}
}