package com.mrboomdev.binyroid.app;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.util.ArrayMap;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.mrboomdev.binyroid.Binyroid;
import com.mrboomdev.binyroid.util.BinyCollections;
import com.mrboomdev.binyroid.util.reflection.BinyReflection;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class BinyApp {

	public static Context getAppContext() {
		Context context = Binyroid.getCache(Binyroid.APP_CONTEXT);
		if(context != null) return context;

		context = BinyReflection.invoke("android.app.AppGlobals", "getInitialApplication");
		Binyroid.putCache(Binyroid.APP_CONTEXT, context);
		return context;
	}

	public static void setTimeout(Runnable runnable, long duration) {
		new Handler().postDelayed(runnable, duration);
	}

	public static Activity getAnyActivity() {
		return getAllActivities().get(0);
	}

	public static Activity getActivity(Class<? extends Activity> clazz) {
		return BinyCollections.find(getAllActivities(), activity ->
				activity.getClass().equals(clazz));
	}

	@NonNull
	public static List<Activity> getAllActivities() {
		Object at = BinyReflection.getField("android.app.ActivityThread", "sCurrentActivityThread");
		ArrayMap<Object, Object> activitiesMap = BinyReflection.getField("mActivities", at);
		Collection<Object> activityInfo = Objects.requireNonNull(activitiesMap).values();

		return BinyCollections.listFrom(activityInfo, info ->
				BinyReflection.getField("activity", info));
	}

	public static void runOnUiThread(Runnable callback) {
		getAnyActivity().runOnUiThread(callback);
	}

	public static Resources getResources() {
		return getAppContext().getResources();
	}

	public static PackageManager getPackageManager() {
		return getAppContext().getPackageManager();
	}

	public static Configuration getConfiguration() {
		return getResources().getConfiguration();
	}

	public static boolean isPortrait() {
		return getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
	}

	public static boolean isLandscape() {
		return getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
	}

	public static Toast makeToast(String text, boolean isLong) {
		Context context = getAppContext();
		return Toast.makeText(context, text, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
	}

	public static Toast makeToast(@StringRes int res, boolean isLong) {
		Context context = getAppContext();
		return Toast.makeText(context, context.getString(res), isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
	}

	public static void showShortToast(String text) {
		makeToast(text, false).show();
	}

	public static void showLongToast(String text) {
		makeToast(text, true).show();
	}
}