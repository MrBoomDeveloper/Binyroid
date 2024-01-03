package com.mrboomdev.binyroid.ui;

import android.view.View;
import android.view.ViewGroup;

import java.util.Iterator;

public class BinyViewIterator implements Iterator<View> {
	private final ViewGroup view;
	private int current = 0;

	public BinyViewIterator(ViewGroup parentView) {
		this.view = parentView;
	}

	@Override
	public boolean hasNext() {
		return current < view.getChildCount();
	}

	@Override
	public void remove() {
		view.removeViewAt(current);
	}

	@Override
	public View next() {
		View item = view.getChildAt(current);
		current++;
		return item;
	}
}