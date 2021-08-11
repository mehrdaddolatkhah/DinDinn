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
import com.dindinn.app.domain.model.OrderDataDetails
import com.dindinn.app.presentation.main.MainViewModel
import com.dindinn.app.presentation.order.adapter.OrderAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.support.v4.runOnUiThread

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private lateinit var fragmentOrderBinding: FragmentOrderBinding
    private val viewModel: OrderViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()

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

    @SuppressLint("NotifyDataSetChanged")
    private fun observeOnLiveData() {
        viewModel.dindinnOrderLiveData.observe(viewLifecycleOwner, Observer { order ->
            fragmentOrderBinding.adapter =
                OrderAdapter(
                    (order.data ?: arrayListOf()) as ArrayList<OrderDataDetails>,
                    viewModel,
                    mainViewModel
                ) {
                    runOnUiThread {
                        viewModel.shouldNotifyAdapter.value = true
                    }
                }
        })

        viewModel.callAlert.observe(viewLifecycleOwner, Observer { id ->
            // todo : Mehrdad make alert
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
}