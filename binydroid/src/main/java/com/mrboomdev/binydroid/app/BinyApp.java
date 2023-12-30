package com.mrboomdev.binydroid.app;

import android.content.Context;

import java.lang.reflect.Method;

public class BinyApp {

	@SuppressWarnings("PrivateApi")
	public static Context getAppContext() {
		try {
			Class<?> clazz = Class.forName("android.app.AppGlobals");
			Method method = clazz.getMethod("getInitialApplication");
			return (Context) method.invoke(null);
		} catch(Exception e) {
			throw new RuntimeException("Failed to gain a context via reflection!", e);
		}
	}
}