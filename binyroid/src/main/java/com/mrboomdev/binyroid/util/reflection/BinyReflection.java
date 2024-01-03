package com.mrboomdev.binyroid.util.reflection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.Contract;

import java.lang.reflect.Field;
import java.util.List;

public class BinyReflection {

	@Nullable
	@SuppressWarnings("unchecked")
	public static <T> T getField(Class<T> clazz, String fieldName, Object target) {
		if(clazz == null) return null;

		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			return (T) field.get(target);
		} catch(NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void putObject(Object target, @NonNull Class<?> clazz, String fieldName, Object newValue) {
		Field field;

		try {
			field = clazz.getDeclaredField(fieldName);
		} catch(NoSuchFieldException e) {
			e.printStackTrace();
			return;
		}

		field.setAccessible(true);

		try {
			field.set(target, newValue);
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void putObject(@NonNull Class<?> clazz, String fieldName, Object newValue) {
		putObject(null, clazz, fieldName, newValue);
	}

	public static void putObject(Object target, String fieldName, Object newValue) {
		putObject(target, target.getClass(), fieldName, newValue);
	}

	public static int getInteger(Class<?> clazz, String fieldName, Object target) {
		if(clazz == null) return 0;

		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.getInt(target);
		} catch(NoSuchFieldException | IllegalAccessException e) {
			return 0;
		}
	}

	public static int getInteger(Class<?> clazz, String fieldName) {
		return getInteger(clazz, fieldName, null);
	}

	@Nullable
	public static Class<?> getClass(String name) {
		try {
			return Class.forName(name);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Nullable
	public static <T> T getField(Class<T> clazz, String fieldName) {
		return getField(clazz, fieldName, null);
	}

	@Nullable
	@SuppressWarnings("unchecked")
	public static <T> T getField(String fieldName, Object target) {
		return (T) getField(target.getClass(), fieldName, target);
	}

	@Nullable
	@SuppressWarnings("unchecked")
	public static <T> T getField(String className, String fieldName, Object target) {
		return (T) getField(getClass(className), fieldName, target);
	}

	@Nullable
	public static <T> T getField(String className, String fieldName) {
		return getField(className, fieldName, null);
	}

	@NonNull
	@Contract(value = " -> new", pure = true)
	public static BinyReflectionInvoker getInvoker() {
		return new BinyReflectionInvoker();
	}

	public static <T> T invoke(String className, String methodName, Object... args) {
		return new BinyReflectionInvoker()
				.setTargetArgs(args)
				.setClass(className)
				.setMethod(methodName)
				.invoke();
	}

	public static <T> T invoke(String className, String methodName, List<Class<?>> classes, List<Object> args) {
		return new BinyReflectionInvoker()
				.setMethodArgs(classes)
				.setTargetArgs(args)
				.setClass(className)
				.setMethod(methodName)
				.invoke();
	}
}