package com.juyao.jmvp.mvp


/**
 *
 *
 *Created by juyao on 2017/6/28 at 15:47.\n
 * 邮箱:juyao0909@gmail.com
 */
interface  IPresent<V>{
    fun attachV(view:V)
    fun detachV()
}


