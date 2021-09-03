package com.dindinn.presentation.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.dindinn.presentation.base.BaseViewModel

class BaseViewHolder<in T, out B : ViewDataBinding>(val binding: B) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T, viewModel: BaseViewModel? = null) {
        if (viewModel != null) binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}