package com.mrboomdev.binyroid.resources;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;

import androidx.annotation.ArrayRes;
import androidx.annotation.BoolRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.FontRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;

import com.mrboomdev.binyroid.app.BinyApp;

public class BinyValues {

	@NonNull
	public static String getString(@StringRes int res) {
		Context context = BinyApp.getAppContext();
		return context.getString(res);
	}

	public static int getColor(@ColorRes int res) {
		Context context = BinyApp.getAppContext();
		return context.getColor(res);
	}

	public static boolean getBoolean(@BoolRes int res) {
		Resources resources = BinyApp.getResources();
		return resources.getBoolean(res);
	}

	@NonNull
	public static int[] getIntArray(@ArrayRes int res) {
		Resources resources = BinyApp.getResources();
		return resources.getIntArray(res);
	}

	public static float getDimension(@DimenRes int res) {
		Resources resources = BinyApp.getResources();
		return resources.getDimension(res);
	}

	public static float getFloat(int res) {
		Resources resources = BinyApp.getResources();
		return ResourcesCompat.getFloat(resources, res);
	}

	public static Typeface getFont(@FontRes int res) {
		Context context = BinyApp.getAppContext();
		return ResourcesCompat.getFont(context, res);
	}
}