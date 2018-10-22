package com.juyao.jmvp.base

import android.content.Context
import android.support.v7.widget.RecyclerView


/**
 *
 *
 *Created by juyao on 2017/11/26 at 09:36.\n
 * 邮箱:juyao0909@gmail.com
 */


abstract class BaseRecAdapter<T,F:RecyclerView.ViewHolder>: RecyclerView.Adapter<F>{
    lateinit var mContext:Context
    var dataList= ArrayList<T>()
    lateinit var recItemClick:RecyclerItemCallback<T,F>
    constructor(context: Context){
        mContext=context
    }

    /**
     * 设置数据源
     */
    fun setData(data:List<T>){
        dataList.clear()
        dataList?.addAll(data)
        notifyDataSetChanged()
    }
    /**
     * 添加数据
     */
    fun addData(data:List<T>){
        val preSize = dataList.size
        if(data.isNotEmpty()){
            if(dataList==null){
                dataList=ArrayList<T>()
            }
            dataList.addAll(data)
            notifyItemRangeInserted(preSize, dataList.size)
        }

    }

    /**
     * 添加元素
     *
     * @param element
     */
    fun addElement(element: T?) {
        element?.let {
            if (dataList == null) {
                dataList=ArrayList()
            }
            dataList.add(it)
            notifyItemInserted(dataList.size)
        }

    }

    fun addElement(position: Int, element: T?) {
        element?.let {
            if (dataList == null) {
                dataList = ArrayList()
            }
            dataList.add(position, it)
            notifyItemInserted(position)
        }

    }



    /**
     * 删除元素
     */
    fun deleteElement(element: T){
        if(dataList.contains(element)){
            val position = dataList.indexOf(element)
            dataList.remove(element)
            notifyItemRemoved(position)
            notifyItemChanged(position)
        }
    }

    /**
     * 删除元素
     *
     * @param position
     */
    fun removeElement(position: Int) {
        if (dataList.isNotEmpty()) {
            dataList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemChanged(position)
        }
    }

    /**
     * 删除元素
     *
     * @param elements
     */
    fun removeElements(elements: List<T>?) {
        if (dataList != null && elements != null && elements.isNotEmpty()
                && dataList.size >= elements.size) {
            elements.forEach {
                if(dataList.contains(it)){
                    dataList.remove(it)
                }
            }

            notifyDataSetChanged()
        }
    }

    /**
     * 更新元素
     *
     * @param element
     * @param position
     */
    fun updateElement(element: T, position: Int) {
        if (position >= 0 && dataList.size > position) {
            dataList.removeAt(position)
            dataList.add(position, element)
            notifyItemChanged(position)
        }
    }

    /**
     * 清除数据源
     */
    fun clearData() {
        dataList?.apply {
            clear()
            notifyDataSetChanged()

        }

    }

    /**
     * 获取数据源
     *
     * @return
     */
    fun getDataSource(): List<T> {
        return dataList
    }

    override fun getItemCount(): Int {
        return if(dataList.isEmpty()){
            0
        }else{
            dataList.size
        }
    }


}
