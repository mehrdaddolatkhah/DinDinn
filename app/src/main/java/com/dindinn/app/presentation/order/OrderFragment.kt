package com.dindinn.app.presentation.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        viewModel.getOrders()
        observeOnLiveData()

        return fragmentOrderBinding.root
    }

    private fun observeOnLiveData() {
        viewModel.dindinnOrderLiveData.observe(viewLifecycleOwner, Observer {
            setOrderAdapters(it)
            handleOrderCountDown(it)
        })

        viewModel.callAlert.observe(viewLifecycleOwner, Observer {

        })

        viewModel.expireOrder.observe(viewLifecycleOwner, Observer {

        })

        viewModel.shouldNavigateToIngredientScreen.observe(viewLifecycleOwner, Observer {
            if (it)
                view?.let { it1 -> findNavController(it1).navigate(R.id.action_orderFragment_to_ingredientFragment) };
        })
    }

    private fun setOrderAdapters(order: DindinnOrder) {
        fragmentOrderBinding.adapter =
            SingleLayoutAdapter<OrderDataDetails, ListItemOrderBinding>(
                R.layout.list_item_order,
                order.data ?: arrayListOf(),
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

    private fun handleOrderCountDown(order: DindinnOrder) {

        viewModel.startGlobalTimer(order)
    }
}