package com.mrboomdev.binyroid.ui;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BinyViewList implements List<View> {
	private final ViewGroup parentView;

	public BinyViewList(ViewGroup parentView) {
		this.parentView = parentView;
	}

	@Override
	public int size() {
		return parentView.getChildCount();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(@Nullable Object o) {
		return indexOf(o) != -1;
	}

	@NonNull
	@Override
	public Iterator<View> iterator() {
		return new BinyViewIterator(parentView);
	}

	@NonNull
	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@NonNull
	@Override
	public <T1> T1[] toArray(@NonNull T1[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(View t) {
		try {
			parentView.addView(t);
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean remove(@Nullable Object o) {
		if(!(o instanceof View)) {
			return false;
		}

		View view = (View)o;
		ViewParent parent = view.getParent();

		if(parentView != parent) {
			return false;
		}

		parentView.removeView(view);
		return true;
	}

	@Override
	public boolean containsAll(@NonNull Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(@NonNull Collection<? extends View> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, @NonNull Collection<? extends View> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(@NonNull Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(@NonNull Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		parentView.removeAllViews();
	}

	@Override
	public View get(int index) {
		return parentView.getChildAt(index);
	}

	@Override
	public View set(int index, View element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, View element) {
		parentView.addView(element, index);
	}

	@Override
	public View remove(int index) {
		View view = get(index);
		if(view == null) return null;

		parentView.removeView(view);
		return view;
	}

	@Override
	public int indexOf(@Nullable Object o) {
		if(!(o instanceof View)) {
			return -1;
		}

		for(int i = 0; i < size(); i++) {
			View view = parentView.getChildAt(i);
			if(view == o) return i;
		}

		return -1;
	}

	@Override
	public int lastIndexOf(@Nullable Object o) {
		if(!(o instanceof View)) {
			return -1;
		}

		int found = -1;

		for(int i = 0; i < size(); i++) {
			View view = parentView.getChildAt(i);
			if(view != o) continue;
			found = i;
		}

		return found;
	}

	@NonNull
	@Override
	public ListIterator<View> listIterator() {
		throw new UnsupportedOperationException();
	}

	@NonNull
	@Override
	public ListIterator<View> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@NonNull
	@Override
	public List<View> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}
}