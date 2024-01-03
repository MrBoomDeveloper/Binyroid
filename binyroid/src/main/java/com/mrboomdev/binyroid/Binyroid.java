package com.mrboomdev.binyroid;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class Binyroid {
	public static final String APP_CONTEXT = "APP_CONTEXT";
	private static Map<String, Object> objects;
	private static boolean isCacheEnabled;

	public static void setCacheEnabled(boolean isEnabled) {
		Binyroid.isCacheEnabled = isEnabled;

		if(!isEnabled) {
			clearCache();
		} else {
			objects = new HashMap<>();
		}
	}

	public static void clearCache() {
		if(objects == null) return;

		objects.clear();
		objects = null;
	}

	public static void putCache(String key, Object obj) {
		if(!isCacheEnabled) return;
		objects.put(key, obj);
	}

	@Nullable
	@SuppressWarnings("unchecked")
	public static <T> T getCache(String key) {
		if(!isCacheEnabled) return null;
		return (T) objects.get(key);
	}

	public static boolean isCacheEnabled() {
		return isCacheEnabled;
	}
}