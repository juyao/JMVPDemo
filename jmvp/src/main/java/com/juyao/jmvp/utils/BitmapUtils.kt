package com.juyao.jmvp.utils

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.support.v4.graphics.drawable.DrawableCompat

/**
 * Created by juyao on 2017/11/29.
 */
class BitmapUtils {
    companion object {
        fun tintDrawable(drawable: Drawable, colors: ColorStateList): Drawable {
            val wrappedDrawable = DrawableCompat.wrap(drawable)
            DrawableCompat.setTintList(wrappedDrawable, colors)
            return wrappedDrawable
        }
    }
}