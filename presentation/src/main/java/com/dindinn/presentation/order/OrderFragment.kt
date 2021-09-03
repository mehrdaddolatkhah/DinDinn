package com.dindinn.presentation.order

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import com.dindinn.domain.model.OrderDataDetails
import com.dindinn.presentation.R
import com.dindinn.presentation.databinding.FragmentOrderBinding
import com.dindinn.presentation.main.MainViewModel
import com.dindinn.presentation.order.adapter.OrderAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private lateinit var fragmentOrderBinding: FragmentOrderBinding
    private val viewModel: OrderViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getOrders()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentOrderBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)
        fragmentOrderBinding.viewModel = viewModel
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
                )
        })

        viewModel.callAlert.observe(viewLifecycleOwner, Observer { id ->
            // todo : Mehrdad make alert
        })

        fragmentOrderBinding.imgIngredient.setOnClickListener {
            view?.let { view ->
                findNavController(view).navigate(R.id.action_orderFragment_to_ingredientFragment)
            }
        }

    }
}