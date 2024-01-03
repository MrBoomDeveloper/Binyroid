package com.mrboomdev.binyroid.util;

public class BinyException extends RuntimeException {

	public BinyException(String text, Throwable t) {
		super(text, t);
	}

	public BinyException(String text) {
		super(text);
	}
}