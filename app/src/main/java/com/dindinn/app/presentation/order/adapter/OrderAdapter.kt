package com.dindinn.app.presentation.order.adapter

import android.annotation.SuppressLint
import com.dindinn.app.R
import com.dindinn.app.databinding.ListItemOrderAddOnBinding
import com.dindinn.app.databinding.ListItemOrderBinding
import com.dindinn.app.domain.model.OrderAddOn
import com.dindinn.app.domain.model.OrderDataDetails
import com.dindinn.app.presentation.base.BaseViewModel
import com.dindinn.app.presentation.base.adapter.BaseAdapter
import com.dindinn.app.presentation.base.adapter.BaseViewHolder
import com.dindinn.app.presentation.base.adapter.SingleLayoutAdapter
import com.dindinn.app.presentation.main.MainViewModel
import com.dindinn.app.presentation.order.OrderViewModel
import com.dindinn.app.util.Utils.toMilliSeconds
import com.dindinn.app.util.constants.ConstantValues
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class OrderAdapter(
    private val items: ArrayList<OrderDataDetails>,
    private val viewModel: OrderViewModel,
    private val mainViewModel: MainViewModel
) : BaseAdapter<OrderDataDetails, ListItemOrderBinding>() {

    var globalTime = 0L

    override fun getDefaultViewModel(): BaseViewModel? = viewModel

    override fun getItem(position: Int): OrderDataDetails = items[position]

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.list_item_order
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<OrderDataDetails, ListItemOrderBinding>,
        position: Int
    ) {

        holder.binding.position = position
        super.onBindViewHolder(holder, position)
    }

    private fun removeOrder(position: Int) {
        if (items.size > 0) {
            items.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, items.size)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewDetachedFromWindow(holder: BaseViewHolder<OrderDataDetails, ListItemOrderBinding>) {
        holder.binding.disposable?.dispose()
        super.onViewDetachedFromWindow(holder)
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onViewAttachedToWindow(holder: BaseViewHolder<OrderDataDetails, ListItemOrderBinding>) {
        var orderDispose: Disposable? = null

        val globalTimerObservable = mainViewModel.globalTimerObservable()
        globalTimerObservable
            .subscribeOn(Schedulers.io())
            .subscribe {
                globalTime = it
            }

        orderDispose = Observable
            .interval(0, 1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                if (items.size != 0) {
                    // todo : pass data here inside chain . sometimes exception occurred
                    // Caused by: java.lang.IndexOutOfBoundsException: Index: 1, Size: 1
                    val orderDetails = items[holder.binding.position]

                    // todo : create function and make modular this section
                    if (orderDetails.createdAt?.toMilliSeconds()!! <= globalTime &&
                        orderDetails.expiredAt?.toMilliSeconds()!! >= globalTime
                    ) {
                        val countDown = orderDetails.expiredAt.toMilliSeconds() - globalTime

                        holder.binding.txtOrderItemAutoRejectSecondValue.text =
                            String.format(
                                ConstantValues.COUNTDOWN_TIME_FORMAT,
                                TimeUnit.MILLISECONDS.toMinutes(countDown),
                                TimeUnit.MILLISECONDS.toSeconds(countDown) -
                                        TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(
                                                countDown
                                            )
                                        )
                            )
                    }


                    if (orderDetails.expiredAt?.toMilliSeconds()!! <= globalTime) {
                        holder.binding.btnOrderItemAccept.text = "Expired"
                        holder.binding.btnOrderItemAccept.isEnabled = false
                        removeOrder(holder.binding.position)

                    } else {
                        holder.binding.btnOrderItemAccept.text = "Accept"
                        holder.binding.btnOrderItemAccept.isEnabled = true
                    }
                }
            }

        holder.binding.btnOrderItemAccept.setOnClickListener {
            removeOrder(holder.binding.position)
        }

        items[holder.binding.position].orderAddOn?.let { addOn ->

            when {
                addOn.size == 1 -> {
                    holder.binding.addonSize = "${addOn.size} item"
                }
                addOn.size > 1 -> {
                    holder.binding.addonSize = "${addOn.size} items"
                }
                else -> {
                    holder.binding.txtOrderItemCountValue.visibility = android.view.View.GONE
                }
            }

            holder.binding.adapter =
                SingleLayoutAdapter<OrderAddOn, ListItemOrderAddOnBinding>(
                    R.layout.list_item_order_add_on,
                    addOn,
                    viewModel
                )
        }

        holder.binding.disposable = orderDispose
        super.onViewAttachedToWindow(holder)
    }
}