package com.mrboomdev.binyroid.util.reflection.unsafe;

import com.mrboomdev.binyroid.util.BinyCollections;
import com.mrboomdev.binyroid.util.reflection.BinyReflection;

public class BinyUnsafe {
	public final int ARRAY_OBJECT_BASE_OFFSET, ARRAY_OBJECT_INDEX_SCALE;
	private final Class<?> unsafeClass;
	private final Object theUnsafe;

	public BinyUnsafe() {
		unsafeClass = BinyReflection.getClass("sun.misc.Unsafe");
		theUnsafe = BinyReflection.getField(unsafeClass, "theUnsafe");

		ARRAY_OBJECT_BASE_OFFSET = arrayBaseOffset(Object[].class);
		ARRAY_OBJECT_INDEX_SCALE = arrayBaseOffset(Object[].class);
	}

	public Object getUnsafe() {
		return theUnsafe;
	}

	public <T> void setValue(Object target, Class<T> targetClassMirror, int fieldIndex, String fieldName, Object newValue) {
		T[] t = BinyArrayCast.arrayCast(this, targetClassMirror, target);
		T a = t[fieldIndex];
		BinyReflection.putObject(a, fieldName, newValue);
	}

	public <T> T invoke(Object target, Class<T> targetClassMirror, int methodIndex, String methodName, Object... args) {
		T[] t = BinyArrayCast.arrayCast(this, targetClassMirror, target);
		T a = t[methodIndex];

		return BinyReflection.getInvoker()
				.setTarget(a)
				.setMethod(methodName)
				.setTargetArgs(args)
				.invoke();
	}

	public <T> T createInstanceWithoutConstructor(Class<T> clazz) {
		return BinyReflection.getInvoker()
				.setTarget(theUnsafe)
				.setMethod("allocateInstance")
				.setMethodArgs(BinyCollections.listOf(Class.class))
				.setTargetArgs(clazz)
				.invoke();
	}

	public void putObject(Object o, long offset, Object x) {
		BinyReflection.getInvoker()
				.setClass(unsafeClass)
				.setTarget(theUnsafe)
				.setMethod("putObject")
				.setMethodArgs(Object.class, long.class, Object.class)
				.setTargetArgs(o, offset, x)
				.invoke();
	}

	public int arrayBaseOffset(Class<?> clazz) {
		return BinyReflection.getInvoker()
				.setTarget(theUnsafe)
				.setMethod("arrayBaseOffset")
				.setMethodArgs(Class.class)
				.setTargetArgs(clazz)
				.invoke();
	}

	public int arrayIndexScale(Class<?> clazz) {
		return BinyReflection.getInvoker()
				.setTarget(theUnsafe)
				.setMethod("arrayIndexScale")
				.setMethodArgs(Class.class)
				.setTargetArgs(clazz)
				.invoke();
	}
}