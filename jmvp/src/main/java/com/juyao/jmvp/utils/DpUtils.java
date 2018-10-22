package com.juyao.jmvp.utils;

import android.content.Context;

/**
 * @author juyao
 * @date 2018/1/7
 * @email juyao0909@gmail.com
 */

public class DpUtils {
    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    static public int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
