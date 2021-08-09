package com.dindinn.app.presentation.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dindinn.app.presentation.base.BaseViewModel

abstract class BaseAdapter<T, B : ViewDataBinding>(var onBind: B.(Int) -> Unit = {}) :
    androidx.recyclerview.widget.RecyclerView.Adapter<BaseViewHolder<T, B>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, B> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: B = DataBindingUtil.inflate(inflater, getLayoutId(viewType), parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, B>, position: Int) {
        holder.bind(getItem(position), getDefaultViewModel())
        holder.binding.onBind(position)
    }

    abstract fun getDefaultViewModel(): BaseViewModel?

    abstract fun getItem(position: Int): T

    abstract fun getLayoutId(viewType: Int): Int

    interface OnItemClickListener<T> {
        fun onItemClick(view: View, item: T, position: Int = -1)
    }
}