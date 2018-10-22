package com.juyao.jmvp.mvp

import android.view.View


/**
 *
 *
 *Created by juyao on 2017/6/28 at 16:00.\n
 * 邮箱:juyao0909@gmail.com
 */
interface VDelegate{
    fun resume()
    fun pause()
    fun destory()
    fun visible(flag:Boolean,view: View)
    fun gone(flag:Boolean,view: View)
    fun inVisible(view:View)
    fun toastShort(msg:String)
}

