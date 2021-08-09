package com.dindinn.app.presentation.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.dindinn.app.presentation.base.BaseViewModel

open class SingleLayoutAdapter<T, B : ViewDataBinding>(
    private val layoutId: Int,
    private var items: List<T>,
    private val viewModel: BaseViewModel? = null,
    onBind: B.(Int) -> Unit = {}
) : BaseAdapter<T, B>(onBind) {

    override fun getDefaultViewModel(): BaseViewModel? = viewModel

    override fun getItem(position: Int): T = items[position]

    override fun getLayoutId(viewType: Int): Int = layoutId

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<T, B>, position: Int) {
        super.onBindViewHolder(holder, position)
    }

    fun swapItems(newItems: List<T>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                items[oldItemPosition] == newItems[newItemPosition]

            override fun getOldListSize(): Int =
                items.size

            override fun getNewListSize(): Int =
                newItems.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                items[oldItemPosition] == newItems[newItemPosition]
        })
        diffResult.dispatchUpdatesTo(this)
        items = newItems.toList()
    }
}