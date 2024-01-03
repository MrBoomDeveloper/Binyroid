package com.mrboomdev.binyroid.ui;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import com.mrboomdev.binyroid.util.BinyCollections;

import org.jetbrains.annotations.Contract;

import java.util.List;

public class BinyView<V extends View> {
	private BinyViewList viewList;
	private final V view;

	public BinyView(V view) {
		this.view = view;
	}

	public void setAlpha(float alpha) {
		view.setAlpha(alpha);
	}

	public void setOnClickListener(Runnable runnable) {
		view.setOnClickListener(_view -> runnable.run());
	}

	public void setClickable(boolean is) {
		view.setClickable(is);
	}

	public void setFocusable(boolean is) {
		view.setFocusable(is);
	}

	public void setOnClickListener(View.OnClickListener listener) {
		view.setOnClickListener(listener);
	}

	public void setOnLongClickListener(View.OnLongClickListener listener) {
		view.setOnLongClickListener(listener);
	}

	public void setPadding(int padding) {
		view.setPadding(padding, padding, padding, padding);
	}

	public void setTopPadding(int padding) {
		view.setPadding(view.getPaddingLeft(), padding, view.getPaddingRight(), view.getPaddingBottom());
	}

	public void setRightPadding(int padding) {
		view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), padding, view.getPaddingBottom());
	}

	public void setLeftPadding(int padding) {
		view.setPadding(padding, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
	}

	public void setBottomPadding(int padding) {
		view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), padding);
	}

	public void setPadding(int horizontal, int vertical) {
		view.setPadding(horizontal, vertical, horizontal, vertical);
	}

	public void setPadding(int left, int top, int right, int bottom) {
		view.setPadding(left, top, right, bottom);
	}

	public float getAlpha() {
		return view.getAlpha();
	}

	public void setBackground(Drawable drawable) {
		view.setBackground(drawable);
	}

	public void setForeground(Drawable drawable) {
		view.setForeground(drawable);
	}

	public void setMargin(int left, int top, int right, int bottom) {
		ViewGroup.LayoutParams params = view.getLayoutParams();

		if(params instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams)params;
			marginParams.setMargins(left, top, right, bottom);
			view.requestLayout();
		}
	}

	public void setRightMargin(int margin) {
		ViewGroup.LayoutParams params = view.getLayoutParams();

		if(params instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams)params;
			marginParams.rightMargin = margin;
			view.requestLayout();
		}
	}

	public void setLeftMargin(int margin) {
		ViewGroup.LayoutParams params = view.getLayoutParams();

		if(params instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams)params;
			marginParams.leftMargin = margin;
			view.requestLayout();
		}
	}

	public void setBottomMargin(int margin) {
		ViewGroup.LayoutParams params = view.getLayoutParams();

		if(params instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams)params;
			marginParams.bottomMargin = margin;
			view.requestLayout();
		}
	}

	public void setTopMargin(int margin) {
		ViewGroup.LayoutParams params = view.getLayoutParams();

		if(params instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams)params;
			marginParams.topMargin = margin;
			view.requestLayout();
		}
	}

	public void setMargin(int margin) {
		setMargin(margin, margin, margin, margin);
	}

	public V getView() {
		return view;
	}

	public Drawable getBackground() {
		return view.getBackground();
	}

	public Drawable getForeground() {
		return view.getForeground();
	}

	@NonNull
	@Contract(pure = true)
	@SuppressWarnings("unchecked")
	public List<View> getChildren() {
		if(view instanceof ViewGroup) {
			if(viewList == null) {
				viewList = new BinyViewList((ViewGroup)view);
			}

			return viewList;
		}

		return (List<View>)BinyCollections.EMPTY_LIST;
	}

	public void setWeight(int weight) {
		ViewGroup.LayoutParams params = view.getLayoutParams();

		if(params instanceof LinearLayout.LayoutParams) {
			((LinearLayout.LayoutParams)params).weight = weight;
		}
	}

	@NonNull
	@Contract(value = "_ -> new", pure = true)
	public static <V extends View> BinyView<V> of(V view) {
		return new BinyView<>(view);
	}

	@NonNull
	@Contract("_, _ -> new")
	public static <V extends View> BinyView<V> of(@NonNull Activity activity, @IdRes int id) {
		return new BinyView<>(activity.findViewById(id));
	}

	@NonNull
	@Contract("_, _ -> new")
	public static <V extends View> BinyView<V> of(@NonNull View view, @IdRes int id) {
		return new BinyView<>(view.findViewById(id));
	}
}