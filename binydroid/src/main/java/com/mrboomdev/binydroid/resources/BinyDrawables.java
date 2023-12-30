package com.mrboomdev.binydroid.resources;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.mrboomdev.binydroid.app.BinyApp;

import java.util.Objects;

public class BinyDrawables {

	private BinyDrawables() {}

	public static Drawable getDrawable(@DrawableRes int res) {
		Context context = Objects.requireNonNull(BinyApp.getAppContext());
		return ResourcesCompat.getDrawable(context.getResources(), res, null);
	}

	@NonNull
	public static Drawable getDrawable(@DrawableRes int res, @ColorInt int color) {
		Drawable drawable = copyDrawable(getDrawable(res));
		drawable.setTint(color);
		return drawable;
	}

	@NonNull
	public static Drawable getDrawable(@DrawableRes int res, @Size(min = 3) String color) {
		return getDrawable(res, Color.parseColor(color));
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {
		if(drawable == null) return null;

		Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);

		drawable = copyDrawable(drawable);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);

		return bitmap;
	}

	public static Drawable copyDrawable(Drawable drawable) {
		if(drawable == null) return null;

		Drawable.ConstantState state = drawable.mutate().getConstantState();

		if(state != null) {
			return state.newDrawable();
		}

		return DrawableCompat.wrap(drawable);
	}
}