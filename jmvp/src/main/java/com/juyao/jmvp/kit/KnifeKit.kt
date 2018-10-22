package com.juyao.jmvp.kit

import android.app.Activity
import android.app.Dialog
import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder


/**
 *
 *
 *Created by juyao on 2017/6/29 at 09:37.\n
 * 邮箱:juyao0909@gmail.com
 */
class KnifeKit{
    companion object{
        fun bind(target:Any):Unbinder{
            if(target is Activity){
                return ButterKnife.bind(target)
            }else if(target is Dialog){
                return ButterKnife.bind(target)
            }else if(target is View){
                return ButterKnife.bind(target)
            }
            return  Unbinder.EMPTY
        }
        fun bind(target:Any,source:Any):Unbinder{
            if (source is Activity) {
                return ButterKnife.bind(target, source)
            } else if (source is Dialog) {
                return ButterKnife.bind(target, source)
            } else if (source is View) {
                return ButterKnife.bind(target, source)
            }
            return Unbinder.EMPTY
        }

    }

}

