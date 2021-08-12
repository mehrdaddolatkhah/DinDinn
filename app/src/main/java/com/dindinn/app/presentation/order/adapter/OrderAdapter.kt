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

    @SuppressLint("CheckResult", "NotifyDataSetChanged", "SetTextI18n")
    override fun onBindViewHolder(
        holder: BaseViewHolder<OrderDataDetails, ListItemOrderBinding>,
        position: Int
    ) {

        var orderDispose: Disposable? = null

        val globalTimerObservable = mainViewModel.globalTimerObservable()
        globalTimerObservable
            .subscribe {
                globalTime = it
            }

        orderDispose = Observable.interval(0, 1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                if (items.size != 0) {
                    val orderDetails = items[position]

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
                        //orderDispose?.let { removeOrder(it, position) }

                    } else {
                        holder.binding.btnOrderItemAccept.text = "Accept"
                        holder.binding.btnOrderItemAccept.isEnabled = true
                    }
                }
            }

        holder.binding.btnOrderItemAccept.setOnClickListener {
            //orderDispose?.let { removeOrder(it, position) }
        }

        items[position].orderAddOn?.let { addOn ->

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

        super.onBindViewHolder(holder, position)
    }

    private fun removeOrder(orderDispose: Disposable, position: Int) {
        if (!orderDispose.isDisposed && items.size > 0) {
            orderDispose.dispose()
            items.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, items.size)
        }
    }
}