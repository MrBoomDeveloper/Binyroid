package com.mrboomdev.binyroid.resources;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.mrboomdev.binyroid.app.BinyApp;

public class BinyValues {

	@NonNull
	public static String getString(@StringRes int res) {
		Context context = BinyApp.getAppContext();
		return context.getString(res);
	}
}