package com.juyao.jmvp.mvp

import android.content.Context
import android.view.View
import android.widget.Toast


/**
 *
 *
 *Created by juyao on 2017/6/28 at 16:09.\n
 * 邮箱:juyao0909@gmail.com
 */
class VDelegateBase(val context: Context):VDelegate{
    companion object{
        @JvmStatic
        fun create(context: Context):VDelegate{
            return VDelegateBase(context)
        }
    }
    override fun resume() {

    }

    override fun pause() {
    }

    override fun destory() {

    }

    override fun visible(flag: Boolean, view: View) {
        if(flag)view.visibility=View.VISIBLE
    }

    override fun gone(flag: Boolean, view: View) {
        if(flag)view.visibility=View.GONE
    }

    override fun inVisible(view: View) {
        view.visibility=View.INVISIBLE
    }

    override fun toastShort(msg: String) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

}


