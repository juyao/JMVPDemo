package com.juyao.jmvp.mvp

import android.os.Bundle
import android.view.View


/**
 *
 *
 *Created by juyao on 2017/6/28 at 15:53.\n
 * 邮箱:juyao0909@gmail.com
 */
interface IView<out P>{
    fun bindUI(rootView: View?)
    fun initData(savedInstanceState:Bundle?)
    fun getOptionsMenuId():Int
    fun getLayoutId(): Int
    fun newP():P



}


