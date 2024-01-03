package com.mrboomdev.binyroid.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mrboomdev.binyroid.util.callback.ValueReturner1;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BinyCollections {
	public static final Set<?> EMPTY_SET = Collections.EMPTY_SET;
	public static final Map<?, ?> EMPTY_MAP = Collections.EMPTY_MAP;
	public static final List<?> EMPTY_LIST = Collections.EMPTY_LIST;
	public static final Iterator<?> EMPTY_ITERATOR = new EmptyIterator<>();

	@NonNull
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> mapOf(Object... objects) {
		if(objects == null) {
			return (Map<K, V>) Collections.EMPTY_MAP;
		}

		if(objects.length % 2 != 0) {
			throw new BinyException("Number of values isn't even!");
		}

		Map<K, V> map = new HashMap<>(objects.length / 2);
		Object key = null;

		for(Object object : objects) {
			if(key == null) {
				key = object;
				continue;
			}

			map.put((K)key, (V)object);
			key = null;
		}

		return map;
	}

	@Nullable
	public static <T> T find(@NonNull Collection<T> collection, ValueReturner1<T, Boolean> valueReturner) {
		for(T t : collection) {
			if(!valueReturner.get(t)) continue;
			return t;
		}

		return null;
	}

	@NonNull
	public static <T, E> List<T> listFrom(@NonNull Collection<E> collection, ValueReturner1<E, T> valueReturner) {
		List<T> list = new ArrayList<>(collection.size());

		for(E e : collection) {
			list.add(valueReturner.get(e));
		}

		return list;
	}

	@NonNull
	public static <T, E> List<T> listFrom(E[] collection, ValueReturner1<E, T> valueReturner) {
		return listFrom(listOf(collection), valueReturner);
	}

	@SafeVarargs
	@NonNull
	@SuppressWarnings("unchecked")
	public static <T> List<T> listOf(T... obj) {
		if(obj == null) {
			return (List<T>) Collections.EMPTY_LIST;
		}

		return Arrays.asList(obj);
	}

	@SafeVarargs
	@NonNull
	@SuppressWarnings("unchecked")
	public static <T> Set<T> setOf(T... objects) {
		if(objects == null) {
			return (Set<T>) Collections.EMPTY_SET;
		}

		return new HashSet<>(Arrays.asList(objects));
	}

	public static class EmptyIterator<T> implements Iterator<T> {

		@Override
		public boolean hasNext() {
			return false;
		}

		@Nullable
		@Contract(pure = true)
		@Override
		public T next() {
			return null;
		}
	}
}