package com.juyao.jmvp.base


/**
 *
 *
 *Created by juyao on 2017/11/26 at 10:24.\n
 * 邮箱:juyao0909@gmail.com
 */


interface RecyclerItemCallback<T,F> {
    fun onItemClick(position:Int,model:T,tag:Int,holder:F)
    fun onItemLongClick(position:Int,model:T,tag:Int,holder:F)

}