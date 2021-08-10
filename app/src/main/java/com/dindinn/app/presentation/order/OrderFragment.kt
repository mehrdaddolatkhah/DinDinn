package com.dindinn.app.presentation.order

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import com.dindinn.app.R
import com.dindinn.app.databinding.FragmentOrderBinding
import com.dindinn.app.databinding.ListItemOrderAddOnBinding
import com.dindinn.app.databinding.ListItemOrderBinding
import com.dindinn.app.domain.model.DindinnOrder
import com.dindinn.app.domain.model.OrderAddOn
import com.dindinn.app.domain.model.OrderDataDetails
import com.dindinn.app.presentation.base.adapter.SingleLayoutAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private lateinit var fragmentOrderBinding: FragmentOrderBinding
    private val viewModel: OrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentOrderBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)
        fragmentOrderBinding.viewModel = viewModel

        initAdapter()
        viewModel.getOrders()
        observeOnLiveData()

        return fragmentOrderBinding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeOnLiveData() {
        viewModel.dindinnOrderLiveData.observe(viewLifecycleOwner, Observer { order ->
            order.data?.let { data -> fragmentOrderBinding.adapter?.swapItems(data) }
            handleOrderCountDown(order)
        })

        viewModel.callAlert.observe(viewLifecycleOwner, Observer { id ->
            // todo : Mehrdad make alert
            viewModel.dindinnOrderLiveData.value?.data?.forEachIndexed { index, orderDataDetails ->
                if (id == orderDataDetails.id) {
                    fragmentOrderBinding.recyclerOrder[index]
                }
            }
        })

        viewModel.expireOrder.observe(viewLifecycleOwner, Observer { id ->
            // todo : Mehrdad remove Observer
            viewModel.dindinnOrderLiveData.value?.data?.forEachIndexed { index, orderDataDetails ->
                if (id == orderDataDetails.id) {
                    fragmentOrderBinding.recyclerOrder[index]
                }
            }
        })

        viewModel.shouldNotifyAdapter.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                fragmentOrderBinding.adapter?.notifyDataSetChanged()
            }
        })

        fragmentOrderBinding.imgIngredient.setOnClickListener {
            view?.let { view ->
                findNavController(view).navigate(R.id.action_orderFragment_to_ingredientFragment)
            }
        }

    }

    private fun handleOrderCountDown(order: DindinnOrder) {
        viewModel.startGlobalTimer(order)
    }

    private fun initAdapter() {
        fragmentOrderBinding.adapter =
            SingleLayoutAdapter<OrderDataDetails, ListItemOrderBinding>(
                R.layout.list_item_order,
                arrayListOf(),
                viewModel,
                onBind = {
                    this.item?.orderAddOn?.let { addOn ->

                        when {
                            addOn.size == 1 -> {
                                this.addonSize = "${addOn.size} item"
                            }
                            addOn.size > 1 -> {
                                this.addonSize = "${addOn.size} items"
                            }
                            else -> {
                                txtOrderItemCountValue.visibility = android.view.View.GONE
                            }
                        }

                        this.adapter =
                            SingleLayoutAdapter<OrderAddOn, ListItemOrderAddOnBinding>(
                                R.layout.list_item_order_add_on,
                                addOn,
                                viewModel,
                                onBind = {
                                }
                            )
                    }
                }
            )
    }
}