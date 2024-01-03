package com.mrboomdev.binyroid.util.reflection;

import androidx.annotation.NonNull;

import com.mrboomdev.binyroid.util.BinyException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BinyReflectionInvoker {
	protected Class<?>[] methodArgs;
	protected Object[] targetArgs;
	protected String methodName;
	protected Class<?> clazz;
	protected Method method;
	protected Object target;

	public BinyReflectionInvoker setTarget(Object target) {
		this.target = target;
		return this;
	}

	public BinyReflectionInvoker setMethodArgs(@NonNull List<Class<?>> classes) {
		return setMethodArgs(classes.toArray(new Class[0]));
	}

	public BinyReflectionInvoker setMethodArgs(Class<?>... classes) {
		methodArgs = classes;
		return this;
	}

	public BinyReflectionInvoker setClass(Class<?> clazz) {
		this.clazz = clazz;
		return this;
	}

	public BinyReflectionInvoker setClass(String className) {
		try {
			return setClass(Class.forName(className));
		} catch(ClassNotFoundException e) {
			throw new BinyException("Class was not found!", e);
		}
	}

	public BinyReflectionInvoker setTargetArgs(Object... args) {
		if(args != null && args.length == 0) {
			return this;
		}

		this.targetArgs = args;
		return this;
	}

	public BinyReflectionInvoker setTargetArgs(@NonNull List<Object> args) {
		return setTargetArgs(args.toArray());
	}

	public BinyReflectionInvoker setMethod(String methodName) {
		this.methodName = methodName;
		return this;
	}

	public BinyReflectionInvoker setMethod(Method method) {
		this.method = method;
		return this;
	}

	protected void prepare() {
		if(method != null) return;

		if(clazz == null && target != null) {
			clazz = target.getClass();
		}

		if(methodArgs == null && targetArgs != null) {
			List<Class<?>> classes = new ArrayList<>();

			for(Object obj : targetArgs) {
				if(obj == null) {
					throw new BinyException("Can't get a type of argument because it is null!");
				}

				classes.add(obj.getClass());
			}

			setMethodArgs(classes);
		}

		if(clazz == null) {
			return;
		}

		try {
			method = clazz.getDeclaredMethod(methodName, methodArgs);
		} catch(NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T invoke() {
		try {
			prepare();

			if(method == null) {
				return null;
			}

			method.setAccessible(true);
			return (T) method.invoke(target, targetArgs);
		} catch(IllegalAccessException | InvocationTargetException e) {
			return null;
		}
	}
}