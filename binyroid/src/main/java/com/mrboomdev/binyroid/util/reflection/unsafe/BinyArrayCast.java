package com.mrboomdev.binyroid.util.reflection.unsafe;

import androidx.annotation.NonNull;

import com.mrboomdev.binyroid.util.BinyException;

import java.lang.reflect.Array;

public class BinyArrayCast {

	@NonNull
	@SuppressWarnings("unchecked")
	public static <T> T[] arrayCast(BinyUnsafe unsafe, @NonNull Class<T> clazz, Object... data) {
		if(clazz.isPrimitive()) {
			throw new BinyException("Can't cast a primitive!");
		}

		T[] out = (T[]) Array.newInstance(clazz, data.length);

		for(int i = 0; i < data.length; i++) {
			unsafe.putObject(
					out,
					unsafe.ARRAY_OBJECT_BASE_OFFSET + ((long) i * unsafe.ARRAY_OBJECT_INDEX_SCALE),
					data[i]);
		}

		return out;
	}
}